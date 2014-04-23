package com.blueone.shop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blueone.admin.domain.AdImgInfo;
import com.blueone.admin.service.IAdImgService;
import com.blueone.category.domain.CategoryInfo;
import com.blueone.category.service.ICategoryManageService;
import com.blueone.common.domain.AttachFileInfo;
import com.blueone.common.service.IAttachFileManageService;
import com.blueone.product.domain.ProductInfo;
import com.blueone.product.service.IProductManageService;
import com.blueone.shop.domain.ShopInfo;
import com.blueone.shop.service.IShopService;



@Controller
public class ShopController {

	
	
	@Autowired
	private IShopService shopService;
	@Autowired
	private IProductManageService productManageService;
	@Autowired
	private ICategoryManageService categoryManageService;
	@Autowired 
	private IAttachFileManageService attFileManageService;
	
	@RequestMapping(value ="/worklist.do", method = RequestMethod.GET)
	public String workList(@ModelAttribute("ShopInfo") ShopInfo shopInfo, BindingResult result, Model model){
		
		return "worklist";
	}
	
	
	
	@RequestMapping(value ="/", method = RequestMethod.GET)
	public String read(@ModelAttribute("adImgInfo") AdImgInfo adImgInfo, @ModelAttribute("productInfo") ProductInfo productInfo, @ModelAttribute("categoryInfo") CategoryInfo categoryInfo, BindingResult result, Model model){
		
		
		List<ProductInfo> productList = shopService.getImgList(productInfo);
		List<ProductInfo> pdSList = new ArrayList<ProductInfo>();
		
		
		
		CategoryInfo largeInf = new CategoryInfo();
		
		if(categoryInfo.getCtgCode() == null || categoryInfo.getCtgCode() == ""){
			largeInf = categoryManageService.getCategoryInfDetail2(categoryInfo);			
		}else{
			largeInf = categoryManageService.getCategoryInfDetail(categoryInfo);			
		}
		
		
		
		
		
		String prdCtgL = largeInf.getCtgCode();
		
		
		//대분류에 대한 상품출력
		for(ProductInfo each: productList){
			if(prdCtgL.equals(each.getPrdCtgL())){
				pdSList.add(each);
			}
		}
		
	
		
		for(ProductInfo each : pdSList){
			AttachFileInfo att = new AttachFileInfo();
			att.setAttCdKey(each.getPrdCd());
			att = attFileManageService.getAttFileInfListImg(att);
			
			if(att==null){
				each.setAttFilePath("");
			}else { 
				
				each.setAttFilePath(att.getAttFilePath());
			}
			
		}
		
		
		model.addAttribute("pdSList", pdSList);
		model.addAttribute("productList", productList);
		
		return "shop/main";
	}
	
	
	
	
	
	
	

	
	
	
	
	
	
}
