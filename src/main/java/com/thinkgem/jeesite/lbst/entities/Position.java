package com.thinkgem.jeesite.lbst.entities;

/**
 * 位置 类
 * @author hcc
 * 下午12:05:48
 */
public class Position {

	private Integer id;   //每个人对应的位置的编号
	private Double longitude;   //经度
	private Double latitude;     //纬度
	private String phoneNo;     //该位置 对应的用户的电话号码
	
	public Position() {}

	public Position(Double longitude, Double latitude, String phoneNo) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
		this.phoneNo = phoneNo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Override
	public String toString() {
		return "Position [id=" + id + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", phoneNo=" + phoneNo + "]";
	}
	
	
	
	
	
}
