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
public class MainFrame extends JFrame implements ItemListener {
	private JTable calendarTable;
	private JComboBox calendarBox;
	private AppointmentListPanel appointmentPanel;
	private CalendarPanel calendar;
	private CalendarModel model;
	private JLabel dateLabel;

	/**
	 * Constructs a new MainFrame window.
	 */
	public MainFrame(String title, CalendarModel model)
	{
		super(title);
		this.model = model;
		
		dateLabel = new JLabel("Year "+model.getYear()+", Week "+model.getWeek()+" ");
		JToolBar toolBar = new JToolBar("Main menu");
		add(toolBar, BorderLayout.NORTH);

		toolBar.add(new NewAppointmentAction());
		toolBar.add(new DeleteAppointmentAction());

		toolBar.addSeparator();

		//String[] calendarList = { "My calendar" };

		calendarBox = new JComboBox();
		Vector<User> calendarList = new Vector<User>(CalendarApp.getApplication().getConnection().getSubscriptions());
		calendarBox.addItem("My calendar");
		for(User user : calendarList)
			calendarBox.addItem(user.toString());

		calendarBox.addItemListener(this);

		toolBar.add(calendarBox);
		toolBar.add(new AddCalendarAction());
		toolBar.add(new RemoveCalendarAction());

		toolBar.addSeparator();

		toolBar.add(new LogoutAction());
		toolBar.add(new HelpAction());

		toolBar.add(Box.createHorizontalGlue());

		toolBar.add(new PrevWeekAction());
		toolBar.add(dateLabel);
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
	 * {@code ItemListener} method.
	 * @param event event.
	 */
	public void itemStateChanged(ItemEvent event)
	{
		if(calendarBox.getSelectedIndex() != 0)
		{
			JOptionPane.showMessageDialog(this, "I am sorry Dave, I cannot let you do that.",
				"HAL-9000", JOptionPane.ERROR_MESSAGE);
			calendarBox.setSelectedIndex(0);
		}
	}
	
	/**
	 * Action for creating a new appointment.
	 */
	public class NewAppointmentAction extends AbstractAction
	{
		public NewAppointmentAction()
		{
			super("New appointment");
			putValue(Action.SMALL_ICON, new ImageIcon("icons/icon_toolbar_new.png"));
		}
		
		public void actionPerformed(ActionEvent event)
		{
			NewAppointmentDialog dialog = new NewAppointmentDialog(null);
			dialog.setVisible(true);

			Appointment newAppointment = dialog.getModel();
			if(newAppointment != null);
				// Do something about it here
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
			putValue(Action.SMALL_ICON, new ImageIcon("icons/icon_toolbar_delete.png"));
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

			putValue(Action.SMALL_ICON, new ImageIcon("icons/icon_toolbar_plus.png"));
		}

		public void actionPerformed(ActionEvent event)
		{
			String username = JOptionPane.showInputDialog(null, "Username:", "Subscribe to user",
				JOptionPane.QUESTION_MESSAGE);
			User user = CalendarApp.getApplication().getConnection().userExists(username);
			if(user == null)
			{
				JOptionPane.showMessageDialog(null, "The username provided does not exist!",
					"Error", JOptionPane.ERROR_MESSAGE);
			} else {
				if(CalendarApp.getApplication().getConnection().addSubscription(user))
					calendarBox.addItem(user.toString());
				else
					JOptionPane.showMessageDialog(null,
						"A server error prevented adding the subscription.",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
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

			putValue(Action.SMALL_ICON, new ImageIcon("icons/icon_toolbar_minus.png"));
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

			putValue(Action.SMALL_ICON, new ImageIcon("icons/icon_toolbar_door.png"));
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

			putValue(Action.SMALL_ICON, new ImageIcon("icons/icon_toolbar_questionmark.png"));
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
			dateLabel.setText("Year "+model.getYear()+", Week "+model.getWeek()+" ");
			calendar.updateUI();
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
			dateLabel.setText("Year "+model.getYear()+", Week "+model.getWeek()+" ");
			calendar.updateUI();
		}
	}
	
	public CalendarModel getModel()
	{
		return model;
	}
	
	public void setModel(CalendarModel model)
	{
		this.model = model;
	}
}
