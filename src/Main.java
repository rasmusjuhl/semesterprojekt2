import java.util.ArrayList;

import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.*;

import java.util.List;


public class Main {

	public static void main(String[] args) {
		
		//Incredibly convoluted constructor that involves telling Jgrapht what object should make out the vertices and what edge type to use.
		ListenableUndirectedWeightedGraph<String, DefaultWeightedEdge> myGraph = new ListenableUndirectedWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		//Strings are created to represent vertices
		String s1 = "Rasmus";
		String s2 = "Søren";
		String s3 = "Silas";
		String s4 = "Kasper";
		
		//Vertices are added to the graph
		myGraph.addVertex(s1);
		myGraph.addVertex(s2);
		myGraph.addVertex(s3);
		myGraph.addVertex(s4);
		
		//Edges are created and linked to vertices
		DefaultWeightedEdge e1 = myGraph.addEdge(s1, s2);
		DefaultWeightedEdge e2 = myGraph.addEdge(s1, s3);
		DefaultWeightedEdge e3 = myGraph.addEdge(s2, s4);
		DefaultWeightedEdge e4 = myGraph.addEdge(s3, s4);
		
		//Weights are added to edges
		myGraph.setEdgeWeight(e1, 100);
		myGraph.setEdgeWeight(e2, 75);
		myGraph.setEdgeWeight(e3, 50);
		myGraph.setEdgeWeight(e4, 25);
		
		//An instance of Dijkstras is created and run
		DijkstraShortestPath<String, DefaultWeightedEdge> dijk = new DijkstraShortestPath<>(myGraph, s1, s4);
		
		//Shortest path and the list of edges visited is retrieved from the dijkstras object
		Double shortestPath = dijk.getPathLength();
		List<DefaultWeightedEdge> edgeList = dijk.getPathEdgeList();
		
		//Prints out the length of the shortest path
		System.out.println("The shortest path found has length: " + shortestPath);
		
		//Prints out the shortest route
		System.out.println("The following route was found to be the shortest:");
		
		for(DefaultWeightedEdge edge : edgeList){
			System.out.println(edge);
		}
		
	}
	

}