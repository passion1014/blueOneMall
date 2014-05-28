package com.blueone.user.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.blueone.admin.domain.AdminInfo;
import com.blueone.admin.domain.AgreementInfo;
import com.blueone.admin.domain.ConfigInfo;
import com.blueone.admin.service.IAdminManageService;
import com.blueone.board.domain.FaqInfo;
import com.blueone.board.service.IBoardService;
import com.blueone.common.domain.AttachFileInfo;
import com.blueone.common.domain.SearchAddress;
import com.blueone.common.service.IAttachFileManageService;
import com.blueone.common.util.Utility;
import com.blueone.customer.domain.CustomerInfo;
import com.blueone.customer.domain.RecipientInfo;
import com.blueone.customer.service.ICustomerManageService;
import com.blueone.order.domain.OrderInfo;
import com.blueone.order.domain.OrderProductInfo;
import com.blueone.order.service.IOrderManageService;
import com.blueone.product.domain.ProductInfo;
import com.blueone.product.service.IProductManageService;
import com.blueone.user.domain.UserInfo;
import com.blueone.user.service.IUserService;


@Controller
public class UserController {
	
	@Autowired IOrderManageService orderManageService;
	@Autowired IUserService userService;
	@Autowired ICustomerManageService customerService;
	@Autowired IOrderManageService orderService;
	@Autowired IAdminManageService adminManageService;
	@Autowired private IProductManageService productManageService;
	@Autowired private IAttachFileManageService attFileManageService;
	@Autowired IBoardService boardService;
<<<<<<< HEAD
	
	//회원가입 폼 생성
=======
	//�쉶�썝媛��엯 �뤌 �깮�꽦
>>>>>>> 9162204e971bf8740bde5afa250bb87047c9bccf
	@RequestMapping(value = "/user/userRegister.do", method=RequestMethod.GET)
	public String userRegister(@ModelAttribute("userInfo") UserInfo userInfo,BindingResult result, Model model,HttpSession session){
		
		List<AgreementInfo> agreementInfo=adminManageService.selectAgreementInfList();
		
		model.addAttribute("agreementInfo",agreementInfo);
		
		return "user/userRegister";
	}

	//�쉶�썝媛��엯 泥섎━
	@RequestMapping(value = "/user/userRegisterProc.do", method=RequestMethod.POST)
	public String userRegisterProc(@ModelAttribute("customerInfo") CustomerInfo customerInfo,BindingResult result, Model model, HttpSession session){
		
		String birth ="1999-11-22";
		customerInfo.setCustBirth(birth);
		
		String phone=customerInfo.getTelNo1()+"-"+customerInfo.getTelNo2()+"-"+customerInfo.getTelNo3();
		customerInfo.setCustPh(phone);
		
		String mobile=customerInfo.getHpNo1()+"-"+customerInfo.getHpNo2()+"-"+customerInfo.getHpNo3();
		customerInfo.setCustMb(mobile);
		
		String email = customerInfo.geteMail1()+"@"+customerInfo.geteMail2();
		customerInfo.setCustEmail(email);
		
		// 怨좉컼�벑濡앹쿂由�
		int rst = customerService.registUserInfo(customerInfo);
		if (rst == -1) {
			model.addAttribute("isRegistYn", "N");
			return "user/userRegister";
		}
	
		session.setAttribute("customerSession", customerInfo);
		
		
		return "redirect:/";	
	}

	//留덉씠�럹�씠吏�
	@RequestMapping(value="/user/userEdit.do", method=RequestMethod.GET)
	public String userEdit(@ModelAttribute("userInfo") UserInfo userInfo,BindingResult result, Model model,HttpSession session){
		//CustomerInfo customerSesstion = (CustomerInfo)session.getAttribute("customerSession");	
		CustomerInfo cus= (CustomerInfo)session.getAttribute("customerSession");	
		// �꽭�뀡泥댄겕
		if (cus == null) {
			return "user/errorPage";
		}	
		
		
		
		/*CustomerInfo cus =new CustomerInfo();
		cus.setCustId("100001639343");
		cus=customerService.getCustomerInfo2(cus);
		*/
		String birth = cus.getCustBirth();
//		cus = useStringToken(birth,"b",cus);
		
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

	//留덉씠�럹�씠吏� 泥섎━
	@RequestMapping(value="/user/userEditProc.do", method=RequestMethod.POST)
	public String userEditProc(@ModelAttribute("customerInfo") CustomerInfo customerInfo,BindingResult result, HttpSession session, Model model, SessionStatus status){
		
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
			
		session.setAttribute("customerSession", customerInfo);
	
		return "redirect:userEdit.do";
	}
	
	//�슦�렪踰덊샇 李얘린 �뙘�뾽
	@RequestMapping(value="/user/searchZipCode.do", method=RequestMethod.GET)
	public String searchZipCode(HttpServletRequest request,String type, HttpServletResponse response, ModelMap model) throws Exception {
			
			model.addAttribute("type",type);	
			return "user/searchZipCode";
	}
	//�슦�렪踰덊샇 李얘린 �뙘�뾽
	@RequestMapping(value="/user/searchZipCodeProc.do", method=RequestMethod.GET)
	public String searchZipCodeProc(@ModelAttribute("searchAddress")SearchAddress sAdd,String type, HttpServletRequest request, HttpServletResponse response, Map<String, Object> commandMap, ModelMap model,HttpSession session) throws Exception {
       
		// CustomerInfo customerSesstion =(CustomerInfo)session.getAttribute("customerSession");
		CustomerInfo cus = (CustomerInfo) session
				.getAttribute("customerSession");
		// �꽭�뀡泥댄겕
		if (cus == null) {
			return "user/errorPage";
		}
		
		
	/*	CustomerInfo cus =new CustomerInfo();
		cus.setCustId("100001639343");
		cus=customerService.getCustomerInfo2(cus);
		*/
		String birth = cus.getCustBirth();
//		cus = useStringToken(birth,"b",cus);
		
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
		
		if(type.equals("userEdit")){
			return "user/userEdit";
		}else if(type.equals("userRegi")){
			return "user/userRegister";
		}else{
			return "redirect:/";
		}
		
	}

		
	//二쇱냼 李얘린 action
	@RequestMapping(value="/user/searchAddress.do", method=RequestMethod.GET)
	public String searchAddress(@ModelAttribute("userInfo") UserInfo userInfo,String type,BindingResult result, Model model,String dong) throws ParserConfigurationException, SAXException, IOException{
		dong = new String(dong.getBytes("8859_1"), "UTF-8");
	
		if(!StringUtils.isEmpty(dong)&&!dong.isEmpty()){
			int lastIdx1 = dong.lastIndexOf("동");
			int lastIdx2 = dong.lastIndexOf("읍");
			int lastIdx3 = dong.lastIndexOf("면");
			
<<<<<<< HEAD
				List<SearchAddress> nList =Utility.searchAdd(dong);
			
				model.addAttribute("nList", nList);
				model.addAttribute("type",type);
=======
			if(lastIdx!=-1){
				if(dong.substring(lastIdx1).equals("동")||dong.substring(lastIdx2).equals("읍")||dong.substring(lastIdx3).equals("면"))
					
				
					List<SearchAddress> nList =Utility.searchAdd(dong);
				
					model.addAttribute("nList", nList);
					model.addAttribute("type",type);
					return "user/searchZipCode";
					
				}else{
					model.addAttribute("error","동/읍/면까지 다 입력해주세요");
					return "redirect:/user/searchZipCode.do?type="+type;
				}
>>>>>>> 9162204e971bf8740bde5afa250bb87047c9bccf
				
				return "user/searchZipCode";
			
			}else{
				model.addAttribute("error","동/읍/면까지 다 입력해주세요");
				return "redirect:/user/searchZipCode.do?type="+type;
			}
		}else{
			model.addAttribute("error","다시 입력해주세요");
			return "redirect:/user/searchZipCode.do?type="+type;
		}
	}
	
	//�쟻由쎄툑�쁽�솴
	@RequestMapping(value="/user/userPointSaving.do", method=RequestMethod.GET)
	public String userPointSaving(@ModelAttribute("userInfo") UserInfo userInfo,BindingResult result, Model model,HttpSession session){
<<<<<<< HEAD
		
		CustomerInfo cus= (CustomerInfo)session.getAttribute("customerSession");	
		
		// 세션체크
		if (cus == null) {
			return "user/errorPage";
		}	
			
=======
		//CustomerInfo customerSesstion = (CustomerInfo)session.getAttribute("customerSession");	
				CustomerInfo cus= (CustomerInfo)session.getAttribute("customerSession");	
				// �꽭�뀡泥댄겕
				if (cus == null) {
					return "user/errorPage";
				}	
					
>>>>>>> 9162204e971bf8740bde5afa250bb87047c9bccf
		return "user/userPointSaving";
	}
	//�궗�슜�궡�뿭議고쉶
	@RequestMapping(value="/user/userPoint.do", method=RequestMethod.GET)
	public String userPoint(@ModelAttribute("userInfo") UserInfo userInfo,BindingResult result, Model model,HttpSession session){
		//CustomerInfo customerSesstion = (CustomerInfo)session.getAttribute("customerSession");	
<<<<<<< HEAD
		
		CustomerInfo cus= (CustomerInfo)session.getAttribute("customerSession");	
		
		// 세션체크
		if (cus == null) {
			return "user/errorPage";
		}	
			
		return "user/userPoint";
=======
				CustomerInfo cus= (CustomerInfo)session.getAttribute("customerSession");	
				// �꽭�뀡泥댄겕
				if (cus == null) {
					return "user/errorPage";
				}	
					
			return "user/userPoint";
>>>>>>> 9162204e971bf8740bde5afa250bb87047c9bccf
	}
	

	//二쇰Ц�궡�뿭由ъ뒪�듃
	@RequestMapping(value="/user/orderListView.do")
	public String orderListView(@ModelAttribute("userInfo") UserInfo userInfo,OrderInfo od,BindingResult result, Model model,HttpSession session) {
		
<<<<<<< HEAD
		//아이디 셋팅
=======
		//�븘�씠�뵒 �뀑�똿
		//OrderInfo od = new OrderInfo();
		// CustomerInfo customerSesstion =(CustomerInfo)session.getAttribute("customerSession");
>>>>>>> 9162204e971bf8740bde5afa250bb87047c9bccf
		CustomerInfo cust = (CustomerInfo) session
				.getAttribute("customerSession");
		// �꽭�뀡泥댄겕
		if (cust == null) {
			return "user/errorPage";
		}
						
		od.setCustomerInfo(cust);
		
		//�븘�씠�뵒濡� 二쇰Ц�궡�뿭媛��졇�삤湲�
		List<OrderInfo> odList = orderService.selectOrderInfoList(od);
		
		if(odList!=null && odList.size()>0){
			
		//二쇰Ц肄붾뱶濡� 二쇰Ц�긽�뭹 �젙蹂� 媛��졇�삤湲�
		for(OrderInfo each : odList){
			
			String odNo = each.getOrderNo();
			OrderProductInfo odPrd = new OrderProductInfo();
			odPrd.setOrderNo(odNo);
			List<OrderProductInfo> opResInf = orderService.selectOrderPrdInfo(odPrd);
			
			if(opResInf!=null && opResInf.size()>0){
				odPrd=opResInf.get(0);
				String prdCd = odPrd.getPrdCd();
				ProductInfo prInf = new ProductInfo();
				prInf.setPrdCd(prdCd);
				prInf=productManageService.getProductInfDetail(prInf);
				
				if(prInf !=null){
				//�긽�뭹 �씠由�
					if(opResInf.size()>1){
						odPrd.setPrdNm(prInf.getPrdNm()+"�쇅 "+(opResInf.size()-1)+"媛�");
						
						
					}else{
						odPrd.setPrdNm(prInf.getPrdNm());
						
						
					}
					
					//�닔�웾 諛� 湲덉븸
	
					BigDecimal total=null;
					BigDecimal realTotal=new BigDecimal(0);
					
					for(OrderProductInfo odp:opResInf){
						odp.setSellPrice(new BigDecimal(prInf.getPrdSellPrc()));
						total = new BigDecimal(prInf.getPrdSellPrc()) ;
						total=total.multiply(new BigDecimal(odp.getBuyCnt()));
						realTotal=realTotal.add(total);
					}
					each.setTotalOrderPrice(realTotal);
					
					}
					else{
						
					}
					
					each.setOrdPrd(odPrd);
				
				
				
					
					
					String reg = each.getRegDate();
					int a = reg.indexOf(" ");
					reg= reg.substring(0, a);
					each.setRegDate(reg);
				}
			}
		}else{
			
		}
	
		model.addAttribute("ordList", odList);
		return "user/orderListView";
	}

	//二쇰Ц痍⑥냼�떊泥�
	@RequestMapping(value="/user/orderCancel.do", method=RequestMethod.GET)
	public String orderCancel(@ModelAttribute("orderInfo") OrderInfo orderInfo,BindingResult result, Model model,HttpSession session) {
		
		
		CustomerInfo cust = (CustomerInfo) session.getAttribute("customerSession");
<<<<<<< HEAD
		
		// 세션체크
=======
		// �꽭�뀡泥댄겕
>>>>>>> 9162204e971bf8740bde5afa250bb87047c9bccf
		if (cust == null) {
			return "user/errorPage";
		}
		
		orderInfo.setCustomerInfo(cust);
		orderInfo.setOrderStatCd("07");
		orderService.updateOrderInf(orderInfo);
	
	
		return "redirect:orderListView.do";
	}


	//諛섑뭹�떊泥�
	@RequestMapping(value="/user/orderTakeBack.do", method=RequestMethod.GET)
	public String orderTakeBack(@ModelAttribute("orderInfo") OrderInfo orderInfo,BindingResult result, Model model,HttpSession session) {
		
		
		CustomerInfo cust = (CustomerInfo) session.getAttribute("customerSession");
<<<<<<< HEAD
		
		// 세션체크
=======
		// �꽭�뀡泥댄겕
>>>>>>> 9162204e971bf8740bde5afa250bb87047c9bccf
		if (cust == null) {
			return "user/errorPage";
		}
		
		orderInfo.setCustomerInfo(cust);
		orderInfo.setOrderStatCd("09");
		
		orderService.updateOrderInf(orderInfo);

		return "redirect:orderListView.do";
	}

	//二쇰Ц�긽�꽭�궡�뿭
	@RequestMapping(value="/user/orderDetail.do", method=RequestMethod.GET)
	public String orderDetail(@ModelAttribute("orderInfo") OrderInfo orderInfo,BindingResult result, Model model,HttpSession session) {
		
		
		CustomerInfo cust = (CustomerInfo) session.getAttribute("customerSession");
<<<<<<< HEAD

		// 세션체크
=======
		// �꽭�뀡泥댄겕
>>>>>>> 9162204e971bf8740bde5afa250bb87047c9bccf
		if (cust == null) {
			return "user/errorPage";
		}
		
		//二쇰Ц �젙蹂�
		orderInfo.setCustomerInfo(cust);
		List<OrderInfo> odList = orderService.selectOrderInfoList(orderInfo);
		model.addAttribute("odInfo",odList.get(0));
		
		//寃곗젣�긽�뭹 蹂댁뿬二쇨린
		String odNo=orderInfo.getOrderNo();
			
			
		OrderProductInfo opRes = new OrderProductInfo();
		opRes.setOrderNo(orderInfo.getOrderNo());
		List<OrderProductInfo> opResInf = orderManageService.selectOrderPrdInfo(opRes);
		
		for(OrderProductInfo each : opResInf){
			String prdCd = each.getPrdCd();
			ProductInfo prInf = new ProductInfo();
			prInf.setPrdCd(prdCd);
			prInf=productManageService.getProductInfDetail(prInf);
			
			//�긽�뭹 �씠由�
			each.setPrdNm(prInf.getPrdNm());
			
			//�샃�뀡
			String option=each.getPrdOption();
			StringTokenizer st = new StringTokenizer(option,",");
			while(st.hasMoreElements()) {
					
					String s = st.nextToken();
					
					if("01".equals(s.substring(0, 2))){
						option+=s+",";
						each.setPrdOpColor(s.substring(3));
					}
					if("02".equals(s.substring(0, 2))){
						option+=s+",";
						each.setPrdOpSize(s.substring(3));
					}
			
			}
			
			//�닔�웾 諛� 湲덉븸
			each.setSellPrice(new BigDecimal(prInf.getPrdSellPrc()));
			BigDecimal total = new BigDecimal(prInf.getPrdSellPrc()) ;
			total=total.multiply(new BigDecimal(each.getBuyCnt()));
			each.setTotalPrice(total);
			
			//�궗吏�
			AttachFileInfo att = new AttachFileInfo();
			att.setAttCdKey(prInf.getPrdCd());
			att.setAttImgType("01");
			att = attFileManageService.getAttFileInfListImg(att);
			if(att==null){
				each.setPrdSmallImg("");
			}else { 
				
				each.setPrdSmallImg(att.getAttFilePath());
			}
			
		}
		model.addAttribute("odPrdInfo",opResInf);
		
		RecipientInfo reInf = new RecipientInfo();
		reInf.setReciOdNum(odNo);
		reInf = orderManageService.selectRecipientInfo(reInf);
		model.addAttribute("reInfo",reInf);
			

		//諛곗넚鍮꾧��젴 �젙蹂�
		ConfigInfo resConfigInfo = adminManageService.selectConfigInf();
		
		model.addAttribute("config", resConfigInfo);
		
		return "user/orderDetail";
	}
<<<<<<< HEAD
	
	//1:1문의하기 목록
=======
	//1:1臾몄쓽�븯湲� 紐⑸줉
>>>>>>> 9162204e971bf8740bde5afa250bb87047c9bccf
	@RequestMapping(value="/user/qnaList.do", method=RequestMethod.GET)
	public String qnaList(@ModelAttribute("userInfo") UserInfo userInfo,BindingResult result, Model model,HttpSession session){
		// CustomerInfo customerSesstion =(CustomerInfo)session.getAttribute("customerSession");
		CustomerInfo cust = (CustomerInfo) session.getAttribute("customerSession");
		// �꽭�뀡泥댄겕
		if (cust == null) {
			return "user/errorPage";
		}
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
