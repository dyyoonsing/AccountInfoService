package kr.ac.kopo.bank.vo;

public class UserInfoVO {
	
	private String 	id;
	private String 	password;
	private String 	name;
	private String 	phoneNum;
	
	
	
	public UserInfoVO() {
		super();
	}


	public UserInfoVO(String name, String id, String password, String phoneNum) {
		super();
		this.name = name;
		this.id = id;
		this.password = password;
		this.phoneNum = phoneNum;
	}
	
	


	public UserInfoVO(String id, String password) {
		super();
		this.id = id;
		this.password = password;
	}


	@Override
	public String toString() {
		return "UserInfoVO [id=" + id + ", password=" + password + ", name=" + name + ", phoneNum=" + phoneNum + "]";
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhoneNum() {
		return phoneNum;
	}


	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}


	
	


}
