package com.blueone.shop.service;

import java.util.List;

import org.springframework.ui.Model;

import com.blueone.shop.domain.ShopInfo;

public interface ShopService {
	
	
	public int shopInsert(ShopInfo shopInfo);
	public List<ShopInfo>  getList(ShopInfo shopInfo);
	/*public ShopInfo getInfo(String data);*/

}
