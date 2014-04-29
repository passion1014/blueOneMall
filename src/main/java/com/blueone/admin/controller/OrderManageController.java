package com.blueone.admin.controller;

import java.util.List;

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
import com.blueone.admin.service.IOrderService;
import com.blueone.order.domain.OrderInfo;
import com.blueone.order.domain.OrderSrchInfo;
import com.blueone.order.service.IOrderManageService;

@Controller
@RequestMapping(value = "/admin")
public class OrderManageController {
	private IOrderService orderService;
	@Autowired IOrderManageService orderManageService;
	
	@RequestMapping(value="/orderList.do", method= RequestMethod.GET)
	public String orderList(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result, Model model,HttpSession session){
/*
		AdminInfo adminSession = (AdminInfo) session
				.getAttribute("adminSession");

		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}
*/
		
		OrderSrchInfo os = new OrderSrchInfo();
		os.setSrchStdDt("2014-01-01");
		os.setSrchEdDt("2100-04-04");
		List<OrderInfo> odList =orderManageService.getOrderInfoListByPeriod(os);
		
		model.addAttribute("odList",odList);
		
		
		return "admin/order/orderList";
	}
}
