package com.blueone.order.domain;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 주문목록조회를 위한 조회정보
 * @author daniel
 */
public class OrderSrchInfo {
	@NotEmpty private String srchStdDt;
	@NotEmpty private String srchEdDt;
	
	public String getSrchStdDt() {
		return srchStdDt;
	}
	public void setSrchStdDt(String srchStdDt) {
		this.srchStdDt = srchStdDt;
	}
	public String getSrchEdDt() {
		return srchEdDt;
	}
	public void setSrchEdDt(String srchEdDt) {
		this.srchEdDt = srchEdDt;
	}
	
}
