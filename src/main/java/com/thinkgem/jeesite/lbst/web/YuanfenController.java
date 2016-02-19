package com.thinkgem.jeesite.lbst.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.thinkgem.jeesite.lbst.entities.AppUser;
import com.thinkgem.jeesite.lbst.entities.Car;
import com.thinkgem.jeesite.lbst.entities.FriendCondition;
import com.thinkgem.jeesite.lbst.entities.Trip;
import com.thinkgem.jeesite.lbst.json.util.ListToJson;
import com.thinkgem.jeesite.lbst.service.AppUserService;
import com.thinkgem.jeesite.lbst.service.MySelfService;
import com.thinkgem.jeesite.lbst.service.TripService;


/**
 * 点击缘分按钮时候 完善个人资料
 * @author hccaa
 * 下午2:32:05
 */

@RequestMapping(value="yuanfen")
@Controller
public class YuanfenController {
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private MySelfService myselfService;
	
	@Autowired
	private TripService tripService;
	
	//====================注册是车友还是乘友====================
	@SuppressWarnings("unused")
	@RequestMapping(value="/tripStatus")
	@ResponseBody
	public String tripStatus(@RequestParam(value="tripStatus",required=false)String tripStatus,
			@RequestParam(value="phoneNo",required=false)String phoneNo,Map<String,Object> map){
		
		//判断有没有该角色(tripStatus)
		String status = appUserService.getTripStatus(phoneNo);
		
		if(tripStatus != null && tripStatus.equals(status) ){
			
			//通过phoneNo和tripStatus获取userId
			Integer id = myselfService.findUserId(phoneNo, tripStatus);
			
			//如果两个状态一致
			if("车主".equals(tripStatus)){
				//是车友，新建路线图
				//查看是否存在路线表
				Integer tripIds = appUserService.getTripIdByUserId(id,tripStatus);
				if(tripIds == 0){
					//没有数据的时候(正常情况下)
					Trip trip = new Trip(null,null,null,null,new Date(),"车主固定线路",id,null);
					//新建路线图
					System.out.println("trip-1- = " + id);
					tripService.addTrip(trip);
				}
				//查询有没有之前的car表
				Integer appcarId = appUserService.findIdByUser(id);
				if(appcarId != null){
					return phoneNo;
				}
				myselfService.newCar(id);
				return phoneNo;
			}else{
				//是乘友，新建两张、返回所有车友
				System.out.println("==============id=============>" + id);
				//查看是否存在路线表
				Integer tripIds = appUserService.getTripIdByUserId(id,tripStatus);
				if(tripIds == 0){
					//没有数据的时候(正常情况下)
					Trip trip = new Trip(null,null,null,null,new Date(),"乘客固定线路",id,null);
					tripService.addTrip(trip);
					Trip trip2 = new Trip(null,null,null,null,new Date(),"乘客及时线路",id,null);
					System.out.println("trip-2- = " + id);
					tripService.addTrip(trip2);
					return phoneNo;
				}else{
					return phoneNo;
				}
	
			}
		
		}else{
			
			//通过phoneNo和tripStatus获取userId
			Integer id = myselfService.findUserId(phoneNo, tripStatus);
			
			//两个状态不一致
			//改变状态
			map.put("phoneNo", phoneNo);
			map.put("tripStatus", tripStatus);
			appUserService.perfecteStepStatus(map);
			
			//通过phoneNo和tripStatus获取userId
			Integer id2 = myselfService.findUserId(phoneNo, tripStatus);
			System.out.println("id2 = "+id2);
			
			if("车主".equals(tripStatus)){
				//是车友，新建路线图、返回所有乘友
				//查看是否存在路线表
				Integer tripIds = appUserService.getTripIdByUserId(id2,tripStatus);
				if(tripIds == 0){
					//查询之前有没有对应的trip表
					Trip trip = new Trip(null,null,null,null,new Date(),"车主固定线路",id2,null);
					//新建路线图
					System.out.println("trip-3- = " + id2);
					tripService.addTrip(trip);
				}
				//查询有没有之前的car表
				Integer appcarId = appUserService.findIdByUser(id2);
				if(appcarId != null){
					return phoneNo;
				}
				myselfService.newCar(id2);
				return phoneNo;
			}else{
				//是乘友，新建两张
				//查看是否存在路线表
				Integer tripIds = appUserService.getTripIdByUserId(id2,tripStatus);
				if(tripIds == 0){
					Trip trip = new Trip(null,null,null,null,new Date(),"乘客固定线路",id2,null);
					tripService.addTrip(trip);
					Trip trip2 = new Trip(null,null,null,null,new Date(),"乘客及时线路",id2,null);
					tripService.addTrip(trip2);
					System.out.println("trip-4- = " + id2);
					return phoneNo;
				}else{
					return phoneNo;
				}
				
	
			}
		}
		
	}
	
	//====================点击附近页====================
		@RequestMapping(value="/yuanfen")
		@ResponseBody
		public String fujin(@RequestParam(value="tripStatus",required=false)String tripStatus,
				@RequestParam(value="phoneNo",required=false)String phoneNo,
				@RequestParam("pageNo") Integer pageNo,Map<String,Object> map){
				//查找对应的user表的id
				//startArea,tripTime,endArea,drive,releaseTime,regularORtimely,userId,"正在约行"
				//选择好了乘友还是车友之后 判断是车友还是乘友 新建一张路线图
			
			//每一页的显示条数
			Integer pageSize = 10;
			
			if(pageNo == null || pageNo <= 0){
				pageNo = 1;
			}
			
			Integer index  = (pageNo-1) * pageSize;
			
				if("车友".equals(tripStatus)){
					//是车友，新建路线图、返回所有乘友
					
					List<AppUser> allDrive = appUserService.getAllDrive(index,pageSize);
					List<AppUser> allDrive2 = new ArrayList<AppUser>();
					for (AppUser appUser : allDrive) {
						int id = appUser.getId();
						FriendCondition condition = appUserService.getFriConditionByUserId(id);
						appUser.setFricondition(condition);
						allDrive2.add(appUser);
					}
					System.out.println("allDrive = " + allDrive);
					System.out.println("allDrive2 = " + allDrive2);
					String allDriveStr = ListToJson.ListToJson(allDrive2);
					return allDriveStr+"pageNo="+pageNo;
				}else{
					//是乘友，新建两张、返回所有车友
					
					List<AppUser> allCar = appUserService.getAllCar(index,pageSize);
					List<AppUser> allCar2 = new ArrayList<AppUser>();
					for (AppUser appUser : allCar) {
						int id = appUser.getId();
						Car car = appUserService.getCarByUserId(id);
						FriendCondition condition = appUserService.getFriConditionByUserId(id);
						appUser.setCar(car);
						appUser.setFricondition(condition);
						allCar2.add(appUser);
						
					}
					System.out.println("allCar = " + allCar);
					System.out.println("allCar2 = " + allCar2);
					String allCarStr = ListToJson.ListToJson(allCar);
					return allCarStr+"pageNo="+pageNo;
				}
		}
	
	//====================详细注册其余资料（乘友、车友）====================
	//=========乘友
	@RequestMapping(value="/perfectedDrive")
	@ResponseBody
	public String perfectedDrive(@RequestParam(value="username",required=false)String username,
			@RequestParam(value="gender",required=false)String gender,
			@RequestParam(value="height",required=false)Integer height,
			@RequestParam(value="birth",required=false)String birth,
			@RequestParam(value="location",required=false)String location,
			@RequestParam(value="maritalStatus",required=false)String maritalStatus,
			@RequestParam(value="education",required=false)String education,
			@RequestParam(value="prefession",required=false)String prefession,
			@RequestParam(value="income",required=false)String income,
			@RequestParam(value="interest",required=false)String interest,
			@RequestParam(value="phoneNo")String phoneNo,Map<String,Object> map){
		
		Map<String,Object> map2 = new HashMap<String,Object>();
		map2.put("username", username);
		map2.put("gender", gender);
		map2.put("height", height);
		map2.put("birth", birth);
		map2.put("location", location);
		map2.put("maritalStatus", maritalStatus);
		
		for(Map.Entry<String, Object> entry : map2.entrySet()){
			System.out.println(entry.getKey() +"-> " +(entry.getValue()+"").length());
			if(entry.getValue() == null || (entry.getValue()+"").length() == 0){
				return entry.getKey()+"不能为空";
			}
		}
		
		map.put("username", username);
		map.put("gender", gender);
		map.put("height", height);
		map.put("birth", birth);
		map.put("location", location);
		map.put("maritalStatus", maritalStatus);
		map.put("education", education);
		map.put("prefession", prefession);
		map.put("income", income);
		map.put("interest", interest);
		map.put("phoneNo", phoneNo);
		
		int perfectedDrive = appUserService.perfectedDrive(map);
		
		if(perfectedDrive == 0){
			return "系统错误";
		}else{
			return "填写成功";
		}
	}
	
	//=========车友
	@RequestMapping(value="/perfectedCarFriend")
	@ResponseBody
	public String perfectedCarFriend(@RequestParam(value="username",required=false)String username,
			@RequestParam(value="gender",required=false)String gender,
			@RequestParam(value="height",required=false)Integer height,
			@RequestParam(value="birth",required=false)String birth,
			@RequestParam(value="location",required=false)String location,
			@RequestParam(value="maritalStatus",required=false)String maritalStatus,
			@RequestParam(value="education",required=false)String education,
			@RequestParam(value="prefession",required=false)String prefession,
			@RequestParam(value="income",required=false)String income,
			@RequestParam(value="interest",required=false)String interest,
			@RequestParam(value="phoneNo")String phoneNo,
			
			@RequestParam(value="carId")String carId,
			@RequestParam(value="carNo")String carNo,
			@RequestParam(value="carIdBirth")String carIdBirth,
			@RequestParam(value="carIs")String carIs,Map<String,Object> map){
		
		//先满足条件 让该填写的字段填写完整
		Map<String,Object> map2 = new HashMap<String,Object>();
		map2.put("username", username);
		map2.put("gender", gender);
		map2.put("height", height);
		map2.put("birth", birth);
		map2.put("location", location);
		map2.put("maritalStatus", maritalStatus);
		map2.put("carId", carId);
		map2.put("carNo", carNo);
		map2.put("carIdBirth", carIdBirth);
		map2.put("carIs", carIs);
		
		for(Map.Entry<String, Object> entry : map2.entrySet()){
			if(entry.getValue() == null){
				return entry.getKey()+"不能为空";
			}
		}
		
		//准备存入car数据库中
		Car car = new Car(carId,carNo,carIdBirth,carIs);
		System.out.println("------save--------");
		int i = appUserService.saveCarInfo(car);
		
		if( i != 1){ //如果存入车辆信息失败 则系统错误
			return "系统错误";
		}else{
			
			//通过car的carId查找car的id
			int id = appUserService.findIdByCarId(carId);
			
			map.put("username", username);
			map.put("gender", gender);
			map.put("height", height);
			map.put("birth", birth);
			map.put("location", location);
			map.put("maritalStatus", maritalStatus);
			map.put("education", education);
			map.put("prefession", prefession);
			map.put("income", income);
			map.put("interest", interest);
			map.put("phoneNo", phoneNo);
			map.put("carsId", id);
			
			int perfectedCarFriend = appUserService.perfectedCarFriend(map); //填入车友资料
			
			if(perfectedCarFriend == 0){
				return "系统错误";
			}else{
				
				return "填写成功";
			}
			
		}
	}
	
	//====================上传头像====================
	
	@RequestMapping(value="/uploadphoto")
	@ResponseBody
	public String uploadImage(@RequestParam("file")MultipartFile file,
			HttpServletRequest request,HttpServletResponse response,ModelMap model)  throws IOException{
		
		System.out.println("开始");  
        String path = request.getSession().getServletContext().getRealPath("upload");  
        String fileName = new Date().getTime()+file.getOriginalFilename();  
//        String fileName = new Date().getTime()+".jpg";  
        System.out.println(path); 
        path="d:\\upload\\";
        System.out.println("fileName = "+fileName);
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
  
        //保存  
        try {  
            file.transferTo(targetFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        model.addAttribute("fileUrl", request.getContextPath()+"/upload/"+fileName);  
  
        return "result"; 
	}
	
	
}
