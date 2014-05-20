<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${reloadVar=='yes'}"><script>opener.location.reload();</script></c:if>

<c:import  url="../inc/top.jsp" />

<body>
<div id="Wrap">
	<div id="Contents">
	<h1>커뮤니티 &gt; FAQ &gt; <strong>FAQ</strong></h1>
<form  method="post" action="faqEditProc.do">

<input type="hidden" name="faqType" value="01" />
<input type="hidden" name="faqIdx" value="${reFaqInfo.faqIdx}" />

<table class="boardNormal" summary="묻고답하기 수정">
	<caption>묻고답하기 수정</caption>
	<colgroup>
		<col width="100" />
		
	</colgroup>

	<tbody>
		
		<tr>
			<th>질문</th>
			<td colspan="3">
				<input type="text" name="faqQes" class="" title="제목 입력" value="${reFaqInfo.faqQes}"/>
			</td>
		</tr>
		<tr>
			<th>답변</th>
			<td colspan="3">
				<input type="text" name="faqAns" class="" title="답변 입력" value="${reFaqInfo.faqAns}"/>
			</td>
		</tr>
		
			
	</tbody>
</table>


<div style="margin-top:10px;text-align:center;">
		<input type="submit" value="수정하기" class="Button Gray">
		<input type="button" value="창닫기" class="Button Gray" onClick="self.close();">
</div>
	</form>
</div>

</div>
</body>

<c:import url="../inc/footer.jsp" />