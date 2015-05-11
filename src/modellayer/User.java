package modellayer;

public class User extends Person{
	
	private String password;


	public User() {
		super();
	}

	
	public User(String password) {
		super();
		this.password = password;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
