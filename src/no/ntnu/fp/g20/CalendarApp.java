// The main application class

package no.ntnu.fp.g20;

import no.ntnu.fp.g20.model.*;
import javax.swing.*;

public class CalendarApp implements Runnable
{
	private LoginDialog loginDialog;
	private ServerConnection connection;
	private Calendar calendarModel;
	private MainFrame mainWindow;
	private User calendarUser;

	public CalendarApp()
	{
		mainWindow = new MainFrame("Calendar Application");
		loginDialog = new LoginDialog(mainWindow);
		loginDialog.setModal(true);
		connection = new ServerConnection();
	}

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
				calendarUser = connection.login(loginDialog.getUsername(), loginDialog.getPassword());
				if(calendarUser != null)
					break;
			}
		}

		mainWindow.setVisible(true);
	}

	public static void main(String[] args)
	{
		System.out.println("Calendar Application - Fellesprosjektet 2012");
		System.out.println("(c) Copyright gruppe 20");

		SwingUtilities.invokeLater(new CalendarApp());
	}
}

