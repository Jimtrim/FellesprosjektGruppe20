package no.ntnu.fp.g20.database;

import java.sql.*;
import java.util.*;

import no.ntnu.fp.g20.model.*;

public class Database extends Connection
{
	private PreparedStatement loginStmt;
	private PreparedStatement getAppointmentsForUserStmt;
	private PreparedStatement getParticipantsStmt;
	private PreparedStatement getNumParticipantsStmt;
	private PreparedStatement getSubscriptionsStmt;
	private PreparedStatement getRoomsStmt;
	private PreparedStatement getUserStmt;
	private PreparedStatement getUserByNameStmt;
	private PreparedStatement addSubscriptionStmt;

	public Database() throws SQLException
	{
		super();
		loginStmt = getConnection().prepareStatement(DBUser.LOGIN_STATEMENT);
		getAppointmentsForUserStmt = getConnection().prepareStatement(DBAppointment.GET_APPOINTMENTS_FOR_USER_STATEMENT);
		getNumParticipantsStmt = getConnection().prepareStatement(DBAppointment.GET_NUM_PARTICIPANTS_STATEMENT);
		getParticipantsStmt = getConnection().prepareStatement(DBParticipants.GET_PARTICIPANTS_STATEMENT);
		getSubscriptionsStmt = getConnection().prepareStatement(DBSubscription.GET_SUBSCRIPTIONS_STATEMENT);
		getRoomsStmt = getConnection().prepareStatement(DBRoom.GET_ROOMS_STATEMENT);
		getUserStmt = getConnection().prepareStatement(DBUser.GET_USER_STATEMENT);
		getUserByNameStmt = getConnection().prepareStatement(DBUser.GET_USER_BY_NAME_STATEMENT);
		addSubscriptionStmt = getConnection().prepareStatement(DBSubscription.ADD_SUBSCRIPTION_STATEMENT);
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
	 * Gets all subscriptions for a specified user.
	 * @param userID the ID of the user whose subscriptions you would like.
	 * @return a linked list of User objects containing the subscriptions for the user.
	 */
	public LinkedList<User> getSubscriptions(int userID)
	{
		LinkedList<User> retval = new LinkedList<User>();

		try {
			getSubscriptionsStmt.setInt(1, userID);
			ResultSet rs = getSubscriptionsStmt.executeQuery();

			while(rs.next())
				retval.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
		} catch(Exception error)
		{
			System.err.println(error.getMessage());
			return null;
		}

		return retval;
	}

	/**
	 * Gets the participants for a specified appointment.
	 * @param apptID the ID of the appointment whose users you would like.
	 * @return an array of users who are participants of the specified appointment.
	 */
	public LinkedList<Participant> getParticipantsForAppointment(int apptID)
	{
		LinkedList<Participant> retval = new LinkedList<Participant>();

		try {
			getParticipantsStmt.setInt(1, apptID);
			ResultSet rs = getParticipantsStmt.executeQuery();
			while(rs.next())
				retval.add(new Participant(rs.getInt(1), apptID, rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getString(5)));
		} catch(Exception error)
		{
			System.out.println(error.getMessage());
			return null;
		}

		return retval;
	}

	/**
	 * Gets user information for the specified username.
	 * @param username the name of the user whose information you would like.
	 * @return a {@code User} object representing the user.
	 */
	public User getUserByName(String username)
	{
		try {
			User retval;

			getUserByNameStmt.setString(1, username);
			ResultSet rs = getUserByNameStmt.executeQuery();

			if(!rs.next())
				return null;
			else
				return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
		} catch(Exception error)
		{
			return null;
		}
	}

	/**
	 * Gets user information for the specified user ID.
	 * @param userID the ID of the user whose information you would like.
	 * @return a {@code User} object representing the user.
	 */
	public User getUserByID(int userID)
	{
		try {
			getUserStmt.setInt(1, userID);
			ResultSet rs = getUserStmt.executeQuery();

			if(!rs.next())
				return null;
			else
				return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
		} catch(Exception error)
		{
			return null;
		}
	}

	/**
	 * Gets the appointments for a specified user.
	 * @return an array of appointment objects for a specified user.
	 * @param userID the ID of the user whose appointments you would like.
	 */
	public LinkedList<Appointment> getAppointmentsForUser(int userID)
	{
		LinkedList<Appointment> retval = new LinkedList<Appointment>();

		try {
			getAppointmentsForUserStmt.setInt(1, userID);
			ResultSet rs = getAppointmentsForUserStmt.executeQuery();

			while(rs.next())
			{
				Appointment temp = new Appointment(rs.getInt(1), rs.getLong(2), rs.getInt(3),
					rs.getString(4), rs.getString(5), rs.getString(6));
				temp.setOwner(getUserByID(rs.getInt(7)));
				retval.add(temp);
			}
		} catch(Exception error)
		{
			System.err.println(error.getMessage());
			return null;
		}

		return retval;
	}

	/**
	 * Adds a subscription to a user.
	 * @param userID the ID of the user to add the subscription for.
	 * @param subsID the ID of the user to subscribe to.
	 * @return true if successful.
	 */
	public boolean addSubscription(int userID, int subsID)
	{
		try {
			addSubscriptionStmt.setInt(1, userID);
			addSubscriptionStmt.setInt(2, subsID);
			addSubscriptionStmt.executeUpdate();
			return true;
		} catch(Exception error)
		{
			System.err.println(error.getMessage());
			return false;
		}
	}

	/**
	 * Gets the list of rooms.
	 * @return a linked list of room objects.
	 */
	public LinkedList<Room> getRoomList()
	{
		LinkedList<Room> retval = new LinkedList<Room>();

		try {
			ResultSet rs = getRoomsStmt.executeQuery();
			while(rs.next())
				retval.add(new Room(rs.getInt(1), rs.getString(2), rs.getInt(3)));
		} catch(Exception error)
		{
			System.err.println(error.getMessage());
			return null;
		}

		return retval;
	}

	public void createAppointment(){
		
	}
}

