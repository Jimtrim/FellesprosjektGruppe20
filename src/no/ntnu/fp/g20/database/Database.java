package no.ntnu.fp.g20.database;

import java.sql.*;
import no.ntnu.fp.g20.*;
import no.ntnu.fp.g20.model.*;

public class Database extends Connection
{
	private final static String APPT_INSERT_STMT = "INSERT INTO appointments (start, duration, description, title, location)"
		+ "VALUES (?, ?, ?, ?, ?)";
	private final static String APPT_GET_STMT = "SELECT * FROM appointments WHERE id = ?";
	private final static String APPT_UPDATE_STMT = "UPDATE appointments SET title = ?, start = ?, duration = ?, "
		+ "description = ?, location = ?";

	private PreparedStatement insertAppointmentStatement;

	public Database()
	{
		super();
		try {
			insertAppointmentStatement = getConnection().prepareStatement(APPT_INSERT_STMT);
		} catch(Exception error)
		{
			// Ignore for now.
		}
	}

	public boolean addAppointment(Appointment appt)
	{
		return false;
	}

	/**
	 * Creates a new user and adds it to the database.
	 * @param uName username
	 * @param pwd password
	 * @param fName firstname
	 * @param lName lastname
	 */
	public void createUser(String uName, String pwd, String fName, String lName){
		boolean input = InputValidation.isUserValid(uName, pwd, fName, lName);
		
		if (input) {
//			this.currentUser = new User(uName, pwd, fName, lName);
//			this.currentUser.setId(DBUser.addUser(this.currentUser));
		} else {
			System.err.println("Something is wrong with the user input!");
		}
	}
	
	public void editUser(){
		//TODO: Make this
		
	}
	public void createRoom(String name, Room.RoomStatus roomStatus, String description, int capacity){
		boolean input = InputValidation.isRoomValid(name, description);
		
		if (input) {
//			this.currentRoom = new Room(name, roomStatus, description, capacity);
//			this.currentRoom.setId(DBRoom.addRoom(this.currentRoom));
		} else {
			System.err.println("Something is wrong with the room input");
		}
	}
	public void editRoom(){
		
	}
	public void createParticipants(){
		
	}
	
	public void editParticipants(){
		
	}
	public void createAppointment(){
		
	}
	
	/**
	 * Executes an update statement against the connected database. Statements
	 * allowed are UPDATE, INSERT and DELETE. By default this method will not return the insert ID for new.
	 * 
	 * @author Kjetil Sletten, Karl Mardoff Kittilsen
	 ** @param query
	 *            The SQL update query you want to execute. This can only be
	 *            UPDASTE, INSERT and DELETE statements
	 * @param returnInsertID
	 *            Specify that this query is an INSERT and have the method
	 *            return the unique insert ID for the row
	 * @return Returns a positive {@code integer} if there was no errors
	 *         updating the database, the positive integer may define a unique
	 *         insert id if that is specified; otherwise {@code -1}.
	 */
	public static int executeUpdate(String query, boolean returnInsertID) {
		PreparedStatement pstmt;

		int result = -1;

		// Checks whether the query is not empty
		if (query != null && !query.equals("")) {

			try {
				//New query statement
				Statement st = no.ntnu.fp.g20.database.Connection.getConnection().createStatement();

				if (returnInsertID) {
					pstmt = no.ntnu.fp.g20.database.Connection.getConnection().prepareStatement(query,
							Statement.RETURN_GENERATED_KEYS);
					// Execute query
					pstmt.executeUpdate();
					ResultSet keys = pstmt.getGeneratedKeys();

					// Set return result
					if (keys.next()) {
						result = keys.getInt(1);
					}

					// Close the connection
					keys.close();
					pstmt.close();

				} else {
					//Execute query
					st.executeUpdate(query);

					result = 1;

				}
				st.close(); 

			} catch (Exception e) {
				e.printStackTrace();

				result = -1;
			}
		} else {
			//Invalid query 
			result = -1;
		}

		return result;
	}
/**
 * Executes the given sql string.
 * 
 * @author Kjetil Sletten
 * @param query This is the SQL query you want to execute. This should be a SELECT 
 * statement. If you want to update/insert something use {@code executeUpdate}.
 * @return Returns a {@code ResultSet} with the requested data.
 * @throws SQLException
 */
	public static ResultSet execute(String query) throws SQLException {
		// Create the query statement
		Statement st = no.ntnu.fp.g20.database.Connection.getConnection().createStatement();

		// Execute the query, and return ResultSet
		ResultSet rs = st.executeQuery(query);

		return rs;
	}
	/**
	 * Executes an update statement against the database. Allowed statements
	 * are; UPDATE, INSERT and DELETE. This method does not return an insert ID.
	 * 
	 * @author Kjetil Sletten, Karl Mardoff Kittilsen
	 * 
	 * @param query The SQL query you want to execute. Only UPDATE, INSERT and DELETE
	 * 		are allowed.
	 * @return Returns {@code 1} if there was no error, a negative value
	 * 		means that an error happened.
	 */
	public static int executeUpdate(String query) {
		return Database.executeUpdate(query, false);
	}
}
