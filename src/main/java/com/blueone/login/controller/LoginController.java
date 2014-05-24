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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.blueone.admin.domain.AgreementInfo;
import com.blueone.admin.service.IAdminManageService;
import com.blueone.customer.domain.CustomerInfo;
import com.blueone.customer.domain.CustomerSrchInfo;
import com.blueone.customer.service.ICustomerManageService;
import com.oreilly.servlet.Base64Decoder;
import com.oreilly.servlet.Base64Encoder;

@Controller
public class LoginController {
	@Autowired IAdminManageService adminManageService;
	
	// 웹서비스 호출과 암복호화를 위한 상수
	private static final String HCDES_KEY = "hd!d$w4shm";	// 암호화키
	private static final String MEDIA_CD = "HM";			// 매체구분(포인트 사용하는 사이트 구분값)
	private static final String ENC_TYPE = "euc-kr";		// 현대몰에서는 인코딩을 euc-kr로 보내준다.
	
	// SSO을 위해 호출할 웹서비스 도메인정보
//	private static final String URL = "https://giftdev.e-hyundai.com:1443/hb2efront_new/pointOpenAPI.do?";	// 개발서버
	private static final String URL = "https://gift.e-hyundai.com:1443/hb2efront_new/pointOpenAPI.do?";	// 운영서버 
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
		String encProcCd = HCDESUtil.encoding(Base64Encoder.encode("100".getBytes()), HCDES_KEY);	// 구분코드 : 조회 -> 100, 사용 -> 200, 취소 -> 300
		
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
		// 2. 호출할 URL을 만든다.
		// --------------------------------------------
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(URL);
		sBuilder.append("chk_data=").append(encMemNm);				// 고객명(체크데이터)
		sBuilder.append("&mem_id=").append(encMemNo);				// 고객아이디
		sBuilder.append("&shopevent_no=").append(encShopEventNo);	// 행사번호
		sBuilder.append("&proc_code=").append(encProcCd);			// 구분코드 (조회:100, 사용:200, 취소:300)
		sBuilder.append("&media_cd=").append(MEDIA_CD);				// 매체코드
		
		
		// --------------------------------------------
		// 3. SSO처리를 위한 웹서비스 호출
		// --------------------------------------------
		String rstXml = "";					// 웹서비스 호출 결과
		Map<String, String> rstMap = null;	// XML을 파싱하여 필요한 데이터를 추출한 맵정보
		try {
			rstXml = invokeRemote(sBuilder.toString());
			rstMap = parsingXML(rstXml);
			
		} catch (Exception e) {
			model.addAttribute("msg", "SSO처리시 에러발생하였습니다.");
			return "user/loginError";
			
		} finally {
			System.out.println("[웹서비스 URL] = " + sBuilder.toString());
			System.out.println("[호출결과] = " + rstXml);
		}
		
		// --------------------------------------------
		// 3.1 체크 - SSO처리 결과를 확인한다.
		// --------------------------------------------
		if (rstMap == null) {
			model.addAttribute("msg", "SSO처리 결과가 없습니다.(1)");
			return "user/loginError";
		} else {
			String returnCode = (String)rstMap.get("return_code");
			
			if (!"000".equals(returnCode)) {
				model.addAttribute("msg", getErrorMsgByCode(returnCode));
				return "user/loginError";
			}
		}
		
		// --------------------------------------------
		// 4. HCDES 복호화를 한다.
		// --------------------------------------------
		String decMemNo = convert(encMemNo);
		String decShopNo = convert(encShopNo);
		String decMemNm = convert(encMemNm);
		String decShopEventNo = convert(encShopEventNo);
		String decEntrNo = convert(encEntrNo);
		String decProcCd = convert(encProcCd);
		

		// --------------------------------------------
		// 5. DB조회하여 회원정보가 있는지 확인한다.
		// --------------------------------------------
		CustomerInfo cust = new CustomerInfo();
		cust.setCustId(decMemNo);
		
		CustomerInfo result = customerManageService.getCustomerInfo2(cust);
		
		if(result!=null){
			session.setAttribute("customerSession", result);
			return "shop/main";
		}else{
			// 회원가입시 ID, 이름은 받은 값으로 셋팅하여 화면에 표시한다.
			cust.setCustNm(decMemNm);
			
			List<AgreementInfo> agreementInfo=adminManageService.selectAgreementInfList();
			model.addAttribute("agreementInfo",agreementInfo);
			model.addAttribute("customer",cust);
			
			return "user/userRegister";
		}
	}
	
	/**
	 * 현대몰에서는 euc-kr의 인코딩방식으로 데이터를 보내준다.
	 * @return
	 * @throws IOException
	 */
	private String convert(String str) throws IOException {
		byte[] decMemNmBytes = Base64Decoder.decodeToBytes(HCDESUtil.decoding(str, HCDES_KEY));
		return new String(decMemNmBytes, ENC_TYPE);
	}
	
	private Map<String, String> parsingXML(String xml) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
		Map<String, String> map = new HashMap<String, String>();
		
		// XML Document 객체 생성
        InputSource is = new InputSource(new StringReader(xml));
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
        
        // XPATH 생성하기
        XPath xpath = XPathFactory.newInstance().newXPath();
        
        String returnCode = (String)xpath.evaluate("//return_code", document, XPathConstants.STRING);
        String procCode = (String)xpath.evaluate("//proc_code", document, XPathConstants.STRING);
        String returnPoint = (String)xpath.evaluate("//return_point", document, XPathConstants.STRING);
        
        map.put("return_code", returnCode);
        map.put("proc_code", procCode);
        map.put("return_point", returnPoint);
        
        return map;
	}
	
	
	/**
	 * 웹서비스를 이용하여 SSO 호출하기
	 */
	private String invokeRemote(String urlString) throws IOException, NoSuchAlgorithmException, KeyManagementException {
		URL url = new URL(urlString);  
		HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
		  
		// HTTPS 통신일 경우 인증서 무시로 접속하도록 셋팅
		conn.setHostnameVerifier(new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					// Ignore host name verification. It always returns true.
					return true;
				}
			}
		);
	  
		// SSL setting
		SSLContext context = SSLContext.getInstance("TLS");
		context.init(null, null, null);  // No validation for now
		conn.setSSLSocketFactory(context.getSocketFactory());
		
		// Connect to host
		conn.connect();
		conn.setInstanceFollowRedirects(true);
		
		// Print response from host
		InputStream in = conn.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		
		StringBuilder sBuilder = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			System.out.printf("%s\n", line);
			sBuilder.append(line);
		}
		
		reader.close();
		
		return sBuilder.toString();
	}
	
	private String getErrorMsgByCode(String errorCd) {
		Map<String, String> errorMap = new HashMap<String, String>();
		
		errorMap.put("001", "Input 데이터 타입 에러");
		errorMap.put("002", "존재하지 않는 회원번호");
		errorMap.put("003", "체크데이터(이름) 불일치");
		errorMap.put("004", "존재하지 않는 처리 코드");
		errorMap.put("005", "포인트금액 오류");
		errorMap.put("006", "주문번호 없음");
		errorMap.put("007", "포인트 없음");
		errorMap.put("008", "포인트 조회 에러");
		errorMap.put("009", "잔여포인트 부족");
		errorMap.put("010", "포인트 상세내역 에러(사용)");
		errorMap.put("011", "포인트 정보 없음");
		errorMap.put("012", "이미 취소된 주문");
		errorMap.put("013", "존재하지 않는 주문번호");
		
		return errorMap.get(errorCd);
	}
}
