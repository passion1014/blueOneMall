package com.blueone.product.domain;

import org.springframework.web.multipart.MultipartFile;

public class AttachFileInfo {
	
	
	private int idx;
	
	private MultipartFile imgInfo;
	
	private String attCdType;
	private String attCdKey;
	private String attImgType;
	private String attSaveFileNm;
	private String attRealFileNm=imgInfo.getOriginalFilename();
	private String attFilePath;
	private int attFileSize=(int)imgInfo.getSize();
	private String attFileExt=imgInfo.getContentType();
	
	
	
	public MultipartFile getImgInfo() {
		return imgInfo;
	}
	public void setImgInfo(MultipartFile imgInfo) {
		this.imgInfo = imgInfo;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
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
	public int getAttFileSize() {
		return attFileSize;
	}
	public void setAttFileSize(int attFileSize) {
		this.attFileSize = attFileSize;
	}
	public String getAttFileExt() {
		return attFileExt;
	}
	public void setAttFileExt(String attFileExt) {
		this.attFileExt = attFileExt;
	}
	  
	
	

}
