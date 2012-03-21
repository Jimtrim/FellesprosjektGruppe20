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

	public ClientHandler(Connection connection)
	{
		this.connection = connection;
		connectedUser = null;
		System.out.println("Client connected.");
	}
}

