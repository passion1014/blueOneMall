package com.blueone.category.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueone.category.domain.CategoryInfo;
import com.blueone.common.domain.ResultInfo;

@Service
public class CategoryManageServiceImpl implements ICategoryManageService {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public ResultInfo registCategoryInf(CategoryInfo categoryInfo) {
		ResultInfo rstInfo = new ResultInfo(ResultInfo.OK);
		
		// -----------------------------------------------
		// 체크로직
		// -----------------------------------------------
		rstInfo = checkCategoryInfo(categoryInfo);
		if (!rstInfo.isOk()) return rstInfo;
		
		// -----------------------------------------------
		// ctgPCode가 있을 경우 
		//   1) 부모 카테고리가 존재하는지 확인
		//   2) 구분코드가 상위 구분코드인지 확인
		// -----------------------------------------------
		if (StringUtils.isNotEmpty(categoryInfo.getCtgPCode())) {
			CategoryInfo pCategoryInfo = getCategoryInfDetail(categoryInfo.getCtgPCode());
			
			// 1) 부모 카테고리가 존재하는지 확인
			if (pCategoryInfo == null) {
				rstInfo = new ResultInfo();
				rstInfo.setRstCd("0");
				rstInfo.setRstMsgCd("srch.category.parent.fail");
				
				return rstInfo;
			}
			
			// 2) 분류코드가 상위코드인지 확인 (대분류= 01, 중분류=02, 소분류=03)
			int iPTypeCd = NumberUtils.toInt(pCategoryInfo.getCtgCodeType(), -1);	// 부모 구분코드
			int iCTypeCd = NumberUtils.toInt(categoryInfo.getCtgCodeType(), -1);	// 자식 구분코드
			
			if (iPTypeCd >= iCTypeCd) {
				rstInfo = new ResultInfo();
				rstInfo.setRstCd("0");
				rstInfo.setRstMsgCd("srch.category.parent.fail");
				
				return rstInfo;
			}
			
		}
		
		// -----------------------------------------------
		// DB Insert 수행
		// -----------------------------------------------
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			// fromDate, toDate 없을경우, 기본값 셋팅
			setDefaultDate(categoryInfo);
			
			// DB 수행
			int rst = sqlSession.insert("category.insertBomCategoryTb0001", categoryInfo);
		} finally {
			sqlSession.close();
		}
		
 		return rstInfo;
	}

	@Override
	public int editCategoryInf(CategoryInfo categoryInfo) {
		int rst = -1;
		
		// -----------------------------------------------
		// 해당하는 카테고리 데이터가 있는지 확인
		// -----------------------------------------------
		CategoryInfo searchRstInf = getCategoryInfDetail(categoryInfo);
		
		// -----------------------------------------------
		// 조회한 결과값이 있으면 DB업데이트
		// -----------------------------------------------
		if (searchRstInf != null && StringUtils.isNotEmpty(searchRstInf.getCtgCode())) {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
				// DB 수행
				rst = sqlSession.update("category.updateBomCategoryTb0001", categoryInfo);
				
			} finally {
				sqlSession.close();
			}
		}
		
		return rst;
	}

	/* 
	 * 카테고리 목록 조회
	 */
	@Override
	public List<CategoryInfo> getCategoryInfList(CategoryInfo categoryInfo) {
		List<CategoryInfo> rstList = new ArrayList<CategoryInfo>();
		// -----------------------------------------------
		// DB Insert 수행
		// -----------------------------------------------
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			/*
			if (StringUtils.isNotEmpty(categoryInfo.getCtgPCode())) {
				// 부모 카테고리로 자식 카테고리 목록을 조회
				rstList = sqlSession.selectList("category.selectListBomCategoryTb0003", categoryInfo);
				
			} else {
				// 전체 카테고리 목록을 조회
				rstList = sqlSession.selectList("category.selectListBomCategoryTb0001");
				
			}
			
			*/
			rstList = sqlSession.selectList("category.selectListBomCategoryTb0001");
				
			 
		} finally {
			sqlSession.close();
		}
		
		return rstList;
	}

	/*
	 * Category 조회 
	 */
	@Override
	public CategoryInfo getCategoryInfDetail(CategoryInfo categoryInfo) {
		                 
		CategoryInfo rstInfo = new CategoryInfo();
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			// -----------------------------------------------
			// 1. 카테고리 기본값 조회
			// -----------------------------------------------
			rstInfo = sqlSession.selectOne("category.selectDtlBomCategoryTb0001", categoryInfo);
			
			// -----------------------------------------------
			// 2. 부모카테고리 조회 - 대분류일 경우 조회가 필요없음
			// -----------------------------------------------
			if (!"01".equals(categoryInfo.getCtgCodeType())) {
				List<CategoryInfo> ctgFullList = sqlSession.selectList("category.selectListBomCategoryTb0006", categoryInfo);
				
				String ctgCode = categoryInfo.getCtgCode();
				for (CategoryInfo each : ctgFullList) {
					if (ctgCode.equals(each.getCtgCode())) {
						rstInfo.setCtgLargeCode(each.getCtgLargeCode());
						rstInfo.setCtgLargeName(each.getCtgLargeName());
						rstInfo.setCtgMiddleCode(each.getCtgMiddleCode());
						rstInfo.setCtgMiddleName(each.getCtgMiddleName());
					}
				}
			}
		} finally {
			sqlSession.close();
		}
		
		
		return rstInfo;
	}
	
	
	
	
	/*
	 * Category 분류 디테일 조회 
	 */
	private CategoryInfo getCategoryInfDetail(String ctgCode) {
		
		CategoryInfo categoryInfo = new CategoryInfo();
		categoryInfo.setCtgCode(ctgCode);
		
		return getCategoryInfDetail(categoryInfo);
	}
	
	
	
	
	/*
	 * Category 대분류 조회....TEST 용 
	 */
	@Override
	public List<CategoryInfo> getCategoryInfList2(CategoryInfo categoryInfo) {
		List<CategoryInfo> rstList = new ArrayList<CategoryInfo>();
		// -----------------------------------------------
		// DB Insert 수행
		// -----------------------------------------------
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			// DB 수행
			rstList = sqlSession.selectList("category.selectListBomCategoryTb0002",categoryInfo);
			
		} finally {
			sqlSession.close();
		}
		
		return rstList;
	}
	
	
	
	/*
	 * Category 중분류 조회.... 
	 */
	@Override
	public List<CategoryInfo> getCategoryInfList3(CategoryInfo categoryInfo) {
		List<CategoryInfo> rstList = new ArrayList<CategoryInfo>();
		// -----------------------------------------------
		// DB Insert 수행
		// -----------------------------------------------
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			// DB 수행
			rstList = sqlSession.selectList("category.selectListBomCategoryTb0004");
			
		} finally {
			sqlSession.close();
		}
		
		return rstList;
	}
	
	
	
	/*
	 * Category 소분류 조회.... 
	 */
	@Override
	public List<CategoryInfo> getCategoryInfList4(CategoryInfo categoryInfo) {
		List<CategoryInfo> rstList = new ArrayList<CategoryInfo>();
		// -----------------------------------------------
		// DB Insert 수행
		// -----------------------------------------------
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			// DB 수행
			rstList = sqlSession.selectList("category.selectListBomCategoryTb0005");
			
		} finally {
			sqlSession.close();
		}
		
		return rstList;
	}
	
	/*
	 * 상위코드에 따른 하위코드 목록 조회 
	 * 작성자 : 이성욱  
	 */
	@Override
	public List<CategoryInfo> getCategoryInfListByPCode(CategoryInfo categoryInfo) {
		List<CategoryInfo> rstList = new ArrayList<CategoryInfo>();
		// -----------------------------------------------
		// DB Insert 수행
		// -----------------------------------------------
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			// DB 수행
			rstList = sqlSession.selectList("category.selectListBomCategoryTb0003", categoryInfo);
			
		} finally {
			sqlSession.close();
		}
		
		return rstList;
	}
	
	

	private ResultInfo checkCategoryInfo(CategoryInfo categoryInfo) {
		ResultInfo rstInfo = new ResultInfo();
		rstInfo.setRstCd("1");
		
		return rstInfo;
	}
	
	private void setDefaultDate(CategoryInfo categoryInfo) {
		
		// fromDate "0001-01-01"
		if (StringUtils.isEmpty(categoryInfo.getFromDate())) {
			categoryInfo.setFromDate("0001-01-01");
		}
		
		// toDate "9999-12-31"
		if (StringUtils.isEmpty(categoryInfo.getToDate())) {
			categoryInfo.setToDate("9999-12-31");
		}
	}
	
	/*
	 * 분류 삭제
	 */
	@Override
	public int deleteCategoryInf(CategoryInfo categoryInfo){
		
		int rst = -1;
		
		
			SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
				// DB 수행
				rst = sqlSession.delete("category.deleteBomCategoryTb0001", categoryInfo);
				
			} finally {
				sqlSession.close();
			}
		
		
		return rst;
	}
	
	

}
