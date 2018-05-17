package com.hudini.servlet;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hudini.dao.TodoDao;
import com.hudini.dto.TodoDto;

@WebServlet("/main")
public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 6943882302211278818L;
    public MainServlet() {}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		//dao를 통해 todo list를 받아옴
		TodoDao dao = new TodoDao();
		List<TodoDto> lists = dao.getTodos();
		//order by regdate desc로 todolist를 가져왔기 때문에 나중 리스트가 아래로 오려면 정렬을 해줘야함. 정렬해서 가져오지 않고 regdate를 asc로 가져와도 됨
		Collections.sort(lists);
		request.setAttribute("todolist", lists);
		RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
		dispatcher.forward(request, response);
	}

}
