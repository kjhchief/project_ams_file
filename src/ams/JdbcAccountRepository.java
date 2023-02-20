package ams;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcAccountRepository implements AccountRepository {
	// DB 연결정보 상수
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:xe"; // Oracle
	private static final String user = "hr";
	private static final String password = "hr";

	private Connection con;

	// 왜 생성자로? 메모리에 생성될 때 만들어지라고?
	public JdbcAccountRepository() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, password);
	}

	@Override
	public int getCount() {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuilder sb = new StringBuilder(); 
			sb.append(" SELECT COUNT(*) cnt")
			.append(" FROM accounts");
			pstmt = con.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("cnt");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}

	// 계좌 등록
	@Override
	public void addAccount(Account account) throws IOException {
		int count = 0;
		PreparedStatement pstmt = null;
		try {
			StringBuilder sb = new StringBuilder(); 
			sb.append(" INSERT INTO accounts").append(" VALUES (?, ?, ?, ?, ?, ?)");
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, account.getAccountNumber());
			pstmt.setString(2, account.getAccountOwner());
			pstmt.setInt(3, account.getPassword());
			pstmt.setLong(4, account.getRestMoney());
			if (account instanceof MinusAccount) {
				MinusAccount ma = (MinusAccount) account;
				pstmt.setLong(5, ma.getRentMoney());
				pstmt.setDate(6, ma.getRentDate());

			} else {
				pstmt.setLong(5, 0);
				pstmt.setDate(6, null);
			}
			count = pstmt.executeUpdate();
			System.out.println(count + " 계좌 등록 완료");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// 전체 목록 조회
	@Override
	public List<Account> getAccounts() throws IOException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			StringBuilder sb = new StringBuilder(); 
			sb.append(" SELECT account_num, owner_name, passwd, rest_money, borrow_money, borrow_date")
			.append(" FROM accounts")
			.append(" ORDER BY account_num");
			pstmt = con.prepareStatement(sb.toString());

			rs = pstmt.executeQuery();
			while (rs.next()) {
				String accountNum = rs.getString("account_num");
				String ownerName = rs.getString("owner_name");
				int passWd = rs.getInt("passwd");
				long restMoney = rs.getLong("rest_money");
				long borrowMoney = rs.getLong("borrow_money");
				Date borrowDate = rs.getDate("borrow_date");
				
				System.out.println(accountNum + " || " + ownerName + " || " + passWd + " || " + restMoney + " || " + borrowMoney + " || " + borrowDate);
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Account findByNumber(String number) throws IOException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(" SELECT account_num, owner_name, passwd, rest_money, borrow_money, borrow_date")
			.append(" FROM accounts")
			.append(" WHERE account_num LIKE ?");
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, "%" + number + "%");

			rs = pstmt.executeQuery();
			while (rs.next()) {
				String accountNum = rs.getString("account_num");
				String ownerName = rs.getString("owner_name");
				int passWd = rs.getInt("passwd");
				long restMoney = rs.getLong("rest_money");
				long rentmoney = rs.getLong("borrow_money");
				Date borrowDate = rs.getDate("borrow_date");
				
				System.out.println(accountNum + " || " + ownerName + " || " + passWd + " || " + restMoney + " || " + rentmoney + " || " + borrowDate);
				
			} 

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public List<Account> findByName(String name) throws IOException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(" SELECT account_num, owner_name, passwd, rest_money, borrow_money, borrow_date")
			.append(" FROM accounts")
			.append(" WHERE owner_name LIKE ?");
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, "%" + name + "%");

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String accountNum = rs.getString("account_num");
				String ownerName = rs.getString("owner_name");
				int passWd = rs.getInt("passwd");
				long restMoney = rs.getLong("rest_money");
				long rentmoney = rs.getLong("borrow_money");
				Date borrowDate = rs.getDate("borrow_date");
				
				System.out.println(accountNum + " || " + ownerName + " || " + passWd + " || " + restMoney + " || " + rentmoney + " || " + borrowDate);
				
			} 

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public boolean removeAccount(String number) {
		PreparedStatement pstmt = null;
		try {
			StringBuilder sb = new StringBuilder(); 
			sb.append(" DELETE FROM accounts")
       		  .append(" WHERE account_num = ?"); 
//			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, number);
			int count = pstmt.executeUpdate();
//			con.commit();
			return count > 0; // 삭제가 성공한 경우 true 반환
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public void disConnect() {
		if(con != null)
			try {
				con.close();
				System.out.println("<<<<< Oracle 데이터베이스 연결 종료 >>>>>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	

}
