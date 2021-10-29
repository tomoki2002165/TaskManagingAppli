package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.Task;

public class ToDoDAO {
	
	private final String DRIVER_NAME = "org.h2.Driver";
	private final String JDBC_URL = "jdbc:h2:~/tomokiToDo";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	//DAO関連の修正：成功した際にbooleanではなくStringをreturn.
	
	public List<Task> getClearedTasks()
	{
		
		Connection conn = null;
		List<Task> cleared_tasks = new ArrayList<>();
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL , DB_USER , DB_PASS);
			
			PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM TODO WHERE TODO_CHECKED=TRUE");
			
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next())
			{
				String todo_text = rs.getString("TODO_TEXT");
				int todo_id = rs.getInt("TODO_ID");
				
				Task task = new Task(todo_id , todo_text , true);
				cleared_tasks.add(task);
			}
			
			conn.close();
			return cleared_tasks;
			
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
	
	public boolean clearTask(int task_id)
	{
		Connection conn = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL , DB_USER , DB_PASS);
			
			Statement st = conn.createStatement();
			st.executeUpdate("UPDATE TODO SET TODO_CHECKED=TRUE WHERE TODO_ID='" + task_id + "'");
			
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
	
	public List<Task> getTasks(Account account)
	{
		//USER_TODOテーブルから主キーがaccount.userIdであるレコードを全て取得。そのレコードのtodo_idをtodoIDsへいれる。
		//取得したtodo_id一つ一つを、todoで取得しTaskとしてインスタンス化。
		//全てのTaskをList<Task>へ追加し、return。
		
		
		Connection conn = null;
		List<Task> tasks = new ArrayList<>();
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL , DB_USER , DB_PASS);
			
			PreparedStatement preCheck = conn.prepareStatement("SELECT TODO_ID FROM USER_TODO WHERE USER_ID = '" + account.getUserId() + "'");
			ResultSet _rs = preCheck.executeQuery();
			
			List<Integer> todoIDs = new ArrayList<>();
			
			while (_rs.next())
			{
				todoIDs.add(_rs.getInt("TODO_ID"));
			}
			
			for (int id : todoIDs)
			{
				PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM TODO WHERE TODO_ID = " + id + "");
				
				ResultSet rs = pStmt.executeQuery();
				
				while (rs.next())
				{
					String todo_text = rs.getString("TODO_TEXT");
					boolean todo_checked = rs.getBoolean("TODO_CHECKED");
					
					Task task = new Task(id , todo_text , todo_checked);
					tasks.add(task);
				}
			}
			
			conn.close();
			return tasks;
			
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
	
	public boolean makeTask(String text , String user_id)
	{
		//TODOテーブルへ登録し、USER_TODOに紐づける。
		
		Connection conn = null;
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL , DB_USER , DB_PASS);
			
			PreparedStatement preCheck = conn.prepareStatement("SELECT * FROM TODO");
			ResultSet _rs = preCheck.executeQuery();
			
			//TODO表の,[レコード数] = [todo_id]とすることで重複しないidが取得できる。
			int todo_id = 0;
			
			while (_rs.next())
			{
				todo_id++;
			}
			
			Statement st = conn.createStatement();
			st.executeUpdate("INSERT INTO TODO (TODO_ID , TODO_TEXT , TODO_CHECKED) VALUES(" +
			"" + todo_id + " ," +
			"'" + text + "' , " +
			"" + false + " )");
			
			st.executeUpdate("INSERT INTO USER_TODO (USER_ID , TODO_ID) VALUES(" +
			"'" + user_id + "' ," +
			"" + todo_id + ")");
			
			conn.close();
			
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
		
		return true;
	}
	
	/*
	 * 	public boolean makeTask(String text , boolean isCleared , String user_id)
	{
		//TODOテーブルへ登録し、USER_TODOに紐づける。
		
		Connection conn = null;
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL , DB_USER , DB_PASS);
			
			PreparedStatement preCheck = conn.prepareStatement("SELECT * FROM TODO");
			ResultSet _rs = preCheck.executeQuery();
			
			//TODO表の,[レコード数] = [todo_id]とすることで重複しないidが取得できる。
			int todo_id = 0;
			
			while (_rs.next())
			{
				todo_id++;
			}
			
			Statement st = conn.createStatement();
			st.executeUpdate("INSERT INTO TODO (TODO_ID , TODO_TEXT , TODO_CHECKED) VALUES(" +
			"" + todo_id + " ," +
			"'" + text + "' , " +
			"" + isCleared + " )");
			
			conn.close();
			
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
		
		return true;
	}
	 */
	
}
