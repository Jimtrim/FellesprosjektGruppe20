package no.ntnu.fp.g20;

import java.io.EOFException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.concurrent.*;

import no.ntnu.fp.net.admin.Log;
import no.ntnu.fp.net.co.Connection;
import no.ntnu.fp.net.co.ConnectionImpl;

import no.ntnu.fp.g20.database.*;

/** 
 * Class to handle requests from a client
 * 
 * @author jimtrim
 *
 */
public class ServerApp {
	/** Server port: */
	public final static int MY_PORT = 26700;
	/** List of logged in users: */
	public final static ConcurrentHashMap<String, ClientHandler> clientMap = new ConcurrentHashMap<String, ClientHandler>();

	/**
	 * Main application entry point.
	 * @param args command line arguments.
	 */
	public static void main(String[] args)
	{
		no.ntnu.fp.net.co.Connection connection = new no.ntnu.fp.net.co.ConnectionImpl(MY_PORT);

		Log serverLog = new Log();
		serverLog.setLogName("SuperCalendar Server Application");

		try {
			Database dbConnection = new Database();

			while(true)
			{
				Connection clientConnection = connection.accept();
				(new ClientHandler(clientConnection, dbConnection)).start();
			}
		} catch(EOFException error)
		{
			// Anyone knows what the second argument to writeToLog is? Log title?
			serverLog.writeToLog("EOF received, exiting.", "SuperCalendar Server");
		} catch(Exception error)
		{
			serverLog.writeToLog("An error occurred: " + error.getMessage(), "SuperCalendar Server");
		}
	}
}
