<%@ page language="java" contentType="text/html;charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c_rt"%> 
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<%
    /* ============================================================================== */
    /* =   PAGE : 결제 요청 PAGE                                                    = */
    /* = -------------------------------------------------------------------------- = */
    /* =   이 페이지는 Payplus Plug-in을 통해서 결제자가 결제 요청을 하는 페이지    = */
    /* =   입니다. 아래의 ※ 필수, ※ 옵션 부분과 매뉴얼을 참조하셔서 연동을        = */
    /* =   진행하여 주시기 바랍니다.                                                = */
    /* = -------------------------------------------------------------------------- = */
    /* =   연동시 오류가 발생하는 경우 아래의 주소로 접속하셔서 확인하시기 바랍니다.= */
    /* =   접속 주소 : http://kcp.co.kr/technique.requestcode.do			        = */
    /* = -------------------------------------------------------------------------- = */
    /* =   Copyright (c)  2013   KCP Inc.   All Rights Reserverd.                   = */
    /* ============================================================================== */
%>
<%
    /* ============================================================================== */
    /* =   환경 설정 파일 Include                                                   = */
    /* = -------------------------------------------------------------------------- = */
    /* =   ※ 필수                                                                  = */
    /* =   테스트 및 실결제 연동시 site_conf_inc.jsp 파일을 수정하시기 바랍니다.    = */
    /* = -------------------------------------------------------------------------- = */
%>
<%@ include file="/resources/kcp/site_conf_inc.jsp" %>
<%
	request.setCharacterEncoding ( "euc-kr" ) ;
    /* = -------------------------------------------------------------------------- = */
    /* =   환경 설정 파일 Include END                                               = */
    /* ============================================================================== */
%>
<%!
    /* ============================================================================== */
    /* =   null 값을 처리하는 메소드                                                = */
    /* = -------------------------------------------------------------------------- = */
        public String f_get_parm( String val )
        {
          if ( val == null ) val = "";
          return  val;
        }
    /* ============================================================================== */
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="imagetoolbar" content="no" />
<title>###### 현대프로모션몰 ######</title>
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/common.css'/>" />
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/sub.css'/>" />
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/custom-theme/jquery-ui-1.8.16.custom.css'/>" />

<script type="text/javascript" src="<c:url value='/resources/js/jquery-1.4.3.min.js'/>"> </script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery-1.7.2.min.js'/>"> </script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery-ui-1.8.16.custom.min.js'/>"> </script>
<script type="text/javascript" src="<c:url value='/resources/js/js_common.js'/>"> </script>


<link href="css/style.css" rel="stylesheet" type="text/css" id="cssLink"/>

<%
    /* ============================================================================== */
    /* =   Javascript source Include                                                = */
    /* = -------------------------------------------------------------------------- = */
    /* =   ※ 필수                                                                  = */
    /* =   테스트 및 실결제 연동시 site_conf_inc.jsp파일을 수정하시기 바랍니다.     = */
    /* = -------------------------------------------------------------------------- = */
%>
    <script type="text/javascript" src="<%= g_conf_js_url %>"></script>
<%
    /* = -------------------------------------------------------------------------- = */
    /* =   Javascript source Include END                                            = */
    /* ============================================================================== */
%>
    <script type="text/javascript">
        /* 플러그인 설치(확인) */
        StartSmartUpdate();
        
        /*  해당 스크립트는 타브라우져에서 적용이 되지 않습니다.
        if( document.Payplus.object == null )
        {
            openwin = window.open( "chk_plugin.html", "chk_plugin", "width=420, height=100, top=300, left=300" );
        }
        */

        /* Payplus Plug-in 실행 */
        function  jsf__pay( form )
        {
            var RetVal = false;

						/* 고객 데이터 세팅 시작 */
						document.getElementById("buyr_tel1").value = document.getElementById("tel1").value + document.getElementById("tel2").value + document.getElementById("tel3").value ;
						document.getElementById("buyr_tel2").value = document.getElementById("hp1").value + document.getElementById("hp2").value + document.getElementById("hp3").value ;

						if(parseInt(document.getElementById("good_mny").value) <= 0){
							alert("최소결제금액이 1000원 이상이어야 합니다");
							return false ;
						}

						if (!$('input[name="agr_check"]').is(":checked")){
							alert("주문에 동의하여야 주문이 완료됩니다.") ;
							return false;
						}
						/* 고객 데이터 세팅 끝 */


            /* Payplus Plugin 실행 */
            if ( MakePayMessage( form ) == true )
            {
                openwin = window.open( "/resources/kcp/proc_win.html", "proc_win", "width=449, height=209, top=300, left=300" );
                RetVal = true ;
            }
            
            else
            {
                /*  res_cd와 res_msg변수에 해당 오류코드와 오류메시지가 설정됩니다.
                    ex) 고객이 Payplus Plugin에서 취소 버튼 클릭시 res_cd=3001, res_msg=사용자 취소
                    값이 설정됩니다.
                */
                res_cd  = document.order_info.res_cd.value ;
                res_msg = document.order_info.res_msg.value ;

            }

            return RetVal ;
        }

        // Payplus Plug-in 설치 안내 
        function init_pay_button()
        { 
            if ((navigator.userAgent.indexOf('MSIE') > 0) || (navigator.userAgent.indexOf('Trident/7.0') > 0))
            {
                try
                {
                    if( document.Payplus.object == null )
                    {
                        document.getElementById("display_setup_message").style.display = "block" ;
                    }
                    else{
                        document.getElementById("display_pay_button").style.display = "block" ;
                    }
                }
                catch (e)
                {
                    document.getElementById("display_setup_message").style.display = "block" ;
                }
            }
            else
            {
                try
                {
                    if( Payplus == null )
                    {
                        document.getElementById("display_setup_message").style.display = "block" ;
                    }
                    else{
                        document.getElementById("display_pay_button").style.display = "block" ;
                    }
                }
                catch (e)
                {
                    document.getElementById("display_setup_message").style.display = "block" ;
                }
            }
        }

        /* 주문번호 생성 예제 */
        function init_orderid()
        {
            /* var today = new Date();
            var year  = today.getFullYear();
            var month = today.getMonth() + 1;
            var date  = today.getDate();
            var time  = today.getTime();

            if(parseInt(month) < 10) {
                month = "0" + month;
            }

            if(parseInt(date) < 10) {
                date = "0" + date;
            }

            var order_idxx = "TEST" + year + "" + month + "" + date + "" + time;
 */
            //document.order_info.ordr_idxx.value =document.getElementById('order_idxx').value;

            /*
             * 인터넷 익스플로러와 파이어폭스(사파리, 크롬.. 등등)는 javascript 파싱법이 틀리기 때문에 object 가 인식 전에 실행 되는 문제
             * 기존에는 onload 부분에 추가를 했지만 setTimeout 부분에 추가
             * setTimeout 300의 의미는 플러그인 인식속도에 따른 여유시간 설정
             * - 20101018 -
             */
            setTimeout("init_pay_button();",300);
        }

        /* onLoad 이벤트 시 Payplus Plug-in이 실행되도록 구성하시려면 다음의 구문을 onLoad 이벤트에 넣어주시기 바랍니다. */
        function onload_pay()
        {
             if( jsf__pay(document.order_info) )
                document.order_info.submit();
        }


				function addr_samsam(key){
					if(key == "basic"){

						document.getElementById("buyr_name").value = document.getElementById("orderName").value ;

						document.getElementById("buyr_tel1").value = document.getElementById("orderTel1").value + document.getElementById("orderTel2").value + document.getElementById("orderTel3").value  ;
						document.getElementById("tel1").value      = document.getElementById("orderTel1").value ;
						document.getElementById("tel2").value      = document.getElementById("orderTel2").value ;
						document.getElementById("tel3").value      = document.getElementById("orderTel3").value ;

						document.getElementById("buyr_tel2").value = document.getElementById("orderHp1").value + document.getElementById("orderHp2").value + document.getElementById("orderHp3").value  ;
						document.getElementById("hp1").value       = document.getElementById("orderHp1").value ;
						document.getElementById("hp2").value       = document.getElementById("orderHp2").value ;
						document.getElementById("hp3").value       = document.getElementById("orderHp3").value ;

						document.getElementById("buyr_mail").value = document.getElementById("orderEmail").value ;

						document.getElementById("custZip1").value  = document.getElementById("orderZip1").value ;
						document.getElementById("custZip2").value  = document.getElementById("orderZip2").value ;
						document.getElementById("custAdd").value   = document.getElementById("orderAdd").value ;
						document.getElementById("custAddDetail").value = document.getElementById("orderAddDetail").value ;
						
					}else if(key == "new"){

						document.getElementById("buyr_name").value = "" ;

						document.getElementById("buyr_tel1").value = "" ;
						document.getElementById("tel1").value = "02" ;
						document.getElementById("tel2").value = "" ;
						document.getElementById("tel3").value = "" ;

						document.getElementById("buyr_tel2").value = "" ;
						document.getElementById("hp1").value = "010" ;
						document.getElementById("hp2").value = "" ;
						document.getElementById("hp3").value = "" ;

						document.getElementById("buyr_mail").value = "" ;

						document.getElementById("custZip1").value  = "" ;
						document.getElementById("custZip2").value  = "" ;
						document.getElementById("custAdd").value   = "" ;
						document.getElementById("custAddDetail").value = "" ;

					}
				}

				function usePointChecker(){

					// 고객이 사용한 포인트를 확인한다
					if($.isNumeric(document.getElementById("use_point").value)){
						var usePoint = parseInt(document.getElementById("use_point").value) ;
					}else{
						var usePoint = 0 ;
						document.getElementById("use_point").value = 0 ;
					}

					// 고객이 원래 보유한 포인트를 확인한다
					var getUserPoint = parseInt(document.getElementById("user_point").value) ;
					var buyPrice     = parseInt(document.getElementById("total_price").value) ; 

					if(getUserPoint < usePoint){

						alert("사용포인트가 보유포인트보다 많습니다.") ;
						document.getElementById("use_point").value = 0 ;
						usePointChecker();
						return false ; 

					}else{

						var resultBuyPrice = parseInt(buyPrice - usePoint) ;

						if(resultBuyPrice >= 0){

							if(resultBuyPrice >= 1000){

								var resultValue    = getUserPoint - usePoint ;
								var resultBuyPrice = buyPrice - usePoint ;

								// 포인트 계산
								document.getElementById("use_point").value         = usePoint ; 
								document.getElementById("myPointView").innerHTML   = resultValue + " P" ; 
								document.getElementById("usePointView2").innerHTML = usePoint ; 

								// 결제금 계산
								document.getElementById("good_mny").value            = resultBuyPrice ; 
								document.getElementById("resultPriceView").innerHTML = resultBuyPrice ; 
								document.getElementById("result_user_point").value   = resultValue ; 

							}else{

								alert("최소결제금액이 1000원이상이어야 합니다.") ;
								document.getElementById("use_point").value = 0 ;
								//usePointChecker();
								return false ; 

							}

						}else{

							alert("사용포인트가 결제금액보다 많습니다.") ;
							document.getElementById("use_point").value = 0 ;
							usePointChecker();
							return false ; 

						}

					}

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
    </script>
</head>

<body onload="init_orderid();">
	<div class="wrap">
	<c:import url="../inc/header.jsp"/>
	
	<!--  header 끝   -->
	<div class="container">
		<c:import url="../inc/orderLnb.jsp" />
		<div class="sub_content">
			
			<!-- 주문정보 입력 form : order_info -->
			<form name="order_info" method="post" action="/resources/kcp/pp_ax_hub.jsp" >
			<input type="hidden" id="customerInfo.custId"        name="customerInfo.custId"  value="${cus.custId}">
			<input type="hidden" id="ord_unit_chk"               name="ord_unit_chk"  value="${orderInfo.ord_unit_chk}">
			<input type="hidden" id="ordr_idxx"                  name="ordr_idxx" value="${orderInfo.orderNo}">
			<input type="hidden" id="customerInfo.modifyUserId"  name="customerInfo.modifyUserId"  value="${cus.custId}">
			<input type="hidden" id="ord_unit_chk" name="ord_unit_chk"  value="${orderInfo.ord_unit_chk}"/>
			<c:if test="${odPrdInfo.size() == 1}"><input type="hidden"  name="good_name"  value="${odPrdInfo[0].prdNm}" /></c:if>
			<c:if test="${odPrdInfo.size() != 1}"><input type="hidden" id="ordPrd.prdNm"   name="good_name" value="${odPrdInfo[0].prdNm} 외 ${odPrdInfo.size()-1}개" /></c:if>

			<!-- 유저 보유포인트 시작 -->
			<!--<input type="text" id="user_point" name="user_point"  value="${userPoint}"/>-->
			<input type="hidden" id="user_point"        name="user_point"         value="10000000"/>
			<input type="hidden" id="result_user_point" name="result_user_point"  value="10000000"/>

			<!-- 유저 보유포인트 끝 -->

			<!-- 주문시 폼 변화에 필요한 필드 시작 -->
			<input type="hidden" id="orderName" name="orderName" value="${cus.custNm}" />
			<input type="hidden" name="orderTel1" id="orderTel1" value="${cus.telNo1}" />
			<input type="hidden" name="orderTel2" id="orderTel2" value="${cus.telNo2}" />
			<input type="hidden" name="orderTel3" id="orderTel3" value="${cus.telNo3}" />
			<input type="hidden" name="orderHp1"  id="orderHp1"  value="${cus.hpNo1}" />
			<input type="hidden" name="orderHp2"  id="orderHp2"  value="${cus.hpNo2}" />
			<input type="hidden" name="orderHp3"  id="orderHp3"  value="${cus.hpNo3}" />
			<input type="hidden" name="orderZip1" id="orderZip1" value="${cus.custZip1}" />
			<input type="hidden" name="orderZip2" id="orderZip2" value="${cus.custZip2}" />
			<input type="hidden" name="orderAdd"  id="reciAdd"  value="document.getElementById('reciAdd1').value+document.getElementById('reciAdd2').value+document.getElementById('reciAdd3').value+document.getElementById('reciAdd4').value;" />
			<input type="hidden" name="orderAddDetail" id="orderAddDetail" value="${cus.custAddDetail}" />
			<input type="hidden" name="orderEmail" id="orderEmail" value="${cus.custEmail}"/>
			<!-- 주문시 폼 변화에 필요한 필드 끝 -->

				<div class="porder_section">
					<h4>제품주문</h4>
					<div class="porder_step">
						<ul>
							<li><img src="<c:url value='/resources/img/sub/shopping_loc3_off.png'/>"  alt="SETP3 주문완료이미지"/></li>
							<li class="mmargin"><img src="<c:url value='/resources/img/sub/shopping_loc2_on.png'/>"  alt="SETP2 주문/결제이미지"/></li>
							<li class="mmargin"><img src="<c:url value='/resources/img/sub/shopping_loc1_off.png'/>"  alt="SETP1 카트이미지"/></li>
						</ul>
						<h5>주문하실 상품</h5>
						<table class="order_tbl" summary="주문상품 리스트">
							<caption>주문상품 목록표</caption>
							<colgroup>
								<col />
								<col width="12%"/>
								<col width="10%"/>
								<col width="12%"/>
								<col width="10%"/>
							</colgroup>
							<thead>
								<tr>
									<th>상품명 / 옵션</th>
									<th>판매금액</th>
									<th>수량</th>
									<th>주문금액</th>
									<th>선택</th>
								</tr>
							</thead>
							<tbody>
							<c:choose>
								<c:when test="${odPrdInfo.size() != 0}">
									<c:forEach items="${odPrdInfo}" var="odPrdInfo" varStatus="i">
										<tr>
											<td class="product_area leftalign">
												<span>
													<img src="${odPrdInfo.prdSmallImg}"  alt="상품이미지"  width="71" height="71">
												</span>
												<input type="hidden" id="orderProductList[${i.index}].prdCd" name="orderProductList[${i.index}].prdCd"  value="${odPrdInfo.prdCd}"  />
												<input type="hidden" id="orderProductList[${i.index}].buyCnt" name="orderProductList[${i.index}].buyCnt"  value="${odPrdInfo.buyCnt}"  />
												<input type="hidden" id="orderProductList[${i.index}].prdOption" name="orderProductList[${i.index}].prdOption"  value="${odPrdInfo.prdOption}"  />
												<span>
													${odPrdInfo.prdNm}
													<c:if test="${'null' ne odPrdInfo.prdOpColor}">/${odPrdInfo.prdOpColor}</c:if>
													<c:if test="${'null' ne odPrdInfo.prdOpSize}">/${odPrdInfo.prdOpSize}</c:if>
												</span>
											</td>
											<td><script>SetPriceInput('${odPrdInfo.sellPrice}');</script></td>
											<td>
												<span class="input_text">${odPrdInfo.buyCnt}</span>
											</td>
											<td><script>SetPriceInput('${odPrdInfo.totalPrice}');</script></td>
											<td>
												<input type="button" value="삭제하기"class="btn_choice2" onClick="confirm_process('','해당 상품을 삭제하시겠습니까?','deleteOrderList.do?cookieKey=${odPrdInfo.cookieKey}&ord_unit_chk=${orderInfo.ord_unit_chk}');" />
											</td>
										</tr>
										<tr><td height="1" colspan="6" bgcolor="#E0E0E0"></td></tr>
									</c:forEach>
								</c:when>
								<c:otherwise><tr><td colspan="5" height="90">구매하실 상품이 없습니다.</td></tr></c:otherwise>
								</c:choose>
								<tr>
									<c:set var="total"  value="0"/>
									<td class="total_choice" colspan="6">
										총 주문금액 : 
										<c:forEach items="${odPrdInfo}" var="odPrdInfo">
										<script>SetPriceInput('${odPrdInfo.totalPrice}');</script>원 +
										<c:set var="total" value="${total+odPrdInfo.totalPrice}"/>
										</c:forEach>
										 <c:if test="${total>=config.buyPrice}">배송비 :  <script>SetPriceInput('${config.trasferPrice}');</script>원</c:if>
										 <c:if test="${total<config.buyPrice}">배송비 : 0원 </c:if>
										 = 합계 <strong><script>SetPriceInput('${total}');</script></strong>원


										 <input type="hidden" id="total_price" name="total_price" value="${total}"/>
										 <input type="hidden" id="good_mny"    name="good_mny"    value="${total}"/>

										 <input type="hidden" id="sndAmount"  name="sndAmount"  value="${total}" /><!-- kst net -->
									</td>
								</tr>
								
							</tbody>
						</table>
						
						<h5>주문서 작성 및 결제</h5>
						<p class="sub_tit1">주문고객/배송지정보 입력</p>
						<p class="sub_tit2">< 주문하시는 분 ></p>
						<table class="order_tblbox" summary="주문고객정보표">
							<caption>고객정보 목록표</caption>
							<colgroup>
								<col width="15%"/>
								<col width="35%"/>
								<col width="15%"/>
								<col width="*"/>
							</colgroup>
							<tbody>
								<tr>
									<th>성명</th>
									<td class="in_text">${cus.custNm}</td>
									<th>전화번호</th>
									<td class="in_sectext">
										${cus.telNo1}-${cus.telNo2}-${cus.telNo3}
										<input type="hidden" id="buyr_tel1" name="buyr_tel1" value="${cus.telNo1}${cus.telNo2}${cus.telNo3}"/>
									</td>
								</tr>
								<tr>
									<th>휴대전화</th>
									<td class="in_sectext">
										${cus.hpNo1}-${cus.hpNo2}-${cus.hpNo3}
										<input type="hidden" id="buyr_tel2" name="buyr_tel2" value="${cus.hpNo1}${cus.hpNo2}${cus.hpNo3}"/>
									</td>
									<th>이메일</th>
									<td class="in_text">${cus.custEmail}</td>
								</tr>
							</tbody>
						</table>
						<p class="sub_tit2">< 받으시는 분 ></p>
						<table class="order_tblbox" summary="주문고객정보표">
							<caption>고객정보 목록표</caption>
							<colgroup>
								<col width="15%"/>
								<col width="35%"/>
								<col width="15%"/>
								<col width="*"/>
							</colgroup>
							<tbody>
								<tr>
									<th>배송지 선택</th>
									<td class="check_box">
										<input type="radio" id="basic_check" name="choice" onClick="addr_samsam('basic');" checked /><label for="basic_check">기본배송지</label> &nbsp;&nbsp;
										<input type="radio" id="new_check"   name="choice" onClick="addr_samsam('new');" /><label for="new_check">새로입력</label>
									</td>
									<th>받으시는분</th>
									<td class="in_text">
										<input type="text" id="reciNm" name="reciNm" value="${cus.custNm}" title="받으시는분 성명 기입"/>
										<input type="hidden"  id="buyr_name" name="buyr_name" value="${cus.custNm}" />
									</td>
								</tr>
								<tr>
									<th>전화번호</th>
									<td class="in_sectext">
										<select  id="tel1" name="tel1" >
											<option value="02"  <c:if test="${cus.telNo1 eq '02'}">selected</c:if>>02</option>
											<option value="031" <c:if test="${cus.telNo1 eq '031'}">selected</c:if>>031</option>
											<option value="032" <c:if test="${cus.telNo1 eq '032'}">selected</c:if>>032</option>
											<option value="033" <c:if test="${cus.telNo1 eq '033'}">selected</c:if>>033</option>
											<option value="041" <c:if test="${cus.telNo1 eq '041'}">selected</c:if>>041</option>
											<option value="042" <c:if test="${cus.telNo1 eq '042'}">selected</c:if>>042</option>
											<option value="043" <c:if test="${cus.telNo1 eq '043'}">selected</c:if>>043</option>
											<option value="051" <c:if test="${cus.telNo1 eq '051'}">selected</c:if>>051</option>
											<option value="052" <c:if test="${cus.telNo1 eq '052'}">selected</c:if>>052</option>
											<option value="053" <c:if test="${cus.telNo1 eq '053'}">selected</c:if>>053</option>
											<option value="054" <c:if test="${cus.telNo1 eq '054'}">selected</c:if>>054</option>
											<option value="061" <c:if test="${cus.telNo1 eq '061'}">selected</c:if>>061</option>
											<option value="062" <c:if test="${cus.telNo1 eq '062'}">selected</c:if>>062</option>
											<option value="063" <c:if test="${cus.telNo1 eq '063'}">selected</c:if>>063</option>
											<option value="064" <c:if test="${cus.telNo1 eq '064'}">selected</c:if>>064</option>
										</select> -
										<input type="text" id="tel2" name="tel2" value="${cus.telNo2}" title="전화번호" maxlength="4" style="width:40px;"/> -
										<input type="text" id="tel3" name="tel3" value="${cus.telNo3}" title="전화번호" maxlength="4" style="width:40px;"/>
									</td>
									<th>휴대전화번호</th>
									<td class="in_sectext">
										<select id="hp1" name="hp1">
											<option value="010" <c:if test="${cus.hpNo1 eq '010'}">selected</c:if>>010</option>
											<option value="011" <c:if test="${cus.hpNo1 eq '011'}">selected</c:if>>011</option>
											<option value="016" <c:if test="${cus.hpNo1 eq '016'}">selected</c:if>>016</option>
											<option value="017" <c:if test="${cus.hpNo1 eq '017'}">selected</c:if>>017</option>
											<option value="018" <c:if test="${cus.hpNo1 eq '018'}">selected</c:if>>018</option>
											<option value="019" <c:if test="${cus.hpNo1 eq '019'}">selected</c:if>>019</option>
										</select> -
										<input type="text" id="hp2" name="hp2" value="${cus.hpNo2}" title="휴대전화번호" maxlength="4" style="width:40px;"/> -
										<input type="text" id="hp3" name="hp3" value="${cus.hpNo3}" title="휴대전화번호" maxlength="4" style="width:40px;"/>
									</td>
								</tr>
								<tr>
									<th>이메일주소</th>
									<td colspan="3" class="in_sectext" style="padding:5px;">
										<input type="text" title="email text"  name="buyr_mail" id="buyr_mail" value="${cus.custEmail}" style="width:300px;" required hname="이메일을 입력하여 주십시오" />
									</td>
								</tr>
								<tr>
									<th>배송주소</th>
									<td colspan="3" class="in_sectext" style="padding:5px;">
										<input type="text" title="address text" style="width:60px;" id="custZip1" name="reciAdd1" value="${cus.custZip1}" readonly required hname="우편번호를 입력하여 주십시오" />-
										<input type="text" title="address text" style="width:60px;" id="custZip2" name="reciAdd2" value="${cus.custZip2}" readonly required hname="우편번호를 입력하여 주십시오" />
										<input type="button" value="우편번호 찾기" style="width:100px;height:21px;" onClick="openWin('/user/searchZipCode.do?type=userEdit','searchZipuserRegiForm',650,450,'scrollbars=yes');" /><br/>
										<input type="text" title="address text" style="width:50%;" id="custAdd"       name="reciAdd3" value="${cus.custAdd}" readonly/>
										<input type="text" title="address text" style="width:40%;" id="custAddDetail" name="reciAdd4" value="${cus.custAddDetail}"/>
									</td>
								</tr>
								<tr>
									<th>배송시 요청사항</th>
									<td colspan="3" class="arrive_box" style="padding:5px;">
										<textarea id="reciReq" name="reciReq" title="배송시 요청사항 기입" style="width:95%;height:120px;"></textarea>
									</td>
								</tr>
							</tbody>
						</table>
						<div class="point_employ1">
							<p class="sub_tit1">포인트 사용하기</p>
							<table class="employ_tbl" summary="고객포인트표">
								<caption>포인트 표</caption>
								<colgroup>
									<col width="30%"/>
									<col width="30%"/>
									<col width="40%"/>
								</colgroup>
								<thead>
									<tr>
										<th class="rb_line">구 분</th>
										<th class="rb_line">보유 포인트</th>
										<th>사용 포인트</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<th class="rb_line">사용현황</th>
										<td class="rb_line"><span id="myPointView">${userPoint} P</span></td>
										<td class="in_text">
											<input type="text" id="use_point" name="use_point" value="0" style="width:80%;text-align:right;padding-right:5px;" onBlur="usePointChecker();" onkeypress="numKeyOnly()" /><label>P</label>
										</td>
									</tr>
								</tbody>
							</table>
							<p class="sub_tit1">결제 수단 선택</p>
							<span class="payment1">
								
								<input type="radio" id="settlement_type" name="settlement_type" onClick="document.getElementById('pay_method').value='100000000000' ;" value="100000000000" checked /><label for="credit_card">신용카드</label>
								<input type="radio" id="settlement_type" name="settlement_type" onClick="document.getElementById('pay_method').value='010000000000' ;" value="010000000000" /><label for="account_transfer">실시간 계좌이체</label>
								<input type="hidden" id="pay_method" name="pay_method" value="100000000000">
								
								<!--
								<select name="pay_method">
									<option value="100000000000">신용카드</option>
									<option value="010000000000">계좌이체</option>
									<option value="000100000000">포인트</option>
								</select>
								-->

							</span>
							<span class="payment2">
								<input type="radio" id="welfare_card" name="payment2" checked /><label for="welfare_card">복지 카드 포인트 사용안함</label>
								<input type="radio" id="welfare_ncard" name="payment2"/><label for="welfare_ncard">복지 카드 포인트 사용</label>
							</span>
						</div>
						<div class="point_employ2">
							<p class="sub_tit1">적립금/포인트 사용하기</p>
							<table class="employ_tbl" summary="고객포인트표">
								<caption>포인트 목록표</caption>
								<colgroup>
									<col width="20%"/>
									<col width="20%"/>
									<col width="40%"/>
								</colgroup>
								<thead>
									<tr>
										<th class="rb_line">총 주문금액</th>
										<th colspan="2">${total} 원</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<th class="tbb_line rb_line">포인트</th>
										<td class="tbb_line rb_line">사용</td>
										<td class="tbb_line"><span id="usePointView2">0</span> P</td>
									</tr>
									<tr>
										<td colspan="2" class="rb_line" style="text-align:center;">할인 및 포인트 차감 후 결제 금액</td>
										<td><span id="resultPriceView" style="color:#5d85bc;font-weight:bold;">${total}</span> 원</td>
									</tr>
								</tbody>
							</table>
							<p class="sub_tit3">< 주문동의 ></p>
							<span class="complete">
								주문하시려는 상품에 대한 수량, 가격, 배송정보 등에 대하여 확인하였으며,
								이에 동의하십니까?
							</span>
							<p class="agree_check">
							<input type="checkbox" id="agr_check" name="agr_check" value="y"/>
							<label for="agr_check">동의합니다.</label>
						</p>
						</div>
					</div>
					<div class="complet_area">
						<span class="btn_complete">
							<!-- <input type="button" value="결제하기" title="결제버튼" onClick="_pay(document.KSPayWeb);"/> -->
							<input name="" type="submit" class="submit" value="결제하기" onclick="return jsf__pay(this.form);"/>
						</span>
						<span class="btn_cancle">
							<input type="reset" value="취소하기" title="취소버튼"/>
						</span>
					</div>
				</div>
<%
    /* = -------------------------------------------------------------------------- = */
    /* =   1. 주문 정보 입력 END                                                    = */
    /* ============================================================================== */
%>

<%
    /* ============================================================================== */
    /* =   2. 가맹점 필수 정보 설정                                                 = */
    /* = -------------------------------------------------------------------------- = */
    /* =   ※ 필수 - 결제에 반드시 필요한 정보입니다.                               = */
    /* =   site_conf_inc.jsp 파일을 참고하셔서 수정하시기 바랍니다.                 = */
    /* = -------------------------------------------------------------------------- = */
    // 요청종류 : 승인(pay)/취소,매입(mod) 요청시 사용
%>
    <input type="hidden" name="req_tx"          value="pay" />
    <input type="hidden" name="site_cd"         value="<%= g_conf_site_cd   %>" />
    <input type="hidden" name="site_name"       value="<%= g_conf_site_name %>" />
<%
    /*
       할부옵션 : Payplus Plug-in에서 카드결제시 최대로 표시할 할부개월 수를 설정합니다.(0 ~ 18 까지 설정 가능)
       ※ 주의  - 할부 선택은 결제금액이 50,000원 이상일 경우에만 가능, 50000원 미만의 금액은 일시불로만 표기됩니다
                  예) value 값을 "5" 로 설정했을 경우 => 카드결제시 결제창에 일시불부터 5개월까지 선택가능
    */
%>
    <input type="hidden" name="quotaopt"        value="12"/>
    <!-- 필수 항목 : 결제 금액/화폐단위 -->
    <input type="hidden" name="currency"        value="WON"/>
<%
    /* = -------------------------------------------------------------------------- = */
    /* =   2. 가맹점 필수 정보 설정 END                                             = */
    /* ============================================================================== */
%>

<%
    /* ============================================================================== */
    /* =   3. Payplus Plugin 필수 정보(변경 불가)                                   = */
    /* = -------------------------------------------------------------------------- = */
    /* =   결제에 필요한 주문 정보를 입력 및 설정합니다.                            = */
    /* = -------------------------------------------------------------------------- = */
%>
    <!-- PLUGIN 설정 정보입니다(변경 불가) -->
    <input type="hidden" name="module_type"     value="<%= module_type %>"/>
<!--
      ※ 필 수
          필수 항목 : Payplus Plugin에서 값을 설정하는 부분으로 반드시 포함되어야 합니다
          값을 설정하지 마십시오
-->
    <input type="hidden" name="res_cd"          value=""/>
    <input type="hidden" name="res_msg"         value=""/>
    <input type="hidden" name="tno"             value=""/>
    <input type="hidden" name="trace_no"        value=""/>
    <input type="hidden" name="enc_info"        value=""/>
    <input type="hidden" name="enc_data"        value=""/>
    <input type="hidden" name="ret_pay_method"  value=""/>
    <input type="hidden" name="tran_cd"         value=""/>
    <input type="hidden" name="bank_name"       value=""/>
    <input type="hidden" name="bank_issu"       value=""/>
    <input type="hidden" name="use_pay_method"  value=""/>

    <!--  현금영수증 관련 정보 : Payplus Plugin 에서 설정하는 정보입니다 -->
    <input type="hidden" name="cash_tsdtime"    value=""/>
    <input type="hidden" name="cash_yn"         value=""/>
    <input type="hidden" name="cash_authno"     value=""/>
    <input type="hidden" name="cash_tr_code"    value=""/>
    <input type="hidden" name="cash_id_info"    value=""/>

	<!-- 2012년 8월 18일 전자상거래법 개정 관련 설정 부분 -->
	<!-- 제공 기간 설정 0:일회성 1:기간설정(ex 1:2012010120120131)  -->
	<input type="hidden" name="good_expr" value="0">

	<!-- 가맹점에서 관리하는 고객 아이디 설정을 해야 합니다.(필수 설정) -->
	<input type="hidden" name="shop_user_id"    value=""/>
	<!-- 복지포인트 결제시 가맹점에 할당되어진 코드 값을 입력해야합니다.(필수 설정) -->
    <input type="hidden" name="pt_memcorp_cd"   value=""/>

<%
    /* = -------------------------------------------------------------------------- = */
    /* =   3. Payplus Plugin 필수 정보 END                                          = */
    /* ============================================================================== */
%>

<%
    /* ============================================================================== */
    /* =   4. 옵션 정보                                                             = */
    /* = -------------------------------------------------------------------------- = */
    /* =   ※ 옵션 - 결제에 필요한 추가 옵션 정보를 입력 및 설정합니다.             = */
    /* = -------------------------------------------------------------------------- = */

    /* 사용카드 설정 여부 파라미터 입니다.(통합결제창 노출 유무)
    <input type="hidden" name="used_card_YN"        value="Y"/> */
    /* 사용카드 설정 파라미터 입니다. (해당 카드만 결제창에 보이게 설정하는 파라미터입니다. used_card_YN 값이 Y일때 적용됩니다.
    /<input type="hidden" name="used_card"        value="CCBC:CCKM:CCSS"/> */

    /* 신용카드 결제시 OK캐쉬백 적립 여부를 묻는 창을 설정하는 파라미터 입니다
         포인트 가맹점의 경우에만 창이 보여집니다
        <input type="hidden" name="save_ocb"        value="Y"/> */

    /* 고정 할부 개월 수 선택
           value값을 "7" 로 설정했을 경우 => 카드결제시 결제창에 할부 7개월만 선택가능
    <input type="hidden" name="fix_inst"        value="07"/> */

    /*  무이자 옵션
            ※ 설정할부    (가맹점 관리자 페이지에 설정 된 무이자 설정을 따른다)                             - "" 로 설정
            ※ 일반할부    (KCP 이벤트 이외에 설정 된 모든 무이자 설정을 무시한다)                           - "N" 로 설정
            ※ 무이자 할부 (가맹점 관리자 페이지에 설정 된 무이자 이벤트 중 원하는 무이자 설정을 세팅한다)   - "Y" 로 설정
    <input type="hidden" name="kcp_noint"       value=""/> */

    /*  무이자 설정
            ※ 주의 1 : 할부는 결제금액이 50,000 원 이상일 경우에만 가능
            ※ 주의 2 : 무이자 설정값은 무이자 옵션이 Y일 경우에만 결제 창에 적용
            예) 전 카드 2,3,6개월 무이자(국민,비씨,엘지,삼성,신한,현대,롯데,외환) : ALL-02:03:04
            BC 2,3,6개월, 국민 3,6개월, 삼성 6,9개월 무이자 : CCBC-02:03:06,CCKM-03:06,CCSS-03:06:04
    <input type="hidden" name="kcp_noint_quota" value="CCBC-02:03:06,CCKM-03:06,CCSS-03:06:09"/> */

    /* 해외카드 구분하는 파라미터 입니다.(해외비자, 해외마스터, 해외JCB로 구분하여 표시)
    <input type="hidden" name="used_card_CCXX"        value="Y"/> */

    /*  가상계좌 은행 선택 파라미터
         ※ 해당 은행을 결제창에서 보이게 합니다.(은행코드는 매뉴얼을 참조)
    <input type="hidden" name="wish_vbank_list" value="05:03:04:07:11:23:26:32:34:81:71"/> */

    /*  가상계좌 입금 기한 설정하는 파라미터 - 발급일 + 3일
    <input type="hidden" name="vcnt_expire_term" value="3"/> */

    /*  가상계좌 입금 시간 설정하는 파라미터
         HHMMSS형식으로 입력하시기 바랍니다
         설정을 안하시는경우 기본적으로 23시59분59초가 세팅이 됩니다
         <input type="hidden" name="vcnt_expire_term_time" value="120000"/> */

    /* 포인트 결제시 복합 결제(신용카드+포인트) 여부를 결정할 수 있습니다.- N 일경우 복합결제 사용안함
        <input type="hidden" name="complex_pnt_yn" value="N"/>    */

    /* 현금영수증 등록 창을 출력 여부를 설정하는 파라미터 입니다
         ※ Y : 현금영수증 등록 창 출력
         ※ N : 현금영수증 등록 창 출력 안함 
    ※ 주의 : 현금영수증 사용 시 KCP 상점관리자 페이지에서 현금영수증 사용 동의를 하셔야 합니다
        <input type="hidden" name="disp_tax_yn"     value="Y"/> */

    /* 결제창에 가맹점 사이트의 로고를 플러그인 좌측 상단에 출력하는 파라미터 입니다
       업체의 로고가 있는 URL을 정확히 입력하셔야 하며, 최대 150 X 50  미만 크기 지원

    ※ 주의 : 로고 용량이 150 X 50 이상일 경우 site_name 값이 표시됩니다.
        <input type="hidden" name="site_logo"       value="" /> */

    /* 결제창 영문 표시 파라미터 입니다. 영문을 기본으로 사용하시려면 Y로 세팅하시기 바랍니다
    	2010-06월 현재 신용카드와 가상계좌만 지원됩니다
        <input type='hidden' name='eng_flag'      value='Y'> */

    /* KCP는 과세상품과 비과세상품을 동시에 판매하는 업체들의 결제관리에 대한 편의성을 제공해드리고자, 
       복합과세 전용 사이트코드를 지원해 드리며 총 금액에 대해 복합과세 처리가 가능하도록 제공하고 있습니다
       복합과세 전용 사이트 코드로 계약하신 가맹점에만 해당이 됩니다
       상품별이 아니라 금액으로 구분하여 요청하셔야 합니다
       총결제 금액은 과세금액 + 부과세 + 비과세금액의 합과 같아야 합니다. 
       (good_mny = comm_tax_mny + comm_vat_mny + comm_free_mny)

        <input type="hidden" name="tax_flag"       value="TG03">  <!-- 변경불가	   -->
        <input type="hidden" name="comm_tax_mny"   value=""    >  <!-- 과세금액	   --> 
        <input type="hidden" name="comm_vat_mny"   value=""    >  <!-- 부가세	   -->
        <input type="hidden" name="comm_free_mny"  value=""    >  <!-- 비과세 금액 --> */

    /* skin_indx 값은 스킨을 변경할 수 있는 파라미터이며 총 7가지가 지원됩니다. 
       변경을 원하시면 1부터 7까지 값을 넣어주시기 바랍니다. 

        <input type='hidden' name='skin_indx'      value='1'> */

    /* 상품코드 설정 파라미터 입니다.(상품권을 따로 구분하여 처리할 수 있는 옵션기능입니다.)
        <input type='hidden' name='good_cd'      value=''> */

    /* = -------------------------------------------------------------------------- = */
    /* =   4. 옵션 정보 END                                                         = */
    /* ============================================================================== */
%>
			</form>
		</div>
	</div>
<!--  container 끝   -->	

	<div class="footer">
		<div class="footer_area">
			<h2><img src="<c:url value='/resources/img/common/footer_logo.jpg'/>"  alt="현대 로고"/></h2>
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

<!-- 결제 요청/처음으로 이미지 -->
<div class="btnset" id="display_pay_button" style="display:block"></div>

<!-- Payplus Plug-in 설치 안내 -->
<div id="display_setup_message" style="display:none">
	<p class="txt">
	결제를 계속 하시려면 상단의 노란색 표시줄을 클릭 하시거나 <a href="http://pay.kcp.co.kr/plugin_new/file/KCPUXWizard.exe"><span>[수동설치]</span></a>를 눌러 Payplus Plug-in을 설치하시기 바랍니다.
	[수동설치]를 눌러 설치하신 경우 새로고침(F5)키를 눌러 진행하시기 바랍니다.
	</p>
</div>