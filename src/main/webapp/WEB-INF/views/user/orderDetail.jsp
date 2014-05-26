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
					<h4>주문상세페이지</h4>
					<p class="sub_tit">주문 상세 내용을 볼 수 있는 페이지 입니다.</p>
					<p class="sub_text"></p>
					<div class="porder_step">
						
						<h5>주문하신 상품 ${orderInfo.orderNo }</h5>
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
								<c:choose>
									<c:when test="${odPrdInfo.size() != 0}">
										<c:forEach items="${odPrdInfo}" var="odPrdInfo" varStatus="i">
								<tr>
									<td class="product_area leftalign">
										<span>
										<img src="${odPrdInfo.prdSmallImg}"  alt="상품이미지"  width="71" height="71"></span>
										<input type="hidden" id="orderProductList[${i.index}].prdCd" name="orderProductList[${i.index}].prdCd"  value="${odPrdInfo.prdCd}"  />
										<input type="hidden" id="orderProductList[${i.index}].buyCnt" name="orderProductList[${i.index}].buyCnt"  value="${odPrdInfo.buyCnt}"  />
										<input type="hidden" id="orderProductList[${i.index}].prdOption" name="orderProductList[${i.index}].prdOption"  value="${odPrdInfo.prdOption}"  />
										 
										 
										<span>
											${odPrdInfo.prdNm}
											<c:if test="${'null' ne odPrdInfo.prdOpColor}">/${odPrdInfo.prdOpColor}</c:if>
											<c:if test="${'null' ne odPrdInfo.prdOpSize}">/${odPrdInfo.prdOpSize}</c:if>
											
										</span>
									</td>
									<td>${odPrdInfo.sellPrice}</td>
									<td>
										<span class="input_text">${odPrdInfo.buyCnt}<!-- <button class="btn_triangle1"></button> --></span>
									</td>
									<td>${odPrdInfo.totalPrice}</td>
									
								</tr>
								<tr>
									<td class="one_choice" colspan="6"></td>
								</tr>
								</c:forEach>
								</c:when>
								</c:choose>
								<tr>
									<c:set var="total"  value="0"/>
									<td class="total_choice" colspan="6">
										총 주문금액 : 
										<c:forEach items="${odPrdInfo}" var="odPrdInfo">
										${odPrdInfo.totalPrice}원 +
										<c:set var="total" value="${total+odPrdInfo.totalPrice}"/>
										</c:forEach>
										<c:if test="${total>=config.buyPrice}">배송비 : ${config.trasferPrice}원</c:if>
										 <c:if test="${total<config.buyPrice}">배송비 : 0원 </c:if>
										 = 합계 <strong>${total}</strong>원
										 <input type="hidden" id="sndAmount"  name="sndAmount"  value="${total}" />
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
									<td class="in_text" colspan="3">${reInfo.reciNm}</td>
								</tr>
								<tr>
									<th>전화번호</th>
									<td class="in_sectext">
										<c:forTokens items="${reInfo.reciPh}" delims="," var="ph" varStatus="i">
											${ph} <c:if test="${i.index != 2}">-</c:if>
										</c:forTokens>
									</td>
									<th>휴대전화번호</th>
									<td class="in_sectext">
										<c:forTokens items="${reInfo.reciMb}" delims="," var="mb" varStatus="i">
											${mb} <c:if test="${i.index != 2}">-</c:if>
										</c:forTokens>
									</td>
								</tr>
								<tr>
									<th>배송지 주소</th>
									<td colspan="3" class="address_box">${reInfo.reciAdd}</td>
								</tr>
								<tr>
									<th>배송시 요청사항</th>
									<td colspan="3" class="arrive_box">${reInfo.reciReq}</td>
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
						<c:if test="${odInfo.orderStatCd eq '01' or odInfo.orderStatCd eq '02'}"><a href="orderCancel.do?orderNo=${odInfo.orderNo}" class="btn_success">주문취소</a></c:if>
						<c:if test="${odInfo.orderStatCd eq '03' or odInfo.orderStatCd eq '04'}"><a href="orderTakeBack.do?orderNo=${odInfo.orderNo}" class="btn_continue">반품신청</a></c:if>
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