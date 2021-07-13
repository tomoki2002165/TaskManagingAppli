package model;

import dao.AccountDAO;

public class AccountRegisterLogic {
	
	
	public String execute(Account account) {
		
		AccountDAO dao = new AccountDAO();
		boolean result = dao.registerAccount(account);
		
		
		result &= (account.getUserId() != null && account.getUserName() != null && account.getPassword() != null);
		result &= (account.getUserId().length() <= 10 && account.getUserId().length() >= 3 
				&& account.getPassword().length() <= 10 && account.getUserName().length() <= 10);
		result &= (account.getUserId() != account.getPassword());
		
		if (result == true)
		{
			return null;
		}
		else
		{
			return "エラーが起きました。";
		}
	}
}