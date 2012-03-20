package no.ntnu.fp.g20;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import no.ntnu.fp.g20.model.Appointment;

public class AppointmentListPanel extends JPanel {
	
	private GridBagConstraints c;
	
	private JLabel sortLabel;
	private JComboBox sortBox;
	private JList appointmentList;
	private JScrollPane scrollPane;
	
	private AppointmentPanel editablePanel;
	private PassiveAppointmentPanel notEditablePanel;
	
	public AppointmentListPanel() {
		
		//set layout and initial constraints
		setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		
		
		// Add the sort by label:
		sortLabel = new JLabel("Sort by: ");
		add(sortLabel, c);
		
		// Add the sort box:
		String[] sortType = { "Time", "Status" };
		c.gridx++;
		c.anchor = GridBagConstraints.WEST;
		sortBox = new JComboBox(sortType);
		add(sortBox, c);
		sortBox.addActionListener(new SortBoxListener());
		
		
		// Add the appointment list:
		appointmentList = new JList();
		appointmentList.setModel(new DefaultListModel());
		scrollPane = new JScrollPane(appointmentList);
		c.gridx = 0;
		c.gridy++;
		c.weighty = 1;
		c.weightx = 0.7;
		c.gridwidth = 2;
		c.gridheight = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.BOTH;
		add(scrollPane, c);
		
		//add the editable appointment panel
		editablePanel = new AppointmentPanel();
		c.gridy = 0;
		c.gridx = 2;
		add(editablePanel, c);
		editablePanel.setVisible(true);
		
		//add the non-editable appointment panel
		notEditablePanel = new PassiveAppointmentPanel();
		add(notEditablePanel, c);
		notEditablePanel.setVisible(false);
		
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
	
	//create listener for the appointment list
	class AppointmentListListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			//TODO alter which panel is shown depending on the users rights for that specific appointment
			//and set the the panels model to that appointment
		}
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new AppointmentListPanel());
		frame.pack();
		frame.setVisible(true);
	}
	
	

}
