<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<c:import  url="../inc/topSub.jsp" />    

<body>
<div class="wrap">
	<!--  header 시작 -->
	<c:import url="../inc/header.jsp"/>
	<!--  header 끝   -->

	<div class="container">
		
		<c:import url="../inc/userLnb.jsp"/>
		
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
								<td><input type="text" title="아이디입력창"/></td>
								<th><span class="bullet_color">*</span>아이디</th>
								<td>id</td>
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
								<th>휴대폰</th>
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
								<th>이메일</th>
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
						</tbody>
					</table>
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