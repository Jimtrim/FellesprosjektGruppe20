package no.ntnu.fp.g20.model;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import no.ntnu.fp.g20.Status;

public class SortableAppointmentListModel extends DefaultListModel{
	private boolean isValidInsertion(Object o){
		if (!(o instanceof Appointment)){
			System.err.println("Tried to add non-Appointment object to SortableAppointmentListModel. Cancelled.");
			return false;
		}
		return true;
	}
	public void addElement(Object element){
		if (!isValidInsertion(element)) return;
		super.addElement(element);
	}
	public void add(int index, Object element){
		if (!isValidInsertion(element)) return;
		super.add(index, element);
	}
	public void insertElementAt(Object obj, int index){
		if (!isValidInsertion(obj)) return;
		super.insertElementAt(obj, index);
	}
	public Object set(int index, Object element){
		if (!isValidInsertion(element)) return getElementAt(index);
		return super.set(index, element);
	}
	public void setElementAt(Object obj, int index){
		if (!isValidInsertion(obj)) return;
		super.setElementAt(obj, index);
	}
	
	public void sortByTime(){
		if (this.size()<2){ return; }
		System.out.println("Sorting Appointment list by time: "+this);
		SortableAppointmentListModel sorted = mergeSortByTime(this);
		System.out.println("Sorted list:                      "+sorted);
		this.clear();
		for (int i=0; i<sorted.size(); i++){
			this.addElement(sorted.get(i));
		}
	}
	private static SortableAppointmentListModel mergeSortByTime(SortableAppointmentListModel list){
		if (list.size()<2){
			return list;
		}
		int mid = (int) list.size()/2;
		SortableAppointmentListModel p0 = new SortableAppointmentListModel();
		SortableAppointmentListModel p1 = new SortableAppointmentListModel();
		//Splits:
		for (int i=0; i<mid; i++){
			p0.addElement(list.get(i));
		}
		for (int i=mid; i<list.size(); i++){
			p1.addElement(list.get(i));
		}
		//Sorts halves
		p0 = mergeSortByTime(p0);
		p1 = mergeSortByTime(p1);
		//Merges and returns:
		return mergeByTime(p0,p1);
	}
	private static SortableAppointmentListModel mergeByTime(DefaultListModel p0, DefaultListModel p1){
		SortableAppointmentListModel merged = new SortableAppointmentListModel();
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
		
		sortByTime();
		
		ArrayList<Appointment> rejected = new ArrayList<Appointment>();
		ArrayList<Appointment> unconfirmed = new ArrayList<Appointment>();
		ArrayList<Appointment> confirmed = new ArrayList<Appointment>();
		ArrayList<Appointment> sorted = new ArrayList<Appointment>();
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
			sorted.add(rejected.get(i));
		}
		for (int i=0; i<unconfirmed.size(); i++){
			sorted.add(unconfirmed.get(i));
		}
		for (int i=0; i<confirmed.size(); i++){
			sorted.add(confirmed.get(i));
		}
		
		this.clear();
		for (int i=0; i<sorted.size(); i++){
			this.addElement(sorted.get(i));
		}
	}
	
	
	
}




