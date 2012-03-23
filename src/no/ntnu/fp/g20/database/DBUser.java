package no.ntnu.fp.g20.database;

import java.sql.*;
import java.util.ArrayList;

import no.ntnu.fp.g20.model.User;

/**
 * Controls all interaction with the database, consequently the User object
 * @author Kjetil Sletten
 *
 */
public class DBUser {
	/** Statement executed to get user information when logging in. */
	public final static String LOGIN_STATEMENT = "SELECT id,firstname,lastname FROM users WHERE username LIKE ? AND password LIKE ?";
	/**
	 * Selects a specified {@code User} from the database.
	 * 
	 * @author Kjetil Sletten
	 * @param id Takes a specified id from the database
	 * @return {@code User}-object
	 */
	public static User getUser(int id) {
		String query = "SELECT * FROM users WHERE id = '" + id + "'";
		User user = null;
		
//		try {
//			ResultSet rs = Database.execute(query);
//			
//			if (rs.next()) {
//				//Make a user object from ResultSet
//				user = makeUserObject(rs);
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
		return user;
	}

	/**
	 * Removes the customer from the database, returns {@code true} if removed correctly
	 * 
	 * @author Kjetil Sletten
	 * 
	 * @param id Takes an id from the database, which is specified {@code User}
	 * @return {@code boolean}
	 */
	public static boolean removeUser(int id) {
		String query = "DELETE FROM users WHERE id = '" + id + "'";
		
//		if (Database.executeUpdate(query) == 1) {
//			return true;
//		} else {
			return false;
//		}
	}
	
	/**
	 * Constructs a {@code User} object from {@code ResultSet}
	 * 
	 * @author Kjetil Sletten
	 * 
	 * @param rs {@code ResultSet} to create new {@code User} object
	 * @return Returns a {@code User} object if no errors; returns {@code null} otherwise.
	 */
	private static User makeUserObject(ResultSet rs) {
		User user = null;
		
		try {
			int id = rs.getInt("id");
			String firstname = rs.getString("firstname");
			String lastname = rs.getString("lastname");
			String username = rs.getString("username");
			String password = rs.getString("password");
			
			user = new User(id, firstname, lastname, username,password);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}

}
