package no.ntnu.fp.g20.model;

import javax.swing.DefaultListModel;

public class SortableRoomListModel extends DefaultListModel{
	
	private boolean isValidInsertion(Object o){
		if (!(o instanceof Room)){
			System.err.println("Tried to add non-Room object to SortableRoomListModel. Cancelled.");
			return false;
		}
		return true;
	}
	public void addElement(Object element){
		if (isValidInsertion(element)) return;
		super.addElement(element);
	}
	public void add(int index, Object element){
		if (isValidInsertion(element)) return;
		super.add(index, element);
	}
	public void insertElementAt(Object obj, int index){
		if (isValidInsertion(obj)) return;
		super.insertElementAt(obj, index);
	}
	public Object set(int index, Object element){
		if (isValidInsertion(element)) return getElementAt(index);
		return super.set(index, element);
	}
	public void setElementAt(Object obj, int index){
		if (isValidInsertion(obj)) return;
		super.setElementAt(obj, index);
	}
	
	public void removeUnavailableRooms(){
		//TODO: Make the list ditch the rooms that are already taken.
	}
	
	
	public void sortByRoomCapacity(){
		if (this.size()<2){ return; }
		SortableRoomListModel sorted = mergeSortByRoomCapacity(this);
		this.clear();
		for (int i=0; i<sorted.size(); i++){
			this.addElement(sorted.get(i));
		}
	}

	private static SortableRoomListModel mergeSortByRoomCapacity(SortableRoomListModel list) {
		int mid = (int) list.size()/2;
		SortableRoomListModel p0 = new SortableRoomListModel();
		SortableRoomListModel p1 = new SortableRoomListModel();
		//Splits:
		for (int i=0; i<mid; i++){
			p0.addElement(list.get(i));
		}
		for (int i=mid; i<list.size(); i++){
			p1.addElement(list.get(i));
		}
		//Sorts halves:
		p0 = mergeSortByRoomCapacity(p0);
		p1 = mergeSortByRoomCapacity(p1);
		//Merges and returns:
		return mergeByRoomCapacity(p0,p1);
	}
	private static SortableRoomListModel mergeByRoomCapacity(SortableRoomListModel p0, SortableRoomListModel p1){
		SortableRoomListModel merged = new SortableRoomListModel();
		int i=0;
		int j=0;
		//Merging:
		while (i<p0.size() && j<p1.size()){
			int p0_capacity = ((Room)p0.getElementAt(i)).getCapacity();
			int p1_capacity = ((Room)p1.getElementAt(j)).getCapacity();
			if (p0_capacity < p1_capacity){
				merged.addElement(p0.getElementAt(i++));
			} else {
				merged.addElement(p1.getElementAt(j++));
			}
		}
		//Adding rest once one part is empty:
		while (i<p0.size()){
			merged.addElement(i++);
		}
		while (j<p1.size()){
			merged.addElement(j++);
		}
		//Returns merged result:
		return merged;
	}
	
}











