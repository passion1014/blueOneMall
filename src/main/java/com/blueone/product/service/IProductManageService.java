package com.blueone.product.service;

import java.util.List;

import com.blueone.category.domain.CategoryInfo;
import com.blueone.common.domain.AttachFileInfo;
import com.blueone.common.domain.ResultInfo;
import com.blueone.product.domain.ProductInfo;
import com.blueone.product.domain.SearchProdInfo;

public interface IProductManageService {

	public List<ProductInfo> getProductInfList(SearchProdInfo productInfo);
	public List<ProductInfo> getProductSearchList(SearchProdInfo searchProdInfo);
	public ProductInfo registProductInfo(ProductInfo productInfo);
	public ProductInfo editProductInfo(ProductInfo productInfo);
	public ProductInfo removeProductInfDetail(ProductInfo productInfo);
	public ProductInfo registProductDtlInfo(ProductInfo productInfo);
	
	public ProductInfo getProductInfDetail(ProductInfo productInfo);
	

	
	
	
	//사용자 페이지
	public List<ProductInfo> shopProductInfList(ProductInfo productInfo);
	public int deleteProductInf(ProductInfo productInfo);
	public int manageProductInf(ProductInfo productInfo);
	public List<ProductInfo> getProductOptionInfDetail(ProductInfo productInfo);
	
}
