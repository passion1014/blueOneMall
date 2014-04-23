package com.blueone.customer.service;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueone.customer.domain.CustomerInfo;
import com.blueone.customer.domain.CustomerSrchInfo;
import com.blueone.product.domain.ProductInfo;

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
	public CustomerInfo getCustomerInfo(CustomerSrchInfo srchInfo) {
		CustomerInfo result = new CustomerInfo();
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			result = sqlSession.selectOne("customer.selectDtlBomCustTb0001", srchInfo);
		} finally {
			sqlSession.close();
		}
		
		return result;
	}
	
	/*
	 * 고객ID등을 이용한 조회 정보로 고객정보(CustomerInfo)를 가져온다. 2
	 */
	@Override
	public CustomerInfo getCustomerInfo2(CustomerInfo customerInfo) {
		CustomerInfo result = new CustomerInfo();
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			result = sqlSession.selectOne("customer.selectDtlBomCustTb0002", customerInfo);
		} finally {
			sqlSession.close();
		}
		
		return result;
	}
	//고객관리(수정)
		@Override
		public int updateCustomerInf(CustomerInfo customerInfo) {
			
			int rst = -1;
			
			// -----------------------------------------------
			// 해당하는 상품 데이터가 있는지 확인
			// -----------------------------------------------
			CustomerInfo searchRstInf = getCustomerInfo2(customerInfo);
			
			// -----------------------------------------------
			// 조회한 결과값이 있으면 DB업데이트
			// -----------------------------------------------
			if (searchRstInf != null && StringUtils.isNotEmpty(customerInfo.getCustId())) {
				SqlSession sqlSession = sqlSessionFactory.openSession();
				try {
					// DB 수행
					rst = sqlSession.update("customer.updateBomCustomerTb0001", customerInfo);
					
						
					
				} finally {
					sqlSession.close();
				}
			}
			
			return rst;
		}

		

}
