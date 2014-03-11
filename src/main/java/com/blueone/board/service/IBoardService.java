package com.blueone.board.service;

import java.util.List;

import com.blueone.board.domain.BoardAttachFileInfo;
import com.blueone.board.domain.BoardCommentInfo;
import com.blueone.board.domain.BoardInfo;
import com.blueone.board.domain.BoardSrchInfo;

public interface IBoardService {
	
	// 게시판관리
	
	// 특정게시판의 게시물리스트
	List<BoardInfo> getBrdTypBoardList(BoardSrchInfo boardSrchModel);
	int getBrdTypTotalCount(BoardSrchInfo boardSrchModel);
	List<BoardInfo> getBrdTypNoticeList(BoardSrchInfo boardSrchModel);
	
	// 게시물조회
	BoardInfo selectBOM_BOARD_TB(long srchBrdSeq);
	List<BoardAttachFileInfo> selectBOM_ATTACHFILE_TB(long srchBrdSeq);
	List<BoardCommentInfo> selectBOM_BOARD_CMT_TB(long srchBrdSeq);
	
	// 게시물추가/수정
	boolean insertBoard(BoardInfo boardModel);
	boolean updateBoard(BoardInfo boardModel);
	boolean insertBOM_BOARD_CMT_TB(BoardCommentInfo boardCommentModel);
	boolean updateBOM_BOARD_CMT_TB(BoardCommentInfo boardCommentModel);
	
	// 게시물 업데이트
	void updateBOM_BOARD_TBHit(long srchBrdSeq);
	void updateBOM_BOARD_TBReplyCnt(long srchBrdSeq);
	void updateBOM_BOARD_TBCommCnt(long srchBrdSeq);
	void updateBOM_BOARD_TBDel(long srchBrdSeq, String userId); 
	void updateBOM_BOARD_CMT_TBDel(long srchBrdSeq, int commNo, String userId);
	
	
	
	// 게시판의 최근 게시물
	List<BoardInfo> getBoardLastList(int[] brdTyps, int size);

}
