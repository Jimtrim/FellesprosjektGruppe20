package no.ntnu.fp.g20.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import no.ntnu.fp.g20.model.Appointment;
import no.ntnu.fp.g20.model.Room;

public class DBAppointment {
	
	public static int addAppointment(long start, int duration, String description, String title, Room room){
		String query = "INSERT INTO appointments "
				+ "(start, duration, description, title, place) VALUES "
				+ "('" + start + "','" + duration + "','" + description 
				+ title + "','" + room.getName() + "')";
		
		
		return Database.executeUpdate(query, true);
	}
	
	public static int addAppointment(Appointment a){
		//TODO: FIX ME
//		return addAppointment(a.getDescription(), a.getName(), a.getLocation(), a.getStartTime(), a.getDuration());
		return 0;
	}
	
	public static Appointment getAppointment(int id) {
		String query = "SELECT * FROM appointments WHERE id = '" + id + "'";
		Appointment app = null;
		
		try {
			ResultSet rs = Database.execute(query);
			if (rs.next()) {
				//Make appointment object from ResultSet
				app = makeAppointmentObject(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return app;
	}
	
	public static ArrayList<Appointment> getAllAppointments() {
		ArrayList<Appointment> appointments = new ArrayList<Appointment>();
		
		String query = "SELECT * FROM appointments";
		
		ResultSet rs;
		
		try {
			rs = Database.execute(query);
			
			while (rs.next()) {
				appointments.add(makeAppointmentObject(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return appointments;
	}
	
	public static boolean removeAppointment(int id) {
		String query = "DELETE FROM appointments WHERE id = '" + id + "'";
		
		if (Database.executeUpdate(query) == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	//TODO: Make appropriate Appointment
	public static boolean editAppointment(Appointment a) {
		String query = "UPDATE appointments SET title = '" + a.getTitle() + "', start = '"
				+ a.getStartTime() + "', duration = '" + a.getDuration() + "', description = '"
				+ a.getDescription() + "', place = '" + a.getLocation() + "'"
				+ "WHERE id = '" + a.getID() + "'";
		
		if (Database.executeUpdate(query) == 1) {
			return true;
		} else {
			return false;
		}
	}

	private static Appointment makeAppointmentObject(ResultSet rs) {
		Appointment app = null;
		
		try {
			int id = rs.getInt("id");
			long timestamp = rs.getLong("start");
			int duration = rs.getInt("duration");
			String description = rs.getString("description");
			String title = rs.getString("title");
			String place = rs.getString("place");
			int roomId = rs.getInt("room_id"); // TODO: Update the database to support!
			
			if(place != null)
				app = new Appointment(id, timestamp, duration, description, title, place);
			else
				app = new Appointment(id, timestamp, duration, description, title, DBRoom.getRoom(roomId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return app;
	}

}
