import java.util.ArrayList;

import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.*;

import java.util.List;


public class Main {

	public static void main(String[] args) {
		ListenableUndirectedWeightedGraph<String, DefaultWeightedEdge> myGraph = new ListenableUndirectedWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		String s1 = "1";
		String s2 = "2";
		String s3 = "3";
		String s4 = "4";
		String s5 = "5";
		String s6 = "6";
		String s7 = "7";
		String s8 = "8";
		String s9 = "9";
		String s10 = "10";
		String s11 = "11";
		String s12 = "12";
		
		myGraph.addVertex(s1);
		myGraph.addVertex(s2);
		myGraph.addVertex(s3);
		myGraph.addVertex(s4);
		myGraph.addVertex(s5);
		myGraph.addVertex(s6);
		myGraph.addVertex(s7);
		myGraph.addVertex(s8);
		myGraph.addVertex(s9);
		myGraph.addVertex(s10);
		myGraph.addVertex(s11);
		myGraph.addVertex(s12);
		
		DefaultWeightedEdge e1 = myGraph.addEdge(s1, s2);
		DefaultWeightedEdge e2 = myGraph.addEdge(s10, s12);
		DefaultWeightedEdge e3 = myGraph.addEdge(s11, s12);
		DefaultWeightedEdge e4 = myGraph.addEdge(s2, s3);
		DefaultWeightedEdge e5 = myGraph.addEdge(s3, s4);
		DefaultWeightedEdge e6 = myGraph.addEdge(s3, s8);
		DefaultWeightedEdge e7 = myGraph.addEdge(s4, s5);
		DefaultWeightedEdge e8 = myGraph.addEdge(s4, s7);
		DefaultWeightedEdge e9 = myGraph.addEdge(s4, s9);
		DefaultWeightedEdge e10 = myGraph.addEdge(s5, s10);
		DefaultWeightedEdge e11 = myGraph.addEdge(s5, s6);
		DefaultWeightedEdge e12 = myGraph.addEdge(s6, s7);
		DefaultWeightedEdge e13 = myGraph.addEdge(s8, s11);
		DefaultWeightedEdge e14 = myGraph.addEdge(s8, s9);
		DefaultWeightedEdge e15 = myGraph.addEdge(s9, s10);
		
		myGraph.setEdgeWeight(e1, 186.54);
		myGraph.setEdgeWeight(e2, 459.01);
		myGraph.setEdgeWeight(e3, 231.80);
		myGraph.setEdgeWeight(e4, 89.83);
		myGraph.setEdgeWeight(e5, 80.69);
		myGraph.setEdgeWeight(e6, 196.47);
		myGraph.setEdgeWeight(e7, 158.02);
		myGraph.setEdgeWeight(e8, 90.97);
		myGraph.setEdgeWeight(e9, 200.12);
		myGraph.setEdgeWeight(e10, 195.28);
		myGraph.setEdgeWeight(e11, 99.13);
		myGraph.setEdgeWeight(e12, 155.42);
		myGraph.setEdgeWeight(e13, 451.12);
		myGraph.setEdgeWeight(e14, 79.66);
		myGraph.setEdgeWeight(e15, 157.21);
		
		DijkstraShortestPath<String, DefaultWeightedEdge> dijk = new DijkstraShortestPath<>(myGraph, s1, s12);
		
		Double shortestPath = dijk.getPathLength();
		ArrayList<DefaultWeightedEdge> edgeList = (ArrayList<DefaultWeightedEdge>) dijk.getPathEdgeList();
		
		System.out.println("fra s1 til s12");
		System.out.println("The shortest path found has length: " + shortestPath);
		
		System.out.println("The following route was found to be the shortest:");
		
		for(DefaultWeightedEdge edge : edgeList){
			System.out.println(edge);
		}
		
		System.out.println(dijk.getPathEdgeList());
		
		// 1
		System.out.println("\nforsøg nr 2");
		System.out.println("fra s12 til s1");
		DijkstraShortestPath<String, DefaultWeightedEdge> dijk1 = new DijkstraShortestPath<>(myGraph, s12, s1);
		
		Double shortestPath1 = dijk1.getPathLength();
		ArrayList<DefaultWeightedEdge> edgeList1 = (ArrayList<DefaultWeightedEdge>) dijk1.getPathEdgeList();
		
		System.out.println("The shortest path found has length: " + shortestPath1);
		
		System.out.println("The following route was found to be the shortest:");
		
		for(DefaultWeightedEdge edge : edgeList1){
			System.out.println(edge);
		}
		
		System.out.println(dijk1.getPathEdgeList());
	}
	

}