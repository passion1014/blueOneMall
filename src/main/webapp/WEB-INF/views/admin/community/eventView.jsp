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
	<h1>커뮤니티 &gt; Event &gt; <strong>Event 수정</strong></h1>
				
			<form  method="post" action="eventEditProc.do" name="tx_editor_form">
			
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
								${admin.name}
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
									
									
										<input type="button" value="삭제"  onClick="confirm_process('','해당 댓글을 삭제하시겠습니까?','eventReplyDelete.do?brdSeq=${qna.brdSeq}&pageSeq=${editBrd.brdSeq}');" />
									
								</td>
							</tr>
							<tr>
								<td class="answer_bg" style="padding:5px 0 20px 15px;">${qna.content}</td>
							</tr>
						</c:forEach>
		
						</tbody>
				</table><br /><br />
				
				<div style="margin-top:10px;text-align:center;">
						<input type="button" value="수정하기" class="Button Gray" onClick="fnAddClick();">
						<input type="button" value="창닫기" class="Button Gray" onClick="self.close();">
				</div>
			</form>
		</div>
	</div>
</body>

<c:import url="../inc/footer.jsp" />