package com.thinkgem.jeesite.lbst.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;




/**
 * 用户类
 * @author hcc
 * 上午11:29:53
 */
public class AppUser implements Serializable{

	//第一轮注册(简单注册)
	private int id;  
	private String password;  //密码
	private String phoneNo;  //用户电话
	private Date registTime; //用户注册时间
	
	//第二轮注册(完善资料) 
	private String  username;      //用户昵称
	private String uname;          //用户名称
	private int uvip;				//用户的vip状态
	private String gender;        //性别 0-男 1-女
	private Integer height;        //身高  单位cm
	private String    birth;         //生日
	private String  tripStatus;    //是车友还是乘友
	private String  location;      //位置
	private String  maritalStatus; //婚姻状态
	private String  education;     //学历
	private String  prefession;    //职业
	private String  income;        //收入
	private String  interest;      //兴趣
								   //图像
	
	private Double distance;      //附近的距离(与当前用户) 
	private Date  loginTime;     //最新登录时间与其他用户的时间差
	private Long timeDiff;     //时间差
	private Car car ; //关联汽车类
	
	private FriendCondition fricondition;
	
	public AppUser() {}
	
	//为简单注册创建构造器
	public AppUser(int id,String username,String phoneNo){
		this.id = id;
		this.username = username;
		this.phoneNo = phoneNo;
	}
	
	//为修改乘友资料创建构造器
	public AppUser(String phoneNo,String username, String location, String maritalStatus,
			String education, String prefession, String income, String interest) {
		super();
		this.phoneNo = phoneNo;
		this.username = username;
		this.location = location;
		this.maritalStatus = maritalStatus;
		this.education = education;
		this.prefession = prefession;
		this.income = income;
		this.interest = interest;
	}
	
	
	public AppUser(int id, String password, String phoneNo, Date registTime,
			String username, String gender, Integer height, String birth,
			String tripStatus, String location, String maritalStatus,
			String education, String prefession, String income,
			String interest, Car car) {
		super();
		this.id = id;
		this.password = password;
		this.phoneNo = phoneNo;
		this.registTime = registTime;
		this.username = username;
		this.gender = gender;
		this.height = height;
		this.birth = birth;
		this.tripStatus = tripStatus;
		this.location = location;
		this.maritalStatus = maritalStatus;
		this.education = education;
		this.prefession = prefession;
		this.income = income;
		this.interest = interest;
		this.car = car;
	}
	
	//快速注册的构造器
	public AppUser(String username,String phoneNo,String gender,
			Integer height,String birth,String location,String maritalStatus,
			String education,String prefession,String income,String interest){
		
		this.phoneNo = phoneNo;
		this.username = username;
		this.gender = gender;
		this.height = height;
		this.birth = birth;
		this.location = location;
		this.maritalStatus = maritalStatus;
		this.education = education;
		this.prefession = prefession;
		this.income = income;
		this.interest = interest;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getTripStatus() {
		return tripStatus;
	}

	public void setTripStatus(String tripStatus) {
		this.tripStatus = tripStatus;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getPrefession() {
		return prefession;
	}

	public void setPrefession(String prefession) {
		this.prefession = prefession;
	}

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
	
	public FriendCondition getFricondition() {
		return fricondition;
	}

	public void setFricondition(FriendCondition fricondition) {
		this.fricondition = fricondition;
	}
	
	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}
	
	

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
	public Long getTimeDiff() {
		return timeDiff;
	}

	public void setTimeDiff(Long timeDiff) {
		this.timeDiff = timeDiff;
	}

	@Override
	public String toString() {
		return "AppUser [id=" + id + ", password=" + password + ", phoneNo="
				+ phoneNo + ", registTime=" + registTime + ", username="
				+ username + ", uname=" + uname + ", uvip=" + uvip
				+ ", gender=" + gender + ", height=" + height + ", birth="
				+ birth + ", tripStatus=" + tripStatus + ", location="
				+ location + ", maritalStatus=" + maritalStatus
				+ ", education=" + education + ", prefession=" + prefession
				+ ", income=" + income + ", interest=" + interest
				+ ", distance=" + distance + ", loginTime=" + loginTime
				+ ", timeDiff=" + timeDiff + ", car=" + car + ", fricondition="
				+ fricondition + "]";
	}

	

	

	




	
	

	
}
