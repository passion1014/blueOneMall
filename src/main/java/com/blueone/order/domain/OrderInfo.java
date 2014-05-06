package com.blueone.order.domain;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.blueone.customer.domain.CustomerContactInfo;
import com.blueone.customer.domain.CustomerInfo;
import com.blueone.customer.domain.RecipientInfo;

public class OrderInfo {
	private int ordIdx;
	
	private String orderNo;				// 주문번호
	private String orderStatCd;			// 주문상태 (01:신청대기, 02:결제완료, 03:배송준비중, 04:배송중, 05:배송완료, 06:고객확인, 07: 취소신청,
										//           08: 취소신청완료,09: 반품신청,10: 반품신청완료)
	private String orderDate;			// 주문발생일
	private BigDecimal totalOrderPrice;	// 총주문금액
	private BigDecimal deliveryPrice;	// 배송금액
	private String useWellfarePointYn;	// 복지카드포인트 사용여부

	private CustomerInfo customerInfo;	// 고객정보
	private CustomerContactInfo customerContactInfo;	// 고객배송지+연락처정보
	private RecipientInfo reciInfo;
	private List<OrderProductInfo> orderProductList;	// 주문상품정보
	private List<PaymentInfo> paymentLisit;				// 결제정보
	private OrderProductInfo ordPrd;
	private String regDate;		// 등록일
	private String lastDate;	// 최종수정일
	private String modifyUserId;// 최종수정자
	private String prdCD;

	private String ord_unit_chk; //다중 checkbox

	
	//검색하기위한 변수
	private String srchStdDt;
	private String srchEdDt;
	
	
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
	public String getOrd_unit_chk() {
		return ord_unit_chk;
	}
	public void setOrd_unit_chk(String ord_unit_chk) {
		this.ord_unit_chk = ord_unit_chk;
	}
	public OrderProductInfo getOrdPrd() {
		return ordPrd;
	}
	public void setOrdPrd(OrderProductInfo ordPrd) {
		this.ordPrd = ordPrd;
	}
	public RecipientInfo getReciInfo() {
		return reciInfo;
	}
	public void setReciInfo(RecipientInfo reciInfo) {
		this.reciInfo = reciInfo;
	}
	public String getPrdCD() {
		return prdCD;
	}
	public void setPrdCD(String prdCD) {
		this.prdCD = prdCD;
	}

	public int getIdx() {
		return ordIdx;
	}
	public void setIdx(int ordIdx) {
		this.ordIdx = ordIdx;
	}
	public String getOrderStatCd() {
		return orderStatCd;
	}
	public void setOrderStatCd(String orderStatCd) {
		this.orderStatCd = orderStatCd;
	}
	public BigDecimal getTotalOrderPrice() {
		return totalOrderPrice;
	}
	public void setTotalOrderPrice(BigDecimal totalOrderPrice) {
		this.totalOrderPrice = totalOrderPrice;
	}
	public BigDecimal getDeliveryPrice() {
		return deliveryPrice;
	}
	public void setDeliveryPrice(BigDecimal deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}
	public String getUseWellfarePointYn() {
		return useWellfarePointYn;
	}
	public void setUseWellfarePointYn(String useWellfarePointYn) {
		this.useWellfarePointYn = useWellfarePointYn;
	}
	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}
	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}
	public CustomerContactInfo getCustomerContactInfo() {
		return customerContactInfo;
	}
	public void setCustomerContactInfo(CustomerContactInfo customerContactInfo) {
		this.customerContactInfo = customerContactInfo;
	}
	public List<OrderProductInfo> getOrderProductList() {
		return orderProductList;
	}
	public void setOrderProductList(List<OrderProductInfo> orderProductList) {
		this.orderProductList = orderProductList;
	}
	public List<PaymentInfo> getPaymentLisit() {
		return paymentLisit;
	}
	public void setPaymentLisit(List<PaymentInfo> paymentLisit) {
		this.paymentLisit = paymentLisit;
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
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
}
