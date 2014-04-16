package com.blueone.common.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueone.common.domain.AttachFileInfo;
import com.blueone.common.domain.ResultInfo;
import com.blueone.product.domain.ProductInfo;

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
		public List<AttachFileInfo> getAttFileInfList(AttachFileInfo attFileInfo) {
			
			List<AttachFileInfo> fileList = new ArrayList<AttachFileInfo>();
			
			// -----------------------------------------------
			// DB Insert 수행
			// -----------------------------------------------
			SqlSession sqlSession = sqlSessionFactory.openSession();
			
			try {
				// DB 수행
				fileList = sqlSession.selectList("attFile.selectListBomAttFileTb0001",attFileInfo);
			} finally {
				sqlSession.close();
			}
			
			return fileList;
		}

		/*
		 * 이미지 삭제
		 */
		@Override
		public int deleteAttachFileInf(AttachFileInfo attFileInfo){
			
			int rst = -1;
			
			
				SqlSession sqlSession = sqlSessionFactory.openSession();
				try {
					// DB 수행
					rst = sqlSession.delete("attFile.deleteBomAttachFileTb0001", attFileInfo);
					
				} finally {
					sqlSession.close();
				}
			
			
			return rst;
		}
		

		//상품관리(수정)
		@Override
		public int updateAttachFileInf(AttachFileInfo attFileInfo) {
			
			int rst = -1;
			
			// -----------------------------------------------
			// 해당하는 상품 데이터가 있는지 확인
			// -----------------------------------------------
			List<AttachFileInfo> searchRstInf = getAttFileInfList(attFileInfo);
			
			// -----------------------------------------------
			// 조회한 결과값이 있으면 DB업데이트
			// -----------------------------------------------
			
			
			for(AttachFileInfo each : searchRstInf){
				if (each != null && StringUtils.isNotEmpty(each.getAttCdKey())) {
					SqlSession sqlSession = sqlSessionFactory.openSession();
					try {
						// DB 수행
						rst = sqlSession.update("attFile.updateBomAttachFileTb0001", attFileInfo);
						
					} finally {
						sqlSession.close();
					}
				}
			}
			
			return rst;
		}

		
		
}
