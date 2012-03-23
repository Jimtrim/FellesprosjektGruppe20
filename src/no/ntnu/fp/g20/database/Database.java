package no.ntnu.fp.g20.database;

import java.sql.*;
import no.ntnu.fp.g20.*;
import no.ntnu.fp.g20.model.*;

public class Database extends Connection
{
	private PreparedStatement loginStmt;

	public Database() throws SQLException
	{
		super();
		loginStmt = getConnection().prepareStatement(DBUser.LOGIN_STATEMENT);
	}

	public boolean addAppointment(Appointment appt)
	{
		return false;
	}

	/**
	 * Logs a user in.
	 * @return the user object for the logged in user or null if an erro occurred.
	 */
	public User loginUser(String username, String password)
	{
		try {
			loginStmt.setString(1, username);
			loginStmt.setString(2, password);
	
			ResultSet rs = loginStmt.executeQuery();
	
			if(!rs.next())
				return null;
			else
				return new User(rs.getInt(1), username, password, rs.getString(2), rs.getString(3));
		} catch(Exception error)
		{
			System.err.println(error.getMessage());
			return null;
		}
	}
	
	public void editUser(){
		//TODO: Make this
		
	}

	public void createAppointment(){
		
	}
}
