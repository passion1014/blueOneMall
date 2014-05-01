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
	<c:import url="../inc/header.jsp"/>
<!--  header 끝   -->

	<div class="container">
		<c:import url="../inc/orderLnb.jsp" />
		<div class="sub_content">
			<form action="orderRegisterProc.do" method="post">
			<input type="hidden" id="customerInfo.custId"  name="customerInfo.custId"  value="id1">
			<input type="hidden" id="customerInfo.modifyUserId"  name="customerInfo.modifyUserId"  value="id1">
			<input type="hidden" id="orderProductList"  name="orderProductList"  value="${odPrdInfo}" />
									
			
				<div class="porder_section">
					<h4>제품주문</h4>
					<div class="porder_step">
						<ul>
							<li><img src="<c:url value='/resources/img/sub/shopping_loc3_off.png'/>"  alt="SETP3 주문완료이미지"/></li>
							<li class="mmargin"><img src="<c:url value='/resources/img/sub/shopping_loc2_on.png'/>"  alt="SETP2 주문/결제이미지"/></li>
							<li class="mmargin"><img src="<c:url value='/resources/img/sub/shopping_loc1_off.png'/>"  alt="SETP1 카트이미지"/></li>
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
									<th>상품명/옵션</th>
									<th>판매금액</th>
									<th>수량</th>
									<th>주문금액</th>
									<th>선택</th>
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
											<c:if test="${'NULL' ne odPrdInfo.prdOpColor}">/${odPrdInfo.prdOpColor}</c:if>
											<c:if test="${'NULL' eq odPrdInfo.prdOpSize}">/${odPrdInfo.prdOpSize}</c:if>
											
										</span>
									</td>
									<td>${odPrdInfo.sellPrice}</td>
									<td>
										<span class="input_text"><input type="text" id="buyCnt${i.index}" naem="buyCnt${i.index}" value="${odPrdInfo.buyCnt}" title="수량기입"><!-- <button class="btn_triangle1"></button> --></span>
										<span class="input_btn"><input type="button" value="수정" title="수정" onClick="location.href='editOrderBuycn.do?ordIdx=${i.index}&ord_unit_chk=${orderInfo.ord_unit_chk}&orderProductList[${i.index}].buyCnt='+document.getElementById('buyCnt${i.index}').value;"> <!-- <button class="btn_triangle2"></button> --></span>
									</td>
									<td>${odPrdInfo.totalPrice}</td>
									<td>
										<input type="button" value="삭제하기"class="btn_choice2" onClick="confirm_process('','해당 상품을 삭제하시겠습니까?','deleteCartList.do?prdCd=${odPrdInfo.prdCd}');" /> 
									</td>
								</tr>
								<tr>
									<td class="one_choice" colspan="6">
										상품가격 : ${odPrdInfo.totalPrice}원 + 배송비 : 0원 = 합계 ${odPrdInfo.totalPrice}원
									</td>
								</tr>
								</c:forEach>
								</c:when>
								<c:otherwise><tr><td>장바구니에 상품이 없습니다.</td></tr></c:otherwise>
								</c:choose>
								<tr>
									<c:set var="total"  value="0"/>
									<td class="total_choice" colspan="6">
										총 주문금액 : 
										<c:forEach items="${odPrdInfo}" var="odPrdInfo">
										${odPrdInfo.totalPrice}원 +
										<c:set var="total" value="${total+odPrdInfo.totalPrice}"/>
										</c:forEach>
										 배송비 : 0원 = 합계 <strong>${total}</strong>원
									</td>
								</tr>
								
							</tbody>
						</table>
						<h5>주문서 작성 및 결제</h5>
						<p class="sub_tit1">주문고객/배송지정보 입력</p>
						<p class="sub_tit2">< 주문하시는 분 ></p>
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
									<th>성명</th>
									<td class="in_text">
										<input type="text" name="customerInfo.custNm" id="customerInfo.custNm" title="성명" value="${cus.custNm}"/>
									</td>
									<th>전화번호</th>
									<td class="in_sectext">
										<select  name="customerInfo.telNo1" id="customerInfo.telNo1">
											<option <c:if test="${cus.telNo1}">selected</c:if>>02</option>
											<option <c:if test="${cus.telNo1}">selected</c:if>>031</option>
											<option <c:if test="${cus.telNo1}">selected</c:if>>032</option>
											<option <c:if test="${cus.telNo1}">selected</c:if>>033</option>
											<option <c:if test="${cus.telNo1}">selected</c:if>>041</option>
											<option <c:if test="${cus.telNo1}">selected</c:if>>042</option>
											<option <c:if test="${cus.telNo1}">selected</c:if>>043</option>
											<option <c:if test="${cus.telNo1}">selected</c:if>>051</option>
											<option <c:if test="${cus.telNo1}">selected</c:if>>052</option>
											<option <c:if test="${cus.telNo1}">selected</c:if>>053</option>
											<option <c:if test="${cus.telNo1}">selected</c:if>>054</option>
											<option <c:if test="${cus.telNo1}">selected</c:if>>061</option>
											<option <c:if test="${cus.telNo1}">selected</c:if>>062</option>
											<option <c:if test="${cus.telNo1}">selected</c:if>>063</option>
											<option <c:if test="${cus.telNo1}">selected</c:if>>064</option>
										</select>
										-<input type="text" title="전화번호" name="customerInfo.telNo2" id="customerInfo.telNo2" value="${cus.telNo2}" />-
										<input type="text" title="전화번호" name="customerInfo.telNo3" id="customerInfo.telNo3" value="${cus.telNo3}"/>
									</td>
								</tr>
								<tr>
									<th>휴대전화</th>
									<td class="in_sectext">
										<select  name="customerInfo.hpNo1" id="customerInfo.hpNo1">
											<option <c:if test="${cus.hpNo1}">selected</c:if>>010</option>
											<option <c:if test="${cus.hpNo1}">selected</c:if>>011</option>
											<option <c:if test="${cus.hpNo1}">selected</c:if>>017</option>
											<option <c:if test="${cus.hpNo1}">selected</c:if>>016</option>
											<option <c:if test="${cus.hpNo1}">selected</c:if>>019</option>
											<option <c:if test="${cus.hpNo1}">selected</c:if>>018</option>
										</select>
										-<input type="text"  name="customerInfo.hpNo2" id="customerInfo.hpNo2"title="휴대전화번호" value="${cus.hpNo2}"/>-
										<input type="text"  name="customerInfo.hpNo3" id="customerInfo.hpNo3" title="휴대전화번호" value="${cus.hpNo3}"/>
									</td>
									<th>이메일</th>
									<td class="in_text">
										<input type="text" name="customerInfo.custEmail" id="customerInfo.custEmail" title="이메일" value="${cus.custEmail}"/>
									</td>
								</tr>
							</tbody>
						</table>
						<p class="sub_tit2">< 받으시는 분 ></p>
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
									<th>배송지 선택</th>
									<td class="check_box">
										<input type="radio" id="new_check" name="choice"/><label for="new_check">새로입력</label>
										<input type="radio" id="basic_check" name="choice"/><label for="basic_check">기본배송지</label>
										<button><img src="<c:url value='/resources/img/./images/common/btn_checkbox.gif'/>" alt="내 배송지에서 선택이미지"/></button>
									</td>
									<th>받으시는분</th>
									<td class="in_text">
										<input type="text" id="reciInfo.reciNm" name="reciInfo.reciNm" title="받으시는분 성명 기입"/>
									</td>
								</tr>
								<tr>
									<th>전화번호</th>
									<td class="in_sectext">
										<select  id="reciInfo.phone1" name="reciInfo.phone1" >
											<option selected>02</option>
											<option>031</option>
											<option>032</option>
											<option>033</option>
											<option>041</option>
											<option>042</option>
											<option>043</option>
											<option>051</option>
											<option>052</option>
											<option>053</option>
											<option>054</option>
											<option>061</option>
											<option>062</option>
											<option>063</option>
											<option>064</option>
										</select>
										-<input type="text" id="reciInfo.phone2" name="reciInfo.phone2" title="전화번호"/>-
										<input type="text"  id="reciInfo.phone3" name="reciInfo.phone3" title="전화번호"/>
									</td>
									<th>휴대전화번호</th>
									<td class="in_sectext">
										<select  id="reciInfo.mobile1" name="reciInfo.mobile1"  >
											<option selected>010</option>
											<option>011</option>
											<option>017</option>
											<option>016</option>
											<option>019</option>
											<option>018</option>
										</select>
										-<input type="text" id="reciInfo.mobile2" name="reciInfo.mobile2" title="휴대전화번호"/>-
										<input type="text" id="reciInfo.mobile3" name="reciInfo.mobile3"  title="휴대전화번호"/>
									</td>
								</tr>
								<tr>
									<th>주소</th>
									<td colspan="3" class="address_box">
										<span class="adr_box1">
											<input type="text" title="우편번호"  id="reciInfo.zipCd1" name="reciInfo.zipCd1" />
											<input type="button" value="우편번호 찾기"onClick="openWin('/user/searchZipCode.do','searchZipForm',600,450,'scrollbars=no');"/><br/>
										</span>
										<span class="adr_box2">
											<input type="text" title="자동주소" id="reciInfo.add1" name="reciInfo.add1" />
										</span>
									</td>
								</tr>
								<tr>
									<th>배송시 요청사항</th>
									<td colspan="3" class="arrive_box">
										<input type="text" id="reciInfo.reciReq" name="reciInfo.reciReq" title="배송시 요청사항 기입"/>
									</td>
								</tr>
							</tbody>
						</table>
						<p class="adr_check">
							<input type="checkbox" id="adr_check" />
							<label for="adr_check">희망배송지 추가(상기 입력된 배송지 정보를 내 배송지 목록에 추가합니다.)</label>
						</p>
						<div class="point_employ1">
							<p class="sub_tit1">적립금/포인트 사용하기</p>
							<table class="employ_tbl" summary="고객포인트표">
								<caption>포인트 목록표</caption>
								<colgroup>
									<col width="30%"/>
									<col width="30%"/>
									<col width="40%"/>
								</colgroup>
								<thead>
									<tr>
										<th class="rb_line">구분</th>
										<th class="rb_line">보유금액</th>
										<th>사용금액</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<th class="rb_line">기본급</th>
										<td class="rb_line">0원</td>
										<td class="in_text">
											<input type="text"/><label>원</label>
										</td>
									</tr>
								</tbody>
							</table>
							<p class="sub_tit1">결제 수단 선택</p>
							<span class="payment1">
								<input type="radio" id="credit_card" name="payment1"/><label for="credit_card">신용카드</label>
								<input type="radio" id="account_transfer" name="payment1"/><label for="account_transfer">실시간 계좌이체</label>
							</span>
							<span class="payment2">
								<input type="radio" id="welfare_card" name="payment2"/><label for="welfare_card">복지 카드 포인트 사용안함</label>
								<input type="radio" id="welfare_ncard" name="payment2"/><label for="welfare_ncard">복지 카드 포인트 사용</label>
							</span>
						</div>
						<div class="point_employ2">
							<p class="sub_tit1">적립금/포인트 사용하기</p>
							<table class="employ_tbl" summary="고객포인트표">
								<caption>포인트 목록표</caption>
								<colgroup>
									<col width="20%"/>
									<col width="20%"/>
									<col width="40%"/>
								</colgroup>
								<thead>
									<tr>
										<th class="rb_line">총 주문금액</th>
										<th colspan="2"></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<th class="rb_line">포인트</th>
										<td class="rb_line">기본급</td>
										<td>0원</td>
									</tr>
									<tr>
										<td colspan="2" class="tbb_line rb_line" style="text-align:center;">소계</td>
										<td class="tbb_line">0원</td>
									</tr>
									<tr>
										<td colspan="2" class="rb_line" style="text-align:center;">할인 및 포인트 차감 후 결제 금액</td>
										<td>0원</td>
									</tr>
								</tbody>
							</table>
							<p class="sub_tit3">< 주문동의 ></p>
							<span class="complete">
								주문하시려는 상품에 대한 수량, 가격, 배송정보 등에 대하여 확인하였으며,
								이에 동의하십니까?
							</span>
							<p class="agree_check">
							<input type="checkbox" id="agr_check" />
							<label for="agr_check">동의합니다.</label>
						</p>
						</div>
					</div>
					<div class="complet_area">
						<span class="btn_complete">
							<input type="submit" value="결제하기" title="결제버튼"/>
						</span>
						<span class="btn_cancle">
							<input type="reset" value="취소하기" title="취소버튼"/>
						</span>
					</div>
				</div>
			</form>
		</div>
	</div>
<!--  container 끝   -->	

	<div class="footer">
		<div class="footer_area">
			<h2><img src="<c:url value='/resources/img/common/footer_logo.jpg'/>"  alt="현대 로고"/></h2>
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