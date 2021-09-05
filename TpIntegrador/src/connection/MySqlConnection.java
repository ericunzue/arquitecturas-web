package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
	private static Connection conn = null;

	private MySqlConnection() {

		String url = "jdbc:mysql://localhost:3306/integrador1db";
		String driver = "com.mysql.cj.jdbc.Driver";
		String user = "root";
		String pass = "1234";

		try {
			Class.forName(driver);
			try {
				conn = DriverManager.getConnection(url, user, pass);
				conn.setAutoCommit(false);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}


	public static Connection getConnection() {

		if (conn == null) {
			new MySqlConnection();
//			System.out.println("Connection Succesfull");
		}

		return conn;
	}
}

