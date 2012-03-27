package no.ntnu.fp.g20;

import java.awt.*;
import java.awt.event.*;
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

		okButton = new JButton(new OkAction());
		cancelButton = new JButton(new CancelAction());

		getRootPane().setDefaultButton(okButton);

		buttonBox.add(Box.createHorizontalGlue());
		buttonBox.add(cancelButton);
		buttonBox.add(Box.createHorizontalStrut(5));
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

	public class OkAction extends AbstractAction
	{
		public OkAction()
		{
			super("OK");
		}

		public void actionPerformed(ActionEvent event)
		{

		}
	}

	public class CancelAction extends AbstractAction
	{
		public CancelAction()
		{
			super("OK");
		}

		public void actionPerformed(ActionEvent event)
		{
			setVisible(false);
			model = null;
		}
	}
}

