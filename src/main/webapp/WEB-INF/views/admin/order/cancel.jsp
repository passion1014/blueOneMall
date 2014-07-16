<%@ page contentType="text/html;charset=euc-kr" %>

<%
    /* ============================================================================== */
    /* =   PAGE : 취소 요청 PAGE                                                    = */
    /* = -------------------------------------------------------------------------- = */
    /* =   아래의 ※ 주의 ※ 부분을 꼭 참고하시여 연동을 진행하시기 바랍니다.       = */
    /* = -------------------------------------------------------------------------- = */
    /* =   연동시 오류가 발생하는 경우 아래의 주소로 접속하셔서 확인하시기 바랍니다.= */
    /* =   접속 주소 : http://kcp.co.kr/technique.requestcode.do			        = */
    /* = -------------------------------------------------------------------------- = */
    /* =   Copyright (c)  2013   KCP Inc.   All Rights Reserverd.                   = */
    /* ============================================================================== */
%>
<%
	request.setCharacterEncoding ( "euc-kr" ) ;
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
<%
    /* ============================================================================== */
    /* =   취소에 필요한 정보                                                       = */
    /* = -------------------------------------------------------------------------- = */
    String tno        = f_get_parm( request.getParameter("param")) ;     // 취소 요청 거래번호
    /* ============================================================================== */
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <title>*** KCP [AX-HUB Version] ***</title>
	<meta http-equiv="Content-Type" content="text/html; charset=euc-kr" />
    <link href="sample/css/style.css" rel="stylesheet" type="text/css"/>

        <script type="text/javascript">
        /* 취소 버튼을 눌렀을 때 호출 */
    function  jsf__go_cancel()
    {
    	   
    	 document.cancel_info.submit();
    }
        
    </script>

<body onload="jsf__go_cancel()">

    <div id="sample_wrap">
<%
    /* ============================================================================== */
    /* =    1. 취소 요청 정보 입력 폼(cancel_info)                                  = */
    /* = -------------------------------------------------------------------------- = */
    /* =   취소 요청에 필요한 정보를 설정합니다.                                    = */
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
    /* =   1. 취소 요청 정보 END                                                    = */
    /* ============================================================================== */
%>
</div>
</body>
</html>
