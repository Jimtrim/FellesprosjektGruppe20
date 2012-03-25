package no.ntnu.fp.g20;

import java.util.*;
import no.ntnu.fp.g20.model.*;
import no.ntnu.fp.net.*;
import no.ntnu.fp.net.co.*;
import no.ntnu.fp.g20.database.*;

/**
 * Client handler thread.
 * @author Kristian Klomsten Skordal
 */
public class ClientHandler extends ReceiveWorker implements MessageListener
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
		super(connection);
		this.connection = connection;
		this.dbConnection = dbConnection;
		connectedUser = null;
		System.out.println("Client connected.");

		addMessageListener(this);
	}

	/**
	 * Sends a message.
	 * @param message the message to send.
	 * @return true if the message was successfully sent.
	 */
	public boolean send(String message)
	{
		try {
			connection.send(message);
		} catch(Exception error)
		{
			return false;
		}

		return true;
	}

	/**
	 * Handles an incoming message.
	 * @param message the message.
	 */
	public void messageReceived(String message)
	{
		System.out.println("###***### Received message: " + message + " ###***###");
		if(message == null)
		{
			try {
				connection.close();
				if(connectedUser != null)
				{
					ServerApp.clientMap.remove(connectedUser);
					connectedUser = null;
				}
			} catch(Exception error)
			{
				// Ignore, we are closing anyways!
			}
		}

		StringTokenizer dataParser = new StringTokenizer(message);
		String command = dataParser.nextToken();

		if(command.startsWith(CalendarProtocol.CMD_LOGIN))			// Handle LOGIN
		{
			String username = dataParser.nextToken();
			String password = dataParser.nextToken();

			handleLogin(username, password);
		} else if(command.startsWith(CalendarProtocol.CMD_LOGOUT))		// Handle LOGOUT
		{
			handleLogout();
		} else if(command.startsWith(CalendarProtocol.CMD_INIT))		// Handle INIT
		{
			handleInit();
		} if(command.startsWith(CalendarProtocol.CMD_APPOINTMENT_CREATE))	// Handle APPT CREATE
		{
			String name 		= dataParser.nextToken();
			String description 	= dataParser.nextToken();
			long startTime 		= Long.parseLong(dataParser.nextToken());
			int roomId 			= Integer.parseInt(dataParser.nextToken());
			String location		= dataParser.nextToken();
			int duration 		= Integer.parseInt(dataParser.nextToken());
			ArrayList<String> participants = new ArrayList<String>();
			
			while (dataParser.hasMoreElements()){
				participants.add(dataParser.nextToken());
			}
			
			if (roomId != 0) {
				location = "null";
			}
			
//			dbConnection.addAppointment(new Appointment(0, 
//						startTime, 
//						duration, 
//						description, 
//						name,
//						location), connectedUser.getId());
			
		} // else if(command.startsWith(CalendarProtocol.CMD_UPDATE))
//		{
//			if(!dataParser.hasMoreElements()) // General update request
//			{
//				send(CalendarProtocol.makeCommand("" + CalendarProtocol.STATUS_GENERAL_REQUEST_ERROR, "Invalid command"));
//			} else { // Initial update request
//				ArrayList<Appointment> appointments = dbConnection.getAppointmentsForUser(connectedUser.getId());
//				for(Appointment a : appointments)
//				{
//					send(CalendarProtocol.makeCommand(CalendarProtocol.CMD_APPOINTMENT_ROOT,
//						"" + a.getID(), a.getTitle(), a.getDescription(),
//						"" + a.getStartTime().getTimeInMillis(), "" + a.getDuration()));
//				}
//			}
//		}
	}

	/**
	 * Handles a login request.
	 * @param username the username
	 * @param password the password
	 * @return a user object representing the user.
	 */
	private void handleLogin(String username, String password)
	{
		connectedUser = dbConnection.loginUser(username, password);
		if(connectedUser == null)
			send(CalendarProtocol.STATUS_LOGIN_ERROR + " invalid credentials");
		else {
			ServerApp.clientMap.put(username, this);
			send(CalendarProtocol.STATUS_LOGIN_SUCCESS + " " + connectedUser.getId() +
				" " + connectedUser.getFirstName() + " " + connectedUser.getLastName());
		}
	}

	/**
	 * Handles a logout request.
	 */
	private void handleLogout()
	{
		if(connectedUser != null)
		{
			ServerApp.clientMap.remove(connectedUser);
			connectedUser = null;
		}
	}

	/**
	 * Handles a request for initial data.
	 */
	private void handleInit()
	{
		LinkedList<User> subscriptions = dbConnection.getSubscriptions(connectedUser.getId());
		LinkedList<Room> rooms = new LinkedList<Room>(); // Temporary
		LinkedList<Appointment> appointments = new LinkedList<Appointment>(); // Temporary

		send("" + CalendarProtocol.STATUS_INIT_LIST + " " + subscriptions.size() + " " +
			rooms.size() + " " + appointments.size());

		for(User sub : subscriptions)
			send(CalendarProtocol.makeCommand(CalendarProtocol.CMD_SUBSCRIBER, sub.getId(), sub.getUsername(),
				sub.getFirstName(), sub.getLastName()));
		for(Room room : rooms)
			send(CalendarProtocol.makeCommand(CalendarProtocol.CMD_ROOM, room.getName(), room.getDescription(),
				room.getCapacity()));
		for(Appointment appt : appointments)
			send(CalendarProtocol.makeCommand(CalendarProtocol.CMD_APPOINTMENT, appt.getID(), appt.getTitle(),
				appt.getDescription(), appt.getStartTime().getTimeInMillis(), appt.getDuration(),
				appt.getRoom() == null ? appt.getLocation() : "NULL",
				appt.getRoom() == null ? "NULL" : appt.getRoom()));
		send("" + CalendarProtocol.STATUS_INIT_EOL + " End of list");
	}
}

