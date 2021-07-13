package model;

import dao.ToDoDAO;

public class TaskClearLogic {
	
	public boolean execute(int task_id) {
		
		ToDoDAO dao = new ToDoDAO();
		return dao.clearTask(task_id);
	}
	
}