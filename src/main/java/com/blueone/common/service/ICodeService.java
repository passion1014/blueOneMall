package com.blueone.common.service;

import java.util.List;

import com.blueone.common.domain.CodeModel;






public interface ICodeService {
	// 코드리스트
	List<CodeModel> getCodeList(String codeKnd1, String codeKnd2);
	
	// 단체리스트
	List<CodeModel> getTBL020110List();
	
	// 게시판리스트
	List<CodeModel> getBoardTypList();
	
	// 교육관련 주제,대상자,강사
	List<CodeModel> getEduSubList();
	List<CodeModel> getEduSubList(String orderSequence);
	List<CodeModel> getEduSubAllList();
	List<CodeModel> getEduTarList();
	List<CodeModel> getEduInsList();
	List<CodeModel> getEduPgmList(int subSeq);
	
	List<CodeModel> getMonthList();
	List<CodeModel> getMailSkinList();

}
