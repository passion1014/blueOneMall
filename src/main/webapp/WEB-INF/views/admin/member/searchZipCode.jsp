<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c_rt"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="imagetoolbar" content="no" />
<link type="text/css" rel="stylesheet"
	href="<c:url value='/resources/css/common.css'/>" />
<link type="text/css" rel="stylesheet"
	href="<c:url value='/resources/css/sub.css'/>" />
<link type="text/css" rel="stylesheet"
	href="<c:url value='/resources/css/custom-theme/jquery-ui-1.8.16.custom.css'/>" />

<script type="text/javascript"
	src="<c:url value='/resources/js/js_ajax.js'/>">
	
</script>
<script type="text/javascript"
	src="<c:url value='/resources/js/js_common.js'/>">
	
</script>
<script type="text/javascript"
	src="<c:url value='/resources/js/js_admin.js'/>">
	
</script>
<script type="text/javascript"
	src="<c:url value='/resources/js/jquery-1.4.3.min.js'/>">
	
</script>
<script type="text/javascript"
	src="<c:url value='/resources/js/jquery-1.7.2.min.js'/>">
	
</script>
<script type="text/javascript"
	src="<c:url value='/resources/js/jquery-ui-1.8.16.custom.min.js'/>">
	
</script>

</head>

<body>

	<!-- 컨덴츠 -->

	<div class="adr_section">
		<h5>주소검색</h5>
		<div class="adr_box">
			<p class="adr_tit">
				<b>읍,면,동</b>을 입력해주시기 바랍니다.(예:압구정동)
			</p>
			<fieldset>
				<legend></legend>
				<input type="text" class="text_info" title="주소지 입력창" id="dong"
					name="dong" /> <input type="button" class="btn_adr" value="주소검색"
					title="주소검색 버튼"
					onClick="javascript:location.href='/user/searchAddress.do?type=${type}&dong='+document.getElementById('dong').value;" />
			</fieldset>
		</div>
		<table class="adres_tbl" summary="주소지검색목록">
			<caption>주소검색목록표</caption>
			<colgroup>
				<col width="20%" />
				<col width="80%" />
			</colgroup>
			<thead>
				<tr>
					<th>우편번호</th>
					<th>지번주소</th>
				</tr>
			</thead>
			<tfoot>
				<tr>
					<th>선택주소</th>
					<td>지번주소 먼저 선택해 주세요</td>
				</tr>
				<tr>
					<th>나머지주소</th>
					<td><input type="text" class="text_adrinfo" title="주소입력창" /></td>
				</tr>
			</tfoot>
			<tbody>
				<c:choose>
					<c:when test="${nList.size() != 0}">
						<c:forEach items="${nList}" var="nList">
							<tr>
								<td style="text-align: center;">${nList.zipCode}</td>
								<td style="text-align: center;"><a
									href="javascript:location.href='/user/searchZipCodeProc.do?address=${nList.address}&zipCode=${nList.zipCode}&custId=${custId}'">${nList.address}</a></td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<th>검색결과가 없습니다. 다시입력해주세요</th>

						</tr>
					</c:otherwise>
				</c:choose>


			</tbody>
		</table>

	</div>




</body>

</html>
