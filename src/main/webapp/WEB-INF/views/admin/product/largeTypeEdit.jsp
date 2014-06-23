<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${reloadVar=='yes'}"><script>opener.location.reload();</script></c:if>

<c:import  url="../inc/adminChecker.jsp" />
<c:import  url="../inc/top.jsp" />
<body>
<div id="Wrap">
	<!-- 컨덴츠 -->
	<div style="padding:10px;">
	<div> 상품관리 > 상품분류 > 대분류 수정	</div>
	<form  method="post" action="editCategoryInfProc.do" enctype="multipart/form-data">
	<input type="hidden" id="ctgPCode"     name="ctgPCode"     value="${largeTypeObj.ctgPCode}">
	<input type="hidden" id="ctgCodeType"  name="ctgCodeType"  value="${largeTypeObj.ctgCodeType}">
	<input type="hidden" id="ctgDesc"      name="ctgDesc"      value="${largeTypeObj.ctgDesc}">
	<input type="hidden" id="ctgCode"      name="ctgCode"      value="${largeTypeObj.ctgCode}">
	<input type="hidden" id="fromDate"     name="fromDate"     value="1900-01-01">
	<input type="hidden" id="toDate"       name="toDate"       value="9999-12-31">
	<input type="hidden" id="modifyUserId" name="modifyUserId" value="${adminSession.id}">
	
	<table class="tbl1">
		<colgroup>
			<col width="20%" />
			<col width="80%" />
		</colgroup>

		<tr>
			<th>정렬순위</th>
			<td class="left">
				<select id="ctgOrder" name="ctgOrder">
				<c:forEach var="i" begin="1" end="50" step="1">
				
				<c:if test="${i==largeTypeObj.ctgOrder}"> 
				<option selected><c:out value="${largeTypeObj.ctgOrder}"></c:out></option>
				
				</c:if>
				<c:if test="${i!=largeTypeObj.ctgOrder}"> 
				<option value="<c:out value="${i}"></c:out>"><c:out value="${i}"></c:out></option>
				</c:if>
					
				</c:forEach>
									
				</select>&nbsp;
			</td>
		</tr>
		<tr>
			<th>대분류명</th>
			<td class="left">
				<input type="text" id="ctgName" name="ctgName" value="${largeTypeObj.ctgName}" class="Text Kor" style="width:90%;">
			</td>
		</tr>
		<tr>
			<th>대분류 사진</th>
			<td>
					<c:if test="${ largeTypeObj.largeImgPath ne ''}">
						<c:set var="listImageViewVal" value="Y"/>
						<img src="${largeTypeObj.largeImgPath}">
						<input type="button" value="삭제" onClick="confirm_process('','해당 사진을 삭제하시겠습니까?','deleteCategoryImg.do?ctgCode=${largeTypeObj.ctgCode}');"  class="Button Gray" />
						
					</c:if>
					
					<c:if test="${largeTypeObj.largeImgPath eq ''}">
						<input type="file" id="largeTpImg" name="largeTpImg" style="width:80%;" />
					</c:if>
			</td>
		</tr>
		
	</table>

	<div style="margin-top:10px;text-align:center;">
		<input type="submit" value="수정하기" class="Button Gray">
		<input type="button" value="창닫기" class="Button Gray" onClick="self.close();">
	</div>
	</form>
	</div>
		
</div>
</body>
