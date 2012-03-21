package no.ntnu.fp.g20.database;

/**
 * Purpose: offer helper-methods for database-statements
 * 
 * @author Jim Frode Hoff
 * 
 *
 */

public class InputValidation {
	public static boolean isAlphaNumeric(String s) {
		for (char c : s.toCharArray()) {
			if ( !(Character.isDigit(c) || Character.isLetter(c)) ) 
				return false;
		}
		return true;
	}
	public static boolean isUserValid(String uName, String pwd, String fName, String lName ) {
		if (fName == null || !InputValidation.isAlphaNumeric(fName) ) {
			System.err.println("Firstname is invalid!");
			return false;
		} else if (lName == null || !InputValidation.isAlphaNumeric(lName) ) {
			System.err.println("Lastname is invalid!");
			return false;
		} else if (uName == null || !InputValidation.isAlphaNumeric(uName) ) {
			System.err.println("Username is invalid!");
			return false;
		} else if (pwd == null || !InputValidation.isAlphaNumeric(pwd) ) {
			// TODO: Lage en hash'e-funksjon på klient-siden
			System.err.println("Password is invalid!");
			return false;
		} else {
			return true;
		}
	}
}
