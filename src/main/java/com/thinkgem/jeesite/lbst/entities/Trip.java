package com.thinkgem.jeesite.lbst.entities;

import java.util.Date;

/**
 * 用来描述约行线路的类
 * @author hcc
 * 下午4:17:48
 */

public class Trip {

	private Integer id;     //出行的id号
	private String startArea;   //出发点
	private String tripTime;    //出发时间(只属于固定线路)
	private String endArea;     //目的地
	private String drive;      //车程
	private Date releaseTime;  //该线路发布的时间
	private String regularORtimely;  //是及时线路还是固定线路
	
	private Integer userId;    
	
	private AppUser appuser;    //该线路是属于哪个用户
	private AppUser otheruser;  //约行的对方是哪个用户
	private String succORfail;  //约行是否成功
	
	public Trip() {}

	public Trip(Integer id, String startArea, String endArea, String drive,
			Date releaseTime, String regularORtimely, AppUser appuser,
			AppUser otheruser, String succORfail) {
		super();
		this.id = id;
		this.startArea = startArea;
		this.endArea = endArea;
		this.drive = drive;
		this.releaseTime = releaseTime;
		this.regularORtimely = regularORtimely;
		this.appuser = appuser;
		this.otheruser = otheruser;
		this.succORfail = succORfail;
	}

	public Trip(String startArea, String tripTime, String endArea,
			String drive, Date releaseTime, String regularORtimely,
			Integer userId, String succORfail) {
		super();
		this.startArea = startArea;
		this.tripTime = tripTime;
		this.endArea = endArea;
		this.drive = drive;
		this.releaseTime = releaseTime;
		this.regularORtimely = regularORtimely;
		this.userId = userId;
		this.succORfail = succORfail;
	}
	
	public Trip(String startArea, String endArea,
			String drive, Date releaseTime, String regularORtimely,
			Integer userId, String succORfail) {
		super();
		this.startArea = startArea;
		this.endArea = endArea;
		this.drive = drive;
		this.releaseTime = releaseTime;
		this.regularORtimely = regularORtimely;
		this.userId = userId;
		this.succORfail = succORfail;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStartArea() {
		return startArea;
	}

	public void setStartArea(String startArea) {
		this.startArea = startArea;
	}

	public String getEndArea() {
		return endArea;
	}

	public void setEndArea(String endArea) {
		this.endArea = endArea;
	}

	public String getDrive() {
		return drive;
	}

	public void setDrive(String drive) {
		this.drive = drive;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getRegularORtimely() {
		return regularORtimely;
	}

	public void setRegularORtimely(String regularORtimely) {
		this.regularORtimely = regularORtimely;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public AppUser getAppuser() {
		return appuser;
	}

	public void setAppuser(AppUser appuser) {
		this.appuser = appuser;
	}

	public AppUser getOtheruser() {
		return otheruser;
	}

	public void setOtheruser(AppUser otheruser) {
		this.otheruser = otheruser;
	}

	public String getSuccORfail() {
		return succORfail;
	}

	public void setSuccORfail(String succORfail) {
		this.succORfail = succORfail;
	}

	@Override
	public String toString() {
		return "Trip [id=" + id + ", startArea=" + startArea + ", endArea="
				+ endArea + ", drive=" + drive + ", releaseTime=" + releaseTime
				+ ", regularORtimely=" + regularORtimely + ", appuser="
				+ appuser + ", otheruser=" + otheruser + ", succORfail="
				+ succORfail + "]";
	}
	
	
	
	
	
}
