package no.ntnu.fp.g20.database;
import java.sql.DriverManager;

public class Connection {
	
	private java.sql.Connection conn;
	
	protected Connection(){
		if (this.conn == null) {
			//Sets up a connection to the database
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				this.conn = DriverManager.getConnection(
						"jdbc:mysql://mysql.stud.ntnu.no/" + Config.SQL_DB
							+ "?profileSQL=false", Config.SQL_USERNAME,Config.SQL_PASSWORD);
			} catch (Exception e) {
				e.printStackTrace();
				this.conn = null;
			}
		}
	}
	private static class ConnectionHolder {
		public static final Connection INSTANCE = new Connection();
	}
	public static Connection getInstance() {
		return ConnectionHolder.INSTANCE;
	}
	public java.sql.Connection getConnection() {
		return this.conn;
	}
	

}
