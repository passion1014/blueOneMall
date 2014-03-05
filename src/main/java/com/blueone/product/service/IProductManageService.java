package com.blueone.product.service;

import java.util.List;

import com.blueone.common.domain.ResultInfo;
import com.blueone.product.domain.ProductInfo;
import com.blueone.product.domain.SearchProdInfo;

public interface IProductManageService {

	public List<ProductInfo> getProductList(SearchProdInfo searchProdInfo);
	public ProductInfo getProductInfDetail(ProductInfo productInfo);
	public ResultInfo registProductInfo(ProductInfo productInfo);
	public ResultInfo editProductInfo(ProductInfo productInfo);
	public ResultInfo removeProductInfDetail(ProductInfo productInfo);

}
