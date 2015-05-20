package ctrlayer;
import java.util.ArrayList;

import dblayer.*;
import modellayer.*;

import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.*;

import java.util.List;

public class RouteCtr {
	
	private CustomerCtr cCtr;
	private DBEdge dbe;

	public RouteCtr() {
		cCtr = new CustomerCtr();
		dbe = new DBEdge();
	}
	
	public ArrayList<Customer> createRoute() // should take 2 Customers
	{
		ListenableUndirectedWeightedGraph<Customer, DefaultWeightedEdge> myGraph = 
				new ListenableUndirectedWeightedGraph<Customer, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		// Add customers as vertexs
		ArrayList<Customer> customerList = cCtr.findAllCustomers();
		for(Customer cus : customerList)
		{
		myGraph.addVertex(cus);
		}
		
		// Add edges as edges		
		ArrayList<Edge> edges = dbe.findAllEdges();
		
//		for(Edge edge : edges)
//		{
//			Customer cusA = edge.getPointA();
//			Customer cusB = edge.getPointB();
//			DefaultWeightedEdge e1 = myGraph.addEdge(cusA, cusB);
//		}
		
		for(int i = 0; i < edges.size(); i++)
		{
			Edge edge = edges.get(i);
			Customer cusA = edge.getPointA();
			Customer cusB = edge.getPointB();
			myGraph.addEdge(cusA, cusB);
			
		}
		
		
		System.out.println(myGraph);
		return customerList;
		
	}

}
