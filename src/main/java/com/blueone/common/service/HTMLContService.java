package com.blueone.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueone.common.domain.HTMLContInfo;

@Service
public class HTMLContService implements IHTMLContService {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	private HashMap<String, Object> valueMap = new HashMap<String, Object>();
	
	@Override
	public List<HTMLContInfo> getHtmlContList(HTMLContInfo htmlContModel) {
		List<HTMLContInfo> htmlContInfList = new ArrayList<HTMLContInfo>();
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			htmlContInfList = sqlSession.selectList("htmlCont.getHtmlContList", htmlContModel);
		} finally {
			sqlSession.close();
		}

		return htmlContInfList;
//		return sqlMapClientTemplate.queryForList("htmlCont.getHtmlContList", htmlContModel);
	}
	
	@Override
	public HTMLContInfo getHtmlCont(int htmlSeq) {
		HTMLContInfo htmlContInfo = new HTMLContInfo();
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			htmlContInfo = sqlSession.selectOne("htmlCont.getHtmlCont", htmlSeq);
		} finally {
			sqlSession.close();
		}

		return htmlContInfo;
//		return (HTMLContModel) sqlMapClientTemplate.queryForObject("htmlCont.getHtmlCont", htmlSeq);
	}
	
	@Override
	public boolean insertTBL010909(HTMLContInfo htmlContModel) {
		int rst = 0;
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
//			sqlMapClient.startTransaction();
//			sqlMapClient.insert("htmlCont.insertTBL010909", htmlContModel);
//			sqlMapClient.commitTransaction();
//			sqlMapClient.getCurrentConnection().commit();
			
			rst = sqlSession.insert("htmlCont.insertTBL010909", htmlContModel);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			sqlSession.close();

		}
		
		return 1 == rst;
	}
	
	@Override
	public boolean updateTBL010909(HTMLContInfo htmlContModel) {
		int rst = 0;
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			
//			sqlMapClient.startTransaction();
//			sqlMapClient.insert("htmlCont.updateTBL010909", htmlContModel);
//			sqlMapClient.commitTransaction();
//			sqlMapClient.getCurrentConnection().commit();

			rst = sqlSession.update("htmlCont.updateTBL010909", htmlContModel);

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			sqlSession.close();
		}
		
		return 1 == rst;
	}
	
	@Override
	public boolean deleteTBL010909(int htmlSeq) {
		
		int rst = 0;
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			
//			sqlMapClient.startTransaction();
//			sqlMapClient.insert("htmlCont.deleteTBL010909", htmlSeq);
//			sqlMapClient.commitTransaction();
//			sqlMapClient.getCurrentConnection().commit();
			rst = sqlSession.delete("htmlCont.deleteTBL010909", htmlSeq);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			sqlSession.close();
		}
		
		return 1 == rst;
	}
	
}//End class
