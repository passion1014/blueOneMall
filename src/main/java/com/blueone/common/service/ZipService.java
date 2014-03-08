package com.blueone.common.service;

import java.util.HashMap;
import java.util.List;

import klac.etc.dao.ZipDao;
import klac.etc.model.ZipModel;


import org.springframework.orm.ibatis.SqlMapClientTemplate;


public class ZipService implements IZipService {
	private SqlMapClientTemplate sqlMapClientTemplate;
	private HashMap<String, Object> valueMap = new HashMap<String, Object>();
	
	public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}

	@Override
	public List<ZipModel> getZipList(ZipModel zipModel) {
		return sqlMapClientTemplate.queryForList("zip.getZipList", zipModel);
	}
	
	@Override
	public List<ZipModel> getGiBunList(ZipModel zipModel) {
		return sqlMapClientTemplate.queryForList("zip.getGiBunList", zipModel);
	}
	
	@Override
	public List<ZipModel> getRoadNmList(ZipModel zipModel) {
		return sqlMapClientTemplate.queryForList("zip.getRoadNmList", zipModel);
	}
	
	@Override
	public HashMap<String, Object> makeRoadAddr(String oldAddr) {
		valueMap.put("avGb", "1");
		valueMap.put("avOldAddr", oldAddr);
		sqlMapClientTemplate.queryForList("zip.callSpNewAddrMake", valueMap);
		return valueMap;
	}
	
}
