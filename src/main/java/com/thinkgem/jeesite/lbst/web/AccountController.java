package com.thinkgem.jeesite.lbst.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.lbst.entities.Account;
import com.thinkgem.jeesite.lbst.json.util.BeanToJson;
import com.thinkgem.jeesite.lbst.service.AccountService;
import com.thinkgem.jeesite.lbst.service.MySelfService;

/**
 * 站内账户controller类
 * @author hcc
 * 下午2:03:52
 */
@RequestMapping(value="/account")
@Controller
public class AccountController {
 
	@Autowired
	private AccountService accountService;
	@Autowired
	private MySelfService mySelfService;
	
	//====================查询自己的账户====================
	@RequestMapping(value="/findAccount")
	@ResponseBody
	public String findAccount(@RequestParam(value="phoneNo",required=false)String phoneNo,
			@RequestParam(value="tripStatus",required=false)String tripStatus){
		
		if(phoneNo != null ){
			//确定用户
			Account account = accountService.getAccount(phoneNo);
			String str = BeanToJson.getJsonGenerator(account);
			return str;
		}
		return null;
	}
	
	//====================个人账户充值或者消费====================
	@RequestMapping(value="/updateAccount")
	@ResponseBody
	public String updateAccount(@RequestParam(value="phoneNo")String phoneNo,
			@RequestParam(value="tcar",required=false)Integer tcar,
			@RequestParam(value="flower",required=false)Integer flower){
		
		//原本的账户
		Account account = accountService.getAccount(phoneNo);
		if(account != null){
			if(tcar < 0 || flower < 0){
				//消费车币、鲜花
				Integer tcar2 = account.getTcar() + tcar;
				Integer flower2 = account.getFlower() + flower;
				
				if(tcar2 < 0 || flower2 < 0){
					return "您的账户余额不够,请您及时充值!";
				}
				Account account2 = new Account(tcar2,flower2,phoneNo);
				int i = accountService.updateAccount(account2);
				if(i == 1){
					return "消费成功!";
				}
			}else{
				//充值车币、鲜花
				Integer tcar2 = account.getTcar() + tcar;
				Integer flower2 = account.getFlower() + flower;
				
				Account account2 = new Account(tcar2,flower2,phoneNo);
				int i = accountService.updateAccount(account2);
				if(i == 1){
					return "充值成功!";
				}
			}
			
		}
		return "系统错误!";
	}
	
	
	
	
	
	
	
	
	
	
}
