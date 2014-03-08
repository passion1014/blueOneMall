package com.blueone.board.service;

import java.util.List;

import com.blueone.board.domain.BoardAttachFileModel;
import com.blueone.board.domain.BoardCommentModel;
import com.blueone.board.domain.BoardInfo;
import com.blueone.board.domain.BoardSrchModel;

public interface IBoardService {
	
	// 게시판관리
	
	// 특정게시판의 게시물리스트
	List<BoardInfo> getBrdTypBoardList(BoardSrchModel boardSrchModel);
	int getBrdTypTotalCount(BoardSrchModel boardSrchModel);
	List<BoardInfo> getBrdTypNoticeList(BoardSrchModel boardSrchModel);
	
	// 게시물조회
	BoardInfo selectTBL010102(long srchBrdSeq);
	List<BoardAttachFileModel> selectTBL010103(long srchBrdSeq);
	List<BoardCommentModel> selectTBL010104(long srchBrdSeq);
	
	// 게시물추가/수정
	boolean insertBoard(BoardInfo boardModel);
	boolean updateBoard(BoardInfo boardModel);
	boolean insertTBL010104(BoardCommentModel boardCommentModel);
	boolean updateTBL010104(BoardCommentModel boardCommentModel);
	
	// 게시물 업데이트
	void updateTBL010102Hit(long srchBrdSeq);
	void updateTBL010102ReplyCnt(long srchBrdSeq);
	void updateTBL010102CommCnt(long srchBrdSeq);
	void updateTBL010102Del(long srchBrdSeq, String userId); 
	void updateTBL010104Del(long srchBrdSeq, int commNo, String userId);
	
	
	
	// 게시판의 최근 게시물
	List<BoardInfo> getBoardLastList(int[] brdTyps, int size);

}
