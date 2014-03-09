package com.blueone.common.service;

import java.util.List;

import com.blueone.common.domain.CodeInfo;






public interface ICodeService {
	// 코드리스트
	List<CodeInfo> getCodeList(String codeKnd1, String codeKnd2);
	
	// 단체리스트
	List<CodeInfo> getTBL020110List();
	
	// 게시판리스트
	List<CodeInfo> getBoardTypList();
	
	// 교육관련 주제,대상자,강사
	List<CodeInfo> getEduSubList();
	List<CodeInfo> getEduSubList(String orderSequence);
	List<CodeInfo> getEduSubAllList();
	List<CodeInfo> getEduTarList();
	List<CodeInfo> getEduInsList();
	List<CodeInfo> getEduPgmList(int subSeq);
	
	List<CodeInfo> getMonthList();
	List<CodeInfo> getMailSkinList();

}
