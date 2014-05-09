<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:import  url="../inc/top.jsp" />

<body>
<div id="Wrap">
	<c:import url="../inc/gnb.jsp"/>
	<c:import url="../inc/lnb.jsp">
		<c:param name="slot" value="conf"/>
	</c:import>
		
	<div id="Contents">
	<h1>환경설정 &gt; 운영자관리 &gt; <strong>운영자목록</strong></h1>
	<table>
		<colgroup>
			<col width="4%" />
			<col width="7%" />
			<col width="10%" />
			<col width="*" />
			<col width="7%" />
			<col width="7%" />
			<col width="7%" />
			<col width="7%" />
			<col width="7%" />
			<col width="6%" />
			<col width="10%" />
		</colgroup>
		<tr>
			<th rowspan="2">No</th>
			<th rowspan="2">상태</th>
			<th rowspan="2">ID</th>
			<th rowspan="2">이름</th>
			<th colspan="5">관리권한</th>
			<th rowspan="2">로그<br />횟수</th>
			<th rowspan="2">등록일</th>
		</tr>
		<tr>
			<th>환경설정</th>
			<th>회원관리</th>
			<th>상품관리</th>
			<th>주문관리</th>
			<th>커뮤니티</th>
		</tr>
   		
   		<c:forEach items="${list}" var="info">
			<tr onClick="line_detail('1')" >
				<td><b>${info.idx}</b></td>
				<td class="center">${info.status}</td>
				<td class="center" style="cursor:pointer;">${info.id}</td>
				<td class="center">${info.name}</td>
					<c:if test="${info.gd[0] eq 'g1'}" ><td class="center">○</td></c:if>
					<c:if test="${info.gd[0] ne 'g1'}" ><td class="center">X</td></c:if>
				
					<c:if test="${info.gd[1] eq 'g2'}" ><td class="center">○</td></c:if>
					<c:if test="${info.gd[1] ne 'g2'}" ><td class="center">X</td></c:if>
				
					<c:if test="${info.gd[2] eq 'g3'}" ><td class="center">○</td></c:if>
					<c:if test="${info.gd[2] ne 'g3'}" ><td class="center">X</td></c:if>
				
					<c:if test="${info.gd[3] eq 'g4'}" ><td class="center">○</td></c:if>
					<c:if test="${info.gd[3] ne 'g4'}" ><td class="center">X</td></c:if>
				
					<c:if test="${info.gd[4] eq 'g5'}" ><td class="center">○</td></c:if>
					<c:if test="${info.gd[4] ne 'g5'}" ><td class="center">X</td></c:if>
				
				<td class="center">${info.hit}</td>
				<td class="center">${info.regDate}</td>
			</tr>
		
			<tr id="line_1" style="display:none;">
				<td colspan="13" style="padding:5px 5px 30px 50px;" class="right">
						
					<table>
						<colgroup>
							<col width="15%" align="center" bgcolor="#F7F7F7" />
							<col width="35%" />
							<col width="15%" align="center" bgcolor="#F7F7F7" />
							<col width="*" />
						</colgroup>
						<tr>
							<th>상태</th>
							<td colspan="3" class="left"><b><u>${info.status}</u></b> &nbsp;&nbsp; [ 총 : ${info.hit} 회,  최근 로그인 시간 : ${info.lastDate}  ]</td>
						</tr>
						<tr>
							<th>관리자 정보</th>
							<td class="left">( ID :${info.id})&nbsp;&nbsp;</td>
							<th>PASS</th>
							<td class="left">${info.password}</td>
						</tr>
						<tr>
							<th>연락처</th>
							<td class="left">
								<c:forTokens items="${info.phone}" delims="," var="ph" varStatus="i">
									${ph} <c:if test="${i.index != 2}">-</c:if>
								</c:forTokens>
							</td>
							<th>핸드폰</th>
							<td class="left">
								<c:forTokens items="${info.mobile}" delims="," var="mb" varStatus="j">
									${mb} <c:if test="${j.index != 2}">-</c:if>
								</c:forTokens>
							</td>
						</tr>
						<tr>
							<th>Email</th>
							<td colspan="3" class="left">${info.email}</td>
						</tr>
						<tr>
							<th>관리등급</th>
							<td colspan="3" style="padding:5px;">
								<table width="100%">
									<colgroup>
										<col width="35%" />
										<col width="15%" />
										<col width="35%" />
										<col width="*"   />
									</colgroup>
									<tr>
										<th>환경관리</th>
										<td class="center">○</td>
										<th>회원관리</th>
										<td class="center">○</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th>관리정보</th>
							<td colspan="3" style="padding:5px 10px 5px 10px;" class="left">${info.comment}</td>
						</tr>
					</table>
					<div style="margin-top:10px;" class="right">                                              
						<%-- <a href="editAdminInfForm.do?id=${info.id}">button</a> &nbsp;
						 --%>
						 <input type="button" value="수정" class="button button_red button_medium" onClick="location.href='editAdminInfForm.do?id=${info.id}'">
						 <input type="button" value="삭제" class="button button_red button_medium" onClick="confirm_process('','관리자 정보를 삭제하시겠습니까? \n\n삭제후에는 복구가 불가능합니다. \n\n상태를 중지 시키면 권한을 박탈할 수 있습니다. \n\n그래도 삭제하시겠습니까?','deleteAdminProc.do?id=${info.id}');">
					</div>
						

				</td>
			</tr>
		</c:forEach>


	</table>

	<!--<div id="Paser"> 1 | 2 | 3</div>-->
	<div id="Paser">
	<c:forEach var="i" begin="1" end="${endNum}">
		<a href="adminList.do?page=${i}">${i}</a>
		<!-- 
		<input type="button" value="${i}" onClick="javascript:location.href='adminList.do?page=${i}'">
		 -->				
	</c:forEach>
	</div>
	
</div>

</div>
</body>

<c:import url="../inc/footer.jsp" />