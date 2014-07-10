package com.blueone.common.domain;

/**
 * 정산처리를 위한 VO
 * @author daniel
 */
public class HMallProcAdjustmentInfo {
	
	private String orderNo;			// 주문번호
	private String itemCd;			// 상품번호
	private String orderGb;			// 주문구분
	private String orderDm;			// 주문일자
	private String shopNo;			// 상점번호
	private String shopEventNo;		// 행사번호
	private String memNo;			// 고객번호
	private String taxGb;			// 과세여부
	private String salePrice;		// 판매금액
	private String pointAmt;		// 기본금
	private String etcAmt;			// 기타결제
	private String mediaCd;			// 매체구분
	private String deliAmt;			// 배송비
	private String itemNm;			// 상품명
	private String itemPrice;		// 단품 가격
	private String orderQty;		// 주문수량
	private String dcPrice;			// 할인금액
	private String returnCode;		// 리턴코드
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getItemCd() {
		return itemCd;
	}
	public void setItemCd(String itemCd) {
		this.itemCd = itemCd;
	}
	public String getOrderGb() {
		return orderGb;
	}
	public void setOrderGb(String orderGb) {
		this.orderGb = orderGb;
	}
	public String getOrderDm() {
		return orderDm;
	}
	public void setOrderDm(String orderDm) {
		this.orderDm = orderDm;
	}
	public String getShopNo() {
		return shopNo;
	}
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}
	public String getShopEventNo() {
		return shopEventNo;
	}
	public void setShopEventNo(String shopEventNo) {
		this.shopEventNo = shopEventNo;
	}
	public String getMemNo() {
		return memNo;
	}
	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}
	public String getTaxGb() {
		return taxGb;
	}
	public void setTaxGb(String taxGb) {
		this.taxGb = taxGb;
	}
	public String getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}
	public String getPointAmt() {
		return pointAmt;
	}
	public void setPointAmt(String pointAmt) {
		this.pointAmt = pointAmt;
	}
	public String getEtcAmt() {
		return etcAmt;
	}
	public void setEtcAmt(String etcAmt) {
		this.etcAmt = etcAmt;
	}
	public String getMediaCd() {
		return mediaCd;
	}
	public void setMediaCd(String mediaCd) {
		this.mediaCd = mediaCd;
	}
	public String getDeliAmt() {
		return deliAmt;
	}
	public void setDeliAmt(String deliAmt) {
		this.deliAmt = deliAmt;
	}
	public String getItemNm() {
		return itemNm;
	}
	public void setItemNm(String itemNm) {
		this.itemNm = itemNm;
	}
	public String getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getOrderQty() {
		return orderQty;
	}
	public void setOrderQty(String orderQty) {
		this.orderQty = orderQty;
	}
	public String getDcPrice() {
		return dcPrice;
	}
	public void setDcPrice(String dcPrice) {
		this.dcPrice = dcPrice;
	}
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	
}
