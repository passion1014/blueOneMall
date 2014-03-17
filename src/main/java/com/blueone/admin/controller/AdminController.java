package com.blueone.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import com.blueone.admin.domain.AdminLoginInfo;
import com.blueone.admin.service.IAdminManageService;

@SessionAttributes("adminSession")
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired IAdminManageService adminManageService;
	
    
	@RequestMapping(value = "/adminLogin.do", method = RequestMethod.GET)
	public String adminLoginform(@ModelAttribute("adminLoginInfo") AdminLoginInfo adminLoginInfo, BindingResult result, Model model) {
		return "admin/adminLogin";
	}
//=======================	
	@RequestMapping(value = "/adminLoginLogic.do", method = RequestMethod.POST)
	public ModelAndView adminLogin(@ModelAttribute("adminLoginInfo") @Valid AdminLoginInfo adminLoginInfo, BindingResult result, Model model) {
		ModelAndView mav = new ModelAndView();
		
		if (result.hasErrors()) {
			mav.setViewName("admin/adminLogin");
			return mav;
		} else {
			// 로그인 서비스 호출
			AdminInfo loggedInfo = adminManageService.adminLogin(adminLoginInfo);

			// viewName 셋팅
			if (loggedInfo != null) {
				mav.addObject("adminSession", loggedInfo);// 세션에 정보 셋팅
				mav.setViewName("redirect:adminDefault.do");
			} else {
				mav.setViewName("redirect:adminLogin.do");
			}
		}
		
		return mav;
	}
	//=======================	
	@RequestMapping(value = "/adminDefault.do", method = RequestMethod.GET)
	public String defaultAdminInfo(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result, Model model) {
		return "admin/defaultMain";
		
	}
	
	@RequestMapping(value="/adminMain.do", method= RequestMethod.GET)
	public String adminConf(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result, Model model){
		  
		   
		
		
		   return "admin/admin/adminMain";
			
	}
	
    @RequestMapping(value = "/registAdminInf.do")
	public String registAdminInfo(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result, Model model) {
		ModelAndView mav = new ModelAndView();
    	
    	adminManageService.registAdminInf(adminInfo);
    		
		return "redirect:/admin/admin/AdminList.do";
	}
	
	@RequestMapping("/adminRegistForm.do")
	public String registAdminForm() {
		
		return "admin/admin/adminRegist";
	}
	//=====================
	@RequestMapping(value = "/editAdminInf.do", method = RequestMethod.POST)
	public String editAdminInfo(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result, Model model) {
		adminManageService.editAdminInf(adminInfo);
		
		return "admin/result";
	}
    //===========================
	
	@RequestMapping(value = "/adminList.do", method = RequestMethod.GET)
	public ModelAndView getAdminInfoList(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result, Model model) {
		ModelAndView mav = new ModelAndView();
		List<AdminInfo> list = adminManageService.getAdminInfList(adminInfo);
		
	    mav.addObject("list", list);
	    mav.setViewName("admin/admin/adminList");
	    
		return mav;
	}

	
	@RequestMapping(value = "/adminDetailInf.do", method = RequestMethod.GET)
	public String getAdminDetailInfo(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result, Model model) {
		Map<String, AdminInfo> map = new HashMap<String, AdminInfo>();
		
		adminInfo= adminManageService.getAdminInfDetail(adminInfo);
	   
		
		model.addAttribute("adminInfo", adminInfo);
		return "admin/admin/adminDetail";
	}
	
	
	
	
	
	

}
