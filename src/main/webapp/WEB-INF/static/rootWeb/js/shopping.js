/**
 * Created by Administrator on 2017/10/19.
 */
$(document).ready(function(){

     var path=$("#path").val();
    //1.单击显示/隐藏商品列表
    var index=false;
    $("#shopping_commend_arrow").click(function(){
        if(index==false){
            $(this).attr("src","/static/rootWeb/images/shopping_arrow_down.gif");
            index=true;
        }else if(index==true){
            $(this).attr("src","/static/rootWeb/images/shopping_arrow_up.gif");
            index=false;
        }
        $("#shopping_commend_sort").toggle();
    });

    //4.把浏览推荐得商品增加到购物车
    $(".shopping_yellow").click(function(){
        var $total=$("#shopping").find("tr[id]");
        var $end=$("#shopping").find("tr[id]").last();
        if($total.length<2){
            $("#content").children(".empty_product").remove();
            $("#shopping").slideDown();
            $end=$("#into");
        }
        //得到售价
        var price=$(this).parent().prev().text();
        var pric=price.substring(1,price.length);//去掉￥符号
        //得到图标
        var titleImg=$(this).parent().next().text();
        //得到原价
        var ini=$(this).parent().prev().prev().text();
        var init=ini.substring(1,ini.length);//去掉￥符号
        //得到游戏名称
        var gname=$(this).parent().next().next().text();
        //得到点卡名称
        var cName=$(this).parent().parent().children(".shopping_commend_list_7").text();
        //获得cid
        var cid=$(this).parent().parent().children(".shopping_commend_list_8").text();
        //获得gid
        var gid=$(this).parent().parent().children(".shopping_commend_list_9").text();
        var node=$copy.clone(true);
        node.attr("id","product"+$total.length+1+"");
        node.children(".cart_td_2").html("<a href="+path+"/static/toCardView/"+cid+"/"+gid+"><img src="+path+"/static/rootWeb/images/"+titleImg+"  alt="+cName+" ></a>");
        node.children(".cart_td_3").html("<a href="+path+"/static/toCardView/"+cid+"/"+gid+" >"+cName+"</a><br />"+gname+"<br />"+
            "保障：<img src="+path+"/static/rootWeb/images/taobao_icon_01.jpg alt='icon' >");
        node.children(".cart_td_4").text(init);
        node.children(".cart_td_5").text(pric);
        node.children(".cart_td_6").children("#num_1").value=1;
        $end.after(node);
        $(this).parent().parent().remove();
        var uid=$("#uid").val();
        $.ajax({
            type: "GET",//请求类型
            url: path + "/saveCart",//请求的url
            data: {cid: cid, gid: gid, price: pric, num: 1, uId: uid},
            dataType: "json",//ajax接口（请求url）返回的数据类型
            success: function (data) {//data：返回数据（json对象）
                if (data == 0) {
                    changeDLGContent("加入购物车失败！");
                    openYesOrNoDLG();
                }
            }
        });
    });

    productCount();//1.执行自动计算函数

    //2.执行单击任何一个子复选框,则会对总复选框进行验证,
    //缺陷,取消子复选框价格没有重新计算,
    // 以完成该效果
    $(".cart_td_1").children("input").click(function(){
        isAll();
    })
    //自动计算的函数
    function productCount(){
        var $tr=$("#shopping").find("tr[id]");
        var sum=0;//总价
        var integral=0;//积分
        $tr.each(function(i,dom){
            var num=$(dom).children(".cart_td_6").find("input").val();//商品数量
            var price=(num*$(dom).children(".cart_td_5").text()).toFixed(2);//商品小计toFixed(2)保留两位小数点
            var p=num*$(dom).children(".cart_td_5").text();
            $(dom).children(".cart_td_7").html(price);//显示商品小计
            //增加一个判断,如果用户选择了该商品,则对该商品进行加入总价的计算
            if($(dom).children(".cart_td_1").children("input").is(":checked")){
                sum+=p;//总价
                integral+=$(dom).children(".cart_td_4").text()*num;//积分
            }
        });
        $("#total").text(sum.toFixed(2));//显示总价
        $("#integral").text(integral);//显示积分
    }

    //全选的单击事件
    var isChecked=false;
    $("#allCheckBox").click(function(){
        if(isChecked==false){
            $(".cart_td_1").children("input").attr("checked",true);
            isChecked=true;
        }else{
            $(".cart_td_1").children("input").attr("checked",false);
            //location.reload();//刷新页面,不然再次选中会失效
            isChecked=false;
        }
        productCount();//执行自动计算函数
    });

    //判断是否符合全选
    function isAll(){
        var checkBox=$(".cart_td_1").children("input");//得到所有子选择框的集合
        var k=0;
        checkBox.each(function(index,dom){
            if($(dom).is(":checked")){//判断每个子元素是否选中
                k++;
            }
        });
        if(k==checkBox.size()){
            $("#allCheckBox").attr("checked",true);
            isChecked=true;
        }else{
            $("#allCheckBox").attr("checked",false);
            isChecked=false;
        }
        productCount();//执行自动计算函数
    }
    //点击减少商品数量,总价格会在选中子选择框进行计算
    $(".cart_td_6").find("img[alt='minus']").click(function(){
        var num=$(this).next().val();//获得商品的数量
        if(num>=2){
            num--;
            $(this).next(".byNum").val(num);
            productCount();
        }else{
            alert("选择商品数量最少为1");
        }
    });
    //点击增加商品数量
    $(".cart_td_6").find("img[alt='add']").click(function(){
        var num=$(this).prev().val();//获得商品的数量
        num++;
        $(this).prev(".byNum").val(num);
        productCount();
    });

    //删除单个商品,试过 end()方法无效
    $(".cart_td_8").children().click(function(){
        var result=confirm("你确定要删除该商品吗?");
        if(result==true){
            $(this).parent().parent().prev().remove();
            $(this).parent().parent().remove();
        }
        productCount();
        addstyle();
    });

    //删除复选框 选中的多个商品
    $("#deleteAll").click(function(){
        var checkBox=$(".cart_td_1").children("input");//得到所有子选择框的集合
        var result=confirm("你确定要删除该商品吗?");
        if(result==false) return;
        checkBox.each(function(index,dom){
            if($(dom).is(":checked")){//判断每个子元素是否选中
                var $total=$("#shopping").find("tr[id]");
                $(dom).parent().parent().remove();
            }
        });
        productCount();
        addstyle();


    });

    //3.2改变清空购物车的div,将就素材的格式用两个div
    function addstyle(){
        var $total=$("#shopping").find("tr[id]");
        if($total.length<2){//定义了一个#into，所以始终有一个
            $("#shopping").slideUp();
            $("#content").append("<div class='empty_product'><div>您还没有挑选商品，<a href='index.html'>去挑选看看>></a></div></div>");
        }
    }
    var $copy=$("#product1").clone(true);//赋值一个购物车商品信息列表,不会受清空的影响

    /**
     * 选择购物车的商品提交订单
     */
    $("#payment").click(function() {
        var blackName = document.getElementsByName("cartCheckBox");
        var $total=$("#shopping").find("tr[id]");
        var k = 0;
        var serialArray="";
        for (var i = 0; i < blackName.length; i++) {
            if (blackName[i].checked == true) {
                k = k + 1;
                var obj=$total.eq(i+1);//前面多了一个id定位+1
                var card=new Cards(obj.children(".id").val(),obj.children(".cId").val(),obj.children(".gid").val(),obj.children(".price").val(),obj.children(".gName").val(),obj.children(".cName").val(),obj.children(".cart_td_6").children(".byNum").val());
                cards[k-1]=card;//数组下标从0开始
            }
        }
        if (k == 0) {
            changeDLGContent("请选择商品后再提交！");
            openYesOrNoDLG();
            return;
        }
        var jsonsa=JSON.stringify(cards);//将一个JavaScript值(对象或者数组)转换为一个 JSON字符串
        $.ajax({
            type: "POST",//请求类型
            url: path+"/toPaymentother",//请求的url
            data: jsonsa,//请求参数
            dataType: "json",//ajax接口（请求url）返回的数据类型
            success: function (data) {//data：返回数据（json对象）
                if (data>0) {
                    window.location.href=path+"/toPaymentOnly";
                }else {
                    changeDLGContent("添加订单失败！");
                    openYesOrNoDLG();
                }
            },
            error: function (data) {//当访问时候，404，500 等非200的错误状态码
                changeDLGContent("添加订单失败！");
                openYesOrNoDLG();
            }
        });
        /*var inputString = $.param(serialArray);//获得input元素的name对应的值*/
    });

    /**
     * 对象函数
     * @param cid
     * @param gid
     * @param price
     * @param gName
     * @param cName
     * @param byNum
     * @constructor
     */
    function Cards(id,cId,gid,price,gName,cName,num) {
        this.id=id;
        this.cId=cId;
        this.gid=gid;
        this.price=price;
        this.gName=gName;
        this.cName=cName;
        this.num=num;
    }
    //创建一个二维数组存贮对象
    var cards=new Array();

    function openYesOrNoDLG(){
        $('.zhezhao').css('display', 'block');
        $('#removeBi').fadeIn();
    }

    function cancleBtn(){
        $('.zhezhao').css('display', 'none');
        $('#removeBi').fadeOut();
    }
    function changeDLGContent(contentStr){
        var p = $("#info");
        p.html("");
        p.html(contentStr);
    }

    $('#no').click(function () {
        cancleBtn();
    });

    $('#yes').click(function () {
        cancleBtn();
    });
});