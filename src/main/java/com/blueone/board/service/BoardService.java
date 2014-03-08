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
import com.blueone.common.domain.FileModel;
import com.blueone.common.util.FileUploadUtility;

@Service
public class BoardService implements IBoardService {
	private HashMap<String, Object> valueMap = new HashMap<String, Object>();
		
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public List<BoardModel> getBrdTypBoardList(BoardSrchModel boardSrchModel) {
		List<BoardModel> boards = new ArrayList<BoardModel>();
		BoardModel board = null;
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			boards = sqlSession.selectList("board.getBrdTypBoardList", boardSrchModel);
			
			if (boards != null && boards.size() > 0) {
				for (int i = 0; i < boards.size(); i++) {
					board = boards.get(i);
					board.setAttachFiles(selectTBL010103(board.getBrdSeq()));
				}
			}
			
		} finally {
			sqlSession.close();
		}

		
		return boards;
	}
	
	@Override
	public int getBrdTypTotalCount(BoardSrchModel boardSrchModel) {
		Integer count = new Integer(0);
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			count = sqlSession.selectOne("board.getBrdTypTotalCount", boardSrchModel);
		} finally {
			sqlSession.close();
		}
		
		return count;
	}
	
	@Override
	public List<BoardModel> getBoardLastList(int[] brdTyps, int size) {
		valueMap.put("brdTyps", brdTyps);
		valueMap.put("size", size);
		
		List<BoardModel> boardInfoList = new ArrayList<BoardModel>();
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			boardInfoList = sqlSession.selectList("board.getBoardLastList", valueMap);
		} finally {
			sqlSession.close();
		}

		return boardInfoList;
//		return sqlMapClientTemplate.queryForList("board.getBoardLastList", valueMap);
	}
	
	@Override
	public List<BoardModel> getBrdTypNoticeList(BoardSrchModel boardSrchModel) {
		List<BoardModel> boardList = new ArrayList<BoardModel>();
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			boardList = sqlSession.selectList("board.getBrdTypNoticeList", boardSrchModel);
			if (boardList != null && boardList.size() > 0) {
				for (int i = 0; i < boardList.size(); i++) {
					BoardModel board = boardList.get(i);
					board.setAttachFiles(selectTBL010103(board.getBrdSeq()));
				}
			}
		} finally {
			sqlSession.close();
		}
		
		return boardList;
	}
	
	@Override
	public boolean insertBoard(BoardModel boardModel) {
		
		FileModel imgFile = null;
		ArrayList<FileModel> uploadFileList = null;
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			
			// 이미지 파일업로드
			imgFile = FileUploadUtility.doFileUpload(FileUploadUtility.UPLOAD_TYP_BOARD_IMAGE, boardModel.getImgFile(), false);
			// 첨부파일 업로드
			uploadFileList = FileUploadUtility.doFileUpload(FileUploadUtility.UPLOAD_TYP_BOARD, boardModel.getUploadFile(), false);
			
//			// 게시물추가(트렌젝션 처리)
//			sqlMapClient.startTransaction();
			
//			Object brdSeq = sqlMapClient.insert("board.insertTBL010102", boardModel);
			sqlSession.insert("board.insertTBL010102", boardModel);
			if (boardModel.getBrdSeq() == 0) return false;
			
			// 답변갯수 업데이트
			if (boardModel.getDepth() > 0 && boardModel.getRootSeq() > 0) {
				sqlSession.update("board.updateTBL010102ReplyCnt", boardModel.getRootSeq());
			}
			
			// 첨부파일정보 추가
			int flNo = 1;
			FileModel fileModel = null;
			BoardAttachFileModel boardAttachFileModel = new BoardAttachFileModel();
			
			if (uploadFileList != null && uploadFileList.size() > 0) {
//				sqlMapClient.startBatch();
				for (int i = 0; i < uploadFileList.size(); i++) {
					fileModel = uploadFileList.get(i);
					boardAttachFileModel.setBrdSeq(boardModel.getBrdSeq());
					boardAttachFileModel.setFlNo(flNo++);
					boardAttachFileModel.setAttaKnd("AF"); // 첨부유형:첨부파일
					boardAttachFileModel.setSaveFilename(fileModel.getSaveFilename());
					boardAttachFileModel.setRealFilename(fileModel.getRealFilename());
					boardAttachFileModel.setFilesize(fileModel.getFilesize());
					boardAttachFileModel.setFileExt(fileModel.getFileExt());
					
					sqlSession.insert("board.insertTBL010103", boardAttachFileModel);
				}
//				sqlMapClient.executeBatch();
			}
			
			// 이미지 정보
			if (imgFile != null) {
//				sqlMapClient.startBatch();
				boardAttachFileModel.setBrdSeq(boardModel.getBrdSeq());
				boardAttachFileModel.setFlNo(flNo++);
				boardAttachFileModel.setAttaKnd("MI"); // 첨부유형:첨부파일
				boardAttachFileModel.setSaveFilename(imgFile.getSaveFilename());
				boardAttachFileModel.setRealFilename(imgFile.getRealFilename());
				boardAttachFileModel.setFilesize(imgFile.getFilesize());
				boardAttachFileModel.setFileExt(imgFile.getFileExt());
				
				sqlSession.insert("board.insertTBL010103", boardAttachFileModel);
//				sqlMapClient.executeBatch();
			}
			
			// 내용에 추가된 이미지
			if (boardModel.getContImageFile() != null && boardModel.getContImageFile().length > 0) {
				for (int i = 0; i < boardModel.getContImageFile().length; i++) {
//					sqlMapClient.startBatch();
					boardAttachFileModel.setBrdSeq(boardModel.getBrdSeq());
					boardAttachFileModel.setFlNo(flNo++);
					boardAttachFileModel.setAttaKnd("CF"); // 첨부유형:내용속첨부파일
					boardAttachFileModel.setSaveFilename(boardModel.getContImageFile()[i]);
					boardAttachFileModel.setRealFilename(boardModel.getContImageFile()[i]);
					boardAttachFileModel.setFilesize(0);
					boardAttachFileModel.setFileExt(FileUploadUtility.getFileExt(boardModel.getContImageFile()[i]).toUpperCase());
					
					sqlSession.insert("board.insertTBL010103", boardAttachFileModel);
//					sqlMapClient.executeBatch();
				}
			}
			
//			sqlMapClient.commitTransaction();
//			sqlMapClient.getCurrentConnection().commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			sqlSession.close();
		}

		return true;
	}
	
	@Override
	public boolean insertTBL010104(BoardCommentModel boardCommentModel) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
//			sqlMapClient.startTransaction();
			sqlSession.insert("board.insertTBL010104", boardCommentModel);
//			sqlMapClient.commitTransaction();
//			sqlMapClient.getCurrentConnection().commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			sqlSession.close();
		}
		
		return true;
	}
	
	@Override
	public boolean updateTBL010104(BoardCommentModel boardCommentModel) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
//			sqlMapClient.startTransaction();
			sqlSession.update("board.updateTBL010104", boardCommentModel);
//			sqlMapClient.commitTransaction();
//			sqlMapClient.getCurrentConnection().commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			sqlSession.close();
		}
		
		return true;
	}
	
	@Override
	public boolean updateBoard(BoardModel boardModel) {
		
		FileModel imgFile = null;
		ArrayList<FileModel> uploadFileList = null;
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {

			// 기존 첨부파일 목록
			List<BoardAttachFileModel> attaFileList = selectTBL010103(boardModel.getBrdSeq());
			
			// 이미지 파일업로드
			imgFile = FileUploadUtility.doFileUpload(FileUploadUtility.UPLOAD_TYP_BOARD_IMAGE, boardModel.getImgFile(), false);
			// 신규 업로드파일
			uploadFileList = FileUploadUtility.doFileUpload(FileUploadUtility.UPLOAD_TYP_BOARD, boardModel.getUploadFile(), false);
			
			// 게시물추가(트렌젝션 처리)
//			sqlMapClient.startTransaction();
			
			// 업데이트
			sqlSession.update("board.updateTBL010102", boardModel);
			
			// 첨부파일정보 추가
			int flNo = 1;
			FileModel fileModel = null;
			BoardAttachFileModel boardAttachFileModel = new BoardAttachFileModel();
			BoardAttachFileModel tmpBoardAttachFileModel = new BoardAttachFileModel();
			
			// 기존첨부파일 모두삭제
			sqlSession.delete("board.deleteTBL010103", boardModel.getBrdSeq());
			
			// 기존첨부파일중 남은 첨부파일 추가
			if (attaFileList != null && attaFileList.size() > 0) {
				for (int i = 0; i < attaFileList.size(); i++) {
					tmpBoardAttachFileModel = attaFileList.get(i);
					
					// 첨부파일
					if ("AF".equals(tmpBoardAttachFileModel.getAttaKnd())) {
						if (boardModel.getFlNo() != null && boardModel.getFlNo().length > 0) {
							for (int j = 0; j < boardModel.getFlNo().length; j++) {
								if (tmpBoardAttachFileModel.getFlNo() == boardModel.getFlNo()[j]) {
									// 첨부파일 등록
									tmpBoardAttachFileModel.setFlNo(flNo++);
									sqlSession.insert("board.insertTBL010103", tmpBoardAttachFileModel);
									tmpBoardAttachFileModel.setTmpStr("INSERT");
									break;
								}
							}
						}
					}
					
					// 이미지
					if ("MI".equals(tmpBoardAttachFileModel.getAttaKnd())) {
						if (imgFile == null) {
							// 첨부파일 등록
							tmpBoardAttachFileModel.setFlNo(flNo++);
							sqlSession.insert("board.insertTBL010103", tmpBoardAttachFileModel);
							tmpBoardAttachFileModel.setTmpStr("INSERT");
						}
					}
					
					// 내용속 첨부파일(무조건 재등록)
					if ("CF".equals(tmpBoardAttachFileModel.getAttaKnd())) {
						tmpBoardAttachFileModel.setFlNo(flNo++);
						sqlSession.insert("board.insertTBL010103", tmpBoardAttachFileModel);
						tmpBoardAttachFileModel.setTmpStr("INSERT");
					}
					
					// 기존첨부파일 삭제
					if (!"INSERT".equals(tmpBoardAttachFileModel.getTmpStr())) {
						FileUploadUtility.doFileDelete(FileUploadUtility.UPLOAD_TYP_BOARD, tmpBoardAttachFileModel.getSaveFilename());
					}
				}
			}
			
			// 신규첨부파일 저장
			if (uploadFileList != null && uploadFileList.size() > 0) {
//				sqlMapClient.startBatch();
				for (int i = 0; i < uploadFileList.size(); i++) {
					fileModel = uploadFileList.get(i);
					boardAttachFileModel.setBrdSeq(boardModel.getBrdSeq());
					boardAttachFileModel.setFlNo(flNo++);
					boardAttachFileModel.setAttaKnd("AF"); // 첨부유형:첨부파일
					boardAttachFileModel.setSaveFilename(fileModel.getSaveFilename());
					boardAttachFileModel.setRealFilename(fileModel.getRealFilename());
					boardAttachFileModel.setFilesize(fileModel.getFilesize());
					boardAttachFileModel.setFileExt(fileModel.getFileExt());
					
					sqlSession.insert("board.insertTBL010103", boardAttachFileModel);
				}
//				sqlMapClient.executeBatch();
			}
			
			// 이미지 정보
			if (imgFile != null) {
//				sqlMapClient.startBatch();
				boardAttachFileModel.setBrdSeq(boardModel.getBrdSeq());
				boardAttachFileModel.setFlNo(flNo++);
				boardAttachFileModel.setAttaKnd("MI"); // 첨부유형:첨부파일
				boardAttachFileModel.setSaveFilename(imgFile.getSaveFilename());
				boardAttachFileModel.setRealFilename(imgFile.getRealFilename());
				boardAttachFileModel.setFilesize(imgFile.getFilesize());
				boardAttachFileModel.setFileExt(imgFile.getFileExt());
				
				sqlSession.insert("board.insertTBL010103", boardAttachFileModel);
//				sqlMapClient.executeBatch();
			}
			
			// 내용에 추가된 이미지
			if (boardModel.getContImageFile() != null && boardModel.getContImageFile().length > 0) {
				for (int i = 0; i < boardModel.getContImageFile().length; i++) {
//					sqlMapClient.startBatch();
					boardAttachFileModel.setBrdSeq(boardModel.getBrdSeq());
					boardAttachFileModel.setFlNo(flNo++);
					boardAttachFileModel.setAttaKnd("CF"); // 첨부유형:내용속첨부파일
					boardAttachFileModel.setSaveFilename(boardModel.getContImageFile()[i]);
					boardAttachFileModel.setRealFilename(boardModel.getContImageFile()[i]);
					boardAttachFileModel.setFilesize(0);
					boardAttachFileModel.setFileExt(FileUploadUtility.getFileExt(boardModel.getContImageFile()[i]).toUpperCase());
					
					sqlSession.insert("board.insertTBL010103", boardAttachFileModel);
//					sqlMapClient.executeBatch();
				}
			}
			
//			sqlMapClient.commitTransaction();
//			sqlMapClient.getCurrentConnection().commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			sqlSession.close();
		}

		return true;
	}
	
	@Override
	public BoardModel selectTBL010102(long srchBrdSeq) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return (BoardModel) sqlSession.selectOne("board.selectTBL010102", srchBrdSeq);
	}
	
	@Override
	public List<BoardAttachFileModel> selectTBL010103(long srchBrdSeq) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession.selectList("board.selectTBL010103", srchBrdSeq);
	}
	
	@Override
	public List<BoardCommentModel> selectTBL010104(long srchBrdSeq) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession.selectList("board.selectTBL010104", srchBrdSeq);
	}
	
	@Override
	public void updateTBL010102Hit(long srchBrdSeq) {

		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
//			sqlMapClient.startTransaction();
			sqlSession.update("board.updateTBL010102Hit", srchBrdSeq);
//			sqlMapClient.commitTransaction();
//			sqlMapClient.getCurrentConnection().commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public void updateTBL010102ReplyCnt(long srchBrdSeq) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
//			sqlMapClient.startTransaction();
			sqlSession.update("board.updateTBL010102ReplyCnt", srchBrdSeq);
//			sqlMapClient.commitTransaction();
//			sqlMapClient.getCurrentConnection().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public void updateTBL010102CommCnt(long srchBrdSeq) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
//			sqlMapClient.startTransaction();
			sqlSession.update("board.updateTBL010102CommCnt", srchBrdSeq);
//			sqlMapClient.commitTransaction();
//			sqlMapClient.getCurrentConnection().commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public void updateTBL010102Del(long srchBrdSeq, String userId) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			valueMap.put("brdSeq", srchBrdSeq);
			valueMap.put("userId", userId);
			
//			sqlMapClient.startTransaction();			
			sqlSession.insert("board.updateTBL010102Del", valueMap);
//			sqlMapClient.commitTransaction();
//			sqlMapClient.getCurrentConnection().commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public void updateTBL010104Del(long srchBrdSeq, int commNo, String userId) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			valueMap.put("brdSeq", srchBrdSeq);
			valueMap.put("commNo", commNo);
			valueMap.put("userId", userId);
			
//			sqlMapClient.startTransaction();			
			sqlSession.insert("board.updateTBL010104Del", valueMap);
//			sqlMapClient.commitTransaction();
//			sqlMapClient.getCurrentConnection().commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

}//End class
