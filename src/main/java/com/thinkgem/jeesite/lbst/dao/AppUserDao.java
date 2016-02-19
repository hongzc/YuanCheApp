package com.thinkgem.jeesite.lbst.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.lbst.entities.AppUser;
import com.thinkgem.jeesite.lbst.entities.Car;
import com.thinkgem.jeesite.lbst.entities.FriendCondition;

@MyBatisDao
public interface AppUserDao {

	//基本注册
	@Insert("INSERT INTO app_user (password,phoneNo,registTime,accountId,uvip,create_by,create_date,update_by,update_date) VALUE (#{password},#{phoneNo},#{registTime},null,0,#{phoneNo},#{registTime},#{phoneNo},#{registTime})")
	public int regist(Map<String,Object> map);
	
	//第二次更换角色注册
	@Insert("INSERT INTO app_user (password,phoneNo,registTime,tripStatus,accountId) VALUE (#{password},#{phoneNo},#{registTime},#{tripStatus},null)")
	public int secondRegist(Map<String,Object> map);
	
	//查询简单注册信息(密码 )
	@Select("SELECT password FROM app_user WHERE phoneNo=#{phoneNo}")
	public String getUserByPhoneNo(@Param("phoneNo")String phoneNo);
	
	//查询tripStatus
	@Select("SELECT tripStatus FROM app_user WHERE phoneNo=#{phoneNo}")
	public String getTripStatus(@Param("phoneNo")String phoneNo);
	
	//检查电话号码不能是之前注册过的
	@Select("SELECT phoneNo FROM app_user")
	public List<String> checkPhoneNo(@Param("phoneNo")String phoneNo);
	
	//登录
	@Select("SELECT password FROM app_user WHERE phoneNo = #{phoneNo}")
	public String login(@Param("phoneNo")String phoneNo);
	
	//如果登录成功，则Update登录时间loginTime
	@Update("UPDATE app_user SET loginTime=#{loginTime} WHERE phoneNo=#{phoneNo}")
	public int updateLoginTime(Map<String,Object> map);
	
	//角色查询
	@Select("SELECT id FROM app_user WHERE phoneNo=#{phoneNo} AND tripStatus=#{tripStatus}")
	public Integer getPhoneNoAndTripStatus(Map<String,Object> map);
	
	//注册tripStatus
	@Update("UPDATE app_user SET tripStatus=#{tripStatus} WHERE phoneNo=#{phoneNo}")
	public int tripStatus(Map<String,Object> map);
	
	//查询所有乘友
	@Select("SELECT * FROM app_user WHERE tripStatus = '乘客' LIMIT index=#{index},pageSzie=#{pageSize}")
	public List<AppUser> getAllDrive(Map<String,Object> map);
	
	//通过userId查询所有的择友条件
	@Select("SELECT * FROM app_fricondition WHERE user=#{user}")
	public FriendCondition getFriConditionByUserId(@Param("user")Integer userId);
	
	//查询所有车友基本user资料
	@Select("SELECT * FROM app_user WHERE tripStatus='车主' LIMIT index=#{index},pageSize=#{pageSize} ")
	public List<AppUser> getAllCar(Map<String,Object> map);
	
	@Select("SELECT * FROM app_car WHERE user=#{user}")
	public Car getCarByUserId(@Param("user")Integer user);
	
	//完善其余资料（乘友注册）
	@Update("UPDATE app_user SET username=#{username},gender=#{gender},height=#{height},"
			+ "birth=#{birth},location=#{location},maritalStatus=#{maritalStatus},education=#{education},"
			+ "prefession=#{prefession},income=#{income},interest=#{interest} WHERE phoneNo=#{phoneNo}")
	public int perfectedDrive(Map<String,Object> map);
	
	//完善其余资料（车友注册）
	//car注册
	@Insert("INSERT INTO app_car (carId,carNo,carIdBirth,carIs) VALUE (#{carId},#{carNo},#{carIdBirth},#{carIs})")
	public int saveCarInfo(Car car);
	
	//通过car的carId查找对应的id
	@Select("SELECT id FROM app_car WHERE carId = #{carId}")
	public int findIdByCarId(@Param("carId")String carId);
	
	//通过userId查找car的id
	@Select("SELECT id FROM app_car WHERE user=#{user}")
	public Integer findIdByUser(@Param("user")Integer userId);
	
	//修改车友信息
	@Update("UPDATE app_user SET username=#{username},gender=#{gender},height=#{height},"
			+ "birth=#{birth},location=#{location},maritalStatus=#{maritalStatus},education=#{education},"
			+ "prefession=#{prefession},income=#{income},interest=#{interest},"
			+ "carId=#{carsId} WHERE phoneNo=#{phoneNo}")
	public int perfectedCarFriend(Map<String,Object> map);
	
	//查询用户之前有没有对应的trip表
	@Select("SELECT id FROM app_trip WHERE user=#{userId} AND regularORtimely like #{tripStatus}")
	public List<Integer> getTripByUserId(Map<String,Object> map);
	
	//通过电话查找user信息
	@Select("SELECT username,tripStatus,loginTime,gender,uvip,height,birth,location,maritalStatus,education,prefession,income,interest FROM app_user WHERE phoneNo=#{phoneNo}")
	public AppUser getAppUserByPhoneNo(@Param("phoneNo") String phoneNo);
	
	//快速注册(乘客)
	@Update("UPDATE app_user SET username=#{username},gender=#{gender},height=#{height},location=#{location},maritalStatus=#{maritalStatus},education=#{education},prefession=#{prefession},income=#{income},interest=#{interest} WHERE phoneNo=#{phoneNo}")
	public int quickDrive(AppUser user);
	
	//快速注册(车主)
	@Update("UPDATE app_car SET carId=#{carId},carNo=#{carNo},carIdBirth=#{carIdBirth},carIs=#{carIs} WHERE user=#{userId}")
	public int quickCar(Car car);
	
}
