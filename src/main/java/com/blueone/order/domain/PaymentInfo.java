package com.blueone.order.domain;

import java.math.BigDecimal;

public class PaymentInfo {
	private int idx;
	private String payMdCd;			// 금종코드 (01:신용카드, 02:실시간이체, 03:핸드폰결제, 04:포인트)
	private BigDecimal payPrice;	// 결제금액
	private String regDate;		// 등록일
	private String lastDate;	// 최종수정일
	private String modifyUserId;// 최종수정자
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getPayMdCd() {
		return payMdCd;
	}
	public void setPayMdCd(String payMdCd) {
		this.payMdCd = payMdCd;
	}
	public BigDecimal getPayPrice() {
		return payPrice;
	}
	public void setPayPrice(BigDecimal payPrice) {
		this.payPrice = payPrice;
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
	public String getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
}
