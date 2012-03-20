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
	 * @return A positive integer if all went well, negative if an error occured.
	 */
	public static int addUser(User user){
		if (user.getFirstname() == null) {
			System.err.println("Firstname is null!");
			return -1;
		} else if (user.getLastName() == null) {
			System.err.println("Lastname is null!");
			return -1;
		} else if (user.getUsername() == null) {
			System.err.println("Username is null");
			return -1;
		} else if (user.getPassword() == null) {
			System.err.println("Password is null");
			return -1;
		}
		
		
		return addDrink(user.getFirstname(), user.getLastName(), user.getUsername(), user.getPassword());
		
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
	private static int addDrink(String firstname, String lastName, String username, String password) {
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
	 * Returns all users from the Database
	 * @author Kjetil Sletten
	 * 
	 * @return Returns an {@code ArrayList<User>} that contains all
	 * users in the database.
	 */
	public static ArrayList<User> getAllUsers(){
		ArrayList<User> users = new ArrayList<User>();
		User user;
		
		String query = "SELECT * FROM users;";
		
		try {
			ResultSet rs = Database.execute(query);
			
			while (rs.next()) {
				//Construct user object ResultSet
				user = makeUserObject(rs);
				
				//Add user to user list
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
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
