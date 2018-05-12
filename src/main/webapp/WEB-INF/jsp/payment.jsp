
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
    <link href="${pageContext.request.contextPath}/static/rootWeb/css/rollpage.css" rel="stylesheet" type="text/css"/>
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
    <div id="loginfo"></div>
    <div class="loginTitle">商品详情</div>
    <div id="cardsInfo">
        <c:if test="${cty==1}">
            <ul>
                <li>${bycards.cName}&nbsp;${bycards.gName}</li>
                <li>单价：${bycards.price}元</li>
                <li>数量：${bycards.byNum}</li>
                <li>小计：<span>${bycards.totalPrice}</span> 元</li>
            </ul>
            <input type="hidden" class="byType" value="1">
        </c:if>
        <c:if test="${cty==2}">
            <c:forEach var="card" items="${cardsListJson}">
                <ul>
                    <li>${card.cName}&nbsp;${card.gName}</li>
                    <li>单价：${card.price}元</li>
                    <li>数量：${card.num}</li>
                    <li>小计：<span>${card.totalPrice}</span> 元</li>
                </ul>
                <input type="hidden" class="byType" value="2">
            </c:forEach>
        </c:if>
    </div>
    <div id="totalPrice">
            <c:if test="${cty==1}}">
                总价格: <span>${bycards.totalPrice}</span> 元&nbsp;
                &nbsp;&nbsp;
            </c:if>
            <c:if test="${cty==2}">
                总价格: <span>${totalPir}</span> 元&nbsp;
                &nbsp;&nbsp;
            </c:if>
    </div>
    <div id="toby">
        <input type="hidden" id="error" value="${error}">
        <input id="butOn" type="button" value="确认，提交订单" width="100px" height="40px">
    </div>
</div>
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
<input type="hidden" id="path" name="path" value="${pageContext.request.contextPath }"/>
<%
    if(null!=session.getAttribute("error")){
        session.removeAttribute("error");
    }
%>
</body>
<script src="${pageContext.request.contextPath}/static/rootWeb/js/jquery-1.8.3.min.js"></script>
<script src="${pageContext.request.contextPath}/static/rootWeb/js/payment.js"></script>
</html>


