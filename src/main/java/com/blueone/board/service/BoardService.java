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
import com.blueone.common.domain.FileInfo;
import com.blueone.common.util.FileUploadUtility;

@Service
public class BoardService implements IBoardService {
	private HashMap<String, Object> valueMap = new HashMap<String, Object>();
		
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public List<BoardInfo> getBrdTypBoardList(BoardSrchInfo boardSrchModel) {
		List<BoardInfo> boards = new ArrayList<BoardInfo>();
		BoardInfo board = null;
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			boards = sqlSession.selectList("board.getBrdTypBoardList", boardSrchModel);
			
			if (boards != null && boards.size() > 0) {
				for (int i = 0; i < boards.size(); i++) {
					board = boards.get(i);
					board.setAttachFiles(selectBOM_ATTACHFILE_TB(board.getBrdSeq()));
				}
			}
			
		} finally {
			sqlSession.close();
		}

		
		return boards;
	}
	
	@Override
	public int getBrdTypTotalCount(BoardSrchInfo boardSrchModel) {
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
	public List<BoardInfo> getBoardLastList(int[] brdTyps, int size) {
		valueMap.put("brdTyps", brdTyps);
		valueMap.put("size", size);
		
		List<BoardInfo> boardInfoList = new ArrayList<BoardInfo>();
		
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
	public List<BoardInfo> getBrdTypNoticeList(BoardSrchInfo boardSrchModel) {
		List<BoardInfo> boardList = new ArrayList<BoardInfo>();
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			boardList = sqlSession.selectList("board.getBrdTypNoticeList", boardSrchModel);
			if (boardList != null && boardList.size() > 0) {
				for (int i = 0; i < boardList.size(); i++) {
					BoardInfo board = boardList.get(i);
					board.setAttachFiles(selectBOM_ATTACHFILE_TB(board.getBrdSeq()));
				}
			}
		} finally {
			sqlSession.close();
		}
		
		return boardList;
	}
	
	@Override
	public boolean insertBoard(BoardInfo boardModel) {
		
		FileInfo imgFile = null;
		ArrayList<FileInfo> uploadFileList = null;
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			
			// 이미지 파일업로드
			imgFile = FileUploadUtility.doFileUpload(FileUploadUtility.UPLOAD_TYP_BOARD_IMAGE, boardModel.getImgFile(), false);
			// 첨부파일 업로드
			uploadFileList = FileUploadUtility.doFileUpload(FileUploadUtility.UPLOAD_TYP_BOARD, boardModel.getUploadFile(), false);
			
			
//			Object brdSeq = sqlMapClient.insert("board.insertTBL010102", boardModel);
			sqlSession.insert("board.insertBOM_BOARD_TB", boardModel);
			if (boardModel.getBrdSeq() == 0) return false;
			
			// 답변갯수 업데이트
			if (boardModel.getDepth() > 0 && boardModel.getRootSeq() > 0) {
				sqlSession.update("board.updateBOM_BOARD_TBReplyCnt", boardModel.getRootSeq());
			}
			
			// 첨부파일정보 추가
			int flNo = 1;
			FileInfo fileModel = null;
			BoardAttachFileInfo boardAttachFileModel = new BoardAttachFileInfo();
			
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
					
					sqlSession.insert("board.insertBOM_ATTACHFILE_TB", boardAttachFileModel);
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
				
				sqlSession.insert("board.insertBOM_ATTACHFILE_TB", boardAttachFileModel);
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
					
					sqlSession.insert("board.insertBOM_ATTACHFILE_TB", boardAttachFileModel);
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
	public boolean insertBOM_BOARD_CMT_TB(BoardCommentInfo boardCommentModel) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
//			sqlMapClient.startTransaction();
			sqlSession.insert("board.insertBOM_BOARD_CMT_TB", boardCommentModel);
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
	public boolean updateBOM_BOARD_CMT_TB(BoardCommentInfo boardCommentModel) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
//			sqlMapClient.startTransaction();
			sqlSession.update("board.updateBOM_BOARD_CMT_TB", boardCommentModel);
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
	public boolean updateBoard(BoardInfo boardModel) {
		
		FileInfo imgFile = null;
		ArrayList<FileInfo> uploadFileList = null;
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {

			// 기존 첨부파일 목록
			List<BoardAttachFileInfo> attaFileList = selectBOM_ATTACHFILE_TB(boardModel.getBrdSeq());
			
			// 이미지 파일업로드
			imgFile = FileUploadUtility.doFileUpload(FileUploadUtility.UPLOAD_TYP_BOARD_IMAGE, boardModel.getImgFile(), false);
			// 신규 업로드파일
			uploadFileList = FileUploadUtility.doFileUpload(FileUploadUtility.UPLOAD_TYP_BOARD, boardModel.getUploadFile(), false);
			
			// 게시물추가(트렌젝션 처리)
//			sqlMapClient.startTransaction();
			
			// 업데이트
			sqlSession.update("board.updateBOM_BOARD_TB", boardModel);
			
			// 첨부파일정보 추가
			int flNo = 1;
			FileInfo fileModel = null;
			BoardAttachFileInfo boardAttachFileModel = new BoardAttachFileInfo();
			BoardAttachFileInfo tmpBoardAttachFileModel = new BoardAttachFileInfo();
			
			// 기존첨부파일 모두삭제
			sqlSession.delete("board.deleteBOM_ATTACHFILE_TB", boardModel.getBrdSeq());
			
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
									sqlSession.insert("board.insertBOM_ATTACHFILE_TB", tmpBoardAttachFileModel);
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
							sqlSession.insert("board.insertBOM_ATTACHFILE_TB", tmpBoardAttachFileModel);
							tmpBoardAttachFileModel.setTmpStr("INSERT");
						}
					}
					
					// 내용속 첨부파일(무조건 재등록)
					if ("CF".equals(tmpBoardAttachFileModel.getAttaKnd())) {
						tmpBoardAttachFileModel.setFlNo(flNo++);
						sqlSession.insert("board.insertBOM_ATTACHFILE_TB", tmpBoardAttachFileModel);
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
					
					sqlSession.insert("board.insertBOM_ATTACHFILE_TB", boardAttachFileModel);
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
				
				sqlSession.insert("board.insertBOM_ATTACHFILE_TB", boardAttachFileModel);
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
					
					sqlSession.insert("board.insertBOM_ATTACHFILE_TB", boardAttachFileModel);
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
	public BoardInfo selectBOM_BOARD_TB(long srchBrdSeq) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return (BoardInfo) sqlSession.selectOne("board.selectBOM_BOARD_TB", srchBrdSeq);
	}
	
	@Override
	public List<BoardAttachFileInfo> selectBOM_ATTACHFILE_TB(long srchBrdSeq) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession.selectList("board.selectBOM_ATTACHFILE_TB", srchBrdSeq);
	}
	
	@Override
	public List<BoardCommentInfo> selectBOM_BOARD_CMT_TB(long srchBrdSeq) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession.selectList("board.selectBOM_BOARD_CMT_TB", srchBrdSeq);
	}
	
	@Override
	public void updateBOM_BOARD_TBHit(long srchBrdSeq) {

		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
//			sqlMapClient.startTransaction();
			sqlSession.update("board.updateBOM_BOARD_TBHit", srchBrdSeq);
//			sqlMapClient.commitTransaction();
//			sqlMapClient.getCurrentConnection().commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public void updateBOM_BOARD_TBReplyCnt(long srchBrdSeq) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
//			sqlMapClient.startTransaction();
			sqlSession.update("board.updateBOM_BOARD_TBReplyCnt", srchBrdSeq);
//			sqlMapClient.commitTransaction();
//			sqlMapClient.getCurrentConnection().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public void updateBOM_BOARD_TBCommCnt(long srchBrdSeq) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
//			sqlMapClient.startTransaction();
			sqlSession.update("board.updateBOM_BOARD_TBCommCnt", srchBrdSeq);
//			sqlMapClient.commitTransaction();
//			sqlMapClient.getCurrentConnection().commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public void updateBOM_BOARD_TBDel(long srchBrdSeq, String userId) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			valueMap.put("brdSeq", srchBrdSeq);
			valueMap.put("userId", userId);
			
//			sqlMapClient.startTransaction();			
			sqlSession.insert("board.updateBOM_BOARD_TBDel", valueMap);
//			sqlMapClient.commitTransaction();
//			sqlMapClient.getCurrentConnection().commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public void updateBOM_BOARD_CMT_TBDel(long srchBrdSeq, int commNo, String userId) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			valueMap.put("brdSeq", srchBrdSeq);
			valueMap.put("commNo", commNo);
			valueMap.put("userId", userId);
			
//			sqlMapClient.startTransaction();			
			sqlSession.insert("board.updateBOM_BOARD_CMT_TBDel", valueMap);
//			sqlMapClient.commitTransaction();
//			sqlMapClient.getCurrentConnection().commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

}//End class
