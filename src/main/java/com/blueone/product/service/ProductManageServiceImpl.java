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

	@Override
	public List<ProductInfo> getProductList(SearchProdInfo searchProdInfo) {
		
		List<ProductInfo> productList = new ArrayList<ProductInfo>();
		
		String srchSqlTp = "";
		if (StringUtils.isNotEmpty(searchProdInfo.getProdNm())) {
			
		}
		
		// -----------------------------------------------
		// DB Insert 수행
		// -----------------------------------------------
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			
			// DB 수행
//			int rst = sqlSession.selectList(, arg1);
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
	public ResultInfo registProductInfo(ProductInfo productInfo) {
		ResultInfo rstInfo = new ResultInfo();
		
		// -----------------------------------------------
		// 체크로직
		// -----------------------------------------------
		rstInfo = checkProductInfo(productInfo);
		if (!rstInfo.isOk()) return rstInfo;
		
		// -----------------------------------------------
		// DB Insert 수행
		// -----------------------------------------------
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			setDefaultDate(productInfo);
			
			// DB 수행
			int rst = sqlSession.insert("product.insertBomProductTb0001", productInfo);
		} finally {
			sqlSession.close();
		}
		
		return null;
	}

	@Override
	public ResultInfo editProductInfo(ProductInfo productInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultInfo removeProductInfDetail(ProductInfo productInfo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private ResultInfo checkProductInfo(ProductInfo productInfo) {
		ResultInfo rstInfo = new ResultInfo();
		rstInfo.setRstCd("1");
		
		return rstInfo;
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
