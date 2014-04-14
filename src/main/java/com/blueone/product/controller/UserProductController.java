package com.blueone.product.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blueone.category.domain.CategoryInfo;
import com.blueone.category.service.ICategoryManageService;
import com.blueone.product.domain.ProductInfo;
import com.blueone.product.service.IUserProductService;

@Controller
public class UserProductController {

	
	@Autowired
	private IUserProductService iUserProductService;
	@Autowired
	private ICategoryManageService categoryManageService;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@RequestMapping(value="/product/productList.do")
	public String productList(@ModelAttribute("productInfo") ProductInfo productInfo, @ModelAttribute("categoryInfo") CategoryInfo categoryInfo, String lCode, BindingResult result, Model model){
		
		// ----------------------------------------------------------
		// 1. 변수선언
		// ----------------------------------------------------------
		List<ProductInfo> productList = null;
		List<Object> priceList = new ArrayList<Object>();		
		List<CategoryInfo> rstList  = new ArrayList<CategoryInfo>();
		List<CategoryInfo> lnbList  = new ArrayList<CategoryInfo>();
		
		// ----------------------------------------------------------
		// 2. 카테고리 조회
		// ----------------------------------------------------------
		List<CategoryInfo> categoryList = categoryManageService.getCategoryInfList(categoryInfo);
		
		for (CategoryInfo each : categoryList) {
			if ("01".equals(each.getCtgCodeType())) {
				rstList.add(each);
			}
		}
		
		for (CategoryInfo each : categoryList) {
			if (lCode.equals(each.getCtgPCode())) {
				lnbList.add(each);
			}
		}
				
		
		// ----------------------------------------------------------
		// 3. 상품+가격목록 조회
		// ----------------------------------------------------------
		productList = iUserProductService.shopProductInfList(productInfo);
		priceList= iUserProductService.shopProductByPriceList(productInfo);
		
		CategoryInfo rstInfo = new CategoryInfo();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			// -----------------------------------------------
			// 1. 카테고리 기본값 조회
			// -----------------------------------------------
			CategoryInfo largeInfo = new CategoryInfo();
			largeInfo.setCtgCode("L2727");
			
			rstInfo = sqlSession.selectOne("category.selectDtlBomCategoryTb0001", largeInfo);
			sqlSession.close();
			
		} finally {
			sqlSession.close();
		}			
		
		
		model.addAttribute("productList", productList);	// 상품리스트
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("priceList", priceList);
		model.addAttribute("rstList",rstList);
		model.addAttribute("categoryList",lnbList);
		model.addAttribute("largeInfo",rstInfo);
		
		return "product/productList";
	}
	
	@RequestMapping(value="/include/header.do")
	public String header() {
		System.out.println("aaa");
		return "/inc/header";
	}
	
	@RequestMapping(value="/product/productView.do")
	public String productView(@ModelAttribute("productInfo") ProductInfo productInfo,@ModelAttribute("categoryInfo") CategoryInfo categoryInfo ,BindingResult result, Model model){
		
		
		categoryInfo = new CategoryInfo();
		categoryInfo = categoryManageService.getCategoryInfDetail(categoryInfo);
		model.addAttribute("categoryInfo", categoryInfo);
		List<CategoryInfo> fullList = categoryManageService.getCategoryInfList(categoryInfo);
		//대분류 조회
		List<CategoryInfo> lFullList = new ArrayList<CategoryInfo>();
		//중분류 조회
		List<CategoryInfo> mFullList = new ArrayList<CategoryInfo>();
		
		String ctgLargeCode = categoryInfo.getCtgLargeCode();
		
		for(CategoryInfo large : fullList){
			if("01".equals(large.getCtgCodeType())){
				lFullList.add(large);
			}
			if("02".equals(large.getCtgCodeType())){
				mFullList.add(large);
			}
			
		}
		
		for(int idx=0; idx < mFullList.size(); idx++) {
			CategoryInfo each = mFullList.get(idx);
			if (!ctgLargeCode.equals(each.getCtgPCode())) {
				mFullList.remove(idx);
				idx--;
			}
		}
		
		
		/*categoryInfo = categoryManageService.getCategoryInfDetail(categoryInfo);
		List<CategoryInfo> list = categoryManageService.getCategoryInfList4(categoryInfo);
		
		List<CategoryInfo> rstList1 = getCategoryListByTypeCd(categoryInfo, "01");//대분류 list
		List<CategoryInfo> rstList2 = getCategoryListByTypeCd(categoryInfo, "02");//중분류 list
		
		String ctgLargeCode = categoryInfo.getCtgLargeCode();
		
		for(int idx=0; idx < rstList2.size(); idx++) {
			CategoryInfo each = rstList2.get(idx);
			if (!ctgLargeCode.equals(each.getCtgPCode())) {
				rstList2.remove(idx);
				idx--;
			}
		}

		
		model.addAttribute("ctgList1", rstList1);
		model.addAttribute("ctgList2", rstList2);
		model.addAttribute("categoryInfo", categoryInfo);
		model.addAttribute("list", list);*/
	
		
		
	
		
		model.addAttribute("lFullList", lFullList);
		model.addAttribute("mFullList", mFullList);
		
		
		
		return "product/productView";
	}
	

	
	
	
	
	
}
