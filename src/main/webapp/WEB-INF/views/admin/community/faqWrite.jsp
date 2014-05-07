<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import  url="../inc/top.jsp" />

<body>
<div id="Wrap">

	<c:import url="../inc/gnb.jsp"/>
	<c:import url="../inc/lnb.jsp">
		<c:param name="slot" value="community"/>
	</c:import>
	
	<div id="Contents">
	<h1>커뮤니티 &gt; FAQ &gt; <strong>FAQ</strong></h1>
<form name="tx_editor_form" method="post" action="faqWrite.do">
<input type="hidden" name="currentPage" value="${srchInfo.currentPage}" />
<input type="hidden" name="srchBrdTyp" value="${srchBrdTyp}" />

<table class="boardNormal" summary="묻고답하기 등록">
	<caption>묻고답하기 등록</caption>
	<colgroup>
		<col width="100" />
		
	</colgroup>

	<tbody>
		
		<tr>
			<th>내용입력</th>
			<td colspan="3">
				<input type="text" name="title" class="" title="제목 입력" />
			</td>
		</tr>
		
			
	</tbody>
</table>
</form>

	<!--<div id="Paser"> 1 | 2 | 3</div>-->
	<div id="Paser">
	<c:forEach var="i" begin="1" end="${endNum}">
		<a href="adminList.do?page=${i}">${i}</a>
		<!-- 
		<input type="button" value="${i}" onClick="javascript:location.href='adminList.do?page=${i}'">
		 -->				
	</c:forEach>
	</div>
	
</div>

</div>
</body>

<c:import url="../inc/footer.jsp" />