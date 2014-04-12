package com.blueone.common.util;

import java.util.ArrayList;
import java.util.List;

import com.blueone.category.domain.CategoryInfo;
import com.blueone.product.domain.ProductInfo;

public class PageDivision {

	int pNum;//페이지 num
	int endNum;//페이지 끝숫자
	
	int startIdx;
	int endIdx;
	
	
	List<CategoryInfo> ctList;
	List<ProductInfo> prdList;
	List<CategoryInfo> ctExList;
	List<ProductInfo> prdExList;
	
	
	
	

	public void setCtList(List<CategoryInfo> ctList) {
		this.ctList = ctList;
		ctExList=new ArrayList<CategoryInfo>();
		
		if(ctList.size()%2==0) {
			endNum=ctList.size()/2;
		}
		else{
			endNum=ctList.size()/2+1;
			}
		
	}
	public List<CategoryInfo> getCtList(int item) {
		int startIdx=pNum*item-item;
		int forend=pNum*item-1;
		
		for(int i=startIdx; i<=forend && i<ctList.size(); i++){
			ctExList.add(ctList.get(i));
		}
		return ctExList;
		
	}

	public void setPrdList(List<ProductInfo> prdList) {
		this.prdList = prdList;
		prdExList=new ArrayList<ProductInfo>();
		
		if(prdList.size()%2==0) {
			endNum=prdList.size()/2;
		}
		else{
			endNum=prdList.size()/2+1;
			}
	}
	public List<ProductInfo> getPrdList(int item) {
		int startIdx=pNum*item-item;
		int forend=pNum*item-1;
		
		for(int i=startIdx; i<=forend && i<prdList.size(); i++){
			prdExList.add(prdList.get(i));
		}
		return prdExList;
		
	}
	public void pageNum(String page){
		pNum=Integer.parseInt(page);
		
	}
	
	public int getEndPageNum(){
		return endNum;
	}
	

	
}
