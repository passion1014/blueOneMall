package com.blueone.common.service;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.blueone.customer.domain.MemberInfo;

public class HTMLContValidatior implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return MemberInfo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	}

}
