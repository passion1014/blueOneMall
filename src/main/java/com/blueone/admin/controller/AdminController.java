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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
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
	@RequestMapping(value = "/adminLoginProc.do", method = RequestMethod.POST)
	public ModelAndView adminLogin(@ModelAttribute("adminLoginInfo") @Valid AdminLoginInfo adminLoginInfo, BindingResult result, Model model,String pw) {
		ModelAndView mav = new ModelAndView();
		
		if (result.hasErrors()) {
			mav.setViewName("admin/adminLogin");
			return mav;
		} else {
			// 로그인 서비스 호출
			AdminInfo loggedInfo = adminManageService.adminLogin(adminLoginInfo);
			String text="잘못입력하셨습니다.";
			String inpw = adminLoginInfo.getAdminPw();
			
			// viewName 셋팅
			if (loggedInfo != null) {
				if(inpw.equals(loggedInfo.getPassword())){
				mav.addObject("adminSession", loggedInfo);// 세션에 정보 셋팅
				model.addAttribute("logged", loggedInfo);
				mav.setViewName("redirect:adminDefault.do");
				}else{mav.setViewName("redirect:adminLogin.do");}
			}else {
				
				mav.setViewName("redirect:adminLogin.do");
		}}
		
		return mav;
	}
	
	@RequestMapping("/adminLoginOut.do")
	public String adminLoginOut(@ModelAttribute("adminInfo") AdminInfo adminInfo, BindingResult result, Model model,SessionStatus session){
		
		session.setComplete();
		
		return "redirect:adminLogin.do";
	}
	
	//=======================	
	@RequestMapping(value = "/adminDefault.do", method = RequestMethod.GET)
	public String defaultAdminInfo(@ModelAttribute("adminInfo") AdminInfo adminInfo, BindingResult result, Model model) {
		ModelAndView mav = new ModelAndView();

		
		AdminLoginInfo adminLoginInfo = new AdminLoginInfo();
		adminInfo = adminManageService.adminLogin(adminLoginInfo);
		mav.addObject("adminSession", adminInfo);
		
		return "admin/defaultMain";

//		

		
	}
	
	@RequestMapping(value="/adminMain.do", method= RequestMethod.GET)
	public String adminConf(@ModelAttribute("adminInfo") AdminInfo adminInfo, BindingResult result, Model model){
		return "admin/admin/adminMain";
	}
	
    @RequestMapping(value = "/registAdminInf.do")
	public String registAdminInfo(@ModelAttribute("adminInfo") @Valid AdminInfo adminInfo, BindingResult result, Model model,String id) {
    	
    	if(!id.equals(adminInfo.getId())){
    	}
    	
    	adminManageService.registAdminInf(adminInfo);
    		
		return "redirect:adminList.do";
	}
    
    @RequestMapping("/checkAdminId.do")
    @ResponseBody
    public String checkAdminDuplicateId(@ModelAttribute("adminInfo") AdminInfo adminInfo, BindingResult result, Model model) {
    	String viewName = "duplicateIdCheck";
    	
    	int rst = adminManageService.checkDupAdminId(adminInfo);
   		model.addAttribute("checkRst", rst);
    	
    	return viewName;
    }
	
	@RequestMapping("/adminRegistForm.do")
	public String registAdminForm() {
		
		return "admin/admin/adminRegist";
	}
	
	//=====================
	@RequestMapping(value = "/editAdminInf.do", method = RequestMethod.GET)
	public ModelAndView editAdminInfo(@ModelAttribute("adminInfo") AdminInfo adminInfo, BindingResult result, Model model) {
		
		adminManageService.editAdminInf(adminInfo);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:adminList.do");
		
		return mav;
	}
    //===========================
	
	@RequestMapping(value="/editAdminInfForm.do", method= RequestMethod.GET)
	public String editAdminInfForm(@ModelAttribute("adminInfo") AdminInfo adminInfo, BindingResult result, Model model,String id){
		
		adminManageService.getAdminInfDetail(id);
		model.addAttribute("adminInfo", adminInfo);
		return "admin/admin/adminEdit";
		
	}
	
	@RequestMapping(value = "/adminList.do", method = RequestMethod.GET)
	public ModelAndView getAdminInfoList(@ModelAttribute("adminInfo") AdminInfo adminInfo, BindingResult result, Model model) {
		ModelAndView mav = new ModelAndView();
		List<AdminInfo> list = adminManageService.getAdminInfList(adminInfo);
		
	    mav.addObject("list", list);
	    mav.setViewName("admin/admin/adminList");
	    
		return mav;
	}

	
	@RequestMapping(value = "/adminDetailInf.do", method = RequestMethod.GET)
	public ModelAndView getAdminDetailInfo(@ModelAttribute("adminInfo") AdminInfo adminInfo, BindingResult result, Model model, String id) {
		
		ModelAndView mav = new ModelAndView();
		adminInfo= adminManageService.getAdminInfDetail(id);
	   
		
		model.addAttribute("adminInfo", adminInfo);
		mav.setViewName("/admin/admin/adminDetail");
		return mav;
	}
	
	
	
	
	
	

}
