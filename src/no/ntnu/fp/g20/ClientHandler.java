package no.ntnu.fp.g20;

import java.util.*;
import no.ntnu.fp.g20.model.*;
import no.ntnu.fp.net.co.*;
import no.ntnu.fp.g20.database.*;

/**
 * Client handler thread.
 * @author Kristian Klomsten Skordal
 */
public class ClientHandler extends Thread
{
	private no.ntnu.fp.net.co.Connection connection;
	private Database dbConnection;
	private User connectedUser;

	/**
	 * Constructs a new client handler thread.
	 * @param connection the client connection.
	 */
	public ClientHandler(no.ntnu.fp.net.co.Connection connection, Database dbConnection)
	{
		this.connection = connection;
		connectedUser = null;
		System.out.println("Client connected.");
	}

	/**
	 * Handles a login request.
	 * @param username the username
	 * @param password the password
	 * @return a user object representing the user.
	 */
	private User handleLogin(String username, String password)
	{
		return null;
	}

	/**
	 * Runs the client handler thread.
	 */
	public void run()
	{
		try {
			// Pseudocode of operation:
			// loop
			// 	client handling;
			// until tired;
			while(true)
			{
				String data = connection.receive();
				StringTokenizer dataParser = new StringTokenizer(data);
				String commandToken = dataParser.nextToken();

				if(connectedUser == null && !commandToken.equals(CalendarProtocol.CMD_LOGIN))
					break;
				if(commandToken.equals(CalendarProtocol.CMD_LOGIN))
				{
					String username = dataParser.nextToken();
					String password = dataParser.nextToken();
					System.out.println("Received login request for user " + username
						+ " with password " + password);
				} else if(commandToken.equals(CalendarProtocol.CMD_LOGOUT))
				{
				} else if(commandToken.equals(CalendarProtocol.CMD_UPDATE))
				{
				} else if(commandToken.equals(CalendarProtocol.CMD_APPOINTMENT_ROOT))
				{
				} else if(commandToken.equals(CalendarProtocol.CMD_ROOM_ROOT))
				{
				}
			}
		} catch(Exception error)
		{
			System.err.println("An error occurred: " + error.getMessage());
		} finally {
			try {
				connection.close();
			} catch(Exception error)
			{
				// Ignore errors, as we are closing anyways.
			}
		}
	}
}

