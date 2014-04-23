package com.blueone.user.service;

import org.springframework.stereotype.Service;

import com.blueone.customer.domain.CustomerInfo;
import com.blueone.user.domain.UserInfo;

@Service
public interface IUserService {
	int registUserInfo(UserInfo userInfo);

	public CustomerInfo getCustomerInfDetail(CustomerInfo customerInfo);
}
