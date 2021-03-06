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
import com.blueone.admin.domain.AgreementInfo;
import com.blueone.admin.domain.ConfigInfo;
import com.blueone.category.domain.CategoryInfo;
import com.blueone.common.util.MsgEnum;

@Service
public class AdminManageServiceImpl implements IAdminManageService {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	

	@Override
	public int checkDupAdminId(AdminInfo adminInfo) {
		int rst = 1;
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			Map<String, AdminInfo> sqlParams = new HashMap<String, AdminInfo>();
			sqlParams.put("adminInfo", adminInfo);
			
			adminInfo = sqlSession.selectOne("admin.selectDtlBomAdminTb0001", sqlParams);
			
			if (adminInfo != null && StringUtils.isNotEmpty(adminInfo.getId())) {
				rst = 0;	// 입력된 아이디에 해당하는 관리자 정보는 없음!
			}
		} finally {
			sqlSession.close();
		}
		
		return rst;
	}

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
			beforeAdminInfo = getAdminInfDetail(adminInfo.getId());
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
	public AdminInfo getAdminInfDetail(String id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		AdminInfo adminInfo = new AdminInfo();
		try {
			adminInfo = sqlSession.selectOne("admin.selectDtlBomAdminTb0001", id);
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
				
				
				// 로그인 정보 체크
				if (!StringUtils.isEmpty(inPwd)) {
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
	/*
	 * 운영자  삭제
	 */
	@Override
	public int deleteAdminInf(AdminInfo adminInfo){
		
		int rst = -1;
		
		
			SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
				// DB 수행
				Map<String, AdminInfo> sqlParams = new HashMap<String, AdminInfo>();
				sqlParams.put("adminInfo", adminInfo);
				rst = sqlSession.delete("admin.deleteBomAdminTb0001", sqlParams);
				
			} finally {
				sqlSession.close();
			}
		
		
		return rst;
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
	
	//은행 조회
	@Override
	public List<AccountInfo> getBankInfList() {
		List<AccountInfo> rstList = new ArrayList<AccountInfo>();
		// -----------------------------------------------
		// DB Insert 수행
		// -----------------------------------------------
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			// DB 수행
			rstList = sqlSession.selectList("account.selectListBomBankTb0001");
			
		} finally {
			sqlSession.close();
		}
		
		return rstList;
	}
	
	
	//계좌등록
	@Override
	public int registAccountInf(AccountInfo attInfo) {
        
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			
			int rst = sqlSession.insert("account.insertBomAccountTb0001", attInfo);
		} finally {
			sqlSession.close();
		}
		
 		return MsgEnum.MsgEnum_SUCCESS.value();
	}

	
	//계좌 전체 조회
	@Override
	public List<AccountInfo> getAccountInfList() {
		List<AccountInfo> rstList = new ArrayList<AccountInfo>();
			// -----------------------------------------------
			// DB Insert 수행
			// -----------------------------------------------
			SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
				// DB 수행
				rstList = sqlSession.selectList("account.selectListBomAccountTb0001");
				
			} finally {
				sqlSession.close();
			}
			
			return rstList;
	}
	
	/*
	 * 계좌  삭제
	 */
	@Override
	public int deleteAccountInf(AccountInfo attInfo){
		
		int rst = -1;
		
		
			SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
				// DB 수행
				rst = sqlSession.delete("account.deleteBomAccountTb0001", attInfo);
				
			} finally {
				sqlSession.close();
			}
		
		
		return rst;
	}
	
	/* 
	 * 계좌 정보 조회
	 * 
	 */
	@Override
	public AccountInfo getAccountInfDetail(AccountInfo accInfo) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		AccountInfo resultInfo = new AccountInfo();
		try {
			resultInfo = sqlSession.selectOne("account.selectListBomAccountTb0002", accInfo);
		} finally {
			sqlSession.close();
		}

		return resultInfo;
	}
	
	@Override
	public int editAccountInf(AccountInfo accInfo) {
		int rst = -1;
		
		// -----------------------------------------------
		// 해당하는 카테고리 데이터가 있는지 확인
		// -----------------------------------------------
		AccountInfo searchRstInf = getAccountInfDetail(accInfo);
		
		// -----------------------------------------------
		// 조회한 결과값이 있으면 DB업데이트
		// -----------------------------------------------
		if (searchRstInf != null ) {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
				// DB 수행
				rst = sqlSession.update("account.updateBomAccountTb0001", accInfo);
				
			} finally {
				sqlSession.close();
			}
		}
		
		return rst;
	}


	//운영설정-수정
	@Override
	public int editConfigInf(ConfigInfo configInfo) {
		int rst = -1;
		
	
		
		// -----------------------------------------------
		// 조회한 결과값이 있으면 DB업데이트
		// -----------------------------------------------
		
			SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
				// DB 수행
				rst = sqlSession.update("config.updateBomAdImgtb0001", configInfo);
				
			} finally {
				sqlSession.close();
			}
		
		
		return rst;
	}
	
	/* 
	 *운영설정-조회
	 */
	@Override
	public ConfigInfo selectConfigInf() {
		
		ConfigInfo result = new ConfigInfo();
		
		// -----------------------------------------------
		// 데이터 조회
		// -----------------------------------------------
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			
			
			result = sqlSession.selectOne("config.selectListBomConfTb0001");
			
		} finally {
			sqlSession.close();
		}

		
		return result;
	}

	//이용약관&개인보호-수정
	@Override
	public int editAgreementInf(AgreementInfo agreementInfo) {
			int rst = -1;
			
		
			
			// -----------------------------------------------
			// 조회한 결과값이 있으면 DB업데이트
			// -----------------------------------------------
			
				SqlSession sqlSession = sqlSessionFactory.openSession();
				try {
					// DB 수행
					rst = sqlSession.update("agreement.updateBomAgreementTB0001", agreementInfo);
					
				} finally {
					sqlSession.close();
				}
			
			
		return rst;
	}
	
	/* 
	 *이용약관&개인보호-조회
	 */
	@Override
	public AgreementInfo selectAgreementInf(AgreementInfo agreement) {
		
		AgreementInfo result = new AgreementInfo();
		
		// -----------------------------------------------
		// 데이터 조회
		// -----------------------------------------------
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			
			
			result = sqlSession.selectOne("agreement.selectDtlBomAgreementTb0001",agreement);
			
		} finally {
			sqlSession.close();
		}

		
		return result;
	}
	
	/* 
	 *이용약관&개인보호-조회 List
	 */
	@Override
	public List<AgreementInfo> selectAgreementInfList() {
		
		List<AgreementInfo> result = new ArrayList<AgreementInfo>();
		
		// -----------------------------------------------
		// 데이터 조회
		// -----------------------------------------------
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			
			
			result = sqlSession.selectList("agreement.selectDtlBomAgreementTb0002");
			
		} finally {
			sqlSession.close();
		}

		
		return result;
	}
}
