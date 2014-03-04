<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c_rt"%> 
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
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








<!-- admin.header.html start -->
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
			<li><a href="getAdminList.do">환경설정</a></li>
			<li><a href="#">회원관리</a></li>
			<li><a href="#">상품관리</a></li>
			<li><a href="#">주문관리</a></li>
			<li><a href="#">커뮤니티</a></li>
		</ul>
	</div>
<!-- admin.gnb.html end -->













<!-- admin.lnb_none.html start -->
<div id="LNB">

	<div style="display:block;">
		<h1>환경설정</h1>
		<ul>
			<h2>SITE 환경설정</h2>
			<li><a href="#">정보관리</a></li>
		</ul>
		<ul>
			<h2>통장관리</h2>
			<li><a href="#">통장목록</a></li>
		</ul>
		<ul>
			<h2>운영자 관리</h2>
			<li><a href="getAdminList.do">운영자 목록</a></li>
			<li><a href="registAdminForm.do">운영자 등록</a></li>
		</ul>
	</div>

</div>


<!-- admin.lnb_none.html end -->










<!-- ./conf/conf.admin_list.html start -->
<div id="Contents">
	<h1>환경설정 &gt; 운영자관리 &gt; <strong>운영자목록</strong></h1>
	<table>
		<colgroup>
			<col width="4%" />
			<col width="7%" />
			<col width="10%" />
			<col width="*" />
			<col width="7%" />
			<col width="7%" />
			<col width="7%" />
			<col width="7%" />
			<col width="7%" />
			<col width="6%" />
			<col width="10%" />
		</colgroup>
		<tr>
			<th rowspan="2">No</th>
			<th rowspan="2">상태</th>
			<th rowspan="2">ID</th>
			<th rowspan="2">이름</th>
			<th colspan="5">관리권한</th>
			<th rowspan="2">로그<br />횟수</th>
			<th rowspan="2">등록일</th>
		</tr>
		<tr>
			<th>환경설정</th>
			<th>회원관리</th>
			<th>상품관리</th>
			<th>주문관리</th>
			<th>커뮤니티</th>
		</tr>
    <c:forEach items="${list}" var="info">
		<tr onClick="line_detail('1')" style="cursor:pointer;">
			<td><b>${info.idx}</b></td>
			<td>0</td>
			<td>0</td>
			<td>0</td>
			<td>○</td>
			<td>○</td>
			<td>○</td>
			<td>○</td>
			<td>○</td>
			<td>212</td>
			<td>2014.02.15</td>
		</tr>
		</c:forEach>
		<tr id="line_1" style="display:none;">
			<td colspan="13" style="padding:5px 5px 30px 50px;" class="right">
						
					<table>
						<colgroup>
							<col width="15%" align="center" bgcolor="#F7F7F7" />
							<col width="35%" />
							<col width="15%" align="center" bgcolor="#F7F7F7" />
							<col width="*" />
						</colgroup>
						<tr>
							<th>상태</th>
							<td colspan="3" class="left"><b><u><?=$_row["ast"]?></u></b> &nbsp;&nbsp; [ 총 : 222 회,  최근 로그인 시간 : 2015.15.21  ]</td>
						</tr>
						<tr>
							<th>관리자 정보</th>
							<td class="left"><?=$_row["name"]?> ( ID : )&nbsp;&nbsp;</td>
							<th>PASS</th>
							<td class="left"><?=$_row["password"]?></td>
						</tr>
						<tr>
							<th>연락처</th>
							<td class="left"><?=stripslashes($_row["phone"])?></td>
							<th>핸드폰</th>
							<td class="left"><?=stripslashes($_row["mobile"])?></td>
						</tr>
						<tr>
							<th>Email</th>
							<td colspan="3" class="left"><?=stripslashes($_row["admin_email"])?></td>
						</tr>
						<tr>
							<th>관리등급</th>
							<td colspan="3" style="padding:5px;">
								<table width="100%">
									<colgroup>
										<col width="35%" />
										<col width="15%" />
										<col width="35%" />
										<col width="*"   />
									</colgroup>
									<tr>
										<th>환경관리</th>
										<td>○</td>
										<th>회원관리</th>
										<td>○</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th>관리정보</th>
							<td colspan="3" style="padding:5px 10px 5px 10px;" class="left"><?=nl2br(stripslashes($_row["admin_cmt"]))?>&nbsp;</td>
						</tr>
					</table>
					<div style="margin-top:10px;" class="right">
						<input type="button" value="정보수정" class="button button_green button_medium" onClick="location.href='./admin.conf.php?slot=conf&type=admin_update&idx='"> &nbsp;
						<input type="button" value="삭제" class="button button_red button_medium" onClick="confirm_process('actionForm','관리자 정보를 삭제하시겠습니까? \n\n삭제후에는 복구가 불가능합니다. \n\n상태를 중지 시키면 권한을 박탈할 수 있습니다. \n\n그래도 삭제하시겠습니까?','');">
					</div>
						

			</td>
		</tr>



	</table>

	<div id="Paser"> 1 | 2 | 3</div>

</div>
<!-- ./conf/conf.admin_list.html end -->
<!--jeonseongho  -->















</div>
</body>
</html>

