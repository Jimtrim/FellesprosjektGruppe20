package no.ntnu.fp.g20.model;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

public class SortableUserListModel extends DefaultListModel{
	
	private boolean isValidInsertion(Object o){
		if (!(o instanceof User)){
			System.err.println("Tried to add non-User object to SortableRoomListModel. Cancelled.");
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
	
	public void sortByLastName(){
		if (this.size()<2){ return; }
		System.out.println("Sorting list: "+this);
		SortableUserListModel sorted = mergeSortByLastName(this);
		System.out.println("Sorted list:  "+sorted);
		this.clear();
		for (int i=0; i<sorted.size(); i++){
			this.addElement(sorted.get(i));
		}
	}

	private static SortableUserListModel mergeSortByLastName(SortableUserListModel list) {
		if (list.size()<2){
			return list;
		}
		int mid = (int) list.size()/2;
		SortableUserListModel p0 = new SortableUserListModel();
		SortableUserListModel p1 = new SortableUserListModel();
		//Splits:
		for (int i=0; i<mid; i++){
			p0.addElement(list.get(i));
		}
		for (int i=mid; i<list.size(); i++){
			p1.addElement(list.get(i));
		}
		//Sorts halves:
		p0 = mergeSortByLastName(p0);
		p1 = mergeSortByLastName(p1);
		//Merges and returns:
		return mergeByLastName(p0,p1);
	}
	private static SortableUserListModel mergeByLastName(SortableUserListModel p0, SortableUserListModel p1){
		SortableUserListModel merged = new SortableUserListModel();
		int i=0;
		int j=0;
		//Merging:
		while (i<p0.size() && j<p1.size()){
			String p0_lastName = ((User)p0.getElementAt(i)).getLastName();
			String p1_lastName = ((User)p1.getElementAt(j)).getLastName();
			if (p0_lastName.compareToIgnoreCase(p1_lastName) <= 0){
				merged.addElement(p0.getElementAt(i++));
			} else {
				merged.addElement(p1.getElementAt(j++));
			}
		}
		//Adding rest once one part is empty:
		while (i<p0.size()){
			merged.addElement(p0.get(i++));
		}
		while (j<p1.size()){
			merged.addElement(p1.get(j++));
		}
		//Returns merged result:
		return merged;
	}
	
}









