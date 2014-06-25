<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${eventEditSuccess=='yes'}"><script>alert("이벤트가 성공적으로 수정되었습니다.");</script></c:if>
<c:import  url="../inc/top.jsp" />



<body>
	<div id="Wrap">

	<c:import url="../inc/gnb.jsp"/>
	<c:import url="../inc/lnb.jsp">
		<c:param name="slot" value="community"/>
	</c:import>
	
	<div id="Contents">
	<h1>커뮤니티 &gt; Q&A &gt; <strong>Q&A 보기</strong></h1>
				
			<form  method="post" action="writeQnA_Answer.do" name="qnaFrm">
			
				<input type="hidden" name="brdTyp" value="11" />
				<input type="hidden" name="brdCodeType" value="02" />
				<input type="hidden" name="insUser" value="${admin.id}" />
				<input type="hidden" name="updUser" value="${admin.id}" />
				<input type="hidden" name="brdSeq" value="${editBrd.brdSeq}" />
				
				
				<table class="boardNormal" summary="Event 등록">
					<colgroup>
						<col width="100" />
						
					</colgroup>
				
					<tbody>
						
						<tr>
							<th>제목</th>
							<td>
								${editBrd.title}
							</td>
							<th>작성자</th>
							<td>
								<script type="text/javascript">
										document.open();
										var text='${editBrd.insUser}';
										var textArray=text.split('_');
										document.write(textArray[1]);
										document.close();
								</script> 
							</td>
							
						</tr>
						
						<tr>
							<th>내용</th>
							<td colspan="3">
								${editBrd.content}
							</td>
						</tr>
						
						
					</tbody>
				</table>
				</form>
				
				<form name="qnaform" method="post" action="writeQnA_Answer.do" onSubmit="return chk_replyForm(this);" >
					<table class="evnt_tbl2" summary="Q&A 답변 목록표">
						<caption>Q&A 답변 목록표</caption>
						<tbody>
							<tr>
								<td class="bgcolor">
									<input type="hidden" name="brdSeq" value="${editBrd.brdSeq}" /> 
									<span>${admin.id}</span>
									<input type="text" id="content" name="content" class="text" style="padding-left:10px;"> 
									<input type="submit" value="댓글등록">
								</td>
							</tr>
						</tbody>
					</table>
				</form><br />
	
				<table class="evnt_tbl1" summary="Q&A 답변 목록표">
					<caption>Q&A 답변 목록표</caption>
					<tbody>
					
					<c:forEach items="${qnaList}" var="qna">
						<tr>
							<td class="question_bg">
								
									${qna.insUser}
								&nbsp;&nbsp; ${qna.insDt}	&nbsp;&nbsp;
								
								
									<input type="button" value="삭제"  onClick="confirm_process('','해당 댓글을 삭제하시겠습니까?','eventDelete.do?brdSeq=${qna.brdSeq}&pageSeq=${brdView.brdSeq}');" />
								
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
</body>

<c:import url="../inc/footer.jsp" />