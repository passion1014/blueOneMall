<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../inc/top.jsp" />

<body>

<div id="Wrap">

	<c:import url="../inc/gnb.jsp"/>
	<c:import url="../inc/lnb.jsp">
	   <c:param name="slot" value="member"/>
	</c:import>
	 <div id="Contents">
	<h1>회원관리 &gt; 회원정보 &gt; <strong>회원목록</strong></h1>
	
	<form id="sfrm" name="sfrm" method="get" action="searchMember.do">
		<table>
			<tr>
				<td class="left">
					<div style="margin-top:5px;">
						<select id="custColum" name="custColum">
							<option value="1" selected>회원명</option>
							<option value="2" >회원ID</option>
							<option value="3">이메일</option>
							<option value="4">핸드폰번호</option>
						</select>
						<input type="text" id="word" name="word" class="Text" value="">
						<input type="submit" value="검색"   class="Small_Button Gray">&nbsp;&nbsp;
					</div>
				</td>
			</tr>
		</table>
		
	    
	<div class="left" style="margin-top:15px;"><b>회원 리스트</b> ( 검색 인원 : ${cust.size()} 명 )</div>
	
	<table>
		<colgroup>
			<col width="5%" />
			<col width="12%" />
			<col width="9%" />
			<col width="9%" />
			<col width="12%" />
			<col width="12%" />
			<col width="*" />
			<col width="12%" />
		</colgroup>
		<tr>
			<th>No</th>
			<th>가입일</th>
			<th>회원ID</th>
			<th>회원명</th>
			<th>연락처</th>
			<th>핸드폰</th>
			<th>Email</th>
			<th>관리</th>
		</tr>

		<c:choose>
			<c:when test="${cust.size() != 0}">
				<c:forEach items="${cust}" var="cust">
					<tr>
						<td class="center">${cust.custSeq}</td>
						<td class="center">${cust.custRegDt.substring(0,10)}</td>
						<td class="center">${cust.custId}</td>
						<td class="center">${cust.custNm}</td>
						<td class="center">${cust.custPh}</td>
						<td class="center">${cust.custMb}</td>
						<td class="center">${cust.custEmail}</td>
						<td class="center">
							<input type="button" value="관리" class="Small_Button Gray" onClick="location.href='memberEdit.do?custId=${cust.custId}'"> 
							<input type="button" value="삭제" class="Small_Button Gray" onClick="confirm_process('','회원 정보를 삭제하시겠습니까? \n\n삭제후에는 복구가 불가능합니다.','deleteMember.do?custId=${cust.custId}');">
						</td>
					</tr>
				</c:forEach>
			</c:when>

			<c:otherwise>
				<tr>
					<td colspan="8" height="150">회원 정보가 없습니다.</th>
				</tr>
			</c:otherwise>
		</c:choose>

	</table>

	<!-- page -->
	<div id="Paser">
		<a class="palign1" href="memberList.do?page=1"><img src='/resources/img/common/btn_first.gif' alt='첫 페이지로 이동' /></a>
		
		<c:set var="prePage" value="${page-1}"/>
		<c:if test="${prePage < 1 }"><c:set var="prePage" value="1"/></c:if>
		<a class="palign2" href="memberList.do?page=${prePage}"><img src='/resources/img/common/btn_prev.gif' alt='이전 페이지로 이동'/></a>
		
		
		<div id="forPage" class="inblock"></div>
		


		<c:set var="nextPage" value="${page+1}"/>
		<c:if test="${nextPage > endNum }"><c:set var="nextPage" value="${endNum}"/></c:if>
		<a class="palign1" href="memberList.do?page=${nextPage}"><img src='/resources/img/common/btn_next.gif'  /></a>
		
		<a class="palign2" href="memberList.do?page=${endNum}"><img src='/resources/img/common/btn_end.gif' alt='마지막 페이지로 이동' /></a>
	</div>

</form>    
	
</div>
	

</div>
<script type="text/javascript" language="JavaScript">
<!--
$(document).ready(function() {
	var str = '';
	
	if(${page+9 >= endNum}){
	
		
		str +=  "<c:forEach var='i' begin='${page}' end='${endNum}'>";
		str +=  "<a href='memberList.do?page=${i}'>&nbsp;${i}</a>";
		str += "&nbsp;</c:forEach>";

	}else{
	
		
		str +=  "<c:forEach var='i' begin='${page}' end='${page+9}'>";
		str +=  "<a href='memberList.do?page=${i}'>&nbsp;${i}</a>";
		str += "&nbsp;</c:forEach>";

	}
	
	document.getElementById("forPage").innerHTML = str;
	
});

-->
</script>

</body>

<c:import url="../inc/footer.jsp" />