package com.blueone.order.domain;

import java.math.BigDecimal;

public class PaymentInfo {
	private int idx;
	private String payMdCd;			// 금종코드
	private String orderNo;		// 등록일
	private int orderNoSeq;		// 등록일
	private String payDate;		// 등록일
	private int payPoint; // 포인트 사용금액
	private BigDecimal payPrice;	// 결제금액
	private String regDate;		// 등록일
	private String lastDate;	// 최종수정일
	private String modifyUserId;// 최종수정자
	private String pymtMemo;
	
	
	
	public String getPymtMemo() {
		return pymtMemo;
	}
	public void setPymtMemo(String pymtMemo) {
		this.pymtMemo = pymtMemo;
	}
	public int getPayPoint() {
		return payPoint;
	}
	public void setPayPoint(int payPoint) {
		this.payPoint = payPoint;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public int getOrderNoSeq() {
		return orderNoSeq;
	}
	public void setOrderNoSeq(int orderNoSeq) {
		this.orderNoSeq = orderNoSeq;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
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
