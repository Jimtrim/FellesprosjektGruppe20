package no.ntnu.fp.g20.model;

import no.ntnu.fp.g20.model.Room.RoomStatus;

public class Room {
	private int id;
	private String name;
	private int capacity;
	private String description;
	private RoomStatus roomStatus;
	
	public Room(int id, String name, int capacity, String description) {
		this.id = id;
		this.name = name;
		this.capacity = capacity;
		this.description = description;
		
	}

	public Room(String name) {
		this.name = name;
	}

	public Room(int room_id, int appointment_id, String roomName,
			RoomStatus roomStatus2, int capacity2) {
		// TODO Auto-generated constructor stub
	}

	public void reserve(){
		
	}

	public void unreserve(){
		
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public RoomStatus getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(RoomStatus roomStatus) {
		this.roomStatus = roomStatus;
	}

	public boolean equals(Object ob){
		if (ob instanceof Room) {
			Room r = (Room)ob;
			if (this.id != r.getId()) {
				System.err.println("ID not the same");
				return false;
			}
			else if (!this.name.equals(r.getName())) {
				System.err.println("Name not the same");
				return false;
			}
			else if (!this.description.equals(r.getDescription())) {
				System.err.println("Description not the same");
				return false;
			}
			else if (this.capacity != r.getCapacity()) {
				System.err.println("Capacity not the same");
				return false;
			}
			else{
				return true;
			}
			
		} else {
			System.err.println("This is not a Room");
			return false;
		}
	}
	
	public enum RoomStatus {
		UNAVAILABLE, AVAILABLE;
	}
	
}
