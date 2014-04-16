package com.blueone.user.domain;

/**
 * 사용자정보 
 */
public class UserInfo {
	private int idx;
	private String userId;
	private String userName;
	private String birthDt;
	private String phoneNo;
	private String mobileNo;
	private String email;
	private String zip1;
	private String zip2;
	private String address;
	private String addressDtl;
	private String regDate;
	private String lastDate;
	private String modifyUserId;

	public String getBirthDt() {
		return birthDt;
	}
	public void setBirthDt(String birthDt) {
		this.birthDt = birthDt;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getZip1() {
		return zip1;
	}
	public void setZip1(String zip1) {
		this.zip1 = zip1;
	}
	public String getZip2() {
		return zip2;
	}
	public void setZip2(String zip2) {
		this.zip2 = zip2;
	}
	public String getAddressDtl() {
		return addressDtl;
	}
	public void setAddressDtl(String addressDtl) {
		this.addressDtl = addressDtl;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getLastDate() {
		return lastDate;
	}
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}
	public String getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
}
