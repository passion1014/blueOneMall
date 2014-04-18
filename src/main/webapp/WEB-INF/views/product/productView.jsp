<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import  url="../inc/topSub.jsp" />

<body>
<div class="wrap">
	<!--  header 시작   -->
	<c:import url="../inc/header.jsp"/>
	<!--  header 끝   -->

	<div class="container">
	
		<c:import url="../inc/productLnb.jsp"/>
		
		<div class="sub_content">
			<form action="/order/cartList.do" method="post">
			<input type="hidden" id="prdCd"  name="prdCd"  value="${pro.prdCd}">
				<div class="product_view">
					<span class="locat_box">Home&nbsp;>&nbsp;
					${pro.ctgLargeName}&nbsp;>&nbsp;
					<c:if test="${'' eq pro.ctgMiddleName}"><c:out value="없음"/></c:if>
					<c:if test="${'' ne pro.ctgMiddleName}"><c:out value="${pro.ctgMiddleName}"/></c:if>
					&nbsp;>&nbsp;
					<c:if test="${'' eq pro.ctgSmallName}"><c:out value="없음"/></c:if>
					<c:if test="${'' ne pro.ctgSmallName}"><c:out value="${pro.ctgSmallName}"/></c:if>
					<h4>${pro.prdCd}&nbsp;${pro.prdNm}</h4>
					<div class="view_dbox1">
						<p>
							<c:forEach items="${imgList}" var="prdImg">
								<c:if test="${'01' eq prdImg.attImgType}">
									<img src="${prdImg.attFilePath}">
								</c:if>
							</c:forEach>
						</p>
						<button><img src="<c:url value='/resources/img/common/btn_viewpro.jpg'/>" alt="자세히보기"/></button>
						<c:forEach items="${imgList}" var="prdImg">
							<c:if test="${'02' eq prdImg.attImgType && 1 eq prdImg.attImgSeq}">
								<span class="mralign"><img src="${prdImg.attFilePath}" alt="상품 작은이미지"></span>
							</c:if>
						</c:forEach>
						<c:forEach items="${imgList}" var="prdImg">
							<c:if test="${'02' eq prdImg.attImgType && 2 eq prdImg.attImgSeq}">
								<span class="mralign"><img src="${prdImg.attFilePath}" alt="상품 작은이미지"></span>
							</c:if>
						</c:forEach>
						<c:forEach items="${imgList}" var="prdImg">
							<c:if test="${'02' eq prdImg.attImgType && 3 eq prdImg.attImgSeq}">
								<span class="mralign"><img src="${prdImg.attFilePath}" alt="상품 작은이미지"></span>
							</c:if>
						</c:forEach>
						<c:forEach items="${imgList}" var="prdImg">
							<c:if test="${'02' eq prdImg.attImgType && 4 eq prdImg.attImgSeq}">
								<span><img src="${prdImg.attFilePath}" alt="상품 작은이미지"></span>
							</c:if>
						</c:forEach>
					</div>
					<div class="view_dbox2">
						<table class="detail_tbl" summary="제품에 대한 상세리스트표">
							<caption>상세리스트 목록</caption>
							<colgroup>
								<col width="25%"/>
								<col width="35%"/>
								<col width="40%"/>
							</colgroup>
							<tbody>
								<tr>
									<th>소비자 가격</th>
									<td colspan="2"><span class="textline">${pro.prdPrice} 원</span></td>
								</tr>
								<tr>
									<th>판매 가격</th>
									<td colspan="2"><strong class="stext">${pro.prdSellPrc} 원</strong></td>
								</tr>
								<tr>
									<th class="bottomline">배송비</th>
									<td colspan="2" class="bottomline">배송조건 : (조건)</td>
								</tr>
								<tr>
									<th>모델명</th>
									<td colspan="2">${pro.prdModel}</td>
								</tr>
								<tr>
									<th>브랜드</th>
									<td colspan="2">${pro.prdBrand}</td>
								</tr>
								<tr>
									<th>모델번호</th>
									<td colspan="2">${pro.prdModelNo}</td>
								</tr>
								<tr>
									<th>제조사</th>
									<td colspan="2">${pro.prdBrand}</td>
								</tr>
								<tr>
									<th class="bottomline">상품코드</th>
									<td colspan="2" class="bottomline">${pro.prdCd}</td>
								</tr>
								<tr>
									<th>옵션적용가</th>
									<td colspan="2">${pro.prdSellPrc} 원</td>
								</tr>
								<tr>
									<th>색상</th>
									<td colspan="2" >
										<select  id="prdColor" name="prdColor">
											<c:forEach var="opKey" items="${pro.optionKey}" begin="0" end="49" varStatus="i">
												<c:if test="${'01' eq opKey}"> 
													<option value="${pro.optionIdx[i.index]}" >${pro.optionValue[i.index]}</option>
      											</c:if>
      										</c:forEach>
      									</select>
									</td>
								</tr>
								<tr>
									<th>수량</th>
									<td colspan="2">
										<select id="prdMany" name="prdMany">
											<c:forEach var="i" begin="1" end="50" step="1">
												<option value="<c:out value="${i}"></c:out>"><c:out value="${i}"></c:out></option>
											</c:forEach>
										</select>	
									</td>
								</tr>
								<tr>
									<th class="bottomline">추가옵션</th>
									<td colspan="2" class="bottomline">
										<select>
											<option>옵션선택</option>
										</select>
									</td>
								</tr>
								
								<tr>
									<th class="bgcolor"></th>
									<td class="bgcolor salign">
										<select>
											<option>1</option>
										</select>
										<label>개</label>
									</td>
									<td class="bgcolor salign">
										<strong class="stext">66,700 원</strong>
									</td>
								</tr>
								
								<tr>
									<th class="bgcolor">화이트</th>
									<td class="bgcolor salign">
										<select>
											<option>1</option>
										</select>
										<label>개</label>
									</td>
									<td class="bgcolor salign">
										<strong class="stext">66,700 원</strong>
									</td>
								</tr>
								<tr>
									<th class="bgcolor bottomline">총 합계 금액</th>
									<td colspan="2" class="bgcolor salign bottomline">
										<strong class="stext">133,400 원</strong>
									</td>
								</tr>
								<tr>
									<td colspan="3" class="btn_box">
										<input type="submit" value="바로 구매하기" class="btn_buy" title="구매하기"/>
										<input type="submit" class="btn_buy" value="장바구니">
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="protextbox">
				</div>
				<div class="detail_section">
					<ul>
						<li class="leftline on">상세정보</li>
						<li class="leftline">상품평</li>
						<li class="leftline">상품 Q & A</li>
						<li class="alignline">배송/반품/교환정보</li>
					</ul>
					<p class="image_section">
						${pro.prdConts}
						<img src="<c:url value='/resources/img/product/pro_detailimg.jpg'/>" alt="제품상세이미지"/>
					</p>
				</div>
				<div class="detail_section">
					<ul>
						<li class="leftline on">상세정보</li>
						<li class="leftline">상품평</li>
						<li class="leftline">상품 Q & A</li>
						<li class="alignline">배송/반품/교환정보</li>
					</ul>
					<p class="image_section">
						<img src="<c:url value='/resources/img/product/inqriry_img.jpg'/>" alt="제품문의이미지"/>
					</p>
				</div>
				<div class="detail_section">
					<ul>
						<li class="leftline on">상세정보</li>
						<li class="leftline">상품평</li>
						<li class="leftline">상품 Q & A</li>
						<li class="alignline">배송/반품/교환정보</li>
					</ul>
					<div class="review_section">
						<h5>제품후기</h5>
						<dl class="review_textbox">
							<dt>이름</dt>
							<dd></dd>
							<dt class="clearfix">첨부</dt>
							<dd>
								<input type="file" title="첨부파일"/>
							</dd>
							<dt class="clearfix textarea">내용</dt>
							<dd class="textarea">
								<textarea></textarea>
								<button>후기쓰기</button>
							</dd>
							<dt class="clearfix">평가하기</dt>
							<dd>
								<span>
									<img src="<c:url value='/resources/img/common/bullet_lstar.png'/>" alt="큰 별 이미지"/>
									<img src="<c:url value='/resources/img/common/bullet_lstar.png'/>" alt="큰 별 이미지"/>
									<img src="<c:url value='/resources/img/common/bullet_lstar.png'/>" alt="큰 별 이미지"/>
									<img src="<c:url value='/resources/img/common/bullet_lstar.png'/>" alt="큰 별 이미지"/>
								</span>
							</dd>
						</dl>
					</div>
				</div>
				<div class="detail_section">
					<ul>
						<li class="leftline on">상세정보</li>
						<li class="leftline">상품평</li>
						<li class="leftline">상품 Q & A</li>
						<li class="alignline">배송/반품/교환정보</li>
					</ul>
					<div class="inquire_section">
						<h6>제품문의</h6>
						<table class="inquire_tbl" summary="제품문의목록표">
							<caption>제품문의목록</caption>
							<colgroup>
								<col width="10%"/>
								<col width="45%"/>
								<col width="15%"/>
								<col width="15%"/>
								<col width="15%"/>
							</colgroup>
							<thead>
								<th class="bgcolor">번호</th>
								<th>내용</th>
								<th class="bgcolor">작성자</th>
								<th>작성일</th>
								<th class="bgcolor">평가</th>
							</thead>
							<tbody>
								<tr>
									<td class="bgcolor">1</td>
									<td class="texalign"><a href="#">마음에 듭니다</a></td>
									<td class="bgcolor">abcd*****</td>
									<td>2014-01-08</td>
									<td class="bgcolor">
										<img src="<c:url value='/resources/img/common/bullet_sstar.png'/>" alt="작은별 이미지"/>
										<img src="<c:url value='/resources/img/common/bullet_sstar.png'/>" alt="작은별 이미지"/>
										<img src="<c:url value='/resources/img/common/bullet_sstar.png'/>" alt="작은별 이미지"/>
										<img src="<c:url value='/resources/img/common/bullet_sstar.png'/>" alt="작은별 이미지"/>
										<img src="<c:url value='/resources/img/common/bullet_sstar.png'/>" alt="작은별 이미지"/>
									</td>
								</tr>
								<tr>
									<td class="bgcolor">2</td>
									<td class="texalign"><a href="#">마음에 듭니다</a></td>
									<td class="bgcolor">abcd*****</td>
									<td>2014-01-08</td>
									<td class="bgcolor">
										<img src="<c:url value='/resources/img/common/bullet_sstar.png'/>" alt="작은별 이미지"/>
										<img src="<c:url value='/resources/img/common/bullet_sstar.png'/>" alt="작은별 이미지"/>
										<img src="<c:url value='/resources/img/common/bullet_sstar.png'/>" alt="작은별 이미지"/>
										<img src="<c:url value='/resources/img/common/bullet_sstar.png'/>" alt="작은별 이미지"/>
										<img src="<c:url value='/resources/img/common/bullet_sstar.png'/>" alt="작은별 이미지"/>
									</td>
								</tr>
								<tr>
									<td class="bgcolor">3</td>
									<td class="texalign"><a href="#">마음에 듭니다</a></td>
									<td class="bgcolor">abcd*****</td>
									<td>2014-01-08</td>
									<td class="bgcolor">
										<img src="<c:url value='/resources/img/common/bullet_sstar.png'/>" alt="작은별 이미지"/>
										<img src="<c:url value='/resources/img/common/bullet_sstar.png'/>" alt="작은별 이미지"/>
										<img src="<c:url value='/resources/img/common/bullet_sstar.png'/>" alt="작은별 이미지"/>
										<img src="<c:url value='/resources/img/common/bullet_sstar.png'/>" alt="작은별 이미지"/>
										<img src="<c:url value='/resources/img/common/bullet_sstar.png'/>" alt="작은별 이미지"/>
									</td>
								</tr>
								<tr>
									<td class="bgcolor">4</td>
									<td class="texalign"><a href="#">마음에 듭니다</a></td>
									<td class="bgcolor">abcd*****</td>
									<td>2014-01-08</td>
									<td class="bgcolor">
										<img src="<c:url value='/resources/img/common/bullet_sstar.png'/>" alt="작은별 이미지"/>
										<img src="<c:url value='/resources/img/common/bullet_sstar.png'/>" alt="작은별 이미지"/>
										<img src="<c:url value='/resources/img/common/bullet_sstar.png'/>" alt="작은별 이미지"/>
										<img src="<c:url value='/resources/img/common/bullet_sstar.png'/>" alt="작은별 이미지"/>
										<img src="<c:url value='/resources/img/common/bullet_sstar.png'/>" alt="작은별 이미지"/>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="paging2">
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
			</form>
		</div>
	</div>
<!--  container 끝   -->	

	<div class="footer">
		<div class="footer_area">
			<h2><img src="<c:url value='/resources/img/common/footer_logo.jpg'/>" alt="현대 로고"/></h2>
			<address>
				공정거래위원회 고시 제2001-1호에 따른 사업자 등록번호:212-81-86027ㅣ대표이사 : 김화웅<br/>
				개인정보관리 책임자 법인사업부 법인영업1팀 송선호 부장 l 주소:서울시 강동구 암사동 513-16번지 현대H&S<br/>
				COPYRIGHT 2012 BY 현대H&S ALL RIGHT RESERVED.
			</address>
		</div>
	</div>
</div>
</body>
</html>
