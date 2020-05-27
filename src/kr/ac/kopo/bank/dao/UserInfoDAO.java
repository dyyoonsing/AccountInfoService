package kr.ac.kopo.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.ac.kopo.bank.vo.UserInfoVO;
import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.util.JDBCClose;

public class UserInfoDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private static String loginName;
	private static String loginID;
	
	public static String getLoginID() {
		return loginID;
	}



	public static void setLoginID(String loginID) {
		UserInfoDAO.loginID = loginID;
	}



	public static String getLoginName() {
		return loginName;
	}



	public static void setLoginName(String loginName) {
		UserInfoDAO.loginName = loginName;
	}



	/*
	 * 로그인하는 기능
	 */
	public UserInfoVO login(UserInfoVO userInfo) {
		
		UserInfoVO user = null;

		
		try {
			conn = new ConnectionFactory().getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("select name, id, pwd, phone_num ");
			sql.append(" from hana_user ");
			sql.append(" where id = ? and pwd = ?  ");
			
			pstmt = conn.prepareStatement(sql.toString());

			String id = userInfo.getId();
			String pwd = userInfo.getPassword();
			
			
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			
			ResultSet rs = pstmt.executeQuery();
			
		

			
			if(rs.next()) {
				
				loginID = rs.getString("id");
				loginName = rs.getString("name");
				String phoneNum = rs.getString("phone_num");
				
				user = new UserInfoVO(loginName, id, pwd, phoneNum);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		
		return user;
		
	}
	
	
	
	/*
	 * 본인확인
	 */
	public boolean userCheck(String name, String phoneNum) {
		
		boolean bool = true;
		
		try {
			conn = new ConnectionFactory().getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("select * ");
			sql.append(" from hana_user ");
			sql.append(" where name = ? and phone_num = ?  ");
			
			pstmt = conn.prepareStatement(sql.toString());

			
			pstmt.setString(1, name);
			pstmt.setString(2, phoneNum);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bool = false;	//이미 등록한 회원입니다.
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCClose.close(conn, pstmt);
		}
		
		return bool;
	}
	
	
	/*
	 * 아이디 중복 확인
	 */
	public boolean userIDCheck(String ID) {
		
		boolean bool = true;
		
		try {
			conn = new ConnectionFactory().getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("select * ");
			sql.append(" from hana_user ");
			sql.append(" where ID = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());

			
			pstmt.setString(1, ID);

			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bool = false;	//이미 있는 아이디입니다.
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCClose.close(conn, pstmt);
		}
		
		return bool;
	}
	
	
	
	
	/*
	 * 회원가입
	 */
	
	public void register(UserInfoVO userInfo) {

		try {
			conn = new ConnectionFactory().getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("insert into hana_user(name, id, pwd, phone_num) ");
			sql.append(" values(?, ?, ?, ?) ");
			
			pstmt = conn.prepareStatement(sql.toString());
			String name = userInfo.getName();
			String id = userInfo.getId();
			String password = userInfo.getPassword();
			String phoneNum = userInfo.getPhoneNum();
			
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setString(3, password);
			pstmt.setString(4, phoneNum);
			
			
			pstmt.executeUpdate();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCClose.close(conn, pstmt);
		}
	}
	
	
	
	


}
