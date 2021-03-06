<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import  url="../inc/topMain.jsp" />
<script type="text/javascript">
<!--
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

function setCookie( name, value, expiredays ) {  
	var todayDate = new Date();  
	todayDate.setDate( todayDate.getDate() + expiredays );  
	document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";" 
}  

function closeWin() {  
	if (document.notice_form.chkbox.checked){  
		setCookie( "maindiv", "done" , 1 );  
	}  
	document.all['divpop1'].style.display="none"; 
} 
//-->   
</script> 
<!-- POPUP --> 
<div id="divpop1" style="position:absolute; left:300px; top:150px; z-index:200;visibility:hidden;"> 
<form name="notice_form"> 
<table cellpadding=2 cellspacing=0> 
	<tr> 
		<td colspan="2" style="border:1px #548ED8 solid" width="200px" height="100px" align=center bgcolor=white valign="top"> 
			<a href="http://blueonestore.cafe24.com/community/eventView.do?brdSeq=22"><img src="/resources/img/popup20140624.jpg"></a>
		</td> 
	</tr> 
	
	<tr> 
		<td align="left" bgcolor="#444444" style="padding:0px 15px;" height="20"> 
		<input type="checkbox" name="chkbox" value="checkbox" >&nbsp;&nbsp;<span style="color: #fff;" ><b >오늘 하루 이 창을 열지 않음</b></span> 
		</td> 
		<td align="right" bgcolor="#444444" style="padding:5px;"> 
		<a href="javascript:closeWin();"><span style="color:#fff;"><b>[닫기]</B></a> 
		</td> 
	</tr>

</table>  
	</form> 
</div>   
<script language="Javascript"> 
	cookiedata = document.cookie;     
	if ( cookiedata.indexOf("maindiv=done") < 0 ){       
		document.all['divpop1'].style.visibility = "visible"; 
	}else { 
		document.all['divpop1'].style.display="none"; 
	} 
</script>

<body>
<div class="wrap">
	
	<c:import url="../inc/header.jsp" />
		
	<div class="container">
		<div class="cont_main">
	 		<div class="mcont">
				<ul class="bxslider">				
					<c:set var="mainSNumber" value="0"></c:set>				
					<c:if test="${AdImgDtl.mdImg1 != '' && AdImgDtl.mdImg1 ne NULL}">
						<li><a href="${AdImgDtl.mdUrl1}"><img src="${AdImgDtl.mdImg1}" width="665" height="440" alt="메인이미지1"/></a></li>
						<c:set var="mainSNumber" value="${mainSNumber + 1}"></c:set>						
					</c:if>
					
					<c:if test="${AdImgDtl.mdImg2 != '' && AdImgDtl.mdImg2 ne NULL}">
						<li><a href="${AdImgDtl.mdUrl2}"><img src="${AdImgDtl.mdImg2}" width="665" height="440" alt="메인이미지2"/></a></li>
						<c:set var="mainSNumber" value="${mainSNumber + 1}"></c:set>
					</c:if>
					
					<c:if test="${AdImgDtl.mdImg3 != '' && AdImgDtl.mdImg3 ne NULL}">
						<li><a href="${AdImgDtl.mdUrl3}"><img src="${AdImgDtl.mdImg3}" width="665" height="440" alt="메인이미지3"/></a></li>
						<c:set var="mainSNumber" value="${mainSNumber + 1}"></c:set>
					</c:if>
					
					<c:if test="${AdImgDtl.mdImg4 != '' && AdImgDtl.mdImg4 ne NULL}">
						<li><a href="${AdImgDtl.mdUrl4}"><img src="${AdImgDtl.mdImg4}" width="665" height="440" alt="메인이미지4"/></a></li>
						<c:set var="mainSNumber" value="${mainSNumber + 1}"></c:set>
					</c:if>
					
					<c:if test="${AdImgDtl.mdImg5 != '' && AdImgDtl.mdImg5 ne NULL}">
						<li><a href="${AdImgDtl.mdUrl5}"><img src="${AdImgDtl.mdImg5}" width="665" height="440" alt="메인이미지5"/></a></li>
						<c:set var="mainSNumber" value="${mainSNumber + 1}"></c:set>
					</c:if>
					
					<c:if test="${AdImgDtl.mdImg6 != '' && AdImgDtl.mdImg6 ne NULL}">
						<li><a href="${AdImgDtl.mdUrl6}"><img src="${AdImgDtl.mdImg6}" width="665" height="440" alt="메인이미지6"/></a></li>
						<c:set var="mainSNumber" value="${mainSNumber + 1}"></c:set>
					</c:if>
				</ul>
				
				<div id="mcont_nav">
					<ol>
						<c:set var="mcontNumber" value="0"></c:set>
						
						<c:if test="${AdImgDtl.mdImg1 != '' && AdImgDtl.mdImg1 ne NULL}">
							<li><a data-slide-index="${mcontNumber}" href="">${AdImgDtl.mdText1}</a></li>
							<c:set var="mcontNumber" value="${mcontNumber + 1}"></c:set>						
						</c:if>
						
						<c:if test="${AdImgDtl.mdImg2 != '' && AdImgDtl.mdImg2 ne NULL}">
							<li><a data-slide-index="${mcontNumber}" href="">${AdImgDtl.mdText2}</a></li>
							<c:set var="mcontNumber" value="${mcontNumber + 1}"></c:set>
						</c:if>
						
						<c:if test="${AdImgDtl.mdImg3 != '' && AdImgDtl.mdImg3 ne NULL}">
							<li><a data-slide-index="${mcontNumber}" href="">${AdImgDtl.mdText3}</a></li>
							<c:set var="mcontNumber" value="${mcontNumber + 1}"></c:set>
						</c:if>
						
						<c:if test="${AdImgDtl.mdImg4 != '' && AdImgDtl.mdImg4 ne NULL}">
							<li><a data-slide-index="${mcontNumber}" href="">${AdImgDtl.mdText4}</a></li>
							<c:set var="mcontNumber" value="${mcontNumber + 1}"></c:set>
						</c:if>
						
						<c:if test="${AdImgDtl.mdImg5 != '' && AdImgDtl.mdImg5 ne NULL}">
							<li><a data-slide-index="${mcontNumber}" href="">${AdImgDtl.mdText5}</a></li>
							<c:set var="mcontNumber" value="${mcontNumber + 1}"></c:set>
						</c:if>
						
						<c:if test="${AdImgDtl.mdImg6 != '' && AdImgDtl.mdImg6 ne NULL}">
							<li><a data-slide-index="${mcontNumber}" href="">${AdImgDtl.mdText6}</a></li>
							<c:set var="mcontNumber" value="${mcontNumber + 1}"></c:set>
						</c:if>			
					</ol>
				</div>				
			</div>
			
			
			<script type="text/javascript">
				$(document).ready(function(){
					var num = null;
					var totalwidth =665;
					var li=$("#mcont_nav ol li");
					var li_num=$("#mcont_nav ol li").get();
					num=li_num.length;
					width=totalwidth/num;
					li.css('width',width);
				});
			</script>
			
			<script type="text/javascript">
				$(document).ready(function(){
					$('.bxslider').bxSlider({
						pagerCustom: '#mcont_nav',
						auto: true,
						pause: 4000,
						autoHover: true,
						speed: 500,
						captions: false,
						prevText: '',
						nextText: ''
					});
				});
			</script>
						
			<dl class="scont1">
				
				<dd class="scont_box1"><a href="${AdImgDtl.bnUrl2}"><img src="${AdImgDtl.bnImg2}" width="153" height="303" alt="product image" style="border:1px solid #ccc;"/></a></dd>
				<dd class="scont_box2"><a href="${AdImgDtl.bnUrl1}"><img src="${AdImgDtl.bnImg1}" width="153" height="153" alt="product image" style="border:1px solid #ccc;"/></a></dd>
			</dl>
			
			<dl class="scont2">
				<dd class="scont_box1"><a href="${AdImgDtl.bnUrl3}"><img src="${AdImgDtl.bnImg3}" width="153" height="303" alt="product image" style="border:1px solid #ccc;"/></a></dd>
				<dd class="scont_box2"><a href="${AdImgDtl.bnUrl4}"><img src="${AdImgDtl.bnImg4}" width="153" height="153" alt="product image" style="border:1px solid #ccc;"/></a></dd>
			</dl>
		</div>

		<div class="cont_product">

			<div id="ep_area_tab" class="product_listbox1" style="display:block;cursor:pointer;">
				<ul class="plist_area">
					<li><a onClick="tab_view1('ep_area_tab');" class="wpadding1 on">EARPHONE</a></li>
					<li><a onClick="tab_view1('hp_area_tab');" class="wpadding2">HEADPHONE</a></li>
					<li><a onClick="tab_view1('mm_area_tab');" class="wpadding3">MULTMEDIA</a></li>
				</ul>
				<!--<div class="product_elist" style="height:580px;">-->
				<div class="product_elist">
					<ul class="pro_list">
						<c:forEach items="${epPrdList}" var="epPrdList" begin="0" end="8" step="1">
						<li>
							<a href="../product/productView.do?prdCd=${epPrdList.prdCd}&ctgCode=${epPrdList.prdCtgL}&ctgMiddleCode=${epPrdList.prdCtgM}">
							<dl class="list_product">
								<dd><img src="${epPrdList.attFilePath}" width="148" height="148" alt="prouct IMG"/></dd>
								<dd class="pro_listText">${epPrdList.prdMainNm}</dd>
								<dd class="pro_listTit">${epPrdList.prdBrand}</dd>
								
								<dd>
									<c:if test="${epPrdList.prdStock>0}">
										<span class="textline"><script>SetPriceInput('${epPrdList.prdPrice}');</script></span>▼
										<span class="area_span"><script>SetPriceInput('${epPrdList.prdSellPrc}');</script>원</span>
									</c:if>
									<c:if test="${epPrdList.prdStock==0}">
										<span>품절</span>
									</c:if>

								</dd>
							</dl>
							</a>
						</li>
						</c:forEach>
					</ul>
				</div>
			</div>


			<div id="hp_area_tab" class="product_listbox1" style="display:none;cursor:pointer;">
				<ul class="plist_area">
					<li><a onClick="tab_view1('ep_area_tab');" class="wpadding1">EARPHONE</a></li>
					<li><a onClick="tab_view1('hp_area_tab');" class="wpadding2 on">HEADPHONE</a></li>
					<li><a onClick="tab_view1('mm_area_tab');" class="wpadding3">MULTMEDIA</a></li>
				</ul>
				<div class="product_elist">
					<ul class="pro_list">
						<c:forEach items="${hpPrdList}" var="hpPrdList" begin="0" end="8" step="1">
						<li>
							<a href="../product/productView.do?prdCd=${hpPrdList.prdCd}&ctgCode=${hpPrdList.prdCtgL}&ctgMiddleCode=${hpPrdList.prdCtgM}">
							<dl class="list_product">
								<dd><img src="${hpPrdList.attFilePath}" width="148" height="148" alt="prouct IMG"/></dd>
								<dd class="pro_listText">${hpPrdList.prdMainNm}</dd>
								<dd class="pro_listTit">${hpPrdList.prdBrand}</dd>
								<dd>

									<c:if test="${hpPrdList.prdStock>0}">
										<span class="textline"><script>SetPriceInput('${hpPrdList.prdPrice}');</script></span>▼
										<span class="area_span"><script>SetPriceInput('${hpPrdList.prdSellPrc}');</script>원</span>
									</c:if>
									<c:if test="${hpPrdList.prdStock==0}">
										<span>품절</span>
									</c:if>
								
								</dd>
							</dl>
							</a>
						</li>
						</c:forEach>
					</ul>
				</div>
			</div>


			<div id="mm_area_tab" class="product_listbox1" style="display:none;cursor:pointer;">
				<ul class="plist_area">
					<li><a onClick="tab_view1('ep_area_tab');" class="wpadding1">EARPHONE</a></li>
					<li><a onClick="tab_view1('hp_area_tab');" class="wpadding2">HEADPHONE</a></li>
					<li><a onClick="tab_view1('mm_area_tab');" class="wpadding3 on">MULTMEDIA</a></li>
				</ul>
				<div class="product_elist">
					<ul class="pro_list">
						<c:forEach items="${mmPrdList}" var="mmPrdList" begin="0" end="8" step="1">
						<li>
							<a href="../product/productView.do?prdCd=${mmPrdList.prdCd}&ctgCode=${mmPrdList.prdCtgL}&ctgMiddleCode=${mmPrdList.prdCtgM}">
							<dl class="list_product">
								<dd><img src="${mmPrdList.attFilePath}" width="148" height="148" alt="prouct IMG"/></dd>
								<dd class="pro_listText">${mmPrdList.prdMainNm}</dd>
								<dd class="pro_listTit">${mmPrdList.prdBrand}</dd>
								<dd>
									<c:if test="${mmPrdList.prdStock>0}">
										<span class="textline"><script>SetPriceInput('${mmPrdList.prdPrice}');</script></span>▼
										<span class="area_span"><script>SetPriceInput('${mmPrdList.prdSellPrc}');</script>원</span>
									</c:if>
									<c:if test="${mmPrdList.prdStock==0}">
										<span>품절</span>
									</c:if>
								</dd>
							</dl>
							</a>
						</li>
						</c:forEach>
					</ul>
				</div>
			</div>


			<div id="bt_area_tab" class="product_listbox2" style="display:block;cursor:pointer;">
				<ul class="plist_area">
					<li><a onClick="tab_view2('bt_area_tab');" class="wpadding1 on">BLUETOOTH</a></li>
					<li><a onClick="tab_view2('ca_area_tab');" class="wpadding2">CASE</a></li>
					<li><a onClick="tab_view2('et_area_tab');" class="wpadding3">ETC</a></li>
				</ul>
				
				<div class="product_elist">
					<ul id="pd_area" class="pro_list">
						<c:forEach items="${pdSList}" var="pdSList" begin="0" end="8" step="1">
						<li>
							<a href="../product/productView.do?prdCd=${pdSList.prdCd}&ctgCode=${pdSList.prdCtgL}&ctgMiddleCode=${pdSList.prdCtgM}">
							<dl class="list_product">
								<dd><img src="${pdSList.attFilePath}" width="148" height="148" alt="prouct IMG"/></dd>
								<dd class="pro_listText">${pdSList.prdMainNm}</dd>
								<dd class="pro_listTit">${pdSList.prdBrand}</dd>
								<dd>
									<c:if test="${pdSList.prdStock>0}">
										<span class="textline"><script>SetPriceInput('${pdSList.prdPrice}');</script></span>▼
										<span class="area_span"><script>SetPriceInput('${pdSList.prdSellPrc}');</script>원</span>
									</c:if>
									<c:if test="${pdSList.prdStock==0}">
											<span>품절</span>
									</c:if>
								</dd>
							</dl>
							</a>
						</li>
						</c:forEach>
					</ul>
					
				</div>
			</div>

			<div id="ca_area_tab" class="product_listbox2" style="display:none;cursor:pointer;">
				<ul class="plist_area">
					<li><a onClick="tab_view2('bt_area_tab');" class="wpadding1">BLUETOOTH</a></li>
					<li><a onClick="tab_view2('ca_area_tab');" class="wpadding2 on">CASE</a></li>
					<li><a onClick="tab_view2('et_area_tab');" class="wpadding3">ETC</a></li>
				</ul>
				
				<div class="product_elist">	
					<ul id="cs_area" class="pro_list">
						<c:forEach items="${csPrdList}" var="csPrdList" begin="0" end="8" step="1">
						<li>
							<a href="../product/productView.do?prdCd=${csPrdList.prdCd}&ctgCode=${csPrdList.prdCtgL}&ctgMiddleCode=${csPrdList.prdCtgM}">
							<dl class="list_product">
								<dd><img src="${csPrdList.attFilePath}" width="148" height="148" alt="prouct IMG"/></dd>
								<dd class="pro_listText">${csPrdList.prdMainNm}</dd>
								<dd class="pro_listTit">${csPrdList.prdBrand}</dd>
								<dd>
									<c:if test="${csPrdList.prdStock>0}">
										<span class="textline"><script>SetPriceInput('${csPrdList.prdPrice}');</script></span>▼
										<span class="area_span"><script>SetPriceInput('${csPrdList.prdSellPrc}');</script>원</span>
									</c:if>
									<c:if test="${csPrdList.prdStock==0}">
											<span>품절</span>
									</c:if>
								</dd>
							</dl>
							</a>
						</li>
						</c:forEach>
					</ul>
				</div>
			</div>


			<div id="et_area_tab" class="product_listbox2" style="display:none;cursor:pointer;">
				<ul class="plist_area">
					<li><a onClick="tab_view2('bt_area_tab');" class="wpadding1">BLUETOOTH</a></li>
					<li><a onClick="tab_view2('ca_area_tab');" class="wpadding2">CASE</a></li>
					<li><a onClick="tab_view2('et_area_tab');" class="wpadding3 on">ETC</a></li>
				</ul>
				
				<div class="product_elist">
					<ul id="xt_area" class="pro_list">
						<c:forEach items="${xtPrdList}" var="xtPrdList" begin="0" end="8" step="1">
						<li>
							<a href="../product/productView.do?prdCd=${xtPrdList.prdCd}&ctgCode=${xtPrdList.prdCtgL}&ctgMiddleCode=${xtPrdList.prdCtgM}">
							<dl class="list_product">
								<dd><img src="${xtPrdList.attFilePath}" width="148" height="148" alt="prouct IMG"/></dd>
								<dd class="pro_listText">${xtPrdList.prdMainNm}</dd>
								<dd class="pro_listTit">${xtPrdList.prdBrand}</dd>
								<dd>
									<c:if test="${xtPrdList.prdStock>0}">
										<span class="textline"><script>SetPriceInput('${xtPrdList.prdPrice}');</script></span>▼
										<span class="area_span"><script>SetPriceInput('${xtPrdList.prdSellPrc}');</script>원</span>
									</c:if>
									<c:if test="${xtPrdList.prdStock==0}">
											<span>품절</span>
									</c:if>
								</dd>
							</dl>
							</a>
						</li>
						</c:forEach>
					</ul>
				</div>
			</div>

		</div>
		
		<div class="cont_banner">
				<c:if test="${AdImgDtl.bnImg5 != '' && AdImgDtl.bnImg5 ne NULL}">
					<a href="${AdImgDtl.bnUrl5}" class="marginright"><img src="${AdImgDtl.bnImg5}" width="494" height="100" alt="main banner image"/></a>
				</c:if>
				<c:if test="${AdImgDtl.bnImg6 != '' && AdImgDtl.bnImg6 ne NULL}">
					<a href="${AdImgDtl.bnUrl6}"><img src="${AdImgDtl.bnImg6}" width="494" height="100" alt="main banner image"/></a>
				</c:if>
		</div>

		<div class="cont_bottom" >
			<dl class="cont_botbox1">
				<dt ><B>입점 브랜드	</B><span>Sound Brand</span></dt>
				<dd>
					<a href="/product/searchProduct.do?schWord=LG" class="mart"><img src="<c:url value='/resources/img/main/main/lglogo.gif'/>" alt="LG전자 logo"/></a>
					<a href="/product/searchProduct.do?schWord=AKG" class="mart"><img src="<c:url value='/resources/img/main/main/akglogo.gif'/>" alt="AKG logo"/></a>
					<a href="/product/searchProduct.do?schWord=harman" class="mart"><img src="<c:url value='/resources/img/main/main/harmanlogo.gif'/>" alt="harman logo"/></a>
					<a href="/product/searchProduct.do?schWord=JBL"  class="mart"><img src="<c:url value='/resources/img/main/main/ubllogo.gif'/>" alt="UBL logo"/></a>
				</dd>
		 	</dl>
		 	<dl class="cont_botbox2" >
				<dt><B>하만 스토어	</B><span class="textbox1">진정한 사운드를 좀 더 가까운 곳에서<br/>	느끼실 수 있습니다.</span>
					<span class="textbox2">서울 특별시 강남구 청담동 85-2 JW빌딩 1층</span>
				</dt>
				<dd>
					<img src="<c:url value='/resources/img/main/main/store_img2.jpg'/>" alt="store image"/>
					<img src="<c:url value='/resources/img/main/main/store_img1.jpg'/>" alt="store image" style="margin:0 10px; " />
					<img src="<c:url value='/resources/img/main/main/store_img3.jpg'/>" alt="store image"/>
				</dd>
		 	</dl>
		</div>
		<div class="cont_notice" style="background-color:#e2e2e2">
			<dl class="notice_box">
				<dt class="notice_tit">NEWS & NOTICE</dt>
				<dd>
					<c:forEach items="${noticeList}" var="qna">
						<a class="noticeblet" href="/community/noticeView.do?brdSeq=${qna.brdSeq}"><img src="/resources/img/main/common/icon_notice.png" alt="공지" style="margin:0 5px 2px 0;">${qna.title}</a><br>
					</c:forEach>
				</dd>
			</dl>
			<span class="icon_box">
				<!--<a href="" class="icon_malign"><img src="<c:url value='/resources/img/main/main/customer_center.gif'/>" alt="customer center"/></a>-->
				<img src="<c:url value='/resources/img/main/main/customer_center.png'/>" alt="customer center"/>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="/community/qnaList.do" class="icon_malign"><img src="<c:url value='/resources/img/main/main/shopinpo2_btn1.gif'/>" alt="qa icon"/></a>
				<a href="/community/faqList.do" class="icon_malign"><img src="<c:url value='/resources/img/main/main/shopinpo2_btn2.gif'/>" alt="faq icon"/></a>
				<a href="/user/orderListView.do" class="icon_malign"><img src="<c:url value='/resources/img/main/main/shopinpo2_btn3.gif'/>" alt="order icon"/></a>
				<a href="/community/qnaList.do"><img src="<c:url value='/resources/img/main/main/shopinpo2_btn4.gif'/>" alt="customer icon"/></a>
<!-- 
				<a href="" class="icon_malign"><img src="<c:url value='/resources/img/main/main/qa_icon.gif'/>" alt="qa icon"/></a>
				<a href="/community/faqList.do" class="icon_malign"><img src="<c:url value='/resources/img/main/main/faq_icon.gif'/>" alt="faq icon"/></a>
				<a href="/user/orderListView.do" class="icon_malign"><img src="<c:url value='/resources/img/main/main/order_icon.gif'/>" alt="order icon"/></a>
				<a href=""><img src="<c:url value='/resources/img/main/main/customer_icon.gif'/>" alt="customer icon"/></a>
 -->
			</span>
		</div>
	</div>
	<!--  container 끝   -->	

	<c:import  url="../inc/footer.jsp" />

</div>
</body>

<script language="javascript">
<!--
function tab_view1(field){
	document.getElementById("ep_area_tab").style.display = "none" ;
	document.getElementById("hp_area_tab").style.display = "none" ;
	document.getElementById("mm_area_tab").style.display = "none" ;
	
	document.getElementById(field).style.display = "block" ;
}

function tab_view2(field){
	document.getElementById("bt_area_tab").style.display = "none" ;
	document.getElementById("ca_area_tab").style.display = "none" ;
	document.getElementById("et_area_tab").style.display = "none" ;
	
	document.getElementById(field).style.display = "block" ;
}
//-->
</script>