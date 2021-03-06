// The appointment and meeting status panel.

package no.ntnu.fp.g20;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.event.*;

import no.ntnu.fp.g20.*;
import no.ntnu.fp.g20.model.*;

/**
 * Appointment information panel class.
 * This panel displays information about all appointments that a
 * user has some relation to.
 * @author Kristian Klomsten Skordal
 */
public class AppointmentPanel extends JPanel {
	
	protected Appointment model;
	
	protected GridBagConstraints c;

	protected JTextField titleField, locationField;
	protected JComboBox hoursBox,dayBox, monthBox, yearBox, durationBox;
	protected JTextArea descriptionField;
	protected JButton participantsButton, roomResButton, saveButton, deleteButton;
	protected Box buttonBox;

	/**
	 * Constructs an appointment panel with the specified model.
	 */
	public AppointmentPanel(Appointment model)
	{
		this.model = model;
	}

	/**
	 * Constructs an appointment panel.
	 */
	public AppointmentPanel()
	{
		super();

		// Set up layout:
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);

		c = new GridBagConstraints();

		c.ipadx = 2;
		c.ipady = 2;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 0;
		c.gridy = 0;

		// Add the panel heading label:
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1;
		JLabel detailsLabel = new JLabel("Appointment details");
		layout.setConstraints(detailsLabel, c);
		add(detailsLabel);

		// Add the title label:
		c.gridy++;
		c.gridwidth = 1;
		c.weightx = 0;
		JLabel titleLabel = new JLabel("Title:");
		layout.setConstraints(titleLabel, c);
		add(titleLabel);

		// Add the title field:
		c.gridx++;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		titleField = new JTextField(20);
		layout.setConstraints(titleField, c);
		add(titleField);
		
		//add the date label
		JLabel dateLabel = new JLabel("Time: ");
		c.gridy++;
		c.gridx = 0;
		c.gridwidth = 1;
		add(dateLabel, c);
		
		//add the date combo boxes
		dayBox = new JComboBox();
		for (int i = 1; i<32; i++)
			dayBox.addItem(i);
		c.gridx++;
		add(dayBox, c);
		
		String[] months = {"January", "February", "March", "April", "May", "June", "July",
				"August", "September", "October", "November", "December"};
		monthBox = new JComboBox(months);
		c.gridx++;
		add(monthBox, c);
		
		yearBox = new JComboBox();
		yearBox.addItem(2012);
		c.gridx++;
		c.gridwidth = 1;
		add(yearBox, c);	

		// Add the time label:
		c.gridx++;
		JLabel timeLabel = new JLabel(" at ");
		layout.setConstraints(timeLabel, c);
		add(timeLabel);

		//add the hours box
		hoursBox = new JComboBox();
		for (int i = CalendarModel.START_HOUR; i < CalendarModel.HOURS + CalendarModel.START_HOUR; i++)
			hoursBox.addItem(String.format("%1$2d:00", i));
		
		c.gridx++;
		add(hoursBox, c);
		
		// Add the duration label:
		JLabel durationLabel = new JLabel("Duration: ");
		layout.setConstraints(durationLabel, c);
		c.gridx++;
		add(durationLabel, c);

		//add the duration box
		durationBox = new JComboBox();
		for (int i = 1; i < 12; i++) {
			durationBox.addItem(i);
		}
		c.gridx++;
		add(durationBox, c);

		// Add the hours label:
		JLabel hourLabel = new JLabel("hour(s)");
		c.gridx++;
		layout.setConstraints(hourLabel, c);
		add(hourLabel);
		
		//add the location label
		JLabel locationLabel = new JLabel("Location: ");
		c.gridy++;
		c.gridx = 0;
		add(locationLabel, c);
		
		//add the location field
		locationField = new JTextField(20);
		c.gridx++;
		c.weightx = 1;
		c.gridwidth = GridBagConstraints.RELATIVE;
		add(locationField, c);
		
		//add room reservation button
		roomResButton = new JButton("Room");
		c.gridx = GridBagConstraints.RELATIVE;
		c.weightx = 0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		add(roomResButton, c);
		roomResButton.addActionListener(new RoomResButtonListener());

		// Add the description label:
		c.gridx = 0;
		c.gridy++;
		c.gridwidth = 2;
		JLabel descriptionLabel = new JLabel("Description:");
		layout.setConstraints(descriptionLabel, c);
		add(descriptionLabel);

		// Add the description field:
		c.gridy++;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.BOTH;
		descriptionField = new JTextArea();
		descriptionField.setRows(2);
//		descriptionField.setLineWrap(true);
		descriptionField.setWrapStyleWord(true);
		layout.setConstraints(descriptionField, c);
		add(descriptionField);

		// Add the button box:
		buttonBox = Box.createHorizontalBox();
		c.gridy++;
		c.gridheight = 1;
		c.weightx = 1;
		c.weighty = 0;
		layout.setConstraints(buttonBox, c);
		add(buttonBox);

		// Add the save and delete buttons:
		saveButton = new JButton("Save");
		deleteButton = new JButton("Delete");

		buttonBox.add(saveButton);
		buttonBox.add(Box.createHorizontalStrut(2));
		buttonBox.add(deleteButton);

		buttonBox.add(Box.createHorizontalGlue());

		// Add the participants button:
		participantsButton = new JButton("Participants");
		buttonBox.add(participantsButton);
		
		//add listeners to the buttons
		saveButton.addActionListener(new SaveButtonListener());
		deleteButton.addActionListener(new DeleteButtonListener());
		participantsButton.addActionListener(new ParticipantsButtonListener());
	}
	
	
	
	public String getTitle() {
		return titleField.getText();
	}



	public void setTitle(String title) {
		this.titleField.setText(title);
	}



	public String getLocationText() {
		return locationField.getText();
	}



	public void setLocationText(String location) {
		this.locationField.setText(location);
	}



	public int getHours() {
		return hoursBox.getSelectedIndex() + 1;
	}



	public void setHours(int hours) {
		this.hoursBox.setSelectedIndex(hours - 1);
	}



	public int getDay() {
		return dayBox.getSelectedIndex() + 1;
	}



	public void setDay(int day) {
		this.dayBox.setSelectedIndex(day - 1);
	}



	public int getMonth() {
		return monthBox.getSelectedIndex() + 1;
	}



	public void setMonth(int month) {
		this.monthBox.setSelectedIndex(month - 1);
	}



	public int getYear() {
		return (Integer) yearBox.getSelectedItem();
	}



	public void setYear(int year) {
		this.yearBox.setSelectedItem(year);
	}



	public int getDuration() {
		return durationBox.getSelectedIndex() + 1;
	}



	public void setDuration(int duration) {
		this.durationBox.setSelectedIndex(duration - 1);
	}



	public String getDescription() {
		return descriptionField.getText();
	}



	public void setDescription(String description) {
		this.descriptionField.setText(description);
	}



	/**
	 * Return the panels Appointment model
	 */
	public Appointment getModel() {
		return this.model;
	}
	
	/**
	 * Sets the panels Appointment model
	 */
	public void setModel(Appointment model) {
		this.model = model;
		if (model != null) {
			titleField.setText(model.getTitle());
			locationField.setText(model.getLocation());
			dayBox.setSelectedItem(model.getStartTime().get(Calendar.DAY_OF_MONTH));
			monthBox.setSelectedItem(model.getStartTime().get(Calendar.MONTH));
			yearBox.setSelectedItem(model.getStartTime().get(Calendar.YEAR));
			hoursBox.setSelectedItem(model.getStartTime().get(Calendar.HOUR));
			durationBox.setSelectedItem(model.getDuration());
			descriptionField.setText(model.getDescription());
		}
	}
	
	//create listener for room reservation button
	class RoomResButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			RoomReservationDialog dialog = new RoomReservationDialog(null /* FIXME */);
			dialog.setVisible(true);
		}
	}
	
	//create listener for the approve button
	class SaveButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	//create listener for the reject button
	class DeleteButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	//create listener for the participants button
	class ParticipantsButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ParticipantListPanel dialog = new ParticipantListPanel();
			dialog.setVisible(true);
		}
	}
}

