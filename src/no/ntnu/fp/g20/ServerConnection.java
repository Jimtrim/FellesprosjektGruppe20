// Client-side server communication class

package no.ntnu.fp.g20;

import no.ntnu.fp.net.*;
import no.ntnu.fp.net.co.*;

import no.ntnu.fp.g20.model.*;

import java.net.*;
import java.util.*;
import javax.swing.*;

/**
 * Class for maintaining a connection to the server.
 * @author Kristian Klomsten Skordal
 * @see MessageListener
 */
public class ServerConnection implements MessageListener
{
	private final static String SERVER_HOST = "localhost";
	private final static short SERVER_PORT = 26700;
	private final static short LOCAL_PORT = 32000;

	private ArrayList<AppointmentListener> appointmentListeners;
	private LinkedList<Appointment> appointments;
	private LinkedList<Room> rooms;
	private LinkedList<User> subscriptions;
	private no.ntnu.fp.net.co.Connection serverConnection;
	private User loggedInUser;
	private ReceiveWorker receiveThread;
	private boolean connected;

	/**
	 * Constructs an unconnected server connection object.
	 */
	public ServerConnection()
	{
		serverConnection = new ConnectionImpl(LOCAL_PORT);
		appointments = new LinkedList<Appointment>();
		rooms = new LinkedList<Room>();
		subscriptions = new LinkedList<User>();
//		receiveThread = new ReceiveWorker(serverConnection);
		connected = false;
	}

	/**
	 * Called when a message is received from the server.
	 * @param message the message received.
	 * @see MessageListener
	 */
	public void messageReceived(String message)
	{
		System.out.println("Message received: " + message);
	}

	/**
	 * Gets the list of subscriptions from the server.
	 * @return a linked list of subscriptions.
	 */
	public LinkedList<User> getSubscriptions()
	{
		return subscriptions;
	}

	/**
	 * Gets the user object for the currently logged in user.
	 * @return the user object for the logged in user.
	 */
	public User getLoggedInUser()
	{
		return loggedInUser;
	}

	/**
	 * Gets the list of available rooms.
	 * Available, as in returns all rooms.
	 * @return the list of rooms.
	 */
	public LinkedList<Room> getRoomList()
	{
		return rooms;
	}

	/**
	 * Connects the the server using {@link SERVER_HOST} as host and {@link SERVER_PORT} as port.
	 * This function shows an error dialogue if something goes wrong.
	 * @return true if successful, false otherwise.
	 */
	private boolean connect()
	{
		try {
			serverConnection.connect(InetAddress.getByName(SERVER_HOST), SERVER_PORT);
			connected = true;
		} catch(SocketTimeoutException error)
		{
			JOptionPane.showMessageDialog(null, "Timeout when attempting to connect to server!",
				"Connection error", JOptionPane.ERROR_MESSAGE);
			return false;
		} catch(Exception error)
		{
			JOptionPane.showMessageDialog(null, "Cannot connect to server!", "Connection error", 
				JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	/**
	 * Sends a message to the server.
	 * Shows a message dialog if anything goes wrong.
	 * @return true if successful, false if not.
	 */
	private boolean send(String message)
	{
		try {
			serverConnection.send(message);
		} catch(ConnectException error)
		{
			JOptionPane.showMessageDialog(null, "Cannot send data to server; not connected!",
				"Connection error", JOptionPane.ERROR_MESSAGE);
			connected = false;
			return false;
		} catch(Exception error)
		{
			JOptionPane.showMessageDialog(null, "Error while trying to send message to server!\n"
				+ "The message that was to be sent:\n"
				+ message, "Connection error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	/**
	 * Receives a message from the server.
	 * Shows a message dialogue if anything goes wrong.
	 * @return the message received or null if anything went wrong.
	 */
	private String receive()
	{
		String message = null;
		try {
			message = serverConnection.receive();
		} catch(ConnectException error)
		{
			JOptionPane.showMessageDialog(null, "Could not receive data from server; not connected!",
				"Connection error", JOptionPane.ERROR_MESSAGE);
		} catch(Exception error)
		{
			JOptionPane.showMessageDialog(null, "Error while trying to receive message from server!",
				"Connection error", JOptionPane.ERROR_MESSAGE);
		} finally
		{
			return message;
		}
	}

	/**
	 * Receives a message from the server and splits it into an array.
	 * @return an array of the words received from the server.
	 */
	private String[] receiveAsArray()
	{
		String message = receive();
		if(message == null)
			return null;

		return message.split("\\s+");
	}

	/**
	 * Checks if a user exists.
	 * @param username the name of the user.
	 * @return the user information of the user or null if the user does not exist.
	 */
	public User userExists(String username)
	{
		if(!send(CalendarProtocol.makeCommand(CalendarProtocol.CMD_EXISTS, username)))
			return null;

		String[] reply = receiveAsArray();
		if(reply == null || reply[0].equals("" + CalendarProtocol.STATUS_USER_NOT_EXISTS))
			return null;
		else
			return new User(Integer.parseInt(reply[1]), reply[2], reply[3], reply[4]);
	}

	/**
	 * Gets the initial state information from the server.
	 * @return true if successful, false if an error occurred.
	 */
	private boolean initialize()
	{
		if(!send(CalendarProtocol.CMD_INIT))
			return false;

		String[] message = receiveAsArray();
		if(message == null)
		{
			System.out.println("Null received!");
			return false;
		}

		if(!message[0].equals("" + CalendarProtocol.STATUS_INIT_LIST) || message.length != 4)
		{
			System.out.println("message[0] is "  + message[0]);
			return false;
		}

		int numSubscriptions = Integer.parseInt(message[1]);
		int numRooms = Integer.parseInt(message[2]);
		int numAppointments = Integer.parseInt(message[3]);

		System.out.println("Receiving:");
		System.out.println("\tSubscriptions: " + numSubscriptions);
		System.out.println("\tRooms: " + numRooms);
		System.out.println("\tAppointments: " + numAppointments);

		// Receive all subscriptions:
		for(int subs = 0; subs < numSubscriptions; ++subs)
		{
			User temp;
			String[] msg = receiveAsArray();

			if(msg == null || msg.length != 5 || !msg[0].equals(CalendarProtocol.CMD_SUBSCRIBER))
				return false;
			else {
				System.out.println("Subscriber added: " + msg[3] + " " + msg[4]);
				subscriptions.add(new User(Integer.parseInt(msg[1]), msg[2], msg[3], msg[4]));
			}
		}

		// Receive all rooms:
		for(int room = 0; room < numRooms; ++room)
		{
			Room temp;
			String[] msg = receiveAsArray();

			if(msg == null || msg.length != 4 || !msg[0].equals(CalendarProtocol.CMD_ROOM))
				return false;
			else {
				System.out.println("Room added: " + msg[1] + " - " + msg[2] + " (" + msg[3] + ")");
				rooms.add(new Room(Integer.parseInt(msg[1]), msg[2], Integer.parseInt(msg[3])));
			}
		}

		// Receive all appointments:
		for(int appt = 0; appt < numAppointments; ++appt)
		{
			Appointment temp;
			String[] msg = receiveAsArray();

			if(msg == null || !msg[0].equals(CalendarProtocol.CMD_APPOINTMENT))
				return false;
			else {
				System.out.println("Appointment added: " + msg[3]);
				if(msg[6].equals("NULL"))
					appointments.add(new Appointment(Integer.parseInt(msg[1]), Long.parseLong(msg[4]),
						Integer.parseInt(msg[5]), "", msg[3], getRoomById(Integer.parseInt(msg[7]))));
				else
					appointments.add(new Appointment(Integer.parseInt(msg[1]), Long.parseLong(msg[4]),
						Integer.parseInt(msg[5]), "", msg[3], msg[6]));
			}
		}

		// Receive the EOL:
		String eol = receive(); // TODO: Maybe check if this really _is_ the EOL; but we don't really care right now.
_endOfList:

		return true;
	}

	/**
	 * Checks if a room is reserved at the specified time.
	 * @param room the room.
	 * @param time the time of the reservation.
	 * @return true if the room is reserved.
	 */
	public boolean isRoomReserved(Room room, long time)
	{
		return false;
	}

	/**
	 * Gets a room ID.
	 * @param id the room id
	 * @return the room object or {@code null} if not found.
	 */
	public Room getRoomById(int id)
	{
		for(Room room : rooms) // Inefficient, but it works.
			if(room.getId() == id)
				return room;

		return null;
	}

	/**
	 * Attempts to log a user in.
	 * @param username the name of the user logging in.
	 * @param password the password of the user logging in (in clear-text).
	 * @return a logged in ServerConnection.
	 */
	public static ServerConnection login(String username, String password)
	{
		ServerConnection retval = new ServerConnection();

		if(!retval.connect())
			return null;

		if(!retval.send(CalendarProtocol.makeCommand(CalendarProtocol.CMD_LOGIN, username, password)))
			return null;

		String reply = retval.receive();
		if(reply == null)
			return null;
		else {
			System.out.println(reply);
			if(reply.startsWith("" + CalendarProtocol.STATUS_LOGIN_SUCCESS))
			{
				StringTokenizer parser = new StringTokenizer(reply);
				parser.nextToken(); // Discard status code.

				int uid = Integer.parseInt(parser.nextToken());
				String firstName = parser.nextToken();
				String lastName = parser.nextToken();

				retval.loggedInUser = new User(uid, username, password, firstName, lastName);
				retval.initialize();
				return retval;
			 } else
				return null;
		}
	}

	/**
	 * Logs the user out and closes the connection.
	 */
	public void logout()
	{
		send(CalendarProtocol.CMD_LOGOUT);
	}

	/**
	 * Send a new appointment to the server.
	 * @param appointment the appointment to create.
	 * @return true if successful, false otherwise.
	 */
	public boolean createAppointment(Appointment appointment)
	{
		// TODO: Implement and use me.
		return false;
	}

	/**
	 * Send appointment update to server.
	 * @param appointment the appointment to update.
	 * @return true if successful, false otherwise.
	 */
	public boolean updateAppointment(Appointment appointment)
	{
		// TODO: Implement and use me.
		return false;
	}

	/**
	 * Gets all appointments for the specified week.
	 * @param week the specified week.
	 * @param year the year of the week.
	 * @return an array with all the appointments for the specified week.
	 */
	public Appointment[][] getAppointmentsForWeek(int week, int year)
	{
		Appointment[][] retval = new Appointment[7][no.ntnu.fp.g20.model.Calendar.HOURS];
		long startTime, endTime;
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		calendar.clear();
		calendar.set(java.util.Calendar.YEAR, year);
		calendar.set(java.util.Calendar.WEEK_OF_YEAR, week);
		calendar.set(java.util.Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
		
		startTime = calendar.getTimeInMillis()/1000;
		calendar.set(java.util.Calendar.WEEK_OF_YEAR, week + 1);
		endTime = calendar.getTimeInMillis()/1000;

		System.out.println("Week interval: "  + startTime + " - " + endTime);

		for(Appointment appt : appointments)
		{

			if(appt.getStartTime().getTimeInMillis() > startTime && appt.getStartTime().getTimeInMillis() < endTime)
				System.out.println(appt.getTitle());
		}

		return retval;
	}

	/**
	 * Deletes an appointment.
	 * @param appointment the appointment to delete.
	 * @return true if successful, false otherwise.
	 */
	public boolean deleteAppointment(Appointment appointment)
	{
		return send(CalendarProtocol.makeCommand(CalendarProtocol.CMD_APPOINTMENT_DELETE, appointment.getID()));
	}

	/**
	 * Attempts to reserve a room.
	 * @param room the room you wish to reserve.
	 * @param appointment the appointment you wish to reserve the room for.
	 * @return true if the room was reserved, false otherwise.
	 */
	public boolean reserveRoom(Room room, Appointment appointment)
	{
		// TODO: implement and use me.
		return false;
	}

	/**
	 * Attempts to unreserve a room.
	 * @param room the room you wish to unreserve.
	 * @param appointment the appointment you wish to unreserve the room for.
	 * @return true if the room was unreserved, false otherwise.
	 */
	public boolean unreserveRoom(Room room, Appointment appointment)
	{
		// TODO: implement and use me.
		return false;
	}

	/**
	 * Adds a subscription to a user.
	 * @param user the user to subscribe to.
	 * @return true if successful.
	 */
	public boolean addSubscription(User user)
	{
		return send(CalendarProtocol.makeCommand(CalendarProtocol.CMD_SUBSCRIBER_ADD, "" + loggedInUser.getId(),
			"" + user.getId()));
	}

	/**
	 * Removes a subscription to a user.
	 * @param user the user whom you want to remove from the subscription list.
	 * @return true if successful.
	 */
	public boolean removeSubscription(User user)
	{
		// TODO: implement and use me.
		return false;
	}
}

