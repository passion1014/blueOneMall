package com.blueone.common.service;

import java.util.HashMap;
import java.util.List;
import com.blueone.common.domain.ZipInfo;

public interface IZipService {
	// 우편리스트
	List<ZipInfo> getZipList(ZipInfo zipModel);
	
	// 우편리스트(법률구조공단 우편번호참보)
	List<ZipInfo> getGiBunList(ZipInfo zipModel);
	List<ZipInfo> getRoadNmList(ZipInfo zipModel);
	HashMap<String, Object> makeRoadAddr(String oldAddr);
	
}
