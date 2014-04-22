<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    


<c:import  url="../inc/topSub.jsp" />
<c:import  url="../inc/topMain.jsp" />     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
	$(document).ready(function() {
		document.cookie;
	});
</script>

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
			<form action="#" method="post">
				<div class="porder_section">
					<h4>제품주문</h4>
					<div class="porder_step">
						<ul>
							<li><img src="<c:url value='/resources/img/sub/shopping_loc3_off.png'/>"  alt="SETP3 주문완료이미지"/></li>
							<li class="mmargin"><img src="<c:url value='/resources/img/sub/shopping_loc2_off.png'/>"  alt="SETP2 주문/결제이미지"/></li>
							<li class="mmargin"><img src="<c:url value='/resources/img/sub/shopping_loc1_on.png'/>"  alt="SETP1 카트이미지"/></li>
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
								<c:choose>
									<c:when test="${odPrdInfo.size() != 0}">
										<c:forEach items="${odPrdInfo}" var="odPrdInfo">
								<tr>
									<th>
										<input type="checkbox" title="상품선택"/>
									</th>
									<td class="product_area">
										<span>
										<img src="${odPrdInfo.prdSmallImg}"  alt="상품이미지"/></span>
										<span>
											${odPrdInfo.prdNm}
											<c:if test="${'NULL' ne odPrdInfo.prdOpColor}">/${odPrdInfo.prdOpColor}</c:if>
											<c:if test="${'NULL' eq odPrdInfo.prdOpSize}">/${odPrdInfo.prdOpSize}</c:if>
											
										</span>
									</td>
									<td>${odPrdInfo.sellPrice}</td>
									<td>
										<span class="input_text"><input type="text" value="${odPrdInfo.buyCnt}" title="수량기입"><button class="btn_triangle1"></button></span>
										<span class="input_btn"><input type="button" value="수정" title="수정"><button class="btn_triangle2"></button></span>
									</td>
									<td>>${odPrdInfo.totalPrice}</td>
									<td>
										<button class="btn_choice1">구매하기</button>
										<!-- <button class="btn_choice2" onClick="confirm_process('','해당 상품을 삭제하시겠습니까?','deleteCartList.do?prdCd=${odPrdInfo.prdCd}');" >삭제하기</button> -->
										<a href="deleteCartList.do?prdCd=${odPrdInfo.prdCd}">[삭제하기]</a>
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
								</c:forEach>
								</c:when>
								<c:otherwise><tr><td>장바구니에 상품이 없습니다.</td></tr></c:otherwise>
								</c:choose>
		
							</tbody>
						</table>
						<span class="btn_bottom1">
							<button>선택상품삭제</button>
							<button>단품/품절 상품삭제</button>
						</span>
						<span class="btn_bottom2">
							<button class="btn_boximg1" onClick="location.href='productList.do'">쇼핑계속</button>
							<button class="btn_boximg2">선택상품주문</button>
							<button class="btn_boximg3">전체상품주문</button>
						</span>
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