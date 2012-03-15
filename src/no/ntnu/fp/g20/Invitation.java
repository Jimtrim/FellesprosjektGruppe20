// Invitation class

package no.ntnu.fp.g20;

/**
 * Class representing an invitation.
 * @author Kristian Klomsten Skordal
 */
public class Invitation
{
	private Status status;

	public Invitation()
	{
		status = Status.UNCONFIRMED;
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

	}

	public void reject()
	{

	}
}

