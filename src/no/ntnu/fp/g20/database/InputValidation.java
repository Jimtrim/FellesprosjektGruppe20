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
}
