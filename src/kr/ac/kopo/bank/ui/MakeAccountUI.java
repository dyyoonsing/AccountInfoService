package kr.ac.kopo.bank.ui;


import kr.ac.kopo.bank.dao.UserInfoDAO;
import kr.ac.kopo.bank.vo.AccountVO;

public class MakeAccountUI extends BaseUI{
	

	

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub

		
		System.out.println("\t\t<<하나은행 계좌 개설>>");
		
		
		String bankName = "하나은행";			
		String accountNum = scanStr("계좌번호 입력 : ");
		
		String whichTable = accountService.checkAccount(accountNum);
		
		AccountVO accountvo = accountService.accountSearch(accountNum, whichTable);
		
		
		while(accountvo != null) {
			System.out.println("이미 있는 계좌입니다.");
			accountNum = scanStr("계좌번호 재입력 : ");
			whichTable = accountService.checkAccount(accountNum);
			accountvo = accountService.accountSearch(accountNum, whichTable);

		}
		
		String alias = scanStr("계좌 별칭 입력 : ");
		String accountPwd = scanStr("비밀번호 입력(숫자 4자리) : ");
		
		while(accountPwd.length() != 4) {
			accountPwd = scanStr("비밀번호는 꼭 4자리 숫자로 입력해주세요 : ");
		}
		
		System.out.println("계좌 개설을 위해서 1000원 이상 금액의 입금이 필요합니다.");
		int balance = scanInt("입금 금액 입력(원): ");
		
		AccountVO accountVO = new AccountVO(bankName, accountNum, UserInfoDAO.getLoginName(), balance, alias, accountPwd);
		
		accountService.makeAccount(accountVO);
		
		
		
		
 
	}

}
