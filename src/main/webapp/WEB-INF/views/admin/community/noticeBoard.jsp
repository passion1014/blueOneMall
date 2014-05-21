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
	<h1>커뮤니티 &gt; 공지사항 &gt; <strong>공지사항</strong></h1>
	<div class="right">
		<input type="button" value="공지사항 등록" class="Small_Button Gray" onClick="openWin('./noticeWrite.do','noticeWriteForm',600,450,'scrollbars=no');">
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
			<c:forEach items="${noticeList}" var="qna">
				<tr>
<<<<<<< HEAD
					<td class="bgcolor">${qna.ntIdx}</td>
					<td class="texalign"><a href="#">${qna.ntTitle}</a></td>
					<td class="bgcolor">${qna.ntRegAdmin}</td>
					<td>${qna.ntRegDate.substring(0,10)}</td>
=======
					<td class="bgcolor">${qna.brdSeq}</td>
					<td class="texalign"><a href="#">${qna.title}</a></td>
					<td class="bgcolor">${qna.insUser}</td>
					<td>${qna.insDt}</td>
>>>>>>> 340c882eee0c84192b731eb2ed25ca0b3cebd3e1
					<td style="text-align:center;">
							<input type="button" value="수정" <%-- onClick="openWin('./faqEdit.do?faqIdx=${qna.faqIdx}','faqEditForm',600,450,'scrollbars=no');" --%> class="Button Gray" />
							<input type="button" value="삭제" <%-- onClick="confirm_process('','해당 FAQ를 삭제하시겠습니까?','faqDelete.do?faqIdx=${qna.faqIdx}');"  --%> class="Button Gray" />
					</td>
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

