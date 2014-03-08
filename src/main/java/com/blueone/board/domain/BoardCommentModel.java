package com.blueone.board.domain;

import com.blueone.common.util.Utility;


public class BoardCommentModel {
	
	private long brdSeq;		// 게시물일련번호(FK)
	private int srchBrdTyp;		// 게시판유형(FK)
	private int commNo;			// 덧글번호
	private int commRootNo;		// 부모덧글번호
	private int commRefNo;		// 자식덧글번호
	private int commDepth;		// 덧글깊이
	private String commUserNm;	// 작성자명
	private String commPasswd;	// 비밀번호
	private String commCont;	// 내용
	private String delYn;		// 삭제여부
	private String delDt;		// 삭제일자
	private String delUser;		// 삭제자
	private String insDt;		// 최초입력일
	private String insUser;		// 최초입력자
	private String updDt;		// 최종변경일
	private String updUser;		// 최종변경자
	private String srchKeyword;
	private int childCommCnt;
	private int maxCommRefNo;
	
	
	public int getChildCommCnt() {
		return childCommCnt;
	}
	public void setChildCommCnt(int childCommCnt) {
		this.childCommCnt = childCommCnt;
	}
	public int getMaxCommRefNo() {
		return maxCommRefNo;
	}
	public void setMaxCommRefNo(int maxCommRefNo) {
		this.maxCommRefNo = maxCommRefNo;
	}
	public String getSrchKeyword() {
		return srchKeyword;
	}
	public void setSrchKeyword(String srchKeyword) {
		this.srchKeyword = srchKeyword;
	}
	public int getSrchBrdTyp() {
		return srchBrdTyp;
	}
	public void setSrchBrdTyp(int srchBrdTyp) {
		this.srchBrdTyp = srchBrdTyp;
	}
	public long getBrdSeq() {
		return brdSeq;
	}
	public void setBrdSeq(long brdSeq) {
		this.brdSeq = brdSeq;
	}
	public int getCommNo() {
		return commNo;
	}
	public void setCommNo(int commNo) {
		this.commNo = commNo;
	}
	public int getCommRootNo() {
		return commRootNo;
	}
	public void setCommRootNo(int commRootNo) {
		this.commRootNo = commRootNo;
	}
	public int getCommRefNo() {
		return commRefNo;
	}
	public void setCommRefNo(int commRefNo) {
		this.commRefNo = commRefNo;
	}
	public int getCommDepth() {
		return commDepth;
	}
	public void setCommDepth(int commDepth) {
		this.commDepth = commDepth;
	}
	public String getCommUserNm() {
		return commUserNm;
	}
	public void setCommUserNm(String commUserNm) {
		this.commUserNm = commUserNm;
	}
	public String getCommPasswd() {
		return commPasswd;
	}
	public void setCommPasswd(String commPasswd) {
		this.commPasswd = commPasswd;
	}
	public String getCommCont() {
		return commCont;
	}
	public void setCommCont(String commCont) {
		this.commCont = commCont;
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
	public String getHtmlCommCont() {
		return Utility.toHTML(commCont);
	}
}
