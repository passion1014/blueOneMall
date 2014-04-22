package com.blueone.order.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueone.common.domain.ResultInfo;
import com.blueone.customer.domain.CustomerContactInfo;
import com.blueone.customer.domain.CustomerInfo;
import com.blueone.customer.domain.CustomerSrchInfo;
import com.blueone.customer.service.CustomerManageServiceImpl;
import com.blueone.customer.service.ICustomerManageService;
import com.blueone.order.domain.OrderInfo;
import com.blueone.order.domain.OrderProductInfo;
import com.blueone.order.domain.OrderSrchInfo;
import com.blueone.product.domain.ProductInfo;

@Service
public class OrderManageServiceImpl implements IOrderManageService{

	@Autowired private SqlSessionFactory sqlSessionFactory;
	@Autowired private ICustomerManageService customerManageService;

	@Override
	public List<OrderInfo> getOrderInfoListByPeriod(OrderSrchInfo orderSrchInfo) {
		List<OrderInfo> orderList;
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			
			// 기간에 따른 주문목록 조회
			orderList = sqlSession.selectList("order.selectListBomOrderTb0001", orderSrchInfo);
			
			for (OrderInfo orderInfo : orderList) {
				if (orderInfo.getCustomerInfo() != null ) {
					CustomerSrchInfo srchInfo = new CustomerSrchInfo();
					srchInfo.setCustId(orderInfo.getCustomerInfo().getCustId());
					
					// 고객정보 셋팅
					CustomerInfo customerInfo = customerManageService.getCustomerInfo(srchInfo);
					if (customerInfo != null) {
						orderInfo.setCustomerInfo(customerInfo);

						// 기본 주소정보 셋팅
						List<CustomerContactInfo> contactList = customerInfo.getCustomerContactList();
						if (contactList != null) {
							orderInfo.setCustomerContactInfo(getDefaultContactInfo(contactList));
						}
					}
				}
			}
		} finally {
			sqlSession.close();
		}
		
		return orderList;
	}
	
	private CustomerContactInfo getDefaultContactInfo(List<CustomerContactInfo> list) {
		if (list == null) return null; 
		if (list.size() == 1) return list.get(0);
		
		for (CustomerContactInfo each : list) {
			if (StringUtils.isNotEmpty(each.getDefaultYn()) &&  "1".equals(each.getDefaultYn())) {
				return each;
			}
		}
		
		return null;
	}
	
	//주문-상품-등록
	@Override
	public int registOrderProductInfo(OrderProductInfo odPrdInfo) {
		
		
		int rst=0;
		// -----------------------------------------------
		// DB Insert 수행
		// -----------------------------------------------
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			
			
			rst = sqlSession.insert("order.insertBomOrderPrdTb0001", odPrdInfo);
			
			
			
		} finally {
			sqlSession.close();
		}
		
		return rst;
	}
	
	//주문-등록
	@Override
	public int registOrderInfo(OrderInfo odInfo) {
			
			
		int rst=0;
		// -----------------------------------------------
		// DB Insert 수행
		// -----------------------------------------------
		SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
				
				
				rst = sqlSession.insert("order.insertBomOrderTb0001", odInfo);
				
				
				
			} finally {
				sqlSession.close();
			}
			
			return rst;
		}
	
}
