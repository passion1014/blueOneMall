package com.blueone.admin.service;

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

import com.blueone.admin.domain.GoodsTypeInfo;
import com.blueone.category.domain.CategoryInfo;
import com.blueone.common.domain.ResultInfo;
import com.blueone.common.util.MsgEnum;

@Service  
public class GoodsServiceImpl implements IGoodsService {
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public ResultInfo registCategoryInf(GoodsTypeInfo goodsTypeInfo) {
		ResultInfo rstInfo = new ResultInfo(ResultInfo.OK);
		
		// -----------------------------------------------
		// 체크로직
		// -----------------------------------------------
		rstInfo = checkGoodsTypeInfo(goodsTypeInfo);
		if (!rstInfo.isOk()) return rstInfo;
		
		// -----------------------------------------------
		// ctgPCode가 있을 경우 
		//   1) 부모 카테고리가 존재하는지 확인
		//   2) 구분코드가 상위 구분코드인지 확인
		// -----------------------------------------------
		if (StringUtils.isNotEmpty(goodsTypeInfo.getCtgPCode())) {
			GoodsTypeInfo pGoodsTypeInfo = getCategoryInfDetail(goodsTypeInfo.getCtgPCode());
			
			// 1) 부모 카테고리가 존재하는지 확인
			if (pGoodsTypeInfo == null) {
				rstInfo = new ResultInfo();
				rstInfo.setRstCd("0");
				rstInfo.setRstMsgCd("srch.category.parent.fail");
				
				return rstInfo;
			}
			
			// 2) 분류코드가 상위코드인지 확인 (대분류= 01, 중분류=02, 소분류=03)
			int iPTypeCd = NumberUtils.toInt(pGoodsTypeInfo.getCtgCodeType(), -1);	// 부모 구분코드
			int iCTypeCd = NumberUtils.toInt(goodsTypeInfo.getCtgCodeType(), -1);	// 자식 구분코드
			
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
			setDefaultDate(goodsTypeInfo);
			int code= (int)(Math.random()*10000)+1;
			String ctgCode= "L"+code;
			
			goodsTypeInfo.setCtgCode(ctgCode);
			
			int	rst = sqlSession.insert("goods.insertBomCategoryTb0001", goodsTypeInfo);
						
			// DB 수행
			
		} finally {
			sqlSession.close();
		}
		
 		return rstInfo;
	}
	

	@Override
	public int editCategoryInf(GoodsTypeInfo goodsTypeInfo) {
		int rst = -1;
		
		// -----------------------------------------------
		// 해당하는 카테고리 데이터가 있는지 확인
		// -----------------------------------------------
		
		
		// -----------------------------------------------
		// 조회한 결과값이 있으면 DB업데이트
		// -----------------------------------------------
		
			SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
				// DB 수행
				rst = sqlSession.update("goods.updateBomCategoryTb0001", goodsTypeInfo);
				
			} finally {
				sqlSession.close();
			}
		
		
		return rst;
	}
	

	/* 
	 * 카테고리 목록 조회
	 */
	@Override
	public List<GoodsTypeInfo> getCategoryInfList(GoodsTypeInfo goodsTypeInfo) {
		List<GoodsTypeInfo> list = new ArrayList<GoodsTypeInfo>();
		// -----------------------------------------------
		// DB Insert 수행
		// -----------------------------------------------
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			// DB 수행
			list = sqlSession.selectList("goods.selectListBomCategoryTb0001",goodsTypeInfo);
			
		} finally {
			sqlSession.close();
		}
		
		return list;
	}
	

	/*
	 * Category 조회 
	 */
	@Override
	public GoodsTypeInfo getCategoryInfDetail(GoodsTypeInfo goodsTypeInfo) {
		
		GoodsTypeInfo rstInfo = new GoodsTypeInfo();
		// -----------------------------------------------
		// 조회
		// -----------------------------------------------
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			rstInfo = sqlSession.selectOne("goods.selectDtlBomCategoryTb0001", goodsTypeInfo);
		} finally {
			sqlSession.close();
		}
		
		return rstInfo;
	}
	
	@Override
	public int deleteCategoryInf(GoodsTypeInfo goodsTypeInfo){
		
		int rst = -1;
		
		
			SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
				// DB 수행
				rst = sqlSession.delete("goods.deleteBomCategoryTb0001", goodsTypeInfo);
				
			} finally {
				sqlSession.close();
			}
		
		
		return rst;
	}
	
	/*
	 * Category 조회 
	 */
	private GoodsTypeInfo getCategoryInfDetail(String ctgCode) {
		
		GoodsTypeInfo goodsTypeInfo = new GoodsTypeInfo();
		goodsTypeInfo.setCtgCode(ctgCode);
		
		return getCategoryInfDetail(goodsTypeInfo);
	}

	private ResultInfo checkGoodsTypeInfo(GoodsTypeInfo goodsTypeInfo) {
		ResultInfo rstInfo = new ResultInfo();
		rstInfo.setRstCd("1");
		
		return rstInfo;
	}
	
	private void setDefaultDate(GoodsTypeInfo goodsTypeInfo) {
		
		// fromDate "0001-01-01"
		if (StringUtils.isEmpty(goodsTypeInfo.getFromDate())) {
			goodsTypeInfo.setFromDate("0001-01-01");
		}
		
		// toDate "9999-12-31"
		if (StringUtils.isEmpty(goodsTypeInfo.getToDate())) {
			goodsTypeInfo.setToDate("9999-12-31");
		}
	}
	

}
