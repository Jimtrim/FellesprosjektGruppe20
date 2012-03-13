package no.ntnu.fp.g20;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DetailsPanel extends JPanel {
	
	private GridBagLayout layout;
	private GridBagConstraints c;
	
	private JLabel titleLabel, locationLabel, descriptionLabel, startTimeLabel, durationLabel;
	private JTextField titleField, locationField;
	private JTextArea descriptionArea;
	private JButton roomButton, removeButton, participantsButton, saveButton;
	private JComboBox timeBox, dayBox, monthBox, yearBox, durationBox;
	
	public DetailsPanel() {

		layout = new GridBagLayout();
		c = new GridBagConstraints();
		setLayout(layout);
		
		//add title label
		titleLabel = new JLabel("Title: ");
		c.gridy = 0;
		c.gridx = 0;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.anchor = GridBagConstraints.WEST;
		add(titleLabel, c);
		
		//add title text field
		titleField = new JTextField(20);
		c.gridx++;
		add(titleField, c);
		
		//add start time label
		startTimeLabel = new JLabel("Start time: ");
		c.gridx = c.gridx + 2;
		add(startTimeLabel, c);
		
		//add start time boxes
		timeBox = new JComboBox();
		timeBox.addItem("time");
		
		dayBox = new JComboBox();
		dayBox.addItem("day");
		
		monthBox = new JComboBox();
		monthBox.addItem("month");
		
		yearBox = new JComboBox();
		yearBox.addItem("year");
		
		Box comboBoxes = Box.createHorizontalBox();
		comboBoxes.add(timeBox);
		comboBoxes.add(dayBox);
		comboBoxes.add(monthBox);
		comboBoxes.add(yearBox);
		
		c.gridx++;
		c.gridwidth = 2;
		add(comboBoxes, c);
		
		//add location label
		locationLabel = new JLabel("Location: ");
		c.gridy++;
		c.gridx = 0;
		c.gridwidth = 1;
		add(locationLabel, c);
		
		//add location text field
		locationField = new JTextField(20);
		c.gridx++;
		add(locationField, c);
		
		//add room button
		roomButton = new JButton("Room");
		c.gridx = c.gridx + c.gridwidth;
		add(roomButton, c);
		
		//add duration label
		durationLabel = new JLabel("Duration: ");
		c.gridx++;
		add(durationLabel, c);
		
		//add duration box
		durationBox = new JComboBox();
		durationBox.addItem("hours");
		c.gridx++;
		add(durationBox, c);
		
		//add description label
		descriptionLabel = new JLabel("Description: ");
		c.gridy++;
		c.gridx = 0;
		c.gridwidth = 2;
		add(descriptionLabel, c);
		
		//add description text area
		descriptionArea = new JTextArea(5, 50);
		descriptionArea.setLineWrap(true);
		descriptionArea.setWrapStyleWord(true);
		c.gridy++;
		c.gridwidth = 6;
		add(descriptionArea, c);
		
		//add remove button
		removeButton = new JButton("Remove");
		c.gridy++;
		c.gridwidth = 1;
		add(removeButton, c);
		
		//add participants button
		participantsButton = new JButton("Participants");
		c.gridx = 4;
		add(participantsButton, c);
		
		//add save button
		saveButton = new JButton("Save");
		c.gridx++;
		add(saveButton, c);
		

	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new DetailsPanel());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
}
