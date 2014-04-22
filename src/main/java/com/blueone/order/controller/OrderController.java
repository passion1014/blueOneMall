package com.blueone.order.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.ws.Response;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blueone.common.domain.AttachFileInfo;
import com.blueone.common.service.IAttachFileManageService;
import com.blueone.common.util.CookieBox;
import com.blueone.order.domain.OrderInfo;
import com.blueone.order.domain.OrderProductInfo;
import com.blueone.order.domain.OrderSrchInfo;
import com.blueone.order.service.IOrderManageService;
import com.blueone.product.domain.ProductInfo;
import com.blueone.product.service.IProductManageService;
import com.blueone.user.domain.UserInfo;
import com.jidesoft.converter.BigDecimalConverter;

@Controller
public class OrderController {
	
	@Autowired IOrderManageService orderManageService;
	@Autowired IProductManageService productManageService;
	@Autowired IAttachFileManageService attFileManageService;
	
	@RequestMapping(value = "/order/getOrderList.do", method = RequestMethod.GET)
	public String getOrderInfoListByDuration(@ModelAttribute("orderSrchInfo") @Valid OrderSrchInfo orderSrchInfo, BindingResult result, Model model) {
		String viewName = "";
		
		if (result.hasErrors()) {
			return "redirect:order/getOrderList.do";
		} else {
			List<OrderInfo> orderInfoList = orderManageService.getOrderInfoListByPeriod(orderSrchInfo);
			model.addAttribute("orderList", orderInfoList);
			viewName = "order/orderList";
		}
		
		return viewName;
	}
	
	//장바구니에 들어가기
	@RequestMapping(value="/order/cartList.do")
	public String order(@ModelAttribute("orderProductInfo") OrderProductInfo orderProductInfo,BindingResult result, Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		CookieBox cki = new CookieBox(request);
		
		//상품이 선택되서 장바구니 페이지로 들어왔을 경우 해당
			String value="";
			
			if(orderProductInfo.getPrdOpColor()!=null){
				value+="01="+orderProductInfo.getPrdOpColor()+",";
			}
			if(orderProductInfo.getPrdOpSize()!=null){
				value+="02="+orderProductInfo.getPrdOpSize()+",";
			}
			if(orderProductInfo.getPrdSmallImg()!=null){
				value+="cn="+orderProductInfo.getBuyCnt();
				Cookie cookie =cki.createCookie("BOM_"+orderProductInfo.getPrdCd(),value,1000);//여기까지 디버깅으로 값이 들어가는것확인
				response.addCookie(cookie);//<-이부분이 하나도 안먹힘
		
			
			}
			
		
	
		return "redirect:cartListView.do";
	}
	
	//장바구니 보여줌
	@RequestMapping(value="/order/cartListView.do")
	public String orderView(@ModelAttribute("orderProductInfo") OrderProductInfo orderProductInfo,BindingResult result, Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{

		CookieBox cki = new CookieBox(request);
		
		List<String> ckKey = cki.getKey();//키를 불러옴, 우리꺼 빼고 다불러옴,쿠키가 안들어가서 불러올수가읎음
		List<OrderProductInfo> ord = new ArrayList<OrderProductInfo>();
		
		// 키를불러오면 처리해줄 부분
		for(String each : ckKey){
			
			if("BOM".equals(each.substring(0, 3))){
			OrderProductInfo odPrdInfo = new OrderProductInfo();
			String key=each.substring(4);
			odPrdInfo.setPrdCd(key);
			ProductInfo prdInfo = new ProductInfo();
			prdInfo.setPrdCd(key);
			prdInfo=productManageService.getProductInfDetail(prdInfo);
			
			odPrdInfo.setPrdNm(prdInfo.getPrdNm());
			odPrdInfo.setSellPrice(new BigDecimal(prdInfo.getPrdSellPrc()));
			
			AttachFileInfo att = new AttachFileInfo();
			att.setAttCdKey(prdInfo.getPrdCd());
			att.setAttImgType("01");
			att = attFileManageService.getAttFileInfListImg(att);
			if(att==null){
				odPrdInfo.setPrdSmallImg("");
			}else { 
				
				odPrdInfo.setPrdSmallImg(att.getAttFilePath());
			}
			
			
			String vl=cki.getValue(each);
				StringTokenizer st = new StringTokenizer(vl,",");
				while(st.hasMoreElements()) {
					
					String s = st.nextToken();
					
					if("01".equals(s.substring(0, 2))){
						odPrdInfo.setPrdOpColor(s.substring(3));
					}
					if("02".equals(s.substring(0, 2))){
						odPrdInfo.setPrdOpSize(s.substring(3));
					}
					if("cn".equals(s.substring(0, 2))){
						odPrdInfo.setBuyCnt(Integer.parseInt(s.substring(3)));
						BigDecimal total = new BigDecimal(odPrdInfo.getSellPrice().toString()) ;
						total.multiply(new BigDecimal(odPrdInfo.getBuyCnt()));
						odPrdInfo.setTotalPrice(total);
					}
					
				}
				
				
				if(vl.equals("")) ;
				else ord.add(odPrdInfo);
			}
		}
		
		
		
		
		model.addAttribute("odPrdInfo",ord);
		return "order/cartList";
	}
	
	//장바구니 상품 삭제
	@RequestMapping(value="/order/deleteCartList.do", method = RequestMethod.GET)
	public String deleteCartList(@ModelAttribute("orderProductInfo") OrderProductInfo orderProductInfo,BindingResult result, Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{
		CookieBox cki = new CookieBox(request);
		
		Cookie cookie =cki.createCookie("BOM_"+orderProductInfo.getPrdCd(),"",-1);
		response.addCookie(cookie);//<-이부분이 하나도 안먹힘
		

		return "redirect:cartListView.do";
	}
			
	//결제페이지
	@RequestMapping(value="/order/orderRegister.do")
	public String orderRegister(@ModelAttribute("orderInfo") OrderInfo orderInfo,BindingResult result, Model model){
		return "order/order";
	}
	
	
	//주문성공페이지
	@RequestMapping(value="/order/orderComplete.do")
	public String orderComplete(@ModelAttribute("orderInfo") OrderInfo orderInfo,BindingResult result, Model model){
		return "order/orderComplete";
	}
	
	//주문실패페이지
	@RequestMapping(value="/order/orderError.do")
	public String orderError(@ModelAttribute("orderInfo") OrderInfo orderInfo,BindingResult result, Model model){
		return "order/orderError";
	}
	
	
	//주문코드 생성
	public String getOrderCode(){
		
		// 주문 코드 채번
		int code= (int)(Math.random()*100000)+1;
		Calendar cal = Calendar.getInstance();
		int year  = cal.get(Calendar.YEAR)-2000;
		int month = cal.get(Calendar.MONTH);
		String mon="";
		switch(month){
		case 1:
			mon="J";
			break;
		case 2:
			mon="F";
			break;
		case 3:
			mon="M";
			break;
		case 4:
			mon="A";
			break;
		case 5:
			mon="M";
			break;
		case 6:
			mon="J";
			break;
		case 7:
			mon="J";
			break;
		case 8:
			mon="A";
			break;
		case 9:
			mon="S";
			break;
		case 10:
			mon="O";
			break;
		case 11:
			mon="N";
			break;
		case 12:
			mon="D";
			break;
		
		}
		String day = Integer.toString((cal.DAY_OF_MONTH)+1);
		day=day.length()>1?day:"0"+day;
		String odCode = "BOM"+year+mon+day+code;
		
		return odCode;
	}
}
