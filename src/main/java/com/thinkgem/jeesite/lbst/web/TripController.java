package com.thinkgem.jeesite.lbst.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.lbst.entities.Trip;
import com.thinkgem.jeesite.lbst.json.util.ListToJson;
import com.thinkgem.jeesite.lbst.service.MySelfService;
import com.thinkgem.jeesite.lbst.service.TripService;

@RequestMapping(value="/trip")
@Controller
public class TripController {

	@Autowired
	private TripService tripService;
	@Autowired
	private MySelfService mySelfService;
	
	//====================新建线路====================
	@RequestMapping(value="/addTrip")
	@ResponseBody
	public String addTrip(@RequestParam(value="regularORtimely")String regularORtimely,
			@RequestParam(value="startArea",required=false)String startArea,
			@RequestParam(value="tripTime",required=false)String tripTime,
			@RequestParam(value="endArea",required=false)String endArea,
			@RequestParam(value="drive",required=false)String drive ,  
			@RequestParam(value="phoneNo",required=false)String phoneNo,
			@RequestParam(value="tripStatus",required=false)String tripStatus){
		
		//确定该线路的用户是谁
		Integer userId = mySelfService.findUserId(phoneNo,tripStatus);
		
		Date releaseTime = new Date();  //发布时间
		
		//首先判断是车友还是乘友;
		if("车友".equals(tripStatus)){
			//车友只有固定线路
			regularORtimely = "固定线路";
			Trip trip = new Trip(startArea,tripTime,endArea,drive,releaseTime,regularORtimely,userId,"正在约行");
			String str = tripService.addTrip(trip);
			return str+"userId = " + userId;
		}else{
			//乘友(固定线路或者是及时线路)
			if("固定线路".equals(regularORtimely)){
				//如果是固定线路
				//regularORtimely = "固定线路";
				Trip trip = new Trip(startArea,tripTime,endArea,drive,releaseTime,regularORtimely,userId,"正在约行");
				String str = tripService.addTrip(trip);
				return str;
			}else{
				//如果是及时线路
				regularORtimely = "及时线路";
				Trip trip = new Trip(startArea,endArea,drive,releaseTime,regularORtimely,userId,"正在约行");
				String str = tripService.addTrip(trip);
				return str;
			}
		}
		
	}
	
	//====================查找线路====================
	@RequestMapping(value="/findTrip")
	@ResponseBody
	public String findTrip(@RequestParam(value="tripStatus",required=false)String tripStatus,
			@RequestParam(value="phoneNo",required=false)String phoneNo){
		
		//确定该线路的用户是谁
		Integer userId = mySelfService.findUserId(phoneNo,tripStatus);
		
		//根据用户id查找对应线路
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("phoneNo", phoneNo);
		map.put("tripStatus", tripStatus);
		
		List<Trip> trips = tripService.getTrip(map);
		String json = ListToJson.ListToJson(trips);
		return json;
		
	}
	
	//====================修改线路====================
	@RequestMapping(value="/updateTrip")
	@ResponseBody
	public String updateTrip(@RequestParam(value="regularORtimely")String regularORtimely,
			@RequestParam(value="startArea",required=false)String startArea,
			@RequestParam(value="tripTime",required=false)String tripTime,
			@RequestParam(value="endArea",required=false)String endArea,
			@RequestParam(value="drive",required=false)String drive ,  
			@RequestParam(value="phoneNo",required=false)String phoneNo,
			@RequestParam(value="tripStatus",required=false)String tripStatus){
		
		//确定该线路的用户是谁
		Integer userId = mySelfService.findUserId(phoneNo,tripStatus);
		
		Date releaseTime = new Date();  //发布时间
		
		//首先判断是车友还是乘友;
		if("车友".equals(tripStatus)){
			//车友只有固定线路
			regularORtimely = "固定线路";
			Trip trip = new Trip(startArea,tripTime,endArea,drive,releaseTime,regularORtimely,userId,"正在约行");
			int i = tripService.updateTrip(trip);
			return i+"-"+phoneNo;
		}else{
			//乘友(固定线路或者是及时线路)
			if("固定线路".equals(regularORtimely)){
				//如果是固定线路
				//regularORtimely = "固定线路";
				Trip trip = new Trip(startArea,tripTime,endArea,drive,releaseTime,regularORtimely,userId,"正在约行");
				int i = tripService.updateTrip(trip);
				return i+"-"+phoneNo;
			}else{
				//如果是及时线路
				regularORtimely = "及时线路";
				Trip trip = new Trip(startArea,endArea,drive,releaseTime,regularORtimely,userId,"正在约行");
				int i = tripService.updateTrip(trip);
				return i+"-"+phoneNo;
			}
		}
	}
	
	
}
