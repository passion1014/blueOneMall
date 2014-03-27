package com.blueone.admin.controller;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.blueone.admin.domain.AdminInfo;
import com.blueone.admin.domain.GoodsTypeInfo;
import com.blueone.admin.service.IGoodsService;
import com.blueone.category.controller.CategoryController;
import com.blueone.category.domain.CategoryInfo;
import com.blueone.category.service.ICategoryManageService;

@Controller
@SessionAttributes("adminSession")
@RequestMapping(value = "/admin")
public class GoodsController {
	
private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	IGoodsService iGoodsService;
	
	
	@RequestMapping(value="/goodsList.do", method= RequestMethod.GET)
	public String goodsList(@ModelAttribute("categoryTypeInfo") GoodsTypeInfo goodsTypeInfo, BindingResult result, Model model){
		return "admin/goods/goodsList";
	}
	
<<<<<<< HEAD
	@RequestMapping(value="/largeTypeList.do", method= RequestMethod.GET)
	public ModelAndView largeTypeList(@ModelAttribute("categoryTypeInfo") GoodsTypeInfo goodsTypeInfo, BindingResult result, Model model){
		ModelAndView mav = new ModelAndView();
		List<GoodsTypeInfo> list = iGoodsService.getGoodsTypeList(goodsTypeInfo);
		
	    mav.addObject("list", list);
	    mav.setViewName("admin/goods/largeTypeList");
	    
		return mav;
			
	}
	
=======
>>>>>>> 9efb416b21b2b7a173d1f24579840d1d1c5e2e95
	
	@RequestMapping(value="/largeTypeRegister.do", method= RequestMethod.GET)
	public String largeTypeRegister(@ModelAttribute("adminInfo") AdminInfo adminInfo, BindingResult result, Model model){
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("adminSession", adminInfo);
		
		return "admin/goods/largeTypeRegister";
	}
	
	/**
	 * 카테고리 등록
	 */
	@RequestMapping(value = "/largeTypeRegisterProc.do", method = RequestMethod.POST)
	public String registCategoryInfo(@ModelAttribute("GoodsTypeInfo") GoodsTypeInfo goodsTypeInfo, BindingResult result, Model model) {
		
		iGoodsService.registCategoryInf(goodsTypeInfo);
		return "admin/goods/largeTypeRegister";
	}
	
	@RequestMapping(value = "/editCategoryInf.do", method = RequestMethod.GET)
	public String editCategoryInfo(@ModelAttribute("GoodsTypeInfo") GoodsTypeInfo goodsTypeInfo, BindingResult result, Model model,String ctgCode) {
		
<<<<<<< HEAD
		iGoodsService.registGoodsType(goodsTypeInfo);
				
		model.addAttribute("reloadVar", "yes");
		return "admin/goods/largeTypeRegister";
			
	}
	
	
	@RequestMapping(value="/largeTypeEdit.do", method=RequestMethod.GET)
	public ModelAndView largeTypeEdit(@ModelAttribute("goodsTypeInfo") GoodsTypeInfo goodsTypeInfo, BindingResult result, Model model){
		ModelAndView mav = new ModelAndView();
		int rst = iGoodsService.goodsTypeEdit(goodsTypeInfo);
		if(rst == 0){
			mav.setViewName("admin/largeTypeEdit");
		}
		mav.setViewName("admin/largeTypeEdit");
		return mav;
	}
	
	
	@RequestMapping(value="/largeTypeDelete.do", method=RequestMethod.GET)
	public ModelAndView largeTypeDelete(@ModelAttribute("goodsTypeInfo") GoodsTypeInfo goodsTypeInfo, BindingResult result, Model model){
		ModelAndView mav = new ModelAndView();
		int rst = iGoodsService.goodsTypeDelete(goodsTypeInfo);
		mav.setViewName("admin/largeTypeDelete");
		return mav;
=======
		
		
		return "admin/goods/largeTypeEdit";
	}

	/**
	 * 카테고리 수정
	 */
	@RequestMapping(value = "/editCategoryInfProc.do", method = RequestMethod.GET)
	public String editCategoryInfoProc(@ModelAttribute("GoodsTypeInfo") GoodsTypeInfo goodsTypeInfo, BindingResult result, Model model) {
		
		iGoodsService.editCategoryInf(goodsTypeInfo);
		
		return "redirect:largeTypeList.do";
	}
	
	/**
	 * 카테고리삭제
	 */
	@RequestMapping(value = "/deleteCategoryInf.do", method = RequestMethod.GET)
	public String deleteCategoryInfo(@ModelAttribute("GoodsTypeInfo") GoodsTypeInfo goodsTypeInfo, BindingResult result, Model model) {
		iGoodsService.deleteCategoryInf(goodsTypeInfo);
		
		return "redirect:largeTypeList.do";
	}
	/**
	 * 카테고리목록 조회
	 */
	@RequestMapping(value = "/largeTypeList.do", method = RequestMethod.GET)
	public String getListCategoryInfo(@ModelAttribute("GoodsTypeInfo") GoodsTypeInfo goodsTypeInfo, BindingResult result, Model model) {
		List<GoodsTypeInfo> list;
		list = iGoodsService.getCategoryInfList(goodsTypeInfo);
		model.addAttribute("list", list);
		return "admin/goods/largeTypeList";
>>>>>>> 9efb416b21b2b7a173d1f24579840d1d1c5e2e95
	}
	
	
	
	

}
