package com.blueone.common.service;

import java.util.HashMap;
import java.util.List;

import com.blueone.common.domain.StatInfo;


public interface IStatService {
	//회원접속통계
	List<StatInfo> getConnection(StatInfo statModel);
	
	//교육대상별신청통계
	List<StatInfo> getAudience(StatInfo statModel);
	
	//교육별신청통계
	List<StatInfo> getProgram(StatInfo statModel);

	//청소년 법교육신청통계
	List<StatInfo> getProgramSch(StatInfo statModel);
	
	//교육실시현황 조회
	List<StatInfo> getTBL090210List(StatInfo statModel);
	int getTBL090210TotalCount(StatInfo statModel);
	HashMap<String, Integer> getTBL090210Stat(StatInfo statModel);
	StatInfo selectTBL090210(long effSeq);
//	List<TBL090211Model> selectTBL090211(long effSeq);
//	List<TBL090212Model> selectTBL090212(long effSeq);
//	List<TBL090212Model> totalTBL090212(StatModel statModel);
	
	//교육실시현황 등록
//	boolean insertTBL090210(StatModel statModel);
//	boolean updateTBL090210(StatModel statModel);
//	boolean deleteTBL090210(StatModel statModel);
	
	// 만족도조사현황 조회
//	List<StatModel> getTBL090211List(StatModel statModel);
//	int getTBL090211TotalCount(StatModel statModel);
//	HashMap<String, Integer> getTBL090211Stat(StatModel statModel);
}
