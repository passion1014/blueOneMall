package com.blueone.admin.service;

import java.util.List;

import com.blueone.admin.domain.AdImgInfo;


public interface IAdImgService {

	public int insertAdImg(AdImgInfo adImgInfo);//메인화면의 메인 이미지 삽입

	public int insertAdImg2(AdImgInfo adImgInfo);
	
	public List<AdImgInfo> getAdImg(AdImgInfo adImgInfo);
	
	public AdImgInfo getAdImgDtl(AdImgInfo adImgInfo);
}
