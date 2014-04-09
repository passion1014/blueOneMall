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
			<h3>마이페이지</h3>
			<ul class="lnb_list">
				<li><a href="#">주문내역관리</a></li>
				<li><a href="#">적립금관리</a></li>
				<div class="onclick_list">
					<a href="#">적립금현황</a>
					<a href="#">사용내역조회</a>
				</div>
				<li><a href="#">쿠폰관리</a></li>
				<div class="onclick_list">
					<a href="#">쿠폰복</a>
					<a href="#">마이쿠폰</a>
				</div>
				<li><a href="#">내정보관리</a></li>
				<div class="onclick_list">
					<a href="#">나의정보</a>
				</div>
			</ul>
		</div>
		<div class="sub_content">
			<form action="#" method="post">
				<div class="porder_section">
					<h4>제품주문</h4>
					<p class="sub_tit">내가 발급 받을 수 있는 쿠폰리스트 입니다.</p>
					<p class="sub_text">오랫동안 장바구니에 보관된 상품을 주문하실 경우,가격이나 혜택이 변돌 될 수 있습니다.</p>
					<div class="porder_step">
						<ul>
							<li><img src="./images/sub/shopping_loc3_off.png" alt="SETP3 주문완료이미지"/></li>
							<li class="mmargin"><img src="./images/sub/shopping_loc2_off.png" alt="SETP2 주문/결제이미지"/></li>
							<li class="mmargin"><img src="./images/sub/shopping_loc1_on.png" alt="SETP1 카트이미지"/></li>
						</ul>
						<h5>주문하실 상품</h5>
						<table class="order_tbl" summary="주문상품 리스트">
							<caption>주문상품 목록표</caption>
							<colgroup>
								<col width="5%"/>
								<col width="51%"/>
								<col width="12%"/>
								<col width="10%"/>
								<col width="12%"/>
								<col width="10%"/>
							</colgroup>
							<thead>
								<tr>
									<th>
										<input type="checkbox" title="전체상품선택"/>
									</th>
									<th>상품명/옵션</th>
									<th>판매금액</th>
									<th>수량</th>
									<th>주문금액</th>
									<th>선택</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th>
										<input type="checkbox" title="상품선택"/>
									</th>
									<td class="product_area">
										<span>
										<img src="./images/sub/product_img1.jpg" alt="상품이미지"/></span>
										<span>
											[Harman Kardon] HAR/KAR-AE 이어폰
										</span>
									</td>
									<td>124,000</td>
									<td>
										<span class="input_text"><input type="text" value="1" title="수량기입"><button class="btn_triangle1"></button></span>
										<span class="input_btn"><input type="button" value="수정" title="수정"><button class="btn_triangle2"></button></span>
									</td>
									<td>124,000</td>
									<td>
										<button class="btn_choice1">구매하기</button>
										<button class="btn_choice2">삭제하기</button>
									</td>
								</tr>
								<tr>
									<td class="one_choice" colspan="6">
										상품가격 : 124,000원 + 배송비 : 0원 = 합계 124,000원
									</td>
								</tr>
								<tr>
									<td class="total_choice" colspan="6">
										총 주문금액 : 124,000원 + 배송비 : 0원 = 합계 <strong>124,000</strong>원
									</td>
								</tr>
							</tbody>
						</table>
						<span class="btn_bottom1">
							<button>선택상품삭제</button>
							<button>단품/품절 상품삭제</button>
						</span>
						<span class="btn_bottom2">
							<button class="btn_boximg1">쇼핑계속</button>
							<button class="btn_boximg2">선택상품주문</button>
							<button class="btn_boximg3">전체상품주문</button>
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