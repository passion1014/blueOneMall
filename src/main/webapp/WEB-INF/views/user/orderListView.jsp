<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<c:import  url="../inc/topSub.jsp" />

<script language="JavaScript" type="text/JavaScript">
<!--
$(document).ready(function() {
	var dates = $("#srchStdDt,#srchEdDt").datepicker({
		changeYear: true,
		changeMonth: true,
		dateFormat: "yy-mm-dd",
		showMonthAfterYear: true,
		onSelect: function(selectedDate) {
			var option = this.id == "srchStdDt" ? "minDate": "maxDate",
			instance = $(this).data("datepicker"),
			date = $.datepicker.parseDate(instance.settings.dateFormat || $.datepicker._defaults.dateFormat, selectedDate, instance.settings);
			dates.not(this).datepicker("option", option, date);
		}
	});

});
//-->
</script>

<body>
	<div class="wrap">
	<!--  header 시작   -->
	<c:import url="../inc/header.jsp" />
	<!--  header 끝   -->

	<div class="container">
		
		<c:import url="../inc/memberLnb.jsp" />
		
		<div class="sub_content">
			<form action="/user/orderListView.do" method="post">
				<div class="mypage_section">
					<h4>주문내역관리</h4>
					<p class="sub_tit">나의 주문내역을 확인 하실 수 있습니다.</p>
					<div class="mypage_step">
						<ul>
							<li class="datebox">
								<span class="tit">주문일자</span>
								<span>
									<input type="text" title="날짜박스" id="srchStdDt" name="srchStdDt" value="${srchStdDt}"/>
									<!--<a><img src="<c:url value='/resources/img/common/btn_date.gif'/>" alt="날짜체크박스" onClick="dateDisable();"/></a>-->&nbsp; ~ &nbsp;
									<input type="text" title="날짜박스" id="srchEdDt" name="srchEdDt"  value="${srchEdDt}"/>
									<!--<a><img src="<c:url value='/resources/img/common/btn_date.gif'/>" alt="날짜체크박스" onClick="dateDisable();"/></a>-->
									<button class="titext">당일</button>
									<button>1개월</button>
									<button>3개월</button>
									<button>6개월</button>
									<button class="btn">조회하기</button>
								</span>
							</li>
							<c:choose>
							 <c:when test="${ordList.size() != 0}">
							<li class="cupon_tb">
								<span class="tbl_tit">검색결과</span><span class="st_color">${ordList.size()}건</span>
								<table class="cupon_tbl" summary="주문내역관리표">
									<caption>주문내역관리목록표</caption>
									<colgroup>
										<col width="15%"/>
										<col width="20%"/>
										<col width="30%"/>
										<col width="15%"/>
										<col width="10%"/>
										<col width="10%"/>
									</colgroup>
									<thead>
										<tr>
											<th>주문일자</th>
											<th>주문번호</th>
											<th>상품명</th>
											<th>주문금액</th>
											<th>주문/배송상태</th>
											<!--<th>배송상태</th>-->
										</tr>
									</thead>
									<tbody>
									
									 <c:forEach var="ordList" items="${ordList}">
										<tr>
											
											<td>${ordList.regDate}</td>
											<td>${ordList.orderNo}</td>
											<td><a href="orderDetail.do?orderNo=${ordList.orderNo}">${ordList.ordPrd.prdNm}</a>
											</td>
											<td>${ordList.totalOrderPrice} 원</td>
											<td>
												<c:if test="${ordList.orderStatCd eq '01'}">신청대기</c:if>
												<c:if test="${ordList.orderStatCd eq '02'}">결제완료</c:if>
												<c:if test="${ordList.orderStatCd eq '07'}">취소신청</c:if>
												<c:if test="${ordList.orderStatCd eq '08'}">취소완료</c:if>
												<c:if test="${ordList.orderStatCd eq '03'}">배송준비</c:if>
												<c:if test="${ordList.orderStatCd eq '04'}">배송중</c:if>
												<c:if test="${ordList.orderStatCd eq '05'}">배송완료</c:if>
												<c:if test="${ordList.orderStatCd eq '09'}">반품신청</c:if>
												<c:if test="${ordList.orderStatCd eq '10'}">반품신청완료</c:if>
												
											</td>
											
										</tr>
										
									  </c:forEach>
									 
									
									</tbody>
								</table>
							</li>
							<li class="paging2">
								<a href="#" class="palign1"><img src="<c:url value='/resources/img/common/btn_first.gif'/>" alt="처음으로"></a>
								<a href="#" class="palign2"><img src="<c:url value='/resources/img/common/btn_prev.gif'/>" alt="이전"></a>
								<a href="#" class="on">1</a>
								<a href="#" class="palign1"><img src="<c:url value='/resources/img/common/btn_next.gif'/>" alt="다음"></a>
								<a href="#" class="palign2"><img src="<c:url value='/resources/img/common/btn_end.gif'/>" alt="끝으로"></a>
							</li>
							</c:when>
							 <c:otherwise>
								<br><h5>주문하신상품이 없습니다.</h5>
							 </c:otherwise>
							</c:choose>
						</ul>
					</div>
				</div>
			</form>
		</div>
	</div>
<!--  container 끝   -->	

<c:import url="../inc/footer.jsp" />
