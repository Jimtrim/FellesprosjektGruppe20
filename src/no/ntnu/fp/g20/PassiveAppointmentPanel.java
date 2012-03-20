package no.ntnu.fp.g20;

import java.awt.Component;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;

import no.ntnu.fp.g20.model.Appointment;

public class PassiveAppointmentPanel extends AppointmentPanel {
	
	private JButton acceptButton, rejectButton;
	private Box passivePanelButtonBox;
	
	public PassiveAppointmentPanel() {
		//call AppointmentPanels constructor
		super();
		
		//disable most of the components
//		int numberOfComponents = getComponentCount();
//		for (int i = 0; i < numberOfComponents; i++ ) {
//			if (getComponent(i) != sortBox) {
//				getComponent(i).setEnabled(false);	
//			}
//		}
		
//		//make all of the components not editable/enabled
		titleField.setEditable(false);
		dayBox.setEnabled(false);
		monthBox.setEnabled(false);
		yearBox.setEnabled(false);
		hoursBox.setEnabled(false);
		durationBox.setEnabled(false);
		roomResButton.setEnabled(false);
		locationField.setEditable(false);
		descriptionField.setEditable(false);
		
		//remove the button box with save and delete buttons
		remove(buttonBox);
		
		//create accept button
		acceptButton = new JButton("Accept");
		
		//create reject button
		rejectButton = new JButton("Reject");
		
		//create horizontal box
		passivePanelButtonBox = Box.createHorizontalBox();
		
		//add buttons to the this panels button box
		passivePanelButtonBox.add(acceptButton);
		passivePanelButtonBox.add(rejectButton);
		passivePanelButtonBox.add(Box.createHorizontalGlue());
		passivePanelButtonBox.add(participantsButton);
		
		//add passivePanelButtonBox to the JPanel
		add(passivePanelButtonBox, c);
		
	}
	
	@Override
	public void setModel(Appointment model) {
		this.model = model;
		if (model != null) {
			titleField.setText(model.getName());
		}
	}
	
	public static void main(String[] args) {
		Appointment a = new Appointment();
		a.setName("test");
		PassiveAppointmentPanel panel = new PassiveAppointmentPanel();
		panel.setModel(a);
		JFrame frame = new JFrame("PassiveAppointmentPanelTest");
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

}
