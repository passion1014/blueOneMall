package com.blueone.category.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.blueone.admin.controller.AdminController;
import com.blueone.admin.domain.AdminInfo;
import com.blueone.category.domain.CategoryInfo;
import com.blueone.category.service.ICategoryManageService;

@Controller
@SessionAttributes("adminSession")
public class CategoryController {

	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	ICategoryManageService categoryManageService;
	
		
	/**
	 * 관리자 대분류 리스트
	 */
	@RequestMapping(value="/admin/largeTypeList.do", method= RequestMethod.GET)
	public ModelAndView largeTypeList(@ModelAttribute("categoryInfo") CategoryInfo categoryInfo, BindingResult result, Model model){
		
		ModelAndView mav = new ModelAndView();
		List<CategoryInfo> list = categoryManageService.getCategoryInfList(categoryInfo);
	    
		mav.addObject("list", list);
		
		mav.setViewName("admin/product/largeTypeList");	    
		return mav;
			
	}
	
	
	/**
	 * 관리자 대분류 등록폼
	 */
	@RequestMapping(value="/admin/largeTypeRegister.do", method= RequestMethod.GET)
	public String largeTypeRegister(@ModelAttribute("adminInfo") AdminInfo adminInfo, BindingResult result, Model model){
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("adminSession", adminInfo);
		
		int code= (int)(Math.random()*10000)+1;
		String ctgCode= "L"+code;
		
		model.addAttribute("ctgCode", ctgCode);
		
		return "admin/product/largeTypeRegister";
		
	}
	
		
	/**
	 * 관리자 대분류 등록처리
	 */
	@RequestMapping(value = "/admin/largeTypeRegisterProc.do", method = RequestMethod.POST)
	public String largeTypeRegisterProc(@ModelAttribute("categoryInfo") CategoryInfo categoryInfo, BindingResult result, Model model) {
		
		categoryManageService.registCategoryInf(categoryInfo);
		
		model.addAttribute("reloadVar", "yes");
		return "admin/product/largeTypeRegister";
		
	}
		
	
	/**
	 * 관리자 대분류 수정폼
	 */		
	@RequestMapping(value="/admin/largeTypeEdit.do", method=RequestMethod.POST)
	public String largeTypeModify(@ModelAttribute("categoryInfo") CategoryInfo categoryInfo, BindingResult result, Model model){
		
		categoryInfo = categoryManageService.getCategoryInfDetail(categoryInfo);
		model.addAttribute("largeTypeObj", categoryInfo);
		
		return "admin/product/largeTypeEdit";
	}
	
	
	
	/**
	 * 관리자 대분류 수정처리
	 */
	@RequestMapping(value = "/admin/editCategoryInfProc.do", method = RequestMethod.POST)
	public String editCategoryInfoProc(@ModelAttribute("categoryInfo") CategoryInfo categoryInfo, BindingResult result, Model model) {
		categoryManageService.editCategoryInf(categoryInfo);
		
		return "redirect:largeTypeList.do";
	}
	
	
	/**
	 * 관리자 대분류 삭제
	 */
	@RequestMapping(value = "/admin/deleteCategoryInf.do", method = RequestMethod.GET)
	public String deleteCategoryInfo(@ModelAttribute("categoryInfo") CategoryInfo categoryInfo, BindingResult result, Model model) {
		categoryManageService.deleteCategoryInf(categoryInfo);
		
		return "redirect:largeTypeList.do";
	}
	
	
	/**
	 * 관리자 중분류 리스트
	 */
	@RequestMapping(value="/admin/middleTypeList.do", method= RequestMethod.GET)
	public ModelAndView middleTypeList(@ModelAttribute("categoryInfo") CategoryInfo categoryInfo, BindingResult result, Model model){
		
		ModelAndView mav = new ModelAndView();
		List<CategoryInfo> list = categoryManageService.getCategoryInfList(categoryInfo);
	    
		mav.addObject("list", list);
		
		mav.setViewName("admin/product/middleTypeList");	    
		return mav;
			
	}

	
	/**
	 * 관리자 중분류 등록폼
	 */
	@RequestMapping(value="/admin/middleTypeRegister.do", method= RequestMethod.GET)
	public String middleTypeRegister(@ModelAttribute("categoryInfo") CategoryInfo categoryInfo, BindingResult result, Model model){
		
		List<CategoryInfo> list = categoryManageService.getCategoryInfList(categoryInfo);
				
		int code= (int)(Math.random()*10000)+1;
		String ctgCode= "M"+code;
		
		// 카테고리타입이 01(대분류)인 것들만 수집
		List<CategoryInfo> rstList = new ArrayList<CategoryInfo>();
		for (CategoryInfo each : list) {
			if ("01".equals(each.getCtgCodeType())) {
				rstList.add(each);
			}
		}
		
		model.addAttribute("ctgCode", ctgCode);
		model.addAttribute("ctgList", rstList);

		return "admin/product/middleTypeRegister";
		
	}
	
	
//	@RequestMapping(value="/admin/middleTypeRegister.do", method= RequestMethod.GET)
//	public ModelAndView middleTypeRegister2(@ModelAttribute("categoryInfo") CategoryInfo categoryInfo, BindingResult result, Model model){
//		
//		List<CategoryInfo> list = categoryManageService.getCategoryInfList(categoryInfo);
//				
//		int code= (int)(Math.random()*10000)+1;
//		String ctgCode= "M"+code;
//		
//		// 카테고리타입이 01(대분류)인 것들만 수집
//		List<CategoryInfo> rstList = new ArrayList<CategoryInfo>();
//		for (CategoryInfo each : list) {
//			if ("01".equals(each.getCtgCodeType())) {
//				rstList.add(each);
//			}
//		}
//
//		// 리턴객체 셋팅
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("ctgCode", ctgCode);
//		mav.addObject("ctgList", rstList);
//		mav.setViewName("admin/product/middleTypeRegister");
//
//		return mav;
//		
//	}

	
	/**
	 * 관리자 소분류 리스트
	 */
	@RequestMapping(value="/admin/smallTypeList.do", method= RequestMethod.GET)
	public ModelAndView smallTypeList(@ModelAttribute("categoryInfo") CategoryInfo categoryInfo, BindingResult result, Model model){
		
		ModelAndView mav = new ModelAndView();
		List<CategoryInfo> list = categoryManageService.getCategoryInfList(categoryInfo);
	    
		mav.addObject("list", list);
		
		mav.setViewName("admin/product/smallTypeList");	    
		return mav;
			
	}

	
	/**
	 * 관리자 소분류 등록폼
	 */
	@RequestMapping(value="/admin/smallTypeRegister.do", method= RequestMethod.GET)
	public String smallTypeRegister(@ModelAttribute("categoryInfo") CategoryInfo categoryInfo, BindingResult result, Model model){
		
		ModelAndView mav = new ModelAndView();
		List<CategoryInfo> list = categoryManageService.getCategoryInfList(categoryInfo);
				
		int code= (int)(Math.random()*10000)+1;
		String ctgCode= "M"+code;
		
		model.addAttribute("ctgCode", ctgCode);
		
		return "admin/product/smallTypeRegister";
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 카테고리 등록
	 */
	@RequestMapping(value = "/registCategoryInf.do", method = RequestMethod.POST)
	public String registCategoryInfo(@ModelAttribute("CategoryInfo") CategoryInfo categoryInfo, BindingResult result, Model model) {
		categoryManageService.registCategoryInf(categoryInfo);
		
		return "category/result";
	}

	/**
	 * 카테고리 수정
	 */
	@RequestMapping(value = "/editCategoryInf.do", method = RequestMethod.POST)
	public String editCategoryInfo(@ModelAttribute("CategoryInfo") CategoryInfo categoryInfo, BindingResult result, Model model) {
		categoryManageService.editCategoryInf(categoryInfo);
		
		return "category/result";
	}

	
	/**
	 * 카테고리목록 조회
	 */
	@RequestMapping(value = "/getCategoryList.do", method = RequestMethod.GET)
	public String getListCategoryInfo(@ModelAttribute("CategoryInfo") CategoryInfo categoryInfo, BindingResult result, Model model) {
		categoryManageService.getCategoryInfList(categoryInfo);
		
		return "category/result";
	}
	
}
