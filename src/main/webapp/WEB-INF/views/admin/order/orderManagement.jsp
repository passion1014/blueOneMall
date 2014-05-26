<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import  url="../inc/top.jsp" />
 
<body>
<div id="Wrap">

	<c:import url="../inc/gnb.jsp"/>
	<c:import url="../inc/lnb.jsp">
	   <c:param name="slot" value="order"/>
	</c:import>
 <div id="Contents">
	<h1>주문관리 &gt; 주문서리스트 &gt; 
	<c:choose>
		<c:when test="${sh eq 'all'}"> 
			<strong>전체리스트</strong>
		</c:when>
		<c:when test="${sh eq 'ordering'}"> 
			<strong>신청중</strong>
		</c:when>
		<c:when test="${sh eq 'orderComplete'}"> 
			<strong>주문완료</strong>
		</c:when>
		<c:when test="${sh eq 'Transfering'}"> 
			<strong>배송중</strong>
		</c:when>
		<c:when test="${sh eq 'TransferReady'}"> 
			<strong>배송준비중</strong>
		</c:when>
		<c:when test="${sh eq 'cancel'}"> 
			<strong>취소신청</strong>
		</c:when>
		<c:when test="${sh eq 'cancelComplete'}"> 
			<strong>취소완료</strong>
		</c:when>
		<c:when test="${sh eq 'return'}"> 
			<strong>반품신청</strong>
		</c:when>
		<c:when test="${sh eq 'retrunComplete'}"> 
			<strong>반품완료</strong>
		</c:when>
				
				
	
	</c:choose>
	</h1>
	<form id="sfrm" name="sfrm" method="get" action="./admin.member.php">
		<input type="hidden" id="slot" name="slot" value="member">
		<input type="hidden" id="type" name="type" value="member_list">
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
						<td class="one_choice" colspan="6">
						</td>
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
	

</div>
</body>

<c:import url="../inc/footer.jsp" />