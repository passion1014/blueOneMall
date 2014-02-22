package com.blueone.admin.service;

import java.util.List;

import com.blueone.admin.domain.AdminInfo;

public interface IAdminManageService {

	public int registAdminInf(AdminInfo adminInfo); /* ������ �űԵ�� */
	public int editAdminInf(AdminInfo adminInfo); /* ������ ���� */
	public List<AdminInfo> getAdminInfList(AdminInfo adminInfo); /* ������ ��� ��ȸ */
	public AdminInfo getAdminInfDetail(AdminInfo adminInfo); /* ������ �� ��ȸ */
	
}
