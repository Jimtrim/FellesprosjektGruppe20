package no.ntnu.fp.g20;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

public class DeleteMeetingDialog extends JOptionPane {
	private String deleteMessage = "Du er i ferd med å slette møtet.\n"+
								   "Fortsett?";
	private String deleteTitle = "Slett møte?";
	private String confirmButtonText = "Ja";
	private String abortButtonText = "Avbryt";
	
	/** 
	 * Constructor.
	 * Constructs a new dialogue.
	 */
	public DeleteMeetingDialog(JFrame parent)
	{
		super();
	}

	/**
	 * 
	 * @return Int, 0 for OK, 2 for CANCEL
	 */
	public int showDialog() {
		return super.showConfirmDialog(getParent(), deleteMessage, deleteTitle, OK_CANCEL_OPTION);
	}
	
	public static void main(String[] args)
	{
		DeleteMeetingDialog dialog = new DeleteMeetingDialog(null);
		int input = dialog.showDialog();
		String choice = (input==0 ? "OK" : "Cancel" ); 
		System.out.println(choice);
	}
}
