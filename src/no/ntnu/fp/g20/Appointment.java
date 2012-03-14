package no.ntnu.fp.g20;

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

}
