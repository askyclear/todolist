package com.hudini.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TodoFormServlet
 */
@WebServlet("/todo")
public class TodoFormServlet extends HttpServlet {
  
	private static final long serialVersionUID = -9085834123687252023L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public TodoFormServlet() {
        super();
    }

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("todoForm.jsp");
		dispatcher.forward(request, response);
	}
	
}
