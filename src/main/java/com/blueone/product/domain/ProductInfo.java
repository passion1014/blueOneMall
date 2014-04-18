package com.blueone.product.domain;

import org.springframework.web.multipart.MultipartFile;

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
	private String prdMany;
	private String prdColor;
	
	//상품 옵션
	private String optionKey[];
	private String optionValue[];
	
	
	
	//대 중 소 분류 네임
	private String ctgLargeCode;
	private String ctgLargeName;
	private String ctgMiddleCode;
	private String ctgMiddleName;
	private String ctgSmallCode;
	private String ctgSmallName;

	private String attFilePath;
	
	private MultipartFile proListImgUp;
	private MultipartFile proImg1Up;
	private MultipartFile proImg2Up;
	private MultipartFile proImg3Up;
	private MultipartFile proImg4Up;
	
	private String unit_chk; //다중 checkbox
	private int propIdx;
	
	private int[] optionIdx;
	private String propType;
	private String propName;
	private String propPrdCD;
	
	private String content;
	

	
	public String getPrdColor() {
		return prdColor;
	}
	public void setPrdColor(String prdColor) {
		this.prdColor = prdColor;
	}
	public String getPrdMany() {
		return prdMany;
	}
	public void setPrdMany(String prdMany) {
		this.prdMany = prdMany;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getPropIdx() {
		return propIdx;
	}
	public void setPropIdx(int propIdx) {
		this.propIdx = propIdx;
	}
	public int[] getOptionIdx() {
		return optionIdx;
	}
	public void setOptionIdx(int[] optionIdx) {
		this.optionIdx = optionIdx;
	}
	public String[] getOptionKey() {
		return optionKey;
	}
	public void setOptionKey(String[] optionKey) {
		this.optionKey = optionKey;
	}
	public String[] getOptionValue() {
		return optionValue;
	}
	public void setOptionValue(String[] optionValue) {
		this.optionValue = optionValue;
	}
	public String getPropType() {
		return propType;
	}
	public void setPropType(String propType) {
		this.propType = propType;
	}
	public String getPropName() {
		return propName;
	}
	public void setPropName(String propName) {
		this.propName = propName;
	}
	public String getPropPrdCD() {
		return propPrdCD;
	}
	public void setPropPrdCD(String propPrdCD) {
		this.propPrdCD = propPrdCD;
	}
	
	public String getAttFilePath() {
		return attFilePath;
	}
	public void setAttFilePath(String attFilePath) {
		this.attFilePath = attFilePath;
	}
	public MultipartFile getProListImgUp() {
		return proListImgUp;
	}
	public void setProListImgUp(MultipartFile proListImgUp) {
		this.proListImgUp = proListImgUp;
	}
	public MultipartFile getProImg1Up() {
		return proImg1Up;
	}
	public void setProImg1Up(MultipartFile proImg1Up) {
		this.proImg1Up = proImg1Up;
	}
	public MultipartFile getProImg2Up() {
		return proImg2Up;
	}
	public void setProImg2Up(MultipartFile proImg2Up) {
		this.proImg2Up = proImg2Up;
	}
	public MultipartFile getProImg3Up() {
		return proImg3Up;
	}
	public void setProImg3Up(MultipartFile proImg3Up) {
		this.proImg3Up = proImg3Up;
	}
	public MultipartFile getProImg4Up() {
		return proImg4Up;
	}
	public void setProImg4Up(MultipartFile proImg4Up) {
		this.proImg4Up = proImg4Up;
	}
	public String getUnit_chk() {
		return unit_chk;
	}
	public void setUnit_chk(String unit_chk) {
		this.unit_chk = unit_chk;
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
	public String getCtgLargeCode() {
		return ctgLargeCode;
	}
	public void setCtgLargeCode(String ctgLargeCode) {
		this.ctgLargeCode = ctgLargeCode;
	}
	public String getCtgLargeName() {
		return ctgLargeName;
	}
	public void setCtgLargeName(String ctgLargeName) {
		this.ctgLargeName = ctgLargeName;
	}
	public String getCtgMiddleCode() {
		return ctgMiddleCode;
	}
	public void setCtgMiddleCode(String ctgMiddleCode) {
		this.ctgMiddleCode = ctgMiddleCode;
	}
	public String getCtgMiddleName() {
		return ctgMiddleName;
	}
	public void setCtgMiddleName(String ctgMiddleName) {
		this.ctgMiddleName = ctgMiddleName;
	}
	public String getCtgSmallCode() {
		return ctgSmallCode;
	}
	public void setCtgSmallCode(String ctgSmallCode) {
		this.ctgSmallCode = ctgSmallCode;
	}
	public String getCtgSmallName() {
		return ctgSmallName;
	}
	public void setCtgSmallName(String ctgSmallName) {
		this.ctgSmallName = ctgSmallName;
	}
	
	
	
}
