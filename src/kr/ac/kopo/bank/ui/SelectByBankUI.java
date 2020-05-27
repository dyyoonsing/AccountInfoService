package kr.ac.kopo.bank.ui;

import java.util.List;

import kr.ac.kopo.bank.vo.AccountVO;

public class SelectByBankUI extends BaseUI {

	@Override
	public void execute() throws Exception {

		
		System.out.println("-----------------------------------------------");
		System.out.println();
		String selectBank = scanStr("보고 싶은 은행명 입력 : ");
		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println("은행\t계좌번호\t\t계좌주\t별칭\t잔액");
		System.out.println("-----------------------------------------------");
		
		List<AccountVO> listAccount = accountService.selectByBank(selectBank);
		
		if(listAccount.isEmpty()) {
			System.out.println("보유하신 계좌가 없습니다.");
		}else {
			for(AccountVO account : listAccount) {

				System.out.println(account.getBankName()+"\t"+account.getAccountNum()
				+"\t"+account.getName()+"\t"+account.getAlias()+"\t"+account.getBalance());


			}
		
		
		
		
		
	}

	}}
