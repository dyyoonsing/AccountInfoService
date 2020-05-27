package kr.ac.kopo.bank.ui;

import kr.ac.kopo.bank.vo.UserInfoVO;

public class LoginUI extends BaseUI{

	@Override
	public void execute() throws Exception {
		
		String id = scanStr("아이디 : ");
		String pwd = scanStr("비밀번호 : ");

		UserInfoVO userInfo = new UserInfoVO(id, pwd);

		
		UserInfoVO user = userInfoService.login(userInfo);
		

		if(user == null) {
			System.out.println();
			System.out.println("아이디/비번을 잘못 입력했습니다.");
			System.out.println("프로그램을 종료합니다");

			
			System.exit(0);
		}else {
			BankSecondUI bsui = new BankSecondUI();
			bsui.execute();
			
			
		}
		
		
		
		
	}
	
	
	
	

}
