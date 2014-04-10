package com.blueone.board.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueone.board.domain.BoardAttachFileInfo;
import com.blueone.board.domain.BoardCommentInfo;
import com.blueone.board.domain.BoardInfo;
import com.blueone.board.domain.BoardSrchInfo;
import com.blueone.board.domain.BoardStatInfo;
import com.blueone.common.domain.AttachFileInfo;
import com.blueone.common.domain.FileInfo;
import com.blueone.common.util.FileUploadUtility;

@Service
public class BoardMngService implements IBoardMngService {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	private HashMap<String, Object> valueMap = new HashMap<String, Object>();
		
	@Override
	public List<BoardInfo> getBoardList(BoardSrchInfo boardSrchModel) {
		
		List<BoardInfo> boardInfoList = new ArrayList<BoardInfo>();
		
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
	public int getBoardTotalCount(BoardSrchInfo boardSrchModel) {
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
	public BoardStatInfo getTodayTotalCount(BoardSrchInfo boardSrchModel) {
		BoardStatInfo boardStatInfo = new BoardStatInfo();
		
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
	public List<BoardInfo> getNoticeList(BoardSrchInfo boardSrchModel) {
		List<BoardInfo> boardInfoList = new ArrayList<BoardInfo>();
		
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
	public BoardInfo getBoard(long brdSeq) {
		BoardInfo boardInfo = new BoardInfo();
		
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
	public List<BoardAttachFileInfo> selectBOM_ATTACHFILE_TB(long brdSeq) {
		List<BoardAttachFileInfo> boardAttachFileList = new ArrayList<BoardAttachFileInfo>();

		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			boardAttachFileList = sqlSession.selectList("boardMng.selectBOM_ATTACHFILE_TB", brdSeq);
		} finally {
			sqlSession.close();
		}
		
		return boardAttachFileList;
//		return sqlMapClientTemplate.queryForList("boardMng.selectTBL010103", brdSeq);
	}
	
	@Override
	public List<BoardCommentInfo> selectBOM_BOARD_CMT_TB(long brdSeq) {

		List<BoardCommentInfo> boardAttachFileList = new ArrayList<BoardCommentInfo>();
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			boardAttachFileList = sqlSession.selectList("boardMng.selectBOM_BOARD_CMT_TB", brdSeq);
		} finally {
			sqlSession.close();
		}

		return boardAttachFileList;
//		return sqlMapClientTemplate.queryForList("boardMng.selectTBL010104", brdSeq);
	}
	
	@Override
	public boolean insertBoard(BoardInfo boardModel) {
		
		ArrayList<AttachFileInfo> uploadFileList = null;
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			uploadFileList = FileUploadUtility.doFileUpload(FileUploadUtility.UPLOAD_TYP_BOARD, boardModel.getUploadFile(), false);
			
//			Object brdSeq = sqlMapClient.insert("boardMng.insertTBL010102", boardModel);
			int rst = sqlSession.insert("boardMng.insertBOM_BOARD_TB", boardModel);
			long brdSeq = boardModel.getBrdSeq();
			
			// 첨부파일정보 추가
			AttachFileInfo fileModel = null;
			BoardAttachFileInfo boardAttachFileModel = new BoardAttachFileInfo();
			
			if (uploadFileList != null && uploadFileList.size() > 0) {
//				sqlMapClient.startBatch();
				for (int i = 0; i < uploadFileList.size(); i++) {
					fileModel = uploadFileList.get(i);
					boardAttachFileModel.setBrdSeq((Long)brdSeq);
					boardAttachFileModel.setFlNo(i+1);
					boardAttachFileModel.setAttaKnd("AF"); // 첨부유형:첨부파일
					boardAttachFileModel.setSaveFilename(fileModel.getAttSaveFileNm());
					boardAttachFileModel.setRealFilename(fileModel.getAttRealFileNm());
					boardAttachFileModel.setFilesize(fileModel.getAttFileSize());
					boardAttachFileModel.setFileExt(fileModel.getAttFileExt());
					
//					sqlMapClient.insert("boardMng.insertBOM_ATTACHFILE_TB", boardAttachFileModel);
					sqlSession.insert("boardMng.insertBOM_ATTACHFILE_TB", boardAttachFileModel);
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
	public Object insertBOM_BOARD_TB(BoardInfo boardModel) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int rst = sqlSession.insert("boardMng.insertBOM_BOARD_TB", boardModel);
		} finally {
			sqlSession.close();
		}

		return boardModel;
//		return sqlMapClientTemplate.insert("boardMng.insertTBL010102", boardModel);
	}
	
	@Override
	public boolean insertBOM_ATTACHFILE_TB(BoardAttachFileInfo boardAttachFileModel) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int rst = sqlSession.insert("boardMng.insertBOM_ATTACHFILE_TB", boardAttachFileModel);
		} finally {
			sqlSession.close();
		}

//		sqlMapClientTemplate.insert("boardMng.insertBOM_ATTACHFILE_TB", boardAttachFileModel);		
		return true;
	}
	
	@Override
	public boolean insertBOM_BOARD_CMT_TB(BoardCommentInfo boardCommentModel) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
//			sqlMapClient.startTransaction();
//			sqlMapClient.insert("boardMng.insertBOM_BOARD_CMT_TB", boardCommentModel);
//			sqlMapClient.commitTransaction();
//			sqlMapClient.getCurrentConnection().commit();
			
			sqlSession.insert("boardMng.insertBOM_BOARD_CMT_TB", boardCommentModel);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			sqlSession.close();
		}
		
		return true;
	}
	
	@Override
	public boolean updateBOM_BOARD_TB(BoardInfo boardModel) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update("boardMng.updateBOM_BOARD_TB", boardModel);
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
	public boolean updateBOM_BOARD_TBDel(BoardSrchInfo boardSrchModel) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
//			sqlMapClient.startTransaction();
//			sqlMapClient.update("boardMng.updateTBL010102Del", boardSrchModel);
//			sqlMapClient.commitTransaction();
//			sqlMapClient.getCurrentConnection().commit();
			
			sqlSession.update("boardMng.updateBOM_BOARD_TBDel", boardSrchModel);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			sqlSession.close();
		}
		
		return true;
	}
	
	@Override
	public boolean updateBOM_BOARD_TBBrdTyp(BoardSrchInfo boardSrchModel) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
//			sqlMapClient.startTransaction();
//			sqlMapClient.update("boardMng.updateBOM_BOARD_TBBrdTyp", boardSrchModel);
//			sqlMapClient.commitTransaction();
//			sqlMapClient.getCurrentConnection().commit();
			
			sqlSession.update("boardMng.updateBOM_BOARD_TBBrdTyp", boardSrchModel);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			sqlSession.close();
		}
		
		return true;
	}
	
	@Override
	public boolean updateBOM_BOARD_TBDel(BoardInfo boardModel) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update("boardMng.updateBOM_BOARD_TB", boardModel);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			sqlSession.close();
		}
		
//		sqlMapClientTemplate.update("boardMng.updateBOM_BOARD_TB", boardModel);
		return true;
	}

}
