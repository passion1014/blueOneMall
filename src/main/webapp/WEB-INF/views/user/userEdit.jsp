<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<c:import  url="../inc/topSub.jsp" />
<html>
<body>
	<div class="wrap">
	<!--  header 시작   -->
	<c:import url="../inc/header.jsp" />
	<!--  header 끝   -->

	<div class="container">
		
		<c:import url="../inc/memberLnb.jsp" />
		
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
								<td class="topline"><input type="text" /></td>
								<th class="topline">아이디</th>
								<td class="topline"><input type="text" /></td>
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