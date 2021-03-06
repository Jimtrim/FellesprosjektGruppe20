// Login Dialogue

package no.ntnu.fp.g20;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Login dialogue class.
 * @author Kristian Klomsten Skordal
 */
public class LoginDialog extends JDialog
{
	/**
	 * Enumeration of the buttons in the dialog.
	 * This is used for checking which of the buttons was clicked to close the dialog box.
	 */
	public enum CloseReason { LOGIN, CANCEL, CLOSE_BUTTON };

	private JTextField usernameField;
	private JPasswordField passwordField;

	private CloseReason closeReason;

	/** 
	 * Constructor.
	 * Constructs a new dialogue.
	 */
	public LoginDialog(JFrame parent)
	{
		super(parent, "Calendar Login", true);
		closeReason = CloseReason.CLOSE_BUTTON;

		// Make layout and layout settings:
		GridBagConstraints c = new GridBagConstraints();
		GridBagLayout layout = new GridBagLayout();
		getContentPane().setLayout(layout);

		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 2;
		c.ipady = 2;

		// Create field labels:
		JLabel usernameLabel = new JLabel("Username: ");
		JLabel passwordLabel = new JLabel("Password: ");

		layout.setConstraints(usernameLabel, c);
		c.gridy++;
		layout.setConstraints(passwordLabel, c);

		add(usernameLabel);
		add(passwordLabel);

		// Create the fields themselves:
		c.gridx++;
		c.gridy = 0;
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;

		usernameField = new JTextField(15);
		passwordField = new JPasswordField(15);

		layout.setConstraints(usernameField, c);
		add(usernameField);

		c.gridy++;
		layout.setConstraints(passwordField, c);
		add(passwordField);

		// Create the help button:
		c.gridy++;
		c.gridx = 0;
		c.weightx = 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;

		JButton helpButton = new JButton(new HelpAction());
		layout.setConstraints(helpButton, c);
		add(helpButton);

		// Create the box for the cancel and login buttons:
		Box buttonBox = Box.createHorizontalBox();
		
		c.gridx = 1;
		c.weightx = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		layout.setConstraints(buttonBox, c);
		add(buttonBox);

		// Create the buttons:
		JButton cancelButton = new JButton(new CancelAction());
		JButton loginButton = new JButton(new LoginAction());
		getRootPane().setDefaultButton(loginButton);

		buttonBox.add(Box.createHorizontalGlue());
		buttonBox.add(cancelButton);
		buttonBox.add(Box.createHorizontalStrut(2));
		buttonBox.add(loginButton);

		// Do sizing:
		pack();
		setMinimumSize(getSize());
		setMaximumSize(getSize());

		setLocationRelativeTo(null);
	}

	/**
	 * Gets the reason why the dialogue was closed.
	 * @return the close reason.
	 * @see CloseReason
	 */
	public CloseReason getCloseReason()
	{
		return closeReason;
	}

	/**
	 * Shows the dialogue.
	 * @param visible set to true to show the dialogue, set to false to hide the dialogue - simple, isn't it?
	 */
	public void setVisible(boolean visible)
	{
		if(visible)
			closeReason = CloseReason.CLOSE_BUTTON;
		super.setVisible(visible);
	}

	/**
	 * Gets the username from the username field.
	 * @return the username.
	 */
	public String getUsername()
	{
		return usernameField.getText();
	}

	/**
	 * Gets the password from the password field.
	 * @return the password.
	 */
	public String getPassword()
	{
		return new String(passwordField.getPassword());
	}

	/**
	 * Action for showing the login dialog help.
	 */
	public class HelpAction extends AbstractAction
	{
		/** Constructs a new help action. */
		public HelpAction()
		{
			super("?");

			putValue(SHORT_DESCRIPTION, "Get help for this dialogue box.");
			putValue(LONG_DESCRIPTION, "Opens a window containing help for this dialogue box.");
		}

		/**
		 * Performs the action.
		 * Shows a dialogue with help instructions.
		 * @param event the action event.
		 */
		public void actionPerformed(ActionEvent event)
		{
			JOptionPane.showMessageDialog(null, 
				"1. Put your username in the username field.\n2. Put your password in the password field."
				+ "\n3. Click the login button to log in.",
				"Login dialogue help", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Action for cancelling the dialogue box.
	 * Closes the dialogue box and sets the close action to CANCEL.
	 * @see CloseAction
	 */
	public class CancelAction extends AbstractAction
	{
		/** Constructs a new cancel action. */
		public CancelAction()
		{
			super("Cancel");

			putValue(SHORT_DESCRIPTION, "Cancels the login dialogue.");
			putValue(LONG_DESCRIPTION, "Cancels the login dialogue and closes the application.");
			putValue(MNEMONIC_KEY, KeyEvent.VK_C);
		}

		/**
		 * Performs the action.
		 * This closes the dialogue box and sets the close reason to CANCEL.
		 * @see CloseReason
		 * @param event the action event.
		 */
		public void actionPerformed(ActionEvent event)
		{
			closeReason = CloseReason.CANCEL;
			setVisible(false);
		}
	}

	/**
	 * Action for starting the login process.
	 * Simply closes the dialogue box and sets the close reason to LOGIN.
	 * @see CloseReason
	 */
	public class LoginAction extends AbstractAction
	{
		/** Constructs a login action. */
		public LoginAction()
		{
			super("Login");

			putValue(SHORT_DESCRIPTION, "Starts the login process.");
			putValue(LONG_DESCRIPTION, "Starts the login process by connecting to the server and checking your credentials.");
			putValue(MNEMONIC_KEY, KeyEvent.VK_L);
		}

		/**
		 * Performs the action.
		 * Closes the dialog and sets the close reason to LOGIN.
		 * @see CloseReason
		 * @param event the action event.
		 */
		public void actionPerformed(ActionEvent event)
		{
			if(usernameField.getText().length() == 0)
			{
				JOptionPane.showMessageDialog(null, "Please type in a username!", "Error!",
					JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				for(int i = 0; i < usernameField.getText().length(); ++i)
				{
					char c = usernameField.getText().charAt(i);
					if(!Character.isLetter(c) && !Character.isDigit(c))
					{
						JOptionPane.showMessageDialog(null,
							"Your username can only contain characters or digits.",
							"Error!", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}

			if(passwordField.getPassword().length == 0)
			{
				JOptionPane.showMessageDialog(null, "Please type in a password!", "Error!",
					JOptionPane.ERROR_MESSAGE);
				return;
			}

			closeReason = CloseReason.LOGIN;
			setVisible(false);
		}
	}
}

