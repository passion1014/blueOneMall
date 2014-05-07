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
	<table class="inquire_tbl" summary="제품문의목록표">
		<colgroup>
			<col width="10%" />
			<col width="60%" />
			<col width="15%"/>
			<col width="15%"/>
		</colgroup>
		<thead>
			<th class="bgcolor">번호</th>
			<th>내용</th>
			<th class="bgcolor">작성자</th>
			<th>작성일</th>
		</thead>
		<tbody>
			<c:forEach items="${qnaList}" var="qna">
				<tr>
					<td class="bgcolor">${qna.brdSeq}</td>
					<td class="texalign"><a href="#">${qna.title}</a></td>
					<td class="bgcolor">${qna.insUser}</td>
					<td>${qna.insDt}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

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

