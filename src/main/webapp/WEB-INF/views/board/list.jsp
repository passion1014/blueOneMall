<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript">

// 조회
function fnInquiry() {
	var f = boardForm;
	f.action = '${pgmId}.do';
	f.currentPage.value = 1;
	f.submit();
}

// 상세보기
function fnDetail(brdSeq, passwdYn, insUser) {
	if (passwdYn) {
		if ('${sessionScope.userInfo.userId}' != insUser) {
			alert('비밀글 입니다.');
			return false;
		}
	}
	
	var f = boardForm;
	f.action = '${pgmId}view.do';
	f.srchBrdSeq.value = brdSeq;
	f.submit();
}

// 등록
function fnAdd() {
	if (${brdTypInfo.writeAuth == '20' && sessionScope.userInfo.userId == null}) {
		/*
		if (!confirm('회원만 등록이 가능합니다.\n\n로그인 페이지로 이동하시겠습니까?')) {
			return false;
		}
		location.href = '${pageContext.request.contextPath}/login.do';
		*/
		if (!confirm('회원 또는 인증 후 등록이 가능합니다.\n\n아이핀 인증을 하시겠습니까?')) {
			return false;
		}
		fnCheckGpin();
		return false;
	} 
	
	var f = boardForm;
	f.action = '${pgmId}write.do';
	f.submit();
}

// 페이지이동
function fnGotoPage(page) {
	var f = boardForm;
	f.action = 'list.do';
	f.currentPage.value = page;
	f.submit();
}
</script>


	<form name="boardForm" method="get" onsubmit="return fnInquiry();" action="">
	<input type="hidden" name="currentPage" value="${srchInfo.currentPage}" />
	<input type="hidden" name="srchBrdTyp" value="${srchInfo.srchBrdTyp}" />
	<input type="hidden" name="srchBrdSeq" value="0" />
	<div id="boardList">
		<%-- <div id="search">
			<fieldset>
			<legend>검색</legend>
			<input type="text" name="srchKeyword" value="${srchInfo.srchKeyword}" class="text" title="검색어 입력" />
			<img class="mousePoint" src="../images/board/btn/btn_search.gif" onclick="fnInquiry();" alt="검색" />
			</fieldset>
		</div> --%>
		
		<!-- 일반게시판 -->
		<c:if test="${brdTypInfo.imgUseYn != 'T'}">
		<table class="boardNormal" summary="묻고답하기 목록">
		<caption>묻고답하기 목록</caption>
		<colgroup>
		<col width="60" />
		<col width="505" />
		<col width="65" />
		<col width="80" />
		<col width="65" />
		</colgroup>
		<thead>
		<tr>
			<th scope="col" class="first">번호</th>
			<th scope="col">제목</th>
			<th scope="col">작성자</th>
			<th scope="col">작성일</th>
			<th scope="col">조회수</th>
		</tr>
		</thead>
		<tbody>
		<!-- 공지 -->
		<c:forEach var="notice" items="${noticeList}">
		<tr>
			<td><strong>[공지]</strong></td>
			<td class="title mousePoint" onclick="fnDetail(${notice.brdSeq}, ${notice.passwd != null}, '${notice.insUser}')">
				${notice.title} <c:if test="${brdTypInfo.commViewYn == 'T'}"><c:if test="${notice.commCnt > 0}"><span class="cmtCnt">[덧글:${notice.commCnt}]</span></c:if></c:if>
				<c:if test="${notice.passwd != null && notice.passwd != ''}"><img class="icon" src="../images/board/icon/icon_secret.gif" alt="비밀글" /></c:if>
				<c:if test="${brdTypInfo.attaViewYn == 'T'}">
				<c:forEach var="attachFile" items="${notice.attachFiles}">
					<c:if test="${attachFile.attaKnd == 'AF'}">
						<c:if test="${attachFile.iconImage != null && attachFile.iconImage != ''}"><img class="icon" src="../images/board/icon/${attachFile.iconImage}" alt="첨부파일" /></c:if>
					</c:if>
				</c:forEach>
				</c:if>
				
				<!-- 신규글표기 -->
				<c:if test="${brdTypInfo.newViewYn == 'T'}">
					<c:if test="${notice.todayYn == 'T'}"><img class="icon" src="../images/board/icon/icon_new.gif" alt="신규글" /></c:if>
				</c:if>&nbsp;
			</td>
			<td>${notice.userNm}<c:if test="${notice.userNm == null || notice.userNm == ''}">&nbsp;</c:if></td>
			<td>${notice.insDt}</td>
			<td class="hit">${notice.hit}</td>
		</tr>
		</c:forEach>
		
		<!-- 일반 -->
		<c:forEach var="board" items="${boardList}">
		<tr>
			<td>${board.brdSeq}</td>
			<td class="title mousePoint" onclick="fnDetail(${board.brdSeq}, ${board.passwd != null}, '${board.insUser}')">
				<c:if test="${board.depth > 0}">
					<img class="reply" src="../images/board/icon/icon_reply.gif" alt="답변글" />
				</c:if>${board.title} <c:if test="${brdTypInfo.commViewYn == 'T'}"><c:if test="${board.commCnt > 0}"><span class="cmtCnt">[덧글:${board.commCnt}]</span></c:if></c:if>
				<c:if test="${board.passwd != null && board.passwd != ''}"><img class="icon" src="../images/board/icon/icon_secret.gif" alt="비밀글" /></c:if>
				<c:if test="${brdTypInfo.attaViewYn == 'T'}">
				<c:forEach var="attachFile" items="${board.attachFiles}">
					<c:if test="${attachFile.attaKnd == 'AF'}">
						<c:if test="${attachFile.iconImage != null && attachFile.iconImage != ''}"><img class="icon" src="../images/board/icon/${attachFile.iconImage}" alt="첨부파일" /></c:if>
					</c:if>
				</c:forEach>
				</c:if>
				
				<!-- 신규글표기 -->
				<c:if test="${brdTypInfo.newViewYn == 'T'}">
					<c:if test="${board.todayYn == 'T'}"><img class="icon" src="../images/board/icon/icon_new.gif" alt="신규글" /></c:if>
				</c:if>&nbsp;
			</td>
			<td>${board.userNm}<c:if test="${board.userNm == null || board.userNm == ''}">&nbsp;</c:if></td>
			<td>${board.insDt}</td>
			<td class="hit">${board.hit}</td>
		</tr>
		</c:forEach>
		</tbody>		
		</table>
		</c:if>
		
		<div id="pagingWrap">
			${pageHtml}
			<c:if test="${brdTypInfo.writeAuth == '10' || brdTypInfo.writeAuth == '20'}">
				<p class="goLink"><img class="mousePoint" src="../images/board/btn/btn_write.gif" onclick="fnAdd()" alt="등록" /></p>
			</c:if>
		</div>
	</div>
	</form>