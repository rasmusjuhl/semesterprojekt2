package modellayer;

import java.util.ArrayList;

public class Route {
	
	private String date;
	private User user;
	private Customer start;
	private Customer end;
	private ArrayList<Edge> edges;

	public Route() {
	}

	public Route(User user, String date, Customer start, Customer end,	ArrayList<Edge> edges) {
		this.date = date;
		this.start = start;
		this.end = end;
		this.edges = edges;
		this.user = user;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
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

	public ArrayList<Edge> getEdges() {
		return edges;
	}

	public void setEdges(ArrayList<Edge> edges) {
		this.edges = edges;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}
	
}
