<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import  url="../inc/adminChecker.jsp" />
<c:import  url="../inc/top.jsp" />
<body>
<div id="dialog-form"></div>
<div id="Wrap">
	<c:import url="../inc/gnb.jsp"/>
	<c:import url="../inc/lnb.jsp">
	 	<c:param name="slot" value="goods"/>
	</c:import>
	<!-- 컨덴츠 -->
	
	<div id="Contents">
	<h1>상품관리 &gt; 검색어 관리&gt; <strong>검색어 목록</strong></h1>
		
	<table>
		<colgroup>
			<col width="10%" />
			<col width="*" />
			<col width="10%" />
			<col width="10%" />
		</colgroup>
	
		<tr>
			<th>검색순위</th>
			<th>검색어</th>
			<th>HIT</th>
			<th>수정</th>
		</tr>
			
		<c:choose>
			<c:when test="${list.size() != 0}">
				<c:forEach items="${list}" var="schWord" varStatus="i">
					<tr>
						<td class="center">${i.index+1}</td>
						<td style="padding-left:30px;">${schWord.swWord}</td>
						<td class="center">${schWord.swHit}</td>
						<td class="center">
							<input type="button" value="수정" onClick="openWin('./searchWordEdit.do?swRank=${schWord.swRank}','largeTypeEditForm',600,450,'scrollbars=no');" class="Button Gray">
							<%-- <input type="button" value="삭제" onClick="confirm_process('','해당 분류를 삭제하시겠습니까?','deleteCategoryInf.do?ctgCode=${category.ctgCode}');"  class="Button Gray"> --%>
						</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="3" height="200"> 등록된 검색어가 없습니다.</td>
				</tr>			
		    </c:otherwise>
		</c:choose>
			
	</table>
	
	<div id="Paser">
	</div>
	
	</div>	
</div>
</body>

<c:import url="../inc/footer.jsp" />
