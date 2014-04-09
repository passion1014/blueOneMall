package com.blueone.user.controller;

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
	
	
	
	//회원가입
	@RequestMapping(value = "/user/userRegister.do", method=RequestMethod.GET)
	public String userRegister(@ModelAttribute("userInfo") UserInfo userInfo,BindingResult result, Model model){
		return "user/userRegister";
	}
	
	//마이페이지
	@RequestMapping(value="/user/userMypage.do", method=RequestMethod.GET)
	public String userMypage(@ModelAttribute("userInfo") UserInfo userInfo,BindingResult result, Model model){
		return "user/userMypage";
	}
	
	//적립금현황
	@RequestMapping(value="/user/userPoint.do", method=RequestMethod.GET)
	public String userPoint(@ModelAttribute("userInfo") UserInfo userInfo,BindingResult result, Model model){
		return "user/userPoint";
	}
	
	//1:1문의하기
	@RequestMapping(value="/user/qnaList.do", method=RequestMethod.GET)
	public String qnaList(@ModelAttribute("userInfo") UserInfo userInfo,BindingResult result, Model model){
		return "user/qnaPage";
	}
}
