package com.blueone.common.service;

import java.util.List;
import com.blueone.common.domain.SmsInfo;

public interface ISmsService {
	// 선택한 회원의 전화번호정보 조회
	String getCheckHpNo(SmsInfo smsModel);
	
	// 등록
	boolean insertArreoSms(SmsInfo smsModel);
	
	// 이력조회
	List<SmsInfo> getSmsList(SmsInfo smsModel);
	int getSmsTotalCount(SmsInfo smsModel);
}
