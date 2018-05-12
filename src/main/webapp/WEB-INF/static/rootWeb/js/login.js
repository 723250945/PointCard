/**
 * Created by Administrator on 2017/11/5.
 */
$(document).ready(function(){

    var path = $("#path").val();
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

    //提交表单
    $("#loginForm").submit(function(){
        var email=$("#email").val();//这个只能是document对象
        var pwd=$("#pwd").val();
        if(email==""){
            changeDLGContent("邮箱不能为空!");
            openYesOrNoDLG();
            return false;
        }else if(pwd==""){
            changeDLGContent("请输入密码");
            openYesOrNoDLG();
            return false;
        }else if(email!="" && pwd!=""){
            getLogin(email,pwd);
            return false;
        }
    });

    //ajax登录验证
    function getLogin(email,pwd) {
        $.ajax({
            type: "GET",//请求类型
            url: path + "/static/login",//请求的url
            data: { name: email, pwd : pwd},//请求参数
            dataType: "json",//ajax接口（请求url）返回的数据类型
            async: false, //true异步，false同步
            success: function (data) {//data：返回数据（json对象）
                if(data==3){
                    window.location.href=path+"/byAgin";
                }else if(data==2){
                    changeDLGContent("该账户为冻结状态，不能登录!");
                    openYesOrNoDLG();
                }else if(data==1){
                    window.location.href=path+"/static/index";
                }else{
                    changeDLGContent("用户或密码错误登录失败!");
                    openYesOrNoDLG();
                }
            },
            error: function (data) {//当访问时候，404，500 等非200的错误状态码
                alert("加载失败！");
            }
        });
    }

});