package kr.ac.kopo.bank.ui;

public class BankFirstUI extends BaseUI {
	
	
	private int choiceMenu() {
		System.out.println("===================================================");
		System.out.println();
		System.out.println("\t\t<하나은행 통합계좌 관리 시스템>");
		System.out.println();
		System.out.println("---------------------------------------------------");
		System.out.println("\t\t1. 로그인");
		System.out.println("\t\t2. 회원가입");
		System.out.println("\t\t0. 프로그램 종료");
		System.out.println("===================================================");

		int type = scanInt("원하는 메뉴 번호를 입력하세요 : ");
		
		return type;
	}

	@Override
	public void execute() throws Exception {
		while(true) {
			int type = choiceMenu();
			IBankUI ui = null;

			
			switch (type) {
			case 1: 
				ui = new LoginUI();
				
				break;
			case 2:
				ui = new RegisterUI(); 
				break;
			case 0: 
				ui = new ExitUI();
				break;
			}
			
			
			if(ui != null) {
				ui.execute();
			}

		}
		
	}
	
	

}
