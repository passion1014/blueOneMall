package com.blueone.category.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueone.admin.domain.AdminInfo;
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
		// �Էµ� AdminInfo�� ����� �� �������� Ȯ���Ѵ�.
		// -----------------------------------------------
		rstInfo = checkCategoryInfo(categoryInfo);
		if (!rstInfo.isOk()) return rstInfo;
		
		// -----------------------------------------------
		// ctgPCode�� ���� ��� 
		//   1) �ش� �θ��ڵ尡 �����ϴ��� Ȯ��
		//   2) �θ��ڵ��� Ÿ���� ���� Ÿ������ Ȯ��
		// -----------------------------------------------
		if (StringUtils.isNotEmpty(categoryInfo.getCtgPCode())) {
			CategoryInfo pCategoryInfo = getCategoryInfDetail(categoryInfo);
			
			// 1) �ش� �θ��ڵ尡 �����ϴ��� Ȯ��
			if (pCategoryInfo == null) {
				rstInfo = new ResultInfo();
				rstInfo.setRstCd("0");
				rstInfo.setRstMsgCd("srch.category.parent.fail");
				
				return rstInfo;
			}
			
			// 2) �θ��ڵ��� Ÿ���� ���� Ÿ������ Ȯ�� (��з�Ÿ��= 01, �ߺз�=02, �Һз�=03)
			int iPTypeCd = NumberUtils.toInt(pCategoryInfo.getCtgCodeType(), -1);	// �θ�ī�װ� �����ڵ�
			int iCTypeCd = NumberUtils.toInt(categoryInfo.getCtgCodeType(), -1);	// �ڽ�ī�װ� �����ڵ�
			
			if (iPTypeCd >= iCTypeCd) {
				rstInfo = new ResultInfo();
				rstInfo.setRstCd("0");
				rstInfo.setRstMsgCd("srch.category.parent.fail");
				
				return rstInfo;
			}
			
		}
		
		// -----------------------------------------------
		// DB�� �ش� ������ �Է��Ѵ�.
		// -----------------------------------------------
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			// fromDate, toDate �� üũ�Ͽ� �⺻�� ����
			setDefaultDate(categoryInfo);
			
			// DB ����
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
//		selectListBomCategoryTb0001
		
		return null;
	}

	/*
	 * Category ����ȸ 
	 */
	@Override
	public CategoryInfo getCategoryInfDetail(CategoryInfo categoryInfo) {
		
		CategoryInfo rstInfo = new CategoryInfo();
		// -----------------------------------------------
		// �ش� ������ ��ȸ�Ѵ�.
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
		
		// fromDate ���ϰ�� "0001-01-01" ����
		if (StringUtils.isEmpty(categoryInfo.getFromDate())) {
			categoryInfo.setFromDate("0001-01-01");
		}
		
		// toDate �� �ϰ�� "9999-12-31" ����
		if (StringUtils.isEmpty(categoryInfo.getToDate())) {
			categoryInfo.setToDate("9999-12-31");
		}
	}
}
