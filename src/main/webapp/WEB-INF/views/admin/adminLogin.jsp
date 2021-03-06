<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<c:import  url="./inc/top.jsp" />
<style>
html{background-color:#93d1e0;}
</style>
<body>
<form:form commandName="adminLoginInfo" name="frm" method="post" action="adminLoginProc.do" onSubmit="return chkForm(this)" id="loginform" style="display:inline;">
	<input type="hidden" name="Mode" value="login">
	
	<div class="login_wrapper">
		<div class="admin_logo"><img src="../resources/img/common/blueone_logo.png" alt="BlueOneMall"></div>
		<div class="admin_input">
		<p class="input_id">
			<label for="user_login">관리자<br />
			<!-- <input type="text" name="id" id="admin_id" class="input Text Eng" value="" size="20" required hname="아이디를 입력하여 주십시오"/></label> -->
			<form:input type="text" path="adminId" id="admin_id" class="input Text Eng" value="" size="20"/></label><form:errors path="adminId" />
		</p>
		<p class="input_pass">
			<label for="user_login">비밀번호<br />
			<!-- <input type="password" name="password" id="admin_pass" class="input" value="" size="20" /></label> -->
			<form:input type="password" path="adminPw" id="admin_pass" class="input" value="" size="20" /></label><form:errors path="adminPw" />
		</p>
		<p class="input_btn" style="margin-bottom:20px">
		<input type="submit" class="btn_login" value="로그인" />
		</p>
		<p class="company">
			<span class="company_tit">Blue One Mall</span><br>
			<!--<span class="company_tit">E-MAIL : 1</span>-->
		</p>
		</div>
	</div>
</form:form>
</body>

<c:import url="./inc/footer.jsp" />

<script type="text/javascript">
<!--
function attempt_focus(){
setTimeout( function(){ try{
d = document.getElementById('admin_id');
d.focus();
d.select();
} catch(e){}
}, 200);
}

attempt_focus();
if(typeof Onload=='function')Onload();
-->
</script>
