package com.blueone.customer.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class CustomerSrchInfo {
	
	@NotEmpty private String custId;

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}
}
