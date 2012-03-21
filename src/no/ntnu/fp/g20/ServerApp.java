package no.ntnu.fp.g20;

import java.io.EOFException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

import no.ntnu.fp.net.admin.Log;
import no.ntnu.fp.net.co.Connection;
import no.ntnu.fp.net.co.ConnectionImpl;

/** 
 * Class to handle requests from a client
 * 
 * @author jimtrim
 *
 */
public class ServerApp {
	/** Server port: */
	public final static int MY_PORT = 26700;
	
	/**
	 * ?
	 * @param payload
	 * @return a constant int to select proper action
	 */
	private int selectAction(String payload) {
		String command = "";
		for (char c : payload.toCharArray()){
			if (c==' ') break;
			command += c;
		}
		// start comparisons
		
		
		return 0;
	}

	/**
	 * Main application entry point.
	 * @param args command line arguments.
	 */
	public static void main(String[] args)
	{
		Connection connection = new ConnectionImpl(MY_PORT);

		Log serverLog = new Log();
		serverLog.setLogName("SuperCalendar Server Application");

		try {
			while(true)
			{
				Connection clientConnection = connection.accept();
				(new ClientHandler(clientConnection)).start();
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
