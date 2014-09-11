<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${reloadVar=='yes'}"><script>opener.location.reload();</script></c:if>
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
	<h1>상품관리 &gt; 상품분류 &gt; <strong>중분류목록</strong></h1>
		
	<div class="right">
		<input type="button" value="중분류 등록" class="Small_Button Gray" onClick="openWin('./middleTypeRegister.do','middleTypeForm',600,450,'scrollbars=no');">
	</div>

	<table>
		<colgroup>
			<col width="6%" />
			<col width="8%" />
			<col width="30%" />
			<col width="*" />
			<col width="15%" />
		</colgroup>
	
		<tr>
			<th>No</th>
			<th>정렬순위</th>
			<th>대분류명</th>
			<th>중분류명</th>
			<th>수정/삭제</th>
		</tr>
		
		<c:choose>
			<c:when test="${list.size() != 0}">
				<c:forEach items="${list}" var="middleCategory">
					<tr>
						<td style="text-align:center;">${middleCategory.idx}</td>
						<td style="text-align:center;">${middleCategory.ctgOrder}</td>
						<td style="text-align:center;">${middleCategory.ctgLargeName}</td>
						<td>${middleCategory.ctgName}</td>
						<td style="text-align:center;">
							<input type="button" value="수정" onClick="dialogUpdate('${middleCategory.ctgCode}');" class="Button Gray">
							<input type="button" value="삭제" onClick="confirm_process('','해당 분류를 삭제하시겠습니까?','deleteMiddleCategoryInf.do?ctgCode=${middleCategory.ctgCode}');"  class="Button Gray">
							
						</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="6" height="200"> 등록된 중분류가 없습니다.</td>
				</tr>			
		    </c:otherwise>
		</c:choose>
			
	</table>
	<!-- page -->
	<div id="Paser">
		<a class="palign1" href="middleTypeList.do?page=1"><img src='/resources/img/common/btn_first.gif' alt='첫 페이지로 이동' /></a>
		
		<c:set var="prePage" value="${page-1}"/>
		<c:if test="${prePage < 1 }"><c:set var="prePage" value="1"/></c:if>
		<a class="palign2" href="middleTypeList.do?page=${prePage}"><img src='/resources/img/common/btn_prev.gif' alt='이전 페이지로 이동' /></a>
		
		<c:forEach var="i" begin="1" end="${endNum}">
			<a href="middleTypeList.do?page=${i}" <c:if test="${i == page}">class="on"</c:if>>${i}</a>
		</c:forEach>
		
		<c:set var="nextPage" value="${page+1}"/>
		<c:if test="${nextPage > endNum }"><c:set var="nextPage" value="${endNum}"/></c:if>
		<a class="palign1" href="middleTypeList.do?page=${nextPage}"><img src='/resources/img/common/btn_next.gif' alt='다음 페이지로 이동' /></a>
		
		<a class="palign2" href="middleTypeList.do?page=${endNum}"><img src='/resources/img/common/btn_end.gif' alt='마지막 페이지로 이동' /></a>
	</div>
	
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

function dialogUpdate(cCode) {
	$.ajax({
		type: "POST",
		url: "middleTypeEdit.do",
		cache: false,
		async: false,
		data: {
			ctgCode : cCode
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