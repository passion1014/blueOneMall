<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/basic.css'/>" />
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/admin.css'/>" />

<script type="text/javascript" src="<c:url value='/resources/js/js_ajax.js'/>"> </script>
<script type="text/javascript" src="<c:url value='/resources/js/js_common.js'/>"> </script>
<script type="text/javascript" src="<c:url value='/resources/js/js_admin.js'/>"> </script>
<title>Insert title here</title>
</head>

<body>
<div id="Wrap">
<c:import url="../inc/gnb.jsp"/>
	<c:import url="../inc/lnb.jsp">
		<c:param name="slot" value="conf"/>
	</c:import>

<iframe name="actionForm" width="700" height="200" frameborder="0" style="display:none;"> </iframe>
<div id="Contents">
	<h1>환경설정 &gt; 운영자관리 &gt; <strong>운영자수정</strong></h1>

	<form name="frm" method="post" action="editAdminInf.do" >
	<input type="hidden" id="Mode" name="Mode" value="admin_add" />
	<input type="hidden" id="admin_id" name="id" value="${info.id}" />
	<input type="hidden" id="status" name="status" value="Y" />
	
	<table>
		<colgroup>
			<col width="15%" />
			<col width="35%" />
			<col width="15%" />
			<col width="*" />
		</colgroup>
		<tr>
			<th>ID</th>
			<td class="left">
				${info.id}
			</td>
			<th>이름</th>
			<td class="left">
				<input type="text" id="admin_name" name="name" value="${info.name}" class="Text Kor" style="width:200px;" required hname="이름를 입력하여 주십시오">
			</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td class="left">
				<input type="password" id="admin_pass" name="password" value="" class="Text Eng" style="width:100px;" maxlength="15"  required hname="비밀번호를 입력하여 주십시오"  option="regPass"> ( 4 ~ 16자 )
			</td>
			<th>비밀번호확인</th>
			<td class="left">
				<input type="password" id="re_pass" name="re_pass" value="" class="Text Eng" style="width:100px;" maxlength="15"  required hname="비밀번호를 입력하여 주십시오">
			</td>
		</tr>
		<tr>
			<th>연락처</th>
			<td class="left">
				<c:forTokens items="${info.phone}" delims="," var="ph" varStatus="i">
					<input type="text" id="Phone${i.index+1}" name="Phone" value="${ph}" class="Text Eng" style="width:50px;"> <c:if test="${i.index != 2}">-</c:if>
				</c:forTokens>
			</td>
			<th>핸드폰</th>
			<td class="left">
				<c:forTokens items="${info.mobile}" delims="," var="mb" varStatus="i">
					<input type="text" id="Mobile${i.index+1}" name="Mobile" value="${mb}" class="Text Eng" style="width:50px;"> <c:if test="${i.index != 2}">-</c:if>
				</c:forTokens>
			
			</td>
		</tr>
		<tr>
			<th>EMAIL</th>
			<td colspan="3" class="left">
			 	<c:forTokens items="${info.email}" delims="," var="email" varStatus="i">
			 	<c:if test="${i.index==0 }">
				<input id="emailID" name="email" type="text" value="${email}" class="Text Eng" style="width:250px;" /> @
				<select id="selectDomin" name="selectDomin" onChange="document.getElementById('emailDomain').value = this.value; ">
					<option value="">직접입력</option>
					<option value="naver.com">naver.com</option>
					<option value="yahoo.co.kr">yahoo.co.kr</option>
					<option value="gmail.com">gmail.com</option>
				</select>
				</c:if>
				<c:if test="${i.index==1 }">
				<input id="emailDomain" name="email" value="${email}" type="text" class="Text Eng" style="width:250px;" /> 
				</c:if>
				</c:forTokens>
			</td>
		</tr>
		<tr>
			<th>관리등급</th>
			<td colspan="3" style="padding:5px 5px 5px 10px;" class="left">
				<input type="checkbox" id="g1" name="grade" value="g1" <c:if test="${info.gd[0] eq 'g1'}" >checked</c:if>/> 환경설정
				<input type="checkbox" id="g2" name="grade" value="g2" <c:if test="${info.gd[1] eq 'g2'}" >checked</c:if> /> 회원관리
				<input type="checkbox" id="g3" name="grade" value="g3" <c:if test="${info.gd[2] eq 'g3'}" >checked</c:if> /> 상품관리
				<input type="checkbox" id="g4" name="grade" value="g4" <c:if test="${info.gd[3] eq 'g4'}" >checked</c:if> /> 주문관리
				<input type="checkbox" id="g5" name="grade" value="g5" <c:if test="${info.gd[4] eq 'g5'}" >checked</c:if> /> 커뮤니티
			</td>
		</tr>
		<tr>
			<th>추가관리정보</th>
			<td colspan="3" class="left">
				<textarea id="admin_cmt" name="comment" class="Text Kor" style="width:97%;height:80pt;padding:3px;">${info.comment}</textarea>
			</td>
		</tr>
	</table>

	<div style="margin-top:20px;" class="center">
		<input type="submit" value="수정하기" class="button_green button_medium"> &nbsp; 
		<input type="button" value="취소" class="button_red button_medium" onClick="reset();">
	</div>
	</form>

</div>


</div>
</body>
</html>