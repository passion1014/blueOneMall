<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import  url="../inc/topSub.jsp" />
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>



<script type="text/javascript">
function fnAddClick() {
	var f = tx_editor_form;
	
	f.action = 'qnaWriteProc.do';

	Editor.save(); // 다음 에디터
}

</script>
<%@ page import="java.util.Calendar"%>
<%
	Calendar calendar = Calendar.getInstance();
	String today = calendar.get(Calendar.YEAR) + "-" +(calendar.get(Calendar.MONTH )+1) + "-" +calendar.get(Calendar.DATE ); //년도를 구한다
	
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>###### 현대프로모션몰 ######</title>
</head>
<body>
	<div class="wrap">
	<c:import url="../inc/header.jsp" />
<!--  header 끝   -->

	<div class="container">
		<c:import url="../inc/communityLnb.jsp" />
		<div class="sub_content">
			<form action="qnaWriteProc.do" method="post" name="tx_editor_form">
				<div class="customer_section">
					<h4>Q&A</h4>
					<p class="sub_tit">상품에 대해 문의 하실 수 있습니다.</p>
					<div class="notice_area">
						<ul class="no_answer">
							<li>
								
								<input type="hidden" name="brdTyp" value="20" />
								<input type="hidden" name="brdCodeType" value="02" />
								<input type="hidden" name="insUser" value="${CUST_NAME}" />
								<input type="hidden" name="updUser" value="${CUST_NAME}" />
								
								
								<table class="boardNormal" summary="qna 등록">
									<colgroup>
										<col width="15%" />
										<col width="35%" />
										<col width="15%" />
										<col width="*" />
									</colgroup>
								
									<tbody>
										<tr>
											<th>작성자</th>
											<td>
												${CUST_NAME}
											</td>
											<th>작성일</th>
											<td>
												<%=today%>
											</td>
										</tr>
						
										<tr>
											<th>제목</th>
											<td colspan="3">
												<input type="text" id="title" name="title" value="" class="text" style="width:100%;" title="제목 입력" />
											</td>
										</tr>
										
										<tr>
											<th>내용</th>
											<td colspan="3">
												<jsp:include page="/resources/editor/editor.jsp" />
											</td>
										</tr>
										
											
									</tbody>
								</table>
							
				
								<div style="margin-top:10px;text-align:center;">
									<input type="button" value="목록으로" class="Button" onClick="location.href='./qnaBoard.do'"> &nbsp;&nbsp;
									<input type="button" value="등록하기" class="Button Gray" onClick="fnAddClick();">
								</div>
							</li>
						</ul>
						<div class="evnt_btn">
							<input type="button" value="목록으로" onClick="location.href='notice.do';">
						</div>
						
					</div>
				</div>
			</form>
		</div>
	</div>
<!--  container 끝   -->	

</div>
</body>
</body>

<c:import url="../inc/footer.jsp" />