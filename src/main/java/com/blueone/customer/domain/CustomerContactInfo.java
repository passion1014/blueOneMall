package com.blueone.customer.domain;

public class CustomerContactInfo {
	private String custId;
	
	private int contactSeq;		// 접촉정보 순번
	private String contactType;	// 집(001), 회사(002), 기타(009)
	private String contactNm;	// 접촉정보 명 (고객이 입력하는 임의의 값)
	private String defaultYn;	// 기본값 여부

	private String email;
	private String mobile1;
	private String mobile2;
	private String mobile3;
	private String phone1;
	private String phone2;
	private String phone3;
	
	// 주소정보
	private String zipCd1;		// 우편번호1
	private String zipCd2;		// 우편번호2
	private String addrDesc1;	// 기본주소
	private String addrDesc2;	// 상세주소
	
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public int getContactSeq() {
		return contactSeq;
	}
	public void setContactSeq(int contactSeq) {
		this.contactSeq = contactSeq;
	}
	public String getContactType() {
		return contactType;
	}
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
	public String getContactNm() {
		return contactNm;
	}
	public void setContactNm(String contactNm) {
		this.contactNm = contactNm;
	}
	public String getDefaultYn() {
		return defaultYn;
	}
	public void setDefaultYn(String defaultYn) {
		this.defaultYn = defaultYn;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile1() {
		return mobile1;
	}
	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}
	public String getMobile2() {
		return mobile2;
	}
	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}
	public String getMobile3() {
		return mobile3;
	}
	public void setMobile3(String mobile3) {
		this.mobile3 = mobile3;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getPhone3() {
		return phone3;
	}
	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}
	public String getZipCd1() {
		return zipCd1;
	}
	public void setZipCd1(String zipCd1) {
		this.zipCd1 = zipCd1;
	}
	public String getZipCd2() {
		return zipCd2;
	}
	public void setZipCd2(String zipCd2) {
		this.zipCd2 = zipCd2;
	}
	public String getAddrDesc1() {
		return addrDesc1;
	}
	public void setAddrDesc1(String addrDesc1) {
		this.addrDesc1 = addrDesc1;
	}
	public String getAddrDesc2() {
		return addrDesc2;
	}
	public void setAddrDesc2(String addrDesc2) {
		this.addrDesc2 = addrDesc2;
	}
}
