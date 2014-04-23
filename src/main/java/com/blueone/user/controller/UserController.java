package com.blueone.user.controller;

import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blueone.customer.domain.CustomerInfo;
import com.blueone.customer.service.ICustomerManageService;
import com.blueone.user.domain.UserInfo;
import com.blueone.user.service.IUserService;

@Controller
public class UserController {
	
	@Autowired IUserService userService;
	@Autowired ICustomerManageService customerService;
	
	
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
		
		CustomerInfo cus = new CustomerInfo ();
		cus.setCustId("id1");
		cus=customerService.getCustomerInfo2(cus);
		
		String birth = cus.getCustBirth();
		cus = useStringToken(birth,"b",cus);
		
		String phone = cus.getCustPh();
		cus = useStringToken(phone,"p",cus);
		
		String mobile = cus.getCustMb();
		cus = useStringToken(mobile,"m",cus);
		
		model.addAttribute("customer",cus);
		
		return "user/userEdit";
	}

	//마이페이지 처리
	@RequestMapping(value="/user/userEditProc.do", method=RequestMethod.POST)
	public String userEditProc(@ModelAttribute("customerInfo") CustomerInfo customerInfo,BindingResult result, Model model){
		
		String birth = customerInfo.getBirthY()+"-"+customerInfo.getBirthM()+"-"+customerInfo.getBirthD();
		customerInfo.setCustBirth(birth);
		
		String phone=customerInfo.getTelNo1()+"-"+customerInfo.getTelNo2()+"-"+customerInfo.getTelNo3();
		customerInfo.setCustPh(phone);
		
		String mobile=customerInfo.getHpNo1()+"-"+customerInfo.getHpNo2()+"-"+customerInfo.getHpNo3();
		customerInfo.setCustMb(mobile);
		
		String email = customerInfo.geteMail1()+"@"+customerInfo.geteMail2();
		customerInfo.setCustEmail(email);
		
		if(customerInfo.getBirthY()!=null){
			String merry = customerInfo.getCustMerryY()+"-"+customerInfo.getCustMerryM()+"-"+customerInfo.getCustMerryD();
			customerInfo.setCustMerry(merry);
		}
		customerService.updateCustomerInf(customerInfo);
		
		return "redirect:userEdit.do";
	}
	
	//적립금현황
	@RequestMapping(value="/user/userPointSaving.do", method=RequestMethod.GET)
	public String userPointSaving(@ModelAttribute("userInfo") UserInfo userInfo,BindingResult result, Model model){
		return "user/userPointSaving";
	}
	//사용내역조회
	@RequestMapping(value="/user/userPoint.do", method=RequestMethod.GET)
	public String userPoint(@ModelAttribute("userInfo") UserInfo userInfo,BindingResult result, Model model){
			return "user/userPoint";
	}
		
	
	//주문내역관리
	@RequestMapping(value="/user/orderListView.do", method=RequestMethod.GET)
	public String orderListView(@ModelAttribute("userInfo") UserInfo userInfo,BindingResult result, Model model){
		return "user/orderListView";
	}
	
	//1:1문의하기 목록
	@RequestMapping(value="/user/qnaList.do", method=RequestMethod.GET)
	public String qnaList(@ModelAttribute("userInfo") UserInfo userInfo,BindingResult result, Model model){
		return "user/qnaPage";
	}

	public CustomerInfo useStringToken(String st, String Type,CustomerInfo cus ){
		
		CustomerInfo result = cus;
		
		String birth = cus.getCustBirth();
		StringTokenizer stTk = new StringTokenizer(st,"-");
		
		int i=0;
		while(stTk.hasMoreElements()){
			switch(i){
			case 1:
				if(Type.equals("b")){
					cus.setBirthY(stTk.nextToken());i++;
				}else if(Type.equals("m")){
					cus.setHpNo1(stTk.nextToken());i++;
				}else if(Type.equals("p")){
					cus.setTelNo1(stTk.nextToken());i++;
				}
				break;
			case 2:
				if(Type.equals("b")){
					cus.setBirthM(stTk.nextToken());i++;
				}else if(Type.equals("m")){
					cus.setHpNo2(stTk.nextToken());i++;
				}else if(Type.equals("p")){
					cus.setTelNo2(stTk.nextToken());i++;
				}
				break;
			case 3:
				if(Type.equals("b")){
					cus.setBirthD(stTk.nextToken());i++;
				}else if(Type.equals("m")){
					cus.setHpNo3(stTk.nextToken());i++;
				}else if(Type.equals("p")){
					cus.setTelNo3(stTk.nextToken());i++;
				}
				break;
				
			}
		}
		
		return cus;
		
	}
	
}
