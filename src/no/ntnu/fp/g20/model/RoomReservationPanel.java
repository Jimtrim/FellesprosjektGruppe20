package no.ntnu.fp.g20.model;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class RoomReservationPanel extends JPanel {
	private JComboBox sortBox;
	private JList roomList;
	private JButton reserveButton;
	private JButton cancelButton;
	private JButton cancelReservationButton;
	
	public RoomReservationPanel(){
		super();
		
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(layout);
		
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 2;
		c.ipady = 2;
		
		//Add the description
		JLabel descriptionLabel = new JLabel("Room reservation in chosen period");
		layout.setConstraints(descriptionLabel, c);
		add(descriptionLabel);
		
		//Add the sort-by label
		JLabel sortLabel = new JLabel("Sort by");
		c.gridy++;
		layout.setConstraints(sortLabel, c);
		add(sortLabel);
		
		//Add the sort box
		String[] sortType = {"Capacity", "Availaible", "Unavailable", "..."};
		sortBox = new JComboBox(sortType);
		c.gridx++;
		layout.setConstraints(sortBox, c);
		add(sortBox);
		
		//Add the roomlist
		roomList = new JList();
		JScrollPane scrollPane = new JScrollPane(roomList);
		c.gridx = 0;
		c.gridy++;
		layout.setConstraints(scrollPane, c);
		add(scrollPane);
		
		//Add the reserveButton
		reserveButton = new JButton("Reserve");
		c.gridy++;
		layout.setConstraints(reserveButton, c);
		add(reserveButton);
		
		//Add the cancelReservationButton
		cancelReservationButton = new JButton("Cancel reservation");
		c.gridx++;
		layout.setConstraints(cancelReservationButton, c);
		add(cancelReservationButton);
		
		//Add the cancelButton
		cancelButton = new JButton("Cancel");
		c.gridx++;
		layout.setConstraints(cancelButton, c);
		add(cancelButton);
		
		
	}
	public static void main(String[] args) {
		JFrame testFrame = new JFrame("RoomreservationPanel Test");
		testFrame.add(new RoomReservationPanel(), BorderLayout.CENTER);
		testFrame.pack();
		
		testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		testFrame.setVisible(true);
	}

}
