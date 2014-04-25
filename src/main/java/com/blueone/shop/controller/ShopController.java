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
	@Autowired
	private IAdImgService adImgService;
	
	@RequestMapping(value ="/worklist.do", method = RequestMethod.GET)
	public String workList(@ModelAttribute("ShopInfo") ShopInfo shopInfo, BindingResult result, Model model){
		
		return "worklist";
	}
	
	
	
	@RequestMapping(value ="/", method = RequestMethod.GET)
	public String read(@ModelAttribute("adImgInfo") AdImgInfo adImgInfo, @ModelAttribute("productInfo") ProductInfo productInfo, @ModelAttribute("categoryInfo") CategoryInfo categoryInfo, BindingResult result, Model model){
		
		List<ProductInfo> productList = shopService.getImgList(productInfo);
		List<ProductInfo> btPrdList = new ArrayList<ProductInfo>();  //블루투스 상품리스트
		List<ProductInfo> hpPrdList = new ArrayList<ProductInfo>();  //해드폰 상품리스트
		List<ProductInfo> mmPrdList = new ArrayList<ProductInfo>();  //멀티미디어 상품리스트
		List<ProductInfo> csPrdList = new ArrayList<ProductInfo>();  //케이스 상품리스트
		List<ProductInfo> xtPrdList = new ArrayList<ProductInfo>();  //xtc 상품리스트
		List<ProductInfo> epPrdList = new ArrayList<ProductInfo>();  //이어폰 상품리스트
		List<ProductInfo> salePrdList = new ArrayList<ProductInfo>();  //sale 상품리스트
		List<ProductInfo> bsPrdList = new ArrayList<ProductInfo>();  //브랜드샵 상품리스트
		
		
		
		CategoryInfo largeInf = new CategoryInfo();
		
		if(categoryInfo.getCtgCode() == null || categoryInfo.getCtgCode() == ""){
			largeInf = categoryManageService.getCategoryInfDetail2(categoryInfo);			
		}else{
			largeInf = categoryManageService.getCategoryInfDetail(categoryInfo);			
		}
		
		
		
		
		
		String prdCtgL = largeInf.getCtgCode();
		
		
		//대분류에 대한 상품출력(블루투스)
		for(ProductInfo each: productList){
			if("L4315".equals(each.getPrdCtgL())){
				btPrdList.add(each);
			}
		}
		
		//이어폰
		for(ProductInfo each: productList){
			if("L1601".equals(each.getPrdCtgL())){
				epPrdList.add(each);
			}
		}
		//케이스
		for(ProductInfo each: productList){
			if("L3862".equals(each.getPrdCtgL())){
				csPrdList.add(each);
			}
		}
		//헤드폰
		for(ProductInfo each: productList){
			if("L3679".equals(each.getPrdCtgL())){
				hpPrdList.add(each);
			}
		}
		//xtc
		for(ProductInfo each: productList){
			if("L7451".equals(each.getPrdCtgL())){
				xtPrdList.add(each);
			}
		}
		//멀티미디어
		for(ProductInfo each: productList){
			if("L2022".equals(each.getPrdCtgL())){
				mmPrdList.add(each);
			}
		}
		//sale
		for(ProductInfo each: productList){
			if("L9540".equals(each.getPrdCtgL())){
				salePrdList.add(each);
			}
		}
		//brandshop
		for(ProductInfo each: productList){
			if("L7933".equals(each.getPrdCtgL())){
				bsPrdList.add(each);
			}
		}
		
	
		
		for(ProductInfo each : btPrdList){
			AttachFileInfo att = new AttachFileInfo();
			att.setAttCdKey(each.getPrdCd());
			att = attFileManageService.getAttFileInfListImg(att);
			
			if(att==null){
				each.setAttFilePath("");
			}else { 
				
				each.setAttFilePath(att.getAttFilePath());
			}
		}
		
		for(ProductInfo each : epPrdList){
			AttachFileInfo att = new AttachFileInfo();
			att.setAttCdKey(each.getPrdCd());
			att = attFileManageService.getAttFileInfListImg(att);
			
			if(att==null){
				each.setAttFilePath("");
			}else { 
				
				each.setAttFilePath(att.getAttFilePath());
			}
		}
		
		for(ProductInfo each : hpPrdList){
			AttachFileInfo att = new AttachFileInfo();
			att.setAttCdKey(each.getPrdCd());
			att = attFileManageService.getAttFileInfListImg(att);
			
			if(att==null){
				each.setAttFilePath("");
			}else { 
				
				each.setAttFilePath(att.getAttFilePath());
			}
		}
		
		for(ProductInfo each : mmPrdList){
			AttachFileInfo att = new AttachFileInfo();
			att.setAttCdKey(each.getPrdCd());
			att = attFileManageService.getAttFileInfListImg(att);
			
			if(att==null){
				each.setAttFilePath("");
			}else { 
				
				each.setAttFilePath(att.getAttFilePath());
			}
		}
		
		for(ProductInfo each : csPrdList){
			AttachFileInfo att = new AttachFileInfo();
			att.setAttCdKey(each.getPrdCd());
			att = attFileManageService.getAttFileInfListImg(att);
			
			if(att==null){
				each.setAttFilePath("");
			}else { 
				
				each.setAttFilePath(att.getAttFilePath());
			}
		}
		
		for(ProductInfo each : xtPrdList){
			AttachFileInfo att = new AttachFileInfo();
			att.setAttCdKey(each.getPrdCd());
			att = attFileManageService.getAttFileInfListImg(att);
			
			if(att==null){
				each.setAttFilePath("");
			}else { 
				
				each.setAttFilePath(att.getAttFilePath());
			}
		}
		
		for(ProductInfo each : salePrdList){
			AttachFileInfo att = new AttachFileInfo();
			att.setAttCdKey(each.getPrdCd());
			att = attFileManageService.getAttFileInfListImg(att);
			
			if(att==null){
				each.setAttFilePath("");
			}else { 
				
				each.setAttFilePath(att.getAttFilePath());
			}
		}
		
		for(ProductInfo each : bsPrdList){
			AttachFileInfo att = new AttachFileInfo();
			att.setAttCdKey(each.getPrdCd());
			att = attFileManageService.getAttFileInfListImg(att);
			
			if(att==null){
				each.setAttFilePath("");
			}else { 
				
				each.setAttFilePath(att.getAttFilePath());
			}
		}
		
		AdImgInfo adDtl = new AdImgInfo();
		
		adDtl = adImgService.getAdImgDtl(adImgInfo);
		
		model.addAttribute("adDtl", adDtl);
		model.addAttribute("productList", productList);
		model.addAttribute("pdSList", btPrdList);
		model.addAttribute("hpPrdList", hpPrdList);
		model.addAttribute("mmPrdList", mmPrdList);
		model.addAttribute("csPrdList", csPrdList);
		model.addAttribute("xtPrdList", xtPrdList);
		model.addAttribute("epPrdList", epPrdList);
		
		return "shop/main";
	}
	
	
	
	
	
	
	

	
	
	
	
	
	
}
