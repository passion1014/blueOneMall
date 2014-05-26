<%@ page language="java" contentType="text/html;charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<c:import  url="../inc/topSub.jsp" />
<c:import  url="../inc/topMain.jsp" />    
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
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<title>###### �������θ�Ǹ� ######</title>
  <meta http-equiv="Content-Type" content="text/html; charset=euc-kr" />
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
            document.order_info.ordr_idxx.value =document.getElementById('order_idxx').value;

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
			<input type="hidden" id="customerInfo.custId"  name="customerInfo.custId"  value="${cus.custId}">
			<input type="hidden" id="ord_unit_chk"  name="ord_unit_chk"  value="${orderInfo.ord_unit_chk}">
			<%-- <input type="hidden" id="orderNo"  name="orderNo"  value="${orderInfo.orderNo}"> --%>
			<input type="hidden"  id="ordr_idxx" name="ordr_idxx" value="${orderInfo.orderNo}">
			<input type="hidden" id="customerInfo.modifyUserId"  name="customerInfo.modifyUserId"  value="${cus.custId}">
			<c:if test="${odPrdInfo.size() == 1}"><input type="hidden"  name="good_name"  value="${odPrdInfo[0].prdNm}" /></c:if>
			<c:if test="${odPrdInfo.size() != 1}"><input type="hidden" id="ordPrd.prdNm"   name="good_name" value="${odPrdInfo[0].prdNm} �� ${odPrdInfo.size()-1}��" /></c:if>
			
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
								<col width="5%"/>
								<col width="51%"/>
								<col width="12%"/>
								<col width="10%"/>
								<col width="12%"/>
								<col width="10%"/>
							</colgroup>
							<thead>
								<tr>
									<th>��ǰ��/�ɼ�</th>
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
										<img src="${odPrdInfo.prdSmallImg}"  alt="��ǰ�̹���"  width="71" height="71"></span>
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
										<span class="input_text">${odPrdInfo.buyCnt}<%-- <input type="text" id="buyCnt${i.index}" naem="buyCnt${i.index}" value="${odPrdInfo.buyCnt}" title="��������"><!-- <button class="btn_triangle1"></button> --> --%></span>
										<span class="input_btn"><%-- <input type="button" value="����" title="����" onClick="location.href='editOrderBuycn.do?ordIdx=${i.index}&ord_unit_chk=${orderInfo.ord_unit_chk}&orderProductList[${i.index}].buyCnt='+document.getElementById('buyCnt${i.index}').value;"> <!-- <button class="btn_triangle2"></button> --> --%></span>
									</td>
									<td>${odPrdInfo.totalPrice}</td>
									<td>
										<input type="button" value="�����ϱ�"class="btn_choice2" onClick="confirm_process('','�ش� ��ǰ�� �����Ͻðڽ��ϱ�?','deleteOrderList.do?cookieKey=${odPrdInfo.cookieKey}&ord_unit_chk=${orderInfo.ord_unit_chk}');" />
									</td>
									<tr>
									<td class="one_choice" colspan="6">
									</td>
								</tr>
								</tr>
								</c:forEach>
								</c:when>
								<c:otherwise><tr><td>�����Ͻ� ��ǰ�� �����ϴ�.</td></tr></c:otherwise>
								</c:choose>
								<tr>
									<c:set var="total"  value="0"/>
									<td class="total_choice" colspan="6">
										�� �ֹ��ݾ� : 
										<c:forEach items="${odPrdInfo}" var="odPrdInfo">
										${odPrdInfo.totalPrice}�� +
										<c:set var="total" value="${total+odPrdInfo.totalPrice}"/>
										</c:forEach>
										 <c:if test="${total>=config.buyPrice}">��ۺ� : ${config.trasferPrice}��</c:if>
										 <c:if test="${total<config.buyPrice}">��ۺ� : 0�� </c:if>
										 = �հ� <strong>${total}</strong>��
										 <input type="hidden" id="sndAmount"  name="sndAmount"  value="${total}" />
										 <input type="hidden"  name="good_mny"  value="${total}"/>
			
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
								<col width="35%"/>
							</colgroup>
							<tbody>
								<tr>
									<th>����</th>
									<td class="in_text">
										<input type="text"  name="buyr_name" title="����" value="${cus.custNm}"/>
									</td>
									<th>��ȭ��ȣ</th>
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
										-<input type="text" title="��ȭ��ȣ" name="customerInfo.telNo2" id="telNo2" value="${cus.telNo2}" />-
										<input type="text" title="��ȭ��ȣ" name="customerInfo.telNo3" id="telNo3" value="${cus.telNo3}"/>
										 <input type="hidden" name="buyr_tel1" value="document.getElementById(telNo1)+document.getElementById(telNo2)+document.getElementById(telNo3);"/>		
									</td>
								</tr>
								<tr>
									<th>�޴���ȭ</th>
									<td class="in_sectext">
										<select  name="customerInfo.hpNo1" id="hpNo1">
											<option <c:if test="${cus.hpNo1}">selected</c:if>>010</option>
											<option <c:if test="${cus.hpNo1}">selected</c:if>>011</option>
											<option <c:if test="${cus.hpNo1}">selected</c:if>>017</option>
											<option <c:if test="${cus.hpNo1}">selected</c:if>>016</option>
											<option <c:if test="${cus.hpNo1}">selected</c:if>>019</option>
											<option <c:if test="${cus.hpNo1}">selected</c:if>>018</option>
										</select>
										-<input type="text"  name="customerInfo.hpNo2" id="hpNo2"title="�޴���ȭ��ȣ" value="${cus.hpNo2}"/>-
										<input type="text"  name="customerInfo.hpNo3" id="hpNo3" title="�޴���ȭ��ȣ" value="${cus.hpNo3}"/>
										<input type="hidden" name="buyr_tel2" value="document.getElementById(hpNo1)+document.getElementById(hpNo2)+document.getElementById(hpNo3);"/>	
									</td>
									<th>�̸���</th>
									<td class="in_text">
										<input type="text" name="buyr_mail" title="�̸���" value="${cus.custEmail}"/>
									</td>
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
								<col width="35%"/>
							</colgroup>
							<tbody>
								<tr>
									<th>����� ����</th>
									<td class="check_box">
										<input type="radio" id="new_check" name="choice"/><label for="new_check">�����Է�</label>
										<input type="radio" id="basic_check" name="choice"/><label for="basic_check">�⺻�����</label>
										<button><img src="<c:url value='/resources/img/./images/common/btn_checkbox.gif'/>" alt="�� ��������� �����̹���"/></button>
									</td>
									<th>�����ôº�</th>
									<td class="in_text">
										<input type="text" id="reciInfo.reciNm" name="reciInfo.reciNm" title="�����ôº� ���� ����"/>
									</td>
								</tr>
								<tr>
									<th>��ȭ��ȣ</th>
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
										-<input type="text" id="reciInfo.reciPh" name="reciInfo.reciPh" title="��ȭ��ȣ"/>-
										<input type="text"  id="reciInfo.reciPh" name="reciInfo.reciPh" title="��ȭ��ȣ"/>
									</td>
									<th>�޴���ȭ��ȣ</th>
									<td class="in_sectext">
										<select  id="reciInfo.reciMb" name="reciInfo.reciMb"  >
											<option selected>010</option>
											<option>011</option>
											<option>017</option>
											<option>016</option>
											<option>019</option>
											<option>018</option>
										</select>
										-<input type="text" id="reciInfo.reciMb" name="reciInfo.reciMb" title="�޴���ȭ��ȣ"/>-
										<input type="text" id="reciInfo.reciMb" name="reciInfo.reciMb"  title="�޴���ȭ��ȣ"/>
									</td>
								</tr>
								<tr>
									<th>�ּ�</th>
									<td colspan="3" class="address_box">
										<span class="adr_box1">
											<input type="text" title="�����ȣ"  id="reciInfo.reciAdd" name="reciInfo.reciAdd" />
											<input type="button" value="�����ȣ ã��"onClick="openWin('/user/searchZipCode.do','searchZipForm',600,450,'scrollbars=no');"/><br/>
										</span>
										<span class="adr_box2">
											<input type="text" title="�ڵ��ּ�" id="reciInfo.reciAdd" name="reciInfo.reciAdd" />
										</span>
									</td>
								</tr>
								<tr>
									<th>��۽� ��û����</th>
									<td colspan="3" class="arrive_box">
										<input type="text" id="reciInfo.reciReq" name="reciInfo.reciReq" title="��۽� ��û���� ����"/>
									</td>
								</tr>
							</tbody>
						</table>
						<p class="adr_check">
							<input type="checkbox" id="adr_check" />
							<label for="adr_check">�������� �߰�(��� �Էµ� ����� ������ �� ����� ��Ͽ� �߰��մϴ�.)</label>
						</p>
						<div class="point_employ1">
							<p class="sub_tit1">������/����Ʈ ����ϱ�</p>
							<table class="employ_tbl" summary="������Ʈǥ">
								<caption>����Ʈ ���ǥ</caption>
								<colgroup>
									<col width="30%"/>
									<col width="30%"/>
									<col width="40%"/>
								</colgroup>
								<thead>
									<tr>
										<th class="rb_line">����</th>
										<th class="rb_line">�����ݾ�</th>
										<th>���ݾ�</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<th class="rb_line">�⺻��</th>
										<td class="rb_line">0��</td>
										<td class="in_text">
											<input type="text"/><label>��</label>
										</td>
									</tr>
								</tbody>
							</table>
							<p class="sub_tit1">���� ���� ����</p>
							<span class="payment1">
								<input type="radio" id="pay_method" name="pay_method" value="100000000000"><label for="credit_card">�ſ�ī��</label>
								<input type="radio" id="pay_method" name="pay_method" value="010000000000"><label for="account_transfer">�ǽð� ������ü</label>
						
								<!--  <select name="pay_method">
	                                <option value="100000000000">�ſ�ī��</option>
	                                <option value="010000000000">������ü</option>
	                                <option value="001000000000">�������</option>
	                                <option value="000100000000">����Ʈ</option>
	                                <option value="000010000000">�޴���</option>
	                                <option value="000000001000">��ǰ��</option>
	                                <option value="000000000010">ARS</option>
	                                <option value="111000000000">�ſ�ī��/������ü/�������</option>
	                            </select> -->
							</span>
							<span class="payment2">
								<input type="radio" id="welfare_card" name="payment2"/><label for="welfare_card">���� ī�� ����Ʈ ������</label>
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
										<th colspan="2"></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<th class="rb_line">����Ʈ</th>
										<td class="rb_line">�⺻��</td>
										<td>0��</td>
									</tr>
									<tr>
										<td colspan="2" class="tbb_line rb_line" style="text-align:center;">�Ұ�</td>
										<td class="tbb_line">0��</td>
									</tr>
									<tr>
										<td colspan="2" class="rb_line" style="text-align:center;">���� �� ����Ʈ ���� �� ���� �ݾ�</td>
										<td>0��</td>
									</tr>
								</tbody>
							</table>
							<p class="sub_tit3">< �ֹ����� ></p>
							<span class="complete">
								�ֹ��Ͻ÷��� ��ǰ�� ���� ����, ����, ������� � ���Ͽ� Ȯ���Ͽ�����,
								�̿� �����Ͻʴϱ�?
							</span>
							<p class="agree_check">
							<input type="checkbox" id="agr_check" />
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