package com.blueone.common.service;

import java.util.HashMap;
import java.util.List;
import com.blueone.common.domain.ZipModel;

public interface IZipService {
	// 우편리스트
	List<ZipModel> getZipList(ZipModel zipModel);
	
	// 우편리스트(법률구조공단 우편번호참보)
	List<ZipModel> getGiBunList(ZipModel zipModel);
	List<ZipModel> getRoadNmList(ZipModel zipModel);
	HashMap<String, Object> makeRoadAddr(String oldAddr);
	
}
