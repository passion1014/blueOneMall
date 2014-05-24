package com.blueone.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueone.admin.domain.AdImgInfo;
import com.blueone.admin.domain.SchWordInfo;

@Service
public class SearchWordServiceImpl implements ISearchWordService{
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	
	//검색어 순위 - 수정
	@Override
	public int updateSchWord(SchWordInfo schWordInfo){
		
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int result = 0;
		try{
			
			result = sqlSession.update("searchWord.updateBomSearchWordTb0001", schWordInfo);
			
		}finally{
			
			sqlSession.close();
		}
		
		
		return result;
	}
	
	//검색어 순위 HIT +1
	@Override
	public int updateHit(SchWordInfo schWordInfo){
		
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int result = 0;
		try{
			
			result = sqlSession.update("searchWord.updateBomSearchWordTb0002", schWordInfo);
			
		}finally{
			
			sqlSession.close();
		}
		
		
		return result;
	}
	
	@Override
	public List<SchWordInfo> getSchWordDtlList(SchWordInfo schWordInfo){
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<SchWordInfo> adDtl = new ArrayList<SchWordInfo>();
		try{
			adDtl =  sqlSession.selectList("searchWord.selectDtlBomSearchWordTb0001", schWordInfo);
			
		}finally{
			sqlSession.close();
			
		}
		return adDtl;
		
	}
	
	@Override
	public SchWordInfo getSchWordDtl(SchWordInfo schWordInfo){
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		SchWordInfo adDtl = new SchWordInfo();
		try{
			adDtl =  sqlSession.selectOne("searchWord.selectDtlBomSearchWordTb0002", schWordInfo);
			
		}finally{
			sqlSession.close();
			
		}
		return adDtl;
		
	}
}
