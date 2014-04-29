package com.blueone.order.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.blueone.customer.domain.CustomerContactInfo;
import com.blueone.customer.domain.CustomerInfo;
import com.blueone.customer.domain.RecipientInfo;
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
		
		if(orderProductInfo.getPrdOpColor()!=null){
			value+="01="+orderProductInfo.getPrdOpColor()+",";
		}
		if(orderProductInfo.getPrdOpSize()!=null){
			value+="02="+orderProductInfo.getPrdOpSize()+",";
		}
		if(orderProductInfo.getPrdSmallImg()!=null){
			value+="cn="+orderProductInfo.getBuyCnt();
			value+="no="+getOrderCode();
			Cookie cookie =cki.createCookie("BOM_"+orderProductInfo.getPrdCd(),value,50000);
			response.addCookie(cookie);//
	
		
		}
		
		
		
		/*
		if(pass.equals("y")){
			return "redirect:orderRegister.do";
		}else{
			return "redirect:cartListView.do";
		}
		*/
		
		return "redirect:cartListView.do";
			
		
	}
	
	//장바구니 보여줌
	@RequestMapping(value="/order/cartListView.do")
	public String orderView(@ModelAttribute("orderProductInfo") OrderProductInfo orderProductInfo,BindingResult result, Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{

		//user session이 있으면 넣을 부분
		CustomerInfo cust = new CustomerInfo();
		cust.setCustId("id1");
		
		
		CookieBox cki = new CookieBox(request);
		List<OrderProductInfo> ord = getCartList(cki);
		
		for(OrderProductInfo each : ord){
			
			//OrderProduct저장
			each.setModiUser(cust.getCustId());//user ID 입력
			orderManageService.registOrderProductInfo(each);
			
			//Order 저장
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setOrderNo(each.getOrderNo());
			orderInfo.setOrderStatCd("01");
			orderInfo.setCustomerInfo(cust);
			orderInfo.setModifyUserId(cust.getCustId());
			orderManageService.registOrderInfo(orderInfo);
		}
		
		model.addAttribute("odPrdInfo",ord);
		
		return "order/cartList";
	}
	
	//장바구니 상품 삭제
	@RequestMapping(value="/order/deleteCartList.do", method = RequestMethod.GET)
	public String deleteCartList(@ModelAttribute("orderProductInfo") OrderProductInfo orderProductInfo,BindingResult result, Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		CookieBox cki = new CookieBox(request);
		
		Cookie cookie =cki.createCookie("BOM_"+orderProductInfo.getPrdCd(),"",-1);
		response.addCookie(cookie);
		

		return "redirect:cartListView.do";
	}
			
	//결제페이지
	@RequestMapping(value="/order/orderRegister.do")
	public String orderRegister(@ModelAttribute("orderInfo") OrderInfo orderInfo,BindingResult result, Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		// 주문번호
		String orderNum = "";
		
		StringTokenizer st = new StringTokenizer(orderInfo.getOrd_unit_chk(),",");
		
		List<OrderProductInfo> oPrdList = new ArrayList<OrderProductInfo>();
		while(st.hasMoreTokens()){ // 반활할 토큰이 있는가? true/false;
			  OrderProductInfo ordPrd = new OrderProductInfo();
			  ordPrd.setOrderNo(st.nextToken());
			  ordPrd=orderManageService.selectOrderPrdInfo(ordPrd);
			  oPrdList.add(ordPrd);
			  
		}
	
		if(orderInfo.getOrderNo() !=null && !orderInfo.getOrderNo().isEmpty()){
			  OrderProductInfo ordPrd = new OrderProductInfo();
			  ordPrd.setOrderNo(orderInfo.getOrderNo());
			  ordPrd=orderManageService.selectOrderPrdInfo(ordPrd);
			  oPrdList.add(ordPrd);
				
		}

		model.addAttribute("odPrdInfo",oPrdList);
		
		//세션에 잇는 정보를 셋팅
		CustomerInfo custom = new CustomerInfo();
		custom.setCustNm("dana");
		custom.setTelNo1("02");
		custom.setTelNo2("123");
		custom.setTelNo3("4567");
		custom.setHpNo1("010");
		custom.setHpNo2("1231");
		custom.setHpNo3("4567");
		model.addAttribute("cus",custom);
		
		
		
		return "order/order";
	}
	
	//결제페이지-처리
	@RequestMapping(value="/order/orderRegisterProc.do")
	public String orderRegisteProc(@ModelAttribute("orderInfo") OrderInfo orderInfo,BindingResult result, Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		//주문번호
		String orderNum="";
		
		
		
		//받는사람 저장 - 주문자와 받는 사람이 다를 경우 저장을 해야될것같아서 일단 만들엇음
		RecipientInfo reInf= orderInfo.getReciInfo();
		String phone = reInf.getPhone1()+reInf.getPhone2()+reInf.getPhone3();
		reInf.setReciPh(phone);
		String mobile = reInf.getMobile1()+reInf.getMobile2()+reInf.getMobile3();
		reInf.setReciMb(mobile);
		reInf.setReciOdNum(orderNum);
		String address=reInf.getZipCd1()+" "+reInf.getAdd1()+" "+reInf.getAdd2();
		reInf.setReciAdd(address);
		orderInfo.setReciInfo(reInf);
		orderManageService.registRecipientInfo(reInf);
		
		//주문 저장
		orderInfo.setOrderNo(orderNum);
		orderInfo.setOrderStatCd("01");
		orderManageService.registOrderInfo(orderInfo);
		
	return "redirect:orderComplete.do?orderNo="+orderNum;
}


	
	//주문성공페이지
	@RequestMapping(value="/order/orderComplete.do")
	public String orderComplete(@ModelAttribute("orderInfo") OrderInfo orderInfo,BindingResult result, Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		//결제상품 보여주기
		String odNo=orderInfo.getOrderNo();
		
		
		OrderProductInfo opResInf = new OrderProductInfo();
		opResInf.setOrderNo(odNo);
		opResInf = orderManageService.selectOrderPrdInfo(opResInf);
		
		String prdCd = opResInf.getPrdCd();
		ProductInfo prInf = new ProductInfo();
		prInf.setPrdCd(prdCd);
		prInf=productManageService.getProductInfDetail(prInf);
		
		//상품 이름
		opResInf.setPrdNm(prInf.getPrdNm());
		
		//옵션
		String option=opResInf.getPrdOption();
		StringTokenizer st = new StringTokenizer(option,",");
		while(st.hasMoreElements()) {
				
				String s = st.nextToken();
				
				if("01".equals(s.substring(0, 2))){
					option+=s+",";
					opResInf.setPrdOpColor(s.substring(3));
				}
				if("02".equals(s.substring(0, 2))){
					option+=s+",";
					opResInf.setPrdOpSize(s.substring(3));
				}
		
		}
		
		//수량 및 금액
		opResInf.setSellPrice(new BigDecimal(prInf.getPrdSellPrc()));
		BigDecimal total = new BigDecimal(prInf.getPrdSellPrc()) ;
		total=total.multiply(new BigDecimal(opResInf.getBuyCnt()));
		opResInf.setTotalPrice(total);
		
		//사진
		AttachFileInfo att = new AttachFileInfo();
		att.setAttCdKey(prInf.getPrdCd());
		att.setAttImgType("01");
		att = attFileManageService.getAttFileInfListImg(att);
		if(att==null){
			opResInf.setPrdSmallImg("");
		}else { 
			
			opResInf.setPrdSmallImg(att.getAttFilePath());
		}
		
		model.addAttribute("odPrdInfo",opResInf);
		
		RecipientInfo reInf = new RecipientInfo();
		reInf.setReciOdNum(odNo);
		reInf = orderManageService.selectRecipientInfo(reInf);
		model.addAttribute("reInfo",reInf);
			
	
		
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

		List<String> ckKey = cki.getKey();//키를 불러옴, 우리꺼 빼고 다불러옴,쿠키가 안들어가서 불러올수가읎음
		List<OrderProductInfo> ord = new ArrayList<OrderProductInfo>();
		
		// 키를불러오면 처리해줄 부분
		for(String each : ckKey){
			
			
			
			if("BOM".equals(each.substring(0, 3))){
			
				OrderProductInfo odPrdInfo = new OrderProductInfo();
				
				String key = each.substring(4);
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

				if (vl.equals(""));
				else ord.add(odPrdInfo);
			}//if end
		}//for-end
		
		
		
		
		return ord;
	}

}


}
