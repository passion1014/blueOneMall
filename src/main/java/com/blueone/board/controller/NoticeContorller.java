package com.blueone.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blueone.admin.domain.AdminInfo;
import com.blueone.board.domain.FaqInfo;
import com.blueone.board.service.IBoardService;
import com.blueone.customer.domain.CustomerInfo;


@Controller
public class NoticeContorller {
	
	@Autowired IBoardService boardService;
	
	@RequestMapping(value = "/community/notice.do", method = RequestMethod.GET)
	public String notice(@ModelAttribute("AdminInfo") AdminInfo adminInfo,
			BindingResult result, Model model, HttpSession session) {
		
		CustomerInfo customerSesstion =(CustomerInfo)session.getAttribute("customerSession");
		// 세션체크
		if (customerSesstion == null) {
			return "user/errorPage";
		}
				
		
		
		 /*List<FaqInfo> faqList=boardService.getFaqInfoList();
		 model.addAttribute("faqList", faqList);*/
		 
		return "community/notice";

	}
	
	@RequestMapping(value = "/community/noticeView.do", method = RequestMethod.GET)
	public String noticeView(@ModelAttribute("AdminInfo") AdminInfo adminInfo,
			BindingResult result, Model model, HttpSession session) {
		
		CustomerInfo customerSesstion =(CustomerInfo)session.getAttribute("customerSession");
		// 세션체크
		if (customerSesstion == null) {
			return "user/errorPage";
		}
				
		
		
		 /*List<FaqInfo> faqList=boardService.getFaqInfoList();
		 model.addAttribute("faqList", faqList);*/
		 
		return "community/noticeView";

	}
	
	
	@RequestMapping(value = "/community/faqList.do", method = RequestMethod.GET)
	public String faqList(@ModelAttribute("AdminInfo") AdminInfo adminInfo,
			BindingResult result, Model model, HttpSession session) {
		
		CustomerInfo customerSesstion =(CustomerInfo)session.getAttribute("customerSession");
		// 세션체크
		if (customerSesstion == null) {
			return "user/errorPage";
		}
				
		
		
		 List<FaqInfo> faqList=boardService.getFaqInfoList();
		 model.addAttribute("faqList", faqList);
		 
		return "community/faqList";

	}
	
	
	@RequestMapping(value = "/community/faqView.do", method = RequestMethod.GET)
	public String faqEdit(@ModelAttribute("AdminInfo") AdminInfo adminInfo, FaqInfo faq,BindingResult result, Model model, HttpSession session) {


		CustomerInfo customerSesstion =(CustomerInfo)session.getAttribute("customerSession");
		// 세션체크
		if (customerSesstion == null) {
			return "user/errorPage";
		}
		
	
		FaqInfo reFaqInfo = boardService.getFaqInfoByIdx(faq);
		model.addAttribute("reFaqInfo", reFaqInfo);
		
		return "community/faqView";

	}
	
}
