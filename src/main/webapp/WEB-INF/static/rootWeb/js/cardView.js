/**
 * Created by Administrator on 2017/11/1.
 */
$(document).ready(function(){

        var path=$("#path").val();
        var indexaa = 0;//在集合中的索引
        var $but = $(".but").children("input");//按钮集合，jQuery对象一定要带“$”
        var $div = $("#butInfo").children("div");//数字集合


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

        /**
         * 点击
         */
        $(document).on("click",".buttonRa",function(){
            indexaa=$but.index($(this));//鼠标悬浮的(数字li)在它所在的数字集合中的索引
            $("#butInfo").children("div").hide();
            $div.eq(indexaa).stop(true,true).fadeIn().siblings().fadeOut();
        })

    //立刻购买
    $(document).on("click","#buy",function(){
        var cid=$("#cid").val();
        var gid=$("#gid").val();
        var price=$("#price").val();
        var gName=$("#gName").val();
        var cName=$("#cName").val();
        var num=$("#numU").val();
        var cnumber=$("#number").val();
        if(num>cnumber){
            changeDLGContent("您选择得数量大于库存数量！");
            openYesOrNoDLG();
        }else{
            window.location.href=path+"/toPayment/"+cid+"/"+gid+"/"+price+"/"+gName+"/"+cName+"/"+num;
        }
    });

        var savNum=0;
    $("#cart").fadeOut();
    //加入购物车
    $(document).on("click","#save",function(){
        var cid=$("#cid").val();
        var gid=$("#gid").val();
        var price=$("#price").val();
        var num=$("#numU").val();
        var uId=$("#uId").val();
        var cnumber=$("#number").val();
        if(num>cnumber){
            changeDLGContent("您选择得数量大于库存数量！");
            openYesOrNoDLG();
        }else{
            $.ajax({
                type: "GET",//请求类型
                url: path+"/saveCart",//请求的url
                data: {cid:cid,gid:gid,price:price,num:num,uId:uId},
                dataType: "json",//ajax接口（请求url）返回的数据类型
                success: function (data) {//data：返回数据（json对象）
                   if(data>0){
                       savNum=parseInt(savNum)+parseInt(num);
                       $(".cartNum").text("");
                       $(".cartNum").text(savNum);
                       $("#cart").fadeIn();
                   }else {
                       changeDLGContent("加入购物车失败！");
                       openYesOrNoDLG();
                   }
                },
                error: function (data) {//当访问时候，404，500 等非200的错误状态码
                    $("#cart").fadeOut();
                    changeDLGContent("请登录账号后再执行操作！");
                    openYesOrNoDLG();
                    $('#yes').click(function () {
                        cancleBtn();
                        window.location.href=path+"/static/tologin";
                    });
                }
            });
        }
    });

});