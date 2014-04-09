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
	<c:import url="../inc/header.jsp" />
<!--  header 끝   -->

	<div class="container">
		<c:import url="../inc/userLnb.jsp" />
		<div class="sub_content">
			<form action="#" method="post">
				<div class="mypage_section">
					<h4>나의 정보</h4>
					<p class="sub_tit">고객의 기본정보 내역을 확인하실 수 있습니다.</p>
					<div class="mypage_infobox">
						<h5 class="basic_infotit">기본정보 입력</h5>
						<table class="myinfo_tbl" summary="정보 입력표">
							<caption>나의정보입력표</caption>
							<colgroup>
								<col width="15%"/>
								<col width="35%"/>
								<col width="15%"/>
								<col width="35%"/>
							</colgroup>
							<tbody>
								<tr>
									<th class="topline">이름</th>
									<td class="topline"></td>
									<th class="topline">생년월일</th>
									<td class="topline">
										<span class="in_text">
											<input type="text" id="year" />
											<label for="year">년</label>
											<input type="text" id="month" />
											<label for="month">월</label>
											<input type="text" id="day" />
											<label for="day">일</label>
										</span>
										<span class="in_radio">
											<input type="radio" id="solar_calendar" name="calender" />
											<label for="solar_calendar">양력</label>
											<input type="radio" id="lunar_calendar" name="calender" />
											<label for="lunar_calendar">음력</label>
										</span>
									</td>
								</tr>
								<tr>
									<th>성별</th>
									<td colspan="3">
										<span class="in_radio">
											<input type="radio" id="woman" name="sex" />
											<label for="woman">여성</label>
											<input type="radio" id="man" name="sex" />
											<label for="man">남성</label>
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
								<tr>
									<th class="bottomline">SMS수신여부</th>
									<td class="bottomline">
										<span class="in_radio">
											<input type="radio" id="agree" name="sms" />
											<label for="agree">수신동의</label>
											<input type="radio" id="refusal" name="sms" />
											<label for="refusal">수신거부</label>
										</span>
									</td>
									<th class="bottomline">e-mail수신여부</th>
									<td class="bottomline">
										<span class="in_radio">
											<input type="radio" id="e_agree" name="email" />
											<label for="e_agree">수신동의</label>
											<input type="radio" id="e_refusal" name="email" />
											<label for="e_refusal">수신거부</label>
										</span>
									</td>
								</tr>
							</tbody>
						</table>
						<p class="tbl_text">※ SMS 및 이메일을 통해 서비스 안내 및 다양한 이벤트 소식이 제공됩니다. 원하시지 않을 경우에는 수신거부를 선택해주시기 바랍니다.</span>
						<h5 class="basic_infotit">선택정보</h5>
						<table class="myinfo_tbl" summary="정보 입력표">
							<caption>나의정보입력표</caption>
							<colgroup>
								<col width="15%"/>
								<col width="35%"/>
								<col width="15%"/>
								<col width="35%"/>
							</colgroup>
							<tbody>
								<tr>
									<th class="topline">결혼여부</th>
									<td class="topline">
										<span class="in_radio">
											<input type="radio" id="single" name="marry" />
											<label for="single">미혼</label>
											<input type="radio" id="married" name="marry" />
											<label for="married">기혼</label>
										</span>
									</td>
									<th class="topline">결혼기념일</th>
									<td class="topline">
										<span class="in_text">
											<input type="text" id="married_year" />
											<label for="married_year">년</label>
											<input type="text" id="married_month" />
											<label for="married_month">월</label>
											<input type="text" id="married_day" />
											<label for="married_day">일</label>
										</span>
								</tr>
								<tr>
									<th class="bottomline">자녀유무</th>
									<td class="bottomline" colspan="3">
										<span class="in_radio">
											<input type="radio" id="without" name="children" />
											<label for="without">무</label>
											<input type="radio" id="with" name="children" />
											<label for="with">유</label>
										</span>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<span class="btn_submit">
						<input type="submit" value="개인정보 변경" title="개인정보변경버튼"/>
					</span>
				</div>
			</form>
		</div>
	</div>
<!--  container 끝   -->	

	<div class="footer">
		<div class="footer_area">
			<h2><img src="<c:url value='/resources/img/common/footer_logo.jpg'/>" alt="현대 로고"/></h2>
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