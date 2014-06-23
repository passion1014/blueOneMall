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
	<h1>커뮤니티 &gt; Q&A &gt; <strong>Q&A</strong></h1>
	<div class="right">
	</div>
	<table class="inquire_tbl" summary="제품문의목록표">
		<colgroup>
			<col width="10%" />
			<col width="50%" />
			<col width="15%"/>
			<col width="15%"/>
			<col width="10%"/>
		</colgroup>
		<thead>
			<th class="bgcolor">번호</th>
			<th>내용</th>
			<th class="bgcolor">작성자</th>
			<th>작성일</th>
			<th>수정/삭제</th>
			
		</thead>
		<tbody>
			<c:forEach items="${qnaList}" var="qna">
				<tr>

					<td class="bgcolor">${qna.brdSeq}</td>
					<td class="texalign"><a href="#">${qna.title}</a></td>
					<td class="bgcolor">${qna.insUser}</td>
					<td>${qna.insDt.substring(0,10)}</td>
					<td style="text-align:center;">
							<input type="button" value="수정"  onClick="openWin('./noticeEdit.do?brdSeq=${qna.brdSeq}','faqEditForm',600,450,'scrollbars=no');" class="Button Gray" />
							<input type="button" value="삭제"  onClick="confirm_process('','해당 Q&A를 삭제하시겠습니까?','qnaDelete.do?brdSeq=${qna.brdSeq}');" class="Button Gray" />
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<!--<div id="Paser"> 1 | 2 | 3</div>-->
	<div id="Paser">
		${pageHtml}
	</div>
	
</div>

</div>
</body>

<c:import url="../inc/footer.jsp" />