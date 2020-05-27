package kr.ac.kopo.bank.ui;

import java.util.Scanner;

import kr.ac.kopo.bank.service.AccountService;
import kr.ac.kopo.bank.service.BankServiceFactory;
import kr.ac.kopo.bank.service.UserInfoService;

public abstract class BaseUI implements IBankUI {
	
	private Scanner sc;
	protected UserInfoService userInfoService;
	protected AccountService accountService;

	
	public BaseUI() {
		sc = new Scanner(System.in);
		userInfoService = BankServiceFactory.getUserInfoService();
		accountService = BankServiceFactory.getAccountService();
		
	}
	
	protected int scanInt(String msg) {
		System.out.print(msg);
		return Integer.parseInt(sc.nextLine());
	}
	
	protected String scanStr(String msg) {
		System.out.print(msg);
		return sc.nextLine();
	}

}
