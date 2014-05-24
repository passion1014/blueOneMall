package com.blueone.admin.domain;

public class SchWordInfo {
	private int swRank;
	private String swWord;
	private int swHit;
	private boolean showMain=false;
	
	
	public boolean isShowMain() {
		return showMain;
	}
	public void setShowMain(boolean showMain) {
		this.showMain = showMain;
	}
	public int getSwHit() {
		return swHit;
	}
	public void setSwHit(int swHit) {
		this.swHit = swHit;
	}
	public int getSwRank() {
		return swRank;
	}
	public void setSwRank(int swRank) {
		this.swRank = swRank;
	}
	public String getSwWord() {
		return swWord;
	}
	public void setSwWord(String swWord) {
		this.swWord = swWord;
	}
	
	
}
