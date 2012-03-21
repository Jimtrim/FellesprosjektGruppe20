package no.ntnu.fp.g20.model;

import java.util.ArrayList;

import no.ntnu.fp.g20.Status;

public class Participants {
	private User user;
	private Appointment appointment;
	private Status status;
	private ArrayList<User> users; 
	private ArrayList<Appointment> appointments;
	
	public Participants(ArrayList<User> users, Appointment appointment, Status status){
		if (users == null) {
			this.users = new ArrayList<User>();
		} else {
			this.users = users;
		}
		
		if (status == null) {
			this.status = Status.UNCONFIRMED;
		} else {
			this.status = status;
		}
		
		this.appointment = appointment;
		
		
	}

}
