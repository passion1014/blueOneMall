package com.blueone.common.service;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.blueone.customer.domain.CustomerInfo;

public class HTMLContValidatior implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return CustomerInfo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	}

}
