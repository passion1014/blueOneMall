<%@ page language="java" contentType="text/html;charset=utf-8"%>

<%
    /* ============================================================================== */
    /* =   PAGE : ���� ��û �� ��� ó�� PAGE                                       = */
    /* = -------------------------------------------------------------------------- = */
    /* =   ������ ���� �߻��ϴ� ��� �Ʒ��� �ּҷ� �����ϼż� Ȯ���Ͻñ� �ٶ�ϴ�.= */
    /* =   ���� �ּ� : http://kcp.co.kr/technique.requestcode.do			        = */
    /* = -------------------------------------------------------------------------- = */
    /* =   Copyright (c)  2013   KCP Inc.   All Rights Reserverd.                   = */
    /* ============================================================================== */
	
	/* ============================================================================== */
    /* =   ȯ�� ���� ���� Include                                                   = */
    /* = -------------------------------------------------------------------------- = */
    /* =   �� �ʼ�                                                                  = */
    /* =   �׽�Ʈ �� �ǰ��� ������ site_conf_inc.jsp������ �����Ͻñ� �ٶ�ϴ�.     = */
    /* = -------------------------------------------------------------------------- = */
%>
	<%@ page import="java.net.URLEncoder"%>
	<%@ page import="com.kcp.C_PP_CLI"%>
    <%@ include file="site_conf_inc.jsp"%>
<%
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
<%
	request.setCharacterEncoding ( "utf-8" ) ;

    /* ============================================================================== */
    /* =   02. ���� ��û ���� ����                                                  = */
    /* = -------------------------------------------------------------------------- = */
    String req_tx         = f_get_parm( request.getParameter( "req_tx"         ) ); // ��û ����
    String tran_cd        = f_get_parm( request.getParameter( "tran_cd"        ) ); // ó�� ����
    /* = -------------------------------------------------------------------------- = */
    String cust_ip        = f_get_parm( request.getRemoteAddr()                  ); // ��û IP
    String ordr_idxx      = f_get_parm( request.getParameter( "ordr_idxx"      ) ); // ���θ� �ֹ���ȣ
    String good_name      = f_get_parm( request.getParameter( "good_name"      ) ); // ��ǰ��
    String good_mny       = f_get_parm( request.getParameter( "good_mny"       ) ); // ���� �ѱݾ�
    /* = -------------------------------------------------------------------------- = */
    String res_cd         = "";                                                     // �����ڵ�
    String res_msg        = "";                                                     // ���� �޼���
    String tno            = f_get_parm( request.getParameter( "tno"            ) ); // KCP �ŷ� ���� ��ȣ
    /* = -------------------------------------------------------------------------- = */
    String buyr_name      = f_get_parm( request.getParameter( "buyr_name"      ) ); // �ֹ��ڸ�
    String buyr_tel1      = f_get_parm( request.getParameter( "buyr_tel1"      ) ); // �ֹ��� ��ȭ��ȣ
    String buyr_tel2      = f_get_parm( request.getParameter( "buyr_tel2"      ) ); // �ֹ��� �ڵ��� ��ȣ
    String buyr_mail      = f_get_parm( request.getParameter( "buyr_mail"      ) ); // �ֹ��� E-mail �ּ�
    /* = -------------------------------------------------------------------------- = */
	String mod_type	      = f_get_parm( request.getParameter( "mod_type"	   ) ); // ����TYPE(������ҽ� �ʿ�)
    String mod_desc       = f_get_parm( request.getParameter( "mod_desc"	   ) ); // �������
    String panc_mod_mny   = "";														// �κ���� �ݾ�
    String panc_rem_mny   = "";														// �κ���� ���� �ݾ�
    String mod_tax_mny    = f_get_parm( request.getParameter( "mod_tax_mny"    ) ); // ��ް� �κ� ��� ��û �ݾ�
    String mod_vat_mny    = f_get_parm( request.getParameter( "mod_vat_mny"    ) ); // �ΰ� �κ� ��� ��û �ݾ�
    String mod_free_mny   = f_get_parm( request.getParameter( "mod_free_mny"   ) ); // ��� �κ� ��� ��û �ݾ�
	/* = -------------------------------------------------------------------------- = */
    String use_pay_method = f_get_parm( request.getParameter( "use_pay_method" ) ); // ���� ���
    String bSucc          = "";                                                     // ��ü DB ó�� ���� ����
    /* = -------------------------------------------------------------------------- = */
    String app_time       = "";                                                     // ���νð� (��� ���� ���� ����)
	String amount		  = "";														// KCP ���� �ŷ��ݾ�         
	String total_amount   = "0";													// ���հ����� �� �ŷ��ݾ�
	String coupon_mny	  = "";														// ����ݾ�
	/* = -------------------------------------------------------------------------- = */
	String card_cd        = "";                                                     // �ſ�ī�� �ڵ�
    String card_name      = "";                                                     // �ſ�ī�� ��
    String app_no         = "";                                                     // �ſ�ī�� ���ι�ȣ
    String noinf          = "";                                                     // �ſ�ī�� ������ ����
    String quota          = "";                                                     // �ſ�ī�� �Һΰ���
	String partcanc_yn    = "";														// �κ���� ��������   
	String card_bin_type_01 = "";													// ī�屸��1
	String card_bin_type_02 = "";													// ī�屸��2
	String card_mny		  = "";														// ī������ݾ�
    /* = -------------------------------------------------------------------------- = */
	String bank_name      = "";                                                     // �����
    String bank_code      = "";                                                     // �����ڵ�
	String bk_mny		  = "";														// ������ü�����ݾ�
    /* = -------------------------------------------------------------------------- = */
    String bankname       = "";                                                     // �Ա� �����
    String depositor      = "";                                                     // �Ա� ���� ������ ����
    String account        = "";                                                     // �Ա� ���� ��ȣ
	String va_date		  = "";														// ������� �Աݸ����ð�
    /* = -------------------------------------------------------------------------- = */
    String pnt_issue      = "";                                                     // ���� ����Ʈ�� �ڵ�
	String pnt_amount     = "";                                                     // ��ݾ� or ���ݾ�
	String pnt_app_time   = "";                                                     // ���νð�
	String pnt_app_no     = "";                                                     // ���ι�ȣ
    String add_pnt        = "";                                                     // �߻� ����Ʈ
	String use_pnt        = "";                                                     // ��밡�� ����Ʈ
	String rsv_pnt        = "";                                                     // �� ���� ����Ʈ
	/* = -------------------------------------------------------------------------- = */
	String commid         = "";														// ��Ż��ڵ�
	String mobile_no      = "";														// �޴����ȣ
	/* = -------------------------------------------------------------------------- = */
	String shop_user_id	  = f_get_parm( request.getParameter( "shop_user_id"     ) ); // ������ �? ���̵�
	String tk_van_code	  = "";														// �߱޻��ڵ�
	String tk_app_no	  = "";														// ���ι�ȣ
	/* = -------------------------------------------------------------------------- = */
    String cash_yn        = f_get_parm( request.getParameter( "cash_yn"        ) ); // ���� ������ ��� ����
    String cash_authno    = "";                                                     // ���� ������ ���� ��ȣ
    String cash_tr_code   = f_get_parm( request.getParameter( "cash_tr_code"   ) ); // ���� ������ ���� ����
    String cash_id_info   = f_get_parm( request.getParameter( "cash_id_info"   ) ); // ���� ������ ��� ��ȣ
    /* ============================================================================== */
	/* =   02. ���� ��û ���� ���� END
	/* ============================================================================== */
	
  	/* ============================================================================== */
    /* =   02-1.�޴»�� ����                                                = */
    /* = -------------------------------------------------------------------------- = */
	String reci_name     = f_get_parm( request.getParameter( "reciNm"      ) ); //�޴»�� �̸�
 	String reci_ph     = f_get_parm( request.getParameter("tel1"))+","+f_get_parm( request.getParameter("tel2"))+","+f_get_parm( request.getParameter("tel3"));//�޴»�� ��ȭ��ȣ
 	String reci_mb     = f_get_parm( request.getParameter("hp1"))+","+f_get_parm( request.getParameter("hp2"))+","+f_get_parm( request.getParameter("hp3")); //�޴»�� �ڵ����ȣ
 	String reci_add     = f_get_parm( request.getParameter( "reciAdd") ); //�޴»�� �ּ�
 	String reci_req     = f_get_parm( request.getParameter( "reciReq"      ) ); //�޴»�� ��û���� 
 	String ord_unit_chk     = f_get_parm( request.getParameter( "ord_unit_chk"      ) ); //
 	
 	
 	
 	
    /* ============================================================================== */
    /* =   03. �ν��Ͻ� �� �� �ʱ�ȭ(���� �Ұ�)                                   = */
    /* = -------------------------------------------------------------------------- = */
    /* =       ������ �ʿ��� �ν��Ͻ��� ���ϰ� �ʱ�ȭ �մϴ�.                     = */
    /* = -------------------------------------------------------------------------- = */
    C_PP_CLI c_PayPlus = new C_PP_CLI();

    c_PayPlus.mf_init( g_conf_home_dir, g_conf_gw_url, g_conf_gw_port, g_conf_tx_mode );
    c_PayPlus.mf_init_set();

	/*
	���������� ���н� ������ ������ �ٸ��� ������ �ݵ�� OS�� ������ �����Ͽ� �ٿ�ε��Ͻñ� �ٶ�ϴ�. 
	Windows �� ��� : pp_cli_exe.exe ���� �̿� 
	UNIX �迭�� ��� : pp_cli ���� �̿� 

	pp_ax_hub ���������� mf_init �ϴ� ���ڰ� ���� ���� 
	// Unix / Linux Platform 
	c_PayPlus.mf_init( g_conf_home_dir, g_conf_gw_url, g_conf_gw_port, g_conf_tx_mode ); 
	// Windows Platform 
	c_PayPlus.mf_init( g_conf_home_dir, g_conf_gw_url, g_conf_gw_port, g_conf_key_dir, g_conf_log_dir, g_conf_tx_mode );
	*/

    /* ============================================================================== */
	/* =   03. �ν��Ͻ� �� �� �ʱ�ȭ END                                          = */
    /* ============================================================================== */


    /* ============================================================================== */
    /* =   04. ó�� ��û ���� ����			                                        = */
    /* = -------------------------------------------------------------------------- = */
    /* = -------------------------------------------------------------------------- = */
    /* =   04-1. ���� ��û ���� ����                                                = */
    /* = -------------------------------------------------------------------------- = */
    if ( req_tx.equals( "pay" ) )
    {
            c_PayPlus.mf_set_enc_data( f_get_parm( request.getParameter( "enc_data" ) ),
                                       f_get_parm( request.getParameter( "enc_info" ) ) );
			
			/* 1004���� ������ ��ü���� �����ϼž� �� �� �ݾ��� �־��ּž� �մϴ�. �����ݾ� ��ȿ�� ����
			if(good_mny.trim().length() > 0)
			{
			    int ordr_data_set_no;

			    ordr_data_set_no = c_PayPlus.mf_add_set( "ordr_data" );
			    
                c_PayPlus.mf_set_us( ordr_data_set_no, "ordr_mony", "1004" );
            }
			*/
    }

    /* = -------------------------------------------------------------------------- = */
    /* =   04-2. ���/���� ��û                                                     = */
    /* = -------------------------------------------------------------------------- = */
    else if ( req_tx.equals( "mod" ) )
        {
            int    mod_data_set_no;

            tran_cd = "00200000";
            mod_data_set_no = c_PayPlus.mf_add_set( "mod_data" );

            c_PayPlus.mf_set_us( mod_data_set_no, "tno",        request.getParameter( "tno"      ) ); // KCP ��ŷ� �ŷ���ȣ
            c_PayPlus.mf_set_us( mod_data_set_no, "mod_type",   mod_type                           ); // ��ŷ� ���� ��û ����
            c_PayPlus.mf_set_us( mod_data_set_no, "mod_ip",     cust_ip                            ); // ���� ��û�� IP
            c_PayPlus.mf_set_us( mod_data_set_no, "mod_desc",   request.getParameter( "mod_desc" ) ); // ���� ����

            if ( mod_type.equals( "STPC" ) ) // �κ������ ���
            {
                c_PayPlus.mf_set_us( mod_data_set_no, "mod_mny", request.getParameter( "mod_mny" ) ); // ��ҿ�û�ݾ�
                c_PayPlus.mf_set_us( mod_data_set_no, "rem_mny", request.getParameter( "rem_mny" ) ); // ��Ұ����ܾ�

                //���հŷ� �κ� ��ҽ� �ּ��� Ǯ�� �ֽñ� �ٶ�ϴ�.
	            //c_PayPlus.mf_set_us( mod_data_set_no, "tax_flag",     "TG03"                       ); // ���հ� ����
                //c_PayPlus.mf_set_us( mod_data_set_no, "mod_tax_mny",  mod_tax_mny                  ); // ��ް� �κ� ��� ��û �ݾ�
                //c_PayPlus.mf_set_us( mod_data_set_no, "mod_vat_mny",  mod_vat_mny                  ); // �ΰ� �κ� ��� ��û �ݾ�
                //c_PayPlus.mf_set_us( mod_data_set_no, "mod_free_mny", mod_free_mny                 ); // ��� �κ� ��� ��û �ݾ�
            }
        }
	/* = -------------------------------------------------------------------------- = */
    /* =   04. ó�� ��û ���� ���� END                                              = */
    /* = ========================================================================== = */


    /* = ========================================================================== = */
    /* =   05. ����                                                                 = */
    /* = -------------------------------------------------------------------------- = */
    if ( tran_cd.length() > 0 )
    {
        c_PayPlus.mf_do_tx( g_conf_site_cd, g_conf_site_key, tran_cd, "", ordr_idxx, g_conf_log_level, "0" );
		
		
	    res_cd  = c_PayPlus.m_res_cd;  // ��� �ڵ�
		res_msg = c_PayPlus.m_res_msg; // ��� �޽���
	}
    else
    {
        c_PayPlus.m_res_cd  = "9562";
        c_PayPlus.m_res_msg = "���� ����|Payplus Plugin�� ��ġ���� �ʾҰų� tran_cd���� �������� �ʾҽ��ϴ�.";
    }

	/* = -------------------------------------------------------------------------- = */
    /* =   05. ���� END                                                             = */
    /* ============================================================================== */


    /* ============================================================================== */
    /* =   06. ���� ��� �� ����                                                    = */
    /* = -------------------------------------------------------------------------- = */
    if ( req_tx.equals( "pay" ) )
    {
        if ( res_cd.equals( "0000" ) )
        {
            tno		  = c_PayPlus.mf_get_res( "tno"		  ); // KCP �ŷ� ���� ��ȣ
            amount	  = c_PayPlus.mf_get_res( "amount"    ); // KCP ���� �ŷ� �ݾ�
			pnt_issue = c_PayPlus.mf_get_res( "pnt_issue" ); // ���� ����Ʈ�� �ڵ�
            coupon_mny = c_PayPlus.mf_get_res( "coupon_mny"	); // ����ݾ�

    /* = -------------------------------------------------------------------------- = */
    /* =   06-1. �ſ�ī�� ���� ��� ó��                                            = */
    /* = -------------------------------------------------------------------------- = */
            if ( use_pay_method.equals( "100000000000" ) )
            {
                card_cd   = c_PayPlus.mf_get_res( "card_cd"   ); // ī��� �ڵ�
                card_name = c_PayPlus.mf_get_res( "card_name" ); // ī��� ��
                app_time  = c_PayPlus.mf_get_res( "app_time"  ); // ���νð�
                app_no    = c_PayPlus.mf_get_res( "app_no"    ); // ���ι�ȣ
                noinf     = c_PayPlus.mf_get_res( "noinf"     ); // ������ ����
                quota     = c_PayPlus.mf_get_res( "quota"     ); // �Һ� ���� ��
				partcanc_yn = c_PayPlus.mf_get_res( "partcanc_yn"     ); // �κ���� ��������
				card_bin_type_01 = c_PayPlus.mf_get_res( "card_bin_type_01" ); // ī�屸��1
				card_bin_type_02 = c_PayPlus.mf_get_res( "card_bin_type_02" ); // ī�屸��2
				card_mny = c_PayPlus.mf_get_res( "card_mny" ); // ī������ݾ�

				/* = -------------------------------------------------------------- = */
                /* =   06-1.1. ���հ���(����Ʈ+�ſ�ī��) ���� ��� ó��             = */
                /* = -------------------------------------------------------------- = */
                if ( pnt_issue.equals( "SCSK" ) || pnt_issue.equals( "SCWB" ) )
                {
                    pnt_amount   = c_PayPlus.mf_get_res( "pnt_amount"   ); // ��ݾ� or ���ݾ�
	                pnt_app_time = c_PayPlus.mf_get_res( "pnt_app_time" ); // ���νð�
	                pnt_app_no   = c_PayPlus.mf_get_res( "pnt_app_no"   ); // ���ι�ȣ
	                add_pnt      = c_PayPlus.mf_get_res( "add_pnt"      ); // �߻� ����Ʈ
                    use_pnt      = c_PayPlus.mf_get_res( "use_pnt"      ); // ��밡�� ����Ʈ
                    rsv_pnt      = c_PayPlus.mf_get_res( "rsv_pnt"      ); // �� ���� ����Ʈ
					total_amount = amount + pnt_amount;					   // ���հ����� �� �ŷ��ݾ�
                }
            }

    /* = -------------------------------------------------------------------------- = */
    /* =   06-2. ������ü ���� ��� ó��                                            = */
    /* = -------------------------------------------------------------------------- = */
            if ( use_pay_method.equals("010000000000") )
            {
				app_time  = c_PayPlus.mf_get_res( "app_time"  ); // ���νð�
                bank_name = c_PayPlus.mf_get_res( "bank_name" ); // �����
                bank_code = c_PayPlus.mf_get_res( "bank_code" ); // �����ڵ�
				bk_mny    = c_PayPlus.mf_get_res( "bk_mny"    ); // ������ü�����ݾ�
            }

    /* = -------------------------------------------------------------------------- = */
    /* =   06-3. ������� ���� ��� ó��                                            = */
    /* = -------------------------------------------------------------------------- = */
            if ( use_pay_method.equals( "001000000000" ) )
            {
                bankname  = c_PayPlus.mf_get_res( "bankname"  ); // �Ա��� ���� �̸�
                depositor = c_PayPlus.mf_get_res( "depositor" ); // �Ա��� ���� ������
                account   = c_PayPlus.mf_get_res( "account"   ); // �Ա��� ���� ��ȣ
                va_date   = c_PayPlus.mf_get_res( "va_date"   ); // ������� �Աݸ����ð�
            }

	/* = -------------------------------------------------------------------------- = */
    /* =   06-4. ����Ʈ ���� ��� ó��                                              = */
    /* = -------------------------------------------------------------------------- = */
            if ( use_pay_method.equals( "000100000000" ) )
            {
                pnt_amount   = c_PayPlus.mf_get_res( "pnt_amount"   ); // ��ݾ� or ���ݾ�
	            pnt_app_time = c_PayPlus.mf_get_res( "pnt_app_time" ); // ���νð�
	            pnt_app_no   = c_PayPlus.mf_get_res( "pnt_app_no"   ); // ���ι�ȣ
	            add_pnt      = c_PayPlus.mf_get_res( "add_pnt"      ); // �߻� ����Ʈ
	            use_pnt      = c_PayPlus.mf_get_res( "use_pnt"      ); // ��밡�� ����Ʈ
                rsv_pnt      = c_PayPlus.mf_get_res( "rsv_pnt"      ); // �� ���� ����Ʈ
            }

    /* = -------------------------------------------------------------------------- = */
    /* =   06-5. �޴��� ���� ��� ó��                                              = */
    /* = -------------------------------------------------------------------------- = */
            if ( use_pay_method.equals( "000010000000" ) )
            {
                app_time = c_PayPlus.mf_get_res( "hp_app_time" ); // ���� �ð�
				commid	 = c_PayPlus.mf_get_res( "commid"	   ); // ��Ż� �ڵ�
				mobile_no= c_PayPlus.mf_get_res( "mobile_no"   ); // �޴��� ��ȣ
            }

    /* = -------------------------------------------------------------------------- = */
    /* =   06-6. ��ǰ�� ���� ��� ó��                                              = */
    /* = -------------------------------------------------------------------------- = */
            if ( use_pay_method.equals( "000000001000" ) )
            {
                app_time    = c_PayPlus.mf_get_res( "tk_app_time" ); // ���� �ð�
				tk_van_code = c_PayPlus.mf_get_res( "tk_van_code" ); // �߱޻� �ڵ�
				tk_app_no   = c_PayPlus.mf_get_res( "tk_app_no"   ); // ���� ��ȣ
            }

	/* = -------------------------------------------------------------------------- = */
    /* =   06-7. ���ݿ����� ���� ��� ó��                                          = */
    /* = -------------------------------------------------------------------------- = */
			cash_authno = c_PayPlus.mf_get_res( "cash_authno" ); // ���ݿ����� ���ι�ȣ
		}
	}
	/* = -------------------------------------------------------------------------- = */
    /* =   06-8. �κ���� ��� ó��                                          = */
    /* = -------------------------------------------------------------------------- = */
    if ( req_tx.equals( "mod" ) )
    {
        if ( res_cd.equals( "0000" ) )
		{
			if ( mod_type.equals ( "STPC") )
			{
				amount		 = c_PayPlus.mf_get_res( "amount"       ); // �� �ݾ�
				panc_mod_mny = c_PayPlus.mf_get_res( "panc_mod_mny" ); // �κ���� ��û�ݾ�
				panc_rem_mny = c_PayPlus.mf_get_res( "panc_rem_mny" ); // �κ���� ���ɱݾ�
			}
		}
	}
	/* = -------------------------------------------------------------------------- = */
    /* =   06. ���� ��� ó�� END                                                   = */
    /* ============================================================================== */

    
    /* = ========================================================================== = */
    /* =   07. ���� �� ���� ��� DB ó��                                            = */
    /* = -------------------------------------------------------------------------- = */
    /* =      ��� ��ü ��ü������ DB ó�� �۾��Ͻô� �κ��Դϴ�.                 = */
    /* = -------------------------------------------------------------------------- = */

	if ( req_tx.equals( "pay" ) )
    {

    /* = -------------------------------------------------------------------------- = */
    /* =   07-1. ���� ��� DB ó��(res_cd == "0000")                                = */
    /* = -------------------------------------------------------------------------- = */
    /* =        �� ���������� �����Ͻþ� DB ó���� �Ͻñ� �ٶ�ϴ�.                 = */
    /* = -------------------------------------------------------------------------- = */
        if ( res_cd.equals( "0000" ) )
        {
            // 07-1-1. �ſ�ī��
            if ( use_pay_method.equals( "100000000000" ) )
            {
                // 07-1-1-1. ���հ���(�ſ�ī��+����Ʈ)
                if ( pnt_issue.equals( "SCSK" ) || pnt_issue.equals( "SCWB" ) )
                {

                }
			}

            // 07-1-2. ������ü
            if ( use_pay_method.equals("010000000000") )
            {

			}
            // 07-1-3. �������
            if ( use_pay_method.equals("001000000000") )
			{
			
			}
            // 07-1-4. ����Ʈ
            if ( use_pay_method.equals("000100000000") )
			{

			}
            // 07-1-5. �޴���
            if ( use_pay_method.equals("000010000000") )
			{

			}
            // 07-1-6. ��ǰ��
            if ( use_pay_method.equals("000000001000") )
			{

			}
		}

        /* = -------------------------------------------------------------------------- = */
        /* =   07-2. ���� ���� DB ó��(res_cd != "0000")                                = */
        /* = -------------------------------------------------------------------------- = */
		if( !"0000".equals ( res_cd ) )
		{
		}
	}	
    /* = -------------------------------------------------------------------------- = */
    /* =   07. ���� �� ���� ��� DB ó�� END                                        = */
    /* = ========================================================================== = */


	/* = ========================================================================== = */
    /* =   08. ���� ��� DB ó�� ���н� : �ڵ����                                  = */
    /* = -------------------------------------------------------------------------- = */
    /* =      ���� ��� DB �۾� �ϴ� �������� ���������� ���ε� �ǿ� ����         = */
    /* =      DB �۾��� �����Ͽ� DB update �� �Ϸ���� ���� ���, �ڵ�����          = */
    /* =      ���� ��� ��û�� �ϴ� ���μ����� �����Ǿ� �ֽ��ϴ�.                   = */
    /* =                                                                            = */
    /* =      DB �۾��� ���� �� ���, bSucc ��� ����(String)�� ���� "false"        = */
    /* =      �� ������ �ֽñ� �ٶ�ϴ�. (DB �۾� ������ ��쿡�� "false" �̿���    = */
    /* =      ���� �����Ͻø� �˴ϴ�.)                                              = */
    /* = -------------------------------------------------------------------------- = */

    // ���� ��� DB ó�� ������ bSucc���� false�� �����Ͽ� �ŷ����� ��� ��û
	bSucc = ""; 

	if (req_tx.equals("pay") )
	{
		if (res_cd.equals("0000") )
		{
            if ( bSucc.equals("false") )
            {
                int mod_data_set_no;

                c_PayPlus.mf_init_set();

                tran_cd = "00200000";

                mod_data_set_no = c_PayPlus.mf_add_set( "mod_data" );

                c_PayPlus.mf_set_us( mod_data_set_no, "tno",      tno      ); // KCP ��ŷ� �ŷ���ȣ
                c_PayPlus.mf_set_us( mod_data_set_no, "mod_type", "STSC"   ); // ��ŷ� ���� ��û ����
                c_PayPlus.mf_set_us( mod_data_set_no, "mod_ip",   cust_ip  ); // ���� ��û�� IP
                c_PayPlus.mf_set_us( mod_data_set_no, "mod_desc", "������ ��� ó�� ���� - ���������� ��� ��û"  ); // ���� ����

				c_PayPlus.mf_do_tx( g_conf_site_cd, g_conf_site_key, tran_cd, "", ordr_idxx, g_conf_log_level, "0" );

                res_cd  = c_PayPlus.m_res_cd;								  // ��� �ڵ�
                res_msg = c_PayPlus.m_res_msg;								  // ��� �޽���
            }
        }
	}
	    // End of [res_cd = "0000"]
    /* = -------------------------------------------------------------------------- = */
    /* =   08. ���� ��� DB ó�� END                                                = */
    /* = ========================================================================== = */


	/* ============================================================================== */
    /* =   09. �� ���� �� ��������� ȣ��                                           = */
    /* -----------------------------------------------------------------------------= */
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
    <head>
        <title>*** KCP [AX-HUB Version] ***</title>
        <script type="text/javascript">
            function goResult()
            {
                var openwin = window.open( 'proc_win.html', 'proc_win', '' );
                document.pay_info.submit();
                openwin.close();
            }

            // ���� �� ���ΰ�ħ ���� ���� ��ũ��Ʈ
            function noRefresh()
            {
                /* CTRL + NŰ ����. */
                if ((event.keyCode == 78) && (event.ctrlKey == true))
                {
                    event.keyCode = 0;
                    return false;
                }
                /* F5 ��Ű ����. */
                if(event.keyCode == 116)
                {
                    event.keyCode = 0;
                    return false;
                }
            }
            document.onkeydown = noRefresh ;
        </script>
    </head>

    <body onload="goResult()">
    <!-- <form name="pay_info" method="post" action="/resources/kcp/result.jsp">-->    
    <form name="pay_info" method="post" action="/order/orderComplete.do">
		<input type="hidden" name="site_cd"         value="<%= g_conf_site_cd	%>">    <!-- ����Ʈ �ڵ� -->
		<input type="hidden" name="req_tx"          value="<%= req_tx			%>">    <!-- ��û ���� -->
        <input type="hidden" name="use_pay_method"  value="<%= use_pay_method	%>">    <!-- ����� ���� ���� -->
        <input type="hidden" name="bSucc"           value="<%= bSucc			%>">    <!-- ���θ� DB ó�� ���� ���� -->
        <input type="hidden" name="panc_mod_mny"    value="<%= panc_mod_mny	    %>">    <!-- �κ���� �ݾ� -->
        <input type="hidden" name="panc_rem_mny"    value="<%= panc_rem_mny		%>">    <!-- �κ���� ���ɱݾ� -->
        <input type="hidden" name="mod_type"        value="<%= mod_type			%>">

        <input type="hidden" name="amount"          value="<%= amount		    %>">	<!-- �ݾ� -->
        <input type="hidden" name="res_cd"          value="<%= res_cd			%>">    <!-- ��� �ڵ� -->
        <input type="hidden" name="res_msg"         value="<%= res_msg			%>">    <!-- ��� �޼��� -->
        <input type="hidden" name="orderNo"       value="<%= ordr_idxx		%>">    <!-- �ֹ���ȣ -->
        <input type="hidden" name="tno"             value="<%= tno				%>">    <!-- KCP �ŷ���ȣ -->
        <input type="hidden" name="good_mny"        value="<%= good_mny			%>">    <!-- �����ݾ� -->
        <input type="hidden" name="good_name"       value="<%= good_name		%>">    <!-- ��ǰ�� -->
        <input type="hidden" name="buyr_name"       value="<%= buyr_name		%>">    <!-- �ֹ��ڸ� -->
        <input type="hidden" name="buyr_tel1"       value="<%= buyr_tel1		%>">    <!-- �ֹ��� ��ȭ��ȣ -->
        <input type="hidden" name="buyr_tel2"       value="<%= buyr_tel2		%>">    <!-- �ֹ��� �޴����ȣ -->
        <input type="hidden" name="buyr_mail"       value="<%= buyr_mail		%>">    <!-- �ֹ��� E-mail -->

		<input type="hidden" name="app_time"        value="<%= app_time			%>">	<!-- ���νð� -->
		<!-- �ſ�ī�� ���� -->
        <input type="hidden" name="card_cd"         value="<%= card_cd			%>">    <!-- ī���ڵ� -->
        <input type="hidden" name="card_name"       value="<%= card_name		%>">    <!-- ī���̸� -->
        <input type="hidden" name="app_no"          value="<%= app_no			%>">    <!-- ���ι�ȣ -->
		<input type="hidden" name="noinf"			value="<%= noinf			%>">    <!-- �����ڿ��� -->
        <input type="hidden" name="quota"           value="<%= quota			%>">    <!-- �Һΰ��� -->
        <input type="hidden" name="partcanc_yn"     value="<%= partcanc_yn      %>">    <!-- �κ���Ұ������� -->
        <input type="hidden" name="card_bin_type_01" value="<%= card_bin_type_01 %>">   <!-- ī�屸��1 -->
        <input type="hidden" name="card_bin_type_02" value="<%= card_bin_type_02 %>">   <!-- ī�屸��2 -->
		<!-- ������ü ���� -->
        <input type="hidden" name="bank_name"       value="<%= bank_name		%>">    <!-- ����� -->
        <input type="hidden" name="bank_code"       value="<%= bank_code		%>">    <!-- �����ڵ� -->
		<!-- ������� ���� -->
        <input type="hidden" name="bankname"        value="<%= bankname			%>">    <!-- �Ա� ���� -->
        <input type="hidden" name="depositor"       value="<%= depositor		%>">    <!-- �Աݰ��� ������ -->
        <input type="hidden" name="account"         value="<%= account			%>">    <!-- �Աݰ��� ��ȣ -->
		<input type="hidden" name="va_date"         value="<%= va_date			%>">    <!-- ������� �Աݸ����ð� -->
		<!-- ����Ʈ ���� -->
		<input type="hidden" name="pnt_issue"		value="<%= pnt_issue		%>">	<!-- ����Ʈ ���񽺻� -->
		<input type="hidden" name="pnt_app_time"	value="<%= pnt_app_time		%>">	<!-- ���νð� -->
        <input type="hidden" name="pnt_app_no"      value="<%= pnt_app_no		%>">	<!-- ���ι�ȣ -->
        <input type="hidden" name="pnt_amount"      value="<%= pnt_amount		%>">	<!-- ��ݾ� or ���ݾ� -->
        <input type="hidden" name="add_pnt"         value="<%= add_pnt			%>">	<!-- �߻� ����Ʈ -->
        <input type="hidden" name="use_pnt"         value="<%= use_pnt			%>">	<!-- ��밡�� ����Ʈ -->
        <input type="hidden" name="rsv_pnt"         value="<%= rsv_pnt			%>">	<!-- �� ���� ����Ʈ -->
		<!-- �޴��� ���� -->
        <input type="hidden" name="commid"          value="<%= commid			%>">	<!-- ��Ż� �ڵ� -->
        <input type="hidden" name="mobile_no"       value="<%= mobile_no		%>">	<!-- �޴��� ��ȣ -->
        <!-- ��ǰ�� ���� -->
        <input type="hidden" name="tk_van_code"     value="<%= tk_van_code		%>">	<!-- �߱޻� �ڵ� -->
        <input type="hidden" name="tk_app_no"       value="<%= tk_app_no		%>">	<!-- ���� ��ȣ -->
        <!-- ���ݿ����� ���� -->
        <input type="hidden" name="cash_yn"         value="<%= cash_yn			%>">	<!-- ���ݿ����� ��� ���� -->
        <input type="hidden" name="cash_authno"     value="<%= cash_authno		%>">	<!-- ���� ������ ���� ��ȣ -->
        <input type="hidden" name="cash_tr_code"    value="<%= cash_tr_code		%>">	<!-- ���� ������ ���� ���� -->
        <input type="hidden" name="cash_id_info"    value="<%= cash_id_info		%>">	<!-- ���� ������ ��� ��ȣ -->
        <!-- �޴»�� ���� -->
        <input type="hidden" name="reciInfo.reciNm"         value="<%= reci_name			%>">    <!-- �̸� -->
        <input type="hidden" name="reciInfo.reciPh"         value="<%= reci_ph			%>">    <!-- ��ȭ��ȣ -->
        <input type="hidden" name="reciInfo.reciMb"         value="<%= reci_mb			%>">    <!-- �ڵ����ȣ-->
        <input type="hidden" name="reciInfo.reciAdd"         value="<%= reci_add			%>">    <!-- �ּ� -->
        <input type="hidden" name="reciInfo.reciReq"         value="<%= reci_req			%>">    <!-- ��û���� --> 
        
        <input type="hidden" id="ord_unit_chk" name="ord_unit_chk"  value="<%= ord_unit_chk			%>"/>
    </form>
	</body>
</html>