package com.blueone.board.domain;


public class BoardStatModel {
	private long todayCnt;			// 금일등록된전체글수
	private long todayCommentCnt;	// 금일등록된덧글전체수
	private long todayHitCnt;		// 금일등록된덧글전체수
	
	public long getTodayCnt() {
		return todayCnt;
	}
	public void setTodayCnt(long todayCnt) {
		this.todayCnt = todayCnt;
	}
	public long getTodayCommentCnt() {
		return todayCommentCnt;
	}
	public void setTodayCommentCnt(long todayCommentCnt) {
		this.todayCommentCnt = todayCommentCnt;
	}
	public long getTodayHitCnt() {
		return todayHitCnt;
	}
	public void setTodayHitCnt(long todayHitCnt) {
		this.todayHitCnt = todayHitCnt;
	}
}
