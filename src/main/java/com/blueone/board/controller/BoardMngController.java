package com.blueone.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;






















import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.blueone.board.domain.BoardAttachFileInfo;
import com.blueone.board.domain.BoardCommentInfo;
import com.blueone.board.domain.BoardInfo;
import com.blueone.board.domain.BoardSrchInfo;
import com.blueone.board.domain.BoardStatInfo;
import com.blueone.board.service.BoardMngService;
import com.blueone.board.service.BoardService;
import com.blueone.board.service.BoardTypService;
import com.blueone.board.service.IBoardMngService;
import com.blueone.board.service.IBoardService;
import com.blueone.board.service.IBoardTypService;
import com.blueone.common.domain.CodeInfo;
import com.blueone.common.service.CodeService;
import com.blueone.common.service.ICodeService;
import com.blueone.common.util.FileDownloadUtility;
import com.blueone.common.util.FileUploadUtility;
import com.blueone.common.util.Utility;
import com.blueone.login.domain.LoginSessionModel;

@Controller
@RequestMapping("/boardMng")
public class BoardMngController {
	// DI
//	private ApplicationContext context = new ClassPathXmlApplicationContext("/config/applicationContext.xml");
//	private BoardTypService boardTypService = (BoardTypService) context.getBean("boardTypService");
//	private BoardMngService boardMngService = (BoardMngService) context.getBean("boardMngService");
//	private CodeService codeService = (CodeService) context.getBean("codeService");
//	private BoardService boardService = (BoardService) context.getBean("boardService");

	@Autowired private IBoardTypService boardTypService;
	@Autowired private IBoardMngService boardMngService;
	@Autowired private ICodeService codeService;
	@Autowired private IBoardService boardService;

	
	@RequestMapping("/add.do")
	public ModelAndView add(@ModelAttribute("BoardSrchModel") BoardSrchInfo boardSrchModel, HttpServletRequest request, HttpSession session) {
		LoginSessionModel userInfo = (LoginSessionModel) session.getAttribute("userInfo");
		
		BoardInfo boardModal = new BoardInfo();
		boardModal.setRootSeq(0);
		boardModal.setRefSeq(1);
		boardModal.setDepth(0);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/board/mngWrite");
		mav.addObject("userNm", userInfo.getUserNm());
		mav.addObject("brdTypInfo", boardTypService.getBoardTyp(getBrdTyp(request)));
		mav.addObject("board", boardModal);
		mav.addObject("srchInfo", boardSrchModel);
		return mav;
	}
	
	@RequestMapping(value="/add.do", method = RequestMethod.POST)
	public ModelAndView add(@ModelAttribute("BoardModel") BoardInfo boardModel, @ModelAttribute("BoardSrchModel") BoardSrchInfo boardSrchModel, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		LoginSessionModel userInfo = (LoginSessionModel) session.getAttribute("userInfo");
		boardModel.setInsUser(userInfo.getUserId());
		boardModel.setUpdUser(userInfo.getUserId());
		
		// 게시판유형 체크
		if (boardModel.getBrdTyp() < 1) {
			boardModel.setBrdTyp(getBrdTyp(request));
		}
		
		if(boardService.insertBoard(boardModel)) {
			// 성공
			mav = list(boardSrchModel, request, response);
			mav.addObject("errCode", 3);
			return mav;
		} else {
			// 실패
			mav = add(boardSrchModel, request, session);
			mav.addObject("errCode", 2);
			return mav;
		}
	}
	
	@RequestMapping("/edit.do")
	public ModelAndView edit(@ModelAttribute("BoardModel") BoardInfo boardModel, @ModelAttribute("BoardSrchModel") BoardSrchInfo boardSrchModel, HttpServletRequest request, HttpSession session){
		long brdSeq = getBrdSeq(request);		
		BoardInfo board = boardService.selectBOM_BOARD_TB(brdSeq);
		List<BoardAttachFileInfo> attaFileList = boardService.selectBOM_ATTACHFILE_TB(brdSeq);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("brdTypInfo", boardTypService.getBoardTyp(board.getBrdTyp()));
		mav.addObject("board", board);
		mav.addObject("attaFileList", attaFileList);
		mav.addObject("srchInfo", boardSrchModel);
		mav.setViewName("/admin/board/mngEdit");
		return mav;
	}
	
	@RequestMapping(value="/edit.do", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("BoardModel") BoardInfo boardModel, @ModelAttribute("BoardSrchModel") BoardSrchInfo boardSrchModel, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		LoginSessionModel userInfo = (LoginSessionModel) session.getAttribute("userInfo");
		boardModel.setUpdUser(userInfo.getUserId());
		
		if(boardService.updateBoard(boardModel)) {
			// 성공
			mav = view(boardSrchModel, request, session);
			mav.addObject("errCode", 3);
			return mav;
		} else {
			// 실패
			mav = edit(boardModel, boardSrchModel, request, session);
			mav.addObject("errCode", 2);
			return mav;
		}
	}
	
	@RequestMapping(value="/editComment.do", method = RequestMethod.POST)
	public ModelAndView editComment(@ModelAttribute("BoardCommentModel") BoardCommentInfo boardCommentModel, @ModelAttribute("BoardSrchModel") BoardSrchInfo boardSrchModel, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		LoginSessionModel userInfo = (LoginSessionModel) session.getAttribute("userInfo");
		boardCommentModel.setUpdUser(userInfo.getUserId());
		
		if(boardService.updateBOM_BOARD_CMT_TB(boardCommentModel)) {
			// 성공
			mav = view(boardSrchModel, request, session);
			mav.addObject("errCode", 3);
			return mav;
		} else {
			// 실패
			mav = view(boardSrchModel, request, session);
			mav.addObject("errCode", 2);
			return mav;
		}
	}
	
	@RequestMapping("/view.do")
	public ModelAndView view(@ModelAttribute("BoardSrchModel") BoardSrchInfo boardSrchModel, HttpServletRequest request, HttpSession session){
		long brdSeq = getBrdSeq(request);		
		BoardInfo board = boardService.selectBOM_BOARD_TB(brdSeq);
		List<BoardAttachFileInfo> attaFileList = boardService.selectBOM_ATTACHFILE_TB(brdSeq);
		List<BoardCommentInfo> commentList = boardService.selectBOM_BOARD_CMT_TB(brdSeq);
		
		// 조회수 업데이트
		boardService.updateBOM_BOARD_TBHit(brdSeq);
		
		BoardCommentInfo boardCommentModel = new BoardCommentInfo();
		boardCommentModel.setCommRootNo(0);
		boardCommentModel.setCommRefNo(1);
		boardCommentModel.setCommDepth(0);
		
		// 조회수 업데이트
		boardService.updateBOM_BOARD_TBHit(brdSeq);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("board", board);
		mav.addObject("srchInfo", boardSrchModel);
		mav.addObject("attaFileList", attaFileList);
		mav.addObject("comment", boardCommentModel);
		mav.addObject("commentList", commentList);
		mav.addObject("brdTypInfo", boardTypService.getBoardTyp(board.getBrdTyp()));
		mav.setViewName("/admin/board/mngView");
		return mav;
	}
	
	@RequestMapping("/reply.do")
	public ModelAndView reply(@ModelAttribute("BoardSrchModel") BoardSrchInfo boardSrchModel, HttpServletRequest request, HttpSession session) {
		LoginSessionModel userInfo = (LoginSessionModel) session.getAttribute("userInfo");
		long brdSeq = getBrdSeq(request);
		BoardInfo boardModel = boardService.selectBOM_BOARD_TB(brdSeq); // get selected article model
		boardModel.setRefSeq(boardModel.getRefSeq() + 1);
		boardModel.setDepth(boardModel.getDepth() + 1);
		boardModel.setTitle("[답변] " + boardModel.getTitle());
		
		boardModel.setCont("");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/board/mngWrite");
		mav.addObject("userNm", userInfo.getUserNm());
		mav.addObject("brdTypInfo", boardTypService.getBoardTyp(boardModel.getBrdTyp()));
		mav.addObject("board", boardModel);
		mav.addObject("srchInfo", boardSrchModel);
		return mav;
	}
	
	@RequestMapping(value="/addComment.do", method = RequestMethod.POST)
	public ModelAndView addComment(@ModelAttribute("BoardCommentModel") BoardCommentInfo boardCommentModel, @ModelAttribute("BoardSrchModel") BoardSrchInfo boardSrchModel, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		LoginSessionModel userInfo = (LoginSessionModel) session.getAttribute("userInfo");
		boardCommentModel.setInsUser(userInfo.getUserId());
		boardCommentModel.setUpdUser(userInfo.getUserId());
		
		// 코멘트수 업데이트
		boardService.updateBOM_BOARD_TBCommCnt(boardCommentModel.getBrdSeq());
		
		if(boardService.insertBOM_BOARD_CMT_TB(boardCommentModel)){
			// 성공
			mav = view(boardSrchModel, request, session);
			mav.addObject("errCode", 3);
			return mav;
		} else {
			// 실패
			mav = view(boardSrchModel, request, session);
			mav.addObject("errCode", 2);
			return mav;
		}
	}
	
	@RequestMapping(value="/delete.do", method = RequestMethod.POST)
	public ModelAndView delete(@ModelAttribute("BoardModel") BoardInfo boardModel, @ModelAttribute("BoardSrchModel") BoardSrchInfo boardSrchModel, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		LoginSessionModel userInfo = (LoginSessionModel) session.getAttribute("userInfo");
		boardSrchModel.setUserId(userInfo.getUserId());
		long[] check = {boardModel.getBrdSeq()};
		boardSrchModel.setCheck(check);
		
		if(boardMngService.updateBOM_BOARD_TBDel(boardSrchModel)){
			// 성공
			mav = list(boardSrchModel, request, response);
			mav.addObject("errCode", 3);
			return mav;
		} else {
			// 실패
			mav = view(boardSrchModel, request, session);
			mav.addObject("errCode", 2);
			return mav;
		}
	}
	
	@RequestMapping("/deleteFromList.do")
	public ModelAndView deleteFromList(@ModelAttribute("BoardSrchModel") BoardSrchInfo boardSrchModel, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		LoginSessionModel userInfo = (LoginSessionModel) session.getAttribute("userInfo");
		boardSrchModel.setUserId(userInfo.getUserId());
		if (boardSrchModel.getCheck() != null) {
			boardMngService.updateBOM_BOARD_TBDel(boardSrchModel);
		}
		
		// 리스트 조회
		return list(boardSrchModel, request, response);
	}
	
	@RequestMapping("/moveFromList.do")
	public ModelAndView moveFromList(@ModelAttribute("BoardSrchModel") BoardSrchInfo boardSrchModel, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		LoginSessionModel userInfo = (LoginSessionModel) session.getAttribute("userInfo");
		boardSrchModel.setUserId(userInfo.getUserId());
		if (boardSrchModel.getCheck() != null) {
			boardMngService.updateBOM_BOARD_TBBrdTyp(boardSrchModel);
		}
		
		// 리스트 조회
		return list(boardSrchModel, request, response);
	}
	
	@RequestMapping("/deleteComment.do")
	public ModelAndView deleteComment(@ModelAttribute("BoardCommentModel") BoardCommentInfo boardCommentModel, @ModelAttribute("BoardSrchModel") BoardSrchInfo boardSrchModel, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		LoginSessionModel userInfo = (LoginSessionModel) session.getAttribute("userInfo");
		
		// 삭제처리
		boardService.updateBOM_BOARD_CMT_TBDel(boardCommentModel.getBrdSeq(), boardCommentModel.getCommNo(), userInfo.getUserId());
		
		ModelAndView mav = new ModelAndView();
		mav = view(boardSrchModel, request, session);
		return mav;
	}
	
	@RequestMapping("/list.do")
	public ModelAndView list(@ModelAttribute("BoardSrchModel") BoardSrchInfo boardSrchModel, HttpServletRequest request, HttpServletResponse response) {
		// 한페이지의 게시물
		boardSrchModel.setRowsPerPage(15);
		
		// 공지사항 조회
		long[] noticeBrdSeq = {};
		List<BoardInfo> noticeList = boardMngService.getNoticeList(boardSrchModel);
		if (noticeList != null && noticeList.size() > 0) {
			noticeBrdSeq = new long[noticeList.size()];
			boardSrchModel.setRowsPerPage(15 - noticeList.size());
			for (int i = 0; i < noticeList.size(); i++) {
				noticeBrdSeq[i] = noticeList.get(i).getBrdSeq();
			}
		}
		boardSrchModel.setNoticeBrdSeq(noticeBrdSeq);
		
		// 게시판리스트 조회
		List<BoardInfo> boardList = boardMngService.getBoardList(boardSrchModel);
		boardSrchModel.setTotalCount(boardMngService.getBoardTotalCount(boardSrchModel));
		List<CodeInfo> boardTypList = codeService.getBoardTypList();
		
		// 신규게시물정보 조회
		BoardStatInfo boardStat = boardMngService.getTodayTotalCount(boardSrchModel);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("srchInfo", boardSrchModel);
		mav.addObject("noticeList", noticeList);
		mav.addObject("boardList", boardList);
		mav.addObject("boardTypList", boardTypList);
		mav.addObject("boardStat", boardStat);
//		mav.addObject("pageHtml", KLACUtility.getAdminPageHtml(boardSrchModel));
		mav.addObject("srchBrdTypNm", codeService.getCodeName(boardTypList, Integer.toString(boardSrchModel.getSrchBrdTyp())));
		mav.setViewName("/admin/board/mngList");
		
		return mav;
	}
	
	@RequestMapping(value="/fileDownload.do", method = RequestMethod.POST)
	public void fileDownload(@ModelAttribute("BoardAttachFileModel") BoardAttachFileInfo boardAttachFileModel, HttpServletRequest request, HttpServletResponse response) {
		String saveFileName = boardAttachFileModel.getSaveFilename();
		String realFileName = boardAttachFileModel.getRealFilename();
		FileDownloadUtility.doFileDownload(request, response, FileUploadUtility.UPLOAD_TYP_BOARD, saveFileName, realFileName);
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
	
	/**
	 * Request의 게시판일련번호를 돌려주는 메소드 
	 * @param request
	 * @return
	 */
	private int getBrdTyp(HttpServletRequest request) {
		String strBrdTyp = request.getParameter("srchBrdTyp");
		if (Utility.isEmpty(strBrdTyp)) {
			strBrdTyp = request.getParameter("brdSeq");
			if (Utility.isEmpty(strBrdTyp)) {
				strBrdTyp = "0";
			}
		}
		
		return Integer.parseInt(strBrdTyp);
	}
}//End class
