package com.hudini.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hudini.dao.TodoDao;
import com.hudini.dto.TodoDto;

/**
 * Servlet implementation class TodoAddServlet
 */
@WebServlet("/todoadd")
public class TodoAddServlet extends HttpServlet {
       
    /**
	 * 
	 */
	private static final long serialVersionUID = 2866966074580669713L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public TodoAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TodoDao dao = new TodoDao();
		request.setCharacterEncoding("utf-8");
		String title = request.getParameter("title");
		String name = request.getParameter("name");
		int sequence = Integer.parseInt(request.getParameter("sequence1"));
		TodoDto dto = new TodoDto(0, name, null, sequence, title, "TODO");
		int count = dao.addTodo(dto);
		if(count == 1){
			response.sendRedirect("./main");
		}else{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/todoForm.jsp");
			dispatcher.forward(request, response);
		}
	}

}
