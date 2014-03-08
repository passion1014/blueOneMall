package com.blueone.common.domain;

import klac.common.BaseModel;

public class StatModel extends BaseModel {
	private int srchTarSeq;
	private int srchSubSeq;
	private int srchPgmSeq;
	private String srchFromYmd;		// 기간(시작일자)
	private String srchToYmd;		// 기간(종료일자)
	private String srchTyp;			// 검색구분(D:일, W:주, M:월)
	private String srchForms;			// 검색구분(EXA:사례형, EXP:체험형)
	private String srchAreaCd;
	private String srchGuBun;       // 구 분(O:1일, T:1박2일)
	private String groupNm;
	private int mg02001Cnt;
	private int mg02002Cnt;
	private int mg02901Cnt;
	private int mg02902Cnt;
	private int mg04101Cnt;
	private int mg04201Cnt;
	private int tarCnt;
	private int pgmCnt;
	private int totalCnt;
	
	private long effSeq;
	private String effYmd;
	private int tarSeq;
	private String tarNm;
	private String areaCd;
	private String areaNm;
	private String detailArea;
	private String guBun;
	private int spouseCnt;
	private int childCnt;
	private int etcRelCnt;
	private int cenEmpCnt;
	private int etcCnt;
	private int srTarCnt;
	private int srSpouseCnt;
	private int srEtcCnt;
	private String insDt;
	private String insUser;
	private String updDt;
	private String updUser;
	private String[] countryCd;
	private String[] countryCnt;
	private String[] queKnd;
	private String[] queNo;
	private String[] ansMinus2;
	private String[] ansMinus1;
	private String[] ansDefault;
	private String[] ansPlus1;
	private String[] ansPlus2;
	private int sumAnsMinus2;
	private int sumAnsMinus1;
	private int sumAnsDefault;
	private int sumAnsPlus1;
	private int sumAnsPlus2;
	private int ansMinus2Per;
	private int ansMinus1Per;
	private int ansDefaultPer;
	private int ansPlus1Per;
	private int ansPlus2Per;
	private int rnum;
	
	public String getSrchAreaCd() {
		return srchAreaCd;
	}
	public void setSrchAreaCd(String srchAreaCd) {
		this.srchAreaCd = srchAreaCd;
	}
	public int getAnsMinus2Per() {
		return ansMinus2Per;
	}
	public void setAnsMinus2Per(int ansMinus2Per) {
		this.ansMinus2Per = ansMinus2Per;
	}
	public int getAnsMinus1Per() {
		return ansMinus1Per;
	}
	public void setAnsMinus1Per(int ansMinus1Per) {
		this.ansMinus1Per = ansMinus1Per;
	}
	public int getAnsDefaultPer() {
		return ansDefaultPer;
	}
	public void setAnsDefaultPer(int ansDefaultPer) {
		this.ansDefaultPer = ansDefaultPer;
	}
	public int getAnsPlus1Per() {
		return ansPlus1Per;
	}
	public void setAnsPlus1Per(int ansPlus1Per) {
		this.ansPlus1Per = ansPlus1Per;
	}
	public int getAnsPlus2Per() {
		return ansPlus2Per;
	}
	public void setAnsPlus2Per(int ansPlus2Per) {
		this.ansPlus2Per = ansPlus2Per;
	}
	public int getSumAnsMinus2() {
		return sumAnsMinus2;
	}
	public void setSumAnsMinus2(int sumAnsMinus2) {
		this.sumAnsMinus2 = sumAnsMinus2;
	}
	public int getSumAnsMinus1() {
		return sumAnsMinus1;
	}
	public void setSumAnsMinus1(int sumAnsMinus1) {
		this.sumAnsMinus1 = sumAnsMinus1;
	}
	public int getSumAnsDefault() {
		return sumAnsDefault;
	}
	public void setSumAnsDefault(int sumAnsDefault) {
		this.sumAnsDefault = sumAnsDefault;
	}
	public int getSumAnsPlus1() {
		return sumAnsPlus1;
	}
	public void setSumAnsPlus1(int sumAnsPlus1) {
		this.sumAnsPlus1 = sumAnsPlus1;
	}
	public int getSumAnsPlus2() {
		return sumAnsPlus2;
	}
	public void setSumAnsPlus2(int sumAnsPlus2) {
		this.sumAnsPlus2 = sumAnsPlus2;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public int getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}
	public String getTarNm() {
		return tarNm;
	}
	public void setTarNm(String tarNm) {
		this.tarNm = tarNm;
	}
	public String getAreaNm() {
		return areaNm;
	}
	public void setAreaNm(String areaNm) {
		this.areaNm = areaNm;
	}
	public String getEffYmd() {
		return effYmd;
	}
	public void setEffYmd(String effYmd) {
		this.effYmd = effYmd;
	}
	public int getTarSeq() {
		return tarSeq;
	}
	public void setTarSeq(int tarSeq) {
		this.tarSeq = tarSeq;
	}
	public String getAreaCd() {
		return areaCd;
	}
	public void setAreaCd(String areaCd) {
		this.areaCd = areaCd;
	}
	public String getDetailArea() {
		return detailArea;
	}
	public void setDetailArea(String detailArea) {
		this.detailArea = detailArea;
	}
	public int getSpouseCnt() {
		return spouseCnt;
	}
	public void setSpouseCnt(int spouseCnt) {
		this.spouseCnt = spouseCnt;
	}
	public int getChildCnt() {
		return childCnt;
	}
	public void setChildCnt(int childCnt) {
		this.childCnt = childCnt;
	}
	public int getEtcRelCnt() {
		return etcRelCnt;
	}
	public void setEtcRelCnt(int etcRelCnt) {
		this.etcRelCnt = etcRelCnt;
	}
	public int getCenEmpCnt() {
		return cenEmpCnt;
	}
	public void setCenEmpCnt(int cenEmpCnt) {
		this.cenEmpCnt = cenEmpCnt;
	}
	public int getEtcCnt() {
		return etcCnt;
	}
	public void setEtcCnt(int etcCnt) {
		this.etcCnt = etcCnt;
	}
	public int getSrTarCnt() {
		return srTarCnt;
	}
	public void setSrTarCnt(int srTarCnt) {
		this.srTarCnt = srTarCnt;
	}
	public int getSrSpouseCnt() {
		return srSpouseCnt;
	}
	public void setSrSpouseCnt(int srSpouseCnt) {
		this.srSpouseCnt = srSpouseCnt;
	}
	public int getSrEtcCnt() {
		return srEtcCnt;
	}
	public void setSrEtcCnt(int srEtcCnt) {
		this.srEtcCnt = srEtcCnt;
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
	public String[] getQueKnd() {
		return queKnd;
	}
	public void setQueKnd(String[] queKnd) {
		this.queKnd = queKnd;
	}
	public String[] getQueNo() {
		return queNo;
	}
	public void setQueNo(String[] queNo) {
		this.queNo = queNo;
	}
	public String[] getAnsMinus2() {
		return ansMinus2;
	}
	public void setAnsMinus2(String[] ansMinus2) {
		this.ansMinus2 = ansMinus2;
	}
	public String[] getAnsMinus1() {
		return ansMinus1;
	}
	public void setAnsMinus1(String[] ansMinus1) {
		this.ansMinus1 = ansMinus1;
	}
	public String[] getAnsDefault() {
		return ansDefault;
	}
	public void setAnsDefault(String[] ansDefault) {
		this.ansDefault = ansDefault;
	}
	public String[] getAnsPlus1() {
		return ansPlus1;
	}
	public void setAnsPlus1(String[] ansPlus1) {
		this.ansPlus1 = ansPlus1;
	}
	public String[] getAnsPlus2() {
		return ansPlus2;
	}
	public void setAnsPlus2(String[] ansPlus2) {
		this.ansPlus2 = ansPlus2;
	}
	public long getEffSeq() {
		return effSeq;
	}
	public void setEffSeq(long effSeq) {
		this.effSeq = effSeq;
	}
	public int getPgmCnt() {
		return pgmCnt;
	}
	public void setPgmCnt(int pgmCnt) {
		this.pgmCnt = pgmCnt;
	}
	public int getSrchPgmSeq() {
		return srchPgmSeq;
	}
	public void setSrchPgmSeq(int srchPgmSeq) {
		this.srchPgmSeq = srchPgmSeq;
	}
	public int getSrchSubSeq() {
		return srchSubSeq;
	}
	public void setSrchSubSeq(int srchSubSeq) {
		this.srchSubSeq = srchSubSeq;
	}
	public int getTarCnt() {
		return tarCnt;
	}
	public void setTarCnt(int tarCnt) {
		this.tarCnt = tarCnt;
	}
	public int getSrchTarSeq() {
		return srchTarSeq;
	}
	public void setSrchTarSeq(int srchTarSeq) {
		this.srchTarSeq = srchTarSeq;
	}
	public String getGroupNm() {
		return groupNm;
	}
	public void setGroupNm(String groupNm) {
		this.groupNm = groupNm;
	}
	public int getMg02001Cnt() {
		return mg02001Cnt;
	}
	public void setMg02001Cnt(int mg02001Cnt) {
		this.mg02001Cnt = mg02001Cnt;
	}
	public int getMg02002Cnt() {
		return mg02002Cnt;
	}
	public void setMg02002Cnt(int mg02002Cnt) {
		this.mg02002Cnt = mg02002Cnt;
	}
	public int getMg02901Cnt() {
		return mg02901Cnt;
	}
	public void setMg02901Cnt(int mg02901Cnt) {
		this.mg02901Cnt = mg02901Cnt;
	}
	public int getMg02902Cnt() {
		return mg02902Cnt;
	}
	public void setMg02902Cnt(int mg02902Cnt) {
		this.mg02902Cnt = mg02902Cnt;
	}
	public int getMg04101Cnt() {
		return mg04101Cnt;
	}
	public void setMg04101Cnt(int mg04101Cnt) {
		this.mg04101Cnt = mg04101Cnt;
	}
	public int getMg04201Cnt() {
		return mg04201Cnt;
	}
	public void setMg04201Cnt(int mg04201Cnt) {
		this.mg04201Cnt = mg04201Cnt;
	}
	public String getSrchTyp() {
		return srchTyp;
	}
	public void setSrchTyp(String srchTyp) {
		this.srchTyp = srchTyp;
	}
	public String getSrchForms() {
		return srchForms;
	}
	public void setSrchForms(String srchForms) {
		this.srchForms = srchForms;
	}
	public String getSrchFromYmd() {
		return srchFromYmd;
	}
	public void setSrchFromYmd(String srchFromYmd) {
		this.srchFromYmd = srchFromYmd;
	}
	public String getSrchToYmd() {
		return srchToYmd;
	}
	public void setSrchToYmd(String srchToYmd) {
		this.srchToYmd = srchToYmd;
	}
	public String[] getCountryCd() {
		return countryCd;
	}
	public void setCountryCd(String[] countryCd) {
		this.countryCd = countryCd;
	}
	public String[] getCountryCnt() {
		return countryCnt;
	}
	public void setCountryCnt(String[] countryCnt) {
		this.countryCnt = countryCnt;
	}
	public String getGuBun() {
		return guBun;
	}
	public void setGuBun(String guBun) {
		this.guBun = guBun;
	}
	public String getSrchGuBun() {
		return srchGuBun;
	}
	public void setSrchGuBun(String srchGuBun) {
		this.srchGuBun = srchGuBun;
	}
}
