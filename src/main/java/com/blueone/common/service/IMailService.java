package com.blueone.common.service;

import java.util.List;

import com.blueone.common.domain.MailInfo;
import com.blueone.customer.domain.MemberInfo;

public interface IMailService {
	// 선택한 회원의 메일정보 조회
	String getCheckEmail(MailInfo mailModel);
	
	// 메일발송
	boolean sentMail(MailInfo mailModel);
	
	// 메일발송목록
	List<MailInfo> getList(MailInfo mailModel);
	int getTotalCount(MailInfo mailModel);
	MailInfo selectTBL090810(long mailSeq);
	
	// 아이디찾기
	boolean sentMailUserId(MemberInfo member);
	boolean sentMailPasswd(MemberInfo member);
	
	// 아이디/비번정보 메일발송
	boolean sentMailUserInfo(MemberInfo member);
}
