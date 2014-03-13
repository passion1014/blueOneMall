package com.blueone.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blueone.admin.domain.AdminInfo;
import com.blueone.admin.service.IOrderService;

@Controller
@RequestMapping(value = "/admin")
public class OrderController {

	
	
	private IOrderService orderService;
	
	@RequestMapping(value="/order.do", method= RequestMethod.GET)
	public String orderList(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result, Model model){
		
		   return "admin/order/orderList";
		
		
	}
}
