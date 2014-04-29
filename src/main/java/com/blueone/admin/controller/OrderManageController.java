package com.blueone.admin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.blueone.admin.domain.AdminInfo;
import com.blueone.admin.service.IOrderService;

@Controller
@RequestMapping(value = "/admin")
public class OrderManageController {
	private IOrderService orderService;
	
	@RequestMapping(value="/orderList.do", method= RequestMethod.GET)
	public String orderList(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result, Model model,HttpSession session){
		AdminInfo adminSession = (AdminInfo)session.getAttribute("adminSession");
		
		if(adminSession == null){
		return "redirect:adminLogin.do";
		}
		
		return "admin/order/orderList";
	}
}
