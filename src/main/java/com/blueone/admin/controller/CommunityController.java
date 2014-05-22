package com.blueone.admin.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blueone.admin.domain.AdminInfo;
import com.blueone.admin.service.ICommunityService;
import com.blueone.board.controller.BoardController;
import com.blueone.board.domain.BoardInfo;
import com.blueone.board.domain.BoardSrchInfo;
import com.blueone.board.domain.FaqInfo;


import com.blueone.board.service.IBoardService;
import com.blueone.common.domain.BaseInfo;


@Controller
@RequestMapping(value = "/admin")
public class CommunityController {

	@Autowired
	IBoardService boardService;
	

	BoardController boardController = new BoardController();



	@RequestMapping(value = "/noticeBoard.do", method = RequestMethod.GET)
	public String noticeBoard(@ModelAttribute("AdminInfo") AdminInfo adminInfo,BindingResult result, Model model, HttpSession session) {

		AdminInfo adminSession = (AdminInfo) session
				.getAttribute("adminSession");
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}

		// 공지사항 페이지
		int currentPage = adminInfo.getCurrentPage();

		// ----------------------------------------------------
		// 공지사항 가져오기
		// ----------------------------------------------------
		BoardSrchInfo boardSrchInfo = new BoardSrchInfo();
		boardSrchInfo.setSrchBrdTyp(8);

		// 페이지정보 셋팅
		if (currentPage != 0)
			boardSrchInfo.setCurrentPage(currentPage);

		List<BoardInfo> boardList = boardService.getBrdTypBoardList(boardSrchInfo);
		boardSrchInfo.setTotalCount(boardService.getBrdTypTotalCount(boardSrchInfo));

		model.addAttribute("noticeList", boardList);
		model.addAttribute("pageHtml", getPageHtml(boardSrchInfo));

		

		 
		 
		return "admin/community/noticeBoard";

	}
	
	@RequestMapping(value = "/noticeDelete.do", method = RequestMethod.GET)
	public String noticeDelete(@ModelAttribute("AdminInfo") BoardInfo brdInfo,BindingResult result, Model model, HttpSession session) {
		
		AdminInfo adminSession = (AdminInfo) session
				.getAttribute("adminSession");
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}
		
		
		boardService.deleteBoardTBInf(brdInfo);
		
		return "redirect:noticeBoard.do";

	}
	
	@RequestMapping(value = "/noticeWrite.do", method = RequestMethod.GET)
	public String noticeWrite(@ModelAttribute("AdminInfo") AdminInfo adminInfo,BindingResult result, Model model, HttpSession session) {
	
		AdminInfo adminSession = (AdminInfo) session.getAttribute("adminSession");
		
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}
		
		
		model.addAttribute("admin", adminSession);
		
		return "admin/community/noticeWrite";

	}
	
	@RequestMapping(value = "/noticeWriteProc.do", method = RequestMethod.POST)
	public String noticeWriteProc(@ModelAttribute("AdminInfo") BoardInfo brdInfo,RedirectAttributes redirectAttributes,BindingResult result, Model model, HttpSession session) {
	
		AdminInfo adminSession = (AdminInfo) session.getAttribute("adminSession");
		
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}
		
		
		boardService.insertBoard(brdInfo);
		redirectAttributes.addFlashAttribute("reloadVar", "yes");
		
		return "redirect:noticeWrite.do";

	}
	
	//공지사항 수정
	@RequestMapping(value = "/noticeEdit.do", method = RequestMethod.GET)
	public String noticeEdit(@ModelAttribute("AdminInfo") BoardInfo brdInfo,BindingResult result, Model model, HttpSession session) {
	
		AdminInfo adminSession = (AdminInfo) session.getAttribute("adminSession");
		
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}
		
		BoardInfo editBrd = boardService.selectBOM_BOARD_TB(brdInfo.getBrdSeq());
		
		model.addAttribute("editBrd", editBrd);
		model.addAttribute("admin", adminSession);
		return "admin/community/noticeEdit";

	}
	
	@RequestMapping(value = "/noticeEditProc.do", method = RequestMethod.POST)
	public String noticeEditProc(@ModelAttribute("AdminInfo") BoardInfo brdInfo,RedirectAttributes redirectAttributes,BindingResult result, Model model, HttpSession session) {
	
		AdminInfo adminSession = (AdminInfo) session.getAttribute("adminSession");
		
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}
		
		
		boardService.updateBOM_BOARD_TB_notice(brdInfo);
		redirectAttributes.addFlashAttribute("reloadVar", "yes");
		
		return "redirect:noticeEdit.do?brdSeq="+brdInfo.getBrdSeq();

	}
	@RequestMapping(value = "/faqBoard.do", method = RequestMethod.GET)
	public String faqBoard(@ModelAttribute("AdminInfo") AdminInfo adminInfo,BindingResult result, Model model, HttpSession session) {
		
		AdminInfo adminSession = (AdminInfo) session
				.getAttribute("adminSession");
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}
		/*// 상품QnA 페이지
		int currentPage = adminInfo.getCurrentPage();

		// ----------------------------------------------------
		// 상품QnA 가져오기
		// ----------------------------------------------------
		BoardSrchInfo boardSrchInfo = new BoardSrchInfo();
		boardSrchInfo.setSrchBrdTyp(9);

		// 페이지정보 셋팅
		if (currentPage != 0)
			boardSrchInfo.setCurrentPage(currentPage);

		List<BoardInfo> boardList = boardService.getBrdTypBoardList(boardSrchInfo);
		boardSrchInfo.setTotalCount(boardService.getBrdTypTotalCount(boardSrchInfo));

		model.addAttribute("qnaList", boardList);
		model.addAttribute("pageHtml", getPageHtml(boardSrchInfo));*/
		
		
		 List<FaqInfo> faqList=boardService.getFaqInfoList();
		 model.addAttribute("faqList", faqList);
		 
		return "admin/community/faq";

	}
	
	
	@RequestMapping(value = "/faqWrite.do", method = RequestMethod.GET)
	public String faqWrite(@ModelAttribute("AdminInfo") FaqInfo faq,
			BindingResult result, Model model, HttpSession session) {
		AdminInfo adminSession = (AdminInfo) session
				.getAttribute("adminSession");
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}
		/*BoardSrchInfo boardSrchInfo = new BoardSrchInfo();
		BoardCommentInfo boardCommentModel = new BoardCommentInfo();

		// 상품QnA 페이지
		int currentPage = adminInfo.getCurrentPage();

		// 페이지정보 셋팅
		if (currentPage != 0)
			boardSrchInfo.setCurrentPage(currentPage);*/

		return "admin/community/faqWrite";

	}
	
	@RequestMapping(value = "/faqEdit.do", method = RequestMethod.GET)
	public String faqEdit(@ModelAttribute("AdminInfo") FaqInfo faq,
			BindingResult result, Model model, HttpSession session) {
		AdminInfo adminSession = (AdminInfo) session
				.getAttribute("adminSession");
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}
		/*BoardSrchInfo boardSrchInfo = new BoardSrchInfo();
		BoardCommentInfo boardCommentModel = new BoardCommentInfo();

		// 상품QnA 페이지
		int currentPage = adminInfo.getCurrentPage();

		// 페이지정보 셋팅
		if (currentPage != 0)
			boardSrchInfo.setCurrentPage(currentPage);*/
		FaqInfo reFaqInfo = boardService.getFaqInfoByIdx(faq);
		model.addAttribute("reFaqInfo", reFaqInfo);
		return "admin/community/faqEdit";

	}
	
	@RequestMapping(value = "/faqDelete.do", method = RequestMethod.GET)
	public String faqDelete(@ModelAttribute("AdminInfo") FaqInfo faq,
			BindingResult result, Model model, HttpSession session) {
		AdminInfo adminSession = (AdminInfo) session
				.getAttribute("adminSession");
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}
		/*BoardSrchInfo boardSrchInfo = new BoardSrchInfo();
		BoardCommentInfo boardCommentModel = new BoardCommentInfo();

		// 상품QnA 페이지
		int currentPage = adminInfo.getCurrentPage();

		// 페이지정보 셋팅
		if (currentPage != 0)
			boardSrchInfo.setCurrentPage(currentPage);*/
		boardService.deleteFaqInf(faq);
		
		return "redirect:faqBoard.do";

	}
	
	@RequestMapping(value = "/faqEditProc.do", method = RequestMethod.POST)
	public String faqEditProc(@ModelAttribute("AdminInfo") FaqInfo faq,
			BindingResult result, Model model,RedirectAttributes redirectAttributes, HttpSession session) {
		AdminInfo adminSession = (AdminInfo) session
				.getAttribute("adminSession");
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}
		/*BoardSrchInfo boardSrchInfo = new BoardSrchInfo();
		BoardCommentInfo boardCommentModel = new BoardCommentInfo();

		// 상품QnA 페이지
		int currentPage = adminInfo.getCurrentPage();

		// 페이지정보 셋팅
		if (currentPage != 0)
			boardSrchInfo.setCurrentPage(currentPage);*/
		 boardService.updateFaqInfo(faq);
		 redirectAttributes.addFlashAttribute("reloadVar", "yes");
		return "redirect:faqEdit.do?faqIdx="+faq.getFaqIdx();

	}
	@RequestMapping(value = "/faqWriteProc.do", method = RequestMethod.POST)
	public String faqWriteProc(@ModelAttribute("AdminInfo") FaqInfo faq,
			BindingResult result, Model model,RedirectAttributes redirectAttributes, HttpSession session) {
		AdminInfo adminSession = (AdminInfo) session
				.getAttribute("adminSession");
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}
		/*BoardSrchInfo boardSrchInfo = new BoardSrchInfo();
		BoardCommentInfo boardCommentModel = new BoardCommentInfo();

		// 상품QnA 페이지
		int currentPage = adminInfo.getCurrentPage();

		// 페이지정보 셋팅
		if (currentPage != 0)
			boardSrchInfo.setCurrentPage(currentPage);*/

		boardService.insertFaq(faq);
		redirectAttributes.addFlashAttribute("reloadVar", "yes");
		
		return "redirect:faqWrite.do";

	}

	/**
	 * 리스트의 하단 페이지를 돌려주는 메소드
	 * 
	 * @param boardSrchModel
	 * @return
	 */
	private String getPageHtml(BaseInfo baseModel) {
		StringBuffer pageHtml = new StringBuffer();
		int startPage = 0;
		int lastPage = 0;
		int prevPage = (baseModel.getCurrentPage() - 1);
		int nextPage = (baseModel.getCurrentPage() + 1);

		int pagesPerPage = baseModel.getPagesPerPage();
		if (pagesPerPage == 0) {
			pagesPerPage = 10;
		}

		// expression page variables
		startPage = ((baseModel.getCurrentPage() - 1) / baseModel
				.getPagesPerPage()) * baseModel.getPagesPerPage() + 1;
		lastPage = startPage + pagesPerPage - 1;

		if (lastPage > (baseModel.getTotalCount() / baseModel.getRowsPerPage())) {
			if ((baseModel.getTotalCount() % baseModel.getRowsPerPage()) == 0) {
				lastPage = (baseModel.getTotalCount() / baseModel
						.getRowsPerPage());
			} else {
				lastPage = (baseModel.getTotalCount() / baseModel
						.getRowsPerPage()) + 1;
			}
		}

		if (prevPage < 1)
			prevPage = 1;
		if (nextPage > lastPage)
			nextPage = lastPage;

		// create page html code
		// pageHtml.append("<div id='paging'>");
		// <a href="#" class="palign1"><img
		// src="<c:url value='/resources/img/common/btn_first.gif'/>"
		// alt="처음으로"></a>
		pageHtml.append("<a class='palign1'><img src='/resources/img/common/btn_first.gif' onclick='fnGotoPage(1);' alt='첫 페이지로 이동' /></a>");
		pageHtml.append("<a class='palign2'><img src='/resources/img/common/btn_prev.gif' onclick='fnGotoPage("
				+ prevPage + ");' alt='이전 페이지로 이동' /></a>");

		for (int i = startPage; i <= lastPage; i++) {
			if (i == baseModel.getCurrentPage()) {
				pageHtml.append("<a href='#' class='on'>" + i + "</a>");
			} else {
				pageHtml.append("<a href='javascript:fnGotoPage(" + i + ");'>"
						+ i + "</a>");
			}

		}

		pageHtml.append("<a class='palign1'><img src='/resources/img/common/btn_next.gif' onclick='fnGotoPage("
				+ nextPage + ");' alt='다음 페이지로 이동' /></a>");
		pageHtml.append("<a class='palign2'><img src='/resources/img/common/btn_end.gif' onclick='fnGotoPage("
				+ lastPage + ");' alt='마지막 페이지로 이동' /></a>");
		// pageHtml.append("</div>");

		return pageHtml.toString();
	}
}
