package com.thinkgem.jeesite.lbst.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.lbst.dao.PositionDao;
import com.thinkgem.jeesite.lbst.entities.Position;

/**
 * 主要提供定位存库的一些主要方法
 * @author hcc
 * 上午10:52:58
 */

@Service
public class PositionService {

	@Autowired
	private PositionDao positionDao;
	
	//新建位置表
	@Transactional
	public int addPositionByPhoneNo(String phoneNo){
		
		if(phoneNo != null){
			int i = positionDao.addPosition(phoneNo);
			return i;
		}
		return 0;
	}
	
	//查找跟phoneNo对应的positionId
	@Transactional(readOnly=true)
	public Integer getPositionId(String phoneNo){
		
		if(phoneNo != null){
			Integer positionId = positionDao.getPositionId(phoneNo);
			return positionId;
		}
		return null;
	}
	
	//保存经纬度
	@Transactional
	public int updatePosition(Double longitude,Double latitude,String phoneNo){
		
		if(phoneNo != null){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("longitude", longitude);
			map.put("latitude", latitude);
			map.put("phoneNo", phoneNo);
			int i = positionDao.updatePosition(map);
			return i;
		}
		return 0;
	}
	
	//通过phoneNo查询当前用户的position
	@Transactional(readOnly=true)
	public Position getPositionByPhoneNo(String phoneNo){
		
		if(phoneNo != null){
			Position position = positionDao.getPositionByPhoneNo(phoneNo);
			return position;
		}
		return null;
	}
	
	//获得所有人的位置
	@Transactional(readOnly=true)
	public List<Position> getAllPosition(String phoneNo,String tripStatus){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("phoneNo", phoneNo);
		map.put("tripStatus", tripStatus);
		List<Position> positions = positionDao.getAllPosition(map);
		return positions;
	}
	
}
