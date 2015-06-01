package ctrlayer;
import java.io.IOException;
import java.util.ArrayList;
import modellayer.*;
import org.jgrapht.Graph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.*;
import java.util.List;

public class RouteCtr {
	
	private RouteCont rCon;
	
	public RouteCtr() {
		rCon = RouteCont.getInstance();
	}
	
	//Creates a route based on the logged in user, a graph, and a start and an end point.
	public static Route createRoute(User user, Graph<Customer, Edge> g, Customer start, Customer end){
		DijkstraShortestPath<Customer, Edge> dijk = new DijkstraShortestPath<Customer, Edge>(g, start, end);
		Route route = new Route(user, start, end, dijk.getPathEdgeList(), dijk.getPathLength());
		return route;
	}
	
	//A method that creates a graph from a list of customers and edges. Uses graphs defined in JGraphT
	public static ListenableUndirectedWeightedGraph<Customer, Edge> createGraph(ArrayList<Customer> customerList, List<Edge> edgeList){
		ListenableUndirectedWeightedGraph<Customer, Edge> myGraph = 
				new ListenableUndirectedWeightedGraph<Customer, Edge>(Edge.class);
		
		//Each customer from the list is used as a vertex in the graph
		for (Customer customer :customerList){
			myGraph.addVertex(customer);
		}
		//Customer information is extracted from each edge and the edge is added between the appropriate customers. An edge weight is also added.
		for (Edge edge :edgeList){
			Customer cus1 = edge.getPointA();
			Customer cus2 = edge.getPointB();
			double weight = edge.getDistance();
			Edge currentEdge = myGraph.addEdge(cus1, cus2);
			myGraph.setEdgeWeight(currentEdge, weight);
		}
		return myGraph;	
	}
	
	//Saves the route to the route container
	public void saveRoute() throws IOException
	{
		rCon.saveToFile();
	}
	
	//Loads the route container
	public void loadRoute() throws IOException, ClassNotFoundException
	{
		rCon.loadFromFile();
	}
}
