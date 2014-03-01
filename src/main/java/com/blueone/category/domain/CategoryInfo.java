package com.blueone.category.domain;

/**
 * ī�װ� ����
 * 	��з�Ÿ��= 01, �ߺз�=02, �Һз�=03
 * @author daniel
 *
 */
public class CategoryInfo {
	
	private int idx;
	private String ctgPCode;
	private String ctgCodeType;
	private String ctgCode;
	private String ctgName;
	private String ctgDesc;
	private String fromDate;
	private String toDate;
	private String regDate;
	private String lastDate;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getCtgCodeType() {
		return ctgCodeType;
	}
	public void setCtgCodeType(String ctgCodeType) {
		this.ctgCodeType = ctgCodeType;
	}
	public String getCtgCode() {
		return ctgCode;
	}
	public void setCtgCode(String ctgCode) {
		this.ctgCode = ctgCode;
	}
	public String getCtgName() {
		return ctgName;
	}
	public void setCtgName(String ctgName) {
		this.ctgName = ctgName;
	}
	public String getCtgDesc() {
		return ctgDesc;
	}
	public void setCtgDesc(String ctgDesc) {
		this.ctgDesc = ctgDesc;
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
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getLastDate() {
		return lastDate;
	}
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}
	public String getCtgPCode() {
		return ctgPCode;
	}
	public void setCtgPCode(String ctgPCode) {
		this.ctgPCode = ctgPCode;
	}
	
}
