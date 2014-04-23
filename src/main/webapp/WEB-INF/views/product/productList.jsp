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
	<form> </form>
		<c:import url="../inc/productLnb.jsp"/>
		
		<div class="sub_content">
			<div class="slide_section">
				<img src="<c:url value='/resources/img/sub/sub_titleimg1.jpg'/>" alt="서브 메인이미지"/>
			</div>
			<div class="cont_section">
				<ul class="cont_list">
					<c:choose>					
						<c:when test="${chkMiddleCode != null}">
							<c:set var="whenCount" value="1"></c:set>
							<c:forEach items="${lnbSList}" var="lnbSList">
								<c:if test="${whenCount == 1}">
									<c:set var="whenClass" value=""></c:set>
								</c:if>
								<c:if test="${whenCount != 1}">
									<c:set var="whenClass" value="leftline"></c:set>
								</c:if>
								
								<li>
									<a href="productList.do?ctgCode=${largeInf.ctgCode}&ctgMiddleCode=${lnbSList.ctgPCode}&prdCtgS=${lnbSList.ctgCode}" class="${whenClass}">
										<c:out value="${lnbSList.ctgName}"></c:out>
									</a>
								</li>
								<c:set var="whenCount" value="${whenCount + 1}"></c:set>	
							</c:forEach>
						</c:when>
						
						<c:otherwise>
							<c:set var="whenCount" value="1"></c:set>
							<c:forEach items="${lnbList}" var="lnbList">
								<c:if test="${whenCount == 1}">
									<c:set var="whenClass" value=""></c:set>
								</c:if>
								<c:if test="${whenCount != 1}">
									<c:set var="whenClass" value="leftline"></c:set>
								</c:if>
								<li>
									<a href="productList.do?ctgCode=${largeInf.ctgCode}&ctgMiddleCode=${lnbList.ctgCode}" class="${whenClass}">
									
										<c:out value="${lnbList.ctgName}"></c:out>
									</a>	
								</li>
								<c:set var="whenCount" value="${whenCount + 1}"></c:set>		
							</c:forEach>
						</c:otherwise>
								 
					</c:choose>
				</ul>
				
				<div class="product_section">
					<p class="pro_total">
						총&nbsp;<span>700</span>&nbsp;중&nbsp;<span>100</span>&nbsp;개
					</p>
							<span class="pro_class">
											<a href="productList.do?orderBy=low&ctgCode=${largeInf.ctgCode}&ctgMiddleCode=${chkMiddleCode}&prdCtgS=${prdCtgS}" class="rightline">낮은 가격</a>
											<a href="productList.do?orderBy=high&ctgCode=${largeInf.ctgCode}&ctgMiddleCode=${chkMiddleCode}&prdCtgS=${prdCtgS}" class="rightline">높은 가격</a>
											<a href="productList.do?orderBy=name&ctgCode=${largeInf.ctgCode}&ctgMiddleCode=${chkMiddleCode}&prdCtgS=${prdCtgS}" class="rightline">제품명</a>
											<a href="productList.do?orderBy=brd&ctgCode=${largeInf.ctgCode}&ctgMiddleCode=${chkMiddleCode}&prdCtgS=${prdCtgS}">제조사순</a>
							</span>
					<ul class="product_list">
						<c:choose>	
							<c:when test="${prdList.size() != 0}">
								<c:forEach var="prdList" items="${prdList}">
									<li class="mlalign">
										<a href="javascript:location.href='productView.do?prdCd=${prdList.prdCd}';">
											<dl class="list_product">
												<dd><img src="${prdList.attFilePath}" alt="product image"/></dd>
												<dd>${prdList.prdBrand}</dd>
												<dd>${prdList.prdNm}</dd>
												<dd>${prdList.prdPrice}&nbsp;↓&nbsp;<span>${prdList.prdSellPrc}원</span></dd>
											</dl>
										</a>
									</li>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<span>없습니다</span>
							</c:otherwise>	
						</c:choose>		
					</ul>
							
							
							
							
					<%-- <ul class="product_list">
						<c:choose>
							<c:when test="${prdLList.size() != 0 && chkMiddleCode == null && prdCtgS == null}">
								<c:forEach var="prdLList" items="${prdLList}">
									<li class="mlalign">
										<a href="javascript:location.href='productView.do?prdCd=${prdLList.prdCd}';">
											<dl class="list_product">
												<dd><img src="${prdLList.attFilePath}" alt="product image"/></dd>
												<dd>${prdLList.prdBrand}</dd>
												<dd>${prdLList.prdNm}</dd>
												<dd>${prdLList.prdPrice}&nbsp;↓&nbsp;<span>${prdLList.prdSellPrc}원</span></dd>
											</dl>
										</a>
									</li>
								</c:forEach>
							</c:when>
							<c:when test="${prdLList.size() != 0 && chkMiddleCode != null && prdCtgS == null}" >
								<c:forEach var="prdMList" items="${prdMList}">
									<li class="mlalign">
										<a href="javascript:location.href='productView.do?prdCd=${prdMList.prdCd}';">
											<dl class="list_product">
												<dd><img src="${prdMList.attFilePath}" alt="product image"/></dd>
												<dd>${prdMList.prdBrand}</dd>
												<dd>${prdMList.prdNm}</dd>
												<dd>${prdMList.prdPrice}&nbsp;↓&nbsp;<span>${prdMList.prdSellPrc}원</span></dd>
											</dl>
										</a>
									</li>
								</c:forEach>
							</c:when>
							<c:when test="${prdLList.size() != 0 && chkMiddleCode != null && prdCtgS != null}" >
								<c:forEach var="prdSList" items="${prdSList}">
									<li class="mlalign">
										<a href="javascript:location.href='productView.do?prdCd=${prdSList.prdCd}';">
											<dl class="list_product">
											
												<dd><img src="${prdSList.attFilePath}" alt="product image"/></dd>
												<dd>${prdSList.prdBrand}</dd>
												<dd>${prdSList.prdNm}</dd>
												<dd>${prdSList.prdPrice}&nbsp;↓&nbsp;<span>${prdSList.prdSellPrc}원</span></dd>
											</dl>
										</a>
									</li>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<span></span>
							</c:otherwise>	
						</c:choose>		
					</ul> --%>
					<div class="paging">
						<a href="#" class="palign1"><img src="<c:url value='/resources/img/common/btn_first.gif'/>" alt="처음으로"></a>
						<a href="#" class="palign2"><img src="<c:url value='/resources/img/common/btn_prev.gif'/>" alt="이전"></a>
						<c:forEach var="i" begin="1" end="${endNum}">
							<a href="productList.do?page=${i}">${i}</a>	
						</c:forEach>
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


