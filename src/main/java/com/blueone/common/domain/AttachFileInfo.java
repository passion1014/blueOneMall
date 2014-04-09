package com.blueone.common.domain;

import org.springframework.web.multipart.MultipartFile;

import com.blueone.common.util.Utility;


public class AttachFileInfo {
	private String codeType;
	private String codeKey;
	private String imgType;
	private String saveFileName;
	private String realFileName;
	private String filePath;
	private long fileSize;
	private String fileExt;

	private MultipartFile uploadFile;		// 첨부파일


//	private int flNo;				// 파일번호
//	private String attaKnd;			// 첨부유형[MG01]
//	private String saveFilename;	// 파일저장명
//	private String realFilename;	// 파일실제명
//	private long filesize;			// 파일사이즈
//	private String fileExt;			// 파일확장자
//	private String iconImage;		// 
//	private String tmpStr;
	
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getCodeType() {
		return codeType;
	}
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	public String getCodeKey() {
		return codeKey;
	}
	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}
	public String getImgType() {
		return imgType;
	}
	public void setImgType(String imgType) {
		this.imgType = imgType;
	}
	public String getSaveFileName() {
		return saveFileName;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	public String getRealFileName() {
		return realFileName;
	}
	public void setRealFileName(String realFileName) {
		this.realFileName = realFileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileExt() {
		return fileExt;
	}
	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}
	
//	public String getTmpStr() {
//		return tmpStr;
//	}
//	public void setTmpStr(String tmpStr) {
//		this.tmpStr = tmpStr;
//	}
//	public String getIconImage() {
//		return iconImage;
//	}
//	public void setIconImage(String iconImage) {
//		this.iconImage = iconImage;
//	}
//	public int getFlNo() {
//		return flNo;
//	}
//	public void setFlNo(int flNo) {
//		this.flNo = flNo;
//	}
//	public String getAttaKnd() {
//		return attaKnd;
//	}
//	public void setAttaKnd(String attaKnd) {
//		this.attaKnd = attaKnd;
//	}
//	public String getSaveFilename() {
//		return saveFilename;
//	}
//	public void setSaveFilename(String saveFilename) {
//		this.saveFilename = saveFilename;
//	}
//	public String getRealFilename() {
//		return realFilename;
//	}
//	public void setRealFilename(String realFilename) {
//		this.realFilename = realFilename;
//	}
//	public long getFilesize() {
//		return filesize;
//	}
//	public void setFilesize(long filesize) {
//		this.filesize = filesize;
//	}
//	public String getFileExt() {
//		return fileExt;
//	}
//	public void setFileExt(String fileExt) {
//		this.fileExt = fileExt;
//	}
//	public String getViewFilesize() {
//		return Utility.getViewFileSize(filesize);
//	}
	
}
