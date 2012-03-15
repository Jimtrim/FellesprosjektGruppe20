package no.ntnu.fp.g20;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Main frame for the calendar.
 * @author Kristian Klomsten Skordal
 */
public class MainFrame extends JFrame {

	private JTable calendarTable;
	private JComboBox calendarBox;
	private AppointmentPanel appointmentList;
	
	/**
	 * Constructs a new MainFrame window.
	 */
	public MainFrame(String title) {
		super(title);

		JToolBar toolBar = new JToolBar("Main menu");
		add(toolBar, BorderLayout.PAGE_START);
		
		toolBar.add(new NewAppointmentAction());
		toolBar.add(new DeleteAppointmentAction());

		toolBar.addSeparator();

		String[] calendarList = { "My calendar" };
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

		calendarTable = new JTable(12, 7);
		add(calendarTable, BorderLayout.CENTER);

		appointmentList = new AppointmentPanel();
		add(appointmentList, BorderLayout.SOUTH);

		pack();
	}
	
	public class NewAppointmentAction extends AbstractAction
	{
		public NewAppointmentAction()
		{
			super("New appointment");
		}
		
		public void actionPerformed(ActionEvent event)
		{
			
		}
	}

	public class DeleteAppointmentAction extends AbstractAction
	{
		public DeleteAppointmentAction()
		{
			super("Delete appointment");
		}

		public void actionPerformed(ActionEvent event)
		{

		}
	}

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

	public class RemoveCalendarAction extends AbstractAction
	{
		public RemoveCalendarAction()
		{
			super("–"); // Beware, endash, not hyphen.
		}

		public void actionPerformed(ActionEvent event)
		{

		}
	}

	public class LogoutAction extends AbstractAction
	{
		public LogoutAction()
		{
			super("Log out");
		}

		public void actionPerformed(ActionEvent event)
		{

		}
	}

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

	public class NextWeekAction extends AbstractAction
	{
		public NextWeekAction()
		{
			super("Next >>>");
		}

		public void actionPerformed(ActionEvent event)
		{

		}
	}

	public class PrevWeekAction extends AbstractAction
	{
		public PrevWeekAction()
		{
			super("<<< Previous");
		}

		public void actionPerformed(ActionEvent event)
		{

		}
	}

	public static void main(String[] args) {
		JFrame frame = new MainFrame("SuperCalendar");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);       
	}
}
