
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>勇士点卡商城注册页面</title>
    <link href="${pageContext.request.contextPath}/static/rootWeb/css/global.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/static/rootWeb/css/layout.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/static/rootWeb/css/template.css" rel="stylesheet" />
</head>

<body>
<header class="header_middle">
    <div id="register_header">
        <div class="register_header_left"><img src="${pageContext.request.contextPath}/static/rootWeb/images/logo.png" alt="logo"/></div>
        <div class="register_header_right"><a href="${pageContext.request.contextPath}/static/index" class="blue">首页</a> | <a href="${pageContext.request.contextPath}/toProduct" class="blue">游戏充值</a>  | <a href="${pageContext.request.contextPath}/toShop" class="blue">购物车</a> |  <a href="${pageContext.request.contextPath}/static/tologin" class="blue">登录</a></div>
    </div>
</header>
<section id="main">
    <div class="register_content">
        <div class="register_mid_bg">用户注册</div>
        <div class="register_message">
            <form action="" method="post" id="myform">
                <dl class="register_row">
                    <dt>设置昵称：</dt>
                    <dd><input id="nickName"  name="loginName" type="text" class="register_input"  required="required" /></dd>
                    <dd>*</dd>
                </dl>
                <dl class="register_row">
                    <dt>设定密码：</dt>
                    <dd><input id="pwd"  type="password" class="register_input"required="required" /></dd>
                    <dd>*</dd>
                </dl>
                <dl class="register_row">
                    <dt>再输入一次密码：</dt>
                    <dd><input id="repwd" name="password" type="password" class="register_input" required="required"/></dd>
                    <dd>*</dd>
                </dl>
                <dl class="register_row">
                    <dt>姓名：</dt>
                    <dd><input id="name" name="userName" type="text" required="required" class="register_input" /></dd>
                    <dd>*</dd>
                </dl>
                <dl class="register_row">
                    <dt>电话：</dt>
                    <dd><input id="telephonetext" name="telephone" type="text" required="required" class="register_input" /></dd>
                    <dd>*</dd>
                </dl>
                <dl class="register_row">
                    <dt>Email地址：</dt>
                    <dd><input id="email" name="userEmail" type="email" required="required" class="register_input" /></dd>
                    <dd>*</dd>
                </dl>
                <dl class="register_row">
                    <dt>QQ：</dt>
                    <dd><input id="QQ" name="userQQ" type="text" required="required" class="register_input" /></dd>
                    <dd>*</dd>
                </dl>
                <dl class="register_row">
                    <dt>联系地址：</dt>
                    <dd><input id="userAddress" name="userAddress" type="text" required="required" class="register_input" /></dd>
                    <dd>*</dd>
                   <%-- <dt>所在地区：</dt>
                    <dd>
                        <select id="province" style="width:120px;">
                            <option>请选择省/城市</option>
                        </select>
                    </dd>
                    <dd>
                        <select id="city"  style="width:130px;">
                            <option>请选择城市</option>
                        </select>
                    </dd>--%>
                </dl>
                <div class="registerBtn"><input id="registerBtn" type="button" value="立即注册" style="width: 200px"/></div>
            </form>
        </div>
    </div>
    <input type="hidden" id="path" name="path" value="${pageContext.request.contextPath }"/>
</section>
<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeBi">
    <div class="removerChid">
        <h2>勇士点卡温馨提示!</h2>
        <div class="removeMain">
            <p id="info">提示信息？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>
<!--网站版权部分开始-->
<footer id="footer">
    <div class="footer_top"><a href="${pageContext.request.contextPath}/static/rootWeb/#" target="_parent" class="footer_dull_red">正版保障</a> | <a href="${pageContext.request.contextPath}/static/rootWeb/#" target="_parent" class="footer_dull_red">满额免运</a> | <a href="${pageContext.request.contextPath}/static/rootWeb/#" target="_parent" class="footer_dull_red">货到付款</a> | <a href="${pageContext.request.contextPath}/static/rootWeb/#" target="_parent" class="footer_dull_red">品种最全</a> | <a href="${pageContext.request.contextPath}/static/rootWeb/#" target="_parent" class="footer_dull_red">免费退换</a></div>
    <div class="footer_end">Copyright (C) 勇士点卡商城 2006-2016, All Rights Reserved  <img src="${pageContext.request.contextPath}/static/rootWeb/images/validate.gif"  alt="版权" style="vertical-align:middle;" /> 京ICP证100488号 出版物经营许可证 京批100160号</div>
</footer>
</body>
<script src="${pageContext.request.contextPath}/static/rootWeb/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/rootWeb/js/jquery-form.js"></script>
<script  src="${pageContext.request.contextPath}/static/rootWeb/js/register.js"></script>
</html>

