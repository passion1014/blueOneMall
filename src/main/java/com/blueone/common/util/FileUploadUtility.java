package com.blueone.common.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

import com.blueone.common.domain.AttachFileInfo;
import com.blueone.common.domain.FileInfo;


/**
 * 첨부파일 유틸리티 객체
 * @author kwanwool
 *
 */
public class FileUploadUtility {

	/** 게시판 첨부파일 */
	public final static int UPLOAD_TYP_BOARD = 1;
	public final static int UPLOAD_TYP_BOARD_IMAGE = 2;
	public final static int UPLOAD_TYP_EDUCATE_IMAGE = 3;
	public final static int UPLOAD_TYP_EDUCATE = 4;
	public final static int UPLOAD_TYP_EDITOR_IMAGE = 5;
	public final static int UPLOAD_TYP_SAMPLE = 6;
	public final static int UPLOAD_TYP_PRODUCT_IMAGE = 7;
	//메인화면이미지
	public final static int UPLOAD_TYP_MAINIMG_IMAGE = 8;
//	/tomcat/webapps/ROOT/upload
//	public final static String FILE_UPLOAD_DIR = Configuration.getInstance().getProperty("file.upload.dir");
//	public final static String WEBROOT_DIR = Configuration.getInstance().getProperty("webroot.dir");
//	public final static String FILE_UPLOAD_DIR = "D:/KLACEDU/upload";
	public final static String FILE_UPLOAD_DIR = "src/main/webapp/uploads";

	public final static String WEBROOT_DIR = "D:/KLACEDU/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/LCECH";
	
	
	/**
	 * 인자로 받은 파일정보를 서버에 저장하는 메소드
	 * @param path 저장할 디렉토리
	 * @param saveFileName 저장할 파일명
	 * @param file 저장할 파일정보
	 * @param existFileDel 기존파일 삭제여부
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static boolean doFileUpload(String path, String saveFileName, MultipartFile file, boolean existFileDel) throws FileNotFoundException,IOException{
		//return writeFile(path, saveFileName, file, existFileDel);
		return false;
	}
	
	
	/**
	 * 인자로 받은 파일을 서버에 업로드하는 메소드
	 * @param uploadTyp 업로드유형
	 * @param file 파일정보
	 * @param existFileDel 기존파일삭제여부
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static AttachFileInfo doFileUpload(int uploadTyp, MultipartFile file, boolean existFileDel) throws FileNotFoundException,IOException{
		// 저장할 파일이 있는지 체크
		if (file == null || Utility.isEmpty(file.getOriginalFilename())) return null;
		
		String savePath = null;
		String saveFilename = null;
		String fullSaveFilename = null;
		String fileExt = null;
		AttachFileInfo fileModel = null;
		
		switch (uploadTyp) {
			case UPLOAD_TYP_BOARD :
				fullSaveFilename = DateUtil.getDate("yyyyMM") + "/";
				savePath = FILE_UPLOAD_DIR + "/" + fullSaveFilename;
				break;
			case UPLOAD_TYP_BOARD_IMAGE :
				fullSaveFilename = DateUtil.getDate("yyyyMM") + "/";
				savePath = WEBROOT_DIR + "/userfiles/board/" + fullSaveFilename;
				break;
			case UPLOAD_TYP_EDUCATE_IMAGE :
				fullSaveFilename = DateUtil.getDate("yyyyMM") + "/";
				savePath = WEBROOT_DIR + "/userfiles/educate/" + fullSaveFilename;
				break;
			case UPLOAD_TYP_EDUCATE :
				fullSaveFilename = DateUtil.getDate("yyyyMM") + "/";
				savePath = FILE_UPLOAD_DIR + "/educate/" + fullSaveFilename;
				break;
			case UPLOAD_TYP_EDITOR_IMAGE :
				fullSaveFilename = DateUtil.getDate("yyyyMM") + "/";
				savePath = WEBROOT_DIR + "/userfiles/editor/" + fullSaveFilename;
				break;
			case UPLOAD_TYP_PRODUCT_IMAGE :
				fullSaveFilename = DateUtil.getDate("yyyyMM") + "/";
				savePath = FILE_UPLOAD_DIR + "/" + fullSaveFilename;
				break;
			case UPLOAD_TYP_MAINIMG_IMAGE :
				fullSaveFilename = DateUtil.getDate("yyyyMM") + "/";
				savePath = FILE_UPLOAD_DIR + "/main/" + fullSaveFilename;
				break;	
					
			default : 
				return null;
		}
		
		try {
			
			File dir = new File(savePath);
			
			// 저장할 폴더가 없을경우 생성
			if(!dir.exists()) dir.mkdirs();
			
			// 저장할 파일이 이미 있는지 체크
			fileExt = getFileExt(file.getOriginalFilename());
			saveFilename = Utility.getCurrentDate("yyyyMMddHHmmssSSS") + Math.round(Math.random() * 1000) + "." + fileExt;
			fullSaveFilename = fullSaveFilename + saveFilename;
			File saveFile = new File(savePath + saveFilename);
			if (saveFile.exists()) {
				if (existFileDel) {
					if (!saveFile.delete()) return null;
				} else {
					return null;
				}
			} else {
				
			}
			
			file.transferTo(saveFile);
			
			fileModel = new AttachFileInfo();
			fileModel.setAttSaveFileNm(fullSaveFilename);
			fileModel.setAttRealFileNm(file.getOriginalFilename());
			fileModel.setAttFileSize(file.getSize());
			fileModel.setAttFileExt(fileExt.toUpperCase());
			fileModel.setAttFilePath("/upload/"+fullSaveFilename);
			
		} catch(FileNotFoundException fe) {
			fe.printStackTrace();
		}
		
		return fileModel;
	}
	
	/**
	 * 인자로 받은 파일을 서버에 업로드하는 메소드
	 * @param uploadTyp 업로드유형
	 * @param files 파일정보
	 * @param existFileDel 기존파일삭제여부
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static ArrayList<AttachFileInfo> doFileUpload(int uploadTyp, MultipartFile[] files, boolean existFileDel) throws FileNotFoundException,IOException{
		AttachFileInfo fileModel = null;
		ArrayList<AttachFileInfo> fileList = new ArrayList<AttachFileInfo>();
		if (files != null && files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				fileModel = doFileUpload(uploadTyp, files[i], existFileDel);
				if (fileModel != null) {
					fileList.add(fileModel);
				}
			}
		}
		
		return fileList;
		
	}
	

	/**
	 * 인자로 받은 파일을 삭제하는 메소드
	 * @param path 파일저장 경로
	 * @param filename 파일명
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static boolean doFileDelete(int uploadTyp, String saveFilename) throws FileNotFoundException,IOException{
		if( Utility.isEmpty( saveFilename)) return true;
		
		String savePath = "";
		switch (uploadTyp) {
			case UPLOAD_TYP_BOARD :
				savePath = FILE_UPLOAD_DIR + "/board/";
				break;
			case UPLOAD_TYP_BOARD_IMAGE :
				savePath = WEBROOT_DIR + "/userfiles/board/";
				break;
			case UPLOAD_TYP_EDUCATE_IMAGE :
				savePath = WEBROOT_DIR + "/userfiles/educate/";
				break;
			case UPLOAD_TYP_EDUCATE :
				savePath = FILE_UPLOAD_DIR + "/educate/";
				break;
			case UPLOAD_TYP_EDITOR_IMAGE :
				savePath = WEBROOT_DIR + "/userfiles/editor/";
				break;
			default : 
				return false;
		}
		
		String fullname = savePath + saveFilename ;
		File file = new File( fullname);
		return file.delete();
	}
	
	/**
	 * 인자로 받은 파일명의 확장자를 돌려주는 메소드
	 * @param fileName
	 * @return
	 */
	public static String getFileExt(String fileName) {
		String fileExt = "";
		int point = fileName.lastIndexOf(".");
		if (point != -1) {
			fileExt = fileName.substring(point + 1);
		}
		return fileExt;
	} 

}
