package com.blueone.admin.service;

import java.util.HashMap;
import java.util.List;

import com.blueone.admin.domain.AdminInfo;

public interface IAdminManageService {

	public int registAdminInf(AdminInfo adminInfo); /*운영자등록*/
	public int editAdminInf(AdminInfo adminInfo); /*운영자수정*/
	public List<AdminInfo> getAdminInfList(AdminInfo adminInfo); /*운영자목록*/
	public AdminInfo getAdminInfDetail(AdminInfo adminInfo); /*운영자 상세정보*/
}
