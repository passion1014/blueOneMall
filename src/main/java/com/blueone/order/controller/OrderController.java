package com.blueone.order.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blueone.order.domain.OrderInfo;
import com.blueone.order.domain.OrderSrchInfo;
import com.blueone.order.service.IOrderManageService;
import com.blueone.user.domain.UserInfo;

@Controller
public class OrderController {
	
	@Autowired IOrderManageService orderManageService;

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
	
	//장바구니페이지
	@RequestMapping(value="/order/cartList.do", method=RequestMethod.GET)
	public String order(@ModelAttribute("orderInfo") OrderInfo orderInfo,BindingResult result, Model model){
		return "order/cartList";
	}
	
	//결제페이지
	@RequestMapping(value="/order/orderRegister.do")
	public String orderRegister(@ModelAttribute("orderInfo") OrderInfo orderInfo,BindingResult result, Model model){
		return "order/order";
	}
	
	//주문성공페이지
	@RequestMapping(value="/order/orderComplete.do")
	public String orderComplete(@ModelAttribute("orderInfo") OrderInfo orderInfo,BindingResult result, Model model){
		return "order/orderComplete";
	}
	
	//주문실패페이지
	@RequestMapping(value="/order/orderError.do")
	public String orderError(@ModelAttribute("orderInfo") OrderInfo orderInfo,BindingResult result, Model model){
		return "order/orderError";
	}
}
