<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${reloadVar=='yes'}"><script>opener.location.reload();</script></c:if>
<c:import  url="../inc/top.jsp" />
<body>
<div id="dialog-form"></div>
<div id="Wrap">
	<c:import url="../inc/gnb.jsp"/>
	<c:import url="../inc/lnb.jsp">
	   <c:param name="slot" value="conf"/>
	</c:import>
	<!-- 컨덴츠 -->
	
	<div id="Contents">
	<h1>환경설정 &gt; 계좌관리 &gt; <strong>계좌목록</strong></h1>
		
	<div class="right">
		<input type="button" value="계좌 등록" class="Small_Button Gray" onClick="openWin('./accountRegister.do','accountForm',600,450,'scrollbars=no');">
	</div>

	<table>
		<colgroup>
			<col width="6%" />
			<col width="8%" />
			<col width="*" />
			<col width="15%" />
		</colgroup>
	
		<tr>
			<th>No</th>
			<th>표시</th>
			<th>그룹</th>
			<th>은행명</th>
			<th>계좌정보</th>
			<th>수정/삭제</th>
		</tr>
			
		<c:choose>
			<c:when test="${list.size() != 0}">
				<c:forEach items="${list}" var="accList">
					<tr>
						<td style="text-align:center;">${accList.accIdx}</td>
						<td style="text-align:center;">
						<c:if test="${'y' eq accList.accShow}">표시</c:if>
						<c:if test="${'n' eq accList.accShow}">숨김</c:if>
						</td>
						<td style="text-align:center;">${accList.accGroup}</td>
						<td style="text-align:center;">${accList.bankName}</td>
						<td>계좌번호 : ${accList.accNum}<br>
							계 좌 주 : ${accList.accName}
						</td>
						<td style="text-align:center;">
							<input type="button" value="수정" onClick="dialogUpdate('${accList.accIdx}');" class="Button Gray">

							<input type="button" value="삭제" onClick="location.href='deleteAccountProc.do?accIdx=${accList.accIdx}';"  class="Button Gray">

							<%-- <input type="button" value="삭제" onClick="confirm_process('','해당 계좌를 삭제하시겠습니까?','deleteAccountProc.do?accIdx=${accList.accIdx}');"  class="Button Gray"> --%>
							<input type="button" value="삭제" onClick="confirm_process('','해당 계좌를 삭제하시겠습니까?','deleteAccountProc.do?accIdx=${accList.accIdx}');"  class="Button Gray">
							

						</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="4" height="200"> 등록된 계좌가 없습니다.</td>
				</tr>			
		    </c:otherwise>
		</c:choose>
			
	</table>

	<c:forEach var="i" begin="1" end="${endNum}">
		<input type="button" value="${i}" onClick="javascript:location.href='accountList.do?page=${i}'">				
	</c:forEach>
	
	</div>	
</div>
</body>

<c:import url="../inc/footer.jsp" />

<script language="JavaScript" type="text/JavaScript">
<!--
$(document).ready(function() {
	
	$("#dialog-form").dialog({
		autoOpen: false,
		closeOnEscape: false,
		draggable: false,
		modal: true,
		resizable: false,
		title: '',
		width: 650,
		//height: 0,
		//zIndex: 0,
		create: function(event, ui) {},
		open: function(event, ui) {},
		close: function(event, ui) {}
	});

});

function openDialog() {
	$("#dialog-form").dialog("open");
}

function closeDialog() {
	$("#dialog-form").dialog("close");
}

function dialogUpdate(idx) {
	$.ajax({
		type: "GET",
		url: "accountEdit.do",
		cache: false,
		async: false,
		data: {
			accIdx : idx
		},
		dataType: "html",
		success: function(e) {
			$("#dialog-form").html(e);
			openDialog();
		}
	});
}

//-->
</script>
