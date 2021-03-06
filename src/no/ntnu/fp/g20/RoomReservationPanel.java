package no.ntnu.fp.g20;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import no.ntnu.fp.g20.database.DBRoom;
import no.ntnu.fp.g20.model.Room;
import no.ntnu.fp.g20.model.SortableRoomListModel;


public class RoomReservationPanel extends JPanel implements ActionListener, ListSelectionListener, ItemListener {
	private JComboBox sortBox;
	private JList roomList;
	private JButton reserveButton;
	private JButton cancelButton;
	private JButton cancelReservationButton;
	private Room model;
	private SortableRoomListModel listModel;

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
		JLabel descriptionLabel = new JLabel("Room reservation");
		layout.setConstraints(descriptionLabel, c);
		add(descriptionLabel);

		//Add the sort-by label
		JLabel sortLabel = new JLabel("Sort by");
		c.gridy++;
		layout.setConstraints(sortLabel, c);
		add(sortLabel);

		//Add the sort box
		c.gridx++;
		String[] sortType = {"Capacity", "Availaible", "Unavailable", "..."};
		sortBox = new JComboBox(sortType);
		layout.setConstraints(sortBox, c);
		add(sortBox);

		//Add the roomlist
		listModel = new SortableRoomListModel();
		roomList = new JList(listModel);
		for (int i=0; i < DBRoom.getAllRooms().size(); i++) {
			listModel.addElement(DBRoom.getRoom(i));
		}
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
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(reserveButton);
		bg.add(cancelButton);

		//Listeners
		reserveButton.addActionListener(this);
		cancelButton.addActionListener(this);
		cancelReservationButton.addActionListener(this);
		roomList.addListSelectionListener(this);
		sortBox.addItemListener(this);
	}

	public Room getModel() {
		return model;
	}

	public void setModel(Room room) {
		this.model = room;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancelButton) {
			getTopLevelAncestor().setEnabled(false);
			getTopLevelAncestor().setVisible(false);
		}
	}

	@Override
	//Handle listeners for the roomList
	public void valueChanged(ListSelectionEvent e) {
		roomList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		if (!e.getValueIsAdjusting()) {
			setModel((Room) roomList.getSelectedValue());
		}
	}

	@Override
	//Handle listeners for sort box
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			sortBox = (JComboBox)e.getSource();
			int index = sortBox.getSelectedIndex();
			if (sortBox.getSelectedItem().equals("Capacity")) {
				listModel.sortByRoomCapacity();
			}
			if (sortBox.getSelectedItem().equals("Available")) {
				//TODO: Sort list by Available
			}
			if (sortBox.getSelectedItem().equals("Unavailable")) {
				//TODO: Sort list by Unavailable
			}
		}
	}

}
