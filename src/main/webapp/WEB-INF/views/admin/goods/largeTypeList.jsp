<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import  url="../inc/top.jsp" />
<c:set var="test" value="goodsList" scope="request"/> 
<body>
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
			<col width="8%" />
			<col width="8%" />
			<col width="8%" />
			<col width="*" />
			<col width="15%" />
		</colgroup>
	
		<tr>
			<th>No</th>
			<th>표시</th>
			<th>정렬순위</th>
			<th>분류명</th>
			<th>수정 / 삭제</th>
		</tr>
	<c:forEach items="${list}" var="goods">
		<tr>
			<td><a href="largeTypeEdit.do?idx=${goods.idx}>"${goods.idx}</a></td>
			<td></td>
			<td></td>
			<td class="left">${goods.ctgName}</td>
			<td>
				<input type="button" value="수정" class="Small_Button Gray" onClick="dialogUpdate('');">&nbsp;&nbsp;
				<input type="button" value="삭제" class="Small_Button Gray" onClick="confirm_process('actionForm','대분류를 삭제하시겠습니까? \n\n삭제 후에는 복구가 불가능합니다. ','./_action/product.do.php?Mode=del_large_type&idx=')">
			</td>
		</tr>
	</c:forEach>
		<tr>
			<td colspan="6" height="200">등록된 대분류가 없습니다.</td>
		</tr>
	
	</table>

	<div id="Paser">
	</div>
	
	</div>	
</div>
</body>

<script language="JavaScript" type="text/JavaScript">
<!--
$(document).ready(function() {
	
	$("#dialog-form").dialog({
		autoOpen: false,
		closeOnEscape: false,
		draggable: false,
		modal: true,
		resizable: false,
		title: '분류 관리',
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

function dialogInput() {
	$.ajax({
		type: "POST",
		url: "./goods/largeRegister.do",
		cache: false,
		async: false,
		dataType: "html",
		success: function(e) {
			$("#dialog-form").html(e);
			openDialog();
		}
	});
}

function dialogUpdate(widx) {
	$.ajax({
		type: "POST",
		url: "./goods/largeUpdate.do",
		cache: false,
		async: false,
		data: {
			type_idx: widx
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


<c:import url="../inc/footer.jsp" />