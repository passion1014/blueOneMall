<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="imagetoolbar" content="no" />
<title> ###### 현대프로모션몰 ######</title>
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/common.css'/>" />
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/sub.css'/>" />
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/custom-theme/jquery-ui-1.8.16.custom.css'/>" />

<script type="text/javascript" src="<c:url value='/resources/js/js_ajax.js'/>"> </script>
<script type="text/javascript" src="<c:url value='/resources/js/js_common.js'/>"> </script>
<script type="text/javascript" src="<c:url value='/resources/js/js_admin.js'/>"> </script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery-1.4.3.min.js'/>"> </script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery-1.7.2.min.js'/>"> </script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery-ui-1.8.16.custom.min.js'/>"> </script>

<!--[if lt IE 9]>
<script src="js/jquery_html5shiv.js"></script>
<![endif]-->
</head>

<body>
<div class="wrap">
	<div class="header">
		<div class="top">
			<h1><a href="#"><img src="<c:url value='/resources/img/common/blueone_logo.png'/>" alt="blueone logo"/></a></h1>
			<p class="top_img">
				<img src="<c:url value='/resources/img/common/top_image.jpg'/>" alt="top image"/>
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
			<h3>CATEGORY</h3>
			<ul class="lnb_list">
				<li><a href="#">Multimedia(MM)</a></li>
				<li><a href="#">Amp</a></li>
				<div class="onclick_list">
					<a href="#">인디앰프</a>
					<a href="#">프리앰프</a>
					<a href="#">파워앰프</a>
					<a href="#">올인원앰프/시스템</a>
					<a href="#">포노앰프</a>
					<a href="#">홈시어터앰프</a>
				</div>
				<li><a href="#">Speaker</a></li>
				<div class="onclick_list">
					<a href="#">Hi-Fi Collecion</a>
					<a href="#">톨보이스커</a>
					<a href="#">북셀프스피커</a>
					<a href="#">멀티채널스피커</a>
					<a href="#">사운드바</a>
				</div>
			</ul>
		</div>
		<div class="sub_content">
			<div class="slide_section">
				<img src="<c:url value='/resources/img/sub/sub_titleimg1.jpg'/>" alt="서브 메인이미지"/>
			</div>
			<div class="cont_section">
				<ul class="cont_list">
					<li><a href="#" class="on">분류1</a></li>
					<li><a href="#" class="leftline">분류2</a></li>
					<li><a href="#" class="leftline">분류3</a></li>
					<li><a href="#" class="leftline">분류4</a></li>
				</ul>
				<div class="product_section">
					<p class="pro_total">
						총&nbsp;<span>700</span>&nbsp;중&nbsp;<span>100</span>&nbsp;개
					</p>
					<span class="pro_class">
						<a href="#" class="rightline">낮은 가격</a>
						<a href="#" class="rightline">높은 가격</a>
						<a href="#" class="rightline">제품명</a>
						<a href="#">제조사순</a>
					</span>
					<ul class="product_list">
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="<c:url value='/resources/img/sub/sub_proimg1.jpg'/>" alt="product image"/></dd>
								<dd>[Sonus Faber│소너스 파베르]</dd>
								<dd>BUS AG USB 케이블</dd>
								<dd>510,000&nbsp;↓&nbsp;<span>200,000원</span></dd>
							</dl>
						</a></li>
						<li class="mlalign"><a href="#">
							<dl class="list_product">
								<dd><img src="<c:url value='/resources/img/sub/sub_proimg2.jpg'/>" alt="product image"/></dd>
								<dd>[Sonus Faber│소너스 파베르]</dd>
								<dd>BUS AG USB 케이블</dd>
								<dd>510,000&nbsp;↓&nbsp;<span>200,000원</span></dd>
							</dl>
						</a></li>
						<li class="mlalign"><a href="#">
							<dl class="list_product">
								<dd><img src="<c:url value='/resources/img/sub/sub_proimg3.jpg'/>" alt="product image"/></dd>
								<dd>[Sonus Faber│소너스 파베르]</dd>
								<dd>BUS AG USB 케이블</dd>
								<dd>510,000&nbsp;↓&nbsp;<span>200,000원</span></dd>
							</dl>
						</a></li>
						<li class="mlalign"><a href="#">
							<dl class="list_product">
								<dd><img src="<c:url value='/resources/img/sub/sub_proimg4.jpg'/>" alt="product image"/></dd>
								<dd>[Sonus Faber│소너스 파베르]</dd>
								<dd>BUS AG USB 케이블</dd>
								<dd>510,000&nbsp;↓&nbsp;<span>200,000원</span></dd>
							</dl>
						</a></li>
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="<c:url value='/resources/img/sub/sub_proimg1.jpg'/>" alt="product image"/></dd>
								<dd>[Sonus Faber│소너스 파베르]</dd>
								<dd>BUS AG USB 케이블</dd>
								<dd>510,000&nbsp;↓&nbsp;<span>200,000원</span></dd>
							</dl>
						</a></li>
						<li class="mlalign"><a href="#">
							<dl class="list_product">
								<dd><img src="<c:url value='/resources/img/sub/sub_proimg2.jpg'/>" alt="product image"/></dd>
								<dd>[Sonus Faber│소너스 파베르]</dd>
								<dd>BUS AG USB 케이블</dd>
								<dd>510,000&nbsp;↓&nbsp;<span>200,000원</span></dd>
							</dl>
						</a></li>
						<li class="mlalign"><a href="#">
							<dl class="list_product">
								<dd><img src="<c:url value='/resources/img/sub/sub_proimg3.jpg'/>" alt="product image"/></dd>
								<dd>[Sonus Faber│소너스 파베르]</dd>
								<dd>BUS AG USB 케이블</dd>
								<dd>510,000&nbsp;↓&nbsp;<span>200,000원</span></dd>
							</dl>
						</a></li>
						<li class="mlalign"><a href="#">
							<dl class="list_product">
								<dd><img src="<c:url value='/resources/img/sub/sub_proimg4.jpg'/>" alt="product image"/></dd>
								<dd>[Sonus Faber│소너스 파베르]</dd>
								<dd>BUS AG USB 케이블</dd>
								<dd>510,000&nbsp;↓&nbsp;<span>200,000원</span></dd>
							</dl>
						</a></li>
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="<c:url value='/resources/img/sub/sub_proimg1.jpg'/>" alt="product image"/></dd>
								<dd>[Sonus Faber│소너스 파베르]</dd>
								<dd>BUS AG USB 케이블</dd>
								<dd>510,000&nbsp;↓&nbsp;<span>200,000원</span></dd>
							</dl>
						</a></li>
						<li class="mlalign"><a href="#">
							<dl class="list_product">
								<dd><img src="<c:url value='/resources/img/sub/sub_proimg2.jpg'/>" alt="product image"/></dd>
								<dd>[Sonus Faber│소너스 파베르]</dd>
								<dd>BUS AG USB 케이블</dd>
								<dd>510,000&nbsp;↓&nbsp;<span>200,000원</span></dd>
							</dl>
						</a></li>
						<li class="mlalign"><a href="#">
							<dl class="list_product">
								<dd><img src="<c:url value='/resources/img/sub/sub_proimg3.jpg'/>" alt="product image"/></dd>
								<dd>[Sonus Faber│소너스 파베르]</dd>
								<dd>BUS AG USB 케이블</dd>
								<dd>510,000&nbsp;↓&nbsp;<span>200,000원</span></dd>
							</dl>
						</a></li>
						<li class="mlalign"><a href="#">
							<dl class="list_product">
								<dd><img src="<c:url value='/resources/img/sub/sub_proimg4.jpg'/>" alt="product image"/></dd>
								<dd>[Sonus Faber│소너스 파베르]</dd>
								<dd>BUS AG USB 케이블</dd>
								<dd>510,000&nbsp;↓&nbsp;<span>200,000원</span></dd>
							</dl>
						</a></li>
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="<c:url value='/resources/img/sub/sub_proimg1.jpg'/>" alt="product image"/></dd>
								<dd>[Sonus Faber│소너스 파베르]</dd>
								<dd>BUS AG USB 케이블</dd>
								<dd>510,000&nbsp;↓&nbsp;<span>200,000원</span></dd>
							</dl>
						</a></li>
						<li class="mlalign"><a href="#">
							<dl class="list_product">
								<dd><img src="<c:url value='/resources/img/sub/sub_proimg2.jpg'/>" alt="product image"/></dd>
								<dd>[Sonus Faber│소너스 파베르]</dd>
								<dd>BUS AG USB 케이블</dd>
								<dd>510,000&nbsp;↓&nbsp;<span>200,000원</span></dd>
							</dl>
						</a></li>
						<li class="mlalign"><a href="#">
							<dl class="list_product">
								<dd><img src="<c:url value='/resources/img/sub/sub_proimg3.jpg'/>" alt="product image"/></dd>
								<dd>[Sonus Faber│소너스 파베르]</dd>
								<dd>BUS AG USB 케이블</dd>
								<dd>510,000&nbsp;↓&nbsp;<span>200,000원</span></dd>
							</dl>
						</a></li>
						<li class="mlalign"><a href="#">
							<dl class="list_product">
								<dd><img src="<c:url value='/resources/img/sub/sub_proimg4.jpg'/>" alt="product image"/></dd>
								<dd>[Sonus Faber│소너스 파베르]</dd>
								<dd>BUS AG USB 케이블</dd>
								<dd>510,000&nbsp;↓&nbsp;<span>200,000원</span></dd>
							</dl>
						</a></li>
					</ul>
					<div class="paging">
						<a href="#" class="palign1"><img src="<c:url value='/resources/img/common/btn_first.gif'/>" alt="처음으로"></a>
						<a href="#" class="palign2"><img src="<c:url value='/resources/img/common/btn_prev.gif'/>" alt="이전"></a>
						<a href="#" class="on">1</a>
						<a href="#">2</a>
						<a href="#">3</a>
						<a href="#">4</a>
						<a href="#">5</a>
						<a href="#">6</a>
						<a href="#">7</a>
						<a href="#">8</a>
						<a href="#">9</a>
						<a href="#">10</a>
						<a href="#" class="palign1"><img src="<c:url value='/resources/img/common/btn_next.gif'/>" alt="다음"></a>
						<a href="#" class="palign2"><img src="<c:url value='/resources/img/common/btn_end.gif'/>" alt="끝으로"></a>
					</div>
				</div>
			</div>
		</div>
	</div>
<!--  container 끝   -->	

	<div class="footer">
		<div class="footer_area">
			<h2><img src="<c:url value='/resources/img/common/footer_logo.jpg'/>" alt="현대 로고"/></h2>
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
