/*
 * Created on Oct 27, 2004
 */
package no.ntnu.fp.net.co;

import java.io.EOFException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import no.ntnu.fp.net.co.NotImplementedException;

import no.ntnu.fp.net.admin.Log;
import no.ntnu.fp.net.cl.ClException;
import no.ntnu.fp.net.cl.ClSocket;
import no.ntnu.fp.net.cl.KtnDatagram;
import no.ntnu.fp.net.cl.KtnDatagram.Flag;

/**
 * Implementation of the Connection-interface. <br>
 * <br>
 * This class implements the behaviour in the methods specified in the interface
 * {@link Connection} over the unreliable, connectionless network realised in
 * {@link ClSocket}. The base class, {@link AbstractConnection} implements some
 * of the functionality, leaving message passing and error handling to this
 * implementation.
 * 
 * @author Sebj�rn Birkeland and Stein Jakob Nordb�
 * @see no.ntnu.fp.net.co.Connection
 * @see no.ntnu.fp.net.cl.ClSocket
 */
public class ConnectionImpl extends AbstractConnection {

    /** Keeps track of the used ports for each server port. */
    private static Map<Integer, Boolean> usedPorts = Collections.synchronizedMap(new HashMap<Integer, Boolean>());
    private static int startPort = 49152;
    private static int maxPort = 65535;
    
    /**
     * Initialise initial sequence number and setup state machine.
     * 
     * @param myPort
     *            - the local port to associate with this connection
     */
    public ConnectionImpl(int myPort) {
		super();
		this.myPort = myPort;
		this.myAddress = getIPv4Address();
    }

    private String getIPv4Address() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        }
        catch (UnknownHostException e) {
            return "127.0.0.1";
        }
    }

    /**
     * Establish a connection to a remote location.
     * 
     * @param remoteAddress
     *            - the remote IP-address to connect to
     * @param remotePort
     *            - the remote portnumber to connect to
     * @throws IOException
     *             If there's an I/O error.
     * @throws java.net.SocketTimeoutException
     *             If timeout expires before connection is completed.
     * @see Connection#connect(InetAddress, int)
     */
    public void connect(InetAddress remoteAddress, int remotePort) throws IOException,
            SocketTimeoutException {
    	state = State.CLOSED;
    	
    	this.remoteAddress = remoteAddress.getHostAddress();
    	this.remotePort = remotePort;
    	
    	KtnDatagram internalPacket = constructInternalPacket(KtnDatagram.Flag.SYN);
    	try {
			simplySendPacket(internalPacket);

			state = State.SYN_SENT;

			KtnDatagram packet = receiveAck();
			if(packet.getFlag() != KtnDatagram.Flag.SYN_ACK ||
					packet == null) {
				throw new IOException("SYN_ACK not received!");
			}
				
			this.remotePort = packet.getSrc_port();

			sendAck(packet, false);
		        state = State.ESTABLISHED;
		} catch (ClException e){
			System.out.println(e.getMessage());
		} catch (ConnectException e) {
			System.out.println(e.getMessage());
		}
    	
    }

    /**
     * Listen for, and accept, incoming connections.
     * 
     * @return A new ConnectionImpl-object representing the new connection.
     * @see Connection#accept()
     */
    public Connection accept() throws IOException, SocketTimeoutException {
	state = State.LISTEN;
		KtnDatagram packet = null;
		
		while(packet == null) {
		    	packet = receivePacket(true);
		}
		
		if(packet.getFlag() != KtnDatagram.Flag.SYN)
		{
			System.out.println(packet.getFlag());
			throw new IOException("SYN not received in accept()");
		}
		
		int newPort=0;
		for(int i=startPort; i<=maxPort; i++) {
			if (usedPorts.containsKey(i) == false) {
				newPort = i;
				usedPorts.put(i, true);
			break;
			}
		} if (newPort==0) throw new IOException();
			
		System.out.println("new port: " + newPort);
		
		ConnectionImpl conn = new ConnectionImpl(newPort);
		conn.remoteAddress = packet.getSrc_addr();
		conn.remotePort = packet.getSrc_port();
		conn.state = State.SYN_RCVD;
		conn.sendAck(packet, true);
		KtnDatagram confirm = conn.receiveAck();
		
		if (confirm!=null) {
			conn.state = AbstractConnection.State.ESTABLISHED;
			return conn;
		} else { 
			throw new SocketTimeoutException(); 
		}
    }

    /**
     * Send a message from the application.
     * 
     * @param msg
     *            - the String to be sent.
     * @throws ConnectException
     *             If no connection exists.
     * @throws IOException
     *             If no ACK was received.
     * @see AbstractConnection#sendDataPacketWithRetransmit(KtnDatagram)
     * @see no.ntnu.fp.net.co.Connection#send(String)
     */
    public void send(String msg) throws ConnectException, IOException {
        KtnDatagram data = constructDataPacket(addParityBits(msg));
        sendDataPacketWithRetransmit(data);
    }

    /**
     * Wait for incoming data.
     * 
     * @return The received data's payload as a String.
     * @see Connection#receive()
     * @see AbstractConnection#receivePacket(boolean)
     * @see AbstractConnection#sendAck(KtnDatagram, boolean)
     */
    public String receive() throws ConnectException, IOException {
    	try {
	        KtnDatagram packet = receivePacket(false);
	       	 if (packet != null && isValid(packet)) {
			sendAck(packet, false);
			System.out.println("Packet: Valid!");
			return stripParityBits((String) packet.getPayload());
		}
	} catch(EOFException eof)
	{
		System.out.println("EOF received");
		state = AbstractConnection.State.CLOSE_WAIT;
		sendAck(disconnectRequest, false);
		throw new EOFException("Client closed the connection");
	}

	return null;
    }

    /**
     * Close the connection.
     * @see Connection#close()
     */
    public void close() throws IOException {
	KtnDatagram packet;

    	if(state == State.CLOSE_WAIT)
	{
		System.out.println("SENDING LAST FIN");
		packet = constructInternalPacket(KtnDatagram.Flag.FIN);
		try {
			simplySendPacket(packet);
			state = State.LAST_ACK;
		} catch(ClException e)
		{
			throw new IOException("A2 error");
		}

		System.out.println("WAITING FOR ACK");
		receiveAck();
	} else {
		System.out.println("CLOSING CONNECTION");
		packet = constructInternalPacket(KtnDatagram.Flag.FIN);

		try {
			simplySendPacket(packet);
			state = State.FIN_WAIT_1;
		}
		catch (ClException e) {
			System.out.println("EXCEPTION: " + e.getMessage());
			throw new IOException("C1Exception");
		}

		System.out.println("Waiting for ack");
		receiveAck();

		state = State.FIN_WAIT_2;
	//	KtnDatagram p = receivePacket(true);
		KtnDatagram p = receiveAck();
		if(p != null)
		{
			sendAck(p, false);
			usedPorts.remove(p.getDest_port());
		}
	}
	state = State.CLOSED;
    }

    /**
     * Calculates the even parity bit for a byte.
     * @param b byte to calculate the parity bit for.
     * @return the parity bit.
     */
    private byte getParityBit(byte data)
    {
        int numOnes = 0;
	for(int i = 0; i < 8; ++i)
		numOnes += data >> i;
	
	if(numOnes % 2 == 1)
		return 1;
	else
		return 0;
    }

    /**
     * Adds parity bits to a message.
     * @param data input message.
     * @return string with parity bits added.
     */
    private String addParityBits(String data) throws UnsupportedEncodingException
    {
    	return data;
    }

    /**
     * Removes parity bits from a message.
     * @param data input message with parity bits.
     * @return string with parity bits removed.
     */
    private String stripParityBits(String data)
    {
    	return data;
    }

    /**
     * Test a packet for transmission errors. This function should only called
     * with data or ACK packets in the ESTABLISHED state.
     * 
     * Validating sequence:
     * 	1: Compare sequence numbers
     * 	2: Compare checksums
     * 	3: Paritycheck
     * 
     * 
     * @param packet
     *            Packet to test.
     * @return true if packet is free of errors, false otherwise.
     */
    protected boolean isValid(KtnDatagram packet) {
    	// Check the sequence number:
    	//if(packet.getSeq_nr() != nextSequenceNo - 1)
	//	return false;
	
	// Check the checksum:
	if(packet.getChecksum() != packet.calculateChecksum())
		return false;

	// Do the parity checking:

	return true;
    }
}
