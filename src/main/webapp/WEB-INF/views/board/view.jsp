<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.blueone.common.util.CKEditorHelper" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com" %>

<script type="text/javascript">
//조회
function fnInquiry() {
	var f = boardForm;
	f.action = '${pgmId}.do';
	f.currentPage.value = 1;
	f.submit();
}

//목록
function fnList() {
	var f = boardForm;
	f.action = '${pgmId}.do';
	f.submit();
}

// 수정
function fnEdit() {
	var f = boardForm;
	f.action = '${pgmId}edit.do';
	f.submit();
}

//코멘트등록
/* function fnAddComment() {
	if (${brdTypInfo.commWriteAuth == '20' && sessionScope.userInfo.userId == null}) {
		if (!confirm('회원만 등록이 가능합니다.\n\n로그인 페이지로 이동하시겠습니까?')) {
			return false;
		}
		location.href = '${pageContext.request.contextPath}/login.do';
		return false;
	} 
	
	var f = boardForm;
	if (isEmpty(f.commCont)) {
		alert('코멘트를 입력해주세요.');
		f.commCont.focus();
		return false;
	}
	
	f.method = 'post';
	f.action = '${pgmId}writeComment.do';
	f.submit();
}

//코멘트수정
function fnEditComment() {
	var f = boardForm;
	
	if (!confirm('코멘트를 수정하시겟습니까?')) {
		return false;
	}
	
	f.method = 'post';
	f.action = '${pgmId}editComment.do';
	f.submit();
} */


//코멘트삭제
function fnDeleteComment(commNo) {
	var f = boardForm;
	
	if (!confirm('코멘트를 삭제하시겟습니까?')) {
		return false;
	}
	
	f.commNo.value = commNo;
	f.action = '${pgmId}deleteComment.do';
	f.submit();
}

//삭제하기
function fnDelete() {
	if (!confirm('삭제 하시겠습니까?')) {
		return false;
	}
	
	var f = boardForm;
	f.action = '${pgmId}delete.do';
	f.submit();
}

//파일다운로드
function fnFileDownload(saveFilename, realFilename) {
	var f = boardForm;
	f.saveFilename.value = saveFilename;
	f.realFilename.value = realFilename;
	f.action = '${pageContext.request.contextPath}/board/fileDownload.do';
	f.target = 'download_frame';
	f.submit();
	f.target = '_self';
}

//이미지 다운로드
function fnFileDownloadImage(saveFilename, realFilename) {
	var f = boardForm;
	f.saveFilename.value = saveFilename;
	f.realFilename.value = realFilename;
	f.action = '${pageContext.request.contextPath}/board/fileDownloadImage.do';
	f.target = 'download_frame';
	f.submit();
	f.target = '_self';
}

// 코멘트 등록버튼 처리
function fnClickComment() {
	var f = boardForm;
	
	if ('ADD' == f.commClickTyp.value) {
		fnAddComment();
	} else if ('EDIT' == f.commClickTyp.value) {
		fnEditComment();
	}
}

// 코멘트 덧글코멘트 처리
function fnClickReplyComment(commNo) {
	var f = boardForm;
	f.commNo.value = commNo;
	f.commRootNo.value = commNo;
	f.commRefNo.value = parseInt(eval('f.maxCommRefNo' + commNo).value, 10) + 1;
	f.commDepth.value = 1;
	f.commCont.value = eval('f.replyCommCont' + commNo).value;
	
	// 코멘트등록
	fnAddComment();	
}

// 코멘트 수정
function setEditComment(commNo) {
	var f = boardForm;
	
	f.commNo.value = commNo;
	f.commCont.value = eval('f.tCommCont' + commNo).value;
	f.commClickTyp.value = 'EDIT';
	f.idComClickBtn.src = '/resources/img/board/com/com_modify2.gif';
}
</script>

<form name="boardForm" method="get" onsubmit="return fnInquiry();" action="">
<input type="hidden" name="currentPage" value="${srchInfo.currentPage}" />
<input type="hidden" name="brdSeq" value="${board.brdSeq}" />
<input type="hidden" name="srchBrdTyp" value="${srchInfo.srchBrdTyp}" />
<input type="hidden" name="commNo" value="" />
<input type="hidden" name="commRootNo" value="${ comment.commRootNo }" />
<input type="hidden" name="commRefNo" value="${ comment.commRefNo }" />
<input type="hidden" name="commDepth" value="${ comment.commDepth }" />
<input type="hidden" name="commUserNm" value="${ sessionScope.userInfo.userNm }" />
<input type="hidden" name="commClickTyp" value="ADD" />
<input type="hidden" name="saveFilename" />
<input type="hidden" name="realFilename" />

<div id="boardView">
	<div id="search">
		<fieldset>
		<legend>검색</legend>
		<input type="text" name="srchKeyword" value="${srchInfo.srchKeyword}" class="text" title="검색어 입력" />
		<img class="mousePoint" src="/resources/img/board/btn/btn_search.gif" onclick="fnInquiry()" alt="검색" />
		</fieldset>
	</div>

	<table class="boardNormal" summary="묻고답하기 상세보기">
		<caption>묻고답하기 상세보기</caption>
		<colgroup>
			<col width="70" />
			<col width="395" />
			<col width="55" />
			<col width="100" />
			<col width="55" />
			<col width="100" />
		</colgroup>
		<thead>
			<tr>
				<th colspan="6" class="title">${board.title}</th>
			</tr>
			<tr>
				<th width="70">작성일 :</th>
				<td width="465">${board.insDt}</td>
				<th width="55">작성자 :</th>
				<td width="65">${board.userNm}<c:if test="${board.userNm == null || board.userNm == ''}">&nbsp;</c:if></td>
				<th width="55">조회수 :</th>
				<td width="65">${board.hit}</td>
			</tr>
			<c:if test="${brdTypInfo.publicUseYn == 'T'}">
			<tr>
				<th>발간일 :</th>
				<td>${board.publicYmd}</td>
				<th>발간자 :</th>
				<td colspan="3">${board.publicNm}</td>
			</tr>
			</c:if>
			<c:if test="${brdTypInfo.imgUseYn == 'T'}">
			<tr>
				<th>이미지 :</th>
				<td colspan="5" class="file"><p>
				<c:forEach var="attaFile" items="${attaFileList}">
				<c:if test="${attaFile.attaKnd == 'MI'}">
					<img class="icon" src="/resources/img/board/icon/${attaFile.iconImage}" alt="파일" /> <span class="mousePoint" onclick="fnFileDownloadImage('${attaFile.saveFilename}','${attaFile.realFilename}');">${attaFile.realFilename}</span><br/>
				</c:if>
				</c:forEach>
				</p></td>
			</tr>
			</c:if>
			<c:if test="${brdTypInfo.attaYn == 'T'}">
			<tr>
				<th width="70">첨부파일 :</th>
				<td width="705" colspan="5" class="file"><p>
				<c:forEach var="attaFile" items="${attaFileList}">
				<c:if test="${attaFile.attaKnd == 'AF'}">
					<img class="icon" src="/resources/img/board/icon/${attaFile.iconImage}" alt="파일" /> <span class="mousePoint" onclick="fnFileDownload('${attaFile.saveFilename}','${attaFile.realFilename}');">${attaFile.realFilename}</span><br/>
				</c:if>
				</c:forEach>
				</p></td>
			</tr>
			</c:if>
		</thead>
		<tbody>
			<tr>
				<td width="745" colspan="6" class="content">${board.content}</td>
			</tr>
		</tbody>
	</table>
	<div id="buttonWrap">
		<img class="mousePoint" src="/resources/img/board/btn/btn_list.gif" onclick="fnList()" alt="목록" />
		<%-- <c:if test="${sessionScope.userInfo.userId == board.insUser}"> --%>
		<img class="mousePoint" src="/resources/img/board/btn/btn_modify.gif" onclick="fnEdit()" alt="수정" />
		<img class="mousePoint" src="/resources/img/board/btn/btn_delete.gif" onclick="fnDelete()" alt="삭제" />
		<%-- </c:if> --%>
	</div>
	
	
	<c:if test="${brdTypInfo.commYn == 'T'}">
	<div id="cmtWrap">
		<c:if test="${brdTypInfo.commWriteAuth == '10' || brdTypInfo.commWriteAuth == '20'}">
		<div class="cmtWrite">
			<fieldset>
				<legend>댓글 쓰기폼</legend>
				<textarea name="commCont" title="댓글입력"></textarea>
				<span><img id="idComClickBtn" class="mousePoint" src="/resources/img/board/com/com_write.gif" onclick="fnClickComment();" alt="댓글등록" /></span>
			</fieldset>
		</div>
		</c:if>
		<c:if test="${brdTypInfo.commReadAuth == '10' || (brdTypInfo.commReadAuth == '20' && sessionScope.userInfo.userId != null)}">
		<div class="cmtView">
			<table summary="댓글 보기">
				<caption>댓글 보기</caption>
				<colgroup>
					<col width="67" />
					<col width="603" />
					<col width="105" />
				</colgroup>
				<thead>
					<tr>
						<th scope="col"></th>
						<th scope="col"></th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="lComment" items="${commentList}">
					<!-- 코멘트 -->
					<c:if test="${lComment.commDepth == 0}">
						<tr>
							<td class="name"><strong>${lComment.commUserNm}</strong><input type="hidden" name="maxCommRefNo${lComment.commNo}" value="${lComment.maxCommRefNo}"></td>
							<td class="centent">${lComment.htmlCommCont}
								<input type="hidden" name="tCommCont${lComment.commNo}" value="${lComment.commCont}">
								<c:if test="${sessionScope.userInfo.userId == board.insUser}"><img class="icon mousePoint" src="/resources/img/board/com/com_modify.gif" onclick="setEditComment(${lComment.commNo});" alt="수정" /><img class="icon mousePoint" src="/resources/img/board/com/com_del.gif" onclick="fnDeleteComment(${lComment.commNo});" alt="삭제" /></c:if><img class="icon mousePoint" src="/resources/img/board/com/com_reply01.gif" onclick="fnFold(idReplyCommCont${lComment.commNo})" alt="댔글" /><c:if test="${lComment.childCommCnt > 0}"><span class="cmtCnt">댓글 [${lComment.childCommCnt}]</span><img class="icon" src="/resources/img/board/com/com_arrow.gif" alt="하위 표시" /></c:if>
							</td>
							<td>${lComment.insDt}</td>
						</tr>
						<tr id="idReplyCommCont${lComment.commNo}" style="display:none">
							<td class="cmtModify" colspan="3" width="775">
								<fieldset>
									<legend>댓글 쓰기폼</legend>
									<span class="name"><strong>${sessionScope.userInfo.userNm}&nbsp;</strong></span>
									<textarea name="replyCommCont${lComment.commNo}" title="댓글수정"></textarea>
									<span class="btn"><img class="mousePoint" src="/resources/img/board/com/com_write.gif" onclick="fnClickReplyComment(${lComment.commNo});" alt="댓글등록" /></span>
								</fieldset>
							</td>
						</tr>
					</c:if>
					<!-- 코멘트 덧글코멘트 -->
					<c:if test="${lComment.commDepth > 0}">
						<tr>
							<td></td>
							<td class="centent">
								<input type="hidden" name="tCommCont${lComment.commNo}" value="${lComment.commCont}">
								<span class="cmdReply"><strong>${lComment.commUserNm}</strong></span>${lComment.htmlCommCont} <c:if test="${sessionScope.userInfo.userId == board.insUser}"><img class="icon mousePoint" src="/resources/img/board/com/com_modify.gif" onclick="setEditComment(${lComment.commNo});" alt="수정" /><img class="icon mousePoint" src="/resources/img/board/com/com_del.gif" onclick="fnDeleteComment(${lComment.commNo});" alt="삭제" /></c:if>
							</td>
							<td>${lComment.insDt}</td>
						</tr>
					</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
		</c:if>
	</div>
	</c:if>
</div>

</form>

<iframe name="download_frame" id="download_frame" title="다운로드" width="0" height="0"></iframe>
