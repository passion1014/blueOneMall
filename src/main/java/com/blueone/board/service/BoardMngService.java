package com.blueone.board.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import klac.board.dao.BoardMngDao;
import klac.board.model.BoardAttachFileModel;
import klac.board.model.BoardCommentModel;
import klac.board.model.BoardModel;
import klac.board.model.BoardSrchModel;
import klac.board.model.BoardStatModel;
import klac.common.FileUploadUtility;
import klac.common.model.FileModel;


import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;

public class BoardMngService implements IBoardMngService {
	private SqlMapClient sqlMapClient;
	private SqlMapClientTemplate sqlMapClientTemplate;
	private HashMap<String, Object> valueMap = new HashMap<String, Object>();
		

	public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
		this.sqlMapClient = sqlMapClientTemplate.getSqlMapClient();
	}
	
	@Override
	public List<BoardModel> getBoardList(BoardSrchModel boardSrchModel) {
		return sqlMapClientTemplate.queryForList("boardMng.getBoardList", boardSrchModel);
	}
	
	@Override
	public int getBoardTotalCount(BoardSrchModel boardSrchModel) {
		return (Integer) sqlMapClientTemplate.queryForObject("boardMng.getBoardTotalCount", boardSrchModel);
	}
	
	@Override
	public BoardStatModel getTodayTotalCount(BoardSrchModel boardSrchModel) {
		return (BoardStatModel) sqlMapClientTemplate.queryForObject("boardMng.getTodayTotalCount", boardSrchModel);
	}
	
	@Override
	public List<BoardModel> getNoticeList(BoardSrchModel boardSrchModel) {
		return sqlMapClientTemplate.queryForList("boardMng.getNoticeList", boardSrchModel);
	}
	
	@Override
	public BoardModel getBoard(long brdSeq) {
		return (BoardModel) sqlMapClientTemplate.queryForObject("boardMng.getBoard", brdSeq);
	}
	
	@Override
	public List<BoardAttachFileModel> selectTBL010103(long brdSeq) {
		return sqlMapClientTemplate.queryForList("boardMng.selectTBL010103", brdSeq);
	}
	
	@Override
	public List<BoardCommentModel> selectTBL010104(long brdSeq) {
		return sqlMapClientTemplate.queryForList("boardMng.selectTBL010104", brdSeq);
	}
	
	@Override
	public boolean insertBoard(BoardModel boardModel) {
		
		ArrayList<FileModel> uploadFileList = null;
		try {
			uploadFileList = FileUploadUtility.doFileUpload(FileUploadUtility.UPLOAD_TYP_BOARD, boardModel.getUploadFile(), false);
			
			// 게시물추가(트렌젝션 처리)
			sqlMapClient.startTransaction();
			
			Object brdSeq = sqlMapClient.insert("boardMng.insertTBL010102", boardModel);
			if (brdSeq == null) return false;
			
			// 첨부파일정보 추가
			FileModel fileModel = null;
			BoardAttachFileModel boardAttachFileModel = new BoardAttachFileModel();
			
			if (uploadFileList != null && uploadFileList.size() > 0) {
				sqlMapClient.startBatch();
				for (int i = 0; i < uploadFileList.size(); i++) {
					fileModel = uploadFileList.get(i);
					boardAttachFileModel.setBrdSeq((Long)brdSeq);
					boardAttachFileModel.setFlNo(i+1);
					boardAttachFileModel.setAttaKnd("AF"); // 첨부유형:첨부파일
					boardAttachFileModel.setSaveFilename(fileModel.getSaveFilename());
					boardAttachFileModel.setRealFilename(fileModel.getRealFilename());
					boardAttachFileModel.setFilesize(fileModel.getFilesize());
					boardAttachFileModel.setFileExt(fileModel.getFileExt());
					
					sqlMapClient.insert("boardMng.insertTBL010103", boardAttachFileModel);
				}
				sqlMapClient.executeBatch();
			}
			
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
	public Object insertTBL010102(BoardModel boardModel) {
		return sqlMapClientTemplate.insert("boardMng.insertTBL010102", boardModel);
	}
	
	@Override
	public boolean insertTBL010103(BoardAttachFileModel boardAttachFileModel) {
		sqlMapClientTemplate.insert("boardMng.insertTBL010103", boardAttachFileModel);		
		return true;
	}
	
	@Override
	public boolean insertTBL010104(BoardCommentModel boardCommentModel) {
		
		try {
			
			sqlMapClient.startTransaction();
			sqlMapClient.insert("boardMng.insertTBL010104", boardCommentModel);
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
	public boolean updateTBL010102(BoardModel boardModel) {
		sqlMapClientTemplate.update("boardMng.updateTBL010102", boardModel);
		return true;
	}
	
	@Override
	public boolean updateTBL010102Del(BoardSrchModel boardSrchModel) {
		
		try {
			
			sqlMapClient.startTransaction();
			sqlMapClient.update("boardMng.updateTBL010102Del", boardSrchModel);
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
	public boolean updateTBL010102BrdTyp(BoardSrchModel boardSrchModel) {
		
		try {
			
			sqlMapClient.startTransaction();
			sqlMapClient.update("boardMng.updateTBL010102BrdTyp", boardSrchModel);
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
	public boolean updateTBL010102Del(BoardModel boardModel) {
		sqlMapClientTemplate.update("boardMng.updateTBL010102", boardModel);
		return true;
	}

}
