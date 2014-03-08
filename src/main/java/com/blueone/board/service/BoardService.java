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
		
		return sqlMapClientTemplate.queryForList("board.getBoardLastList", valueMap);
	}
	
	@Override
	public List<BoardModel> getBrdTypNoticeList(BoardSrchModel boardSrchModel) {
		BoardModel board = null;
		List<BoardModel> boards = sqlMapClientTemplate.queryForList("board.getBrdTypNoticeList", boardSrchModel);
		if (boards != null && boards.size() > 0) {
			for (int i = 0; i < boards.size(); i++) {
				board = boards.get(i);
				board.setAttachFiles(selectTBL010103(board.getBrdSeq()));
			}
		}
		
		return boards;
	}
	
	@Override
	public boolean insertBoard(BoardModel boardModel) {
		
		FileModel imgFile = null;
		ArrayList<FileModel> uploadFileList = null;
		
		try {
			
			// 이미지 파일업로드
			imgFile = FileUploadUtility.doFileUpload(FileUploadUtility.UPLOAD_TYP_BOARD_IMAGE, boardModel.getImgFile(), false);
			// 첨부파일 업로드
			uploadFileList = FileUploadUtility.doFileUpload(FileUploadUtility.UPLOAD_TYP_BOARD, boardModel.getUploadFile(), false);
			
			// 게시물추가(트렌젝션 처리)
			sqlMapClient.startTransaction();
			
			Object brdSeq = sqlMapClient.insert("board.insertTBL010102", boardModel);
			if (brdSeq == null) return false;
			
			// 답변갯수 업데이트
			if (boardModel.getDepth() > 0 && boardModel.getRootSeq() > 0) {
				sqlMapClient.update("board.updateTBL010102ReplyCnt", boardModel.getRootSeq());
			}
			
			// 첨부파일정보 추가
			int flNo = 1;
			FileModel fileModel = null;
			BoardAttachFileModel boardAttachFileModel = new BoardAttachFileModel();
			
			if (uploadFileList != null && uploadFileList.size() > 0) {
				sqlMapClient.startBatch();
				for (int i = 0; i < uploadFileList.size(); i++) {
					fileModel = uploadFileList.get(i);
					boardAttachFileModel.setBrdSeq((Long)brdSeq);
					boardAttachFileModel.setFlNo(flNo++);
					boardAttachFileModel.setAttaKnd("AF"); // 첨부유형:첨부파일
					boardAttachFileModel.setSaveFilename(fileModel.getSaveFilename());
					boardAttachFileModel.setRealFilename(fileModel.getRealFilename());
					boardAttachFileModel.setFilesize(fileModel.getFilesize());
					boardAttachFileModel.setFileExt(fileModel.getFileExt());
					
					sqlMapClient.insert("board.insertTBL010103", boardAttachFileModel);
				}
				sqlMapClient.executeBatch();
			}
			
			// 이미지 정보
			if (imgFile != null) {
				sqlMapClient.startBatch();
				boardAttachFileModel.setBrdSeq((Long)brdSeq);
				boardAttachFileModel.setFlNo(flNo++);
				boardAttachFileModel.setAttaKnd("MI"); // 첨부유형:첨부파일
				boardAttachFileModel.setSaveFilename(imgFile.getSaveFilename());
				boardAttachFileModel.setRealFilename(imgFile.getRealFilename());
				boardAttachFileModel.setFilesize(imgFile.getFilesize());
				boardAttachFileModel.setFileExt(imgFile.getFileExt());
				
				sqlMapClient.insert("board.insertTBL010103", boardAttachFileModel);
				sqlMapClient.executeBatch();
			}
			
			// 내용에 추가된 이미지
			if (boardModel.getContImageFile() != null && boardModel.getContImageFile().length > 0) {
				for (int i = 0; i < boardModel.getContImageFile().length; i++) {
					sqlMapClient.startBatch();
					boardAttachFileModel.setBrdSeq((Long)brdSeq);
					boardAttachFileModel.setFlNo(flNo++);
					boardAttachFileModel.setAttaKnd("CF"); // 첨부유형:내용속첨부파일
					boardAttachFileModel.setSaveFilename(boardModel.getContImageFile()[i]);
					boardAttachFileModel.setRealFilename(boardModel.getContImageFile()[i]);
					boardAttachFileModel.setFilesize(0);
					boardAttachFileModel.setFileExt(FileUploadUtility.getFileExt(boardModel.getContImageFile()[i]).toUpperCase());
					
					sqlMapClient.insert("board.insertTBL010103", boardAttachFileModel);
					sqlMapClient.executeBatch();
				}
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
	public boolean insertTBL010104(BoardCommentModel boardCommentModel) {
		
		try {
			
			sqlMapClient.startTransaction();
			sqlMapClient.insert("board.insertTBL010104", boardCommentModel);
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
	public boolean updateTBL010104(BoardCommentModel boardCommentModel) {
		
		try {
			
			sqlMapClient.startTransaction();
			sqlMapClient.update("board.updateTBL010104", boardCommentModel);
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
	public boolean updateBoard(BoardModel boardModel) {
		
		FileModel imgFile = null;
		ArrayList<FileModel> uploadFileList = null;
		
		try {

			// 기존 첨부파일 목록
			List<BoardAttachFileModel> attaFileList = selectTBL010103(boardModel.getBrdSeq());
			
			// 이미지 파일업로드
			imgFile = FileUploadUtility.doFileUpload(FileUploadUtility.UPLOAD_TYP_BOARD_IMAGE, boardModel.getImgFile(), false);
			// 신규 업로드파일
			uploadFileList = FileUploadUtility.doFileUpload(FileUploadUtility.UPLOAD_TYP_BOARD, boardModel.getUploadFile(), false);
			
			// 게시물추가(트렌젝션 처리)
			sqlMapClient.startTransaction();
			
			// 업데이트
			sqlMapClient.update("board.updateTBL010102", boardModel);
			
			// 첨부파일정보 추가
			int flNo = 1;
			FileModel fileModel = null;
			BoardAttachFileModel boardAttachFileModel = new BoardAttachFileModel();
			BoardAttachFileModel tmpBoardAttachFileModel = new BoardAttachFileModel();
			
			// 기존첨부파일 모두삭제
			sqlMapClient.delete("board.deleteTBL010103", boardModel.getBrdSeq());
			
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
									sqlMapClient.insert("board.insertTBL010103", tmpBoardAttachFileModel);
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
							sqlMapClient.insert("board.insertTBL010103", tmpBoardAttachFileModel);
							tmpBoardAttachFileModel.setTmpStr("INSERT");
						}
					}
					
					// 내용속 첨부파일(무조건 재등록)
					if ("CF".equals(tmpBoardAttachFileModel.getAttaKnd())) {
						tmpBoardAttachFileModel.setFlNo(flNo++);
						sqlMapClient.insert("board.insertTBL010103", tmpBoardAttachFileModel);
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
				sqlMapClient.startBatch();
				for (int i = 0; i < uploadFileList.size(); i++) {
					fileModel = uploadFileList.get(i);
					boardAttachFileModel.setBrdSeq(boardModel.getBrdSeq());
					boardAttachFileModel.setFlNo(flNo++);
					boardAttachFileModel.setAttaKnd("AF"); // 첨부유형:첨부파일
					boardAttachFileModel.setSaveFilename(fileModel.getSaveFilename());
					boardAttachFileModel.setRealFilename(fileModel.getRealFilename());
					boardAttachFileModel.setFilesize(fileModel.getFilesize());
					boardAttachFileModel.setFileExt(fileModel.getFileExt());
					
					sqlMapClient.insert("board.insertTBL010103", boardAttachFileModel);
				}
				sqlMapClient.executeBatch();
			}
			
			// 이미지 정보
			if (imgFile != null) {
				sqlMapClient.startBatch();
				boardAttachFileModel.setBrdSeq(boardModel.getBrdSeq());
				boardAttachFileModel.setFlNo(flNo++);
				boardAttachFileModel.setAttaKnd("MI"); // 첨부유형:첨부파일
				boardAttachFileModel.setSaveFilename(imgFile.getSaveFilename());
				boardAttachFileModel.setRealFilename(imgFile.getRealFilename());
				boardAttachFileModel.setFilesize(imgFile.getFilesize());
				boardAttachFileModel.setFileExt(imgFile.getFileExt());
				
				sqlMapClient.insert("board.insertTBL010103", boardAttachFileModel);
				sqlMapClient.executeBatch();
			}
			
			// 내용에 추가된 이미지
			if (boardModel.getContImageFile() != null && boardModel.getContImageFile().length > 0) {
				for (int i = 0; i < boardModel.getContImageFile().length; i++) {
					sqlMapClient.startBatch();
					boardAttachFileModel.setBrdSeq(boardModel.getBrdSeq());
					boardAttachFileModel.setFlNo(flNo++);
					boardAttachFileModel.setAttaKnd("CF"); // 첨부유형:내용속첨부파일
					boardAttachFileModel.setSaveFilename(boardModel.getContImageFile()[i]);
					boardAttachFileModel.setRealFilename(boardModel.getContImageFile()[i]);
					boardAttachFileModel.setFilesize(0);
					boardAttachFileModel.setFileExt(FileUploadUtility.getFileExt(boardModel.getContImageFile()[i]).toUpperCase());
					
					sqlMapClient.insert("board.insertTBL010103", boardAttachFileModel);
					sqlMapClient.executeBatch();
				}
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
	public BoardModel selectTBL010102(long srchBrdSeq) {
		return (BoardModel) sqlMapClientTemplate.queryForObject("board.selectTBL010102", srchBrdSeq);
	}
	
	@Override
	public List<BoardAttachFileModel> selectTBL010103(long srchBrdSeq) {
		return sqlMapClientTemplate.queryForList("board.selectTBL010103", srchBrdSeq);
	}
	
	@Override
	public List<BoardCommentModel> selectTBL010104(long srchBrdSeq) {
		return sqlMapClientTemplate.queryForList("board.selectTBL010104", srchBrdSeq);
	}
	
	@Override
	public void updateTBL010102Hit(long srchBrdSeq) {
		try {
			
			sqlMapClient.startTransaction();
			sqlMapClient.update("board.updateTBL010102Hit", srchBrdSeq);
			sqlMapClient.commitTransaction();
			sqlMapClient.getCurrentConnection().commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try { if (sqlMapClient != null) sqlMapClient.endTransaction(); } catch (Exception e) {};
		}
	}
	
	@Override
	public void updateTBL010102ReplyCnt(long srchBrdSeq) {
		try {
			
			sqlMapClient.startTransaction();
			sqlMapClient.update("board.updateTBL010102ReplyCnt", srchBrdSeq);
			sqlMapClient.commitTransaction();
			sqlMapClient.getCurrentConnection().commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try { if (sqlMapClient != null) sqlMapClient.endTransaction(); } catch (Exception e) {};
		}
	}
	
	@Override
	public void updateTBL010102CommCnt(long srchBrdSeq) {
		try {
			
			sqlMapClient.startTransaction();
			sqlMapClient.update("board.updateTBL010102CommCnt", srchBrdSeq);
			sqlMapClient.commitTransaction();
			sqlMapClient.getCurrentConnection().commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try { if (sqlMapClient != null) sqlMapClient.endTransaction(); } catch (Exception e) {};
		}
	}
	
	@Override
	public void updateTBL010102Del(long srchBrdSeq, String userId) {
		try {
			
			valueMap.put("brdSeq", srchBrdSeq);
			valueMap.put("userId", userId);
			sqlMapClient.startTransaction();			
			sqlMapClient.insert("board.updateTBL010102Del", valueMap);
			sqlMapClient.commitTransaction();
			sqlMapClient.getCurrentConnection().commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try { if (sqlMapClient != null) sqlMapClient.endTransaction(); } catch (Exception e) {};
		}
	}
	
	@Override
	public void updateTBL010104Del(long srchBrdSeq, int commNo, String userId) {
		try {
			
			valueMap.put("brdSeq", srchBrdSeq);
			valueMap.put("commNo", commNo);
			valueMap.put("userId", userId);
			sqlMapClient.startTransaction();			
			sqlMapClient.insert("board.updateTBL010104Del", valueMap);
			sqlMapClient.commitTransaction();
			sqlMapClient.getCurrentConnection().commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try { if (sqlMapClient != null) sqlMapClient.endTransaction(); } catch (Exception e) {};
		}
	}

}//End class
