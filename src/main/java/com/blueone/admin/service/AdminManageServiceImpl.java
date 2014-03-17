package com.blueone.admin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.blueone.admin.domain.AccountInfo;
import com.blueone.admin.domain.AdminInfo;
import com.blueone.admin.domain.AdminLoginInfo;
import com.blueone.common.util.MsgEnum;

@Service
public class AdminManageServiceImpl implements IAdminManageService {

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
	 * 관리자정보 수정
	 * @see com.blueone.admin.service.IAdminManageService#editAdminInf(com.blueone.admin.domain.AdminInfo)
	 */
	@Override
	public int editAdminInf(AdminInfo adminInfo) {
		
		// 입력한 데이터에 문제가 없는지 확인
		if ( !checkAdminInfo(adminInfo) ) {
			return MsgEnum.MsgEnum_FAIL.value();
		}
	
		AdminInfo beforeAdminInfo = null;
		try {
			beforeAdminInfo = getAdminInfDetail(adminInfo);
		} catch (Exception e) {
			System.out.println("관리자 정보 조회시 에러가 발생하였습니다.");
		}
		
		// 수정전 데이터가 있는지 확인
		if (beforeAdminInfo == null) {
			System.out.println("입력하신 관리자ID는 존재하지 않습니다.");
			return 0;
		}
		
		// -----------------------------------------------
		// 데이터 조회
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
	 * 전체 관리자 정보 조회
	 * @see com.blueone.admin.service.IAdminManageService#getAdminInfList(com.blueone.admin.domain.AdminInfo)
	 */
	@Override
	public List<AdminInfo> getAdminInfList(AdminInfo adminInfo) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		List<AdminInfo> list = new ArrayList<AdminInfo>();
		
		try {
			Map<String, AdminInfo> sqlParams = new HashMap<String, AdminInfo>();

			
		    list = sqlSession.selectList("admin.selectListBomAdminTb0001",sqlParams);
			
		} finally {
			sqlSession.close();
		}

		
		return  list;
	}

	/* 
	 * 관리자 상세정보 조회
	 * @see com.blueone.admin.service.IAdminManageService#getAdminInfDetail(com.blueone.admin.domain.AdminInfo)
	 */
	@Override
	public AdminInfo getAdminInfDetail(AdminInfo adminInfo) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			
			
			adminInfo = sqlSession.selectOne("admin.selectDtlBomAdminTb0001", adminInfo);
			
		} finally {
			sqlSession.close();
		}

		return adminInfo;
	}
	
	@Override
	public AdminInfo adminLogin(AdminLoginInfo adminLoginInfo) {
		AdminInfo adminInfo = new AdminInfo();
		
		adminLoginInfo.setRightLogin(false);	// 최초 Default 셋팅
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			// DB에서 해당 아이디로 조회
			Map<String, AdminInfo> sqlParams = new HashMap<String, AdminInfo>();
			sqlParams.put("adminInfo", new AdminInfo(adminLoginInfo.getAdminId(), adminLoginInfo.getAdminPw()));
			adminInfo = sqlSession.selectOne("admin.selectDtlBomAdminTb0001", sqlParams);
			
			if (adminInfo != null) {
				String inPwd = adminLoginInfo.getAdminPw();
				String dbPwd = adminInfo.getPassword();
				
				// 로그인 정보 체크
				if (!StringUtils.isEmpty(inPwd) && inPwd.equals(dbPwd)) {
					return adminInfo;
				}
			}
		} catch(Exception e) {
			System.out.println(e);
		} finally {
			sqlSession.close();
		}
		
		return adminInfo;
	}
	
	@Override
	public List<AccountInfo> getAccountInfList(AccountInfo accountInfo){
		
        SqlSession sqlSession = sqlSessionFactory.openSession();
		
		List<AccountInfo> list = new ArrayList<AccountInfo>();
		
		try {
			Map<String, AccountInfo> sqlParams = new HashMap<String, AccountInfo>();

			
		    list = sqlSession.selectList("account.selectListBomAdminTb0001",sqlParams);
			
		} finally {
			sqlSession.close();
		}
   
		return  list;
		
		
		
	} 
	
	/**
	 * 체크
	 * @param adminInfo
	 * @return
	 */
	private boolean checkAdminInfo(AdminInfo adminInfo) {
		
		if (adminInfo == null) return false;
		
		return true;
	}

}
