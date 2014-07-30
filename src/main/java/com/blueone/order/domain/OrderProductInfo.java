package com.blueone.order.domain;

import java.math.BigDecimal;

public class OrderProductInfo {

	private int idx;
	private String orderNo;	
	private String prdCd;			// 상품코드
	private String prdNm;			// 상품명
	private String prdSmallImg;		// 상품이미지(작은이미지)
	private String prdCtgL;			// 대분류코드
	private String prdCtgM;			// 중분류코드
	private String prdCtgS;			// 소분류코드
	private BigDecimal sellPrice;	// 판매가격
	private int buyCnt;				// 주문수량
	private BigDecimal totalPrice;	// 총금액(판매가격 * 주문수량)
	private String fromDate;		// 적용시작일
	private String toDate;			// 적용종료일
	private String regDate;			// 등록일
	private String lastDate;		// 최종수정일
	private String modiUser;	    // 최종수정자
	private String prdOpColor;		// 옵션-색상
	private String prdOpSize;		// 옵션-크기
	private String prdOption;
	private String oderDelete;
	private String prdModel;
	private String cookieKey;
	
	
	public String getPrdModel() {
		return prdModel;
	}
	public void setPrdModel(String prdModel) {
		this.prdModel = prdModel;
	}
	public String getCookieKey() {
		return cookieKey;
	}
	public void setCookieKey(String cookieKey) {
		this.cookieKey = cookieKey;
	}
	public String getPrdOption() {
		return prdOption;
	}
	public void setPrdOption(String prdOption) {
		this.prdOption = prdOption;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOderDelete() {
		return oderDelete;
	}
	public void setOderDelete(String oderDelete) {
		this.oderDelete = oderDelete;
	}
	public String getPrdOpColor() {
		return prdOpColor;
	}
	public void setPrdOpColor(String prdOpColor) {
		this.prdOpColor = prdOpColor;
	}
	public String getPrdOpSize() {
		return prdOpSize;
	}
	public void setPrdOpSize(String prdOpSize) {
		this.prdOpSize = prdOpSize;
	}
	
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
	public String getPrdSmallImg() {
		return prdSmallImg;
	}
	public void setPrdSmallImg(String prdSmallImg) {
		this.prdSmallImg = prdSmallImg;
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
	public BigDecimal getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}
	public int getBuyCnt() {
		return buyCnt;
	}
	public void setBuyCnt(int buyCnt) {
		this.buyCnt = buyCnt;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
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
	public String getModiUser() {
		return modiUser;
	}
	public void setModiUser(String modiUser) {
		this.modiUser = modiUser;
	}
	
	
}
