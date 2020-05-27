package kr.ac.kopo.bank.ui;

import kr.ac.kopo.bank.vo.AccountVO;

public class SearchAccount extends BaseUI{

	@Override
	public void execute() throws Exception {
		
		String accountNum = scanStr("보고 싶은 계좌번호 입력 : ");
		
		String tableName = accountService.checkAccount(accountNum);
		AccountVO searchAccount = accountService.accountSearch(accountNum, tableName);
		
		System.out.println("-----------------------------------------------");
		System.out.println("은행\t계좌번호\t\t계좌주\t별칭\t잔액");
		System.out.println("-----------------------------------------------");
		
		

		System.out.println(searchAccount.getBankName()+"\t"+searchAccount.getAccountNum()
		+"\t"+searchAccount.getName()+"\t"+searchAccount.getAlias()+"\t"+searchAccount.getBalance());

		
	}
	
	

}
