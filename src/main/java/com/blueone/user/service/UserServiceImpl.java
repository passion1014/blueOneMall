package com.blueone.user.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueone.customer.domain.CustomerInfo;
import com.blueone.product.domain.ProductInfo;
import com.blueone.user.domain.UserInfo;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public int registUserInfo(UserInfo userInfo) {
		
		int rst = -1;
		SqlSession sqlSession = sqlSessionFactory.openSession();

		// -----------------------------------------------
		// DB Insert 수행
		// -----------------------------------------------
		try {
			rst = sqlSession.insert("user.insertBomCustTb0001", userInfo);
		} finally {
			sqlSession.close();
		}
		
		return rst;
	}

	

	/*
	 *  사용자 아이디로 조회
	 */
	@Override
	public CustomerInfo getCustomerInfDetail(CustomerInfo customerInfo) {
		                 
		CustomerInfo rstInfo = new CustomerInfo();
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			// -----------------------------------------------
			// 1. 상품코드 기본값 조회
			// -----------------------------------------------
			rstInfo = sqlSession.selectOne("customer.selectDtlBomProductTb0001", customerInfo);
			
			
		} finally {
			sqlSession.close();
		}
		
		
		return rstInfo;
	}
	
	
}
