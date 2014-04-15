package com.blueone.product.service;

import java.util.List;

import com.blueone.product.domain.TransferInfo;

public interface ITransferService {

	public int transferInsert(TransferInfo transferInfo);//배송정보등록
	public List<TransferInfo> transferList(TransferInfo transferInfo); //배송정보목록
	public int transferEdit(TransferInfo transferInfo);//배송정보수정
	public TransferInfo transferDetail(TransferInfo transferInfo);//배송정보상세조회
	public int transDelete(TransferInfo transferInfo);//배송정보삭제
}
