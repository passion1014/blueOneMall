package com.blueone.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blueone.admin.domain.AccountInfo;
import com.blueone.admin.domain.AdminInfo;
import com.blueone.admin.domain.AdminLoginInfo;
import com.blueone.admin.domain.ConfigInfo;
import com.blueone.admin.service.IAdminManageService;
import com.blueone.category.domain.CategoryInfo;
import com.blueone.common.util.PageDivision;

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
					mav.setViewName("redirect:adminDefault.do");
				}else{
					mav.setViewName("redirect:adminLogin.do");
				}
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
	
	@RequestMapping(value = "/adminDefault.do", method = RequestMethod.GET)
	public String defaultAdminInfo(HttpSession session,Model model) {

		AdminInfo adminSession = (AdminInfo)session.getAttribute("adminSession");
		// 세션체크
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}
		
		return "admin/defaultMain";
	}
	
	@RequestMapping(value="/adminMain.do", method= RequestMethod.GET)
	public String adminConf(@ModelAttribute("adminInfo") @Valid AdminInfo adminInfo,Model model, HttpSession session){
		
		AdminInfo adminSession = (AdminInfo)session.getAttribute("adminSession");
		
		// 세션체크
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}
		
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
	public String registAdminForm(HttpSession session) {
		AdminInfo adminSession = (AdminInfo)session.getAttribute("adminSession");
		if(adminSession == null){
		return "redirect:adminLogin.do";
		}
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
	public String editAdminInfForm(@ModelAttribute("adminInfo") AdminInfo adminInfo, BindingResult result, Model model,String id,HttpSession session){
		AdminInfo adminSession = (AdminInfo)session.getAttribute("adminSession");
		// 세션체크
				if (adminSession == null) {
					return "redirect:adminLogin.do";
				}
				
		adminManageService.getAdminInfDetail(id);
		model.addAttribute("adminInfo", adminInfo);
		return "admin/admin/adminEdit";
		
	}
	
	@RequestMapping(value = "/adminList.do", method = RequestMethod.GET)
	public ModelAndView getAdminInfoList(@ModelAttribute("adminInfo") AdminInfo adminInfo, BindingResult result, Model model,HttpSession session, String page) {
		AdminInfo adminSession = (AdminInfo)session.getAttribute("adminSession");
		ModelAndView mav = new ModelAndView();
		// 세션체크
		if (adminSession == null) {
			mav.setViewName("redirect:adminLogin.do");
			return mav;
		}
		
		
		List<AdminInfo> list = adminManageService.getAdminInfList(adminInfo);
		PageDivision pd = new PageDivision();

		if(StringUtils.isEmpty(page)) pd.pageNum("1");
		else pd.pageNum(page);
		
		pd.setAdInfolist(list);
		
		
		model.addAttribute("list", pd.getAdInfolist(1));
		model.addAttribute("endNum",pd.getEndPageNum());
	    
	    mav.setViewName("admin/admin/adminList");
	    
			return mav;
	}

	
	@RequestMapping(value = "/adminDetailInf.do", method = RequestMethod.GET)
	public ModelAndView getAdminDetailInfo(@ModelAttribute("adminInfo") AdminInfo adminInfo, BindingResult result, Model model, String id) {
		
		ModelAndView mav = new ModelAndView();
		adminInfo= adminManageService.getAdminInfDetail(id);
	   
		
		model.addAttribute("adminInfo", adminInfo);
		mav.setViewName("admin/admin/adminDetail");
		return mav;
	}
	
	
	/**
	 * 계좌 목록 리스트
	 */
	@RequestMapping(value="/accountList.do", method= RequestMethod.GET)
	public String adminAccountList(@ModelAttribute("accountInfo") AccountInfo accInfo, BindingResult result, Model model,HttpSession session,String page){
		
		AdminInfo adminSession = (AdminInfo)session.getAttribute("adminSession");
		
		if(adminSession == null){
		return "redirect:adminLogin.do";
		}
		
		List<AccountInfo> accList = adminManageService.getAccountInfList();
		
		PageDivision pd = new PageDivision();
		

		if(StringUtils.isEmpty(page)) pd.pageNum("1");
		else pd.pageNum(page);
		
		pd.setAccList(accList);
		
		
		model.addAttribute("list", pd.getAccList(10));
		model.addAttribute("endNum",pd.getEndPageNum());
		
		
		return "admin/admin/accountList";
			
	}
	
	
	
	/**
	 * 계좌 등록폼
	 */
	@RequestMapping(value="/accountRegister.do", method= RequestMethod.GET)
	public String largeTypeRegister(@ModelAttribute("accountInfo") AccountInfo accInfo, BindingResult result, Model model){
		
		List<AccountInfo> rstList = adminManageService.getBankInfList();
		model.addAttribute("bankList", rstList);
		
		
		return "admin/admin/accountRegister";
		
	}
		
	/**
	 * 계좌 등록처리
	 */
	@RequestMapping(value = "/accountRegisterProc.do", method = RequestMethod.POST)
	public String largeTypeRegisterProc(@ModelAttribute("accountInfo") AccountInfo accInfo, BindingResult result, Model model,RedirectAttributes redirectAttributes) {
		
		adminManageService.registAccountInf(accInfo);
		
		redirectAttributes.addFlashAttribute("reloadVar", "yes");
		return "redirect:accountRegister.do";
		
	}
	
	
	/**
	 * 계좌 삭제
	 */
	@RequestMapping(value = "/deleteAccountProc.do", method = RequestMethod.GET)
	public String deletesAccountInfo(@ModelAttribute("accountInfo") AccountInfo accInfo, BindingResult result, Model model) {
		
		adminManageService.deleteAccountInf(accInfo);
		
		return "redirect:accountList.do";
	}
	
	/**
	 * 계좌 수정폼
	 */		
	@RequestMapping(value="/accountEdit.do", method=RequestMethod.GET)
	public String accountModify(@ModelAttribute("accountInfo") AccountInfo accInfo, BindingResult result, Model model){
		
		AccountInfo resInf = adminManageService.getAccountInfDetail(accInfo);
		model.addAttribute("acc", resInf);
		List<AccountInfo> rstList = adminManageService.getBankInfList();
		model.addAttribute("bankList", rstList);
		
		return "admin/admin/accountEdit";
	}
	
	/**
	 * 계좌 수정처리
	 */		
	@RequestMapping(value="/editAccountInfProc.do", method=RequestMethod.POST)
	public String accountEditProc(@ModelAttribute("accountInfo") AccountInfo accInfo, BindingResult result, Model model){
		
		adminManageService.editAccountInf(accInfo);
		
		
		return "redirect:accountList.do";
	}
	
	
	//운영설정
	@RequestMapping(value="/configEdit.do", method= RequestMethod.GET)
	public String configEdit(@ModelAttribute("adminInfo") @Valid AdminInfo adminInfo,Model model, HttpSession session){
/*
		AdminInfo adminSession = (AdminInfo) session
				.getAttribute("adminSession");

		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}
*/
		ConfigInfo result = adminManageService.selectConfigInf();
		
		model.addAttribute("config", result);
		return "admin/admin/configEdit";
	}

	//운영설정-수정
	@RequestMapping(value = "/configEditProc.do", method = RequestMethod.POST)
	public String configEditProc(
	@ModelAttribute("adminInfo") ConfigInfo configInfo,Model model, HttpSession session) {
		
		adminManageService.editConfigInf(configInfo);

		
		return "redirect:configEdit.do";
	}
}
