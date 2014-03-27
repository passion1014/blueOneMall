package com.blueone.admin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
			
			int	rst = sqlSession.insert("goods.insertBomCategoryTb0001", goodsTypeInfo);
						
		} finally {
			sqlSession.close();
		}
		
		return MsgEnum.MsgEnum_SUCCESS.value();
	}
	
	@Override
	public List<GoodsTypeInfo> getGoodsTypeList(GoodsTypeInfo goodsTypeInfo){
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<GoodsTypeInfo> list = new ArrayList<GoodsTypeInfo>();
		try{
			list = sqlSession.selectList("goods.selectListBomCategoryTb0001", goodsTypeInfo);
		}finally{
			sqlSession.close();
		}
		return list;
	}
	
	@Override
	public int goodsTypeEdit(GoodsTypeInfo goodsTypeInfo){
		int rst = 0;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try{
			Map<String, GoodsTypeInfo> map = new HashMap<String, GoodsTypeInfo>();
			map.put("categoryTypeInfo", goodsTypeInfo);
			
		rst = sqlSession.update("goods.updateBomCategoryTb0001", map);
			
		}finally{
			sqlSession.close();
		}
		return rst;
	}
	
	@Override
	public int goodsTypeDelete(GoodsTypeInfo goodsTypeInfo){
		
		int rst = 0;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try{
			Map<String, GoodsTypeInfo> map = new HashMap<String, GoodsTypeInfo>();
			map.put("categoryTypeInfo", goodsTypeInfo);
			rst = sqlSession.delete("goods.deleteBomCategoryTb0001", map);
		}finally{
			sqlSession.close();
		}
		return rst;
	}
	

}
