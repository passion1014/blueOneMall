package com.blueone.common.domain;

import com.blueone.login.domain.LoginSessionModel;


public class BaseModel {
	/** 로그인 세션정보 */
	private LoginSessionModel loginSessionModel = new LoginSessionModel();
	private String pgmId = "";			/** 프로그램ID */
	private String pgmNm = "";			/** 프로그램명 */
	private String menuPath = "";		/** 메뉴경로 */
	private String menuId = "";			/** 메뉴ID */
	private String subMenuId = "";		/** 부메뉴ID */
	private int	currentPage = 1;		/** 현재페이지 번호 */	
	private int	totalPage = 0;			/** 전체페이지수 */	
	private int totalCount = 0;			/** 전체데이터 */
	private String orderColumn = "";	/** 정렬컬럼 */
	private String orderSequence = "";	/** 정렬방법 */
	private int	rowsPerPage	= 10;		/** 페이지 당 ROW수 */
	private int pagesPerPage = 10;		/** 페이지 당 PAGE수  */
	
	public LoginSessionModel getLoginSessionModel() {
		return loginSessionModel;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getSubMenuId() {
		return subMenuId;
	}
	public void setSubMenuId(String subMenuId) {
		this.subMenuId = subMenuId;
	}
	public int getPagesPerPage() {
		return pagesPerPage;
	}
	public void setPagesPerPage(int pagesPerPage) {
		this.pagesPerPage = pagesPerPage;
	}
	public void setLoginSessionModel(LoginSessionModel loginSessionModel) {
		this.loginSessionModel = loginSessionModel;
	}
	public String getPgmId() {
		return pgmId;
	}
	public void setPgmId(String pgmId) {
		this.pgmId = pgmId;
	}
	public String getPgmNm() {
		return pgmNm;
	}
	public void setPgmNm(String pgmNm) {
		this.pgmNm = pgmNm;
	}
	public String getMenuPath() {
		return menuPath;
	}
	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public String getOrderColumn() {
		return orderColumn;
	}
	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}
	public String getOrderSequence() {
		return orderSequence;
	}
	public void setOrderSequence(String orderSequence) {
		this.orderSequence = orderSequence;
	}
	public int getRowsPerPage() {
		return rowsPerPage;
	}
	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}
	
}
