package kr.ac.kopo.bank;

import kr.ac.kopo.bank.ui.BankFirstUI;

public class BankMain {
	
	
	public static void main(String[] args) {
		
		BankFirstUI ui = new BankFirstUI();
		try {
			ui.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
