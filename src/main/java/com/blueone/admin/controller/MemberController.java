package com.blueone.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blueone.admin.domain.AdminInfo;
import com.blueone.admin.service.IMemberService;

@Controller
@RequestMapping(value = "/admin")
public class MemberController {

	private IMemberService memberService;
	
	@RequestMapping(value="/member.do", method= RequestMethod.GET)
	public String memberList(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result, Model model){
		
		   return "admin/member/memberList";
		
		
	}
	
	
}
