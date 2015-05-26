package modellayer;

import java.util.Date;
import java.util.List;

public class Route {
	
	private Date date;
	private Customer start;
	private Customer end;
	private List<Edge> edges;
	private double routeLength;

	public Route() {
	}

	public Route(Customer start, Customer end, List<Edge> edges, Double routeLength) {
		this.date = new Date();
		this.start = start;
		this.end = end;
		this.edges = edges;
		this.routeLength = routeLength;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Customer getStart() {
		return start;
	}

	public void setStart(Customer start) {
		this.start = start;
	}

	public Customer getEnd() {
		return end;
	}

	public void setEnd(Customer end) {
		this.end = end;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}

	public double getRouteLength() {
		return routeLength;
	}

	public void setRouteLength(double routeLength) {
		this.routeLength = routeLength;
	}

	
	
}
