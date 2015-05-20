package modellayer;

public class Edge {
	private Customer pointA;
	private Customer pointB;
	private double distance;

	public Edge() {
		
	}

	public Edge(Customer pointA, Customer pointB, double distance) {
		this.pointA = pointA;
		this.pointB = pointB;
		this.distance = distance;
	}

	public Customer getPointA() {
		return pointA;
	}

	public void setPointA(Customer pointA) {
		this.pointA = pointA;
	}

	public Customer getPointB() {
		return pointB;
	}

	public void setPointB(Customer pointB) {
		this.pointB = pointB;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

}
