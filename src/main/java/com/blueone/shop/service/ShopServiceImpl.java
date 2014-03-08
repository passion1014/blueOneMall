package com.blueone.shop.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.blueone.shop.domain.ShopInfo;
import com.blueone.admin.domain.AdminInfo;
import com.blueone.common.util.MsgEnum;

@Service
public class ShopServiceImpl implements ShopService{

	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	
	
	
		
	@Override
	public int shopInsert(ShopInfo shopInfo){
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		
		try{
			
			
			int rst = sqlSession.insert("shop.insertBomShopTb0001", shopInfo);
			
			
			
		}finally{ 
			
			sqlSession.close();
		}
		return MsgEnum.MsgEnum_SUCCESS.value();
		
		
		
	}
	
	
	@Override
	public List<ShopInfo>  getList(ShopInfo shopInfo){
		
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		List<ShopInfo> list = new ArrayList<ShopInfo>();
		
		try{
			
			Map<String, ShopInfo> map = new HashMap<String , ShopInfo>();
			
			list = sqlSession.selectList("shop.selectListBomShopTb0001", map);
		}finally{
			
			sqlSession.close();
		}
		
		
		  
		
		return list;
		
		
		
		
		
		
	}
	
	/*
	@Override
	public ShopInfo getInfo(String data){
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		
		
		
	}
	*/

	
	
	
	
	
	
	
}
