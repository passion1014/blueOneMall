<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<c:import  url="../inc/topSub.jsp" />

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
											<c:if test="${'' ne odPrdInfo.prdOpColor}">/${odPrdInfo.prdOpColor}</c:if>
											<c:if test="${'' ne odPrdInfo.prdOpSize}">/${odPrdInfo.prdOpSize}</c:if>
											
										</span>
									</td>
									<td><script>SetPriceInput('${odPrdInfo.sellPrice}');</script></td>
									<td>
										<span class="input_text">${odPrdInfo.buyCnt}<!-- <button class="btn_triangle1"></button> --></span>
									</td>
									<td><script>SetPriceInput('${odPrdInfo.totalPrice}');</script></td>
									
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
										<script>SetPriceInput('${odPrdInfo.totalPrice}');</script> 원 + 
										<c:set var="total" value="${total+odPrdInfo.totalPrice}"/>
										</c:forEach>
										 <c:if test="${total<=config.buyPrice}">
											 배송비 :  <script>SetPriceInput('${config.trasferPrice}');</script>원
											<c:set var="total" value="${total+config.trasferPrice}"/>
										 </c:if>
										 <c:if test="${total>config.buyPrice}">  배송비 : 0 원 </c:if>
										 <c:if test="${payList.get(0).payPoint>0}"> - 사용포인트 : ${payList.get(0).payPoint} point<c:set var="total" value="${total-payList.get(0).payPoint}"/> </c:if>
										 = 합계 <strong><script>SetPriceInput('${total}');</script></strong> 원
										 <input type="hidden" id="sndAmount"  name="sndAmount"  value="${total}" />
									</td>
								</tr>
								
							</tbody>
						</table>
						<h5>주문 현황</h5>
						<table class="order_tblbox" summary="주문고객정보표">
							<caption>고객정보 목록표</caption>
							<colgroup>
								<col width="15%"/>
								<col width="35%"/>
								<col width="15%"/>
								<col width="35%"/>
							</colgroup>
							<tbody>
								<c:if test="${payList.size() != 0}">
									<c:forEach var="payList" items="${payList}">
										<tr>
											<th>결제금액</th>
											<td class="in_text"><strong><script>SetPriceInput('${total}');</script></strong> 원</td>
											<th>사용 포인트</th>
											<td class="in_text"><script>SetPriceInput('${payList.payPoint}');</script> point</td>
										</tr>
										<tr>
											<th>주문상태</th>
											<td class="in_sectext">
												<c:if test="${odInfo.orderStatCd eq '01'}">신청대기</c:if>
												<c:if test="${odInfo.orderStatCd eq '02'}">결제완료</c:if>
												<c:if test="${odInfo.orderStatCd eq '07'}">취소신청</c:if>
												<c:if test="${odInfo.orderStatCd eq '08'}">취소완료</c:if>
												<c:if test="${odInfo.orderStatCd eq '03'}">배송준비</c:if>
												<c:if test="${odInfo.orderStatCd eq '04'}">배송중</c:if>
												<c:if test="${odInfo.orderStatCd eq '05'}">배송완료</c:if>
												<c:if test="${odInfo.orderStatCd eq '06'}">구매확정</c:if>
												<c:if test="${odInfo.orderStatCd eq '09'}">반품신청</c:if>
												<c:if test="${odInfo.orderStatCd eq '10'}">반품신청완료</c:if>
											</td>
											<th>결제 수단</th>
											<td class="in_sectext">
												<c:if test="${payList.payMdCd eq '100000000000'}">신용카드</c:if>
												<c:if test="${payList.payMdCd eq '010000000000'}">계좌이체</c:if>
												<c:if test="${payList.payMdCd eq '000100000000'}">복지카드</c:if>
												<c:if test="${payList.payMdCd eq '000000000001'}">포인트</c:if>
											</td>
										</tr>
										<tr>
											<th>비고</th>
											<td class="in_text" colspan="3">${payList.pymtMemo}</td>
										</tr>
										</c:forEach>
								</c:if>
							</tbody>
						</table>
						<h5>배송정보</h5>
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
					</div>
					<div class="complet_box">
						<c:if test="${odInfo.orderStatCd eq '01' or odInfo.orderStatCd eq '03'}"><a href="orderCancel.do?orderNo=${odInfo.orderNo}&" class="btn_success">주문취소</a></c:if>
						<c:if test="${odInfo.orderStatCd eq '04'}">
							<a href="orderTakeBack.do?orderNo=${odInfo.orderNo}" class="btn_continue">반품신청</a>
							<a href="orderComplete.do?orderNo=${odInfo.orderNo}" class="btn_success">구매확정</a>
						</c:if>
						
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