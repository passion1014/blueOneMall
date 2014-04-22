package com.blueone.common.service;

import java.util.List;

import com.blueone.common.domain.AttachFileInfo;

public interface IAttachFileManageService {

	public AttachFileInfo registProductImgInfo(AttachFileInfo attFileInfo);
	public List<AttachFileInfo> getAttFileInfList(AttachFileInfo attFileInfo);
	public int deleteAttachFileInf(AttachFileInfo attFileInfo);
	public int updateAttachFileInf(AttachFileInfo attFileInfo);
	public AttachFileInfo getAttFileInfListImg(AttachFileInfo attFileInfo);

	
}
