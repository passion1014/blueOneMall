package com.blueone.admin.service;

import java.util.List;

import com.blueone.admin.domain.GoodsTypeInfo;

public interface IGoodsService {

	public int registGoodsType(GoodsTypeInfo goodsTypeInfo); /* 상품관리 */
	public List<GoodsTypeInfo> getGoodsTypeList(GoodsTypeInfo goodsTypeInfo);
	public int goodsTypeEdit(GoodsTypeInfo goodsTypeInfo); /*상품수정*/
	public int goodsTypeDelete(GoodsTypeInfo goodsTypeInfo);/*상품삭제*/
}
