package com.blueone.category.service;

import java.util.List;

import com.blueone.category.domain.CategoryInfo;
import com.blueone.common.domain.ResultInfo;

public interface ICategoryManageService {
	
	public ResultInfo registCategoryInf(CategoryInfo categoryInfo); /* 카테고리 신규등록 */
	public int editCategoryInf(CategoryInfo categoryInfo); /* 카테고리 수정 */
	public List<CategoryInfo> getCategoryInfList(CategoryInfo categoryInfo); /* 카테고리 목록 조회 */
	public CategoryInfo getCategoryInfDetail(CategoryInfo categoryInfo); /* 카테고리 상세 조회 */
 
}
