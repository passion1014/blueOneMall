<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c_rt"%> 
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">


<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/common.css'/>" />
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/component.css'/>" />
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/default.css'/>" />
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/main.css'/>" />
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/sub.css'/>" />
<script type="text/javascript" src="<c:url value='/resources/js/jquery-ui.custom.js'/>"> </script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.js'/>"> </script>

<script type="text/javascript">
	<!-- tab module -->
	$(function() {
		$( ".mcont" ).tabs();
	});

	function change_area(n){
		for(var i=1;i<=6;i++){
			area_id = "area_"+i ;
			if(i == n){
				document.getElementById(area_id).style.display = "block";
			}else{
				document.getElementById(area_id).style.display = "none";
			}
		}
	}

		function change_area1(n){
		for(var i=1;i<=3;i++){
			area_id = "area_"+i ;
			if(i == n){
				document.getElementById(area_id).style.display = "block";
			}else{
				document.getElementById(area_id).style.display = "none";
			}
		}
	}
	<!--// tab module -->
</script>
<title>Insert title here</title>
</head>
<body>
   <div class="wrap">
	<div class="header">
		<div class="top">
			<h1><a href="#"><img src="<c:url value='/resources/img/main/common/blueone_logo.png'/>"/></a></h1>
			<p class="top_img">
				<img src="<c:url value='/resources/img/main/common/top_image.jpg'/>"/>
			</p>
			<form action="#" method="post">
				<div class="search_area">
					<span class="search_menu">
						<a href="#" class="menu_site">��ٱ���</a>
						<a href="#" class="menu_site">�ֹ���� ��ȸ</a>
						<a href="#">����������</a>
					</span>
					<span class="search_box">
						<input type="text" title="��ġ�ؽ�Ʈ�ڽ�"/>
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
<!--  header ��   -->
	<div class="container">
		<div class="cont_main">
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
						<li><a href="#"><img src="./images/main/smain_img1.jpg"/></a></li>
						<li class="leftline"><a href="#"><img src="./images/main/smain_img2.jpg"/></a></li>
						<li class="leftline"><a href="#"><img src="./images/main/smain_img3.jpg"/></a></li>
					</ul>
					<p><img src="./images/main/main_img.jpg"/></p>
				</div>
				<div id="area_4" class="click_nav" style="display:none;">
					<ul>
						<li><a href="#"><img src="./images/main/smain_img1.jpg"/></a></li>
						<li class="leftline"><a href="#"><img src="./images/main/smain_img2.jpg"/></a></li>
						<li class="leftline"><a href="#"><img src="./images/main/smain_img3.jpg"/></a></li>
					</ul>
					<p><img src="./images/main/main_img.jpg"/></p>
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
						<li><a href="#"><img src=".<c:url value='/resources/img/main/main/smain_img1.jpg'/>"/></a></li>
						<li class="leftline"><a href="#"><img src="<c:url value='/resources/img/main/main/smain_img2.jpg'/>"/></a></li>
						<li class="leftline"><a href="#"><img src="<c:url value='/resources/img/main/main/smain_img3.jpg'/>"/></a></li>
					</ul>
					<p><img src="./images/main/main_img.jpg"/></p>
				</div>
			</div>
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
					<p class="tit_loc"><span class="loc_text1">BEST ITEM</span><span class="loc_text2">������ ��</span></p>
					<ul class="pro_list">
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="./images/main/pro_img1.jpg" alt="product image"/></dd>
								<dd>[Sonus Faber���ҳʽ� �ĺ���]</dd>
								<dd>BUS AG USB ���̺�</dd>
								<dd><strong class="textline"> 510,000</strong>��<span>200,000��</span></dd>
							</dl>
						</a></li>
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="./images/main/pro_img2.jpg" alt="product image"/></dd>
								<dd>[Sonus Faber���ҳʽ� �ĺ���]</dd>
								<dd>BUS AG USB ���̺�</dd>
								<dd><strong class="textline"> 510,000</strong>��<span>200,000��</span></dd>
							</dl>
						</a></li>
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="./images/main/pro_img3.jpg" alt="product image"/></dd>
								<dd>[Sonus Faber���ҳʽ� �ĺ���]</dd>
								<dd>BUS AG USB ���̺�</dd>
								<dd><strong class="textline"> 510,000</strong>��<span>200,000��</span></dd>
							</dl>
						</a></li>
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="./images/main/pro_img1.jpg" alt="product image"/></dd>
								<dd>[Sonus Faber���ҳʽ� �ĺ���]</dd>
								<dd>BUS AG USB ���̺�</dd>
								<dd><strong class="textline"> 510,000</strong>��<span>200,000��</span></dd>
							</dl>
						</a></li>
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="./images/main/pro_img2.jpg" alt="product image"/></dd>
								<dd>[Sonus Faber���ҳʽ� �ĺ���]</dd>
								<dd>BUS AG USB ���̺�</dd>
								<dd><strong class="textline"> 510,000</strong>��<span>200,000��</span></dd>
							</dl>
						</a></li>
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="./images/main/pro_img3.jpg" alt="product image"/></dd>
								<dd>[Sonus Faber���ҳʽ� �ĺ���]</dd>
								<dd>BUS AG USB ���̺�</dd>
								<dd><strong class="textline"> 510,000</strong>��<span>200,000��</span></dd>
							</dl>
						</a></li>
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="./images/main/pro_img1.jpg" alt="product image"/></dd>
								<dd>[Sonus Faber���ҳʽ� �ĺ���]</dd>
								<dd>BUS AG USB ���̺�</dd>
								<dd><strong class="textline"> 510,000</strong>��<span>200,000��</span></dd>
							</dl>
						</a></li>
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="./images/main/pro_img2.jpg" alt="product image"/></dd>
								<dd>[Sonus Faber���ҳʽ� �ĺ���]</dd>
								<dd>BUS AG USB ���̺�</dd>
								<dd><strong class="textline"> 510,000</strong>��<span>200,000��</span></dd>
							</dl>
						</a></li>
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="./images/main/pro_img3.jpg" alt="product image"/></dd>
								<dd>[Sonus Faber���ҳʽ� �ĺ���]</dd>
								<dd>BUS AG USB ���̺�</dd>
								<dd><strong class="textline"> 510,000</strong>��<span>200,000��</span></dd>
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
					<p class="tit_loc"><span class="loc_text1">BEST ITEM</span><span class="loc_text2">������ ��</span></p>
					<ul class="pro_list">
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="./images/main/pro_img1.jpg" alt="product image"/></dd>
								<dd>[Sonus Faber���ҳʽ� �ĺ���]</dd>
								<dd>BUS AG USB ���̺�</dd>
								<dd><strong class="textline"> 510,000</strong>��<span>200,000��</span></dd>
							</dl>
						</a></li>
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="./images/main/pro_img2.jpg" alt="product image"/></dd>
								<dd>[Sonus Faber���ҳʽ� �ĺ���]</dd>
								<dd>BUS AG USB ���̺�</dd>
								<dd><strong class="textline"> 510,000</strong>��<span>200,000��</span></dd>
							</dl>
						</a></li>
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="./images/main/pro_img3.jpg" alt="product image"/></dd>
								<dd>[Sonus Faber���ҳʽ� �ĺ���]</dd>
								<dd>BUS AG USB ���̺�</dd>
								<dd><strong class="textline"> 510,000</strong>��<span>200,000��</span></dd>
							</dl>
						</a></li>
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="./images/main/pro_img1.jpg" alt="product image"/></dd>
								<dd>[Sonus Faber���ҳʽ� �ĺ���]</dd>
								<dd>BUS AG USB ���̺�</dd>
								<dd><strong class="textline"> 510,000</strong>��<span>200,000��</span></dd>
							</dl>
						</a></li>
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="./images/main/pro_img2.jpg" alt="product image"/></dd>
								<dd>[Sonus Faber���ҳʽ� �ĺ���]</dd>
								<dd>BUS AG USB ���̺�</dd>
								<dd><strong class="textline"> 510,000</strong>��<span>200,000��</span></dd>
							</dl>
						</a></li>
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="./images/main/pro_img3.jpg" alt="product image"/></dd>
								<dd>[Sonus Faber���ҳʽ� �ĺ���]</dd>
								<dd>BUS AG USB ���̺�</dd>
								<dd><strong class="textline"> 510,000</strong>��<span>200,000��</span></dd>
							</dl>
						</a></li>
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="./images/main/pro_img1.jpg" alt="product image"/></dd>
								<dd>[Sonus Faber���ҳʽ� �ĺ���]</dd>
								<dd>BUS AG USB ���̺�</dd>
								<dd><strong class="textline"> 510,000</strong>��<span>200,000��</span></dd>
							</dl>
						</a></li>
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="./images/main/pro_img2.jpg" alt="product image"/></dd>
								<dd>[Sonus Faber���ҳʽ� �ĺ���]</dd>
								<dd>BUS AG USB ���̺�</dd>
								<dd><strong class="textline"> 510,000</strong>��<span>200,000��</span></dd>
							</dl>
						</a></li>
						<li><a href="#">
							<dl class="list_product">
								<dd><img src="./images/main/pro_img3.jpg" alt="product image"/></dd>
								<dd>[Sonus Faber���ҳʽ� �ĺ���]</dd>
								<dd>BUS AG USB ���̺�</dd>
								<dd><strong class="textline"> 510,000</strong>��<span>200,000��</span></dd>
							</dl>
						</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="cont_banner">
			<a href="#" class="marginright"><img src="./images/main/main_banner1.jpg" alt="main banner image"/></a>
			<a href="#"><img src="./images/main/main_banner2.jpg" alt="main banner image"/></a>
		</div>
		<div class="cont_bottom">
		 <dl class="cont_botbox1">
			<dt>���� �귡��
				<span>Sound Brand</span>
			</dt>
			<dd>
				<a href="#" class="mart"><img src="./images/main/lglogo.gif" alt="LG���� logo"/></a>
				<a href="#" class="mart"><img src="./images/main/akglogo.gif" alt="AKG logo"/></a>
				<a href="#" class="mart"><img src="./images/main/harmanlogo.gif" alt="harman logo"/></a>
				<a href="#"><img src="./images/main/ubllogo.gif" alt="UBL logo"/></a>
			</dd>
		 </dl>
		 <dl class="cont_botbox2">
			<dt>�ϸ� �����
				<span class="textbox1">������ ���带 �� �� ����� ������<br/>
				������ �� �ֽ��ϴ�.</span>
				<span class="textbox2">���� Ư���� ������ û�㵿 85-2 JW���� 1��</span>
			</dt>
			<dd>
				<a href="#" class="mart"><img src="./images/main/store_img1.jpg" alt="store image"/></a>
				<a href="#" class="mart"><img src="./images/main/store_img2.jpg" alt="store image"/></a>
				<a href="#"><img src="./images/main/store_img3.jpg" alt="store image"/></a>
			</dd>
		 </dl>
		</div>
		<div class="cont_notice">
			<dl class="notice_box">
				<dt class="notice_tit">NEWS & NOTICE</dt>
				<dd>
					<a href="#" class="noticeblet">LG G2 �����̽� ���</a>
					<a href="#" class="noticeblet">���� �ְ�!! HBS-730NEW ���!!</a>
					<a href="#" class="noticeblet">SBS������ AKG ����� ����</a>
				</dd>
			</dl>
			<span class="icon_box">
				<a href="#" class="icon_malign"><img src="./images/main/customer_center.gif" alt="customer center"/></a>
				<a href="#" class="icon_malign"><img src="./images/main/qa_icon.gif" alt="qa icon"/></a>
				<a href="#" class="icon_malign"><img src="./images/main/faq_icon.gif" alt="faq icon"/></a>
				<a href="#" class="icon_malign"><img src="./images/main/order_icon.gif" alt="order icon"/></a>
				<a href="#"><img src="./images/main/customer_icon.gif" alt="customer icon"/></a>
			</span>
		</div>
	</div>
<!--  container ��   -->	

	<div class="footer">
		<div class="footer_area">
			<h2><img src="./images/common/footer_logo.jpg" alt="���� �ΰ�"/></h2>
			<address>
				�����ŷ�����ȸ ��� ��2001-1ȣ�� ���� ����� ��Ϲ�ȣ:212-81-86027�Ӵ�ǥ�̻� : ��ȭ��<br/>
				������������ å���� ���λ���� ���ο���1�� �ۼ�ȣ ���� l �ּ�:����� ������ �ϻ絿 513-16���� ����H&S<br/>
				COPYRIGHT 2012 BY ����H&S ALL RIGHT RESERVED.
			</address>
		</div>
	</div>
</div>
</body>
</html>