package com.blueone.product.domain;

public class ProductInfo {
	private int idx;
	private String prdCd;		// 상품코드
	private String prdNm;		// 상품명
	private String prdDesc;		// 상품상세내용
	private String prdDescHtml;	// 상품상세내용HTML
	private String prdCtgL;		// 대분류코드
	private String prdCtgM;		// 중분류코드
	private String prdCtgS;		// 소분류코드
	private String fromDate;	// 적용시작일
	private String toDate;		// 적용종료일
	private String regDate;		// 등록일
	private String lastDate;	// 최종수정일
	private String modifyUserId;// 최종수정자
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getPrdCd() {
		return prdCd;
	}
	public void setPrdCd(String prdCd) {
		this.prdCd = prdCd;
	}
	public String getPrdNm() {
		return prdNm;
	}
	public void setPrdNm(String prdNm) {
		this.prdNm = prdNm;
	}
	public String getPrdDesc() {
		return prdDesc;
	}
	public void setPrdDesc(String prdDesc) {
		this.prdDesc = prdDesc;
	}
	public String getPrdDescHtml() {
		return prdDescHtml;
	}
	public void setPrdDescHtml(String prdDescHtml) {
		this.prdDescHtml = prdDescHtml;
	}
	public String getPrdCtgL() {
		return prdCtgL;
	}
	public void setPrdCtgL(String prdCtgL) {
		this.prdCtgL = prdCtgL;
	}
	public String getPrdCtgM() {
		return prdCtgM;
	}
	public void setPrdCtgM(String prdCtgM) {
		this.prdCtgM = prdCtgM;
	}
	public String getPrdCtgS() {
		return prdCtgS;
	}
	public void setPrdCtgS(String prdCtgS) {
		this.prdCtgS = prdCtgS;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getLastDate() {
		return lastDate;
	}
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}
	public String getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
}
