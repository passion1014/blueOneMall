package com.blueone.user.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.blueone.common.domain.SearchAddress;
import com.blueone.common.util.Utility;
import com.blueone.customer.domain.CustomerInfo;
import com.blueone.customer.service.ICustomerManageService;
import com.blueone.order.domain.OrderInfo;
import com.blueone.order.domain.OrderProductInfo;
import com.blueone.order.service.IOrderManageService;
import com.blueone.user.domain.UserInfo;
import com.blueone.user.service.IUserService;


@Controller
public class UserController {
	
	@Autowired IUserService userService;
	@Autowired ICustomerManageService customerService;
	@Autowired IOrderManageService orderService;
	
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
		cus.setCustId("id1");//사용자가 로그인해서 들어오면 자동으로 넣어줘야하는 id
		
		
		
		
		cus=customerService.getCustomerInfo2(cus);
		
		String birth = cus.getCustBirth();
		cus = useStringToken(birth,"b",cus);
		
		String phone = cus.getCustPh();
		cus = useStringToken(phone,"p",cus);
		
		String mobile = cus.getCustMb();
		cus = useStringToken(mobile,"m",cus);
		
		String mail=cus.getCustEmail();
		int a = mail.indexOf("@");
		String mail1= mail.substring(0, a);
		String mail2= mail.substring(a+1);
		cus.seteMail1(mail1);
		cus.seteMail2(mail2);
		
	
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
		
		if(customerInfo.getCustMerryY()!=null){
			String merry = customerInfo.getCustMerryY()+"-"+customerInfo.getCustMerryM()+"-"+customerInfo.getCustMerryD();
			customerInfo.setCustMerry(merry);
		}
		customerService.updateCustomerInf(customerInfo);
		
		return "redirect:userEdit.do";
	}
	
	//우편번호 찾기 팝업
	@RequestMapping(value="/user/searchZipCode.do", method=RequestMethod.GET)
	public String searchZipCode(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
       
			return "user/searchZipCode";
	}
	//우편번호 찾기 팝업
	@RequestMapping(value="/user/searchZipCodeProc.do", method=RequestMethod.GET)
	public String searchZipCodeProc(@ModelAttribute("searchAddress")SearchAddress sAdd, HttpServletRequest request, HttpServletResponse response, Map<String, Object> commandMap, ModelMap model) throws Exception {
       
		CustomerInfo cus = new CustomerInfo();
		
		
		cus.setCustId("id1");//사용자가 로그인해서 들어오면 자동으로 넣어줘야하는 id
		
		
		
		
		cus=customerService.getCustomerInfo2(cus);
		
		String birth = cus.getCustBirth();
		cus = useStringToken(birth,"b",cus);
		
		String phone = cus.getCustPh();
		cus = useStringToken(phone,"p",cus);
		
		String mobile = cus.getCustMb();
		cus = useStringToken(mobile,"m",cus);
		
		String mail=cus.getCustEmail();
		int a = mail.indexOf("@");
		String mail1= mail.substring(0, a);
		String mail2= mail.substring(a+1);
		cus.seteMail1(mail1);
		cus.seteMail2(mail2);
		String add = sAdd.getAddress();
		add = new String(add.getBytes("8859_1"), "UTF-8");
		cus.setCustAdd(add);
		cus.setCustZip(sAdd.getZipCode());
		
	
		model.addAttribute("customer",cus);
		
		return "user/userEdit";
	}

		
	//주소 찾기 action
	@RequestMapping(value="/user/searchAddress.do", method=RequestMethod.GET)
	public String searchAddress(@ModelAttribute("userInfo") UserInfo userInfo,BindingResult result, Model model,String dong) throws ParserConfigurationException, SAXException, IOException{
		dong = new String(dong.getBytes("8859_1"), "UTF-8");
		
		 List<SearchAddress> nList =Utility.searchAdd(dong);
		
			model.addAttribute("nList", nList);
			return "user/searchZipCode";
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
	public String orderListView(@ModelAttribute("userInfo") UserInfo userInfo,BindingResult result, Model model) {
		
		//아이디 셋팅
		OrderInfo od = new OrderInfo();
		CustomerInfo cust = new CustomerInfo();
		cust.setCustId("dana");//사용자가 로그인해서 들어오면 자동으로 넣어줘야하는 id
		od.setCustomerInfo(cust);
		
		//아이디로 주문내역가져오기
		List<OrderInfo> odList = orderService.selectOrderInfoList(od);
		
		
		//주문코드로 주문상품 정보 가져오기
		for(OrderInfo each : odList){
			String odNo = each.getOrderNo();
			OrderProductInfo odPrd = new OrderProductInfo();
			odPrd.setOrderNo(odNo);
			odPrd=orderService.selectOrderPrdInfo(odPrd);
			odPrd=orderService.toProduct(odPrd);
			each.setOrdPrd(odPrd);
			
			String reg = each.getRegDate();
			int a = reg.indexOf(" ");
			reg= reg.substring(0, a);
			each.setRegDate(reg);
		}
		
	
		model.addAttribute("ordList", odList);
		return "user/orderListView";
	}

	

	//1:1문의하기 목록
	@RequestMapping(value="/user/qnaList.do", method=RequestMethod.GET)
	public String qnaList(@ModelAttribute("userInfo") UserInfo userInfo,BindingResult result, Model model){
		return "user/qnaPage";
	}

	public CustomerInfo useStringToken(String st, String Type,CustomerInfo cus ){
		
		CustomerInfo result = cus;
		
		String birth = result.getCustBirth();
		StringTokenizer stTk = new StringTokenizer(st,"-");
		
		int i=1;
		while(stTk.hasMoreElements()){
			switch(i){
			case 1:
				if(Type.equals("b")){
					result.setBirthY(Integer.parseInt(stTk.nextToken()));i++;
				}else if(Type.equals("m")){
					result.setHpNo1(stTk.nextToken());i++;
				}else if(Type.equals("p")){
					result.setTelNo1(stTk.nextToken());i++;
				}
				break;
			case 2:
				if(Type.equals("b")){
					result.setBirthM(Integer.parseInt(stTk.nextToken()));i++;
				}else if(Type.equals("m")){
					result.setHpNo2(stTk.nextToken());i++;
				}else if(Type.equals("p")){
					result.setTelNo2(stTk.nextToken());i++;
				}
				break;
			case 3:
				if(Type.equals("b")){
					String day = stTk.nextToken();
					day=day.substring(0, 2);
					result.setBirthD(Integer.parseInt(day));i++;
				}else if(Type.equals("m")){
					result.setHpNo3(stTk.nextToken());i++;
				}else if(Type.equals("p")){
					result.setTelNo3(stTk.nextToken());i++;
				}
				break;
				
			}
		}
		
		return result;
	

	}
	
	
	
	
}
