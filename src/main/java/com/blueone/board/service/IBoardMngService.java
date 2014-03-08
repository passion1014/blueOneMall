package com.blueone.board.service;

import java.util.List;

import com.blueone.board.domain.BoardAttachFileModel;
import com.blueone.board.domain.BoardCommentModel;
import com.blueone.board.domain.BoardModel;
import com.blueone.board.domain.BoardSrchModel;
import com.blueone.board.domain.BoardStatModel;

public interface IBoardMngService {
	
	// 게시물 리스트 조회
	List<BoardModel> getBoardList(BoardSrchModel boardSrchModel);
	int getBoardTotalCount(BoardSrchModel boardSrchModel);
	BoardStatModel getTodayTotalCount(BoardSrchModel boardSrchModel);
	List<BoardModel> getNoticeList(BoardSrchModel boardSrchModel);
	
	// 게시물 상세조회
	BoardModel getBoard(long brdSeq);
	List<BoardAttachFileModel> selectTBL010103(long brdSeq);
	List<BoardCommentModel> selectTBL010104(long brdSeq);
	
	// 게시물 추가/수정/삭제 처리
	boolean insertBoard(BoardModel boardModel);
	Object insertTBL010102(BoardModel boardModel);
	boolean insertTBL010104(BoardCommentModel boardCommentModel);
	boolean insertTBL010103(BoardAttachFileModel boardAttachFileModel);
	boolean updateTBL010102(BoardModel boardModel);
	boolean updateTBL010102Del(BoardModel boardModel);
	boolean updateTBL010102Del(BoardSrchModel boardSrchModel);
	boolean updateTBL010102BrdTyp(BoardSrchModel boardSrchModel);
	
	
}
