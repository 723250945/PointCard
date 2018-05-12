/**
 * Created by Administrator on 2017/11/1.
 */
$(document).ready(function(){

    var path=$("#path").val();
    var error=$("#error").val();
    //立刻购买
    $(document).on("click","#butOn",function(){
        if(error!="" && error!=undefined){
            changeDLGContent(error);
            openYesOrNoDLG();
            $('#yes').click(function () {
                cancleBtn();
                window.location.href=path+"/static/index";
            });
            return;
        }
        var byType=$(".byType").val();
        window.location.href=path+"/toChoicePayment/"+byType;
    });

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