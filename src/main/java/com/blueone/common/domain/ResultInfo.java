package com.blueone.common.domain;

/**
 * ���� �������� ��� ����
 * @author daniel
 *
 */
public class ResultInfo {
	
	public static final String OK = "1";
	public static final String FAIL = "0";
	
	private String rstCd;		// ��� �ڵ� (1:����, 0:����)
	private String rstMsgCd;	// ��� �޼��� �ڵ�
	private String rstMsg;		// ��� �޼���
	
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
