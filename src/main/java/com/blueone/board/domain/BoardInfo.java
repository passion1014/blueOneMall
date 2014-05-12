package com.blueone.board.domain;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class BoardInfo {
	private long brdSeq;		// 게시물일련번호
	private int brdTyp;			// 게시판유형(FK)
	private int srchBrdTyp;		// 게시판유형(FK)
	private String brdTypNm;	// 게시판명
	private long rootSeq;		// 부모일련번호
	private long refSeq;		// 자식일련번호
	private int depth;			// 게시물깊이
	private String brdCodeType;	// 해당게시판이 다른 테이블과 조인될경우 그 테이블의 PK의 종류(01:상품QnA)
	private String brdCodeKey;	// 해당게시판이 다른 테이블과 조인될경우 그 테이블의 PK
	private String passwd;		// 비밀번호
	private String title;		// 제목
	private String content;		// 내용
	private int hit;			// 조회수
	private int commCnt;		// 덧글수
	private int replyCnt;		// 답변수
	private String delYn;		// 삭제여부
	private String delDt;		// 삭제일자
	private String delUser;		// 삭제자
	private String insDt;		// 최초입력일
	private String insDtTyp1;	// 최초입력일
	private String insUser;		// 최초입력자
	private String updDt;		// 최종변경일
	private String updUser;		// 최종변경자
	private MultipartFile[] uploadFile;		// 첩부파일
	private MultipartFile imgFile;			// 첨부 대표이미지
	private String procSt;		// 처리상태
	private String userNm;		// 작성자명
	private String noticeYn;		// 공지여부
	private int rnum;				// ROWNUM
	private String todayYn;
	private int[] flNo;
	private String publicYmd;
	private String orgPublicYmd;
	private String publicNm;
	private String repreImage;
	private String[] contImageFile;	// 내용에 추가된 이미지파일
	private List<BoardAttachFileInfo> attachFiles;
	
	
	public String getInsDtTyp1() {
		return insDtTyp1;
	}
	public void setInsDtTyp1(String insDtTyp1) {
		this.insDtTyp1 = insDtTyp1;
	}
	public List<BoardAttachFileInfo> getAttachFiles() {
		return attachFiles;
	}
	public void setAttachFiles(List<BoardAttachFileInfo> attachFiles) {
		this.attachFiles = attachFiles;
	}
	public String[] getContImageFile() {
		return contImageFile;
	}
	public void setContImageFile(String[] contImageFile) {
		this.contImageFile = contImageFile;
	}
	public String getOrgPublicYmd() {
		return orgPublicYmd;
	}
	public void setOrgPublicYmd(String orgPublicYmd) {
		this.orgPublicYmd = orgPublicYmd;
	}
	public MultipartFile getImgFile() {
		return imgFile;
	}
	public void setImgFile(MultipartFile imgFile) {
		this.imgFile = imgFile;
	}
	public String getRepreImage() {
		return repreImage;
	}
	public void setRepreImage(String repreImage) {
		this.repreImage = repreImage;
	}
	public String getPublicYmd() {
		return publicYmd;
	}
	public void setPublicYmd(String publicYmd) {
		this.publicYmd = publicYmd;
	}
	public String getPublicNm() {
		return publicNm;
	}
	public void setPublicNm(String publicNm) {
		this.publicNm = publicNm;
	}
	public int[] getFlNo() {
		return flNo;
	}
	public void setFlNo(int[] flNo) {
		this.flNo = flNo;
	}
	public int getSrchBrdTyp() {
		return srchBrdTyp;
	}
	public void setSrchBrdTyp(int srchBrdTyp) {
		this.srchBrdTyp = srchBrdTyp;
	}
	public MultipartFile[] getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile[] uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getTodayYn() {
		return todayYn;
	}
	public void setTodayYn(String todayYn) {
		this.todayYn = todayYn;
	}
	public String getNoticeYn() {
		return noticeYn;
	}
	public void setNoticeYn(String noticeYn) {
		this.noticeYn = noticeYn;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public String getBrdTypNm() {
		return brdTypNm;
	}
	public void setBrdTypNm(String brdTypNm) {
		this.brdTypNm = brdTypNm;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getProcSt() {
		return procSt;
	}
	public void setProcSt(String procSt) {
		this.procSt = procSt;
	}
	public long getBrdSeq() {
		return brdSeq;
	}
	public void setBrdSeq(long brdSeq) {
		this.brdSeq = brdSeq;
	}
	public int getBrdTyp() {
		return brdTyp;
	}
	public void setBrdTyp(int brdTyp) {
		this.brdTyp = brdTyp;
	}
	public long getRootSeq() {
		return rootSeq;
	}
	public void setRootSeq(long rootSeq) {
		this.rootSeq = rootSeq;
	}
	public long getRefSeq() {
		return refSeq;
	}
	public void setRefSeq(long refSeq) {
		this.refSeq = refSeq;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getCommCnt() {
		return commCnt;
	}
	public void setCommCnt(int commCnt) {
		this.commCnt = commCnt;
	}
	public int getReplyCnt() {
		return replyCnt;
	}
	public void setReplyCnt(int replyCnt) {
		this.replyCnt = replyCnt;
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
	public String getContText() {
		if (content != null) {
			return content.replaceAll("(?i)<((/)?)(object|param|embed|img|OBJECT|PARAM|EMBED|IMG|Object|Param|Embed|Img)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>","");
		} else {
			return "";
		}
	}
	public String getBrdCodeType() {
		return brdCodeType;
	}
	public void setBrdCodeType(String brdCodeType) {
		this.brdCodeType = brdCodeType;
	}
	public String getBrdCodeKey() {
		return brdCodeKey;
	}
	public void setBrdCodeKey(String brdCodeKey) {
		this.brdCodeKey = brdCodeKey;
	}
}
