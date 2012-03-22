package no.ntnu.fp.g20.database;
import java.sql.DriverManager;

public class Connection {
	private java.sql.Connection conn;
	
	protected Connection(){
		if (conn == null) {
			//Sets up a connection to the database
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn = DriverManager.getConnection("jdbc:mysql://msyql.stud.ntnu.no/" + Config.SQL_DB
					+ "?profileSQL=false", Config.SQL_USERNAME, Config.SQL_PASSWORD);
			} catch (Exception e) {
				e.printStackTrace();
				conn = null;
			}
		}
	}

	protected java.sql.Connection getConnection() {
		return conn;
	}
}
