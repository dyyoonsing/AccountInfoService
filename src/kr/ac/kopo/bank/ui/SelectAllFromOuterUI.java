package kr.ac.kopo.bank.ui;

import java.util.List;

//import kr.ac.kopo.bank.dao.UserInfoDAO;
import kr.ac.kopo.bank.vo.AccountVO;

public class SelectAllFromOuterUI extends BaseUI {

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub  
		

		List<AccountVO> listAccount = accountService.selectAllFromOuter();

		System.out.println("-----------------------------------------------");
		System.out.println("\t외부 통합 은행 계좌");
		System.out.println("-----------------------------------------------");
		System.out.println("은행\t계좌번호\t\t계좌주\t별칭\t잔액");
		System.out.println("-----------------------------------------------");
		
		
		if(listAccount.isEmpty()) {
			System.out.println("보유하신 계좌가 없습니다.");
		}else {
			for(AccountVO account : listAccount) {

				System.out.println(account.getBankName()+"\t"+account.getAccountNum()
				+"\t"+account.getName()+"\t"+account.getAlias()+"\t"+account.getBalance());


			}
		
	}

}
}
