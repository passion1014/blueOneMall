package com.blueone.order.service;

import java.util.List;

import com.blueone.order.domain.OrderInfo;
import com.blueone.order.domain.OrderProductInfo;
import com.blueone.order.domain.OrderSrchInfo;

public interface IOrderManageService {
	public List<OrderInfo> getOrderInfoListByPeriod(OrderSrchInfo orderSrchInfo);

	public int registOrderProductInfo(OrderProductInfo odPrdInfo);
	
}
