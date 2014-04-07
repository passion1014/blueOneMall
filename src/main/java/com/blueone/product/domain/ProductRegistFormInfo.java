package com.blueone.product.domain;

import java.util.List;

import com.blueone.category.domain.CategoryInfo;

public class ProductRegistFormInfo {
	private List<CategoryInfo> categoryLList;
	private List<CategoryInfo> categoryMList;
	private List<CategoryInfo> categorySList;
	
	public List<CategoryInfo> getCategoryLList() {
		return categoryLList;
	}
	public void setCategoryLList(List<CategoryInfo> categoryLList) {
		this.categoryLList = categoryLList;
	}
	public List<CategoryInfo> getCategoryMList() {
		return categoryMList;
	}
	public void setCategoryMList(List<CategoryInfo> categoryMList) {
		this.categoryMList = categoryMList;
	}
	public List<CategoryInfo> getCategorySList() {
		return categorySList;
	}
	public void setCategorySList(List<CategoryInfo> categorySList) {
		this.categorySList = categorySList;
	}
}
