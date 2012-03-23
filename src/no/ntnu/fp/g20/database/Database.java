package no.ntnu.fp.g20.database;

import java.sql.*;
import java.util.*;

import no.ntnu.fp.g20.*;
import no.ntnu.fp.g20.model.*;

public class Database extends Connection
{
	private PreparedStatement loginStmt;
	private PreparedStatement getAppointmentStmt;
	private PreparedStatement getParticipantStmt;
	private PreparedStatement getRoomStmt;
	private PreparedStatement newAppointmentStmt;

	public Database() throws SQLException
	{
		super();
		loginStmt = getConnection().prepareStatement(DBUser.LOGIN_STATEMENT);
		getAppointmentStmt = getConnection().prepareStatement("SELECT * FROM appointments WHERE owner = ?");
		getRoomStmt = getConnection().prepareStatement("SELECT * FROM rooms WHERE room_id = ?");
		newAppointmentStmt = getConnection().prepareStatement("INSERT " +
				"INTO appointments (title, start, duration, description, location, owner)" +
				"VALUES(?, ?, ?, ?, ?, ?)");
	}

	public boolean addAppointment(Appointment appt, int userid)
	{
		try { 
			newAppointmentStmt.setString(1, appt.getTitle());
			newAppointmentStmt.setString(2, ""+appt.getStartTime().getTimeInMillis());
			newAppointmentStmt.setString(3, ""+appt.getDuration());
			newAppointmentStmt.setString(4, appt.getDescription());
			newAppointmentStmt.setString(5, appt.getLocation());
			newAppointmentStmt.setString(6, ""+userid);
			
			newAppointmentStmt.executeQuery();
			
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
		
		
		
		
	}
	// ubrukt, atm
	public Room getRoomById(int id){
		try {
			getRoomStmt.setString(1, ""+id);
			
			ResultSet rs = getAppointmentStmt.executeQuery();
			return new Room(rs.getInt("room_id"), rs.getString("name"),
						rs.getString("description"), rs.getInt("capacity"));		
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
		
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
	public ArrayList<User> getParticipantsForAppointment(int apptID)
	{
		ArrayList<User> retval = new ArrayList<User>();
		return retval;
	}

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


}
