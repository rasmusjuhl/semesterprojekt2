package ctrlayer;
import java.util.ArrayList;

import dblayer.*;
import modellayer.*;

import org.jgrapht.Graph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.*;

import java.util.List;

public class RouteCtr {
	
	private static CustomerCtr cCtr;
	private static DBEdge dbe;

	public RouteCtr() {
		cCtr = new CustomerCtr();
		dbe = new DBEdge();
	}
	
	public static Route createRoute(User user, Graph<Customer, Edge> g, Customer start, Customer end) // should take 2 Customers
	{
		DijkstraShortestPath<Customer, Edge> dijk = new DijkstraShortestPath<Customer, Edge>(g, start, end);
		Route route = new Route(user, start, end, dijk.getPathEdgeList(), dijk.getPathLength());
		return route;
	}
	
	public static ListenableUndirectedWeightedGraph<Customer, Edge> createGraph(ArrayList<Customer> customerList, List<Edge> edgeList){
		ListenableUndirectedWeightedGraph<Customer, Edge> myGraph = 
				new ListenableUndirectedWeightedGraph<Customer, Edge>(Edge.class);
		for (Customer customer :customerList){
			myGraph.addVertex(customer);
		}
		
		for (Edge edge :edgeList){
			Customer cus1 = edge.getPointA();
			Customer cus2 = edge.getPointB();
			double weight = edge.getDistance();
			Edge currentEdge = myGraph.addEdge(cus1, cus2);
			myGraph.setEdgeWeight(currentEdge, weight);
		}
		
		return myGraph;
			
	}
}
