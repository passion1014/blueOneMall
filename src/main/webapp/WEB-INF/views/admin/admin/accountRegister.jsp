<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${reloadVar=='yes'}"><script>opener.location.reload();</script></c:if>


<c:import  url="../inc/top.jsp" />


<body>
<div id="Wrap">
	<!-- 컨덴츠 -->
	<div style="padding:10px;">
	<div> 환경설정 > 계좌관리 > 계좌 등록	</div>
	<form name="frm" method="post" action="accountRegisterProc.do">
	
	<table class="tbl1">
		<colgroup>
			<col width="20%" />
			<col width="50%" />
			<col width="*" />
		</colgroup>

		<tr>
			<th>표시/그룹</th>
			<td class="left">
				<select id="accShow" name="accShow">
					<option value="y"><c:out value="표시"></c:out></option>
					<option value="n"><c:out value="숨김"></c:out></option>							
				</select>&nbsp;
				<select id="accGroup" name="accGroup">
				<c:forEach var="i" begin="1" end="30" step="1">
					<option value="<c:out value="${i}"></c:out>"><c:out value="${i}"></c:out></option>
				</c:forEach>							
				</select>&nbsp;
			</td>
		</tr>
		<tr>
			<th>거래은행</th>
			<td colspan="3" class="left">
				<select id="accBank" name="accBank">
				<c:forEach items="${bankList}" var="bankList">
					<option value="<c:out value="${bankList.bankType}"></c:out>"><c:out value="${bankList.bankName}"></c:out></option>
				</c:forEach>							
				</select>&nbsp;
			</td>
		</tr>
		<tr>
			<th>계좌번호</th>
			<td colspan="3" class="left">
				<input type="text" id="accNum" name="accNum" class="Text Kor" style="width:90%;" required hname="'-'없이 숫자만입력해주세요">
			</td>
		</tr>
		<tr>
			<th>계좌주</th>
			<td colspan="3" class="left">
				<input type="text" id="accName" name="accName" class="Text Kor" style="width:90%;" required hname="이름을 입력해주세요">
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