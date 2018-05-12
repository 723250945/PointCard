
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
<div class="header_middle">
    <div class="logo"><img src="${pageContext.request.contextPath}/static/rootWeb/images/logo.png" alt="logo"/></div>
    <div class="search"><input type="text" id="info" placeholder="请输入搜索关键字"><input type="button" id="searchInfo"></div>
</div>
<nav id="nav">
    <ul>
        <li><a href="${pageContext.request.contextPath}/static/index"> 首页</a></li>
        <li><a href="#"> 游戏充值</a></li>
        <li><a href="${pageContext.request.contextPath}/torecharge"> 帐户充值</a></li>
        <li><a href="${pageContext.request.contextPath}/static/toNews"> 关于我们</a></li>
    </ul>
</nav>
<!--头部end-->
<!--中间部分开始-->
<section id="main">
    <div class="current_place">您现在的位置：<a href="${pageContext.request.contextPath}/static/index">勇士点卡商城</a> &gt;&gt;  游戏充值&gt;&gt; </div>
    <!--左侧菜单开始-->
    <div id="product_left">
        <div id="product_catList">
            <div class="product_catList_top">可充值游戏列表</div>
                <nav class="nav" id="navs">
                    ajax
                </nav>
        </div>
        <div class="product_catList_end">
            <img src="${pageContext.request.contextPath}/static/rootWeb/images/product_01.gif" alt="shopping"/>
            <img src="${pageContext.request.contextPath}/static/rootWeb/images/product_02.gif" alt="shopping"/>
        </div>
    </div>
    <!--右侧内容开始-->
    <div id="product_storyList">
        <div id="product_storyList_top">
            <ul>
                <li>排序方式</li>
                <li><img src="${pageContext.request.contextPath}/static/rootWeb/images/dd_arrow_right.gif" alt="arrow"/></li>
                <li>
                    <select id="compositor" name="sort">
                        <option value="1">按销量 降序</option>
                        <option value="2">按销量 升序</option>
                        <option value="3">按价格 降序</option>
                        <option value="4">按价格 升序</option>
                        <option value="5">按上架时间 降序</option>
                        <option value="6">按上架时间 升序</option>
                    </select>
                </li>
                <li id="pageNo" style="float:right;">第1页</li>
            </ul>
        </div>
        <div id="product_array">
            <a class="click" name="array"  href="javascript:void(0)">列表</a>
            <a name="bigImg" href="javascript:void(0)">大图</a>
        </div>
        <!--图书排列开始-->
        <div id="product_storyList_content" class="product_storyList_content">
            <div id="storyBooksssss"><!--使用javaScript显示图书列表--></div>
        </div>
    </div>
    <!--右侧内容结束-->
</section>
<!--网站版权部分开始-->
<footer id="footer">
    <input type="hidden" id="path" name="path" value="${pageContext.request.contextPath }"/>
    <div class="footer_top"><a href="${pageContext.request.contextPath}/static/rootWeb/#" target="_parent" class="footer_dull_red">正版保障</a> | <a href="${pageContext.request.contextPath}/static/rootWeb/#" target="_parent" class="footer_dull_red">满额免运</a> | <a href="${pageContext.request.contextPath}/static/rootWeb/#" target="_parent" class="footer_dull_red">货到付款</a> | <a href="${pageContext.request.contextPath}/static/rootWeb/#" target="_parent" class="footer_dull_red">品种最全</a> | <a href="${pageContext.request.contextPath}/static/rootWeb/#" target="_parent" class="footer_dull_red">免费退换</a></div>
    <div class="footer_end">Copyright (C) 勇士点卡商城 2006-2016, All Rights Reserved  <img src="${pageContext.request.contextPath}/static/rootWeb/images/validate.gif"  alt="版权" style="vertical-align:middle;" /> 京ICP证100488号 出版物经营许可证 京批100160号</div>
</footer>
<script src="${pageContext.request.contextPath}/static/rootWeb/js/jquery-1.8.3.min.js"></script>
<script src="${pageContext.request.contextPath}/static/rootWeb/js/productAjax.js"></script>
<script src="${pageContext.request.contextPath}/static/rootWeb/js/product.js"></script>
<script src="${pageContext.request.contextPath}/static/rootWeb/js/header.js"></script>
</body>
</html>
