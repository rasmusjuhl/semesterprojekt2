import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JApplet;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.ListenableUndirectedWeightedGraph;

public class JGraphAdapter extends JApplet {
    private static final Color     DEFAULT_BG_COLOR = Color.decode( "#FAFBFF" );
    private static final Dimension DEFAULT_SIZE = new Dimension( 530, 320 );

    // 
    private JGraphModelAdapter m_jgAdapter;

    /**
     * @see java.applet.Applet#init().
     */
    public void init(  ) {
        // create a JGraphT graph
		ListenableUndirectedWeightedGraph<String, MyEdge> myGraph = new ListenableUndirectedWeightedGraph<String, MyEdge>(MyEdge.class);


        // create a visualization using JGraph, via an adapter
        m_jgAdapter = new JGraphModelAdapter( myGraph );

        JGraph jgraph = new JGraph( m_jgAdapter );

        adjustDisplaySettings( jgraph );
        getContentPane(  ).add( jgraph );
        resize( DEFAULT_SIZE );

        // add some sample data (graph manipulated via JGraphT)
        
		String s1 = "Rasmus";
		String s2 = "Søren";
		String s3 = "Silas";
		String s4 = "Kasper";
		
        myGraph.addVertex( s1 );
        myGraph.addVertex( s2 );
        myGraph.addVertex( s3 );
        myGraph.addVertex( s4 );

        MyEdge e1 = myGraph.addEdge( s1, s2 );
        MyEdge e2 = myGraph.addEdge( s1, s3 );
        MyEdge e3 = myGraph.addEdge( s2, s4 );
        MyEdge e4 = myGraph.addEdge( s3, s4 );

		myGraph.setEdgeWeight(e1, 100);
		myGraph.setEdgeWeight(e2, 75);
		myGraph.setEdgeWeight(e3, 50);
		myGraph.setEdgeWeight(e4, 25);
		
        // position vertices nicely within JGraph component
        positionVertexAt( s1, 150, 130 );
        positionVertexAt( s2, 100, 50 );
        positionVertexAt( s3, 300, 150);
        positionVertexAt( s4, 70, 200 );

        // that's all there is to it!...
    }


    private void adjustDisplaySettings( JGraph jg ) {
        jg.setPreferredSize( DEFAULT_SIZE );

        Color  c        = DEFAULT_BG_COLOR;
        String colorStr = null;

        try {
            colorStr = getParameter( "bgcolor" );
        }
         catch( Exception e ) {}

        if( colorStr != null ) {
            c = Color.decode( colorStr );
        }

        jg.setBackground( c );
    }

    public static class MyEdge extends DefaultWeightedEdge {
        @Override
        public String toString() {
            return String.valueOf(getWeight());
        }
    }

    private void positionVertexAt( Object vertex, int x, int y ) {
        DefaultGraphCell cell = m_jgAdapter.getVertexCell( vertex );
        Map              attr = cell.getAttributes(  );
        Rectangle2D        b    = GraphConstants.getBounds( attr );

        GraphConstants.setBounds( attr, new Rectangle( x, y, b.OUT_RIGHT, b.OUT_TOP ) );

        Map cellAttr = new HashMap(  );
        cellAttr.put( cell, attr );
        m_jgAdapter.edit( cellAttr, null, null, null );
    }
}