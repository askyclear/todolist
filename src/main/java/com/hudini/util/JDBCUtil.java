package com.hudini.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil {
	private static JDBCUtil instance;
	private final String url = "jdbc:mysql://localhost/connectdb";
	private final String user = "askyclear";
	private final String password = "gksmf12a";
	private JDBCUtil(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static JDBCUtil getInstance(){
		if(instance==null){
			instance = new JDBCUtil();
		}
		return instance;
	}
	/**
	 * db에 연결 한후 Connection을 return 한다.
	 * @return Connection fail is null;
	 */
	public Connection getConnection(){
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(url, user, password);
		}catch(SQLException e){
			conn = null;
		}
		return conn;
	}
	/**
	 * close method is SQL close에 대한 메소드 이다.
	 * 오버로딩 한 다음 close(@link {@link Connection}, {@link PreparedStatement}, {@link ResultSet};으로 move 
	 * @param conn Connection in sql
	 */
	public void close(Connection conn){
		close(conn,null,null);
	}
	public void close(Connection conn, PreparedStatement ps){
		close(conn,ps,null);
	}
	/**
	 * JDBC의  트렌젝션을 마친 후 연결을 모두 close 해주는 메소드 
	 * @param conn Connection이 null이 아닐경우 close 함
	 * @param ps PreparedStatement가 null이 아닐경우 close 함
	 * @param rs ResultSet이 null이 아닐경우 close 함
	 */
	public void close(Connection conn, PreparedStatement ps, ResultSet rs){
		if(rs != null){
			try{
				rs.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(ps != null){
			try{
				ps.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(conn != null){
			try{
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
}
