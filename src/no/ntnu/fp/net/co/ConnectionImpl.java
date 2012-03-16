/*
 * Created on Oct 27, 2004
 */
package no.ntnu.fp.net.co;

import java.io.EOFException;
import java.io.IOException;
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
    private static int startPort = 10000;

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
    	// TODO: Make singleton connector   	
    	
    	KtnDatagram internalPacket = super.constructInternalPacket(KtnDatagram.Flag.SYN);
    	try {
			super.simplySendPacket(internalPacket);
			super.receivePacket(true);
			super.receiveAck();
			
			super.sendAck(internalPacket, false);
		} catch (ClException e) {
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
    	Connection clientConnection;
    	KtnDatagram packet = receivePacket(true);

	return null;
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
        KtnDatagram data = constructDataPacket(msg);
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
        throw new NotImplementedException();
    }

    /**
     * Close the connection.
     * @see Connection#close()
     */
    public void close() throws IOException {
        KtnDatagram packet = constructInternalPacket(Flag.FIN);
        try {        	
        	simplySendPacket(packet);
        	this.state = State.FIN_WAIT_1;
        }
        catch (ClException e) {
			throw new IOException("C1Exception");
		}
        KtnDatagram ack = receiveAck();
        if (ack != null) {
        	this.state = State.FIN_WAIT_2;
        	KtnDatagram receivedPacket = receivePacket(true);
        	sendAck(receivedPacket, false);
        	this.state = State.CLOSED;
        	usedPorts.remove(receivedPacket.getDest_port());
        }
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
    	if (this.nextSequenceNo != packet.getSeq_nr()) {
    		return false;
    	}
    	if (packet.getChecksum() != packet.calculateChecksum()) {
    		return false;
    	}
    	
    	// TODO: Paritycheck (ref. docs)
    	
    	byte[]payloadBytes = packet.getPayloadAsBytes();
    	
    	
    	
        this.lastValidPacketReceived = packet;
        return true;
    }
}
