package modellayer;

public class Vertex {

	private String fromID;
	private String toID;
	private double distanceTo;
	
	public Vertex() {
	}

	

	public Vertex(String fromID, String toID, double distanceTo) {
		this.fromID = fromID;
		this.toID = toID;
		this.distanceTo = distanceTo;
	}

	public String getFromID() {
		return fromID;
	}

	public void setFromID(String fromID) {
		this.fromID = fromID;
	}

	public String getToID() {
		return toID;
	}

	public void setToID(String toID) {
		this.toID = toID;
	}

	public double getDistanceTo() {
		return distanceTo;
	}

	public void setDistanceTo(double distanceTo) {
		this.distanceTo = distanceTo;
	}

	
	
	
}
