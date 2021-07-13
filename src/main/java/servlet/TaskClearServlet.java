package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TaskClearLogic;


@WebServlet("/TaskClearServlet")
public class TaskClearServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		int task_id = Integer.parseInt(request.getParameter("task_id"));
		String task_name = request.getParameter("task_name");
		
		TaskClearLogic logic = new TaskClearLogic();
		boolean result = logic.execute(task_id);
		
		if (result)
		{
			//request.getSession().setAttribute("clearMsg", "タスク:" + task_name + "をクリアしました！");
		}
		else
		{
			//request.getSession().setAttribute("clearMsg", "エラーが発生しました。");
		}
		
		response.sendRedirect("/toDoAppli/MenuServlet");
		
	}

}
