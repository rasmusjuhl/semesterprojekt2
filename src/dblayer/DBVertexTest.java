package dblayer;
import java.util.ArrayList;

import modellayer.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class DBVertexTest {


	@Test
	public void test() {
		DBVertex dbVer = new DBVertex();
		ArrayList<Vertex> list = dbVer.findAllVertex();
		for(int i = 0; i < list.size(); i++)
		{
			System.out.println("Fra kunde: " + list.get(i).getFromID());
			System.out.println("Til kunde: " + list.get(i).getToID());
			System.out.println("Er der " + list.get(i).getDistanceTo() + " km.");
			System.out.println("");
		}
		
		
		
//		fail("Not yet implemented");
	}

}
