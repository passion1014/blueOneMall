<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<body>
<div id="Wrap">
	<!-- 컨덴츠 -->
	<div style="padding:10px;">
	<div> 우편번호찾기	</div>
	<form name="frm" method="post" action="userEdit.do">
			
				
	<table class="tbl1">
		<colgroup>
			<col width="20%" />
			<col width="50%" />
			<col width="*" />
		</colgroup>

		<tr>
			<th>우편번호 찾기</th>
			<td class="left">
				<input type="text" id="dong" name="dong" class="Text Kor" style="width:60%;">
				<input type="button" value="찾기" class="Button Gray" oncClick="location.herf:'searchAddress.do?dong='+document.getElementById("dong").value;">
			</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<th>주소</th>
			
		<tr>
		
		<c:choose>
			<c:when test="${nList.size() != 0}">
				<c:forEach items="${nList}" var="nodeDOC">
					<tr>
						<td style="text-align:center;">${nodeDOC.zipCode}</td>
						<td style="text-align:left;">${nodeDOC.address}</td>
						
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="4" height="200"> 주소를 찾을 수 없습니다. 다시 입력해 주세요</td>
				</tr>			
		    </c:otherwise>
		</c:choose>
		
			
 <tr>
  <td><input type="submit" value="등록하기" class="Button Gray"></td>
  <td></td>
 </tr>


		
	</table>

	
	</form>
	</div>
		
</div>
</body>

			