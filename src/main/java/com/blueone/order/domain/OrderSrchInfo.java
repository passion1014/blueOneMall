package com.blueone.order.domain;



/**
 * 주문목록조회를 위한 조회정보
 * @author daniel
 */
public class OrderSrchInfo {
	private String srchStdDt;
	private String srchEdDt;
	private int keyfield;
	private String keyword;
	private int startIdx;
	
	
	public int getStartIdx() {
		return startIdx;
	}
	public void setStartIdx(int inx) {
		this.startIdx = inx*15-15;
	}
	
	public int getKeyfield() {
		return keyfield;
	}
	public void setKeyfield(int keyfield) {
		this.keyfield = keyfield;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getSrchStdDt() {
		return srchStdDt;
	}
	public void setSrchStdDt(String srchStdDt) {
		this.srchStdDt = srchStdDt;
	}
	public String getSrchEdDt() {
		return srchEdDt;
	}
	public void setSrchEdDt(String srchEdDt) {
		this.srchEdDt = srchEdDt;
	}
	
}
