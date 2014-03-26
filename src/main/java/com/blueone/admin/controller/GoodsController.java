package com.blueone.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@Controller
@SessionAttributes("adminSession")
@RequestMapping(value = "/admin")
public class GoodsController {
	
	@Autowired
	private IGoodsService iGoodsService;
	
	
	@RequestMapping(value="/goodsList.do", method= RequestMethod.GET)
	public String goodsList(@ModelAttribute("categoryTypeInfo") GoodsTypeInfo goodsTypeInfo, BindingResult result, Model model){
		return "admin/goods/goodsList";
	}
	
	@RequestMapping(value="/largeTypeList.do", method= RequestMethod.GET)
	public ModelAndView largeTypeList(@ModelAttribute("categoryTypeInfo") GoodsTypeInfo goodsTypeInfo, BindingResult result, Model model){
		ModelAndView mav = new ModelAndView();
		List<GoodsTypeInfo> list = iGoodsService.getGoodsTypeList(goodsTypeInfo);
		
	    mav.addObject("list", list);
	    mav.setViewName("admin/goods/largeTypeList");
	    
		return mav;
		
		
		
	}
	
	
	@RequestMapping(value="/largeTypeRegister.do", method= RequestMethod.GET)
	public String largeTypeRegister(@ModelAttribute("adminInfo") AdminInfo adminInfo, BindingResult result, Model model){
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("adminSession", adminInfo);
		
		return "admin/goods/largeTypeRegister";
	}
	
	
	@RequestMapping(value="/largeTypeRegisterProc.do", method= RequestMethod.POST)
	public String largeTypeRegisterProc(@ModelAttribute("goodsTypeInfo") GoodsTypeInfo goodsTypeInfo, BindingResult result, Model model){
		
		iGoodsService.registGoodsType(goodsTypeInfo);
		
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
	}

}
