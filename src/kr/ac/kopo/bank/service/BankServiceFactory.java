package kr.ac.kopo.bank.service;

public class BankServiceFactory {
	
	private static UserInfoService service = null;
	private static AccountService serviceAcc = null;
	
	
	public static UserInfoService getUserInfoService() {
		
		if(service == null) {
			service = new UserInfoService();
		}
		
		return service;
	}
	
	public static AccountService getAccountService() {
		
		if(serviceAcc == null) {
			serviceAcc = new AccountService();
		}
		
		return serviceAcc;
	}

}
