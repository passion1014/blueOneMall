package com.blueone.common.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueone.common.domain.ZipInfo;

@Service
public class ZipService implements IZipService {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	private HashMap<String, Object> valueMap = new HashMap<String, Object>();
	
	@Override
	public List<ZipInfo> getZipList(ZipInfo zipModel) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession.selectList("zip.getZipList", zipModel);
	}
	
	@Override
	public List<ZipInfo> getGiBunList(ZipInfo zipModel) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession.selectList("zip.getGiBunList", zipModel);
	}
	
	@Override
	public List<ZipInfo> getRoadNmList(ZipInfo zipModel) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession.selectList("zip.getRoadNmList", zipModel);
	}
	
	@Override
	public HashMap<String, Object> makeRoadAddr(String oldAddr) {
		valueMap.put("avGb", "1");
		valueMap.put("avOldAddr", oldAddr);
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.selectList("zip.callSpNewAddrMake", valueMap);
		
		return valueMap;
	}
	
}
