<%@ page contentType="text/html;charset=euc-kr" %>

<%
    /* ============================================================================== */
    /* =   PAGE : ��� ��û PAGE                                                    = */
    /* = -------------------------------------------------------------------------- = */
    /* =   �Ʒ��� �� ���� �� �κ��� �� �����Ͻÿ� ������ �����Ͻñ� �ٶ��ϴ�.       = */
    /* = -------------------------------------------------------------------------- = */
    /* =   ������ ������ �߻��ϴ� ��� �Ʒ��� �ּҷ� �����ϼż� Ȯ���Ͻñ� �ٶ��ϴ�.= */
    /* =   ���� �ּ� : http://kcp.co.kr/technique.requestcode.do			        = */
    /* = -------------------------------------------------------------------------- = */
    /* =   Copyright (c)  2013   KCP Inc.   All Rights Reserverd.                   = */
    /* ============================================================================== */
%>
<%
	request.setCharacterEncoding ( "euc-kr" ) ;
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
<%
    /* ============================================================================== */
    /* =   ��ҿ� �ʿ��� ����                                                       = */
    /* = -------------------------------------------------------------------------- = */
    String tno        = f_get_parm( request.getParameter("param")) ;     // ��� ��û �ŷ���ȣ
    /* ============================================================================== */
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <title>*** KCP [AX-HUB Version] ***</title>
	<meta http-equiv="Content-Type" content="text/html; charset=euc-kr" />
    <link href="sample/css/style.css" rel="stylesheet" type="text/css"/>

        <script type="text/javascript">
        /* ��� ��ư�� ������ �� ȣ�� */
    function  jsf__go_cancel()
    {
    	   
    	 document.cancel_info.submit();
    }
        
    </script>

<body onload="jsf__go_cancel()">

    <div id="sample_wrap">
<%
    /* ============================================================================== */
    /* =    1. ��� ��û ���� �Է� ��(cancel_info)                                  = */
    /* = -------------------------------------------------------------------------- = */
    /* =   ��� ��û�� �ʿ��� ������ �����մϴ�.                                    = */
    /* = -------------------------------------------------------------------------- = */
%>
    <form name="cancel_info" action="/resources/kcp/pp_ax_hub.jsp" method="post">
		<input type="hidden" name="mod_type"   value="STSC"  />
		<input type="text" name="tno" value="${tno}"  class="w200" maxlength="14"/>
		<input type="text" name="mod_desc" value="cacel" class="w200" maxlength="50"/>
 		<input type="hidden" name="ordr_idxx"   value="${orderInfo.orderNo}"  />
        <input type="hidden" name="custId"   value="${orderInfo.customerInfo.custId}" />
        <input type="hidden" name="req_tx"   value="${req_tx}"  />
    </form>
<%
    /* = -------------------------------------------------------------------------- = */
    /* =   1. ��� ��û ���� END                                                    = */
    /* ============================================================================== */
%>
</div>
</body>
</html>
