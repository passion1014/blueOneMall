<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<c:import  url="../inc/topSub.jsp" />    
<script type="text/javascript">
function fnRegistUserInfo() {
	var f = frm;
	f.action = "/user/userRegister.do";

	// --------------------------------------------
	// 데이터 체크
	// --------------------------------------------
	if (f.userId.value == null || f.userId.value == "") {
		alert("아이디를 확인하여 주십시요");
		f.userId.focus();
	}

	
	// --------------------------------------------
	// 데이터 변환이 필요한 항목 처리
	// --------------------------------------------
	// 생년월일
	var birthDt = f.yyyy.value + f.mm.value + f.dd.value;
	f.birthDt.value = birthDt;
	
	// 전화번호
	var phoneNo = f.phoneNo1.value + f.phoneNo2.value + f.phoneNo3.value;
	f.phoneNo.value = phoneNo;
	
	// 휴대폰
	var mobileNo = f.mobileNo1.value + f.mobileNo2.value + f.mobileNo3.value;
	f.mobileNo.value = mobileNo;
	
	// email
	var email = f.email1.value + "@" + f.email2.value;
	f.email.value = email;
	
	// 우편번호
	var zip1 = f.zipcode.value;
	var zip2 = f.zipcode.value;
	f.zip1.value = zip1;
	f.zip2.value = zip2;

	f.submit();
}
</script>
<body>
<div class="wrap">
	<!--  header 시작 -->
	<c:import url="../inc/header.jsp"/>
	<!--  header 끝   -->

	<div class="container">
		
		<c:import url="../inc/userLnb.jsp"/>
		
		<div class="sub_content">
			<form name="frm" action="#" method="post">
			<input type="hidden" name="birthDt">
			<input type="hidden" name="phoneNo">
			<input type="hidden" name="mobileNo">
			<input type="hidden" name="email">
			<input type="hidden" name="zip1">
			<input type="hidden" name="zip2">
				<div class="membership_section">
					<h4>회원가입</h4>
					<ul class="lctit_section">
						<li class="on">
							<span class="loc_boxtit">STEP 1</span>
							<span class="loc_boxstit">약관동의 및 실명인증<br/>
							회원가입
							</span>
						</li>
						
						<li class="rightline">
							<span class="loc_aboxtit">STEP 2</span>
							<span class="loc_boxstit">회원가입 완료</span>
						</li>
					</ul>
						<h5>약관동의</h5>
					<div class="text_area">
						<p class="sub_tit1"> > 회원약관</p>
						<textarea></textarea>
						<span class="agree_box">
							<input type="checkbox" id="agre_check1" name="agree_checked1"/>
							<label for="agre_check1">이용약관에 동의합니다</label>
						</span>
						<p class="sub_tit1 clearfix"> > 개인정보 수집항목, 목적 및 이용안내 </p>
						<textarea></textarea>
						<span class="agree_box">
							<input type="checkbox" id="agre_check2" name="agree checked2"/>
							<label for="agre_check2">이용약관에 동의합니다</label>
						</span>
					</div>
					<h5>기본정보 입력</h5>
					<table class="membership_tbl" summary="회원가입양식">
						<caption>회원가입목록</caption>
						<colgroup>
							<col width="15%"/>
							<col width="38%"/>
							<col width="15%"/>
							<col width="32%"/>
						</colgroup>
						<tbody>
							<tr>
								<th>이름</th>
								<td><input type="text" name="userName" title="아이디입력창"/></td>
								<th><span class="bullet_color">*</span>아이디</th>
								<td><input type="text" name="userId"/></td>
							</tr>
							<tr>
								<th>생년월일</th>
								<td colspan="3">
									<span class="in_number">
										<input type="text" name="yyyy"/><label>년</label>
										<input type="text" name="mm"/><label>월</label>
										<input type="text" name="dd"/><label>일</label>
									</span>
								</td>
							</tr>
							<tr>
								<th>전화번호</th>
								<td>
									<span class="in_number">
										<select name="phoneNo1">
											<option>02</option>
										</select>-
										<input type="text" title="phone number" name="phoneNo2"/>-
										<input type="text" title="phone number" name="phoneNo3"/>
									</span>
								</td>
								<th>휴대폰</th>
								<td>
									<span class="in_number">
										<select name="mobileNo1">
											<option>010</option>
										</select>-
										<input type="text" title="phone number" name="mobileNo2" />-
										<input type="text" title="phone number" name="mobileNo3" />
									</span>
								</td>
							</tr>
							<tr>
								<th>이메일</th>
								<td colspan="3">
									<span class="in_email">
										<input type="text" title="email text" name="email1"/>&nbsp;@&nbsp;
										<input type="text" title="email text" name="email2"/>
										<select>
											<option>직접입력</option>
										</select>
									</span>
								</td>
							</tr>
							<tr>
								<th>주소</th>
								<td colspan="3" class="in_address">
									<input type="text" title="address text" name="zipcode" style="width:120px;"/><button>우편번호 찾기</button><br/>
									<input type="text" title="address text" name="address"/><input type="text" title="address text" name="addressDtl" style="width:220px;"/>
								</td>
							</tr>
						</tbody>
					</table>
					<div class="complet_box">
						<span class="btn_complete">
							<input type="button" value="확인" title="확인" onClick="fnRegistUserInfo();"/>
						</span>
						<span class="btn_cancle">
							<input type="reset" value="취소" title="취소"/>
						</span>
					</div>
				</div>
			</form>
		</div>
	</div>
<!--  container 끝   -->	

	<c:import url="../inc/footer.jsp"/>
</body>
</html>