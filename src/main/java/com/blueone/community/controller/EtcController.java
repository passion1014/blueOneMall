package com.blueone.community.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.blueone.board.controller.BoardController;
import com.blueone.board.domain.BoardCommentInfo;
import com.blueone.board.domain.BoardInfo;
import com.blueone.board.domain.BoardSrchInfo;
import com.blueone.board.service.IBoardService;
import com.blueone.board.service.IBoardTypService;
import com.blueone.common.service.HTMLContService;
import com.blueone.common.service.IHTMLContService;
import com.blueone.common.util.Utility;

@Controller
@RequestMapping("/etc")
public class EtcController {
	@Autowired private IHTMLContService htmlContService;

	private BoardController boardController = new BoardController();
	
	@RequestMapping("/etc01.do")
	public ModelAndView etc01(@ModelAttribute("BoardSrchModel") BoardSrchInfo boardSrchModel, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = boardController.boardList(boardSrchModel, request, response);
		mav.setViewName("/etc/etc01");
		return mav;
	}
	
	@RequestMapping("/etc01write.do")
	public ModelAndView etc01write(@ModelAttribute("BoardSrchModel") BoardSrchInfo boardSrchModel, HttpServletRequest request) {
		ModelAndView mav = boardController.add(boardSrchModel, request);
		mav.setViewName("/etc/etc01write");
		return mav;
	}
	
	@RequestMapping(value="/etc01write.do", method = RequestMethod.POST)
	public ModelAndView etc01write(@ModelAttribute("BoardModel") BoardInfo boardModel, BindingResult result, HttpSession session) {
		ModelAndView mav = boardController.add(boardModel, result, session);
		mav.addObject("srchBrdTyp", boardModel.getBrdTyp());
		mav.setViewName("redirect:/etc/etc01.do");
		return mav;
	}
	
	@RequestMapping(value="/etc01writeComment.do", method = RequestMethod.POST)
	public ModelAndView etc01writeComment(@ModelAttribute("BoardCommentModel") BoardCommentInfo boardCommentModel, BindingResult result, HttpSession session) {
		ModelAndView mav = boardController.addComment(boardCommentModel, result, session);
		mav.setViewName("redirect:/etc/etc01view.do");
		return mav;
	}
	
	@RequestMapping("/etc01edit.do")
	public ModelAndView etc01edit(@ModelAttribute("BoardSrchModel") BoardSrchInfo boardSrchModel, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = boardController.edit(boardSrchModel, request, session);
		mav.setViewName("/etc/etc01edit");
		return mav;
	}
	
	@RequestMapping(value="/etc01edit.do", method = RequestMethod.POST)
	public ModelAndView etc01edit(@ModelAttribute("BoardModel") BoardInfo boardModel, BindingResult result, HttpSession session) {
		ModelAndView mav = boardController.edit(boardModel, result, session);
		mav.setViewName("redirect:/etc/etc01.do");
		return mav;
	}
	
	@RequestMapping(value="/etc01editComment.do", method = RequestMethod.POST)
	public ModelAndView etc01editComment(@ModelAttribute("BoardCommentModel") BoardCommentInfo boardCommentModel, BindingResult result, HttpSession session) {
		ModelAndView mav = boardController.editComment(boardCommentModel, result, session);
		mav.setViewName("redirect:/etc/etc01view.do");
		return mav;
	}
	
	@RequestMapping("/etc01view.do")
	public ModelAndView etc01view(@ModelAttribute("BoardSrchModel") BoardSrchInfo boardSrchModel, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = boardController.view(boardSrchModel, request, session);
		mav.setViewName("/etc/etc01view");
		return mav;
	}
	
	@RequestMapping("/etc01delete.do")
	public ModelAndView etc01delete(@ModelAttribute("BoardSrchModel") BoardSrchInfo boardSrchModel, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelAndView mav = boardController.delete(boardSrchModel, request, response, session);
		mav.addObject("srchKeyword", boardSrchModel.getSrchKeyword());
		mav.addObject("srchBrdTyp", boardSrchModel.getSrchBrdTyp());
		mav.setViewName("redirect:/etc/etc01.do");
		return mav;
	}
	
	@RequestMapping("/etc01deleteComment.do")
	public ModelAndView etc01deleteComment(@ModelAttribute("BoardCommentModel") BoardCommentInfo boardCommentModel, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelAndView mav = boardController.deleteComment(boardCommentModel, request, response, session);
		mav.addObject("srchKeyword", boardCommentModel.getSrchKeyword());
		mav.addObject("srchBrdTyp", boardCommentModel.getSrchBrdTyp());
		mav.setViewName("redirect:/etc/etc01view.do");
		return mav;
	}
	
	
	
	
	
	@RequestMapping("/etc02.do")
	public ModelAndView etc02(HttpServletRequest request, HttpServletResponse response) {
		String strHtmlCont = request.getParameter("htmlSeq");
		int htmlCont = 17;
		if (!Utility.isEmpty(strHtmlCont)) {
			htmlCont = Integer.parseInt(strHtmlCont);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/etc/etc02");
		mav.addObject("htmlCont", htmlContService.getHtmlCont(htmlCont));
		return mav;
	}
	
	@RequestMapping("/etc03.do")
	public ModelAndView etc03(HttpServletRequest request, HttpServletResponse response) {
		String strHtmlCont = request.getParameter("htmlSeq");
		int htmlCont = 18;
		if (!Utility.isEmpty(strHtmlCont)) {
			htmlCont = Integer.parseInt(strHtmlCont);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/etc/etc03");
		mav.addObject("htmlCont", htmlContService.getHtmlCont(htmlCont));
		return mav;
	}
	
	@RequestMapping("/etc04.do")
	public ModelAndView etc04(HttpServletRequest request, HttpServletResponse response) {
		String strHtmlCont = request.getParameter("htmlSeq");
		int htmlCont = 26;
		if (!Utility.isEmpty(strHtmlCont)) {
			htmlCont = Integer.parseInt(strHtmlCont);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/etc/etc04");
		mav.addObject("htmlCont", htmlContService.getHtmlCont(htmlCont));
		return mav;
	}
	
}//End class
