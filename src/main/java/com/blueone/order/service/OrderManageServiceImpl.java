package com.blueone.order.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueone.board.domain.BoardSrchInfo;
import com.blueone.common.domain.AttachFileInfo;
import com.blueone.common.domain.HMallProcAdjustmentInfo;
import com.blueone.common.domain.ResultInfo;
import com.blueone.common.service.IAttachFileManageService;
import com.blueone.customer.domain.CustomerContactInfo;
import com.blueone.customer.domain.CustomerInfo;
import com.blueone.customer.domain.CustomerSrchInfo;
import com.blueone.customer.domain.RecipientInfo;
import com.blueone.customer.service.CustomerManageServiceImpl;
import com.blueone.customer.service.ICustomerManageService;
import com.blueone.order.domain.OrderInfo;
import com.blueone.order.domain.OrderProductInfo;
import com.blueone.order.domain.OrderSrchInfo;
import com.blueone.order.domain.PaymentInfo;
import com.blueone.product.domain.ProductInfo;
import com.blueone.product.service.IProductManageService;

@Service
public class OrderManageServiceImpl implements IOrderManageService{

	@Autowired private SqlSessionFactory sqlSessionFactory;
	@Autowired private ICustomerManageService customerManageService;
	@Autowired private IProductManageService productManageService;
	@Autowired private IAttachFileManageService attFileManageService;
	
	@Override
	public List<OrderInfo> getOrderInfoListByPeriod(OrderInfo orderInfo) {
		List<OrderInfo> orderList;
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			
			// 기간에 따른 주문목록 조회
			orderList = sqlSession.selectList("order.selectListBomOrderTb0001", orderInfo);
			
			/*
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
				
				
			}*/
		} finally {
			sqlSession.close();
		}
		
		return orderList;
	}
	@Override
	public List<OrderInfo> selectListBomOrderTbToExel0001(OrderInfo orderInfo) {
		List<OrderInfo> orderList;
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			
			// 기간에 따른 주문목록 조회
			orderList = sqlSession.selectList("order.selectListBomOrderTbToExel0001", orderInfo);
			
			/*
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
				
				
			}*/
		} finally {
			sqlSession.close();
		}
		
		return orderList;
	}
	@Override
	public List<OrderInfo> getOrderInfoListBySchInfo(OrderSrchInfo orderSrchInfo) {
		List<OrderInfo> orderList;
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			
			// 기간에 따른 주문목록 조회
			orderList = sqlSession.selectList("order.selectListBomOrderTb0003", orderSrchInfo);
			
			/*
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
				
				
			}*/
		} finally {
			sqlSession.close();
		}
		
		return orderList;
	}
	@Override
	public List<HMallProcAdjustmentInfo> selectListBomHMTb0001() {
		List<HMallProcAdjustmentInfo> HMlist;
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			
			// 기간에 따른 주문목록 조회
			HMlist = sqlSession.selectList("order.selectListBomHMTb0001");
			
			/*
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
				
				
			}*/
		} finally {
			sqlSession.close();
		}
		
		return HMlist;
	}
	@Override
	public List<OrderInfo> getOrderInfoListBySchInfo2(OrderSrchInfo orderSrchInfo) {
		List<OrderInfo> orderList;
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			
			// 기간에 따른 주문목록 조회
			orderList = sqlSession.selectList("order.selectListBomOrderTb0004", orderSrchInfo);
			
			/*
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
				
				
			}*/
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
	public int insertBomHMTb0001(HMallProcAdjustmentInfo hmInfo) {
		
		
		int rst=0;
		// -----------------------------------------------
		// DB Insert 수행
		// -----------------------------------------------
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			
			
			rst = sqlSession.insert("order.insertBomHMTb0001", hmInfo);
			
			
			
		} finally {
			sqlSession.close();
		}
		
		return rst;
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
	
	//주문-결제-등록
		@Override
		public int registPaymentInfo(PaymentInfo paymentInfo) {
			
			
			int rst=0;
			// -----------------------------------------------
			// DB Insert 수행
			// -----------------------------------------------
			SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
				
				
				rst = sqlSession.insert("order.insertBomPaymentTb0001", paymentInfo);
				
				
				
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
	
	//주문-받는사람
	@Override
	public int registRecipientInfo(RecipientInfo reciInfo) {
				
				
			int rst=0;
			// -----------------------------------------------
			// DB Insert 수행
			// -----------------------------------------------
			SqlSession sqlSession = sqlSessionFactory.openSession();
				try {
					
					
					rst = sqlSession.insert("recipient.insertBomRecipientTb0001", reciInfo);
					
					
					
				} finally {
					sqlSession.close();
				}
				
				return rst;
		
		}
	
	@Override
	//주문코드로 주문상품 조회
	public List<OrderProductInfo> selectOrderPrdInfo(OrderProductInfo odPrdInfo){
		 List<OrderProductInfo> result = new  ArrayList<OrderProductInfo>();
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			// -----------------------------------------------
			// 1. 상품코드 기본값 조회
			// -----------------------------------------------
			result = sqlSession.selectList("order.selectListBomOrderPrdTb0001", odPrdInfo);
			
		} finally {
			sqlSession.close();
		}
		return result;
		
		
	}
	
	@Override
	//포인트사용 조회
	public List<PaymentInfo> selectPaymentInfo(PaymentInfo paymentInfo){
		 List<PaymentInfo> result = new  ArrayList<PaymentInfo>();
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			// -----------------------------------------------
			// 1. 상품코드 기본값 조회
			// -----------------------------------------------
			result = sqlSession.selectList("order.selectListBomPaymentTb0001", paymentInfo);
			
		} finally {
			sqlSession.close();
		}
		return result;
		
		
	}

	@Override
	//사용자 아이디 or 주문코드로or날짜로 주문내역 조회
	public List<OrderInfo> selectOrderInfoList(OrderInfo odInfo){
		 List<OrderInfo> result = new ArrayList<OrderInfo>();
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			// -----------------------------------------------
			// 1. 상품코드 기본값 조회
			// -----------------------------------------------
			result = sqlSession.selectList("order.selectListBomOrderTb0002", odInfo);
			
		} finally {
			sqlSession.close();
		}
		return result;
		
		
	}
	
	@Override
	//주문코드로 받는사람 조회
	public RecipientInfo selectRecipientInfo(RecipientInfo reciInfo){
		RecipientInfo result = new RecipientInfo();
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			// -----------------------------------------------
			// 1. 상품코드 기본값 조회
			// -----------------------------------------------
			result = sqlSession.selectOne("recipient.selectListBomRecipientTb0001", reciInfo);
			
		} finally {
			sqlSession.close();
		}
		return result;
		
		
	}
	
	@Override
	//OrderProductInfo로 Product정보 가져오기
	public OrderProductInfo toProduct(OrderProductInfo opResInf){
		
		
		String prdCd = opResInf.getPrdCd();
		ProductInfo prInf = new ProductInfo();
		prInf.setPrdCd(prdCd);
		prInf=productManageService.getProductInfDetail(prInf);
		
		if(prInf !=null){
		//상품 이름
		opResInf.setPrdNm(prInf.getPrdNm());
		
		//옵션
		String option=opResInf.getPrdOption();
		StringTokenizer st = new StringTokenizer(option,",");
		while(st.hasMoreElements()) {
				
				String s = st.nextToken();
				
				if("01".equals(s.substring(0, 2))){
					option+=s+",";
					opResInf.setPrdOpColor(s.substring(3));
				}
				if("02".equals(s.substring(0, 2))){
					option+=s+",";
					opResInf.setPrdOpSize(s.substring(3));
				}
		
		}
		
		//수량 및 금액
		opResInf.setSellPrice(new BigDecimal(prInf.getPrdSellPrc()));
		BigDecimal total = new BigDecimal(prInf.getPrdSellPrc()) ;
		total=total.multiply(new BigDecimal(opResInf.getBuyCnt()));
		opResInf.setTotalPrice(total);
		
		//사진
		AttachFileInfo att = new AttachFileInfo();
		att.setAttCdKey(prInf.getPrdCd());
		att.setAttImgType("01");
		att = attFileManageService.getAttFileInfListImg(att);
		if(att==null){
			opResInf.setPrdSmallImg("");
		}else { 
			
			opResInf.setPrdSmallImg(att.getAttFilePath());
		}
		}
		else{
			
		}
		return  opResInf;
		
		
	}

	//상품관리(수정)
	@Override
	public int updateOrderInf(OrderInfo odInfo) {
		int rst = -1;

		// -----------------------------------------------
		// 해당하는 상품 데이터가 있는지 확인
		// -----------------------------------------------
//		ProductInfo searchRstInf = getProductInfDetail(productInfo);
	
		// -----------------------------------------------
		// 조회한 결과값이 있으면 DB업데이트
		// -----------------------------------------------
		// if (searchRstInf != null && StringUtils.isNotEmpty(searchRstInf.getPrdCd())) {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
				// DB 수행
				rst = sqlSession.update("order.updateBomOrderTb0001", odInfo);
			} finally {
				sqlSession.close();
			}
		//}

		return rst;
	}

	@Override
	public List<OrderInfo> getOrderInfoListByPeriod(OrderSrchInfo orderSrchInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getOrderTypTotalCount(OrderSrchInfo orderSrchInfo) {
		Integer count = new Integer(0);
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			count = sqlSession.selectOne("order.getOrderTypTotalCount", orderSrchInfo);
		} finally {
			sqlSession.close();
		}
		
		return count;
	}
	
	
}
