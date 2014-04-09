package com.blueone.product.domain;

public class ProductInfo {
	
	//제품 보여줄때 필요한 정보
	private String prdCd;		// 상품코드
	private String prdDp;		// 진열상태
	private int  prdOrd;		// 정렬
	private String prdNm;		// 상품명
	private String prdCtgL;		// 대분류코드
	private String prdCtgM;		// 중분류코드
	private String prdCtgS;		// 소분류코드
	private String prdSpe1;		// 특수설정-베스트
	private String prdSpe2;		// 특수설정-행사품목
	private String schWord;		// 검색단어
	private String fromDate;	// 적용시작일
	private String toDate;		// 적용종료일
	private String regDate;		// 등록일
	private String lastDate;	// 최종수정일
	private int prdPrice;		//소비자가
	private int prdSellPrc;		//판매가
	private String prdModel;	//모델명
	private String prdModelNo;	//모델명번호
	private String prdBrand;	//제조사
	private String prdListCmt;	//목록간략내용
	private String prdConts;	//상세보기
	private int prdTransInf;	//배송,반품,교환정보
	private String admMemo;		//관리자메모
	private String modifyUserId;// 최종수정자
	
	public String getPrdCd() {
		return prdCd;
	}
	public void setPrdCd(String prdCd) {
		this.prdCd = prdCd;
	}
	public String getPrdDp() {
		return prdDp;
	}
	public void setPrdDp(String prdDp) {
		this.prdDp = prdDp;
	}
	public int getPrdOrd() {
		return prdOrd;
	}
	public void setPrdOrd(int prdOrd) {
		this.prdOrd = prdOrd;
	}
	public String getPrdNm() {
		return prdNm;
	}
	public void setPrdNm(String prdNm) {
		this.prdNm = prdNm;
	}
	public String getPrdCtgL() {
		return prdCtgL;
	}
	public void setPrdCtgL(String prdCtgL) {
		this.prdCtgL = prdCtgL;
	}
	public String getPrdCtgM() {
		return prdCtgM;
	}
	public void setPrdCtgM(String prdCtgM) {
		this.prdCtgM = prdCtgM;
	}
	public String getPrdCtgS() {
		return prdCtgS;
	}
	public void setPrdCtgS(String prdCtgS) {
		this.prdCtgS = prdCtgS;
	}
	public String getPrdSpe1() {
		return prdSpe1;
	}
	public void setPrdSpe1(String prdSpe1) {
		this.prdSpe1 = prdSpe1;
	}
	public String getPrdSpe2() {
		return prdSpe2;
	}
	public void setPrdSpe2(String prdSpe2) {
		this.prdSpe2 = prdSpe2;
	}
	public String getSchWord() {
		return schWord;
	}
	public void setSchWord(String schWord) {
		this.schWord = schWord;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getLastDate() {
		return lastDate;
	}
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}


	public int getPrdPrice() {
		return prdPrice;
	}
	public void setPrdPrice(int prdPrice) {
		this.prdPrice = prdPrice;
	}
	public int getPrdSellPrc() {
		return prdSellPrc;
	}
	public void setPrdSellPrc(int prdSellPrc) {
		this.prdSellPrc = prdSellPrc;
	}
	public String getPrdModel() {
		return prdModel;
	}
	public void setPrdModel(String prdModel) {
		this.prdModel = prdModel;
	}
	public String getPrdModelNo() {
		return prdModelNo;
	}
	public void setPrdModelNo(String prdModelNo) {
		this.prdModelNo = prdModelNo;
	}
	public String getPrdBrand() {
		return prdBrand;
	}
	public void setPrdBrand(String prdBrand) {
		this.prdBrand = prdBrand;
	}
	public String getPrdListCmt() {
		return prdListCmt;
	}
	public void setPrdListCmt(String prdListCmt) {
		this.prdListCmt = prdListCmt;
	}
	public String getPrdConts() {
		return prdConts;
	}
	public void setPrdConts(String prdConts) {
		this.prdConts = prdConts;
	}
	public int getPrdTransInf() {
		return prdTransInf;
	}
	public void setPrdTransInf(int prdTransInf) {
		this.prdTransInf = prdTransInf;
	}
	public String getAdmMemo() {
		return admMemo;
	}
	public void setAdmMemo(String admMemo) {
		this.admMemo = admMemo;
	}
	public String getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	
	
	
}
