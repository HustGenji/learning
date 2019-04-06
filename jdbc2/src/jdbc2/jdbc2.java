package jdbc2;

import java.sql.*;


public class jdbc2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// TODO Auto-generated method stub
			//加载驱动类
			Class.forName("com.mysql.jdbc.Driver");
			//建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/genji", "root", "admin");
			Statement stmt=conn.createStatement();
			String sql="SELECT * FROM stu";
			stmt.execute(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
