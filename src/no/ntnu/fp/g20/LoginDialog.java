package no.ntnu.fp.g20;

// Login Dialogue

import javax.swing.*;

/**
 * Login dialogue class.
 * @author Kristian Klomsten Skordal
 */
public class LoginDialog extends JDialog
{
	/** 
	 * Constructor.
	 * Constructs a new dialogue.
	 */
	public LoginDialog()
	{
	//	super("Calendar Login");
	}

	/**
	 * Main class for testing.
	 * @param args command line arguments.
	 */
	public static void main(String[] args)
	{
		LoginDialog dialog = new LoginDialog();
		dialog.setVisible(true);
	}
}

