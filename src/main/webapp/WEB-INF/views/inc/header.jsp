<%@page import="java.util.List"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="com.blueone.category.domain.CategoryInfo"%>
<%@page import="com.blueone.category.service.CategoryManageServiceImpl"%>
<%@page import="com.blueone.category.service.ICategoryManageService"%>
<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	ApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
	ICategoryManageService categoryService = (CategoryManageServiceImpl) ctx.getBean(ICategoryManageService.class);
	
	// 조회
	CategoryInfo categoryInfo = new CategoryInfo();
	List<CategoryInfo> largeMenuList = categoryService.getCategoryInfList2(categoryInfo);
%>
<!--  header 시작 -->
<div class="header">
	<div class="top">
		<h1><a href="#"><img src="<c:url value='/resources/img/main/common/blueone_logo.png'/>"/></a></h1>
		<p class="top_img">
			<img src="<c:url value='/resources/img/main/common/top_image.jpg'/>"/>
		</p>
		<form action="#" method="post">
			<div class="search_area">
				<span class="search_menu">
					<a href="/order/cartListView.do" class="menu_site">장바구니</a>
					<a href="/user/orderListView.do" class="menu_site">주문배송 조회</a>
					<a href="/user/userEdit.do">마이페이지</a>
				</span>
				<span class="search_box">
					<input type="text" title="서치텍스트박스"/>
					<button></button>
				</span>
			</div>
		</form>
	</div>
	
	<div class="gnb">
		<ul>
			<c:set value="1" var="counterNumber" />
			<c:forEach begin="0" end="8" step="1" var="largeList" items="<%=largeMenuList%>">
				<c:choose>
					<c:when test="${counterNumber == '1'}">
						<li><a href="/product/productList.do?ctgCode=${largeList.ctgCode}">${largeList.ctgName}</a></li>
					</c:when>
					<c:otherwise>
						<li class="gnb_list"><a href="/product/productList.do?ctgCode=${largeList.ctgCode}">${largeList.ctgName}</a></li>
					</c:otherwise>
				</c:choose>	
				<c:set value="${counterNumber+1}" var="counterNumber" />
			</c:forEach>
		</ul>
	</div>
</div>
<!--  header 끝   -->