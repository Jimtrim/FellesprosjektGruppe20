package no.ntnu.fp.g20.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import no.ntnu.fp.g20.model.Appointment;
import no.ntnu.fp.g20.model.Room;
import no.ntnu.fp.g20.model.User;

public class Database extends Connection
{
	private PreparedStatement loginStmt;
	private PreparedStatement getAppointmentStmt;
	private PreparedStatement getParticipantStmt;
	private PreparedStatement getRoomStmt;
	private PreparedStatement newAppointmentStmt;
	private PreparedStatement getAppointmentsForWeekStmt;

	public Database() throws SQLException
	{
		super();
		loginStmt = getConnection().prepareStatement(DBUser.LOGIN_STATEMENT);
		getAppointmentStmt = getConnection().prepareStatement("SELECT * FROM appointments WHERE owner = ?");
		getRoomStmt = getConnection().prepareStatement("SELECT * FROM rooms WHERE room_id = ?");
		newAppointmentStmt = getConnection().prepareStatement("INSERT " +
				"INTO appointments (title, start, duration, description, location, owner)" +
				"VALUES(?, ?, ?, ?, ?, ?)");
		getAppointmentsForWeekStmt = getConnection().prepareStatement("SELECT * FROM appointments WHERE owner = ? AND startTime BETWEEN ? AND ?");
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

	/**
	 * Gets the appointments for a specified week.
	 * @param user the user ID.
	 * @param week the week.
	 * @param year the year.
	 * @return a list of the appointments for a specified week.
	 */
	public ArrayList<Appointment> getAppointmentsForWeek(int user, int week, int year)
	{
		ArrayList<Appointment> retval = new ArrayList<Appointment>();
		Calendar calendar = Calendar.getInstance();
		long weekStart;
		long weekEnd;

		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.WEEK_OF_YEAR, week);
		calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());

		weekStart = calendar.getTimeInMillis();

		calendar.set(Calendar.WEEK_OF_YEAR, week + 1);
		weekEnd = calendar.getTimeInMillis();

		System.out.println("Requesting appointments for time interval: " + weekStart + " - " + weekEnd);

		try {
			getAppointmentsForWeekStmt.setInt(1, user);
			getAppointmentsForWeekStmt.setLong(2, weekStart);
			getAppointmentsForWeekStmt.setLong(3, weekStart);
			ResultSet rs = getAppointmentsForWeekStmt.executeQuery();
			while(rs.next())
			{
				retval.add(new Appointment(rs.getInt(1), rs.getLong(2), rs.getInt(3), rs.getString(4),
					rs.getString(5), rs.getString(6)));
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
