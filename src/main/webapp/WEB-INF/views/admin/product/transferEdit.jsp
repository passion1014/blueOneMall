<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import  url="../inc/adminChecker.jsp" />
<c:import  url="../inc/top.jsp" />
<script type="text/javascript">

	function fnAddClick() {
		var f = tx_editor_form;

		f.action = 'transferEditProc.do';

		Editor.save(); // 다음 에디터
	}
	$(document).ready(function() {
		Editor.modify({
	     	"content":'${transferInfo.content}'
	     });
	});

</script>
<body>
<div id="Wrap">

	<c:import url="../inc/gnb.jsp"/>
	<c:import url="../inc/lnb.jsp">
	   <c:param name="slot" value="goods"/>
	</c:import>
	

	<div id="Contents">
	
		<h1>상품관리 &gt; 배송정보관리 &gt; <strong>배송정보등록</strong></h1>
		<form name="tx_editor_form" method="post" action="transferEditProc.do">
		<div style="padding:10px 5px 2px 5px;"><b>배송정보</b></div>
		<input type="hidden" name="idx" id="idx" value="${transferInfo.idx}">
	
		<table>
			<colgroup>
				<col width="15%">
				<col width="*">
			</colgroup>
			
			<tr>
				<th>배송정보명</th>
				<td colspan="3" class="left">
					<input type="text" id="transferTitle" name="tTitle" value="${transferInfo.tTitle}" style="width:80%;">
				</td>
			</tr>
			<tr>
				<th>상세내용</th>
				<td colspan="3" style="text-align:left;">
					
						<jsp:include page="/resources/editor/editor.jsp" />
					
				</td>
			</tr>		
		</table>
	
		<div class="Btn_area">
			<input type="button" value="수정하기" class="Button Gray" onClick="fnAddClick();" /> &nbsp; 
			<input type="button" value="취소"     class="Button Gray" onClick="history.back();">
		</div>
	
		</form>
		
	</div>
	
</div>
</body>

<c:import url="../inc/footer.jsp" />