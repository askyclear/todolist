package com.hudini.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.hudini.dto.TodoDto;
import com.hudini.util.JDBCUtil;

public class TodoDao {
	public int addTodo(TodoDto todo){
		int count = 0;
		JDBCUtil jdbcUtil = JDBCUtil.getInstance(); 
		Connection conn = jdbcUtil.getConnection();
		PreparedStatement ps = null;
		
		String sql = "insert into todo(title, name, sequence) values( ?, ?, ?)";
		try{
			ps = conn.prepareStatement(sql);
			ps.setString(1, todo.getTitle());
			ps.setString(2, todo.getName());
			ps.setInt(3, todo.getSequence());
			count = ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			jdbcUtil.close(conn,ps);
		}
		return count;
	}
	public List<TodoDto> getTodos(){
		List<TodoDto> todos = new ArrayList<>();
		JDBCUtil jdbcUtil = JDBCUtil.getInstance(); 
		Connection conn = jdbcUtil.getConnection();
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select id, title, name, sequence, type, regdate from todo order by regdate desc";
//		String sql = "select id, title, name, sequence, type, regdate from todo where type = ? order by regdate desc";
		try{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				long id = rs.getLong("id");
				String title = rs.getString("title");
				String name = rs.getString("name");
				int sequence = rs.getInt("sequence");
				String type = rs.getString("type");
				String regdate = format.format(rs.getDate("regdate"));
				TodoDto todo = new TodoDto(id, name, regdate, sequence, title, type);
				todos.add(todo);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			jdbcUtil.close(conn,ps,rs);
		}
		
		return todos;
	}
	public int updateTodo(long id){
		int count = 0;
		JDBCUtil jdbcUtil = JDBCUtil.getInstance(); 
		Connection conn = jdbcUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sqlSelect = "select type from todo where id = ?";
		String sqlUpdate = "update todo set type = ? where id = ?";
		try{
			ps = conn.prepareStatement(sqlSelect);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			String type = null;
			while(rs.next()){
				type = rs.getString("type");
			}
			ps.close();
			ps = conn.prepareStatement(sqlUpdate);
			switch(type){
				case "TODO" : ps.setString(1, "DOING");
					break;
				case "DOING" : ps.setString(1, "DONE");
					break;
				default:
					ps.setString(1,"DONE");
			}
			ps.setLong(2, id);
			count = ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			jdbcUtil.close(conn,ps);
		}
		return count;
	}
}
