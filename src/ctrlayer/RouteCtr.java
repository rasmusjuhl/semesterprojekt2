package ctrlayer;
import java.util.ArrayList;

import dblayer.*;
import modellayer.*;

import org.jgraph.JGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.*;

import java.util.List;

import javax.swing.JFrame;

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
		ListenableUndirectedWeightedGraph<Customer, DefaultWeightedEdge> myGraph = 
				new ListenableUndirectedWeightedGraph<Customer, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		ArrayList<Customer> customerList = cCtr.findAllCustomers(); 
		ArrayList<Edge> edges = dbe.findAllEdges();
		
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
			DefaultWeightedEdge curEdge = myGraph.addEdge(cus1,cus2);
			myGraph.setEdgeWeight(curEdge, weight);
		}
		JFrame frame = new JFrame();
		frame.setSize(800, 800);
		JGraph jgraph = new JGraph( new JGraphModelAdapter( myGraph ) );
		frame.getContentPane().add(jgraph);
		frame.setVisible(true);
		while (true){
			Thread.sleep(2000);
		}
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
