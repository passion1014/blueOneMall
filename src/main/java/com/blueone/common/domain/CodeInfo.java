package com.blueone.common.domain;

public class CodeInfo {
	private String codeKnd1;	// 대분류
	private String codeKnd2;	// 중분류
	private String code;		// 코드
	private String codeNm;		// 코드명
	private String codeDesc;	// 코드설명
	private String remarks;		// 기타
	private String orderSeq;	// 정렬순서
	private String countryCd;   // 국가코드
	private String countryNm;   // 국가명
	private String countryCnt;
	
	public String getCodeKnd1() {
		return codeKnd1;
	}
	public void setCodeKnd1(String codeKnd1) {
		this.codeKnd1 = codeKnd1;
	}
	public String getCodeKnd2() {
		return codeKnd2;
	}
	public void setCodeKnd2(String codeKnd2) {
		this.codeKnd2 = codeKnd2;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCodeNm() {
		return codeNm;
	}
	public void setCodeNm(String codeNm) {
		this.codeNm = codeNm;
	}
	public String getCodeDesc() {
		return codeDesc;
	}
	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getOrderSeq() {
		return orderSeq;
	}
	public void setOrderSeq(String orderSeq) {
		this.orderSeq = orderSeq;
	}
	
	public String getCountryCd() {
		return countryCd;
	}
	public void setCountryCd(String countryCd) {
		this.countryCd = countryCd;
	}
	public String getCountryCnt() {
		return countryCnt;
	}
	public void setCountryCnt(String countryCnt) {
		this.countryCnt = countryCnt;
	}
	public String getCountryNm() {
		return countryNm;
	}
	public void setCountryNm(String countryNm) {
		this.countryNm = countryNm;
	}
}
