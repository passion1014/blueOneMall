<%@page import="java.util.List"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="com.blueone.category.domain.CategoryInfo"%>
<%@page import="com.blueone.admin.domain.AdImgInfo"%>
<%@page import="com.blueone.admin.domain.SchWordInfo"%>

<%@page import="com.blueone.category.service.CategoryManageServiceImpl"%>
<%@page import="com.blueone.category.service.ICategoryManageService"%>
<%@page import="java.net.URLDecoder"%>
<%@page import= "com.blueone.shop.service.IShopService"%>
<%@page import ="com.blueone.shop.service.ShopServiceImpl"%>
<%@page import= "com.blueone.admin.service.ISearchWordService"%>
<%@page import ="com.blueone.admin.service.SearchWordServiceImpl"%>

<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	ApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
	ICategoryManageService categoryService = (CategoryManageServiceImpl) ctx.getBean(ICategoryManageService.class);
	
	ApplicationContext ctx1 = RequestContextUtils.getWebApplicationContext(request);
	IShopService shopService=(ShopServiceImpl)ctx1.getBean(IShopService.class);
	
	
	ApplicationContext ctx2 = RequestContextUtils.getWebApplicationContext(request);
	ISearchWordService schWordService=(SearchWordServiceImpl)ctx2.getBean(ISearchWordService.class);
	
	// 조회
	CategoryInfo categoryInfo = new CategoryInfo();
	List<CategoryInfo> largeMenuList = categoryService.getCategoryInfList2(categoryInfo);
	
	//메인 이미지
	AdImgInfo AdImgDtl = new AdImgInfo();
	AdImgDtl = shopService.getAdImg(AdImgDtl);
	
	//검색순위
	SchWordInfo schWord = new SchWordInfo();
	schWord.setShowMain(true);
	List<SchWordInfo> schWordList = schWordService.getSchWordDtlList(schWord);
	
%>
<!--  header 시작 -->
<div class="header">
	<div class="top">
		<h1><a href="../"><img src="<c:url value='/resources/img/main/common/blueone_logo.png'/>"/></a></h1>
		<p class="top_img">
			<img src="<%=AdImgDtl.getBnImg7()%>"/>
		</p>
		<form method="POST" id="topSFrm" name="topSFrm" action="/product/searchProduct.do">
		<div class="search_area">
			<span class="search_menu" style="text-align:right;">
				<a href="/order/cartListView.do" class="menu_site">장바구니</a>
				<a href="/user/orderListView.do" class="menu_site">주문배송 조회</a>
				<a href="/user/userEdit.do">마이페이지</a>
			</span>
			<span class="search_box">
				<input type="text" id="schWord" name="schWord" value="${schword}"title="서치텍스트박스"/>
				<!--  input type="button" value="검색" onClick="javascript:location.href='/product/searchProduct.do?schWord='+document.getElementById('schWord').value;"/-->
				<button onClick="topSFrm.submit();" style="cursor:pointer;"></button>
				<select onChange="document.getElementById('schWord').value = this.value;">
					<c:forEach var="schWordList" items="<%=schWordList%>">
						<option value='${schWordList.swWord}'>${schWordList.swWord}</option>
					</c:forEach>
				</select>
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