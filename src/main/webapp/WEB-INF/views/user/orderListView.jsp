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
					<h4>주문내역관리</h4>
					<p class="sub_tit">나의 주문내역을 확인 하실 수 있습니다.</p>
					<div class="mypage_step">
						<ul>
							<li class="datebox">
								<span class="tit">주문일자</span>
								<span>
									<input type="text" title="날짜박스" value="2014-02-13"/>
									<a href=""><img src="<c:url value='/resources/img/common/btn_date.gif'/>" alt="날짜체크박스"/></a>&nbsp; ~ &nbsp;
									<input type="text" title="날짜박스" value="2014-02-14"/>
									<a href=""><img src="<c:url value='/resources/img/common/btn_date.gif'/>" alt="날짜체크박스"/></a>
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
								<span class="tbl_tit">검색결과</span><span class="st_color">1건</span>
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
											<th>주문상태</th>
											<th>배송상태</th>
										</tr>
									</thead>
									<tbody>
									
									 <c:forEach var="ordList" items="${ordList}">
										<tr>
											<td>${ordList.regDate}</td>
											<td>${ordList.orderNo}</td>
											<td>${ordList.ordPrd.prdNm}
											</td>
											<td>${ordList.ordPrd.sellPrice}</td>
											<td>취소</td>
											<td>
												<c:if test="${ordList.orderStatCd eq '02'}">결제완료</c:if>
												<c:if test="${ordList.orderStatCd eq '03'}">배송중</c:if>
												<c:if test="${ordList.orderStatCd eq '04'}">배송완료</c:if>
												<c:if test="${ordList.orderStatCd eq '05'}">고객확인</c:if>
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
								주문하신상품이 없습니다.
							 </c:otherwise>
							</c:choose>
						</ul>
					</div>
				</div>
			</form>
		</div>
	</div>
<!--  container 끝   -->	

	<div class="footer">
		<div class="footer_area">
			<h2><img src="./images/common/footer_logo.jpg" alt="현대 로고"/></h2>
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
