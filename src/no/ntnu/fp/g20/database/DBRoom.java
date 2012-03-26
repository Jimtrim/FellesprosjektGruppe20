package no.ntnu.fp.g20.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import no.ntnu.fp.g20.Status;
import no.ntnu.fp.g20.model.Room;

public class DBRoom {
	public final static String GET_ROOMS_STATEMENT = "SELECT * FROM rooms";
	
	public static Room getRoom(int id) {
		String query = "SELECT * FROM rooms WHERE id = '" + id +"'";
		Room room = null;
		
/*		try {
			ResultSet rs = Database.execute(query);
			
			if (rs.next()) {
				//Make a room object from ResultSet
				room = makeRoomObject(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} */
		
		return room;
	}
	
	public static ArrayList<Room> getAllRooms() {
		ArrayList<Room> rooms = new ArrayList<Room>();
		String query = "SELECT * FROM rooms;";
		
/*		try {
			ResultSet rs = Database.execute(query);
			
			while (rs.next()) {
				rooms.add(makeRoomObject(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} */
		return rooms;
	}
	
	private static Room makeRoomObject(ResultSet rs) {
		Room room = null;
		
/*		try {
			int room_id = rs.getInt("room_id");
			int appointment_id = rs.getInt("appointment_id");
			String roomName = rs.getString("name");
			Room.RoomStatus roomStatus = Room.RoomStatus.valueOf(rs.getString("available"));
			String description = rs.getString("description");
			int capacity = rs.getInt("capacity");
			
			//TODO: Make appropriate Constructor
			room = new Room(room_id, appointment_id, roomName, roomStatus, description, capacity);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} */
		
		return room;
	}

}
