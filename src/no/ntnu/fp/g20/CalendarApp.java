// The main application class

package no.ntnu.fp.g20;

import no.ntnu.fp.g20.model.*;
import javax.swing.*;

/**
 * The main application class.
 * @author Kristian Klomsten Skordal
 */
public class CalendarApp implements Runnable
{
	static private CalendarApp application;

	private LoginDialog loginDialog;
	private ServerConnection connection;
	private Calendar calendarModel;
	private MainFrame mainWindow;
	private User calendarUser;

	/**
	 * Constructs a new calendar application object.
	 * Does some initialization and stuff.
	 */
	public CalendarApp()
	{
		loginDialog = new LoginDialog(mainWindow);
		loginDialog.setModal(true);
		connection = new ServerConnection();
	}

	/**
	 * Runs the application.
	 * This is started by swing's invokeLater method.
	 */
	public void run()
	{
		boolean loggedIn = false;

/*		while(!loggedIn)
		{
			loginDialog.setVisible(true);
			System.out.println("Login dialog closed.");
	
			if(loginDialog.getCloseReason() == LoginDialog.CloseReason.CANCEL
				|| loginDialog.getCloseReason() == LoginDialog.CloseReason.CLOSE_BUTTON)
				System.exit(0);
			else {
				calendarUser = connection.login(loginDialog.getUsername(), loginDialog.getPassword());
				if(calendarUser != null)
					break;
			}
		}
		*/

		calendarUser = new User(1, "skordal", "testpass", "Kristian", "Skordal");

		calendarModel = new Calendar(calendarUser);

		mainWindow = new MainFrame("Calendar Application", calendarModel);
		mainWindow.setVisible(true);
	}

	/**
	 * Returns the global calendar application object.
	 * @return the global application object.
	 */
	public static CalendarApp getApplication()
	{
		return application;
	}

	/**
	 * Returns the logged in user object.
	 * @return the logged in user's User object.
	 * @see User
	 */
	public static User getLoggedInUser()
	{
		return CalendarApp.getApplication().calendarUser;
	}

	/**
	 * Application entry point method.
	 * This function starts the calendar application.
	 * @param args command line arguments, currently ignored.
	 */
	public static void main(String[] args)
	{
		System.out.println("Calendar Application - Fellesprosjektet 2012");
		System.out.println("(c) Copyright gruppe 20");

		application = new CalendarApp();
		SwingUtilities.invokeLater(application);
	}
}

