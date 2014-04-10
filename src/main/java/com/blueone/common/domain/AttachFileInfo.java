package com.blueone.common.domain;

import org.springframework.web.multipart.MultipartFile;

import com.blueone.common.util.Utility;


public class AttachFileInfo {
	private int idx ;
	private String attCdType;
	private String attCdKey;
	private String attImgType;
	private String attSaveFileNm;
	private String attRealFileNm;
	private String attFilePath;
	private long attFileSize;
	private String attFileExt;
	   
	private MultipartFile uploadFile;	
	// 첨부파일
	public AttachFileInfo(){
	
		
	}
	
	public AttachFileInfo(MultipartFile imgInfo){
		uploadFile=imgInfo;
		
		attSaveFileNm=imgInfo.getOriginalFilename();
		attFileSize=imgInfo.getSize();
		attFileExt=imgInfo.getContentType();
		
	}
	
	
	public int getIdx() {
		return idx;
	}


	public void setIdx(int idx) {
		this.idx = idx;
	}


	public long getAttFileSize() {
		return attFileSize;
	}


	public void setAttFileSize(long attFileSize) {
		this.attFileSize = attFileSize;
	}


	public String getAttCdType() {
		return attCdType;
	}

	public void setAttCdType(String attCdType) {
		this.attCdType = attCdType;
	}

	public String getAttCdKey() {
		return attCdKey;
	}

	public void setAttCdKey(String attCdKey) {
		this.attCdKey = attCdKey;
	}

	public String getAttImgType() {
		return attImgType;
	}

	public void setAttImgType(String attImgType) {
		this.attImgType = attImgType;
	}

	public String getAttSaveFileNm() {
		return attSaveFileNm;
	}

	public void setAttSaveFileNm(String attSaveFileNm) {
		this.attSaveFileNm = attSaveFileNm;
	}

	public String getAttRealFileNm() {
		return attRealFileNm;
	}

	public void setAttRealFileNm(String attRealFileNm) {
		this.attRealFileNm = attRealFileNm;
	}

	public String getAttFilePath() {
		return attFilePath;
	}

	public void setAttFilePath(String attFilePath) {
		this.attFilePath = attFilePath;
	}

	

	public String getAttFileExt() {
		return attFileExt;
	}

	public void setAttFileExt(String attFileExt) {
		this.attFileExt = attFileExt;
	}

	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}


//	private int flNo;				// 파일번호
//	private String attaKnd;			// 첨부유형[MG01]
//	private String saveFilename;	// 파일저장명
//	private String realFilename;	// 파일실제명
//	private long filesize;			// 파일사이즈
//	private String fileExt;			// 파일확장자
//	private String iconImage;		// 
//	private String tmpStr;
	

	
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
