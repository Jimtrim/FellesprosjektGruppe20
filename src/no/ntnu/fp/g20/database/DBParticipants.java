package no.ntnu.fp.g20.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import no.ntnu.fp.g20.Status;
import no.ntnu.fp.g20.model.Participants;


public class DBParticipants {
	
	public static int addParticipants(int appointmentID, int userID, Status status ){
		String query = "INSERT INTO participants "
				+ "(appointment_id, user_id, status) VALUES ('" 
				+ appointmentID + "','" + userID + "','" + status + "')";
		
		return Database.executeUpdate(query, true);
		
	}
	
	public static int addParticipants(Participants p){
		return addParticipants(p.getAppointmentID(), p.getUserID(), p.getStatus());
		
	}
	
	public static Participants getParticipant(int appointmentID, int userID){
		String query = "SELECT * FROM participants WHERE appointment_id = '" + appointmentID
				+ "'" + " AND user_id = '" + userID + "'";
		Participants participant = null;
		
		try {
			ResultSet rs = Database.execute(query);
			
			if (rs.next()) {
				//Make participant object from ResultSet
				participant = makeParticipantObject(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return participant;
	}
	
	public static ArrayList<Participants> getAllParticipants() {
		ArrayList<Participants> participants = new ArrayList<Participants>();
		Participants part;
		
		String query = "SELECT * FROM participants;";
		
		try {
			ResultSet rs = Database.execute(query);
			
			while (rs.next()) {
				part = makeParticipantObject(rs);
				
				participants.add(part);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return participants;
	}
	
	public static boolean removeParticipant(int appointmentID, int userID) {
		String query = "DELETE FROM participants WHERE appointment_id = '" + appointmentID
				+ "'" + " AND user_id = '" + userID + "'";
		
		if (Database.executeUpdate(query) == 1) {
			return true;
		} else {
			return false;
		}
	}

	private static Participants makeParticipantObject(ResultSet rs) {
		Participants part = null;
		
		try {
			int appointmentID = rs.getInt("appointment_id");
			int userID = rs.getInt("user_id");
			Status status = Status.valueOf(rs.getString("status"));
			
			part = new Participants(appointmentID, userID, status);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return part;
	}
	

}
