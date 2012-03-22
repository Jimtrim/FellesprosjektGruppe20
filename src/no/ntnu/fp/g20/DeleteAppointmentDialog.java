package no.ntnu.fp.g20;

import javax.swing.*;

public class DeleteMeetingDialog extends JOptionPane {
	private String deleteMessage = "You are about to delete this meeting.\n"+
								   "Continue?";
	private String deleteTitle = "Delete meeting?";
	String[] buttons = { "Confirm", "Cancel"};
	
	/**
	 * @return Int, 0 for OK, 2 for CANCEL
	 */
	public int showDialog() {
		return super.showOptionDialog(null, deleteMessage, deleteTitle,
		        super.INFORMATION_MESSAGE, super.INFORMATION_MESSAGE, null, buttons, buttons[1]);
	}
	
	public static void main(String[] args)
	{
		DeleteMeetingDialog dialog = new DeleteMeetingDialog();
		int input = dialog.showDialog();
		String choice = (input==0 ? "OK" : "Cancel" ); 
		System.out.println(choice);
	}
}
