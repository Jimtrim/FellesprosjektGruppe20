package no.ntnu.fp.g20.database;

import java.sql.*;
import java.util.*;

import no.ntnu.fp.g20.*;
import no.ntnu.fp.g20.model.*;

public class Database extends Connection
{
	private PreparedStatement loginStmt;
	private PreparedStatement getAppointmentStmt;

	public Database() throws SQLException
	{
		super();
		loginStmt = getConnection().prepareStatement(DBUser.LOGIN_STATEMENT);
		// TODO: Move string statement below.
		getAppointmentStmt = getConnection().prepareStatement("SELECT * FROM appointments WHERE owner = ?");
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

	/**
	 * Gets the participants for a specified appointment.
	 * @param apptID the ID of the appointment whose users you would like.
	 * @return an array of users who are participants of the specified appointment.
	 */

	/**
	 * Gets the appointments for a specified user.
	 * @return an array of appointment objects for a specified user.
	 * @param userID the ID of the user whose appointments you would like.
	 */
	public ArrayList<Appointment> getAppointmentsForUser(int userID)
	{
		ArrayList<Appointment> retval = new ArrayList<Appointment>();

		try {
			getAppointmentStmt.setInt(1, userID);
			ResultSet rs = getAppointmentStmt.executeQuery();

			while(rs.next())
			{
				retval.add(new Appointment(rs.getInt(1), rs.getLong(2), rs.getInt(3), rs.getString(4),
					rs.getString(5), rs.getString(6)));
//				getAppointmentParticipants(rs.getInt(1));
			}
		} catch(Exception error)
		{
			System.err.println(error.getMessage());
			return null;
		}

		return retval;
	}
	
	public void editUser(){
		//TODO: Make this
		
	}

	public void createAppointment(){
		
	}
}
