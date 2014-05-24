package com.blueone.admin.service;

import java.util.List;

import com.blueone.admin.domain.SchWordInfo;

public interface ISearchWordService {

	public int updateSchWord(SchWordInfo schWordInfo);

	public SchWordInfo getSchWordDtl(SchWordInfo schWordInfo);

	public List<SchWordInfo> getSchWordDtlList(SchWordInfo schWordInfo);

}
