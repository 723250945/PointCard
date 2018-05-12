/**
 * Created by Administrator on 2017/10/19.
 */
$(document).ready(function(){

    var path=$("#path").val();
    //2.计算商品的总价
    function Count(){
        var $tr=$("#myTableProduct").find("tr[id]");//找到元素节点
        var sum=0;//总价
        var ini=0;//原价钱
        $tr.each(function(i,dom){
            var price=$(dom).children(".shopping_product_list_4").children().text();
            sum=sum+parseFloat(price);//总价
        });
        $("#product_total").text(sum.toFixed(2));//显示总价
        $("#product_save").text((sum*0.01).toFixed(0));//显示节省的金额
        $("#product_integral").text(sum.toFixed(0));//显示积分
    }
    Count();


    //3.删除单个订单
    $(".shopping_product_list_6").children().click(function(){
        var ordId=$(this).next(".ordId").val();
        var answer=confirm("您确定要删除该订单"+ordId+"吗?");
        if(answer==true){
            $(this).parent().parent().slideUp();
            $.ajax({
                type: "POST",//请求类型
                url: path+"/toDeleteOrder",//请求的url
                data: {ordId:ordId},//请求参数
                dataType: "json",//ajax接口（请求url）返回的数据类型
                success: function (data) {//data：返回数据（json对象）
                    if (data>0) {
                        $(this).parent().parent().remove();
                        changeDLGContent("删除订单"+ordId+"成功！");
                        openYesOrNoDLG();
                    }else {
                        $(this).parent().parent().slideDown();
                        changeDLGContent("删除订单"+ordId+"失败！");
                        openYesOrNoDLG();
                    }
                },
                error: function (data) {//当访问时候，404，500 等非200的错误状态码
                    changeDLGContent("系统错误！");
                    openYesOrNoDLG();
                }
            });
            Count();
        }
        var $total=$("#myTableProduct").find("tr[id]");//找到要删除的节点
        //判断是否全部删除了
        if($total.length<=0){
            $(".shopping_list_end").children().remove();
            addstyle();
        }
    })
    //3.删除全部订单
    $("#removeAllProduct").click(function(){
        var answer=confirm("您确定要删除全部订单吗?");
        if(answer==true){
            var inputs=$("#orderForm").find(":input");  //获得所有的input元素
            var serialArray=inputs.serializeArray();
            var inputString=$.param(serialArray);//获得input元素的name对应的值
            //通过ajax请求
            $.ajax({
                type: "POST",//请求类型
                url: path+"/deleteOrders",//请求的url
                data: inputString,//请求参数
                dataType: "json",//ajax接口（请求url）返回的数据类型
                success: function (data) {//data：返回数据（json对象）
                    if(data>0){
                        $(this).parent().parent().slideUp();
                        $("#myTableProduct").children().remove();//删除商品列表的子元素,没删除表是为了以后的添加
                        $(".shopping_list_end").children().remove();
                        addstyle();
                    }else{
                        changeDLGContent("删除订单失败！");
                        openYesOrNoDLG();
                    }
                },
                error: function (data) {//当访问时候，404，500 等非200的错误状态码
                    changeDLGContent("系统错误！");
                    openYesOrNoDLG();
                }
            });
        }

    });

    checkShow();
    /**
     * 判断没有后台传递的数据，就不显示一些元素
     */
    function checkShow() {
        var ordId=$(".ordId").val();
        if(ordId=="" || ordId==undefined){
            $(".shopping_list_end").slideUp();
        }
    }
    /**
     * 全部提交订单
     */
    $("#but").click(function(){
        $("#orderForm").submit();
    });
    //3.2改变清空购物车的div,将就素材的格式用两个div
    function addstyle(){
        $(".shopping_list_border").append("<div class='empty_product'><div>您还没有挑选商品，<a href='index.html'>去挑选看看>></a></div></div>");
    }


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