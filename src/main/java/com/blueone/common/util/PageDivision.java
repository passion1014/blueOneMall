package com.blueone.common.util;

import java.util.ArrayList;
import java.util.List;

import com.blueone.admin.domain.AccountInfo;
import com.blueone.admin.domain.AdminInfo;
import com.blueone.category.domain.CategoryInfo;
import com.blueone.order.domain.OrderInfo;
import com.blueone.product.domain.ProductInfo;
import com.blueone.product.domain.TransferInfo;

public class PageDivision {

	int pNum;//페이지 num
	int endNum;//페이지 끝숫자
	int itemNum;
	int startIdx;
	int endIdx;
	
	
	List<CategoryInfo> ctList;
	List<ProductInfo> prdList;
	List<CategoryInfo> ctExList;
	List<ProductInfo> prdExList;
	List<TransferInfo> trList;
	List<TransferInfo> trExList;
	List<AccountInfo> accList;
	List<AccountInfo> accExList;
	List<AdminInfo> adInfolist;
	List<AdminInfo> adInfoExlist;
	List<OrderInfo> orderlist;
	List<OrderInfo> orderExlist;
	

	public int getItemNum() {
		return itemNum;
	}

	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}

	public List<AdminInfo> getAdInfolist() {
		int startIdx=pNum*itemNum-itemNum;
		int forend=pNum*itemNum-1;
		for(int i=startIdx; i<=forend && i<adInfolist.size(); i++){
			adInfoExlist.add(adInfolist.get(i));
		}
		return adInfoExlist;
	}
	
	public void setAdInfolist(List<AdminInfo> adInfolist) {
		this.adInfolist = adInfolist;
		
		adInfoExlist=new ArrayList<AdminInfo>();
		
		if(adInfolist.size()%itemNum==0) {
			endNum=adInfolist.size()/itemNum;
		}
		else{
			endNum=adInfolist.size()/itemNum+1;
			}
		
	}
	
	
	public List<AccountInfo> getAccList() {
		int startIdx=pNum*itemNum-itemNum;
		int forend=pNum*itemNum-1;
		for(int i=startIdx; i<=forend && i<accList.size(); i++){
			accExList.add(accList.get(i));
		}
		return accExList;
	}
	
	public void setAccList(List<AccountInfo> accList) {
		this.accList = accList;
		
		accExList=new ArrayList<AccountInfo>();
		
		if(accList.size()%itemNum==0) {
			endNum=accList.size()/itemNum;
		}
		else{
			endNum=accList.size()/itemNum+1;
			}
		
	}
	
	
	public List<TransferInfo> getTrList() {
		int startIdx=pNum*itemNum-itemNum;
		int forend=pNum*itemNum-1;
		for(int i=startIdx; i<=forend && i<trList.size(); i++){
			trExList.add(trList.get(i));
		}
		return trList;
	}
	public void setTrList(List<TransferInfo> trList) {
		this.trList = trList;
		trExList=new ArrayList<TransferInfo>();
		if(trList.size()%itemNum==0) {
			endNum=trList.size()/itemNum;
		}
		else{
			endNum=trList.size()/itemNum+1;
			}
		
	}
	

	public void setCtList(List<CategoryInfo> ctList) {
		this.ctList = ctList;
		ctExList=new ArrayList<CategoryInfo>();
		
		if(ctList.size()%itemNum==0) {
			endNum=ctList.size()/itemNum;
		}
		else{
			endNum=ctList.size()/itemNum+1;
			}
		
	}
	public List<CategoryInfo> getCtList() {
		int startIdx=pNum*itemNum-itemNum;
		int forend=pNum*itemNum-1;
		
		for(int i=startIdx; i<=forend && i<ctList.size(); i++){
			ctExList.add(ctList.get(i));
		}
		return ctExList;
		
	}

	public void setPrdList(List<ProductInfo> prdList) {
		this.prdList = prdList;
		prdExList=new ArrayList<ProductInfo>();
		
		if(prdList.size()%itemNum==0) {
			endNum=prdList.size()/itemNum;
		}
		else{
			endNum=prdList.size()/itemNum+1;
		}
	}
	public List<ProductInfo> getPrdList() {
		int startIdx=pNum*itemNum-itemNum;
		
		int forend=pNum*itemNum-1;
		
		for(int i=startIdx; i<=forend && i<prdList.size(); i++){
			prdExList.add(prdList.get(i));
		}
		return prdExList;
		
	}
	
	public void setOrderInfoList(List<OrderInfo> orderlist) {
		this.orderlist = orderlist;
		orderExlist=new ArrayList<OrderInfo>();
		
		if(orderlist.size()%itemNum==0) {
			endNum=orderlist.size()/itemNum;
		}
		else{
			endNum=orderlist.size()/itemNum+1;
		}
	}
	public List<OrderInfo> getOrderInfoList() {
		int startIdx=pNum*itemNum-itemNum;
		
		int forend=pNum*itemNum-1;
		
		for(int i=startIdx; i<=forend && i<orderlist.size(); i++){
			orderExlist.add(orderlist.get(i));
		}
		return orderExlist;
		
	}
	
	public void pageNum(String page){
		pNum=Integer.parseInt(page);
		
	}
	
	public int getEndPageNum(){
		return endNum;
	}
	
	
	

	
}
