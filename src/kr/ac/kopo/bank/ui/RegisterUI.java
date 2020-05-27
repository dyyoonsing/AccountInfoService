package kr.ac.kopo.bank.ui;

import kr.ac.kopo.bank.vo.UserInfoVO;

public class RegisterUI extends BaseUI {

	@Override
	public void execute() throws Exception {
		
		System.out.println();
		System.out.println("본인 인증을 위해 이름과 전화번호를 입력하세요");
		String name = scanStr("이름 : ");
		String phoneNum = scanStr("전화번호 : ");
		
		/*
		 * 본인이 등록한 아이디인지 확인
		 */
		boolean userCheck = userInfoService.userCheck(name, phoneNum);
		if(!userCheck) {
			System.out.println("이미 등록한 회원입니다.");
			System.out.println("로그인을 해주세요 :)");

		} else {
			
			
			
			System.out.println("ID와 비밀번호를 입력하세요");
			String id = scanStr("ID : ");
			boolean userIDCheck = userInfoService.userIDCheck(id);
			
			while(!userIDCheck) {

				id = scanStr("이미 있는 아이디입니다. ID를 재입력하세요 : ");
				userIDCheck = userInfoService.userIDCheck(id);
			}			
			
			String pwd = scanStr("비밀번호  : ");
			
			UserInfoVO newUser = new UserInfoVO(name, id, pwd, phoneNum);
			
			userInfoService.resigter(newUser);

			
			
			
			System.out.println("\t~~~~~~~~회원가입을 축하합니다!!!~~~~~~~~");
			
			
			
		}
		
		
		
		
	}
	
	
	
	

}
