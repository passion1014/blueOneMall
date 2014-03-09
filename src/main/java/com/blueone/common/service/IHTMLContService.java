package com.blueone.common.service;

import java.util.List;

import com.blueone.common.domain.HTMLContInfo;

public interface IHTMLContService {
	// HTML콘텐츠 리스트 조회
	List<HTMLContInfo> getHtmlContList(HTMLContInfo htmlContModel);
	
	// HTML콘텐츠 상세조회
	HTMLContInfo getHtmlCont(int htmlSeq);
	
	// 추가/수정/삭제 처리
	boolean insertTBL010909(HTMLContInfo htmlContModel);
	boolean updateTBL010909(HTMLContInfo htmlContModel);
	boolean deleteTBL010909(int htmlSeq);

}
