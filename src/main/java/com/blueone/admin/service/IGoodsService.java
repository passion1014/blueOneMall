package com.blueone.admin.service;

import java.util.List;

import com.blueone.admin.domain.GoodsTypeInfo;
import com.blueone.category.domain.CategoryInfo;
import com.blueone.common.domain.ResultInfo;

public interface IGoodsService {

	public ResultInfo registCategoryInf(GoodsTypeInfo goodsTypeInfo);/*관리자등록*/
	public int editCategoryInf(GoodsTypeInfo goodsTypeInfo);/*관리자수정*/
	public List<GoodsTypeInfo> getCategoryInfList(GoodsTypeInfo goodsTypeInfo);/*관리자리스트*/
	public GoodsTypeInfo getCategoryInfDetail(GoodsTypeInfo goodsTypeInfo);/*관리자정보*/
	public int deleteCategoryInf(GoodsTypeInfo goodsTypeInfo);/*관리자삭제*/
}
