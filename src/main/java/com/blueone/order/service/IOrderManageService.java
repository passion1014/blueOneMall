package com.blueone.order.service;

import java.util.List;

import com.blueone.customer.domain.RecipientInfo;
import com.blueone.order.domain.OrderInfo;
import com.blueone.order.domain.OrderProductInfo;
import com.blueone.order.domain.OrderSrchInfo;


public interface IOrderManageService {
	
	public List<OrderInfo> getOrderInfoListByPeriod(OrderSrchInfo orderSrchInfo);
	public int registOrderProductInfo(OrderProductInfo odPrdInfo);
	public int registOrderInfo(OrderInfo odInfo);
	public int registRecipientInfo(RecipientInfo reciInfo);
	public List<OrderProductInfo> selectOrderPrdInfo(OrderProductInfo odPrdInfo);
	public RecipientInfo selectRecipientInfo(RecipientInfo reciInfo);
	public List<OrderInfo> selectOrderInfoList(OrderInfo odInfo);
	public OrderProductInfo toProduct(OrderProductInfo opResInf);
	public List<OrderInfo> getOrderInfoListByPeriod(OrderInfo orderInfo);
	public int updateOrderInf(OrderInfo odInfo);
	
	
}
