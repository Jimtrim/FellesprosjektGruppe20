package no.ntnu.fp.g20;

import javax.swing.*;

public class DeleteAppointmentDialog extends JOptionPane {
	private final static String deleteMessage = "You are about to delete this appointment.\nContinue?";
	private final static String deleteTitle = "Delete appointment?";
	private final static String[] buttons = { "Confirm", "Cancel"};
	
	/**
	 * Shows the dialogue.
	 * @return 0 for OK, 2 for cancel.
	 */
	public int showDialog() {
		return super.showOptionDialog(null, deleteMessage, deleteTitle,
		        super.INFORMATION_MESSAGE, super.INFORMATION_MESSAGE, null, buttons, buttons[1]);
	}
}
