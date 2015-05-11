package modellayer;

public class Customer extends Person{

	private String address;
	private String zipCode;
	private Location location;
	
	public Customer() {
		super();
	}

	public Customer(String name, String phone, String email, String address, String zipCode, Location location) {
		super(name, phone, email);
		this.address = address;
		this.zipCode = zipCode;
		this.location = location;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	

	
	
	
	

}
