package kr.ac.kopo.bank.vo;

public class AccountVO {
	
	private String	bankName;
	private String 	accountNum;
	private String 	name;
	private int 	balance;
	private String 	alias;
	private String	accountPwd;
	private String  id;

	
	public AccountVO() {
		
	}
	
	
	
	public AccountVO(String bankName, String accountNum, String name, int balance, String alias, String accountPwd) {
		super();
		this.bankName = bankName;
		this.accountNum = accountNum;
		this.name = name;
		this.balance = balance;
		this.alias = alias;
		this.accountPwd = accountPwd;
	}
	
	
	

	public AccountVO(String bankName, String accountNum, String name, String alias, int balance) {
		super();
		this.bankName = bankName;
		this.accountNum = accountNum;
		this.name = name;
		this.balance = balance;
		this.alias = alias;
	}
	
//	//계좌 조회 string
//	public String toAccountString() {
//		return bankName + "\t" + accountNum + "\t" + name + "\t" + alias + "\t" + balance;
//	}

	@Override
	public String toString() {
		return "AccountVO [bankName=" + bankName + ", accountNum=" + accountNum + ", name=" + name + ", balance="
				+ balance + ", alias=" + alias + ", accountPwd=" + accountPwd + "]";
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAccountPwd() {
		return accountPwd;
	}

	public void setAccountPwd(String accountPwd) {
		this.accountPwd = accountPwd;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}
	
	
	
	

}
