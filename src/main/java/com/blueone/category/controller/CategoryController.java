package com.blueone.category.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blueone.admin.controller.AdminController;
import com.blueone.category.domain.CategoryInfo;
import com.blueone.category.service.ICategoryManageService;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	ICategoryManageService categoryManageService;
	
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
