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
	private CalendarModel calendarModel;
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
	}

	/**
	 * Runs the application.
	 * This is started by swing's invokeLater method.
	 */
	public void run()
	{
		boolean loggedIn = false;

		while(!loggedIn)
		{
			loginDialog.setVisible(true);
			System.out.println("Login dialog closed.");
	
			if(loginDialog.getCloseReason() == LoginDialog.CloseReason.CANCEL
				|| loginDialog.getCloseReason() == LoginDialog.CloseReason.CLOSE_BUTTON)
				System.exit(0);
			else {
				connection = ServerConnection.login(loginDialog.getUsername(), loginDialog.getPassword());
				if(connection != null)
				{
					loggedIn = true;
					break;
				} else
					JOptionPane.showMessageDialog(null, "Your username and password do not match!",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		calendarUser = connection.getLoggedInUser();

		// A very informative and polite message box:
//		JOptionPane.showMessageDialog(null, "Welcome, sir " + calendarUser.getLastName() + "!\n"
//			+ "Here is your calendar, please have fun!", "Welcome master!", JOptionPane.INFORMATION_MESSAGE);

		calendarModel = new CalendarModel(calendarUser, true);

		mainWindow = new MainFrame("Calendar Application", calendarModel);
		mainWindow.setVisible(true);
	}

	/**
	 * Returns the application's connection to the server.
	 * @return the connection to the server.
	 */
	public ServerConnection getConnection()
	{
		return connection;
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

