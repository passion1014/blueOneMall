<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<c:import url="../inc/header.jsp"/>
<!--  header 끝   -->

	<div class="container">
	
		<c:import url="../inc/productLnb.jsp"/>
		
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
						<c:forEach var="list" items="${list}">
							<li><a href="#">
								<dl class="list_product">
									<dd>${list.prdPrice}<img src="<c:url value='/resources/img/sub/sub_proimg1.jpg'/>" alt="product image"/></dd>
									<dd></dd>
									<dd>BUS AG USB 케이블</dd>
									<dd>510,000&nbsp;↓&nbsp;<span>200,000원</span></dd>
								</dl>
							</a></li>
							
						</c:forEach>	
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
	<c:import url="../inc/footer.jsp" />
</div>
</body>
</html>


