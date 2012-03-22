package no.ntnu.fp.g20;

public class CalendarProtocol {
	/* CLIENT CODES */
	public final static String CMD_LOGIN  = "LOGIN";
	public final static String CMD_LOGOUT = "LOGOUT";

	public final static String CMD_APPOINTMENT_CREATE = "APPT CREATE";
	public final static String CMD_APPOINTMENT_UPDATE = "APPT UPDATE";
	public final static String CMD_APPOINTMENT_DELETE = "APPT DELETE";

	public final static String CMD_ROOM_CREATE 	= "ROOM CREATE";
	public final static String CMD_ROOM_RESERVE 	= "ROOM RES";
	public final static String CMD_ROOM_UNRESERVE 	= "ROOM UNRES";
	public final static String CMD_ROOM_DELETE 	= "ROOM DEL";

	public final static String CMD_REQUEST_UPDATE 	= "UPDATE";
	
	/* Status codes */
	public final static int STATUS_LOGIN_SUCCESS = 100;
	public final static int STATUS_LOGIN_ERROR = 109;
	public final static int STATUS_LOGOUT_SUCCESS = 190;
	public final static int STATUS_LOGOUT_ERROR = 199;
	
	public final static int STATUS_APPOINTMENT_CREATE_SUCCESS = 200;
	public final static int STATUS_APPOINTMENT_CREATE_ERROR = 209;
	public final static int STATUS_APPOINTMENT_UPDATE_SUCCESS = 210;
	public final static int STATUS_APPOINTMENT_UPDATE_ERROR = 219;
	public final static int STATUS_APPOINTMENT_DELETE_SUCCESS = 290;
	public final static int STATUS_APPOINTMENT_DELETE_ERROR = 299;
	
	public final static int STATUS_ROOM_CREATE_SUCCESS = 300;
	public final static int STATUS_ROOM_CREATE_ERROR = 309;
	public final static int STATUS_ROOM_RESERVE_SUCCESS = 310;
	public final static int STATUS_ROOM_RESERVE_ERROR = 319;
	public final static int STATUS_ROOM_UNRESERVE_SUCCESS = 320;
	public final static int STATUS_ROOM_UNRESERVE_ERROR = 329;
	public final static int STATUS_ROOM_DELETE_SUCCESS = 390;
	public final static int STATUS_ROOM_DELETE_ERROR = 391;
	
	public final static int STATUS_GENERAL_NEW_UPDATES = 910;
	public final static int STATUS_GENERAL_NO_UPDATES = 911;
	public final static int STATUS_GENERAL_REQUEST_ERROR = 919;
}
