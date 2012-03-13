import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.TextAction;
import javax.swing.JScrollPane;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.JEditorPane;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


public class MainPage extends JPanel {

	private JFrame frame;
	
	private final ButtonGroup AddRemove = new ButtonGroup();
	
	
	 private JTable table_1;
	/**
	 * @wbp.parser.entryPoint
	 */
	public MainPage() {
		
		//frame
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.GRAY);
		Component tabbedPane = null;
		frame.getContentPane().add(tabbedPane);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		
		//tabs
		JPanel panel = new JPanel();
		tabbedPane_1.addTab("Detaljer", null, panel, null);
		
		JPanel panel_1 = new JPanel();
		tabbedPane_1.addTab("Møteliste", null, panel_1, null);
		
		//table
		String [] columNames = {"Tid", "Mandag", "Tirsdag", "Onsdag", "Torsdag", "Fredag"};
		//Object [] data = {
		//{"07:00",null, null, null, null, null},{"08:00"},{"09:00"},{"10:00"},{"11:00"},{"12:00"},{"13:00"},{"14:00"},
		//{"15:00"},{"16:00"},{"17:00"},{"18:00"},{"19:00"},{"20:00"},{"21:00"},{"22:00"}}
		
		
		
		//addApointment
	
		
		//removeAppointment
		JButton addApointment = new JButton("ico");
		addApointment.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JButton removeApointment = new JButton("ico");
		removeApointment.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		JButton addCalendar = new JButton("+");
		removeApointment.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JButton removeCalendar = new JButton("-");
		removeApointment.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JButton lastWeek = new JButton("Forrige uke");
		removeApointment.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JButton nextWeek = new JButton("Neste uke");
		removeApointment.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		TableModel dataModel = new AbstractTableModel() {
			public int getColumnCount() { return 6; }
			public int getRowCount() { return 15;}
			public Object getValueAt(int row, int col) { return new Integer(row*col); }
		};
		
		table_1 = new JTable(dataModel);
		
		
	 
	      
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(removeApointment)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(addApointment, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addGap(51)
					.addComponent(addCalendar, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(removeCalendar, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(nextWeek)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lastWeek))
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(tabbedPane_1, GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(table_1, GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(addCalendar)
						.addComponent(removeCalendar)
						.addComponent(nextWeek)
						.addComponent(lastWeek)
						.addComponent(removeApointment)
						.addComponent(addApointment))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(table_1, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tabbedPane_1, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
	
	}
		
		 public static void main(String[] args) {
		        JFrame frame = new JFrame("");
		        frame.setContentPane(new MainPage());
		        frame.pack();
		        frame.setVisible(true);
        

	}
}
