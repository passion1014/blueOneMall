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
		<div class="pro_content">
			<div>
				<p><img src="<c:url value='/resources/img/product/search_banner.jpg'/>" alt="서치페이지 메인베너"/></p>
			</div>
			<div class="pro_contbox">
					<c:choose>
						<c:when test="${prdList.size() != 0}">
						<c:forEach items="${prdList}" var="prd">	
							<div class="cont_box">
								<p class="pro_contlocation">${prd.ctgLargeName}&nbsp;>&nbsp;${prd.ctgMiddleName}&nbsp;>&nbsp;${prd.ctgSmallName}</p>
								<dl>
									<dt>
										${prd.prdNm} <span class="tit_code">(${prd.prdCd})</span>
									</dt>
									<dd class="pro_imgarea">
										<img src="<c:url value='${prd.attFilePath}'/>"
											alt="상품이미지" />
									</dd>
									<dd class="pro_textarea">
										<span><strong>제조사:</strong>${prd.prdBrand}</span>
									</dd>
									<dd class="pro_textarea">상품의 따른 설명부분입니다.</dd>
									<dd class="pro_linkarea">
										<a href="javascript:location.href='productView.do?prdCd=${prd.prdCd}';">상품보기</a>
									</dd>
								</dl>
							</div>
						</c:forEach>
						</c:when>
						<c:otherwise>
							<h1>검색결과가없습니다.</h1>
						</c:otherwise>
					</c:choose>
			</div>
			<div class="paging2">
				<a href="#" class="palign1"><img src="<c:url value='/resources/img/common/btn_first.gif'/>" alt="처음으로"></a>
				<a href="#" class="palign2"><img src="<c:url value='/resources/img/common/btn_prev.gif'/>" alt="이전"></a>
				
				<c:forEach var="i" begin="1" end="${endNum}">
					<a href="searchProduct.do?page=${i}&schWord=${schWord}">${i}</a>	
				</c:forEach>
				<a href="#" class="palign1"><img src="<c:url value='/resources/img/common/btn_next.gif'/>" alt="다음"></a>
				<a href="#" class="palign2"><img src="<c:url value='/resources/img/common/btn_end.gif'/>" alt="끝으로"></a>
			</div>
		</div>
	</div>
<!--  container 끝   -->	

	<c:import url="../inc/footer.jsp" />
</div>
</body>
</html>
