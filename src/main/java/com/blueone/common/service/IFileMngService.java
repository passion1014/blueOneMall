package com.blueone.common.service;

import javax.servlet.ServletContext;

import com.blueone.common.domain.AttachFileInfo;

public interface IFileMngService {
	AttachFileInfo fileUpload(AttachFileInfo attachFileInfo);
}
