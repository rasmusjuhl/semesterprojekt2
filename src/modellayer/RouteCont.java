package modellayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * En container til at gemme routes lokalt.
 * 
 * @author Kasper
 */
public class RouteCont {

	private ArrayList<Route> routes;
	private static RouteCont instance;
	private RouteFileHandler fileHandler;
	
	public RouteCont()
	{
		routes = new ArrayList<Route>();
		fileHandler = new RouteFileHandler("routeData");
	}
	
	public static RouteCont getInstance()
	{
		if(instance == null)
		{
			instance = new RouteCont();
		}
		return instance;
	}
	
	public ArrayList<Route> getList()
	{
		return routes;
	}
	
	public void addRoute(Route r)
	{
		routes.add(r);
	}
	
	public Route findRoute(User user, Date date)
	{
		Route returnRoute = null;
		boolean found = false;
		int size = routes.size();
		for(int i = 0; i < size && !found; i++)
		{
			if(routes.get(i).getUser() == user && routes.get(i).getDate() == date)
			{
				returnRoute = routes.get(i);
				found = true;
			}
		}
		return returnRoute;
	}
	
	public void deleteRoute(User user, Date date)
	{
		Route tempR = findRoute(user, date);
		routes.remove(tempR);
	}
	
	public void saveToFile() throws IOException
	{
		fileHandler.saveToFileRoute(routes);
	}
	
	public void loadFromFile() throws IOException, ClassNotFoundException
	{
		routes = fileHandler.readFromFileRoute();
	}
}
