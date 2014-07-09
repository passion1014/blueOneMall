
package com.blueone.order.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.xml.ws.Response;

import net.welfare.HCDESUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blueone.admin.domain.ConfigInfo;
import com.blueone.admin.service.IAdminManageService;
import com.blueone.common.domain.AttachFileInfo;
import com.blueone.common.service.IAttachFileManageService;
import com.blueone.common.util.CookieBox;
import com.blueone.common.util.CookieUtil;
import com.blueone.common.util.HMallInterworkUtility;
import com.blueone.customer.domain.CustomerContactInfo;
import com.blueone.customer.domain.CustomerInfo;
import com.blueone.customer.domain.RecipientInfo;
import com.blueone.customer.service.ICustomerManageService;
import com.blueone.order.domain.OrderInfo;
import com.blueone.order.domain.OrderProductInfo;
import com.blueone.order.domain.OrderSrchInfo;
import com.blueone.order.domain.PaymentInfo;
import com.blueone.order.service.IOrderManageService;
import com.blueone.product.domain.ProductInfo;
import com.blueone.product.service.IProductManageService;
import com.blueone.user.domain.UserInfo;
import com.jidesoft.converter.BigDecimalConverter;
import com.oreilly.servlet.Base64Encoder;


@Controller
public class OrderController {
	
	@Autowired IOrderManageService orderManageService;
	@Autowired IProductManageService productManageService;
	@Autowired IAttachFileManageService attFileManageService;
	@Autowired ICustomerManageService customerManageService;
	@Autowired IAdminManageService adminManageService;
	
	private static final String MEDIA_CD = "HM";			// 매체구분(포인트 사용하는 사이트 구분값)
	private static final String ENC_TYPE = "euc-kr";		// 현대몰에서는 인코딩을 euc-kr로 보내준다.
	private static final String HCDES_KEY = "hd!d$w4shm";	// 암호화키
	
	@RequestMapping(value = "/order/getOrderList.do", method = RequestMethod.GET)
	public String getOrderInfoListByDuration(@ModelAttribute("orderSrchInfo") @Valid OrderSrchInfo orderSrchInfo, BindingResult result, Model model) {
		String viewName = "";
		
		if (result.hasErrors()) {
			return "redirect:order/getOrderList.do";
		} else {
			List<OrderInfo> orderInfoList = orderManageService.getOrderInfoListByPeriod(orderSrchInfo);
			model.addAttribute("orderList", orderInfoList);
			viewName = "order/orderList";
		}
		
		return viewName;
	}
	
	//?λ컮援щ땲???ㅼ뼱媛?린
	@RequestMapping(value="/order/cartList.do")
	public String order(@ModelAttribute("orderProductInfo") OrderProductInfo orderProductInfo,String buyType,HttpSession session,BindingResult result, Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{
		CustomerInfo cus= (CustomerInfo)session.getAttribute("customerSession");	
		// ?몄뀡泥댄겕
		if (cus == null) {
			return "user/errorPage";
		}	
		
		model.addAttribute("CUST_NAME", cus.getCustNm());
		String customerPoint = (String)session.getAttribute("customerPoint");
		model.addAttribute("CUST_POINT", customerPoint);
		/*CustomerInfo cus =new CustomerInfo();
		cus.setCustId("100001639343");
		cus=customerService.getCustomerInfo2(cus);
		*/
		String birth = cus.getCustBirth();
//		cus = useStringToken(birth,"b",cus);
		
		String phone = cus.getCustPh();
		cus = useStringToken(phone,"p",cus);
		
		String mobile = cus.getCustMb();
		cus = useStringToken(mobile,"m",cus);
				
		CookieBox cki = new CookieBox(request);
		
		//?곹뭹???좏깮?섏꽌 ?λ컮援щ땲 ?섏씠吏?줈 ?ㅼ뼱?붿쓣 寃쎌슦 ?대떦
		String value="";
		
		
		List<String> ckKey = cki.getKey();
	
		
		int pNum=0;
		int count= orderProductInfo.getBuyCnt();
		int prdCount=-1;
		String countExist="n";
		
		for(String each : ckKey){
			
			if("BOM".equals(each.substring(0, 3)) && orderProductInfo.getPrdCd().equals(each.substring(3, each.indexOf("_")))){
				++prdCount;
				String vl = cki.getValue(each);
				StringTokenizer st = new StringTokenizer(vl, ",");
				String optionColor="";
				String optionSize= "";
				
				while (st.hasMoreElements()) {

					String s = st.nextToken();
					if ("01".equals(s.substring(0, 2))) {
						optionColor=s.substring(3);
					}
					if ("02".equals(s.substring(0, 2))) {
						optionSize= s.substring(3);
					}
					if ("cn".equals(s.substring(0, 2))) {
						countExist="y";
						count=Integer.parseInt(s.substring(3));
					}
					
				}
				
				pNum = Integer.parseInt(each.substring(each.indexOf("_")+1));
				if((orderProductInfo.getPrdOpColor()==null || orderProductInfo.getPrdOpColor().isEmpty()) && optionSize.equals(orderProductInfo.getPrdOpSize()) && countExist.equals("y")){
					count += orderProductInfo.getBuyCnt();
					break;
				}else if((orderProductInfo.getPrdOpSize()==null || orderProductInfo.getPrdOpSize().isEmpty()) && optionColor.equals(orderProductInfo.getPrdOpColor()) && countExist.equals("y")){
					count += orderProductInfo.getBuyCnt();
					break;
				}else if((orderProductInfo.getPrdOpColor()==null || orderProductInfo.getPrdOpColor().isEmpty()) && (orderProductInfo.getPrdOpSize()==null || orderProductInfo.getPrdOpSize().isEmpty()) && countExist.equals("y") ){ 
					
					count += orderProductInfo.getBuyCnt();
					break;
				}else if(optionSize.equals(orderProductInfo.getPrdOpSize()) && optionColor.equals(orderProductInfo.getPrdOpColor()) && countExist.equals("y")){
						count += orderProductInfo.getBuyCnt();
						break;
				}else{
	
					pNum = prdCount+1 ;
					count=orderProductInfo.getBuyCnt();
					
				}
			}
				
				
		}
			
		if(orderProductInfo.getPrdOpColor()!=null){
		
			value+="01="+orderProductInfo.getPrdOpColor()+",";
		}
		if(orderProductInfo.getPrdOpSize()!=null){
			
			value+="02="+orderProductInfo.getPrdOpSize()+",";
		}
		if(orderProductInfo.getPrdSmallImg()!=null){
			value+="cn="+count+",";
			Cookie cookie =cki.createCookie("BOM"+orderProductInfo.getPrdCd()+"_"+pNum,value);
			response.addCookie(cookie);//
	
		
		}
		
		
		

		if(buyType.equals("cart")){
			return "redirect:cartListView.do";
		}else{
			return "redirect:orderRegister.do?ord_unit_chk="+"BOM"+orderProductInfo.getPrdCd()+"_"+pNum;
		}
		
	}

	// ?λ컮援щ땲-?섎웾 ?섏젙
	@RequestMapping(value="/order/editBuyCnt.do")
	public String editBuyCnt(@ModelAttribute("orderProductInfo") OrderProductInfo orderProductInfo,BindingResult result,HttpSession session, Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{
		CustomerInfo cus= (CustomerInfo)session.getAttribute("customerSession");	
		// ?몄뀡泥댄겕
		if (cus == null) {
			return "user/errorPage";
		}	
		
		
		model.addAttribute("CUST_NAME", cus.getCustNm());
		String customerPoint = (String)session.getAttribute("customerPoint");
		model.addAttribute("CUST_POINT", customerPoint);
		/*CustomerInfo cus =new CustomerInfo();
		cus.setCustId("100001639343");
		cus=customerService.getCustomerInfo2(cus);
		*/
		String birth = cus.getCustBirth();
//		cus = useStringToken(birth,"b",cus);
		
		String phone = cus.getCustPh();
		cus = useStringToken(phone,"p",cus);
		
		String mobile = cus.getCustMb();
		cus = useStringToken(mobile,"m",cus);
				
		CookieBox cki = new CookieBox(request);
		String cookieVal = cki.getValue(orderProductInfo.getCookieKey());
		
		String changeValue = CookieUtil.changeProdOption(cookieVal, "cn", String.valueOf(orderProductInfo.getBuyCnt()));
		
		Cookie cookie =cki.createCookie(orderProductInfo.getCookieKey(),changeValue);
		response.addCookie(cookie);//
		
		return "redirect:cartListView.do";
	}
	
	//?곹뭹援щℓ - ?섎웾?섏젙
	@RequestMapping(value="/order/editOrderBuycn.do")
	public String editOrderBuycn(@ModelAttribute("orderInfo") OrderInfo orderInfo,BindingResult result,HttpSession session, Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{
	
		
		
		
		CustomerInfo cus= (CustomerInfo)session.getAttribute("customerSession");	
		// ?몄뀡泥댄겕
		if (cus == null) {
			return "user/errorPage";
		}	
		
		model.addAttribute("CUST_NAME", cus.getCustNm());
		String customerPoint = (String)session.getAttribute("customerPoint");
		model.addAttribute("CUST_POINT", customerPoint);
		/*CustomerInfo cus =new CustomerInfo();
		cus.setCustId("100001639343");
		cus=customerService.getCustomerInfo2(cus);
		*/
		String birth = cus.getCustBirth();
//		cus = useStringToken(birth,"b",cus);
		
		String phone = cus.getCustPh();
		cus = useStringToken(phone,"p",cus);
		
		String mobile = cus.getCustMb();
		cus = useStringToken(mobile,"m",cus);
		model.addAttribute("cus",cus);
		
		
		int IDX =orderInfo.getIdx();
		
		StringTokenizer st = new StringTokenizer(orderInfo.getOrd_unit_chk(),",");

		
		List<OrderProductInfo> oPrdList = new ArrayList<OrderProductInfo>();
		
		CookieBox cki = new CookieBox(request);
		
		
		while(st.hasMoreTokens()){ // 諛섑솢???좏겙???덈뒗媛? true/false;
			OrderProductInfo odPrdInfo = new OrderProductInfo();
			
			String key = st.nextToken();
			odPrdInfo.setPrdCd(key);
			ProductInfo prdInfo = new ProductInfo();
			prdInfo.setPrdCd(key);
			prdInfo = productManageService.getProductInfDetail(prdInfo);

			odPrdInfo.setPrdNm(prdInfo.getPrdNm());
			odPrdInfo.setSellPrice(new BigDecimal(prdInfo.getPrdSellPrc()));

			AttachFileInfo att = new AttachFileInfo();
			att.setAttCdKey(prdInfo.getPrdCd());
			att.setAttImgType("01");
			att = attFileManageService.getAttFileInfListImg(att);
			if (att == null) {
				odPrdInfo.setPrdSmallImg("");
			} else {

				odPrdInfo.setPrdSmallImg(att.getAttFilePath());
			}

			String vl = cki.getValue("BOM_"+key);
			StringTokenizer st1 = new StringTokenizer(vl, ",");
			String option = "";
			
			while (st1.hasMoreElements()) {

				String s = st1.nextToken();

				if ("01".equals(s.substring(0, 2))) {
					option += s + ",";
					odPrdInfo.setPrdOpColor(URLDecoder.decode(s.substring(3), "UTF-8"));
				}
				if ("02".equals(s.substring(0, 2))) {
					option += s + ",";
					
					odPrdInfo.setPrdOpSize(URLDecoder.decode(s.substring(3), "UTF-8"));
				}
				if("no".equals(s.substring(0, 2))){
					odPrdInfo.setOrderNo(s.substring(3));
				}
				if ("cn".equals(s.substring(0, 2))) {
					odPrdInfo.setBuyCnt(Integer.parseInt(s.substring(3)));
					BigDecimal total = new BigDecimal(
							prdInfo.getPrdSellPrc());
					total = total.multiply(new BigDecimal(odPrdInfo
							.getBuyCnt()));
					odPrdInfo.setTotalPrice(total);
				}

				odPrdInfo.setPrdOption(option);
			}
			
			oPrdList.add(odPrdInfo);
			  
		}
		
		
		OrderProductInfo res = oPrdList.get(IDX);
		oPrdList.remove(IDX);
		List<OrderProductInfo> buy = orderInfo.getOrderProductList();
		
		String cookieVal = cki.getValue("BOM_" + res.getPrdCd());
		StringTokenizer st1 = new StringTokenizer(cookieVal, ",");
		String option = "";
		String value = "";
		while (st1.hasMoreElements()) {

			String s = st1.nextToken();

			if ("cn".equals(s.substring(0, 2))) {
				res.setBuyCnt(buy.get(IDX).getBuyCnt());
				BigDecimal total = res.getSellPrice();
				total = total.multiply(new BigDecimal(res.getBuyCnt()));
				res.setTotalPrice(total);
				value += "," + "cn=" + res.getBuyCnt() + ",";
			} else {
				value += s;
			}

		}

		Cookie cookie = cki.createCookie("BOM_" + res.getPrdCd(), value);
		response.addCookie(cookie);//

		oPrdList.add(IDX, res);
		model.addAttribute("odPrdInfo", oPrdList);
		
	
		return "order/order";
	}
	
	
	
	//?λ컮援щ땲 蹂댁뿬以?
	@RequestMapping(value="/order/cartListView.do")
	public String orderView(@ModelAttribute("orderProductInfo") OrderProductInfo orderProductInfo,HttpSession session,BindingResult result, Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{

		
		CustomerInfo cus= (CustomerInfo)session.getAttribute("customerSession");	
		// ?몄뀡泥댄겕
		if (cus == null) {
			return "user/errorPage";
		}	
		model.addAttribute("CUST_NAME", cus.getCustNm());
		String customerPoint = (String)session.getAttribute("customerPoint");
		model.addAttribute("CUST_POINT", customerPoint);
		
		/*CustomerInfo cus =new CustomerInfo();
		cus.setCustId("100001639343");
		cus=customerService.getCustomerInfo2(cus);
		*/
		String birth = cus.getCustBirth();
//		cus = useStringToken(birth,"b",cus);
		
		String phone = cus.getCustPh();
		cus = useStringToken(phone,"p",cus);
		
		String mobile = cus.getCustMb();
		cus = useStringToken(mobile,"m",cus);
		
		CookieBox cki = new CookieBox(request);
		List<OrderProductInfo> ord = getCartList(cki);
		
		ConfigInfo resConfigInfo = adminManageService.selectConfigInf();
		
		model.addAttribute("config", resConfigInfo);
		model.addAttribute("odPrdInfo",ord);
		
		
		return "order/cartList";
	}
	
	//?λ컮援щ땲 ?곹뭹 ??젣
	@RequestMapping(value="/order/deleteCartList.do", method = RequestMethod.GET)
	public String deleteCartList(@ModelAttribute("orderProductInfo") OrderProductInfo orderProductInfo,HttpSession session,BindingResult result, Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{
		CustomerInfo cus= (CustomerInfo)session.getAttribute("customerSession");	
		// ?몄뀡泥댄겕
		if (cus == null) {
			return "user/errorPage";
		}	
		
	
		CookieBox cki = new CookieBox(request);
		
		Cookie cookie =cki.createCookie(orderProductInfo.getCookieKey(),"null",-1);
		response.addCookie(cookie);
		

		return "redirect:cartListView.do";
	}
	
	//二쇰Ц ?곹뭹 ??젣
	@RequestMapping(value="/order/deleteOrderList.do", method = RequestMethod.GET)
	public String deleteOrderList(@ModelAttribute("orderProductInfo") OrderProductInfo orderProductInfo,HttpSession session,@ModelAttribute("orderInfo") OrderInfo orderInfo,BindingResult result, Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{
		CustomerInfo cus= (CustomerInfo)session.getAttribute("customerSession");	
		// ?몄뀡泥댄겕
		if (cus == null) {
			return "user/errorPage";
		}	
		CookieBox cki = new CookieBox(request);
		
		Cookie cookie =cki.createCookie(orderProductInfo.getCookieKey(),"null",-1);
		response.addCookie(cookie);
		StringTokenizer st = new StringTokenizer(orderInfo.getOrd_unit_chk(),",");
		
		String checked ="";
		while(st.hasMoreTokens()){ // 諛섑솢???좏겙???덈뒗媛? true/false;
			String cookieKey = st.nextToken();
			if(orderProductInfo.getCookieKey().equals(cookieKey)){
				
			}else{
				checked+=","+cookieKey+",";
			}
		}

		return "redirect:orderRegister.do?ord_unit_chk="+checked;
	}
		
	//?λ컮援щ땲->寃곗젣?섏씠吏?
	@RequestMapping(value="/order/orderRegister.do")
	public String orderRegister(@ModelAttribute("orderInfo") OrderInfo orderInfo,BindingResult result,HttpSession session, Model model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		CustomerInfo cus= (CustomerInfo)session.getAttribute("customerSession");	
		// ?몄뀡泥댄겕
		if (cus == null) {
			return "user/errorPage";
		}
		model.addAttribute("CUST_NAME", cus.getCustNm());
		
		
		StringTokenizer st = new StringTokenizer(orderInfo.getOrd_unit_chk(),",");
		
		List<OrderProductInfo> oPrdList = new ArrayList<OrderProductInfo>();
		
		CookieBox cki = new CookieBox(request);
		
		BigDecimal total= null;
		while(st.hasMoreTokens()){ // 諛섑솢???좏겙???덈뒗媛? true/false;
			OrderProductInfo odPrdInfo = new OrderProductInfo();
			
			String cookieKey = st.nextToken();
			odPrdInfo.setCookieKey(cookieKey);
			String key = cookieKey.substring(3,cookieKey.indexOf("_"));
			odPrdInfo.setPrdCd(key);
			ProductInfo prdInfo = new ProductInfo();
			prdInfo.setPrdCd(key);
			prdInfo = productManageService.getProductInfDetail(prdInfo);

			odPrdInfo.setPrdNm(prdInfo.getPrdNm());
			odPrdInfo.setSellPrice(new BigDecimal(prdInfo.getPrdSellPrc()));

			AttachFileInfo att = new AttachFileInfo();
			att.setAttCdKey(prdInfo.getPrdCd());
			att.setAttImgType("01");
			att = attFileManageService.getAttFileInfListImg(att);
			if (att == null) {
				odPrdInfo.setPrdSmallImg("");
			} else {

				odPrdInfo.setPrdSmallImg(att.getAttFilePath());
			}

			String vl = cki.getValue(cookieKey);
			StringTokenizer st1 = new StringTokenizer(vl, ",");
			String option = "";
			
			while (st1.hasMoreElements()) {

				String s = st1.nextToken();

				if ("01".equals(s.substring(0, 2))) {
					option += s + ",";
					odPrdInfo.setPrdOpColor(URLDecoder.decode(s.substring(3), "UTF-8"));
				}
				if ("02".equals(s.substring(0, 2))) {
					option += s + ",";
					
					odPrdInfo.setPrdOpSize(URLDecoder.decode(s.substring(3), "UTF-8"));
				}
				if("no".equals(s.substring(0, 2))){
					odPrdInfo.setOrderNo(s.substring(3));
				}
				if ("cn".equals(s.substring(0, 2))) {
					odPrdInfo.setBuyCnt(Integer.parseInt(s.substring(3)));
					total = new BigDecimal(
							prdInfo.getPrdSellPrc());
					total = total.multiply(new BigDecimal(odPrdInfo
							.getBuyCnt()));
					odPrdInfo.setTotalPrice(total);
				}

				odPrdInfo.setPrdOption(option);
			}
			
			oPrdList.add(odPrdInfo);
			  
		}
		orderInfo.setOrderNo(getOrderCode());
		model.addAttribute("odPrdInfo",oPrdList);
		
		//?몄뀡???뉖뒗 ?뺣낫瑜??뗮똿
		CustomerInfo custom  = setSession(session);
		model.addAttribute("cus",custom);
					
		model.addAttribute("orderInfo",orderInfo);
		
		//諛곗넚鍮꾧????뺣낫
		ConfigInfo resConfigInfo = adminManageService.selectConfigInf();
		
		model.addAttribute("config", resConfigInfo);
		
		
		//포인트 조회
		Map<String, String> pointmap = HMallInterworkUtility.procSearchPoint(cus.getCustNm(), cus.getCustId(),(String)session.getAttribute("shopEventNo"));
		String point = (String)pointmap.get("return_point");
		model.addAttribute("CUST_POINT", point);// header.jsp 에 포인트를 보여줌
		model.addAttribute("userPoint", point);//하단에 포인트 보여줌
		return "order/order";
	}
	
	//寃곗젣?섏씠吏?泥섎━
	@RequestMapping(value="/order/orderRegisterProc.do")
	public String orderRegisteProc(@ModelAttribute("orderInfo") OrderInfo orderInfo,HttpSession session,BindingResult result, Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{
		/*String rcid		= request.getParameter("reWHCid"		);
		String rctype	= request.getParameter("reWHCtype"		);
		String rhash	= request.getParameter("reWHHash"			);

		String	authyn =  "";
		String	trno   =  "";
		String	trddt  =  "";
		String	trdtm  =  "";
		String	amt    =  "";
		String	authno =  "";
		String	msg1   =  "";
		String	msg2   =  "";
		String	ordno  =  "";
		String	isscd  =  "";
		String	aqucd  =  "";
		String	temp_v =  "";
		String	res =  "";
		String	halbu  =  "";
		String	cbtrno =  "";
		String	cbauthno =  "";
		String	resultcd =  "";

		//?낆껜?먯꽌 異붽??섏떊 ?몄옄媛믪쓣 諛쏅뒗 遺?텇?낅땲??
		String	a =  request.getParameter("a"); 
		String	b =  request.getParameter("b"); 
		String	c =  request.getParameter("c"); 
		String	d =  request.getParameter("d");

		com.blueone.common.util.KSPayWebHostBean ipg = new com.blueone.common.util.KSPayWebHostBean(rcid);
		if (ipg.kspay_send_msg("1"))		//KSNET 寃곗젣寃곌낵 以??꾨옒???섑??섏? ?딆? ??ぉ???꾩슂??寃쎌슦 Null ??떊 ?꾩슂????ぉ紐낆쓣 ?ㅼ젙?????덉뒿?덈떎.
		{
			authyn	 = ipg.kspay_get_value("authyn");
			trno	 = ipg.kspay_get_value("trno"  );
			trddt	 = ipg.kspay_get_value("trddt" );
			trdtm	 = ipg.kspay_get_value("trdtm" );
			amt		 = ipg.kspay_get_value("amt"   );
			authno	 = ipg.kspay_get_value("authno");
			msg1	 = ipg.kspay_get_value("msg1"  );
			msg2	 = ipg.kspay_get_value("msg2"  );
			ordno	 = ipg.kspay_get_value("ordno" );
			isscd	 = ipg.kspay_get_value("isscd" );
			aqucd	 = ipg.kspay_get_value("aqucd" );
			temp_v	 = "";
			res	 = ipg.kspay_get_value("result");
			halbu	 = ipg.kspay_get_value("halbu");
			cbtrno	 = ipg.kspay_get_value("cbtrno");
			cbauthno	 = ipg.kspay_get_value("cbauthno");
			
			if (null != authyn && 1 == authyn.length())
			{
				if (authyn.equals("O"))
				{
					resultcd = "0000";
				}else
				{
					resultcd = authno.trim();
				}

				//ipg.kspay_send_msg("3");		// ?뺤긽泥섎━媛??꾨즺?섏뿀??寃쎌슦 ?몄텧?⑸땲??(??怨쇱젙???놁쑝硫??쇱떆?곸쑝濡?kspay_send_msg("1")???몄텧?섏뿬 嫄곕옒?댁뿭 議고쉶媛?媛?뒫?⑸땲??)
			}
		}*/
		CustomerInfo cus= (CustomerInfo)session.getAttribute("customerSession");	
		// ?몄뀡泥댄겕
		if (cus == null) {
			return "user/errorPage";
		}	
		
		
		
		/*CustomerInfo cus =new CustomerInfo();
		cus.setCustId("100001639343");
		cus=customerService.getCustomerInfo2(cus);
		*/
		String birth = cus.getCustBirth();
//		cus = useStringToken(birth,"b",cus);
		
		String phone = cus.getCustPh();
		cus = useStringToken(phone,"p",cus);
		
		String mobile = cus.getCustMb();
		cus = useStringToken(mobile,"m",cus);
		
		
		//二쇰Ц踰덊샇
		String orderNum=orderInfo.getOrderNo();
		
		List<OrderProductInfo> ord  = orderInfo.getOrderProductList();
		for(OrderProductInfo each : ord){
			
			//OrderProduct??옣
			each.setOrderNo(orderNum);
			each.setModiUser(cus.getCustId());//user ID ?낅젰
			orderManageService.registOrderProductInfo(each);

		
		
		}
		
		//Order ??옣
		OrderInfo orderInfo1 = new OrderInfo();
		orderInfo1.setOrderNo(orderNum);
		orderInfo1.setOrderStatCd("02");
		orderInfo1.setCustomerInfo(cus);
		orderInfo1.setModifyUserId(cus.getCustId());
		orderManageService.registOrderInfo(orderInfo1);
		StringTokenizer st = new StringTokenizer(orderInfo.getOrd_unit_chk(),",");
	
		
		CookieBox cki = new CookieBox(request);
		
		
		while(st.hasMoreTokens()){ // 諛섑솢???좏겙???덈뒗媛? true/false;
			Cookie cookie =cki.createCookie(st.nextToken(),"null",-1);
			response.addCookie(cookie);
		}

		
		RecipientInfo re = new RecipientInfo();
		re=orderInfo.getReciInfo();
		re.setReciOdNum(orderNum);
		cus.setCustAdd(re.getAdd1());
		customerManageService.updateCustomerInf(cus);
		orderManageService.registRecipientInfo(re);
		
		return "redirect:orderComplete.do?orderNo="+orderNum;
}


	//二쇰Ц?깃났?섏씠吏?
	@RequestMapping(value="/order/orderComplete.do")
	public String orderComplete(@ModelAttribute("orderInfo") OrderInfo orderInfo,String use_pay_method,BindingResult result,String card_cd,String good_mny,HttpSession session, Model model,HttpServletRequest request,HttpServletResponse response) throws Exception{
CustomerInfo cus= (CustomerInfo)session.getAttribute("customerSession");
		
		// ?몄뀡泥댄겕
		if (cus == null) {
			return "user/errorPage";
		}	
		model.addAttribute("CUST_NAME", cus.getCustNm());
	
		String customerPoint = (String)session.getAttribute("customerPoint");
		model.addAttribute("CUST_POINT", customerPoint);
		
		String birth = cus.getCustBirth();
//		cus = useStringToken(birth,"b",cus);
		
		String phone = cus.getCustPh();
		cus = useStringToken(phone,"p",cus);
		
		String mobile = cus.getCustMb();
		cus = useStringToken(mobile,"m",cus);
		model.addAttribute("cus",cus);
		
		
		
		String odNo=orderInfo.getOrderNo();
		if(odNo==null || StringUtils.isEmpty(odNo) || odNo.isEmpty()){
			return "redirect:/";
		}
		
		BigDecimal total = null;
		BigDecimal total1 = new BigDecimal(0);
		
		OrderProductInfo opRes = new OrderProductInfo();
		opRes.setOrderNo(orderInfo.getOrderNo());
		
		List<OrderProductInfo> opResInf = new ArrayList<OrderProductInfo>();
		StringTokenizer st1 = new StringTokenizer(orderInfo.getOrd_unit_chk(),",");
		
		//List<OrderProductInfo> opResInf = new ArrayList<OrderProductInfo>();
		
		CookieBox cki = new CookieBox(request);
		
		while(st1.hasMoreTokens()){
			OrderProductInfo odPrdInfo = new OrderProductInfo();
			
			String cookieKey = st1.nextToken();
			odPrdInfo.setCookieKey(cookieKey);
			
			String prdCd =cookieKey.substring(3,cookieKey.indexOf("_"));
			ProductInfo prInf = new ProductInfo();
			odPrdInfo.setPrdCd(prdCd);
			prInf.setPrdCd(prdCd);
			prInf=productManageService.getProductInfDetail(prInf);
			
			//?곹뭹 ?대쫫
			odPrdInfo.setPrdNm(prInf.getPrdNm());
			
			//?듭뀡
			String vl = cki.getValue(cookieKey);
			if(vl==null || StringUtils.isEmpty(vl) ||vl.equals("null")){
				return "redirect:/";
			}
			StringTokenizer st = new StringTokenizer(vl, ",");
			String option = "";
			
			while (st.hasMoreElements()) {

				String s = st.nextToken();

				if ("01".equals(s.substring(0, 2))) {
					option += URLDecoder.decode(s, "UTF-8") + ",";
					
					odPrdInfo.setPrdOpColor(URLDecoder.decode(s.substring(3), "UTF-8"));
				}
				if ("02".equals(s.substring(0, 2))) {
					option += URLDecoder.decode(s, "UTF-8") + ",";
					
					odPrdInfo.setPrdOpSize(URLDecoder.decode(s.substring(3), "UTF-8"));
				}
				if("no".equals(s.substring(0, 2))){
					odPrdInfo.setOrderNo(s.substring(3));
				}
				if ("cn".equals(s.substring(0, 2))) {
					
					odPrdInfo.setBuyCnt(Integer.parseInt(s.substring(3)));
					total = new BigDecimal(prInf.getPrdSellPrc());
					total = total.multiply(new BigDecimal(odPrdInfo.getBuyCnt()));
					odPrdInfo.setTotalPrice(total);
				}
			}
			odPrdInfo.setPrdOption(option);
			
			//?섎웾 諛?湲덉븸
			odPrdInfo.setSellPrice(new BigDecimal(prInf.getPrdSellPrc()));
			total = new BigDecimal(prInf.getPrdSellPrc()) ;
			total=total.multiply(new BigDecimal(odPrdInfo.getBuyCnt()));
			odPrdInfo.setTotalPrice(total);
			
			total1=total1.add(total);
			
			//?ъ쭊
			AttachFileInfo att = new AttachFileInfo();
			att.setAttCdKey(prInf.getPrdCd());
			att.setAttImgType("01");
			att = attFileManageService.getAttFileInfListImg(att);
			if(att==null){
				odPrdInfo.setPrdSmallImg("");
			}else { 
				
				odPrdInfo.setPrdSmallImg(att.getAttFilePath());
			}
			
			opResInf.add(odPrdInfo);
			Cookie cookie =cki.createCookie(cookieKey,"null",-1);
			response.addCookie(cookie);
		
			odPrdInfo.setOrderNo(orderInfo.getOrderNo());
			odPrdInfo.setModiUser(cus.getCustId());//user ID ?낅젰
			orderManageService.registOrderProductInfo(odPrdInfo);
			
			//재고 감소
			ProductInfo productInfo = new ProductInfo();
			productInfo.setPrdCd(prdCd);
			productInfo = productManageService.getProductInfDetail(productInfo);
			productInfo.setPrdStock(productInfo.getPrdStock()-odPrdInfo.getBuyCnt());
			productManageService.manageProductInf(productInfo);
			
			//구매 수 증가
			productInfo.setPrdBuyCount(odPrdInfo.getBuyCnt());
			productManageService.updateProductBuyCount(productInfo);
			
		}

		//諛곗넚鍮꾧????뺣낫
		ConfigInfo resConfigInfo = adminManageService.selectConfigInf();
		
		model.addAttribute("config", resConfigInfo);
		
		if(total1.intValue()<=resConfigInfo.getBuyPrice()){
			total1=total1.add(new BigDecimal(resConfigInfo.getTrasferPrice()));
		}
		
		//pay
		PaymentInfo payment = new PaymentInfo();
		payment.setOrderNo(odNo);
		payment.setOrderNoSeq(1);
		
		payment.setPayMdCd(use_pay_method);
		model.addAttribute("pay",use_pay_method);
		payment.setModifyUserId(cus.getCustId());
		
		int usePoint =0;
		orderInfo.setUserPointInfo("");
		//포인트 결제
		if(!good_mny.equals(total1.toString())){
			String decMemNm = cus.getCustNm();
			String decMemNo = cus.getCustId();
			String decShopEventNo = (String)session.getAttribute("shopEventNo");
			usePoint = total1.intValue()-Integer.parseInt(good_mny);
			String decPoint = Integer.toString(usePoint);
			String decOrderNo = odNo;
			orderInfo.setUserPointInfo(decMemNm+"_"+decMemNo+"_"+decShopEventNo+"_"+decPoint);
			// --------------------------------------------
			// 2. SSO泥섎━瑜??꾪븳 ?뱀꽌鍮꾩뒪 ?몄텧
			// --------------------------------------------
			Map<String, String> rstMap = null;
			try {
				rstMap = HMallInterworkUtility.procUsePoint(decMemNm, decMemNo, decShopEventNo, decPoint, decOrderNo);
			} catch (Exception e) {
				model.addAttribute("msg", "SSO에러");
				payment.setPymtMemo("포인트 결제 에러");
				orderManageService.registPaymentInfo(payment);
				return "user/loginError";
			}
			
			// --------------------------------------------
			// 3. 泥댄겕 - SSO泥섎━ 寃곌낵瑜??뺤씤?쒕떎.
			// --------------------------------------------
			if (rstMap == null) {
				model.addAttribute("msg", "SSO에러");
				payment.setPymtMemo("포인트 결제 에러");
				orderManageService.registPaymentInfo(payment);
				
				return "user/loginError";
			} else {
				String returnCode = (String)rstMap.get("return_code");
				
				if (!"000".equals(returnCode)) {
					model.addAttribute("msg", HMallInterworkUtility.getErrorMsgByCode(returnCode));
					payment.setPymtMemo("포인트 결제 에러");
					orderManageService.registPaymentInfo(payment);
					return "user/loginError";
				}
			}
			
			payment.setPayPoint(usePoint);
			Map<String, String> map = HMallInterworkUtility.procSearchPoint(cus.getCustNm(), cus.getCustId(),decShopEventNo);
			customerPoint = (String)map.get("return_point");
			model.addAttribute("CUST_POINT", customerPoint);
			session.setAttribute("customerPoint", customerPoint);
			
		}
		model.addAttribute("usePoint",usePoint);
		payment.setPymtMemo("결제완료");
		payment.setPayPrice(new BigDecimal(total1.intValue()-usePoint));
		orderManageService.registPaymentInfo(payment);
		model.addAttribute("odPrdInfo",opResInf);
		
		//諛쏅뒗?щ엺 ?뺣낫
		RecipientInfo re = new RecipientInfo();
		re=orderInfo.getReciInfo();
		re.setReciOdNum(odNo);
		
		re.setReciNm(URLDecoder.decode(re.getReciNm(), "UTF-8"));
		re.setReciPh(URLDecoder.decode(re.getReciPh(), "UTF-8"));
		re.setReciMb(URLDecoder.decode(re.getReciMb(), "UTF-8"));
		re.setReciAdd(URLDecoder.decode(re.getReciAdd(), "UTF-8"));
		re.setReciReq(URLDecoder.decode(re.getReciReq(), "UTF-8"));
		
		customerManageService.updateCustomerInf(cus);
		orderManageService.registRecipientInfo(re);
		model.addAttribute("reInfo",re);
		
		//Order ??옣
		orderInfo.setCustomerInfo(cus);
		orderInfo.setModifyUserId(cus.getCustId());
		orderInfo.setOrderStatCd("03");
		orderManageService.registOrderInfo(orderInfo);
	
		
		
		model.addAttribute("config", resConfigInfo);
	
		return "order/orderComplete";
	}
	
	//二쇰Ц?깃났?섏씠吏?
	@RequestMapping(value="/order/orderComplete_allPoint.do")
	public String orderComplete_allPoint(@ModelAttribute("orderInfo") OrderInfo orderInfo,String use_pay_method2,BindingResult result,String card_cd,String good_mny,HttpSession session, Model model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		CustomerInfo cus= (CustomerInfo)session.getAttribute("customerSession");
		
		// ?몄뀡泥댄겕
		if (cus == null) {
			return "user/errorPage";
		}	
		model.addAttribute("CUST_NAME", cus.getCustNm());
	
		String customerPoint = (String)session.getAttribute("customerPoint");
		model.addAttribute("CUST_POINT", customerPoint);
		
		String birth = cus.getCustBirth();
//		cus = useStringToken(birth,"b",cus);
		
		String phone = cus.getCustPh();
		cus = useStringToken(phone,"p",cus);
		
		String mobile = cus.getCustMb();
		cus = useStringToken(mobile,"m",cus);
		model.addAttribute("cus",cus);
		
		
		
		String odNo=orderInfo.getOrderNo();
		if(odNo==null || StringUtils.isEmpty(odNo) || odNo.isEmpty()){
			return "redirect:/";
		}
		
		BigDecimal total = null;
		BigDecimal total1 = new BigDecimal(0);
		
		OrderProductInfo opRes = new OrderProductInfo();
		opRes.setOrderNo(orderInfo.getOrderNo());
		
		List<OrderProductInfo> opResInf = new ArrayList<OrderProductInfo>();
		StringTokenizer st1 = new StringTokenizer(orderInfo.getOrd_unit_chk(),",");
		
		//List<OrderProductInfo> opResInf = new ArrayList<OrderProductInfo>();
		
		CookieBox cki = new CookieBox(request);
		
		while(st1.hasMoreTokens()){
			OrderProductInfo odPrdInfo = new OrderProductInfo();
			
			String cookieKey = st1.nextToken();
			odPrdInfo.setCookieKey(cookieKey);
			
			String prdCd =cookieKey.substring(3,cookieKey.indexOf("_"));
			ProductInfo prInf = new ProductInfo();
			odPrdInfo.setPrdCd(prdCd);
			prInf.setPrdCd(prdCd);
			prInf=productManageService.getProductInfDetail(prInf);
			
			//?곹뭹 ?대쫫
			odPrdInfo.setPrdNm(prInf.getPrdNm());
			
			//?듭뀡
			String vl = cki.getValue(cookieKey);
			if(vl==null || StringUtils.isEmpty(vl) ||vl.equals("null")){
				return "redirect:/";
			}
			StringTokenizer st = new StringTokenizer(vl, ",");
			String option = "";
			
			while (st.hasMoreElements()) {

				String s = st.nextToken();

				if ("01".equals(s.substring(0, 2))) {
					option += URLDecoder.decode(s, "UTF-8") + ",";
					
					odPrdInfo.setPrdOpColor(URLDecoder.decode(s.substring(3), "UTF-8"));
				}
				if ("02".equals(s.substring(0, 2))) {
					option += URLDecoder.decode(s, "UTF-8") + ",";
					odPrdInfo.setPrdOpSize(URLDecoder.decode(s.substring(3), "UTF-8"));
				}
				if("no".equals(s.substring(0, 2))){
					odPrdInfo.setOrderNo(s.substring(3));
				}
				if ("cn".equals(s.substring(0, 2))) {
					odPrdInfo.setBuyCnt(Integer.parseInt(s.substring(3)));
					total = new BigDecimal(prInf.getPrdSellPrc());
					total = total.multiply(new BigDecimal(odPrdInfo.getBuyCnt()));
					odPrdInfo.setTotalPrice(total);
				}
			}
			odPrdInfo.setPrdOption(option);
			
			//?섎웾 諛?湲덉븸
			odPrdInfo.setSellPrice(new BigDecimal(prInf.getPrdSellPrc()));
			total = new BigDecimal(prInf.getPrdSellPrc()) ;
			total=total.multiply(new BigDecimal(odPrdInfo.getBuyCnt()));
			odPrdInfo.setTotalPrice(total);
			total1=total1.add(total);
			//?ъ쭊
			AttachFileInfo att = new AttachFileInfo();
			att.setAttCdKey(prInf.getPrdCd());
			att.setAttImgType("01");
			att = attFileManageService.getAttFileInfListImg(att);
			if(att==null){
				odPrdInfo.setPrdSmallImg("");
			}else { 
				
				odPrdInfo.setPrdSmallImg(att.getAttFilePath());
			}
			
			opResInf.add(odPrdInfo);
			Cookie cookie =cki.createCookie(cookieKey,"null",-1);
			response.addCookie(cookie);
		
			odPrdInfo.setOrderNo(orderInfo.getOrderNo());
			odPrdInfo.setModiUser(cus.getCustId());//user ID ?낅젰
			orderManageService.registOrderProductInfo(odPrdInfo);
			
			//재고 감소
			ProductInfo productInfo = new ProductInfo();
			productInfo.setPrdCd(prdCd);
			productInfo = productManageService.getProductInfDetail(productInfo);
			productInfo.setPrdStock(productInfo.getPrdStock()-odPrdInfo.getBuyCnt());
			productManageService.manageProductInf(productInfo);
			
			//구매 수 증가
			productInfo.setPrdBuyCount(odPrdInfo.getBuyCnt());
			productManageService.updateProductBuyCount(productInfo);
		}

		//諛곗넚鍮꾧????뺣낫
		ConfigInfo resConfigInfo = adminManageService.selectConfigInf();
		
		model.addAttribute("config", resConfigInfo);
		
		if(total1.intValue()<=resConfigInfo.getBuyPrice()){
			total1=total1.add(new BigDecimal(resConfigInfo.getTrasferPrice()));
		}
		
		//pay
		PaymentInfo payment = new PaymentInfo();
		payment.setOrderNo(odNo);
		payment.setOrderNoSeq(1);
		payment.setPayPrice(total1);
		payment.setPayMdCd(use_pay_method2);
		model.addAttribute("pay",use_pay_method2);
		payment.setModifyUserId(cus.getCustId());
		
		
		
		String decMemNm = cus.getCustNm();
		String decMemNo = cus.getCustId();
		String decShopEventNo = (String)session.getAttribute("shopEventNo");
		String decPoint = total1.toString();
		String decOrderNo = odNo;
		orderInfo.setUserPointInfo(decMemNm+"_"+decMemNo+"_"+decShopEventNo+"_"+decPoint);
		// --------------------------------------------
		// 2. SSO泥섎━瑜??꾪븳 ?뱀꽌鍮꾩뒪 ?몄텧
		// --------------------------------------------
		Map<String, String> rstMap = null;
		try {
			rstMap = HMallInterworkUtility.procUsePoint(decMemNm, decMemNo, decShopEventNo, decPoint, decOrderNo);
		} catch (Exception e) {
			model.addAttribute("msg", "SSO에러");
			payment.setPymtMemo("포인트 결제 에러");
			orderManageService.registPaymentInfo(payment);
			return "user/loginError";
		}
		
		// --------------------------------------------
		// 3. 泥댄겕 - SSO泥섎━ 寃곌낵瑜??뺤씤?쒕떎.
		// --------------------------------------------
		if (rstMap == null) {
			model.addAttribute("msg", "SSO에러");
			payment.setPymtMemo("포인트 결제 에러");
			orderManageService.registPaymentInfo(payment);
			
			return "user/loginError";
		} else {
			String returnCode = (String)rstMap.get("return_code");
			
			if (!"000".equals(returnCode)) {
				model.addAttribute("msg", HMallInterworkUtility.getErrorMsgByCode(returnCode));
				payment.setPymtMemo("포인트 결제 에러");
				orderManageService.registPaymentInfo(payment);
				return "user/loginError";
			}
		}
		
		payment.setPayPoint(total1.intValue());
		Map<String, String> map = HMallInterworkUtility.procSearchPoint(cus.getCustNm(), cus.getCustId(),decShopEventNo);
		customerPoint = (String)map.get("return_point");
		model.addAttribute("CUST_POINT", customerPoint);
		session.setAttribute("customerPoint", customerPoint);
		

		model.addAttribute("usePoint",total1);
		payment.setPymtMemo("결제완료");
		orderManageService.registPaymentInfo(payment);
		model.addAttribute("odPrdInfo",opResInf);
		
		//諛쏅뒗?щ엺 ?뺣낫
		RecipientInfo re = new RecipientInfo();
		re=orderInfo.getReciInfo();
		re.setReciOdNum(odNo);
		re.setReciNm(URLDecoder.decode(re.getReciNm(), "UTF-8"));
		re.setReciPh(URLDecoder.decode(re.getReciPh(), "UTF-8"));
		re.setReciMb(URLDecoder.decode(re.getReciMb(), "UTF-8"));
		re.setReciAdd(URLDecoder.decode(re.getReciAdd(), "UTF-8"));
		re.setReciReq(URLDecoder.decode(re.getReciReq(), "UTF-8"));
		
		customerManageService.updateCustomerInf(cus);
		orderManageService.registRecipientInfo(re);
		model.addAttribute("reInfo",re);
		
		//Order ??옣
		orderInfo.setCustomerInfo(cus);
		orderInfo.setModifyUserId(cus.getCustId());
		orderInfo.setOrderStatCd("03");
		orderManageService.registOrderInfo(orderInfo);
	
				
	
		return "order/orderComplete";
	}
	
	//二쇰Ц?ㅽ뙣?섏씠吏?
	@RequestMapping(value="/order/orderError.do")
	public String orderError(@ModelAttribute("orderInfo") OrderInfo orderInfo,BindingResult result, Model model){
		return "order/orderError";
	}
	//二쇰Ц肄붾뱶 ?앹꽦?섎뒗 硫붿냼??- ?닿쾬??諛붽퓭二쇱떖 媛먯궗
	public String getOrderCode(){

		// 二쇰Ц 肄붾뱶 梨꾨쾲
		int code= (int) Math.round(Math.random() * 100000);
		Calendar cal = Calendar.getInstance();
		int year  = cal.get(Calendar.YEAR)-2000;
		int month = cal.get(Calendar.MONTH);
		String mon="";
		switch(month){
		case 1:
			mon="J";
			break;
		case 2:
			mon="F";
			break;
		case 3:
			mon="M";
			break;
		case 4:
			mon="A";
			break;
		case 5:
			mon="M";
			break;
		case 6:
			mon="J";
			break;
		case 7:
			mon="J";
			break;
		case 8:
			mon="A";
			break;
		case 9:
			mon="S";
			break;
		case 10:
			mon="O";
			break;
		case 11:
			mon="N";
			break;
		case 12:
			mon="D";
			break;
		
		}
		String day = Integer.toString((cal.DAY_OF_MONTH)+1);
		day=day.length()>1?day:"0"+day;
		String odCode = "BOM"+year+mon+day+code;
		
		return odCode;
	}
	
	
	public List<OrderProductInfo> getCartList(CookieBox cki) throws IOException{

		List<String> ckKey = cki.getKey();
		List<OrderProductInfo> ord = new ArrayList<OrderProductInfo>();
		
		// ?ㅻ?遺덈윭?ㅻ㈃ 泥섎━?댁쨪 遺?텇
		for(String each : ckKey){
			if (each == null || each.length() < 4) continue;
			
			if("BOMP".equals(each.substring(0, 4))){
			
				OrderProductInfo odPrdInfo = new OrderProductInfo();
				odPrdInfo.setCookieKey(each);
				String key = each.substring(3,each.indexOf("_"));
				odPrdInfo.setPrdCd(key);
				ProductInfo prdInfo = new ProductInfo();
				prdInfo.setPrdCd(key);
				prdInfo = productManageService.getProductInfDetail(prdInfo);

				if(prdInfo!=null){
					odPrdInfo.setPrdCd(key);
					odPrdInfo.setPrdNm(prdInfo.getPrdNm());
					odPrdInfo.setSellPrice(new BigDecimal(prdInfo.getPrdSellPrc()));
	
					AttachFileInfo att = new AttachFileInfo();
					att.setAttCdKey(prdInfo.getPrdCd());
					att.setAttImgType("01");
					att = attFileManageService.getAttFileInfListImg(att);
					if (att == null) {
						odPrdInfo.setPrdSmallImg("");
					} else {
	
						odPrdInfo.setPrdSmallImg(att.getAttFilePath());
					}
	
					String vl = cki.getValue(each);
					StringTokenizer st = new StringTokenizer(vl, ",");
					String option = "";
					
					while (st.hasMoreElements()) {
	
						String s = st.nextToken();
	
						
						
						if ("01".equals(s.substring(0, 2))) {
							option += s + ",";
							odPrdInfo.setPrdOpColor(URLDecoder.decode(s.substring(3), "UTF-8"));
						}
						if ("02".equals(s.substring(0, 2))) {
							option += s + ",";
							
							odPrdInfo.setPrdOpSize(URLDecoder.decode(s.substring(3), "UTF-8"));
						}
							/*if("no".equals(s.substring(0, 2))){
								odPrdInfo.setOrderNo(s.substring(3));
							}*/
							if ("cn".equals(s.substring(0, 2))) {
								odPrdInfo.setBuyCnt(Integer.parseInt(s.substring(3)));
								BigDecimal total = new BigDecimal(
										prdInfo.getPrdSellPrc());
								total = total.multiply(new BigDecimal(odPrdInfo
										.getBuyCnt()));
								odPrdInfo.setTotalPrice(total);
							}
		
							odPrdInfo.setPrdOption(option);
							
							
					}
					if (vl.equals(""));
					else if(vl.equals("null"));
					else ord.add(odPrdInfo);
				
				}
				
			}//if end
		}//for-end
		
		
		
		
		return ord;
	}
	
	public CustomerInfo setSession(HttpSession session){
		CustomerInfo custom = (CustomerInfo)session.getAttribute("customerSession");
		/*String phone = custom.getCustPh();
		String[] phoneArr = phone.split("-");
		
		if (phoneArr.length > 0) custom.setTelNo1(phoneArr[0]);
		if (phoneArr.length > 1) custom.setTelNo2(phoneArr[1]);
		if (phoneArr.length > 2) custom.setTelNo3(phoneArr[2]);
	
		custom.setTelNo1(phoneArr[0]);
		 custom.setTelNo2(phoneArr[1]);
		 custom.setTelNo3(phoneArr[2]);
		
		phone = phoneArr[0]+phoneArr[1]+phoneArr[2];
		custom.setCustPh(phone);
		
		String mobile = custom.getCustMb();
		String[] mobileArr = mobile.split("-");
		if (mobileArr.length > 0) custom.setHpNo1(mobileArr[0]);
		if (mobileArr.length > 1) custom.setHpNo2(mobileArr[1]);
		if (mobileArr.length > 2) custom.setHpNo3(mobileArr[2]);
	
		 custom.setHpNo1(mobileArr[0]);
	 custom.setHpNo2(mobileArr[1]);
		 custom.setHpNo3(mobileArr[2]);
		mobile = mobileArr[0]+mobileArr[1]+mobileArr[2];
		custom.setCustMb(mobile);*/
		// ?몄뀡泥댄겕
		
		String phone = custom.getCustPh();
		custom = useStringToken(phone,"p",custom);
		
		String mobile = custom.getCustMb();
		custom = useStringToken(mobile,"m",custom);
		
		custom.setCustMb(mobile);
		custom.setCustPh(phone);
		return custom;
		
	}
			
	public CustomerInfo useStringToken(String st, String Type,CustomerInfo cus ){
		
		CustomerInfo result = cus;
		
		String birth = result.getCustBirth();
		StringTokenizer stTk = new StringTokenizer(st,"-");
		
		int i=1;
		while(stTk.hasMoreElements()){
			switch(i){
			case 1:
				if(Type.equals("b")){
					result.setBirthY(Integer.parseInt(stTk.nextToken()));i++;
				}else if(Type.equals("m")){
					result.setHpNo1(stTk.nextToken());i++;
				}else if(Type.equals("p")){
					result.setTelNo1(stTk.nextToken());i++;
				}
				break;
			case 2:
				if(Type.equals("b")){
					result.setBirthM(Integer.parseInt(stTk.nextToken()));i++;
				}else if(Type.equals("m")){
					result.setHpNo2(stTk.nextToken());i++;
				}else if(Type.equals("p")){
					result.setTelNo2(stTk.nextToken());i++;
				}
				break;
			case 3:
				if(Type.equals("b")){
					String day = stTk.nextToken();
					day=day.substring(0, 2);
					result.setBirthD(Integer.parseInt(day));i++;
				}else if(Type.equals("m")){
					result.setHpNo3(stTk.nextToken());i++;
				}else if(Type.equals("p")){
					result.setTelNo3(stTk.nextToken());i++;
				}
				break;
				
			}
		}
		
		return result;
	

	}
	
	public static String convertEnc(String str) throws IOException {
		String rtn = HCDESUtil.encoding(Base64Encoder.encode(str.getBytes(ENC_TYPE)), HCDES_KEY);
		return rtn;
	}
}
