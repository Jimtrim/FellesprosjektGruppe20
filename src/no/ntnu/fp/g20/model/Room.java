package no.ntnu.fp.g20.model;

public class Room {
	private int id;
	private int appointmentID;
	private String name;
	private int capacity;
	
	public Room(int id, String name, int capacity) {
		this.id = id;
		this.name = name;
		this.capacity = capacity;
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
	
	public boolean equals(Object ob){
		if (ob instanceof Room) {
			Room r = (Room)ob;
			if (this.id != r.getId()) {
				System.err.println("ID not the same");
				return false;
			} else if (!this.name.equals(r.getName())) {
				System.err.println("Name not the same");
				return false;
			} else if (this.capacity != r.getCapacity()) {
				System.err.println("Capacity not the same");
				return false;
			} else
				return true;
		} else {
			System.err.println("This is not a Room");
			return false;
		}
	}
	
	public String toString()
	{
		return name + " (" + capacity + ")";
	}
}
