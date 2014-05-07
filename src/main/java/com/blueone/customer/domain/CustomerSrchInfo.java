package com.blueone.customer.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class CustomerSrchInfo {
	
	@NotEmpty private String custId;
	private int custColum; //1: 회원명, 2: 회원ID, 3:이메일, 4:핸드폰번호
	private String word;
	
	
	public int getCustColum() {
		return custColum;
	}

	public void setCustColum(int custColum) {
		this.custColum = custColum;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}
}
