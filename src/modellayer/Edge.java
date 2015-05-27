package modellayer;

import org.jgrapht.graph.DefaultWeightedEdge;

public class Edge extends DefaultWeightedEdge{
	
	private static final long serialVersionUID = 1L;
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

	@Override
	public Object getSource() {
		// TODO Auto-generated method stub
		return super.getSource();
	}

	@Override
	public Object getTarget() {
		// TODO Auto-generated method stub
		return super.getTarget();
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

	@Override
	public String toString() {
		return getSource().toString() + " : " + getTarget().toString() + " : " + getWeight();
	}

	@Override
	protected double getWeight() {
		// TODO Auto-generated method stub
		return super.getWeight();
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

}
