$(document).ready(function(){

    var path=$("#path").val();
    getGamesByPant();//获得所有游戏类型的游戏集合
    getPage(1,1,"");//初始化分页数据

    function getGamesByPant() {
        $.ajax({
            type:"GET",//请求类型
            url:path+"/getGamesByPant",//请求的url
            dataType:"json",//ajax接口（请求url）返回的数据类型
            async: false , //true异步(默认)，异步方式，$.Ajax执行后，会继续执行ajax后面的脚本，false同步并且按顺序执行，只有第一个执行完成之后执行下一个
            success:function(data){//data：返回数据（json对象）
                $("#navs").html("");
                var div = "";
                //循环遍历集合
                div+="<ul>";
                var index=0;
                for(var i=0;i<data.length;i++){
                    if(data[i].tId!=index){
                        index=data[i].tId;
                        if(index>1){
                            div+="</dl></li>";
                        }
                        div+="<li><dl><dt>"+data[i].tName+"</dt>";
                    }
                    if(data[i].tId==index){
                        div+="<dd><a href=\"javascript:;\" gid=" + data[i].gid + " class='gameList' >"+data[i].gName+"</a></dd>";
                    }
                }
                div+="</dl></li></ul>";
                $("#navs").html(div);
            }
        });
    }


    //获得分页得函数
    function getPage(obj,num,info) {
        $.ajax({
            type: "GET",//请求类型
            url: path+"/getCardsPage",//请求的url
            data: {id: obj,index:num,info:info},//请求参数
            dataType: "json",//ajax接口（请求url）返回的数据类型
            async: false , //true异步，false同步
            success: function (data) {//data：返回数据（json对象）
                $("#storyBooksssss").html("");
                var div = "";
                for (var i = 0; i < data.newList.length; i++) {
                    div+="<div class=\"product_storyList_content_left\"><img src="+path+"/static/rootWeb/images/"+data.newList[i].titleImg+" alt=\"点卡列表\"/></div>" +
                        " <div class=\"product_storyList_content_right\"><ul>"+
                        " <li class=\"product_storyList_content_dash\"><a href=\"#\" class=\"blue_14\">"+data.newList[i].gName+"</a></li>"+
                        "<li>顾客评分：<img src="+path+"/static/rootWeb/images/star_red.gif><img src="+path+"/static/rootWeb/images/star_red.gif alt='star'/><img src="+path+"/static/rootWeb/images/star_red.gif alt='star'/><img src="+path+"/static/rootWeb/images/star_red.gif alt='star'/><img src="+path+"/static/rootWeb/images/star_red.gif alt='star'/></li>"+
                        "<li>好评率：" + data.newList[i].praiseRate + "%</li>"+
                        "<li>点卡名称：" + data.newList[i].cName + "</li>"+
                        " <li>上架时间：" + data.newList[i].upTime + "</li>"+
                        "<li>" + data.newList[i].cInfo + "</li>"+
                        "<li><dl class=\"product_content_dd\"> <dd>&nbsp;&nbsp;</dd>" +
                        "<dd><a href="+path+"/static/toCardView/"+ data.newList[i].cId + "/"+data.newList[i].gid+">"+
                        "<img src="+path+"/static/rootWeb/images/product_buy_01.gif></a></dd>"+
                        " <dd class=\"footer_dull_red\"><span>实际售价：￥"+data.newList[i].price+"</span></dd>"+
                        "<dd class=\"product_content_delete\"><span>原价：￥"+data.newList[i].iniPrice+"</span></dd>"+
                        "</dl> </li> </ul> </div><div class=\"product_storyList_content_bottom\"></div>";
                }
                div+="<input type='hidden' id='totalPageCount' value="+data.totalPageCount+">";

                $("#storyBooksssss").html(div);
                $("#pageNo").html("");
                $(".page-bar").remove();//错误点，必须加上这句清空
                var page="<div class='page-bar'>"+
                    "<p class='page-num-ul'>"+
                    "<span>&nbsp;&nbsp;共"+data.totalCount+"条记录</span>"+
                    "<span>&nbsp;&nbsp;"+data.currPageNo+"/"+data.totalPageCount+"页</span>";
                if(data.currPageNo>1){
                    page+="<a href=\"javascript:;\" id='1' class='first' >&nbsp;&nbsp;首页</a>"+
                        "<a href=\"javascript:;\" class='pre' id='"+(data.currPageNo-1)+"'>&nbsp;&nbsp;上一页</a>";
                }
                if(data.currPageNo < data.totalPageCount ){
                    page+="<a href=\"javascript:;\" class='next'  id='"+(data.currPageNo+1)+"'>&nbsp;&nbsp;下一页</a>"+
                        "<a href=\"javascript:;\" class='last'  id='"+data.totalPageCount+"'>&nbsp;&nbsp;最后一页</a>";
                }
                page+="<span class='page-go-form'><label>&nbsp;&nbsp;跳转至</label>"+
                    "<input type='text' name='inputPage' id='inputPage' class='page-key' />页"+
                    "<button type='button' class='page-btn' id='page-btn'>GO</button>"+
                    "</span></p></div>";
                $("#pageNo").html(page);
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
        if(info==""){
            info=null;
        }
        getPage(0,pageNo,info);
    });

    //输入页数
    $(document).on("click",".page-btn",function(){
        var info=$("#info").val();
        if(info==""){
            info=null;
        }
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
        if(info==""){
            info=null;
        }
        num=1;//这个不加跟页码有冲突
        getPage(0,num,info);
    });
    //点击游戏搜索点卡信息
    $(".gameList").click(function(){
        info=null;
        var obj = $(this).attr("gid");
        getPage(obj,1,info);
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
});