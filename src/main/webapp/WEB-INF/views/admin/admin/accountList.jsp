<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${reloadVar=='yes'}"><script>opener.location.reload();</script></c:if>

<c:import  url="../inc/top.jsp" />

<body>
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
			<col width="8%" />
			<col width="12%" />
			<col width="*" />
			<col width="12%" />
			<col width="12%" />
		</colgroup>
	
		<tr>
			<th>No</th>
			<th>표시</th>
			<th>정렬순위</th>
			<th>거래은행명</th>
			<th>계좌번호</th>
			<th>계좌주</th>
			<th>수정/삭제</th>
		</tr>
			
		<c:choose>
			<c:when test="${list.size() != 0}">
				<c:forEach items="${list}" var="accList">
					<tr>
						<td class="center">${accList.accIdx}</td>
						<td class="center">
							<c:if test="${'y' eq accList.accShow}">표시</c:if>
							<c:if test="${'n' eq accList.accShow}">숨김</c:if>
						</td>
						<td class="center">${accList.accGroup}</td>
						<td class="center">${accList.bankName}</td>
						<td>${accList.accNum}</td>
						<td class="center">${accList.accName}</td>
						<td class="center">
							<input type="button" value="수정" onClick="dialogUpdate('${accList.accIdx}');" class="Button Gray">
							<input type="button" value="삭제" class="Button Gray" onClick="confirm_process('','해당 계좌를 삭제하시겠습니까?','deleteAccountProc.do?accIdx=${accList.accIdx}');">
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

	<!-- page -->
	<div id="Paser">
		<a class="palign1" href="accountList.do?page=1"><img src='/resources/img/common/btn_first.gif' alt='첫 페이지로 이동' /></a>
		
		<c:set var="prePage" value="${page-1}"/>
		<c:if test="${prePage < 1 }"><c:set var="prePage" value="1"/></c:if>
		<a class="palign2" href="accountList.do?page=${prePage}"><img src='/resources/img/common/btn_prev.gif' alt='이전 페이지로 이동' /></a>
		
		<c:forEach var="i" begin="1" end="${endNum}">
			<a href="accountList.do?page=${i}" <c:if test="${i == page}">class="on"</c:if>>${i}</a>
		</c:forEach>
		
		<c:set var="nextPage" value="${page+1}"/>
		<c:if test="${nextPage > endNum }"><c:set var="nextPage" value="${endNum}"/></c:if>
		<a class="palign1" href="accountList.do?page=${nextPage}"><img src='/resources/img/common/btn_next.gif' alt='다음 페이지로 이동' /></a>
		
		<a class="palign2" href="accountList.do?page=${endNum}"><img src='/resources/img/common/btn_end.gif' alt='마지막 페이지로 이동' /></a>
	</div>

</div>
</body>

<c:import url="../inc/footer.jsp" />

<div id="dialog-form"></div>
<script language="JavaScript">
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
