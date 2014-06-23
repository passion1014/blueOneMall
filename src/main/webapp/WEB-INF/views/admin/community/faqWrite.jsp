<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${reloadVar=='yes'}"><script>opener.location.reload();</script></c:if>

<c:import  url="../inc/top.jsp" />

<script type="text/javascript">
<!--
	function submitFaqFm(_frm) {
		
		document.faqWriteFm.faqAns.value = escape(document.faqWriteFm.faqAns.value);
		
		_frm.action ='faqWriteProc.do';
		
		_frm.submit();
	}
-->
</script>

<body>
<div id="Wrap">
	<div class="cont_popuparea">
	<h1>커뮤니티 &gt; FAQ &gt; <strong>FAQ</strong></h1>
	
	
<form  method="post" action="faqWriteProc.do" name="faqWriteFm">
<%-- <input type="hidden" name="currentPage" value="${srchInfo.currentPage}" />
<input type="hidden" name="srchBrdTyp" value="${srchBrdTyp}" /> --%>
<input type="hidden" name="faqType" value="01" />


<table class="boardNormal" summary="묻고답하기 등록">
	<caption>묻고답하기 등록</caption>
	<colgroup>
		<col width="100" />
		
	</colgroup>

	<tbody>
		
		<tr>
			<th>질문</th>
			<td>
				<input type="text" name="faqQes" class="text" title="제목 입력" />
			</td>
		</tr>
		<tr>
			<th>답변</th>
			<td>
				<textarea name="faqAns" class="Reply_Text"></textarea>
			</td>
		</tr>
		
			
	</tbody>
</table>


<div style="margin-top:10px;text-align:center;">
		<input type="button" value="등록하기" class="Button Gray" onClick="submitFaqFm(document.faqWriteFm);">
		<input type="button" value="창닫기" class="Button Gray" onClick="self.close();">
</div>
</form>
</div>

</div>
</body>

<c:import url="../inc/footer.jsp" />