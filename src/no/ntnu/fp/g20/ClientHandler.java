package no.ntnu.fp.g20;

import java.util.*;
import no.ntnu.fp.g20.model.*;
import no.ntnu.fp.net.co.*;

/**
 * Client handler thread.
 * @author Kristian Klomsten Skordal
 */
public class ClientHandler extends Thread
{
	private Connection connection;
	private User connectedUser;

	/**
	 * Constructs a new client handler thread.
	 * @param connection the client connection.
	 */
	public ClientHandler(Connection connection)
	{
		this.connection = connection;
		connectedUser = null;
		System.out.println("Client connected.");
	}

	/**
	 * Runs the client handler thread.
	 */
	public void run()
	{
		try {
			// TODO: See pseudocode below :-)
			// loop
			// 	client handling;
			// until tired;
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

