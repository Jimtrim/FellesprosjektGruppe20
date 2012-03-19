// ServerListener interface

package no.ntnu.fp.g20;

import no.ntnu.fp.g20.model.Appointment;

/**
 * Interface for listening to server events.
 * @author Kristian Klomsten Skordal
 */
public interface ServerListener
{
	/**
	 * Called when an appointment is added.
	 * @return appointment details for the added appointment.
	 */
	public Appointment appointmentAdded();
	/**
	 * Called when an appointment is updated.
	 * @return appointment details for the updated appointment.
	 */
	public Appointment appointmentUpdated();
	/**
	 * Called when an appointment is deleted.
	 * @return appointment details for the deleted appointment.
	 */
	public Appointment appointmentDeleted();

	/**
	 * Called when an invitation is confirmed.
	 * @return the confirmed invitation.
	 */
	public Invitation invitationConfirmed();
	/**
	 * Called when an invitation is rejected.
	 * @return the rejected invitation.
	 */
	public Invitation invitationRejected();
}

