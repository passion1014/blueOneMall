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
			// -----------------------------------------------
			// 입력된 AdminInfo가 제대로 된 정보인지 확인한다.
			// -----------------------------------------------
			checkAdminInfo(adminInfo);
			
			// -----------------------------------------------
			// DB에 해당 정보를 입력한다.
			// -----------------------------------------------
			int rst = sqlSession.insert("admin.insertBomAdminTb0001", adminInfo);
			
		} finally {
			sqlSession.close();
		}
		
 		return MsgEnum.MsgEnum_SUCCESS.value();
	}

	/* 
	 * 관리자 정보 수정
	 * @see com.blueone.admin.service.IAdminManageService#editAdminInf(com.blueone.admin.domain.AdminInfo)
	 */
	@Override
	public int editAdminInf(AdminInfo adminInfo) {
		
		// -----------------------------------------------
		// 1. 입력된 관리자정보 체크
		// -----------------------------------------------
		if ( !checkAdminInfo(adminInfo) ) {
			return MsgEnum.MsgEnum_FAIL.value();
		}
		
		// -----------------------------------------------
		// 2. 입력된 id값으로 현재 존재하는지 확인한다.
		//    - 조회 결과가 없을 경우 "해당 고객이 존재하지 않습니다." 리턴
		// -----------------------------------------------
		AdminInfo beforeAdminInfo = null;
		try {
			beforeAdminInfo = getAdminInfDetail(adminInfo);
		} catch (Exception e) {
			System.out.println("입력하신 고객ID에 해당하는 정보가 존재하지 않습니다.");
		}
		
		// 입력한 ID로 조회한 결과가 있는지 확인한다.
		if (beforeAdminInfo == null) {
			System.out.println("입력하신 고객ID에 해당하는 정보가 존재하지 않습니다.");
			return 0;
		}
		
		// -----------------------------------------------
		// 3. 입력된 관리자정보 값으로 update 한다.
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
	 * 관리자 목록 조회
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
	 * 관리자 상세정보 조회
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
	 * 등록/수정시 필요한 정보가 있는지 확인한다.
	 * @param adminInfo
	 * @return
	 */
	private boolean checkAdminInfo(AdminInfo adminInfo) {
		
		if (adminInfo == null) return false;
		
		return true;
	}

}
