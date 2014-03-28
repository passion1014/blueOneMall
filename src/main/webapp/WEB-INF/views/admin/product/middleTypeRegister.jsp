<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${reloadVar=='yes'}"><script>opener.location.reload();</script></c:if>

<c:import  url="../inc/top.jsp" />


<body>
<div id="Wrap">
	<!-- 컨덴츠 -->
	<div style="padding:10px;">
	<div> 상품관리 > 상품분류 > 중분류 등록	</div>
	<form name="frm" method="post" action="middleTypeRegisterProc.do">
	<input type="text" id="ctgPCode"     name="ctgPCode" value="">
	<input type="text" id="ctgCodeType"  name="ctgCodeType"  value="01">
	<input type="text" id="ctgDesc"      name="ctgDesc"      value="">
	<input type="text" id="fromDate"     name="fromDate" value="1900-01-01">
	<input type="text" id="toDate"       name="toDate" value="9999-12-31">
	<input type="text" id="modifyUserId" name="modifyUserId" value="${adminSession.id}">
	
	<table class="tbl1">
		<colgroup>
			<col width="20%" />
			<col width="30%" />
			<col width="20%" />
			<col width="*" />
		</colgroup>

		<tr>
			<th>정렬순위</th>
			<td colspan="3" class="left">
				<select id="ctgOrder" name="ctgOrder">
				<c:forEach var="i" begin="1" end="50" step="1">
					<option value="<c:out value="${i}"></c:out>"><c:out value="${i}"></c:out></option>
				</c:forEach>							
				</select>&nbsp;
			</td>
		</tr>
		
		<tr>
			<th>대분류</th>
			<td colspan="3" class="left">
				<select id="ctgOrder" name="ctgOrder">
				<c:forEach var="i" begin="1" end="50" step="1">
					<option value="<c:out value="${i}"></c:out>"><c:out value="${i}"></c:out></option>
				</c:forEach>							
				</select>&nbsp;
			</td>
		</tr>
		
		<tr>
			<th>중분류명</th>
			<td colspan="3" class="left">
				<input type="text" id="ctgName" name="ctgName" class="Text Kor" style="width:90%;" required hname="대분류명을 입력하여 주십시오">
			</td>
		</tr>
	</table>

	<div style="margin-top:10px;text-align:center;">
		<input type="submit" value="등록하기" class="Button Gray">
		<input type="button" value="창닫기" class="Button Gray" onClick="self.close();">
	</div>
	</form>
	</div>
		
</div>
</body>


<c:import url="../inc/footer.jsp" />