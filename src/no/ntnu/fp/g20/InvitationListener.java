package no.ntnu.fp.g20;
import no.ntnu.fp.g20.model.Invitation;

/**
 * Interface for managing invitations.
 * @author Kristian Klomsten Skordal
 */
public interface InvitationListener
{
	/**
	 * Called when an invitation is confirmed.
	 * @param invitation the confirmed invitation.
	 */
	public void invitationConfirmed(Invitation invitation);

	/**
	 * Called when an invitation is rejected.
	 * @param invitation the rejected invitation.
	 */
	public void invitationRejected(Invitation invitation);
}

