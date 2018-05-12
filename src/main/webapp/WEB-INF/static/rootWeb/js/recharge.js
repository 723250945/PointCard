
$(document).ready(function(){

    var path=$("#path").val();
    var indexaa = 0;//在集合中的索引
    var $but = $(".bua").children("input");//按钮集合，jQuery对象一定要带“$”
    var $div = $("#showFather").children("div");//数字集合


    /**
     * 点击
     */
    $(document).on("click",".buttonRa",function(){
        indexaa=$but.index($(this));//鼠标悬浮的(数字li)在它所在的数字集合中的索引
        $("#showFather").children("div").hide();
        $div.eq(indexaa).stop(true,true).fadeIn().siblings().fadeOut();
    })





    //余额支付
    $(document).on("click",".payment",function(){
        var type=document.getElementsByName("type").value;
        var rechargeNum=$("#rechargeNum").val();
        window.location.href=path+"/recharge/"+type+"/"+rechargeNum;

    });

});