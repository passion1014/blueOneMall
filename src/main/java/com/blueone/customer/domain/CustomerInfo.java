package com.blueone.customer.domain;

import java.util.List;

//import org.springframework.web.multipart.MultipartFile;
//import com.blueone.common.util.Utility;


/**
 * 고객정보
 */

public class CustomerInfo {
	
	private long custSeq;			// 고객일련번호
	private String custId;			// 고객아이디
	private String custKnd;			// 고객구분[MG02] (### 사용안함-삭제예정)
	private String custNm;			// 고객명(단체명)
	private String passwd;			// 비밀번호
	private String birthYmd;		// 생년월일
	private String smsReceYn;		// SMS 수신동의여부
	private String mailReceYn;		// Mail 수신동의여부
	private String eMail;			// 이메일
	private String telNo;			// 전화번호
	private String hpNo;			// 휴대폰번호
	private String remarks;			// 특이사항 (### 사용안함-삭제예정)
	private String delYn;			// 삭제여부 (### 사용안함-삭제예정)
	private String delDt;			// 삭제일자 (### 사용안함-삭제예정)
	private String delUser;			// 삭제자 (### 사용안함-삭제예정)
	private String insDt;			// 최초입력일
	private String insUser;			// 최초입력자
	private String updDt;			// 최종변경일
	private String updUser;			// 최종변경자
	private String memo;			// 메모
	private String lastLoginDt;		// 최종접속일
	private String reasonWithd;		// 탈퇴사유
//	private MultipartFile excelFile;// 첨부 대표이미지
	private String virtualNo;		// 개인식별번호
	
	private String telNo1;			// 전화번호-국번
	private String telNo2;			// 전화번호-중간번호
	private String telNo3;			// 전화번호-끝번호
	private String hpNo1;
	private String hpNo2;
	private String hpNo3;
	
	private List<CustomerContactInfo> customerContactList;
	
	
	public String getHpNo1() {
		return hpNo1;
	}
	public void setHpNo1(String hpNo1) {
		this.hpNo1 = hpNo1;
	}
	public String getHpNo2() {
		return hpNo2;
	}
	public void setHpNo2(String hpNo2) {
		this.hpNo2 = hpNo2;
	}
	public String getHpNo3() {
		return hpNo3;
	}
	public void setHpNo3(String hpNo3) {
		this.hpNo3 = hpNo3;
	}
	public String getTelNo1() {
		return telNo1;
	}
	public void setTelNo1(String telNo1) {
		this.telNo1 = telNo1;
	}
	public String getTelNo2() {
		return telNo2;
	}
	public void setTelNo2(String telNo2) {
		this.telNo2 = telNo2;
	}
	public String getTelNo3() {
		return telNo3;
	}
	public void setTelNo3(String telNo3) {
		this.telNo3 = telNo3;
	}
	public List<CustomerContactInfo> getCustomerContactList() {
		return customerContactList;
	}
	public void setCustomerContactList(List<CustomerContactInfo> customerContactList) {
		this.customerContactList = customerContactList;
	}
	public long getCustSeq() {
		return custSeq;
	}
	public void setCustSeq(long userSeq) {
		this.custSeq = userSeq;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String userId) {
		this.custId = userId;
	}
	public String getCustKnd() {
		return custKnd;
	}
	public void setCustKnd(String userKnd) {
		this.custKnd = userKnd;
	}
	public String getCustNm() {
		return custNm;
	}
	public void setCustNm(String userNm) {
		this.custNm = userNm;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getBirthYmd() {
		return birthYmd;
	}
	public void setBirthYmd(String birthYmd) {
		this.birthYmd = birthYmd;
	}
	public String getSmsReceYn() {
		return smsReceYn;
	}
	public void setSmsReceYn(String smsReceYn) {
		this.smsReceYn = smsReceYn;
	}
	public String getMailReceYn() {
		return mailReceYn;
	}
	public void setMailReceYn(String mailReceYn) {
		this.mailReceYn = mailReceYn;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public String getHpNo() {
		return hpNo;
	}
	public void setHpNo(String hpNo) {
		this.hpNo = hpNo;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public String getDelDt() {
		return delDt;
	}
	public void setDelDt(String delDt) {
		this.delDt = delDt;
	}
	public String getDelUser() {
		return delUser;
	}
	public void setDelUser(String delUser) {
		this.delUser = delUser;
	}
	public String getInsDt() {
		return insDt;
	}
	public void setInsDt(String insDt) {
		this.insDt = insDt;
	}
	public String getInsUser() {
		return insUser;
	}
	public void setInsUser(String insUser) {
		this.insUser = insUser;
	}
	public String getUpdDt() {
		return updDt;
	}
	public void setUpdDt(String updDt) {
		this.updDt = updDt;
	}
	public String getUpdUser() {
		return updUser;
	}
	public void setUpdUser(String updUser) {
		this.updUser = updUser;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getLastLoginDt() {
		return lastLoginDt;
	}
	public void setLastLoginDt(String lastLoginDt) {
		this.lastLoginDt = lastLoginDt;
	}
	public String getReasonWithd() {
		return reasonWithd;
	}
	public void setReasonWithd(String reasonWithd) {
		this.reasonWithd = reasonWithd;
	}
	public String getVirtualNo() {
		return virtualNo;
	}
	public void setVirtualNo(String virtualNo) {
		this.virtualNo = virtualNo;
	}
	
}
