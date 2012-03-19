// The appointment and meeting status panel.

package no.ntnu.fp.g20;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Appointment information panel class.
 * This panel displays information about all appointments that a
 * user has some relation to.
 * @author Kristian Klomsten Skordal
 */
public class AppointmentPanel extends JPanel
{
	private JComboBox sortBox;
	private JList appointmentList;
	private JTextField titleField;
	private JComboBox hours, minutes, day, month, year, duration;
	private JTextArea descriptionField;
	private JToggleButton approveButton, rejectButton;
	private JButton participantsButton;

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
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 2;
		c.ipady = 2;

		// Add the sort by label:
		JLabel sortLabel = new JLabel("Sort by:");
		layout.setConstraints(sortLabel, c);
		add(sortLabel);

		// Add the sort box:
		String[] sortType = { "Time", "Status" };
		c.gridx++;
		sortBox = new JComboBox(sortType);
		layout.setConstraints(sortBox, c);
		add(sortBox);
		
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
		
		//add listener to sortBox
		sortBox.addActionListener(new SortBoxListener());
		

		// Add the appointment list:
		appointmentList = new JList();
		JScrollPane scrollPane = new JScrollPane(appointmentList);
		c.gridx = 0;
		c.gridy++;
		c.weighty = 1;
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
		titleField = new JTextField(20);
		titleField.setText("Go home");
		layout.setConstraints(titleField, c);
		add(titleField);
		
		//add the date label
		JLabel dateLabel = new JLabel("Date: ");
		c.gridy++;
		c.gridx = 2;
		c.gridwidth = 1;
		add(dateLabel, c);
		
		
		//add the date combo boxes
		day = new JComboBox();
		day.addItem(19);
		c.gridx++;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.EAST;
		add(day, c);
		
		month = new JComboBox();
		month.addItem("March");
		c.gridx++;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(month, c);
		
		year = new JComboBox();
		year.addItem(2012);
		c.gridx = c.gridx + c.gridwidth;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		add(year, c);
		

		// Add the start time label:
		c.gridx = 2;
		c.gridy++;
		JLabel timeLabel = new JLabel("Start time:");
		layout.setConstraints(timeLabel, c);
		add(timeLabel);

		//add the start hours combo box
		hours = new JComboBox();
		hours.addItem(13);
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.EAST;
		c.gridx++;
		add(hours, c);
		
		//add the minutes combo box
		minutes = new JComboBox();
		minutes.addItem(59);
		c.gridx++;
		c.anchor = GridBagConstraints.WEST;
		add(minutes, c);
		
		// Add the duration label:
		JLabel durationLabel = new JLabel("Duration: ");
		layout.setConstraints(durationLabel, c);
		c.gridx++;
		add(durationLabel, c);

		//add the duration combo box
		duration = new JComboBox();
		c.gridx++;
		add(duration, c);

		// Add the hours label:
		JLabel hourLabel = new JLabel("hours");
		c.gridx++;
		layout.setConstraints(hourLabel, c);
		add(hourLabel);

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
		c.gridwidth = c.REMAINDER;
		c.fill = GridBagConstraints.BOTH;
		descriptionField = new JTextArea();
		descriptionField.setRows(2);
//		descriptionField.setLineWrap(true);
		descriptionField.setWrapStyleWord(true);
		layout.setConstraints(descriptionField, c);
		add(descriptionField);

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
		
		//create listener for the approve button
		class ApproveButtonListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		}
		
		//add listener for the approve button
		approveButton.addActionListener(new ApproveButtonListener());
		
		//create listener for the reject button
		class RejectButtonListener implements ActionListener {
			
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		}
		
		//add listener for the reject button
		rejectButton.addActionListener(new RejectButtonListener());
		
		//create listener for the participants button
		class ParticipantsButtonListener implements ActionListener {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ParticipantList listOfParticipants = new ParticipantList();
				listOfParticipants.setVisible(true);
			}
		}
		
		//add listener for the participants button
		participantsButton.addActionListener(new ParticipantsButtonListener());
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

