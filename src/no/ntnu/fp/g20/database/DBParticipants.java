package no.ntnu.fp.g20.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import no.ntnu.fp.g20.Status;
import no.ntnu.fp.g20.model.Participant;


public class DBParticipants {
	public final static String GET_PARTICIPANTS_STATEMENT = "SELECT id,username,firstname,lastname,participants.status FROM users "
		+ "JOIN participants ON users.id = participants.user_id "
		+ "WHERE participants.appointment_id = ?";
	
	public static int addParticipant(int appointmentID, int userID, Status status ){
		String query = "INSERT INTO participants "
				+ "(appointment_id, user_id, status) VALUES ('" 
				+ appointmentID + "','" + userID + "','" + status + "')";
		
//		return Database.executeUpdate(query, true);
		return 0;
		
	}
	
	public static int addParticipant(Participant p){
		return addParticipant(p.getAppointmentID(), p.getId(), p.getStatus());
		
	}
	
	public static Participant getParticipant(int appointmentID, int userID){
		String query = "SELECT * FROM participants WHERE appointment_id = '" + appointmentID
				+ "'" + " AND user_id = '" + userID + "'";
		Participant participant = null;
		
/*		try {
			ResultSet rs = Database.execute(query);
			
			if (rs.next()) {
				//Make participant object from ResultSet
				participant = makeParticipantObject(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} */
		
		return participant;
	}
	
	public static boolean removeParticipant(int appointmentID, int userID) {
		String query = "DELETE FROM participants WHERE appointment_id = '" + appointmentID
				+ "'" + " AND user_id = '" + userID + "'";
		
//		if (Database.executeUpdate(query) == 1) {
//			return true;
//		} else {
			return false;
//		}
	}

	private static Participant makeParticipantObject(ResultSet rs) {
		Participant part = null;
		
/*		try {
			int appointmentID = rs.getInt("appointment_id");
			int userID = rs.getInt("user_id");
			Status status = Status.valueOf(rs.getString("status"));
			
			part = new Participants(appointmentID, userID, status);
			
		} catch (Exception e) {
			e.printStackTrace();
		} */
		return part;
	}
	

}
