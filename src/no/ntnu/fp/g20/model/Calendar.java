package no.ntnu.fp.g20.model;

import no.ntnu.fp.g20.*;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

/**
 * Calendar model class.
 * @author Kristian Klomsten Skordal
 */
public class Calendar extends AbstractTableModel
	implements PropertyChangeListener, TableCellRenderer
{
	public final static int HOURS = 12;
	public final static int START_HOUR = 7;

	private PropertyChangeSupport pcs;
	private Appointment[][] appointments;
	private User user;

	/**
	 * Constructs a new calendar model class.
	 * @param user the user the calendar belongs to.
	 */
	public Calendar(User user)
	{
		pcs = new PropertyChangeSupport(this);
		appointments = new Appointment[7][HOURS];
		this.user = user;

		appointments[4][4] = new Appointment();
		appointments[4][4].setName("Test appointment");
		appointments[4][4].setLocation("Funroom");
	
		// Create a start time:
		java.util.Calendar startTime = java.util.Calendar.getInstance();
		startTime.set(2012, 3, 19, 4, 0);

		appointments[4][4].setStartTime(startTime);
	}

	/**
	 * Property change listener that listens for changes to appointments.
	 * @param event the property change event.
	 */
	public void propertyChange(PropertyChangeEvent event)
	{
		if(event.getSource() instanceof Appointment)
		{
			if(event.getPropertyName() == Appointment.START_TIME_PROPERTY)
			{
				int dayIndex, newDayIndex;
				int hourIndex, newHourIndex;

				dayIndex = ((java.util.Calendar) event.getOldValue()).get(java.util.Calendar.DAY_OF_WEEK);
				hourIndex = ((java.util.Calendar) event.getOldValue()).get(java.util.Calendar.HOUR_OF_DAY);

				newDayIndex = ((java.util.Calendar) event.getNewValue()).get(java.util.Calendar.DAY_OF_WEEK);
				newHourIndex = ((java.util.Calendar) event.getNewValue()).get(java.util.Calendar.HOUR_OF_DAY);

				appointments[dayIndex][hourIndex] = null;
				appointments[newDayIndex][newHourIndex] = (Appointment) event.getSource();

				System.out.println("Appointment time changed from " + dayIndex + ":" + hourIndex
					+ " to " + newDayIndex + ":" + newHourIndex);
			}

			if(event.getPropertyName() == Appointment.DURATION_PROPERTY)
			{

			}
		}
	}

	/**
	 * Adds a new property change listener.
	 * @param listener the listener to add.
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener)
	{
		pcs.addPropertyChangeListener(listener);
	}

	/**
	 * Gets the number of columns in the table.
	 * @return 7, the number of days in a week.
	 */
	public int getColumnCount()
	{
		return 7;
	}

	/**
	 * Gets the names of the columns in the table.
	 * @return the name of the day specified (zero based).
	 */
	@Override public String getColumnName(int day)
	{
		String[] days = {
			"Monday",
			"Tuesday",
			"Wednesday",
			"Thursday",
			"Friday",
			"Saturday",
			"Sunday"
		};

		return days[day];
	}

	/**
	 * Gets the number of rows in the table.
	 * This may be changed when it is decided how many hours to show.
	 * @return the number of rows in the table.
	 */
	public int getRowCount()
	{
		return HOURS;
	}

	/**
	 * Gets the object used for representing the contents of a cell.
	 * @param row the row to retrieve the contents of.
	 * @param col the column to retrieve the contents of.
	 * @return the contents of the specified cell.
	 */
	public Object getValueAt(int row, int column)
	{
		return appointments[column][row];
	}

	/**
	 * Returns the component used for rendering cells in the table.
	 * @param table the table associated with the renderer.
	 * @param value value to render.
	 * @param isSelected true if the cell is selected.
	 * @param hasFocus true if the cell is focused.
	 * @param row the row the component belongs to.
	 * @param column the column the component belongs to.
	 * @return the component to be used for rendering.
	 */
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
		boolean hasFocus, int row, int column)
	{
		if(appointments[column][row] == null)
			return new AppointmentWidget(row);
		else
			return new AppointmentWidget(appointments[column][row]);
	}
}

