<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Calendar"%>
<%
	Calendar calendar = Calendar.getInstance();
	String today = calendar.get(Calendar.YEAR) + "-" +(calendar.get(Calendar.MONTH )+1) + "-" +calendar.get(Calendar.DATE ); //년도를 구한다
	
%>

<c:import  url="../inc/top.jsp" />

<script type="text/javascript">
function fnAddClick() {
	var f = tx_editor_form;
	
	f.action = 'eventWriteProc.do';

	Editor.save(); // 다음 에디터
}

</script>

<body>
	<div id="Wrap">

	<c:import url="../inc/gnb.jsp"/>
	<c:import url="../inc/lnb.jsp">
		<c:param name="slot" value="community"/>
	</c:import>
	
	<div id="Contents">
	<h1>커뮤니티 &gt; Event &gt; <strong>Event등록</strong></h1>
				
			<form  method="post" action="eventWriteProc.do" name="tx_editor_form">
			
				<input type="hidden" name="brdTyp" value="11" />
				<input type="hidden" name="brdCodeType" value="02" />
				<input type="hidden" name="insUser" value="${admin.id}" />
				<input type="hidden" name="updUser" value="${admin.id}" />
				
				
				<table class="boardNormal" summary="Event 등록">
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
								${admin.name}
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
					<input type="button" value="목록으로" class="Button" onClick="location.href='./eventBoard.do'"> &nbsp;&nbsp;
					<input type="button" value="등록하기" class="Button Gray" onClick="fnAddClick();">
				</div>
			</form>
		</div>
	</div>
</body>

<c:import url="../inc/footer.jsp" />