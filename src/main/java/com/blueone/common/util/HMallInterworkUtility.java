package com.blueone.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import net.welfare.HCDESUtil;

import org.apache.commons.lang.StringUtils;
import org.jfree.util.PublicCloneable;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.oreilly.servlet.Base64Decoder;
import com.oreilly.servlet.Base64Encoder;

public class HMallInterworkUtility {
	// 웹서비스 호출과 암복호화를 위한 상수
	private static final String HCDES_KEY = "hd!d$w4shm";	// 암호화키
	private static final String MEDIA_CD = "HM";			// 매체구분(포인트 사용하는 사이트 구분값)
	private static final String ENC_TYPE = "euc-kr";		// 현대몰에서는 인코딩을 euc-kr로 보내준다.
	
	// SSO을 위해 호출할 웹서비스 도메인정보
	private static final String URL = "https://giftdev.e-hyundai.com:1443/hb2efront_new/pointOpenAPI.do?";	// 개발서버
	//private static final String URL = "https://gift.e-hyundai.com:1443/hb2efront_new/pointOpenAPI.do?";	// 운영서버 

	/**
	 * 포인트 사용시 호출하는 함수
	 * @param decMemNm
	 * @param decMemNo
	 * @param decShopEventNo
	 * @param decProcCd
	 * @return
	 */
	public static Map<String, String> procUsePoint(String decMemNm, String decMemNo, String decShopEventNo, String decPoint, String decOrderNo) throws Exception {
		
		String encMemNm = convertEnc(decMemNm);
		String encMemNo = convertEnc(decMemNo);
		String encShopEventNo = convertEnc(decShopEventNo);
		String encPoint = convertEnc(decPoint);
		String encOrderNo = convertEnc(decOrderNo);
		String encProcCd = convertEnc("200");	// 구분코드 : 조회->100, 사용->200, 취소->300
		
		// 호출할 URL을 만든다.
		String invokeUrl = makeInvokeUrl(encMemNm, encMemNo, encShopEventNo, encProcCd, encPoint, encOrderNo);
		
		// --------------------------------------------
		// 포인트사용을 위한 웹서비스 호출
		// --------------------------------------------k
		String rstXml = "";					// 웹서비스 호출 결과
		Map<String, String> rstMap = null;	// XML을 파싱하여 필요한 데이터를 추출한 맵정보
		try {
			rstXml = invokeRemote(invokeUrl);
			rstMap = parsingXML(rstXml);
			
		} catch (Exception e) {
			return null;
			
		} finally {
			System.out.println("[웹서비스 URL] = " + invokeUrl);
			System.out.println("[호출결과] = " + rstXml);
		}

		return rstMap;
	}
	
	/**
	 * 포인트 사용취소시 사용하는 이벤트
	 * @param decMemNm
	 * @param decMemNo
	 * @param decShopEventNo
	 * @param decPoint
	 * @param decOrderNo
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> procCancelPoint(String decMemNm, String decMemNo, String decShopEventNo, String decPoint, String decOrderNo) throws Exception {
		
		String encMemNm = convertEnc(decMemNm);
		String encMemNo = convertEnc(decMemNo);
		String encShopEventNo = convertEnc(decShopEventNo);
		String encPoint = convertEnc(decPoint);
		String encOrderNo = convertEnc(decOrderNo);
		String encProcCd = convertEnc("300");	// 구분코드 : 조회->100, 사용->200, 취소->300
		
		// 호출할 URL을 만든다.
		String invokeUrl = makeInvokeUrl(encMemNm, encMemNo, encShopEventNo, encProcCd, encPoint, encOrderNo);
		
		// --------------------------------------------
		// 포인트사용을 위한 웹서비스 호출
		// --------------------------------------------
		String rstXml = "";					// 웹서비스 호출 결과
		Map<String, String> rstMap = null;	// XML을 파싱하여 필요한 데이터를 추출한 맵정보
		try {
			rstXml = invokeRemote(invokeUrl);
			rstMap = parsingXML(rstXml);
			
		} catch (Exception e) {
			return null;
			
		} finally {
			System.out.println("[웹서비스 URL] = " + invokeUrl);
			System.out.println("[호출결과] = " + rstXml);
		}

		return rstMap;
	}
	
	/**
	 * 포인트 조회하는 함수
	 * @param decMemNm
	 * @param decMemNo
	 * @param decShopEventNo
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> procSearchPoint(String decMemNm, String decMemNo, String decShopEventNo) throws Exception {
		String encMemNm = convertEnc(decMemNm);
		String encMemNo = convertEnc(decMemNo);
		String encShopEventNo = convertEnc(decShopEventNo);

		return procSSO(encMemNm, encMemNo, encShopEventNo);
	}
	
	/**
	 * 로그인시 호출하는 SSO 처리함수
	 * - 현대에서 호출하는 URL의 파라미터를 사용 (암호화된 정보)
	 * @param encMemNm
	 * @param encMemNo
	 * @param encShopEventNo
	 * @param encProcCd
	 * @return
	 */
	public static Map<String, String> procSSO(String encMemNm, String encMemNo, String encShopEventNo) {
		
		// 구분코드 : 조회->100, 사용->200, 취소->300
		String encProcCd = HCDESUtil.encoding(Base64Encoder.encode("100".getBytes()), HCDES_KEY);

		// 호출할 URL을 만든다.
		String invokeUrl = makeInvokeUrl(encMemNm, encMemNo, encShopEventNo, encProcCd, "", "");
		
		// --------------------------------------------
		// SSO처리를 위한 웹서비스 호출
		// --------------------------------------------
		String rstXml = "";					// 웹서비스 호출 결과
		Map<String, String> rstMap = null;	// XML을 파싱하여 필요한 데이터를 추출한 맵정보
		try {
			rstXml = invokeRemote(invokeUrl);
			rstMap = parsingXML(rstXml);
			
		} catch (Exception e) {
			return null;
			
		} finally {
			System.out.println("[웹서비스 URL] = " + invokeUrl);
			System.out.println("[호출결과] = " + rstXml);
		}
		
		return rstMap;
	}
	
	public static String makeInvokeUrl(String encMemNm, String encMemNo, String encShopEventNo, String encProcCd, String encPoint, String encOrderNo) {
		// --------------------------------------------
		// 호출할 URL을 만든다.
		//  - 안에 들어가는 모든 변수는 암호화된 정보여야 한다.
		// --------------------------------------------
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(URL);
		sBuilder.append("chk_data=").append(encMemNm);				// 고객명(체크데이터)
		sBuilder.append("&mem_id=").append(encMemNo);				// 고객아이디
		sBuilder.append("&shopevent_no=").append(encShopEventNo);	// 행사번호
		sBuilder.append("&proc_code=").append(encProcCd);			// 구분코드 (조회:100, 사용:200, 취소:300)
		sBuilder.append("&media_cd=").append(MEDIA_CD);				// 매체코드
		
		if (StringUtils.isNotEmpty(encOrderNo)) {
			sBuilder.append("&order_no=").append(encOrderNo);		// 자사의 주문번호 (포인트 사용내역 대사 및 취소를 위한 값)
		}
		
		if (StringUtils.isNotEmpty(encPoint)) {
			sBuilder.append("&point=").append(encPoint);			// 포인트 (사용 또는 취소할 포인트 금액(콤마없음))
		}
		

		return sBuilder.toString();
	}

	/**
	 * 웹서비스를 이용하여 SSO 호출하기
	 */
	public static String invokeRemote(String urlString) throws IOException, NoSuchAlgorithmException, KeyManagementException {
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
	
	public static Map<String, String> parsingXML(String xml) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
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
	 * 복호화
	 * 현대몰에서는 euc-kr의 인코딩방식으로 데이터를 보내준다.
	 * @return
	 * @throws IOException
	 */
	public static String convertDec(String str) throws IOException {
		byte[] decMemNmBytes = Base64Decoder.decodeToBytes(HCDESUtil.decoding(str, HCDES_KEY));
		return new String(decMemNmBytes, ENC_TYPE);
	}
	
	/**
	 * 암호화
	 * 현대몰에서는 euc-kr의 인코딩방식으로 데이터를 보내준다.
	 * @return
	 * @throws IOException
	 */
	public static String convertEnc(String str) throws IOException {
		String rtn = HCDESUtil.encoding(Base64Encoder.encode(str.getBytes(ENC_TYPE)), HCDES_KEY);
		return rtn;
	}

	
	public static String getErrorMsgByCode(String errorCd) {
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
	
	public static void main(String[] args) throws Exception {
		String str = "%C3%D6%B5%BF%BD%C4";

//		String str = "ÃÖµ¿½Ä";
		
//		System.out.println(URLDecoder.decode(str, "euc-kr"));
//		System.out.println(URLDecoder.decode(str, "utf-8"));
		
		System.out.println(new String(URLDecoder.decode(str, "euc-kr").getBytes(), "utf-8"));
		System.out.println(new String(URLDecoder.decode(str, "euc-kr").getBytes(ENC_TYPE), "utf-8"));
		System.out.println(new String(URLDecoder.decode(str, "euc-kr")));
		
		
	}
}
