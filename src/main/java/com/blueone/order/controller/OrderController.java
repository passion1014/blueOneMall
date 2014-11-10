
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
import com.blueone.common.domain.HMallProcAdjustmentInfo;
import com.blueone.common.service.IAttachFileManageService;
import com.blueone.common.util.CookieBox;
import com.blueone.common.util.CookieUtil;
import com.blueone.common.util.DateUtil;
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
	public void orderRegisteProc(@ModelAttribute("orderInfo") OrderInfo orderInfo,HttpSession session,BindingResult result, Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		CustomerInfo cus= (CustomerInfo)session.getAttribute("customerSession");	
		/*// ?몄뀡泥댄겕
		if (cus == null) {
			return "user/errorPage";
		}	
		*/
	
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
	
}


	//二쇰Ц?깃났?섏씠吏?
	@RequestMapping(value="/order/orderComplete.do")
	public String orderComplete(@ModelAttribute("orderInfo") OrderInfo orderInfo,String use_pay_method,BindingResult result,String card_cd,String good_mny,HttpSession session, Model model,
			HttpServletRequest request,HttpServletResponse response, String req_tx,String bSucc,String res_cd,String app_no,String card_name) throws Exception{
		

		if(req_tx!=null && !req_tx.isEmpty() && !StringUtils.isEmpty(req_tx)){
			if( req_tx.equals("mod")) return "redirect:/admin/orderManagement.do?orderNo="+orderInfo.getOrderNo()+"&custId="+orderInfo.getCustomerInfo().getCustId();
		}
		CustomerInfo cus= (CustomerInfo)session.getAttribute("customerSession");
		
		// ?몄뀡泥댄겕
		if (cus == null) {
			return "user/errorPage";
		}	
		model.addAttribute("CUST_NAME", cus.getCustNm());
	
		if(res_cd != null || !res_cd.isEmpty()){
		
			if(!res_cd.equals("0000"))
				return "order/orderError";
			
		}
		
		if(bSucc != null || !bSucc.isEmpty()){
			
			if(bSucc.equals("false"))
				return "order/orderError";
			
		}
		
			
		
		
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
		String deliAmt="0";
		if(total1.intValue()<=resConfigInfo.getBuyPrice()){
			total1=total1.add(new BigDecimal(resConfigInfo.getTrasferPrice()));
			deliAmt=Integer.toString(resConfigInfo.getTrasferPrice());
		}
		
		//pay
		PaymentInfo payment = new PaymentInfo();
		payment.setOrderNo(odNo);
		payment.setOrderNoSeq(1);
		
		payment.setPayMdCd(use_pay_method);
		model.addAttribute("pay",use_pay_method);
		payment.setModifyUserId(cus.getCustId());
		
		int usePoint =0;
		String decMemNo = cus.getCustId();
		String decShopEventNo = (String)session.getAttribute("shopEventNo");
		String decShopNo = (String)session.getAttribute("shopNo");
		orderInfo.setUserPointInfo(decShopNo);
		//포인트 결제
		if(!good_mny.equals(total1.toString())){
			String decMemNm = cus.getCustNm();
			decMemNo = cus.getCustId();
			decShopEventNo = (String)session.getAttribute("shopEventNo");
			usePoint = total1.intValue()-Integer.parseInt(good_mny);
			String decPoint = Integer.toString(usePoint);
			String decOrderNo = odNo;
			orderInfo.setUserPointInfo(decMemNm+"_"+decMemNo+"_"+decShopEventNo+"_"+decPoint+"_"+decShopNo);
			
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
		if(res_cd.equals("0000"))
			payment.setPymtMemo("결제완료");
		else
			payment.setPymtMemo("결제실패");
		
		if(bSucc.equals("false"))
			payment.setPymtMemo("결제실패(PG사 디비오류)");
		else
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
		orderInfo.setOrderStatCd("02");
		if(app_no != null || !app_no.isEmpty()) orderInfo.setCardInfo1(app_no);
		//if(card_name != null || !card_name.isEmpty()) orderInfo.setCardInfo2(URLDecoder.decode(card_name, "UTF-8"));
		//if(card_name != null || !card_name.isEmpty()) orderInfo.setCardInfo2(card_name);
		//orderManageService.updateOrderInf(orderInfo);
	    orderManageService.registOrderInfo(orderInfo);
		
		
		model.addAttribute("config", resConfigInfo);
		
		
		List<OrderInfo> orderList = orderManageService.selectListBomOrderTbToExel0001(orderInfo);
		
		for(OrderInfo each : orderList){
			String pointInfo= each.getUserPointInfo();
			
			String[] point = pointInfo.split("_");
			Map<String, String> rstMap = null;
			HMallProcAdjustmentInfo adjustment = new HMallProcAdjustmentInfo();
			try {
				// --------------------------------------------
				// 1. 정산 처리 
				// --------------------------------------------
				
				adjustment.setOrderNo(orderInfo.getOrderNo());
				adjustment.setItemCd(each.getOrdPrd().getPrdCd());
				adjustment.setOrderGb("10");
				adjustment.setOrderDm(DateUtil.getDate("yyyyMMdd"));
				adjustment.setShopNo( decShopNo);
				adjustment.setShopEventNo(decShopEventNo);
				adjustment.setMemNo(cus.getCustId());
				adjustment.setTaxGb("1");
				adjustment.setSalePrice(each.getOrdPrd().getSellPrice().multiply(new BigDecimal(each.getOrdPrd().getBuyCnt())).toString());
				adjustment.setPointAmt(Integer.toString(each.getPaymentInfo().getPayPoint()/orderList.size()));
				BigDecimal etc = each.getOrdPrd().getSellPrice();
				etc =etc.subtract(new BigDecimal(each.getPaymentInfo().getPayPoint()/orderList.size()));
				adjustment.setEtcAmt(etc.toString());
				adjustment.setDeliAmt("0");
				String option = each.getOrdPrd().getPrdOpColor();
				if(option == null || option.isEmpty()) adjustment.setItemNm(each.getOrdPrd().getPrdNm());
				else adjustment.setItemNm(each.getOrdPrd().getPrdNm()+option.substring(3, option.indexOf(",")));
	        	adjustment.setItemPrice(each.getOrdPrd().getSellPrice().toString());
	        	adjustment.setOrderQty(Integer.toString(each.getOrdPrd().getBuyCnt()));
	        	adjustment.setDcPrice("0");
	        	
	        	
	        	rstMap = HMallInterworkUtility.procAdjustment(adjustment);
	        	
			} catch (Exception e) {
				model.addAttribute("msg", "정산 처리시 에러발생하였습니다.");
				return "user/loginError";
			}
			
			// --------------------------------------------
			// 3. 체크 - SSO처리 결과를 확인한다.
			// --------------------------------------------
			if (rstMap == null) {
				model.addAttribute("msg", "SSO처리 결과가 없습니다.(1)");
				return "user/loginError";
			} else {
				String returnCode = (String)rstMap.get("return_code");
				String date = adjustment.getOrderDm()+"("+DateUtil.getDate("h:mm a")+")";
				adjustment.setOrderDm(date);
				adjustment.setReturnCode(returnCode);
				if (!"000".equals(returnCode)) {
					model.addAttribute("msg", HMallInterworkUtility.getErrorMsgByCode(returnCode));
				}
				orderManageService.insertBomHMTb0001(adjustment);
			}
		}
		
	
	
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
					if(!s.substring(3).isEmpty()){
						option += URLDecoder.decode(s, "UTF-8") + ",";
						
						odPrdInfo.setPrdOpColor(URLDecoder.decode(s.substring(3), "UTF-8"));
					}
				}
				if ("02".equals(s.substring(0, 2))) {
				
					if(!s.substring(3).isEmpty()){
						option += URLDecoder.decode(s, "UTF-8") + ",";
						odPrdInfo.setPrdOpSize(URLDecoder.decode(s.substring(3), "UTF-8"));
					}
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
		String decShopNo = (String)session.getAttribute("shopNo");
		String decPoint = total1.toString();
		String decOrderNo = odNo;
		orderInfo.setUserPointInfo(decMemNm+"_"+decMemNo+"_"+decShopEventNo+"_"+decPoint+"_"+decShopNo);
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
	
		List<OrderInfo> orderList = orderManageService.selectListBomOrderTbToExel0001(orderInfo);
		
		for(OrderInfo each : orderList){
			
			rstMap = null;
			HMallProcAdjustmentInfo adjustment = new HMallProcAdjustmentInfo();
			try {
				// --------------------------------------------
				// 1. 정산 처리 
				// --------------------------------------------
				
				adjustment.setOrderNo(odNo);
				adjustment.setItemCd(each.getOrdPrd().getPrdCd());
				adjustment.setOrderGb("10");
				adjustment.setOrderDm(DateUtil.getDate("yyyyMMdd"));
				adjustment.setShopNo( decShopNo);
				adjustment.setShopEventNo(decShopEventNo);
				adjustment.setMemNo(cus.getCustId());
				adjustment.setTaxGb("1");
				adjustment.setSalePrice(each.getOrdPrd().getSellPrice().multiply(new BigDecimal(each.getOrdPrd().getBuyCnt())).toString());
				adjustment.setPointAmt(each.getOrdPrd().getSellPrice().multiply(new BigDecimal(each.getOrdPrd().getBuyCnt())).toString());
				adjustment.setEtcAmt("0");
				adjustment.setDeliAmt("0");
				String option = each.getOrdPrd().getPrdOpColor();
				if(option == null || option.isEmpty()) adjustment.setItemNm(each.getOrdPrd().getPrdNm());
				else adjustment.setItemNm(each.getOrdPrd().getPrdNm()+option.substring(3, option.indexOf(",")));
	        	adjustment.setItemPrice(each.getOrdPrd().getSellPrice().toString());
	        	adjustment.setOrderQty(Integer.toString(each.getOrdPrd().getBuyCnt()));
	        	adjustment.setDcPrice("0");
	        	
	        	
	        	rstMap = HMallInterworkUtility.procAdjustment(adjustment);
	        	
			} catch (Exception e) {
				model.addAttribute("msg", "정산 처리시 에러발생하였습니다.");
				return "user/loginError";
			}
			
			// --------------------------------------------
			// 3. 체크 - SSO처리 결과를 확인한다.
			// --------------------------------------------
			if (rstMap == null) {
				model.addAttribute("msg", "SSO처리 결과가 없습니다.(1)");
				return "user/loginError";
			} else {
				String returnCode = (String)rstMap.get("return_code");
				String date = adjustment.getOrderDm()+"("+DateUtil.getDate("h:mm a")+")";
				adjustment.setOrderDm(date);
				adjustment.setReturnCode(returnCode);
				orderManageService.insertBomHMTb0001(adjustment);
				
				if (!"000".equals(returnCode)) {
					model.addAttribute("msg", HMallInterworkUtility.getErrorMsgByCode(returnCode));
				}
				
				
			}
		}
		
	
	
		
		
	
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
