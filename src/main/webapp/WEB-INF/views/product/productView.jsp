<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import  url="../inc/topSub.jsp" />

<script type="text/javascript">
<!--

function chk_shopForm(k){
	
	document.getElementById("buyType").value = k ;

	if(!$("#buyType").val()){
		alert("정보가 옳바르지 않습니다.");
		return false ;
	}

	if($("#sellPrice").val() <= 0){
		alert("금액이 옳바르지 않습니다");
		return false;
	}

	if($("#buyCnt").val() <= 0){
		alert("구매 수량이 옳바르지 않습니다");
		return false;
	}

	if(${pro.prdColor eq 'y'}){

		if($("#prdOpColor_ch").val() =='ch_color'){
			alert("색상을 선택해주세요");
			return false;
		}

		document.getElementById("prdOpColor").value   = encodeURI(document.getElementById("prdOpColor_ch").value) ;	

	}

	
	if(${pro.prdSize eq 'y'}){
	
		if($("prdOpSize_ch").val() =='ch_size'){
			alert("크기를 선택해주세요");
			return false;
		}

		document.getElementById("prdOpSize").value   = encodeURI(document.getElementById("prdOpSize_ch").value) ;	

	}
	



	document.getElementById("prdfm").submit() ;
}

function SetPriceInput(str)
{
	str=str.replace(/,/g,'');
	var retValue = "";
	for(i=1; i<=str.length; i++)
	{
		if(i > 1 && (i%3)==1) 
			  retValue = str.charAt(str.length - i) + "," + retValue;
		else 
			  retValue = str.charAt(str.length - i) + retValue;    
	}
	document.write(retValue); 
}

function SetPriceView(str)
{
	str=str.replace(/,/g,'');
	var retValue = "";
	for(i=1; i<=str.length; i++)
	{
		if(i > 1 && (i%3)==1) 
			  retValue = str.charAt(str.length - i) + "," + retValue;
		else 
			  retValue = str.charAt(str.length - i) + retValue;    
	}
	return retValue ; 
}

function sumPrice(n){
	price = $("#unitPrice").val();
	sell_price = parseInt( price * n ) ;

	$("#sellPrice").val(sell_price);

	var resultPrice = SetPriceView(""+sell_price) ;

	document.getElementById("sellPriceView").innerHTML= resultPrice ;
}

function writeUserName(val){
	var nameObj = val.split('_');
	document.write(nameObj[1]);
}

function chgImage(ref){
	$("#MainImg").attr("src",ref);
	$("#prdLayerImg").val(ref) ;
}

//-->
</script>

<body>
<div class="wrap">
	<!--  header 시작   -->
	<c:import url="../inc/header.jsp"/>
	<!--  header 끝   -->
	<div class="container">

		<c:import url="../inc/productLnb.jsp"/>

		<div class="sub_content">
			<div class="product_view">
				<form method="post" id="prdfm" name="prdfm" action="/order/cartList.do">
				<input type="hidden" id="prdCd"  name="prdCd"  value="${pro.prdCd}">
				<input type="hidden" id="prdNm"  name="prdNm"  value="${pro.prdNm}">
				<input type="hidden" id="unitPrice"  name="unitPrice"  value="${pro.prdSellPrc}">
				<input type="hidden" id="sellPrice"  name="sellPrice"  value="${pro.prdSellPrc}">
				<input type="hidden" id="buyType"  name="buyType"  value="">
				<input type="hidden" id="prdOpColor"  name="prdOpColor"  value="">
				<input type="hidden" id="prdOpSize"  name="prdOpSize"  value="">
				


					<span class="locat_box">Home&nbsp;>&nbsp;${pro.ctgLargeName}&nbsp;>&nbsp;
					<c:if test="${'' eq pro.ctgMiddleName}"><c:out value="없음"/></c:if>
					<c:if test="${'' ne pro.ctgMiddleName}"><c:out value="${pro.ctgMiddleName}"/></c:if>
					&nbsp;>&nbsp;
					<c:if test="${'' eq pro.ctgSmallName}"><c:out value="없음"/></c:if>
					<c:if test="${'' ne pro.ctgSmallName}"><c:out value="${pro.ctgSmallName}"/></c:if>
					<h4>${pro.prdNm}</h4>
					<div class="view_dbox1" style="text-align:center;">
						<p style="width:378px;height:379px;">
						<c:forEach items="${imgList}" var="prdImg">
							<c:if test="${'01' eq prdImg.attImgType}">
								<img id="MainImg" src="${prdImg.attFilePath}"  width="378" height="379">
								<input type="hidden" id="prdSmallImg"  name="prdSmallImg"  value="${prdImg.attFilePath}">
								<input type="hidden" id="prdLayerImg"  name="prdLayerImg"  value="${prdImg.attFilePath}">
							</c:if>
						</c:forEach>
						</p>
						<div class="detail_button" ><img src="<c:url value='/resources/img/common/btn_viewpro.jpg'/>" alt="자세히보기" onClick="dialogImageForm(document.getElementById('prdLayerImg').value);" style="cursor:pointer;"/></div>
						<c:forEach items="${imgList}" var="prdImg">
							<c:if test="${'02' eq prdImg.attImgType && 1 eq prdImg.attImgSeq}">
								<span class="mralign"><img src="${prdImg.attFilePath}" alt="상품 작은이미지"  width="87" height="87" style="cursor:pointer;" onMouseOver="chgImage('${prdImg.attFilePath}');"></span>
							</c:if>
						</c:forEach>
						<c:forEach items="${imgList}" var="prdImg">
							<c:if test="${'02' eq prdImg.attImgType && 2 eq prdImg.attImgSeq}">
								<span class="mralign"><img src="${prdImg.attFilePath}" alt="상품 작은이미지"  width="87" height="87" style="cursor:pointer;" onMouseOver="chgImage('${prdImg.attFilePath}');"></span>
							</c:if>
						</c:forEach>
						<c:forEach items="${imgList}" var="prdImg">
							<c:if test="${'02' eq prdImg.attImgType && 3 eq prdImg.attImgSeq}">
								<span class="mralign"><img src="${prdImg.attFilePath}" alt="상품 작은이미지"  width="87" height="87" style="cursor:pointer;" onMouseOver="chgImage('${prdImg.attFilePath}');"></span>
							</c:if>
						</c:forEach>
						<c:forEach items="${imgList}" var="prdImg">
							<c:if test="${'02' eq prdImg.attImgType && 4 eq prdImg.attImgSeq}">
								<span><img src="${prdImg.attFilePath}" alt="상품 작은이미지"  width="87" height="87" style="cursor:pointer;" onMouseOver="chgImage('${prdImg.attFilePath}');"></span>
							</c:if>
						</c:forEach>
					</div>
					<div class="view_dbox2">
						<table class="detail_tbl" summary="제품에 대한 상세리스트표">
							<caption>상세리스트 목록</caption>
							<colgroup>
								<col width="30%"/>
								<col width="50%"/>
								<col width="20%"/>
							</colgroup>
							<tbody>
								<tr bgcolor="#f8f8f8">
									<th>소비자 가격</th>
									<td colspan="2"><span class="textline"><script>SetPriceInput('${pro.prdPrice}');</script> 원</span></td>
								</tr >
								<tr bgcolor="#f8f8f8">
									<th>판매 가격</th>
									<td colspan="2"><strong class="stext2"><script>SetPriceInput('${pro.prdSellPrc}');</script> 원</strong></td>
								</tr>
								<!--
								<tr bgcolor="#f8f8f8">
									<th class="bottomline">배송비</th>
									<td colspan="2" class="bottomline"><script>SetPriceInput('${config.buyPrice}');</script>원 이하 구매 시 <script>SetPriceInput('${config.trasferPrice}');</script>원 추가</td>
								</tr>
								-->
								<tr bgcolor="#f8f8f8">
									<th class="bottomline">배송비</th>
									<td colspan="2" class="bottomline">무료배송</td>
								</tr>
								<tr>
									<th>상품코드</th>
									<td colspan="2">${pro.prdCd}</td>
								</tr>
								<tr>
									<th>모델명</th>
									<td colspan="2">${pro.prdModel}</td>
								</tr>
								<tr>
									<th>모델번호</th>
									<td colspan="2">${pro.prdModelNo}</td>
								</tr>
								<tr>
									<th>브랜드</th>
									<td colspan="2">${pro.prdBrand}</td>
								</tr>
								<tr>
									<th class="bottomline">제조사</th>
									<td colspan="2" class="bottomline">${pro.prdBrand}</td>
								</tr>
								
								<c:if test="${pro.prdColor eq 'y'}">
								<tr bgcolor="#f8f8f8">
									<th>색상</th>
									<td colspan="2" >
										<select  id="prdOpColor_ch" name="prdOpColor_ch">
											<option value="ch_color">색상을 선택해주세요</option>
											<c:forEach var="opKey" items="${pro.optionKey}" begin="0" end="49" varStatus="i">
												<c:if test="${'01' eq opKey}"> 
													<option value="${pro.optionValue[i.index]}" >${pro.optionValue[i.index]}</option>
      									</c:if>
      								</c:forEach>
      							</select>
									</td>
								</tr>
								</c:if>

								<c:if test="${pro.prdSize eq 'y'}">
								<tr bgcolor="#f8f8f8">
									<th>크기</th>
									<td colspan="2" >
										<select id="prdOpSize_ch" name="prdOpSize_ch">
											<option value="ch_size">크기를을 선택해주세요</option>
											<c:forEach var="opKey" items="${pro.optionKey}" begin="0" end="49" varStatus="i">
												<c:if test="${'02' eq opKey}"> 
													<option value="${pro.optionValue[i.index]}" >${pro.optionValue[i.index]}</option>
												</c:if>
											</c:forEach>
										</select>
									</td>
								</tr>
								</c:if>
								<tr bgcolor="#f8f8f8">
									<th>수량</th>
									<td colspan="2">
										<c:choose>
											<c:when test="${pro.prdStock > 0}"> (재고수량 : ${pro.prdStock} ) &nbsp;
												<!--
												<select id="buyCnt" name="buyCnt" onChange="sumPrice(this.value);">
													<c:forEach var="i" begin="1" end="50" step="1">
														<option value="<c:out value="${i}"></c:out>" <c:if test="${i==1 }">selected</c:if>><c:out value="${i}"></c:out></option>
													</c:forEach>
												</select>
												-->
												<input type="text" id="buyCnt" name="buyCnt" value="1" style="border:2px solid #f00;width:30px;text-align:right;padding-right:10px;" onBlur="sumPrice(this.value);" onkeypress="numKeyOnly();">
											</c:when>
											<c:otherwise>
												품절
											</c:otherwise>
										</c:choose>
												
									</td>
								</tr>
								<tr>
									<th   class="bgcolor bottomline2">총 합계 금액</th>
									<td colspan="2" class="bgcolor salign bottomline2">
										<strong class="stext"><span id="sellPriceView"><script>SetPriceInput('${pro.prdSellPrc}');</script></span> 원</strong>
									</td>
								</tr>
								
								<!--
								<tr>
									<td colspan="3" class="btn_box">
										<input type="button" value="바로 구매하기" onClick="list_Submit()" class="btn_buy" title="구매하기"/>
										<input type="submit" class="btn_buy" value="장바구니">
									</td>
								</tr>
								-->

								<c:choose>
									<c:when test="${pro.prdStock > 0}">
										<tr>
											<td colspan="3" class="btn_box">
												<input type="button" value="구매하기" class="btn_cart" title="구매하기" onClick="chk_shopForm('direct')" style="cursor:pointer;"/>
												<input type="button" value="장바구니" class="btn_buy" title="장바구니" onClick="chk_shopForm('cart')" style="cursor:pointer;">
											</td>
										</tr>
									</c:when>
									<c:otherwise>
										<tr>
											<td colspan="3" class="btn_box">
												<input type="button" value="품절입니다" class="btn_buy" title="품절" onClick="alert('품절입니다')" style="cursor:pointer;"/>
											</td>
										</tr>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
					</div>
				</form>
				</div>
				
				<!-- <div class="protextbox"></div> -->
				
				<div id="mark1" class="detail_section">
					<ul>
						<li class="leftline on">상세정보</li>
						<li class="leftline" onClick="location.href='#mark2';">배송/반품/교환정보</li>
						<li class="leftline" onClick="location.href='#mark3';">제품후기</li>
						<li class="endline" onClick="location.href='/community/qnaWrite.do';">상품문의</li>
					</ul>
					<p class="image_section">
						${pro.prdConts}
					</p>
				</div>

				<div id="mark2" class="detail_section">
					<ul>
						<li class="leftline" onClick="location.href='#mark1';">상세정보</li>
						<li class="leftline on">배송/반품/교환정보</li>
						<li class="leftline" onClick="location.href='#mark3';">제품후기</li>
						<li class="endline" onClick="location.href='/community/qnaWrite.do';">상품문의</li>
					</ul>
					<p class="image_section">
						${pro.prdTransContents}
					</p>
				</div>

				<div id="mark3" class="detail_section">
					<ul>
						<li class="leftline" onClick="location.href='#mark1';">상세정보</li>
						<li class="leftline" onClick="location.href='#mark2';">배송/반품/교환정보</li>
						<li class="leftline on">제품후기</li>
						<li class="endline" onClick="location.href='/community/qnaWrite.do';">상품문의</li>
					</ul>
					<div class="review_section">
						<form action="/product/writeQnA.do" method="post" name="qnaform">
						<input type="hidden" name="prdCd" value="${pro.prdCd}" />
							<dl class="review_textbox">
								<dt>이름</dt>
								<dd>${customerSession.custNm}</dd>
								<dt class="clearfix textarea">내용</dt>
								<dd class="textarea">
									<textarea name="content" style="height:80px;"></textarea>
									<button>후기쓰기</button>
								</dd>
							</dl>
						</form>
					</div>
					<div class="inquire_section">
						<table class="inquire_tbl" summary="제품문의목록표">
							<caption>제품문의목록</caption>
							<colgroup>
								<col width="10%"/>
								<col width="60%"/>
								<col width="15%"/>
								<col width="15%"/>
								<%-- <col width="15%"/> --%>
							</colgroup>
							<thead>
								<th class="bgcolor">번호</th>
								<th class="bgcolor">내용</th>
								<th class="bgcolor">작성자</th>
								<th class="bgcolor">작성일</th>
							</thead>
							<tbody>
							<c:forEach items="${qnaList}" var="qna">
								<tr>
									<td class="bgcolor">${qna.brdSeq}</td>
									<td class="texalign"><a href="#">${qna.content}</a></td>
									<td class="bgcolor"><script>writeUserName('${qna.insUser}');</script></td>
									<td>${qna.insDt}</td>
								</tr>
								<tr id="line_${qna.brdSeq}" style="display:none">
									<td colspan="4" style="text-align:left;padding:5px; 5px; 12px; 5px;">${qna.content}</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<div class="paging2">
				${pageHtml}
					<%-- <a href="#" class="palign1"><img src="<c:url value='/resources/img/common/btn_first.gif'/>" alt="처음으로"></a>
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
					<a href="#" class="palign2"><img src="<c:url value='/resources/img/common/btn_end.gif'/>" alt="끝으로"></a> --%>
				</div>
		</div>
	</div>
<!--  container 끝   -->	

	
</div>

<div id="dialog-image-form"><img id="layerImage" src="${prdImg.attFilePath}"></div>

<c:import url="../inc/footer.jsp" />


<script language="JavaScript" type="text/JavaScript">
<!--
$(document).ready(function() {

	$("#dialog-image-form").dialog({
		autoOpen: false,
		closeOnEscape: false,
		draggable: false,
		modal: true,
		resizable: false,
		show: 'fade',
		hide: 'fade',
		title: '',
		width: 'auto',
		create: function(event, ui) {},
		open: function(event, ui) {},
		close: function(event, ui) {}
	});

});


function openImageDialog() {
	$("#dialog-image-form").dialog("open");
}

function closeImageDialog() {
	$("#dialog-image-form").dialog("close");
}

function dialogImageForm(imgUrl) {
	$("#layerImage").attr("src",imgUrl);
	openImageDialog();
}

//-->
</script>
