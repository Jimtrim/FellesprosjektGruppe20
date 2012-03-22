package no.ntnu.fp.g20;

import javax.swing.JOptionPane;

public class NewInviteDialog extends JOptionPane {
	private final static String inviteMessage = "You have recieved a an invite";
	private final static String inviteTitle = "New meeting";
	
	private final static String[] buttons = { "Show", "Cancel"};
	
	/**
	 * Shows the dialogue.
	 * @return 0 for show, 1 for cancel.
	 */
	public int showDialog() {
	    return showOptionDialog(null, inviteMessage, inviteTitle,
	       INFORMATION_MESSAGE, INFORMATION_MESSAGE, null, buttons, buttons[0]);
	}
}
