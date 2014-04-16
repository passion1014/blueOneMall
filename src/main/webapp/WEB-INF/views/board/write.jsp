<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.blueone.common.util.CKEditorHelper" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
function fnAddClick() {
	var f = tx_editor_form;

	f.action = '/board/add.do';

	Editor.save(); // 다음 에디터
}

</script>



<form name="tx_editor_form" method="post" enctype="multipart/form-data" action="http://posttestserver.com/post.php">
<table class="boardNormal" summary="묻고답하기 등록">
	<caption>묻고답하기 등록</caption>
	<colgroup>
		<col width="100" />
		<col width="250" />
		<col width="100" />
		<col width="275" />
	</colgroup>

	<tbody>
		<c:if test="${brdTypInfo.secrYn == 'T'}">
		<tr>
			<th>비밀글 여부</th>
			<td colspan="3">
				<input type="password" name="passwd" class="inp_130" title="비밀번호 입력" /> <input type="checkbox" class="checkbox" name="passwdYn" value="T" /><label>비밀글 설정</label>
			</td>
		</tr>
		</c:if>
		<c:if test="${brdTypInfo.publicUseYn == 'T'}">
		<tr>
			<th>발간일자</th>
			<td>
				<input type="text" name="publicYmd" class="inp_130" title="발간일자" /> 
			</td>
			<th>발간자</th>
			<td>
				<input type="text" name="publicNm" class="inp_130" title="발간자" /> 
			</td>
		</tr>
		</c:if>
		<c:if test="${brdTypInfo.imgUseYn == 'T'}">
		<tr>
			<th>이미지</th>
			<td colspan="3">
				<input type="file" name="imgFile" class="inp_500" title="이미지" />
			</td>
		</tr>
		</c:if>
		<c:if test="${sessionScope.userInfo.userId == null}">
		<tr>
			<th>작성자</th>
			<td colspan="3">
				<input type="text" name="userNm" class="inp_130" title="작성자 입력" />
			</td>
		</tr>
		</c:if>
		<c:if test="${sessionScope.userInfo.userId != null}">
		<input type="hidden" name="userNm" value="${ sessionScope.userInfo.userNm }" />
		</c:if>
		<tr>
			<th>제목</th>
			<td colspan="3">
				<input type="text" name="title" class="inp_620" title="제목 입력" />
			</td>
		</tr>
		<tr>
			<th>내용입력</th>
			<td colspan="3">
				<jsp:include page="/resources/editor/editor.jsp" />
			</td>
		</tr>
		<c:if test="${brdTypInfo.attaYn == 'T'}">
		<tr>
			<th>첨부파일</th>
			<td id="idAttaInfo" colspan="3">
				<p><input type="file" name="uploadFile" class="inp_500" title="첨부파일" /><label><img class="mousePoint" src="../images/board/btn/btn_fadd.gif" onclick="addAttaLine()" alt="파일추가"/></label></p>
			</td>
		</tr>
		</c:if>
		<tr>
			<th>저장</th>
			<td id="idAttaInfo" colspan="3">
				<p><input type="button" value="등록" onClick="fnAddClick();"/></p>
			</td>
		</tr>		
	</tbody>
</table>
</form>
