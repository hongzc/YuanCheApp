package com.thinkgem.jeesite.lbst.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.list.TreeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.lbst.entities.AppUser;
import com.thinkgem.jeesite.lbst.entities.Car;
import com.thinkgem.jeesite.lbst.entities.FriendCondition;
import com.thinkgem.jeesite.lbst.entities.Position;
import com.thinkgem.jeesite.lbst.json.util.ListToJson;
import com.thinkgem.jeesite.lbst.map.util.BaiDuMap;
import com.thinkgem.jeesite.lbst.service.AppUserService;
import com.thinkgem.jeesite.lbst.service.MySelfService;
import com.thinkgem.jeesite.lbst.service.PositionService;

@RequestMapping(value="/Fujin")
@Controller
public class FujinController {
	
	@Autowired
	private PositionService positionService;
	
	@Autowired
	private MySelfService myselfService;
	
	@Autowired
	private AppUserService appUserService;
	
	@RequestMapping(value="/fujin")
	@ResponseBody
	public String getNearPeople(@RequestParam(value="phoneNo")String phoneNo,
			@RequestParam(value="tripStatus")String tripStatus){
		
		System.out.println("-----fujin-----");
		
		//通过电话号码查出用户的经纬度
		Position posi = positionService.getPositionByPhoneNo(phoneNo);
		
		List<Position> positions = positionService.getAllPosition(phoneNo,tripStatus);
		
		//新建list保存与其他用户的距离
		List<Object> list = new ArrayList<Object>();
		System.out.println(list);
		//如果查询出用户的经纬度
		if(posi != null){
			for (Position position : positions) {
				
				//测出该用户与其他用户的距离(单位/米)
				double distance = BaiDuMap.GetShortDistance(posi.getLongitude(), posi.getLatitude(), position.getLongitude(), position.getLatitude());
				list.add(distance);
				list.add(position.getPhoneNo());
				System.out.println(list);
			}
			//创建第二个list
			List<Double> list2 = new ArrayList<Double>();
			for(int i = 0; i < list.size(); i++){
				if(i % 2 == 0){
					list2.add((Double)list.get(i));
				}
			}
			Collections.sort(list2);
			System.out.println("list2 = "+list2);
			//新建一个list3保存phoneNo
			List<String> list3 = new ArrayList<String>();
			for(int i = 0; i < list2.size();i++){
				for(int j = 0; j < list.size() / 2; j++){
					if(list2.get(i) == list.get(2*j)){
						list3.add((String)list.get(2*j+1));
					}
				}
			}
			System.out.println("lis3 = "+list3);
			
			//新建list4保存user
			List<AppUser> list4 = new ArrayList<AppUser>();
			
			for(int i = 0; i < list3.size(); i++){
				//通过phoneNo查找对应 的用户信息(车主、乘客)
				Integer userId = myselfService.findUserIdByPhoneNo(list3.get(i));
				//用户的基本信息
				AppUser user = appUserService.getAppUserByPhoneNo(list3.get(i));
				System.out.println("------>" + user.getTripStatus());
				if("车主".equals(user.getTripStatus())){
					//car的信息
					Car car = myselfService.findCarById(userId);
					System.out.println(" car  = " + car);
					//择友条件
					FriendCondition condition = appUserService.getFriConditionByUserId(userId);
					user.setCar(car);
					user.setFricondition(condition);
				}else{
					//择友条件
					FriendCondition condition = appUserService.getFriConditionByUserId(userId);
					user.setFricondition(condition);
					
				}
				list4.add(user);
			}
			
			//找出当前user的登录时间
			AppUser user = appUserService.getAppUserByPhoneNo(phoneNo);
			Date date = user.getLoginTime();
			long t1 = date.getTime();
			
			for(int j = 0 ; j < list2.size();j ++){
				list4.get(j).setDistance(list2.get(j));
				long t2 = list4.get(j).getLoginTime().getTime();
				long t3 = (t1 - t2)/(1000*60);
				
				list4.get(j).setTimeDiff(t3);
			}
			System.out.println("list4 = " + list4);
			String json = ListToJson.ListToJson(list4);
			return json;
		}
		
		return "对不起您没有数据";
	}

}
