package com.blueone.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blueone.admin.service.IAdminManageService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	IAdminManageService adminManageService;
	
	/**
	 * ������ ���
	 */
	@RequestMapping(value = "/registAdminInf.do")
	public void registAdminInfo(Locale locale, Model model) {
//		adminManageService.registAdmin(adminInfo);
	}
	
	
	/**
	 * ������ ����
	 */
	@RequestMapping(value = "/editAdminInf.do")
	public void editAdminInfo(Locale locale, Model model) {
		
	}

	/**
	 * ������ ��� ��ȸ
	 */
	@RequestMapping(value = "/getAdminListdo")
	public void getAdminInfoList(Locale locale, Model model) {
		
	}

	/**
	 * ������ �� ��ȸ
	 */
	@RequestMapping(value = "/getAdminDetailInf.do")
	public void getAdminDetailInfo(Locale locale, Model model) {
		
	}

}
