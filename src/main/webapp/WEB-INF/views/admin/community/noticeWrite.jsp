<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${reloadVar=='yes'}"><script>opener.location.reload();</script></c:if>

<c:import  url="../inc/top.jsp" />

<script type="text/javascript">
<!--
	function submitFaqFm(_frm) {
		
		document.noticeWriteFm.content.value = escape(document.noticeWriteFm.content.value);
		
		_frm.action ='noticeWriteProc.do';
		
		_frm.submit();
	}
-->
</script>

<body>
	<div id="Wrap">
		<div class="cont_popuparea">
			<h1>커뮤니티 &gt; 공지사항 &gt; <strong>공지사항</strong></h1>
		
				
			<form  method="post" action="noticeWriteProc.do" name="noticeWriteFm">
			
				<input type="hidden" name="brdTyp" value="8" />
				<input type="hidden" name="brdCodeType" value="02" />
				<input type="hidden" name="insUser" value="${admin.id}" />
				<input type="hidden" name="updUser" value="${admin.id}" />
				
				
				<table class="boardNormal" summary="공지사항 등록">
					<caption>공지사항 등록</caption>
					<colgroup>
						<col width="100" />
						
					</colgroup>
				
					<tbody>
						
						<tr>
							<th>제목</th>
							<td>
								<input type="text" name="title" class="text" title="제목 입력" />
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
						<input type="button" value="등록하기" class="Button Gray" onClick="submitFaqFm(document.noticeWriteFm);">
						<input type="button" value="창닫기" class="Button Gray" onClick="self.close();">
				</div>
			</form>
		</div>
	
	</div>
</body>

<c:import url="../inc/footer.jsp" />