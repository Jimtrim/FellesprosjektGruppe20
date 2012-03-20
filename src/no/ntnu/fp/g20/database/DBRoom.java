package no.ntnu.fp.g20.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import no.ntnu.fp.g20.Status;
import no.ntnu.fp.g20.model.Room;
import no.ntnu.fp.g20.model.Room.RoomStatus;

public class DBRoom {
	
	public static int addRoom(Room room) {
		return addRoom(room.getName(), room.getRoomStatus(), room.getDescription(), room.getCapacity());
		
	}

	public static int addRoom(String name, no.ntnu.fp.g20.model.Room.RoomStatus roomStatus, String description, int capacity) {
		String query = "INSERT INTO rooms "
				+ "(name, available, description, capacity) VALUES ('" + name + "','"
				+ roomStatus + "','" + description + "','" + capacity + "')";
		
		return Database.executeUpdate(query, true);
		
	}
	
	public static Room getRoom(int id) {
		String query = "SELECT * FROM rooms WHERE id = '" + id +"'";
		Room room = null;
		
		try {
			ResultSet rs = Database.execute(query);
			
			if (rs.next()) {
				//Make a room object from ResultSet
				room = makeRoomObject(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return room;
	}
	
	public static ArrayList<Room> getAllRooms() {
		ArrayList<Room> rooms = new ArrayList<Room>();
		String query = "SELECT * FROM rooms;";
		
		try {
			ResultSet rs = Database.execute(query);
			
			while (rs.next()) {
				rooms.add(makeRoomObject(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rooms;
	}
	
	public static int updataRoomStatus(int roomID, RoomStatus roomStatus) {
		String query = "UPDATE rooms SET available ='" + roomStatus.name() + "' WHERE id='" + roomID + "'";
		int roomId = Database.executeUpdate(query, true);
		return roomId;
	}

	private static Room makeRoomObject(ResultSet rs) {
		Room room = null;
		
		try {
			int room_id = rs.getInt("room_id");
			int appointment_id = rs.getInt("appointment_id");
			String roomName = rs.getString("name");
			Room.RoomStatus roomStatus = Room.RoomStatus.valueOf(rs.getString("available"));
			int capacity = rs.getInt("capacity");
			
			//TODO: Make appropriate Constructor
			room = new Room(room_id, appointment_id, roomName, roomStatus, capacity);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return room;
	}

}
