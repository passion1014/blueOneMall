package com.blueone.board.domain;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;

import com.blueone.common.domain.BaseInfo;


public class BoardSrchInfo extends BaseInfo {
	private long brdSeq;
	private long srchBrdSeq;
	private int srchBrdTyp;				// 게시판유형
	private int brdTyp;					// 게시판유형
	private String srchTitle;			// 제목
	private String srchUserNm;			// 작성자
	private String srchInsFromYmd;		// 등록시작일
	private String srchInsToYmd;		// 등록종료일
	private String srchKeyword;			// 기본검색어
	private String brdCodeType;			// 해당게시판이 다른 테이블과 조인될경우 그 테이블의 PK의 종류(01:상품QnA)
	private String brdCodeKey;			// 해당게시판이 다른 테이블과 조인될경우 그 테이블의 PK
	private String userId;
	private long[] check;
	private long[] noticeBrdSeq;
	
	
	public long[] getNoticeBrdSeq() {
		return noticeBrdSeq;
	}
	public void setNoticeBrdSeq(long[] noticeBrdSeq) {
		this.noticeBrdSeq = noticeBrdSeq;
	}
	public int getBrdTyp() {
		return brdTyp;
	}
	public void setBrdTyp(int brdTyp) {
		this.brdTyp = brdTyp;
	}
	public long getBrdSeq() {
		return brdSeq;
	}
	public void setBrdSeq(long brdSeq) {
		this.brdSeq = brdSeq;
	}
	public long getSrchBrdSeq() {
		return srchBrdSeq;
	}
	public void setSrchBrdSeq(long srchBrdSeq) {
		this.srchBrdSeq = srchBrdSeq;
	}
	public String getSrchKeyword() {
		return srchKeyword;
	}
	public void setSrchKeyword(String srchKeyword) {
		this.srchKeyword = srchKeyword;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public long[] getCheck() {
		return check;
	}
	public void setCheck(long[] check) {
		this.check = check;
	}
	public int getSrchBrdTyp() {
		return srchBrdTyp;
	}
	public void setSrchBrdTyp(int srchBrdTyp) {
		this.srchBrdTyp = srchBrdTyp;
	}
	public String getSrchTitle() {
		return srchTitle;
	}
	public void setSrchTitle(String srchTitle) {
		this.srchTitle = srchTitle;
	}
	public String getSrchInsFromYmd() {
		return srchInsFromYmd;
	}
	public void setSrchInsFromYmd(String srchInsFromYmd) {
		this.srchInsFromYmd = srchInsFromYmd;
	}
	public String getSrchInsToYmd() {
		return srchInsToYmd;
	}
	public void setSrchInsToYmd(String srchInsToYmd) {
		this.srchInsToYmd = srchInsToYmd;
	}
	public String getSrchUserNm() {
		return srchUserNm;
	}
	public void setSrchUserNm(String srchUserNm) {
		this.srchUserNm = srchUserNm;
	}
	public String getBrdCodeType() {
		return brdCodeType;
	}
	public void setBrdCodeType(String brdCodeType) {
		this.brdCodeType = brdCodeType;
	}
	public String getBrdCodeKey() {
		return brdCodeKey;
	}
	public void setBrdCodeKey(String brdCodeKey) {
		this.brdCodeKey = brdCodeKey;
	}
	
}
