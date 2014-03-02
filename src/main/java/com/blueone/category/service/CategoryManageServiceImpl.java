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
			CategoryInfo pCategoryInfo = getCategoryInfDetail(categoryInfo);
			
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
		// TODO Auto-generated method stub
		return 0;
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
			// DB 수행
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
		// -----------------------------------------------
		// 조회
		// -----------------------------------------------
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			Map<String, CategoryInfo> sqlParams = new HashMap<String, CategoryInfo>();
			sqlParams.put("categoryInfo", categoryInfo);
			
			rstInfo = sqlSession.selectOne("category.selectDtlBomCategoryTb0001", sqlParams);
		} finally {
			sqlSession.close();
		}
		
		System.out.println("a");
		
		return rstInfo;
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
}
