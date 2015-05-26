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
	
	public static void main(String[] args) throws InterruptedException {
		cCtr = new CustomerCtr();
		dbe = new DBEdge();
		//Unsure what Edge type to use. Do we extend DefaultWeightedEdge? This breaks the edges list (See console output - (null : null)
		ListenableUndirectedWeightedGraph<Customer, Edge> myGraph = 
				new ListenableUndirectedWeightedGraph<Customer, Edge>(Edge.class);
		
		ArrayList<Customer> customerList = cCtr.findAllCustomers(); 
		List<Edge> edges = dbe.findAllEdges();
		

		
		System.out.println(customerList);
		System.out.println(edges);
		
		for( Customer cust :customerList){
			myGraph.addVertex(cust);
			System.out.println("The following customer was added as a vertex:" +cust.getName());
		}
		
		for( Edge edge :edges){
			Customer cus1 =  edge.getPointA();
			Customer cus2 = edge.getPointB();
			double weight = edge.getDistance();
			Edge curEdge = myGraph.addEdge(cus1,cus2);
			myGraph.setEdgeWeight(curEdge, weight);
		}
		
		Route route = createRoute(myGraph, customerList.get(1), customerList.get(2));
		System.out.println("The following path was found to be the shortest:");
		for (Edge edge :route.getEdges()){
			edge.getPointA().getName();
			edge.getPointB().getName();
		}
		System.out.println("The route length is:" + route.getRouteLength());
		
	}
	
	public static Route createRoute(Graph<Customer, Edge> g, Customer start, Customer end) // should take 2 Customers
	{
		DijkstraShortestPath<Customer, Edge> dijk = new DijkstraShortestPath<Customer, Edge>(g, start, end);
		Route route = new Route(start, end, dijk.getPathEdgeList(), dijk.getPathLength());
		return route;
	}
	
	

}
