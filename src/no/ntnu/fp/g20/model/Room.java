package no.ntnu.fp.g20.model;

public class Room {
	public String toString(){ return name+" ("+capacity+")"; }
	
	private int id;
	private int appointmentID;
	private String name;
	private int capacity;
	private String description;
	private RoomStatus roomStatus;
	
	public Room(int id, String name, String description, int capacity) {
		this.id = id;
		this.name = name;
		this.capacity = capacity;
		this.description = description;
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
	
	public int getAppointmentID() {
		return appointmentID;
	}

	public void setAppointmentID(int appointmentID) {
		this.appointmentID = appointmentID;
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
