<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<script type="text/javascript">
<!--
function SetPriceInput(str)
{
	str=str.replace(/,/g,'');
	var retValue = "";
	for(i=1; i<=str.length; i++)
	{
		if(i > 1 && (i%3)==1) 
			  retValue = str.charAt(str.length - i) + "," + retValue;
		else 
			  retValue = str.charAt(str.length - i) + retValue;    
	}
	document.write(retValue); 
}
//-->
</script>
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
									<td><script>SetPriceInput('${odPrdInfo.sellPrice}');</script></td>
									<td>
										<span class="input_text">${odPrdInfo.buyCnt}<%-- <input type="text" id="buyCnt${i.index}" naem="buyCnt${i.index}" value="${odPrdInfo.buyCnt}" title="수량기입"><!-- <button class="btn_triangle1"></button> --> --%></span>
										<span class="input_btn"><%-- <input type="button" value="수정" title="수정" onClick="location.href='editOrderBuycn.do?ordIdx=${i.index}&ord_unit_chk=${orderInfo.ord_unit_chk}&orderProductList[${i.index}].buyCnt='+document.getElementById('buyCnt${i.index}').value;"> <!-- <button class="btn_triangle2"></button> --> --%></span>
									</td>
									<td><script>SetPriceInput('${odPrdInfo.totalPrice}');</script></td>
									
								</tr>
								<!--<tr>
									<td class="one_choice" colspan="6">
										상품가격 : ${odPrdInfo.totalPrice}원 + 배송비 : 0원 = 합계 ${odPrdInfo.totalPrice}원
									</td>
								</tr>-->
								</c:forEach>
								</c:when>
								<c:otherwise><tr><td>장바구니에 상품이 없습니다.</td></tr></c:otherwise>
								</c:choose>
								<tr>
									<c:set var="total"  value="0"/>
									<td class="total_choice" colspan="6">
										총 주문금액 : 
										<c:forEach items="${odPrdInfo}" var="odPrdInfo">
										<script>SetPriceInput('${odPrdInfo.totalPrice}');</script>원 +
										<c:set var="total" value="${total+odPrdInfo.totalPrice}"/>
										</c:forEach>
										 <c:if test="${total>=config.buyPrice}">배송비 :  <script>SetPriceInput('${config.trasferPrice}');</script>원</c:if>
										 <c:if test="${total<config.buyPrice}">배송비 : 0원 </c:if>
										  <c:if test="${usePoint>0}">- 사용포인트 : <script>SetPriceInput('${usePoint}');</script>원 </c:if>
										 = 합계 <strong><script>SetPriceInput('${total}');</script></strong>원
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
									<td class="in_text" colspan="3">${usePoint}</td>
								</tr>
								<tr>
									<th>결제 수단</th>
									<td class="in_sectext">
										<c:if test="${pay eq '100000000000'}">신용카드</c:if>
										<c:if test="${pay eq '010000000000'}">계좌이체</c:if>
										<c:if test="${pay eq '000100000000'}">복지카드</c:if>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="complet_box">
						<a href="/product/productList.do" class="btn_success">계속쇼핑하기</a>
						<a href="" class="btn_continue">확인</a>
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