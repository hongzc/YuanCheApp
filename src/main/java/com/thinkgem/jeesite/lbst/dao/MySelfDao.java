package com.thinkgem.jeesite.lbst.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.lbst.entities.AppUser;
import com.thinkgem.jeesite.lbst.entities.Car;
import com.thinkgem.jeesite.lbst.entities.FriendCondition;

/**
 * 实现"我"这个  按钮的增删改查
 * @author hcc
 * 下午4:00:04
 */

@MyBatisDao
public interface MySelfDao {

	
	//==========资料修改==========
	
	//先查询该用户的所有信息(判断是车友还是乘友,而不是在数据库查找时候直接用select *,减小数据库压力)
	//乘友资料查找
	@Select("SELECT username,gender,height,birth,location,maritalStatus,education,prefession,income,interest FROM app_user WHERE tripStatus='乘友' AND phoneNo=#{phoneNo}")
	public AppUser selectDriveData(@Param("phoneNo")String phoneNo);
	//乘友资料修改
	@Update("UPDATE app_user SET username=#{username},location=#{location},maritalStatus=#{maritalStatus},education=#{education},prefession=#{prefession},income=#{income},interest=#{interest} WHERE tripStatus='乘友' AND phoneNo=#{phoneNo}")
	public int DriveDatamodify(AppUser appuser);
	
	//新建app_car表
	@Insert("INSERT INTO app_car (carId,carNo,carIdBirth,carIs,user) VALUE (NULL,NULL,NULL,NULL,#{user})")
	public int newCar(@Param("user")Integer userId);
	
	//车友资料查找
	@Select("SELECT username,gender,height,birth,location,maritalStatus,education,prefession,income,interest FROM app_user WHERE tripStatus='车友' AND phoneNo=#{phoneNo}")
	public AppUser selectCarData(@Param("phoneNo")String phoneNo);
	
	//通过phoneNo和tripStatus查询出carId
	@Select("SELECT carId FROM app_user WHERE tripStatus = '车友' AND phoneNo=#{phoneNo} ")
	public Integer selectCarIdByPhoneNo(@Param("phoneNo")String phoneNo);
	
	//通过carId查询出car的信息(----×---)
	@Select("SELECT carId,carNo,carIdBirth,carIs FROM app_car WHERE id=#{id}")
	public Car selectCarById(@Param("id") int id);
	
	//通过userId查询对应的car信息
	@Select("SELECT id,carId,carNo,carIdBirth,carIs FROM app_car WHERE user=#{userId}")
	public Car selectCarByUserId(@Param("userId")Integer userId);
	
	//修改车友资料
	@Update("UPDATE app_car SET carId=#{carId},carNo=#{carNo},carIdBirth=#{carIdBirth},carIs=#{carIs} WHERE id=#{id}")
	public int CarfriendDatamodifycar(Car car);
	
	@Update("UPDATE app_user SET username=#{username},location=#{location},maritalStatus=#{maritalStatus},education=#{education},prefession=#{prefession},income=#{income},interest=#{interest} WHERE tripStatus='车友' AND phoneNo=#{phoneNo}")
	public int CarfriendDatamodifyuser(AppUser appuser);
	
	//==========添加择友条件
	
	//判断是否有择友条件
	@Select("SELECT id FROM app_fricondition WHERE user=#{user}")
	public Integer hasFriCondition(@Param("user")Integer userId);
	
	//新建择友条件
	@Insert("INSERT INTO app_fricondition (ageRa,heightRa,incomeRa,education,maritalStatus,location,heart,user) VALUE ('不限','不限','不限','不限','不限','不限',NULL,#{user})")
	public int newFriendCondition(@Param("user")Integer user);
	
	//先确定用户是谁
	@Select("SELECT id FROM app_user WHERE tripStatus=#{tripStatus} AND phoneNo=#{phoneNo}")
	public Integer selectUserIdByCondition(Map<String,Object> map);
	
	@Select("SELECT id FROM app_user WHERE phoneNo=#{phoneNo}")
	public Integer selectUserIdByPhoneNo(@Param("phoneNo")String phoneNo);
	
	//修改app_user表中的fricondition列
	@Update("UPDATE app_user SET fricondition=#{fricondition} WHERE tripStatus=#{tripStatus} AND phoneNo=#{phoneNo}")
	public int updateFriColumn(Map<String,Object> map);
	
	@Update("UPDATE app_fricondition SET ageRa=#{ageRa},heightRa=#{heightRa},incomeRa=#{incomeRa},education=#{education},maritalStatus=#{maritalStatus},location=#{location} WHERE user=#{userId}")
	public Integer addFriendCondition(FriendCondition fc);
	
	@Update("UPDATE app_user SET fricondition=#{id} WHERE tripStatus=#{tripStatus} AND phoneNo=#{phoneNo}")
	public int addFC2User(Map<String,Object> map);
	
	//==========修改内心独白
	@Update("UPDATE app_fricondition SET heart=#{heart} WHERE user=#{userId}")
	public int heartModify(Map<String,Object> map);
	
	
	
	
}
