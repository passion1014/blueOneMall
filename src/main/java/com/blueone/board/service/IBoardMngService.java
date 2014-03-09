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
	List<BoardAttachFileInfo> selectTBL010103(long brdSeq);
	List<BoardCommentInfo> selectTBL010104(long brdSeq);
	
	// 게시물 추가/수정/삭제 처리
	boolean insertBoard(BoardInfo boardModel);
	Object insertTBL010102(BoardInfo boardModel);
	boolean insertTBL010104(BoardCommentInfo boardCommentModel);
	boolean insertTBL010103(BoardAttachFileInfo boardAttachFileModel);
	boolean updateTBL010102(BoardInfo boardModel);
	boolean updateTBL010102Del(BoardInfo boardModel);
	boolean updateTBL010102Del(BoardSrchInfo boardSrchModel);
	boolean updateTBL010102BrdTyp(BoardSrchInfo boardSrchModel);
}
