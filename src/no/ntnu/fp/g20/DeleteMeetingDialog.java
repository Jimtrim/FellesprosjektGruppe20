package no.ntnu.fp.g20;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

public class DeleteMeetingDialog extends JOptionPane {
	
	/** 
	 * Constructor.
	 * Constructs a new dialogue.
	 */
	public DeleteMeetingDialog(JFrame parent)
	{
		
	}
	
	public static void main(String[] args)
	{
		DeleteMeetingDialog dialog = new LoginDialog(null);
		dialog.setVisible(true);
	}
}
