package com.blueone.admin.domain;

import org.hibernate.validator.constraints.NotEmpty;


public class AdminInfo {
	private int idx;
	private String status;
	private String id;
	private String password;
	private String name;
	private String phone;
	private String mobile;
	private String email;
	private String grade;
	private int hit;
	private String comment;
	private String regDate;
	private String lastDate;
	private String[] gd;
	
	private int currentPage;
	
	

	public String[] getGd() {
		return gd;
	}

	public void setGd(String[] gd) {
		this.gd = gd;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public AdminInfo() {}
	
	public AdminInfo(String id, String password) {
		this.id = id;
		this.password = password;
	}

	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
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
	
	
	
	
	
	@Override
	public String toString(){
		
		return "AdminInfo [idx="+idx+", id="+id+", password="+password+", name="+name+","
				+ "phone="+phone+", mobile="+mobile+", email="+email+", grade="+grade+","
				+ "hit="+hit+",comment="+comment+", regDate="+regDate+", lastDate="+lastDate+",]";
		
		
		
	}
}
