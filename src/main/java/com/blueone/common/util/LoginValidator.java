package com.blueone.common.util;

import com.blueone.admin.domain.AdminInfo;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class LoginValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return AdminInfo.class.isAssignableFrom(clazz);
	}

	public void validate(Object command, Errors errors) {

		AdminInfo adminInfo = (AdminInfo) command;
		if (!StringUtils.hasLength(adminInfo.getId())) {
			errors.rejectValue("id", "error.required");
		}

		if (!StringUtils.hasLength(adminInfo.getPassword())) {
			errors.rejectValue("password", "error.required");
		}

		if (errors.hasErrors()) {
			errors.reject("error.input.adminInfo");
		}
	}
}