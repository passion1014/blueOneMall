package com.blueone.board.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueone.board.domain.BoardTypModel;

@Service
public class BoardTypService implements IBoardTypService {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	private HashMap<String, Object> valueMap = new HashMap<String, Object>();

	@Override
	public List<BoardTypModel> getBoardTypList() {
		List<BoardTypModel> boardTypInfoList = new ArrayList<BoardTypModel>();
		
		// -----------------------------------------------------
		// DB 실행
		// -----------------------------------------------------
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			boardTypInfoList = sqlSession.selectList("boardTyp.getBoardTypList");
		} finally {
			sqlSession.close();
		}

		return boardTypInfoList;
//		return sqlMapClientTemplate.queryForList("boardTyp.getBoardTypList");
	}
	
	@Override
	public BoardTypModel getBoardTyp(int brdTyp) {
		BoardTypModel boardTypInfo = new BoardTypModel();
		
		// -----------------------------------------------------
		// DB 실행
		// -----------------------------------------------------
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			boardTypInfo = sqlSession.selectOne("boardTyp.getBoardTyp", brdTyp);
		} finally {
			sqlSession.close();
		}

		return boardTypInfo;
//		return (BoardTypModel) sqlMapClientTemplate.queryForObject("boardTyp.getBoardTyp", brdTyp);
	}
	
	@Override
	public boolean insertTBL010101(BoardTypModel boardTypModel) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();		
		try {
//			sqlMapClient.startTransaction();
//			sqlMapClient.insert("boardTyp.insertTBL010101", boardTypModel);
//			sqlMapClient.commitTransaction();
//			sqlMapClient.getCurrentConnection().commit();
			
			sqlSession.insert("boardTyp.insertTBL010101", boardTypModel);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			sqlSession.close();
		}
		
		return true;
	}
	
	@Override
	public boolean updateTBL010101(BoardTypModel boardTypModel) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();		
		try {
//			sqlMapClient.startTransaction();
//			sqlMapClient.update("boardTyp.updateTBL010101", boardTypModel);
//			sqlMapClient.commitTransaction();
//			sqlMapClient.getCurrentConnection().commit();
			
			sqlSession.update("boardTyp.insertTBL010101", boardTypModel);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			sqlSession.close();
		}
		
		return true;
	}

}
