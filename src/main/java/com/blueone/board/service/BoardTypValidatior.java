package com.blueone.board.service;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.blueone.board.domain.BoardTypInfo;
import com.blueone.common.util.Utility;
import com.blueone.customer.domain.MemberInfo;

public class BoardTypValidatior implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return MemberInfo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		BoardTypInfo boardTypModel = (BoardTypInfo) target;
		
		if(Utility.isEmpty(boardTypModel.getListReadAuth())) {
			errors.rejectValue("listReadAuth", "required");
		}
	}

}