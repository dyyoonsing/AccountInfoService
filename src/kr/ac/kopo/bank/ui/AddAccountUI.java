package kr.ac.kopo.bank.ui;

import kr.ac.kopo.bank.vo.AccountVO;

public class AddAccountUI extends BaseUI {

	@Override
	public void execute() throws Exception {
		
		SelectAllFromOuterUI ui = new SelectAllFromOuterUI();
		ui.execute();
		
		
		
		String accountNum = scanStr("추가하고 싶은 은행 계좌 입력 : ");
		String tableName = "account_info";
		AccountVO accountVO = accountService.accountSearch(accountNum, tableName);
		
		accountService.addAccount(accountVO);

				
	}

}
