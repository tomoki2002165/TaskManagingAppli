package model;

import java.io.Serializable;

public class Account implements Serializable {
	
	private String user_id;
	private String user_name;
	private String password;
	
	public Account(String user_id , String user_name , String password)
	{
		this.user_id = user_id;
		this.user_name = user_name;
		this.password = password;
	}
	
	public String getUserId()
	{
		return user_id;
	}
	
	public String getUserName()
	{
		return user_name;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setUserId(String user_id)
	{
		this.user_id = user_id;
	}
	
	public void setUserName(String user_name)
	{
		this.user_name = user_name;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
}