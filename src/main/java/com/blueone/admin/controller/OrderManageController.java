package com.blueone.admin.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.blueone.admin.domain.AdminInfo;
import com.blueone.admin.domain.ConfigInfo;
import com.blueone.admin.service.IAdminManageService;
import com.blueone.admin.service.IOrderService;
import com.blueone.common.domain.AttachFileInfo;
import com.blueone.common.service.IAttachFileManageService;
import com.blueone.customer.domain.CustomerInfo;
import com.blueone.customer.domain.RecipientInfo;
import com.blueone.customer.service.ICustomerManageService;
import com.blueone.order.domain.OrderInfo;
import com.blueone.order.domain.OrderProductInfo;
import com.blueone.order.domain.OrderSrchInfo;
import com.blueone.order.service.IOrderManageService;
import com.blueone.product.domain.ProductInfo;
import com.blueone.product.service.IProductManageService;

@Controller
@RequestMapping(value = "/admin")
public class OrderManageController {
	@Autowired IOrderManageService orderService;
	@Autowired IOrderManageService orderManageService;
	@Autowired private IProductManageService productManageService;
	@Autowired private IAttachFileManageService attFileManageService;
	@Autowired ICustomerManageService customerManageService;
	@Autowired IAdminManageService adminManageService;
	
	@RequestMapping(value="/orderList.do", method= RequestMethod.GET)
	public String orderList(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result, Model model,HttpSession session){

		AdminInfo adminSession = (AdminInfo) session.getAttribute("adminSession");

		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}

		
		OrderInfo os = new OrderInfo();
		List<OrderInfo> odList =orderManageService.getOrderInfoListByPeriod(os);
		
		model.addAttribute("odList",odList);
		model.addAttribute("sh","all");
		
		
		return "admin/order/orderList";
	}
	
	//관리 페이지
	@RequestMapping(value="/orderManagement.do", method= RequestMethod.GET)
	public String orderManagement(@ModelAttribute("AdminInfo") AdminInfo adminInfo,OrderInfo orderInfo,CustomerInfo customerInfo, BindingResult result, Model model,HttpSession session){

		AdminInfo adminSession = (AdminInfo) session.getAttribute("adminSession");

		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}

		CustomerInfo cus=customerManageService.getCustomerInfo2(customerInfo);
		orderInfo.setCustomerInfo(cus);
		List<OrderInfo> odList = orderService.selectOrderInfoList(orderInfo);
		model.addAttribute("odInfo",odList.get(0));
		model.addAttribute("cus",cus);
		
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
		
		//배송비관련 정보
		ConfigInfo resConfigInfo = adminManageService.selectConfigInf();
		model.addAttribute("config", resConfigInfo);
		return "admin/order/orderManagement";
	}
	
	@RequestMapping(value="orderManagementProc.do", method=RequestMethod.POST)
	public String orderManagementProc(@ModelAttribute("orderInfo") OrderInfo orderInfo,BindingResult result, Model model,HttpSession session) {
		
		

		orderService.updateOrderInf(orderInfo);
		CustomerInfo cust = orderInfo.getCustomerInfo();
		
	
		return "redirect:orderManagement.do?orderNo="+orderInfo.getOrderNo()+"&custId="+cust.getCustId();
	}

	//주문검색
	@RequestMapping(value="/orderSearchList.do", method= RequestMethod.GET)
	public String orderSearchList(@ModelAttribute("AdminInfo") AdminInfo adminInfo,OrderSrchInfo orderInfo, BindingResult result, Model model,HttpSession session){

		AdminInfo adminSession = (AdminInfo) session.getAttribute("adminSession");

		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}

		
		List<OrderInfo> odList= orderManageService.getOrderInfoListBySchInfo(orderInfo);
		model.addAttribute("odList",odList);
		model.addAttribute("sh","all");
		
		
		
		return "admin/order/orderList";
	}
	
	//신청중
	@RequestMapping(value="/orderingList.do", method= RequestMethod.GET)
	public String orderingList(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result, Model model,HttpSession session){

		AdminInfo adminSession = (AdminInfo) session.getAttribute("adminSession");

		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}

		
		OrderInfo os = new OrderInfo();
		os.setOrderStatCd("01");
		List<OrderInfo> odList =orderManageService.getOrderInfoListByPeriod(os);
		
		model.addAttribute("odList",odList);
		model.addAttribute("sh","ordering");
		
		
		
		return "admin/order/orderList";
	}
	
	//주문완료
	@RequestMapping(value="/orderCompleteList.do", method= RequestMethod.GET)
	public String orderCompleteList(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result, Model model,HttpSession session){

		AdminInfo adminSession = (AdminInfo) session.getAttribute("adminSession");

		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}

		
		OrderInfo os = new OrderInfo();
		os.setOrderStatCd("02");
		List<OrderInfo> odList =orderManageService.getOrderInfoListByPeriod(os);
		
		model.addAttribute("odList",odList);
		model.addAttribute("sh","orderComplete");
		
		
		
		return "admin/order/orderList";
	}
	
	//배송중
	@RequestMapping(value="/orderTransferingList.do", method= RequestMethod.GET)
	public String orderTransferingList(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result, Model model,HttpSession session){

		AdminInfo adminSession = (AdminInfo) session.getAttribute("adminSession");

		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}

		
		OrderInfo os = new OrderInfo();
		os.setOrderStatCd("04");
		List<OrderInfo> odList =orderManageService.getOrderInfoListByPeriod(os);
		
		model.addAttribute("odList",odList);
		model.addAttribute("sh","Transfering");
		
		
		
		return "admin/order/orderList";
	}
	
	// 배송준비중
	@RequestMapping(value = "/orderTransferReadyList.do", method = RequestMethod.GET)
	public String orderTransferReadyList(@ModelAttribute("AdminInfo") AdminInfo adminInfo,BindingResult result, Model model, HttpSession session) {
		
		  AdminInfo adminSession = (AdminInfo) session.getAttribute("adminSession");
		  
		  if (adminSession == null) { return "redirect:adminLogin.do"; }
		

		OrderInfo os = new OrderInfo();
		os.setOrderStatCd("03");
		List<OrderInfo> odList = orderManageService
				.getOrderInfoListByPeriod(os);

		model.addAttribute("odList", odList);
		model.addAttribute("sh", "TransferReady");

		return "admin/order/orderList";
	}
	// 주문취소신청
	@RequestMapping(value = "/orderCancelList.do", method = RequestMethod.GET)
	public String orderCancelList(@ModelAttribute("AdminInfo") AdminInfo adminInfo,BindingResult result, Model model, HttpSession session) {
		
		  AdminInfo adminSession = (AdminInfo) session.getAttribute("adminSession");
		  
		  if (adminSession == null) { return "redirect:adminLogin.do"; }
		

		OrderInfo os = new OrderInfo();
		os.setOrderStatCd("07");
		List<OrderInfo> odList = orderManageService.getOrderInfoListByPeriod(os);

		model.addAttribute("odList", odList);
		model.addAttribute("sh", "cancel");

		return "admin/order/orderList";
	}
	//주문취소신청완료
	@RequestMapping(value = "/orderCancelCompleteList.do", method = RequestMethod.GET)
	public String orderCancelCompleteList(@ModelAttribute("AdminInfo") AdminInfo adminInfo,BindingResult result, Model model, HttpSession session) {
		
		  AdminInfo adminSession = (AdminInfo) session.getAttribute("adminSession");
		  
		  if (adminSession == null) { return "redirect:adminLogin.do"; }
		

		OrderInfo os = new OrderInfo();
		os.setOrderStatCd("08");
		List<OrderInfo> odList = orderManageService
				.getOrderInfoListByPeriod(os);

		model.addAttribute("odList", odList);
		model.addAttribute("sh", "cancelComplete");

		return "admin/order/orderList";
	}
	// 반품신청
	@RequestMapping(value = "/orderTakeBackList.do", method = RequestMethod.GET)
	public String orderTakeBackList(@ModelAttribute("AdminInfo") AdminInfo adminInfo,BindingResult result, Model model, HttpSession session) {
		
		  AdminInfo adminSession = (AdminInfo) session.getAttribute("adminSession");
		  
		  if (adminSession == null) { return "redirect:adminLogin.do"; }
		

		OrderInfo os = new OrderInfo();
		os.setOrderStatCd("09");
		List<OrderInfo> odList = orderManageService
				.getOrderInfoListByPeriod(os);

		model.addAttribute("odList", odList);
		model.addAttribute("sh", "return");

		return "admin/order/orderList";
	}
	
	// 반품신청완료
	@RequestMapping(value = "/orderTakeBackCompleteList.do", method = RequestMethod.GET)
	public String orderTakeBackCompleteList(@ModelAttribute("AdminInfo") AdminInfo adminInfo,BindingResult result, Model model, HttpSession session) {
		
		  AdminInfo adminSession = (AdminInfo) session.getAttribute("adminSession");
		  
		  if (adminSession == null) { return "redirect:adminLogin.do"; }
		

		OrderInfo os = new OrderInfo();
		os.setOrderStatCd("10");
		List<OrderInfo> odList = orderManageService
				.getOrderInfoListByPeriod(os);

		model.addAttribute("odList", odList);
		model.addAttribute("sh", "retrunComplete");

		return "admin/order/orderList";
	}
	// 월별 거래내역조회
	@RequestMapping(value = "/monthTradeList.do", method = RequestMethod.GET)
	public String monthTradeList(@ModelAttribute("AdminInfo") AdminInfo adminInfo,BindingResult result, Model model, HttpSession session) {
		
		 AdminInfo adminSession = (AdminInfo) session .getAttribute("adminSession");
		  
		  if (adminSession == null) { return "redirect:adminLogin.do"; }
		 

		
		model.addAttribute("sh", "month");

		return "admin/order/orderTrade";
	}

	// 상품별 거래내역조회
	@RequestMapping(value = "/productTradeList.do", method = RequestMethod.GET)
	public String productTradeList(@ModelAttribute("AdminInfo") AdminInfo adminInfo,BindingResult result, Model model, HttpSession session) {
		/*
		 * AdminInfo adminSession = (AdminInfo) session
		 * .getAttribute("adminSession");
		 * 
		 * if (adminSession == null) { return "redirect:adminLogin.do"; }
		 */

		
		model.addAttribute("sh", "product");

		return "admin/order/orderTrade";
	}

			
		

		
		
			
		
}
