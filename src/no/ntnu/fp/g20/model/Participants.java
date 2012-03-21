// Invitation class

package no.ntnu.fp.g20.model;

import no.ntnu.fp.g20.Status;

/**
 * Class representing an invitation.
 * @author Kristian Klomsten Skordal
 */
public class Participants
{
	private int appointmentID;
	private int userID;
	private Status status;
	
	public Participants(int appointmentID, int userID, Status status) {
		this.appointmentID = appointmentID;
		this.userID = userID;
		this.status = Status.UNCONFIRMED;
	}
	
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID ) {
		this.userID = userID;
	}

	public int getAppointmentID() {
		return appointmentID;
	}

	public void setAppointmentID(int appointmentID) {
		this.appointmentID = appointmentID;
	}

	public Status getStatus(){
		return status;
	}

	public void setStatus(Status status){
		this.status = status;
	}

	public void confirm(){
		this.status = Status.CONFIRMED;
	}

	public void reject(){
		this.status = Status.REJECTED;
	}
}

