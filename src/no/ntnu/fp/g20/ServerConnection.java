// Client-side server communication class

package no.ntnu.fp.g20;

import no.ntnu.fp.g20.model.*;
import no.ntnu.fp.net.co.*;

import java.net.*;
import java.util.*;
import javax.swing.*;

/**
 * Class for maintaining a connection to the server.
 * @author Kristian Klomsten Skordal
 */
public class ServerConnection
{
	private final static String SERVER_HOST = "localhost";
	private final static short SERVER_PORT = 26700;
	private final static short LOCAL_PORT = 32000;

	private ArrayList<ServerListener> listeners;
	private no.ntnu.fp.net.co.Connection serverConnection;
	private boolean connected;

	/**
	 * Constructs an unconnected server connection object.
	 */
	public ServerConnection()
	{
		serverConnection = new ConnectionImpl(LOCAL_PORT);
		connected = false;
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
	 * Attempts to log a user in.
	 * @return the user object corresponding to the logged in user or null if an error occurred.
	 */
	public User login(String username, String password)
	{
		if(!connected)
			if(!connect())
				return null;

		if(!send(CalendarProtocol.CMD_LOGIN + " " + username + " " + password))
			return null;

		String reply = receive();
		if(reply == null)
			return null;
		else {
			if(reply.startsWith("" + CalendarProtocol.STATUS_LOGIN_SUCCESS))
			{
				StringTokenizer parser = new StringTokenizer(reply);
				parser.nextToken(); // Discard status code.

				int uid = Integer.parseInt(parser.nextToken());
				String firstName = parser.nextToken();
				String lastName = parser.nextToken();

				return new User(uid, username, password, firstName, lastName);
			 } else
				return null;
		}
	}

	/**
	 * Sends an update request to the server.
	 */
	public void requestUpdate()
	{

	}

	/**
	 * Send a new appointment to the server.
	 * @param appointment the appointment to create.
	 * @return true if successful, false otherwise.
	 */
	public boolean createAppointment(Appointment appointment)
	{
		// TODO: Implement me.
		return false;
	}

	/**
	 * Send appointment update to server.
	 * @param appointment the appointment to update.
	 * @return true if successful, false otherwise.
	 */
	public boolean updateAppointment(Appointment appointment)
	{
		// TODO: Implement me.
		return false;
	}

	/**
	 * Deletes an appointment.
	 * @param appointment the appointment to delete.
	 * @return true if successful, false otherwise.
	 */
	public boolean deleteAppointment(Appointment appointment)
	{
		// TODO: Implement me.
		return false;
	}

	/**
	 * Adds a listener to this server connection object.
	 * @param listener the listener to add.
	 */
	public void addServerListener(ServerListener listener)
	{
		listeners.add(listener);
	}

	/**
	 * Removes a listener from this server connection object.
	 * @param listener the listener to remove.
	 */
	public void removeServerListener(ServerListener listener)
	{
		listeners.remove(listener);
	}
}

