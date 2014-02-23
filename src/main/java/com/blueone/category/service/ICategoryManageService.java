package com.blueone.category.service;

import java.util.List;

import com.blueone.category.domain.CategoryInfo;

public interface ICategoryManageService {
	
	public int registCategoryInf(CategoryInfo categoryInfo); /* ī�װ� �űԵ�� */
	public int editCategoryInf(CategoryInfo categoryInfo); /* ī�װ� ���� */
	public List<CategoryInfo> getCategoryInfList(CategoryInfo categoryInfo); /* ī�װ� ��� ��ȸ */
	public CategoryInfo getCategoryInfDetail(CategoryInfo categoryInfo); /* ī�װ� �� ��ȸ */
 
}
