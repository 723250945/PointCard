
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="fm"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>勇士点卡商城</title>
    <link href="${pageContext.request.contextPath}/static/rootWeb/css/global.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/static/rootWeb/css/layout.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/static/rootWeb/css/template.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/static/rootWeb/css/home.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/static/rootWeb/js/jquery-1.8.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/rootWeb/js/header.js"></script>
</head>
<body>
<!--头部-->
<header id="header">
    <div class="header_top">
        <input type="hidden" value="${user.loginName}">
        <div class="header_top_left">${user.loginName}您好！欢迎勇士点卡商城
            <c:choose>
                <c:when test="${user.loginName !=null}">
                    [<a href="${pageContext.request.contextPath}/loginOut" target="_parent">注销</a>
                </c:when>
                <c:otherwise>
                    [<a href="${pageContext.request.contextPath}/static/tologin" target="_parent">登录</a>
                </c:otherwise>
            </c:choose>
            | <a href="${pageContext.request.contextPath}/static/toregist" target="_parent">免费注册</a>]</div>
        <div class="header_top_right">
            <ul>
                <li><a href="#" target="_self">客户服务</a></li>
                <li>|</li>
                <li id="menu"><a href="#" target="_self">新手入门</a> <img src="${pageContext.request.contextPath}/static/rootWeb/images/arrow_down.gif"  alt="arrow" />
                    <div id="dd_menu_top_down">
                        <a href="#" target="_self">购物保障</a><br />
                        <a href="#" target="_self">购物流程</a><br />
                        <a href="#" target="_self">会员介绍</a><br />
                        <a href="#" target="_self">常见问题</a><br />
                    </div>
                </li>
                <li>|</li>
                <li><a href="#" target="_self">礼品卡</a></li>
                <li>|</li>
                <li><a href="${pageContext.request.contextPath}/tomyOrders" target="_self">我的订单</a></li>
                <li>|</li>
                <li><a href="${pageCntext.request.contextPath}/toHome" target="_self">我的账户</a></li>
                <li>|</li>
                <li><a href="${pageContext.request.contextPath}/toShop" target="_parent">购物车</a></li>
                <li><img src="${pageContext.request.contextPath}/static/rootWeb/images/header_shop.gif" alt="shopping"/></li>
            </ul>
        </div>
    </div>
</header>
<nav id="nav">
    <ul>
        <li><a href="${pageContext.request.contextPath}/static/index"> 首页</a></li>
        <li><a href="${pageContext.request.contextPath}/toProduct"> 游戏充值</a></li>
        <li><a href="${pageContext.request.contextPath}/torecharge"> 帐户充值</a></li>
        <li><a href="${pageContext.request.contextPath}/static/toNews"> 关于我们</a></li>
    </ul>
</nav>
<!--头部end-->
<!--网站中间内容开始-->
<div id="homebody">
    <div id="hLeft">
        <ul>
            <li class="titleLi">用户信息</li>
            <li> <strong>${userDatil.userName}</strong> </li>
            <li>级别：<strong>
                <c:choose>
                    <c:when test="${userDatil.userStep==0}">
                        普通会员
                    </c:when>
                    <c:when test="${userDatil.userStep==-1}">
                        管理员账号
                    </c:when>
                    <c:otherwise>
                        尊贵${userDatil.userStep}级会员
                    </c:otherwise>
                </c:choose>
            </strong>
            </li>
            <li>帐户余额：<strong>${userDatil.userChange}元</strong></li>
            <li>积分: <strong>${userDatil.totalPrice}</strong>(每一元一分积分)</li>
        </ul>
        <ul>
            <li class="titleLi">资料管理</li>
            <li><a href="">个人资料</a></li>
            <li><a href="">修改密码</a></li>
        </ul>
        <ul>
            <li class="titleLi">订单管理</li>
            <li><a href="">订单列表</a></li>
            <li><a href="">订单查询</a></li>
        </ul>
        <ul>
            <li class="titleLi">充值管理</li>
            <li><a href="">积分查询</a></li>
            <li><a href="">余额查询</a></li>
        </ul>
        <ul>
            <li class="titleLi">留言建议</li>
            <li><a href="">我的留言</a></li>
            <li><a href="">添加留言</a></li>
        </ul>
        <ul>
            <li class="titleLi">其他管理</li>
            <li><a href="">我的收藏</a></li>
            <li><a href="">....</a></li>
        </ul>
    </div>
    <div id="hRight">
        <div id="address">
            <div id="addressL">当前位置：<a href="${pageContext.request.contextPath}/static/index">勇士点卡商城</a> 》会员中心</div>
            <div id="addressR">您好！${userDatil.userName}
                <div id="loginOUt"><a href="${pageContext.request.contextPath}/loginOut" >退出</a></div>
            </div>
        </div>
        <div style="text-indent: 15px;margin-top: 15px">
            <img style="vertical-align:middle" src="${pageContext.request.contextPath}/static/rootWeb/images/user.png" alt="${userDatil.userName}"> &nbsp;&nbsp;&nbsp; <strong>用户中心</strong>
            <p>你好,${userDatil.userName},欢迎进入用户中心！</p>
        </div>
        <div id="userInfo">
            <ul>
                <li>用户信息</li>
                <li>您的账户目前总积分：${userDatil.totalPrice}分&nbsp; 查看积分历史</li>
                <li>余额：${userDatil.userChange}元&nbsp; &nbsp; <a href="">马上去充值</a></li>
                <li>您的订单交易总数量：${orderCount}个&nbsp;&nbsp; <a href="${pageContext.request.contextPath}/toOrderList">进入订单列表</a></li>
                <li></li>
            </ul>
            <div>
                <h2 style="text-indent: 20px">浏览记录</h2>
                <hr/>
                <div class="cards"></div>
                <div class="cards"></div>
                <div class="cards"></div>
                <div class="cards"></div>
            </div>
        </div>
    </div>
</div>


</body>
</html>

