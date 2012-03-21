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
	// Server-config
	final static int MY_PORT = 8181;
	
	// TODO: Implement complete server- and client-protocols
	// Constants for command-recognition
	final int USER_LOGIN = 10; // 1x = USER-command
	final int USER_LOGOUT = 19;
	final int APPOINTMENT_CREATE = 20; // 2x = APPOINTMENT-command
	final int APPOINTMENT_REMOVE = 29;
	
	
	Connection server = new ConnectionImpl(MY_PORT); 
	Connection conn;
	
	public ServerApp() {
	} // end ServerApp()
	
	/**
	 * 
	 * @param payload
	 * @return a constant int to select proper action
	 */
	private int selectAction(String payload) {
		String command = "";
		for (char c : payload.toCharArray()){
			if (c==' ') break;
			command += c;
		}
		int commandCode = Integer.parseInt(command);
		// commandCode will be compared to protocol-codes
		// It will contain all numbers before first space
		// in the payload
		
		
		
		
		return 0;
	}
	
	public static void main (String args[]){
		
	    // Create log
		Log log = new Log();
		log.setLogName("ServerApp");
		
		// server connection instance, listen on myPort
		Connection server = new ConnectionImpl(MY_PORT);
		// each new connection lives in its own instance
		Connection conn;
		try {
			conn = server.accept();
	
			try {
				while (true) {
					String msg = conn.receive();
					Log.writeToLog("Message got through to server: " + msg,
					"TestServer");
					
				
				}
			} catch (EOFException e){
				Log.writeToLog("Got close request (EOFException), closing.",
				"TestServer");
				conn.close();
			}
	
		System.out.println("SERVER TEST FINISHED");
		Log.writeToLog("TEST SERVER FINISHED","TestServer");
		}
		catch (IOException e){
			e.printStackTrace();
		}
	} // End: main()
}
