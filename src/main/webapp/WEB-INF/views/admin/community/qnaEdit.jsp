<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${eventEditSuccess eq 'yes'}"><script>alert("이벤트가 성공적으로 수정되었습니다.");</script></c:if>
<c:import  url="../inc/top.jsp" />

<script type="text/javascript">
function fnAddClick() {
	var f = tx_editor_form;
	
	f.action = 'qnaEditProc.do';

	Editor.save(); // 다음 에디터
}
$(document).ready(function() {
	Editor.modify({
     	"content":'${editBrd.content}'
     });
});
</script>

<body>
	<div id="Wrap">

	<c:import url="../inc/gnb.jsp"/>
	<c:import url="../inc/lnb.jsp">
		<c:param name="slot" value="community"/>
	</c:import>
	
	<div id="Contents">
	<h1>커뮤니티 &gt; Q&A &gt; <strong>Q&A 수정</strong></h1>
				
			<form  method="post" action="qnaEditProc.do" name="tx_editor_form">
			
				<input type="hidden" name="brdTyp" value="20" />
				<input type="hidden" name="brdCodeType" value="02" />
				<input type="hidden" name="updUser" value="${admin.id}" />
				<input type="hidden" name="brdSeq" value="${editBrd.brdSeq}" />
				
				
				<table class="boardNormal" summary="Event 등록">
					<colgroup>
						<col width="15%" />
						<col width="*" />
					</colgroup>
				
					<tbody>
						
						<tr>
							<th>제목</th>
							<td>
								<input type="text" name="title" class="text" value="${editBrd.title}" title="제목 입력" style="width:90%;" />
							</td>
						</tr>
						
						<tr>
							<th>내용</th>
							<td>
								<jsp:include page="/resources/editor/editor.jsp" />
							</td>
						</tr>
						
							
					</tbody>
				</table>
				
				
				<div style="margin-top:10px;text-align:center;">
					<input type="button" value="목록으로" class="Button" onClick="location.href='./eventBoard.do'"> &nbsp;&nbsp;
					<input type="button" value="수정하기" class="Button Gray" onClick="fnAddClick();">
				</div>
			</form>
		</div>
	</div>
</body>

<c:import url="../inc/footer.jsp" />