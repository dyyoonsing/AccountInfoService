package kr.ac.kopo.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.bank.vo.AccountVO;
import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.util.JDBCClose;

public class AccountDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
		
	//1. 새로운 계좌 개설
	public void makeAccount(AccountVO accountVO) {	
		
		try {			
			conn = new ConnectionFactory().getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("insert into hana_account_info(bank_name, account_num, name, alias, account_pwd, balance, id) ");
			sql.append(" values(?, ?, ?, ?, ?, ?, ?) ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			String bankName = accountVO.getBankName();
			String accountNum = accountVO.getAccountNum();
			String name = accountVO.getName();
			String alias = accountVO.getAlias();
			String accountPwd = accountVO.getAccountPwd();
			int balance = accountVO.getBalance();
			
			
			pstmt.setString(1, bankName);
			pstmt.setString(2, accountNum);
			pstmt.setString(3, name);
			pstmt.setString(4, alias);
			pstmt.setString(5, accountPwd);
			pstmt.setInt(6, balance);
			pstmt.setString(7, UserInfoDAO.getLoginID());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
	}
	
	//2. 계좌 조회 
		//2-1. 전체 계좌 조회
	public List<AccountVO> selectAll() {
		
		List<AccountVO> accountList = new ArrayList<AccountVO>();
		
		try {
			conn = new ConnectionFactory().getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append(" select bank_name, account_num, name, alias, balance  ");
			sql.append(" from hana_account_info  ");
			sql.append(" where id = ?  ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, UserInfoDAO.getLoginID());
			
			ResultSet rs = pstmt.executeQuery();
			
	
			
			while(rs.next()) {
				
				String bankName = rs.getString("bank_name");
				String accountNum = rs.getString("account_num");
				String name = rs.getString("name");
				String alias = rs.getString("alias");
				int balance = rs.getInt("balance");
				
				AccountVO accountVO = new AccountVO(bankName, accountNum, name, alias, balance);
				
				accountList.add(accountVO);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		
		return accountList;
		
	}
	
		//2-2. 은행별 계좌 조회
	public List<AccountVO> selectByBank(String bank) {
		
		List<AccountVO> accountList = new ArrayList<AccountVO>();
		
		try {
			conn = new ConnectionFactory().getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append(" select bank_name, account_num, name, alias, balance  ");
			sql.append(" from hana_account_info  ");
			sql.append(" where id = ? and bank_name = ?  ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, UserInfoDAO.getLoginID());
			pstmt.setString(2, bank);
			
			ResultSet rs = pstmt.executeQuery();
			
	
			
			while(rs.next()) {
				
				String bankName = rs.getString("bank_name");
				String accountNum = rs.getString("account_num");
				String name = rs.getString("name");
				String alias = rs.getString("alias");
				int balance = rs.getInt("balance");
				
				AccountVO accountVO = new AccountVO(bankName, accountNum, name, alias, balance);
				
				accountList.add(accountVO);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		
		return accountList;
		
	}
	
	
		//2-3. 외부 은행 계좌 조회
	public List<AccountVO> selectAllFromOuter(){
		
		List<AccountVO> accountList = new ArrayList<AccountVO>();
		
		try {
			conn = new ConnectionFactory().getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append(" select bank_name, account_num, name, alias, balance  ");
			sql.append(" from account_info  ");
			sql.append(" where id = ?  ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, UserInfoDAO.getLoginID());
			
			ResultSet rs = pstmt.executeQuery();
			

			while(rs.next()) {
				
				String bankName = rs.getString("bank_name");
				String accountNum = rs.getString("account_num");
				String name = rs.getString("name");
				String alias = rs.getString("alias");
				int balance = rs.getInt("balance");
				
				AccountVO accountVO = new AccountVO(bankName, accountNum, name, alias, balance);
				
				accountList.add(accountVO);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		
		return accountList;
		
		
	}
	
	
	
	//3. 계좌 관리
		//3-1. 계좌 추가 (account_info에서 hana_account_info로)
	public void addAccount(AccountVO accountVO) {
		try {
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);
			
			
			StringBuilder sql = new StringBuilder();
			sql.append("insert into hana_account_info ");
			sql.append(" values(?, ?, ?, ?, ?, ?, ?) ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, accountVO.getBankName());
			pstmt.setString(2, accountVO.getAccountNum());
			pstmt.setString(3, accountVO.getName());
			pstmt.setInt(4, accountVO.getBalance());
			pstmt.setString(5, accountVO.getAlias());
			pstmt.setString(6, accountVO.getAccountPwd());
			pstmt.setString(7, UserInfoDAO.getLoginID());
			
			pstmt.executeUpdate();
			
			pstmt.clearParameters();
			

			sql.setLength(0);
			
			sql.append("delete from account_info ");
			sql.append("where account_num = ? ");
			
			String accountNum = accountVO.getAccountNum();
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, accountNum);
			
			pstmt.executeUpdate();
			
				
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			JDBCClose.close(conn, pstmt);
		}
		
		
		
	}
	
		//3-2. 계좌 삭제
	public void deleteAccout(AccountVO accountVO) {
		
		try {
			
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);
			
			
			StringBuilder sql = new StringBuilder();
			
			sql.append("delete from hana_account_info ");
			sql.append("where account_num = ? ");
			
			String accountNum = accountVO.getAccountNum();
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, accountNum);
			
			pstmt.executeUpdate();
			
			pstmt.clearParameters();
			

			sql.setLength(0);
			
			sql.append("insert into account_info ");
			sql.append(" values(?, ?, ?, ?, ?, ?, ?) ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, accountVO.getBankName());
			pstmt.setString(2, accountVO.getAccountNum());
			pstmt.setString(3, accountVO.getName());
			pstmt.setInt(4, accountVO.getBalance());
			pstmt.setString(5, accountVO.getAlias());
			pstmt.setString(6, accountVO.getAccountPwd());
			pstmt.setString(7, UserInfoDAO.getLoginID());
			
			pstmt.executeUpdate();
			
			
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				try {
					conn.setAutoCommit(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			JDBCClose.close(conn, pstmt);
		}
		
		
		
	}
	
	//4. 별칭 수정 
	public void modifyAliasUI(String accountNum, String modifyAlias) {
		
		try {
			conn = new ConnectionFactory().getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append(" update  hana_account_info  ");
	        sql.append(" set alias = ?  ");
	        sql.append(" where  account_num = ?  ");
	        
	        pstmt = conn.prepareStatement(sql.toString());
	        
	        pstmt.setString(1, modifyAlias);
	        pstmt.setString(2, accountNum);
	        
	        pstmt.executeUpdate();
	        
	        
	        

			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		
		
	}
	
	
	
	
	
	
	
	
	//5. 계좌 검색
	
	public AccountVO accountSearch(String accountNum, String tableName) {
		AccountVO accountVO = null;
		
		try {
			conn = new ConnectionFactory().getConnection();
			
			StringBuilder sql = new StringBuilder();
			if(tableName == "hana_account_info") {
				sql.append("select * from hana_account_info ");
				sql.append("where account_num = ? ");
				
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, accountNum);
				
			}else if(tableName == "account_info")  {
				sql.append("select * from account_info ");
				sql.append("where account_num = ? ");
				
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, accountNum);
			}else {
				return accountVO;
			}
			
			
			ResultSet rs = pstmt.executeQuery();
			
			
			if(rs.next()) {
				accountVO = new AccountVO();				
				accountVO.setBankName(rs.getString("bank_name"));
				accountVO.setAccountNum(accountNum);
				accountVO.setName(rs.getString("name"));
				accountVO.setAlias(rs.getString("alias"));
				accountVO.setAccountPwd(rs.getString("account_pwd"));
				accountVO.setBalance(rs.getInt("balance"));
				accountVO.setId(UserInfoDAO.getLoginID());
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCClose.close(conn, pstmt);
		}
		
		return accountVO;
	}
	
	

	
	

	
	

	//5. 입금하기	
	public void deposit(AccountVO accountVO) {
		
		try {
			conn = new ConnectionFactory().getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("update hana_account_info ");
			sql.append(" set   balance = (balance + ?)  ");
			sql.append(" where (bank_name = ?) and (account_num = ?)  ");
			
			int addBalance = accountVO.getBalance();
			String bankName = accountVO.getBankName();
			String AccountNum = accountVO.getAccountNum();
			
			pstmt = conn.prepareStatement(sql.toString());
		
			
			pstmt.setInt(1, addBalance);
			pstmt.setString(2, bankName);
			pstmt.setString(3, AccountNum);	
			
			pstmt.executeUpdate();
	
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		
		
	}
	
	
	
	//6. 출금하기
		//6-1. 출금전 금액 확인
	public int checkBalance(AccountVO accountVO) {
		
		int nowBalance = 0;
		
		try {
			conn = new ConnectionFactory().getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("select balance ");
			sql.append("from hana_account_info ");
			sql.append("where (bank_name = ?) and (account_num = ?) ");

			String bankName = accountVO.getBankName();
			String AccountNum = accountVO.getAccountNum();
			
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, bankName);
			pstmt.setString(2, AccountNum);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				nowBalance = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		
		return nowBalance;
				
	}
	
		//6-2. 출금하기
	public void withdraw(AccountVO accountVO) {
		
		try {
			conn = new ConnectionFactory().getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("update hana_account_info ");
			sql.append(" set  balance = (balance - ?)  ");
			sql.append(" where (bank_name = ?) and (account_num = ?)  ");
			
			int minusBalance = accountVO.getBalance();
			String bankName = accountVO.getBankName();
			String AccountNum = accountVO.getAccountNum();
			
			pstmt = conn.prepareStatement(sql.toString());
						
			pstmt.setInt(1, minusBalance);
			pstmt.setString(2, bankName);
			pstmt.setString(3, AccountNum);

				
			pstmt.executeUpdate();

			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		
		
	}
	
	
	//7. 계좌 이체
	public void transfer(String myAccount, String yourAccount, int money) {
		
		try {
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);
			
			//내 계좌에서 출금
			StringBuilder sql = new StringBuilder();
			
			sql.append(" update hana_account_info set balance = ( balance - ? )  ");
			sql.append(" where (account_num = ?)  ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, money);
			pstmt.setString(2, myAccount);
			
			pstmt.executeUpdate();
			
			pstmt.clearParameters();
			

			sql.setLength(0);
			
			//계좌 어느 테이블에 있는지 확인			
			boolean bool = true; 		//hana_account_info 테이블에 결과값있다

			sql.append("select * from hana_account_info ");
			sql.append(" where (account_num = ?) ");
			

			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, yourAccount);
			
				
			ResultSet rs = pstmt.executeQuery();

			
			

			if(!rs.next()) {
				bool = false;	//account_info 테이블에 결과값 있다.
			}
				
			
			pstmt.clearParameters();

			
			//상대방 계좌에 입금
			sql.setLength(0);
			
			if(bool) {
				sql.append(" update hana_account_info set balance = ( balance + ? ) ");
				sql.append(" where (account_num = ?) ");
			}else {
				sql.append(" update account_info set balance = ( balance + ? ) ");
				sql.append(" where (account_num = ?) ");
			}
			
			pstmt = conn.prepareStatement(sql.toString());
		
			pstmt.setInt(1, money);
			pstmt.setString(2, yourAccount);


			pstmt.executeUpdate();
			

			conn.commit();
	
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			JDBCClose.close(conn, pstmt);
		}
		
		
	}
	
	//account가 어느 테이블에 있는지 확인
	public String checkAccount(String accountNum) {
		
		String whichAccount = null;
		
		try {
			conn = new ConnectionFactory().getConnection();
			
			StringBuilder sql = new StringBuilder();
			
			sql.append("select * from hana_account_info ");
			sql.append("where account_num = ?  ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, accountNum);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				whichAccount = "hana_account_info";
			}else {
				
				pstmt.clearParameters();
				sql.setLength(0);
				
				sql.append("select * from account_info ");
				sql.append("where account_num = ?  ");
				
				pstmt = conn.prepareStatement(sql.toString());
				
				pstmt.setString(1, accountNum);
				
				ResultSet rs2 = pstmt.executeQuery();
				
				if(rs2.next()) {
					whichAccount = "account_info";
				}
				
			}
			

			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCClose.close(conn, pstmt);
		}
		
		
		return whichAccount;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
