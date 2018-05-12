$(document).ready(function(){
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

    function toNewwindow(windo) {
        $('#yes').click(function () {
            cancleBtn();
            window.location.href=windo;
        });
    }

    var path=$("#path").val();
    var nickName=$("#nickName");
    //密码
    var pwd = $("#pwd");
    //再次验证密码
    var rpwd = $("#repwd");
    //验证姓名
    var name = $("#name");
    var telephone = $("#telephonetext");
    //邮箱的验证
    var email = $("#email");
    //QQ的验证
    var QQ = $("#QQ");//这个只能是document对象
    var flg=false;//验证用户名是否可用
    //地址的验证
    var userAddress = $("#userAddress");//这个只能是document对象
    /**
     * 错误信息
     * @param yuansu
     * @param info
     */
    function  error(yuansu,info) {
        yuansu.parent().next().html(info);
        yuansu.parent().next().addClass("red");
    }
    //正确信息
     function  isok(cases,info) {
         cases.parent().next().html(info);
         cases.parent().next().addClass("green");
     }


    nickName.bind("focus",function(){
        error(nickName,"请输入您要注册得用户名称！");
    }).bind("blur",function(){
        if (nickName.val()=="") {
            error(nickName,"昵称不能为空!");
            return;
        }else if (!(/[a-zA-Z0-9]{4,20}/.test(nickName.val()))) {
            error(nickName,"昵称必须是4-20位的英文和数字！");
            return;
        }else {
            //ajax后台验证--Name是否已存在
            $.ajax({
                type:"GET",//请求类型
                url:path+"/static/checkName",//请求的url
                data:{APKName:$("#nickName").val()},//请求参数
                dataType:"json",//ajax接口（请求url）返回的数据类型
                success:function(data){//data：返回数据（json对象）
                    if(data== "exist") {//参数APKName为空，错误提示
                        error(nickName,"该昵称已存在，不能使用！");
                        nickName.focus();
                    }else {
                        flg=true;
                        isok(nickName,"ok");
                    }
                },
                error:function(data){//当访问时候，404，500 等非200的错误状态码
                    error(nickName,"验证APKName信息出现错误！");
                }
            });
            return;
        }
    });

    //注册提交
    $("#registerBtn").click(function() {
        if(flg==true){
            if (pwd.val()=="") {
                error(pwd,"密码不能为空!");
                return;
            }else if (!(/[a-zA-Z0-9]{6,20}/.test(pwd.val()))) {
                error(pwd,"密码必须是6-20位的英文和数字！");
                return;
            }else if (rpwd.val()=="") {
                error(rpwd,"请再次输入密码!");
                return;
            } else if (rpwd.val() != pwd.val()) {
                error(rpwd,"两次输入的密码不一样！");
                return;
            }else if (name.val()=="") {
                error(name,"用户姓名不能为空!");
                return;
            } else if (!(/^[a-zA-Z ]{1,20}$/.test(name.val()) || /^[\u4e00-\u9fa5]{1,10}$/.test(name.val()))) {
                error(name,"请输入合法的中文或者英文名！");
                return;
            }else if (telephone.val()=="") {
                error(telephone,"电话号码不能为空!");
                return;
            } else if (!(/^1[3,4,5,7,8]\d{9}$/.test(telephone.val()))) {
                error(telephone,"请输入正确的电话号码！");
                return;
            }else if (email.val()=="") {
                error(email,"邮箱不能为空!");
                return;
            } else if (!(/^\w+@\w+(\.[a-zA-Z]{2,3}){1,2}$/.test(email.val()))) {
                error(email,"邮箱格式不正确！");
                return;
            }else if (QQ.val()=="") {
                error(QQ,"QQ号码不能为空!");
                return;
            }else if (!(/^[1-9][0-9]{4,11}$/.test(QQ.val()))) {
                error(QQ,"QQ号码格式不正确！");
                return;
            }else if (userAddress.val()=="") {
                error(userAddress,"用户地址不能为空!");
                return;
            } else {
                $("#myform").ajaxSubmit(
                    {
                        type: 'post',
                        url: path+"/static/registuser",//请求的url
                        success: function (data) {
                            if (data > 0) {
                                changeDLGContent("注册成功，赶快去登录吧！");
                                openYesOrNoDLG();
                                toNewwindow(path+"/static/tologin");
                                return;
                            } else {
                                changeDLGContent("注册失败！");
                                openYesOrNoDLG();
                                return;
                            }
                        }
                    });
            }
        }else {
            return;
        }
    });


});

