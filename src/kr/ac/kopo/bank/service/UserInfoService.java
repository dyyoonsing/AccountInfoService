package kr.ac.kopo.bank.service;

import kr.ac.kopo.bank.dao.UserInfoDAO;
import kr.ac.kopo.bank.vo.UserInfoVO;

public class UserInfoService {

	private UserInfoDAO userInfoDAO;
	
	public UserInfoService() {
		userInfoDAO = new UserInfoDAO();
	}
	
	public UserInfoVO login(UserInfoVO userInfo) {
		
		UserInfoVO user = userInfoDAO.login(userInfo);
		return user;
	}
	
	
	
	public boolean userCheck(String name, String phoneNum) {
		
		boolean bool = userInfoDAO.userCheck(name, phoneNum);
		return bool;
		
	}
	
	public boolean userIDCheck(String id) {
		
		boolean bool = userInfoDAO.userIDCheck(id);
		return bool;
	}
	
	public void resigter(UserInfoVO userInfo) {
		
		userInfoDAO.register(userInfo);
	}
	
	
}
