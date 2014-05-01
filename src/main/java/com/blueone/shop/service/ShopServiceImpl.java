package com.blueone.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueone.admin.domain.AdImgInfo;
import com.blueone.category.domain.CategoryInfo;
import com.blueone.product.domain.ProductInfo;



@Service
public class ShopServiceImpl implements IShopService{

	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	
	//메인화면에 배너이미지출력
	@Override
	public AdImgInfo getAdImg(AdImgInfo adImgInfo){
		
		
		AdImgInfo adImg = new AdImgInfo();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try{
			
			adImg = sqlSession.selectOne("adImg.selectBomAdImgTb0001", adImgInfo);
			
		}finally{
			sqlSession.close();
		}
		
		
		return adImg;
		
		
		
	}
	
	//메인화면에 상품이미지 출력
	@Override
	public List<ProductInfo> getImgList(ProductInfo productInfo){
		
		List<ProductInfo> rstInfo = new ArrayList<ProductInfo>();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try{
			
			rstInfo = sqlSession.selectList("product.selectListBomProductTb0005", productInfo);
			
		}finally{
			
			sqlSession.close();
			
		}
		return rstInfo;
		
	}
	

//	@Override
//	public AdImgInfo getAdImg2(AdImgInfo adImgInfo){
//		
//		SqlSession sqlSession = sqlSessionFactory.openSession();
//		
//		try{
//			
//			adImgInfo = sqlSession.selectOne("adImg.selectBomAdImgTb0002", adImgInfo);
//			
//		}finally{
//			
//			sqlSession.close();
//			
//		}
//		return adImgInfo;
//	}
//	
	

	
	
	
	
	
	
	
}
