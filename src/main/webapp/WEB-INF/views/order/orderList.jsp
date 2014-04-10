<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
    
    
<c:import  url="../inc/topSub.jsp" />
<c:import  url="../inc/topMain.jsp" /> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="wrap">
	<c:import url="../inc/header.jsp" />
<!--  header 끝   -->

	<div class="container">
		<c:import url="../inc/userLnb.jsp" />
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
									<a href=""><img src="./images/common/btn_date.gif" alt="날짜체크박스"/></a>&nbsp; ~ &nbsp;
									<input type="text" title="날짜박스" value="2014-02-14"/>
									<a href=""><img src="./images/common/btn_date.gif" alt="날짜체크박스"/></a>
									<button class="titext">당일</button>
									<button>1개월</button>
									<button>3개월</button>
									<button>6개월</button>
									<button class="btn">조회하기</button>
								</span>
							</li>
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
										<tr>
											<th>2014-00-00</th>
											<td>12345678912345678</td>
											<td>[JBL]Flip (블루투스 휴대용 스피커 플립)
													-01.FLIP_블랙
											</td>
											<td>99,000원</td>
											<td>취소</td>
											<td>배송대기</td>
										</tr>
									</tbody>
								</table>
							</li>
							<li class="paging2">
								<a href="#" class="palign1"><img src="<c:url value='/resources/img/common/btn_first.gif'/>"  alt="처음으로"></a>
								<a href="#" class="palign2"><img src="<c:url value='/resources/img/common/btn_prev.gif'/>"  alt="이전"></a>
								<a href="#" class="on">1</a>
								<a href="#" class="palign1"><img src="<c:url value='/resources/img/common/btn_next.gif'/>"  alt="다음"></a>
								<a href="#" class="palign2"><img src="<c:url value='/resources/img/common/btn_end.gif'/>"  alt="끝으로"></a>
							</li>
						</ul>
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