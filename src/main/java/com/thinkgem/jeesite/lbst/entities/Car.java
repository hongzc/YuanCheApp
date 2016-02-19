package com.thinkgem.jeesite.lbst.entities;


public class Car {
	
	private Integer id;
	private String  carId;         //车牌号
	private String  carNo;         //车型号
	private String    carIdBirth;    //上牌年月
	private String  carIs;         //车辆所属
	
	private Integer userId;       //车辆属于哪一个人
	//private AppUser user;          //车属于那个人

	public Car() {}
	
	public Car(Integer id, String carId, String carNo, String carIdBirth,
			String carIs) {
		super();
		this.id = id;
		this.carId = carId;
		this.carNo = carNo;
		this.carIdBirth = carIdBirth;
		this.carIs = carIs;
	}

	public Car(String carId, String carNo, String carIdBirth, String carIs) {
		this.carId = carId;
		this.carNo = carNo;
		this.carIdBirth = carIdBirth;
		this.carIs = carIs;
	}
	
	//用于快速注册
	public Car(String carId, String carNo, String carIdBirth, String carIs,Integer userId){
		this.carId = carId;
		this.carNo = carNo;
		this.carIdBirth = carIdBirth;
		this.carIs = carIs;
		this.userId = userId;
	}
	

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getCarIdBirth() {
		return carIdBirth;
	}

	public void setCarIdBirth(String carIdBirth) {
		this.carIdBirth = carIdBirth;
	}

	public String getCarIs() {
		return carIs;
	}

	public void setCarIs(String carIs) {
		this.carIs = carIs;
	}

	/*public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}*/

	@Override
	public String toString() {
		return "Car [id=" + id + ", carId=" + carId + ", carNo=" + carNo
				+ ", carIdBirth=" + carIdBirth + ", carIs=" + carIs  + "]";
	}
	
	
	
	
}
