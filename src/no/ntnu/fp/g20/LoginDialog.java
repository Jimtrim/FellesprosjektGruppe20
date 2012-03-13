package no.ntnu.fp.g20;

// Login Dialogue

package no.ntnu.fp.g20;

import java.awt.*;
import javax.swing.*;

/**
 * Login dialogue class.
 * @author Kristian Klomsten Skordal
 */
public class LoginDialog extends JDialog
{
	private JTextField usernameField;
	private JPasswordField passwordField;

	/** 
	 * Constructor.
	 * Constructs a new dialogue.
	 */
	public LoginDialog(JFrame parent)
	{
		super(parent, "Calendar Login");

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

		JButton helpButton = new JButton("?");
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
		JButton cancelButton = new JButton("Cancel");
		JButton loginButton = new JButton("Login");

		buttonBox.add(Box.createHorizontalGlue());
		buttonBox.add(cancelButton);
		buttonBox.add(Box.createHorizontalStrut(2));
		buttonBox.add(loginButton);

		// Do sizing:
		pack();
		setMinimumSize(getSize());
		setMaximumSize(getSize());
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
	 * Main function for testing.
	 * @param args command line arguments.
	 */
	public static void main(String[] args)
	{
		LoginDialog dialog = new LoginDialog(null);
		dialog.setVisible(true);
	}
}

