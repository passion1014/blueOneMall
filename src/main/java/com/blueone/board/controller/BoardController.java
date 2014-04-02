package com.blueone.board.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.blueone.board.domain.BoardAttachFileInfo;
import com.blueone.board.domain.BoardCommentInfo;
import com.blueone.board.domain.BoardInfo;
import com.blueone.board.domain.BoardSrchInfo;
import com.blueone.board.service.IBoardService;
import com.blueone.board.service.IBoardTypService;
import com.blueone.common.domain.BaseInfo;
import com.blueone.common.util.FileDownloadUtility;
import com.blueone.common.util.FileUploadUtility;
import com.blueone.common.util.Utility;
import com.blueone.login.domain.LoginSessionModel;

@Controller
@RequestMapping("/board")
public class BoardController {
	// DI
//	private ApplicationContext context = new ClassPathXmlApplicationContext("/config/applicationContext.xml");
//	private BoardService boardService = (BoardService) context.getBean("boardService");
//	private BoardTypService boardTypService = (BoardTypService) context.getBean("boardTypService");
	
	@Autowired private IBoardService boardService;
	@Autowired private IBoardTypService boardTypService;
	
	@RequestMapping("/list.do")
	public ModelAndView boardList(@ModelAttribute("BoardSrchModel") BoardSrchInfo boardSrchInfo, HttpServletRequest request, HttpServletResponse response) {
		long[] noticeBrdSeq = {};
		List<BoardInfo> noticeList = null;
		List<BoardInfo> boardList = null;
		
		if (boardSrchInfo.getSrchBrdTyp() > 0) {
			
			noticeList = boardService.getBrdTypNoticeList(boardSrchInfo);
			if (noticeList != null && noticeList.size() > 0) {
				noticeBrdSeq = new long[noticeList.size()];
				boardSrchInfo.setRowsPerPage(10 - noticeList.size());
				for (int i = 0; i < noticeList.size(); i++) {
					noticeBrdSeq[i] = noticeList.get(i).getBrdSeq();
				}
			}
			
			boardSrchInfo.setNoticeBrdSeq(noticeBrdSeq);
			boardList = boardService.getBrdTypBoardList(boardSrchInfo);
			boardSrchInfo.setTotalCount(boardService.getBrdTypTotalCount(boardSrchInfo));
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("srchInfo", boardSrchInfo);
		mav.addObject("noticeList", noticeList);
		mav.addObject("boardList", boardList);
		mav.addObject("pageHtml", getPageHtml(boardSrchInfo));
		mav.addObject("brdTypInfo", boardTypService.getBoardTyp(boardSrchInfo.getSrchBrdTyp()));
		mav.setViewName("/board/list");
		
		return mav;
	}
	
	@RequestMapping("/add.do")
	public ModelAndView add(@ModelAttribute("BoardSrchModel") BoardSrchInfo boardSrchModel, HttpServletRequest request) {
		String srchBrdTyp = request.getParameter("srchBrdTyp");
		int brdTyp = Integer.parseInt((Utility.isEmpty(srchBrdTyp) ? "0" : srchBrdTyp));
		
		BoardInfo boardModal = new BoardInfo();
		boardModal.setRootSeq(0);
		boardModal.setRefSeq(1);
		boardModal.setDepth(0);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/board/write");
		mav.addObject("srchBrdTyp", srchBrdTyp);
		mav.addObject("brdTypInfo", boardTypService.getBoardTyp(brdTyp));
		mav.addObject("board", boardModal);
		mav.addObject("srchInfo", boardSrchModel);
		return mav;
	}
	
	@RequestMapping(value="/add.do", method = RequestMethod.POST)
	public ModelAndView add(@ModelAttribute("boardInfo") BoardInfo boardInfo, BindingResult result, HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		LoginSessionModel userInfo = (LoginSessionModel) session.getAttribute("userInfo");
		if (userInfo == null) {
			boardInfo.setInsUser("Guest");
			boardInfo.setUpdUser("Guest");
		} else {
			boardInfo.setInsUser(userInfo.getUserId());
			boardInfo.setUpdUser(userInfo.getUserId());
		}
		
		// 게시판유형 체크
		if (boardInfo.getBrdTyp() < 1) {
			boardInfo.setBrdTyp(boardInfo.getSrchBrdTyp());
		}
		
		if(boardService.insertBoard(boardInfo)){
			mav.addObject("errCode", 3);
			mav.setViewName("redirect:/board/list.do"); // success to add new member; move to login page
			return mav;
		} else {
			mav.addObject("errCode", 2); // failed to add new member
			mav.addObject("srchBrdTyp", boardInfo.getBrdTyp());
			mav.setViewName("redirect:/board/add.do");
			return mav;
		}
		
	}
	
	@RequestMapping(value="/addComment.do", method = RequestMethod.POST)
	public ModelAndView addComment(@ModelAttribute("BoardCommentModel") BoardCommentInfo boardCommentModel, BindingResult result, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		LoginSessionModel userInfo = (LoginSessionModel) session.getAttribute("userInfo");
		if (userInfo == null) {
			boardCommentModel.setInsUser("Guest");
			boardCommentModel.setUpdUser("Guest");
		} else {
			boardCommentModel.setInsUser(userInfo.getUserId());
			boardCommentModel.setUpdUser(userInfo.getUserId());
		}
		
		// 코멘트수 업데이트
		boardService.updateBOM_BOARD_TBCommCnt(boardCommentModel.getBrdSeq());
		
		if(boardService.insertBOM_BOARD_CMT_TB(boardCommentModel)){
			mav.addObject("errCode", 3);
			mav.addObject("brdSeq", boardCommentModel.getBrdSeq());
			mav.addObject("srchBrdTyp", boardCommentModel.getSrchBrdTyp());
			mav.setViewName("redirect:/board/view.do"); // success to add new member; move to login page
			return mav;
		} else {
			mav.addObject("errCode", 2); // failed to add new member
			mav.addObject("brdSeq", boardCommentModel.getBrdSeq());
			mav.addObject("srchBrdTyp", boardCommentModel.getSrchBrdTyp());
			mav.setViewName("redirect:/board/view.do");
			return mav;
		}
	}
	
	@RequestMapping("/edit.do")
	public ModelAndView edit(@ModelAttribute("BoardSrchModel") BoardSrchInfo boardSrchModel, HttpServletRequest request, HttpSession session){
		String srchBrdSeq = request.getParameter("srchBrdSeq");
		if (Utility.isEmpty(srchBrdSeq)) {
			srchBrdSeq = request.getParameter("brdSeq");
			if (Utility.isEmpty(srchBrdSeq)) {
				srchBrdSeq = "0";
			}
		}
		
		// 조회
		long brdSeq = Long.parseLong(srchBrdSeq);		
		BoardInfo board = boardService.selectBOM_BOARD_TB(brdSeq);
		List<BoardAttachFileInfo> attaFileList = boardService.selectBOM_ATTACHFILE_TB(brdSeq);
		//List<BoardCommentModel> commentList = boardService.selectTBL010104(brdSeq);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("board", board);
		mav.addObject("brdSeq", srchBrdSeq);
		mav.addObject("srchBrdTyp", request.getParameter("srchBrdTyp"));
		mav.addObject("srchInfo", boardSrchModel);
		mav.addObject("attaFileList", attaFileList);
		//mav.addObject("commentList", commentList);
		mav.addObject("brdTypInfo", boardTypService.getBoardTyp(boardSrchModel.getSrchBrdTyp()));
		mav.setViewName("/board/edit");
		return mav;
	}
	
	@RequestMapping(value="/edit.do", method = RequestMethod.POST)
	public ModelAndView edit(@ModelAttribute("BoardModel") BoardInfo boardModel, BindingResult result, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		LoginSessionModel userInfo = (LoginSessionModel) session.getAttribute("userInfo");
		if (userInfo == null) {
			boardModel.setUpdUser("Guest");
		} else {
			boardModel.setUpdUser(userInfo.getUserId());
		}
		
		if(boardService.updateBoard(boardModel)){
			mav.addObject("errCode", 3);
			mav.addObject("srchBrdTyp", boardModel.getSrchBrdTyp());
			mav.setViewName("redirect:/board/list.do"); // success to add new member; move to login page
			return mav;
		} else {
			mav.addObject("errCode", 2); // failed to add new member
			mav.addObject("brdSeq", boardModel.getBrdSeq());
			mav.addObject("srchBrdTyp", boardModel.getSrchBrdTyp());
			mav.setViewName("redirect:/board/edit.do");
			return mav;
		}
	}
	
	@RequestMapping(value="/editComment.do", method = RequestMethod.POST)
	public ModelAndView editComment(@ModelAttribute("BoardCommentModel") BoardCommentInfo boardCommentModel, BindingResult result, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		LoginSessionModel userInfo = (LoginSessionModel) session.getAttribute("userInfo");
		if (userInfo == null) {
			boardCommentModel.setUpdUser("Guest");
		} else {
			boardCommentModel.setUpdUser(userInfo.getUserId());
		}
		
		if(boardService.updateBOM_BOARD_CMT_TB(boardCommentModel)){
			mav.addObject("errCode", 3);
			mav.addObject("brdSeq", boardCommentModel.getBrdSeq());
			mav.addObject("srchBrdTyp", boardCommentModel.getSrchBrdTyp());
			mav.setViewName("redirect:/board/view.do"); // success to add new member; move to login page
			return mav;
		} else {
			mav.addObject("errCode", 2); // failed to add new member
			mav.addObject("brdSeq", boardCommentModel.getBrdSeq());
			mav.addObject("srchBrdTyp", boardCommentModel.getSrchBrdTyp());
			mav.setViewName("redirect:/board/view.do");
			return mav;
		}
	}
	
	@RequestMapping("/view.do")
	public ModelAndView view(@ModelAttribute("BoardSrchModel") BoardSrchInfo boardSrchModel, HttpServletRequest request, HttpSession session) {
		
		// 조회
		long brdSeq = getBrdSeq(request);		
		BoardInfo board = boardService.selectBOM_BOARD_TB(brdSeq);
		List<BoardAttachFileInfo> attaFileList = boardService.selectBOM_ATTACHFILE_TB(brdSeq);
		List<BoardCommentInfo> commentList = boardService.selectBOM_BOARD_CMT_TB(brdSeq);
		
		// 조회수 업데이트
		boardService.updateBOM_BOARD_TBHit(brdSeq);
		
		// 코멘트정보
		BoardCommentInfo boardCommentModel = new BoardCommentInfo();
		boardCommentModel.setCommRootNo(0);
		boardCommentModel.setCommRefNo(1);
		boardCommentModel.setCommDepth(0);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("board", board);
		mav.addObject("srchInfo", boardSrchModel);
		mav.addObject("attaFileList", attaFileList);
		mav.addObject("comment", boardCommentModel);
		mav.addObject("commentList", commentList);
		mav.addObject("brdTypInfo", boardTypService.getBoardTyp(boardSrchModel.getSrchBrdTyp()));
		mav.setViewName("/board/view");
		return mav;
	}
	
	@RequestMapping("/delete.do")
	public ModelAndView delete(@ModelAttribute("BoardSrchModel") BoardSrchInfo boardSrchModel, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
		LoginSessionModel userInfo = (LoginSessionModel) session.getAttribute("userInfo");
		
		// 게시물일련번호
		long brdSeq = boardSrchModel.getBrdSeq();
		if (brdSeq < 1) brdSeq = boardSrchModel.getSrchBrdSeq();
		
		// 삭제자
		String userId = "Guest";
		if (userInfo != null) userId = userInfo.getUserId();
		
		// 삭제처리
		boardService.updateBOM_BOARD_TBDel(brdSeq, userId);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("srchInfo", boardSrchModel);
		return mav;
	}
	
	@RequestMapping("/deleteComment.do")
	public ModelAndView deleteComment(@ModelAttribute("BoardCommentModel") BoardCommentInfo boardCommentModel, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		LoginSessionModel userInfo = (LoginSessionModel) session.getAttribute("userInfo");
		
		// 삭제자
		String userId = "Guest";
		if (userInfo != null) userId = userInfo.getUserId();
		
		// 삭제처리
		boardService.updateBOM_BOARD_CMT_TBDel(boardCommentModel.getBrdSeq(), boardCommentModel.getCommNo(), userId);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("brdSeq", boardCommentModel.getBrdSeq());
		mav.addObject("srchBrdTyp", boardCommentModel.getSrchBrdTyp());
		mav.setViewName("redirect:/board/view.do");
		return mav;
	}
	
	@RequestMapping("/tPage.do")
	public String tPage(){		
		return "/board/tPage";
	}
	
	/**
	 * 리스트의 하단 페이지를 돌려주는 메소드
	 * @param boardSrchModel
	 * @return
	 */
	private String getPageHtml(BaseInfo baseModel) {
		StringBuffer pageHtml = new StringBuffer();
		int startPage = 0;
		int lastPage = 0;
		int prevPage=  (baseModel.getCurrentPage() - 1);
		int nextPage = (baseModel.getCurrentPage() + 1);
		
		// expression page variables
		startPage = ((baseModel.getCurrentPage()-1) / baseModel.getPagesPerPage()) * baseModel.getPagesPerPage() + 1;
		lastPage = startPage + baseModel.getPagesPerPage() - 1;
		
		if(lastPage > (baseModel.getTotalCount() / baseModel.getRowsPerPage())) {
			if ((baseModel.getTotalCount() % baseModel.getRowsPerPage()) == 0) {
				lastPage = (baseModel.getTotalCount() / baseModel.getRowsPerPage());
			} else {
				lastPage = (baseModel.getTotalCount() / baseModel.getRowsPerPage()) + 1;
			}
		}
		
		if (prevPage < 1) prevPage= 1;
		if (nextPage > lastPage) nextPage = lastPage;
		
		// create page html code
		pageHtml.append("<div id='paging'>");
		pageHtml.append("<a class='img'><img class='mousePoint' src='../images/board/btn/page_first.gif' onclick='fnGotoPage(1);' alt='첫 페이지로 이동' /></a>");
		pageHtml.append("<a class='img'><img class='mousePoint' src='../images/board/btn/page_prev.gif' onclick='fnGotoPage(" + prevPage + ");' alt='이전 페이지로 이동' /></a>");
			
		for(int i = startPage ; i <= lastPage ; i++) {
			if(i == baseModel.getCurrentPage()){
				pageHtml.append("<strong class='first now'>" + i + "</strong>");
			} else {
				pageHtml.append("<strong class='mousePoint'><a href='javascript:fnGotoPage(" + i + ");'>" + i + "</a></strong>");
			}
			
		}
		
		pageHtml.append("<a class='img'><img class='mousePoint' src='../images/board/btn/page_next.gif' onclick='fnGotoPage(" + nextPage + ");' alt='다음 페이지로 이동' /></a>");
		pageHtml.append("<a class='img'><img class='mousePoint' src='../images/board/btn/page_last.gif' onclick='fnGotoPage(" + lastPage + ");' alt='마지막 페이지로 이동' /></a>");
		pageHtml.append("</div>");
		
		return pageHtml.toString();
	}
	
	@RequestMapping("/fileDownload.do")
	public void fileDownload(@ModelAttribute("BoardAttachFileModel") BoardAttachFileInfo boardAttachFileModel, HttpServletRequest request, HttpServletResponse response) {
		String saveFileName = boardAttachFileModel.getSaveFilename();
		String realFileName = boardAttachFileModel.getRealFilename();
		FileDownloadUtility.doFileDownload(request, response, FileUploadUtility.UPLOAD_TYP_BOARD, saveFileName, realFileName);
	}
	
	@RequestMapping("/fileDownloadImage.do")
	public void fileDownloadImage(@ModelAttribute("BoardAttachFileModel") BoardAttachFileInfo boardAttachFileModel, HttpServletRequest request, HttpServletResponse response) {
		String saveFileName = boardAttachFileModel.getSaveFilename();
		String realFileName = boardAttachFileModel.getRealFilename();
		FileDownloadUtility.doFileDownload(request, response, FileUploadUtility.UPLOAD_TYP_BOARD_IMAGE, saveFileName, realFileName);
	}
	
	/**
	 * Request게시물일련번호를 돌려주는 메소드 
	 * @param request
	 * @return
	 */
	private long getBrdSeq(HttpServletRequest request) {
		String strBrdSeq = request.getParameter("srchBrdSeq");
		if (Utility.isEmpty(strBrdSeq)) {
			strBrdSeq = request.getParameter("brdSeq");
			if (Utility.isEmpty(strBrdSeq)) {
				strBrdSeq = "0";
			}
		}
		
		return Long.parseLong(strBrdSeq);
	}
	
}
