<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.blueone.common.util.CKEditorHelper" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com" %>

<script type="text/javascript">

function fnAddClick() {
	var f = boardForm;
	
//	f.action = '${pgmId}write.do';
	f.action = 'add.do';
	f.currentPage.value = 1;
	f.submit();
}

</script>


<!-- <form name="boardForm" method="post" enctype="multipart/form-data" action=""> -->
<form name="boardForm" method="post" action="">
<input type="hidden" name="currentPage" value="${srchInfo.currentPage}" />
<input type="hidden" name="srchBrdTyp" value="${srchBrdTyp}" />
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
					<textarea id="cont" name="cont" class="area_620_350"></textarea>
      					<ckeditor:replace replace="cont" basePath="${pageContext.request.contextPath}/module/ckeditor/" config="<%= CKEditorHelper.getDefaultConfig(620, 350) %>" />
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
		</tbody>
	</table>
	<div>
		<input type="button" name="확인" onclick="fnAddClick()"> 
		<input type="button" name="취소" onclick="fnList()">
	</div>
</div>
</form>