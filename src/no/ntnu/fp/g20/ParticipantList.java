import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class ParticipantList extends JDialog{
	
	public static void main(String[] args){
		ParticipantList participantList = new ParticipantList();
		participantList.setVisible(true);
	}
	
	JScrollPane users;
	JScrollPane participantPane;
	
	JList userList;
	JList participantList;
	
	DefaultListModel userListModel;
	DefaultListModel participantListModel;
	
	JButton addSelected;
	JButton removeSelected;
	JButton addAll;
	JButton removeAll;
	JButton doneButton;
	JButton cancelButton;
	
	JLabel usersLabel;
	JLabel participantsLabel;
	
	GridBagConstraints c;
	
	public ParticipantList(){
		this.setLayout(new GridBagLayout());
		this.setTitle("Add/remove participants");
		
		c = new GridBagConstraints();
		
		
		
		//Labels:
		usersLabel = new JLabel("Users:");
		participantsLabel = new JLabel("Participants:");
		
		//Users:
		userList = new JList(); //TODO: get user list
		userList.setBorder(BorderFactory.createLoweredBevelBorder());
		users = new JScrollPane(userList);
		
		//"Add selected" button:
		addSelected = new JButton("Add selected");
		addSelected.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] selectedIndices = userList.getSelectedIndices();
				for (int i=0; i<selectedIndices.length; i++){
					participantListModel.addElement(userListModel.getElementAt(selectedIndices[i]));
				}
				for (int i=selectedIndices.length-1; i>=0; i--){
					userListModel.removeElementAt(selectedIndices[i]);
				}
				userList.setSelectedIndex(selectedIndices[0]);
				//TODO(?): Sort participant list?
			}
		});
		
		//"Remove selected" button:
		removeSelected = new JButton("Remove selected");
		removeSelected.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] selectedIndices = participantList.getSelectedIndices();
				for (int i=0; i<selectedIndices.length; i++){
					userListModel.addElement(participantListModel.getElementAt(selectedIndices[i]));
				}
				for (int i=selectedIndices.length-1; i>=0; i--){
					participantListModel.removeElementAt(selectedIndices[i]);
				}
				participantList.setSelectedIndex(selectedIndices[0]);
				//TODO(?): Sort user list?
			}
		});
		
		//"Add all" button
		addAll = new JButton("Add all");
		addAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] allIndices = new int[userListModel.size()];
				for (int i=0; i<userListModel.size(); i++){
					allIndices[i] = i;
				}
				userList.setSelectedIndices(allIndices);
				addSelected.doClick();
				//TODO(?): Sort participant list?
			}
		});
		
		//"Remove all" button
		removeAll = new JButton("Remove all");
		removeAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] allIndices = new int[participantListModel.size()];
				for (int i=0; i<participantListModel.size(); i++){
					allIndices[i] = i;
				}
				participantList.setSelectedIndices(allIndices);
				removeSelected.doClick();
				//TODO(?): Sort user list?
			}
		});
		
		//"Done" button
		doneButton = new JButton("Done");
		doneButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Save changes
				// TODO Close dialog
			}
		});
		
		//"Cancel" button
		cancelButton = new JButton("Cancel");
		cancelButton.setForeground(new Color(170,0,0));
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Close dialog (without saving)
			}
		});
		
		
		
		//Participants:
		participantList = new JList(); //TODO: get participant list
		participantList.setBorder(BorderFactory.createLoweredBevelBorder());
		participantPane = new JScrollPane(participantList);
		
		
		//MODELS:
		userListModel = new DefaultListModel();
		userList.setModel(userListModel);
		participantListModel = new DefaultListModel();
		participantList.setModel(participantListModel);
		
		//TODO: 'Tis a test. Remove. Adds test persons to move around.
		for (Person p : new ExamplePersonArrayList()){
			userListModel.addElement(p);
		}
		
		
		//ADDING:
		
		Insets insetsNormal = new Insets(2, 5, 2, 5);
		Insets insetsLargeUpper = new Insets(15, 5, 2, 5);
		Insets insetsVeryLargeUpper = new Insets(35, 5, 2, 5);
		Insets insetsLargeUpperMediumBottom = new Insets(15, 5, 5, 5);
		Insets insetsMediumBottom = new Insets(2, 5, 5, 5);
		
		c.insets = insetsNormal;
		
		c.fill = GridBagConstraints.BOTH;
		
		//Col 0:
		
		c.gridx = 0;
		c.gridy = 0;
		add(usersLabel,c);
		
		c.gridy++;
		c.gridheight = GridBagConstraints.REMAINDER;
		add(users,c);
		
		//Col 1:
		
		c.insets = insetsVeryLargeUpper;
		c.gridx++;
		c.gridy = 1;
		c.gridheight = 1;
		add(addSelected,c);
		
		c.insets = insetsNormal;
		c.gridy++;
		add(removeSelected,c);
		
		c.insets = insetsLargeUpper;
		c.gridy++;
		add(addAll,c);
		
		c.insets = insetsNormal;
		c.gridy++;
		add(removeAll,c);
		
		c.insets = insetsLargeUpper;
		c.gridy++;
		add(cancelButton,c);
		
		c.insets = insetsMediumBottom;
		c.gridy++;
		add(doneButton,c);
		
		//Col 2:
		
		c.insets = insetsNormal;
		c.gridx++;
		c.gridy = 0;
		add(participantsLabel,c);
		
		c.gridy++;
		c.gridheight = GridBagConstraints.REMAINDER;
		add(participantPane,c);
		
		
		this.pack();
	}
	
}
















