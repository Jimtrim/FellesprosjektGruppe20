package no.ntnu.fp.g20.model;

import no.ntnu.fp.g20.*;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.*;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

/**
 * Calendar model class.
 * @author Kristian Klomsten Skordal
 */
public class CalendarModel extends AbstractTableModel
	implements PropertyChangeListener, TableCellRenderer, ListSelectionListener, AppointmentListener
{
	public final static int HOURS = 12;
	public final static int START_HOUR = 7;

	private PropertyChangeSupport pcs;
	private Appointment[][] appointments;
	private AppointmentWidget appointmentWidget;
	private User user;
	private boolean editable;
	private int week;
	private int year;

	/**
	 * Constructs a new calendar model class.
	 * @param user the user the calendar belongs to.
	 */
	public CalendarModel(User user, boolean editable)
	{
		pcs = new PropertyChangeSupport(this);
		appointments = new Appointment[7][HOURS];
		this.user = user;
		this.editable = editable;
		this.week = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);

		setAppointmentsInWeek(week);

		appointments[4][3] = new Appointment(2, 258342L, 4, "Test appointment for fun", "Test appointment",
			"The funroom");

		appointmentWidget = new AppointmentWidget(editable);
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
	 * Gets the user object for the current calendar.
	 * @return the user this calendar belongs to.
	 */
	public User getUser()
	{
		return user;
	}

	/**
	 * Listens for a selected row.
	 * @param event the selection event
	 */
	public void valueChanged(ListSelectionEvent event)
	{
		if(!event.getValueIsAdjusting())
			System.out.println(((ListSelectionModel) event.getSource()).getAnchorSelectionIndex());
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
		appointmentWidget.setParameters(appointments[column][row], isSelected, hasFocus, row);
		return appointmentWidget;
	}

	public void appointmentAdded(Appointment appointment) {
		
		CalendarApp.getApplication().getConnection().createAppointment(appointment);
		
		
	}

	public void appointmentUpdated(Appointment appointment) {
		CalendarApp.getApplication().getConnection().updateAppointment(appointment);
		
	}

	public void appointmentDeleted(Appointment appointment) {
		CalendarApp.getApplication().getConnection().deleteAppointment(appointment);
		
	}
	
	public Appointment[][] getAppointmentsInWeek() {
		return appointments;
	}
	
	public void setAppointmentsInWeek(int week) {
		appointments = CalendarApp.getApplication().getConnection().getAppointmentsForWeek(week, 2012);
//		appointments = new Appointment[7][HOURS];
		if (week < 1) {
			week = 52;
			setYear(year-1);
		}
		else if (week > 52) {
			week = 1;
			setYear(year+1);
		}
		
		setWeek(week);
	}
	
	public int getWeek() {
		return this.week;
	}
	
	public void setWeek(int week) {
		this.week = week;
	}
	public int getYear() {
		return this.year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
}

