package no.ntnu.fp.g20;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Appointment {
	
	private int id;
	private Date startDate;
	private Time startTime;
	private int duration;
	private String location;
	private Room room;
	private ArrayList<User> listOfParticipants;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Time getStartTime() {
		return startTime;
	}
	
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	
	public User[] getListOfParticipants() {
		return listOfParticipants.toArray();
	}

	public void addParticipant(User participant) {
		listOfParticipants.add(participant);
	}
	
	public void removeParticipant(User participant) {
		listOfParticipants.remove(participant);
	}

}
