package com.blueone.order.controller;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blueone.common.util.CookieBox;
import com.blueone.order.domain.OrderInfo;
import com.blueone.order.domain.OrderSrchInfo;
import com.blueone.order.service.IOrderManageService;
import com.blueone.product.domain.ProductInfo;
import com.blueone.user.domain.UserInfo;

@Controller
public class OrderController {
	
	@Autowired IOrderManageService orderManageService;

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
	
	//장바구니페이지
	@RequestMapping(value="/order/cartList.do", method=RequestMethod.POST)
	public String order(@ModelAttribute("productInfo") ProductInfo productInfo,BindingResult result, Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		CookieBox cki = new CookieBox(request);
		
		String value = productInfo.getPrdColor()+","+productInfo.getPrdMany();
		
		Cookie cookie =cki.createCookie(productInfo.getPrdCd(),value,100);
		response.addCookie(cookie);
		
		// 주문 코드 채번
		int code= (int)(Math.random()*1000000)+1;
		Date to = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		String today = date.format(to);
		StringTokenizer st1 = new StringTokenizer(today,"-");
		String[] d = new String[3];
		int i=0;
		while(st1.hasMoreTokens()){
			if(i==0){
				d[i]=st1.nextToken();
			}
			else if(i==1){
				switch(Integer.getInteger(st1.nextToken())){
				case 1:
					d[i]="J";
					break;
				case 2:
					d[i]="F";
					break;
				case 3:
					d[i]="M";
					break;
				case 4:
					d[i]="A";
					break;
				case 5:
					d[i]="M";
					break;
				case 6:
					d[i]="J";
					break;
				case 7:
					d[i]="J";
					break;
				case 8:
					d[i]="A";
					break;
				case 9:
					d[i]="S";
					break;
				case 10:
					d[i]="O";
					break;
				case 11:
					d[i]="N";
					break;
				case 12:
					d[i]="D";
					break;
				
				}
			}
			else if(i==2){
				d[i]=st1.nextToken();
			}
			i++;
		}
		
		String odCode = "BOM"+d[0]+d[1]+d[2]+code;
		
		System.out.println(odCode);
		
		//System.out.println(cki.getValue(productInfo.getPropPrdCD()));
		return "order/cartList";
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
}
