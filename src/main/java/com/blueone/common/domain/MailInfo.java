package com.blueone.common.domain;


public class MailInfo extends BaseInfo {
	private long mailSeq;		// 메일일련번호
	private int skinSeq;		// 스킨일련번호(컨텐츠일련번호)
	private String sentTyp;		// 메일유형[MG20]
	private String fromNm;		// 보내는사람
	private String fromAddr;	// 보내는주소
	private String toAddr;		// 받는사람
	private String subject;		// 제목
	private String cont;		// 내용
	private String insDt;		// 최초입력일
	private String insUser;		// 최초입력자
	private long[] check;		// checkbox
	private String srchSentYmd;
	private String srchSubject;
	
	public String getSrchSentYmd() {
		return srchSentYmd;
	}
	public void setSrchSentYmd(String srchSentYmd) {
		this.srchSentYmd = srchSentYmd;
	}
	public String getSrchSubject() {
		return srchSubject;
	}
	public void setSrchSubject(String srchSubject) {
		this.srchSubject = srchSubject;
	}
	public int getSkinSeq() {
		return skinSeq;
	}
	public void setSkinSeq(int skinSeq) {
		this.skinSeq = skinSeq;
	}
	public String getFromNm() {
		return fromNm;
	}
	public void setFromNm(String fromNm) {
		this.fromNm = fromNm;
	}
	public long getMailSeq() {
		return mailSeq;
	}
	public void setMailSeq(long mailSeq) {
		this.mailSeq = mailSeq;
	}
	public String getSentTyp() {
		return sentTyp;
	}
	public void setSentTyp(String sentTyp) {
		this.sentTyp = sentTyp;
	}
	public String getFromAddr() {
		return fromAddr;
	}
	public void setFromAddr(String fromAddr) {
		this.fromAddr = fromAddr;
	}
	public String getToAddr() {
		return toAddr;
	}
	public void setToAddr(String toAddr) {
		this.toAddr = toAddr;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
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
	public long[] getCheck() {
		return check;
	}
	public void setCheck(long[] check) {
		this.check = check;
	}
}
