package com.blueone.board.service;

import java.util.List;

import com.blueone.board.domain.BoardAttachFileInfo;
import com.blueone.board.domain.BoardCommentInfo;
import com.blueone.board.domain.BoardInfo;
import com.blueone.board.domain.BoardSrchInfo;
import com.blueone.board.domain.BoardStatInfo;

public interface IBoardMngService {
	
	// 게시물 리스트 조회
	List<BoardInfo> getBoardList(BoardSrchInfo boardSrchModel);
	int getBoardTotalCount(BoardSrchInfo boardSrchModel);
	BoardStatInfo getTodayTotalCount(BoardSrchInfo boardSrchModel);
	List<BoardInfo> getNoticeList(BoardSrchInfo boardSrchModel);
	
	// 게시물 상세조회
	BoardInfo getBoard(long brdSeq);
	List<BoardAttachFileInfo> selectBOM_ATTACHFILE_TB(long brdSeq);
	List<BoardCommentInfo> selectBOM_BOARD_CMT_TB(long brdSeq);
	
	// 게시물 추가/수정/삭제 처리
	boolean insertBoard(BoardInfo boardModel);
	Object insertBOM_BOARD_TB(BoardInfo boardModel);
	boolean insertBOM_BOARD_CMT_TB(BoardCommentInfo boardCommentModel);
	boolean insertBOM_ATTACHFILE_TB(BoardAttachFileInfo boardAttachFileModel);
	boolean updateBOM_BOARD_TB(BoardInfo boardModel);
	boolean updateBOM_BOARD_TBDel(BoardInfo boardModel);
	boolean updateBOM_BOARD_TBDel(BoardSrchInfo boardSrchModel);
	boolean updateBOM_BOARD_TBBrdTyp(BoardSrchInfo boardSrchModel);
}
