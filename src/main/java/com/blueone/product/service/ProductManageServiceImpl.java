package com.blueone.product.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueone.category.domain.CategoryInfo;
import com.blueone.common.domain.ResultInfo;
import com.blueone.product.domain.ProductInfo;
import com.blueone.product.domain.SearchProdInfo;

@Service
public class ProductManageServiceImpl implements IProductManageService {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	
	//상품목록 불러옴
	@Override
	public List<ProductInfo> getProductInfList(ProductInfo productInfo) {
		
		List<ProductInfo> productList = new ArrayList<ProductInfo>();
		
//		String srchSqlTp = "";
//		if (StringUtils.isNotEmpty(searchProdInfo.getProdNm())) {
//			
//		}
		
		// -----------------------------------------------
		// DB Insert 수행
		// -----------------------------------------------
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			
			// DB 수행
			productList = sqlSession.selectList("product.selectListBomProductTb0001", productInfo);
			productList = sqlSession.selectList("product.selectListBomProductDtlTb0001", productInfo);
			
		} finally {
			sqlSession.close();
		}
		
		return productList;
	}

	@Override
	public List<ProductInfo> getProductSearchList(SearchProdInfo searchProdInfo) {
		
		List<ProductInfo> productList = new ArrayList<ProductInfo>();
		
//		String srchSqlTp = "";
//		if (StringUtils.isNotEmpty(searchProdInfo.getProdNm())) {
//			
//		}
		
		// -----------------------------------------------
		// DB Insert 수행
		// -----------------------------------------------
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			
			// DB 수행
			productList = sqlSession.selectList("product.selectListBomProductTb0001", searchProdInfo);
		} finally {
			sqlSession.close();
		}
		
		return productList;
	}

	@Override
	public ProductInfo getProductInfDetail(ProductInfo productInfo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ProductInfo registProductInfo(ProductInfo productInfo) {
		ResultInfo rstInfo = new ResultInfo();
		
		// -----------------------------------------------
		// 체크로직
		// -----------------------------------------------
		String checkRst = checkProductInfo(productInfo);
		if (!"1".equals(checkRst)) return productInfo;
		
		// -----------------------------------------------
		// DB Insert 수행
		// -----------------------------------------------
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			setDefaultDate(productInfo);
			
			int rst = sqlSession.insert("product.insertBomProductTb0001", productInfo);
			
		} finally {
			sqlSession.close();
		}
		
		return productInfo;
	}

	@Override
	public ProductInfo registProductDtlInfo(ProductInfo productInfo) {
		ResultInfo rstInfo = new ResultInfo();
		
		// -----------------------------------------------
		// 체크로직
		// -----------------------------------------------
		String checkRst = checkProductInfo(productInfo);
		if (!"1".equals(checkRst)) return productInfo;
		
		// -----------------------------------------------
		// DB Insert 수행
		// -----------------------------------------------
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			setDefaultDate(productInfo);
			
			int rst = sqlSession.insert("product.insertBomProductDtlTb0001", productInfo);
			
		} finally {
			sqlSession.close();
		}
		
		return productInfo;
	}


	@Override
	public ProductInfo editProductInfo(ProductInfo productInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductInfo removeProductInfDetail(ProductInfo productInfo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String checkProductInfo(ProductInfo productInfo) {
		return "1";
	}

	private void setDefaultDate(ProductInfo productInfo) {
		
		// fromDate "0001-01-01"
		if (StringUtils.isEmpty(productInfo.getFromDate())) {
			productInfo.setFromDate("0001-01-01");
		}
		
		// toDate "9999-12-31"
		if (StringUtils.isEmpty(productInfo.getToDate())) {
			productInfo.setToDate("9999-12-31");
		}
	}
}
