// Client-side server communication class

package no.ntnu.fp.g20;

import no.ntnu.fp.net.co.*;

import java.net.*;
import java.util.*;
import javax.swing.*;

/**
 * Class for maintaining a connection to the server.
 */
public class ServerConnection
{
	private final static String SERVER_HOST = "localhost";
	private final static short SERVER_PORT = 26700;
	private final static short LOCAL_PORT = 32000;

	private ArrayList<ServerListener> listeners;
	private Connection serverConnection;
	private boolean connected;

	public ServerConnection()
	{
		serverConnection = new ConnectionImpl(LOCAL_PORT);
		connected = false;
	}

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

	public User login(String username, String password)
	{
		User retval;

		if(!connected)
			if(!connect())
				return null;

		if(!send("LOGIN " + username + " " + password))
			return null;

		String reply = receive();
		if(reply == null)
			return fnull;
		else {
			if(!reply.startsWith("LOGIN OK"))
				return null;
			else {
				StringTokenizer parser = new StringTokenizer(reply);
				retval = new User();

				if(!parser.nextToken().equals("LOGIN"))
					return null;
				if(!parser.nextToken().equals("OK))
					return null;

				user.setId(Integer.parseInt(parser.nextToken()));
				return user;
			}
		}
	}

	public void addServerListener(ServerListener listener)
	{
		listeners.add(listener);
	}

	public void removeServerListener(ServerListener listener)
	{
		listeners.remove(listener);
	}
}

