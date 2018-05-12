
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
    <link href="${pageContext.request.contextPath}/static/rootWeb/css/myCart.css" rel="stylesheet" type="text/css"/>
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
                <li><a href="#" target="_self">我的订单</a></li>
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
<!--中间部分开始-->
<div id="main">
  <%--  <div>&nbsp;&nbsp;<img src="${pageContext.request.contextPath}/static/rootWeb/images/shopping_myshopping.gif" alt="shopping"/> <a href="#">全场免运费买点卡送肥藩活动中</a></div>--%>
    <div class="shopping_list_top">我的订单中心</div>
    <c:if test="${myorderList!=null}">
        <form action="${pageContext.request.contextPath}/getOrders" method="post" id="orderForm">
    <div class="shopping_list_border">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr class="shopping_list_title">
                <td class="shopping_list_title_1">订单编号</td>
                <td class="shopping_list_title_2">订单名称</td>
                <td class="shopping_list_title_3">下单时间</td>
                <td class="shopping_list_title_4">订单金额</td>
                <td class="shopping_list_title_5">支付状态</td>
                <td class="shopping_list_title_6">删除</td>
            </tr>
        </table>
        <table width="100%" border="0" cellspacing="0" cellpadding="0" id="myTableProduct">
            <c:forEach var="order" items="${myorderList}" varStatus="index">
                <tr class="shopping_product_list" id="shoppingProduct_"${index}>
                    <td class="shopping_product_list_1"><a href="#" class="blue">${order.id}</a></td>
                    <td class="shopping_product_list_2"><label>${order.orderName}</label></td>
                    <td class="shopping_product_list_3"><fmt:formatDate value="${order.byTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td class="shopping_product_list_4">￥<label>${order.totalPrice}</label>(99折)</td>
                    <td class="shopping_product_list_5">
                        <c:if test="${order.paymentState==1}">
                            未支付
                        </c:if>
                        <c:if test="${order.paymentState==2}">
                            已支付
                        </c:if>
                    </td>
                    <td class="shopping_product_list_6"><a href="javascript:void(0)" class="blue">删除</a>
                        <input type="hidden" class="ordId" name="id" value="${order.id}">
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div class="shopping_list_end">
            <div><a id="removeAllProduct" href="javascript:void(0);">清除全部订单</a></div>
            <ul>
                <li class="shopping_list_end_1"><input name="" id="but" type="image" src="${pageContext.request.contextPath}/static/rootWeb/images/shopping_balance.gif"/></li>
                <li class="shopping_list_end_2">￥<label id="product_total"></label></li>
                <li class="shopping_list_end_3">订单金额总计：</li>
                <li  class="shopping_list_end_4">您共节省金额：￥<label class="shopping_list_end_yellow" id="product_save"></label><br/>
                    可获商品积分：<label class="shopping_list_end_yellow" id="product_integral"></label>
                </li>
            </ul>
        </div>
    </div>
        </form>
    </c:if>
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
<!--网站版权部分开始-->
<input type="hidden" id="path" name="path" value="${pageContext.request.contextPath }"/>
<footer id="footer">
    <div class="footer_top"><a href="#" target="_parent" class="footer_dull_red">正版保障</a> | <a href="#" target="_parent" class="footer_dull_red">满额免运</a> | <a href="#" target="_parent" class="footer_dull_red">货到付款</a> | <a href="#" target="_parent" class="footer_dull_red">品种最全</a> | <a href="#" target="_parent" class="footer_dull_red">免费退换</a></div>
    <div class="footer_end">Copyright (C) 勇士点卡商城 2006-2016, All Rights Reserved  <img src="${pageContext.request.contextPath}/static/rootWeb/images/validate.gif"  alt="版权" style="vertical-align:middle;" /> 京ICP证100488号 出版物经营许可证 京批100160号</div>
</footer>
<script src="${pageContext.request.contextPath}/static/rootWeb/js/jquery-1.12.4.js"></script>
<script  src="${pageContext.request.contextPath}/static/rootWeb/js/myOrder.js"></script>
<script src="${pageContext.request.contextPath}/static/rootWeb/js/header.js"></script>
</body>
</html>

