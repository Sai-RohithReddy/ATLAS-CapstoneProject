package com.amazon.utilities;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtils {

	public static Connection conn = null;

	public static Connection getDBConnection() {

		try {

			if (conn == null) {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/amazondb", "root", "root");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}
	
	public static Connection getDBConnection(String user, String pwd, String db) {

		try {

			if (conn == null) {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, user, pwd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}
}
