package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Account;
import model.GetTasksLogic;
import model.PostTaskLogic;
import model.Task;

/**
 * Servlet implementation class MenuServlet
 */
@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getSession().getAttribute("account") == null)
		{
			response.sendRedirect("/toDoAppli/MainServlet");
			return;
		}
		else {
			//TODOリストの読み込みを行う。
			GetTasksLogic logic = new GetTasksLogic();
			List<Task> tasks = logic.execute((Account) request.getSession().getAttribute("account"));
			
			request.getSession().setAttribute("tasks", tasks);
			
			List<Task> cleared_tasks = logic.getClearedTasks();
			
			request.getSession().setAttribute("cleared_tasks", cleared_tasks);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mainMenu.jsp");
		dispatcher.forward(request, response);
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String text = request.getParameter("text");
		PostTaskLogic logic = new PostTaskLogic();
		boolean result = logic.execute(text , ((Account)request.getSession().getAttribute("account")).getUserId());
		
		
		if (result)
		{
			//投稿が成功したら
			request.setAttribute("resultMsg", "投稿できました。");
		}
		else
		{
			request.setAttribute("resultMsg", "投稿に失敗しました。");
		}
		
		this.doGet(request, response);
		
	}

}
