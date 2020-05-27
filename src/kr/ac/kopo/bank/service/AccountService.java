package kr.ac.kopo.bank.service;

import java.util.List;

import kr.ac.kopo.bank.dao.AccountDAO;
import kr.ac.kopo.bank.vo.AccountVO;

public class AccountService {
	
	private AccountDAO accountDAO;
	
	public AccountService() {
		accountDAO = new AccountDAO();
	}
	
	public void makeAccount(AccountVO accountVO) {
		
		accountDAO.makeAccount(accountVO);
	}
	
	
	public List<AccountVO> selectAll() {
		
		return accountDAO.selectAll();
	}
	
	public List<AccountVO> selectByBank(String selectBank){
		return accountDAO.selectByBank(selectBank);
	}
	
	
	public void deposit(AccountVO accountVO) {
		accountDAO.deposit(accountVO);
	}
	
	public int checkBalance(AccountVO accountVO) {
		int nowBalance = accountDAO.checkBalance(accountVO);
		return nowBalance;
	}
	
	public void withdraw(AccountVO accountVO) {
		accountDAO.withdraw(accountVO);
	}
	
	public void transfer(String myAccount, String yourAccount, int money) {
		accountDAO.transfer(myAccount, yourAccount, money);
	}
	
	public String checkAccount(String accountNum) {
		return accountDAO.checkAccount(accountNum);

	}
	
	public AccountVO accountSearch(String accountNum, String tableName) {
		return accountDAO.accountSearch(accountNum, tableName);
	}
	
	public void addAccount(AccountVO accountVO) {
		accountDAO.addAccount(accountVO);
	}
	
	public void deleteAccout(AccountVO accountVO) {
		accountDAO.deleteAccout(accountVO);
	}
	
	public List<AccountVO> selectAllFromOuter(){
		return accountDAO.selectAllFromOuter();
	}
	
	public void modifyAliasUI(String accountNum, String modifyAlias) {
		accountDAO.modifyAliasUI(accountNum, modifyAlias);
	}
	
	

}
