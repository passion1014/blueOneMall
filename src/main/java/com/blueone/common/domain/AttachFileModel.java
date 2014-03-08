package com.blueone.common.domain;

import klac.common.Utility;

public class AttachFileModel {
	private int flNo;				// 파일번호
	private String attaKnd;			// 첨부유형[MG01]
	private String saveFilename;	// 파일저장명
	private String realFilename;	// 파일실제명
	private long filesize;			// 파일사이즈
	private String fileExt;			// 파일확장자
	private String iconImage;		// 
	private String tmpStr;
	
	
	public String getTmpStr() {
		return tmpStr;
	}
	public void setTmpStr(String tmpStr) {
		this.tmpStr = tmpStr;
	}
	public String getIconImage() {
		return iconImage;
	}
	public void setIconImage(String iconImage) {
		this.iconImage = iconImage;
	}
	public int getFlNo() {
		return flNo;
	}
	public void setFlNo(int flNo) {
		this.flNo = flNo;
	}
	public String getAttaKnd() {
		return attaKnd;
	}
	public void setAttaKnd(String attaKnd) {
		this.attaKnd = attaKnd;
	}
	public String getSaveFilename() {
		return saveFilename;
	}
	public void setSaveFilename(String saveFilename) {
		this.saveFilename = saveFilename;
	}
	public String getRealFilename() {
		return realFilename;
	}
	public void setRealFilename(String realFilename) {
		this.realFilename = realFilename;
	}
	public long getFilesize() {
		return filesize;
	}
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
	public String getFileExt() {
		return fileExt;
	}
	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}
	public String getViewFilesize() {
		return Utility.getViewFileSize(filesize);
	}
	
}
