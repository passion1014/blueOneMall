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
					<p class="sub_tit">나의 적립금 사용내역을 확인 할 수 있습니다.</p>
					<div class="mypage_step">
						<h5>적립금 사용내역</h5>
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
							<li class="cupon_tb">
								<span class="tbl_tit">검색결과</span><span class="st_color">0건</span>
								<table class="cupon_tbl" summary="쿠폰사용목록표">
									<caption>쿠폰사용표</caption>
									<colgroup>
										<col width="10%"/>
										<col width="20%"/>
										<col width="50%"/>
										<col width="20%"/>
									</colgroup>
									<thead>
										<tr>
											<th>NO</th>
											<th>사용일</th>
											<th>내용</th>
											<th>사용적립금</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<th></th>
											<td></td>
											<td>최근에 적립 내역이 없습니다.</td>
											<td></td>
										</tr>
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