<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import  url="../inc/topMain.jsp" />

<body>
<div class="wrap">
	<c:import  url="../inc/header.jsp" />
		
	<div class="container">
		<div class="cont_main">
		
		<!-- 
	 		<div class="mcont">
				<ul class="mcont_nav">
					<li><a href="#" onClick="change_area('1')">event 1</a></li>
					<li><a href="#" onClick="change_area('2')">event 2</a></li>
					<li><a href="#" onClick="change_area('3')">event 3</a></li>
					<li><a href="#" onClick="change_area('4')">event 4</a></li>
					<li><a href="#" onClick="change_area('5')">event 5</a></li>
					<li><a href="#" onClick="change_area('6')">event 6</a></li>
				</ul>
				<div id="area_1" class="click_nav" style="display:block;">
					<ul>                
						<li><a href="#"><img src="<c:url value='/resources/img/main/main/smain_img1.jpg'/>"/></a></li>
						<li class="leftline"><a href="#"><img src="<c:url value='/resources/img/main/main/smain_img2.jpg'/>"/></a></li>
						<li class="leftline"><a href="#"><img src="<c:url value='/resources/img//main/main/smain_img3.jpg'/>"/></a></li>
					</ul>
					<p><img src="<c:url value='/resources/img/main/main/main_img.jpg'/>"/></p>
				</div>
				<div id="area_2" class="click_nav" style="display:none;">
					<ul>
						<li><a href="#"><img src="<c:url value='/resources/img/main/main/smain_img1.jpg'/>"/></a></li>
						<li class="leftline"><a href="#"><img src="<c:url value='/resources/img/main/main/smain_img2.jpg'/>"/></a></li>
						<li class="leftline"><a href="#"><img src="<c:url value='/resources/img/main/main/smain_img3.jpg'/>"/></a></li>
					</ul>
					<p><img src="<c:url value='/resources/img/main/main/main_img.jpg'/>"/></p>
				</div>
				<div id="area_3" class="click_nav" style="display:none;">
					<ul>
						<li><a href="#"><img src="<c:url value='/resources/img/main/main/smain_img1.jpg'/>"/></a></li>
						<li class="leftline"><a href="#"><img src="<c:url value='/resources/img/main/main/smain_img2.jpg'/>"/></a></li>
						<li class="leftline"><a href="#"><img src="<c:url value='/resources/img/main/main/smain_img3.jpg'/>"/></a></li>
					</ul>
					<p><img src="<c:url value='/resources/img/main/main/main_img.jpg'/>"/></p>
				</div>
				<div id="area_4" class="click_nav" style="display:none;">
					<ul>
						<li><a href="#"><img src="<c:url value='/resources/img/main/main/smain_img1.jpg'/>"/></a></li>
						<li class="leftline"><a href="#"><img src="<c:url value='/resources/img/main/main/smain_img2.jpg'/>"/></a></li>
						<li class="leftline"><a href="#"><img src="<c:url value='/resources/img/main/main/smain_img3.jpg'/>"/></a></li>
					</ul>
					<p><img src="<c:url value='/resources/img/main/main/main_img.jpg'/>"/></p>
				</div>
				<div id="area_5" class="click_nav" style="display:none;">
					<ul>
						<li><a href="#"><img src="<c:url value='/resources/img/main/main/smain_img1.jpg'/>"/></a></li>
						<li class="leftline"><a href="#"><img src="<c:url value='/resources/img/main/main/smain_img2.jpg'/>"/></a></li>
						<li class="leftline"><a href="#"><img src="<c:url value='/resources/img/main/main/smain_img3.jpg'/>"/></a></li>
					</ul>
					<p><img src="<c:url value='/resources/img/main/main/main_img.jpg'/>"/></p>
				</div>
				<div id="area_6" class="click_nav" style="display:none;">
					<ul>
						<li><a href="#"><img src="<c:url value='/resources/img/main/main/smain_img1.jpg'/>"/></a></li>
						<li class="leftline"><a href="#"><img src="<c:url value='/resources/img/main/main/smain_img2.jpg'/>"/></a></li>
						<li class="leftline"><a href="#"><img src="<c:url value='/resources/img/main/main/smain_img3.jpg'/>"/></a></li>
					</ul>
					<p><img src="<c:url value='/resources/img/main/main/main_img.jpg'/>"/></p>
				</div>
			</div>
			//-->
			
			
			
			
			
			
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
					<li><a href="#" class="wpadding1">EARPHONE</a></li>
					<li><a href="#" class="wpadding2 on">HEADPHONE</a></li>
					<li><a href="#" class="wpadding3">MULTMEDIA</a></li>
				</ul>
				<div class="product_elist">
					<p class="tit_loc"><span class="loc_text1">BEST ITEM</span><span class="loc_text2">오늘의 딜</span></p>
					<ul class="pro_list">
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="<c:url value='/resources/img/main/main/pro_img1.jpg'/>" alt="product image"/></dd>
								<dd>[Sonus Faber│소너스 파베르]</dd>
								<dd>BUS AG USB 케이블</dd>
								<dd><strong class="textline"> 510,000</strong>↓<span>200,000원</span></dd>
							</dl>
						</a></li>
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="<c:url value='/resources/img/main/main/pro_img2.jpg'/>"  alt="product image"/></dd>
								<dd>[Sonus Faber│소너스 파베르]</dd>
								<dd>BUS AG USB 케이블</dd>
								<dd><strong class="textline"> 510,000</strong>↓<span>200,000원</span></dd>
							</dl>
						</a></li>
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="<c:url value='/resources/img/main/main/pro_img3.jpg'/>" alt="product image"/></dd>
								<dd>[Sonus Faber│소너스 파베르]</dd>
								<dd>BUS AG USB 케이블</dd>
								<dd><strong class="textline"> 510,000</strong>↓<span>200,000원</span></dd>
							</dl>
						</a></li>
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="<c:url value='/resources/img/main/main/pro_img1.jpg'/>" alt="product image"/></dd>
								<dd>[Sonus Faber│소너스 파베르]</dd>
								<dd>BUS AG USB 케이블</dd>
								<dd><strong class="textline"> 510,000</strong>↓<span>200,000원</span></dd>
							</dl>
						</a></li>
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="<c:url value='/resources/img/main/main/pro_img2.jpg'/>"  alt="product image"/></dd>
								<dd>[Sonus Faber│소너스 파베르]</dd>
								<dd>BUS AG USB 케이블</dd>
								<dd><strong class="textline"> 510,000</strong>↓<span>200,000원</span></dd>
							</dl>
						</a></li>
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="<c:url value='/resources/img/main/main/pro_img3.jpg'/>" alt="product image"/></dd>
								<dd>[Sonus Faber│소너스 파베르]</dd>
								<dd>BUS AG USB 케이블</dd>
								<dd><strong class="textline"> 510,000</strong>↓<span>200,000원</span></dd>
							</dl>
						</a></li>
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="<c:url value='/resources/img/main/main/pro_img1.jpg'/>"  alt="product image"/></dd>
								<dd>[Sonus Faber│소너스 파베르]</dd>
								<dd>BUS AG USB 케이블</dd>
								<dd><strong class="textline"> 510,000</strong>↓<span>200,000원</span></dd>
							</dl>
						</a></li>
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="<c:url value='/resources/img/main/main/pro_img2.jpg'/>" alt="product image"/></dd>
								<dd>[Sonus Faber│소너스 파베르]</dd>
								<dd>BUS AG USB 케이블</dd>
								<dd><strong class="textline"> 510,000</strong>↓<span>200,000원</span></dd>
							</dl>
						</a></li>
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="<c:url value='/resources/img/main/main/pro_img3.jpg'/>"  alt="product image"/></dd>
								<dd>[Sonus Faber│소너스 파베르]</dd>
								<dd>BUS AG USB 케이블</dd>
								<dd><strong class="textline"> 510,000</strong>↓<span>200,000원</span></dd>
							</dl>
						</a></li>
					</ul>
				</div>
			</div>
			<div class="product_listbox2">
				<ul class="plist_area">
					<li><a href="#" class="wpadding1">BLUETOOTH</a></li>
					<li><a href="#" class="wpadding2 on">CASE</a></li>
					<li><a href="#" class="wpadding3">ETC</a></li>
				</ul>
				<div class="product_elist">
					<p class="tit_loc"><span class="loc_text1">BEST ITEM</span><span class="loc_text2">오늘의 딜</span></p>
					<ul class="pro_list">
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="<c:url value='/resources/img/main/main/pro_img1.jpg'/>"  alt="product image"/></dd>
								<dd>[Sonus Faber│소너스 파베르]</dd>
								<dd>BUS AG USB 케이블</dd>
								<dd><strong class="textline"> 510,000</strong>↓<span>200,000원</span></dd>
							</dl>
						</a></li>
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="<c:url value='/resources/img/main/main/pro_img2.jpg'/>"  alt="product image"/></dd>
								<dd>[Sonus Faber│소너스 파베르]</dd>
								<dd>BUS AG USB 케이블</dd>
								<dd><strong class="textline"> 510,000</strong>↓<span>200,000원</span></dd>
							</dl>
						</a></li>
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="<c:url value='/resources/img/main/main/pro_img3.jpg'/>" alt="product image"/></dd>
								<dd>[Sonus Faber│소너스 파베르]</dd>
								<dd>BUS AG USB 케이블</dd>
								<dd><strong class="textline"> 510,000</strong>↓<span>200,000원</span></dd>
							</dl>
						</a></li>
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="<c:url value='/resources/img/main/main/pro_img1.jpg'/>"  alt="product image"/></dd>
								<dd>[Sonus Faber│소너스 파베르]</dd>
								<dd>BUS AG USB 케이블</dd>
								<dd><strong class="textline"> 510,000</strong>↓<span>200,000원</span></dd>
							</dl>
						</a></li>
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="<c:url value='/resources/img/main/main/pro_img2.jpg'/>"  alt="product image"/></dd>
								<dd>[Sonus Faber│소너스 파베르]</dd>
								<dd>BUS AG USB 케이블</dd>
								<dd><strong class="textline"> 510,000</strong>↓<span>200,000원</span></dd>
							</dl>
						</a></li>
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="<c:url value='/resources/img/main/main/pro_img3.jpg'/>"  alt="product image"/></dd>
								<dd>[Sonus Faber│소너스 파베르]</dd>
								<dd>BUS AG USB 케이블</dd>
								<dd><strong class="textline"> 510,000</strong>↓<span>200,000원</span></dd>
							</dl>
						</a></li>
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="<c:url value='/resources/img/main/main/pro_img1.jpg'/>"  alt="product image"/></dd>
								<dd>[Sonus Faber│소너스 파베르]</dd>
								<dd>BUS AG USB 케이블</dd>
								<dd><strong class="textline"> 510,000</strong>↓<span>200,000원</span></dd>
							</dl>
						</a></li>
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="<c:url value='/resources/img/main/main/pro_img2.jpg'/>"  alt="product image"/></dd>
								<dd>[Sonus Faber│소너스 파베르]</dd>
								<dd>BUS AG USB 케이블</dd>
								<dd><strong class="textline"> 510,000</strong>↓<span>200,000원</span></dd>
							</dl>
						</a></li>
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="<c:url value='/resources/img/main/main/pro_img3.jpg'/>"  alt="product image"/></dd>
								<dd>[Sonus Faber│소너스 파베르]</dd>
								<dd>BUS AG USB 케이블</dd>
								<dd><strong class="textline"> 510,000</strong>↓<span>200,000원</span></dd>
							</dl>
						</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="cont_banner">
			<a href="#" class="marginright"><img src="<c:url value='/resources/img/main/main/main_banner1.jpg'/>" alt="main banner image"/></a>
			<a href="#" class="marginright"><img src="<c:url value='/resources/img/main/main/main_banner2.jpg'/>" alt="main banner image"/></a>
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
				<a href="#"><img src="<c:url value='/resources/img/main/main/store_img3.jpg'/>" alt="store image"/></a>
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
