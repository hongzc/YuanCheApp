package com.thinkgem.jeesite.lbst.entities;

/**
 * 用户的个人择友条件类
 * @author hcc
 * 下午2:43:42
 */
public class FriendCondition {

	private Integer id;            //择友条件编号
	private String ageRa;          //年龄范围
	private String heightRa;       //身高范围
	private String incomeRa;       //收入范围
	private String education;      //学历要求
	private String maritalStatus;  //婚姻状态
	private String location;       //所在地区
	private Integer userId;        //用户的id
	private String heart;          //内心独白
	
	private AppUser appuser;       //属于哪个用户
	
	public FriendCondition() {
		super();
	}
	
	public FriendCondition(String ageRa, String heightRa,
			String incomeRa, String education, String maritalStatus,
			String location) {
		this.ageRa = ageRa;
		this.heightRa = heightRa;
		this.incomeRa = incomeRa;
		this.education = education;
		this.maritalStatus = maritalStatus;
		this.location = location;
	}
	
	public FriendCondition(String ageRa, String heightRa,
			String incomeRa, String education, String maritalStatus,
			String location, Integer userId) {
		this.ageRa = ageRa;
		this.heightRa = heightRa;
		this.incomeRa = incomeRa;
		this.education = education;
		this.maritalStatus = maritalStatus;
		this.location = location;
		this.userId = userId;
	}
	
	

	public String getHeart() {
		return heart;
	}

	public void setHeart(String heart) {
		this.heart = heart;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAgeRa() {
		return ageRa;
	}
	public void setAgeRa(String ageRa) {
		this.ageRa = ageRa;
	}
	public String getHeightRa() {
		return heightRa;
	}
	public void setHeightRa(String heightRa) {
		this.heightRa = heightRa;
	}
	public String getIncomeRa() {
		return incomeRa;
	}
	public void setIncomeRa(String incomeRa) {
		this.incomeRa = incomeRa;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public AppUser getAppuser() {
		return appuser;
	}

	public void setAppuser(AppUser appuser) {
		this.appuser = appuser;
	}

	@Override
	public String toString() {
		return "FriendCondition [id=" + id + ", ageRa=" + ageRa + ", heightRa="
				+ heightRa + ", incomeRa=" + incomeRa + ", education="
				+ education + ", maritalStatus=" + maritalStatus
				+ ", location=" + location + "]";
	}
	
	
	
	
	
}
