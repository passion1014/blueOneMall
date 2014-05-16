<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import  url="../inc/adminChecker.jsp" />
<c:import  url="../inc/top.jsp" />
<body>

<div id="Wrap">
	<c:import url="../inc/gnb.jsp"/>
	<c:import url="../inc/lnb.jsp">
	   <c:param name="slot" value="goods"/>
	</c:import>
	<!-- 컨덴츠 -->
	
	<div id="Contents">
	<h1>상품관리 &gt; 배송정보관리 &gt; <strong>배송정보목록</strong></h1>
		
	<table>
		<colgroup>
			<col width="6%" />
			<col width="*" />
			<col width="15%" />
		</colgroup>
	
		<tr>
			<th>No</th>
			<th>배송정보</th>
			<th>수정/삭제</th>
		</tr>
			
		<c:choose>
			<c:when test="${transferList.size() != 0}">
				<c:forEach items="${transferList}" var="transferList">
					<tr>
						<td style="text-align:center;">${transferList.idx}</td>
						<td>
							${transferList.tTitle} <BR />
						</td>
						<td style="text-align:center;">
							<input type="button" value="수정" onClick="location.href='transferEdit.do?idx=${transferList.idx}';" class="Button Gray">
							<input type="button" value="삭제" onClick="confirm_process('','해당 분류를 삭제하시겠습니까?','transferDelete.do?idx=${transferList.idx}');"  class="Button Gray">
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
	
	<span class="f_right">
		<c:forEach var="i" begin="1" end="${endNum}">
			<input type="button" value="${i}" onClick="javascript:location.href='productList.do?page=${i}'">				
		</c:forEach>
		</span>
	<div id="Paser">
	</div>
	
	</div>	
</div>
</body>

<c:import url="../inc/footer.jsp" />
