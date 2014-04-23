package com.blueone.shop.service;

import java.util.List;

import com.blueone.admin.domain.AdImgInfo;
import com.blueone.product.domain.ProductInfo;



public interface IShopService {
	
	public AdImgInfo getAdImg(AdImgInfo adImgInfo);//메인화면에 배너이미지 출력
	public List<ProductInfo> getImgList(ProductInfo productInfo);//메인화면에 상품이미지 출력


}
