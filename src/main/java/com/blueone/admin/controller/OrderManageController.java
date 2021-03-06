package com.blueone.admin.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blueone.admin.domain.AdImgInfo;
import com.blueone.admin.domain.AdminInfo;
import com.blueone.admin.domain.ConfigInfo;
import com.blueone.admin.service.IAdminManageService;
import com.blueone.admin.service.IOrderService;
import com.blueone.common.domain.AttachFileInfo;
import com.blueone.common.domain.HMallProcAdjustmentInfo;
import com.blueone.common.service.IAttachFileManageService;
import com.blueone.common.util.DateUtil;
import com.blueone.common.util.FileUploadUtility;
import com.blueone.common.util.HMallInterworkUtility;
import com.blueone.common.util.PageDivision;
import com.blueone.customer.domain.CustomerInfo;
import com.blueone.customer.domain.RecipientInfo;
import com.blueone.customer.service.ICustomerManageService;
import com.blueone.order.domain.OrderInfo;
import com.blueone.order.domain.OrderProductInfo;
import com.blueone.order.domain.OrderSrchInfo;
import com.blueone.order.domain.PaymentInfo;
import com.blueone.order.service.IOrderManageService;
import com.blueone.product.domain.ProductInfo;
import com.blueone.product.service.IProductManageService;

import java.io.File;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

@Controller
@RequestMapping(value = "/admin")
public class OrderManageController {
	@Autowired IOrderManageService orderService;
	@Autowired IOrderManageService orderManageService;
	@Autowired private IProductManageService productManageService;
	@Autowired private IAttachFileManageService attFileManageService;
	@Autowired ICustomerManageService customerManageService;
	@Autowired IAdminManageService adminManageService;
	
	 /** 다운로드 버퍼 크기 */
	  private static final int BUFFER_SIZE = 8192; // 8kb
	 
	  /** 문자 인코딩 */
	  private static final String CHARSET = "UTF-8";
	@RequestMapping(value="/orderList.do", method= RequestMethod.GET)
	public String orderList(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result, Model model,HttpSession session,String page){

		AdminInfo adminSession = (AdminInfo) session.getAttribute("adminSession");

		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}
		
		OrderInfo os = new OrderInfo();
		
		
		//List<OrderInfo> odList =orderManageService.getOrderInfoListByPeriod(os);
		os.setCustomerInfo(new CustomerInfo());
		List<OrderInfo> odList = orderManageService.selectOrderInfoList(os);
		
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
						odPrd.setPrdNm(prInf.getPrdNm()+"외 "+(opResInf.size()-1)+"개");
						
						
					}else{
						odPrd.setPrdNm(prInf.getPrdNm());
						
						
					}
					
					
					PaymentInfo pay = new PaymentInfo();
					pay.setOrderNo(odNo);
					List<PaymentInfo> payList = orderManageService.selectPaymentInfo(pay);
					if(payList.size()>0)
						each.setPaymentInfo(payList.get(0));
					
					}
					
					
					each.setOrdPrd(odPrd);
				
				
				
					
					
					String reg = each.getRegDate();
					int a = reg.indexOf(" ");
					reg= reg.substring(0, a);
					each.setRegDate(reg);
				}
			}
		}
		PageDivision pd = new PageDivision();
		
		if (StringUtils.isEmpty(page)){
			pd.pageNum("1");
			page="1";
		}	
		else
			pd.pageNum(page);
		pd.setItemNum(10);
		pd.setOrderInfoList(odList);
		
		
		model.addAttribute("odList",pd.getOrderInfoList());
		model.addAttribute("page", page);
	
		model.addAttribute("endNum", pd.getEndPageNum());
		model.addAttribute("orderStatCd","");
		return "admin/order/orderList";
	}
	
	@RequestMapping(value="/orderListToExel.do", method= RequestMethod.GET)
	public String orderListToExel(@ModelAttribute("AdminInfo") AdminInfo adminInfo, OrderInfo orderInfo,BindingResult result, Model model,HttpSession session,String page,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		 //------------------------------
		 //엑셀파일 생성
		 //------------------------------
		 //String filepath = "D:/BlueOne/"+DateUtil.getDate("yyyyMMddHHmmss")+"HM_LIST.xls"; //개발
		 String filepath = "/home/hosting_users/blueonestore/tomcat/webapps/ROOT/resources/upload/"+DateUtil.getDate("yyyyMMddHHmmss")+"HM_LIST.xls"; //운영
	
	
		    try {
		    	
		        HSSFWorkbook workbook = new HSSFWorkbook();
	
	
		        HSSFSheet sheet = workbook.createSheet();
		       // workbook.setSheetName(0 , "한글명" ,HSSFWorkbook.ENCODING_UTF_16);
	
	
		       HSSFCellStyle style = workbook.createCellStyle();
		       /*   style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		        style.setBottomBorderColor(HSSFColor.BLACK.index);
		        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		        style.setLeftBorderColor(HSSFColor.GREEN.index);
		        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		        style.setRightBorderColor(HSSFColor.BLUE.index);
		        style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM_DASHED);
		        style.setTopBorderColor(HSSFColor.BLACK.index);           */
	
	
		        HSSFRow row = sheet.createRow(0);
		       
		  
	            HSSFCell cell = row.createCell((short)0);
	            cell.setCellStyle(style);
	            cell.setCellValue("주문번호(주문일)");     
	            
	            cell = row.createCell((short)1);
	            cell.setCellStyle(style);
	            cell.setCellValue("주문금액");     
	            
	            cell = row.createCell((short)2);
	            cell.setCellStyle(style);
	            cell.setCellValue("우편번호");  
	            
	            cell = row.createCell((short)3);
	            cell.setCellStyle(style);
	            cell.setCellValue("주소");  
	            
	            cell = row.createCell((short)4);
	            cell.setCellStyle(style);
	            cell.setCellValue("주문인"); 
	            
	            cell = row.createCell((short)5);
	            cell.setCellStyle(style);
	            cell.setCellValue("연락처(주문인)"); 
	            
	            cell = row.createCell((short)6);
	            cell.setCellStyle(style);
	            cell.setCellValue("수취인");  
	            
	            cell = row.createCell((short)7);
	            cell.setCellStyle(style);
	            cell.setCellValue("전화번호(수취인)");   
	            
	            cell = row.createCell((short)8);
	            cell.setCellStyle(style);
	            cell.setCellValue("핸드폰(수취인)");   
	            
	            cell = row.createCell((short)9);
	            cell.setCellStyle(style);
	            cell.setCellValue("상품코드");   
	            
	            cell = row.createCell((short)10);
	            cell.setCellStyle(style);
	            cell.setCellValue("상품명");   
	            
	            cell = row.createCell((short)11);
	            cell.setCellStyle(style);
	            cell.setCellValue("옵션");   
	            
	            cell = row.createCell((short)12);
	            cell.setCellStyle(style);
	            cell.setCellValue("수량");   
	           
	            cell = row.createCell((short)13);
	            cell.setCellStyle(style);
	            cell.setCellValue("배송메세지");   
	            
	            cell = row.createCell((short)14);
	            cell.setCellStyle(style);
	            cell.setCellValue("입금일자");   
	           
	            cell = row.createCell((short)15);
	            cell.setCellStyle(style);
	            cell.setCellValue("발송일자");   
	            
	            cell = row.createCell((short)16);
	            cell.setCellStyle(style);
	            cell.setCellValue("송장번호");   
	           
	            cell = row.createCell((short)17);
	            cell.setCellStyle(style);
	            cell.setCellValue("결제방법");   
	            
	            cell = row.createCell((short)18);
	            cell.setCellStyle(style);
	            cell.setCellValue("택배사");   
	           
	            cell = row.createCell((short)19);
	            cell.setCellStyle(style);
	            cell.setCellValue("주문상태"); 
	            
	            cell = row.createCell((short)20);
	            cell.setCellStyle(style);
	            cell.setCellValue("상품금액");   
	            
	            cell = row.createCell((short)21);
	            cell.setCellStyle(style);
	            cell.setCellValue("정산예정금액");   
	            
	            cell = row.createCell((short)22);
	            cell.setCellStyle(style);
	            cell.setCellValue("승인번호");   
	            
	            
	           
	            
	            
	           // orderInfo.setReciInfo(new RecipientInfo());
	
	            List<OrderInfo> odList =orderManageService.selectListBomOrderTbToExel0001(orderInfo);
	            
		        int i=1;
	            for (OrderInfo each : odList){
	           
	            	row = sheet.createRow(i);
	            	
	            	//주문번호(주문일)
		            cell = row.createCell((short)0);
		            cell.setCellValue(each.getOrderNo()+"("+each.getOrderDate()+")");     
		            
		            //주문금액
		            cell = row.createCell((short)1);
			        cell.setCellValue(each.getPaymentInfo().getPayPrice().toString());   
			        
			        //우편번호
		            cell = row.createCell((short)2);
		            if(each.getReciInfo().getReciAdd().length()>6)
		            	cell.setCellValue(each.getReciInfo().getReciAdd().substring(0,7)); 
		            else
		            	cell.setCellValue(""); 
		            //주소
		            cell = row.createCell((short)3);
		            if(each.getReciInfo().getReciAdd().length()>6)
		            	cell.setCellValue(each.getReciInfo().getReciAdd().substring(8)); 
		            else
		            	cell.setCellValue(""); 
		            
		            //주문인
		            cell = row.createCell((short)4);
		            cell.setCellValue(each.getCustomerInfo().getCustNm()); 
		            
		            //주문인(연락처)
		            cell = row.createCell((short)5);
		            cell.setCellValue(each.getCustomerInfo().getCustMb()); 
		            
		            //수취인
		            cell = row.createCell((short)6);
		            cell.setCellValue(each.getReciInfo().getReciNm()); 
		            
		            //수취인(전화번호)
		            cell = row.createCell((short)7);
		            cell.setCellValue(each.getReciInfo().getReciPh()); 
		            
		            //수취인(핸드폰)
		            cell = row.createCell((short)8);
		            cell.setCellValue(each.getReciInfo().getReciMb()); 
		            
		            //상품코드
		            cell = row.createCell((short)9);
		            cell.setCellValue(each.getOrdPrd().getPrdCd()); 
		            
		            //상품명
		            cell = row.createCell((short)10);
		            cell.setCellValue(each.getOrdPrd().getPrdNm()); 
		            
		            //옵션
		            cell = row.createCell((short)11);
		            if(each.getOrdPrd() != null && each.getOrdPrd().getPrdOpColor() !=null && !each.getOrdPrd().getPrdOpColor().isEmpty()) {
		            	String option = each.getOrdPrd().getPrdOpColor();
		            	option = option.replaceAll("01=", "색상:");
		            	option = option.replaceAll("02=", "크기:");
		            	cell.setCellValue(option);
		            }
		            else cell.setCellValue("");
		            
		            //수량
		            cell = row.createCell((short)12);
		            cell.setCellValue(each.getOrdPrd().getBuyCnt());
		            
		            //배송메세지
		            cell = row.createCell((short)13);
		            cell.setCellValue(each.getReciInfo().getReciReq());
		            
		            //입금일자
		            cell = row.createCell((short)14);
		            cell.setCellValue(each.getPaymentInfo().getPayDate());
		            
		            //발송일자
		            cell = row.createCell((short)15);
		            cell.setCellValue("");
		            
		            //송장번호
		            cell = row.createCell((short)16);
		            cell.setCellValue(each.getOrdTransNo());
		            
		            //결제방법
		            cell = row.createCell((short)17);
		            String payment="";
		            if(each.getPaymentInfo().getPayMdCd()!=null && each.getPaymentInfo().getPayMdCd().equals("100000000000"))     payment="신용카드";
		            else if(each.getPaymentInfo().getPayMdCd()!=null && each.getPaymentInfo().getPayMdCd().equals("010000000000"))payment="계좌이체";
		            else if(each.getPaymentInfo().getPayMdCd()!=null && each.getPaymentInfo().getPayMdCd().equals("000100000000"))payment="복지카드";
		            else if(each.getPaymentInfo().getPayMdCd()!=null && each.getPaymentInfo().getPayMdCd().equals("000000000001"))payment="포인트";
		            cell.setCellValue(payment);   
		            
		            //택배사
		            cell = row.createCell((short)18);
		            cell.setCellValue("");
		 
		            //주문상태
		            cell = row.createCell((short)19);
		            String orderState = "";
		            if(each.getOrderStatCd().equals("01"))orderState="신청대기";
					else if(each.getOrderStatCd().equals("02"))orderState="주문완료";
					else if(each.getOrderStatCd().equals("07"))orderState="취소신청";
					else if(each.getOrderStatCd().equals("08"))orderState="취소완료";
					else if(each.getOrderStatCd().equals("03"))orderState="배송준비";
					else if(each.getOrderStatCd().equals("04"))orderState="배송중";
					else if(each.getOrderStatCd().equals("05"))orderState="배송완료";
					else if(each.getOrderStatCd().equals("09"))orderState="반품신청";
					else if(each.getOrderStatCd().equals("10"))orderState="반품신청완료";
		            cell.setCellValue(orderState);  
		            
		            //상품금액
		            cell = row.createCell((short)20);
		            cell.setCellValue(each.getOrdPrd().getSellPrice().toString());
		            //정산예정금액
		            cell = row.createCell((short)21);
		            cell.setCellValue(each.getPaymentInfo().getPayPrice().toString());
		            
		          //정산예정금액
		            cell = row.createCell((short)21);
		            cell.setCellValue(each.getCardInfo1());
		            
		            i++;
		        }
		        FileOutputStream fs = null;
		        try { 
		            fs = new FileOutputStream(filepath);
		            workbook.write(fs);
		            
		        } catch (Exception e) {
		        } finally {
		            if (fs != null) fs.close();
		        }
		        
		    } catch (Exception e) {
	
		        
		        e.printStackTrace();
		    }    
	    
		 //------------------------------
		 //파일 다운로드
		 //------------------------------
		  File file = new File(filepath);
		  String mimetype = request.getSession().getServletContext().getMimeType(file.getName());
		  InputStream is = null;
		  
		  try {
			  is = new FileInputStream(file);
			  download(request, response, is, file.getName(), file.length(), mimetype);
		  } finally {
			  try {
				  	is.close();
			  } catch (Exception ex) {
			  }
		  }
		//------------------------------
		 //파일 삭제
		 //------------------------------
		  file.delete();
		  
		return "redirect:orderList.do";
	}
	
	//관리 페이지
	@RequestMapping(value="/orderManagement.do", method= RequestMethod.GET)
	public String orderManagement(@ModelAttribute("AdminInfo") AdminInfo adminInfo,OrderInfo orderInfo,CustomerInfo customerInfo, BindingResult result, Model model,HttpSession session){

		AdminInfo adminSession = (AdminInfo) session.getAttribute("adminSession");

		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}

		CustomerInfo cus=customerManageService.getCustomerInfo2(customerInfo);
		orderInfo.setCustomerInfo(cus);
		List<OrderInfo> odList = orderService.selectOrderInfoList(orderInfo);
		model.addAttribute("odInfo",odList.get(0));
		model.addAttribute("cus",cus);
		
		//결제상품 보여주기
		String odNo=orderInfo.getOrderNo();
			
			
		OrderProductInfo opRes = new OrderProductInfo();
		opRes.setOrderNo(orderInfo.getOrderNo());
		List<OrderProductInfo> opResInf = orderManageService.selectOrderPrdInfo(opRes);
		
		for(OrderProductInfo each : opResInf){
			String prdCd = each.getPrdCd();
			ProductInfo prInf = new ProductInfo();
			prInf.setPrdCd(prdCd);
			prInf=productManageService.getProductInfDetail(prInf);
			
			//상품 이름
			each.setPrdNm(prInf.getPrdNm());
			
			//옵션
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
			
			//수량 및 금액
			each.setSellPrice(new BigDecimal(prInf.getPrdSellPrc()));
			BigDecimal total = new BigDecimal(prInf.getPrdSellPrc()) ;
			total=total.multiply(new BigDecimal(each.getBuyCnt()));
			each.setTotalPrice(total);
			
			//사진
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
		
		//배송비관련 정보
		ConfigInfo resConfigInfo = adminManageService.selectConfigInf();
		model.addAttribute("config", resConfigInfo);
		
		
		//결제 정보
		PaymentInfo pay = new PaymentInfo();
		pay.setOrderNo(odNo);
		List<PaymentInfo> payList = orderManageService.selectPaymentInfo(pay);
		model.addAttribute("payList", payList);
		
		return "admin/order/orderManagement";
	}
	
	@RequestMapping(value="orderManagementProc.do", method=RequestMethod.POST)
	public String orderManagementProc(@ModelAttribute("orderInfo") OrderInfo orderInfo,BindingResult result, Model model,HttpSession session,RedirectAttributes redirectAttributes) throws UnsupportedEncodingException {
		
		

		orderService.updateOrderInf(orderInfo);
		CustomerInfo cust = orderInfo.getCustomerInfo();
		
		if(orderInfo.getOrderStatCd().equals("08") || orderInfo.getOrderStatCd().equals("10")){
			List<OrderInfo> odList = orderService.selectOrderInfoList(orderInfo);
			String pointInfo= odList.get(0).getUserPointInfo();
			String[] point = pointInfo.split("_");
			
			if(point.length>1){
				if(point[1]!=null ||  !StringUtils.isEmpty(point[1]) || !point[1].isEmpty() ){
						
					String decMemNm = point[0];
					String decMemNo = point[1];
					String decShopEventNo = point[2];
					String decPoint = point[3];
					String decOrderNo = orderInfo.getOrderNo();
					
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
				}
			}
			//재고증가
			OrderProductInfo opRes = new OrderProductInfo();
			opRes.setOrderNo(orderInfo.getOrderNo());
			List<OrderProductInfo> opResInf = orderManageService.selectOrderPrdInfo(opRes);
			
			for(OrderProductInfo each : opResInf){
				String prdCd = each.getPrdCd();
				ProductInfo productInfo = new ProductInfo();
				productInfo.setPrdCd(prdCd);
				productInfo = productManageService.getProductInfDetail(productInfo);
				productInfo.setPrdStock(productInfo.getPrdStock()+each.getBuyCnt());
				productManageService.manageProductInf(productInfo);
			}
			
			
			List<OrderInfo> orderList = orderService.selectListBomOrderTbToExel0002(orderInfo);
			
			for(OrderInfo each : orderList){
				
				if(pointInfo==null ||  StringUtils.isEmpty(pointInfo) || pointInfo.isEmpty() ){
					redirectAttributes.addFlashAttribute("orderSucess", "yes");
					return "redirect:orderList.do";
				}
				Map<String, String> rstMap = null;
				HMallProcAdjustmentInfo adjustment = new HMallProcAdjustmentInfo();
				try {
					// --------------------------------------------
					// 1. 정산 처리 
					// --------------------------------------------
					
					adjustment.setOrderNo(orderInfo.getOrderNo());
					adjustment.setItemCd(each.getOrdPrd().getPrdCd());
					adjustment.setOrderGb("20");
					adjustment.setOrderDm(DateUtil.getDate("yyyyMMdd"));
					adjustment.setShopNo(each.getShopno());
					adjustment.setShopEventNo(each.getShopevent());
					adjustment.setMemNo(each.getCustomerInfo().getCustId());
					adjustment.setTaxGb("1");
					adjustment.setSalePrice(each.getOrdPrd().getSellPrice().multiply(new BigDecimal(each.getOrdPrd().getBuyCnt())).toString());
					adjustment.setPointAmt(Integer.toString(each.getPaymentInfo().getPayPoint()/orderList.size()));
					BigDecimal etc = each.getOrdPrd().getSellPrice();
					etc =etc.subtract(new BigDecimal(each.getPaymentInfo().getPayPoint()/orderList.size()));
					adjustment.setEtcAmt(etc.toString());
					adjustment.setDeliAmt("0");
					String option = each.getOrdPrd().getPrdOpColor();
					if(option == null || option.isEmpty()) adjustment.setItemNm(each.getOrdPrd().getPrdNm());
					else adjustment.setItemNm(each.getOrdPrd().getPrdNm()+option.substring(3, option.indexOf(",")));
		        	adjustment.setItemPrice(each.getOrdPrd().getSellPrice().toString());
		        	adjustment.setOrderQty(Integer.toString(each.getOrdPrd().getBuyCnt()));
		        	adjustment.setDcPrice("0");
		        	
		        	
		        	rstMap = HMallInterworkUtility.procAdjustment(adjustment);
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
					String date = adjustment.getOrderDm()+"("+DateUtil.getDate("h:mm a")+")";
					adjustment.setOrderDm(date);
					adjustment.setReturnCode(returnCode);
					orderService.insertBomHMTb0001(adjustment);
					
					if (!"000".equals(returnCode)) {
						model.addAttribute("msg", HMallInterworkUtility.getErrorMsgByCode(returnCode));
					}
		
					
				}
			}
			
			//결제 자동취소
			if(!odList.get(0).getTno().equals(" ")){
				model.addAttribute("req_tx","mod");
				model.addAttribute("tno",odList.get(0).getTno());
				model.addAttribute("orderInfo",orderInfo);
			}
			return "admin/order/cancel";
			
			
		}
		/*
		if(orderInfo.getOrderStatCd().equals("06") ){
			List<OrderInfo> orderList = orderService.selectListBomOrderTbToExel0001(orderInfo);
			
			for(OrderInfo each : orderList){
				String pointInfo= each.getUserPointInfo();
				
				if(pointInfo==null ||  StringUtils.isEmpty(pointInfo) || pointInfo.isEmpty() ){
					redirectAttributes.addFlashAttribute("orderSucess", "yes");
					return "redirect:orderManagement.do?orderNo="+orderInfo.getOrderNo()+"&custId="+cust.getCustId();
				}
				String[] point = pointInfo.split("_");
				Map<String, String> rstMap = null;
				HMallProcAdjustmentInfo adjustment = new HMallProcAdjustmentInfo();
				try {
					// --------------------------------------------
					// 1. 정산 처리 
					// --------------------------------------------
					
					adjustment.setOrderNo(orderInfo.getOrderNo());
					adjustment.setItemCd(each.getOrdPrd().getPrdCd());
					adjustment.setOrderGb("10");
					adjustment.setOrderDm(DateUtil.getDate("yyyyMMdd"));
					adjustment.setShopNo( point[4]);
					adjustment.setShopEventNo( point[2]);
					adjustment.setMemNo(point[1]);
					adjustment.setTaxGb("1");
					adjustment.setSalePrice(each.getOrdPrd().getSellPrice().multiply(new BigDecimal(each.getOrdPrd().getBuyCnt())).toString());
					adjustment.setPointAmt(Integer.toString(each.getPaymentInfo().getPayPoint()));
					BigDecimal etc = each.getPaymentInfo().getPayPrice();
					etc =etc .subtract(new BigDecimal(each.getPaymentInfo().getPayPoint()));
					adjustment.setEtcAmt(etc.toString());
					adjustment.setDeliAmt("0");
					String option = each.getOrdPrd().getPrdOpColor();
		        	adjustment.setItemNm(each.getOrdPrd().getPrdNm()+option.substring(3, option.indexOf(",")));
		        	adjustment.setItemPrice(each.getOrdPrd().getSellPrice().toString());
		        	adjustment.setOrderQty(Integer.toString(each.getOrdPrd().getBuyCnt()));
		        	adjustment.setDcPrice("0");
		        	
		        	rstMap = HMallInterworkUtility.procAdjustment(adjustment);
		        	
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
					String date = adjustment.getOrderDm()+"("+DateUtil.getDate("h:mm a")+")";
					adjustment.setOrderDm(date);
					adjustment.setReturnCode(returnCode);
					orderService.insertBomHMTb0001(adjustment);
					
					if (!"000".equals(returnCode)) {
						model.addAttribute("msg", HMallInterworkUtility.getErrorMsgByCode(returnCode));
					}
				}
			}
			
		}
		*/
		return "redirect:orderManagement.do?orderNo="+orderInfo.getOrderNo()+"&custId="+cust.getCustId();
	}
	@RequestMapping(value="/orderStateEdit.do")
	public String orderStateEdit(@ModelAttribute("orderInfo") OrderInfo ordInfo,BindingResult result, Model model,HttpSession session,RedirectAttributes redirectAttributes) throws UnsupportedEncodingException {
		
		StringTokenizer st = new StringTokenizer(ordInfo.getOrd_unit_chk(), ",");
		
		while(st.hasMoreElements()){
			String ordNo = st.nextToken();
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setOrderNo(ordNo);
			orderInfo.setCustomerInfo(new CustomerInfo());
			orderInfo.setOrderStatCd(ordInfo.getOrderStatCd());
			orderService.updateOrderInf(orderInfo);
			
			
			if(ordInfo.getOrderStatCd().equals("08") || ordInfo.getOrderStatCd().equals("10")){
				List<OrderInfo> odList = orderService.selectOrderInfoList(orderInfo);
				String pointInfo= odList.get(0).getUserPointInfo();
				String[] point = pointInfo.split("_");
				
				//포인트 취소
				if(point[1]!=null ||  !StringUtils.isEmpty(point[1]) || !point[1].isEmpty() ){
					String decMemNm = point[0];
					String decMemNo = point[1];
					String decShopEventNo = point[2];
					String decPoint = point[3];
					String decOrderNo = orderInfo.getOrderNo();
					
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
				}
				//재고증가
				OrderProductInfo opRes = new OrderProductInfo();
				opRes.setOrderNo(orderInfo.getOrderNo());
				List<OrderProductInfo> opResInf = orderManageService.selectOrderPrdInfo(opRes);
				
				for(OrderProductInfo each : opResInf){
					String prdCd = each.getPrdCd();
					ProductInfo productInfo = new ProductInfo();
					productInfo.setPrdCd(prdCd);
					productInfo = productManageService.getProductInfDetail(productInfo);
					productInfo.setPrdStock(productInfo.getPrdStock()+each.getBuyCnt());
					productManageService.manageProductInf(productInfo);
				}
				
				
				List<OrderInfo> orderList = orderService.selectListBomOrderTbToExel0001(orderInfo);
				
				for(OrderInfo each : orderList){
					
					if(pointInfo==null ||  StringUtils.isEmpty(pointInfo) || pointInfo.isEmpty() ){
						redirectAttributes.addFlashAttribute("orderSucess", "yes");
						return "redirect:orderList.do";
					}

					Map<String, String> rstMap = null;
					HMallProcAdjustmentInfo adjustment = new HMallProcAdjustmentInfo();
					try {
						// --------------------------------------------
						// 1. 정산 처리 
						// --------------------------------------------
						
						adjustment.setOrderNo(orderInfo.getOrderNo());
						adjustment.setItemCd(each.getOrdPrd().getPrdCd());
						adjustment.setOrderGb("20");
						adjustment.setOrderDm(DateUtil.getDate("yyyyMMdd"));
						adjustment.setShopNo(each.getShopno());
						adjustment.setShopEventNo(each.getShopevent());
						adjustment.setMemNo(each.getCustomerInfo().getCustId());
						adjustment.setTaxGb("1");
						adjustment.setSalePrice(each.getOrdPrd().getSellPrice().multiply(new BigDecimal(each.getOrdPrd().getBuyCnt())).toString());
						adjustment.setPointAmt(Integer.toString(each.getPaymentInfo().getPayPoint()/orderList.size()));
						BigDecimal etc = each.getOrdPrd().getSellPrice();
						etc =etc.subtract(new BigDecimal(each.getPaymentInfo().getPayPoint()/orderList.size()));
						adjustment.setEtcAmt(etc.toString());
						adjustment.setDeliAmt("0");
						String option = each.getOrdPrd().getPrdOpColor();
						if(option == null || option.isEmpty()) adjustment.setItemNm(each.getOrdPrd().getPrdNm());
						else adjustment.setItemNm(each.getOrdPrd().getPrdNm()+option.substring(3, option.indexOf(",")));
			        	adjustment.setItemPrice(each.getOrdPrd().getSellPrice().toString());
			        	adjustment.setOrderQty(Integer.toString(each.getOrdPrd().getBuyCnt()));
			        	adjustment.setDcPrice("0");
			        	
			        	
			        	rstMap = HMallInterworkUtility.procAdjustment(adjustment);
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
						String date = adjustment.getOrderDm()+"("+DateUtil.getDate("h:mm a")+")";
						adjustment.setOrderDm(date);
						adjustment.setReturnCode(returnCode);
						if (!"000".equals(returnCode)) {
							model.addAttribute("msg", HMallInterworkUtility.getErrorMsgByCode(returnCode));
						}
						orderService.insertBomHMTb0001(adjustment);
					}
				}
			
				
				
				return "admin/order/cancel";
			}
			/*
			if(orderInfo.getOrderStatCd().equals("06") || orderInfo.getOrderStatCd().equals("02")){
				List<OrderInfo> orderList = orderService.selectListBomOrderTbToExel0001(orderInfo);
				
				for(OrderInfo each : orderList){
					String pointInfo= each.getUserPointInfo();
					
					if(pointInfo==null ||  StringUtils.isEmpty(pointInfo) || pointInfo.isEmpty() ){
						redirectAttributes.addFlashAttribute("orderSucess", "yes");
						return "redirect:orderList.do";
					}
					String[] point = pointInfo.split("_");
					Map<String, String> rstMap = null;
					HMallProcAdjustmentInfo adjustment = new HMallProcAdjustmentInfo();
					try {
						// --------------------------------------------
						// 1. 정산 처리 
						// --------------------------------------------
						
						adjustment.setOrderNo(orderInfo.getOrderNo());
						adjustment.setItemCd(each.getOrdPrd().getPrdCd());
						adjustment.setOrderGb("10");
						adjustment.setOrderDm(DateUtil.getDate("yyyyMMdd"));
						adjustment.setShopNo( point[4]);
						adjustment.setShopEventNo( point[2]);
						adjustment.setMemNo(point[1]);
						adjustment.setTaxGb("1");
						adjustment.setSalePrice(each.getOrdPrd().getSellPrice().multiply(new BigDecimal(each.getOrdPrd().getBuyCnt())).toString());
						adjustment.setPointAmt(Integer.toString(each.getPaymentInfo().getPayPoint()));
						BigDecimal etc = each.getPaymentInfo().getPayPrice();
						etc =etc .subtract(new BigDecimal(each.getPaymentInfo().getPayPoint()));
						adjustment.setEtcAmt(etc.toString());
						adjustment.setDeliAmt("0");
						String option = each.getOrdPrd().getPrdOpColor();
			        	adjustment.setItemNm(each.getOrdPrd().getPrdNm()+option.substring(3, option.indexOf(",")));
			        	adjustment.setItemPrice(each.getOrdPrd().getSellPrice().toString());
			        	adjustment.setOrderQty(Integer.toString(each.getOrdPrd().getBuyCnt()));
			        	adjustment.setDcPrice("0");
			        	
			        	
			        	rstMap = HMallInterworkUtility.procAdjustment(adjustment);
			        	
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
						String date = adjustment.getOrderDm()+"("+DateUtil.getDate("h:mm a")+")";
						adjustment.setOrderDm(date);
						adjustment.setReturnCode(returnCode);
						orderService.insertBomHMTb0001(adjustment);
						if (!"000".equals(returnCode)) {
							model.addAttribute("msg", HMallInterworkUtility.getErrorMsgByCode(returnCode));
						}
					}
				}
				
			}
			
			*/
		
		}
		return "redirect:orderList.do";
	}

	//주문검색
	@RequestMapping(value="/orderSearchList.do", method= RequestMethod.GET)
	public String orderSearchList(@ModelAttribute("AdminInfo") AdminInfo adminInfo,OrderSrchInfo orderInfo, String page,BindingResult result, Model model,HttpSession session) throws UnsupportedEncodingException{

		AdminInfo adminSession = (AdminInfo) session.getAttribute("adminSession");

		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}

		
		if (StringUtils.isEmpty(page))
			{page="1";orderInfo.setStartIdx(Integer.parseInt(page));}
		else 
			orderInfo.setStartIdx(Integer.parseInt(page));
		
		if(orderInfo.getKeyword()!=null && !orderInfo.getKeyword().isEmpty() && !StringUtils.isEmpty(orderInfo.getKeyword()))
			orderInfo.setKeyword(URLDecoder.decode(orderInfo.getKeyword(), "UTF-8"));
		
		List<OrderInfo> odList =orderManageService.getOrderInfoListBySchInfo(orderInfo);
		
		PageDivision pd = new PageDivision();
		
		if (StringUtils.isEmpty(page)){
			pd.pageNum("1");
			page="1";
		}	
		else
			pd.pageNum(page);
		pd.setItemNum(10);
		pd.setOrderInfoList(odList);
		
		
		model.addAttribute("odList",pd.getOrderInfoList());
		model.addAttribute("nowPage", page);
	
		model.addAttribute("endNum", pd.getEndPageNum());
		model.addAttribute("orderSrchInfo", orderInfo);
		return "admin/order/orderList";
	}
	
	//신청대기
	@RequestMapping(value="/orderingList.do", method= RequestMethod.GET)
	public String orderingList(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result,String page, Model model,HttpSession session){

		AdminInfo adminSession = (AdminInfo) session.getAttribute("adminSession");

		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}

		
	
		OrderInfo os = new OrderInfo();
		os.setOrderStatCd("02");
		if (StringUtils.isEmpty(page))
			{page="1";os.setStartIdx(Integer.parseInt(page));}
		else 
			os.setStartIdx(Integer.parseInt(page));
		
		List<OrderInfo> odList =orderManageService.getOrderInfoListByPeriod(os);
		
		model.addAttribute("odList",odList);
		model.addAttribute("sh","ordering");
		OrderSrchInfo orderSrchInfo = new OrderSrchInfo();
		orderSrchInfo.setKeyfield(3);
		orderSrchInfo.setKeyword("02");
		
		int endNum;
		int total= orderManageService.getOrderTypTotalCount(orderSrchInfo);
		
		if(total%15==0 || total/15<0 ) {
			endNum=total/15;
		}
		else{
			endNum=total/15+1;
			}
		
		
		model.addAttribute("endNum",endNum);
		model.addAttribute("orderStatCd","01");
		
		return "admin/order/orderList";
	}
	
	//구매확정
	@RequestMapping(value="/orderCompleteList.do", method= RequestMethod.GET)
	public String orderCompleteList(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result,String page, Model model,HttpSession session){

		AdminInfo adminSession = (AdminInfo) session.getAttribute("adminSession");

		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}

		
		OrderInfo os = new OrderInfo();
		os.setOrderStatCd("06");
		if (StringUtils.isEmpty(page))
			{page="1";os.setStartIdx(Integer.parseInt(page));}
		else 
			os.setStartIdx(Integer.parseInt(page));
		
		List<OrderInfo> odList =orderManageService.getOrderInfoListByPeriod(os);
		
		model.addAttribute("odList",odList);
		model.addAttribute("sh","orderComplete");
		OrderSrchInfo orderSrchInfo = new OrderSrchInfo();
		orderSrchInfo.setKeyfield(3);
		orderSrchInfo.setKeyword("06");
		
		int endNum;
		int total= orderManageService.getOrderTypTotalCount(orderSrchInfo);
		
		if(total%15==0 || total/15<0 ) {
			endNum=total/15;
		}
		else{
			endNum=total/15+1;
			}
		
		
		model.addAttribute("endNum",endNum);
		model.addAttribute("orderStatCd","02");
		
		return "admin/order/orderList";
	}
	
	//배송중
	@RequestMapping(value="/orderTransferingList.do", method= RequestMethod.GET)
	public String orderTransferingList(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BindingResult result,String page, Model model,HttpSession session){

		AdminInfo adminSession = (AdminInfo) session.getAttribute("adminSession");

		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}

		
		OrderInfo os = new OrderInfo();
		os.setOrderStatCd("04");
		if (StringUtils.isEmpty(page))
		{page="1";os.setStartIdx(Integer.parseInt(page));}
		else 
			os.setStartIdx(Integer.parseInt(page));
		
		List<OrderInfo> odList =orderManageService.getOrderInfoListByPeriod(os);
		
		model.addAttribute("odList",odList);
		model.addAttribute("sh","Transfering");
		OrderSrchInfo orderSrchInfo = new OrderSrchInfo();
		orderSrchInfo.setKeyfield(3);
		orderSrchInfo.setKeyword("04");
		
		int endNum;
		int total= orderManageService.getOrderTypTotalCount(orderSrchInfo);
		
		if(total%15==0 || total/15<0 ) {
			endNum=total/15;
		}
		else{
			endNum=total/15+1;
			}
		
		
		model.addAttribute("endNum",endNum);
	
		model.addAttribute("orderStatCd","04");
		
		return "admin/order/orderList";
	}
	
	// 배송준비중
	@RequestMapping(value = "/orderTransferReadyList.do", method = RequestMethod.GET)
	public String orderTransferReadyList(@ModelAttribute("AdminInfo") AdminInfo adminInfo,BindingResult result, String page, Model model, HttpSession session) {
		
		  AdminInfo adminSession = (AdminInfo) session.getAttribute("adminSession");
		  
		  if (adminSession == null) { return "redirect:adminLogin.do"; }
		

		OrderInfo os = new OrderInfo();
		os.setOrderStatCd("03");
		if (StringUtils.isEmpty(page))
		{page="1";os.setStartIdx(Integer.parseInt(page));}
		else 
			os.setStartIdx(Integer.parseInt(page));
		
		List<OrderInfo> odList =orderManageService.getOrderInfoListByPeriod(os);
		
		model.addAttribute("odList",odList);
		model.addAttribute("sh","TransferReady");
		OrderSrchInfo orderSrchInfo = new OrderSrchInfo();
		orderSrchInfo.setKeyfield(3);
		orderSrchInfo.setKeyword("03");
		
		int endNum;
		int total= orderManageService.getOrderTypTotalCount(orderSrchInfo);
		
		if(total%15==0 || total/15<0 ) {
			endNum=total/15;
		}
		else{
			endNum=total/15+1;
			}
		
		
		model.addAttribute("endNum",endNum);
	
		model.addAttribute("orderStatCd","03");
		return "admin/order/orderList";
	}
	// 주문취소신청
	@RequestMapping(value = "/orderCancelList.do", method = RequestMethod.GET)
	public String orderCancelList(@ModelAttribute("AdminInfo") AdminInfo adminInfo,BindingResult result,String page, Model model, HttpSession session) {
		
		  AdminInfo adminSession = (AdminInfo) session.getAttribute("adminSession");
		  
		  if (adminSession == null) { return "redirect:adminLogin.do"; }
		

		OrderInfo os = new OrderInfo();
		os.setOrderStatCd("07");
		
		if (StringUtils.isEmpty(page))
		{page="1";os.setStartIdx(Integer.parseInt(page));}
		else 
			os.setStartIdx(Integer.parseInt(page));
		
		List<OrderInfo> odList =orderManageService.getOrderInfoListByPeriod(os);
		
		model.addAttribute("odList",odList);
		model.addAttribute("sh","cancel");
		OrderSrchInfo orderSrchInfo = new OrderSrchInfo();
		orderSrchInfo.setKeyfield(3);
		orderSrchInfo.setKeyword("07");
		
		int endNum;
		int total= orderManageService.getOrderTypTotalCount(orderSrchInfo);
		
		if(total%15==0 || total/15<0 ) {
			endNum=total/15;
		}
		else{
			endNum=total/15+1;
			}
		
		
		model.addAttribute("endNum",endNum);
		model.addAttribute("orderStatCd","07");

		return "admin/order/orderList";
	}
	//주문취소신청완료
	@RequestMapping(value = "/orderCancelCompleteList.do", method = RequestMethod.GET)
	public String orderCancelCompleteList(@ModelAttribute("AdminInfo") AdminInfo adminInfo,BindingResult result,String page, Model model, HttpSession session) {
		
		  AdminInfo adminSession = (AdminInfo) session.getAttribute("adminSession");
		  
		  if (adminSession == null) { return "redirect:adminLogin.do"; }
		

		OrderInfo os = new OrderInfo();
		os.setOrderStatCd("08");
		if (StringUtils.isEmpty(page))
		{page="1";os.setStartIdx(Integer.parseInt(page));}
		else 
			os.setStartIdx(Integer.parseInt(page));
		
		List<OrderInfo> odList =orderManageService.getOrderInfoListByPeriod(os);
		
		model.addAttribute("odList",odList);
		model.addAttribute("sh","cancelComplete");
		OrderSrchInfo orderSrchInfo = new OrderSrchInfo();
		orderSrchInfo.setKeyfield(3);
		orderSrchInfo.setKeyword("08");
		
		int endNum;
		int total= orderManageService.getOrderTypTotalCount(orderSrchInfo);
		
		if(total%15==0 || total/15<0 ) {
			endNum=total/15;
		}
		else{
			endNum=total/15+1;
			}
		
		model.addAttribute("orderStatCd","08");
		model.addAttribute("endNum",endNum);


		return "admin/order/orderList";
	}
	// 반품신청
	@RequestMapping(value = "/orderTakeBackList.do", method = RequestMethod.GET)
	public String orderTakeBackList(@ModelAttribute("AdminInfo") AdminInfo adminInfo,BindingResult result,String page, Model model, HttpSession session) {
		
		  AdminInfo adminSession = (AdminInfo) session.getAttribute("adminSession");
		  
		  if (adminSession == null) { return "redirect:adminLogin.do"; }
		

		OrderInfo os = new OrderInfo();
		os.setOrderStatCd("09");
		if (StringUtils.isEmpty(page))
		{page="1";os.setStartIdx(Integer.parseInt(page));}
		else 
			os.setStartIdx(Integer.parseInt(page));
		
		List<OrderInfo> odList =orderManageService.getOrderInfoListByPeriod(os);
		
		model.addAttribute("odList",odList);
		model.addAttribute("sh","return");
		OrderSrchInfo orderSrchInfo = new OrderSrchInfo();
		orderSrchInfo.setKeyfield(3);
		orderSrchInfo.setKeyword("09");
		
		int endNum;
		int total= orderManageService.getOrderTypTotalCount(orderSrchInfo);
		
		if(total%15==0 || total/15<0 ) {
			endNum=total/15;
		}
		else{
			endNum=total/15+1;
			}
		
		
		model.addAttribute("endNum",endNum);
		model.addAttribute("orderStatCd","09");

		return "admin/order/orderList";
	}
	
	// 반품신청완료
	@RequestMapping(value = "/orderTakeBackCompleteList.do", method = RequestMethod.GET)
	public String orderTakeBackCompleteList(@ModelAttribute("AdminInfo") AdminInfo adminInfo,BindingResult result,String page, Model model, HttpSession session) {
		
		  AdminInfo adminSession = (AdminInfo) session.getAttribute("adminSession");
		  
		  if (adminSession == null) { return "redirect:adminLogin.do"; }
		

		OrderInfo os = new OrderInfo();
		os.setOrderStatCd("10");
		
		if (StringUtils.isEmpty(page))
		{page="1";os.setStartIdx(Integer.parseInt(page));}
		else 
			os.setStartIdx(Integer.parseInt(page));
		
		List<OrderInfo> odList =orderManageService.getOrderInfoListByPeriod(os);
		
		model.addAttribute("odList",odList);
		model.addAttribute("sh","retrunComplete");
		OrderSrchInfo orderSrchInfo = new OrderSrchInfo();
		orderSrchInfo.setKeyfield(3);
		orderSrchInfo.setKeyword("10");
		int endNum;
		int total= orderManageService.getOrderTypTotalCount(orderSrchInfo);
		
		if(total%15==0 || total/15<0 ) {
			endNum=total/15;
		}
		else{
			endNum=total/15+1;
			}
		
		model.addAttribute("orderStatCd","10");
		model.addAttribute("endNum",endNum);


		return "admin/order/orderList";
	}
	// 월별 거래내역조회
	@RequestMapping(value = "/monthTradeList.do", method = RequestMethod.GET)
	public String monthTradeList(@ModelAttribute("AdminInfo") AdminInfo adminInfo,BindingResult result,String page, Model model, HttpSession session) {
		
		AdminInfo adminSession = (AdminInfo) session .getAttribute("adminSession");
		if (adminSession == null) { return "redirect:adminLogin.do"; }
		 
		OrderInfo os = new OrderInfo();
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
		Date currentTime = new Date ( );
		String mTime = mSimpleDateFormat.format ( currentTime );
		
		os.setSrchStdDt(mTime.substring(0,7) +"-01");
		os.setSrchEdDt(mTime);
		
		List<OrderInfo> odList =orderManageService.getOrderInfoListByPeriod(os);
		PageDivision pd = new PageDivision();
		
		if (StringUtils.isEmpty(page)){
			pd.pageNum("1");
			page="1";
		}	
		else
			pd.pageNum(page);
		pd.setItemNum(20);
		pd.setOrderInfoList(odList);
		
		List<OrderInfo> odResultList =  pd.getOrderInfoList();
		
		
		//총 결제금액 계산 및 주문명
		for(OrderInfo each : odResultList ){
			OrderProductInfo ordPrd = new OrderProductInfo();
			ordPrd.setOrderNo(each.getOrderNo());
			List<OrderProductInfo> ordPrdList= orderManageService.selectOrderPrdInfo(ordPrd);
			
			BigDecimal total = new BigDecimal(0);
			for(OrderProductInfo each1 : ordPrdList ){
				if(each1!=null && each1.getSellPrice() != null)
				total = total.add(each1.getSellPrice());
			}
			
			each.setTotalOrderPrice(total);
			
			if(ordPrdList.size()>1) {
				OrderProductInfo prd = new OrderProductInfo();
				prd.setPrdNm(each.getOrdPrd().getPrdNm()+"외 "+(ordPrdList.size()-1));
			}
			
		}
		model.addAttribute("odList", odResultList);
		model.addAttribute("page", page);
	
		model.addAttribute("endNum", pd.getEndPageNum());
		model.addAttribute("orderStatCd","");
		  

		 return "admin/order/orderMonthTrade";
	}
	
	// 월별 거래내역조회
	@RequestMapping(value = "/monthTradeSeachList.do", method = RequestMethod.GET)
	public String monthTradeSeachList(@ModelAttribute("AdminInfo") AdminInfo adminInfo,OrderInfo os,BindingResult result,String page, Model model, HttpSession session) {
		
		AdminInfo adminSession = (AdminInfo) session .getAttribute("adminSession");
		if (adminSession == null) { return "redirect:adminLogin.do"; }
		 
		
		
		
		List<OrderInfo> odList =orderManageService.getOrderInfoListByPeriod(os);
		PageDivision pd = new PageDivision();
		
		if (StringUtils.isEmpty(page)){
			pd.pageNum("1");
			page="1";
		}	
		else
			pd.pageNum(page);
		pd.setItemNum(20);
		pd.setOrderInfoList(odList);
		
		List<OrderInfo> odResultList =  pd.getOrderInfoList();
		
		
		//총 결제금액 계산 및 주문명
		for(OrderInfo each : odResultList ){
			OrderProductInfo ordPrd = new OrderProductInfo();
			ordPrd.setOrderNo(each.getOrderNo());
			List<OrderProductInfo> ordPrdList= orderManageService.selectOrderPrdInfo(ordPrd);
			
			BigDecimal total = new BigDecimal(0);
			for(OrderProductInfo each1 : ordPrdList ){
				if(each1!=null && each1.getSellPrice() != null)
				total = total.add(each1.getSellPrice());
			}
			
			each.setTotalOrderPrice(total);
			
			if(ordPrdList.size()>1) {
				OrderProductInfo prd = new OrderProductInfo();
				prd.setPrdNm(each.getOrdPrd().getPrdNm()+"외 "+(ordPrdList.size()-1));
			}
			
		}
		model.addAttribute("odList", odResultList);
		model.addAttribute("nowPage", page);
		model.addAttribute("schOrdInfo", os);
		model.addAttribute("endNum", pd.getEndPageNum());
		model.addAttribute("orderStatCd","");
		  

		 return "admin/order/orderMonthTrade";
	}
	// 상품별 거래내역조회
	@RequestMapping(value = "/productTradeList.do", method = RequestMethod.GET)
	public String productTradeList(@ModelAttribute("AdminInfo") AdminInfo adminInfo,String page,BindingResult result, Model model, HttpSession session) {
		AdminInfo adminSession = (AdminInfo) session .getAttribute("adminSession");
		if (adminSession == null) { return "redirect:adminLogin.do"; }
		 
		OrderInfo os = new OrderInfo();
		os.setReciInfo(new RecipientInfo());
		
		List<OrderInfo> odList =orderManageService.selectListBomOrderTbToExel0001(os);
		PageDivision pd = new PageDivision();
		
		if (StringUtils.isEmpty(page)){
			pd.pageNum("1");
			page="1";
		}	
		else
			pd.pageNum(page);
		pd.setItemNum(20);
		pd.setOrderInfoList(odList);
	
		
		model.addAttribute("odList", pd.getOrderInfoList());
		model.addAttribute("page", page);
	
		model.addAttribute("endNum", pd.getEndPageNum());
		model.addAttribute("orderStatCd","");
		  

	
		return "admin/order/orderProductTrade";
	}

	//상품별 거래내역 주문검색
	@RequestMapping(value="/orderSearchProdcutList.do", method= RequestMethod.GET)
	public String orderSearchProdcutList(@ModelAttribute("AdminInfo") AdminInfo adminInfo,OrderSrchInfo orderInfo, String page,BindingResult result, Model model,HttpSession session){

		AdminInfo adminSession = (AdminInfo) session.getAttribute("adminSession");

		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}

		
		if (StringUtils.isEmpty(page))
			{page="1";orderInfo.setStartIdx(Integer.parseInt(page));}
		else 
			orderInfo.setStartIdx(Integer.parseInt(page));
		
		
		List<OrderInfo> odList =orderManageService.getOrderInfoListBySchInfo2(orderInfo);
		
		PageDivision pd = new PageDivision();
		
		if (StringUtils.isEmpty(page)){
			pd.pageNum("1");
			page="1";
		}	
		else
			pd.pageNum(page);
		pd.setItemNum(10);
		pd.setOrderInfoList(odList);
		
		
		model.addAttribute("odList",pd.getOrderInfoList());
		model.addAttribute("nowPage", page);
	
		model.addAttribute("endNum", pd.getEndPageNum());
		model.addAttribute("orderSrchInfo", orderInfo);
		return "admin/order/orderProductTrade";
	}
	//현대몰 정산
	@RequestMapping(value="/orderHMList.do", method= RequestMethod.GET)
	public String orderHMList(String page, Model model,HttpSession session,HMallProcAdjustmentInfo hminfo ){

		AdminInfo adminSession = (AdminInfo) session.getAttribute("adminSession");

		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}

		
		List<HMallProcAdjustmentInfo> odList =orderManageService.selectListBomHMTb0001(hminfo);
		
		PageDivision pd = new PageDivision();
		
		if (StringUtils.isEmpty(page)){
			pd.pageNum("1");
			page="1";
		}	
		else
			pd.pageNum(page);
		pd.setItemNum(10);
		pd.setHMInfoList(odList);
		
		
		model.addAttribute("odList",pd.getHMInfoList());
		model.addAttribute("page", page);
		model.addAttribute("hminfo", hminfo);
		
		model.addAttribute("endNum", pd.getEndPageNum());
		return "admin/order/orderHMList";
	}
	/**
	 * 엑셀파일 등록 처리
	 * @throws BiffException 
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	@RequestMapping(value="/exelUpload.do")
	public String exelUpload(Model model,HttpSession session, MultipartFile exelFile) throws BiffException{
		try {
			
			//엑셀파일 업로드
			AttachFileInfo contImg = new AttachFileInfo();
			if(exelFile != null && !exelFile.isEmpty()) {
				contImg = FileUploadUtility.doFileUpload(7,exelFile, false);
			}
			
			//엑셀파일 읽기
			//File file = new File("D:/upload/"+contImg.getAttSaveFileNm());
			File file = new File("/home/hosting_users/blueonestore/tomcat/webapps/ROOT/resources/upload/"+contImg.getAttSaveFileNm());
			
			Workbook workBook = Workbook.getWorkbook(file);
			
			Sheet sheet = workBook.getSheet(0);
			
			for(int i=1; i<sheet.getRows(); i++) {// 행 구분
				
				Cell cell1 = null;
				Cell cell2 = null;
				String transferNo = new String();
				String transfer = new String();
				
				if((Cell) sheet.getCell(15,i) != null){
					cell1 = (Cell) sheet.getCell(15,i);
					transferNo = cell1.getContents();
				}
				
				if((Cell) sheet.getCell(17,i) != null){
					cell2 = (Cell) sheet.getCell(17,i);
					transfer   = cell2.getContents();
					
				}
				
				OrderInfo upOrdInfo = new OrderInfo();
				upOrdInfo.setCustomerInfo(new CustomerInfo());
				String ordNo=sheet.getCell(0,i).getContents();
				upOrdInfo.setOrderNo(ordNo.substring(0, ordNo.indexOf("(")));
				upOrdInfo.setOrdTransNo(transferNo);
				upOrdInfo.setOrdTrans(transfer);
				orderService.updateOrderInf(upOrdInfo);
				
				
			}
			//------------------------------
			 //파일 삭제
			 //------------------------------
			 
			file.delete();
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return "redirect:/admin/orderList.do";
	}
	
	@RequestMapping(value="/orderCancel.do")
	public String orderCancel(Model model,HttpSession session){
		
		
		
		return "admin/order/cancel";
	}
	@RequestMapping(value="/orderHMListToExel.do", method= RequestMethod.GET)
	public String orderHMListToExel( Model model,HttpSession session,HttpServletRequest request, HttpServletResponse response,HMallProcAdjustmentInfo hmInfo) throws ServletException, IOException{
		 //------------------------------
		 //엑셀파일 생성
		 //------------------------------
		// String filepath = "C:/Users/Administrator/Documents/"+DateUtil.getDate("yyyyMMddHHmmss")+"HM_LIST.xls"; //개발
		 String filepath = "/home/hosting_users/blueonestore/tomcat/webapps/ROOT/resources/upload/"+DateUtil.getDate("yyyyMMddHHmmss")+"HM_LIST.xls"; //운영
	
	
		    try {
		    	
		        HSSFWorkbook workbook = new HSSFWorkbook();
	
	
		        HSSFSheet sheet = workbook.createSheet();
		       // workbook.setSheetName(0 , "한글명" ,HSSFWorkbook.ENCODING_UTF_16);
	
	
		       HSSFCellStyle style = workbook.createCellStyle();
		       /*   style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		        style.setBottomBorderColor(HSSFColor.BLACK.index);
		        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		        style.setLeftBorderColor(HSSFColor.GREEN.index);
		        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		        style.setRightBorderColor(HSSFColor.BLUE.index);
		        style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM_DASHED);
		        style.setTopBorderColor(HSSFColor.BLACK.index);           */
	
	
		        HSSFRow row = sheet.createRow(0);
		       
		  
	            HSSFCell cell = row.createCell((short)0);
	            cell.setCellStyle(style);
	            cell.setCellValue("주문번호");     
	            
	            cell = row.createCell((short)1);
	            cell.setCellStyle(style);
	            cell.setCellValue("상품번호");     
	            
	            cell = row.createCell((short)2);
	            cell.setCellStyle(style);
	            cell.setCellValue("주문구분");  
	            
	            cell = row.createCell((short)3);
	            cell.setCellStyle(style);
	            cell.setCellValue("주문일자"); 
	            
	            cell = row.createCell((short)4);
	            cell.setCellStyle(style);
	            cell.setCellValue("상점번호"); 
	            
	            cell = row.createCell((short)5);
	            cell.setCellStyle(style);
	            cell.setCellValue("행사번호");  
	            
	            cell = row.createCell((short)6);
	            cell.setCellStyle(style);
	            cell.setCellValue("고객번호");   
	            
	            cell = row.createCell((short)7);
	            cell.setCellStyle(style);
	            cell.setCellValue("과세여부");   
	            
	            cell = row.createCell((short)8);
	            cell.setCellStyle(style);
	            cell.setCellValue("판매금액");   
	            
	            cell = row.createCell((short)9);
	            cell.setCellStyle(style);
	            cell.setCellValue("기본금");   
	            
	            cell = row.createCell((short)10);
	            cell.setCellStyle(style);
	            cell.setCellValue("기타결제");   
	            
	            cell = row.createCell((short)11);
	            cell.setCellStyle(style);
	            cell.setCellValue("매체구분");   
	           
	            cell = row.createCell((short)12);
	            cell.setCellStyle(style);
	            cell.setCellValue("배송비");   
	            
	            cell = row.createCell((short)13);
	            cell.setCellStyle(style);
	            cell.setCellValue("상품명");   
	           
	            cell = row.createCell((short)14);
	            cell.setCellStyle(style);
	            cell.setCellValue("단품가격");   
	            
	            cell = row.createCell((short)15);
	            cell.setCellStyle(style);
	            cell.setCellValue("주문수량");   
	           
	            cell = row.createCell((short)16);
	            cell.setCellStyle(style);
	            cell.setCellValue("할인금액");   
	            
	            cell = row.createCell((short)17);
	            cell.setCellStyle(style);
	            cell.setCellValue("리턴코드");   
	           
	            
	
	            List<HMallProcAdjustmentInfo> odList =orderManageService.selectListBomHMTb0001(hmInfo);
	            
		        int i=1;
	            for (HMallProcAdjustmentInfo each : odList){
	            	
	            	row = sheet.createRow(i);
	            	
	            
		            cell = row.createCell((short)0);
		            cell.setCellValue(each.getOrderNo());     
		            
		           
		            cell = row.createCell((short)1);
			        cell.setCellValue(each.getItemCd());   
			        
			       
		            cell = row.createCell((short)2);
		            String orderGb ="";
		            if(each.getOrderGb().equals("10")) {
		            	orderGb = "주문";
		            }
		            if(each.getOrderGb().equals("20")) {
		            	orderGb = "취소";
		            }
		            orderGb +="("+each.getOrderGb()+")";
		            cell.setCellValue(orderGb); 
		            
		            
		            cell = row.createCell((short)3);
		            cell.setCellValue(each.getOrderDm()); 
		            
		        
		            cell = row.createCell((short)4);
		            cell.setCellValue(each.getShopNo()); 
		            
		            
		            cell = row.createCell((short)5);
		            cell.setCellValue(each.getShopEventNo()); 
		          
		            cell = row.createCell((short)6);
		            cell.setCellValue(each.getMemNo()); 

		            cell = row.createCell((short)7);
		            String tax ="";
		            if(each.getTaxGb().equals("1")) {
		            	tax = "과세";
		            }
		            if(each.getTaxGb().equals("2")) {
		            	tax = "비과세";
		            }
		            tax+="("+each.getTaxGb()+")";
		            cell.setCellValue(tax); 
		            
		  
		            cell = row.createCell((short)8);
		            cell.setCellValue(each.getSalePrice()); 
		            
		   
		            cell = row.createCell((short)9);
		            cell.setCellValue(each.getPointAmt()); 
		            
		            cell = row.createCell((short)10);
		            cell.setCellValue(each.getEtcAmt());
		            
		           
		            cell = row.createCell((short)11);
		            cell.setCellValue(each.getMediaCd());
		            
		            
		            cell = row.createCell((short)12);
		            cell.setCellValue(each.getDeliAmt());
		            
		            cell = row.createCell((short)13);
		            cell.setCellValue(each.getItemNm());
		            
		            cell = row.createCell((short)14);
		            cell.setCellValue(each.getItemPrice());
		            
		            cell = row.createCell((short)15);
		            cell.setCellValue(each.getOrderQty());
		            
		            cell = row.createCell((short)16);
		            cell.setCellValue(each.getDcPrice());
		            
		            cell = row.createCell((short)17);
		            cell.setCellValue(each.getReturnCode());
		            
		            i++;
		        }
		        FileOutputStream fs = null;
		        try { 
		            fs = new FileOutputStream(filepath);
		            workbook.write(fs);
		            
		        } catch (Exception e) {
		        } finally {
		            if (fs != null) fs.close();
		        }
		        
		    } catch (Exception e) {
	
		        
		        e.printStackTrace();
		    }    
	    
		 //------------------------------
		 //파일 다운로드
		 //------------------------------
		  File file = new File(filepath);
		  String mimetype = request.getSession().getServletContext().getMimeType(file.getName());
		  InputStream is = null;
		  
		  try {
			  is = new FileInputStream(file);
			  download(request, response, is, file.getName(), file.length(), mimetype);
		  } finally {
			  try {
				  	is.close();
			  } catch (Exception ex) {
			  }
		  }
		//------------------------------
		 //파일 삭제
		 //------------------------------
		  file.delete();
		  
		return "redirect:orderHMList.do";
	}
	
	@RequestMapping(value="/orderMonthListToExel.do")
	public String orderMonthListToExel(@ModelAttribute("AdminInfo") AdminInfo adminInfo, OrderInfo orderInfo,BindingResult result, Model model,HttpSession session,String page,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		 //------------------------------
		 //엑셀파일 생성
		 //------------------------------
		//String filepath = "D:/Documents/write.xls"; //개발
	String filepath = "/home/hosting_users/blueonestore/tomcat/webapps/ROOT/resources/upload/"+DateUtil.getDate("yyyyMMddHHmmss")+"HM_LIST.xls"; //운영
	
	
		    try {
		    	
		        HSSFWorkbook workbook = new HSSFWorkbook();
	
	
		        HSSFSheet sheet = workbook.createSheet();
		     
	
	
		       HSSFCellStyle style = workbook.createCellStyle();
		       /*   style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		        style.setBottomBorderColor(HSSFColor.BLACK.index);
		        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		        style.setLeftBorderColor(HSSFColor.GREEN.index);
		        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		        style.setRightBorderColor(HSSFColor.BLUE.index);
		        style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM_DASHED);
		        style.setTopBorderColor(HSSFColor.BLACK.index);           */
	
	
		        HSSFRow row = sheet.createRow(0);
		       
		  
	            HSSFCell cell = row.createCell((short)0);
	            cell.setCellStyle(style);
	            cell.setCellValue("사이트명");     
	            
	            cell = row.createCell((short)1);
	            cell.setCellStyle(style);
	            cell.setCellValue("정산일");     
	            
	            cell = row.createCell((short)2);
	            cell.setCellStyle(style);
	            cell.setCellValue("정산기준일");  
	            
	            cell = row.createCell((short)3);
	            cell.setCellStyle(style);
	            cell.setCellValue("주문번호");  
	            
	            cell = row.createCell((short)4);
	            cell.setCellStyle(style);
	            cell.setCellValue("주문일"); 
	            
	            cell = row.createCell((short)5);
	            cell.setCellStyle(style);
	            cell.setCellValue("취소일"); 
	            
	            cell = row.createCell((short)6);
	            cell.setCellStyle(style);
	            cell.setCellValue("주문상태");  
	            
	            cell = row.createCell((short)7);
	            cell.setCellStyle(style);
	            cell.setCellValue("상품번호");   
	            
	            cell = row.createCell((short)8);
	            cell.setCellStyle(style);
	            cell.setCellValue("모델명");   
	            
	            cell = row.createCell((short)9);
	            cell.setCellStyle(style);
	            cell.setCellValue("상품명");   
	            
	            cell = row.createCell((short)10);
	            cell.setCellStyle(style);
	            cell.setCellValue("수량");   
	            
	            cell = row.createCell((short)11);
	            cell.setCellStyle(style);
	            cell.setCellValue("과세타입");   
	           
	            cell = row.createCell((short)12);
	            cell.setCellStyle(style);
	            cell.setCellValue("상품금액");   
	            
	            cell = row.createCell((short)13);
	            cell.setCellStyle(style);
	            cell.setCellValue("고객명");   
	           
	            cell = row.createCell((short)14);
	            cell.setCellStyle(style);
	            cell.setCellValue("사번");   
	            
	            cell = row.createCell((short)15);
	            cell.setCellStyle(style);
	            cell.setCellValue("배송비");   
	           
	            cell = row.createCell((short)16);
	            cell.setCellStyle(style);
	            cell.setCellValue("결제금액");   
	            
	            cell = row.createCell((short)17);
	            cell.setCellStyle(style);
	            cell.setCellValue("신용카드결제금액");   
	           
	            cell = row.createCell((short)18);
	            cell.setCellStyle(style);
	            cell.setCellValue("계좌이체결제금액"); 
	            
	            cell = row.createCell((short)19);
	            cell.setCellStyle(style);
	            cell.setCellValue("복지카드 결제금액");   
	            
	            cell = row.createCell((short)20);
	            cell.setCellStyle(style);
	            cell.setCellValue("외부포인트결제금액");   
	            
	            cell = row.createCell((short)21);
	            cell.setCellStyle(style);
	            cell.setCellValue("신용카드 결제금액취소");   
	            
	            cell = row.createCell((short)22);
	            cell.setCellStyle(style);
	            cell.setCellValue("계좌이체 결제금액취소");   
	            
	            cell = row.createCell((short)23);
	            cell.setCellStyle(style);
	            cell.setCellValue("복지카드 결제금액취소");   
	            
	            cell = row.createCell((short)24);
	            cell.setCellStyle(style);
	            cell.setCellValue("외부포인트 결제금액취소");   
	            
	            cell = row.createCell((short)25);
	            cell.setCellStyle(style);
	            cell.setCellValue("행사코드");   
	            
	            cell = row.createCell((short)26);
	            cell.setCellStyle(style);
	            cell.setCellValue("상점코드");   
	            
	            cell = row.createCell((short)27);
	            cell.setCellStyle(style);
	            cell.setCellValue("상품코드");   
	            
	            cell = row.createCell((short)28);
	            cell.setCellStyle(style);
	            cell.setCellValue("단품코드");   
	            
	            cell = row.createCell((short)29);
	            cell.setCellStyle(style);
	            cell.setCellValue("OP코드");   
	            
	            cell = row.createCell((short)30);
	            cell.setCellStyle(style);
	            cell.setCellValue("마진율");   
	            
	            
	            
	    		OrderInfo os = new OrderInfo();
	    		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
	    		Date currentTime = new Date ( );
	    		String mTime = mSimpleDateFormat.format ( currentTime );
	    		
	    		os.setSrchStdDt(mTime.substring(0,7) +"-01");
	    		os.setSrchEdDt(mTime);
	    		os.setReciInfo(new RecipientInfo());
	            List<OrderInfo> odList =orderManageService.selectListBomOrderTbToExel0002(os);
	            
		        int i=1;
	            for (OrderInfo each : odList){
	            	
	            	row = sheet.createRow(i);
	            	
	            	//사이트명
		            cell = row.createCell((short)0);
		            cell.setCellValue("BlueOneImall");     
		            
		            //정산일
		            cell = row.createCell((short)1);
			        cell.setCellValue(each.getOrderDate().substring(0,10));   
			        
			        //정산기준일
		            cell = row.createCell((short)2);
		            cell.setCellValue("매월 25일"); 
		            
		            //주문번호
		            cell = row.createCell((short)3);
		            cell.setCellValue(each.getOrderNo()); 
		            
		            //주문일
		            cell = row.createCell((short)4);
		            if(each.getOrderStatCd().equals("07") || each.getOrderStatCd().equals("08") ||
		               each.getOrderStatCd().equals("09") || each.getOrderStatCd().equals("10"))
		            	 cell.setCellValue(each.getOrderDate().substring(0,10)); 
		            else cell.setCellValue(each.getOrderDate().substring(0,10)); 
		            
		            //취소일
		            cell = row.createCell((short)5);
		            if(each.getOrderStatCd().equals("07") || each.getOrderStatCd().equals("08") ||
				        each.getOrderStatCd().equals("09") || each.getOrderStatCd().equals("10"))
		            	 cell.setCellValue(each.getOrderDate().substring(0,10)); 
		            else cell.setCellValue(""); 
		            
		            //주문상태
		            cell = row.createCell((short)6);
		            String orderState = "";
		            if(each.getOrderStatCd().equals("01"))orderState="신청대기";
					else if(each.getOrderStatCd().equals("02"))orderState="주문완료";
					else if(each.getOrderStatCd().equals("07"))orderState="취소신청";
					else if(each.getOrderStatCd().equals("08"))orderState="취소완료";
					else if(each.getOrderStatCd().equals("03"))orderState="배송준비";
					else if(each.getOrderStatCd().equals("04"))orderState="배송중";
					else if(each.getOrderStatCd().equals("05"))orderState="배송완료";
					else if(each.getOrderStatCd().equals("09"))orderState="반품신청";
					else if(each.getOrderStatCd().equals("10"))orderState="반품신청완료";
		            cell.setCellValue(orderState);  
		            
		            //상품번호
		            cell = row.createCell((short)7);
		            cell.setCellValue(each.getOrdPrd().getPrdCd()); 
		            
		            //모델명
		            cell = row.createCell((short)8);
		            cell.setCellValue(each.getOrdPrd().getPrdModel()); 
		            
		            //상품명
		            cell = row.createCell((short)9);
		            cell.setCellValue(each.getOrdPrd().getPrdNm()); 
		           
		            //수량
		            cell = row.createCell((short)10);
		            cell.setCellValue(each.getOrdPrd().getBuyCnt());
		           
		            //과세타입
		            cell = row.createCell((short)11);
		            cell.setCellValue("과세");
		           
		            //상품금액
		            cell = row.createCell((short)12);
		            cell.setCellValue(each.getOrdPrd().getSellPrice().toString());
		            
		            //고객명
		            cell = row.createCell((short)13);
		            cell.setCellValue(each.getCustomerInfo().getCustNm());
		            
		            //사번
		            cell = row.createCell((short)14);
		            cell.setCellValue(each.getCustomerInfo().getCustId());
		            
		            //배송비
		            cell = row.createCell((short)15);
		            cell.setCellValue("0");
		            
		            //결제금액
		            cell = row.createCell((short)16);
		            cell.setCellValue(each.getPaymentInfo().getPayPrice().toString());
		            
		            if(each.getPaymentInfo().getPayMdCd()!=null && each.getPaymentInfo().getPayMdCd().equals("100000000000")){
		            	//신용카드 결제금액
			            cell = row.createCell((short)17);
			            cell.setCellValue(each.getPaymentInfo().getPayPrice().toString());
		            }
		            else if(each.getPaymentInfo().getPayMdCd()!=null && each.getPaymentInfo().getPayMdCd().equals("010000000000")){
		            	//계좌이체 결제금액
			            cell = row.createCell((short)18);
			            cell.setCellValue(each.getPaymentInfo().getPayPrice().toString());
		            }
		            else if(each.getPaymentInfo().getPayMdCd()!=null && each.getPaymentInfo().getPayMdCd().equals("000100000000")){
		            	//복지카드 결제금액
			            cell = row.createCell((short)19);
			            cell.setCellValue(each.getPaymentInfo().getPayPrice().toString());
		            }
		            if(each.getPaymentInfo().getPayMdCd()!=null && each.getPaymentInfo().getPayMdCd().equals("000000000001")){
		            	//외부포인트 결제금액 
			            cell = row.createCell((short)20);
			            cell.setCellValue(each.getPaymentInfo().getPayPrice().toString());
		            }else {
		            	//외부포인트 결제금액(포인트 일부분 사용)
			            cell = row.createCell((short)20);
			            BigDecimal usePoint = each.getOrdPrd().getSellPrice().subtract(each.getPaymentInfo().getPayPrice());
			            cell.setCellValue(usePoint.toString());
		            }
		            
		            if(each.getOrderStatCd().equals("08") || each.getOrderStatCd().equals("10")){
		            	if(each.getPaymentInfo().getPayMdCd()!=null && each.getPaymentInfo().getPayMdCd().equals("100000000000")){
			            	//신용카드 결제금액
				            cell = row.createCell((short)21);
				            cell.setCellValue(each.getPaymentInfo().getPayPrice().toString());
				            cell = row.createCell((short)22);
				            cell.setCellValue(0);
				            cell = row.createCell((short)23);
				            cell.setCellValue(0);
			            }
			            else if(each.getPaymentInfo().getPayMdCd()!=null && each.getPaymentInfo().getPayMdCd().equals("010000000000")){
			            	//계좌이체 결제금액
				            cell = row.createCell((short)22);
				            cell.setCellValue(each.getPaymentInfo().getPayPrice().toString());
				            cell = row.createCell((short)21);
				            cell.setCellValue(0);
				            cell = row.createCell((short)23);
				            cell.setCellValue(0);
				            
			            }
			            else if(each.getPaymentInfo().getPayMdCd()!=null && each.getPaymentInfo().getPayMdCd().equals("000100000000")){
			            	//복지카드 결제금액
				            cell = row.createCell((short)23);
				            cell.setCellValue(each.getPaymentInfo().getPayPrice().toString());
				            cell = row.createCell((short)22);
				            cell.setCellValue(0);
				            cell = row.createCell((short)21);
				            cell.setCellValue(0);
			            }
			            if(each.getPaymentInfo().getPayMdCd()!=null && each.getPaymentInfo().getPayMdCd().equals("000000000001")){
			            	//외부포인트 결제금액 
				            cell = row.createCell((short)24);
				            cell.setCellValue(each.getPaymentInfo().getPayPrice().toString());
			            }else {
			            	//외부포인트 결제금액(포인트 일부분 사용)
				            cell = row.createCell((short)24);
				            BigDecimal usePoint = each.getOrdPrd().getSellPrice().subtract(each.getPaymentInfo().getPayPrice());
				            cell.setCellValue(usePoint.toString());
			            }
		            }else{
		            	cell = row.createCell((short)21);
			            cell.setCellValue(0);
			            cell = row.createCell((short)22);
			            cell.setCellValue(0);
			            cell = row.createCell((short)23);
			            cell.setCellValue(0);
			            cell = row.createCell((short)24);
			            cell.setCellValue(0);
			            
		            }
		            
					cell = row.createCell((short)26);
		            cell.setCellValue("");
		            
		            cell = row.createCell((short)25);
		            cell.setCellValue("");
		            
		            cell = row.createCell((short)27);
		            cell.setCellValue( each.getOrdPrd().getPrdCd());
		            
		            cell = row.createCell((short)28);
		            cell.setCellValue("");
		            
		            cell = row.createCell((short)29);
		            cell.setCellValue("");
		            cell = row.createCell((short)30);
		            cell.setCellValue("");
		            
		            
		            i++;
		        }
		        FileOutputStream fs = null;
		        try { 
		            fs = new FileOutputStream(filepath);
		            workbook.write(fs);
		            
		        } catch (Exception e) {
		        } finally {
		            if (fs != null) fs.close();
		        }
		        
		    } catch (Exception e) {
	
		        
		        e.printStackTrace();
		    }    
	    
		 //------------------------------
		 //파일 다운로드
		 //------------------------------
		  File file = new File(filepath);
		  String mimetype = request.getSession().getServletContext().getMimeType(file.getName());
		  InputStream is = null;
		  
		  try {
			  is = new FileInputStream(file);
			  download(request, response, is, file.getName(), file.length(), mimetype);
		  } finally {
			  try {
				  	is.close();
			  } catch (Exception ex) {
			  }
		  }
		//------------------------------
		 //파일 삭제
		 //------------------------------
		  file.delete();
		  
		return "redirect:monthTradeList.do";
	}
	
	public static void download(HttpServletRequest request, HttpServletResponse response, InputStream is,
		      String filename, long filesize, String mimetype) throws ServletException, IOException {
		    String mime = mimetype;
		 
		    if (mimetype == null || mimetype.length() == 0) {
		      mime = "application/octet-stream;";
		    }
		 
		 
		    byte[] buffer = new byte[BUFFER_SIZE];
		 
		    response.setContentType(mime + "; charset=" + CHARSET);
		 
		    // 아래 부분에서 euc-kr 을 utf-8 로 바꾸거나 URLEncoding을 안하거나 등의 테스트를
		    // 해서 한글이 정상적으로 다운로드 되는 것으로 지정한다.
		    String userAgent = request.getHeader("User-Agent");
		 
		    // attachment; 가 붙으면 IE의 경우 무조건 다운로드창이 뜬다. 상황에 따라 써야한다.
		    if (userAgent != null && userAgent.indexOf("MSIE 5.5") > -1) { // MS IE 5.5 이하
		      response.setHeader("Content-Disposition", "filename=" + URLEncoder.encode(filename, "UTF-8") + ";");
		    } else if (userAgent != null && userAgent.indexOf("MSIE") > -1) { // MS IE (보통은 6.x 이상 가정)
		      response.setHeader("Content-Disposition", "attachment; filename="
		          + java.net.URLEncoder.encode(filename, "UTF-8") + ";");
		    } else { // 모질라나 오페라
		      response.setHeader("Content-Disposition", "attachment; filename="
		          + new String(filename.getBytes(CHARSET), "latin1") + ";");
		    }
		 
		    // 파일 사이즈가 정확하지 않을때는 아예 지정하지 않는다.
		    if (filesize > 0) {
		      response.setHeader("Content-Length", "" + filesize);
		    }
		 
		    BufferedInputStream fin = null;
		    BufferedOutputStream outs = null;
		 
		    try {
		      fin = new BufferedInputStream(is);
		      outs = new BufferedOutputStream(response.getOutputStream());
		      int read = 0;
		 
		      while ((read = fin.read(buffer)) != -1) {
		        outs.write(buffer, 0, read);
		      }
		    } catch (IOException ex) {
		        // Tomcat ClientAbortException을 잡아서 무시하도록 처리해주는게 좋다.
		    } finally {
		      try {
		        outs.close();
		      } catch (Exception ex1) {
		      }
		 
		      try {
		        fin.close();
		      } catch (Exception ex2) {
		 
		      }
		    } // end of try/catch
		  }
		
		
			
		
}
