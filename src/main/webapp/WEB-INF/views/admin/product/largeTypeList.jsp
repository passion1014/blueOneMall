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
	<h1>상품관리 &gt; 상품분류 &gt; <strong>대분류목록</strong></h1>
		
	<div class="right">
		<input type="button" value="대분류 등록" class="Small_Button Gray" onClick="openWin('./largeTypeRegister.do','largeTypeForm',600,450,'scrollbars=no');">
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
			<th>정렬순위</th>
			<th>분류명</th>
			<th>수정/삭제</th>
		</tr>
			
		<c:choose>
			<c:when test="${list.size() != 0}">
				<c:forEach items="${list}" var="category">
					<tr>
						<td style="text-align:center;">${category.idx}</td>
						<td style="text-align:center;">${category.ctgOrder}</td>
						<td>${category.ctgName}</td>
						<td style="text-align:center;">
							<input type="button" value="수정" onClick="dialogUpdate('${category.ctgCode}');" class="Button Gray">
							<input type="button" value="삭제" onClick="confirm_process('','해당 분류를 삭제하시겠습니까?','deleteCategoryInf.do?ctgCode=${category.ctgCode}');"  class="Button Gray">
						</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="4" height="200"> 등록된 대분류가 없습니다.</td>
				</tr>			
		    </c:otherwise>
		</c:choose>
			
	</table>
	<c:forEach var="i" begin="1" end="${endNum}">
		<input type="button" value="${i}" onClick="javascript:location.href='largeTypeList.do?page=${i}'">				
	</c:forEach>
	<div id="Paser">
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
		url: "largeTypeEdit.do",
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