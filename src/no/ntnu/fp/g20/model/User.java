package no.ntnu.fp.g20.model;

public class User {
	
	private int id;
	private String username;
	private String password;
	private String firstname;
	private String lastName;
	
	public User(int id, String username, String password, String firstname, String lastName) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastName = lastName;
	}

	public User()
	{
		id = 0;
	}
	
	public User(String uName, String pwd, String fName, String lName) {
		this(0, uName, pwd, fName, lName);
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
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public boolean equals(Object ob){
		if (ob instanceof User) {
			User u = (User) ob;
			if (this.id != u.getId()) {
				System.err.println("ID was not the same");
				return false;
			}
			else if (!this.firstname.equals(u.getFirstname())) {
				System.err.println("Firstname was not the same");
				return false;
			
			}
			else if (!this.lastName.equals(u.getLastName())) {
				System.err.println("Lastname was not the same");
				return false;
			
			} else if (!this.password.equals(u.password)) {
				System.err.println("Password was not the same");
				return false;
				
			} else if (!this.username.equals(u.username)) {
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
	
	public void login(String username, String password){
		
	}
	
	

}
