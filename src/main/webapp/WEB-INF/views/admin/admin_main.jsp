<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- admin.top.html start -->

<!doctype html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=1280; user-scalable=yes">
<meta name="apple-touch-fullscreen" content="yes">
<meta name="apple-mobile-web-app-capable" content="yes">
<title>:: ADMIN ::</title>
<link rel="stylesheet" href="../../css/admin/basic.css" />
<link rel="stylesheet" href="../../css/admin/admin.css" />
<script language="javascript" type="text/javascript" src="../js/js_ajax.js"> </script>
<script language="javascript" type="text/javascript" src="../js/js_common.js"> </script>
<script language="javascript" type="text/javascript" src="../js/js_admin.js"> </script>





<!--[if lt IE 9]>
<script src="js/jquery_html5shiv.js"></script>
<![endif]-->

<!-- admin.top.html end -->

</head>
<iframe name="actionForm" width="700" height="200" frameborder="0" style="display:none;"> </iframe>

<body>
<div id="Wrap">

	<div id="Header">
		<a href="/_admin/"><img src="/img/top_logo.gif" alt=""></a>
		<div style="padding-right:10px">
			<b>전체관리자</b> 께서 로그인하셨습니다. <input type="button" value="로그아웃" onClick="location.href='./admin.login.html';" style="cursor:pointer;"/>
		</div>
	</div>
<!-- admin.header.html end -->


<!-- admin.gnb.html start -->
	<div id="GNB">
		<ul>
			<li><a href="./admin.conf1.html?slot=conf&type=admin_list">환경설정</a></li>
			<li><a href="#">회원관리</a></li>
			<li><a href="#">상품관리</a></li>
			<li><a href="#">주문관리</a></li>
			<li><a href="#">커뮤니티</a></li>
		</ul>
	</div>
<!-- admin.gnb.html end -->


<!-- admin.lnb_none.html start -->
	<div id="LNB" style="display:none;">
		<h1>관리메뉴</h1>
		<ul>
			<h2>&nbsp;</h2>
			<span style="line-height:400px;">&nbsp;</span>
		</ul>
	</div>
<!-- admin.lnb_none.html end -->



<!-- ./main/main.default.html start -->
	<div style="text-align:center;color:#fff;background:#2d80a3;;width:300px;height:110px;margin:150px auto;padding-top:50px;border-bottom:2px solid #00395a;">
		<span style="font-weight:bold">현대몰</span> 관리자모드입니다.<br><br>원하시는 관리메뉴를 선택해 주세요
	</div>
<!-- ./main/main.default.html end -->




<!-- admin.bottom.html start-->
</div>
</body>
</html>
<!-- admin.bottom.html end-->