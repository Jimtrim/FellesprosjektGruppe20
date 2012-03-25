package no.ntnu.fp.g20.model;

public class User {
	private int id;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	
	public User(int id, String username, String password, String firstname, String lastname) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public User(int id, String username, String firstname, String lastname)
	{
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstname;
	}
	
	public void setFirstName(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastName() {
		return lastname;
	}
	
	public void setLastName(String lastName) {
		this.lastname = lastname;
	}
	
	public boolean equals(Object ob){
		if (ob instanceof User) {
			User u = (User) ob;
			if (id != u.getId()) {
				System.err.println("ID was not the same");
				return false;
			}
			else if (!firstname.equals(u.getFirstName())) {
				System.err.println("Firstname was not the same");
				return false;
			
			}
			else if (!lastname.equals(u.getLastName())) {
				System.err.println("Lastname was not the same");
				return false;
			
			} else if (!password.equals(u.password)) {
				System.err.println("Password was not the same");
				return false;
				
			} else if (!username.equals(u.username)) {
				System.err.println("Username was not the same");
				return false;
			} else {
				return true;
			}
		}
		else{
			return false;
		}
	}
	
	public String toString()
	{
		return (lastname + ", " + firstname);
	}

}
