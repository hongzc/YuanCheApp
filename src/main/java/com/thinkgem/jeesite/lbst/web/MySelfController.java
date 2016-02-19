package com.thinkgem.jeesite.lbst.web;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.lbst.entities.AppUser;
import com.thinkgem.jeesite.lbst.entities.Car;
import com.thinkgem.jeesite.lbst.entities.FriendCondition;
import com.thinkgem.jeesite.lbst.json.util.BeanToJson;
import com.thinkgem.jeesite.lbst.service.AppUserService;
import com.thinkgem.jeesite.lbst.service.MySelfService;

/**
 * 该controller类 用于 用户完善修改自己的资料
 * @author hcc
 * 下午5:01:54
 */
@RequestMapping("/myself")
@Controller
public class MySelfController {

	@Autowired
	private MySelfService myselfService;
	@Autowired
	private AppUserService appUserService;
	
	//====================返回给当前资料信息====================
	@RequestMapping(value="/finddata")
	@ResponseBody
	public String DataModify(@RequestParam(value="phoneNo")String phoneNo,
			@RequestParam(value="tripStatus",required=false)String tripStatus){
		
		if("乘友".equals(tripStatus)){
			if(phoneNo != null || phoneNo.length() > 0){
				AppUser user = myselfService.findDataByDrive(phoneNo);
				System.out.println("user = " + user);
				String userJson = BeanToJson.getJsonGenerator(user);
				if(userJson == null){
					return "没有资料";
				}
				return userJson;
			}
			return null;
		}
		else{
			if(phoneNo != null || phoneNo.length() > 0){
				
				AppUser user = myselfService.findDataByCar(phoneNo);
				//查询出carId
				int carId = myselfService.findCarIdByPhoneNo(phoneNo);
				//查询出car
				Car car = myselfService.findCarById(carId);
				
				//把car手动包装进user中，比较麻烦一点
				
				user.setCar(car);
				
				String userJson = BeanToJson.getJsonGenerator(user);
				
				if(userJson == null){
					return "没有资料";
				}
				return userJson;
			}
			return null;
		}
	}
	
	//====================修改乘友资料====================
	@RequestMapping("/drivedatamodify")
	@ResponseBody
	public String DriveDataModify(@RequestParam(value="username",required=false)String username,
			@RequestParam(value="location",required=false)String location,
			@RequestParam(value="maritalStatus",required=false)String maritalStatus,
			@RequestParam(value="education",required=false)String education,
			@RequestParam(value="prefession",required=false)String prefession,
			@RequestParam(value="income",required=false)String income,
			@RequestParam(value="interest",required=false)String interest,
			@RequestParam(value="phoneNo",required=false)String phoneNo){
		
		AppUser appuser = new AppUser(phoneNo,username,location,maritalStatus,education,prefession,income,interest);
		//修改乘友资料
		int i = myselfService.driveDataModify(appuser);
		if(i == 1){
			return "资料修改成功!";
		}
		return "资料修改失败!";
	}
	
	//====================修改车友资料====================
	@RequestMapping("/carfrienddatamodify")
	@ResponseBody
	public String CarfriendDataModify(@RequestParam(value="username",required=false)String username,
			@RequestParam(value="location",required=false)String location,
			@RequestParam(value="maritalStatus",required=false)String maritalStatus,
			@RequestParam(value="education",required=false)String education,
			@RequestParam(value="prefession",required=false)String prefession,
			@RequestParam(value="income",required=false)String income,
			@RequestParam(value="interest",required=false)String interest,
			@RequestParam(value="phoneNo",required=false)String phoneNo,
			@RequestParam(value="carId")String carId,
			@RequestParam(value="carNo")String carNo,
			@RequestParam(value="carIdBirth")String carIdBirth,
			@RequestParam(value="carIs")String carIs){
		
		//通过phoneNo找出carId以及对应的car表中的id
		Integer cid = myselfService.findCarIdByPhoneNo(phoneNo);
		System.out.println("查询到的车子的编号为: "+ cid);
		Car car = new Car(cid,carId,carNo,carIdBirth,carIs);
		AppUser appuser = new AppUser(phoneNo,username,location,maritalStatus,education,prefession,income,interest);
		//修改乘友资料
		int i = myselfService.CarfriendDataModify(appuser,car);
		if(i == 1){
			return "资料修改成功!";
		}
		return "资料修改失败!";
	}
	
	//====================修改择友条件====================
	@RequestMapping(value="/updatefricondition")
	@ResponseBody
	public String addFriendCondition(@RequestParam(value="tripStatus")String tripStatus,
			@RequestParam(value="phoneNo")String phoneNo, 
			@RequestParam(value="ageRa",required=false)String ageRa,
			@RequestParam(value="heightRa",required=false)String heightRa,
			@RequestParam(value="incomeRa",required=false)String incomeRa,
			@RequestParam(value="education",required=false)String education, 
			@RequestParam(value="maritalStatus",required=false)String maritalStatus,
			@RequestParam(value="location",required=false)String location){
		
		//首先根据PhoneNo和tripStatus确定用户是谁
		//确定用户的id
		Integer userId = myselfService.findUserId(phoneNo,tripStatus);
		
		FriendCondition fc = new FriendCondition(ageRa,heightRa,incomeRa,education,maritalStatus,location,userId);
		String str = myselfService.addFriendCondition(fc);
		if("1".equals(str)){
			return "修改成功!";
		}
		return "修改失败!";
	}
	
	//====================修改内心独白====================
	@RequestMapping(value="/heartModify")
	@ResponseBody
	public String heartModify(@RequestParam(value="tripStatus")String tripStatus,
			@RequestParam(value="phoneNo")String phoneNo,
			@RequestParam(value="heart",required=false)String heart){
		
		//首先根据PhoneNo和tripStatus确定用户是谁
		//确定用户的id
		Integer userId = myselfService.findUserId(phoneNo,tripStatus);
		
		Map<String,Object> map2 = new HashMap<String,Object>();
		map2.put("heart", heart);
		map2.put("userId", userId);
		
		int i = myselfService.heartModify(map2);
		if(i == 1){
			return "修改成功!";
		}
		return "修改失败";
	}
	
	//====================快速注册====================
	@RequestMapping(value="/quickRegist")
	@ResponseBody
	public String quickRegist(@RequestParam(value="username")String username,
				@RequestParam(value="gender")String gender,
				@RequestParam(value="phoneNo")String phoneNo,
				@RequestParam(value="tripStatus")String tripStatus,
				@RequestParam(value="height")Integer height,
				@RequestParam(value="birth")String birth,
				@RequestParam(value="location")String location,
				@RequestParam(value="maritalStatus")String maritalStatus,
				@RequestParam(value="education")String education,
				@RequestParam(value="prefession")String prefession,
				@RequestParam(value="income")String income,
				@RequestParam(value="interest")String interest,
				@RequestParam(value="carId",required=false)String carId,
				@RequestParam(value="carNo",required=false)String carNo,
				@RequestParam(value="carIdBirth",required=false)String carIdBirth,
				@RequestParam(value="carIs",required=false)String carIs){
		
		
		System.out.println("----------来准备快速注册----------");
		
		AppUser user = new AppUser(username,phoneNo,gender,height,birth,location,maritalStatus,education,prefession,income,interest);
		
		//首先判断该用户是乘客还是车主
		if("乘客".equals(tripStatus)){
			//如果该用户是乘客
			if(user != null ){
				String str = appUserService.quickRegistDrive(user);
				return str;
			}
			return null;
			
		}else{
			//如果该用户是车主，先获取该用户的userId
			Integer userId = myselfService.findUserIdByPhoneNo(phoneNo);
			Car car = new Car(carId,carNo,carIdBirth,carIs,userId);
			if(user != null && car != null){
				String str = appUserService.quickRegistCar(user, car);
				return str;
			}
			return null;
		}
		
	}
	
}
