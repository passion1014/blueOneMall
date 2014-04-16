package com.blueone.user.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blueone.user.domain.UserInfo;
import com.blueone.user.service.IUserService;

@Controller
public class UserController {
	
	@Autowired IUserService userService;
	
	
	//회원가입 폼 생성
	@RequestMapping(value = "/user/userRegister.do", method=RequestMethod.GET)
	public String userRegister(@ModelAttribute("userInfo") UserInfo userInfo,BindingResult result, Model model){
		return "user/userRegister";
	}

	//회원가입 처리
	@RequestMapping(value = "/user/userRegister.do", method=RequestMethod.POST)
	public String userRegisterProc(@ModelAttribute("userInfo") UserInfo userInfo,BindingResult result, Model model){

		// 고객등록처리
		int rst = userService.registUserInfo(userInfo);
		if (rst == -1) {
			model.addAttribute("isRegistYn", "N");
			return "user/userRegister";
		}
		
		return "shop/main";	
	}

	//마이페이지
	@RequestMapping(value="/user/userEdit.do", method=RequestMethod.GET)
	public String userEdit(@ModelAttribute("userInfo") UserInfo userInfo,BindingResult result, Model model){
		return "user/userEdit";
	}
	
	//적립금현황
	@RequestMapping(value="/user/userPoint.do", method=RequestMethod.GET)
	public String userPoint(@ModelAttribute("userInfo") UserInfo userInfo,BindingResult result, Model model){
		return "user/userPoint";
	}
	
	
	//1:1문의하기 목록
	@RequestMapping(value="/user/qnaList.do", method=RequestMethod.GET)
	public String qnaList(@ModelAttribute("userInfo") UserInfo userInfo,BindingResult result, Model model){
		return "user/qnaPage";
	}

	
}
