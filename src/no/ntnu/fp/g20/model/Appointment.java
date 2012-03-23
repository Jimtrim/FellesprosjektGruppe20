package no.ntnu.fp.g20.model;

import java.beans.*;
import java.text.DateFormat;
import java.util.*;

import no.ntnu.fp.g20.Status;

/**
 * Class representing an appointment.
 * @author ?
 */
public class Appointment
{	
	public String toString(){ 
		int dayOfMonth = startTime.get(java.util.Calendar.DAY_OF_MONTH);
		int month = startTime.get(java.util.Calendar.MONTH);
		int year = startTime.get(java.util.Calendar.YEAR);
		return "["+dayOfMonth+"."+month+"."+year+", "+duration+" hrs] "+title+"   ("+getLocation()+")";
	}
	
	public final static String DURATION_PROPERTY = "DurationProperty";
	public final static String START_TIME_PROPERTY = "StartTimeProperty";
	public final static String LOCATION_PROPERTY = "LocationProperty";

	private int id;
	private String title;
	private java.util.Calendar startTime;
	private int duration;
	private String location;
	private String description;
	private Room room;
	private User owner;
	private ArrayList<User> listOfParticipants;
	private Status status;
	private ArrayList<Participants> listOfInvitations;
	
	private PropertyChangeSupport pcs;

	public Appointment()
	{
		listOfParticipants = new ArrayList<User>();
		status = Status.UNCONFIRMED;
		pcs = new PropertyChangeSupport(this);
		this.startTime = java.util.Calendar.getInstance();
	}

	public Appointment(int id, long startTime, int duration, String description, String title, String location)
	{
		this();
		this.id = id;
		this.startTime.setTime(new Date(startTime));
		this.duration = duration;
		this.description = description;
		this.title = title;
		this.location = location;
		this.room = null;
	}

	public Appointment(int id, long startTime, int duration, String description, String title, Room location)
	{
		this();
		this.id = id;
		this.startTime.setTime(new Date(startTime));
		this.duration = duration;
		this.description = description;
		this.title = title;
		this.location = null;
		this.room = location;
	}

	public void addParticipant(User participant)
	{
		listOfParticipants.add(participant);
	}
	
	public void removeParticipant(User participant)
	{
		listOfParticipants.remove(participant);
	}

	public java.util.Calendar getStartTime()
	{
		return startTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getID()
	{
		return id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation()
	{
		if(room == null)
			return location;
		else
			return room.toString();
	}

	public void setLocation(String location)
	{
		this.location = location;
	}

	public void setStartTime(java.util.Calendar startTime)
	{
		this.startTime = startTime;
	}
	
	public void setStatus(Status status){
		this.status = status;
	}
	public Status getStatus(){
		return status;
	}

	public boolean equals(Object ob) {
		if (ob instanceof Appointment) {
			Appointment app = (Appointment) ob;
			if (app.getID() != this.id) {
				System.err.println("Appointment ID not the same");
				return false;
			}
			return true;
		} 
		return false;
	}

	public void invitationConfirmed(Participants invitation)
	{
		listOfInvitations.get(listOfInvitations.indexOf(invitation)).setStatus(Status.CONFIRMED);
	}

	public void invitationRejected(Participants invitation)
	{
		listOfInvitations.get(listOfInvitations.indexOf(invitation)).setStatus(Status.REJECTED);
	}

}
