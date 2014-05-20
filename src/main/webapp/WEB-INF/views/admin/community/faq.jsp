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
	<div class="right">
		<input type="button" value="FAQ 등록" class="Small_Button Gray" onClick="openWin('./faqWrite.do','faqWriteForm',600,450,'scrollbars=no');">
	</div>
	<table class="inquire_tbl" summary="제품문의목록표">
		<colgroup>
			<col width="10%" />
			<col width="60%" />
			<col width="15%"/>
			<col width="15%"/>
		</colgroup>
		<thead>
			<th class="bgcolor">번호</th>
			<th>제목</th>
			<th>작성일</th>
			<th>수정/삭제</th>
		</thead>
		<tbody>
			<c:forEach items="${faqList}" var="qna">
				<tr>
					<td class="bgcolor">${qna.faqIdx}</td>
					<td class="texalign"><a href="faqWrite.do">${qna.faqQes}</a></td>
					<td>${qna.faqRegDate.substring(0,10)}</td>
					<td style="text-align:center;">
							<input type="button" value="수정" onClick="openWin('./faqEdit.do?faqIdx=${qna.faqIdx}','faqEditForm',600,450,'scrollbars=no');" class="Button Gray">
							<input type="button" value="삭제" onClick="confirm_process('','해당 FAQ를 삭제하시겠습니까?','faqDelete.do?faqIdx=${qna.faqIdx}');"  class="Button Gray">
					</td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<!--<div id="Paser"> 1 | 2 | 3</div>-->
	<div id="Paser">${pageHtml}
	</div>
	
</div>

</div>
</body>

<c:import url="../inc/footer.jsp" />