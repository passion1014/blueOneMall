package com.blueone.common.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.blueone.common.util.CookieBox;
import com.blueone.customer.domain.CustomerInfo;

/**
 * Handles requests for the application home page.
 */

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private MessageSourceAccessor messageSourceAccessor;

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
//		String resourceHost = messageSourceAccessor.getMessage("DataDrivenEnumerationValueImpl_friendyName", "");
//		System.out.println("==================[" + resourceHost + "]==================");
		
		List<String> list = sqlSession.selectList("myBatis.test.getTest");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("shop/main");
		return mav;
	}
	
	@RequestMapping(value = "/sessionTest.do", method = RequestMethod.GET)
	public ModelAndView sessionTest(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		CustomerInfo memberInfo = new CustomerInfo();
		memberInfo.setCustNm("�댁꽦��");
		
		List<String> list = sqlSession.selectList("myBatis.test.getTest");
		
		// 寃곌낵媛�由ы꽩
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", list.toString());
		mav.addObject("memberInfo", memberInfo);
		mav.setViewName("home");

		return mav;
	}

	@RequestMapping(value = "/makingCookie.do", method = RequestMethod.GET)
	public ModelAndView createCookieTest(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		try {
			CookieBox cookieBox = new CookieBox(request);
			Cookie cookie = cookieBox.createCookie("name", "daniel", 30000);
			response.addCookie(cookie);
		} catch (IOException ioe) {
			
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		return mav;
	}
	
	@RequestMapping(value = "/getCookie.do", method = RequestMethod.GET)
	public ModelAndView getCookieTest(Locale locale, Model model, HttpServletRequest request) {
		
		try {
			CookieBox cookieBox = new CookieBox(request);
			String rst = cookieBox.getValue("name");
			
			System.out.println("cookie value = " + rst);
		} catch (IOException ioe) {
			
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		return mav;
	}

	
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String home(Locale locale, Model model) {
//		// 占싸깍옙占쏙옙占쏙옙 占쏙옙占�
//		logger.info("Welcome home! The client locale is {}.", locale);
//		
//		List<String> list = sqlSession.selectList("myBatis.test.getTest");
//		
//		model.addAttribute("msg", list.toString() );
//		return "home";
//	}

	
	
}
