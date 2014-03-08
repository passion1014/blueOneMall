package com.blueone.common.service;

import java.util.List;

import com.blueone.common.domain.HTMLContModel;

public interface IHTMLContService {
	// HTML콘텐츠 리스트 조회
	List<HTMLContModel> getHtmlContList(HTMLContModel htmlContModel);
	
	// HTML콘텐츠 상세조회
	HTMLContModel getHtmlCont(int htmlSeq);
	
	// 추가/수정/삭제 처리
	boolean insertTBL010909(HTMLContModel htmlContModel);
	boolean updateTBL010909(HTMLContModel htmlContModel);
	boolean deleteTBL010909(int htmlSeq);

}
