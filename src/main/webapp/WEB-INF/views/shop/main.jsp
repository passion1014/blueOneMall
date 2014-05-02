<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import  url="../inc/topMain.jsp" />

<body>
<div class="wrap">
	<c:import  url="../inc/header.jsp" />
		
	<div class="container">
		<div class="cont_main">
		

	 		<div class="mcont">
				<ul class="bxslider">
					<li><a href="${AdImgDtl.mdUrl1}"><img src="${AdImgDtl.mdImg1}" alt="메인이미지1"/></a></li>
					<li><a href="${AdImgDtl.mdUrl2}"><img src="${AdImgDtl.mdImg2}" alt="메인이미지2"/></a></li>
					<li><a href="${AdImgDtl.mdUrl3}"><img src="${AdImgDtl.mdImg3}" alt="메인이미지3"/></a></li>
					<li><a href="${AdImgDtl.mdUrl4}"><img src="${AdImgDtl.mdImg4}" alt="메인이미지4"/></a></li>
					<li><a href="${AdImgDtl.mdUrl5}"><img src="${AdImgDtl.mdImg5}" alt="메인이미지5"/></a></li>
					<li><a href="${AdImgDtl.mdUrl6}"><img src="${AdImgDtl.mdImg6}" alt="메인이미지5"/></a></li>
							
				</ul>
				<div id="mcont_nav">
					<ol>
						<li><a data-slide-index="0" href="">main_visual1</a></li>
						<li><a data-slide-index="1" href="">main_visual2</a></li>
						<li><a data-slide-index="2" href="">main_visual3</a></li>
						<li><a data-slide-index="3" href="">main_visual4</a></li>
						<li><a data-slide-index="4" href="">main_visual5</a></li>
						<li><a data-slide-index="5" href="">main_visual6</a></li>
					</ol>
				</div>
			</div>

				<script type="text/javascript">
					$(document).ready(function(){
						$('.bxslider').bxSlider({
							pagerCustom: '#mcont_nav',
							auto: true,
							pause: 4000,
							autoHover: true,
							speed: 500,
							captions: false,
							prevText: '',
							nextText: ''
						});
					});
				</script>


			
			
			
			
			
			
			<dl class="scont1">
				<dd class="scont_box2"><a href="#"><img src="<c:url value='/resources/img/main/main/scont_img1.jpg'/>" alt="product image"/></a></dd>
				<dd class="scont_box1"><a href="#"><img src="<c:url value='/resources/img/main//main/scont_img2.jpg'/>" alt="product image"/></a></dd>
			</dl>
			<dl class="scont2">
				<dd class="scont_box1"><a href="#"><img src="<c:url value='/resources/img/main/main/scont_img3.jpg'/>" alt="product image"/></a></dd>
				<dd class="scont_box2"><a href="#"><img src="<c:url value='/resources/img/main/main/scont_img4.jpg'/>" alt="product image"/></a></dd>
			</dl>
			
		</div>
		<div class="cont_product">
			<div class="product_listbox1">
				<ul class="plist_area">
						<li><a href="/?prdCtgL=L1601" class="wpadding1 on">EARPHONE</a></li>
						<li><a href="/?prdCtgL=L3679" class="wpadding2">HEADPHONE</a></li>
						<li><a href="/?prdCtgL=L2022" class="wpadding3">MULTMEDIA</a></li>
				</ul>
				<div class="product_elist">
					<ul class="pro_list">
						<c:forEach items="${epPrdList}" var="epPrdList">
							<li>
								<a href="#">
									<dl class="list_product">
											<dd><img src="${epPrdList.attFilePath}" width="119" height="119" alt="prouct IMG"/></dd>
											<dd>${epPrdList.prdBrand}</dd>
											<dd>${epPrdList.prdModel}</dd>
											<dd><strong class="textline">${epPrdList.prdPrice}</strong>↓<span>${epPrdList.prdSellPrc}원</span></dd>
									</dl>
								</a>
							</li>
						</c:forEach>
						<c:forEach items="${hpPrdList}" var="hpPrdList">
							<li>
								<a href="#">
									<dl class="list_product">
											<dd><img src="${hpPrdList.attFilePath}" width="119" height="119" alt="prouct IMG"/></dd>
											<dd>${hpPrdList.prdBrand}</dd>
											<dd>${hpPrdList.prdModel}</dd>
											<dd><strong class="textline">${hpPrdList.prdPrice}</strong>↓<span>${hpPrdList.prdSellPrc}원</span></dd>
									</dl>
								</a>
							</li>
						</c:forEach>
						<c:forEach items="${mmPrdList}" var="mmPrdList">
							<li>
								<a href="#">
									<dl class="list_product">
											<dd><img src="${mmPrdList.attFilePath}" width="119" height="119" alt="prouct IMG"/></dd>
											<dd>${mmPrdList.prdBrand}</dd>
											<dd>${mmPrdList.prdModel}</dd>
											<dd><strong class="textline">${mmPrdList.prdPrice}</strong>↓<span>${mmPrdList.prdSellPrc}원</span></dd>
									</dl>
								</a>
							</li>
						</c:forEach>		
					</ul>
				</div>
			</div>
			
			
			<div class="product_listbox2">
				<ul class="plist_area">
					<li><a href="/?prdCtgL=L4315" class="wpadding1 on">BLUETOOTH</a></li>
					<li><a href="/?prdCtgL=L3862" class="wpadding2">CASE</a></li>
					<li><a href="/?prdCtgL=L7451" class="wpadding3">XTC</a></li>
				</ul>
				<div class="product_elist">
					<ul class="pro_list">
						<c:forEach items="${pdSList}" var="pdSList">
							<li>
								<a href="#">
									<dl class="list_product">
											<dd><img src="${pdSList.attFilePath}" width="119" height="119" alt="prouct IMG"/></dd>
											<dd>${pdSList.prdBrand}</dd>
											<dd>${pdSList.prdModel}</dd>
											<dd><strong class="textline">${pdSList.prdPrice}</strong>↓<span>${pdSList.prdSellPrc}원</span></dd>
									</dl>
								</a>
							</li>
						</c:forEach>
						<c:forEach items="${csPrdList}" var="csPrdList">
							<li>
								<a href="#">
									<dl class="list_product">
											<dd><img src="${csPrdList.attFilePath}" width="119" height="119" alt="prouct IMG"/></dd>
											<dd>${csPrdList.prdBrand}</dd>
											<dd>${csPrdList.prdModel}</dd>
											<dd><strong class="textline">${csPrdList.prdPrice}</strong>↓<span>${csPrdList.prdSellPrc}원</span></dd>
									</dl>
								</a>
							</li>
						</c:forEach>
						<c:forEach items="${xtPrdList}" var="xtPrdList">
							<li>
								<a href="#">
									<dl class="list_product">
											<dd><img src="${xtPrdList.attFilePath}" width="119" height="119" alt="prouct IMG"/></dd>
											<dd>${xtPrdList.prdBrand}</dd>
											<dd>${xtPrdList.prdModel}</dd>
											<dd><strong class="textline">${xtPrdList.prdPrice}</strong>↓<span>${xtPrdList.prdSellPrc}원</span></dd>
									</dl>
								</a>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			
			
		</div>
		<div class="cont_banner">
			<a href="#" class="marginright"><img src="<c:url value='/resources/img/main/main/main_banner1.jpg'/>" alt="main banner image"/></a>
			<a href="#"><img src="<c:url value='/resources/img/main/main/main_banner2.jpg'/>" alt="main banner image"/></a>
		</div>
		<div class="cont_bottom">
		 <dl class="cont_botbox1">
			<dt>입점 브래드
				<span>Sound Brand</span>
			</dt>
			<dd>
				<a href="#" class="mart"><img src="<c:url value='/resources/img/main/main/lglogo.gif'/>" alt="LG전자 logo"/></a>
				<a href="#" class="mart"><img src="<c:url value='/resources/img/main/main/akglogo.gif'/>" alt="AKG logo"/></a>
				<a href="#" class="mart"><img src="<c:url value='/resources/img/main/main/harmanlogo.gif'/>" alt="harman logo"/></a>
				<a href="#"><img src="<c:url value='/resources/img/main/main/ubllogo.gif'/>" alt="UBL logo"/></a>
			</dd>
		 </dl>
		 <dl class="cont_botbox2">
			<dt>하만 스토어
				<span class="textbox1">진정한 사운드를 좀 더 가까운 곳에서<br/>
				느끼실 수 있습니다.</span>
				<span class="textbox2">서울 특별시 강남구 청담동 85-2 JW빌딩 1층</span>
			</dt>
			<dd>
				<a href="#" class="mart"><img src="<c:url value='/resources/img/main/main/store_img1.jpg'/>" alt="store image"/></a>
				<a href="#" class="mart"><img src="<c:url value='/resources/img/main/main/store_img2.jpg'/>" alt="store image"/></a>
				<a href="#"><img src="<c:url value='/resources/img/main/main/store_img3.jpg'/>" alt="store image"/git ></a>
			</dd>
		 </dl>
		</div>
		<div class="cont_notice">
			<dl class="notice_box">
				<dt class="notice_tit">NEWS & NOTICE</dt>
				<dd>
					<a href="#" class="noticeblet">LG G2 정케이스 출시</a>
					<a href="#" class="noticeblet">동급 최강!! HBS-730NEW 출시!!</a>
					<a href="#" class="noticeblet">SBS수목드라마 AKG 헤드폰 협찬</a>
				</dd>
			</dl>
			<span class="icon_box">
				<a href="#" class="icon_malign"><img src="<c:url value='/resources/img/main/main/customer_center.gif'/>" alt="customer center"/></a>
				<a href="#" class="icon_malign"><img src="<c:url value='/resources/img/main/main/qa_icon.gif'/>" alt="qa icon"/></a>
				<a href="#" class="icon_malign"><img src="<c:url value='/resources/img/main/main/faq_icon.gif'/>" alt="faq icon"/></a>
				<a href="#" class="icon_malign"><img src="<c:url value='/resources/img/main/main/order_icon.gif'/>" alt="order icon"/></a>
				<a href="#"><img src="<c:url value='/resources/img/main/main/customer_icon.gif'/>" alt="customer icon"/></a>
			</span>
		</div>
	</div>
<!--  container 끝   -->	


	<c:import  url="../inc/footer.jsp" />
	
</div>
</body>

<c:import  url="../inc/bottom.jsp" />
