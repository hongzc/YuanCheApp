package com.thinkgem.jeesite.lbst.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.lbst.dao.AppUserDao;
import com.thinkgem.jeesite.lbst.entities.AppUser;
import com.thinkgem.jeesite.lbst.entities.Car;
import com.thinkgem.jeesite.lbst.entities.FriendCondition;

/**
 * 负责处理用户管理的业务层
 * @author hcc
 * 上午9:47:49
 */
@Service
public class AppUserService {

	@Autowired()
	private AppUserDao appUserDao;
	
	//简单注册
	@Transactional
	public int regist(Map<String,Object> map){
		map.put("registTime", new Date());
		int i = appUserDao.regist(map);
		return i;
	}
	
	//检查phoneNo不能为之前注册过的
	@Transactional(readOnly=true)
	public List<String> checkPhone(String phoneNo){
		if(phoneNo != null){
			List<String> list = appUserDao.checkPhoneNo(phoneNo);
			return list;
		}
		return null;
	}
	
	//更新登录时间
	public int updateLoginTime(String phoneNo){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("loginTime", new Date());
		map.put("phoneNo", phoneNo);
		if(phoneNo != null){
			int i = appUserDao.updateLoginTime(map);
			return i;
		}
		return 0;
	}
	
	//第二次新建信息的时候查询密码 
	@Transactional(readOnly=true)
	public String getPasswordByPhoneNo(String phoneNo){
		if(phoneNo != null){
			String password = appUserDao.getUserByPhoneNo(phoneNo);
			return password;
		}
		return null;
	}
	
	//第二次换角色注册
	@Transactional
	public int secondRegist(Map<String,Object> map){
		int i = appUserDao.secondRegist(map);
		return i;
	}
	
	//根据电话查询tripStatus
	@Transactional(readOnly=true)
	public String getTripStatus(String phoneNo){
		if(phoneNo != null){
			String tripStatus = appUserDao.getTripStatus(phoneNo);
			return tripStatus;
		}
		return null;
	}
	
	
	//登录
	@Transactional(readOnly=true)
	public String login(String username){
		if(username != null){
			String psd = appUserDao.login(username);
			return psd;
		}
		return null;
	}
	
	/*//根据乘客或者车友查询角色
	@Transactional(readOnly=true)
	public Integer getPhoneNoAndTripStatus(String phoneNo,String tripStatus){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("phoneNo", phoneNo);
		map.put("tripStatus", tripStatus);
		Integer id = appUserDao.getPhoneNoAndTripStatus(map);
		return id;
		
	}*/
	
	//获得所有的乘友
	@Transactional(readOnly=true)
	public List<AppUser> getAllDrive(Integer index,Integer pageSize){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("index", index);
		map.put("pageSize", pageSize);
		List<AppUser> allDrive = appUserDao.getAllDrive(map);
		return allDrive;
	}
	
	//通过userId查询择友条件
	@Transactional(readOnly=true)
	public FriendCondition getFriConditionByUserId(Integer userId){
		if(userId != null){
			FriendCondition condition = appUserDao.getFriConditionByUserId(userId);
			return condition;
		}
		return null;
	}
	
	//通过userId查询路线是否有
	@Transactional(readOnly=true)
	public Integer getTripIdByUserId(Integer userId,String tripStatus){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("tripStatus", "%"+tripStatus+"%");
		List<Integer> tripIds = appUserDao.getTripByUserId(map);
		System.out.println("appuserservice.tripIds = " + tripIds);
		try{
			int i = tripIds.size()+ 0;
		}catch (Exception e){
			return 0;
		}
		return tripIds.size();
	}
	
	//获得所有车友
	@Transactional(readOnly=true)
	public List<AppUser> getAllCar(Integer index,Integer pageSize){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("index", index);
		map.put("pageSize", pageSize);
		List<AppUser> allCar = appUserDao.getAllCar(map);
		return allCar;
	}
	
	//完善自己的出行状态(车友还是乘友)
	@Transactional
	public int perfecteStepStatus(Map<String,Object> map){
		int i = appUserDao.tripStatus(map);
		return i;
	}
	
	//完善自己的详细资料(乘友)
	@Transactional
	public int perfectedDrive(Map<String,Object> map){
		int i = appUserDao.perfectedDrive(map);
		return i ;
	}
	
	//完善自己的详细资料(车友)
	
	//保存车辆信息
	@Transactional
	public int saveCarInfo(Car car){
		if(car != null){
			int i = appUserDao.saveCarInfo(car);
			return i;
		}
		return 0;
	}
	
	//查询自己的车辆信息通过userId
	@Transactional(readOnly=true)
	public Car getCarByUserId(Integer userId){
		if(userId != null){
			Car car = appUserDao.getCarByUserId(userId);
			return car;
		}
		return null;
	}
	
	//通过carId查找对应的car的id
	@Transactional(readOnly=true)
	public Integer findIdByCarId(String carId){
		if(carId != null){
			int id = appUserDao.findIdByCarId(carId);
			return id;
		}
		return null;
	}
	
	//通过userId查询对应的car的id
	@Transactional(readOnly=true)
	public Integer findIdByUser(Integer userId){
		if(userId != null){
			Integer id = null;
			try{
				id = appUserDao.findIdByUser(userId);
			}catch (Exception e){
				return null;
			}
			return id;
		}
		return null;
	}
	
	@Transactional
	//保存车友信息
	public int perfectedCarFriend(Map<String,Object> map){
		int i = appUserDao.perfectedCarFriend(map);
		return i;
	}
	
	//通过phoneNo查询appuser信息
	@Transactional
	public AppUser getAppUserByPhoneNo(String phoneNo){
		if(phoneNo != null){
			AppUser user = appUserDao.getAppUserByPhoneNo(phoneNo);
			return user;
		}
		return null;
	}
	
	//用于快速注册(乘客)
	@Transactional
	public String quickRegistDrive(AppUser user){
		if(user != null){
			int i = appUserDao.quickDrive(user);
			return i+"";
		}
		return null;
	}
	
	//用于快速注册(车主)
	@Transactional
	public String quickRegistCar(AppUser user,Car car){
		if(user != null && car != null){
			int i = appUserDao.quickCar(car);
			if(i != 0){
				int j = appUserDao.quickDrive(user);
				return j+"";
			}
		}
		return null;
	}
	
}
