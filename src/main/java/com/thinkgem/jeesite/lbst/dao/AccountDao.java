package com.thinkgem.jeesite.lbst.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.lbst.entities.Account;

/**
 * 用于一些站内账户操作的类(充值鲜花、车币)
 * @author hcc
 * 下午1:55:31
 */

@MyBatisDao
public interface AccountDao {

	//==============================简单注册之后=================================
	//新建账户表
	@Insert("INSERT INTO app_account (Tcar,flower,phoneNo) VALUE (0,0,#{phoneNo})")
	public int addAccount(@Param("phoneNo")String phoneNo);
	
	//更新user表
	@Update("UPDATE app_user SET accountId=#{accountId},positionId=#{positionId} WHERE id=#{id}")
	public int updateAppUser(Map<String,Object> map);
	//根据phoneNo查找userId
	@Select("SELECT id FROM app_user WHERE phoneNo=#{phoneNo}")
	public Integer getUserId(@Param("phoneNo")String phoneNo);
	//根据phoneNo查找accountId
	@Select("SELECT id FROM app_account WHERE phoneNo=#{phoneNo}")
	public Integer getAccountId(@Param("phoneNo")String phoneNo);
	
	//===============================================================
	//查询账户
	@Select("SELECT Tcar,flower FROM app_account WHERE phoneNo=#{phoneNo}")
	public Account getAccount(@Param("phoneNo")String phoneNo);
	
	//更改账户的车币和鲜花
	@Update("UPDATE app_account SET Tcar=#{Tcar},flower=#{flower} WHERE phoneNo=#{phoneNo}")
	public int updateAccount(Account account);
}
