package com.blueone.admin.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.blueone.admin.domain.AdminInfo;
import com.blueone.admin.domain.GoodsTypeInfo;
import com.blueone.common.util.MsgEnum;

@Service  
public class GoodsServiceImpl implements IGoodsService {
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Override
	public int registGoodsType(GoodsTypeInfo goodsTypeInfo) {
	 
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			String ctgCode= "xxxxx";
			goodsTypeInfo.setCtgCode(ctgCode);
		int	rst = sqlSession.insert("category.insertBomCategoryTb0001", goodsTypeInfo);
		} finally {
			sqlSession.close();
		}
		
		return MsgEnum.MsgEnum_SUCCESS.value();
	}
	
	
	
	

}
