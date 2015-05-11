package modellayer;

public class Customer extends Person{

	private String address;
	private Location location;
	
	public Customer() {
		super();
	}

	public Customer(String address, Location location) {
		super();
		this.address = address;
		this.location = location;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	

	
	
	
	

}
