package com.blueone.common.service;

import java.util.ArrayList;

import javax.servlet.ServletContext;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blueone.board.domain.BoardAttachFileInfo;
import com.blueone.common.domain.AttachFileInfo;
import com.blueone.common.domain.FileInfo;
import com.blueone.common.util.FileUploadUtility;

@Service
public class FileMngService implements IFileMngService {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	/* 
	 * 파일 업로드 서비스
	 */
	public AttachFileInfo fileUpload(AttachFileInfo attachFileInfo) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			// 첨부파일 업로드
			AttachFileInfo uploadFile = FileUploadUtility.doFileUpload(FileUploadUtility.UPLOAD_TYP_BOARD, attachFileInfo.getUploadFile(), false);
			
			if (uploadFile != null) {
				attachFileInfo.setAttSaveFileNm(uploadFile.getAttSaveFileNm());
				attachFileInfo.setAttRealFileNm(uploadFile.getAttRealFileNm());
				attachFileInfo.setAttFileSize(uploadFile.getAttFileSize());
				attachFileInfo.setAttFileExt(uploadFile.getAttFileExt());
				
//				boardAttachFileInfo.setBrdSeq(boardModel.getBrdSeq());
//				boardAttachFileInfo.setFlNo(flNo++);
//				boardAttachFileInfo.setAttaKnd("MI"); // 첨부유형:첨부파일
//				
//				sqlSession.insert("board.insertBOM_ATTACHFILE_TB", boardAttachFileInfo);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
		
		return attachFileInfo;
	}
}
