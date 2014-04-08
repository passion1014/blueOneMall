package com.blueone.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blueone.product.service.IUserProductService;

@Controller
public class UserProductController {

	
	@Autowired
	private IUserProductService iUserProductService;
	
	@RequestMapping(value="/product/productList.do")
	public String productList(){
		return "product/productList";
	}
}
