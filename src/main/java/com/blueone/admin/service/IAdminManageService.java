package com.blueone.admin.service;

import java.util.List;

import com.blueone.admin.domain.AdminInfo;

public interface IAdminManageService {

	public int registAdminInf(AdminInfo adminInfo); /* 관리자 신규등록 */
	public int editAdminInf(AdminInfo adminInfo); /* 관리자 수정 */
	public List<AdminInfo> getAdminInfList(AdminInfo adminInfo); /* 관리자 목록 조회 */
	public AdminInfo getAdminInfDetail(AdminInfo adminInfo); /* 관리자 상세 조회 */
	
}
