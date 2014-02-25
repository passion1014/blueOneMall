package com.blueone.category.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueone.category.domain.CategoryInfo;
import com.blueone.common.domain.ResultInfo;
import com.blueone.common.util.MsgEnum;

@Service
public class CategoryManageServiceImpl implements ICategoryManageService {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public ResultInfo registCategoryInf(CategoryInfo categoryInfo) {
		ResultInfo rstInfo = new ResultInfo(ResultInfo.OK);
		
		// -----------------------------------------------
		// 입력된 AdminInfo가 제대로 된 정보인지 확인한다.
		// -----------------------------------------------
		rstInfo = checkCategoryInfo(categoryInfo);
		if (!rstInfo.isOk()) return rstInfo;
		
		// -----------------------------------------------
		// ctgPCode가 있을 경우 
		//   1) 해당 부모코드가 존재하는지 확인
		//   2) 부모코드의 타입이 상위 타입인지 확인
		// -----------------------------------------------
		if (StringUtils.isNotEmpty(categoryInfo.getCtgPCode())) {
			CategoryInfo pCategoryInfo = getCategoryInfDetail(categoryInfo);
			
			// 1) 해당 부모코드가 존재하는지 확인
			if (pCategoryInfo == null) {
				rstInfo = new ResultInfo();
				rstInfo.setRstCd("0");
				rstInfo.setRstMsgCd("srch.category.parent.fail");
				
				return rstInfo;
			}
			
			// 2) 부모코드의 타입이 상위 타입인지 확인
		}
		
		// -----------------------------------------------
		// DB에 해당 정보를 입력한다.
		// -----------------------------------------------
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			// fromDate, toDate 빈값 체크하여 기본값 세팅
			setDefaultDate(categoryInfo);
			
			// DB 저장
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

	@Override
	public List<CategoryInfo> getCategoryInfList(CategoryInfo categoryInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryInfo getCategoryInfDetail(CategoryInfo categoryInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	private ResultInfo checkCategoryInfo(CategoryInfo categoryInfo) {
		ResultInfo rstInfo = new ResultInfo();
		return rstInfo;
	}
	
	private void setDefaultDate(CategoryInfo categoryInfo) {
		
		// fromDate 빈값일경우 "0001-01-01" 셋팅
		if (StringUtils.isEmpty(categoryInfo.getFromDate())) {
			categoryInfo.setFromDate("0001-01-01");
		}
		
		// toDate 빈값 일경우 "9999-12-31" 셋팅
		if (StringUtils.isEmpty(categoryInfo.getToDate())) {
			categoryInfo.setToDate("9999-12-31");
		}
	}
}
