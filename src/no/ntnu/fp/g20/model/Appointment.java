package no.ntnu.fp.g20.model;

import java.beans.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Class representing an appointment.
 * @author ?
 */
public class Appointment
{	
	public final static String DURATION_PROPERTY = "DurationProperty";
	public final static String START_TIME_PROPERTY = "StartTimeProperty";
	public final static String LOCATION_PROPERTY = "LocationProperty";

	private int id;
	private Calendar startTime;
	private int duration;
	private String location;
	private Room room;
	private ArrayList<User> listOfParticipants;

	private PropertyChangeSupport pcs;

	public Appointment()
	{
		listOfParticipants = new ArrayList<User>();
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
		} 
		return false;
	}
}
