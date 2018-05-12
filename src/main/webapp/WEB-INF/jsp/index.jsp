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
    <script src="${pageContext.request.contextPath}/static/rootWeb/js/jquery-1.8.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/rootWeb/js/index.js"></script>
    <script src="${pageContext.request.contextPath}/static/rootWeb/js/header.js"></script>
    <script src="${pageContext.request.contextPath}/static/rootWeb/js/indexAjax.js"></script>
</head>
<body>
<!--随滚动条滚动可关闭广告-->
<div id="right" class="right">
    ajax显示
</div>
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
                <li><a href="${pageContext.request.contextPath}/static/help" target="_self">客户服务</a></li>
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
        <li><a href="#"> 首页</a></li>
        <li><a href="${pageContext.request.contextPath}/toProduct"> 游戏充值</a></li>
        <li><a href="${pageContext.request.contextPath}/torecharge"> 帐户充值</a></li>
        <li><a href="${pageContext.request.contextPath}/static/toNews"> 关于我们</a></li>
    </ul>
</nav>
<!--网站中间内容开始-->
<section id="main">
    <!--左侧菜单开始-->
    <div id="catList">
        <!--商品分类开始-->
        <div class="book_sort">
            <div class="book_sort_bg"><img src="${pageContext.request.contextPath}/static/rootWeb/images/dd_book_cate_icon.gif" alt="游戏" /> 游戏分类</div><!--1及菜单-->
            <div id="gameType">
                ajax请求数据
            </div>
        </div>
        <!--图书商品分类结束-->
    </div>
    <!--左侧菜单结束-->
    <!--中间部分开始-->
    <div id="content">
        <!--轮换显示的横幅广告图片-->
        <div class="scroll">
            <ul id="scroll_img">
               ajax显示
            </ul>
            <ul id="scroll_number">
                <li>1</li>
                <li>2</li>
                <li>3</li>
                <li>4</li>
            </ul>
        </div>
    </div>
    <!--中间部分结束-->
    <!--右侧部分开始-->
    <section id="silder">
        <!--书讯快递-->
        <div class="book_sort">
            <div class="book_sort_bg"><img src="${pageContext.request.contextPath}/static/rootWeb/images/dd_book_mess.gif" alt="mess" style=" vertical-align:text-bottom;"/>最新资讯</div>
            <div class="book_class">
                <div id="dome">
                    <div id="dome1">
                        <ul id="express">
                            ajax加载
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!--右侧部分结束-->
    <section class="book">
        <h1>点卡列表</h1>
        <ul id="cards">
           ajax
        </ul>
       <%-- <c:import url="rollpage.jsp">
            <c:param name="totalCount" value="${pager.totalCount}"/>
            <c:param name="currentPageNo" value="${pager.currPageNo}"/>
            <c:param name="totalPageCount" value="${pager.totalPageCount}"/>
        </c:import>--%>
    </section>
    <section class="tab">
        <ol>
            <li>点卡热销榜</li>
            <li>最新上架</li>
        </ol>
        <ul id="topHot">
            ajax
        </ul>
        <ul id="topNew">
           ajax
        </ul>
    </section>
</section>
<!--网站版权部分开始-->
<footer id="footer">
    <div class="footer_top"><a href="#" target="_parent" class="footer_dull_red">正版保障</a> | <a href="#" target="_parent" class="footer_dull_red">会员折扣</a> | <a href="#" target="_parent" class="footer_dull_red">货到付款</a> | <a href="#" target="_parent" class="footer_dull_red">点卡大全</a> | <a href="#" target="_parent" class="footer_dull_red">建议投诉</a></div>
    <div class="footer_end">Copyright (C) 勇士点卡商城 2006-2086, All Rights Reserved  <img src="${pageContext.request.contextPath}/static/rootWeb/images/validate.gif"  alt="版权" style="vertical-align:middle;" /> 京ICP证100488号 出版物经营许可证 京批100160号</div>
    <input type="hidden" id="path" name="path" value="${pageContext.request.contextPath }"/>
</footer>
</body>
</html>
