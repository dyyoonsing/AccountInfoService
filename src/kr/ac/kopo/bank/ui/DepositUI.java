package kr.ac.kopo.bank.ui;

import kr.ac.kopo.bank.vo.AccountVO;

public class DepositUI extends BaseUI {

	@Override
	public void execute() throws Exception {
		
		
		String bankName = scanStr("입금할 은행 : ");
		String accountNum = scanStr("계좌번호 : ");
		int addBalance = scanInt("입금할 금액(원) : ");
		
		
		AccountVO newAccount = new AccountVO();
		newAccount.setBankName(bankName);
		newAccount.setAccountNum(accountNum);
		newAccount.setBalance(addBalance);
		

		//추가하기
		accountService.deposit(newAccount);
		
		System.out.println("입금이 완료되었습니다.");
	}
	
	

}
