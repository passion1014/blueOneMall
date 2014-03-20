package com.blueone.customer.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueone.customer.domain.CustomerInfo;
import com.blueone.customer.domain.CustomerSrchInfo;

@Service
public class CustomerManageServiceImpl implements ICustomerManageService {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public CustomerInfo custLogin(CustomerInfo customerInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * 고객ID등을 이용한 조회 정보로 고객정보(CustomerInfo)를 가져온다. 
	 */
	@Override
	public CustomerInfo getCustomerInfo(CustomerSrchInfo customerSrchInfo) {
		CustomerInfo customerInfo = new CustomerInfo();
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			customerInfo = sqlSession.selectOne("customer.selectDtlBomCustTb0001", customerSrchInfo);
		} finally {
			sqlSession.close();
		}
		
		return customerInfo;
	}

}
