<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<c:import  url="../inc/topSub.jsp" />


<body>
	<div class="wrap">
	<c:import url="../inc/header.jsp" />
<!--  header 끝   -->

	<div class="container">
		<c:import url="../inc/communityLnb.jsp" />
		<div class="sub_content">
			<div class="customer_section">
				<h4>묻고답하기</h4>
				<p class="sub_tit">Q&A 질문과 답변을 볼 수 있습니다.</p>
				<div class="notice_area">
					<ul class="no_answer">
						<li>
							<table class="noanswer_tbl" summary="Q&A">
								<caption>Q&A</caption>
								<colgroup>
									<col width="20%"/>
									<col width="*"/>
								</colgroup>
								<tbody>
									<tr>
										<th>질문</th>
										<td>${brdView.title}</td>
									</tr>
									<tr>
										<th>답변</th>
										<td colspan="2" class="h_tdarea" style="padding:5px 0 5px 0;">
										${brdView.content}
										</td>
									</tr>
								</tbody>
							</table>
						</li>
					</ul>
					<%--
					<div class="paging2">
						<a href="#" class="palign1"><img src="<c:url value='/resources/img/common/btn_first.gif'/>"  alt="처음으로"></a>
						<a href="#" class="palign2"><img src="<c:url value='/resources/img/common/btn_prev.gif'/>"  alt="이전"></a>
						<a href="#" class="on">1</a>
						<a href="#" class="palign1"><img src="<c:url value='/resources/img/common/btn_next.gif'/>"  alt="다음"></a>
						<a href="#" class="palign2"><img src="<c:url value='/resources/img/common/btn_end.gif'/>"  alt="끝으로"></a>
					</div>
					--%>
					<div class="evnt_btn">
						<input type="button" value="목록으로" onClick="location.href='qnaList.do';">
						<input type="button" value="수정하기" onClick="location.href='qnaEdit.do?brdSeq=${brdView.brdSeq}';">
					</div>
				</div>
			</div>

			

			<table class="evnt_tbl1" summary="Q&A 답변 목록">
				<caption>Q&A 답변 목록표</caption>
				<tbody>
				
				<c:forEach items="${qnaList}" var="qna">
					<tr>
						<td class="question_bg">
							관리자
							&nbsp;&nbsp; ${qna.insDt}	&nbsp;&nbsp;
							
							
						</td>
					</tr>
					<tr>
						<td class="answer_bg" style="padding:5px 0 20px 15px;">${qna.content}</td>
					</tr>
				</c:forEach>

				</tbody>
			</table><br /><br />
			

			

		</div>
	</div>
<!--  container 끝   -->	

</div>
</body>
</body>

<c:import url="../inc/footer.jsp" />