<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="imagetoolbar" content="no" />
<title> ### worklist ### </title>

<style type="text/css">
html, body, div, dl, dt, dd, ul, ol, li, h1, h2, h3, h4, h5, h6, hr, pre, code, form, fieldset, legend, input, textarea, button,  p, blockquote, th, td {margin:0; padding:0;}
html, body { width:100%; font-family:"NanumGothic",'돋움', 'Dotum', 'Apple Gothic', 'sans-serif';}
body {font-size:13px; font-family:"NanumGothic"; color:#3b3b3b;}
li {list-style:none;}
img, fieldset {border:0 none;}
img {vertical-align:middle;}
legend, hr {display:none;}
legend {position:absolute; top:0; left:0; width:0; height:0; display:block; overflow:hidden; visibility:hidden; font-size:0; line-height:0;}
input, textarea {font-size:100%; color:#747474; background:#fff;}
button {border:0 none;background:transparent; padding:0px; margin:0px;}
em, address {font-style:normal;}
table {border-spacing:0; border-collapse:collapse; width:100%;}
caption {font-size: 0; line-height: 0; width:0; height:0; text-indent:-1000px; overflow: hidden;}
a, a:link, a:visited {text-decoration:none;}
a:hover, a:active {text-decoration:underline;}

/* begin - font css */
@import url(http://fonts.googleapis.com/earlyaccess/nanumgothic.css);

@font-face {font-family:"NanumGothic"; font-style:normal; font-weight:normal; src:url("font/NanumGothic.eot"); src:url('./font/NanumGothic.eot?NanumGothic'), local('?'), url('./font/NanumGothic.woff') format('woff'), url('./font/NanumGothic.svg#NanumGothic') format('svg'), url('./font/NanumGothic.ttf') format('truetype');}

@font-face {font-family:"NanumGothic-ExtraBold"; font-style:normal; font-weight: bold; src:url('./font/NanumGothic-ExtraBold.eot'); src:url('./font/NanumGothic-ExtraBold.eot?iefix') format('embedded-opentype'), local('?'),	url('./font/NanumGothic-ExtraBold.woff') format('woff'), url('./font/NanumGothic-ExtraBold.svg#NanumGothicEB') format('svg'), url('./font/NanumGothic-ExtraBold.ttf') format('truetype');}


.wrap {width:100%;}
h1 {margin:10px 0 0 15px; font-family:"NanumGothic-ExtraBold"; font-size:25px; color:#084d7d;}
p {margin:10px 0 0 15px; font-size:15px;}
table {margin-top:10px;}
table thead th {line-height:45px; background:#0564a7; color:#fff; font-size:15px; font-family:"NanumGothic-ExtraBold"; border:1px solid #07446e; text-transform: uppercase;}
table tbody th {background:#f2f1f1;}
table tbody th,td {line-height:30px; height:30px; border:1px solid #979696; text-align:center; font-weight:normal;}
table tbody th,td a {color:#3b3b3b;}

</style>
</head>

<body>
	<div class="wrap">
		<h1>&laquo; 페이지 뷰 &raquo;</h1>
		<table summary="work목록">
			<caption>work목록표</caption>
			<colgroup>
				<col width="15%"/>
				<col width="15%"/>
				<col width="15%"/>
				<col width="35%"/>
				<col width="10%"/>
				<col width="10%"/>
			</colgroup>
			<thead>
				<tr>
					<th>Step1</th>
					<th>Step2</th>
					<th>Step3</th>
					<th>HTML</th>
					<th>Etc</th>
					<th>Date</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>main</th>
					<td></td>
					<td></td>
					<td><a href="main.do">main.do</a></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<th rowspan="12">login</th>
					<td>login</td>
					<td></td>
					<td><a href="./login.html">login.html</a></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td rowspan="3">membership</td>
					<td>회원가입 약관동의</td>
					<td><a href="./membership.html">membership</a></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>회원가입 회원정보입력</td>
					<td><a href="./membership_step1.html">membership_step1</a></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>회원가입 완료</td>
					<td><a href="#"></a></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td rowspan="8">MY PAGE</td>
					<td>나의정보</td>
					<td><a href="./mypage.html">mypage</a></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>마이쿠폰1</td>
					<td><a href="./mypage_step1.html">mypage_step1</a></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>마이쿠폰2</td>
					<td><a href="./mypage_step2.html">mypage_step2</a></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>적립금관리 사용내역조회</td>
					<td><a href="./mypage_step3.html">mypage_step3</a></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>적립금관리 적립금현황1</td>
					<td><a href="./mypage_step4.html">mypage_step4</a></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>적립금관리 적립금현황2</td>
					<td><a href="./mypage_step5.html">mypage_step5</a></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>주문내역관리</td>
					<td><a href="./mypage_step6.html">mypage_step6</a></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>쿠폰북</td>
					<td><a href="./mypage_step7.html">mypage_step7</a></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<th rowspan="10">sub</th>
					<td rowspan="2">product</td>
					<td>보기</td>
					<td><a href="./product_list.html">product_list</a></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>쓰기</td>
					<td><a href="./product_view.html">product_view</a></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td rowspan="4">블루원 고객센터</td>
					<td>고객센터</td>
					<td><a href="./customer.html">customer</a></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>1:1문의하기</td>
					<td><a href="./customer_step1.html">customer_step1</a></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>FAQ</td>
					<td><a href="./customer_step2.html">customer_step2</a></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>공지사항</td>
					<td><a href="./customer_step3.html">customer_step3</a></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td rowspan="2">장바구니</td>
					<td>장바구니 카트</td>
					<td><a href="./product_order.html">product_order</a></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>장바구니 주문결제</td>
					<td><a href="./product_order_step1.html">product_order_step1</a></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td rowspan="2">content4</td>
					<td>보기</td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>쓰기</td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
		</table>
	</div>

</body>
</html>