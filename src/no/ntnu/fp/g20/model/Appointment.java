package no.ntnu.fp.g20.model;

import java.sql.Date;
import java.util.ArrayList;

public class Appointment {
	
	private int id;
	private Date startTime;
	private int duration;
	private String location;
	private Room room;
	private ArrayList<User> listOfParticipants;
	
	public void addParticipant(User participant) {
		listOfParticipants.add(participant);
	}
	
	public void removeParticipant(User participant) {
		listOfParticipants.remove(participant);
	}
	public int getID() {
		return this.id;
	}
	
	public boolean equals(Object ob) {
		if (ob instanceof Appointment) {
			Appointment app = (Appointment) ob;
			if (app.getID() != this.id) {
				System.err.println("Appointment ID not the same");
				return false;
			}
			return true;
		} return false;
	}
}
