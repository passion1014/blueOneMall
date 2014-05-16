package com.blueone.product.domain;

public class SearchProdInfo {
	private static final int DEFAULT_PAGE_NUM = 1;
	
	private String prdNm;
	private String prdCtgL;
	private String prdCtgM;
	private String prdCtgS;
	private int pageNum;
	private String schWord;
	private String schType;
	
	public String getSchType() {
		return schType;
	}
	public void setSchType(String schType) {
		this.schType = schType;
	}
	public String getSchWord() {
		return schWord;
	}
	public void setSchWord(String schWord) {
		this.schWord = schWord;
	}
	public String getPrdNm() {
		return prdNm;
	}
	public void setPrdNm(String prodNm) {
		this.prdNm = prodNm;
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
	public int getPageNum() {
		if (this.pageNum == 0) this.pageNum = DEFAULT_PAGE_NUM;
		
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

}
