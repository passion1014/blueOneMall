<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
    
    
<c:import  url="../inc/topSub.jsp" />
<c:import  url="../inc/topMain.jsp" />  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="wrap">
	<c:import url="../inc/header.jsp"/>
<!--  header 끝   -->

	<div class="container">
		<c:import url="../inc/orderLnb.jsp" />
		<div class="sub_content">
			<form action="#" method="post">
				<div class="porder_section">
					<h4>주문 완료</h4>
					<p class="sub_tit">내가 발급 받을 수 있는 쿠폰리스트 입니다.</p>
					<p class="sub_text">오랫동안 장바구니에 보관된 상품을 주문하실 경우,가격이나 혜택이 변돌 될 수 있습니다.</p>
					<div class="porder_step">
						<ul>
							<li><img src="<c:url value='/resources/img/sub/shopping_loc3_on.png'/>" alt="SETP3 주문완료이미지"/></li>
							<li class="mmargin"><img src="<c:url value='/resources/img/sub/shopping_loc2_off.png'/>" alt="SETP2 주문/결제이미지"/></li>
							<li class="mmargin"><img src="<c:url value='/resources/img/sub/shopping_loc1_off.png'/>" alt="SETP1 카트이미지"/></li>
						</ul>
						<h5>주문하신 상품</h5>
						<table class="order_tbl" summary="주문상품 리스트">
							<caption>주문상품 목록표</caption>
							<colgroup>
								<col width="65%"/>
								<col width="12%"/>
								<col width="10%"/>
								<col width="17%"/>
							</colgroup>
							<thead>
								<tr>
									<th>상품명/옵션</th>
									<th>판매금액</th>
									<th>수량</th>
									<th>주문금액</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td class="product_area leftalign">
										<span>
										<img src="<c:url value='/resources/img/sub/product_img1.jpg'/>" alt="상품이미지"/></span>
										<span>
											[Harman Kardon] HAR/KAR-AE 이어폰
										</span>
									</td>
									<td>124,000</td>
									<td>1개</td>
									<td>124,000</td>
								</tr>
								<tr>
									<td class="one_choice" colspan="4">
										상품가격 : 124,000원 + 배송비 : 0원 = 합계 124,000원
									</td>
								</tr>
								<tr>
									<td class="total_choice" colspan="4">
										총 주문금액 : 124,000원 + 배송비 : 0원 = 합계 <strong>124,000</strong>원
									</td>
								</tr>
							</tbody>
						</table>
						<h5>배송지 현황</h5>
						<table class="order_tblbox" summary="주문고객정보표">
							<caption>고객정보 목록표</caption>
							<colgroup>
								<col width="15%"/>
								<col width="35%"/>
								<col width="15%"/>
								<col width="35%"/>
							</colgroup>
							<tbody>
								<tr>
									<th>받으시는분</th>
									<td class="in_text" colspan="3">
									</td>
								</tr>
								<tr>
									<th>전화번호</th>
									<td class="in_sectext">
									</td>
									<th>휴대전화번호</th>
									<td class="in_sectext">
									</td>
								</tr>
								<tr>
									<th>배송지 주소</th>
									<td colspan="3" class="address_box">
									</td>
								</tr>
								<tr>
									<th>배송시 요청사항</th>
									<td colspan="3" class="arrive_box">
									</td>
								</tr>
							</tbody>
						</table>
						<h5>적립금 및 포인트 / 결제 현황</h5>
						<table class="order_tblbox" summary="주문고객정보표">
							<caption>고객정보 목록표</caption>
							<colgroup>
								<col width="15%"/>
								<col width="85%"/>
							</colgroup>
							<tbody>
								<tr>
									<th>적립금/포인트</th>
									<td class="in_text" colspan="3">
									</td>
								</tr>
								<tr>
									<th>결제 수단</th>
									<td class="in_sectext">
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="complet_box">
						<a href="#" class="btn_success">계속쇼핑하기</a>
						<a href="#" class="btn_continue">확인</a>
					</div>
				</div>
			</form>
		</div>
	</div>
<!--  container 끝   -->	
	<c:import url="../inc/footer.jsp" />
</div>
</body>
</html>