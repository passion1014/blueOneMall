package com.blueone.order.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueone.order.domain.OrderInfo;
import com.blueone.order.domain.OrderSrchInfo;

@Service
public class OrderManageServiceImpl implements IOrderManageService{

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public List<OrderInfo> getOrderInfoListByPeriod(OrderSrchInfo orderSrchInfo) {
		
		// -----------------------------------------------
		// DB Insert 수행
		// -----------------------------------------------
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			// DB 수행
			sqlSession.selectList("order.selectListBomOrderTb0001", orderSrchInfo);
		} finally {
			sqlSession.close();
		}
		
		return null;
	}

}
