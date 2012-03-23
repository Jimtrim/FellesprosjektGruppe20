package no.ntnu.fp.g20.model;

import no.ntnu.fp.g20.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.table.AbstractTableModel;

public class CalendarModel extends AbstractTableModel implements AppointmentListener {
	private Appointment[][] appointments;
	private User user;
	private PropertyChangeSupport pcs;
	
	public CalendarModel(){
		pcs = new PropertyChangeSupport(this);
		
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void appointmentAdded(Appointment appointment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void appointmentUpdated(Appointment appointment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void appointmentDeleted(Appointment appointment) {
		// TODO Auto-generated method stub
		
	}

}
