package modellayer;

public class Step {

	private int visitNo;
	private double distanceTo;
	
	public Step() {
	}

	public Step(int visitNo, double distanceTo) {
		this.visitNo = visitNo;
		this.distanceTo = distanceTo;
	}

	public int getVisitNo() {
		return visitNo;
	}

	public void setVisitNo(int visitNo) {
		this.visitNo = visitNo;
	}

	public double getDistanceTo() {
		return distanceTo;
	}

	public void setDistanceTo(double distanceTo) {
		this.distanceTo = distanceTo;
	}

	
	
	
}
