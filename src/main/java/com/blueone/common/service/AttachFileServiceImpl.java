package com.blueone.common.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueone.common.domain.AttachFileInfo;
import com.blueone.common.domain.ResultInfo;

@Service
public class AttachFileServiceImpl implements IAttachFileManageService{
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	 public static List stringsToList(String[] strings) {
	     List list = new ArrayList();
	     if(strings != null) {
	      if(strings.length > 0) {
	       for(int i=0; i<strings.length; i++) {
	        list.add(strings[i]);
	       }
	      }
	     }
	     return list;
	    } 
	 
	 
	 @Override
		public AttachFileInfo registProductImgInfo(AttachFileInfo attFileInfo) {
			ResultInfo rstInfo = new ResultInfo();
			
			/*
			// -----------------------------------------------
			// 체크로직
			// -----------------------------------------------
			String checkRst = checkProductInfo(attFileInfo);
			if (!"1".equals(checkRst)) return attFileInfo;
			*/
			
			// -----------------------------------------------
			// DB Insert 수행
			// -----------------------------------------------
			SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
				
				int rst = sqlSession.insert("attFile.insertBomAttachFileTb0001", attFileInfo);
				
			} finally {
				sqlSession.close();
			}
			
			return attFileInfo;
		}
	 

		//상품 목록 이미지 불러옴
		@Override
		public List<AttachFileInfo> getAttFileInfList() {
			
			List<AttachFileInfo> fileList = new ArrayList<AttachFileInfo>();
			
			// -----------------------------------------------
			// DB Insert 수행
			// -----------------------------------------------
			SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
				// DB 수행
				fileList = sqlSession.selectList("attFile.selectListBomAttFileTb0001");
			} finally {
				sqlSession.close();
			}
			
			return fileList;
		}

}
