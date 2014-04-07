package com.blueone.product.domain;

public class ProductInfo {
	
	//제품 보여줄때 필요한 정보
	private int idx;
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
	private String modifyUserId;// 최종수정자

	//제품 상세정보
	private String proCd;
	private int proPrice;	//소비자가
	private int proSellPrc;	//판매가
	private String proModel;	//모델명
	private String proModelNo;	//모델명번호
	private String proBrand;	//제조사
	private String proListCmt;	//목록간략내용
	private String proListImg;	//목록이미지
	private String proImg1;		//상세보기 이미지1
	private String proImg2;		//상세보기 이미지2
	private String proImg3;		//상세보기 이미지3
	private String proImg4;		//상세보기 이미지4
	private String proConts;	//상세보기
	private String proTransInf;	//배송,반품,교환정보
	private String admMeno;		//관리자메모
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
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
	public String getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	public String getProModel() {
		return proModel;
	}
	public void setProModel(String proModel) {
		this.proModel = proModel;
	}
	public String getProModelNo() {
		return proModelNo;
	}
	public void setProModelNo(String proModelNo) {
		this.proModelNo = proModelNo;
	}
	public String getProBrand() {
		return proBrand;
	}
	public void setProBrand(String proBrand) {
		this.proBrand = proBrand;
	}
	public String getProListCmt() {
		return proListCmt;
	}
	public void setProListCmt(String proListCmt) {
		this.proListCmt = proListCmt;
	}
	public String getProListImg() {
		return proListImg;
	}
	public void setProListImg(String proListImg) {
		this.proListImg = proListImg;
	}
	public String getProImg1() {
		return proImg1;
	}
	public void setProImg1(String proImg1) {
		this.proImg1 = proImg1;
	}
	public String getProImg2() {
		return proImg2;
	}
	public void setProImg2(String proImg2) {
		this.proImg2 = proImg2;
	}
	public String getProImg3() {
		return proImg3;
	}
	public void setProImg3(String proImg3) {
		this.proImg3 = proImg3;
	}
	public String getProImg4() {
		return proImg4;
	}
	public void setProImg4(String proImg4) {
		this.proImg4 = proImg4;
	}
	public String getProConts() {
		return proConts;
	}
	public void setProConts(String proConts) {
		this.proConts = proConts;
	}
	public String getProTransInf() {
		return proTransInf;
	}
	public void setProTransInf(String proTransInf) {
		this.proTransInf = proTransInf;
	}
	public String getAdmMeno() {
		return admMeno;
	}
	public void setAdmMeno(String admMeno) {
		this.admMeno = admMeno;
	}
	public int getProPrice() {
		return proPrice;
	}
	public void setProPrice(int proPrice) {
		this.proPrice = proPrice;
	}
	public int getProSellPrc() {
		return proSellPrc;
	}
	public void setProSellPrc(int proSellPrc) {
		this.proSellPrc = proSellPrc;
	}
	public String getProCd() {
		return proCd;
	}
	public void setProCd(String proCd) {
		this.proCd = proCd;
	}
	

	
}
