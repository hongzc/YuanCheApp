package com.thinkgem.jeesite.lbst.entities;

/**
 * 站内账户的信息(包括车币、鲜花、等等)
 * @author hcc
 * 下午1:43:23
 */
public class Account {
	
	private Integer id;      //账户id
	private Integer Tcar;    //车币
	private Integer flower;  //鲜花
	
	private String phoneNo;  //电话 确定用户

	public Account() {
		super();
	}
	
	public Account(Integer tcar, Integer flower, String phoneNo) {
		super();
		Tcar = tcar;
		this.flower = flower;
		this.phoneNo = phoneNo;
	}




	public String getPhoneNo() {
		return phoneNo;
	}




	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTcar() {
		return Tcar;
	}

	public void setTcar(Integer tcar) {
		Tcar = tcar;
	}

	public Integer getFlower() {
		return flower;
	}

	public void setFlower(Integer flower) {
		this.flower = flower;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", Tcar=" + Tcar + ", flower=" + flower
				+ ", phoneNo=" + phoneNo + "]";
	}

	
}
