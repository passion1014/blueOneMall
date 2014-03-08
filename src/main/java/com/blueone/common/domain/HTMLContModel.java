package com.blueone.common.domain;

import klac.common.Utility;

public class HTMLContModel {
	private int htmlSeq;			// HTML일련번호
	private String htmlKnd;			// HTML구분[MG05]
	private String htmlKndNm;		// HTML구분[MG05]
	private String title;			// 제목
	private String cont;			// 내용
	private String explain;			// 설명
	private String insDt;			// 최초입력일
	private String insUser;			// 최초입력자
	private String updDt;			// 최종변경일
	private String updUser;			// 최종변경자
	private String srchHtmlKnd;		// 컨텐츠구분
	
	public String getSrchHtmlKnd() {
		return srchHtmlKnd;
	}
	public void setSrchHtmlKnd(String srchHtmlKnd) {
		this.srchHtmlKnd = srchHtmlKnd;
	}
	public String getHtmlKndNm() {
		return htmlKndNm;
	}
	public void setHtmlKndNm(String htmlKndNm) {
		this.htmlKndNm = htmlKndNm;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	public int getHtmlSeq() {
		return htmlSeq;
	}
	public void setHtmlSeq(int htmlSeq) {
		this.htmlSeq = htmlSeq;
	}
	public String getHtmlKnd() {
		return htmlKnd;
	}
	public void setHtmlKnd(String htmlKnd) {
		this.htmlKnd = htmlKnd;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	
	public String getTextCont() {
		String text = "";
		text = Utility.replace(cont, "<br />", "\n");
		text = Utility.replace(text, "<br>", "\n");
		text = Utility.replace(text, "<br/>", "\n");
		text = Utility.replace(text, "<p>", "");
		text = Utility.replace(text, "</p>", "");
		
		return text;
	}
	
}
