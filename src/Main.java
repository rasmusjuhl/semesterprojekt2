import org.jgrapht.graph.*;


public class Main {

	public static void main(String[] args) {
		ListenableUndirectedWeightedGraph<String, DefaultWeightedEdge> myGraph = new ListenableUndirectedWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		String s1 = "Rasmus";
		String s2 = "Søren";
		String s3 = "Silas";
		String s4 = "Kasper";
		
		myGraph.addVertex(s1);
		myGraph.addVertex(s2);
		myGraph.addVertex(s3);
		myGraph.addVertex(s4);
		
		myGraph.addEdge(s1, s2);
		myGraph.addEdge(s1, s3);
		myGraph.addEdge(s2, s4);
		myGraph.addEdge(s3, s4);
	}
	

}