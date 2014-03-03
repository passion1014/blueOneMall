<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- admin.top.html start -->

<!doctype html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=1280; user-scalable=yes">
<meta name="apple-touch-fullscreen" content="yes">
<meta name="apple-mobile-web-app-capable" content="yes">
<title>:: ADMIN ::</title>
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/admin/basic.css'/>" />
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/admin/admin.css'/>" />

<script type="text/javascript" src="<c:url value='/resources/js/admin/js_ajax.js'/>"> </script>
<script type="text/javascript" src="<c:url value='/resources/js/admin/js_common.js'/>"> </script>
<script type="text/javascript" src="<c:url value='/resources/js/admin/js_admin.js'/>"> </script>
<!--[if lt IE 9]>
<script src="js/jquery_html5shiv.js"></script>
<![endif]-->

<!-- admin.top.html end -->








<!-- admin.header.html start -->
</head>
<iframe name="actionForm" width="700" height="200" frameborder="0" style="display:none;"> </iframe>

<body>
<div id="Wrap">

	<div id="Header">
		<a href="/_admin/"><img src="/img/top_logo.gif" alt=""></a>
		<div style="padding-right:10px">
			<b>전체관리자</b> 께서 로그인하셨습니다. <input type="button" value="로그아웃" onClick="location.href='./admin.login.html';" style="cursor:pointer;"/>
		</div>
	</div>
<!-- admin.header.html end -->









<!-- admin.gnb.html start -->
	<div id="GNB">
		<ul>
			<li><a href="getAdminList.do">환경설정</a></li>
			<li><a href="#">회원관리</a></li>
			<li><a href="#">상품관리</a></li>
			<li><a href="#">주문관리</a></li>
			<li><a href="#">커뮤니티</a></li>
		</ul>
	</div>
<!-- admin.gnb.html end -->













<!-- admin.lnb_none.html start -->
<div id="LNB">

	<div style="display:block;">
		<h1>환경설정</h1>
		<ul>
			<h2>SITE 환경설정</h2>
			<li><a href="#">정보관리</a></li>
		</ul>
		<ul>
			<h2>통장관리</h2>
			<li><a href="#">통장목록</a></li>
		</ul>
		<ul>
			<h2>운영자 관리</h2>
			<li><a href="getAdminList.do">운영자 목록</a></li>
			<li><a href="registAdminForm.do">운영자 등록</a></li>
		</ul>
	</div>

</div>


<!-- admin.lnb_none.html end -->


<div id="Contents">
	<h1>환경설정 &gt; 운영자관리 &gt; <strong>운영자등록</strong></h1>

	<form name="frm" method="post" action="registAdminInf.do" onSubmit="return chk_admin_form(this);" style="display:inline;" target="actionForm">
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
				<input type="checkbox" id="g1" name="g1" value="y" /> 환경설정
				<input type="checkbox" id="g2" name="g2" value="y" /> 회원관리
				<input type="checkbox" id="g3" name="g3" value="y" /> 상품관리
				<input type="checkbox" id="g4" name="g4" value="Y" /> 주문관리
				<input type="checkbox" id="g5" name="g5" value="Y" /> 커뮤니티
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
</html>
<!-- admin.bottom.html end-->