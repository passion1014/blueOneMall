package com.blueone.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blueone.admin.domain.AdminInfo;
import com.blueone.admin.service.IAdminManageService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	IAdminManageService adminManageService;
	
     
	@RequestMapping(value = "/adminMain.do", method = RequestMethod.GET)
	public String mainAdminInfo(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result, Model model) {
		
		return "admin/admin_main";
	}
	
	@RequestMapping(value = "/registAdminInf.do")
	public String registAdminInfo(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result, Model model) {
		adminManageService.registAdminInf(adminInfo);
		
		return "admin/admin_conf2";
	}
	
	@RequestMapping(value = "/registAdminForm.do", method = RequestMethod.GET)
	public String registAdminForm() {
		
		
		return "admin/admin_conf2";
	}
	
	
	@RequestMapping(value = "/editAdminInf.do", method = RequestMethod.POST)
	public String editAdminInfo(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result, Model model) {
		adminManageService.editAdminInf(adminInfo);
		
		return "admin/result";
	}

	
	@RequestMapping(value = "/getAdminList.do", method = RequestMethod.GET)
	public String getAdminInfoList(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result, Model model) {
		adminManageService.getAdminInfList(adminInfo);
		
		return "admin/admin_conf1";
	}

	
	@RequestMapping(value = "/getAdminDetailInf.do", method = RequestMethod.GET)
	public String getAdminDetailInfo(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result, Model model) {
		adminManageService.getAdminInfDetail(adminInfo);
		
		return "admin/result";
	}

}
