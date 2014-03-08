package com.blueone.board.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueone.board.domain.BoardAttachFileModel;
import com.blueone.board.domain.BoardCommentModel;
import com.blueone.board.domain.BoardModel;
import com.blueone.board.domain.BoardSrchModel;
import com.blueone.board.domain.BoardStatModel;
import com.blueone.common.domain.FileModel;
import com.blueone.common.util.FileUploadUtility;

@Service
public class BoardMngService implements IBoardMngService {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	private HashMap<String, Object> valueMap = new HashMap<String, Object>();
		
	@Override
	public List<BoardModel> getBoardList(BoardSrchModel boardSrchModel) {
		
		List<BoardModel> boardInfoList = new ArrayList<BoardModel>();
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			boardInfoList = sqlSession.selectList("boardMng.getBoardList", boardSrchModel);
		} finally {
			sqlSession.close();
		}

		return boardInfoList;
//		return sqlMapClientTemplate.queryForList("boardMng.getBoardList", boardSrchModel);
	}
	
	@Override
	public int getBoardTotalCount(BoardSrchModel boardSrchModel) {
		int rst = 0;
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			rst = sqlSession.selectOne("boardMng.getBoardTotalCount", boardSrchModel);
		} finally {
			sqlSession.close();
		}

		return rst;
//		return (Integer) sqlMapClientTemplate.queryForObject("boardMng.getBoardTotalCount", boardSrchModel);
	}
	
	@Override
	public BoardStatModel getTodayTotalCount(BoardSrchModel boardSrchModel) {
		BoardStatModel boardStatInfo = new BoardStatModel();
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			boardStatInfo = sqlSession.selectOne("boardMng.getTodayTotalCount", boardSrchModel);
		} finally {
			sqlSession.close();
		}
		
		return boardStatInfo;
//		return (BoardStatModel) sqlMapClientTemplate.queryForObject("boardMng.getTodayTotalCount", boardSrchModel);
	}
	
	@Override
	public List<BoardModel> getNoticeList(BoardSrchModel boardSrchModel) {
		List<BoardModel> boardInfoList = new ArrayList<BoardModel>();
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			boardInfoList = sqlSession.selectList("boardMng.getNoticeList", boardSrchModel);
		} finally {
			sqlSession.close();
		}
		
		return boardInfoList;
//		return sqlMapClientTemplate.queryForList("boardMng.getNoticeList", boardSrchModel);
	}
	
	@Override
	public BoardModel getBoard(long brdSeq) {
		BoardModel boardInfo = new BoardModel();
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			boardInfo = sqlSession.selectOne("boardMng.getBoard", brdSeq);
		} finally {
			sqlSession.close();
		}
		
		return boardInfo;
//		return (BoardModel) sqlMapClientTemplate.queryForObject("boardMng.getBoard", brdSeq);
	}
	
	@Override
	public List<BoardAttachFileModel> selectTBL010103(long brdSeq) {
		List<BoardAttachFileModel> boardAttachFileList = new ArrayList<BoardAttachFileModel>();

		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			boardAttachFileList = sqlSession.selectList("boardMng.selectTBL010103", brdSeq);
		} finally {
			sqlSession.close();
		}
		
		return boardAttachFileList;
//		return sqlMapClientTemplate.queryForList("boardMng.selectTBL010103", brdSeq);
	}
	
	@Override
	public List<BoardCommentModel> selectTBL010104(long brdSeq) {

		List<BoardCommentModel> boardAttachFileList = new ArrayList<BoardCommentModel>();
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			boardAttachFileList = sqlSession.selectList("boardMng.selectTBL010104", brdSeq);
		} finally {
			sqlSession.close();
		}

		return boardAttachFileList;
//		return sqlMapClientTemplate.queryForList("boardMng.selectTBL010104", brdSeq);
	}
	
	@Override
	public boolean insertBoard(BoardModel boardModel) {
		
		ArrayList<FileModel> uploadFileList = null;
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			uploadFileList = FileUploadUtility.doFileUpload(FileUploadUtility.UPLOAD_TYP_BOARD, boardModel.getUploadFile(), false);
			
//			Object brdSeq = sqlMapClient.insert("boardMng.insertTBL010102", boardModel);
			int rst = sqlSession.insert("boardMng.insertTBL010102", boardModel);
			long brdSeq = boardModel.getBrdSeq();
			
			// 첨부파일정보 추가
			FileModel fileModel = null;
			BoardAttachFileModel boardAttachFileModel = new BoardAttachFileModel();
			
			if (uploadFileList != null && uploadFileList.size() > 0) {
//				sqlMapClient.startBatch();
				for (int i = 0; i < uploadFileList.size(); i++) {
					fileModel = uploadFileList.get(i);
					boardAttachFileModel.setBrdSeq((Long)brdSeq);
					boardAttachFileModel.setFlNo(i+1);
					boardAttachFileModel.setAttaKnd("AF"); // 첨부유형:첨부파일
					boardAttachFileModel.setSaveFilename(fileModel.getSaveFilename());
					boardAttachFileModel.setRealFilename(fileModel.getRealFilename());
					boardAttachFileModel.setFilesize(fileModel.getFilesize());
					boardAttachFileModel.setFileExt(fileModel.getFileExt());
					
//					sqlMapClient.insert("boardMng.insertTBL010103", boardAttachFileModel);
					sqlSession.insert("boardMng.insertTBL010103", boardAttachFileModel);
				}
//				sqlMapClient.executeBatch();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			sqlSession.close();
		}

		return true;
	}
	
	@Override
	public Object insertTBL010102(BoardModel boardModel) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int rst = sqlSession.insert("boardMng.insertTBL010102", boardModel);
		} finally {
			sqlSession.close();
		}

		return boardModel;
//		return sqlMapClientTemplate.insert("boardMng.insertTBL010102", boardModel);
	}
	
	@Override
	public boolean insertTBL010103(BoardAttachFileModel boardAttachFileModel) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int rst = sqlSession.insert("boardMng.insertTBL010103", boardAttachFileModel);
		} finally {
			sqlSession.close();
		}

//		sqlMapClientTemplate.insert("boardMng.insertTBL010103", boardAttachFileModel);		
		return true;
	}
	
	@Override
	public boolean insertTBL010104(BoardCommentModel boardCommentModel) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
//			sqlMapClient.startTransaction();
//			sqlMapClient.insert("boardMng.insertTBL010104", boardCommentModel);
//			sqlMapClient.commitTransaction();
//			sqlMapClient.getCurrentConnection().commit();
			
			sqlSession.insert("boardMng.insertTBL010104", boardCommentModel);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			sqlSession.close();
		}
		
		return true;
	}
	
	@Override
	public boolean updateTBL010102(BoardModel boardModel) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update("boardMng.updateTBL010102", boardModel);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			sqlSession.close();
		}
		
//		sqlMapClientTemplate.update("boardMng.updateTBL010102", boardModel);
		return true;
	}
	
	@Override
	public boolean updateTBL010102Del(BoardSrchModel boardSrchModel) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
//			sqlMapClient.startTransaction();
//			sqlMapClient.update("boardMng.updateTBL010102Del", boardSrchModel);
//			sqlMapClient.commitTransaction();
//			sqlMapClient.getCurrentConnection().commit();
			
			sqlSession.update("boardMng.updateTBL010102Del", boardSrchModel);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			sqlSession.close();
		}
		
		return true;
	}
	
	@Override
	public boolean updateTBL010102BrdTyp(BoardSrchModel boardSrchModel) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
//			sqlMapClient.startTransaction();
//			sqlMapClient.update("boardMng.updateTBL010102BrdTyp", boardSrchModel);
//			sqlMapClient.commitTransaction();
//			sqlMapClient.getCurrentConnection().commit();
			
			sqlSession.update("boardMng.updateTBL010102BrdTyp", boardSrchModel);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			sqlSession.close();
		}
		
		return true;
	}
	
	@Override
	public boolean updateTBL010102Del(BoardModel boardModel) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update("boardMng.updateTBL010102", boardModel);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			sqlSession.close();
		}
		
//		sqlMapClientTemplate.update("boardMng.updateTBL010102", boardModel);
		return true;
	}

}
