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
	public String productList(@ModelAttribute("productInfo") ProductInfo productInfo, @ModelAttribute("categoryInfo") CategoryInfo categoryInfo,BindingResult result, Model model){
		
		
		
		
				
		List<ProductInfo> list = null;
		List<Object> list2 = new ArrayList<Object>();		
		
		//대분류이름
		List<CategoryInfo> list1 = categoryManageService.getCategoryInfList(categoryInfo);
		List<CategoryInfo> rstList  = new ArrayList<CategoryInfo>();
		List<CategoryInfo> lnbList  = new ArrayList<CategoryInfo>();
		
		//필터링 하는중
		
		String lCode = "L2727" ;
		
		for (CategoryInfo each : list1) {
			if ("01".equals(each.getCtgCodeType())) {
				rstList.add(each);
			}
		}
		
		for (CategoryInfo each : list1) {
			if (lCode.equals(each.getCtgPCode())) {
				lnbList.add(each);
			}
		}
				
		
		list = iUserProductService.shopProductInfList(productInfo);
		list2= iUserProductService.shopProductByPriceList(productInfo);
		
		
		model.addAttribute("list", list);
		model.addAttribute("list1", list1);
		
		model.addAttribute("price", list2);
		model.addAttribute("rstList",rstList);
		model.addAttribute("categoryList",lnbList);
		
			
		
		
		CategoryInfo largeInfo = new CategoryInfo();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			// -----------------------------------------------
			// 1. 카테고리 기본값 조회
			// -----------------------------------------------
			largeInfo.setCtgCode("L2727");
			largeInfo = sqlSession.selectOne("category.selectDtlBomCategoryTb0001", largeInfo);
			sqlSession.close();
			
		} finally {
			sqlSession.close();
		}			
		
		
		model.addAttribute("largeInfo",largeInfo);
		
		
		
		return "product/productList";
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
