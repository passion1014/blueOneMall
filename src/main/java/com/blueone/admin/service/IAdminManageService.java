package com.blueone.admin.service;

import java.util.HashMap;
import java.util.List;

import com.blueone.admin.domain.AccountInfo;
import com.blueone.admin.domain.AdminInfo;
import com.blueone.admin.domain.AdminLoginInfo;
import com.blueone.admin.domain.AgreementInfo;
import com.blueone.admin.domain.ConfigInfo;

public interface IAdminManageService {

	public int checkDupAdminId(AdminInfo adminInfo);
	public int registAdminInf(AdminInfo adminInfo); 
	public int editAdminInf(AdminInfo adminInfo);
	public List<AdminInfo> getAdminInfList(AdminInfo adminInfo); 
	public AdminInfo getAdminInfDetail(String id);
	public List<AccountInfo> getAccountInfList(AccountInfo accountInfo);
	public AdminInfo adminLogin(AdminLoginInfo adminLoginInfo);
	public List<AccountInfo> getBankInfList();
	public int registAccountInf(AccountInfo attInfo);
	public List<AccountInfo> getAccountInfList();
	public int deleteAccountInf(AccountInfo attInfo);
	public AccountInfo getAccountInfDetail(AccountInfo accInfo);
	public int editAccountInf(AccountInfo accInfo);
	public int editConfigInf(ConfigInfo configInfo);
	public ConfigInfo selectConfigInf();
	public int editAgreementInf(AgreementInfo agreementInfo);
	public AgreementInfo selectAgreementInf(AgreementInfo agreement);
	public List<AgreementInfo> selectAgreementInfList();
	public int deleteAdminInf(AdminInfo adminInfo);	
	
}
