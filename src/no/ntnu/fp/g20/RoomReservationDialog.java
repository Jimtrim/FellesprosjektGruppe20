// Room reservation panel

package no.ntnu.fp.g20;

import no.ntnu.fp.g20.*;

import java.awt.*;
import javax.swing.*;

public class RoomReservationDialog extends JDialog
{
	private RoomReservationPanel panel;

	public RoomReservationDialog(JFrame parent)
	{
		super(parent, "Reserve room", true);
		panel = new RoomReservationPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		pack();
	}
}

