package com.blueone.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;

import com.blueone.admin.domain.AdminInfo;
import com.blueone.admin.service.IAdminManageService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	IAdminManageService adminManageService;
	
    
	@RequestMapping(value = "/adminLoginForm.do", method = RequestMethod.GET)
	public String adminLoginform(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result, Model model) {
		return "admin/adminLoginForm";
		
	}
	
	@RequestMapping(value = "/adminLogin.do", method = RequestMethod.GET)
	public String adminLogin(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result, Model model) {
		return "redirect:admin/adminDefault.do";
		
	}
	
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
		adminManageService.registAdminInf(adminInfo);
		
		return "redirect:/admin/getAdminList.do";
	}
	
	@RequestMapping("/registAdminForm.do")
	public String registAdminForm() {
		
		return "admin/admin_conf2";
	}
	//=====================
	@RequestMapping(value = "/editAdminInf.do", method = RequestMethod.POST)
	public String editAdminInfo(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result, Model model) {
		adminManageService.editAdminInf(adminInfo);
		
		return "admin/result";
	}
    //===========================
	
	@RequestMapping(value = "/getAdminList.do", method = RequestMethod.GET)
	public String getAdminInfoList(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result, Model model) {
		List<AdminInfo> list = adminManageService.getAdminInfList(adminInfo);
		
	    model.addAttribute("list", list);
	    
		return "admin/admin_conf1";
	}

	
	@RequestMapping(value = "/getAdminDetailInf.do", method = RequestMethod.GET)
	public String getAdminDetailInfo(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result, Model model) {
		
		    adminManageService.getAdminInfDetail(adminInfo);
		
		return "admin/result";
	}
	
	
	
	
	
	

}
