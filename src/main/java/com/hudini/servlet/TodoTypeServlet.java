package com.hudini.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hudini.dao.TodoDao;


@WebServlet("/todochange")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodoTypeServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//json 파일로 보내주기 위한 response 설정
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		PrintWriter out = response.getWriter();
		
		//넘겨받은 Parameter 가져오기
		long id = Long.parseLong(request.getParameter("id"));
		String curType = request.getParameter("type");
		
		//dao에 업데이트할 id와 type 값 넘기기
		TodoDao dao = new TodoDao();
		int count = dao.updateTodo(id,curType);
		
		//count 가 1이면 1개 업데이트 되었고, id는 유일값이므로 1개면 업데이트가 성공적으로 처리
		if(count==1){
			request.setAttribute("todolist", dao.getTodos());
			response.setStatus(200); 
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(dao.getTodos());
			out.println(json);
		}
		out.close();
	}	
}