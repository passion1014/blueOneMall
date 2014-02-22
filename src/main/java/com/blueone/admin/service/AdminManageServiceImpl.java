package com.blueone.admin.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueone.admin.domain.AdminInfo;
import com.blueone.common.util.MsgEnum;

@Service
public class AdminManageServiceImpl implements IAdminManageService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int registAdminInf(AdminInfo adminInfo) {

		// -----------------------------------------------
		// �Էµ� AdminInfo�� ����� �� �������� Ȯ���Ѵ�.
		// -----------------------------------------------
		checkAdminInfo(adminInfo);
		
		// -----------------------------------------------
		// DB�� �ش� ������ �Է��Ѵ�.
		// -----------------------------------------------
		int rst = sqlSession.insert("admin.insertBomAdminTb0001", adminInfo);
		
 		return MsgEnum.MsgEnum_SUCCESS.value();
	}

	/* 
	 * ������ ���� ����
	 * @see com.blueone.admin.service.IAdminManageService#editAdminInf(com.blueone.admin.domain.AdminInfo)
	 */
	@Override
	public int editAdminInf(AdminInfo adminInfo) {
		
		// -----------------------------------------------
		// 1. �Էµ� ���������� üũ
		// -----------------------------------------------
		if ( !checkAdminInfo(adminInfo) ) {
			return MsgEnum.MsgEnum_FAIL.value();
		}
		
		// -----------------------------------------------
		// 2. �Էµ� id������ ���� �����ϴ��� Ȯ���Ѵ�.
		//    - ��ȸ ����� ���� ��� "�ش� ���� �������� �ʽ��ϴ�." ����
		// -----------------------------------------------
		
		
		// -----------------------------------------------
		// 3. �Էµ� ���������� ������ update �Ѵ�.
		// -----------------------------------------------
		
		return 0;
	}

	/* 
	 * ������ ��� ��ȸ
	 * @see com.blueone.admin.service.IAdminManageService#getAdminInfList(com.blueone.admin.domain.AdminInfo)
	 */
	@Override
	public List<AdminInfo> getAdminInfList(AdminInfo adminInfo) {
		return null;
	}

	/* 
	 * ������ ������ ��ȸ
	 * @see com.blueone.admin.service.IAdminManageService#getAdminInfDetail(com.blueone.admin.domain.AdminInfo)
	 */
	@Override
	public AdminInfo getAdminInfDetail(AdminInfo adminInfo) {
		return null;
	}
	
	/**
	 * ���/������ �ʿ��� ������ �ִ��� Ȯ���Ѵ�.
	 * @param adminInfo
	 * @return
	 */
	private boolean checkAdminInfo(AdminInfo adminInfo) {
		
		if (adminInfo == null) return false;
		
		return true;
	}

}
