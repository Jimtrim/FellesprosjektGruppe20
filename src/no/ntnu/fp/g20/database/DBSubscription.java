package no.ntnu.fp.g20.database;

public class DBSubscription {
	
	public static Subscription getSubscriptions(int userID){
		
		String query = "SELECT * FROM subscriptions WHERE user_id = '" + userID + "'";
		
	}
	
	public static boolean removeSubscription(int userID){
		String query = "DELETE FROM subscriptions WHERE user_id = '" + userID + "'";
		
		return false;
	}

}
