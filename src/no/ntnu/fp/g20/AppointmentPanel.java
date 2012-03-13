// The appointment and meeting status panel.

package no.ntnu.fp.g20;

import java.awt.*;
import javax.swing.*;

public class AppointmentPanel extends JPanel
{
	private JComboBox sortBox;
	private JList appointmentList;
	private JTextField titleField;
	private JTextField timeField;
	private JTextField durationField;
	private JTextArea descriptionField;
	private JToggleButton approveButton, rejectButton;
	private JButton detailsButton;

	public AppointmentPanel()
	{
		super();

		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);

		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 2;
		c.ipady = 2;

		// Add the sort box:
		String[] sortType = { "Time", "Status" };
		sortBox = new JComboBox(sortType);
		layout.setConstraints(sortBox, c);
		add(sortBox);

		// Add the appointment list:
		appointmentList = new JList();
		JScrollPane scrollPane = new JScrollPane(appointmentList);
		c.gridy++;
		c.weighty = 1;
		c.gridheight = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.BOTH;
		layout.setConstraints(scrollPane, c);
		add(scrollPane);

		// Add the panel heading
		c.gridx = 1;
		c.gridy = 0;
		c.weighty = 0;
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
		c.weightx = 1;
		c.gridwidth = GridBagConstraints.REMAINDER;
		titleField = new JTextField(15);
		titleField.setEditable(false);
		layout.setConstraints(titleField, c);
		add(titleField);

		// Add the start time label:
		c.gridx--;
		c.gridy++;
		c.gridwidth = 1;
		c.weightx = 0;
		JLabel timeLabel = new JLabel("Start time:");
		layout.setConstraints(timeLabel, c);
		add(timeLabel);

		// Add the start time field:
		c.gridx++;
		c.weightx = 1;
		timeField = new JTextField(15);
		timeField.setEditable(false);
		layout.setConstraints(timeField, c);
		add(timeField);

		// Add the duration label:
		c.gridx++;
		c.weightx = 0;
		JLabel durationLabel = new JLabel("Duration: ");
		layout.setConstraints(durationLabel, c);
		add(durationLabel);

		// Add the duration field:
		c.gridx++;
		durationField = new JTextField(2);
		durationField.setEditable(false);
		layout.setConstraints(durationField, c);
		add(durationField);

		// Add the hours label:
		JLabel hourLabel = new JLabel("hours");
		c.gridx++;
		layout.setConstraints(hourLabel, c);
		add(hourLabel);

		// Add the description label:
		c.gridx = 1;
		c.gridy++;
		c.gridwidth = GridBagConstraints.REMAINDER;
		JLabel descriptionLabel = new JLabel("Description:");
		layout.setConstraints(descriptionLabel, c);
		add(descriptionLabel);

		// Add the description field:
		c.gridy++;
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		descriptionField = new JTextArea();
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

		// Add the details button:
		detailsButton = new JButton("Details");
		buttonBox.add(detailsButton);
	}

	public static void main(String[] args)
	{
		JFrame testFrame = new JFrame("Appointment Panel Test Application");
		testFrame.add(new AppointmentPanel(), BorderLayout.CENTER);
		testFrame.pack();

		testFrame.setVisible(true);
	}
}

