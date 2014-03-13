package com.blueone.order.domain;

import java.math.BigDecimal;
import java.util.List;

import com.blueone.customer.domain.CustomerContactInfo;
import com.blueone.customer.domain.CustomerInfo;

public class OrderInfo {
	private int idx;
	private String orderStatCd;			// 주문상태 (01:주문중, 02:결제완료, 03:배송중, 04:배송완료, 05:고객확인)
	private String paymentDivCd;		// 결제수단 (01:신용카드, 02:실시간이체, 03:핸드폰결제)
	private BigDecimal totalOrderPrice;	// 총주문금액
	private BigDecimal deliveryPrice;	// 배송금액

	private BigDecimal usePoint;		// 사용포인트
	private String useWellfarePointYn;	// 복지카드포이트 사용여부
	
	private CustomerInfo customerInfo;	// 고객정보
	private CustomerContactInfo customerContactInfo;	// 고객배송지+연락처정보
	private List<OrderProductInfo> orderProductList;	// 주문상품정보

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getPaymentDivCd() {
		return paymentDivCd;
	}

	public void setPaymentDivCd(String paymentDivCd) {
		this.paymentDivCd = paymentDivCd;
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

	public BigDecimal getUsePoint() {
		return usePoint;
	}

	public void setUsePoint(BigDecimal usePoint) {
		this.usePoint = usePoint;
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
	public String getOrderStatCd() {
		return orderStatCd;
	}

	public void setOrderStatCd(String orderStatCd) {
		this.orderStatCd = orderStatCd;
	}
}
