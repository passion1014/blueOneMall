package com.blueone.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blueone.admin.domain.AdminInfo;
import com.blueone.admin.service.IGoodsService;

@Controller
@RequestMapping(value = "/admin")
public class GoodsController {
	
	
	private IGoodsService goodService;
	
	@RequestMapping(value="/goods.do", method= RequestMethod.GET)
	public String goodsList(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result, Model model){
		
		   return "admin/goods/goodsList";
		
		
	}

}
