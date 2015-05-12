package modellayer;

import java.util.ArrayList;

public class Route {
	
	private String date;
	private ArrayList<Vertex> edges;

	public Route() {
	}

	public Route(String date, ArrayList<Vertex> edges) {
		this.date = date;
		this.edges = edges;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ArrayList<Vertex> getEdges() {
		return edges;
	}

	public void setEdges(ArrayList<Vertex> edges) {
		this.edges = edges;
	}

	
	
	
}
