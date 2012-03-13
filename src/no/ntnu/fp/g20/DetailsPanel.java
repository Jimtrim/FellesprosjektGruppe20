package no.ntnu.fp.g20;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

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
	
	private Box comboBox;
	private Box buttonBox;
	
	private JLabel titleLabel;
	private JLabel locationLabel;
	private JLabel descriptionLabel;
	private JLabel startTimeLabel;
	private JLabel durationLabel;
	
	private JTextField titleField;
	private JTextField locationField;
	private JTextArea descriptionArea;
	
	private JButton roomButton;
	private JButton removeButton;
	private JButton participantsButton;
	private JButton saveButton;
	
	private JComboBox timeBox;
	private JComboBox dayBox;
	private JComboBox monthBox;
	private JComboBox yearBox;
	private JComboBox durationBox;
	
	
	public DetailsPanel() {
		titleLabel = new JLabel("Title: ");
		locationLabel = new JLabel("Location: ");
		descriptionLabel = new JLabel("Description: ");
		startTimeLabel = new JLabel("Start time: ");
		durationLabel = new JLabel("Duration: ");
		
		titleField = new JTextField(20);
		locationField = new JTextField(20);
		descriptionArea = new JTextArea(5,50);
		
		roomButton = new JButton("Room");
		removeButton = new JButton("Remove");
		participantsButton = new JButton("Participants");
		saveButton = new JButton("Save");
		
		timeBox = new JComboBox();
		timeBox.addItem("time");
		dayBox = new JComboBox();
		dayBox.addItem("day");
		monthBox = new JComboBox();
		monthBox.addItem("month");
		yearBox = new JComboBox();
		yearBox.addItem("year");
		durationBox = new JComboBox();
		durationBox.addItem("hours");
		
		
		layout = new GridBagLayout();
		c = new GridBagConstraints();
		
		setLayout(layout);
		
		c.anchor = GridBagConstraints.WEST;
		
		c.gridy = 0;
		c.gridx = 0;
		add(titleLabel, c);
		
		c.gridx++;
		add(titleField, c);
		
		c.gridx = 3;
		add(startTimeLabel, c);
		
		comboBox = new Box(BoxLayout.X_AXIS);
		comboBox.add(timeBox);
		comboBox.add(dayBox);
		comboBox.add(monthBox);
		comboBox.add(yearBox);
		
		c.gridx++;
		add(comboBox, c);
		
		c.gridy++;
		c.gridx = 0;
		add(locationLabel, c);
		
		c.gridx++;
		add(locationField, c);
		
		c.gridx++;
		add(roomButton, c);
		
		c.gridx++;
		add(durationLabel, c);
		
		c.gridx++;
		add(durationBox, c);
		
		c.gridy ++;
		c.gridx = 0;
		add(descriptionLabel, c);
		
		c.gridy = 3;
		c.gridheight = 5;
		c.gridwidth = 50;
		add(descriptionArea, c);
		
		buttonBox = Box.createHorizontalBox();
		buttonBox.add(removeButton);
		buttonBox.add(participantsButton);
		buttonBox.add(saveButton);
		
		c.gridy = c.gridy + c.gridheight;
		add(buttonBox, c);


	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new DetailsPanel());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
}
