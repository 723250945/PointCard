
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="fm"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>勇士点卡商城订单中心</title>
    <link href="${pageContext.request.contextPath}/static/rootWeb/css/global.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/static/rootWeb/css/layout.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/static/rootWeb/css/template.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/static/rootWeb/css/public.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/static/rootWeb/css/style.css" rel="stylesheet" type="text/css"/>
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
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>订单管理页面</span>
        </div>
        <div class="search">
            <form method="post" action="">
                <input name="method" value="search" class="input-text" type="hidden">
                <span>订单名称：</span>
                <input name="orderName" type="text" value="${orderName }" id="orderName">
                <span>订单编号：</span>
                <input name="" type="text" value="${id}" id="id">
                <span>是否付款：</span>
                <select name="queryIsPayment" id="queryIsPayment">
                    <option value="0">--请选择--</option>
                    <option value="1" ${queryIsPayment == 1 ? "selected=\"selected\"":"" }>未付款</option>
                    <option value="2" ${queryIsPayment == 2 ? "selected=\"selected\"":"" }>已付款</option>
                </select>
                <input type="hidden" name="pageIndex" value="1"/>
                <input	value="查 询" type="submit" id="searchbutton" onclick="searchList();">
            </form>
        </div>
        <!--账单表格 样式和供应商公用-->
        <table class="providerTable" cellpadding="0" cellspacing="0">
            <tr class="firstTr">
                <th width="20%">订单编号</th>
                <th width="20%">订单名称</th>
                <th width="10%">下单时间</th>
                <th width="10%">订单金额</th>
                <th width="10%">支付状态</th>
                <th width="30%">操作</th>
            </tr>
            <c:forEach var="order" items="${orderPages.newList}" varStatus="status">
                <tr>
                    <td>
                        <span><a href="#" class="blue">${order.id}</a></span>
                    </td>
                    <td>
                        <span><label>${order.orderName}</label></span>
                    </td>
                    <td>
                        <span><fmt:formatDate value="${order.byTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                    </td>
                    <td>
                        <span>￥<label>${order.totalPrice}</label>(99折)</span>
                    </td>
                    <td>
					<span>
						 <c:if test="${order.paymentState==1}">
                             未支付
                         </c:if>
                        <c:if test="${order.paymentState==2}">
                            已支付
                        </c:if>
					</span>
                    </td>
                    <td>
					<span>
					<fmt:formatDate value="${bill.creationDate}" pattern="yyyy-MM-dd"/>
					</span>
                    </td>
                    <td>
                        <span><a class="viewBill" href="javascript:;" ordId=${order.id }><img src="${pageContext.request.contextPath }/static/rootWeb/images/read.png" alt="查看" title="查看"/></a></span>
                        <span><a class="modifyBill" href="javascript:;" ordId=${order.id } }><img src="${pageContext.request.contextPath }/static/rootWeb/images/xiugai.png" alt="评论" title="评论"/></a></span>
                        <span><a class="deleteBill" href="javascript:;" ordId=${order.id }><img src="${pageContext.request.contextPath }/static/rootWeb/images/schu.png" alt="删除" title="删除"/></a></span>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <input type="hidden" id="totalPageCount" value="${orderPages.totalPageCount}"/>
        <c:import url="rollpage.jsp">
            <c:param name="totalCount" value="${orderPages.totalCount}"/>
            <c:param name="currentPageNo" value="${orderPages.currPageNo}"/>
            <c:param name="totalPageCount" value="${orderPages.totalPageCount}"/>
        </c:import>
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
<script  src="${pageContext.request.contextPath}/static/rootWeb/js/myOrder.js"></script>
<script src="${pageContext.request.contextPath}/static/rootWeb/js/header.js"></script>
</body>
</html>

