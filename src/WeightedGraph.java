import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jgrapht.ListenableGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.ListenableUndirectedWeightedGraph;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.swing.mxGraphComponent;

public class WeightedGraph {

    private static void createAndShowGui() {
        JFrame frame = new JFrame("DemoGraph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ListenableGraph<String, MyEdge> g = buildGraph();
        JGraphXAdapter<String, MyEdge> graphAdapter = 
                new JGraphXAdapter<String, MyEdge>(g);

        mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());

        frame.add(new mxGraphComponent(graphAdapter));

        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
    }

    public static class MyEdge extends DefaultWeightedEdge {
        @Override
        public String toString() {
            return String.valueOf(getWeight());
        }
    }

    public static ListenableGraph<String, MyEdge> buildGraph() {
        ListenableUndirectedWeightedGraph<String, MyEdge> myGraph = 
            new ListenableUndirectedWeightedGraph<String, MyEdge>(MyEdge.class);
        
		String s1 = "Rasmus";
		String s2 = "Søren";
		String s3 = "Silas";
		String s4 = "Kasper";
				
		myGraph.addVertex(s1);
		myGraph.addVertex(s2);
		myGraph.addVertex(s3);
		myGraph.addVertex(s4);
			
		MyEdge e1 = myGraph.addEdge(s1, s2);
		myGraph.setEdgeWeight(e1, 100);
				
		MyEdge e2 = myGraph.addEdge(s1, s3);
		myGraph.setEdgeWeight(e2, 75);

		MyEdge e3 = myGraph.addEdge(s2, s4);
		myGraph.setEdgeWeight(e3, 50);
		
		MyEdge e4 = myGraph.addEdge(s3, s4);
		myGraph.setEdgeWeight(e4, 25);
						

		
		DijkstraShortestPath<String, MyEdge> dijk = new DijkstraShortestPath<>(myGraph, s1, s4);
		
		Double shortestPath = dijk.getPathLength();
		List<MyEdge> edgeList = dijk.getPathEdgeList();
		
		System.out.println("The shortest path found has length: " + shortestPath);
		
		System.out.println("The following route was found to be the shortest:");
		
		for(MyEdge edge : edgeList){			
			System.out.println("Fra: " + myGraph.getEdgeSource(edge));
			System.out.println("Til: " + myGraph.getEdgeTarget(edge));
			System.out.println(edge);
		}

        return myGraph;
    }
}