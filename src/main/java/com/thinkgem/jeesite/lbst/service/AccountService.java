package com.thinkgem.jeesite.lbst.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.lbst.dao.AccountDao;
import com.thinkgem.jeesite.lbst.entities.Account;

/**
 * 站内账户操作类
 * @author hcc
 * 下午2:00:11
 */
@Service
public class AccountService {

	@Autowired
	private AccountDao accountDao;
	
	//简单注册一个用户的时候 创建账户
	@Transactional
	public int addAccount(String phoneNo){
		if(phoneNo != null){
			int i = accountDao.addAccount(phoneNo);
			return i;
		}
		return 0;
	}
	
	//根据phoneNo查找accountId
	@Transactional(readOnly=true)
	public Integer getAccountId(String phoneNo){
		if(phoneNo != null){
			Integer accountId = accountDao.getAccountId(phoneNo);
			return accountId;
		}
		return null;
	}
	//根据phoneNo查找userId
	@Transactional(readOnly=true)
	public Integer getUserId(String phoneNo){
		if(phoneNo != null){
			Integer accountId = accountDao.getUserId(phoneNo);
			return accountId;
		}
		return null;
	}
	
	//更新自己的app_user中的accountId列
	@Transactional
	public int updateAppUser(Map<String,Object> map){
		int i = accountDao.updateAppUser(map);
		return i;
	}
	
	//查找自己的账户
	@Transactional(readOnly=true)
	public Account getAccount(String phoneNo){
		if(phoneNo != null){
			Account account = accountDao.getAccount(phoneNo);
			return account;
		}
		return null;
	}
	
	//账户充值或者消费
	@Transactional
	public int updateAccount(Account account){
		if(account != null){
			int i = accountDao.updateAccount(account);
			return i;
		}
		return 0;
	}
	
}
