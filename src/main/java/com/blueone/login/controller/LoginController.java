package com.blueone.login.controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import net.welfare.HCDESUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.blueone.admin.domain.AgreementInfo;
import com.blueone.admin.service.IAdminManageService;
import com.blueone.common.domain.HMallProcAdjustmentInfo;
import com.blueone.common.util.HMallInterworkUtility;
import com.blueone.customer.domain.CustomerInfo;
import com.blueone.customer.domain.CustomerSrchInfo;
import com.blueone.customer.service.ICustomerManageService;
import com.oreilly.servlet.Base64Decoder;
import com.oreilly.servlet.Base64Encoder;

@Controller
public class LoginController {
	@Autowired IAdminManageService adminManageService;
	@Autowired ICustomerManageService customerManageService;
	
	/**
	 * 로그인 모듈
	 * 
	 * example 데이터
	 *   >> https://giftdev.e-hyundai.com:1443/hb2efront_new/pointOpenAPI.do?chk_data=88E47E665935EEFE&point=FA156C018605778E&proc_code=FA156C018605778E&orderNo=7BD4FC20BF5DBD0C&shopevent_no=5E1F456A9D36B942B97F35E597E81E5A&mem_id=B856E909E78FDFA2132E230C3A557FC3&media_cd=HM
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/sso/login.do", method= RequestMethod.GET)
	public String getCustomerInfo(HttpServletRequest request, Model model, HttpSession session) throws Exception {
		
		// --------------------------------------------
		// 1. 변수값을 받아서 확인한다. (암호화된 정보)
		// --------------------------------------------
		String encMemNo = request.getParameter("MEM_NO");				// 고객번호
		String encShopNo = request.getParameter("SHOP_NO");				// 상점번호
		String encMemNm = request.getParameter("MEM_NM");				// 고객명
		String encShopEventNo = request.getParameter("SHOPEVENT_NO");	// 행사번호(자사에서 고객사를 구분 짓기 위한 코드(고정))
		String encEntrNo = request.getParameter("ENTR_NO");				// 고객사번호
		// --------------------------------------------
		// 1.1 체크 - 필요한 값이 모두 있는지 확인한다.
		// --------------------------------------------
		if (StringUtils.isEmpty(encMemNo)) {
			model.addAttribute("msg", "고객아이디가 없습니다.[MEM_NO]");
			return "user/loginError";
		} else if (StringUtils.isEmpty(encShopNo)) {
			model.addAttribute("msg", "상점번호가 없습니다.[SHOP_NO]");
			return "user/loginError";
		} else if (StringUtils.isEmpty(encMemNm)) {
			model.addAttribute("msg", "고객명이 없습니다.[MEM_NM]");
			return "user/loginError";
		} else if (StringUtils.isEmpty(encShopEventNo)) {
			model.addAttribute("msg", "행사번호가 없습니다.[SHOPEVENT_NO]");
			return "user/loginError";
		} else if (StringUtils.isEmpty(encEntrNo)) {
			model.addAttribute("msg", "고객사번호가 없습니다.[ENTR_NO]");
			return "user/loginError";
		}

		// --------------------------------------------
		// 2. SSO처리를 위한 웹서비스 호출
		// --------------------------------------------
		Map<String, String> rstMap = null;
		try {
			rstMap = HMallInterworkUtility.procSSO(encMemNm, encMemNo, encShopEventNo);
		} catch (Exception e) {
			model.addAttribute("msg", "SSO처리시 에러발생하였습니다.");
			return "user/loginError";
		}
		
		// --------------------------------------------
		// 3. 체크 - SSO처리 결과를 확인한다.
		// --------------------------------------------
		if (rstMap == null) {
			model.addAttribute("msg", "SSO처리 결과가 없습니다.(1)");
			return "user/loginError";
		} else {
			String returnCode = (String)rstMap.get("return_code");
			/*
			if (!"000".equals(returnCode)) {
				model.addAttribute("msg", HMallInterworkUtility.getErrorMsgByCode(returnCode));
				
				return "user/loginError";
			}*/
		}
		
		// --------------------------------------------
		// 4. HCDES 복호화를 한다.
		// --------------------------------------------
		String decMemNo = HMallInterworkUtility.convertDec(encMemNo);
		String decMemNm = HMallInterworkUtility.convertDec(encMemNm);
		String decShopNo = HMallInterworkUtility.convertDec(encShopNo);
		String decShopEventNo = HMallInterworkUtility.convertDec(encShopEventNo);
//		String decEntrNo = HMallInterworkUtility.convert(encEntrNo);
//		String decProcCd = HMallInterworkUtility.convert(encProcCd);
		
		
		
		// --------------------------------------------
		// 5. DB조회하여 회원정보가 있는지 확인한다.
		// --------------------------------------------
		CustomerInfo cust = new CustomerInfo();
		cust.setCustId(decMemNo);
		
		CustomerInfo result = customerManageService.getCustomerInfo2(cust);
		
		if(result!=null){
			// 포인트 연동을 위한 이벤트번호, 상점번호 세션에 함께 셋팅한다.
			session.setAttribute("shopEventNo", decShopEventNo);
			session.setAttribute("shopNo", decShopNo);
			
			//포인트 세션에 저장
			Map<String, String> map = HMallInterworkUtility.procSearchPoint(result.getCustNm(), result.getCustId(),decShopEventNo);
			String point = (String)map.get("return_point");
			session.setAttribute("customerPoint", point);
		
			
			// 고객정보를 세션에 저장한다.
			session.setAttribute("customerSession", result);
			
			return "redirect:/";
		}else{
			// 포인트 연동을 위한 이벤트번호, 상점번호 세션에 함께 셋팅한다.
			session.setAttribute("shopEventNo", decShopEventNo);
			session.setAttribute("shopNo", decShopNo);
			
			
			// 회원가입시 ID, 이름은 받은 값으로 셋팅하여 화면에 표시한다.
			cust.setCustNm(decMemNm);
			
			List<AgreementInfo> agreementInfo=adminManageService.selectAgreementInfList();
			model.addAttribute("agreementInfo",agreementInfo);
			model.addAttribute("customer",cust);
			
			return "user/userRegister";
		}
	}
	
	@RequestMapping(value="/sso/usePoint.do", method= RequestMethod.GET)
	public String usePointSample(HttpServletRequest request, Model model, HttpSession session, String orderNo, String usepoint) throws Exception {
		
		String decMemNm = "최동식";
		String decMemNo = "100001639343";
		String decShopEventNo = (String)session.getAttribute("shopEventNo");
		String decPoint = usepoint;
		String decOrderNo = orderNo;
		
		// --------------------------------------------
		// 2. SSO처리를 위한 웹서비스 호출
		// --------------------------------------------
		Map<String, String> rstMap = null;
		try {
			rstMap = HMallInterworkUtility.procUsePoint(decMemNm, decMemNo, decShopEventNo, decPoint, decOrderNo);
		} catch (Exception e) {
			model.addAttribute("msg", "SSO처리시 에러발생하였습니다.");
			return "user/loginError";
		}
		
		// --------------------------------------------
		// 3. 체크 - SSO처리 결과를 확인한다.
		// --------------------------------------------
		if (rstMap == null) {
			model.addAttribute("msg", "SSO처리 결과가 없습니다.(1)");
			return "user/loginError";
		} else {
			String returnCode = (String)rstMap.get("return_code");
			
			if (!"000".equals(returnCode)) {
				model.addAttribute("msg", HMallInterworkUtility.getErrorMsgByCode(returnCode));
				return "user/loginError";
			}
		}
				
		return "";
	}

	@RequestMapping(value="/sso/cancelPoint.do", method= RequestMethod.GET)
	public String cancelPointSample(HttpServletRequest request, Model model, HttpSession session, String orderNo,String usepoint) throws Exception {
		
		String decMemNm = "최동식";
		String decMemNo = "100001639343";
		String decShopEventNo = (String)session.getAttribute("shopEventNo");
		String decPoint = usepoint;
		String decOrderNo = orderNo;
		
		// --------------------------------------------
		// 2. SSO처리를 위한 웹서비스 호출
		// --------------------------------------------
		Map<String, String> rstMap = null;
		try {
			rstMap = HMallInterworkUtility.procCancelPoint(decMemNm, decMemNo, decShopEventNo, decPoint, decOrderNo);
		} catch (Exception e) {
			model.addAttribute("msg", "SSO처리시 에러발생하였습니다.");
			return "user/loginError";
		}
		
		// --------------------------------------------
		// 3. 체크 - SSO처리 결과를 확인한다.
		// --------------------------------------------
		if (rstMap == null) {
			model.addAttribute("msg", "SSO처리 결과가 없습니다.(1)");
			return "user/loginError";
		} else {
			String returnCode = (String)rstMap.get("return_code");
			
			if (!"000".equals(returnCode)) {
				model.addAttribute("msg", HMallInterworkUtility.getErrorMsgByCode(returnCode));
				return "user/loginError";
			}
		}
				
		return "redirect:/";
	}

	@RequestMapping(value="/sso/retrievePoint.do", method= RequestMethod.GET)
	public String retireivelPointSample(HttpServletRequest request, Model model, HttpSession session) throws Exception {
		String decMemNm = "최동식";
		String decMemNo = "100001639343";
		String decShopEventNo = (String)session.getAttribute("shopEventNo");
		String decPoint = "18000";
		String decOrderNo = "BOM14M0688513";
		
		// --------------------------------------------
		// 2. SSO처리를 위한 웹서비스 호출
		// --------------------------------------------
		Map<String, String> rstMap = null;
		try {
			rstMap = HMallInterworkUtility.procSearchPoint(decMemNm, decMemNo, decShopEventNo);
		} catch (Exception e) {
			model.addAttribute("msg", "SSO처리시 에러발생하였습니다.");
			return "user/loginError";
		}
		
		// --------------------------------------------
		// 3. 체크 - SSO처리 결과를 확인한다.
		// --------------------------------------------
		if (rstMap == null) {
			model.addAttribute("msg", "SSO처리 결과가 없습니다.(1)");
			return "user/loginError";
		} else {
			String returnCode = (String)rstMap.get("return_code");
			
			if (!"000".equals(returnCode)) {
				model.addAttribute("msg", HMallInterworkUtility.getErrorMsgByCode(returnCode));
				return "user/loginError";
			}
		}
		
		return "redirect:/";
	}

	@RequestMapping(value="/sso/sendAdjustmentSample.do", method= RequestMethod.GET)
	public String sendAdjustmentSample(HttpServletRequest request, Model model, HttpSession session, String orderNo, String usepoint) throws Exception {
		
		String decMemNm = "최동식";
		String decMemNo = "100001639343";
		String decShopEventNo = (String)session.getAttribute("shopEventNo");
		
		// --------------------------------------------
		// 2. SSO처리를 위한 웹서비스 호출
		// --------------------------------------------
		Map<String, String> rstMap = null;
		try {
			HMallProcAdjustmentInfo info = new HMallProcAdjustmentInfo();
			info.setOrderNo(orderNo);
			info.setItemCd("PRD0001");
			info.setOrderGb("10");
			info.setOrderDm("20140709");
//			info.setShopNo(shopNo);
			info.setShopEventNo(decShopEventNo);
			info.setMemNo(decMemNo);
			info.setTaxGb("1");
			info.setSalePrice("123000");
			info.setPointAmt("100000");
			info.setEtcAmt("20000");
//			info.setDeliAmt("3000");
//			info.setItemNm("");
//			info.setItemPrice(itemPrice);
//			info.setOrderQty(orderQty);
//			info.setDcPrice(dcPrice);
			
			rstMap = HMallInterworkUtility.procAdjustment(info);
		} catch (Exception e) {
			model.addAttribute("msg", "정산 처리시 에러발생하였습니다.");
			return "user/loginError";
		}
		
		// --------------------------------------------
		// 3. 체크 - SSO처리 결과를 확인한다.
		// --------------------------------------------
		if (rstMap == null) {
			model.addAttribute("msg", "SSO처리 결과가 없습니다.(1)");
			return "user/loginError";
		} else {
			String returnCode = (String)rstMap.get("return_code");
			
			if (!"000".equals(returnCode)) {
				model.addAttribute("msg", HMallInterworkUtility.getErrorMsgByCode(returnCode));
			}
		}
				
		return "user/loginError";
	}
		
		
//	/**
//	 * 현대몰에서는 euc-kr의 인코딩방식으로 데이터를 보내준다.
//	 * @return
//	 * @throws IOException
//	 */
//	private String convert(String str) throws IOException {
//		byte[] decMemNmBytes = Base64Decoder.decodeToBytes(HCDESUtil.decoding(str, HCDES_KEY));
//		return new String(decMemNmBytes, ENC_TYPE);
//	}
	
//	private Map<String, String> parsingXML(String xml) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
//		Map<String, String> map = new HashMap<String, String>();
//		
//		// XML Document 객체 생성
//        InputSource is = new InputSource(new StringReader(xml));
//        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
//        
//        // XPATH 생성하기
//        XPath xpath = XPathFactory.newInstance().newXPath();
//        
//        String returnCode = (String)xpath.evaluate("//return_code", document, XPathConstants.STRING);
//        String procCode = (String)xpath.evaluate("//proc_code", document, XPathConstants.STRING);
//        String returnPoint = (String)xpath.evaluate("//return_point", document, XPathConstants.STRING);
//        
//        map.put("return_code", returnCode);
//        map.put("proc_code", procCode);
//        map.put("return_point", returnPoint);
//        
//        return map;
//	}
	
	
//	/**
//	 * 웹서비스를 이용하여 SSO 호출하기
//	 */
//	private String invokeRemote(String urlString) throws IOException, NoSuchAlgorithmException, KeyManagementException {
//		URL url = new URL(urlString);  
//		HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
//		  
//		// HTTPS 통신일 경우 인증서 무시로 접속하도록 셋팅
//		conn.setHostnameVerifier(new HostnameVerifier() {
//				@Override
//				public boolean verify(String hostname, SSLSession session) {
//					// Ignore host name verification. It always returns true.
//					return true;
//				}
//			}
//		);
//	  
//		// SSL setting
//		SSLContext context = SSLContext.getInstance("TLS");
//		context.init(null, null, null);  // No validation for now
//		conn.setSSLSocketFactory(context.getSocketFactory());
//		
//		// Connect to host
//		conn.connect();
//		conn.setInstanceFollowRedirects(true);
//		
//		// Print response from host
//		InputStream in = conn.getInputStream();
//		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//		
//		StringBuilder sBuilder = new StringBuilder();
//		String line = null;
//		while ((line = reader.readLine()) != null) {
//			System.out.printf("%s\n", line);
//			sBuilder.append(line);
//		}
//		
//		reader.close();
//		
//		return sBuilder.toString();
//	}
	
//	private String getErrorMsgByCode(String errorCd) {
//		Map<String, String> errorMap = new HashMap<String, String>();
//		
//		errorMap.put("001", "Input 데이터 타입 에러");
//		errorMap.put("002", "존재하지 않는 회원번호");
//		errorMap.put("003", "체크데이터(이름) 불일치");
//		errorMap.put("004", "존재하지 않는 처리 코드");
//		errorMap.put("005", "포인트금액 오류");
//		errorMap.put("006", "주문번호 없음");
//		errorMap.put("007", "포인트 없음");
//		errorMap.put("008", "포인트 조회 에러");
//		errorMap.put("009", "잔여포인트 부족");
//		errorMap.put("010", "포인트 상세내역 에러(사용)");
//		errorMap.put("011", "포인트 정보 없음");
//		errorMap.put("012", "이미 취소된 주문");
//		errorMap.put("013", "존재하지 않는 주문번호");
//		
//		return errorMap.get(errorCd);
//	}
}
