package no.ntnu.fp.g20;

/**
 * Calendar protocol constants class.
 * See the {@code protocol.txt} file for more information.
 * @author Jim
 */
public class CalendarProtocol {
	/* Client/server commands: */
	public final static String CMD_LOGIN  = "LOGIN";
	public final static String CMD_LOGOUT = "LOGOUT";

	public final static String CMD_APPOINTMENT		= "APPT";
	public final static String CMD_APPOINTMENT_CREATE	= "APPT CREATE";
	public final static String CMD_APPOINTMENT_UPDATE	= "APPT UPDATE";
	public final static String CMD_APPOINTMENT_DELETE	= "APPT DELETE";

	public final static String CMD_ROOM		= "ROOM";
	public final static String CMD_ROOM_RESERVE 	= "ROOM RES";
	public final static String CMD_ROOM_UNRESERVE 	= "ROOM UNRES";

	public final static String CMD_SUBSCRIBER = "SUBS";

	public final static String CMD_INIT = "INIT";
	
	/* Status codes: */
	public final static int STATUS_LOGIN_SUCCESS = 100;
	public final static int STATUS_LOGIN_ERROR = 109;
	public final static int STATUS_LOGOUT_SUCCESS = 190;
	
	public final static int STATUS_APPOINTMENT_CREATE_SUCCESS = 200;
	public final static int STATUS_APPOINTMENT_CREATE_ERROR = 209;
	public final static int STATUS_APPOINTMENT_UPDATE_SUCCESS = 210;
	public final static int STATUS_APPOINTMENT_UPDATE_ERROR = 219;
	public final static int STATUS_APPOINTMENT_DELETE_SUCCESS = 290;
	public final static int STATUS_APPOINTMENT_DELETE_ERROR = 299;
	
	public final static int STATUS_ROOM_RESERVE_SUCCESS = 310;
	public final static int STATUS_ROOM_RESERVE_ERROR = 319;
	public final static int STATUS_ROOM_UNRESERVE_SUCCESS = 320;
	public final static int STATUS_ROOM_UNRESERVE_ERROR = 329;

	public final static int STATUS_INIT_ERROR = 929;
	public final static int STATUS_INIT_LIST = 921;
	public final static int STATUS_INIT_EOL = 922;

	public final static int STATUS_GENERAL_REQUEST_ERROR = 999;

	/**
	 * Creates a command string.
	 * @param command the command to send.
	 * @param arguments arguments to the command.
	 * @return a pretty little command string.
	 */
	public static String makeCommand(String command, Object ... arguments)
	{
		String retval = command;

		for(Object argument : arguments)
			retval += " " + argument;

		return retval;
	}
}
