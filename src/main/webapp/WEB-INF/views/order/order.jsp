<%@ page language="java" contentType="text/html;charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c_rt"%> 
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<%
    /* ============================================================================== */
    /* =   PAGE : ���� ��û PAGE                                                    = */
    /* = -------------------------------------------------------------------------- = */
    /* =   �� �������� Payplus Plug-in�� ���ؼ� �����ڰ� ���� ��û�� �ϴ� ������    = */
    /* =   �Դϴ�. �Ʒ��� �� �ʼ�, �� �ɼ� �κа� �Ŵ����� �����ϼż� ������        = */
    /* =   �����Ͽ� �ֽñ� �ٶ��ϴ�.                                                = */
    /* = -------------------------------------------------------------------------- = */
    /* =   ������ ������ �߻��ϴ� ��� �Ʒ��� �ּҷ� �����ϼż� Ȯ���Ͻñ� �ٶ��ϴ�.= */
    /* =   ���� �ּ� : http://kcp.co.kr/technique.requestcode.do			        = */
    /* = -------------------------------------------------------------------------- = */
    /* =   Copyright (c)  2013   KCP Inc.   All Rights Reserverd.                   = */
    /* ============================================================================== */
%>
<%
    /* ============================================================================== */
    /* =   ȯ�� ���� ���� Include                                                   = */
    /* = -------------------------------------------------------------------------- = */
    /* =   �� �ʼ�                                                                  = */
    /* =   �׽�Ʈ �� �ǰ��� ������ site_conf_inc.jsp ������ �����Ͻñ� �ٶ��ϴ�.    = */
    /* = -------------------------------------------------------------------------- = */
%>
<%@ include file="/resources/kcp/site_conf_inc.jsp" %>
<%
	request.setCharacterEncoding ( "euc-kr" ) ;
    /* = -------------------------------------------------------------------------- = */
    /* =   ȯ�� ���� ���� Include END                                               = */
    /* ============================================================================== */
%>
<%!
    /* ============================================================================== */
    /* =   null ���� ó���ϴ� �޼ҵ�                                                = */
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
<title>###### �������θ�Ǹ� ######</title>
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
    /* =   �� �ʼ�                                                                  = */
    /* =   �׽�Ʈ �� �ǰ��� ������ site_conf_inc.jsp������ �����Ͻñ� �ٶ��ϴ�.     = */
    /* = -------------------------------------------------------------------------- = */
%>
    <script type="text/javascript" src="<%= g_conf_js_url %>"></script>
<%
    /* = -------------------------------------------------------------------------- = */
    /* =   Javascript source Include END                                            = */
    /* ============================================================================== */
%>
    <script type="text/javascript">
        /* �÷����� ��ġ(Ȯ��) */
        StartSmartUpdate();
        
        /*  �ش� ��ũ��Ʈ�� Ÿ���������� ������ ���� �ʽ��ϴ�.
        if( document.Payplus.object == null )
        {
            openwin = window.open( "chk_plugin.html", "chk_plugin", "width=420, height=100, top=300, left=300" );
        }
        */

        /* Payplus Plug-in ���� */
        function  jsf__pay( form )
        {
            var RetVal = false;

						/* �� ������ ���� ���� */
						document.getElementById("buyr_tel1").value = document.getElementById("tel1").value + document.getElementById("tel2").value + document.getElementById("tel3").value ;
						document.getElementById("buyr_tel2").value = document.getElementById("hp1").value + document.getElementById("hp2").value + document.getElementById("hp3").value ;

						if(parseInt(document.getElementById("good_mny").value) <= 0){
							alert("�ּҰ����ݾ��� 1000�� �̻��̾�� �մϴ�");
							return false ;
						}

						if (!$('input[name="agr_check"]').is(":checked")){
							alert("�ֹ��� �����Ͽ��� �ֹ��� �Ϸ�˴ϴ�.") ;
							return false;
						}
						/* �� ������ ���� �� */


            /* Payplus Plugin ���� */
            if ( MakePayMessage( form ) == true )
            {
                openwin = window.open( "/resources/kcp/proc_win.html", "proc_win", "width=449, height=209, top=300, left=300" );
                RetVal = true ;
            }
            
            else
            {
                /*  res_cd�� res_msg������ �ش� �����ڵ�� �����޽����� �����˴ϴ�.
                    ex) ���� Payplus Plugin���� ��� ��ư Ŭ���� res_cd=3001, res_msg=����� ���
                    ���� �����˴ϴ�.
                */
                res_cd  = document.order_info.res_cd.value ;
                res_msg = document.order_info.res_msg.value ;

            }

            return RetVal ;
        }

        // Payplus Plug-in ��ġ �ȳ� 
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

        /* �ֹ���ȣ ���� ���� */
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
             * ���ͳ� �ͽ��÷η��� ���̾�����(���ĸ�, ũ��.. ���)�� javascript �Ľ̹��� Ʋ���� ������ object �� �ν� ���� ���� �Ǵ� ����
             * �������� onload �κп� �߰��� ������ setTimeout �κп� �߰�
             * setTimeout 300�� �ǹ̴� �÷����� �νļӵ��� ���� �����ð� ����
             * - 20101018 -
             */
            setTimeout("init_pay_button();",300);
        }

        /* onLoad �̺�Ʈ �� Payplus Plug-in�� ����ǵ��� �����Ͻ÷��� ������ ������ onLoad �̺�Ʈ�� �־��ֽñ� �ٶ��ϴ�. */
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

					// ���� ����� ����Ʈ�� Ȯ���Ѵ�
					if($.isNumeric(document.getElementById("use_point").value)){
						var usePoint = parseInt(document.getElementById("use_point").value) ;
					}else{
						var usePoint = 0 ;
						document.getElementById("use_point").value = 0 ;
					}

					// ���� ���� ������ ����Ʈ�� Ȯ���Ѵ�
					var getUserPoint = parseInt(document.getElementById("user_point").value) ;
					var buyPrice     = parseInt(document.getElementById("total_price").value) ; 

					if(getUserPoint < usePoint){

						alert("�������Ʈ�� ��������Ʈ���� �����ϴ�.") ;
						document.getElementById("use_point").value = 0 ;
						usePointChecker();
						return false ; 

					}else{

						var resultBuyPrice = parseInt(buyPrice - usePoint) ;

						if(resultBuyPrice >= 0){

							if(resultBuyPrice >= 1000){

								var resultValue    = getUserPoint - usePoint ;
								var resultBuyPrice = buyPrice - usePoint ;

								// ����Ʈ ���
								document.getElementById("use_point").value         = usePoint ; 
								document.getElementById("myPointView").innerHTML   = resultValue + " P" ; 
								document.getElementById("usePointView2").innerHTML = usePoint ; 

								// ������ ���
								document.getElementById("good_mny").value            = resultBuyPrice ; 
								document.getElementById("resultPriceView").innerHTML = resultBuyPrice ; 
								document.getElementById("result_user_point").value   = resultValue ; 

							}else{

								alert("�ּҰ����ݾ��� 1000���̻��̾�� �մϴ�.") ;
								document.getElementById("use_point").value = 0 ;
								//usePointChecker();
								return false ; 

							}

						}else{

							alert("�������Ʈ�� �����ݾ׺��� �����ϴ�.") ;
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
	
	<!--  header ��   -->
	<div class="container">
		<c:import url="../inc/orderLnb.jsp" />
		<div class="sub_content">
			
			<!-- �ֹ����� �Է� form : order_info -->
			<form name="order_info" method="post" action="/resources/kcp/pp_ax_hub.jsp" >
			<input type="hidden" id="customerInfo.custId"        name="customerInfo.custId"  value="${cus.custId}">
			<input type="hidden" id="ord_unit_chk"               name="ord_unit_chk"  value="${orderInfo.ord_unit_chk}">
			<input type="hidden" id="ordr_idxx"                  name="ordr_idxx" value="${orderInfo.orderNo}">
			<input type="hidden" id="customerInfo.modifyUserId"  name="customerInfo.modifyUserId"  value="${cus.custId}">
			<input type="hidden" id="ord_unit_chk" name="ord_unit_chk"  value="${orderInfo.ord_unit_chk}"/>
			<c:if test="${odPrdInfo.size() == 1}"><input type="hidden"  name="good_name"  value="${odPrdInfo[0].prdNm}" /></c:if>
			<c:if test="${odPrdInfo.size() != 1}"><input type="hidden" id="ordPrd.prdNm"   name="good_name" value="${odPrdInfo[0].prdNm} �� ${odPrdInfo.size()-1}��" /></c:if>

			<!-- ���� ��������Ʈ ���� -->
			<!--<input type="text" id="user_point" name="user_point"  value="${userPoint}"/>-->
			<input type="hidden" id="user_point"        name="user_point"         value="10000000"/>
			<input type="hidden" id="result_user_point" name="result_user_point"  value="10000000"/>

			<!-- ���� ��������Ʈ �� -->

			<!-- �ֹ��� �� ��ȭ�� �ʿ��� �ʵ� ���� -->
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
			<!-- �ֹ��� �� ��ȭ�� �ʿ��� �ʵ� �� -->

				<div class="porder_section">
					<h4>��ǰ�ֹ�</h4>
					<div class="porder_step">
						<ul>
							<li><img src="<c:url value='/resources/img/sub/shopping_loc3_off.png'/>"  alt="SETP3 �ֹ��Ϸ��̹���"/></li>
							<li class="mmargin"><img src="<c:url value='/resources/img/sub/shopping_loc2_on.png'/>"  alt="SETP2 �ֹ�/�����̹���"/></li>
							<li class="mmargin"><img src="<c:url value='/resources/img/sub/shopping_loc1_off.png'/>"  alt="SETP1 īƮ�̹���"/></li>
						</ul>
						<h5>�ֹ��Ͻ� ��ǰ</h5>
						<table class="order_tbl" summary="�ֹ���ǰ ����Ʈ">
							<caption>�ֹ���ǰ ���ǥ</caption>
							<colgroup>
								<col />
								<col width="12%"/>
								<col width="10%"/>
								<col width="12%"/>
								<col width="10%"/>
							</colgroup>
							<thead>
								<tr>
									<th>��ǰ�� / �ɼ�</th>
									<th>�Ǹűݾ�</th>
									<th>����</th>
									<th>�ֹ��ݾ�</th>
									<th>����</th>
								</tr>
							</thead>
							<tbody>
							<c:choose>
								<c:when test="${odPrdInfo.size() != 0}">
									<c:forEach items="${odPrdInfo}" var="odPrdInfo" varStatus="i">
										<tr>
											<td class="product_area leftalign">
												<span>
													<img src="${odPrdInfo.prdSmallImg}"  alt="��ǰ�̹���"  width="71" height="71">
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
												<input type="button" value="�����ϱ�"class="btn_choice2" onClick="confirm_process('','�ش� ��ǰ�� �����Ͻðڽ��ϱ�?','deleteOrderList.do?cookieKey=${odPrdInfo.cookieKey}&ord_unit_chk=${orderInfo.ord_unit_chk}');" />
											</td>
										</tr>
										<tr><td height="1" colspan="6" bgcolor="#E0E0E0"></td></tr>
									</c:forEach>
								</c:when>
								<c:otherwise><tr><td colspan="5" height="90">�����Ͻ� ��ǰ�� �����ϴ�.</td></tr></c:otherwise>
								</c:choose>
								<tr>
									<c:set var="total"  value="0"/>
									<td class="total_choice" colspan="6">
										�� �ֹ��ݾ� : 
										<c:forEach items="${odPrdInfo}" var="odPrdInfo">
										<script>SetPriceInput('${odPrdInfo.totalPrice}');</script>�� +
										<c:set var="total" value="${total+odPrdInfo.totalPrice}"/>
										</c:forEach>
										 <c:if test="${total>=config.buyPrice}">��ۺ� :  <script>SetPriceInput('${config.trasferPrice}');</script>��</c:if>
										 <c:if test="${total<config.buyPrice}">��ۺ� : 0�� </c:if>
										 = �հ� <strong><script>SetPriceInput('${total}');</script></strong>��


										 <input type="hidden" id="total_price" name="total_price" value="${total}"/>
										 <input type="hidden" id="good_mny"    name="good_mny"    value="${total}"/>

										 <input type="hidden" id="sndAmount"  name="sndAmount"  value="${total}" /><!-- kst net -->
									</td>
								</tr>
								
							</tbody>
						</table>
						
						<h5>�ֹ��� �ۼ� �� ����</h5>
						<p class="sub_tit1">�ֹ���/��������� �Է�</p>
						<p class="sub_tit2">< �ֹ��Ͻô� �� ></p>
						<table class="order_tblbox" summary="�ֹ�������ǥ">
							<caption>������ ���ǥ</caption>
							<colgroup>
								<col width="15%"/>
								<col width="35%"/>
								<col width="15%"/>
								<col width="*"/>
							</colgroup>
							<tbody>
								<tr>
									<th>����</th>
									<td class="in_text">${cus.custNm}</td>
									<th>��ȭ��ȣ</th>
									<td class="in_sectext">
										${cus.telNo1}-${cus.telNo2}-${cus.telNo3}
										<input type="hidden" id="buyr_tel1" name="buyr_tel1" value="${cus.telNo1}${cus.telNo2}${cus.telNo3}"/>
									</td>
								</tr>
								<tr>
									<th>�޴���ȭ</th>
									<td class="in_sectext">
										${cus.hpNo1}-${cus.hpNo2}-${cus.hpNo3}
										<input type="hidden" id="buyr_tel2" name="buyr_tel2" value="${cus.hpNo1}${cus.hpNo2}${cus.hpNo3}"/>
									</td>
									<th>�̸���</th>
									<td class="in_text">${cus.custEmail}</td>
								</tr>
							</tbody>
						</table>
						<p class="sub_tit2">< �����ô� �� ></p>
						<table class="order_tblbox" summary="�ֹ�������ǥ">
							<caption>������ ���ǥ</caption>
							<colgroup>
								<col width="15%"/>
								<col width="35%"/>
								<col width="15%"/>
								<col width="*"/>
							</colgroup>
							<tbody>
								<tr>
									<th>����� ����</th>
									<td class="check_box">
										<input type="radio" id="basic_check" name="choice" onClick="addr_samsam('basic');" checked /><label for="basic_check">�⺻�����</label> &nbsp;&nbsp;
										<input type="radio" id="new_check"   name="choice" onClick="addr_samsam('new');" /><label for="new_check">�����Է�</label>
									</td>
									<th>�����ôº�</th>
									<td class="in_text">
										<input type="text" id="reciNm" name="reciNm" value="${cus.custNm}" title="�����ôº� ���� ����"/>
										<input type="hidden"  id="buyr_name" name="buyr_name" value="${cus.custNm}" />
									</td>
								</tr>
								<tr>
									<th>��ȭ��ȣ</th>
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
										<input type="text" id="tel2" name="tel2" value="${cus.telNo2}" title="��ȭ��ȣ" maxlength="4" style="width:40px;"/> -
										<input type="text" id="tel3" name="tel3" value="${cus.telNo3}" title="��ȭ��ȣ" maxlength="4" style="width:40px;"/>
									</td>
									<th>�޴���ȭ��ȣ</th>
									<td class="in_sectext">
										<select id="hp1" name="hp1">
											<option value="010" <c:if test="${cus.hpNo1 eq '010'}">selected</c:if>>010</option>
											<option value="011" <c:if test="${cus.hpNo1 eq '011'}">selected</c:if>>011</option>
											<option value="016" <c:if test="${cus.hpNo1 eq '016'}">selected</c:if>>016</option>
											<option value="017" <c:if test="${cus.hpNo1 eq '017'}">selected</c:if>>017</option>
											<option value="018" <c:if test="${cus.hpNo1 eq '018'}">selected</c:if>>018</option>
											<option value="019" <c:if test="${cus.hpNo1 eq '019'}">selected</c:if>>019</option>
										</select> -
										<input type="text" id="hp2" name="hp2" value="${cus.hpNo2}" title="�޴���ȭ��ȣ" maxlength="4" style="width:40px;"/> -
										<input type="text" id="hp3" name="hp3" value="${cus.hpNo3}" title="�޴���ȭ��ȣ" maxlength="4" style="width:40px;"/>
									</td>
								</tr>
								<tr>
									<th>�̸����ּ�</th>
									<td colspan="3" class="in_sectext" style="padding:5px;">
										<input type="text" title="email text"  name="buyr_mail" id="buyr_mail" value="${cus.custEmail}" style="width:300px;" required hname="�̸����� �Է��Ͽ� �ֽʽÿ�" />
									</td>
								</tr>
								<tr>
									<th>����ּ�</th>
									<td colspan="3" class="in_sectext" style="padding:5px;">
										<input type="text" title="address text" style="width:60px;" id="custZip1" name="reciAdd1" value="${cus.custZip1}" readonly required hname="�����ȣ�� �Է��Ͽ� �ֽʽÿ�" />-
										<input type="text" title="address text" style="width:60px;" id="custZip2" name="reciAdd2" value="${cus.custZip2}" readonly required hname="�����ȣ�� �Է��Ͽ� �ֽʽÿ�" />
										<input type="button" value="�����ȣ ã��" style="width:100px;height:21px;" onClick="openWin('/user/searchZipCode.do?type=userEdit','searchZipuserRegiForm',650,450,'scrollbars=yes');" /><br/>
										<input type="text" title="address text" style="width:50%;" id="custAdd"       name="reciAdd3" value="${cus.custAdd}" readonly/>
										<input type="text" title="address text" style="width:40%;" id="custAddDetail" name="reciAdd4" value="${cus.custAddDetail}"/>
									</td>
								</tr>
								<tr>
									<th>��۽� ��û����</th>
									<td colspan="3" class="arrive_box" style="padding:5px;">
										<textarea id="reciReq" name="reciReq" title="��۽� ��û���� ����" style="width:95%;height:120px;"></textarea>
									</td>
								</tr>
							</tbody>
						</table>
						<div class="point_employ1">
							<p class="sub_tit1">����Ʈ ����ϱ�</p>
							<table class="employ_tbl" summary="������Ʈǥ">
								<caption>����Ʈ ǥ</caption>
								<colgroup>
									<col width="30%"/>
									<col width="30%"/>
									<col width="40%"/>
								</colgroup>
								<thead>
									<tr>
										<th class="rb_line">�� ��</th>
										<th class="rb_line">���� ����Ʈ</th>
										<th>��� ����Ʈ</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<th class="rb_line">�����Ȳ</th>
										<td class="rb_line"><span id="myPointView">${userPoint} P</span></td>
										<td class="in_text">
											<input type="text" id="use_point" name="use_point" value="0" style="width:80%;text-align:right;padding-right:5px;" onBlur="usePointChecker();" onkeypress="numKeyOnly()" /><label>P</label>
										</td>
									</tr>
								</tbody>
							</table>
							<p class="sub_tit1">���� ���� ����</p>
							<span class="payment1">
								
								<input type="radio" id="settlement_type" name="settlement_type" onClick="document.getElementById('pay_method').value='100000000000' ;" value="100000000000" checked /><label for="credit_card">�ſ�ī��</label>
								<input type="radio" id="settlement_type" name="settlement_type" onClick="document.getElementById('pay_method').value='010000000000' ;" value="010000000000" /><label for="account_transfer">�ǽð� ������ü</label>
								<input type="hidden" id="pay_method" name="pay_method" value="100000000000">
								
								<!--
								<select name="pay_method">
									<option value="100000000000">�ſ�ī��</option>
									<option value="010000000000">������ü</option>
									<option value="000100000000">����Ʈ</option>
								</select>
								-->

							</span>
							<span class="payment2">
								<input type="radio" id="welfare_card" name="payment2" checked /><label for="welfare_card">���� ī�� ����Ʈ ������</label>
								<input type="radio" id="welfare_ncard" name="payment2"/><label for="welfare_ncard">���� ī�� ����Ʈ ���</label>
							</span>
						</div>
						<div class="point_employ2">
							<p class="sub_tit1">������/����Ʈ ����ϱ�</p>
							<table class="employ_tbl" summary="������Ʈǥ">
								<caption>����Ʈ ���ǥ</caption>
								<colgroup>
									<col width="20%"/>
									<col width="20%"/>
									<col width="40%"/>
								</colgroup>
								<thead>
									<tr>
										<th class="rb_line">�� �ֹ��ݾ�</th>
										<th colspan="2">${total} ��</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<th class="tbb_line rb_line">����Ʈ</th>
										<td class="tbb_line rb_line">���</td>
										<td class="tbb_line"><span id="usePointView2">0</span> P</td>
									</tr>
									<tr>
										<td colspan="2" class="rb_line" style="text-align:center;">���� �� ����Ʈ ���� �� ���� �ݾ�</td>
										<td><span id="resultPriceView" style="color:#5d85bc;font-weight:bold;">${total}</span> ��</td>
									</tr>
								</tbody>
							</table>
							<p class="sub_tit3">< �ֹ����� ></p>
							<span class="complete">
								�ֹ��Ͻ÷��� ��ǰ�� ���� ����, ����, ������� � ���Ͽ� Ȯ���Ͽ�����,
								�̿� �����Ͻʴϱ�?
							</span>
							<p class="agree_check">
							<input type="checkbox" id="agr_check" name="agr_check" value="y"/>
							<label for="agr_check">�����մϴ�.</label>
						</p>
						</div>
					</div>
					<div class="complet_area">
						<span class="btn_complete">
							<!-- <input type="button" value="�����ϱ�" title="������ư" onClick="_pay(document.KSPayWeb);"/> -->
							<input name="" type="submit" class="submit" value="�����ϱ�" onclick="return jsf__pay(this.form);"/>
						</span>
						<span class="btn_cancle">
							<input type="reset" value="����ϱ�" title="��ҹ�ư"/>
						</span>
					</div>
				</div>
<%
    /* = -------------------------------------------------------------------------- = */
    /* =   1. �ֹ� ���� �Է� END                                                    = */
    /* ============================================================================== */
%>

<%
    /* ============================================================================== */
    /* =   2. ������ �ʼ� ���� ����                                                 = */
    /* = -------------------------------------------------------------------------- = */
    /* =   �� �ʼ� - ������ �ݵ�� �ʿ��� �����Դϴ�.                               = */
    /* =   site_conf_inc.jsp ������ �����ϼż� �����Ͻñ� �ٶ��ϴ�.                 = */
    /* = -------------------------------------------------------------------------- = */
    // ��û���� : ����(pay)/���,����(mod) ��û�� ���
%>
    <input type="hidden" name="req_tx"          value="pay" />
    <input type="hidden" name="site_cd"         value="<%= g_conf_site_cd   %>" />
    <input type="hidden" name="site_name"       value="<%= g_conf_site_name %>" />
<%
    /*
       �Һοɼ� : Payplus Plug-in���� ī������� �ִ�� ǥ���� �Һΰ��� ���� �����մϴ�.(0 ~ 18 ���� ���� ����)
       �� ����  - �Һ� ������ �����ݾ��� 50,000�� �̻��� ��쿡�� ����, 50000�� �̸��� �ݾ��� �Ͻúҷθ� ǥ��˴ϴ�
                  ��) value ���� "5" �� �������� ��� => ī������� ����â�� �ϽúҺ��� 5�������� ���ð���
    */
%>
    <input type="hidden" name="quotaopt"        value="12"/>
    <!-- �ʼ� �׸� : ���� �ݾ�/ȭ����� -->
    <input type="hidden" name="currency"        value="WON"/>
<%
    /* = -------------------------------------------------------------------------- = */
    /* =   2. ������ �ʼ� ���� ���� END                                             = */
    /* ============================================================================== */
%>

<%
    /* ============================================================================== */
    /* =   3. Payplus Plugin �ʼ� ����(���� �Ұ�)                                   = */
    /* = -------------------------------------------------------------------------- = */
    /* =   ������ �ʿ��� �ֹ� ������ �Է� �� �����մϴ�.                            = */
    /* = -------------------------------------------------------------------------- = */
%>
    <!-- PLUGIN ���� �����Դϴ�(���� �Ұ�) -->
    <input type="hidden" name="module_type"     value="<%= module_type %>"/>
<!--
      �� �� ��
          �ʼ� �׸� : Payplus Plugin���� ���� �����ϴ� �κ����� �ݵ�� ���ԵǾ�� �մϴ�
          ���� �������� ���ʽÿ�
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

    <!--  ���ݿ����� ���� ���� : Payplus Plugin ���� �����ϴ� �����Դϴ� -->
    <input type="hidden" name="cash_tsdtime"    value=""/>
    <input type="hidden" name="cash_yn"         value=""/>
    <input type="hidden" name="cash_authno"     value=""/>
    <input type="hidden" name="cash_tr_code"    value=""/>
    <input type="hidden" name="cash_id_info"    value=""/>

	<!-- 2012�� 8�� 18�� ���ڻ�ŷ��� ���� ���� ���� �κ� -->
	<!-- ���� �Ⱓ ���� 0:��ȸ�� 1:�Ⱓ����(ex 1:2012010120120131)  -->
	<input type="hidden" name="good_expr" value="0">

	<!-- ���������� �����ϴ� �� ���̵� ������ �ؾ� �մϴ�.(�ʼ� ����) -->
	<input type="hidden" name="shop_user_id"    value=""/>
	<!-- ��������Ʈ ������ �������� �Ҵ�Ǿ��� �ڵ� ���� �Է��ؾ��մϴ�.(�ʼ� ����) -->
    <input type="hidden" name="pt_memcorp_cd"   value=""/>

<%
    /* = -------------------------------------------------------------------------- = */
    /* =   3. Payplus Plugin �ʼ� ���� END                                          = */
    /* ============================================================================== */
%>

<%
    /* ============================================================================== */
    /* =   4. �ɼ� ����                                                             = */
    /* = -------------------------------------------------------------------------- = */
    /* =   �� �ɼ� - ������ �ʿ��� �߰� �ɼ� ������ �Է� �� �����մϴ�.             = */
    /* = -------------------------------------------------------------------------- = */

    /* ���ī�� ���� ���� �Ķ���� �Դϴ�.(���հ���â ���� ����)
    <input type="hidden" name="used_card_YN"        value="Y"/> */
    /* ���ī�� ���� �Ķ���� �Դϴ�. (�ش� ī�常 ����â�� ���̰� �����ϴ� �Ķ�����Դϴ�. used_card_YN ���� Y�϶� ����˴ϴ�.
    /<input type="hidden" name="used_card"        value="CCBC:CCKM:CCSS"/> */

    /* �ſ�ī�� ������ OKĳ���� ���� ���θ� ���� â�� �����ϴ� �Ķ���� �Դϴ�
         ����Ʈ �������� ��쿡�� â�� �������ϴ�
        <input type="hidden" name="save_ocb"        value="Y"/> */

    /* ���� �Һ� ���� �� ����
           value���� "7" �� �������� ��� => ī������� ����â�� �Һ� 7������ ���ð���
    <input type="hidden" name="fix_inst"        value="07"/> */

    /*  ������ �ɼ�
            �� �����Һ�    (������ ������ �������� ���� �� ������ ������ ������)                             - "" �� ����
            �� �Ϲ��Һ�    (KCP �̺�Ʈ �̿ܿ� ���� �� ��� ������ ������ �����Ѵ�)                           - "N" �� ����
            �� ������ �Һ� (������ ������ �������� ���� �� ������ �̺�Ʈ �� ���ϴ� ������ ������ �����Ѵ�)   - "Y" �� ����
    <input type="hidden" name="kcp_noint"       value=""/> */

    /*  ������ ����
            �� ���� 1 : �Һδ� �����ݾ��� 50,000 �� �̻��� ��쿡�� ����
            �� ���� 2 : ������ �������� ������ �ɼ��� Y�� ��쿡�� ���� â�� ����
            ��) �� ī�� 2,3,6���� ������(����,��,����,�Ｚ,����,����,�Ե�,��ȯ) : ALL-02:03:04
            BC 2,3,6����, ���� 3,6����, �Ｚ 6,9���� ������ : CCBC-02:03:06,CCKM-03:06,CCSS-03:06:04
    <input type="hidden" name="kcp_noint_quota" value="CCBC-02:03:06,CCKM-03:06,CCSS-03:06:09"/> */

    /* �ؿ�ī�� �����ϴ� �Ķ���� �Դϴ�.(�ؿܺ���, �ؿܸ�����, �ؿ�JCB�� �����Ͽ� ǥ��)
    <input type="hidden" name="used_card_CCXX"        value="Y"/> */

    /*  ������� ���� ���� �Ķ����
         �� �ش� ������ ����â���� ���̰� �մϴ�.(�����ڵ�� �Ŵ����� ����)
    <input type="hidden" name="wish_vbank_list" value="05:03:04:07:11:23:26:32:34:81:71"/> */

    /*  ������� �Ա� ���� �����ϴ� �Ķ���� - �߱��� + 3��
    <input type="hidden" name="vcnt_expire_term" value="3"/> */

    /*  ������� �Ա� �ð� �����ϴ� �Ķ����
         HHMMSS�������� �Է��Ͻñ� �ٶ��ϴ�
         ������ ���Ͻô°�� �⺻������ 23��59��59�ʰ� ������ �˴ϴ�
         <input type="hidden" name="vcnt_expire_term_time" value="120000"/> */

    /* ����Ʈ ������ ���� ����(�ſ�ī��+����Ʈ) ���θ� ������ �� �ֽ��ϴ�.- N �ϰ�� ���հ��� ������
        <input type="hidden" name="complex_pnt_yn" value="N"/>    */

    /* ���ݿ����� ��� â�� ��� ���θ� �����ϴ� �Ķ���� �Դϴ�
         �� Y : ���ݿ����� ��� â ���
         �� N : ���ݿ����� ��� â ��� ���� 
    �� ���� : ���ݿ����� ��� �� KCP ���������� ���������� ���ݿ����� ��� ���Ǹ� �ϼž� �մϴ�
        <input type="hidden" name="disp_tax_yn"     value="Y"/> */

    /* ����â�� ������ ����Ʈ�� �ΰ� �÷����� ���� ��ܿ� ����ϴ� �Ķ���� �Դϴ�
       ��ü�� �ΰ� �ִ� URL�� ��Ȯ�� �Է��ϼž� �ϸ�, �ִ� 150 X 50  �̸� ũ�� ����

    �� ���� : �ΰ� �뷮�� 150 X 50 �̻��� ��� site_name ���� ǥ�õ˴ϴ�.
        <input type="hidden" name="site_logo"       value="" /> */

    /* ����â ���� ǥ�� �Ķ���� �Դϴ�. ������ �⺻���� ����Ͻ÷��� Y�� �����Ͻñ� �ٶ��ϴ�
    	2010-06�� ���� �ſ�ī��� ������¸� �����˴ϴ�
        <input type='hidden' name='eng_flag'      value='Y'> */

    /* KCP�� ������ǰ�� �������ǰ�� ���ÿ� �Ǹ��ϴ� ��ü���� ���������� ���� ���Ǽ��� �����ص帮����, 
       ���հ��� ���� ����Ʈ�ڵ带 ������ �帮�� �� �ݾ׿� ���� ���հ��� ó���� �����ϵ��� �����ϰ� �ֽ��ϴ�
       ���հ��� ���� ����Ʈ �ڵ�� ����Ͻ� ���������� �ش��� �˴ϴ�
       ��ǰ���� �ƴ϶� �ݾ����� �����Ͽ� ��û�ϼž� �մϴ�
       �Ѱ��� �ݾ��� �����ݾ� + �ΰ��� + ������ݾ��� �հ� ���ƾ� �մϴ�. 
       (good_mny = comm_tax_mny + comm_vat_mny + comm_free_mny)

        <input type="hidden" name="tax_flag"       value="TG03">  <!-- ����Ұ�	   -->
        <input type="hidden" name="comm_tax_mny"   value=""    >  <!-- �����ݾ�	   --> 
        <input type="hidden" name="comm_vat_mny"   value=""    >  <!-- �ΰ���	   -->
        <input type="hidden" name="comm_free_mny"  value=""    >  <!-- ����� �ݾ� --> */

    /* skin_indx ���� ��Ų�� ������ �� �ִ� �Ķ�����̸� �� 7������ �����˴ϴ�. 
       ������ ���Ͻø� 1���� 7���� ���� �־��ֽñ� �ٶ��ϴ�. 

        <input type='hidden' name='skin_indx'      value='1'> */

    /* ��ǰ�ڵ� ���� �Ķ���� �Դϴ�.(��ǰ���� ���� �����Ͽ� ó���� �� �ִ� �ɼǱ���Դϴ�.)
        <input type='hidden' name='good_cd'      value=''> */

    /* = -------------------------------------------------------------------------- = */
    /* =   4. �ɼ� ���� END                                                         = */
    /* ============================================================================== */
%>
			</form>
		</div>
	</div>
<!--  container ��   -->	

	<div class="footer">
		<div class="footer_area">
			<h2><img src="<c:url value='/resources/img/common/footer_logo.jpg'/>"  alt="���� �ΰ�"/></h2>
			<address>
				�����ŷ�����ȸ ��� ��2001-1ȣ�� ���� ����� ��Ϲ�ȣ:212-81-86027�Ӵ�ǥ�̻� : ��ȭ��<br/>
				������������ å���� ���λ���� ���ο���1�� �ۼ�ȣ ���� l �ּ�:����� ������ �ϻ絿 513-16���� ����H&S<br/>
				COPYRIGHT 2012 BY ����H&S ALL RIGHT RESERVED.
			</address>
		</div>
	</div>
</div>
</body>
</html>

<!-- ���� ��û/ó������ �̹��� -->
<div class="btnset" id="display_pay_button" style="display:block"></div>

<!-- Payplus Plug-in ��ġ �ȳ� -->
<div id="display_setup_message" style="display:none">
	<p class="txt">
	������ ��� �Ͻ÷��� ����� ����� ǥ������ Ŭ�� �Ͻðų� <a href="http://pay.kcp.co.kr/plugin_new/file/KCPUXWizard.exe"><span>[������ġ]</span></a>�� ���� Payplus Plug-in�� ��ġ�Ͻñ� �ٶ��ϴ�.
	[������ġ]�� ���� ��ġ�Ͻ� ��� ���ΰ�ħ(F5)Ű�� ���� �����Ͻñ� �ٶ��ϴ�.
	</p>
</div>