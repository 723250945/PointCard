<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="fm"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <link href="${pageContext.request.contextPath}/static/rootWeb/css/global.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/static/rootWeb/css/layout.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/static/rootWeb/css/template.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/static/rootWeb/css/myCart.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<!--头部-->
<header id="header">
    <div class="header_top">
        <input type="hidden" id="uid" value="${user.id}">
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
                <li><a href="#" target="_parent">购物车</a></li>
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
<!--中间部分开始-->
<div id="main">
    <div>&nbsp;&nbsp;<img src="${pageContext.request.contextPath}/static/rootWeb/images/shopping_myshopping.gif" alt="shopping"/> <a href="#">全场免运费活动中</a></div>
    <c:if test="${not empty browseList}">
        <!--为您推荐商品开始-->
        <div class="shopping_commend">
            <div class="shopping_commend_left">根据您挑选的商品，勇士点卡商城为您推荐</div>
            <div class="shopping_commend_right"><img src="${pageContext.request.contextPath}/static/rootWeb/images/shopping_arrow_up.gif" alt="shopping" id="shopping_commend_arrow"/></div>
        </div>
        <div id="shopping_commend_sort">
            <div class="shopping_commend_sort_left">
        <c:forEach var="card" items="${browseList}" varStatus="indexo">
                <ul>
                    <li class="shopping_commend_list_1">·<a href="${pageContext.request.contextPath}/static/toCardView/${card.cId}/${card.gid}" class="blue">${card.cName}</a></li>
                    <li class="shopping_commend_list_2">￥${card.iniPrice}</li>
                    <li class="shopping_commend_list_3">￥${card.price}</li>
                    <li class="shopping_commend_list_4"><a href="#" class="shopping_yellow">购买</a></li>
                    <li class="shopping_commend_list_5" style="display: none">${card.titleImg}</li>
                    <li class="shopping_commend_list_6" style="display: none">${card.gName}</li>
                    <li class="shopping_commend_list_7" style="display: none">${card.cName}</li>
                    <li class="shopping_commend_list_8" style="display: none">${card.cId}</li>
                    <li class="shopping_commend_list_9" style="display: none">${card.gid}</li>
                </ul>
            <c:if test="${indexo.count/1==5}">
                </div>
                <div class="shopping_commend_sort_mid"></div>
                <div class="shopping_commend_sort_left">
            </c:if>
            <c:if test="${indexo.last}">
                        </div>
                        </div>
            </c:if>
        </c:forEach>
    </c:if>
    <div class="shopping_list_top">您已选购以下商品</div>
    <div class="shopping_list_border">
        <div id="content">
            <c:if test="${cartList!=null}">
                <form action="" method="post">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="shopping">
                        <tr class="tabas">
                            <td class="title_1"><input id="allCheckBox" type="checkbox" value=""/>全选</td>
                            <td class="title_2" colspan="2">商品名称</td>
                            <td class="title_3">原价（元）</td>
                            <td class="title_4">单价（元）</td>
                            <td class="title_5">数量</td>
                            <td class="title_6">小计（元）</td>
                            <td class="title_7">操作</td>
                        </tr>
                        <tr class="tabas" id="into">
                            <td colspan="8" class="line"></td>
                        </tr>
                        <c:forEach var="shop" items="${cartList}">
                            <tr id="product${shop.id}" class="pagn">
                                <td class="cart_td_1"><input name="cartCheckBox" type="checkbox" value="product1"/></td>
                                <td class="cart_td_2"><a href="${pageContext.request.contextPath}/static/toCardView/${shop.cId}/${shop.gid}" ><img src="${pageContext.request.contextPath}/static/rootWeb/images/${shop.titleImg}"  alt="${shop.cName}"/></a></td>
                                <td class="cart_td_3"><a href="${pageContext.request.contextPath}/static/toCardView/${shop.cId}/${shop.gid}" >${shop.cName}</a><br />
                                        ${shop.gName}<br />
                                    保障：<img src="${pageContext.request.contextPath}/static/rootWeb/images/taobao_icon_01.jpg" alt="icon" /></td>
                                <td class="cart_td_4">${shop.iniPrice}</td>
                                <td class="cart_td_5">${shop.price}</td>
                                <td class="cart_td_6"><img src="${pageContext.request.contextPath}/static/rootWeb/images/taobao_minus.jpg" alt="minus" class="hand"/> <input id="num_1" type="text"  name="byNum"  value="${shop.num}" class="byNum" min="1" readonly="readonly"/> <img src="${pageContext.request.contextPath}/static/rootWeb/images/taobao_adding.jpg" alt="add" class="hand"/></td>
                                <td class="cart_td_7"></td>
                                <td class="cart_td_8"><a href="javascript:void(0);">删除</a></td>
                                <input type="hidden" name="id" class="id" value="${shop.id}">
                                <input type="hidden" name="cId" class="cId" value="${shop.cId}">
                                <input type="hidden" name="gid" class="gid" value="${shop.gid}">
                                <input type="hidden" name="price" class="price" value="${shop.price}">
                                <input type="hidden" name="gName" class="gName" value="${shop.gName}">
                                <input type="hidden" name="cName" class="cName" value="${shop.cName}">
                            </tr>
                        </c:forEach>
                        <tr class="tabas">
                            <td  colspan="3"><a id="deleteAll" href="javascript:void(0);"><img src="${pageContext.request.contextPath}/static/rootWeb/images/taobao_del.jpg" alt="delete"/></a></td>
                            <td colspan="5" class="shopend">商品总价（不含运费）：<label id="total" class="yellow"></label> 元<br />
                                可获积分 <label class="yellow" id="integral"></label> 点<br />
                                <input id="payment" type="image" src="${pageContext.request.contextPath}/static/rootWeb/images/taobao_subtn.jpg" /></td>
                        </tr>
                    </table>
                </form>
            </c:if>
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
<!--网站版权部分开始-->
<input type="hidden" id="path" name="path" value="${pageContext.request.contextPath }"/>
<footer id="footer">
    <div class="footer_top"><a href="#" target="_parent" class="footer_dull_red">正版保障</a> | <a href="#" target="_parent" class="footer_dull_red">满额免运</a> | <a href="#" target="_parent" class="footer_dull_red">货到付款</a> | <a href="#" target="_parent" class="footer_dull_red">品种最全</a> | <a href="#" target="_parent" class="footer_dull_red">免费退换</a></div>
    <div class="footer_end">Copyright (C) 勇士点卡商城 2006-2016, All Rights Reserved  <img src="${pageContext.request.contextPath}/static/rootWeb/images/validate.gif"  alt="版权" style="vertical-align:middle;" /> 京ICP证100488号 出版物经营许可证 京批100160号</div>
</footer>
<script src="${pageContext.request.contextPath}/static/rootWeb/js/jquery-1.12.4.js"></script>
<script  src="${pageContext.request.contextPath}/static/rootWeb/js/shopping.js"></script>
<script src="${pageContext.request.contextPath}/static/rootWeb/js/header.js"></script>
</body>
</html>
