package com.blueone.common.domain;

import klac.common.BaseModel;

public class ZipModel extends BaseModel {
	private int zipSeq;			//우편일련번호
	private String zipCd;		//우편번호
	private String zipCity;		//시도
	private String zipGun;		//구군
	private String zipDong;		//면동
	private String zipRi;		//리
	private String zipBunji;	//번지
	private String zipAddr;		//주소
	private String srchAddr;	//검색주소
	private String zipAddr1;	//기본주소
	private String zipAddr2;	//상세주소
	private String chgZipAddr;	//변환주소(지번을 도로명으로 변환주소)
	private String zipRealAddr;	//
	
	public String getZipAddr1() {
		return zipAddr1;
	}
	public void setZipAddr1(String zipAddr1) {
		this.zipAddr1 = zipAddr1;
	}
	public String getZipAddr2() {
		return zipAddr2;
	}
	public void setZipAddr2(String zipAddr2) {
		this.zipAddr2 = zipAddr2;
	}
	public String getChgZipAddr() {
		return chgZipAddr;
	}
	public void setChgZipAddr(String chgZipAddr) {
		this.chgZipAddr = chgZipAddr;
	}
	public String getZipRealAddr() {
		return zipRealAddr;
	}
	public void setZipRealAddr(String zipRealAddr) {
		this.zipRealAddr = zipRealAddr;
	}
	public String getSrchAddr() {
		return srchAddr;
	}
	public void setSrchAddr(String srchAddr) {
		this.srchAddr = srchAddr;
	}
	public int getZipSeq() {
		return zipSeq;
	}
	public void setZipSeq(int zipSeq) {
		this.zipSeq = zipSeq;
	}
	public String getZipCd() {
		return zipCd;
	}
	public void setZipCd(String zipCd) {
		this.zipCd = zipCd;
	}
	public String getZipCity() {
		return zipCity;
	}
	public void setZipCity(String zipCity) {
		this.zipCity = zipCity;
	}
	public String getZipGun() {
		return zipGun;
	}
	public void setZipGun(String zipGun) {
		this.zipGun = zipGun;
	}
	public String getZipDong() {
		return zipDong;
	}
	public void setZipDong(String zipDong) {
		this.zipDong = zipDong;
	}
	public String getZipRi() {
		return zipRi;
	}
	public void setZipRi(String zipRi) {
		this.zipRi = zipRi;
	}
	public String getZipBunji() {
		return zipBunji;
	}
	public void setZipBunji(String zipBunji) {
		this.zipBunji = zipBunji;
	}
	public String getZipAddr() {
		return zipAddr;
	}
	public void setZipAddr(String zipAddr) {
		this.zipAddr = zipAddr;
	}
	public String getZipCdNm() {
		String rtnValue = zipCd;
		if (zipCd != null) {
			rtnValue = zipCd.substring(0, 3) + "-" + zipCd.substring(3);
		}
		return rtnValue;
	}
}
