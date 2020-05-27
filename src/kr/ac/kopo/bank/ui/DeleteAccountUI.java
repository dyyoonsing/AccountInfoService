package kr.ac.kopo.bank.ui;

import kr.ac.kopo.bank.vo.AccountVO;

public class DeleteAccountUI extends BaseUI {

	@Override
	public void execute() throws Exception {
		
		SelectAllUI ui = new SelectAllUI();
		ui.execute();
		
		String accountNum = scanStr("삭제하고 싶은 은행 계좌 입력 : ");
		String tableName = "hana_account_info";
		AccountVO accountVO = accountService.accountSearch(accountNum, tableName);
		
		accountService.deleteAccout(accountVO);
		
	}

}
