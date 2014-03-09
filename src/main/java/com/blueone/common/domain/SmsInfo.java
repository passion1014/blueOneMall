package com.blueone.common.domain;

public class SmsInfo extends BaseInfo {
	private String cmpMsgId;	//to_char(sysdateyyyymmddhh24miss)+5자리숫자
	private String cmpUsrId;	//00000
	private String odrFg;		//2
	private String smsGb;		//1
	private String usedCd;		//00
	private String msgGb;		//A
	private String wrtDttm;		//to_char(sysdateyyyymmddhh24miss)
	private String sndDttm;		//to_char(sysdateyyyymmddhh24miss)
	private String rcvPhnId;	//수신번호
	private String sndMsg;		//단문내용
	private int expireVal;		//0
	private String smsSt;		//0
	private int rsltVal;		//99
	private String upCd;		//90:회원가입/ 91:교육일정안내 등 기타
	private String orgCd;		//발송기관
	private String empId;		//발송자 정보
	private String caseNo;		//단문발송게시판 글번호
	private String upFg;		//1
	private String callback;	//발송번호
	private long[] check;		//선택한 회원정보
	
	public long[] getCheck() {
		return check;
	}
	public void setCheck(long[] check) {
		this.check = check;
	}
	public String getCmpMsgId() {
		return cmpMsgId;
	}
	public void setCmpMsgId(String cmpMsgId) {
		this.cmpMsgId = cmpMsgId;
	}
	public String getCmpUsrId() {
		return cmpUsrId;
	}
	public void setCmpUsrId(String cmpUsrId) {
		this.cmpUsrId = cmpUsrId;
	}
	public String getOdrFg() {
		return odrFg;
	}
	public void setOdrFg(String odrFg) {
		this.odrFg = odrFg;
	}
	public String getSmsGb() {
		return smsGb;
	}
	public void setSmsGb(String smsGb) {
		this.smsGb = smsGb;
	}
	public String getUsedCd() {
		return usedCd;
	}
	public void setUsedCd(String usedCd) {
		this.usedCd = usedCd;
	}
	public String getMsgGb() {
		return msgGb;
	}
	public void setMsgGb(String msgGb) {
		this.msgGb = msgGb;
	}
	public String getWrtDttm() {
		return wrtDttm;
	}
	public void setWrtDttm(String wrtDttm) {
		this.wrtDttm = wrtDttm;
	}
	public String getSndDttm() {
		return sndDttm;
	}
	public void setSndDttm(String sndDttm) {
		this.sndDttm = sndDttm;
	}
	public String getRcvPhnId() {
		return rcvPhnId;
	}
	public void setRcvPhnId(String rcvPhnId) {
		this.rcvPhnId = rcvPhnId;
	}
	public String getSndMsg() {
		return sndMsg;
	}
	public void setSndMsg(String sndMsg) {
		this.sndMsg = sndMsg;
	}
	public int getExpireVal() {
		return expireVal;
	}
	public void setExpireVal(int expireVal) {
		this.expireVal = expireVal;
	}
	public String getSmsSt() {
		return smsSt;
	}
	public void setSmsSt(String smsSt) {
		this.smsSt = smsSt;
	}
	public int getRsltVal() {
		return rsltVal;
	}
	public void setRsltVal(int rsltVal) {
		this.rsltVal = rsltVal;
	}
	public String getUpCd() {
		return upCd;
	}
	public void setUpCd(String upCd) {
		this.upCd = upCd;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public String getUpFg() {
		return upFg;
	}
	public void setUpFg(String upFg) {
		this.upFg = upFg;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	
}
