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
	/**
	 * Does the same as the first {@code addUser} but it doesn't need a specific name
	 * it is obtained from the {@code User} -object
	 * 
	 * @author Kjetil Sletten
	 * 
	 * @param user
	 * 			Takes a parameter user from a {@code User}
	 * @return A positive integer if all went well, negative if an error occurred.
	 */
	public static int addUser(User user){
		String fName = user.getFirstName(); 
		String lName = user.getLastName(); 
		String uName = user.getUsername();
		String pwd = user.getPassword();
		
		
		if (fName == null || !InputValidation.isAlphaNumeric(fName) ) {
			System.err.println("Firstname is invalid!");
			return -1;
		} else if (lName == null || !InputValidation.isAlphaNumeric(lName) ) {
			System.err.println("Lastname is invalid!");
			return -1;
		} else if (uName == null || !InputValidation.isAlphaNumeric(uName) ) {
			System.err.println("Username is invalid!");
			return -1;
		} else if (pwd == null || !InputValidation.isAlphaNumeric(pwd) ) {
			// TODO: Lage en hash'e-funksjon på klient-siden
			System.err.println("Password is invalid!");
			return -1;
		}
		
		
		return addUser(fName, lName, uName, pwd);
		
	}
	
	/**
	 * Adds a {@code User} to the database
	 * 
	 * @author Kjetil Sletten
	 * @param firstname The firstname of the user.
	 * @param lastName The lastname of the user.
	 * @param username The username of the user.
	 * @param password The password of the user.
	 * @return int Return value, positive for ok, or -1 for error.
	 */
	private static int addUser(String firstname, String lastName, String username, String password) {
		String query = "INSERT INTO users "
				+ "(firstname, lastname, username, password) VALUES ('" + firstname + "','"
				+ lastName + "','" + username + "','" + password + "')";
		
		return Database.executeUpdate(query, true);
		
	}
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
		
		try {
			ResultSet rs = Database.execute(query);
			
			if (rs.next()) {
				//Make a user object from ResultSet
				user = makeUserObject(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
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
		
		if (Database.executeUpdate(query) == 1) {
			return true;
		} else {
			return false;
		}
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
