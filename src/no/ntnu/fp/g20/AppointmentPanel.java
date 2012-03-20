// The appointment and meeting status panel.

package no.ntnu.fp.g20;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import no.ntnu.fp.g20.model.Appointment;

/**
 * Appointment information panel class.
 * This panel displays information about all appointments that a
 * user has some relation to.
 * @author Kristian Klomsten Skordal
 */
public class AppointmentPanel extends JPanel {
	
	private Appointment model;

	private JComboBox sortBox;
	private JList appointmentList;
	private JTextField titleField, locationField;
	private JComboBox hoursBox, minutesBox, dayBox, monthBox, yearBox, durationBox;
	private JTextArea descriptionField;
	private JToggleButton approveButton, rejectButton;
	private JButton participantsButton, roomResButton;

	/**
	 * Constructs an appointment panel.
	 */
	public AppointmentPanel()
	{
		super();

		// Set up layout:
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);

		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 2;
		c.ipady = 2;

		// Add the sort by label:
		JLabel sortLabel = new JLabel("Sort by:");
		layout.setConstraints(sortLabel, c);
		add(sortLabel);

		// Add the sort box:
		String[] sortType = { "Time", "Status" };
		c.gridx++;
		c.anchor = GridBagConstraints.WEST;
		sortBox = new JComboBox(sortType);
		layout.setConstraints(sortBox, c);
		add(sortBox);
		sortBox.addActionListener(new SortBoxListener());
		

		// Add the appointment list:
		appointmentList = new JList();
		appointmentList.setModel(new DefaultListModel());
		JScrollPane scrollPane = new JScrollPane(appointmentList);
		c.gridx = 0;
		c.gridy++;
		c.weighty = 1;
		c.weightx = 0.7;
		c.gridwidth = 2;
		c.gridheight = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.BOTH;
		layout.setConstraints(scrollPane, c);
		add(scrollPane);

		// Add the panel heading label:
		c.gridx = 2;
		c.gridy = 0;
		c.weighty = 0;
		c.weightx = 0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 1;

		JLabel detailsLabel = new JLabel("Appointment details");
		layout.setConstraints(detailsLabel, c);
		add(detailsLabel);

		// Add the title label:
		c.gridy++;
		c.gridwidth = 1;
		JLabel titleLabel = new JLabel("Title:");
		layout.setConstraints(titleLabel, c);
		add(titleLabel);

		// Add the title field:
		c.gridx++;
		c.gridwidth = 5;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		titleField = new JTextField(20);
		titleField.setText("Go home");
		layout.setConstraints(titleField, c);
		add(titleField);
		titleField.addKeyListener(new TitleFieldListener());
		
		//add the date label
		JLabel dateLabel = new JLabel("Date: ");
		c.gridy++;
		c.gridx = 2;
		c.gridwidth = 1;
		add(dateLabel, c);
		
		//add the date combo boxes
		dayBox = new JComboBox();
		for (int i = 0; i<31; i++) {
			dayBox.addItem(i+1);
		}
		c.gridx++;
		c.anchor = GridBagConstraints.EAST;
		add(dayBox, c);
		
		String[] months = {"January", "February", "March", "April", "May", "June", "July",
				"August", "September", "October", "November", "December"};
		monthBox = new JComboBox(months);
		c.gridx++;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(monthBox, c);
		
		yearBox = new JComboBox();
		yearBox.addItem(2012);
		c.gridx = c.gridx + c.gridwidth;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		add(yearBox, c);
		
		//add listeners to the date boxes
		dayBox.addActionListener(new DayBoxListener());
		monthBox.addActionListener(new MonthBoxListener());
		yearBox.addActionListener(new YearBoxListener());
		

		// Add the time label:
		c.gridx = 2;
		c.gridy++;
		JLabel timeLabel = new JLabel("Time:");
		layout.setConstraints(timeLabel, c);
		add(timeLabel);

		//add the time boxes
		hoursBox = new JComboBox();
		hoursBox.addItem(13);
		c.anchor = GridBagConstraints.EAST;
		c.gridx++;
		add(hoursBox, c);
		
		minutesBox = new JComboBox();
		minutesBox.addItem(59);
		c.gridx++;
		c.anchor = GridBagConstraints.WEST;
		add(minutesBox, c);
			
		//add listeners to the time boxes
		hoursBox.addActionListener(new HoursBoxListener());
		minutesBox.addActionListener(new MinutesBoxListener());
		
		// Add the duration label:
		JLabel durationLabel = new JLabel("Duration: ");
		layout.setConstraints(durationLabel, c);
		c.gridx++;
		add(durationLabel, c);

		//add the duration box
		durationBox = new JComboBox();
		c.gridx++;
		add(durationBox, c);
		durationBox.addActionListener(new DurationBoxListener());

		// Add the hours label:
		JLabel hourLabel = new JLabel("hours");
		c.gridx++;
		layout.setConstraints(hourLabel, c);
		add(hourLabel);
		
		//add the location label
		JLabel locationLabel = new JLabel("Location: ");
		c.gridy++;
		c.gridx = 2;
		c.gridwidth = 1;
		add(locationLabel, c);
		
		//add the location field
		locationField = new JTextField(20);
		c.gridx++;
		c.gridwidth = 5;
		add(locationField, c);
		locationField.addKeyListener(new LocationFieldListener());
		
		//add room reservation button
		roomResButton = new JButton("Room");
		c.gridx = c.gridx + c.gridwidth;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.WEST;
		add(roomResButton, c);
		roomResButton.addActionListener(new RoomResButtonListener());

		// Add the description label:
		c.gridx = 2;
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
		descriptionField.addKeyListener(new DescriptionFieldListener());

		// Add the button box:
		Box buttonBox = Box.createHorizontalBox();
		c.gridy++;
		c.gridheight = 1;
		c.weightx = 1;
		c.weighty = 0;
		layout.setConstraints(buttonBox, c);
		add(buttonBox);

		// Add the confirm/reject buttons:
		ButtonGroup confirmationGroup = new ButtonGroup();
		approveButton = new JToggleButton("Approve");
		rejectButton = new JToggleButton("Reject");

		confirmationGroup.add(approveButton);
		confirmationGroup.add(rejectButton);

		buttonBox.add(approveButton);
		buttonBox.add(Box.createHorizontalStrut(2));
		buttonBox.add(rejectButton);

		buttonBox.add(Box.createHorizontalGlue());

		// Add the participants button:
		participantsButton = new JButton("Participants");
		buttonBox.add(participantsButton);
		
		//add listeners to the buttons
		approveButton.addActionListener(new ApproveButtonListener());
		rejectButton.addActionListener(new RejectButtonListener());
		participantsButton.addActionListener(new ParticipantsButtonListener());
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
			titleField.setText(model.getName());
			locationField.setText(model.getLocation());
			dayBox.setSelectedItem(model.getStartTime().get(Calendar.DAY_OF_MONTH));
			monthBox.setSelectedItem(model.getStartTime().get(Calendar.MONTH));
			yearBox.setSelectedItem(model.getStartTime().get(Calendar.YEAR));
			hoursBox.setSelectedItem(model.getStartTime().get(Calendar.HOUR));
			minutesBox.setSelectedItem(model.getStartTime().get(Calendar.MINUTE));
			//no getter for duration in Appointment class
			//no description field in Appointment class
		}
	}

	
	//create listener for the sort box
	class SortBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (sortBox.getSelectedItem().equals("Time")) {
				
			}
			else if (sortBox.getSelectedItem().equals("Status")) {
				
			}
		}
	}
	
	//create listener for the title field
	class TitleFieldListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	//create listener for location field
	class LocationFieldListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	//create listener for description field
	class DescriptionFieldListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	//create listener for room reservation button
	class RoomResButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame roomResFrame = new JFrame();
			roomResFrame.add(new RoomReservationPanel());
			roomResFrame.pack();
			roomResFrame.setVisible(true);
		}
	}
	
	//create listeners for the date boxes
	class DayBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	class MonthBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	class YearBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}	
	}
	
	//create listeners for the time boxes
	class HoursBoxListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	class MinutesBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	class DurationBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	//create listener for the approve button
	class ApproveButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	//create listener for the reject button
	class RejectButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	//create listener for the participants button
	class ParticipantsButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ParticipantList listOfParticipants = new ParticipantList();
			listOfParticipants.setVisible(true);
		}
	}
	
	//create listener for the appointment list
	class AppointmentListListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			setModel((Appointment) appointmentList.getSelectedValue());
		}
	}

	/**
	 * Test application main function.
	 * @param args command line arguments.
	 */
	public static void main(String[] args)
	{
		JFrame testFrame = new JFrame("Appointment Panel Test Application");
		testFrame.add(new AppointmentPanel(), BorderLayout.CENTER);
		testFrame.pack();

		testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		testFrame.setVisible(true);
	}
}

