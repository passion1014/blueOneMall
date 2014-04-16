package com.blueone.user.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueone.user.domain.UserInfo;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public int registUserInfo(UserInfo userInfo) {
		
		int rst = -1;
		SqlSession sqlSession = sqlSessionFactory.openSession();

		// -----------------------------------------------
		// DB Insert 수행
		// -----------------------------------------------
		try {
			rst = sqlSession.insert("user.insertBomCustTb0001", userInfo);
		} finally {
			sqlSession.close();
		}
		
		return rst;
	}

	
}
