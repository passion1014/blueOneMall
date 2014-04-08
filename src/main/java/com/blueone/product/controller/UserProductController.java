package com.blueone.product.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blueone.product.domain.ProductInfo;
import com.blueone.product.service.IUserProductService;

@Controller
public class UserProductController {

	
	@Autowired
	private IUserProductService iUserProductService;
	
	@RequestMapping(value="/product/productList.do")
	public String productList(@ModelAttribute("productInfo") ProductInfo productInfo, BindingResult result, Model model){
		
		List<ProductInfo> list = null;
		List<Object> list2 = new ArrayList<Object>();
		list = iUserProductService.shopProductInfList(productInfo);
		list2= iUserProductService.shopProductByPriceList(productInfo);
		model.addAttribute("list", list);
		model.addAttribute("price", list2);
		
		return "product/productList";
	}
	
	@RequestMapping(value="/product/productView.do")
	public String productView(@ModelAttribute("productInfo") ProductInfo productInfo, BindingResult result, Model model){
		
		return "product/productView";
	}
}