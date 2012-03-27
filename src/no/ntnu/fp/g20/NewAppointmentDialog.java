package no.ntnu.fp.g20;

import java.awt.*;
import javax.swing.*;
import no.ntnu.fp.g20.model.*;

/**
 * Dialogue for creating new appointments.
 */
public class NewAppointmentDialog extends JDialog
{
	private Appointment model;
	private AppointmentPanel panel;

	private JButton okButton, cancelButton;

	/**
	 * Constructs a new appointment dialogue with the specified parent frame.
	 * @param parent the parent frame.
	 */
	public NewAppointmentDialog(JFrame parent)
	{
		super(parent, "New appointment");
		model = new Appointment();

		panel = new AppointmentPanel(model);
		add(panel, BorderLayout.CENTER);

		Box buttonBox = Box.createHorizontalBox();
		add(buttonBox, BorderLayout.SOUTH);

		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");

		getRootPane().setDefaultButton(okButton);

		buttonBox.add(cancelButton);
		buttonBox.createHorizontalStrut(5);
		buttonBox.add(okButton);

		pack();
	}

	/**
	 * Gets the appointment constructed by this dialogue.
	 * @return an appointment.
	 */
	public Appointment getModel()
	{
		return model;
	}
}

