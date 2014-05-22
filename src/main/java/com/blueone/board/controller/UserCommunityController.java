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
import com.blueone.board.domain.BoardInfo;
import com.blueone.board.domain.BoardSrchInfo;
import com.blueone.board.domain.FaqInfo;
import com.blueone.board.service.IBoardService;
import com.blueone.common.domain.BaseInfo;
import com.blueone.customer.domain.CustomerInfo;

@Controller
public class UserCommunityController {
	
	@Autowired IBoardService boardService;
	
	@RequestMapping(value = "/community/notice.do", method = RequestMethod.GET)
	public String notice(@ModelAttribute("AdminInfo") AdminInfo adminInfo,
			BindingResult result, Model model, HttpSession session) {
		
		CustomerInfo customerSesstion =(CustomerInfo)session.getAttribute("customerSession");
		// 세션체크
		if (customerSesstion == null) {
			return "user/errorPage";
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
		
		return "community/notice";

	}
	
	@RequestMapping(value = "/community/noticeView.do", method = RequestMethod.GET)
	public String noticeView(@ModelAttribute("AdminInfo") AdminInfo adminInfo, BoardInfo brdInfo,BindingResult result, Model model, HttpSession session) {
		
		CustomerInfo customerSesstion =(CustomerInfo)session.getAttribute("customerSession");
		// 세션체크
		if (customerSesstion == null) {
			return "user/errorPage";
		}
				
		BoardInfo brdView = boardService.selectBOM_BOARD_TB(brdInfo.getBrdSeq());
		String cnt = brdView.getContent();
		cnt =cnt.replace("%0A", "<br>");
		brdView.setContent(cnt);
		model.addAttribute("brdView", brdView);
		 
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
		
	
		FaqInfo reFaqInfo = boardService.getFaqInfoByIdx(faq);
		model.addAttribute("reFaqInfo", reFaqInfo);
		
		return "community/faqView";

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
