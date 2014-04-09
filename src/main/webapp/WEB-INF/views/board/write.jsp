<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.blueone.common.util.CKEditorHelper" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">

function fnAddClick() {
	var f = tx_editor_form;

	f.action = 'http://posttestserver.com/post.php';

	Editor.save(); // 다음 에디터
//	f.submit();
	
}

</script>

<!-- <form name="boardForm" method="post" enctype="multipart/form-data" action=""> -->
<%-- <form name="boardForm" method="post" action="">
<input type="hidden" name="currentPage" value="${srchInfo.currentPage}" />
<input type="hidden" name="srchBrdTyp" value="${srchBrdTyp}" />

	<div>
		<input type="button" name="확인" onclick="fnAddClick()"> 
		<input type="button" name="취소" onclick="fnList()">
	</div>
</div>
</form> --%>

다음에디터<br/>

<form name="tx_editor_form" method="post" enctype="multipart/form-data" action="http://posttestserver.com/post.php">
	작성자 : <input type="text" name="writer" /><br/>	
	제목 : <input type="text" name="title" />
	<jsp:include page="/resources/editor/editor.jsp" />
	<input type="button" name="확인" onClick="fnAddClick();"/>
</form>
