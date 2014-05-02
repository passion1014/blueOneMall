package com.blueone.admin.service;

import java.util.List;

import com.blueone.admin.domain.AdImgInfo;


public interface IAdImgService {

	public int insertAdImg(AdImgInfo adImgInfo);//메인화면의 메인 이미지 삽입

	public int insertAdImg2(AdImgInfo adImgInfo);
	
	public AdImgInfo getAdImgDtl(AdImgInfo adImgInfo);

	public int updateAdImg(AdImgInfo adImgInfo);//메인화면 이미지업데이트
}
