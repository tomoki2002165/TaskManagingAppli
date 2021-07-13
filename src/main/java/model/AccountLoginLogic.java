package model;

import dao.AccountDAO;

public class AccountLoginLogic {
	
	public boolean execute(String user_id , String password) {
		
		AccountDAO dao = new AccountDAO();
		
		if (dao.isAccountRegistered(user_id , password))
		{
			//成功
			return true;
		}
		else
		{
			//ログインIDが間違っているかパスワードが違います。
			return false;
		}
		
	}
	
	public Account getAccountById(String user_id)
	{
		AccountDAO dao = new AccountDAO();
		
		return dao.getAccountById(user_id);
	}
	
}