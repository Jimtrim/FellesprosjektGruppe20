package no.ntnu.fp.g20.model;

import java.beans.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import no.ntnu.fp.g20.*;

/**
 * Class representing an appointment.
 * @author ?
 */
public class Appointment implements InvitationListener
{	
	public final static String DURATION_PROPERTY = "DurationProperty";
	public final static String START_TIME_PROPERTY = "StartTimeProperty";
	public final static String LOCATION_PROPERTY = "LocationProperty";

	private int id;
	private String name;
	private Calendar startTime;
	private int duration;
	private String location;
	private String description;
	private Room room;
	private ArrayList<User> listOfParticipants;
	private ArrayList<Invitation> listOfInvitations;

	private PropertyChangeSupport pcs;

	public Appointment()
	{
		listOfParticipants = new ArrayList<User>();
		listOfInvitations = new ArrayList<Invitation>();
		pcs = new PropertyChangeSupport(this);
	}

	public void addParticipant(User participant)
	{
		listOfParticipants.add(participant);
	}
	
	public void removeParticipant(User participant)
	{
		listOfParticipants.remove(participant);
	}

	public Calendar getStartTime()
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

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
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

	public void setStartTime(Calendar startTime)
	{
		this.startTime = startTime;
	}
	
	public Status getStatus()
	{
		if(listOfInvitations.isEmpty())
			return Status.CONFIRMED;

		// Just return unconfirmed:
		// TODO: Fix here :-)-<-<
		return Status.UNCONFIRMED;
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

	public void invitationConfirmed(Invitation invitation)
	{
		listOfInvitations.get(listOfInvitations.indexOf(invitation)).setStatus(Status.CONFIRMED);
	}

	public void invitationRejected(Invitation invitation)
	{
		listOfInvitations.get(listOfInvitations.indexOf(invitation)).setStatus(Status.REJECTED);
	}

	
}
