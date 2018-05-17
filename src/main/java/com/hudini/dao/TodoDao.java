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
/*
 * todo Table에 접근하기 위한 Data Acess Object 
 */
public class TodoDao {
	/**
	 * todo table에 데이터 insert 하는 메소드
	 * @param todo TodoDTo 타입
	 * @return success 시 1을 return
	 */
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
	/**
	 * todo Table에서 모든 데이터를 가져오는 메소드
	 * @return TodoDto 타입의 리스트 객체 반환 
	 */
	public List<TodoDto> getTodos(){
		List<TodoDto> todos = new ArrayList<>();
		JDBCUtil jdbcUtil = JDBCUtil.getInstance(); 
		Connection conn = jdbcUtil.getConnection();
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select id, title, name, sequence, type, regdate from todo order by regdate desc";
		
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
	/**
	 * 
	 * @param id        long type의  primaryKey
	 * @param curType   TODO 또는 DOING 의 값을 가진 String 
	 * @return success시 int 형 1을 반환
	 */
	public int updateTodo(long id, String curType){
		int count = 0;
		JDBCUtil jdbcUtil = JDBCUtil.getInstance(); 
		Connection conn = jdbcUtil.getConnection();
		PreparedStatement ps = null;
		String sqlUpdate = "update todo set type = ? where id = ?";
		try{
			ps = conn.prepareStatement(sqlUpdate);
			if(curType.equals("TODO")){
				ps.setString(1, "DOING");
			}else if(curType.equals("DOING")){
				ps.setString(1, "DONE");
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
