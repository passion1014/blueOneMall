<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<c:import  url="../inc/topSub.jsp" />
<script language="javascript">
<!--
function chk_replyForm(f){
	if(f.content.value == ""){
		alert("댓글을 입력하여 주십시오");
		f.content.focus();
		return false;
	}
}
//-->
</script>

<body>
	<div class="wrap">
	<c:import url="../inc/header.jsp" />
<!--  header 끝   -->

	<div class="container">
		<c:import url="../inc/communityLnb.jsp" />
		<div class="sub_content">
			<div class="customer_section">
				<h4>Event</h4>
				<p class="sub_tit">쇼핑몰의 새로운 소식이나 이벤트를 확인하실 수 있습니다.</p>
				<div class="notice_area">
					<ul class="no_answer">
						<li>
							<table class="noanswer_tbl" summary="이벤트목록">
								<caption>이벤트목록표</caption>
								<colgroup>
									<col width="20%"/>
									<col width="*"/>
								</colgroup>
								<tbody>
									<tr>
										<th>이벤트</th>
										<td>${brdView.title}</td>
									</tr>
									<tr>
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
						<input type="button" value="목록으로" onClick="location.href='event.do';">
					</div>
				</div>
			</div>

			<form name="qnaform" method="post" action="/community/writeEvent.do" onSubmit="return chk_replyForm(this);" >
				<table class="evnt_tbl2" summary="이벤트댓글 목록">
					<caption>이벤트댓글 목록표</caption>
					<tbody>
						<tr>
							<td class="bgcolor">
								<input type="hidden" name="brdSeq" value="${brdView.brdSeq}" /> 
								<span>${customerSession.custNm}</span>
								<input type="text" id="content" name="content" class="text" style="padding-left:10px;"> 
								<input type="submit" value="댓글등록">
							</td>
						</tr>
					</tbody>
				</table>
			</form><br />

			<table class="evnt_tbl1" summary="이벤트댓글 목록">
				<caption>이벤트댓글 목록표</caption>
				<tbody>
				
				<c:forEach items="${qnaList}" var="qna">
					<tr>
						<td class="question_bg">
							<script type="text/javascript">
								document.open();
								var text='${qna.insUser}';
								var textArray=text.split('_');
								document.write(textArray[1]);
								document.close();
							</script> 
							&nbsp;&nbsp; ${qna.insDt}	&nbsp;&nbsp;
							<c:set var="custIdNm" value="${customerSession.custId}_${customerSession.custNm}"/>
							<c:if test="${custIdNm eq qna.insUser}">
								<input type="button" value="삭제"  onClick="confirm_process('','해당 댓글을 삭제하시겠습니까?','eventDelete.do?brdSeq=${qna.brdSeq}&pageSeq=${brdView.brdSeq}');" />
							</c:if>
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