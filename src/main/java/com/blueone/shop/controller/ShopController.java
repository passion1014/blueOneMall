package com.blueone.shop.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blueone.shop.domain.ShopInfo;
import com.blueone.shop.service.ShopService;



@Controller
public class ShopController {

	
	
	@Autowired
	ShopService shopService;
	
	

	
	
	
	
	@RequestMapping(value ="/main.do", method = RequestMethod.GET)
	public String read(@ModelAttribute("ShopInfo") ShopInfo shopInfo, BindingResult result, Model model){
		
		return "shop/main";
	}
	
	
	
	@RequestMapping(value="/shop/insertform.do" , method=RequestMethod.GET)
	public String insertForm(@ModelAttribute("ShopInfo") ShopInfo shopInfo, BindingResult result, Model model){
	
		
		
		return "shop/insertform";
    }
	
	@RequestMapping(value="/shop/insert.do", method=RequestMethod.POST)
	public String insert(@ModelAttribute("ShopInfo") ShopInfo shopInfo, BindingResult result, Model model){
		
		
		shopService.shopInsert(shopInfo);
		
		return "redirect:/shop/list.do";
		
	}
	
	@RequestMapping(value="/shop/list.do",method=RequestMethod.GET)
	public String getList(@ModelAttribute("shopInfo") ShopInfo shopInfo, BindingResult result, Model model){
		
		List<ShopInfo>  list = shopService.getList(shopInfo);
		
		model.addAttribute("list", list);
		
		return "shop/list";
		
		
	}
	
	
	

	
	
	
	
	
	
}
