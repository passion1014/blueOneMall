package com.blueone.customer.service;

import com.blueone.customer.domain.CustomerInfo;
import com.blueone.customer.domain.CustomerSrchInfo;

public interface ICustomerManageService {

	public CustomerInfo custLogin(CustomerInfo customerInfo);
	public CustomerInfo getCustomerInfo(CustomerSrchInfo customerSrchInfo);
	public int updateCustomerInf(CustomerInfo customerInfo);
	public CustomerInfo getCustomerInfo2(CustomerInfo customerInfo);
	public int registUserInfo(CustomerInfo customerInfo);
}
