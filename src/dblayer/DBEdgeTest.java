package dblayer;
import modellayer.*;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DBEdgeTest {

	@Test
	public void test() {
		DBEdge dbe = new DBEdge();
		DBCustomer dbc = new DBCustomer();
		Customer a = dbc.findCustomer("1");
		Customer b = dbc.findCustomer("2");
		Edge e = dbe.findEdge(a, b);
		
		if(e != null)
		{
			System.out.println("DBEdge works");
			System.out.println(e.getPointA().getName());
			System.out.println(e.getPointB().getName());
			System.out.println(e.getDistance());
		}
		else
		{
		fail("Edge not found");
		}
	}

}
