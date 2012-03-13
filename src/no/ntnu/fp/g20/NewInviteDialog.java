package no.ntnu.fp.g20;

import javax.swing.JOptionPane;

public class NewInviteDialog extends JOptionPane {
	private String inviteMessage = "You have recieved a an invite";
	private String inviteTitle = "New meeting";
	
	String[] buttons = { "Show", "Cancel"};
	
	/**
	* @return Int, 0 for SHOW, 1 for CANCEL
	*/
	public int showDialog() {
	    return super.showOptionDialog(null, inviteMessage, inviteTitle,
	        super.INFORMATION_MESSAGE, super.INFORMATION_MESSAGE, null, buttons, buttons[0]);
	}
	
	public static void main(String[] args)
	{
		NewInviteDialog dialog = new NewInviteDialog();
		int input = dialog.showDialog();
		System.out.println(input);
		
		String choice = (input==0 ? "OK" : "Cancel" ); 
		System.out.println(choice);
	}
	
}
