<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${reloadVar=='yes'}"><script>opener.location.reload();</script></c:if>

<c:import  url="../inc/top.jsp" />

<script type="text/javascript">
<!--
	$(document).ready(function() {
		document.noticeEditFm.content.value = unescape('${editBrd.content}');
		
	});
	
	function submitFaqFm(_frm) {
		
		document.noticeEditFm.content.value = escape(document.noticeEditFm.content.value);
		
		_frm.action ='noticeEditProc.do';
		
		_frm.submit();
	}
-->
</script>

<body>
	<div id="Wrap">
		<div class="cont_popuparea">
			<h1>커뮤니티 &gt; 공지사항 &gt; <strong>공지사항</strong></h1>
		
				
			<form  method="post" action="noticeWriteProc.do" name="noticeEditFm">
			
				<input type="hidden" name="brdTyp" value="8" />
				<input type="hidden" name="brdCodeType" value="02" />
				<input type="hidden" name="updUser" value="${admin.id}" />
				<input type="hidden" name="brdSeq" value="${editBrd.brdSeq}" />
				
				
				<table class="boardNormal" summary="공지사항 수정">
					<caption>공지사항 수정</caption>
					<colgroup>
						<col width="100" />
						
					</colgroup>
				
					<tbody>
						
						<tr>
							<th>제목</th>
							<td>
								<input type="text" name="title" class="text" value="${editBrd.title}" title="제목 입력" />
							</td>
							<th>작성자</th>
							<td>
								${admin.name}
							</td>
							
						</tr>
						
						<tr>
							<th>내용</th>
							<td colspan="3">
								<textarea name="content" class="Reply_Text"></textarea>
							</td>
						</tr>
						
							
					</tbody>
				</table>
				
				
				<div style="margin-top:10px;text-align:center;">
						<input type="button" value="수정하기" class="Button Gray" onClick="submitFaqFm(document.noticeEditFm);">
						<input type="button" value="창닫기" class="Button Gray" onClick="self.close();">
				</div>
			</form>
		</div>
	
	</div>
</body>

<c:import url="../inc/footer.jsp" />