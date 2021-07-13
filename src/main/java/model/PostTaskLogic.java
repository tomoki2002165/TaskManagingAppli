package model;

import dao.ToDoDAO;

public class PostTaskLogic {
	
	public boolean execute(String text , String user_id) {
		
		ToDoDAO dao = new ToDoDAO();
		boolean result = dao.makeTask(text , user_id);
		
		return result;
	}
	
}