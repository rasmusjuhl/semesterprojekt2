package ctrlayer;
import java.util.ArrayList;

import dblayer.*;
import modellayer.*;

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
	
	public static void main(String[] args) {
		cCtr = new CustomerCtr();
		dbe = new DBEdge();
		ListenableUndirectedWeightedGraph<Customer, DefaultWeightedEdge> myGraph = 
				new ListenableUndirectedWeightedGraph<Customer, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		ArrayList<Customer> customerList = cCtr.findAllCustomers(); 
		ArrayList<Edge> edges = dbe.findAllEdges();
		
		for(int i = 0; i < customerList.size(); i++)
		{
			myGraph.addVertex(customerList.get(i));
		}
		
		
		System.out.println("\ncustomers");
		for(int i = 0; i < customerList.size(); i++)
		{
			System.out.println(customerList.get(i).getName());
		}
		System.out.println("\nedges");
		for(int i = 0; i < edges.size(); i++)
		{
			System.out.println(edges.get(i).getPointA().getName() + " -> " + edges.get(i).getPointB().getName());
		}
		
		
//		for(int j = 0; j < edges.size(); j++)
//		{
//			DefaultWeightedEdge e = myGraph.addEdge(edges.get(j).getPointA(), edges.get(j).getPointB());
//			myGraph.setEdgeWeight(e, edges.get(j).getDistance());
//		}
		
		
//		DefaultWeightedEdge e1 = myGraph.addEdge(edges.get(0).getPointA(), edges.get(0).getPointB());		
		DefaultWeightedEdge e1 = myGraph.addEdge(customerList.get(0), customerList.get(4));
		
		DefaultWeightedEdge e2 = myGraph.addEdge(customerList.get(1), customerList.get(3));
		DefaultWeightedEdge e3 = myGraph.addEdge(customerList.get(2), customerList.get(3));
		DefaultWeightedEdge e4 = myGraph.addEdge(customerList.get(4), customerList.get(5));
		DefaultWeightedEdge e5 = myGraph.addEdge(customerList.get(5), customerList.get(6));
		DefaultWeightedEdge e6 = myGraph.addEdge(customerList.get(5), customerList.get(10));
		DefaultWeightedEdge e7 = myGraph.addEdge(customerList.get(6), customerList.get(7));
		DefaultWeightedEdge e8 = myGraph.addEdge(customerList.get(6), customerList.get(9));
		DefaultWeightedEdge e9 = myGraph.addEdge(customerList.get(6), customerList.get(11));
		DefaultWeightedEdge e10 = myGraph.addEdge(customerList.get(7), customerList.get(2));
		DefaultWeightedEdge e11 = myGraph.addEdge(customerList.get(7), customerList.get(8));
		DefaultWeightedEdge e12 = myGraph.addEdge(customerList.get(8), customerList.get(9));
		DefaultWeightedEdge e13 = myGraph.addEdge(customerList.get(10), customerList.get(2));
		DefaultWeightedEdge e14 = myGraph.addEdge(customerList.get(10), customerList.get(11));
		DefaultWeightedEdge e15 = myGraph.addEdge(customerList.get(11), customerList.get(1));
		
		myGraph.setEdgeWeight(e1, edges.get(0).getDistance());
		myGraph.setEdgeWeight(e2, edges.get(1).getDistance());
		myGraph.setEdgeWeight(e3, edges.get(2).getDistance());
		myGraph.setEdgeWeight(e4, edges.get(3).getDistance());
		myGraph.setEdgeWeight(e5, edges.get(4).getDistance());
		myGraph.setEdgeWeight(e6, edges.get(5).getDistance());
		myGraph.setEdgeWeight(e7, edges.get(6).getDistance());
		myGraph.setEdgeWeight(e8, edges.get(7).getDistance());
		myGraph.setEdgeWeight(e9, edges.get(8).getDistance());
		myGraph.setEdgeWeight(e10, edges.get(9).getDistance());
		myGraph.setEdgeWeight(e11, edges.get(10).getDistance());
		myGraph.setEdgeWeight(e12, edges.get(11).getDistance());
		myGraph.setEdgeWeight(e13, edges.get(12).getDistance());
		myGraph.setEdgeWeight(e14, edges.get(13).getDistance());
		myGraph.setEdgeWeight(e15, edges.get(14).getDistance());
		
		DijkstraShortestPath<Customer, DefaultWeightedEdge> dijk = new DijkstraShortestPath<>(myGraph, customerList.get(0), customerList.get(1));

		//Shortest path and the list of edges visited is retrieved from the dijkstras object
		Double shortestPath = dijk.getPathLength();
		List<DefaultWeightedEdge> edgeList = dijk.getPathEdgeList();

		//Prints out the length of the shortest path
		System.out.println("\nThe shortest path found has length: " + shortestPath);

		//Prints out the shortest route
		System.out.println("The following route was found to be the shortest:");

		for(DefaultWeightedEdge edge : edgeList){
			System.out.println(edge);
		}
		System.out.println("");
		System.out.println(dijk);
		
	}
	
	public static ArrayList<Customer> createRoute() // should take 2 Customers
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
