<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="fm"%>
<%@ page import="cn.jbit.entity.Order" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>勇士点卡商城</title>
    <link href="${pageContext.request.contextPath}/static/rootWeb/css/global.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/static/rootWeb/css/layout.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/static/rootWeb/css/template.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/static/rootWeb/css/home.css" rel="stylesheet" type="text/css"/>
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
    <div id="loginfoa"></div>
    <div class="loginTitle">订单详情</div>
    <div id="cardsInfo">
        <c:if test="${ordId==1}">
            <ul>
                <input type="hidden" class="oid" value="${orders.id}">
                <li>订单编号：${orders.id}</li>
                <li>订单编号总金额：${orders.totalPrice}元</li>
                <li>买家：${user.loginName}</li>
                <li>下单时间：<fmt:formatDate value="${orders.byTime}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
            </ul>
        </c:if>
        <c:if test="${ordId==2}">
            <input type="hidden" class="oid" value="1">
            <c:forEach var="orders" items="${myorderList}">
                <ul style="border-bottom: 1px dashed green">
                    <li>订单编号：${orders.id}</li>
                    <li>订单编号总金额：${orders.totalPrice}元</li>
                    <li>买家：${user.loginName}</li>
                    <li>下单时间：<fmt:formatDate value="${orders.byTime}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
                </ul>
            </c:forEach>
            <li style="text-align: right">总金额：￥${totalPrcie} 元</li>
        </c:if>
    </div>
    <div class="loginTitle">支付方式</div>
    <div class="but">
        <input class="buttonRa" type="button" value="在线支付">
        <input class="buttonRa" type="button" value="余额支付">
        <input class="buttonRa" type="button" value="微信扫码">
        <input class="buttonRa" type="button" value="支付宝扫码">
    </div>
    <div id="showFather">
        <div id="buttypeA">
            <div id="zhifuImga">
                <input type="radio" name="type" value="1">支付宝支付
            </div>
            <div id="zhifuImgb">
                <input type="radio" name="type" value="2">微信支付
            </div>
            <div id="bankChoice">
                网银支付
            </div>
            <div id="bankA"><input type="radio" name="type" value="3"></div>
            <div id="bankB"><input type="radio" name="type" value="4"></div>
            <div id="bankC"><input type="radio" name="type" value="5"></div>
            <div id="bankD"><input type="radio" name="type" value="6"></div>
            <div id="bankE"><input type="radio" name="type" value="7"></div>
            <div id="bankF"><input type="radio" name="type" value="8"></div>
            <div id="footBut"><input type="button" id="payment" value="确认并支付"></div>
        </div>
        <div id="buttypeB">
            <input type="hidden" id="pwd" value="${userDatil.paypwd}">
            <div>您当前的帐户余额为：<strong>${userDatil.userChange}</strong>元</div>
            <c:if test="${ordId==1}">
                <c:if test="${userDatil.userChange > orders.totalPrice}">
                    <div> 请输入支付密码：<input type="password" class="password">
                        <span style="color: red" id="errorInfo"></span>
                    </div>
                    <div><input type="button" value="确认并支付" class="paymenta"></div>
                </c:if>
                <c:if test="${userDatil.userChange < orders.totalPrice}">
                    <div> 您当前得余额小于支付金额${orders.totalPrice - userDatil.userChange}元
                    </div>
                    <div><input type="button" value="返回充值" class="paymentb"></div>
                </c:if>
            </c:if>
            <c:if test="${ordId==2}">
                <c:if test="${userDatil.userChange > totalPrcie}">
                    <div> 请输入支付密码：<input type="password" class="password">
                        <span style="color: red" class="errorInfo"></span>
                    </div>
                    <div><input type="button" value="确认并支付" class="paymenta"></div>
                </c:if>
                <c:if test="${userDatil.userChange < totalPrcie}">
                    <div> 您当前得余额小于支付金额${totalPrcie - userDatil.userChange}元
                    </div>
                    <div><input type="button" value="返回充值" class="paymentb"></div>
                </c:if>
            </c:if>
        </div>
        <div id="buttypeC">
            <div style="text-align: center">
                <img src="${pageContext.request.contextPath}/static/rootWeb/images/weixinapi.png" alt="微信扫码支付">
            </div>
        </div>
        <div id="buttypeD">
            <div style="text-align: center">
                <img src="${pageContext.request.contextPath}/static/rootWeb/images/zhifubaoapi.png" alt="微信扫码支付">
            </div>
        </div>
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
<%--<%
    if(null!=session.getAttribute("myorderList")){
        session.removeAttribute("myorderList");
    }
%>--%>
<input type="hidden" id="path" name="path" value="${pageContext.request.contextPath }"/>
<input type="hidden" id="error" value="${error}">
<%
    if(null!=session.getAttribute("error")){
        session.removeAttribute("error");
    }
%>
</body>
<script src="${pageContext.request.contextPath}/static/rootWeb/js/jquery-1.8.3.min.js"></script>
<script src="${pageContext.request.contextPath}/static/rootWeb/js/header.js"></script>
<script src="${pageContext.request.contextPath}/static/rootWeb/js/paymentChoice.js"></script>
</html>



