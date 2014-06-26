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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blueone.admin.domain.AdminInfo;
import com.blueone.board.domain.BoardInfo;
import com.blueone.board.domain.BoardSrchInfo;
import com.blueone.board.domain.FaqInfo;
import com.blueone.board.service.IBoardService;
import com.blueone.common.domain.BaseInfo;
import com.blueone.customer.domain.CustomerInfo;
import com.blueone.user.domain.UserInfo;

@Controller
public class UserCommunityController {
	
	@Autowired IBoardService boardService;
	
	@RequestMapping(value = "/community/communityMain.do", method = RequestMethod.GET)
	public String communityMain(@ModelAttribute("AdminInfo") AdminInfo adminInfo,
			BindingResult result, Model model, HttpSession session) {
		
		CustomerInfo customerSesstion =(CustomerInfo)session.getAttribute("customerSession");
		// 세션체크
		if (customerSesstion == null) {
			return "user/errorPage";
		}
		model.addAttribute("CUST_NAME", customerSesstion.getCustNm());
		String customerPoint = (String)session.getAttribute("customerPoint");
		model.addAttribute("CUST_POINT", customerPoint);
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
		
		return "community/notice";

	}
	
	@RequestMapping(value = "/community/notice.do", method = RequestMethod.GET)
	public String notice(@ModelAttribute("AdminInfo") AdminInfo adminInfo,
			BindingResult result, Model model, HttpSession session) {
		
		CustomerInfo customerSesstion =(CustomerInfo)session.getAttribute("customerSession");
		// 세션체크
		if (customerSesstion == null) {
			return "user/errorPage";
		}
		model.addAttribute("CUST_NAME", customerSesstion.getCustNm());
		String customerPoint = (String)session.getAttribute("customerPoint");
		model.addAttribute("CUST_POINT", customerPoint);
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
		
		return "community/notice";

	}
	@RequestMapping(value = "/community/noticeSeach.do")
	public String noticeSeach(@ModelAttribute("AdminInfo") AdminInfo adminInfo,BoardSrchInfo boardSrchInfo,BindingResult result, Model model, HttpSession session) {
		
		CustomerInfo customerSesstion =(CustomerInfo)session.getAttribute("customerSession");
		// 세션체크
		if (customerSesstion == null) {
			return "user/errorPage";
		}
		model.addAttribute("CUST_NAME", customerSesstion.getCustNm());
		String customerPoint = (String)session.getAttribute("customerPoint");
		model.addAttribute("CUST_POINT", customerPoint);	
		// 이벤트 페이지
		int currentPage = adminInfo.getCurrentPage();
		
		// ----------------------------------------------------
		// 이벤트 가져오기
		// ----------------------------------------------------
		
		boardSrchInfo.setSrchBrdTyp(11);
		
		// 페이지정보 셋팅
		if (currentPage != 0)
			boardSrchInfo.setCurrentPage(currentPage);

		List<BoardInfo> boardList = boardService.getBrdTypBoardList(boardSrchInfo);
		boardSrchInfo.setTotalCount(boardService.getBrdTypTotalCount(boardSrchInfo));
		
		model.addAttribute("noticeList", boardList);
		model.addAttribute("pageHtml", getPageHtml(boardSrchInfo));
		
		return "community/notice";

	}
	@RequestMapping(value = "/community/noticeView.do", method = RequestMethod.GET)
	public String noticeView(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BoardInfo brdInfo,BindingResult result, Model model, HttpSession session) {
		
		CustomerInfo customerSesstion =(CustomerInfo)session.getAttribute("customerSession");
		// 세션체크
		if (customerSesstion == null) {
			return "user/errorPage";
		}
		model.addAttribute("CUST_NAME", customerSesstion.getCustNm());
		String customerPoint = (String)session.getAttribute("customerPoint");
		model.addAttribute("CUST_POINT", customerPoint);	
		BoardInfo brdView = boardService.selectBOM_BOARD_TB(brdInfo.getBrdSeq());
		boardService.updateBoardHit(brdInfo);
		String cnt = brdView.getContent();
		cnt =cnt.replace("%0A", "<br>");
		brdView.setContent(cnt);
		model.addAttribute("brdView", brdView);
		 
		return "community/noticeView";

	}
	
	@RequestMapping(value = "/community/event.do", method = RequestMethod.GET)
	public String event(@ModelAttribute("AdminInfo") AdminInfo adminInfo,BindingResult result, Model model, HttpSession session) {
		
		CustomerInfo customerSesstion =(CustomerInfo)session.getAttribute("customerSession");
		// 세션체크
		if (customerSesstion == null) {
			return "user/errorPage";
		}
		model.addAttribute("CUST_NAME", customerSesstion.getCustNm());
		String customerPoint = (String)session.getAttribute("customerPoint");
		model.addAttribute("CUST_POINT", customerPoint);	
		
		// 이벤트 페이지
		int currentPage = adminInfo.getCurrentPage();
		
		// ----------------------------------------------------
		// 이벤트 가져오기
		// ----------------------------------------------------
		BoardSrchInfo boardSrchInfo = new BoardSrchInfo();
		boardSrchInfo.setSrchBrdTyp(11);

		// 페이지정보 셋팅
		if (currentPage != 0)
			boardSrchInfo.setCurrentPage(currentPage);

		List<BoardInfo> boardList = boardService.getBrdTypBoardList(boardSrchInfo);
		boardSrchInfo.setTotalCount(boardService.getBrdTypTotalCount(boardSrchInfo));
		
		model.addAttribute("noticeList", boardList);
		model.addAttribute("pageHtml", getPageHtml(boardSrchInfo));
		
		return "community/event";

	}
	
	@RequestMapping(value = "/community/eventSeach.do")
	public String eventSeach(@ModelAttribute("AdminInfo") AdminInfo adminInfo,BoardSrchInfo boardSrchInfo,BindingResult result, Model model, HttpSession session) {
		
		CustomerInfo customerSesstion =(CustomerInfo)session.getAttribute("customerSession");
		// 세션체크
		if (customerSesstion == null) {
			return "user/errorPage";
		}
		model.addAttribute("CUST_NAME", customerSesstion.getCustNm());
		String customerPoint = (String)session.getAttribute("customerPoint");
		model.addAttribute("CUST_POINT", customerPoint);		
		// 이벤트 페이지
		int currentPage = adminInfo.getCurrentPage();
		
		// ----------------------------------------------------
		// 이벤트 가져오기
		// ----------------------------------------------------
		
		boardSrchInfo.setSrchBrdTyp(11);
		
		// 페이지정보 셋팅
		if (currentPage != 0)
			boardSrchInfo.setCurrentPage(currentPage);

		List<BoardInfo> boardList = boardService.getBrdTypBoardList(boardSrchInfo);
		boardSrchInfo.setTotalCount(boardService.getBrdTypTotalCount(boardSrchInfo));
		
		model.addAttribute("noticeList", boardList);
		model.addAttribute("pageHtml", getPageHtml(boardSrchInfo));
		
		return "community/event";

	}
	@RequestMapping(value = "/community/eventView.do", method = RequestMethod.GET)
	public String eventView(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BoardInfo brdInfo,BindingResult result, Model model, HttpSession session) {
		
		CustomerInfo customerSesstion =(CustomerInfo)session.getAttribute("customerSession");
		// 세션체크
		if (customerSesstion == null) {
			return "user/errorPage";
		}
		
		//head.jsp부분 이름과 포인트 보여주기 
		model.addAttribute("CUST_NAME", customerSesstion.getCustNm());
		String customerPoint = (String)session.getAttribute("customerPoint");
		model.addAttribute("CUST_POINT", customerPoint);
		
		//이벤트 보여주기
		BoardInfo brdView = boardService.selectBOM_BOARD_TB(brdInfo.getBrdSeq());
		model.addAttribute("brdView", brdView);
		boardService.updateBoardHit(brdInfo);
		
		// ----------------------------------------------------
		// 상품댓글 가져오기
		// ----------------------------------------------------
		BoardSrchInfo boardSrchInfo = new BoardSrchInfo();
		boardSrchInfo.setSrchBrdTyp(10);
		boardSrchInfo.setBrdCodeType("01");
		boardSrchInfo.setBrdCodeKey("E"+Long.toString(brdInfo.getBrdSeq()));

		/*// 페이지정보 셋팅
		if (currentPage != 0)
			boardSrchInfo.setCurrentPage(currentPage);*/

		List<BoardInfo> boardList = boardService
				.getBrdTypBoardList(boardSrchInfo);
		boardSrchInfo.setTotalCount(boardService
				.getBrdTypTotalCount(boardSrchInfo));

		model.addAttribute("qnaList", boardList);
		//model.addAttribute("pageHtml",	getPageHtml(Long.toString(brdInfo.getBrdSeq()), boardSrchInfo));
		return "community/eventView";

	}
	@RequestMapping(value = "/community/writeEvent.do")
	public String writeQNAByProdCd(String content, String brdSeq,HttpSession session) {
		CustomerInfo cust = (CustomerInfo) session
				.getAttribute("customerSession");
		if (cust == null) { // 세션체크
		// return "user/errorPage";
			cust = new CustomerInfo();
			cust.setCustId("Guest!");
		}
		
		int brdTyp = 10;// 게시판유형 = 10 (event댓글)

		BoardInfo boardInfo = new BoardInfo();
		boardInfo.setBrdTyp(brdTyp);
		boardInfo.setInsUser(cust.getCustId()+"_"+cust.getCustNm());
		boardInfo.setContent(content);
		boardInfo.setSrchBrdTyp(brdTyp);
		boardInfo.setBrdCodeKey("E"+brdSeq);
		boardInfo.setBrdCodeType("01"); //

		if (boardService.insertBoard(boardInfo)) {
			// 글이 등록됐음
			System.out.println("ok");
		} else {
			// 글 등록이 안됐음.
			System.out.println("fail");
		}

		String viewName = "redirect:eventView.do?brdSeq=" + brdSeq;
		return viewName;
	}
	//event 댓글 삭제
	@RequestMapping(value = "/community/eventDelete.do", method = RequestMethod.GET)
	public String eventDelete(@ModelAttribute("AdminInfo") BoardInfo brdInfo,BindingResult result, Model model, HttpSession session,int pageSeq) {
		CustomerInfo customerSesstion =(CustomerInfo)session.getAttribute("customerSession");
		// 세션체크
		if (customerSesstion == null) {
			return "user/errorPage";
		}
		model.addAttribute("CUST_NAME", customerSesstion.getCustNm());
		String customerPoint = (String)session.getAttribute("customerPoint");
		model.addAttribute("CUST_POINT", customerPoint);
		boardService.deleteBoardTBInf(brdInfo);
		
		return "redirect:eventView.do?brdSeq=" + pageSeq;

	}
	@RequestMapping(value = "/community/faqList.do", method = RequestMethod.GET)
	public String faqList(@ModelAttribute("AdminInfo") AdminInfo adminInfo,
			BindingResult result, Model model, HttpSession session) {
		
		CustomerInfo customerSesstion =(CustomerInfo)session.getAttribute("customerSession");
		// 세션체크
		if (customerSesstion == null) {
			return "user/errorPage";
		}
		model.addAttribute("CUST_NAME", customerSesstion.getCustNm());
		String customerPoint = (String)session.getAttribute("customerPoint");
		model.addAttribute("CUST_POINT", customerPoint);		
		
		
		 List<FaqInfo> faqList=boardService.getFaqInfoList();
		 for(FaqInfo each: faqList){
			 String cnt = each.getFaqQes();
			 cnt =cnt.replace("%0A", "<br>");
			 each.setFaqQes(cnt);
		 }
		 model.addAttribute("faqList", faqList);
		 
		return "community/faqList";

	}
	
	@RequestMapping(value = "/community/faqSearch.do", method = RequestMethod.POST)
	public String faqSearch(@ModelAttribute("AdminInfo") FaqInfo faqInfo,
			BindingResult result, Model model, HttpSession session) {
		
		CustomerInfo customerSesstion =(CustomerInfo)session.getAttribute("customerSession");
		// 세션체크
		if (customerSesstion == null) {
			return "user/errorPage";
		}
		model.addAttribute("CUST_NAME", customerSesstion.getCustNm());
		String customerPoint = (String)session.getAttribute("customerPoint");
		model.addAttribute("CUST_POINT", customerPoint);		
		
		
		 List<FaqInfo> faqList=boardService.getFaqInfoList(faqInfo);
		 
		 for(FaqInfo each: faqList){
			 String cnt = each.getFaqQes();
			 cnt =cnt.replace("%0A", "<br>");
			 each.setFaqQes(cnt);
		 }
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
		model.addAttribute("CUST_NAME", customerSesstion.getCustNm());
		String customerPoint = (String)session.getAttribute("customerPoint");
		model.addAttribute("CUST_POINT", customerPoint);
	
		FaqInfo reFaqInfo = boardService.getFaqInfoByIdx(faq);
		model.addAttribute("reFaqInfo", reFaqInfo);
		
		return "community/faqView";

	}
	//1:1문의하기 목록
	@RequestMapping(value="/community/qnaList.do", method=RequestMethod.GET)
	public String qnaList(@ModelAttribute("AdminInfo") AdminInfo adminInfo,BindingResult result, Model model, HttpSession session){
		CustomerInfo customerSesstion =(CustomerInfo)session.getAttribute("customerSession");
		// 세션체크
		if (customerSesstion == null) {
			return "user/errorPage";
		}
		model.addAttribute("CUST_NAME", customerSesstion.getCustNm());
		String customerPoint = (String)session.getAttribute("customerPoint");
		model.addAttribute("CUST_POINT", customerPoint);	
		
		// 이벤트 페이지
		int currentPage = adminInfo.getCurrentPage();
		
		// ----------------------------------------------------
		// 이벤트 가져오기
		// ----------------------------------------------------
		BoardSrchInfo boardSrchInfo = new BoardSrchInfo();
		boardSrchInfo.setSrchBrdTyp(20);
		boardSrchInfo.setSrchUserNm(customerSesstion.getCustId()+"_"+customerSesstion.getCustNm());
		// 페이지정보 셋팅
		if (currentPage != 0)
			boardSrchInfo.setCurrentPage(currentPage);

		List<BoardInfo> boardList = boardService.getBrdTypBoardList(boardSrchInfo);
		boardSrchInfo.setTotalCount(boardService.getBrdTypTotalCount(boardSrchInfo));
		
		
		model.addAttribute("pageHtml", getPageHtml(boardSrchInfo));
		
		for(BoardInfo each : boardList ){
			// ----------------------------------------------------
			// 답변수 가져오기
			// ----------------------------------------------------
			BoardSrchInfo boardSrchInfo1 = new BoardSrchInfo();
			boardSrchInfo1.setSrchBrdTyp(21);
			boardSrchInfo1.setBrdCodeType("01");
			boardSrchInfo1.setBrdCodeKey(Long.toString(each.getBrdSeq()));
	
			each.setHit(boardService.getBrdTypTotalCount(boardSrchInfo));
		}
		
		model.addAttribute("noticeList", boardList);
		model.addAttribute("qnaList", boardList);
		return "community/qnaPage";
	}
	//Q&A 등록
	@RequestMapping(value = "/community/qnaWrite.do", method = RequestMethod.GET)
	public String qnaWrite(@ModelAttribute("AdminInfo") AdminInfo adminInfo,BindingResult result, Model model, HttpSession session) {
	
		CustomerInfo customerSesstion =(CustomerInfo)session.getAttribute("customerSession");
		// 세션체크
		if (customerSesstion == null) {
			return "user/errorPage";
		}
		model.addAttribute("CUST_NAME", customerSesstion.getCustNm());
		String customerPoint = (String)session.getAttribute("customerPoint");
		model.addAttribute("CUST_POINT", customerPoint);	
		model.addAttribute("cust", customerSesstion);	
		return "community/qnaWrite";

	}
	
	//Q&A 등록 처리
	@RequestMapping(value = "/community/qnaWriteProc.do", method = RequestMethod.POST)
	public String qnaWriteProc(@ModelAttribute("AdminInfo") BoardInfo brdInfo,RedirectAttributes redirectAttributes,BindingResult result, Model model, HttpSession session) {
	
		CustomerInfo customerSesstion =(CustomerInfo)session.getAttribute("customerSession");
		// 세션체크
		if (customerSesstion == null) {
			return "user/errorPage";
		}
		
		brdInfo.setInsUser(customerSesstion.getCustId()+"_"+customerSesstion.getCustNm());
		brdInfo.setUpdUser(customerSesstion.getCustId()+"_"+customerSesstion.getCustNm());
		
		boardService.insertBoard(brdInfo);
		redirectAttributes.addFlashAttribute("reloadVar", "yes");
		
		return "redirect:qnaList.do";

	}
	
	@RequestMapping(value = "/community/qnaView.do", method = RequestMethod.GET)
	public String qnaView(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BoardInfo brdInfo,BindingResult result, Model model, HttpSession session) {
		

		CustomerInfo customerSesstion =(CustomerInfo)session.getAttribute("customerSession");
		// 세션체크
		if (customerSesstion == null) {
			return "user/errorPage";
		}
		
		//head.jsp부분 이름과 포인트 보여주기 
		model.addAttribute("CUST_NAME", customerSesstion.getCustNm());
		String customerPoint = (String)session.getAttribute("customerPoint");
		model.addAttribute("CUST_POINT", customerPoint);
		
		//이벤트 보여주기
		BoardInfo brdView = boardService.selectBOM_BOARD_TB(brdInfo.getBrdSeq());
		model.addAttribute("brdView", brdView);
		boardService.updateBoardHit(brdInfo);
		
		// ----------------------------------------------------
		// 상품댓글 가져오기
		// ----------------------------------------------------
		BoardSrchInfo boardSrchInfo = new BoardSrchInfo();
		boardSrchInfo.setSrchBrdTyp(21);
		boardSrchInfo.setBrdCodeType("01");
		boardSrchInfo.setBrdCodeKey(Long.toString(brdInfo.getBrdSeq()));

		/*// 페이지정보 셋팅
		if (currentPage != 0)
			boardSrchInfo.setCurrentPage(currentPage);*/

		List<BoardInfo> boardList = boardService
				.getBrdTypBoardList(boardSrchInfo);
		boardSrchInfo.setTotalCount(boardService
				.getBrdTypTotalCount(boardSrchInfo));

		model.addAttribute("qnaList", boardList);
		//model.addAttribute("pageHtml",	getPageHtml(Long.toString(brdInfo.getBrdSeq()), boardSrchInfo));
		
		 
		return "community/qnaView";

	}
	//qna 수정
	@RequestMapping(value = "/community/qnaEdit.do", method = RequestMethod.GET)
	public String qnaEdit(@ModelAttribute("AdminInfo") BoardInfo brdInfo,BindingResult result, Model model, HttpSession session) {
		CustomerInfo customerSesstion =(CustomerInfo)session.getAttribute("customerSession");
		// 세션체크
		if (customerSesstion == null) {
			return "user/errorPage";
		}
		
		//head.jsp부분 이름과 포인트 보여주기 
		model.addAttribute("CUST_NAME", customerSesstion.getCustNm());
		String customerPoint = (String)session.getAttribute("customerPoint");
		model.addAttribute("CUST_POINT", customerPoint);
		
		BoardInfo editEventBrd = boardService.selectBOM_BOARD_TB(brdInfo.getBrdSeq());
		
		model.addAttribute("editBrd", editEventBrd);
		
		return "/community/qnaEdit";

	}
	
	@RequestMapping(value = "/community/qnaEditProc.do", method = RequestMethod.POST)
	public String qnaEditProc(@ModelAttribute("AdminInfo") BoardInfo brdInfo,RedirectAttributes redirectAttributes,BindingResult result, Model model, HttpSession session) {
	
		CustomerInfo customerSesstion =(CustomerInfo)session.getAttribute("customerSession");
		// 세션체크
		if (customerSesstion == null) {
			return "user/errorPage";
		}
		
		//head.jsp부분 이름과 포인트 보여주기 
		model.addAttribute("CUST_NAME", customerSesstion.getCustNm());
		String customerPoint = (String)session.getAttribute("customerPoint");
		model.addAttribute("CUST_POINT", customerPoint);
		
		boardService.updateBOM_BOARD_TB_notice(brdInfo);
		
		
		return "redirect:qnaView.do?brdSeq="+brdInfo.getBrdSeq();

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
