package no.ntnu.fp.g20.model;

import java.util.ArrayList;

import no.ntnu.fp.g20.database.DBAppointment;
import no.ntnu.fp.g20.database.DBParticipants;
import no.ntnu.fp.g20.database.DBRoom;
import no.ntnu.fp.g20.database.DBUser;
import no.ntnu.fp.g20.database.Database;
import no.ntnu.fp.g20.database.InputValidation;
import no.ntnu.fp.g20.model.Room.RoomStatus;

/**
 * Class that holds all the {@code User}, {@code Participants}, {@code Room}
 * and {@code Appointment} after they have been retrieved from the database.
 * 
 * @author Kjetil Sletten
 *
 */

public class Model {
	private ArrayList<User> users;
	private ArrayList<Room> rooms;
	private ArrayList<Participants> participants;
	private ArrayList<Appointment> appointments;
	private User currentUser;
	private Room currentRoom;
	private Participants currentParticipant;
	private Appointment currentAppointment;
	
	/**
	 * Returns an {@code ArrayList<User>} with all the users from the database
	 * @return {@code ArrayList<User>} users
	 */
	public ArrayList<User> getUsers(){
		this.users = DBUser.getAllUsers();
		return this.users;
	}
	/**
	 * Returns an {@code ArrayList<Room>} with all the rooms from the database
	 * @return {@code ArrayList<Room>} rooms
	 */
	public ArrayList<Room> getRooms() {
		this.rooms = DBRoom.getAllRooms();
		return this.rooms;
	}
	/**
	 * Returns an {@code ArrayList<Participants>} with all the participants from the database
	 * @return {@code ArrayList<Participants>} participants
	 */
	public ArrayList<Participants> getParticipants() {
		this.participants = DBParticipants.getAllParticipants();
		return this.participants;
	}
	/**
	 * Returns an {@code ArrayList<Appointment>} with all the appointments from the database
	 * @return {@code ArrayList<Appointment>} appointments
	 */
	public ArrayList<Appointment> getAppointments(){
		this.appointments = DBAppointment.getAllAppointments();
		return this.appointments;
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
			this.currentUser = new User(uName, pwd, fName, lName);
			this.currentUser.setId(DBUser.addUser(this.currentUser));
			
		} else {
			System.err.println("Something is wrong with the user input!");
		}
	}
	
	public void editUser(){
		//TODO: Make this
		
	}
	public void createRoom(String name, RoomStatus roomStatus, String description, int capacity){
		boolean input = InputValidation.isRoomValid(name, description);
		
		if (input) {
			this.currentRoom = new Room(name, roomStatus, description, capacity);
			this.currentRoom.setId(DBRoom.addRoom(this.currentRoom));
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
	
	
	

	

}
