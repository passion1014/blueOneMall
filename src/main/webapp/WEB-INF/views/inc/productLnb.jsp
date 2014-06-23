<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="lnb">
	<h3><a href="productList.do?ctgCode=${largeInf.ctgCode}">${largeInf.ctgName}</a></h3>
	<ul class="lnb_list">
	
		<c:forEach items="${lnbList}" var="lnbList">
			<li><a href="productList.do?ctgCode=${largeInf.ctgCode}&ctgMiddleCode=${lnbList.ctgCode}"><c:out value="${lnbList.ctgName}"></c:out></a></li>
			<c:if test="${lnbSList.size() != 0 && lnbList.ctgCode == chkMiddleCode}">
				<div class="onclick_list">
					<c:forEach items="${lnbSList}" var="lnbSList">
							<a href="productList.do?ctgCode=${largeInf.ctgCode}&ctgMiddleCode=${lnbList.ctgCode}&prdCtgS=${lnbSList.ctgCode}">${lnbSList.ctgName}</a>
					</c:forEach>	
				</div>			
			</c:if>			
		</c:forEach>
	
		<%-- <c:forEach items="${lnbList}" var="lnbList">
			<c:if test="${lnbList.ctgCode eq chkMiddleCode}">
				<li><a href="productList.do?ctgCode=${largeInf.ctgCode}&ctgMiddleCode=${lnbList.ctgCode}"><c:out value="${lnbList.ctgName} 1"></c:out></a></li>
				<div id="ss_${lnbList.ctgCode}" class="onclick_list" style="display:block;">
					<c:forEach items="${categoryList}" var="smallList">
						<c:if test="${smallList.ctgPCode eq lnbList.ctgCode}">
							<a href="#">${smallList.ctgName}</a>
						</c:if>
					</c:forEach>	
				</div>			
			</c:if>
			
			<c:if test="${lnbList.ctgCode ne chkMiddleCode}">
				<li><a href="productList.do?ctgCode=${largeInf.ctgCode}&ctgMiddleCode=${lnbList.ctgCode}"><c:out value="${lnbList.ctgName}"></c:out></a></li>
			</c:if>			
		</c:forEach> --%>
		
		
		<%-- <c:forEach items="${lnbList}" var="lnbList">
			<c:set value="0" var="smallCounter"></c:set>					
			<c:forEach items="${categoryList}" var="countList">
				<c:if test="${countList.ctgPCode eq lnbList.ctgCode}">
					<c:set value="${smallCounter+1}" var="smallCounter" />					
				</c:if>
			</c:forEach>
			<c:if test="${smallCounter > 0}">
				<li><a href="productList.do?ctgCode=${largeInf.ctgCode}" onClick="document.getElementById('ss_${lnbList.ctgCode}').style.display='block';"><c:out value="${lnbList.ctgName}"></c:out></a></li>
				<div id="ss_${lnbList.ctgCode}" class="onclick_list" style="display:none;">
					<c:forEach items="${categoryList}" var="smallList">
						<c:if test="${smallList.ctgPCode eq lnbList.ctgCode}">
							<a href="#">${smallList.ctgName}</a>
						</c:if>
					</c:forEach>	
				</div>			
			</c:if>
			<c:if test="${smallCounter < 1}">
				<li><a href="productList.do?ctgCode=${largeInf.ctgCode}"><c:out value="${lnbList.ctgName}"></c:out></a></li>			
			</c:if>
		</c:forEach> --%>
	</ul>
</div>