package com.blueone.shop.service;

import java.util.List;

import com.blueone.admin.domain.AdImgInfo;
import com.blueone.product.domain.ProductInfo;



public interface IShopService {
	
	public AdImgInfo getAdImg(AdImgInfo adImgInfo);//메인화면에 배너이미지 출력
	public List<ProductInfo> getProdListForMain(ProductInfo productInfo);//메인화면에 상품이미지 출력

	public int updateAdImg(AdImgInfo adImgInfo);//메인화면 이미지업데이트


//	public AdImgInfo getAdImg2(AdImgInfo adImgInfo);//메인화면의 슬라이드이미지출력

}
