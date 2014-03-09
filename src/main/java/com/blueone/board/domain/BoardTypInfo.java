package com.blueone.board.domain;

public class BoardTypInfo {
	private int brdTyp;				// 게시판유형
	private String title;			// 게시판명
	private String attaYn;			// 첨부파일여부
	private String replyYn;			// 답변글여부
	private String commYn;			// 덧글여부
	private String secrYn;			// 비밀글여부
	private String newViewYn;		// 신규글표시여부
	private String commViewYn;		// 덧글표시여부
	private String attaViewYn;		// 첨부파일표시여부
	private String listReadAuth;	// 리스트읽기권한
	private String listReadAuthNm;	// 리스트읽기권한
	private String writeAuth;		// 쓰기권한
	private String writeAuthNm;		// 쓰기권한
	private String readAuth;		// 읽기권한
	private String readAuthNm;		// 읽기권한
	private String replyWriteAuth;	// 답글쓰기권한
	private String replyReadAuth;	// 답글읽기권한
	private String commWriteAuth;	// 덧글쓰기권한
	private String commReadAuth;	// 덧글읽기권한
	private String delYn;			// 삭제여부
	private String delDt;			// 삭제일자
	private String delUser;			// 삭제자
	private String insDt;			// 최초입력일
	private String insUser;			// 최초입력자
	private String updDt;			// 최종변경일
	private String updUser;			// 최종변경자
	private int totalCnt;			// 전체게시물
	private int todayCnt;			// 오늘게시물
	private String imgUseYn;		// 이미지사용여부
	private String publicUseYn;		// 발간자사용여부
	
	
	public String getImgUseYn() {
		return imgUseYn;
	}
	public void setImgUseYn(String imgUseYn) {
		this.imgUseYn = imgUseYn;
	}
	public String getPublicUseYn() {
		return publicUseYn;
	}
	public void setPublicUseYn(String publicUseYn) {
		this.publicUseYn = publicUseYn;
	}
	public String getListReadAuthNm() {
		return listReadAuthNm;
	}
	public void setListReadAuthNm(String listReadAuthNm) {
		this.listReadAuthNm = listReadAuthNm;
	}
	public String getWriteAuthNm() {
		return writeAuthNm;
	}
	public void setWriteAuthNm(String writeAuthNm) {
		this.writeAuthNm = writeAuthNm;
	}
	public String getReadAuthNm() {
		return readAuthNm;
	}
	public void setReadAuthNm(String readAuthNm) {
		this.readAuthNm = readAuthNm;
	}
	public int getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}
	public int getTodayCnt() {
		return todayCnt;
	}
	public void setTodayCnt(int todayCnt) {
		this.todayCnt = todayCnt;
	}
	public int getBrdTyp() {
		return brdTyp;
	}
	public void setBrdTyp(int brdTyp) {
		this.brdTyp = brdTyp;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAttaYn() {
		return attaYn;
	}
	public void setAttaYn(String attaYn) {
		this.attaYn = attaYn;
	}
	public String getReplyYn() {
		return replyYn;
	}
	public void setReplyYn(String replyYn) {
		this.replyYn = replyYn;
	}
	public String getCommYn() {
		return commYn;
	}
	public void setCommYn(String commYn) {
		this.commYn = commYn;
	}
	public String getSecrYn() {
		return secrYn;
	}
	public void setSecrYn(String secrYn) {
		this.secrYn = secrYn;
	}
	public String getNewViewYn() {
		return newViewYn;
	}
	public void setNewViewYn(String newViewYn) {
		this.newViewYn = newViewYn;
	}
	public String getCommViewYn() {
		return commViewYn;
	}
	public void setCommViewYn(String commViewYn) {
		this.commViewYn = commViewYn;
	}
	public String getAttaViewYn() {
		return attaViewYn;
	}
	public void setAttaViewYn(String attaViewYn) {
		this.attaViewYn = attaViewYn;
	}
	public String getListReadAuth() {
		return listReadAuth;
	}
	public void setListReadAuth(String listReadAuth) {
		this.listReadAuth = listReadAuth;
	}
	public String getWriteAuth() {
		return writeAuth;
	}
	public void setWriteAuth(String writeAuth) {
		this.writeAuth = writeAuth;
	}
	public String getReadAuth() {
		return readAuth;
	}
	public void setReadAuth(String readAuth) {
		this.readAuth = readAuth;
	}
	public String getReplyWriteAuth() {
		return replyWriteAuth;
	}
	public void setReplyWriteAuth(String replyWriteAuth) {
		this.replyWriteAuth = replyWriteAuth;
	}
	public String getReplyReadAuth() {
		return replyReadAuth;
	}
	public void setReplyReadAuth(String replyReadAuth) {
		this.replyReadAuth = replyReadAuth;
	}
	public String getCommWriteAuth() {
		return commWriteAuth;
	}
	public void setCommWriteAuth(String commWriteAuth) {
		this.commWriteAuth = commWriteAuth;
	}
	public String getCommReadAuth() {
		return commReadAuth;
	}
	public void setCommReadAuth(String commReadAuth) {
		this.commReadAuth = commReadAuth;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public String getDelDt() {
		return delDt;
	}
	public void setDelDt(String delDt) {
		this.delDt = delDt;
	}
	public String getDelUser() {
		return delUser;
	}
	public void setDelUser(String delUser) {
		this.delUser = delUser;
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
	
}
