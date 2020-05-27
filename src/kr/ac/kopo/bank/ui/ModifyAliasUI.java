package kr.ac.kopo.bank.ui;


public class ModifyAliasUI extends BaseUI{

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		
	      String accountNum = scanStr("수정할 계좌번호를 입력하세요. : ");
	      String alias = scanStr("수정할 계좌 별칭을 새로 입력하세요. : ");
	     
	      String tableName = accountService.checkAccount(accountNum);
	      
	      while(tableName != "hana_account_info") {
	    	  System.out.println("계좌번호를 잘못 입력했습니다.");
	    	  alias = scanStr("계좌번호를 다시 입력하세요 : ");
	    	  tableName = accountService.checkAccount(accountNum);
	      }
	      
	      accountService.modifyAliasUI(accountNum, alias);
	      
	      System.out.println("수정완료되었습니다...");
		
		
	}
	
	

}
