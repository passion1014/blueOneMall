<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
    
    
<c:import  url="../inc/topSub.jsp" />
<c:import  url="../inc/topMain.jsp" />    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>###### 현대프로모션몰 ######</title>
</head>
<body>
		<div class="wrap">
	<c:import url="../inc/header.jsp"/>
<!--  header 끝   -->

	<div class="container">
		<c:import url="../inc/productLnb.jsp"/>
		<div class="sub_content">
			<form action="#" method="post">
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
							<input type="checkbox" id="agre_check1" name="agree checked1"/>
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
								<td></td>
								<th><span class="bullet_color">*</span>아이디</th>
								<td>
									<span class="in_idpass">
										<input type="text" title="아이디입력창"/>
									</span>
								</td>
							</tr>
							<tr>
								<th>생년월일</th>
								<td colspan="3">
									<span class="in_number">
										<input type="text" /><label>년</label>
										<input type="text" /><label>월</label>
										<input type="text" /><label>일</label>
									</span>
								</td>
							</tr>
							<tr>
								<th>전화번호</th>
								<td>
									<span class="in_number">
										<select>
											<option>02</option>
										</select>-
										<input type="text" title="phone number" />-
										<input type="text" title="phone number" />
									</span>
								</td>
								<th><span class="bullet_color">*</span>휴대폰</th>
								<td>
									<span class="in_number">
										<select>
											<option>010</option>
										</select>-
										<input type="text" title="phone number" />-
										<input type="text" title="phone number" />
									</span>
								</td>
							</tr>
							<tr>
								<th><span class="bullet_color">*</span>이메일</th>
								<td colspan="3">
									<span class="in_email">
										<input type="text" title="email text" />&nbsp;@&nbsp;
										<input type="text" title="email text" />
										<select>
											<option>직접입력</option>
										</select>
									</span>
								</td>
							</tr>
							<tr>
								<th>주소</th>
								<td colspan="3" class="in_address">
									<input type="text" title="address text" style="width:120px;"/><button>우편번호 찾기</button><br/>
									<input type="text" title="address text" /><input type="text" title="address text" style="width:220px;"/>
								</td>
							</tr>
							<tr>
								<th><span class="bullet_color">*</span>SMS수신여부</th>
								<td>
									<span class="in_radio">
										<input type="radio" id="agree" name="sms" />
										<label for="agree">수신동의</label>
										<input type="radio" id="refusal" name="sms" />
										<label for="refusal">수신거부</label>
									</span>
								</td>
								<th><span class="bullet_color">*</span>e-mail수신여부</th>
								<td>
									<span class="in_radio">
										<input type="radio" id="e_agree" name="email" />
										<label for="e_agree">수신동의</label>
										<input type="radio" id="e_refusal" name="email" />
										<label for="e_refusal">수신거부</label>
									</span>
								</td>
							</tr>
							<tr>
								<th><span class="bullet_color">*</span>비밀번호</th>
								<td>
									<span class="in_idpass">
										<input type="password" title="비밀번호" />
									</span>
								</td>
								<th><span class="bullet_color">*</span>비밀번호 확인</th>
								<td>
									<span class="in_idpass">
										<input type="password" title="비밀번호 확인" />
									</span>
								</td>
							</tr>
						</tbody>
					</table>
					<p class="tabbt_text">
						※ 보안을 위하여 비밀번호는 영문과 숫자혼합 8자~12자 이내로 작성해 주시기 바라며, 같은 문자나 숫자가 연속으로 3회 이상 반복되면 안됩니다.
					</p>
					<div class="complet_box">
						<span class="btn_complete">
							<input type="submit" value="확인" title="확인"/>
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