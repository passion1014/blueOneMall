<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import  url="../inc/adminChecker.jsp" />
<c:import  url="../inc/top.jsp" />
<body>

<div id="Wrap">
	
	<!-- 컨덴츠 -->
	
	<div id="Contents">
	
		
	<table>
		<colgroup>
			<col width="6%" />
			<col width="*" />
			<col width="15%" />
		</colgroup>
	
		<tr>
			<th>No</th>
			<th>배송정보</th>
			<th>선택</th>
		</tr>
			
		<c:choose>
			<c:when test="${transferList.size() != 0}">
				<c:forEach items="${transferList}" var="transferList">
					<tr>
						<td style="text-align:center;">${transferList.idx}</td>
						<td>${transferList.tContents}</td>
						<td style="text-align:center;">
							<input type="button" value="등록" onClick="location.href='transferInfoPopupProc.do?idx=${transferList.idx}';" class="Button Gray">
						</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="3" height="200"> 등록된 대분류가 없습니다.</td>
				</tr>			
		    </c:otherwise>
		</c:choose>
			
	</table>

	
	</div>	
</div>
</body>