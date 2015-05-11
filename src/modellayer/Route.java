package modellayer;

import java.util.List;

public class Route {
	
	private String date;
	private List<Step> routeSteps;

	public Route() {
	}

	public Route(String date, List<Step> routeSteps) {
		super();
		this.date = date;
		this.routeSteps = routeSteps;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<Step> getRouteSteps() {
		return routeSteps;
	}

	public void setRouteSteps(List<Step> routeSteps) {
		this.routeSteps = routeSteps;
	}

	
	
	
}
