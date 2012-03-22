// Calendar Cell Component

package no.ntnu.fp.g20;
import no.ntnu.fp.g20.model.*;

import java.awt.*;
import javax.swing.*;
import java.util.*;

// Major TODO: Draw multi-hour appointments.

/**
 * Component used for rendering appointments in the calendar table.
 * @author Kristian Klomsten Skordal
 */
public class AppointmentWidget extends JComponent
{
	private final static Color APPOINTMENT_COLOR = new Color(255, 100, 100);
	private int hour;

	private Appointment appointment;
	private boolean selected;
	private boolean focused;

	/**
	 * Constructs a new appointment widget without an appointment.
	 */
	public AppointmentWidget()
	{
	}

	/**
	 * Sets the parameters for the widget.
	 * @param appointment the appointment to render.
	 * @param isSelected true if the cell is selected.
	 * @param isFocused true if the cell has focus.
	 * @param hour the hour of the current cell.
	 */
	public void setParameters(Appointment appointment, boolean isSelected, boolean isFocused, int hour)
	{
		this.appointment = appointment;
		selected = isSelected;
		focused = isFocused;
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
		} else {
			g.setColor(APPOINTMENT_COLOR);
			g.fillRect(0, 0, getWidth(), getHeight());
		}

		if(focused)
		{
			g.setColor(Color.GRAY);
			g.drawLine(0, 0, getWidth() - 1, 0);
			g.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight() - 1);
			g.drawLine(getWidth() - 1, getHeight() - 1, 0, getHeight() - 1);
			g.drawLine(0, getHeight() - 1, 0, 0);
		}

		if(selected)
		{
			g.setColor(Color.BLACK);
			g.drawLine(0, 0, getWidth() - 1, 0);
			g.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight() - 1);
			g.drawLine(getWidth() - 1, getHeight() - 1, 0, getHeight() - 1);
			g.drawLine(0, getHeight() - 1, 0, 0);
		}

		if(appointment == null)
		{
			g.setColor(Color.GRAY);
			g.setFont(hourFont);
			g.drawString(timeString, 0, metrics.getHeight());
		} else {
			g.setColor(Color.GRAY);
			g.setFont(hourFont);
			g.drawString(timeString, 0, metrics.getHeight());

			int titleY = metrics.getHeight() * 2;

			g.setFont(getFont());
			g.setColor(Color.BLACK);
			g.drawString(appointment.getTitle(), 0, titleY);

			titleY += metrics.getHeight();

			g.setColor(Color.GRAY);
			g.drawString(appointment.getLocation(), 0, titleY);
		}
	}
}

