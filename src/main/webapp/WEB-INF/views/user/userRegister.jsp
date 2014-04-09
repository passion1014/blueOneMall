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
	<div class="header">
		<div class="top">
			<h1><a href="#"><img src="./images/common/blueone_logo.png" alt="blueone logo"/></a></h1>
			<p class="top_img">
				<img src="./images/common/top_image.jpg" alt="top image"/>
			</p>
			<form action="#" method="post">
				<div class="search_area">
					<span class="search_menu">
						<a href="#" class="menu_site">장바구니</a>
						<a href="#" class="menu_site">주문배송 조회</a>
						<a href="#">마이페이지</a>
					</span>
					<span class="search_box">
						<input type="text" title="서치텍스트박스"/>
						<button></button>
					</span>
				</div>
			</form>
		</div>
		<div class="gnb">
			<ul>
				<li><a href="#">BLUETOOTH</a></li>
				<li class="gnb_list"><a href="#">EARPHONE</a></li>
				<li class="gnb_list"><a href="#">HEADPHONE</a></li>
				<li class="gnb_list"><a href="#">MULTMEDIA</a></li>
				<li class="gnb_list"><a href="#">CASE</a></li>
				<li class="gnb_list"><a href="#">XTC</a></li>
				<li class="gnb_list"><a href="#">SALE</a></li>
				<li class="gnb_list"><a href="#">BARND SHOP</a></li>
			</ul>
		</div>
	</div>
<!--  header 끝   -->

	<div class="container">
		<div class="lnb">
			<h3>CATEGORY</h3>
			<ul class="lnb_list">
				<li><a href="#">Multimedia(MM)</a></li>
				<li><a href="#">Amp</a></li>
				<div class="onclick_list">
					<a href="#">인디앰프</a>
					<a href="#">프리앰프</a>
					<a href="#">파워앰프</a>
					<a href="#">올인원앰프/시스템</a>
					<a href="#">포노앰프</a>
					<a href="#">홈시어터앰프</a>
				</div>
				<li><a href="#">Speaker</a></li>
				<div class="onclick_list">
					<a href="#">Hi-Fi Collecion</a>
					<a href="#">톨보이스커</a>
					<a href="#">북셀프스피커</a>
					<a href="#">멀티채널스피커</a>
					<a href="#">사운드바</a>
				</div>
			</ul>
		</div>
		<div class="sub_content">
			<form action="#" method="post">
				<div class="membership_section">
					<h4>회원가입</h4>
					<p class="sub_tit">모든 서비스를 이용하시려면 회원가입을 하셔야 합니다.</p>
					<p class="sub_text">회원 가입하시려면 회원약관 및 개인정보 수집항목, 목적 및 이용안내, 쇼핑몰의 
					수집한 개인정보 취급위탁의 동의와 실명인증이 필요합니다.</p>
					<ul class="lctit_section">
						<li>
							<span class="loc_boxtit">STEP 1</span>
							<span class="loc_boxstit">약관동의 및 <br/>
							실명인증
							</span>
						</li>
						<li class="on">
							<span class="loc_aboxtit">STEP 2</span>
							<span class="loc_boxstit">회원정보 입력</span>
						</li>
						<li class="rightline">
							<span class="loc_aboxtit">STEP 3</span>
							<span class="loc_boxstit">회원가입 완료</span>
						</li>
					</ul>
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

	<div class="footer">
		<div class="footer_area">
			<h2><img src="./images/common/footer_logo.jpg" alt="현대 로고"/></h2>
			<address>
				공정거래위원회 고시 제2001-1호에 따른 사업자 등록번호:212-81-86027ㅣ대표이사 : 김화웅<br/>
				개인정보관리 책임자 법인사업부 법인영업1팀 송선호 부장 l 주소:서울시 강동구 암사동 513-16번지 현대H&S<br/>
				COPYRIGHT 2012 BY 현대H&S ALL RIGHT RESERVED.
			</address>
		</div>
	</div>
</div>
</body>
</html>