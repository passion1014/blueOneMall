<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    


<c:import  url="../inc/topSub.jsp" />
<c:import  url="../inc/topMain.jsp" />     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
<!--
function list_Submit(){
	msg = "선택하신 상품을 구매하시겠습니까?" ;
	document.getElementById("odfrm").action = "orderRegister.do" ;
	
	var chk_num = document.odfrm.elements.length;
	
	for(var i = 0; i < chk_num; i++){
		var checkbox_obj = eval("document.odfrm.elements["+i+"]");
		if(checkbox_obj.checked == true)	break;
	}

	if(i == chk_num) {
		alert("먼저 상품을 선택하여 주십시오");
		return false;
	} else {
		if(confirm(msg)){
			document.getElementById("odfrm").submit() ;
			return false;
		}
	}

}

function allChk(obj){
    var chkObj = document.getElementsByName("ord_unit_chk");
    var rowCnt = chkObj.length - 1;
    var check = obj.checked;
    
    if (check) {﻿
        for (var i=0; i<=rowCnt; i++){
         if(chkObj[i].type == "checkbox")
             chkObj[i].checked = true; 
        }
    } else {
        for (var i=0; i<=rowCnt; i++) {
         if(chkObj[i].type == "checkbox"){
             chkObj[i].checked = false; 
         }
        }
    }
} 

function all_Submit(){
	var chkObj = document.getElementsByName("ord_unit_chk");
	var rowCnt = chkObj.length - 1;

	for (var i=0; i<=rowCnt; i++) {
		if(chkObj[i].type == "checkbox") chkObj[i].checked = true; 
	}
	
	list_Submit();
}

function cnt_UP(){
	var chkObj = document.getElementsByName("ord_unit_chk");
	var rowCnt = chkObj.length - 1;
	    ﻿
	for (var i=0; i<=rowCnt; i++) {
		if(chkObj[i].type == "checkbox") chkObj[i].checked = true; 
	}

	list_Submit();
}

//-->
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
			<form action="orderRegister.do"  id="odfrm" name="odfrm" >
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
										<input type="checkbox" name="ord_multi_chk" onClick="allChk(this);" title="전체상품선택"/>
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
										<input type="checkbox" id="ord_unit_chk" name="ord_unit_chk"  value="${odPrdInfo.cookieKey}" title="상품선택"/>
									</th>
									<td class="product_area leftalign">
										<span>
										<img src="${odPrdInfo.prdSmallImg}"  alt="상품이미지"  width="71" height="71"></span>
										<span>
											${odPrdInfo.prdNm}
											<c:if test="${'NULL' ne odPrdInfo.prdOpColor}">/${odPrdInfo.prdOpColor}</c:if>
											<c:if test="${'NULL' ne odPrdInfo.prdOpSize}">/${odPrdInfo.prdOpSize}</c:if>
											
										</span>
									</td>
									<td>${odPrdInfo.sellPrice}</td>
									<td>
										<span class="input_text"><input type="text" value="${odPrdInfo.buyCnt}" id="buyCnt" nam="buyCnt" title="수량기입"><!-- <button class="btn_triangle1" ></button> --></span>
										<span class="input_btn"><input type="button" value="수정" title="수정" onClick="location.href='editBuyCnt.do?cookieKey=${odPrdInfo.cookieKey}&buyCnt='+document.getElementById('buyCnt').value;"><!-- <button class="btn_triangle2"></button> --></span>
									</td>
									<td>${odPrdInfo.totalPrice}</td>
									<td>
										<input type="button" value="구매하기 "class="btn_choice1" onClick="location.href='orderDirect.do?cookieKey=${odPrdInfo.cookieKey}&prdCd=${odPrdInfo.prdCd}&buyCnt=${odPrdInfo.buyCnt}';"/>
										<input type="button" value="삭제하기" class="btn_choice2" onClick="confirm_process('','해당 상품을 삭제하시겠습니까?','deleteCartList.do?cookieKey=${odPrdInfo.cookieKey}');" /> 
									</td>
								</tr>
								<tr>
									<td class="one_choice" colspan="6">
										<c:set var="totalPrc" value="${odPrdInfo.totalPrice.intValue()}"/>
										상품가격 : ${odPrdInfo.totalPrice}원  + <c:if test="${totalPrc>=config.buyPrice}">배송비 : ${config.trasferPrice}원</c:if>
																			   <c:if test="${totalPrc<config.buyPrice}">배송비 : 0원 </c:if>= 합계 ${odPrdInfo.totalPrice}원
									 </td>
								</tr>
								</c:forEach>
								</c:when>
								<c:otherwise><tr><td colspan="6" height="100">장바구니에 상품이 없습니다.</td></tr></c:otherwise>
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
									</td>
								</tr>
								
								
		
							</tbody>
						</table>
						<span class="btn_bottom1">
							<button>선택상품삭제</button>
							<button>단품/품절 상품삭제</button>
						</span>
						<span class="btn_bottom2">
							<input type="button" class="btn_boximg1" value="쇼핑계속" onClick="location.href='/product/productList.do'"/>
							<input type="button" class="btn_boximg2" value="선택상품주문" onClick="list_Submit()"/>
	
							<input type="button" class="btn_boximg3" value="전체상품주문" onClick="all_Submit();"/>
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