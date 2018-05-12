
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
    <link href="${pageContext.request.contextPath}/static/rootWeb/css/rollpage.css" rel="stylesheet" type="text/css"/>
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
    <div id="loginfob"></div>
    <div class="loginTitle">支付结果</div>
    <div id="cardsInfo">
        <c:if test="${result>0}">
           <div id="resultOk">支付成功！</div>
        </c:if>
        <c:if test="${result<=0}">
            <div id="resultNo">支付失败！</div>
        </c:if>
        <c:if test="${ordId==1}">
            <ul>
                <li>订单名称：${order.orderName}</li>
                <li>订单编号：${order.id}</li>
                <li>订单编号总金额：${order.totalPrice}元</li>
                <li>下单时间：<fmt:formatDate value="${order.byTime}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
            </ul>
        </c:if>
        <c:if test="${ordId==2}">
            <c:forEach var="order" items="${orderList}">
                <ul>
                    <li>订单名称：${order.orderName}</li>
                    <li>订单编号：${order.id}</li>
                    <li>订单编号总金额：${order.totalPrice}元</li>
                    <li>下单时间：<fmt:formatDate value="${order.byTime}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
                </ul>
            </c:forEach>
        </c:if>
    </div>
    <div id="totalPrice">
    </div>
    <div id="info-panel">
        <a class="buttonchoice" href="${pageContext.request.contextPath}/toProduct">继续挑选商品</a>
        <a class="buttonchoice" href="${pageContext.request.contextPath}/toHome">立即去评价</a>
    </div>
</div>
<input type="hidden" id="path" name="path" value="${pageContext.request.contextPath }"/>
<input type="hidden" id="error" value="${error}">
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
</body>
<%
    if(null!=session.getAttribute("error")){
        session.removeAttribute("error");
    }
%>
<script src="${pageContext.request.contextPath}/static/rootWeb/js/jquery-1.8.3.min.js"></script>
<script src="${pageContext.request.contextPath}/static/rootWeb/js/paymentresult.js"></script>
</html>



