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
    <link href="${pageContext.request.contextPath}/static/rootWeb/css/news.css" rel="stylesheet" type="text/css"/>
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
<!-- Unnamed (形状) -->
<div id="homebody">
    <div id="newleft">
        <ul>
            <li><a href="${pageContext.request.contextPath}/static/toNewList/1">${cardSals[0].type}</a></li>
            <li><a href="${pageContext.request.contextPath}/static/toNewList/2">${recruitList[0].type}</a></li>
            <li><a href="${pageContext.request.contextPath}/static/toNewList/3">${cooperationList[0].type}</a></li>
            <li><a href="${pageContext.request.contextPath}/static/toNewList/4">${concatList[0].type}</a></li>
            <li><a href="${pageContext.request.contextPath}/static/toNewList/5">${weList[0].type}</a></li>
        </ul>
    </div>
    <div id="newsRight">
        <div id="titleAddress">
            <a href=""> 帮助中心</a>&gt; <a href="">${news.type}</a>
        </div>
        <div id="titleNews">
            <p>${news.title} </p>
            <p>时间：<fmt:formatDate value="${news.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/> 来源：<a href="">${news.from} </a></p>
        </div>
        <div id="newsInfo">
            ${news.context}
        </div>
    </div>
</div>
</body>
</html>
