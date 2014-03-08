package com.blueone.common.service;

import java.util.HashMap;
import java.util.List;

import klac.etc.dao.SmsDao;
import klac.etc.model.SmsModel;


import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;


public class SmsService implements ISmsService {
	private SqlMapClient sqlMapClient;
	private SqlMapClientTemplate sqlMapClientTemplate;
	private HashMap<String, Object> valueMap = new HashMap<String, Object>();
	
	public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
		this.sqlMapClient = sqlMapClientTemplate.getSqlMapClient();
	}
	
	@Override
	public String getCheckHpNo(SmsModel smsModel) {
		StringBuffer hpNoInfo = new StringBuffer();
		List<SmsModel> smsList = sqlMapClientTemplate.queryForList("sms.getCheckHpNo", smsModel);
		if (smsList != null && smsList.size() > 0) {
			for (int i = 0; i < smsList.size(); i++) {
				if (i>0) hpNoInfo.append(",");
				hpNoInfo.append(smsList.get(i).getRcvPhnId());
			}
		}
		
		return hpNoInfo.toString();
	}
	
	@Override
	public boolean insertArreoSms(SmsModel smsModel) {
		String tmpRcvPhnId = smsModel.getRcvPhnId();
		
		try {
			
			String[] rcvPhnId = splitPhoneNumber(new String[] {smsModel.getRcvPhnId()});
			
			// SMS추가(트렌젝션 처리)
			sqlMapClient.startTransaction();
			
			if (rcvPhnId != null && rcvPhnId.length > 0) {
				for (int i = 0; i < rcvPhnId.length; i++) {
					smsModel.setRcvPhnId(rcvPhnId[i]);
					Object cmpMsgId = sqlMapClient.insert("sms.insertARREO_SMS", smsModel);
					if (cmpMsgId == null) return false;
				}
			}
			
			sqlMapClient.commitTransaction();
			sqlMapClient.getCurrentConnection().commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			smsModel.setRcvPhnId(tmpRcvPhnId);
			try { if (sqlMapClient != null) sqlMapClient.endTransaction(); } catch (Exception e) {};
		}

		return true;
	}
	
	@Override
	public List<SmsModel> getSmsList(SmsModel smsModel) {
		return sqlMapClientTemplate.queryForList("sms.getSmsList", smsModel);
	}
	
	@Override
	public int getSmsTotalCount(SmsModel smsModel) {
		return (Integer) sqlMapClientTemplate.queryForObject("sms.getSmsTotalCount", smsModel);
	}
	
	/**
	 * , ; 로 구분되어 한꺼번에 넘어오는 전화번호를 배열화
	 * 이미 배열로 넘어오면 받은 인자를 그대로 돌려줌
	 * @param phoneNumbers 보내는사람 번호
	 * @return
	 */
	private static String[] splitPhoneNumber(String[] phoneNumbers) {

		String[] tmp_destNum = null;

		if (phoneNumbers.length == 1 && (phoneNumbers[0].indexOf(",") != -1)
				|| (phoneNumbers[0].indexOf(";") != -1)) {
			tmp_destNum = phoneNumbers[0].replaceAll(",", ";").split(";");
		} else {
			tmp_destNum = phoneNumbers;
		}

		return tmp_destNum;
	}
	
}//End class
