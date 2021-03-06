package com.blueone.category.domain;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * 카테고리정보
 * 	대분류= 01, 중분류=02, 소분류=03
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
	private int ctgOrder;
	private String fromDate;
	private String toDate;
	private String regDate;
	private String lastDate;
	private String modifyUserId;
	private String ctgLargeCode;
	private String ctgLargeName;
	private String ctgMiddleCode;
	private String ctgMiddleName;
	private String ctgSmallCode;
	private String ctgSmallName;
	
	
	private String largeImgPath;
	private MultipartFile largeTpImg;
	
	private String srchFullYn;
	private String chooseYn;	// 선택된중분류일경우 Y
	
	List<CategoryInfo> childList;
	
	
	public String getLargeImgPath() {
		return largeImgPath;
	}
	public void setLargeImgPath(String largeImgPath) {
		this.largeImgPath = largeImgPath;
	}
	public MultipartFile getLargeTpImg() {
		return largeTpImg;
	}
	public void setLargeTpImg(MultipartFile largeTpImg) {
		this.largeTpImg = largeTpImg;
	}
	public List<CategoryInfo> getChildList() {
		return childList;
	}
	public void setChildList(List<CategoryInfo> childList) {
		this.childList = childList;
	}
	public String getSrchFullYn() {
		return srchFullYn;
	}
	public void setSrchFullYn(String srchFullYn) {
		this.srchFullYn = srchFullYn;
	}
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
	public int getCtgOrder() {
		return ctgOrder;
	}
	public void setCtgOrder(int ctgOrder) {
		this.ctgOrder = ctgOrder;
	}
	public String getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	public String getCtgLargeCode() {
		return ctgLargeCode;
	}
	public void setCtgLargeCode(String ctgLargeCode) {
		this.ctgLargeCode = ctgLargeCode;
	}
	public String getCtgLargeName() {
		return ctgLargeName;
	}
	public void setCtgLargeName(String ctgLargeName) {
		this.ctgLargeName = ctgLargeName;
	}
	public String getCtgMiddleCode() {
		return ctgMiddleCode;
	}
	public void setCtgMiddleCode(String ctgMiddleCode) {
		this.ctgMiddleCode = ctgMiddleCode;
	}
	public String getCtgMiddleName() {
		return ctgMiddleName;
	}
	public void setCtgMiddleName(String ctgMiddleName) {
		this.ctgMiddleName = ctgMiddleName;
	}
	public String getCtgSmallCode() {
		return ctgSmallCode;
	}
	public void setCtgSmallCode(String ctgSmallCode) {
		this.ctgSmallCode = ctgSmallCode;
	}
	public String getCtgSmallName() {
		return ctgSmallName;
	}
	public void setCtgSmallName(String ctgSmallName) {
		this.ctgSmallName = ctgSmallName;
	}
	
	
	
}
