package com.blueone.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueone.common.domain.CodeModel;
import com.blueone.common.util.Utility;


@Service
public class CodeService implements ICodeService {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	private HashMap<String, Object> valueMap = new HashMap<String, Object>();
	
	@Override
	public List<CodeModel> getCodeList(String codeKnd1, String codeKnd2) {
		
		List<CodeModel> codeList = new ArrayList<CodeModel>();
		
		valueMap.put("codeKnd1", codeKnd1);
		valueMap.put("codeKnd2", codeKnd2);

		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			codeList = sqlSession.selectList("code.getCodeList", valueMap);
//			codeList = sqlMapClientTemplate.queryForList("code.getCodeList", valueMap);
		} finally {
			sqlSession.close();
		}
		
		return codeList;
	}
	
	@Override
	public List<CodeModel> getTBL020110List() {
		List<CodeModel> codeList = new ArrayList<CodeModel>();

		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			codeList = sqlSession.selectList("code.getTBL020110List", valueMap);
		} finally {
			sqlSession.close();
		}

//		return sqlMapClientTemplate.queryForList("code.getTBL020110List", valueMap);
		return codeList;
	}
	
	@Override
	public List<CodeModel> getBoardTypList() {
		List<CodeModel> codeList = new ArrayList<CodeModel>();
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			codeList = sqlSession.selectList("code.getBoardTypList", valueMap);
		} finally {
			sqlSession.close();
		}

//		return sqlMapClientTemplate.queryForList("code.getBoardTypList", valueMap);
		return codeList;
	}
	
	@Override
	public List<CodeModel> getEduSubList() {
		List<CodeModel> codeList = new ArrayList<CodeModel>();
		
		valueMap.put("insideType", "F");
		valueMap.put("orderSequence", "DESC");
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			codeList = sqlSession.selectList("code.getBoardTypList", valueMap);
		} finally {
			sqlSession.close();
		}

//		return sqlMapClientTemplate.queryForList("code.getEduSubList", valueMap);
		return codeList;
	}
	
	@Override
	public List<CodeModel> getEduSubList(String orderSequence) {
		List<CodeModel> codeList = new ArrayList<CodeModel>();

		valueMap.put("insideType", "F");
		valueMap.put("orderSequence", orderSequence);
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			codeList = sqlSession.selectList("code.getBoardTypList", valueMap);
		} finally {
			sqlSession.close();
		}
		
//		return sqlMapClientTemplate.queryForList("code.getEduSubList", valueMap);
		return codeList;
	}
	
	@Override
	public List<CodeModel> getEduSubAllList() {
		List<CodeModel> codeList = new ArrayList<CodeModel>();
		
		valueMap.put("insideType", "");
		valueMap.put("orderSequence", "DESC");

		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			codeList = sqlSession.selectList("code.getEduSubList", valueMap);
		} finally {
			sqlSession.close();
		}
		return codeList;
//		return sqlMapClientTemplate.queryForList("code.getEduSubList", valueMap);
	}
	
	@Override
	public List<CodeModel> getEduTarList() {
		List<CodeModel> codeList = new ArrayList<CodeModel>();
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			codeList = sqlSession.selectList("code.getEduTarList", valueMap);
		} finally {
			sqlSession.close();
		}
		return codeList;
//		return sqlMapClientTemplate.queryForList("code.getEduTarList", valueMap);
	}
	
	@Override
	public List<CodeModel> getEduInsList() {
		List<CodeModel> codeList = new ArrayList<CodeModel>();
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			codeList = sqlSession.selectList("code.getEduInsList", valueMap);
		} finally {
			sqlSession.close();
		}
		return codeList;
//		return sqlMapClientTemplate.queryForList("code.getEduInsList", valueMap);
	}
	
	@Override
	public List<CodeModel> getEduPgmList(int subSeq) {
		List<CodeModel> codeList = new ArrayList<CodeModel>();
		valueMap.put("subSeq", subSeq);
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			codeList = sqlSession.selectList("code.getEduPgmList", valueMap);
		} finally {
			sqlSession.close();
		}
		return codeList;
//		return sqlMapClientTemplate.queryForList("code.getEduPgmList", valueMap);
	}
	
	@Override
	public List<CodeModel> getMailSkinList() {
		List<CodeModel> codeList = new ArrayList<CodeModel>();
	
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			codeList = sqlSession.selectList("code.getMailSkinList", valueMap);
		} finally {
			sqlSession.close();
		}
		return codeList;
//		return sqlMapClientTemplate.queryForList("code.getMailSkinList", valueMap);
	}
	
	@Override
	public List<CodeModel> getMonthList() {
		List<CodeModel> monthList = new ArrayList<CodeModel>();
		for (int i = 1; i <= 12; i++) {
			CodeModel code = new CodeModel();
			code.setCode(Utility.lpad(Integer.toString(i), 2, '0'));
			code.setCodeNm(Utility.lpad(Integer.toString(i), 2, '0'));
			monthList.add(code);
		}
		
		return monthList;
	}
	
	
	/**
	 * 인자로 받은 LIST에서 인자로 받은 code의 값을 돌려주는 메소드
	 * @param list
	 * @param code
	 * @return
	 */
	public String getCodeName(List<CodeModel> list, String code) {
		String rtnValue = "";
		
		if (list != null && list.size() > 0) {
			CodeModel codeModal = null;
			for (int i = 0; i < list.size(); i++) {
				codeModal = list.get(i);
				if (codeModal.getCode().equals(code)) {
					rtnValue = codeModal.getCodeNm();
					break;
				}
			}
		}
		
		return rtnValue;
	}
	
	/**
	 * 인자로 받은 코드정보를 SELECT박서 형태의 HTML로 돌려주는 메소드
	 * @param codeKnd1 대분류
	 * @param codeKnd2 중분류
	 * @return
	 */
	public String getCodeHTML (String name, String codeKnd1, String codeKnd2) {
		StringBuffer codeHtml = new StringBuffer();
		List<CodeModel> codeList = getCodeList(codeKnd1, codeKnd2);
		
		codeHtml.append("<select id='" + name + "' name='" + name + "'  class='form_1'>");
		codeHtml.append("<option value=''>전체</option>");
		if (codeList != null && codeList.size() > 0) {
			for (int i = 0; i < codeList.size(); i++) {
				codeHtml.append("<option value='" + codeList.get(i).getCode() + "'>" + codeList.get(i).getCodeNm() + "</option>");
			}
		}
		codeHtml.append("</select>");
		
		return codeHtml.toString();
	}
	
}
