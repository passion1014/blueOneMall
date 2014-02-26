package com.blueone.admin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueone.admin.domain.AdminInfo;
import com.blueone.common.util.MsgEnum;

@Service
public class AdminManageServiceImpl implements IAdminManageService {

//	@Autowired
//	private SqlSession sqlSession;
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public int registAdminInf(AdminInfo adminInfo) {

		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
		
			checkAdminInfo(adminInfo);
			
		
			int rst = sqlSession.insert("admin.insertBomAdminTb0001", adminInfo);
			
		} finally {
			sqlSession.close();
		}
		
 		return MsgEnum.MsgEnum_SUCCESS.value();
	}

	/* 
	 * ���� ���� ����
	 * @see com.blueone.admin.service.IAdminManageService#editAdminInf(com.blueone.admin.domain.AdminInfo)
	 */
	@Override
	public int editAdminInf(AdminInfo adminInfo) {
		
	
		if ( !checkAdminInfo(adminInfo) ) {
			return MsgEnum.MsgEnum_FAIL.value();
		}
		
	
		AdminInfo beforeAdminInfo = null;
		try {
			beforeAdminInfo = getAdminInfDetail(adminInfo);
		} catch (Exception e) {
			System.out.println("�Է��Ͻ� �?ID�� �ش��ϴ� ������ �������� �ʽ��ϴ�.");
		}
		
		// �Է��� ID�� ��ȸ�� ��� �ִ��� Ȯ���Ѵ�.
		if (beforeAdminInfo == null) {
			System.out.println("�Է��Ͻ� �?ID�� �ش��ϴ� ������ �������� �ʽ��ϴ�.");
			return 0;
		}
		
		// -----------------------------------------------
		// 3. �Էµ� �������� ������ update �Ѵ�.
		// -----------------------------------------------
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			Map<String, AdminInfo> sqlParams = new HashMap<String, AdminInfo>();
			sqlParams.put("adminInfo", adminInfo);
			
			adminInfo = sqlSession.selectOne("admin.updateBomAdminTb0001", sqlParams);
			
		} finally {
			sqlSession.close();
		}

		
		return 0;
	}

	/* 
	 * ���� ��� ��ȸ
	 * @see com.blueone.admin.service.IAdminManageService#getAdminInfList(com.blueone.admin.domain.AdminInfo)
	 */
	@Override
	public List<AdminInfo> getAdminInfList(AdminInfo adminInfo) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		List<AdminInfo> adminInfoList = new ArrayList<AdminInfo>();
		try {
			Map<String, AdminInfo> sqlParams = new HashMap<String, AdminInfo>();
//			sqlParams.put("adminInfo", adminInfo);
			
			adminInfoList = sqlSession.selectList("admin.selectListBomAdminTb0001", sqlParams);
			
		} finally {
			sqlSession.close();
		}

		return adminInfoList;
	}

	/* 
	 * ���� ������ ��ȸ
	 * @see com.blueone.admin.service.IAdminManageService#getAdminInfDetail(com.blueone.admin.domain.AdminInfo)
	 */
	@Override
	public AdminInfo getAdminInfDetail(AdminInfo adminInfo) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			Map<String, AdminInfo> sqlParams = new HashMap<String, AdminInfo>();
			sqlParams.put("adminInfo", adminInfo);
			
			adminInfo = sqlSession.selectOne("admin.selectDtlBomAdminTb0001", sqlParams);
			
		} finally {
			sqlSession.close();
		}

		return adminInfo;
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
