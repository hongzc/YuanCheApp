package com.thinkgem.jeesite.lbst.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.lbst.entities.Trip;

@MyBatisDao
public interface TripDao {
	
	//判断用户是否存在线路
	@Select("SELECT id FROM app_trip WHERE user=#{user}")
	public List<Integer> hasTrip(Map<String,Object> map);

	//添加(及时/固定)线路
	@Insert("INSERT INTO app_trip (startArea,tripTime,endArea,drive,releaseTime,regularORtimely,user,succORfail) VALUE (#{startArea},#{tripTime},#{endArea},#{drive},#{releaseTime},#{regularORtimely},#{userId},#{succORfail})")
	public int addTrip(Trip trip);
	
	//查找约行线路
	@Select("SELECT regularORtimely,startArea,endArea,drive,otherUser FROM app_trip t LEFT OUTER JOIN app_user a ON t.otheruser = a.id WHERE USER=32 ")
	public List<Trip> getTripByMySelf(Map<String,Object> map);
	
	//修改线路
	@Update("UPDATE app_trip SET startArea=#{startArea},tripTime=#{tripTime},endArea=#{endArea},drive=#{drive},releaseTime=#{releaseTime},user=#{userId},succORfail=#{succORfail} WHERE user=#{userId} AND regularORtimely=#{regularORtimely}")
	public int updateTrip(Trip trip);
}
