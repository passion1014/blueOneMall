package com.blueone.admin.service;

import java.util.HashMap;
import java.util.List;

import com.blueone.admin.domain.AccountInfo;
import com.blueone.admin.domain.AdminInfo;
import com.blueone.admin.domain.AdminLoginInfo;

public interface IAdminManageService {

	public int checkDupAdminId(AdminInfo adminInfo); /*관리자등록시 중복 아이디 체크*/
	public int registAdminInf(AdminInfo adminInfo); /*운영자등록*/
	public int editAdminInf(AdminInfo adminInfo); /*운영자수정*/
	public List<AdminInfo> getAdminInfList(AdminInfo adminInfo); /*운영자목록*/
	public AdminInfo getAdminInfDetail(String id); /*운영자 상세정보*/
	public List<AccountInfo> getAccountInfList(AccountInfo accountInfo);/*계좌목록*/
	public AdminInfo adminLogin(AdminLoginInfo adminInfo);	/* 관리자 로그인 */
}
