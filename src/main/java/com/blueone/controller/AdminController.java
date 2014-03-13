package com.blueone.controller;

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
	

	@RequestMapping(value = "/adminMain.do", method = RequestMethod.GET)
	public String mainAdminInfo(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result, Model model) {
		return "admin/admin/main";
	}
	
		
	@RequestMapping(value="/adminMember.do", method= RequestMethod.GET)
	public String MemberList(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result, Model model){
		
		   return "admin/member/memberList";
		
		
	}
	
	@RequestMapping(value="/goods.do", method= RequestMethod.GET)
	public String goodsList(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result, Model model){
		
		   return "admin/goods/goodsList";
		
		
	}
	
	@RequestMapping(value="/order.do", method= RequestMethod.GET)
	public String orderList(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result, Model model){
		
		   return "admin/order/orderList";
		
		
	}
	
	
	
	@RequestMapping(value="/community.do", method= RequestMethod.GET)
	public String community(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result, Model model){
		
		   return "admin/community/boardList";
		
		
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
