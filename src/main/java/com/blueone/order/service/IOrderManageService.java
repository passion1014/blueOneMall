package com.blueone.order.service;

import java.util.List;

import com.blueone.common.domain.HMallProcAdjustmentInfo;
import com.blueone.customer.domain.RecipientInfo;
import com.blueone.order.domain.OrderInfo;
import com.blueone.order.domain.OrderProductInfo;
import com.blueone.order.domain.OrderSrchInfo;
import com.blueone.order.domain.PaymentInfo;


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
	public List<OrderInfo> getOrderInfoListBySchInfo(OrderSrchInfo orderSrchInfo);
	public int getOrderTypTotalCount(OrderSrchInfo orderSrchInfo);
	public int registPaymentInfo(PaymentInfo paymentInfo);
	public List<PaymentInfo> selectPaymentInfo(PaymentInfo paymentInfo);
	public List<OrderInfo> selectListBomOrderTbToExel0001(OrderInfo orderInfo);
	public List<OrderInfo> getOrderInfoListBySchInfo2(OrderSrchInfo orderSrchInfo);
	public int insertBomHMTb0001(HMallProcAdjustmentInfo hmInfo);
	public List<HMallProcAdjustmentInfo> selectListBomHMTb0001(HMallProcAdjustmentInfo hmInfo );
	public List<OrderInfo> selectListBomOrderTbToExel0002(OrderInfo orderInfo);
	
	
}
