package com.blueone.admin.domain;

public class ConfigInfo {
	private String siteName;
	private String siteDomain;
	private String siteMaster;
	private String sitePh;
	private String siteHp;
	private String siteEmail;
	private int buyPrice;
	private int trasferPrice;
	private int pointConf;
	private String transferOffice;
	
	public int getPointConf() {
		return pointConf;
	}
	public void setPointConf(int pointConf) {
		this.pointConf = pointConf;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getSiteDomain() {
		return siteDomain;
	}
	public void setSiteDomain(String siteDomain) {
		this.siteDomain = siteDomain;
	}
	public String getSiteMaster() {
		return siteMaster;
	}
	public void setSiteMaster(String siteMaster) {
		this.siteMaster = siteMaster;
	}
	public String getSitePh() {
		return sitePh;
	}
	public void setSitePh(String sitePh) {
		this.sitePh = sitePh;
	}
	public String getSiteHp() {
		return siteHp;
	}
	public void setSiteHp(String siteHp) {
		this.siteHp = siteHp;
	}
	public String getSiteEmail() {
		return siteEmail;
	}
	public void setSiteEmail(String siteEmail) {
		this.siteEmail = siteEmail;
	}
	
	public int getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(int buyPrice) {
		this.buyPrice = buyPrice;
	}
	public int getTrasferPrice() {
		return trasferPrice;
	}
	public void setTrasferPrice(int trasferPrice) {
		this.trasferPrice = trasferPrice;
	}
	public String getTransferOffice() {
		return transferOffice;
	}
	public void setTransferOffice(String transferOffice) {
		this.transferOffice = transferOffice;
	}
	
	


}
