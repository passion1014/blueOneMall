package com.blueone.category.service;

import java.util.List;

import com.blueone.category.domain.CategoryInfo;
import com.blueone.common.domain.ResultInfo;

public interface ICategoryManageService {
	
	public ResultInfo registCategoryInf(CategoryInfo categoryInfo);
	public int editCategoryInf(CategoryInfo categoryInfo);
	public List<CategoryInfo> getCategoryInfList(CategoryInfo categoryInfo);
	public List<CategoryInfo> getCategoryInfList2(CategoryInfo categoryInfo);
	public List<CategoryInfo> getCategoryInfList3(CategoryInfo categoryInfo);
	public List<CategoryInfo> getCategoryInfListByPCode(CategoryInfo categoryInfo);
	public CategoryInfo getCategoryInfDetail(CategoryInfo categoryInfo);
	public int deleteCategoryInf(CategoryInfo categoryInfo);
	public List<CategoryInfo> getCategoryInfList4(CategoryInfo categoryInfo);
	public List<CategoryInfo> getCategoryInfList5(CategoryInfo categoryInfo);
	
}
