package modellayer;

public class Location {

	private String zipCode;
	private String city;
	
	public Location()
	{
		
	}

	public Location(String zipCode, String city) {
		this.zipCode = zipCode;
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	
	
}
