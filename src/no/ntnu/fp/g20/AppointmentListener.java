package no.ntnu.fp.g20;
import no.ntnu.fp.g20.model.Appointment;

/**
 * Interface for listening to changes in appointments.
 * @author Kristian Klomsten Skordal
 */
public interface AppointmentListener
{
	/**
	 * Called when an appointment is added.
	 * @param appointment the appointment that was added.
	 */
	public void appointmentAdded(Appointment appointment);

	/**
	 * Called when an appointment is updated.
	 * @param appointment the appointment that was updated.
	 */
	public void appointmentUpdated(Appointment appointment);

	/**
	 * Called when an appointment is deleted.
	 * @param appointment the appointment that was deleted.
	 */
	public void appointmentDeleted(Appointment appointment);

}
