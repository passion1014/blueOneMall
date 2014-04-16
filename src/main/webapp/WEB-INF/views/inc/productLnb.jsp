<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="lnb">
	
	<h3>
	
		
			${lMenuDetail.ctgName}
	
	</h3>
	
	<ul class="lnb_list">
		<%-- <c:forEach items="${categoryList}" var="lList">
			<li><a href="#">${lList.ctgName}</a></li>
			
			
			<c:forEach items="${list1}" var="sList">
				<div class="onclick_list">				
				<c:if test="${sList.ctgCodeType == '03'}">
					<c:if test="${sList.ctgPCode} == ${lList.ctgCode}">
						<a href="#">${sList.ctgName}</a>
					</c:if>					
				</c:if>
				</div>					
			</c:forEach>
			
				
		</c:forEach> --%>
		
		<c:forEach items="${middleCode}" var="middleCode">
				<c:if test="${middleCode.ctgPCode} == ${lMenuDetail.ctgCode}">
					<c:out value="${middleCode.ctgName}"></c:out>
				</c:if>
		</c:forEach> 
		
		
		
		
		<!-- <li><a href="#">Multimedia(MM)</a></li>
		<li><a href="#">Amp</a></li>
		<div class="onclick_list">
			<a href="#">인디앰프</a>
			<a href="#">프리앰프</a>
			<a href="#">파워앰프</a>
			<a href="#">올인원앰프/시스템</a>
			<a href="#">포노앰프</a>
			<a href="#">홈시어터앰프</a>
		</div>
		<li><a href="#">Speaker</a></li>
		<div class="onclick_list">
			<a href="#">Hi-Fi Collecion</a>
			<a href="#">톨보이스커</a>
			<a href="#">북셀프스피커</a>
			<a href="#">멀티채널스피커</a>
			<a href="#">사운드바</a>
		</div> -->
	</ul>
</div>