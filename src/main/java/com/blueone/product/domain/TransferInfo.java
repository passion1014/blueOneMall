package com.blueone.product.domain;

public class TransferInfo {

	
	private int idx;
	private String tTitle;
	private String tContents;
	String content;
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String gettTitle() {
		return tTitle;
	}
	public void settTitle(String tTitle) {
		this.tTitle = tTitle;
	}
	public String gettContents() {
		return tContents;
	}
	public void settContents(String tContents) {
		this.tContents = tContents;
	}
	
	
}
