// Calendar Cell Component

package no.ntnu.fp.g20;
import no.ntnu.fp.g20.model.*;

import java.awt.*;
import javax.swing.*;

/**
 * Component used for rendering appointments in the calendar table.
 * @author Kristian Klomsten Skordal
 */
public class AppointmentWidget extends JComponent
{
	private Appointment appointment;

	/**
	 * Constructs a new appointment widget for the specified appointment.
	 */
	public AppointmentWidget(Appointment appointment)
	{
		this.appointment = appointment;
	}

	/**
	 * Update function, calls {@link paint}.
	 * @param g graphics context.
	 */
	public void update(Graphics g)
	{
		paint(g);
	}

	/**
	 * Paint function, paints the widget.
	 * @param g graphics context.
	 */
	public void paint(Graphics g)
	{
		
		g.setColor(new Color(255, 100, 100));
		g.fillRect(0, 0, getWidth(), getHeight());
	}
}

