<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import  url="../inc/topSub.jsp" />

<body>
<div class="wrap">

	<!--  header 시작   -->
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
								<dt> ${prd.prdNm} <span class="tit_code">(${prd.prdCd})</span> </dt>
								<dd class="pro_imgarea">
									<img src="<c:url value='${prd.attFilePath}'/>" width="125" alt="상품이미지" />
								</dd>
								<dd class="pro_textarea">
									<span><strong>제조사:</strong>${prd.prdBrand}</span>
								</dd>
								<dd class="pro_textarea">${prd.prdListCmt}</dd>
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
