
$(document).ready(function(){

    var path=$("#path").val();
    var error=$("#error").val();
    var indexaa = 0;//在集合中的索引
    var $but = $(".but").children("input");//按钮集合，jQuery对象一定要带“$”
    var $div = $("#showFather").children("div");//数字集合

    ini();//重复提交检测

    /**
     * 点击
     */
    $(document).on("click",".buttonRa",function(){
        indexaa=$but.index($(this));//鼠标悬浮的(数字li)在它所在的数字集合中的索引
        $("#showFather").children("div").hide();
        $div.eq(indexaa).stop(true,true).fadeIn().siblings().fadeOut();
    })


    //余额不足，返回充值
    $(document).on("click",".paymentb",function(){
        window.location.href=path+"/torecharge";
    });

    //验证支付密码
    var password=$(".password");
    password.bind("focus",function(){
        $(this).next().html("请输入8位数支付密码");
    }).bind("blur",function(){
        var pwd=$("#pwd").val();
        if(password.val()!=pwd) {
            $(this).next().html("输入的支付密码错误！");
        }else{
            $(this).next().html("");
        }
    });
    //余额支付
    $(document).on("click",".paymenta",function(){
        var pwd=$("#pwd").val();
        if(password.val()!=pwd){
            password.next().html("输入的支付密码错误！");
            return;
        }else {
            var oid=$(".oid").val();
            window.location.href=path+"/paymentCard/"+oid;
        }
    });

    function ini(){
        if(error!="" && error!=undefined){
            changeDLGContent(error);
            openYesOrNoDLG();
            $('#yes').click(function () {
                cancleBtn();
                window.location.href=path+"/static/index";
            });
        }
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


});