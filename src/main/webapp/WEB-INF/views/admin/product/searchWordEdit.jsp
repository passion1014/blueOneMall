<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import  url="../inc/adminChecker.jsp" />
<c:import  url="../inc/top.jsp" />
<c:if test="${reloadVar=='yes'}"><script>opener.location.reload();</script></c:if>

<body>
<div id="Wrap">
	<!-- 컨덴츠 -->
	<div style="padding:10px;">
	<div> 상품관리 > 검색어 관리 > 검색어 수정	</div>
	<form  method="post" action="searchWordEditProc.do" enctype="multipart/form-data">
	<input type="hidden" id="swRank"  name="swRank"  value="${resSchWd.swRank}">
	
	<table class="tbl1">
		<colgroup>
			<col width="20%" />
			<col width="*" />
		</colgroup>

		<tr>
			<th>검색어순위</th>
			<td class="left">${resSchWd.swRank}</td>
		</tr>
		<tr>
			<th>검색어</th>
			<td colspan="3" class="left">
				<input type="text" id="swWord" name="swWord" value="${resSchWd.swWord}" class="Text Kor" style="width:90%;">
			</td>
		</tr>
		<tr>
			<th>HIT</th>
			<td colspan="3" class="left">
				<input type="text" id="swHit" name="swHit" value="${resSchWd.swHit}" class="Text Kor" style="width:90%;">
			</td>
		</tr>
		
		
	</table>

	<div style="margin-top:10px;text-align:center;">
		<input type="submit" value="수정하기" class="Button Gray">
		<input type="button" value="창닫기" class="Button Gray" onClick="self.close();">
	</div>
	</form>
	</div>
		
</div>
</body>
