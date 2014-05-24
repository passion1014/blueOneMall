<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import  url="../inc/top.jsp" />

<script type="text/javascript">
	function chk_double_admin_id() {
		frm.action = "checkAdminId.do";
		frm.submit();
	}

	/*
	$(document).read(function() {
		$('#loginidcheck').click(function() {
			$.getJSON('checkloginid/' + $('#loginid').val(), function(result) {
				if (result.duplicated == true) {
					alert('이미 등록된 아이디입니다.');
				} else {
					alert('사용가능한 아이디입니다.');
				}
			});
		});
	});
	*/
</script>

<body>
<div id="Wrap">
	<c:import url="../inc/gnb.jsp"/>
	<c:import url="../inc/lnb.jsp">
		<c:param name="slot" value="conf"/>
	</c:import>

	<div id="Contents">
		<h1>환경설정 &gt; 운영자관리 &gt; <strong>운영자등록</strong></h1>

		<form name="frm" method="post" action="registAdminInf.do" >
		<input type="hidden" id="Mode" name="Mode" value="admin_add">
		<input type="hidden" id="chk_id" name="chk_id" value="" required hname="아이디 중복찾기를 하여 주십시오!">
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
					<input type="text" id="admin_id" name="id" value="" class="Text Eng" style="width:120px;" required hname="아이디를 등록하여 주십시오" option="regId"> <input type="button" value="중복찾기" class="button_green button_small" onClick="chk_double_admin_id();">
				</td>
				<th>이름</th>
				<td class="left">
					<input type="text" id="admin_name" name="name" value="" class="Text Kor" style="width:200px;" required hname="이름를 입력하여 주십시오">
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
					<input type="text" id="Phone1" name="Phone" value="" class="Text Eng" style="width:50px;"> -
					<input type="text" id="Phone2" name="Phone" value="" class="Text Eng" style="width:50px;"> -
					<input type="text" id="Phone3" name="Phone" value="" class="Text Eng" style="width:50px;">
				</td>
				<th>핸드폰</th>
				<td class="left">
					<input type="text" id="Mobile1" name="Mobile" value="" class="Text Eng" style="width:50px;"> -
					<input type="text" id="Mobile2" name="Mobile" value="" class="Text Eng" style="width:50px;"> -
					<input type="text" id="Mobile3" name="Mobile" value="" class="Text Eng" style="width:50px;">
				</td>
			</tr>
			<tr>
				<th>EMAIL</th>
				<td colspan="3" class="left">
					<input id="emailID" name="email" type="text" class="Text Eng" style="width:250px;" /> @
					<select id="selectDomin" name="selectDomin" onChange="document.getElementById('emailDomain').value = this.value; ">
						<option value="">직접입력</option>
						<option value="naver.com">naver.com</option>
						<option value="yahoo.co.kr">yahoo.co.kr</option>
						<option value="gmail.com">gmail.com</option>
					</select>
					<input id="emailDomain" name="email" type="text" class="Text Eng" style="width:250px;" /> 
				</td>
			</tr>
			<tr>
				<th>관리등급</th>
				<td colspan="3" style="padding:5px 5px 5px 10px;" class="left">
					<input type="checkbox" id="g1" name="grade" value="g1" /> 환경설정
					<input type="checkbox" id="g2" name="grade" value="g2" /> 회원관리
					<input type="checkbox" id="g3" name="grade" value="g3" /> 상품관리
					<input type="checkbox" id="g4" name="grade" value="g4" /> 주문관리
					<input type="checkbox" id="g5" name="grade" value="g5" /> 커뮤니티
				</td>
			</tr>
			<tr>
				<th>추가관리정보</th>
				<td colspan="3" class="left">
					<textarea id="admin_cmt" name="comment" class="Text Kor" style="width:97%;height:80pt;padding:3px;"></textarea>
				</td>
			</tr>
		</table>

		<div style="margin-top:20px;" class="center">
			<input type="submit" value="등록하기" class="button_green button_medium"> &nbsp; 
			<input type="button" value="취소" class="button_red button_medium" onClick="reset();">
		</div>
		</form>

	</div>

	<!-- admin.bottom.html start-->
</div>
</body>

<c:import url="../inc/footer.jsp" />