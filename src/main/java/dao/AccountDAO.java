package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Account;

public class AccountDAO {
	
	private final String DRIVER_NAME = "org.h2.Driver";
	private final String JDBC_URL = "jdbc:h2:~/tomokiToDo";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	public boolean registerAccount(Account account) {
		
		//修正案 return値をboolean→Stringとし、errorMsgを返す。String:nullならばアカウントがregisterできてるとする。

		
		Connection conn = null;
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL , DB_USER , DB_PASS);
			
			String sql = "SELECT USER_ID FROM ACCOUNT";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next())
			{
				String user_id = rs.getString("USER_ID");
				
				if (account.getUserId().equalsIgnoreCase(user_id))
				{
					conn.close();
					return false;
				}
			}
			
			Statement st = conn.createStatement();
			st.executeUpdate("INSERT INTO ACCOUNT (USER_ID , NAME , PASS) VALUES(" +
			"'" + account.getUserId() + "' ," +
			"'" + account.getUserName() + "' , " +
			"'" + account.getPassword() + "' )");
			conn.close();
			
			return true;
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			return false;
		}
		finally {
			if (conn != null)
			{
				try {
					conn.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
					return false;
				}
			}
		}
		
	}
	
	public boolean isAccountRegistered(String user_id , String password) {
		
		Connection conn = null;
		
		try {
			int count = 0;
			
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL , DB_USER , DB_PASS);
			
			String sql = "SELECT * FROM ACCOUNT WHERE USER_ID = '" + user_id + "' AND PASS = '" + password + "'";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next()) count++;
			
			if (count > 0)
			{
				return true;
			}
			else
			{
				return false;
			}
			
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			return false;
		}
		finally {
			if (conn != null)
			{
				try {
					conn.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
					return false;
				}
			}
		}
	}
	
	public Account getAccountById(String user_id)
	{
		Connection conn = null;
		
		try {
			Account account = null;
			
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL , DB_USER , DB_PASS);
			
			String sql = "SELECT * FROM ACCOUNT WHERE USER_ID = '" + user_id + "'";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next())
			{
				String password = rs.getString("PASS");
				String user_name = rs.getString("NAME");
				
				account = new Account(user_id , user_name , password);
			}
			
			return account;
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			return null;
		}
		finally {
			if (conn != null)
			{
				try {
					conn.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
					return null;
				}
			}
		}
		
	}
}
