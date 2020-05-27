package kr.ac.kopo.bank.ui;

import kr.ac.kopo.bank.dao.UserInfoDAO;

public class BankSecondUI extends BaseUI {
	
	
	private int choiceMenu() {
		System.out.println();
		System.out.println("===================================================");
		System.out.println("\t\t" + UserInfoDAO.getLoginName() +"님 반갑습니다 :)");
		System.out.println("---------------------------------------------------");
		System.out.println("\t\t1. 새로운 계좌 개설");
		System.out.println("\t\t2. 계좌 조회");
		System.out.println("\t\t3. 통합 계좌 관리");
		System.out.println("\t\t4. 별칭 수정");
		System.out.println("\t\t5. 계좌 검색");
		System.out.println("\t\t6. 입금하기");
		System.out.println("\t\t7. 출금하기");
		System.out.println("\t\t8. 계좌 이체");
		System.out.println("\t\t9. 로그아웃");
		System.out.println("\t\t0. 프로그램 종료");
		
		System.out.println("===================================================");
		int type = scanInt("원하는 메뉴의 번호를 입력하세요 : ");
		System.out.println();
		
		return type;
	}
	
	

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		while(true) {
			int type = choiceMenu();
			IBankUI ui = null;
			
			switch (type) {
			case 1: 
				ui = new MakeAccountUI();
				break;
				
			case 2:
				System.out.println("\t\t<< 계좌 조회 서비스 >>");
				int select = scanInt("\t\t1.전체 계좌 조회\n\t\t2.은행별 계좌 조회 \n 번호를 입력하세요 : ");
				if(select == 1) {
					ui = new SelectAllUI();
				}else if(select == 2) {
					ui = new SelectByBankUI();
				}else {
					System.out.println("메뉴를 잘못 선택했습니다...");
				}				
				break;
				
			case 3:	
				System.out.println("---------------------------------------------------");
				System.out.println("\t<< 통합 계좌 관리 시스템 >>");
				System.out.println("---------------------------------------------------");
				int selectCase3 = scanInt("\t1.관리 목록에 다른 은행 계좌 추가 \n\t2.관리 목록에 계좌 삭제 \n번호를 입력하세요 : ");
				if(selectCase3 == 1) {
					//은행계좌 추가 (통합 테이블 -> 하나 테이블로 이동)
					ui = new AddAccountUI();
				}else if(selectCase3 == 2) {
					//계좌 삭제 (하나테이블 -> 통합테이블로 이동)
					ui = new DeleteAccountUI();
				}else {
					System.out.println("메뉴를 잘못 선택했습니다...");
				}
			
				break;
				
			case 4:
				ui = new ModifyAliasUI();				
				break;
				
			case 5:
				ui = new SearchAccount();
				break;				
			case 6:
				ui = new DepositUI();
				break;				
			case 7:
				ui = new WithdrawUI();
				break;				
			case 8:
				ui = new TransferUI();
				break;			
			case 9:
				ui = new BankFirstUI();
				System.out.println("로그아웃되었습니다...");
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
