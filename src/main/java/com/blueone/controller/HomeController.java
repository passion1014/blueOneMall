package com.blueone.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.blueone.customer.domain.MemberInfo;

/**
 * Handles requests for the application home page.
 */

@SessionAttributes("memberInfo")
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
		
		String resourceHost = messageSourceAccessor.getMessage("DataDrivenEnumerationValueImpl_friendyName", "");
		System.out.println("==================[" + resourceHost + "]==================");
		
		List<String> list = sqlSession.selectList("myBatis.test.getTest");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		mav.addObject("msg", list.toString() );
		return mav;
	}
	
	@RequestMapping(value = "/sessionTest.do", method = RequestMethod.GET)
	public ModelAndView sessionTest(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		MemberInfo memberInfo = new MemberInfo();
		memberInfo.setUserNm("이성욱");
		
		List<String> list = sqlSession.selectList("myBatis.test.getTest");
		
		// 결과값 리턴
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", list.toString());
		mav.addObject("memberInfo", memberInfo);
		mav.setViewName("home");

		return mav;
	}

	
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String home(Locale locale, Model model) {
//		// �α����� ���
//		logger.info("Welcome home! The client locale is {}.", locale);
//		
//		List<String> list = sqlSession.selectList("myBatis.test.getTest");
//		
//		model.addAttribute("msg", list.toString() );
//		return "home";
//	}

	
	
}
