package com.conn;
import java.sql.*;
public class DbConnect {
	private static Connection conn;
	
	public static Connection getConn() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/phonebook?useUnicode=true&serverTimezone=UTC&useSSL=false", "root", "GFhjkm2003");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
