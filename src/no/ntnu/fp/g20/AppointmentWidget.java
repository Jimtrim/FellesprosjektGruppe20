// Calendar Cell Component

package no.ntnu.fp.g20;
import no.ntnu.fp.g20.model.*;

import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 * Component used for rendering appointments in the calendar table.
 * @author Kristian Klomsten Skordal
 */
public class AppointmentWidget extends JComponent
{
	private Appointment appointment;
	private final static Color APPOINTMENT_COLOR = new Color(255, 100, 100);
	private int hour;

	/**
	 * Constructs a new appointment widget for the specified appointment.
	 */
	public AppointmentWidget(Appointment appointment)
	{
		this.appointment = appointment;
		this.hour = appointment.getStartTime().get(java.util.Calendar.HOUR_OF_DAY);
	}

	/**
	 * Constructs a new appointment widget without an appointment.
	 */
	public AppointmentWidget(int hour)
	{
		this.hour = hour;
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
	public void paint(Graphics graphics)
	{
		Graphics2D g = (Graphics2D) graphics;
		Font hourFont = getFont().deriveFont(8.0f);
		FontMetrics metrics = g.getFontMetrics(hourFont);
		String timeString = String.format("%1$2d:00", no.ntnu.fp.g20.model.Calendar.START_HOUR + hour);

		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
			RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		if(appointment == null)
		{
			g.setColor(getBackground());
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.GRAY);
			g.setFont(hourFont);
			g.drawString(timeString, 0, metrics.getHeight());
		} else {
			g.setColor(APPOINTMENT_COLOR);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.GRAY);
			g.setFont(hourFont);
			g.drawString(timeString, 0, metrics.getHeight());

			int titleY = metrics.getHeight() * 2;

			g.setFont(getFont());
			g.setColor(Color.BLACK);
		}
	}
}

