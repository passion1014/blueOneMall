package com.blueone.customer.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueone.admin.domain.AccountInfo;
import com.blueone.customer.domain.CustomerInfo;
import com.blueone.customer.domain.CustomerSrchInfo;
import com.blueone.order.domain.OrderSrchInfo;
import com.blueone.product.domain.ProductInfo;
import com.blueone.user.domain.UserInfo;

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
	/*
	 * 모든 고객정보(CustomerInfo)를 가져온다. 3
	 */
	@Override
	public List<CustomerInfo> getCustomerInfoList(CustomerInfo custInfo) {
		List<CustomerInfo> result = new ArrayList<CustomerInfo>();
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			result = sqlSession.selectList("customer.selectDtlBomCustTb0003",custInfo);
		} finally {
			sqlSession.close();
		}
		
		return result;
	}
	
	/*
	 * 검색한 고객정보(CustomerInfo)를 가져온다. 3
	 */
	@Override
	public List<CustomerInfo> searchCustomerInfoList(CustomerInfo customerInfo) {
		List<CustomerInfo> result = new ArrayList<CustomerInfo>();
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			result = sqlSession.selectList("customer.selectDtlBomCustTb0004",customerInfo);
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

		//고객등록
		@Override
		public int registUserInfo(CustomerInfo customerInfo) {
			
			int rst = -1;
			SqlSession sqlSession = sqlSessionFactory.openSession();

			// -----------------------------------------------
			// DB Insert 수행
			// -----------------------------------------------
			try {
				rst = sqlSession.insert("customer.insertBomCustTb0001", customerInfo);
			} finally {
				sqlSession.close();
			}
			
			return rst;
		}
		/*
		 * 고객삭제
		 */
		@Override
		public int deleteCustomerInf(CustomerInfo customerInfo){
			
			int rst = -1;
			
			
				SqlSession sqlSession = sqlSessionFactory.openSession();
				try {
					// DB 수행
					rst = sqlSession.delete("customer.deleteBomCustomerTb0001", customerInfo);
					
				} finally {
					sqlSession.close();
				}
			
			
			return rst;
		}
		
		@Override
		public int getCustomerTypTotalCount(CustomerInfo customerInfo) {
			Integer count = new Integer(0);
			
			SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
				count = sqlSession.selectOne("customer.getCustomerTypTotalCount", customerInfo);
			} finally {
				sqlSession.close();
			}
			
			return count;
		}
		
}
