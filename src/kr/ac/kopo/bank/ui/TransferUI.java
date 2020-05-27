package kr.ac.kopo.bank.ui;

import kr.ac.kopo.bank.vo.AccountVO;

public class TransferUI extends BaseUI {

	@Override
	public void execute() throws Exception {
		
		
		
		System.out.println("\t<계좌이체 서비스>");
		String myBankName = scanStr("내 은행명 : ");
		String myAccountNum = scanStr("내 계좌번호 : ");
		String check = accountService.checkAccount(myAccountNum);
		
		while(check == null) {
			myAccountNum = scanStr("계좌번호를 다시 입력하세요 : ");
			check = accountService.checkAccount(myAccountNum);
		}
		
		int transferBalance = scanInt("내가 이체할 금액 : ");
		
		AccountVO accountVO = new AccountVO();
		accountVO.setBankName(myBankName);
		accountVO.setAccountNum(myAccountNum);
		accountVO.setBalance(transferBalance);

		
		int nowBalance = accountService.checkBalance(accountVO);
		
		if(nowBalance >= transferBalance) {
			System.out.println("--------------------");
			String yourAccountNum = scanStr("보낼 계좌번호 : ");
			
			check = accountService.checkAccount(yourAccountNum);
			
			while(check == null) {
				yourAccountNum = scanStr("계좌번호를 다시 입력하세요 : ");
				check = accountService.checkAccount(yourAccountNum);
			}
			
			accountService.transfer(myAccountNum, yourAccountNum, transferBalance);
			
		}else {
			System.out.println("이체할 금액이 부족합니다...");
		}
		
		
		
		
		
		
	}
	
	

}
