package com.blueone.board.service;

import java.util.HashMap;
import java.util.List;




import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.blueone.board.domain.BoardTypModel;
import com.ibatis.sqlmap.client.SqlMapClient;

public class BoardTypService implements IBoardTypService {
	private HashMap<String, Object> valueMap = new HashMap<String, Object>();
		

	@Override
	public List<BoardTypModel> getBoardTypList() {
		return sqlMapClientTemplate.queryForList("boardTyp.getBoardTypList");
	};
	
	@Override
	public BoardTypModel getBoardTyp(int brdTyp) {
		return (BoardTypModel) sqlMapClientTemplate.queryForObject("boardTyp.getBoardTyp", brdTyp);
	}
	
	@Override
	public boolean insertTBL010101(BoardTypModel boardTypModel) {
		
		try {
			
			sqlMapClient.startTransaction();
			sqlMapClient.insert("boardTyp.insertTBL010101", boardTypModel);
			sqlMapClient.commitTransaction();
			sqlMapClient.getCurrentConnection().commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			try { if (sqlMapClient != null) sqlMapClient.endTransaction(); } catch (Exception e) {};
		}
		
		return true;
	}
	
	@Override
	public boolean updateTBL010101(BoardTypModel boardTypModel) {
		
		try {
			
			sqlMapClient.startTransaction();
			sqlMapClient.update("boardTyp.updateTBL010101", boardTypModel);
			sqlMapClient.commitTransaction();
			sqlMapClient.getCurrentConnection().commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			try { if (sqlMapClient != null) sqlMapClient.endTransaction(); } catch (Exception e) {};
		}
		
		return true;
	}

}
