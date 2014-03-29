<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="Wrap">
	<!-- 컨덴츠 -->
	<div style="padding:10px;">
	<div> 상품관리 > 상품분류 > 대분류 수정	</div>
	<form name="frm" method="post" action="editCategoryInfProc.do" onSubmit="return chkForm(this);">
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
			<col width="30%" />
			<col width="20%" />
			<col width="*" />
		</colgroup>

		<tr>
			<th>표시여부</th>
			<td class="left">
				<select id="type_status" name="type_status">
					<option value="y">표시</option>
					<option value="n">숨김</option>
				</select>&nbsp;
			</td>
			<th>정렬순위</th>
			<td class="left">
				<select id="ctgOrder" name="ctgOrder">
				<c:forEach var="i" begin="1" end="50" step="1">
					<option value="<c:out value="${i}"></c:out>"><c:out value="${i}"></c:out></option>
				</c:forEach>							
				</select>&nbsp;
			</td>
		</tr>
		<tr>
			<th>대분류명</th>
			<td colspan="3" class="left">
				<input type="text" id="ctgName" name="ctgName" value="${largeTypeObj.ctgName}" class="Text Kor" style="width:90%;">
			</td>
		</tr>
	</table>

	<div style="margin-top:10px;text-align:center;">
		<input type="submit" value="수정하기" class="Button Gray">
		<input type="button" value="창닫기" class="Button Gray" onClick="closeDialog();">
	</div>
	</form>
	</div>
		
</div>