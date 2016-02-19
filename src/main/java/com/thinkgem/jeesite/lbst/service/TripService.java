package com.thinkgem.jeesite.lbst.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.lbst.dao.TripDao;
import com.thinkgem.jeesite.lbst.entities.Trip;

@Service
public class TripService {

	@Autowired
	private TripDao tripDao;
	
	//查询是否存在线路
	@Transactional
	public List<Integer> hasTrip(String tripStatus,Integer userId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("tripStatus"+"固定线路", tripStatus);
		map.put("userId", userId);
		if(userId != null){
			List<Integer> trip = tripDao.hasTrip(map);
			return trip;
		}
		return null;
	}
	
	//新建约行线路
	@Transactional
	public String addTrip(Trip trip){
		if(trip != null){
			int i = tripDao.addTrip(trip);
			return i+"";
		}
		return null;
	}
	
	//查找约行线路
	@Transactional(readOnly=true)
	public List<Trip> getTrip(Map<String,Object> map){
		List<Trip> list = tripDao.getTripByMySelf(map);
		return list;
	}
	
	//修改约行线路
	@Transactional
	public int updateTrip(Trip trip){
		if(trip != null){
			int i = tripDao.updateTrip(trip);
			return i;
		}
		return 0;
	}
}
