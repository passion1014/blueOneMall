package com.blueone.common.domain;

/**
 * 서비스 실행결과를 담는 정보
 * @author daniel
 *
 */
public class ResultInfo {
	
	public static final String OK = "1";
	public static final String FAIL = "0";
	
	private String rstCd;		// 결과 코드 (1:성공, 0:실패)
	private String rstMsgCd;	// 결과 메세지 코드
	private String rstMsg;		// 결과 메세지
	
	public ResultInfo(){}
	public ResultInfo(String rstCd){
		this.rstCd = rstCd;
	}
	
	public boolean isOk() {
		return rstCd.equals("1");
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
