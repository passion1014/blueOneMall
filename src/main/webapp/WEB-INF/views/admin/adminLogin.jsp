<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<c:import  url="./inc/top.jsp" />

<body>
<form name="frm" method="post" action="" onSubmit="return chkForm(this)" id="loginform" target="actionForm" style="display:inline;">
<input type="hidden" name="Mode" value="login">
	<div class="login_wrapper">
		<div class="admin_logo"><img src="/img/top_logo.gif" alt="WISEBRAND"></div>
		<div class="admin_input">
		<p class="input_id">
			<label for="user_login">������<br />
			<input type="text" name="admin_id" id="admin_id" class="input Text Eng" value="" size="20" required hname="���̵� �Է��Ͽ� �ֽʽÿ�"/></label>
		</p>
		<p class="input_pass">
			<label for="user_login">��й�ȣ<br />
			<input type="password" name="admin_pass" id="admin_pass" class="input" value="" size="20" /></label>
		</p>
		<p class="input_btn" style="margin-bottom:20px">
		<input type="submit" class="btn_login" value="�α���" />
		</p>
		<p class="company">
			<span class="company_tit">WISEBRAND</span><br>
			<span class="company_tit">E-MAIL : </span>
		</p>
		</div>
	</div>
</form>
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
