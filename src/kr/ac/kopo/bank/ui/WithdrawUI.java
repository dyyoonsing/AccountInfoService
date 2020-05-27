package kr.ac.kopo.bank.ui;

import kr.ac.kopo.bank.vo.AccountVO;

public class WithdrawUI extends BaseUI {

	@Override
	public void execute() throws Exception {
		
		String bankName = scanStr("출금할 은행 : ");
		String accountNum = scanStr("계좌번호 : ");
		int minusBalance = scanInt("출금할 금액(원) : ");
		
		AccountVO newAccount = new AccountVO();
		newAccount.setBankName(bankName);
		newAccount.setAccountNum(accountNum);
		newAccount.setBalance(minusBalance);
		
		int nowBalance = accountService.checkBalance(newAccount);
		
		if(nowBalance >= minusBalance) {
			accountService.withdraw(newAccount);
		} else {
			System.out.println("출금할 금액이 부족합니다....");
		}
		
		System.out.println("출금이 완료되었습니다.");

	}
	
	
	
	

}
