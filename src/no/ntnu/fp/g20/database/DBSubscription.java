package no.ntnu.fp.g20.database;

public class DBSubscription {
	public final static String GET_SUBSCRIPTIONS_STATEMENT = "SELECT id,username,firstname,lastname FROM users "
		+ "WHERE id = (SELECT subscribes_to FROM subscriptions WHERE user_id = ?)";
	
//	public static Subscription getSubscriptions(int userID){
//		
//		String query = "SELECT * FROM subscriptions WHERE user_id = '" + userID + "'";
//		
//	}
	
//	public static boolean removeSubscription(int userID){
//		String query = "DELETE FROM subscriptions WHERE user_id = '" + userID + "'";
//		
//		return false;
//	}

}
