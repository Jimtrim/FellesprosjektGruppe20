// Invitation class

package no.ntnu.fp.g20.model;

import no.ntnu.fp.g20.Status;

/**
 * Class representing an invitation.
 * @author Kristian Klomsten Skordal
 */
public class Participant extends User
{
	private Status status;
	private int appointmentID;

	public Participant(int id, int appointmentID, String username, String firstName, String lastName, Status status)
	{
		super(id, username, null, firstName, lastName);
		this.appointmentID = appointmentID;
		this.status = status;
	}

	public int getAppointmentID()
	{
		return appointmentID;
	}

	public void setAppointmentID(int appointmentID)
	{
		this.appointmentID = appointmentID;
	}

	public Status getStatus()
	{
		return status;
	}

	public void setStatus(Status status)
	{
		this.status = status;
	}

	public void confirm()
	{
		this.status = Status.CONFIRMED;
	}

	public void reject()
	{
		this.status = Status.REJECTED;
	}
}

