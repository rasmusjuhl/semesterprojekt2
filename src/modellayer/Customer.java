package modellayer;

public class Customer extends Person{

	private String password;
	
	public Customer() {
		
	}

	public Customer(String password) {
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
