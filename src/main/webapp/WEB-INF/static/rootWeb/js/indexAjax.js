$(document).ready(function() {
    var path = $("#path").val();

    getGameTypes();//获取游戏类型参数
    getHotCards();//获取热销榜单的点卡信息
    getNewCards();//获取最新上架的点卡信息
    getCarousel();//获取轮播广告信息
    getWindoadvertising();//加载弹窗广告
    getCardCarousel();//获取首页咨询信息
    getPage(1,1,"");

    function getGameTypes() {
        $.ajax({
            type:"GET",//请求类型
            url:path+"/static/getGameType",//请求的url
            dataType:"json",//ajax接口（请求url）返回的数据类型
            async: false , //true异步，false同步
            success:function(data){//data：返回数据（json对象）
                $("#gameType").html("");
                var div = "";
                //循环遍历集合
                for(var i=0;i<data.length;i++){
                    var sta=data[i];
                    div+="<div class='book_sort_bottom' href=\"javascript:;\" tid='" + sta.tId + "'><a href=\"javascript:;\" tid='" + sta.tId + "'>"+sta.tName+"</a></div>";
                }
                $("#gameType").html(div);
            }
        });
    }

    //点击游戏类型改变点卡列表页数
    $(".book_sort_bottom").click(function(){
        $("#info").val("");
        $("#info").html("");
        var obj = $(this).attr("tid");
        getPage(obj,1,"");
    });

   /* /!**
     * 刷新搜索框的函数
     *!/
    function clearSearch() {
        /!*$(".header_middle").remove($(".logo"));
        $(".header_middle").remove($(".search"));*!/
        $(".header_middle").html("");//点击会清空搜索框，不然点击下一页会混淆数据
        $(".header_middle").html("<div class=\"logo\"><img src='/static/rootWeb/images/logo.png' alt='logo'/></div>" +
            "<div class=\"search\"><input type=\"text\" id=\"info\" placeholder=\"请输入搜索关键字\"><input type=\"button\" id=\"searchInfo\"></div>");
    }
*/
    //获得分页得函数
    function getPage(obj,num,info) {
        $.ajax({
            type: "GET",//请求类型
            url: path+"/static/getCards",//请求的url
            data: {id: obj,index:num,info:info},//请求参数
            dataType: "json",//ajax接口（请求url）返回的数据类型
            async: false , //true异步，false同步
            success: function (data) {//data：返回数据（json对象）
                loadjscssfile('/static/rootWeb/css/layout.css', "");
                loadjscssfile('/static/rootWeb/js/indextwo.js', "js"); //加载js文件，会加速图片的轮播
                $("#cards").html("");
                var div = "";
                for (var i = 0; i < data.newList.length; i++) {
                    div += "<li><a class='byCard' href="+path+"/static/toCardView/"+ data.newList[i].cId + "/"+data.newList[i].gid+">"+
                        "<img class='radius' src=/static/rootWeb/images/" + data.newList[i].titleImg + " ></a>" +
                        "<p>"+data.newList[i].gName+"</p>" +
                        "<p><s>"+data.newList[i].iniPrice+"</s>&nbsp;&nbsp;<span style='color: red'>实售:￥"+data.newList[i].price+"</span></p>" +
                        "<p><a class='byCard' href="+path+"/static/toCardView/"+ data.newList[i].cId + "/"+data.newList[i].gid+">立刻购买</a></p>" +
                        "</li>";

                }
                div+="<input type='hidden' id='totalPageCount' value="+data.totalPageCount+">";

                $("#cards").html(div);
                $("#cards").next().html("");
                $(".page-bar").remove();//错误点，必须加上这句清空
                var page="<div class='page-bar'>"+
                    "<p class='page-num-ul'>"+
                    "<span>&nbsp;&nbsp;共"+data.totalCount+"条记录</span>"+
                    "<span>&nbsp;&nbsp;"+data.currPageNo+"/"+data.totalPageCount+"页</span>";
                if(data.currPageNo>1){
                    page+="<a href=\"javascript:;\" id='1' class='first' >首页</a>"+
                        "<a href=\"javascript:;\" class='pre' id='"+(data.currPageNo-1)+"'>上一页</a>";
                }
                if(data.currPageNo < data.totalPageCount ){
                    page+="<a href=\"javascript:;\" class='next'  id='"+(data.currPageNo+1)+"'>下一页</a>"+
                        "<a href=\"javascript:;\" class='last'  id='"+data.totalPageCount+"'>最后一页</a>";
                }
                page+="<span class='page-go-form'><label>跳转至</label>"+
                    "<input type='text' name='inputPage' id='inputPage' class='page-key' />页"+
                    "<button type='button' class='page-btn' id='page-btn'>GO</button>"+
                    "</span></p></div>";
                $("#cards").after(page);
                /*loadjscssfile('/static/rootWeb/css/layout.css', "css");
                loadjscssfile('/static/rootWeb/css/rollpage.css', "css"); //加载css文件*/
            },
            error: function (data) {//当访问时候，404，500 等非200的错误状态码
                alert("加载分页信息失败！");
            }
        });
    }

    /**
     * 点击上下页
     */
    $(document).on("click",".last,.first,.pre,.next",function(){
        var pageNo=this.id;
        var info=$("#info").val();
        getPage(0,pageNo,info);
    })

    //输入页数
    $(document).on("click",".page-btn",function(){
        var info=$("#info").val();
        var regexp=/^[1-9]\d*$/;
        var totalPageCount = $("#totalPageCount").val();
        var index=$("#inputPage").val();
        if(!regexp.test(index)){
            alert("请输入大于0的正整数！");
            return false;
        }else if((index-totalPageCount) > 0){
            alert("请输入小于总页数的页码");
            return false;
        }else{
            getPage(0,index,info);
        }
    });

    //模糊查询点卡信息，包括（点卡名称，点卡面值，游戏类型名称，游戏名称）
    $("#searchInfo").click(function(){
        var info=$("#info").val();
        num=1;//这个不加跟页码有冲突
        getPage(0,num,info);
    });

    //动态加载 js /css
    function loadjscssfile(filename, filetype) {
        if (filetype == "js") { //判定文件类型
            var fileref = document.createElement('script')//创建标签
            fileref.setAttribute("type", "text/javascript")//定义属性type的值为text/javascript
            fileref.setAttribute("src", filename)//文件的地址
        }
        else if (filetype == "css") { //判定文件类型
            var fileref = document.createElement("link")
            fileref.setAttribute("rel", "stylesheet")
            fileref.setAttribute("type", "text/css")
            fileref.setAttribute("href", filename)
        }
        if (typeof fileref != "undefined")
            document.getElementsByTagName("head")[0].appendChild(fileref)
    }

    /**
     * 获取销售点卡的榜单
     */
    function getHotCards() {
        $.ajax({
            type: "GET",//请求类型
            url: path+"/static/getCardSalTop",//请求的url
            dataType: "json",//ajax接口（请求url）返回的数据类型
            async: false , //true异步，false同步
            success: function (data) {//data：返回数据（json对象）
                loadjscssfile('/static/rootWeb/css/layout.css', "");
                loadjscssfile('/static/rootWeb/js/indextwo.js', "js"); //加载js文件，会加速图片的轮播
                $("#topHot").html("");
                var div = "";
                for (var i = 0; i < data.length; i++) {
                    div += "<li><span>"+(i+1)+"</span><p><a href=\"javascript:;\" class='rankings'  >"+data[i].cName+"</a></p><dl><dt>"+
                    "<img class='radius' src=/static/rootWeb/images/" + data[i].titleImg + ">"+
                    "</dt><dd><h1>"+data[i].cName+"</h1>"+data[i].cInfo+"</dd></dl></li>";
                }
                $("#topHot").html(div);
            },
            error: function (data) {//当访问时候，404，500 等非200的错误状态码
                alert("加载点卡销售榜单失败！");
            }
        });
    }
    //获取最新上架的点卡信息
    function getNewCards(){
        $.ajax({
            type: "GET",//请求类型
            url: path+"/static/getNewCards",//请求的url
            dataType: "json",//ajax接口（请求url）返回的数据类型
            async: false , //true异步，false同步
            success: function (data) {//data：返回数据（json对象）
                loadjscssfile('/static/rootWeb/js/indextwo.js', "js"); //加载js文件，会加速图片的轮播
                $("#topNew").html("");
                var div = "";
                for (var i = 0; i < data.length; i++) {
                    div += "<li><span>"+(i+1)+"</span><p><a href=\"javascript:;\" class='rankings' >"+data[i].cName+"</a></p><dl><dt>"+
                        "<img class='radius' src=/static/rootWeb/images/" + data[i].titleImg + ">"+
                        "</dt><dd><h1>"+data[i].cName+"</h1>"+data[i].cInfo+"</dd></dl></li>";
                }
                $("#topNew").html(div);
            },
            error: function (data) {//当访问时候，404，500 等非200的错误状态码
                alert("加载最新点卡信息失败！");
            }
        });
    }

    /**
     * 点击最新、热销点卡榜单显示点卡信息
     */
    $(".rankings").click(function () {
        var info=$(this).html();
        $("#info").val("");
        $("#info").val(info);
        num=1;//这个不加跟页码有冲突
        getPage(0,num,info);
    });

    //获取轮播广告信息
    function getCarousel() {
        $.ajax({
            type: "GET",//请求类型
            url: path + "/static/getCarousel",//请求的url
            dataType: "json",//ajax接口（请求url）返回的数据类型
            async: false, //true异步，false同步
            success: function (data) {//data：返回数据（json对象）
                loadjscssfile('/static/rootWeb/js/Carousel.js', "js"); //加载js文件，会加速图片的轮播
                $("#scroll_img").html("");
                for (var i = 0; i < 4; i++) {//
                    var li="<li><a target='_blank' href=" + data[i].from + " ><img src=/static/rootWeb/images/" + data[i].npath + "></a></li>";
                    $("#scroll_img").append(li);
               }
            },
            error: function (data) {//当访问时候，404，500 等非200的错误状态码
                alert("加载轮播广告失败！");
            }
        });
    }

    /**
     * 获取弹窗广告
     */
    function getWindoadvertising(){
        $.ajax({
            type: "GET",//请求类型
            url: path + "/static/getWindoadvertising",//请求的url
            dataType: "json",//ajax接口（请求url）返回的数据类型
            async: false, //true异步，false同步
            success: function (data) {//data：返回数据（json对象）
                loadjscssfile('/static/rootWeb/js/indextwo.js', "js"); //加载js文件，会加速图片的轮播
                $("#right").html("");
                var div = "";
                div+="<div class=\"dd_close\" id=\"dd_close\"><a href=\"javascript:void(0);\">关闭</a></div>" +
                    "<a href=" + data.from + " target=\"_blank\"><img id='right1' src=/static/rootWeb/images/" + data.npath + "></a>";

                $("#right").html(div);
            },
            error: function (data) {//当访问时候，404，500 等非200的错误状态码
                alert("加载弹窗广告失败！");
            }
        });
    }

    //获取首页咨询信息
    function getCardCarousel() {
        $.ajax({
            type: "GET",//请求类型
            url: path + "/static/getCardCarousel",//请求的url
            dataType: "json",//ajax接口（请求url）返回的数据类型
            async: false, //true异步，false同步
            success: function (data) {//data：返回数据（json对象）
                loadjscssfile('/static/rootWeb/js/Consultation.js', "js"); //加载js文件，会加速图片的轮播
                $("#express").html("");
                var div = "";
                for (var i = 0; i < data.length; i++) {
                    div += "<li ><a href="+path+"/static/toNewsView/"+ data[i].nId + ">" + data[i].title + "</a></li>";
                }
                $("#express").html(div);
            },
            error: function (data) {//当访问时候，404，500 等非200的错误状态码
                alert("加载轮播咨询失败！");
            }
        });
    }
});

