package com.blueone.product.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueone.product.domain.ProductInfo;

@Service
public class UserProductServiceImpl implements IUserProductService{
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
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
		
		
		
		
	}

