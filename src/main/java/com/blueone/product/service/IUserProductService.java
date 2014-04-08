package com.blueone.product.service;

import java.util.List;

import com.blueone.product.domain.ProductInfo;

public interface IUserProductService {

	//사용자 페이지
		public List<ProductInfo> shopProductInfList(ProductInfo productInfo);
		public List<Object> shopProductByPriceList(ProductInfo productInfo);
}
