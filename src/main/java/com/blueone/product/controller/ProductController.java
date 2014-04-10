package com.blueone.product.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blueone.admin.domain.AdminInfo;
import com.blueone.category.domain.CategoryInfo;
import com.blueone.category.service.ICategoryManageService;
import com.blueone.common.domain.AttachFileInfo;
import com.blueone.product.domain.ProductInfo;
import com.blueone.product.domain.SearchProdInfo;
import com.blueone.product.service.IProductManageService;

@Controller
@SessionAttributes("adminSession")
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired IProductManageService productManageService;
	@Autowired ICategoryManageService categoryManageService;
	
	
	/*
	 * 관리자 물품 리스트
	 */
	@RequestMapping(value = "/admin/productList.do")
	public String productList(@ModelAttribute("ProductInfo") SearchProdInfo srchProdInfo, BindingResult result, Model model,HttpSession session) {
		/*
		AdminInfo adminSession = (AdminInfo)session.getAttribute("adminSession");		
		if(adminSession==null){
		return "redirect:adminLogin.do";
		}
		
		*/
		
		List<ProductInfo> list = productManageService.getProductInfList(srchProdInfo);
	    
		model.addAttribute("list", list);
		
		
		
		return "admin/product/productList";
	}
	
	/*
	 * 상품등록 폼 구성
	 */
	@RequestMapping(value = "/admin/productRegister.do")
	public String productRegister(@ModelAttribute("ProductInfo")ProductInfo productInfo, BindingResult result, Model model,HttpSession session) {
		/*
		// -----------------------------------------------------------------
		// 1. 세션정보를 확인해서 세션정보가 없을 경우 로그인 페이지로 이동한다.
		// -----------------------------------------------------------------
		AdminInfo adminSession = (AdminInfo)session.getAttribute("adminSession");
		if(adminSession == null){
			return "redirect:adminLogin.do";
		}
*/
	
		// -----------------------------------------------------------------
		// 2. 상품등록을 위한 카테고리(대분류) 리스트를 넘긴다.
		// -----------------------------------------------------------------
		CategoryInfo categoryInfo = new CategoryInfo();
		List<CategoryInfo> list = categoryManageService.getCategoryInfList(categoryInfo);
		
		// 대분류로만 필터링한다.
		List<CategoryInfo> rstList = new ArrayList<CategoryInfo>();
		
		//중분류만 필터링
		List<CategoryInfo> rstList2 = new ArrayList<CategoryInfo>();
		
		for (CategoryInfo each : list) {
			if ("01".equals(each.getCtgCodeType())) {
				rstList.add(each);
			
			}
			
		}
		

		
		// 결과값 셋팅
		model.addAttribute("ctgLList", rstList);
		
					
		return "admin/product/ProductRegister";
	}
	
	/**
	 * 상품등록처리 
	 */
	@RequestMapping(value = "/admin/productRegisterProc.do", method = RequestMethod.POST)
	public String productRegisterProc(@ModelAttribute("productInfo") ProductInfo productInfo, BindingResult result, Model model,String ctgname,RedirectAttributes redirectAttributes) {

		// 상품 코드 채번
		int code= (int)(Math.random()*10000)+1;
		productInfo.setPrdCd("P"+code);
		
		//상품 이미지 등록
		AttachFileInfo contImg = new AttachFileInfo();
		contImg.setUploadFile(productInfo.getProListImg());
		
		// 상픔등록
		productManageService.registProductInfo(productInfo);
		
		

		redirectAttributes.addFlashAttribute("reloadVar", "yes");
		
		return "redirect:productRegister.do";
		
	}


	@RequestMapping(value = "/registProductInfo.do")
	public String registAdminInfo(@ModelAttribute("ProductInfo") ProductInfo productInfo, BindingResult result, Model model) {
		productManageService.registProductInfo(productInfo);
		
		return "product/result";
	}

	@RequestMapping(value = "/searchProductList.do", method = RequestMethod.GET)
	public String getAdminInfoList(@ModelAttribute("searchProdInfo") SearchProdInfo searchProdInfo, BindingResult result, Model model) {

		List<ProductInfo> list = productManageService.getProductSearchList(searchProdInfo);
	    model.addAttribute("list", list);
	    
		return "product/result";
	}
	
	
	/**
	 * 상품 관리 수정폼
	 */		
	@RequestMapping(value="/admin/productManagement.do", method=RequestMethod.GET)
	public String productManage(@ModelAttribute("productInfo") ProductInfo productInfo, BindingResult result, Model model, String pCd){
		
		productInfo.setPrdCd(pCd);
		
		productInfo = productManageService.getProductInfDetail(productInfo);
		
		// -----------------------------------------------------------------
		// 2. 상품수정을 위한 카테고리(대분류) 리스트를 넘긴다.
		// -----------------------------------------------------------------
		CategoryInfo categoryInfo = new CategoryInfo();
		
		//카테고리항목 다 불러오기
		List<CategoryInfo> list = categoryManageService.getCategoryInfList(categoryInfo);
		
		
		// 대분류로만 필터링한다.
		List<CategoryInfo> rstList = new ArrayList<CategoryInfo>();
		//중분류만 필터링
		List<CategoryInfo> rstList2 = new ArrayList<CategoryInfo>();
		//소분류만 필터링
		List<CategoryInfo> rstList3 = new ArrayList<CategoryInfo>();

		//필터링 하는중
		for (CategoryInfo each : list) {
			if ("01".equals(each.getCtgCodeType())) {
				rstList.add(each);
				
			}
			if("02".equals(each.getCtgCodeType())){
				rstList2.add(each);

			}
			if("03".equals(each.getCtgCodeType())){
				rstList3.add(each);
			}
			
		}

		//해당 대분류코드빼고 다른 중분류 코드 제거
		String ctgLargeCode = productInfo.getPrdCtgL();
		
		for(int idx=0; idx < rstList2.size(); idx++) {
			CategoryInfo each = rstList2.get(idx);
			if (!ctgLargeCode.equals(each.getCtgPCode())) {
				rstList2.remove(idx);
				idx--;
			}
			
		}
		



		//해당 중분류코드빼고 다른 소분류 코드 제거
		String ctgMiddleCode = productInfo.getPrdCtgM();
		
		for(int idx=0; idx < rstList3.size(); idx++) {
			CategoryInfo each = rstList3.get(idx);
			if (!ctgMiddleCode.equals(each.getCtgPCode())) {
				rstList3.remove(idx);
				idx--;
			}
		}
		
		
		
		model.addAttribute("prdInfo", productInfo);
		
		// 결과값 셋팅
		model.addAttribute("ctgLList", rstList);
		model.addAttribute("ctgMList", rstList2);
		model.addAttribute("ctgSList", rstList3);

		
		return "admin/product/productManagement";
	}
	
	/**
	 * 상품 수정처리
	 */
	@RequestMapping(value = "/admin/manageProductInfProc.do", method = RequestMethod.POST)
	public String manageProductInfProc(@ModelAttribute("productInfo") ProductInfo productInfo, BindingResult result, Model model) {
		productManageService.manageProductInf(productInfo);
		
		return "redirect:productList.do";
	}
	
	/**
	 * 상품 삭제
	 */
	@RequestMapping(value = "/admin/deleteProductInf.do", method = RequestMethod.GET)
	public String deleteProductInfo(@ModelAttribute("productInfo") ProductInfo productInfo, BindingResult result, Model model) {
		productManageService.deleteProductInf(productInfo);
		
		return "redirect:productList.do";
	}
	
	/**
	 * 상품 다중 삭제
	 */
	@RequestMapping(value = "/admin/deleteProductsInf.do", method = RequestMethod.POST)
	public String deleteProductsInfo(@ModelAttribute("productInfo") ProductInfo productInfo, BindingResult result, Model model) {
		
		
		
		StringTokenizer st = new StringTokenizer(productInfo.getUnit_chk(),",");

		while(st.hasMoreTokens()){ // 반활할 토큰이 있는가? true/false;
			   productInfo.setPrdCd(st.nextToken());
			   productManageService.deleteProductInf(productInfo);
				
			  
		}
			
		
		return "redirect:productList.do";
	}
	/**
	 * 이미지 찾기 폼
	 */
	@RequestMapping(value = "/admin/searchProductImg.do", method = RequestMethod.POST)
	public String searchProductImg(@ModelAttribute("productInfo") ProductInfo productInfo, BindingResult result, Model model) {
		
		return "redirect:productList.do";
	}
	/**
	 * 이미지 찾기 처리
	 */
	@RequestMapping(value = "/admin/searchProductImgProc.do", method = RequestMethod.POST)
	public String searchProductImgProc(@ModelAttribute("productInfo") ProductInfo productInfo, BindingResult result, Model model) {
		
		return "redirect:productList.do";
	}
	

/*
	private List<CategoryInfo> getCategoryListByTypeCd(CategoryInfo categoryInfo, String ctgCodeType) {
		List<CategoryInfo> list = categoryManageService.getCategoryInfList(categoryInfo);
		List<CategoryInfo> rstList = new ArrayList<CategoryInfo>();
		for (CategoryInfo each : list) {
			if (ctgCodeType.equals(each.getCtgCodeType())) {
				rstList.add(each);
			}
		}
		
		return rstList;
	}
*/
	
}
