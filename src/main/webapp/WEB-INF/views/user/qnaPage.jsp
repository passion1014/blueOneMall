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
	<div class="header">
		<div class="top">
			<h1><a href="#"><img src="./images/common/blueone_logo.png" alt="blueone logo"/></a></h1>
			<p class="top_img">
				<img src="./images/common/top_image.jpg" alt="top image"/>
			</p>
			<form action="#" method="post">
				<div class="search_area">
					<span class="search_menu">
						<a href="#" class="menu_site">장바구니</a>
						<a href="#" class="menu_site">주문배송 조회</a>
						<a href="#">마이페이지</a>
					</span>
					<span class="search_box">
						<input type="text" title="서치텍스트박스"/>
						<button></button>
					</span>
				</div>
			</form>
		</div>
		<div class="gnb">
			<ul>
				<li><a href="#">BLUETOOTH</a></li>
				<li class="gnb_list"><a href="#">EARPHONE</a></li>
				<li class="gnb_list"><a href="#">HEADPHONE</a></li>
				<li class="gnb_list"><a href="#">MULTMEDIA</a></li>
				<li class="gnb_list"><a href="#">CASE</a></li>
				<li class="gnb_list"><a href="#">XTC</a></li>
				<li class="gnb_list"><a href="#">SALE</a></li>
				<li class="gnb_list"><a href="#">BARND SHOP</a></li>
			</ul>
		</div>
	</div>
<!--  header 끝   -->

	<div class="container">
		<div class="lnb">
			<h3>고객센터</h3>
			<ul class="lnb_list">
				<li><a href="#">공지사항</a></li>
				<li><a href="#">FAQ</a></li>
				<li><a href="#">1:1 문의하기</a></li>
			</ul>
		</div>
		<div class="sub_content">
			<form action="#" method="post">
				<div class="customer_section">
					<h4>1:1 문의하기</h4>
					<p class="sub_tit">쇼핑몰 이용중 의문사항이나 불편한 점을 문의해주세요.</p>
					<div class="inquiry_area">
						<ul>
							<li class="inquiry_tit">
								<span class="textbox">전일 17시 이후 ~ 당일 17시 까지 문의해 주신 경우 당일 18시 이내 <br/>
								당일 17시 이후 문의해 주신 경우 익을 9시 이후에 신속하게 답변처리해 드리겠습니다.</span>
								<span class="btn">
								<button>상담하기</button>
								</span>
							</li>
							<li>
								<ol class="datebox">
									<li class="datebox_day">
										<span class="tit">등록일</span>
										<span>
											<input type="text" title="날짜박스" value="2014-02-13"/>
											<a href=""><img src="./images/common/btn_date.gif" alt="날짜체크박스"/></a>&nbsp; ~ &nbsp;
											<input type="text" title="날짜박스" value="2014-02-14"/>
											<a href=""><img src="./images/common/btn_date.gif" alt="날짜체크박스"/></a>
											<button class="tit">당일</button>
											<button>1개월</button>
											<button>3개월</button>
											<button>6개월</button>
											<button class="btn">조회하기</button>
										</span>
									</li>
									<li class="datebox_search">
										<span class="tit">검색</span>
										<span>
											<select>
												<option>전체</option>
											</select>
											<input type="text" title="searchbox" />
											<button class="btn">조회하기</button>
										</span>
									</li>
								</ol>
							</li>
							<li>
								<span class="tbl_tit">검색결과</span><span class="st_color">0건</span>
								<table class="inquiry_tbl" summary="문의하기목록표">
									<caption>문의하기목록</caption>
									<colgroup>
										<col width="20%"/>
										<col width="40%"/>
										<col width="20%"/>
										<col width="20%"/>
									</colgroup>
									<thead>
										<tr>
											<th>상담유형</th>
											<th>제목</th>
											<th>문의일자</th>
											<th>처리현황</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<th></th>
											<td>검색결과가 없습니다.</td>
											<td></td>
											<td></td>
										</tr>
									</tbody>
								</table>
							</li>
						</ul>
						<div class="paging2">
							<a href="#" class="palign1"><img src="./images/common/btn_first.gif" alt="처음으로"></a>
							<a href="#" class="palign2"><img src="./images/common/btn_prev.gif" alt="이전"></a>
							<a href="#" class="on">1</a>
							<a href="#" class="palign1"><img src="./images/common/btn_next.gif" alt="다음"></a>
							<a href="#" class="palign2"><img src="./images/common/btn_end.gif" alt="끝으로"></a>
						</div>
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