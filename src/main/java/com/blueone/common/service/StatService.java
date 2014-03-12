package com.blueone.common.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueone.common.domain.StatInfo;


//@Service
public class StatService implements IStatService {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	private HashMap<String, Object> valueMap = new HashMap<String, Object>();
	
	@Override
	public List<StatInfo> getConnection(StatInfo statModel) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession.selectList("statMng.getConnection", statModel);
	}
	
	@Override
	public List<StatInfo> getAudience(StatInfo statModel) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession.selectList("statMng.getAudience", statModel);
	}
	
	@Override
	public List<StatInfo> getProgram(StatInfo statModel) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession.selectList("statMng.getProgram", statModel);
	}
	
	@Override
	public List<StatInfo> getProgramSch(StatInfo statModel) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession.selectList("statMng.getProgramSch", statModel);
	}
	
	@Override
	public List<StatInfo> getTBL090210List(StatInfo statModel) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession.selectList("statMng.getTBL090210List", statModel);
	}
	
	@Override
	public int getTBL090210TotalCount(StatInfo statModel) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return (Integer) sqlSession.selectOne("statMng.getTBL090210TotalCount", statModel);
	}
	
	@Override
	public HashMap<String, Integer> getTBL090210Stat(StatInfo statModel) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return (HashMap) sqlSession.selectOne("statMng.getTBL090210Stat", statModel);
	}
	
//	@Override
//	public List<TBL090212Model> totalTBL090212(StatModel statModel) {
//		return sqlMapClientTemplate.queryForList("statMng.totalTBL090212", statModel);
//	}
	
	@Override
	public StatInfo selectTBL090210(long effSeq) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return (StatInfo) sqlSession.selectOne("statMng.selectTBL090210", effSeq);
	}
	
//	@Override
//	public List<TBL090211Model> selectTBL090211(long effSeq) {
//		return sqlMapClientTemplate.queryForList("statMng.selectTBL090211", effSeq);
//	}
//	public List<TBL090212Model> selectTBL090212(long effSeq) {
//		return sqlMapClientTemplate.queryForList("statMng.selectTBL090212", effSeq);
//	}

//	@Override
//	public boolean insertTBL090210(StatModel statModel) {
//		
//		try {
//			
//			sqlMapClient.startTransaction();
//			Object effSeq = sqlMapClient.insert("statMng.insertTBL090210", statModel);
//			
//			if (statModel.getCountryCd() != null && statModel.getCountryCd().length > 0) {
//				TBL090212Model tbl090212Model = new TBL090212Model();
//				tbl090212Model.setEffSeq((Long) effSeq);
//				sqlMapClient.startBatch();
//				for (int i = 0; i < statModel.getCountryCd().length; i++) {
//					tbl090212Model.setCountryCd(statModel.getCountryCd()[i]);
//					tbl090212Model.setCountryCnt(statModel.getCountryCnt()[i]);
//			
//					sqlMapClient.insert("statMng.insertTBL090212", tbl090212Model);
//				}
//				sqlMapClient.executeBatch();
//			}
//			
//			if (statModel.getQueKnd() != null && statModel.getQueKnd().length > 0) {
//				TBL090211Model tbl090211Model = new TBL090211Model();
//				tbl090211Model.setEffSeq((Long) effSeq);
//				sqlMapClient.startBatch();
//				for (int i = 0; i < statModel.getQueKnd().length; i++) {
//					tbl090211Model.setQueKnd(statModel.getQueKnd()[i]);
//					tbl090211Model.setQueNo(statModel.getQueNo()[i]);
//					tbl090211Model.setAnsMinus2(statModel.getAnsMinus2()[i]);
//					tbl090211Model.setAnsMinus1(statModel.getAnsMinus1()[i]);
//					tbl090211Model.setAnsDefault(statModel.getAnsDefault()[i]);
//					tbl090211Model.setAnsPlus1(statModel.getAnsPlus1()[i]);
//					tbl090211Model.setAnsPlus2(statModel.getAnsPlus2()[i]);
//					
//					sqlMapClient.insert("statMng.insertTBL090211", tbl090211Model);
//				}
//				sqlMapClient.executeBatch();
//			}
//			
//			sqlMapClient.commitTransaction();
//			sqlMapClient.getCurrentConnection().commit();
//			
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			return false;
//		} finally {
//			try { if (sqlMapClient != null) sqlMapClient.endTransaction(); } catch (Exception e) {};
//		}
//		
//		return true;
//	}
//	
//	@Override
//	public boolean updateTBL090210(StatModel statModel) {
//		
//		try {
//			
//			sqlMapClient.startTransaction();
//			sqlMapClient.update("statMng.updateTBL090210", statModel);
//			
//			
//			
//			if (statModel.getCountryCd() != null && statModel.getCountryCd().length > 0) {
//				sqlMapClient.startBatch();
//				
//				// 기존 데이터 삭제
//				sqlMapClient.delete("statMng.deleteTBL090212", statModel.getEffSeq());
//				
//				TBL090212Model tbl090212Model = new TBL090212Model();
//				tbl090212Model.setEffSeq(statModel.getEffSeq());
//				
//				for (int i = 0; i < statModel.getCountryCd().length; i++) {
//					tbl090212Model.setCountryCd(statModel.getCountryCd()[i]);
//					tbl090212Model.setCountryCnt(statModel.getCountryCnt()[i]);
//			
//					sqlMapClient.insert("statMng.insertTBL090212", tbl090212Model);
//				}
//				sqlMapClient.executeBatch();
//			}
//			
//			
//			if (statModel.getQueKnd() != null && statModel.getQueKnd().length > 0) {
//				sqlMapClient.startBatch();
//				
//				// 기존 데이터 삭제
//				sqlMapClient.delete("statMng.deleteTBL090211", statModel.getEffSeq());
//				
//				TBL090211Model tbl090211Model = new TBL090211Model();
//				tbl090211Model.setEffSeq(statModel.getEffSeq());
//				
//				for (int i = 0; i < statModel.getQueKnd().length; i++) {
//					tbl090211Model.setQueKnd(statModel.getQueKnd()[i]);
//					tbl090211Model.setQueNo(statModel.getQueNo()[i]);
//					tbl090211Model.setAnsMinus2(statModel.getAnsMinus2()[i]);
//					tbl090211Model.setAnsMinus1(statModel.getAnsMinus1()[i]);
//					tbl090211Model.setAnsDefault(statModel.getAnsDefault()[i]);
//					tbl090211Model.setAnsPlus1(statModel.getAnsPlus1()[i]);
//					tbl090211Model.setAnsPlus2(statModel.getAnsPlus2()[i]);
//					
//					sqlMapClient.insert("statMng.insertTBL090211", tbl090211Model);
//				}
//				sqlMapClient.executeBatch();
//			}
//			
//			sqlMapClient.commitTransaction();
//			sqlMapClient.getCurrentConnection().commit();
//			
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			return false;
//		} finally {
//			try { if (sqlMapClient != null) sqlMapClient.endTransaction(); } catch (Exception e) {};
//		}
//		
//		return true;
//	}
//	
//	@Override
//	public boolean deleteTBL090210(StatModel statModel) {
//		
//		try {
//			
//			sqlMapClient.startTransaction();
//			sqlMapClient.delete("statMng.deleteTBL090212", statModel.getEffSeq());
//			sqlMapClient.delete("statMng.deleteTBL090211", statModel.getEffSeq());
//			sqlMapClient.update("statMng.deleteTBL090210", statModel.getEffSeq());
//			sqlMapClient.commitTransaction();
//			sqlMapClient.getCurrentConnection().commit();
//			
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			return false;
//		} finally {
//			try { if (sqlMapClient != null) sqlMapClient.endTransaction(); } catch (Exception e) {};
//		}
//		
//		return true;
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	@Override
//	public List<StatModel> getTBL090211List(StatModel statModel) {
//		return sqlMapClientTemplate.queryForList("statMng.getTBL090211List", statModel);
//	}
//	
//	@Override
//	public int getTBL090211TotalCount(StatModel statModel) {
//		return (Integer) sqlMapClientTemplate.queryForObject("statMng.getTBL090211TotalCount", statModel);
//	}
//	
//	@Override
//	public HashMap<String, Integer> getTBL090211Stat(StatModel statModel) {
//		return (HashMap) sqlMapClientTemplate.queryForObject("statMng.getTBL090211Stat", statModel);
//	}
}//End class
