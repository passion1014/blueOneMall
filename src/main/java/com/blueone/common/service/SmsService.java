package com.blueone.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueone.common.domain.SmsInfo;

@Service
public class SmsService implements ISmsService {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	private HashMap<String, Object> valueMap = new HashMap<String, Object>();
	
	@Override
	public String getCheckHpNo(SmsInfo smsModel) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		StringBuffer hpNoInfo = new StringBuffer();
		List<SmsInfo> smsList = sqlSession.selectList("sms.getCheckHpNo", smsModel);
		if (smsList != null && smsList.size() > 0) {
			for (int i = 0; i < smsList.size(); i++) {
				if (i>0) hpNoInfo.append(",");
				hpNoInfo.append(smsList.get(i).getRcvPhnId());
			}
		}
		
		return hpNoInfo.toString();
	}
	
	@Override
	public boolean insertArreoSms(SmsInfo smsModel) {
		String tmpRcvPhnId = smsModel.getRcvPhnId();
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			String[] rcvPhnId = splitPhoneNumber(new String[] {smsModel.getRcvPhnId()});
			
			// SMS추가(트렌젝션 처리)
//			sqlMapClient.startTransaction();
			
			if (rcvPhnId != null && rcvPhnId.length > 0) {
				for (int i = 0; i < rcvPhnId.length; i++) {
					smsModel.setRcvPhnId(rcvPhnId[i]);
					Object cmpMsgId = sqlSession.insert("sms.insertARREO_SMS", smsModel);
					if (cmpMsgId == null) return false;
				}
			}
			
//			sqlMapClient.commitTransaction();
//			sqlMapClient.getCurrentConnection().commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			smsModel.setRcvPhnId(tmpRcvPhnId);
			sqlSession.close();
		}

		return true;
	}
	
	@Override
	public List<SmsInfo> getSmsList(SmsInfo smsModel) {
		List<SmsInfo> smsList = new ArrayList<SmsInfo>();
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			smsList= sqlSession.selectList("sms.getSmsList", smsModel);
		} finally {
			sqlSession.close();
		}
		
		return smsList;
//		return sqlMapClientTemplate.queryForList("sms.getSmsList", smsModel);
	}
	
	@Override
	public int getSmsTotalCount(SmsInfo smsModel) {
		Integer rst = 0;
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			rst = sqlSession.selectOne("sms.getSmsTotalCount", smsModel);
		} finally {
			sqlSession.close();
		}
		
		return rst;
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
