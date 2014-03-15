package com.blueone.admin.domain;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Login 할때 사용하는 domain객체
 */
public class AdminLoginInfo {
	@NotEmpty private String adminId;
	@NotEmpty private String adminPw;
	private boolean isRightLogin;
	
	public boolean isRightLogin() {
		return isRightLogin;
	}
	public void setRightLogin(boolean isRightLogin) {
		this.isRightLogin = isRightLogin;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminPw() {
		return adminPw;
	}
	public void setAdminPw(String adminPw) {
		this.adminPw = adminPw;
	}
}
