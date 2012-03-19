package no.ntnu.fp.g20.model;

public class Room {
	private int id;
	private String name;
	private int capacity;
	private Available available;
	private String description;
	
	public Room(int id, String name, int capacity, Available available, String description) {
		this.id = id;
		this.name = name;
		this.capacity = capacity;
		this.available = available;
		this.description = description;
		
	}

	public void reserve(){
		
	}

	public void unreserve(){
		
	}

	public Available getAvailable() {
		return available;
	}

	public void setAvailable(Available available) {
		this.available = available;
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
			else if (!this.available.equals(r.getAvailable())) {
				System.err.println("Available not the same");
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
	
	public enum Available{
		AVAILABLE, UNAVAILABLE;
	}

}
