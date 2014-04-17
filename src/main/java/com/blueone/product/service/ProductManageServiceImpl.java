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
import com.blueone.common.domain.AttachFileInfo;
import com.blueone.common.domain.ResultInfo;
import com.blueone.common.service.IAttachFileManageService;
import com.blueone.product.domain.ProductInfo;
import com.blueone.product.domain.SearchProdInfo;

@Service
public class ProductManageServiceImpl implements IProductManageService {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired IAttachFileManageService attFileManageService;
	
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	 public static List stringsToList(String[] strings) {
	     List list = new ArrayList();
	     if(strings != null) {
	      if(strings.length > 0) {
	       for(int i=0; i<strings.length; i++) {
	        list.add(strings[i]);
	       }
	      }
	     }
	     return list;
	    } 
	
	//상품목록 불러옴
	@Override
	public List<ProductInfo> getProductInfList(SearchProdInfo srchProdInfo) {
		
		List<ProductInfo> prodBaseList = new ArrayList<ProductInfo>();
		
		// -----------------------------------------------
		// DB Insert 수행
		// -----------------------------------------------
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			// DB 수행
			//AttachFileInfo att = new AttachFileInfo();
			//att.setAttCdKey(srchProdInfo.getPrdNm());
			
			//List<AttachFileInfo> attList= attFileManageService.getAttFileInfList(att);
			//
			prodBaseList = sqlSession.selectList("product.selectListBomProductTb0001", srchProdInfo);
		} finally {
			sqlSession.close();
		}
		
		return prodBaseList;
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
			
			for(int i=0; i<50; i++){
				if(!productInfo.getOptionValue()[i].equals("")){
					productInfo.setPropType(productInfo.getOptionKey()[i]);
					productInfo.setPropName(productInfo.getOptionValue()[i]);
					rst = sqlSession.insert("product.insertBomProductOptionTb0001", productInfo);
				}
			}
			
			
			
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
	
	
	//상품관리(수정)
	@Override
	public int manageProductInf(ProductInfo productInfo) {
		
		int rst = -1;
		
		// -----------------------------------------------
		// 해당하는 상품 데이터가 있는지 확인
		// -----------------------------------------------
		ProductInfo searchRstInf = getProductInfDetail(productInfo);
		
		// -----------------------------------------------
		// 조회한 결과값이 있으면 DB업데이트
		// -----------------------------------------------
		if (searchRstInf != null && StringUtils.isNotEmpty(searchRstInf.getPrdCd())) {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
				// DB 수행
				rst = sqlSession.update("product.updateBomProductTb0001", productInfo);
				
				for(int i=0; i<50; i++){
					if(!productInfo.getOptionValue()[i].equals("") && productInfo.getOptionIdx()!=null){
						productInfo.setPropType(productInfo.getOptionKey()[i]);
						productInfo.setPropName(productInfo.getOptionValue()[i]);
						productInfo.setPropIdx(productInfo.getOptionIdx()[i]);
						rst = sqlSession.insert("product.updateBomProductTb0001", productInfo);
					}
					else if(!productInfo.getOptionValue()[i].equals("")  && productInfo.getOptionIdx()==null){
						productInfo.setPropType(productInfo.getOptionKey()[i]);
						productInfo.setPropName(productInfo.getOptionValue()[i]);
						rst = sqlSession.insert("product.insertBomProductOptionTb0001", productInfo);
					}
					
				}
			} finally {
				sqlSession.close();
			}
		}
		
		return rst;
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
	
	private ProductInfo getProdInfoByProdCd(List<ProductInfo> list, String prodCd) {
		
		for (ProductInfo each : list) {
			if (prodCd.equals(each.getPrdCd())) {
				return each;
			}
		}
		
		return null;
	}
	
	
	
	
	
	
	@Override
	public List<ProductInfo> shopProductInfList(ProductInfo productInfo){
		
		List<ProductInfo> list = null;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try{
			
		list =	sqlSession.selectList("product.selectListBomShopProductTb0001", productInfo);
			
		}finally{
			sqlSession.close();
		}
		
		return list;
	}
	
	

	/*
	 * 상품 코드에 따라 조회 
	 */
	@Override
	public ProductInfo getProductInfDetail(ProductInfo productInfo) {
		                 
		ProductInfo rstInfo = new ProductInfo();
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			// -----------------------------------------------
			// 1. 상품코드 기본값 조회
			// -----------------------------------------------
			rstInfo = sqlSession.selectOne("product.selectDtlBomProductTb0001", productInfo);
			
			
		} finally {
			sqlSession.close();
		}
		
		
		return rstInfo;
	}
	
	/*
	 * 상품 코드에 따라 옵션조회 
	 */
	@Override
	public List<ProductInfo> getProductOptionInfDetail(ProductInfo productInfo) {
		                 
		List<ProductInfo> prdOpList = new ArrayList<ProductInfo>();
		
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			// -----------------------------------------------
			// 1. 상품코드 기본값 조회
			// -----------------------------------------------
			prdOpList = sqlSession.selectList("product.selectDtlBomProductOptionTb0001", productInfo);
			
		} finally {
			sqlSession.close();
		}
		
		
		return prdOpList;
	}
	
	/*
	 * 인덱스에 따라 옵션조회 
	 */
	@Override
	public ProductInfo getProductOptionInfDetail2(ProductInfo productInfo) {
		                 
		ProductInfo prdOp = new ProductInfo();
		
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			// -----------------------------------------------
			// 1. 상품코드 기본값 조회
			// -----------------------------------------------
			prdOp = sqlSession.selectOne("product.selectDtlBomProductOptionTb0002", productInfo);
			
		} finally {
			sqlSession.close();
		}
		
		
		return prdOp;
	}
	
	/*
	 * 상품 삭제
	 */
	@Override
	public int deleteProductInf(ProductInfo productInfo){
		
		int rst = -1;
		
		
			SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
				// DB 수행
				rst = sqlSession.delete("product.deleteBomProductTb0001", productInfo);
				
			} finally {
				sqlSession.close();
			}
		
		
		return rst;
	}

	/*
	 * 상품 옵션 삭제
	 */
	@Override
	public int deleteProductOptionInf(ProductInfo productInfo){
		
		int rst = -1;
		
		
			SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
				// DB 수행
				rst = sqlSession.delete("product.deleteBomProductOptionTb0001", productInfo);
				
			} finally {
				sqlSession.close();
			}
		
		
		return rst;
	}



	
}
