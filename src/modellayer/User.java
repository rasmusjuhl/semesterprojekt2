package modellayer;

public class User extends Person{
	
	private String password;


	public User() {
		super();
	}

	
	public User(String name, String phone, String email, String password) {
		super(name, phone, email);
		this.password = password;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
