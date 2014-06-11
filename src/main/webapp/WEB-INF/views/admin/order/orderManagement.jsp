<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import  url="../inc/top.jsp" />

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

function chk_form(f){
	if(confirm("수정을 진행하시겠습니까?")){
		return true ;
	}else{
		return false ;
	}
}
//-->
</script>

<body>
<div id="Wrap">
	<c:import url="../inc/gnb.jsp"/>
	<c:import url="../inc/lnb.jsp">
		<c:param name="slot" value="order"/>
	</c:import>
	
	<div id="Contents">
		<h1>주문관리 &gt; 주문서리스트 &gt; <strong>주문서수정</strong></h1>
		<form id="sfrm" name="sfrm" method="post" action="orderManagementProc.do" onSubmit="return chk_form(this);">
		<input type="hidden" id="orderNo" name="orderNo" value="${odInfo.orderNo}">
		<input type="hidden" id="customerInfo.custId"  name="customerInfo.custId"  value="${cus.custId}">
		<table class="order_tbl" summary="주문상품 리스트">
			<h5>주문상품 목록표</h5>
			<colgroup>
				<col width="*"/>
				<col width="10%"/>
				<col width="7%"/>
				<col width="10%"/>
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
								<img src="${odPrdInfo.prdSmallImg}"  alt="상품이미지"  width="71" height="71">
							</span>
							<input type="hidden" id="orderProductList[${i.index}].prdCd" name="orderProductList[${i.index}].prdCd"  value="${odPrdInfo.prdCd}"  />
							<input type="hidden" id="orderProductList[${i.index}].buyCnt" name="orderProductList[${i.index}].buyCnt"  value="${odPrdInfo.buyCnt}"  />
							<input type="hidden" id="orderProductList[${i.index}].prdOption" name="orderProductList[${i.index}].prdOption"  value="${odPrdInfo.prdOption}"  />
							<span>
								${odPrdInfo.prdNm}
								<c:if test="${'null' ne odPrdInfo.prdOpColor}"> / ${odPrdInfo.prdOpColor} </c:if>
								<c:if test="${'null' ne odPrdInfo.prdOpSize}"> / ${odPrdInfo.prdOpSize} </c:if>
							</span>
						</td>
						<td class="right"><script>SetPriceInput('${odPrdInfo.sellPrice}');</script> 원&nbsp;&nbsp;</td>
						<td class="center">${odPrdInfo.buyCnt}</td>
						<td class="right"><script>SetPriceInput('${odPrdInfo.totalPrice}');</script> 원&nbsp;&nbsp;</td>
						
					</tr>
					</c:forEach>
				</c:when>
			</c:choose>
			<tr>
				<c:set var="total"  value="0"/>
				<td class="total_choice right" colspan="6">
					총 주문금액 : 
					<c:forEach items="${odPrdInfo}" var="odPrdInfo">
						${odPrdInfo.totalPrice}원 +
						<c:set var="total" value="${total+odPrdInfo.totalPrice}"/>
					</c:forEach>
					<c:if test="${total<=config.buyPrice}">배송비 : ${config.trasferPrice}원<c:set var="total"  value="${total+config.trasferPrice}"/></c:if>
					<c:if test="${total>config.buyPrice}">배송비 : 0원 </c:if>
					= 합계 <strong>${total}</strong>원
					<input type="hidden" id="sndAmount"  name="sndAmount"  value="${total}" />
				</td>
			</tr>
			</tbody>
			</table><br/>
			
			<h5>배송지 현황</h5>
			<table class="order_tblbox">
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
			</table><br/>

			<h5>포인트 / 결제 현황 / 주문 상태</h5>
			<table class="order_tblbox" >
				<colgroup>
					<col width="15%"/>
					<col width="85%"/>
				</colgroup>
				<tbody>
					<tr>
						<th>주문 상태</th>
						<td class="in_sectext">
							<c:if test="${odInfo.orderStatCd eq '01'}">신청대기</c:if>
							<c:if test="${odInfo.orderStatCd eq '02'}">주문완료</c:if>
							<c:if test="${odInfo.orderStatCd eq '07'}">취소신청</c:if>
							<c:if test="${odInfo.orderStatCd eq '08'}">취소완료</c:if>
							<c:if test="${odInfo.orderStatCd eq '03'}">배송준비</c:if>
							<c:if test="${odInfo.orderStatCd eq '04'}">배송중</c:if>
							<c:if test="${odInfo.orderStatCd eq '05'}">배송완료</c:if>
							<c:if test="${odInfo.orderStatCd eq '09'}">반품신청</c:if>
							<c:if test="${odInfo.orderStatCd eq '10'}">반품신청완료</c:if>
							&nbsp;&nbsp;
							<select name=orderStatCd>
								<option value=01 <c:if test="${odInfo.orderStatCd eq '01'}">selected</c:if>>신청대기</option>
								<option value=02 <c:if test="${odInfo.orderStatCd eq '02'}">selected</c:if>>결제완료</option>
								<option value=07 <c:if test="${odInfo.orderStatCd eq '07'}">selected</c:if>>취소신청</option>
								<option value=08 <c:if test="${odInfo.orderStatCd eq '08'}">selected</c:if>>취소완료</option>
								<option value=03 <c:if test="${odInfo.orderStatCd eq '03'}">selected</c:if>>배송분비</option>
								<option value=04 <c:if test="${odInfo.orderStatCd eq '04'}">selected</c:if>>배송중</option>
								<option value=05 <c:if test="${odInfo.orderStatCd eq '05'}">selected</c:if>>배송완료</option>
								<option value=09 <c:if test="${odInfo.orderStatCd eq '09'}">selected</c:if>>반품신청</option>
								<option value=10 <c:if test="${odInfo.orderStatCd eq '10'}">selected</c:if>>반품신청완료</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>포인트 사용현황</th>
						<td class="in_text" colspan="3">${reInfo.reciAdd}</td>
					</tr>
					<tr>
						<th>결제 수단</th>
						<td class="in_sectext">${reInfo.reciAdd}</td>
					</tr>
				</tbody>
			</table>
			<div style="margin-top:20px;" class="center">
				<input type="submit" value="수정하기" class="button_green button_medium"> &nbsp; 
				<input type="button" value="취소" class="button_red button_medium" onClick="reset();">
			</div>
		</form>
	</div>
</div>
</body>

<c:import url="../inc/footer.jsp" />