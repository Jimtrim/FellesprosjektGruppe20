package no.ntnu.fp.g20;

import java.awt.*;
import javax.swing.*;
import no.ntnu.fp.g20.model.*;

public class NewAppointmentDialog extends JDialog
{
	private Appointment model;
	private AppointmentPanel panel;

	public NewAppointmentDialog(JFrame parent)
	{
		super(parent, "New appointment");
		model = new Appointment();

		panel = new AppointmentPanel(model);
		add(panel, BorderLayout.CENTER);
	}
}

