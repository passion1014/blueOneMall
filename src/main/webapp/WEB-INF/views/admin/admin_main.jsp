<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- admin.top.html start -->

<!doctype html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=1280; user-scalable=yes">
<meta name="apple-touch-fullscreen" content="yes">
<meta name="apple-mobile-web-app-capable" content="yes">
<title>:: ADMIN ::</title>
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/admin/basic.css'/>" />
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/admin/admin.css'/>" />

<script type="text/javascript" src="<c:url value='/resources/js/admin/js_ajax.js'/>"> </script>
<script type="text/javascript" src="<c:url value='/resources/js/admin/js_common.js'/>"> </script>
<script type="text/javascript" src="<c:url value='/resources/js/admin/js_admin.js'/>"> </script>






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
			<b>��ü������</b> ���� �α����ϼ̽��ϴ�. <input type="button" value="�α׾ƿ�" onClick="location.href='./admin.login.html';" style="cursor:pointer;"/>
		</div>
	</div>
<!-- admin.header.html end -->


<!-- admin.gnb.html start -->
	<div id="GNB">
		<ul>
			<li><a href="getAdminList.do">ȯ�漳��</a></li>
			<li><a href="#">ȸ������</a></li>
			<li><a href="#">��ǰ����</a></li>
			<li><a href="#">�ֹ�����</a></li>
			<li><a href="#">Ŀ�´�Ƽ</a></li>
		</ul>
	</div>
<!-- admin.gnb.html end -->


<!-- admin.lnb_none.html start -->
	<div id="LNB" style="display:none;">
		<h1>�����޴�</h1>
		<ul>
			<h2>&nbsp;</h2>
			<span style="line-height:400px;">&nbsp;</span>
		</ul>
	</div>
<!-- admin.lnb_none.html end -->



<!-- ./main/main.default.html start -->
	<div style="text-align:center;color:#fff;background:#2d80a3;;width:300px;height:110px;margin:150px auto;padding-top:50px;border-bottom:2px solid #00395a;">
		<span style="font-weight:bold">�����</span> �����ڸ���Դϴ�.<br><br>���Ͻô� �����޴��� ������ �ּ���
	</div>
<!-- ./main/main.default.html end -->




<!-- admin.bottom.html start-->
</div>
</body>
</html>
<!-- admin.bottom.html end-->