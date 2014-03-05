package com.blueone.product.domain;

public class SearchProdInfo {
	private static final int DEFAULT_PAGE_NUM = 1;
	
	private String prodNm;
	private String prdCtgL;
	private String prdCtgM;
	private String prdCtgS;
	private int pageNum;
	
	public String getProdNm() {
		return prodNm;
	}
	public void setProdNm(String prodNm) {
		this.prodNm = prodNm;
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
