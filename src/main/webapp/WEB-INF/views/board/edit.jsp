<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com" %>

<script type="text/javascript">
//등록
function fnEdit() {
	var f = boardForm;	
	f.action = '${pgmId}edit.do';
	f.submit();
}

//목록
function fnList() {
	var f = boardForm;
	f.action = '${pgmId}.do';
	f.submit();
}

//수정
function fnEditClick() {
	var f = boardForm;
	if (f.passwdYn != null) {
		if (f.passwdYn.checked) {
			if (isEmpty(f.passwd)) {
				alert('비밀번호를 입력해주세요.');
				f.passwd.focus();
				return false;
			}
		} else {
			if (!isEmpty(f.passwd)) {
				alert('비밀글 설정을 체크해주세요.');
				f.passwdYn.focus();
				return false;
			}
		}
	}
	
	if (f.publicYmd != null) {
		if (isEmpty(f.publicYmd)) {
			alert('발간일자를 입력해주세요.');
			f.publicYmd.focus();
			return false;
		} else {
			f.publicYmd.value = removeDashValue( f.publicYmd.value);
			if (!checkDate(f.publicYmd)) {
				return false;
			}
		}
		
		if (isEmpty(f.publicNm)) {
			alert('발간자를 입력해주세요.');
			f.publicNm.focus();
			return false;
		}
	}
	
	if (f.imgFile != null) {
		if (!isEmpty(f.imgFile)) {
			var fileExt = fnFileExt(f.imgFile.value).toLowerCase();
			if (fileExt != 'gif' && fileExt != 'jpg') {
				alert('이미지는 GIF/JPG 파일만 업로드 가능합니다.');
				return false;
			}
		}
	}
		
	if (isEmpty(f.title)) {
		alert('제목을 입력해주세요.');
		f.title.focus();
		return false;
	}
	
	fnEdit();
}

//파일양식 추가
function addAttaLine() {
	var f = boardForm;
	
	var attaCount = 0;
	if( f.flNo != null && f.flNo.length != null) attaCount = f.flNo.length;
	else if (f.flNo != null) attaCount = 1;
	if( f.uploadFile != null && f.uploadFile.length != null) attaCount = attaCount + f.uploadFile.length;
	else if (f.uploadFile != null) attaCount = attaCount + 1;
	
	if( attaCount > 4)  {
		alert("파일은 최대 5개까지 첨부하실수 있습니다.");
		return;
	}
	
	attaHtml = "<p><input type='file' name='uploadFile' class='inp_500' title='첨부파일' /><label></label></p>";
	$('#idAttaInfo').append(attaHtml);
}

// 파일삭제
function deleteAtta(flNo) {
	$('#idAttaFile' + flNo).remove();
}

//에디터에서 추가한 이미지
function setUploadImage(path) {
	var appendHtml = "<input type='hidden' name='contImageFile' value='" + path + "'>";
	$('#idAddImageInfo').append(appendHtml);
}
</script>

<form name="boardForm" method="post" enctype="multipart/form-data" action="">
<input type="hidden" name="currentPage" value="${srchInfo.currentPage}" />
<input type="hidden" name="brdSeq" value="${brdSeq}" />
<input type="hidden" name="srchBrdTyp" value="${srchBrdTyp}" />
<span id="idAddImageInfo"></span>
<div id="boardWrite">
	<div class="boxView">
		<div class="boxBtm">
			<div class="boxTop">
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
								<input type="text" name="publicYmd" class="inp_130" value="${board.orgPublicYmd}" title="발간일자" /> 
							</td>
							<th>발간자</th>
							<td>
								<input type="text" name="publicNm" class="inp_130" value="${board.publicNm}" title="발간자" /> 
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
						<tr>
							<th>제목</th>
							<td colspan="3">
								<input type="text" name="title" class="inp_620" value="${board.title}" title="제목 입력" />
							</td>
						</tr>
						<tr>
							<th>내용입력</th>
							<td colspan="3">
								<textarea id="cont" name="cont" class="area_620_350">${board.cont}</textarea>
							</td>
						</tr>
						<c:if test="${brdTypInfo.attaYn == 'T'}">
						<tr>
							<th>첨부파일</th>
							<td id="idAttaInfo" colspan="3">

								<p><input type="file" name="uploadFile" class="inp_500" title="첨부파일" /><label><img class="mousePoint" src="../images/board/btn/btn_fadd.gif" onclick="addAttaLine()" alt="파일추가"/></label></p>
							</td>
						</tr>
						<tr>
							<th>기존파일</th>
							<td class="file" colspan="3">
								<c:forEach var="attaFile" items="${attaFileList}">
								<c:if test="${attaFile.attaKnd == 'AF'}">
									<p id="idAttaFile${attaFile.flNo}"><img class="icon" src="../images/board/icon/${attaFile.iconImage}" alt="파일" /> <span class="mousePoint" onclick="fnFileDownload('${attaFile.saveFilename}','${attaFile.realFilename}');">${attaFile.realFilename}</span><label><img class="icon mousePoint" src="../images/board/com/com_del.gif" onclick="deleteAtta(${attaFile.flNo});" alt="삭제" /></label><input type="hidden" name="flNo" value="${attaFile.flNo}"></p>
								</c:if>
								</c:forEach>
							</td>
						</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div id="buttonWrap">
		<img class="mousePoint" src="../images/board/btn/btn_ok.gif" onclick="fnEditClick()" alt="확인" />
		<img class="mousePoint" src="../images/board/btn/btn_scancel.gif" onclick="fnList()" alt="취소"/>
	</div>
</div>
</form>