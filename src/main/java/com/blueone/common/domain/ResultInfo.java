package com.blueone.common.domain;

/**
 * @author daniel
 *
 */
public class ResultInfo {
	
	public static final String OK = "1";
	public static final String FAIL = "0";
	
	private String rstCd;		// 1:OK, 0:FAIL
	private String rstMsgCd;	// 메세지코드
	private String rstMsg;		// 메세지내용
	
	public ResultInfo(){}
	public ResultInfo(String rstCd){
		this.rstCd = rstCd;
	}
	
	public boolean isOk() {
		return "1".equals(rstCd);
	}
	
	public String getRstCd() {
		return rstCd;
	}
	public void setRstCd(String rstCd) {
		this.rstCd = rstCd;
	}
	public String getRstMsgCd() {
		return rstMsgCd;
	}
	public void setRstMsgCd(String rstMsgCd) {
		this.rstMsgCd = rstMsgCd;
	}
	public String getRstMsg() {
		return rstMsg;
	}
	public void setRstMsg(String rstMsg) {
		this.rstMsg = rstMsg;
	}
	
	
}
