package com.thinkgem.jeesite.lbst.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.lbst.service.AccountService;
import com.thinkgem.jeesite.lbst.service.AppUserService;
import com.thinkgem.jeesite.lbst.service.MySelfService;
import com.thinkgem.jeesite.lbst.service.PositionService;

/**
 * 主要负责用户的注册登录的controller类
 * @author hcc
 * 上午9:38:03
 */

@RequestMapping(value="/appuser")
@Controller
public class AppUserController{
	
	@Autowired
	private AppUserService appUserService;
	@Autowired
	private MySelfService mySelfService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private PositionService positionService;
	
	//================================简单注册用户(电话、密码)=========================================
	@RequestMapping(value="/easyRegist")
	@ResponseBody
	public String regist(@RequestParam(value="phoneNo",required=false)String phoneNo,
			@RequestParam(value="reppassword",required=false)String reppassword,
			@RequestParam(value="password",required=false)String password,
			Map<String,Object> map){
		
		//准备注册 包含 手机号 密码 重复密码
		//电话号码不能为空或者是空串
		if(phoneNo != null && !"".equals(phoneNo)){
			//密码不能为空或者是空串3
			if(password != null && !"".equals(password)){
				if(password.equals(reppassword)){
					map.put("phoneNo", phoneNo);
					map.put("password", password);
					
					//检查手机号码
					List<String> list = appUserService.checkPhone(phoneNo);
					System.out.println(list);
					for (String str : list) {
						if(phoneNo.equals(str)){
							return "5";   //该号码已经被注册过
						}
					}
					int i = appUserService.regist(map);
					if(i == 1){   
						//注册成功后新建择友条件
						Integer id = mySelfService.findUserIdByPhoneNo(phoneNo);
						mySelfService.newFriCondition(id);
						//注册成功后新建账户
						int j = accountService.addAccount(phoneNo);
						//新建位置信息
						int l = positionService.addPositionByPhoneNo(phoneNo);
						//查找AccountId
						Integer accountId = accountService.getAccountId(phoneNo);
						//查找positionId
						Integer positionId = positionService.getPositionId(phoneNo);
						//查找userId
						Integer userId = accountService.getUserId(phoneNo);
						Map<String,Object> map2 = new HashMap<String,Object>();
						map2.put("accountId", accountId);
						map2.put("id", userId);
						map2.put("positionId", positionId);
						int k = accountService.updateAppUser(map2);
						return "1";  // 简单注册成功
					}
				}
				return "2"; //两次密码不一致
			}
			return "3";    //请输入密码
		}
		return "4";        //注册失败
	}
	
	
	
	//================================登录用户=========================================
	@RequestMapping(value="/login")
	@ResponseBody
	public String login(@RequestParam(value="phoneNo",required=false)String phoneNo,
			@RequestParam(value="password",required=false)String password){
		
		
		String psd = appUserService.login(phoneNo); //用手机号从数据库获取密码
		
		if(password != null){
			if(password.equals(psd)){
				int i = appUserService.updateLoginTime(phoneNo);
				if(i == 1){
					return phoneNo; //登陆成功的话，返回手机号
				}else{
					return "0";
				}
			}
		}
		return "0"; //代表失败
	}
	
	
}
