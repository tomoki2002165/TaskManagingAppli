package model;

import java.util.List;

import dao.ToDoDAO;

public class GetTasksLogic {
	
	public List<Task> execute(Account account) {
		
		ToDoDAO dao = new ToDoDAO();
		return dao.getTasks(account);
		
	}
	
	public List<Task> getClearedTasks()
	{
		ToDoDAO dao = new ToDoDAO();
		return dao.getClearedTasks();
	}
}