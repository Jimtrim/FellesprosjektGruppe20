package no.ntnu.fp.g20;

import no.ntnu.fp.g20.model.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 * Main frame for the calendar.
 * @author Kristian Klomsten Skordal
 */
public class MainFrame extends JFrame {

	private JTable calendarTable;
	private JComboBox calendarBox;
	private AppointmentListPanel appointmentPanel;
	private CalendarPanel calendar;
	private CalendarModel model;

	
	/**
	 * Constructs a new MainFrame window.
	 */
	public MainFrame(String title, CalendarModel model)
	{
		super(title);
		this.model = model;

		JToolBar toolBar = new JToolBar("Main menu");
		add(toolBar, BorderLayout.NORTH);
		
		toolBar.add(new NewAppointmentAction());
		toolBar.add(new DeleteAppointmentAction());

		toolBar.addSeparator();

		//String[] calendarList = { "My calendar" };
		//String[] calendarList = { model.getUser().getFirstName() + " " + model.getUser().getLastName() + "'s Calendar"};
		Vector<Object> calendarList = new Vector(); // TODO: Maybe use something other than String?
		calendarList.add("My calendar");
		// TODO: Find a better way of populating this list; I made the below line just as an example :-)
		calendarList.addAll(CalendarApp.getApplication().getConnection().getSubscriptions());

		calendarBox = new JComboBox(calendarList);
		toolBar.add(calendarBox);
		toolBar.add(new AddCalendarAction());
		toolBar.add(new RemoveCalendarAction());

		toolBar.addSeparator();

		toolBar.add(new LogoutAction());
		toolBar.add(new HelpAction());

		toolBar.add(Box.createHorizontalGlue());

		toolBar.add(new PrevWeekAction());
		toolBar.add(new NextWeekAction());
		
		toolBar.setFloatable(false);
		
		calendar = new CalendarPanel(model);
		add(calendar, BorderLayout.CENTER);
		
		appointmentPanel = new AppointmentListPanel();
		add(appointmentPanel, BorderLayout.SOUTH);

		pack();
		setMinimumSize(getSize());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Action for creating a new appointment.
	 */
	public class NewAppointmentAction extends AbstractAction
	{
		public NewAppointmentAction()
		{
			super("New appointment");
		}
		
		public void actionPerformed(ActionEvent event)
		{
			getModel().appointmentAdded(appointmentPanel.getAppointmentDetails());
		}
	}

	/**
	 * Action for deleting an appointment.
	 */
	public class DeleteAppointmentAction extends AbstractAction
	{
		public DeleteAppointmentAction()
		{
			super("Delete appointment");
		}

		public void actionPerformed(ActionEvent event)
		{
			getModel().appointmentDeleted(appointmentPanel.getAppointmentDetails());	
		}
	}

	/**
	 * Action for adding a calendar to the list of calendar subscriptions.
	 */
	public class AddCalendarAction extends AbstractAction
	{
		public AddCalendarAction()
		{
			super("+");
		}

		public void actionPerformed(ActionEvent event)
		{
			
		}
	}

	/**
	 * Action for removing a calendar from the list of calendar subscriptions.
	 */
	public class RemoveCalendarAction extends AbstractAction
	{
		public RemoveCalendarAction()
		{
			super("-"); // Beware, n-dash, not hyphen.
		}

		public void actionPerformed(ActionEvent event)
		{

		}
	}

	/**
	 * Action for logging out from the system.
	 */
	public class LogoutAction extends AbstractAction
	{
		public LogoutAction()
		{
			super("Log out");
		}

		public void actionPerformed(ActionEvent event)
		{
			CalendarApp.getApplication().getConnection().logout();
			setVisible(false);
			System.exit(0);
		}
	}

	/**
	 * Action for showing the manual.
	 */
	public class HelpAction extends AbstractAction
	{
		public HelpAction()
		{
			super("Help");
		}

		public void actionPerformed(ActionEvent event)
		{

		}
	}

	/**
	 * Action for going to the next week in the calendar view.
	 */
	public class NextWeekAction extends AbstractAction
	{
		public NextWeekAction()
		{
			super("Next >>>");
		}

		public void actionPerformed(ActionEvent event)
		{
			getModel().setAppointmentsInWeek(getModel().getWeek() + 1);
		}
	}

	/**
	 * Action for going to the previous week in the calendar view.
	 */
	public class PrevWeekAction extends AbstractAction
	{
		public PrevWeekAction()
		{
			super("<<< Previous");
		}

		public void actionPerformed(ActionEvent event)
		{
			getModel().setAppointmentsInWeek(getModel().getWeek() - 1);
		}
	}
	
	public CalendarModel getModel() {
		return model;
	}
	
	public void setModel(CalendarModel model) {
		this.model = model;
	}
	
	public static void main(String[] args) {
	}
}
