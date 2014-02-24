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
	
	/**
	 * 관리자 등록
	 */
	@RequestMapping(value = "/registAdminInf.do", method = RequestMethod.POST)
	public String registAdminInfo(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result, Model model) {
		adminManageService.registAdminInf(adminInfo);
		
		return "admin/result";
	}
	
	
	/**
	 * 관리자 수정
	 */
	@RequestMapping(value = "/editAdminInf.do", method = RequestMethod.POST)
	public String editAdminInfo(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result, Model model) {
		adminManageService.editAdminInf(adminInfo);
		
		return "admin/result";
	}

	/**
	 * 관리자 목록 조회
	 */
	@RequestMapping(value = "/getAdminList.do", method = RequestMethod.GET)
	public String getAdminInfoList(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result, Model model) {
		adminManageService.getAdminInfList(adminInfo);
		
		return "admin/result";
	}

	/**
	 * 관리자 상세 조회
	 */
	@RequestMapping(value = "/getAdminDetailInf.do", method = RequestMethod.GET)
	public String getAdminDetailInfo(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result, Model model) {
		adminManageService.getAdminInfDetail(adminInfo);
		
		return "admin/result";
	}

}
