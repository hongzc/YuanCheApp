package com.thinkgem.jeesite.lbst.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.lbst.entities.Position;


/**
 * 用来处理个人定位的dao
 * @author hcc
 * 上午10:33:59
 */
@MyBatisDao
public interface PositionDao {

	//创建自己的经纬度colunm
	@Insert("INSERT INTO app_map (longitude,latitude,phoneNo) VALUE (NULL,NULL,#{phoneNo})")
	public int addPosition(@Param("phoneNo")String phoneNo);
	
	//查找跟phoneNo对应的positionId
	@Select("SELECT id FROM app_map WHERE phoneNo=#{phoneNo}")
	public Integer getPositionId(@Param("phoneNo")String phoneNo);
	
	//保存经纬度
	@Update("UPDATE app_map SET longitude=#{longitude},latitude=#{latitude} WHERE phoneNo=#{phoneNo}")
	public int updatePosition(Map<String,Object> map);
	
	//通过phoneNo查出当前用户的经纬度
	@Select("SELECT longitude,latitude FROM app_map WHERE phoneNo=#{phoneNo}")
	public Position getPositionByPhoneNo(@Param("phoneNo")String phoneNo);
	
	//获得所有人的位置(不包括自己)
	@Select("SELECT longitude,latitude,m.phoneNo FROM app_map m LEFT OUTER JOIN app_user u ON m.phoneNo=u.phoneNo  WHERE m.phoneNo <> #{phoneNo} AND u.tripStatus<>#{tripStatus}")
	public List<Position> getAllPosition(Map<String,Object> map);
}
