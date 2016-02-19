package com.thinkgem.jeesite.lbst.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.lbst.dao.MySelfDao;
import com.thinkgem.jeesite.lbst.entities.AppUser;
import com.thinkgem.jeesite.lbst.entities.Car;
import com.thinkgem.jeesite.lbst.entities.FriendCondition;

/**
 * 实现"我"按钮的业务逻辑层
 * @author hcc
 * 下午4:02:58
 */
@Service
public class MySelfService {
	
	@Autowired
	private MySelfDao myselfDao;
	
	//===================================================
	//乘友资料查找
	@Transactional(readOnly=true)
	public AppUser findDataByDrive(String phoneNo){
		AppUser user = myselfDao.selectDriveData(phoneNo);
		return user;
	}
	
	@Transactional
	//乘友资料修改
	public int driveDataModify(AppUser appuser){
		if(appuser != null){
			int i = myselfDao.DriveDatamodify(appuser);
			return i;
		}
		return 0;
	}
	
	//===================================================
	
	//新建车信息表
	@Transactional
	public int newCar(Integer userId){
		if(userId != null){
			int i = myselfDao.newCar(userId);
			return i;
		}
		return 0;
	}
	
	//通过phoneNo查找carId
	@Transactional(readOnly=true)
	public Integer findCarIdByPhoneNo(String phoneNo){
		System.out.println("--phoneNo--" + phoneNo + "/t"+" myselfDao " + myselfDao==null);
		if(phoneNo != null){
			Integer carId = myselfDao.selectCarIdByPhoneNo(phoneNo);
			return carId;
		}
		return null ;
	}
	
	//通过user表中的CarId查询出car的信息
	@Transactional(readOnly=true)
	public Car findCarById(Integer id){
		if(id != null){
			Car car = myselfDao.selectCarByUserId(id);
			return car;
		}
		return null;
	}
	
	//车友资料查找
	@Transactional(readOnly=true)
	public AppUser findDataByCar(String phoneNo){
		AppUser user = myselfDao.selectCarData(phoneNo);
		return user;
	}
	
	//车友资料修改
	@Transactional
	public int CarfriendDataModify(AppUser appuser,Car car){
		if(appuser != null){
			int i = myselfDao.CarfriendDatamodifycar(car);
			if(i == 1){
				int j = myselfDao.CarfriendDatamodifyuser(appuser);
				return j;
			}
			return i;
		}
		return 0;
	}
	
	//===================================================
	//判断是否有择友条件
	@Transactional(readOnly=true)
	public Integer hasFriCondition(Integer userId){
		if(null != userId){
			Integer id = myselfDao.hasFriCondition(userId);
			return id;
		}
		return null;
	}
	
	//新建择友条件(在注册时候)
	@Transactional
	public int newFriCondition(Integer id){
		int i = myselfDao.newFriendCondition(id);
		return i;
	}
	
	//确定用户
	@Transactional(readOnly=true)
	public Integer findUserId(String phoneNo,String tripStatus){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("phoneNo", phoneNo);
		map.put("tripStatus", tripStatus);
		Integer userId = myselfDao.selectUserIdByCondition(map);
		return userId;
	}
	
	//查找userId
	@Transactional(readOnly=true)
	public Integer findUserIdByPhoneNo(String phoneNo){
		if(phoneNo != null){
			Integer id = myselfDao.selectUserIdByPhoneNo(phoneNo);
			return id;
		}
		return null;
	}
	
	
	//添加择友条件
	@Transactional
	public String addFriendCondition(FriendCondition fc){
		if(fc != null){
			System.out.println("fc = " + fc);
			Integer i = myselfDao.addFriendCondition(fc);
			return i+"" ;
		}
		return null;
	}
	
	
	//修改内心独白
	@Transactional
	public int heartModify(Map<String,Object> map){
		int i = myselfDao.heartModify(map);
		return i;
	}
	
	
}
