package com.blueone.board.service;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.blueone.board.domain.BoardTypInfo;
import com.blueone.common.util.Utility;
import com.blueone.customer.domain.CustomerInfo;

public class BoardTypValidatior implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return CustomerInfo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		BoardTypInfo boardTypModel = (BoardTypInfo) target;
		
		if(Utility.isEmpty(boardTypModel.getListReadAuth())) {
			errors.rejectValue("listReadAuth", "required");
		}
	}

}
