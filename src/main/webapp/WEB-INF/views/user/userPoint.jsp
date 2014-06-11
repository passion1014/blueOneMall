<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<c:import  url="../inc/topSub.jsp" />
<html>
<body>
	<div class="wrap">
	<!--  header 시작   -->
	<c:import url="../inc/header.jsp" />
	<!--  header 끝   -->

	<div class="container">
		
		<c:import url="../inc/memberLnb.jsp" />
		
		<div class="sub_content">
			<form action="#" method="post">
				<div class="mypage_section">
					<h4>사용내역조회</h4>
					<p class="sub_tit">나의 포인트 사용내역을 확인 할 수 있습니다.</p>
					<div class="mypage_step">
						<h5>포인트 사용내역</h5>
						<ul>
							<li class="datebox">
								<span class="tit">주문일자</span>
								<span>
										<input type="text" title="날짜박스" value="2014-02-13"/>
										<a href=""><img src="<c:url value='/resources/img/common/btn_date.gif'/>" alt="날짜체크박스"/></a>&nbsp; ~ &nbsp;
										<input type="text" title="날짜박스" value="2014-02-14"/>
										<a href=""><img src="<c:url value='/resources/img/common/btn_date.gif'/>"  alt="날짜체크박스"/></a>
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
								<table class="cupon_tbl" summary="포인트내역관리표">
									<caption>포인트내역관리목록표</caption>
									<colgroup>
										<col width="5%"/>
										<col width="15%"/>
										<col width="20%"/>
										<col width="20%"/>
										<col width="15%"/>
										<col width="10%"/>
										<col width="10%"/>
										<col width="20%"/>
									</colgroup>
									<thead>
										<tr>
											<th>No</th>
											<th>결제일</th>
											<th>주문번호</th>
											<th>결제내역</th>
											<th>결제종류</th>
											<th>결제금액</th>
											<th>사용 포인트</th>
											<!-- <th>복지 포인트</th> -->
										</tr>
									</thead>
									<tbody>
									
									 <c:forEach var="ordList" items="${ordList}">
										<tr>
											
											<td>${ordList.paymentInfo.idx}</td>
											<td>${ordList.paymentInfo.regDate.substring(0,10)}</td>
											<td>${ordList.orderNo}</td>
											<td>${ordList.ordPrd.prdNm}</td>
											<td>
												<c:if test="${ordList.paymentInfo.payMdCd eq '100000000000'}">신용카드</c:if>
												<c:if test="${ordList.paymentInfo.payMdCd eq '010000000000'}">계좌이체</c:if>
												<c:if test="${ordList.paymentInfo.payMdCd eq '000100000000'}">포인트</c:if>
											</td>
											<td>${ordList.totalOrderPrice}원</td>
											<td>${ordList.paymentInfo.payPoint}원</td>
										
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

	<c:import url="../inc/footer.jsp"/>
</div>
</body>
</html>