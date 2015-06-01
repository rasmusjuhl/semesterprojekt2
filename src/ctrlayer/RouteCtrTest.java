package ctrlayer;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import dblayer.*;
import modellayer.*;

import org.jgrapht.Graph;
import org.jgrapht.graph.ListenableUndirectedWeightedGraph;
import org.junit.Test;

public class RouteCtrTest {

	@Test
	public void testCreateRoute() {
		User user = new User();
		DBCustomer dbCust = new DBCustomer();
		DBEdge dbEdge = new DBEdge();
		ArrayList<Customer> custList = dbCust.getAllCustomers();
		List<Edge> edgeList = dbEdge.findAllEdges();
		ListenableUndirectedWeightedGraph<Customer, Edge> myGraph = RouteCtr.createGraph(custList, edgeList);
		Route route = RouteCtr.createRoute(user, myGraph, custList.get(0), custList.get(1));
		if(route.getRouteLength() == Double.POSITIVE_INFINITY)
			fail("Ruten er ugyldig!");
		else
			System.out.println("Success! The route is valid!");
	}
}
