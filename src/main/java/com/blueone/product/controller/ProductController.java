package com.blueone.product.controller;

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

import com.blueone.admin.domain.AdminInfo;
import com.blueone.product.domain.ProductInfo;
import com.blueone.product.domain.SearchProdInfo;
import com.blueone.product.service.IProductManageService;

@Controller
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	IProductManageService productManageService;
	
	/*
	 * 관리자 물품 리스트
	 */
	@RequestMapping(value = "/admin/productList.do")
	public String productList(@ModelAttribute("ProductInfo") ProductInfo productInfo, BindingResult result, Model model) {
				
		return "admin/product/productList";
	}
	
	/*
	 * 관리자 물품 리스트
	 */
	@RequestMapping(value = "/admin/productRegister.do")
	public String productRegister(@ModelAttribute("ProductInfo") ProductInfo productInfo, BindingResult result, Model model) {
				
		return "admin/product/productRegister";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/registProductInfo.do")
	public String registAdminInfo(@ModelAttribute("ProductInfo") ProductInfo productInfo, BindingResult result, Model model) {
		productManageService.registProductInfo(productInfo);
		
		return "product/result";
	}

	@RequestMapping(value = "/searchProductList.do", method = RequestMethod.GET)
	public String getAdminInfoList(@ModelAttribute("searchProdInfo") SearchProdInfo searchProdInfo, BindingResult result, Model model) {

		List<ProductInfo> list = productManageService.getProductList(searchProdInfo);
	    model.addAttribute("list", list);
	    
		return "product/result";
	}
	
}
