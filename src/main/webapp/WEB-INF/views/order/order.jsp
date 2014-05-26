<%@ page language="java" contentType="text/html;charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<c:import  url="../inc/topSub.jsp" />
<c:import  url="../inc/topMain.jsp" />    
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
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<title>###### 현대프로모션몰 ######</title>
  <meta http-equiv="Content-Type" content="text/html; charset=euc-kr" />
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
            document.order_info.ordr_idxx.value =document.getElementById('order_idxx').value;

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
			<input type="hidden" id="customerInfo.custId"  name="customerInfo.custId"  value="${cus.custId}">
			<input type="hidden" id="ord_unit_chk"  name="ord_unit_chk"  value="${orderInfo.ord_unit_chk}">
			<%-- <input type="hidden" id="orderNo"  name="orderNo"  value="${orderInfo.orderNo}"> --%>
			<input type="hidden"  id="ordr_idxx" name="ordr_idxx" value="${orderInfo.orderNo}">
			<input type="hidden" id="customerInfo.modifyUserId"  name="customerInfo.modifyUserId"  value="${cus.custId}">
			<c:if test="${odPrdInfo.size() == 1}"><input type="hidden"  name="good_name"  value="${odPrdInfo[0].prdNm}" /></c:if>
			<c:if test="${odPrdInfo.size() != 1}"><input type="hidden" id="ordPrd.prdNm"   name="good_name" value="${odPrdInfo[0].prdNm} 외 ${odPrdInfo.size()-1}개" /></c:if>
			
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
								<col width="5%"/>
								<col width="51%"/>
								<col width="12%"/>
								<col width="10%"/>
								<col width="12%"/>
								<col width="10%"/>
							</colgroup>
							<thead>
								<tr>
									<th>상품명/옵션</th>
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
										<img src="${odPrdInfo.prdSmallImg}"  alt="상품이미지"  width="71" height="71"></span>
										<input type="hidden" id="orderProductList[${i.index}].prdCd" name="orderProductList[${i.index}].prdCd"  value="${odPrdInfo.prdCd}"  />
										<input type="hidden" id="orderProductList[${i.index}].buyCnt" name="orderProductList[${i.index}].buyCnt"  value="${odPrdInfo.buyCnt}"  />
										<input type="hidden" id="orderProductList[${i.index}].prdOption" name="orderProductList[${i.index}].prdOption"  value="${odPrdInfo.prdOption}"  />
										 
										 
										<span>
											${odPrdInfo.prdNm}
											<c:if test="${'null' ne odPrdInfo.prdOpColor}">/${odPrdInfo.prdOpColor}</c:if>
											<c:if test="${'null' ne odPrdInfo.prdOpSize}">/${odPrdInfo.prdOpSize}</c:if>
											
										</span>
									</td>
									<td>${odPrdInfo.sellPrice}</td>
									<td>
										<span class="input_text">${odPrdInfo.buyCnt}<%-- <input type="text" id="buyCnt${i.index}" naem="buyCnt${i.index}" value="${odPrdInfo.buyCnt}" title="수량기입"><!-- <button class="btn_triangle1"></button> --> --%></span>
										<span class="input_btn"><%-- <input type="button" value="수정" title="수정" onClick="location.href='editOrderBuycn.do?ordIdx=${i.index}&ord_unit_chk=${orderInfo.ord_unit_chk}&orderProductList[${i.index}].buyCnt='+document.getElementById('buyCnt${i.index}').value;"> <!-- <button class="btn_triangle2"></button> --> --%></span>
									</td>
									<td>${odPrdInfo.totalPrice}</td>
									<td>
										<input type="button" value="삭제하기"class="btn_choice2" onClick="confirm_process('','해당 상품을 삭제하시겠습니까?','deleteOrderList.do?cookieKey=${odPrdInfo.cookieKey}&ord_unit_chk=${orderInfo.ord_unit_chk}');" />
									</td>
								</tr>
								<tr>
									<td class="one_choice" colspan="6">
										상품가격 : ${odPrdInfo.totalPrice}원 + 배송비 : 0원 = 합계 ${odPrdInfo.totalPrice}원
									</td>
								</tr>
								</c:forEach>
								</c:when>
								<c:otherwise><tr><td>구매하실 상품이 없습니다.</td></tr></c:otherwise>
								</c:choose>
								<tr>
									<c:set var="total"  value="0"/>
									<td class="total_choice" colspan="6">
										총 주문금액 : 
										<c:forEach items="${odPrdInfo}" var="odPrdInfo">
										${odPrdInfo.totalPrice}원 +
										<c:set var="total" value="${total+odPrdInfo.totalPrice}"/>
										</c:forEach>
										 배송비 : 0원 = 합계 <strong>${total}</strong>원
										 <input type="hidden" id="sndAmount"  name="sndAmount"  value="${total}" />
										 <input type="hidden"  name="good_mny"  value="${total}"/>
			
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
								<col width="35%"/>
							</colgroup>
							<tbody>
								<tr>
									<th>성명</th>
									<td class="in_text">
										<input type="text"  name="buyr_name" title="성명" value="${cus.custNm}"/>
									</td>
									<th>전화번호</th>
									<td class="in_sectext">
										<select  name="customerInfo.telNo1" id="telNo1">
											<option <c:if test="${cus.telNo1}">selected</c:if>>02</option>
											<option <c:if test="${cus.telNo1}">selected</c:if>>031</option>
											<option <c:if test="${cus.telNo1}">selected</c:if>>032</option>
											<option <c:if test="${cus.telNo1}">selected</c:if>>033</option>
											<option <c:if test="${cus.telNo1}">selected</c:if>>041</option>
											<option <c:if test="${cus.telNo1}">selected</c:if>>042</option>
											<option <c:if test="${cus.telNo1}">selected</c:if>>043</option>
											<option <c:if test="${cus.telNo1}">selected</c:if>>051</option>
											<option <c:if test="${cus.telNo1}">selected</c:if>>052</option>
											<option <c:if test="${cus.telNo1}">selected</c:if>>053</option>
											<option <c:if test="${cus.telNo1}">selected</c:if>>054</option>
											<option <c:if test="${cus.telNo1}">selected</c:if>>061</option>
											<option <c:if test="${cus.telNo1}">selected</c:if>>062</option>
											<option <c:if test="${cus.telNo1}">selected</c:if>>063</option>
											<option <c:if test="${cus.telNo1}">selected</c:if>>064</option>
										</select>
										-<input type="text" title="전화번호" name="customerInfo.telNo2" id="telNo2" value="${cus.telNo2}" />-
										<input type="text" title="전화번호" name="customerInfo.telNo3" id="telNo3" value="${cus.telNo3}"/>
										 <input type="hidden" name="buyr_tel1" value="document.getElementById(telNo1)+document.getElementById(telNo2)+document.getElementById(telNo3);"/>		
									</td>
								</tr>
								<tr>
									<th>휴대전화</th>
									<td class="in_sectext">
										<select  name="customerInfo.hpNo1" id="hpNo1">
											<option <c:if test="${cus.hpNo1}">selected</c:if>>010</option>
											<option <c:if test="${cus.hpNo1}">selected</c:if>>011</option>
											<option <c:if test="${cus.hpNo1}">selected</c:if>>017</option>
											<option <c:if test="${cus.hpNo1}">selected</c:if>>016</option>
											<option <c:if test="${cus.hpNo1}">selected</c:if>>019</option>
											<option <c:if test="${cus.hpNo1}">selected</c:if>>018</option>
										</select>
										-<input type="text"  name="customerInfo.hpNo2" id="hpNo2"title="휴대전화번호" value="${cus.hpNo2}"/>-
										<input type="text"  name="customerInfo.hpNo3" id="hpNo3" title="휴대전화번호" value="${cus.hpNo3}"/>
										<input type="hidden" name="buyr_tel2" value="document.getElementById(hpNo1)+document.getElementById(hpNo2)+document.getElementById(hpNo3);"/>	
									</td>
									<th>이메일</th>
									<td class="in_text">
										<input type="text" name="buyr_mail" title="이메일" value="${cus.custEmail}"/>
									</td>
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
								<col width="35%"/>
							</colgroup>
							<tbody>
								<tr>
									<th>배송지 선택</th>
									<td class="check_box">
										<input type="radio" id="new_check" name="choice"/><label for="new_check">새로입력</label>
										<input type="radio" id="basic_check" name="choice"/><label for="basic_check">기본배송지</label>
										<button><img src="<c:url value='/resources/img/./images/common/btn_checkbox.gif'/>" alt="내 배송지에서 선택이미지"/></button>
									</td>
									<th>받으시는분</th>
									<td class="in_text">
										<input type="text" id="reciInfo.reciNm" name="reciInfo.reciNm" title="받으시는분 성명 기입"/>
									</td>
								</tr>
								<tr>
									<th>전화번호</th>
									<td class="in_sectext">
										<select  id="reciInfo.reciPh" name="reciInfo.reciPh" >
											<option selected>02</option>
											<option>031</option>
											<option>032</option>
											<option>033</option>
											<option>041</option>
											<option>042</option>
											<option>043</option>
											<option>051</option>
											<option>052</option>
											<option>053</option>
											<option>054</option>
											<option>061</option>
											<option>062</option>
											<option>063</option>
											<option>064</option>
										</select>
										-<input type="text" id="reciInfo.reciPh" name="reciInfo.reciPh" title="전화번호"/>-
										<input type="text"  id="reciInfo.reciPh" name="reciInfo.reciPh" title="전화번호"/>
									</td>
									<th>휴대전화번호</th>
									<td class="in_sectext">
										<select  id="reciInfo.reciMb" name="reciInfo.reciMb"  >
											<option selected>010</option>
											<option>011</option>
											<option>017</option>
											<option>016</option>
											<option>019</option>
											<option>018</option>
										</select>
										-<input type="text" id="reciInfo.reciMb" name="reciInfo.reciMb" title="휴대전화번호"/>-
										<input type="text" id="reciInfo.reciMb" name="reciInfo.reciMb"  title="휴대전화번호"/>
									</td>
								</tr>
								<tr>
									<th>주소</th>
									<td colspan="3" class="address_box">
										<span class="adr_box1">
											<input type="text" title="우편번호"  id="reciInfo.reciAdd" name="reciInfo.reciAdd" />
											<input type="button" value="우편번호 찾기"onClick="openWin('/user/searchZipCode.do','searchZipForm',600,450,'scrollbars=no');"/><br/>
										</span>
										<span class="adr_box2">
											<input type="text" title="자동주소" id="reciInfo.reciAdd" name="reciInfo.reciAdd" />
										</span>
									</td>
								</tr>
								<tr>
									<th>배송시 요청사항</th>
									<td colspan="3" class="arrive_box">
										<input type="text" id="reciInfo.reciReq" name="reciInfo.reciReq" title="배송시 요청사항 기입"/>
									</td>
								</tr>
							</tbody>
						</table>
						<p class="adr_check">
							<input type="checkbox" id="adr_check" />
							<label for="adr_check">희망배송지 추가(상기 입력된 배송지 정보를 내 배송지 목록에 추가합니다.)</label>
						</p>
						<div class="point_employ1">
							<p class="sub_tit1">적립금/포인트 사용하기</p>
							<table class="employ_tbl" summary="고객포인트표">
								<caption>포인트 목록표</caption>
								<colgroup>
									<col width="30%"/>
									<col width="30%"/>
									<col width="40%"/>
								</colgroup>
								<thead>
									<tr>
										<th class="rb_line">구분</th>
										<th class="rb_line">보유금액</th>
										<th>사용금액</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<th class="rb_line">기본급</th>
										<td class="rb_line">0원</td>
										<td class="in_text">
											<input type="text"/><label>원</label>
										</td>
									</tr>
								</tbody>
							</table>
							<p class="sub_tit1">결제 수단 선택</p>
							<span class="payment1">
								<input type="radio" id="pay_method" name="pay_method" value="100000000000"><label for="credit_card">신용카드</label>
								<input type="radio" id="pay_method" name="pay_method" value="010000000000"><label for="account_transfer">실시간 계좌이체</label>
						
								<!--  <select name="pay_method">
	                                <option value="100000000000">신용카드</option>
	                                <option value="010000000000">계좌이체</option>
	                                <option value="001000000000">가상계좌</option>
	                                <option value="000100000000">포인트</option>
	                                <option value="000010000000">휴대폰</option>
	                                <option value="000000001000">상품권</option>
	                                <option value="000000000010">ARS</option>
	                                <option value="111000000000">신용카드/계좌이체/가상계좌</option>
	                            </select> -->
							</span>
							<span class="payment2">
								<input type="radio" id="welfare_card" name="payment2"/><label for="welfare_card">복지 카드 포인트 사용안함</label>
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
										<th colspan="2"></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<th class="rb_line">포인트</th>
										<td class="rb_line">기본급</td>
										<td>0원</td>
									</tr>
									<tr>
										<td colspan="2" class="tbb_line rb_line" style="text-align:center;">소계</td>
										<td class="tbb_line">0원</td>
									</tr>
									<tr>
										<td colspan="2" class="rb_line" style="text-align:center;">할인 및 포인트 차감 후 결제 금액</td>
										<td>0원</td>
									</tr>
								</tbody>
							</table>
							<p class="sub_tit3">< 주문동의 ></p>
							<span class="complete">
								주문하시려는 상품에 대한 수량, 가격, 배송정보 등에 대하여 확인하였으며,
								이에 동의하십니까?
							</span>
							<p class="agree_check">
							<input type="checkbox" id="agr_check" />
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