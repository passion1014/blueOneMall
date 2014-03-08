package com.blueone.customer.domain;

import org.springframework.web.multipart.MultipartFile;

import com.blueone.common.util.Utility;


/**
 * 회원정보 Model
 * 
 * @version $Revision: 1.0 $
 * @author $Author: kwanwool $
 */

public class MemberInfo {
	
	private long userSeq;			// 회원일련번호
	private String userId;			// 회원아이디
	private String userKnd;			// 회원구분[MG02]
	private String userNm;			// 회원명(단체명)
	private String chargeNm;		// 담당자명
	private String passwd;			// 비밀번호
	private String passUpdDt;		// 비밀번호최종변경일
	private String birthYmd;		// 생년월일
	private String smsReceYn;		// SMS 수신동의여부
	private String mailReceYn;		// Mail 수신동의여부
	private String country;			// 출신국
	private String koStayYear;		// 한국체류기간
	private String nationalityYn;	// 국적취득여부
	private String koUnderSt;		// 한국어이해정도[MG03]
	private String eMail;			// 이메일
	private String telNo;			// 전화번호
	private String hpNo;			// 휴대폰번호
	private String zipCd;			// 우편번호
	private String zipAddr1;		// 기본주소
	private String zipAddr2;		// 상세주소
	private String remarks;			// 특이사항
	private String orgSeq;			// 단체일련번호(FK)
	private String orgNm;			// 단체명
	private String delYn;			// 삭제여부
	private String delDt;			// 삭제일자
	private String delUser;			// 삭제자
	private String insDt;			// 최초입력일
	private String insUser;			// 최초입력자
	private String updDt;			// 최종변경일
	private String updUser;			// 최종변경자
	private String position;		// 직급
	private String department;		// 부서
	private String chageSector;		// 담당분야
	private String memo;			// 메모
	private String authMenu01;		// 권한여부 메뉴01
	private String authMenu02;		// 권한여부 메뉴02
	private String authMenu03;		// 권한여부 메뉴03
	private String authMenu04;		// 권한여부 메뉴04
	private String authMenu05;		// 권한여부 메뉴05
	private String authMenu06;		// 권한여부 메뉴06
	private String authMenu07;		// 권한여부 메뉴07
	private String authMenu08;		// 권한여부 메뉴08
	private String authMenu09;		// 권한여부 메뉴09
	private String authMenu10;		// 권한여부 메뉴10
	private String authMenu11;		// 권한여부 메뉴11
	private String authMenu12;		// 권한여부 메뉴12
	private String userKndNm;		// 사용자구분명
	private String lastLoginDt;		// 최종접속일
	private long[] check;
	private String reasonWithd;		// 탈퇴사유
	private MultipartFile excelFile;			// 첨부 대표이미지
	private String virtualNo;		// 개인식별번호
	private String dupInfo;			// 중복가입확인정보
	
	
	public String getVirtualNo() {
		return virtualNo;
	}
	public void setVirtualNo(String virtualNo) {
		this.virtualNo = virtualNo;
	}
	public String getDupInfo() {
		return dupInfo;
	}
	public void setDupInfo(String dupInfo) {
		this.dupInfo = dupInfo;
	}
	public MultipartFile getExcelFile() {
		return excelFile;
	}
	public void setExcelFile(MultipartFile excelFile) {
		this.excelFile = excelFile;
	}
	public String getReasonWithd() {
		return reasonWithd;
	}
	public void setReasonWithd(String reasonWithd) {
		this.reasonWithd = reasonWithd;
	}
	public long[] getCheck() {
		return check;
	}
	public void setCheck(long[] check) {
		this.check = check;
	}
	public String getOrgNm() {
		return orgNm;
	}
	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}
	public String getLastLoginDt() {
		return lastLoginDt;
	}
	public void setLastLoginDt(String lastLoginDt) {
		this.lastLoginDt = lastLoginDt;
	}
	public long getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(long userSeq) {
		this.userSeq = userSeq;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserKnd() {
		return userKnd;
	}
	public void setUserKnd(String userKnd) {
		this.userKnd = userKnd;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getChargeNm() {
		return chargeNm;
	}
	public void setChargeNm(String chargeNm) {
		this.chargeNm = chargeNm;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getPassUpdDt() {
		return passUpdDt;
	}
	public void setPassUpdDt(String passUpdDt) {
		this.passUpdDt = passUpdDt;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getKoStayYear() {
		return koStayYear;
	}
	public void setKoStayYear(String koStayYear) {
		this.koStayYear = koStayYear;
	}
	public String getNationalityYn() {
		return nationalityYn;
	}
	public void setNationalityYn(String nationalityYn) {
		this.nationalityYn = nationalityYn;
	}
	public String getKoUnderSt() {
		return koUnderSt;
	}
	public void setKoUnderSt(String koUnderSt) {
		this.koUnderSt = koUnderSt;
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
	public String getZipCd() {
		return zipCd;
	}
	public void setZipCd(String zipCd) {
		this.zipCd = zipCd;
	}
	public String getZipAddr1() {
		return zipAddr1;
	}
	public void setZipAddr1(String zipAddr1) {
		this.zipAddr1 = zipAddr1;
	}
	public String getZipAddr2() {
		return zipAddr2;
	}
	public void setZipAddr2(String zipAddr2) {
		this.zipAddr2 = zipAddr2;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getOrgSeq() {
		return orgSeq;
	}
	public void setOrgSeq(String orgSeq) {
		this.orgSeq = orgSeq;
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
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getChageSector() {
		return chageSector;
	}
	public void setChageSector(String chageSector) {
		this.chageSector = chageSector;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getAuthMenu01() {
		return authMenu01;
	}
	public void setAuthMenu01(String authMenu01) {
		this.authMenu01 = authMenu01;
	}
	public String getAuthMenu02() {
		return authMenu02;
	}
	public void setAuthMenu02(String authMenu02) {
		this.authMenu02 = authMenu02;
	}
	public String getAuthMenu03() {
		return authMenu03;
	}
	public void setAuthMenu03(String authMenu03) {
		this.authMenu03 = authMenu03;
	}
	public String getAuthMenu04() {
		return authMenu04;
	}
	public void setAuthMenu04(String authMenu04) {
		this.authMenu04 = authMenu04;
	}
	public String getAuthMenu05() {
		return authMenu05;
	}
	public void setAuthMenu05(String authMenu05) {
		this.authMenu05 = authMenu05;
	}
	public String getAuthMenu06() {
		return authMenu06;
	}
	public void setAuthMenu06(String authMenu06) {
		this.authMenu06 = authMenu06;
	}
	public String getAuthMenu07() {
		return authMenu07;
	}
	public void setAuthMenu07(String authMenu07) {
		this.authMenu07 = authMenu07;
	}
	public String getAuthMenu08() {
		return authMenu08;
	}
	public void setAuthMenu08(String authMenu08) {
		this.authMenu08 = authMenu08;
	}
	public String getAuthMenu09() {
		return authMenu09;
	}
	public void setAuthMenu09(String authMenu09) {
		this.authMenu09 = authMenu09;
	}
	public String getAuthMenu10() {
		return authMenu10;
	}
	public void setAuthMenu10(String authMenu10) {
		this.authMenu10 = authMenu10;
	}
	public String getAuthMenu11() {
		return authMenu11;
	}
	public void setAuthMenu11(String authMenu11) {
		this.authMenu11 = authMenu11;
	}
	public String getAuthMenu12() {
		return authMenu12;
	}
	public void setAuthMenu12(String authMenu12) {
		this.authMenu12 = authMenu12;
	}
	public String getUserKndNm() {
		return userKndNm;
	}
	public void setUserKndNm(String userKndNm) {
		this.userKndNm = userKndNm;
	}
	public String getTelNo1() {
		return Utility.getTelPartNo(telNo, 1);
	}
	public String getTelNo2() {
		return Utility.getTelPartNo(telNo, 2);
	}
	public String getTelNo3() {
		return Utility.getTelPartNo(telNo, 3);
	}
	public String getHpNo1() {
		return Utility.getHpPartNo(hpNo, 1);
	}
	public String getHpNo2() {
		return Utility.getHpPartNo(hpNo, 2);
	}
	public String getHpNo3() {
		return Utility.getHpPartNo(hpNo, 3);
	}
	
	/**
	 * 메일주소의 앞부분
	 * @return
	 */
	public String geteMail1() {
		int point = -1;
		String eMail1 = "";
		if (eMail != null && (point = eMail.indexOf("@")) != -1) {
			eMail1 = eMail.substring(0, point);
		}
		 
		return eMail1;
	}
	
	/**
	 * 메일주소의 뒷부분
	 * @return
	 */
	public String geteMail2() {
		int point = -1;
		String eMail2 = "";
		if (eMail != null && (point = eMail.indexOf("@")) != -1) {
			eMail2 = eMail.substring(point+1);
		}
		 
		return eMail2;
	}
	
}
