package com.blueone.board.service;

import java.util.List;

import com.blueone.board.domain.BoardTypInfo;



public interface IBoardTypService {
	
	// 게시판 리스트 조회
	List<BoardTypInfo> getBoardTypList();
	
	// 게시판 상세조회
	BoardTypInfo getBoardTyp(int brdTyp);
	
	// 게시판 추가/수정/삭제 처리
	boolean insertBOM_BOARD_MNG_TB(BoardTypInfo boardTypModel);
	boolean updateBOM_BOARD_MNG_TB(BoardTypInfo boardTypModel);
	
}
