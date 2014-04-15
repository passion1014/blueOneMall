package com.blueone.product.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueone.product.domain.TransferInfo;

@Service
public class TransferServiceImpl implements ITransferService{

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	
	@Override
	public int transferInsert(TransferInfo transferInfo){
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int result = 0;
		
		try{
			
		result = sqlSession.insert("transfer.insertBomTransferTb0001", transferInfo);	
			
		}finally{
			sqlSession.close();
		}
		
		return result;
		
	}
	
	
	@Override
	public List<TransferInfo> transferList(TransferInfo transferInfo){
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<TransferInfo> list = new ArrayList<TransferInfo>();
		try{
			
		list = 	sqlSession.selectList("transfer.selectListBomTransferTb0001", transferInfo);
			
		}finally{
			sqlSession.close();
		}
		
		return list;
		
	}
	
	@Override
	public int transferEdit(TransferInfo transferInfo){
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int result = 0;
		try{
			
			result = sqlSession.update("transfer.updateBomTransferTb0001", transferInfo);
				
		}finally{
				sqlSession.close();
		}
		return result;
	}
	
	@Override
	public TransferInfo transferDetail(TransferInfo transferInfo){
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		TransferInfo transDetail = new TransferInfo();
		
		try{
			
			transDetail = sqlSession.selectOne("transfer.selectDtlBomTransfer0001", transferInfo);
				
		}finally{
				sqlSession.close();
		}
		
		return transDetail;
		
	}
	
	@Override
	public int transDelete(TransferInfo transferInfo){
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int result = 0;
		try{
			
			result = sqlSession.delete("transfer.deleteBomTransferTb0001", transferInfo);
			
		}finally{
			sqlSession.close();
		}
		
		return result;
		
		
	}
	
}

