package com.blueone.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.blueone.customer.domain.CustomerInfo;
import com.blueone.customer.domain.CustomerSrchInfo;
import com.blueone.customer.service.ICustomerManageService;

@SessionAttributes("custLoginInfo")
@Controller
@RequestMapping(value = "/cust")
public class CustomerController {

	@Autowired ICustomerManageService customerManageService;

	@RequestMapping(value="/getCustomerInfo.do", method= RequestMethod.GET)
	public String getCustomerInfo(@ModelAttribute("customerSrchInfo") CustomerSrchInfo customerSrchInfo, BindingResult result, Model model){
		String viewName = "cust/custDetail";
		
		CustomerInfo customerInfo = customerManageService.getCustomerInfo(customerSrchInfo);
		
		model.addAttribute("customerInfo", customerInfo);
		
		return viewName;
	}

	
	/*
	@RequestMapping(value="/setSessionTest.do", method= RequestMethod.GET)
	public ModelAndView setSessionTest(AdminInfo adminInfo, BindingResult result, Model model){
		
		CustomerInfo custInf = new CustomerInfo();
		custInf.setCustNm("Daniel Lee");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("custLoginInfo", custInf);	// login Info
		mav.setViewName("/cust/getSessionTest.do");

		return mav;
	}
	
	@RequestMapping(value="/getSessionTest.do", method= RequestMethod.GET)
	public ModelAndView getSessionTest(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result, Model model, HttpSession session){
		CustomerInfo custInf = (CustomerInfo)session.getAttribute("custLoginInfo");
		System.out.println("custInf=" + custInf.getCustNm());
		return null;
	}

	@RequestMapping(value="/getSessionTest2.do", method= RequestMethod.GET)
	public ModelAndView getSessionTest2(@ModelAttribute("AdminInfo") AdminInfo adminInfo, @ModelAttribute("custLoginInfo") CustomerInfo customerInfo, BindingResult result, Model model){
		System.out.println("custInf2=" + customerInfo.getCustNm());
		return null;
	}
	*/
}
