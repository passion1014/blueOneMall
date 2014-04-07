package com.blueone.product.service;

import java.util.List;

import com.blueone.common.domain.ResultInfo;
import com.blueone.product.domain.ProductInfo;
import com.blueone.product.domain.SearchProdInfo;

public interface IProductManageService {

	public List<ProductInfo> getProductList(SearchProdInfo searchProdInfo);
	public ProductInfo getProductInfDetail(ProductInfo productInfo);
	public ProductInfo registProductInfo(ProductInfo productInfo);
	public ProductInfo editProductInfo(ProductInfo productInfo);
	public ProductInfo removeProductInfDetail(ProductInfo productInfo);
	public ProductInfo registProductDtlInfo(ProductInfo productInfo);

}
