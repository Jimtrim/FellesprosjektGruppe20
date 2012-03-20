package no.ntnu.fp.g20.model;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import no.ntnu.fp.g20.Status;

public class SortableAppointmentListModel extends DefaultListModel{
	
	boolean containsNonAppointmentObject = false;
	
	public void addElement(Object o){
		if (!(o instanceof Appointment)){
			System.err.println("Put a non-Appointment object in a SortableAppointmentListModel! Most unwise. Please die.");
			containsNonAppointmentObject = true;
		}
		super.addElement(o);
	}
	
	public void sortByTime(){
		if (containsNonAppointmentObject){
			System.err.println("Contains non-Appointment object. Cannot sort by Appointment time.");
			return;
		}
		DefaultListModel sorted = mergeSort(this);
		this.clear();
		for (int i=0; i<sorted.size(); i++){
			this.addElement(sorted.get(i));
		}
	}
	private DefaultListModel mergeSort(DefaultListModel list){
		if (list.size()<2){
			return list;
		}
		DefaultListModel p0 = new DefaultListModel();
		DefaultListModel p1 = new DefaultListModel();
		
		//Splits:
		int mid = (int) list.size()/2;
		for (int i=0; i<mid; i++){
			p0.addElement(list.get(i));
		}
		for (int i=mid; i<list.size(); i++){
			p1.addElement(list.get(i));
		}
		//Sorts halves
		p0 = mergeSort(p0);
		p1 = mergeSort(p1);
		//Merges:
		return merge(p0,p1);
	}
	private DefaultListModel merge(DefaultListModel p0, DefaultListModel p1){
		DefaultListModel merged = new DefaultListModel();
		int i = 0; long iMillis;
		int j = 0; long jMillis;
		
		//Merging:
		while (i<p0.size() && j<p1.size()){
			iMillis = ((Appointment) (p0.get(i))).getStartTime().getTimeInMillis();
			jMillis = ((Appointment) (p1.get(j))).getStartTime().getTimeInMillis();
			if ( iMillis <= jMillis){
				merged.addElement(p0.get(i++));
			} else {
				merged.addElement(p1.get(j++));
			}
		}
		//Adding the rest once one of the halves is empty:
		while (i<p0.size()){
			merged.addElement(p0.get(i++));
		}
		while (j<p1.size()){
			merged.addElement(p1.get(j++));
		}
		return merged;
	}
	
	public void sortByStatus(){
		if (containsNonAppointmentObject){
			System.err.println("Contains non-Appointment object. Cannot sort by Appointment Status.");
			return;
		}
		
		sortByTime();
		
		ArrayList<Appointment> rejected = new ArrayList<Appointment>();
		ArrayList<Appointment> unconfirmed = new ArrayList<Appointment>();
		ArrayList<Appointment> confirmed = new ArrayList<Appointment>();
		DefaultListModel sorted = new DefaultListModel();
		int size = this.size();
		boolean[] done = new boolean[size];
		for (int i=0; i<size; i++){ done[i] = false; }
		int doneCount = 0;
		
		while (doneCount<size){
			for (int i=0; i<size; i++){
				if (!done[i]){
					Status status = ((Appointment) this.get(i)).getStatus();
					if (status == Status.REJECTED){
						rejected.add((Appointment) this.get(i));
					} else if (status == Status.UNCONFIRMED){
						unconfirmed.add((Appointment) this.get(i));
					} else if (status == Status.CONFIRMED){
						confirmed.add((Appointment) this.get(i));
					}
					done[i] = true; doneCount++;
				}
			}
		}
		for (int i=0; i<rejected.size(); i++){
			sorted.addElement(rejected.get(i));
		}
		for (int i=0; i<unconfirmed.size(); i++){
			sorted.addElement(unconfirmed.get(i));
		}
		for (int i=0; i<confirmed.size(); i++){
			sorted.addElement(confirmed.get(i));
		}
		
		this.clear();
		for (int i=0; i<sorted.size(); i++){
			this.addElement(sorted.get(i));
		}
	}
	
	
	
}




