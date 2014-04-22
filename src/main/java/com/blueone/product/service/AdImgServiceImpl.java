package com.blueone.product.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueone.product.domain.AdImgInfo;

@Service
public class AdImgServiceImpl implements IAdImgService{
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Override
	public int insertAdImg(AdImgInfo adImgInfo){
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int result = 0;
		try{
			
			result = sqlSession.insert("adImg.insertBomAdImgTb0001", adImgInfo);
			
		}finally{
			sqlSession.close();
		}
		
		return result;
	}

	
}
