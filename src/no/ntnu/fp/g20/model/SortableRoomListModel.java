package no.ntnu.fp.g20.model;

import no.ntnu.fp.g20.*;
import javax.swing.*;
import java.util.*;

public class SortableRoomListModel extends DefaultListModel{
	
	public SortableRoomListModel()
	{
		LinkedList<Room> rooms = CalendarApp.getApplication().getConnection().getRoomList();
		for(Room room : rooms)
			addElement(room);
	}

	private boolean isValidInsertion(Object o){
		if (!(o instanceof Room)){
			System.err.println("Tried to add non-Room object to SortableRoomListModel. Cancelled.");
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
	
	public void removeUnavailableRooms(){
		//TODO: Make the list ditch the rooms that are already taken.
	}
	
	public static final int SORT_BY_CAPACITY = 0;
	public static final int SORT_BY_NAME = 1;
	
	private static boolean isValidField(int field){
		if ( !(field == SORT_BY_CAPACITY || field == SORT_BY_NAME) ){
			System.err.println("Attempted mergeSortByRoomField with bad field. Use SORT_BY_CAPACITY or SORT_BY_NAME.");
			return false;
		}
		return true;
	}
	
	public void sortByRoomCapacity(){
		if (this.size()<2){ return; }
		System.out.println("Sorting list by Room.capacity: "+this);
		SortableRoomListModel sorted = mergeSortByRoomField(this, SORT_BY_CAPACITY);
		System.out.println("Sorted list:                   "+sorted);
		if (sorted == null){
			return;
		}
		this.clear();
		for (int i=0; i<sorted.size(); i++){
			this.addElement(sorted.get(i));
		}
	}
	public void sortByRoomName(){
		if (this.size()<2){ return; }
		System.out.println("Sorting list by Room.name: "+this);
		SortableRoomListModel sorted = mergeSortByRoomField(this, SORT_BY_NAME);
		System.out.println("Sorted list:               "+sorted);
		if (sorted == null){
			return;
		}
		this.clear();
		for (int i=0; i<sorted.size(); i++){
			this.addElement(sorted.get(i));
		}
	}
	
	private static SortableRoomListModel mergeSortByRoomField(SortableRoomListModel list, int field) {
		if (list.size()<2){
			return list;
		}
		if (!isValidField(field)){ return null; }
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
		p0 = mergeSortByRoomField(p0, field);
		p1 = mergeSortByRoomField(p1, field);
		//Merges and returns:
		return mergeByRoomField(p0, p1, field);
	}

	private static SortableRoomListModel mergeByRoomField(SortableRoomListModel p0, SortableRoomListModel p1, int field){
		SortableRoomListModel merged = new SortableRoomListModel();
		int i=0; int p0_capacity;
		int j=0; int p1_capacity;
		//Merging:
		if (field == SORT_BY_CAPACITY){
			while (i<p0.size() && j<p1.size()){
				p0_capacity = ((Room)p0.getElementAt(i)).getCapacity();
				p1_capacity = ((Room)p1.getElementAt(j)).getCapacity();
				if (p0_capacity < p1_capacity){
					merged.addElement(p0.getElementAt(i++));
				} else {
					merged.addElement(p1.getElementAt(j++));
				}
			}
		} else if (field == SORT_BY_NAME){
			while (i<p0.size() && j<p1.size()){
				String p0_name = ((Room) p0.getElementAt(i)).getName();
				String p1_name = ((Room) p1.getElementAt(j)).getName();
				if (p0_name.compareToIgnoreCase(p1_name) < 0){
					merged.addElement(p0.getElementAt(i++));
				} else {
					merged.addElement(p1.getElementAt(j++));
				}
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











