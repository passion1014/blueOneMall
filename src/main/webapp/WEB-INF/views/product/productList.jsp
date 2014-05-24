<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<c:import  url="../inc/topSub.jsp" />

<body>
<div class="wrap">
	<c:import url="../inc/header.jsp"/>

	<div class="container">
		<c:import url="../inc/productLnb.jsp"/>
		
		<div class="sub_content">
			<div class="slide_section">
				<img src="${largeInf.largeImgPath}" alt="서브 메인이미지"/>
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
					<p class="pro_total">총&nbsp;<span>${total}&nbsp;개</p>
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
							<li class="mlalign" style="padding:20px 0 20px 0;">
								<a href="javascript:location.href='productView.do?prdCd=${prdList.prdCd}&ctgCode=${largeInf.ctgCode}&ctgMiddleCode=${chkMiddleCode}';">
								<dl class="list_product">
									<dd><img src="${prdList.attFilePath}" alt="product image"  width="166" height="166" /></dd>
									<dd>${prdList.prdBrand}</dd>
									<dd>${prdList.prdNm}</dd>
									<dd>${prdList.prdPrice}&nbsp;↓&nbsp;<span>${prdList.prdSellPrc}원</span></dd>
								</dl>
								</a>
							</li>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<div style="width:100%;height:200px;text-align:center;padding-top:200px;">상품이 없습니다</div>
						</c:otherwise>	
					</c:choose>		
					</ul>
					<div class="paging" style="padding-bottom:100px;">
						<a href="javascript:void(0);" class="palign1"><img src="<c:url value='/resources/img/common/btn_first.gif'/>" alt="처음으로"></a>
						<a href="javascript:void(0);" class="palign2"><img src="<c:url value='/resources/img/common/btn_prev.gif'/>" alt="이전"></a>
						<c:forEach var="i" begin="1" end="${endNum}">
							<a href="productList.do?ctgCode=${categoryInfo.ctgCode}&ctgMiddleCode=${categoryInfo.ctgMiddleCode}&prdCtgS=${productInfo.prdCtgS}&page=${i}">${i}</a>
						</c:forEach>
						<a href="javascript:void(0);" class="palign1"><img src="<c:url value='/resources/img/common/btn_next.gif'/>" alt="다음"></a>
						<a href="javascript:void(0);" class="palign2"><img src="<c:url value='/resources/img/common/btn_end.gif'/>" alt="끝으로"></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--  container 끝   -->	
</div>
</body>

<c:import url="../inc/footer.jsp" />