package com.blueone.common.service;

import java.util.List;
import com.blueone.common.domain.SmsModel;

public interface ISmsService {
	// 선택한 회원의 전화번호정보 조회
	String getCheckHpNo(SmsModel smsModel);
	
	// 등록
	boolean insertArreoSms(SmsModel smsModel);
	
	// 이력조회
	List<SmsModel> getSmsList(SmsModel smsModel);
	int getSmsTotalCount(SmsModel smsModel);
}
