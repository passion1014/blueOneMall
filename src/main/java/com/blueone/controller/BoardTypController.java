package com.blueone.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import klac.board.model.BoardTypModel;
import klac.board.service.BoardTypService;
import klac.board.service.BoardTypValidatior;
import klac.etc.service.CodeService;


import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/boardTypMng")
public class BoardTypController {
	// DI
	private ApplicationContext context = new ClassPathXmlApplicationContext("/config/applicationContext.xml");
	private BoardTypService boardTypService = (BoardTypService) context.getBean("boardTypService");
	private CodeService codeService = (CodeService) context.getBean("codeService");
	
	@RequestMapping("/add.do")
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/board/typWrite");
		mav.addObject("authList", codeService.getCodeList("MG", "MG16"));
		return mav;
	}
	
	@RequestMapping(value="/add.do", method = RequestMethod.POST)
	public ModelAndView add(@ModelAttribute("BoardTypModel") BoardTypModel boardTypModel, BindingResult result, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		new BoardTypValidatior().validate(boardTypModel, result);
		if(result.hasErrors()){
			mav.setViewName("redirect:/boardTypMng/add.do");
			return mav;
		}
		
		String insUser = (String) session.getAttribute("userId");
		
		if (StringUtils.isEmpty(insUser)) {
			insUser = "tmpAdmin";
		}
		
		boardTypModel.setInsUser(insUser);
		boardTypModel.setUpdUser(insUser);
		
		if(boardTypService.insertTBL010101(boardTypModel)){
			mav.addObject("errCode", 3);
			mav.setViewName("redirect:/boardTypMng/list.do"); // success to add new member; move to login page
			return mav;
		} else {
			mav.addObject("errCode", 2); // failed to add new member
			mav.setViewName("redirect:/boardTypMng/add.do");
			return mav;
		}
	}
	
	@RequestMapping("/edit.do")
	public ModelAndView edit(HttpServletRequest request, HttpSession session){
		int brdTyp = Integer.parseInt(request.getParameter("brdTyp"));
		BoardTypModel boardTyp = boardTypService.getBoardTyp(brdTyp); // get selected article model
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardTyp", boardTyp);
		mav.addObject("authList", codeService.getCodeList("MG", "MG16"));
		mav.setViewName("/admin/board/typEdit");
		return mav;
	}
	
	@RequestMapping(value="/edit.do", method = RequestMethod.POST)
	public ModelAndView edit(@ModelAttribute("BoardTypModel") BoardTypModel boardTypModel, BindingResult result, HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		new BoardTypValidatior().validate(boardTypModel, result);
		if(result.hasErrors()){
			mav.addObject("brdTyp", boardTypModel.getBrdTyp());
			mav.setViewName("redirect:/boardTypMng/edit.do");
			return mav;
		}
		
		String userId = (String) session.getAttribute("userId");
		boardTypModel.setUpdUser(userId);
		
		if(boardTypService.updateTBL010101(boardTypModel)){
			mav.addObject("errCode", 3);
			mav.addObject("brdTyp", boardTypModel.getBrdTyp());
			mav.setViewName("redirect:/boardTypMng/list.do"); // success to add new member; move to login page
			return mav;
		} else {
			mav.addObject("errCode", 2); // failed to add new member
			mav.addObject("brdTyp", boardTypModel.getBrdTyp());
			mav.setViewName("redirect:/boardTypMng/edit.do");
			return mav;
		}
	}
	
	@RequestMapping("/list.do")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
		// 게시판리스트 조회
		List<BoardTypModel> boardTyplist = null;
		boardTyplist = boardTypService.getBoardTypList();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardTyplist", boardTyplist);
		mav.setViewName("/admin/board/typList");
		
		return mav;
	}
	
}//End class
