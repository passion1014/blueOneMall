package com.blueone.admin.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xml.sax.SAXException;

import com.blueone.admin.domain.AdminInfo;
import com.blueone.admin.domain.AgreementInfo;
import com.blueone.admin.service.IAdminManageService;
import com.blueone.admin.service.IMemberService;
import com.blueone.common.domain.SearchAddress;
import com.blueone.common.util.Utility;
import com.blueone.customer.domain.CustomerInfo;
import com.blueone.customer.domain.CustomerSrchInfo;
import com.blueone.customer.service.ICustomerManageService;
import com.blueone.order.domain.OrderInfo;
import com.blueone.order.domain.OrderSrchInfo;
import com.blueone.user.domain.UserInfo;

@Controller
@RequestMapping(value = "/admin")
public class MemberController {

	private IMemberService memberService;
	@Autowired ICustomerManageService customerManageService;
	@Autowired IAdminManageService adminManageService;
	
	@RequestMapping(value="/memberList.do", method= RequestMethod.GET)
	public String memberList(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result,String page, Model model, HttpSession session){
		
		AdminInfo adminSession = (AdminInfo)session.getAttribute("adminSession");
		
		// 세션체크
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}	
		
		
		
		CustomerInfo custInfo = new CustomerInfo();
		
		if (StringUtils.isEmpty(page))
		{page="1";custInfo.setStartIdx(Integer.parseInt(page));}
		else 
			custInfo.setStartIdx(Integer.parseInt(page));
		
		List<CustomerInfo> allCust=customerManageService.getCustomerInfoList(custInfo);
		model.addAttribute("cust", allCust);
		
		
		int endNum;
		int total= customerManageService.getCustomerTypTotalCount(custInfo);
		
		if(total%15==0 || total/15<0  ) {
			endNum=total/15;
		}
		else{
			endNum=total/15+1;
			}
	
	
		model.addAttribute("endNum",endNum);
		model.addAttribute("custURL","memberList.do");
		return "admin/member/memberList";
	}
	
	@RequestMapping(value="/searchMember.do", method= RequestMethod.GET)
	public String searchMember(@ModelAttribute("AdminInfo") CustomerSrchInfo customerSrchInfo,String page, BindingResult result, Model model, HttpSession session){
		
		AdminInfo adminSession = (AdminInfo)session.getAttribute("adminSession");
		
		// 세션체크
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}	
		
		CustomerInfo search = new CustomerInfo();
		int colum = customerSrchInfo.getCustColum();
		switch(colum){
			case 1 :
				search.setCustNm(customerSrchInfo.getWord());
				break;
			case 2 :
				search.setCustId(customerSrchInfo.getWord());
				break;
			case 3 :
				search.setCustEmail(customerSrchInfo.getWord());
				break;
			case 4 :
				search.setCustMb(customerSrchInfo.getWord());
				break;
			
			
		}
	
		
		if (StringUtils.isEmpty(page))
		{page="1";search.setStartIdx(Integer.parseInt(page));}
		else 
			search.setStartIdx(Integer.parseInt(page));
		
		List<CustomerInfo> allCust=customerManageService.searchCustomerInfoList(search);
		model.addAttribute("cust", allCust);
		
		
		
		int endNum;
		int total= customerManageService.getCustomerTypTotalCount(search);
		
		if(total%15==0 || total/15<0 ) {
			endNum=total/15;
		}
		else{
			endNum=total/15+1;
			}
	
	
		model.addAttribute("endNum",endNum);
		model.addAttribute("custURL","searchMember.do");
		return "admin/member/memberList";
	}
	//회원정보 삭제
	@RequestMapping(value="/deleteMember.do", method= RequestMethod.GET)
	public String deleteMember(@ModelAttribute("customerInfo") CustomerInfo customerInfo, BindingResult result, Model model, HttpSession session){
		
		AdminInfo adminSession = (AdminInfo)session.getAttribute("adminSession");
		
		// 세션체크
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}	
		
		customerManageService.deleteCustomerInf(customerInfo);
		
		
		return "redirect:memberList.do";
	}
	
	//회원정보 수정
	@RequestMapping(value="/memberEdit.do", method= RequestMethod.GET)
	public String memberEdit(@ModelAttribute("customerInfo") CustomerInfo customerInfo, BindingResult result, Model model, HttpSession session){
		
		AdminInfo adminSession = (AdminInfo)session.getAttribute("adminSession");
		
		// 세션체크
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}	
		
		CustomerInfo cus=customerManageService.getCustomerInfo2(customerInfo);
		
		/*String birth = cus.getCustBirth();
		if ( !StringUtils.isEmpty(birth) ) {
			cus = useStringToken(birth,"b",cus);
		}
		*/
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
		model.addAttribute("customer", cus);
		
		return "admin/member/memberEdit";
	}
	//우편번호 찾기 팝업
	@RequestMapping(value="/searchZipCode.do", method=RequestMethod.GET)
	public String searchZipCode(HttpServletRequest request, HttpServletResponse response, ModelMap model, String custId) throws Exception {
		model.addAttribute("custId", custId);
		
			return "admin/member/searchZipCode";
	}
	//우편번호 찾기 팝업
	@RequestMapping(value="/searchZipCodeProc.do", method=RequestMethod.GET)
	public String searchZipCodeProc(@ModelAttribute("searchAddress")SearchAddress sAdd,String custId, HttpServletRequest request, HttpServletResponse response, Map<String, Object> commandMap, ModelMap model,HttpSession session) throws Exception {
       
		
		
		
		
		CustomerInfo cus =new CustomerInfo();
		cus.setCustId(custId);
		cus=customerManageService.getCustomerInfo2(cus);
		
		String birth = cus.getCustBirth();
		if(!birth.isEmpty() || birth !=null){
			cus = useStringToken(birth,"b",cus);
		}
		
		String phone = cus.getCustPh();
		if(!phone.isEmpty() || phone !=null){
			cus = useStringToken(phone,"p",cus);
		}
		String mobile = cus.getCustMb();
		if(!mobile.isEmpty() || mobile !=null){
			cus = useStringToken(mobile,"m",cus);
		}
		
		String mail=cus.getCustEmail();
		if(!mail.isEmpty() || mail !=null || !mail.equals("")){
			int a = mail.indexOf("@");
			String mail1= mail.substring(0, a);
			String mail2= mail.substring(a+1);
			cus.seteMail1(mail1);
			cus.seteMail2(mail2);
		}
		
		String add = sAdd.getAddress();
		add = new String(add.getBytes("8859_1"), "UTF-8");
		cus.setCustAdd(add);
		cus.setCustZip(sAdd.getZipCode());
		
	
		model.addAttribute("customer",cus);
		
		
			return  "admin/member/memberEdit";
		
		
	}
	//주소 찾기 action
	@RequestMapping(value="/searchAddress.do", method=RequestMethod.GET)
	public String searchAddress(@ModelAttribute("userInfo") UserInfo userInfo,String custId,BindingResult result, Model model,String dong) throws ParserConfigurationException, SAXException, IOException{
		dong = new String(dong.getBytes("8859_1"), "UTF-8");
		
		 List<SearchAddress> nList =Utility.searchAdd(dong);
		
			model.addAttribute("nList", nList);
			model.addAttribute("custId",custId);
			return "admin/member/searchZipCode";
	}
	//회원정보 수정 처리
	@RequestMapping(value="/memberEditProc.do", method= RequestMethod.POST)
	public String memberEditProc(@ModelAttribute("customerInfo") CustomerInfo customerInfo, BindingResult result, Model model, HttpSession session){
		
		String birth = "1990-11-12";
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
		customerManageService.updateCustomerInf(customerInfo);
		
		
		return "redirect:memberList.do";
	}
	
	//이용약관
	@RequestMapping(value="/memberAgreement.do")
	public String memberAgreement(@ModelAttribute("AdminInfo") AgreementInfo agreementInfo, BindingResult result, Model model, HttpSession session){
		AdminInfo adminSession = (AdminInfo)session.getAttribute("adminSession");
		
		// 세션체크
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}	
		agreementInfo.setAgrType(1);
		agreementInfo=adminManageService.selectAgreementInf(agreementInfo);
		model.addAttribute("agreementInfo",agreementInfo);
		return "admin/member/memberAgreement";
	}
	//이용약관-처리
	@RequestMapping(value="/memberAgreementProc.do", method=RequestMethod.POST)
	public String memberAgreementProc(@ModelAttribute("AdminInfo") AgreementInfo agreementInfo, BindingResult result, Model model, HttpSession session){
		
		AdminInfo adminSession = (AdminInfo)session.getAttribute("adminSession");
		
		// 세션체크
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}	
		
		adminManageService.editAgreementInf(agreementInfo);
		
		return "redirect:memberAgreement.do";
	}
	
	@RequestMapping(value="/memberInfo.do")
	public String personInfo(@ModelAttribute("AdminInfo") AgreementInfo agreementInfo, BindingResult result, Model model, HttpSession session){
		AdminInfo adminSession = (AdminInfo)session.getAttribute("adminSession");
		
		// 세션체크
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}	
		agreementInfo.setAgrType(2);
		agreementInfo=adminManageService.selectAgreementInf(agreementInfo);
		model.addAttribute("agreementInfo",agreementInfo);
		
		return "admin/member/memberInfo";
	}
	//개인보호-처리
		@RequestMapping(value="/memberInfoProc.do", method=RequestMethod.POST)
		public String memberInfoProc(@ModelAttribute("AdminInfo") AgreementInfo agreementInfo, BindingResult result, Model model, HttpSession session){
			
			AdminInfo adminSession = (AdminInfo)session.getAttribute("adminSession");
			
			// 세션체크
			if (adminSession == null) {
				return "redirect:adminLogin.do";
			}	
			
			adminManageService.editAgreementInf(agreementInfo);
			
			return "redirect:memberInfo.do";
		}
		
	@RequestMapping(value="/memberRegist.do", method=RequestMethod.GET)
	public String memberRegist(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result, Model model){
		return "admin/member/memberRegist";
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
