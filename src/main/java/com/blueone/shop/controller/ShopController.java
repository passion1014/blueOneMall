package com.blueone.shop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
		
		//블루투스,  해드폰, 멀티미디어, 케이스, xtc, 이어폰, sale, 브랜드샵
		String[] srchCateCdArr = {"pdSList", "hpPrdList", "mmPrdList", "csPrdList", "xtPrdList", "epPrdList"};
		String[] srchCateArr = {"L1601", "L3862", "L3679", "L7451", "L2022", "L9540"};

		ProductInfo srchPrdInfo = new ProductInfo();
		srchPrdInfo.setSrchCateArr(srchCateArr);
		srchPrdInfo.setPrdDp("y");

		// 상품리스트
		List<ProductInfo> prdList = shopService.getProdListForMain(srchPrdInfo);

		// 조회한 상품리스트를 각카테고리별로 Map에 담는다.
		Map<String, List<ProductInfo>> map = new HashMap<String, List<ProductInfo>>();
		for (int idx=0; idx < srchCateCdArr.length; idx++ ) {
			List<ProductInfo> list = new ArrayList<ProductInfo>();
			
			for (ProductInfo each : prdList) {
				if (each != null && StringUtils.isEmpty(each.getCtgLargeCode())) continue;
				
				if (srchCateArr[idx].equals(each.getCtgLargeCode())) {
					list.add(each);
				}
			}
			
			map.put(srchCateCdArr[idx], list);
		}
		
		
		AdImgInfo AdImgDtl = new AdImgInfo();
		AdImgDtl = shopService.getAdImg(adImgInfo);
		
		model.addAttribute("AdImgDtl", AdImgDtl);
		model.addAllAttributes(map);
		
//		//model.addAttribute("productList", productList);
//		model.addAttribute("pdSList", btPrdList);
//		model.addAttribute("hpPrdList", hpPrdList);
//		model.addAttribute("mmPrdList", mmPrdList);
//		model.addAttribute("csPrdList", csPrdList);
//		model.addAttribute("xtPrdList", ecPrdList);
//		model.addAttribute("epPrdList", epPrdList);
				
		/*//대분류에 대한 상품출력(블루투스)
		for(ProductInfo each: productList){
			if("L4315".equals(each.getPrdCtgL()) ){
				btPrdList.add(each);
			}
		}
		
		}*/
		
	
		
//		for(ProductInfo each : btPrdList){
//			AttachFileInfo att = new AttachFileInfo();
//			att.setAttCdKey(each.getPrdCd());
//			att = attFileManageService.getAttFileInfListImg(att);
//			
//			if(att==null){
//				each.setAttFilePath("");
//			}else { 
//				
//				each.setAttFilePath(att.getAttFilePath());
//			}
//		}
//		
		return "shop/main";
	}
	
	
	
	
	
	
	

	
	
	
	
	
	
}
