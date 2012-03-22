package no.ntnu.fp.g20;

public class CalendarProtocol {
	/* CLIENT CODES */
	public final static int USER_LOGIN 		= 10;
	public final static int USER_LOGOUT 	= 19;
	
	public final static int APPOINTMENT_CREATE = 20;
	public final static int APPOINTMENT_UPDATE = 21;
	public final static int APPOINTMENT_DELETE = 29;

	public final static int ROOM_CREATE 	= 30;
	public final static int ROOM_RESERVE 	= 31;
	public final static int ROOM_UNRESERVE 	= 32;
	public final static int ROOM_DELETE 	= 39;
	
	public final static int GENERAL_REQUEST_UPDATE 	= 91;
	
	/* SERVER CODES */
	public final static int SERVER_LOGIN_SUCCESS = 100;
	public final static int SERVER_LOGIN_ERROR = 109;
	public final static int SERVER_LOGOUT_SUCCESS = 190;
	public final static int SERVER_LOGOUT_ERROR = 199;
	
	public final static int SERVER_APPOINTMENT_CREATE_SUCCESS = 200;
	public final static int SERVER_APPOINTMENT_CREATE_ERROR = 209;
	public final static int SERVER_APPOINTMENT_UPDATE_SUCCESS = 210;
	public final static int SERVER_APPOINTMENT_UPDATE_ERROR = 219;
	public final static int SERVER_APPOINTMENT_DELETE_SUCCESS = 290;
	public final static int SERVER_APPOINTMENT_DELETE_ERROR = 299;
	
	public final static int SERVER_ROOM_CREATE_SUCCESS = 300;
	public final static int SERVER_ROOM_CREATE_ERROR = 309;
	public final static int SERVER_ROOM_RESERVE_SUCCESS = 310;
	public final static int SERVER_ROOM_RESERVE_ERROR = 319;
	public final static int SERVER_ROOM_UNRESERVE_SUCCESS = 320;
	public final static int SERVER_ROOM_UNRESERVE_ERROR = 329;
	public final static int SERVER_ROOM_DELETE_SUCCESS = 390;
	public final static int SERVER_ROOM_DELETE_ERROR = 391;
	
	public final static int SERVER_GENERAL_NEW_UPDATES = 910;
	public final static int SERVER_GENERAL_NO_UPDATES = 911;
	public final static int SERVER_GENERAL_REQUEST_ERROR = 919;
}
