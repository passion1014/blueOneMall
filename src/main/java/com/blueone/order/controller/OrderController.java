package com.blueone.order.controller;

import java.io.IOException;
import java.math.BigDecimal;
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

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blueone.common.domain.AttachFileInfo;
import com.blueone.common.service.IAttachFileManageService;
import com.blueone.common.util.CookieBox;
import com.blueone.common.util.CookieUtil;
import com.blueone.customer.domain.CustomerContactInfo;
import com.blueone.customer.domain.CustomerInfo;
import com.blueone.customer.domain.RecipientInfo;
import com.blueone.customer.service.ICustomerManageService;
import com.blueone.order.domain.OrderInfo;
import com.blueone.order.domain.OrderProductInfo;
import com.blueone.order.domain.OrderSrchInfo;
import com.blueone.order.service.IOrderManageService;
import com.blueone.product.domain.ProductInfo;
import com.blueone.product.service.IProductManageService;
import com.blueone.user.domain.UserInfo;
import com.jidesoft.converter.BigDecimalConverter;

@Controller
public class OrderController {
	
	@Autowired IOrderManageService orderManageService;
	@Autowired IProductManageService productManageService;
	@Autowired IAttachFileManageService attFileManageService;
	@Autowired ICustomerManageService customerManageService;
	
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
	
	//장바구니에 들어가기
	@RequestMapping(value="/order/cartList.do")
	public String order(@ModelAttribute("orderProductInfo") OrderProductInfo orderProductInfo,BindingResult result, Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		CookieBox cki = new CookieBox(request);
		
		//상품이 선택되서 장바구니 페이지로 들어왔을 경우 해당
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
				if((orderProductInfo.getPrdOpColor()==null || orderProductInfo.getPrdOpColor().isEmpty()) && optionSize.equals(orderProductInfo.getPrdOpSize())){
					count += orderProductInfo.getBuyCnt();
					break;
				}else if((orderProductInfo.getPrdOpSize()==null || orderProductInfo.getPrdOpSize().isEmpty()) && optionColor.equals(orderProductInfo.getPrdOpColor())){
					count += orderProductInfo.getBuyCnt();
					break;
				}else if((orderProductInfo.getPrdOpSize()==null || orderProductInfo.getPrdOpSize().isEmpty()) && (orderProductInfo.getPrdOpColor()==null || orderProductInfo.getPrdOpColor().isEmpty())){
					if(optionSize.equals(orderProductInfo.getPrdOpSize()) && optionColor.equals(orderProductInfo.getPrdOpColor())){
						count += orderProductInfo.getBuyCnt();
						break;
					}else if(countExist.equals("y")){
						count += orderProductInfo.getBuyCnt();
					}else{
						pNum = prdCount+1 ;
						count=orderProductInfo.getBuyCnt();
						
					}
					
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
			Cookie cookie =cki.createCookie("BOM"+orderProductInfo.getPrdCd()+"_"+pNum,value,50000);
			response.addCookie(cookie);//
	
		
		}
		
		
		

		
		return "redirect:cartListView.do";
			
		
	}

	// 장바구니-수량 수정
	@RequestMapping(value="/order/editBuyCnt.do")
	public String editBuyCnt(@ModelAttribute("orderProductInfo") OrderProductInfo orderProductInfo,BindingResult result,HttpSession session, Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{
		//세션에 잇는 정보를 셋팅
		CustomerInfo custom = setSession(session);
				
		CookieBox cki = new CookieBox(request);
		String cookieVal = cki.getValue(orderProductInfo.getCookieKey());
		
		String changeValue = CookieUtil.changeProdOption(cookieVal, "cn", String.valueOf(orderProductInfo.getBuyCnt()));
		
		Cookie cookie =cki.createCookie(orderProductInfo.getCookieKey(),changeValue, 50000);
		response.addCookie(cookie);//
		
		return "redirect:cartListView.do";
	}
	
	//상품구매 - 수량수정
	@RequestMapping(value="/order/editOrderBuycn.do")
	public String editOrderBuycn(@ModelAttribute("orderInfo") OrderInfo orderInfo,BindingResult result,HttpSession session, Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{
	
		
		
		
		//세션에 잇는 정보를 셋팅
		CustomerInfo custom = setSession(session);
		model.addAttribute("cus",custom);
		
		
		int IDX =orderInfo.getIdx();
		
		StringTokenizer st = new StringTokenizer(orderInfo.getOrd_unit_chk(),",");

		
		List<OrderProductInfo> oPrdList = new ArrayList<OrderProductInfo>();
		
		CookieBox cki = new CookieBox(request);
		
		
		while(st.hasMoreTokens()){ // 반활할 토큰이 있는가? true/false;
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
					odPrdInfo.setPrdOpColor(s.substring(3));
				}
				if ("02".equals(s.substring(0, 2))) {
					option += s + ",";
					odPrdInfo.setPrdOpSize(s.substring(3));
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

		Cookie cookie = cki.createCookie("BOM_" + res.getPrdCd(), value, 50000);
		response.addCookie(cookie);//

		oPrdList.add(IDX, res);
		model.addAttribute("odPrdInfo", oPrdList);
		
	
		return "order/order";
	}
	
	
	//바로구매
	@RequestMapping(value="/order/orderDirect.do")
	public String orderDirect(@ModelAttribute("orderProductInfo") OrderProductInfo orderProductInfo,HttpSession session,BindingResult result, Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{
	
		
		
		//세션에 잇는 정보를 셋팅
		CustomerInfo custom = setSession(session);
		
		model.addAttribute("cus",custom);
		
		List<OrderProductInfo> oPrdList = new ArrayList<OrderProductInfo>();
		
		CookieBox cki = new CookieBox(request);
		
		//상품이 선택되서 장바구니 페이지로 들어왔을 경우 해당
		String value="";
		
		
		List<String> ckKey = cki.getKey();
	
		
		int pNum=0;
		int count= orderProductInfo.getBuyCnt(); 
		int prdCount=-1;
		String countExist="n";
		
		if(ckKey!=null){
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
					if((orderProductInfo.getPrdOpColor()==null || orderProductInfo.getPrdOpColor().isEmpty()) && optionSize.equals(orderProductInfo.getPrdOpSize())){
						count += orderProductInfo.getBuyCnt();
						break;
					}else if((orderProductInfo.getPrdOpSize()==null || orderProductInfo.getPrdOpSize().isEmpty()) && optionColor.equals(orderProductInfo.getPrdOpColor())){
						count += orderProductInfo.getBuyCnt();
						break;
					}else if((orderProductInfo.getPrdOpSize()==null || orderProductInfo.getPrdOpSize().isEmpty()) && (orderProductInfo.getPrdOpColor()==null || orderProductInfo.getPrdOpColor().isEmpty())){
						if(optionSize.equals(orderProductInfo.getPrdOpSize()) && optionColor.equals(orderProductInfo.getPrdOpColor())){
							count += orderProductInfo.getBuyCnt();
							break;
						}else if(countExist.equals("y")){
							count += orderProductInfo.getBuyCnt();
						}else{
							pNum = prdCount+1 ;
							count=orderProductInfo.getBuyCnt();
							
						}
						
					}else{
						pNum = prdCount+1 ;
						count=orderProductInfo.getBuyCnt();
						
					}
				}
					
					
			}
		}
		
		
		
		
		String key = orderProductInfo.getPrdCd();
		ProductInfo prdInfo = new ProductInfo();
		prdInfo.setPrdCd(key);
		prdInfo = productManageService.getProductInfDetail(prdInfo);

		orderProductInfo.setPrdNm(prdInfo.getPrdNm());
		orderProductInfo.setSellPrice(new BigDecimal(prdInfo.getPrdSellPrc()));

		AttachFileInfo att = new AttachFileInfo();
		att.setAttCdKey(prdInfo.getPrdCd());
		att.setAttImgType("01");
		att = attFileManageService.getAttFileInfListImg(att);
		if (att == null) {
			orderProductInfo.setPrdSmallImg("");
		} else {

			orderProductInfo.setPrdSmallImg(att.getAttFilePath());
		}

	
			
		orderProductInfo.setOrderNo(getOrderCode());

		BigDecimal total = new BigDecimal(prdInfo.getPrdSellPrc());
		total = total.multiply(new BigDecimal(orderProductInfo.getBuyCnt()));
		orderProductInfo.setTotalPrice(total);
		OrderInfo od = new OrderInfo();
		od.setOrd_unit_chk("BOM"+orderProductInfo.getPrdCd()+"_"+pNum);
		od.setOrderNo(getOrderCode());
		oPrdList.add(orderProductInfo);
		
	
		model.addAttribute("orderInfo",od);
		model.addAttribute("odPrdInfo",oPrdList);
		
		return "order/order";
	}
	
	//장바구니 보여줌
	@RequestMapping(value="/order/cartListView.do")
	public String orderView(@ModelAttribute("orderProductInfo") OrderProductInfo orderProductInfo,HttpSession session,BindingResult result, Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{

		
		//세션에 잇는 정보를 셋팅
		CustomerInfo custom = setSession(session);
		
		CookieBox cki = new CookieBox(request);
		List<OrderProductInfo> ord = getCartList(cki);
		
		
		model.addAttribute("odPrdInfo",ord);
		
		
		return "order/cartList";
	}
	
	//장바구니 상품 삭제
	@RequestMapping(value="/order/deleteCartList.do", method = RequestMethod.GET)
	public String deleteCartList(@ModelAttribute("orderProductInfo") OrderProductInfo orderProductInfo,BindingResult result, Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		CookieBox cki = new CookieBox(request);
		
		Cookie cookie =cki.createCookie(orderProductInfo.getCookieKey(),"null",-1);
		response.addCookie(cookie);
		

		return "redirect:cartListView.do";
	}
	
	//주문 상품 삭제
	@RequestMapping(value="/order/deleteOrderList.do", method = RequestMethod.GET)
	public String deleteOrderList(@ModelAttribute("orderProductInfo") OrderProductInfo orderProductInfo,@ModelAttribute("orderInfo") OrderInfo orderInfo,BindingResult result, Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		CookieBox cki = new CookieBox(request);
		
		Cookie cookie =cki.createCookie(orderProductInfo.getCookieKey(),"null",-1);
		response.addCookie(cookie);
		StringTokenizer st = new StringTokenizer(orderInfo.getOrd_unit_chk(),",");
		
		String checked ="";
		while(st.hasMoreTokens()){ // 반활할 토큰이 있는가? true/false;
			String cookieKey = st.nextToken();
			if(orderProductInfo.getCookieKey().equals(cookieKey)){
				
			}else{
				checked+=","+cookieKey+",";
			}
		}

		return "redirect:orderRegister.do?ord_unit_chk="+checked;
	}
		
	//장바구니->결제페이지
	@RequestMapping(value="/order/orderRegister.do")
	public String orderRegister(@ModelAttribute("orderInfo") OrderInfo orderInfo,BindingResult result,HttpSession session, Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		StringTokenizer st = new StringTokenizer(orderInfo.getOrd_unit_chk(),",");
		
		List<OrderProductInfo> oPrdList = new ArrayList<OrderProductInfo>();
		
		CookieBox cki = new CookieBox(request);
		
		
		while(st.hasMoreTokens()){ // 반활할 토큰이 있는가? true/false;
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
					odPrdInfo.setPrdOpColor(s.substring(3));
				}
				if ("02".equals(s.substring(0, 2))) {
					option += s + ",";
					odPrdInfo.setPrdOpSize(s.substring(3));
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
		orderInfo.setOrderNo(getOrderCode());
		model.addAttribute("odPrdInfo",oPrdList);
		
		//세션에 잇는 정보를 셋팅
		CustomerInfo custom  = setSession(session);
		model.addAttribute("cus",custom);
					
		model.addAttribute("orderInfo",orderInfo);
		
		return "order/order";
	}
	
	//결제페이지-처리
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

		//업체에서 추가하신 인자값을 받는 부분입니다
		String	a =  request.getParameter("a"); 
		String	b =  request.getParameter("b"); 
		String	c =  request.getParameter("c"); 
		String	d =  request.getParameter("d");

		com.blueone.common.util.KSPayWebHostBean ipg = new com.blueone.common.util.KSPayWebHostBean(rcid);
		if (ipg.kspay_send_msg("1"))		//KSNET 결제결과 중 아래에 나타나지 않은 항목이 필요한 경우 Null 대신 필요한 항목명을 설정할 수 있습니다.
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

				//ipg.kspay_send_msg("3");		// 정상처리가 완료되었을 경우 호출합니다.(이 과정이 없으면 일시적으로 kspay_send_msg("1")을 호출하여 거래내역 조회가 가능합니다.)
			}
		}*/
		//세션에 잇는 정보를 셋팅
		CustomerInfo custom = setSession(session);
		
		
		//주문번호
		String orderNum=orderInfo.getOrderNo();
		
		List<OrderProductInfo> ord  = orderInfo.getOrderProductList();
		for(OrderProductInfo each : ord){
			
			//OrderProduct저장
			each.setOrderNo(orderNum);
			each.setModiUser(custom.getCustId());//user ID 입력
			orderManageService.registOrderProductInfo(each);

		
		
		}
		
		//Order 저장
		OrderInfo orderInfo1 = new OrderInfo();
		orderInfo1.setOrderNo(orderNum);
		orderInfo1.setOrderStatCd("02");
		orderInfo1.setCustomerInfo(custom);
		orderInfo1.setModifyUserId(custom.getCustId());
		orderManageService.registOrderInfo(orderInfo1);
		StringTokenizer st = new StringTokenizer(orderInfo.getOrd_unit_chk(),",");
	
		
		CookieBox cki = new CookieBox(request);
		
		
		while(st.hasMoreTokens()){ // 반활할 토큰이 있는가? true/false;
			Cookie cookie =cki.createCookie(st.nextToken(),"null",-1);
			response.addCookie(cookie);
		}

		
		RecipientInfo re = new RecipientInfo();
		re=orderInfo.getReciInfo();
		re.setReciOdNum(orderNum);
		custom.setCustAdd(re.getAdd1());
		customerManageService.updateCustomerInf(custom);
		orderManageService.registRecipientInfo(re);
		
	return "redirect:orderComplete.do?orderNo="+orderNum;
}


	//결제 팝업
	@RequestMapping(value="/order/orderPay.do")
	public String orderPay(@ModelAttribute("orderInfo") OrderInfo orderInfo,HttpSession session,BindingResult result, Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		//세션에 잇는 정보를 셋팅
		CustomerInfo custom = setSession(session);
		
		
		//주문번호
		String orderNum=orderInfo.getOrderNo();
		
		List<OrderProductInfo> ord  = orderInfo.getOrderProductList();
		for(OrderProductInfo each : ord){
			
			//OrderProduct저장
			each.setOrderNo(orderNum);
			each.setModiUser(custom.getCustId());//user ID 입력
			orderManageService.registOrderProductInfo(each);

		
		
		}
		
		//Order 저장
		OrderInfo orderInfo1 = new OrderInfo();
		orderInfo1.setOrderNo(orderNum);
		orderInfo1.setOrderStatCd("01");
		orderInfo1.setCustomerInfo(custom);
		orderInfo1.setModifyUserId(custom.getCustId());
		orderManageService.registOrderInfo(orderInfo1);
		StringTokenizer st = new StringTokenizer(orderInfo.getOrd_unit_chk(),",");
	
		
		CookieBox cki = new CookieBox(request);
		
		
		while(st.hasMoreTokens()){ // 반활할 토큰이 있는가? true/false;
			Cookie cookie =cki.createCookie(st.nextToken(),"null",-1);
			response.addCookie(cookie);
		}

		
		RecipientInfo re = new RecipientInfo();
		re=orderInfo.getReciInfo();
		re.setReciOdNum(orderNum);
		custom.setCustAdd(re.getAdd1());
		customerManageService.updateCustomerInf(custom);
		orderManageService.registRecipientInfo(re);
		
		model.addAttribute("orderInfo1",orderInfo1);
		model.addAttribute("orderInfo",orderInfo);
		
		model.addAttribute("recipientInfo",re);
		
		return "order/orderPay";
	}
	
	
	//결제 팝업
	@RequestMapping(value="/order/orderPayKcp.do")
	public String orderPayKcp(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		return "order/pp_ax_hub";
	}


	//주문성공페이지
	@RequestMapping(value="/order/orderComplete.do")
	public String orderComplete(@ModelAttribute("orderInfo") OrderInfo orderInfo,BindingResult result,HttpSession session, Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		//세션에 잇는 정보를 셋팅
		CustomerInfo custom = setSession(session);
		model.addAttribute("cus",custom);
		
		//결제상품 보여주기
		String odNo=orderInfo.getOrderNo();
		
		
		OrderProductInfo opRes = new OrderProductInfo();
		opRes.setOrderNo(orderInfo.getOrderNo());
		List<OrderProductInfo> opResInf = orderManageService.selectOrderPrdInfo(opRes);
		
		for(OrderProductInfo each : opResInf){
			String prdCd = each.getPrdCd();
			ProductInfo prInf = new ProductInfo();
			prInf.setPrdCd(prdCd);
			prInf=productManageService.getProductInfDetail(prInf);
			
			//상품 이름
			each.setPrdNm(prInf.getPrdNm());
			
			//옵션
			String option=each.getPrdOption();
			StringTokenizer st = new StringTokenizer(option,",");
			while(st.hasMoreElements()) {
					
					String s = st.nextToken();
					
					if("01".equals(s.substring(0, 2))){
						option+=s+",";
						each.setPrdOpColor(s.substring(3));
					}
					if("02".equals(s.substring(0, 2))){
						option+=s+",";
						each.setPrdOpSize(s.substring(3));
					}
			
			}
			
			//수량 및 금액
			each.setSellPrice(new BigDecimal(prInf.getPrdSellPrc()));
			BigDecimal total = new BigDecimal(prInf.getPrdSellPrc()) ;
			total=total.multiply(new BigDecimal(each.getBuyCnt()));
			each.setTotalPrice(total);
			
			//사진
			AttachFileInfo att = new AttachFileInfo();
			att.setAttCdKey(prInf.getPrdCd());
			att.setAttImgType("01");
			att = attFileManageService.getAttFileInfListImg(att);
			if(att==null){
				each.setPrdSmallImg("");
			}else { 
				
				each.setPrdSmallImg(att.getAttFilePath());
			}
			
		}
		model.addAttribute("odPrdInfo",opResInf);
		
		RecipientInfo reInf = new RecipientInfo();
		reInf.setReciOdNum(odNo);
		reInf = orderManageService.selectRecipientInfo(reInf);
		model.addAttribute("reInfo",reInf);
			
		//Order 저장
		orderInfo.setOrderStatCd("0");
		
		orderManageService.updateOrderInf(orderInfo);
		
		return "order/orderComplete";
	}
	
	//주문실패페이지
	@RequestMapping(value="/order/orderError.do")
	public String orderError(@ModelAttribute("orderInfo") OrderInfo orderInfo,BindingResult result, Model model){
		return "order/orderError";
	}
	//주문코드 생성하는 메소드 - 이것도 바꿔주심 감사
	public String getOrderCode(){

		// 주문 코드 채번
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
		
		// 키를불러오면 처리해줄 부분
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
								odPrdInfo.setPrdOpColor(s.substring(3));
							}
							if ("02".equals(s.substring(0, 2))) {
								option += s + ",";
								odPrdInfo.setPrdOpSize(s.substring(3));
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
		// 세션체크
		
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
}



