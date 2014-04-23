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
	private String custNm; 
	private String custBirth; 
	private String custSx; 
	private String custPh; 
	private String custMb; 
	private String custEmail; 
	private String custZip; 
	private String custAdd; 
	private String custSmSRcv; 
	private String custMailRcv; 
	private String custPass; 
	private String custRegDt; 
	private String custLastDt; 
	private String passwd;
	private String custMerry;
	private String custMerryY;
	private String custMerryM;
	private String custMerryD;
	
	private String custChild;
	

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

	private String custAdd1;
	private String custAdd2;
	
	private String birthY;
	private String birthM;
	private String birthD;
	
	
	private String eMail1;
	private String eMail2;
	
	private String telNo1;			// 전화번호-국번
	private String telNo2;			// 전화번호-중간번호
	private String telNo3;			// 전화번호-끝번호
	private String hpNo1;
	private String hpNo2;
	private String hpNo3;
	
	private List<CustomerContactInfo> customerContactList;

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public long getCustSeq() {
		return custSeq;
	}

	public void setCustSeq(long custSeq) {
		this.custSeq = custSeq;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCustNm() {
		return custNm;
	}

	public void setCustNm(String custNm) {
		this.custNm = custNm;
	}

	public String getCustBirth() {
		return custBirth;
	}

	public void setCustBirth(String custBirth) {
		this.custBirth = custBirth;
	}

	public String getCustSx() {
		return custSx;
	}

	public void setCustSx(String custSx) {
		this.custSx = custSx;
	}

	public String getCustPh() {
		return custPh;
	}

	public void setCustPh(String custPh) {
		this.custPh = custPh;
	}

	public String getCustMb() {
		return custMb;
	}

	public void setCustMb(String custMb) {
		this.custMb = custMb;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public String getCustZip() {
		return custZip;
	}

	public void setCustZip(String custZip) {
		this.custZip = custZip;
	}

	public String getCustAdd() {
		return custAdd;
	}

	public void setCustAdd(String custAdd) {
		this.custAdd = custAdd;
	}

	public String getCustSmSRcv() {
		return custSmSRcv;
	}

	public void setCustSmSRcv(String custSmSRcv) {
		this.custSmSRcv = custSmSRcv;
	}

	public String getCustMailRcv() {
		return custMailRcv;
	}

	public void setCustMailRcv(String custMailRcv) {
		this.custMailRcv = custMailRcv;
	}

	public String getCustPass() {
		return custPass;
	}

	public void setCustPass(String custPass) {
		this.custPass = custPass;
	}

	public String getCustRegDt() {
		return custRegDt;
	}

	public void setCustRegDt(String custRegDt) {
		this.custRegDt = custRegDt;
	}

	public String getCustLastDt() {
		return custLastDt;
	}

	public void setCustLastDt(String custLastDt) {
		this.custLastDt = custLastDt;
	}

	public String getCustMerry() {
		return custMerry;
	}

	public void setCustMerry(String custMerry) {
		this.custMerry = custMerry;
	}

	public String getCustMerryY() {
		return custMerryY;
	}

	public void setCustMerryY(String custMerryY) {
		this.custMerryY = custMerryY;
	}

	public String getCustMerryM() {
		return custMerryM;
	}

	public void setCustMerryM(String custMerryM) {
		this.custMerryM = custMerryM;
	}

	public String getCustMerryD() {
		return custMerryD;
	}

	public void setCustMerryD(String custMerryD) {
		this.custMerryD = custMerryD;
	}

	public String getCustChild() {
		return custChild;
	}

	public void setCustChild(String custChild) {
		this.custChild = custChild;
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

	public String getCustAdd1() {
		return custAdd1;
	}

	public void setCustAdd1(String custAdd1) {
		this.custAdd1 = custAdd1;
	}

	public String getCustAdd2() {
		return custAdd2;
	}

	public void setCustAdd2(String custAdd2) {
		this.custAdd2 = custAdd2;
	}

	public String getBirthY() {
		return birthY;
	}

	public void setBirthY(String birthY) {
		this.birthY = birthY;
	}

	public String getBirthM() {
		return birthM;
	}

	public void setBirthM(String birthM) {
		this.birthM = birthM;
	}

	public String getBirthD() {
		return birthD;
	}

	public void setBirthD(String birthD) {
		this.birthD = birthD;
	}

	public String geteMail1() {
		return eMail1;
	}

	public void seteMail1(String eMail1) {
		this.eMail1 = eMail1;
	}

	public String geteMail2() {
		return eMail2;
	}

	public void seteMail2(String eMail2) {
		this.eMail2 = eMail2;
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

	public List<CustomerContactInfo> getCustomerContactList() {
		return customerContactList;
	}

	public void setCustomerContactList(List<CustomerContactInfo> customerContactList) {
		this.customerContactList = customerContactList;
	}


}
