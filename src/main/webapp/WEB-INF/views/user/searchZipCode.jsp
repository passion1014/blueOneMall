<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import  url="../inc/topSub.jsp" />

<script language="javascript">
<!--
function addZipCode(zipcode1, zipcode2, address){

	opener.document.getElementById("custZip1").value = zipcode1 ;
	opener.document.getElementById("custZip2").value = zipcode2 ;

	opener.document.getElementById("custAdd").value = address ;
	opener.document.getElementById("custAddDetail").focus() ;

	self.close();

}
//-->
</script>

<body>
<div class="adr_section">
	<h5>주소검색</h5>
	<div class="adr_box">
		<p class="adr_tit"><b>읍,면,동</b>을 입력해주시기 바랍니다.(예:압구정동)</p>
		<fieldset>
			<legend></legend>
			<input type="text" class="text_info" title="주소지 입력창" id="dong" name="dong"/>
			<input type="button" class="btn_adr" value="주소검색" title="주소검색 버튼" onClick="javascript:location.href='searchAddress.do?type=${type}&dong='+document.getElementById('dong').value;"/>
		</fieldset>
	</div>
	<table class="adres_tbl" summary="주소지검색목록">
		<caption>주소검색목록표</caption>
		<colgroup>
			<col width="20%"/>
			<col width="80%"/>
		</colgroup>
		<thead>
			<tr>
				<th>우편번호</th>
				<th>지번주소</th>
			</tr>
		</thead>
		<tfoot>
			<c:if test="${error.isEmpty() == false}">
				<tr>
					<td colspan="2"><c:out value="${error}"></c:out></td>
				</tr>
			</c:if>
			<tr>
				<!-- <th>나머지주소</th>
				<td>
					<input type="text" class="text_adrinfo" title="주소입력창"/>
				</td> -->
				<th>선택주소</th>
				<td>지번주소 먼저 선택해 주세요</td>
			</tr>
		</tfoot>
		<tbody>
		<c:choose>
			<c:when test="${nList.size() != 0}">
				<c:forEach items="${nList}"  var="nList">
					<tr>
						<td style="text-align:center;">${nList.zipCode}</td>
						<!--<td style="text-align:center;"><a href="javascript:location.href='searchZipCodeProc.do?address=${nList.address}&zipCode=${nList.zipCode}&type=${type}'">${nList.address}</a></td>-->
						<td style="text-align:center;"><a href="javascript:void(0);" onClick="addZipCode('${nList.zipCode.substring(0,3)}', '${nList.zipCode.substring(4)}','${nList.address}')">${nList.address}</a></td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<th colspan="2">검색결과가 없습니다. 다시입력해주세요</th>
				</tr>
			</c:otherwise>
		</c:choose>
		</tbody>
	</table>
</div>
</body>
</html>	